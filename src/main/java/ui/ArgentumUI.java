package ui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Collections;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JCheckBoxMenuItem;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.SwingWorker;
import javax.swing.border.LineBorder;
import javax.swing.text.MaskFormatter;

import argentum.SerieTemporal;
import indicadores.IndicadorAbertura;
import indicadores.IndicadorFechamento;
import indicadores.MediaMovelPonderada;
import indicadores.MediaMovelSimples;
import model.Candlestick;
import model.Negocio;
import reader.CandlestickFactory;

public class ArgentumUI {
	private JPanel painelBotoes;
	private JFrame janela;
	private JPanel painelPrincipal;
	private JTable tabela;
	private JTabbedPane abas;
	private JFormattedTextField campoDataInicio;
	private JCheckBoxMenuItem mediaSimplesFechamento;
	private JCheckBoxMenuItem mediaSimplesAbertura;
	private JCheckBoxMenuItem mediaPonderadaAbertura;
	private JCheckBoxMenuItem mediaPonderadaFechamento;

	public static void main(String[] args) {
		new ArgentumUI().montaTela();
	}

	public void montaTela() {
		montaJanela();
		montaMenu();
		montaPainelPrincipal();
		montaAbas();
		montaTitulo();
		montaTabelaComScroll();
		montaPainelBotoes();
		montaCampoData();
		montaBotaoCarregar();
		montaBotaoSair();

		mostraJanela();
	}

	private void montaJanela() {
		janela = new JFrame("Argentum");
		janela.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

	}

	private void montaPainelPrincipal() {

		painelPrincipal = new JPanel();
		painelPrincipal.setLayout(new BorderLayout());
		janela.add(painelPrincipal);

	}

	private void montaBotaoCarregar() {
		JButton botaoCarregar = new JButton("Carregar XML");
		botaoCarregar.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				new CarregaXMLWorker().execute();

			}
		});
		painelBotoes.add(botaoCarregar);
	}

	private void montaBotaoSair() {
		JButton botaoSair = new JButton("Sair");
		botaoSair.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				System.exit(0);

			}
		});

		painelBotoes.add(botaoSair);

	}

	public void mostraJanela() {
		janela.pack();
		janela.setSize(540, 540);
		janela.setVisible(true);
	}

	public void montaTabelaComScroll() {

		tabela = new JTable();
		tabela.setBorder(new LineBorder(Color.black));
		tabela.setGridColor(Color.black);
		tabela.setShowGrid(true);
		JScrollPane scroll = new JScrollPane();
		scroll.getViewport().setBorder(null);
		scroll.getViewport().add(tabela);
		scroll.setSize(450, 450);
//		painelPrincipal.add(scroll,BorderLayout.CENTER);
		abas.setComponentAt(0, scroll);

	}

	private void montaTitulo() {
		JLabel titulo = new JLabel("Lista de Negócios");
		titulo.setFont(new Font("Verdana", Font.BOLD, 25));
		titulo.setForeground(new Color(50, 50, 100));
		titulo.setHorizontalAlignment(SwingConstants.CENTER);
		painelPrincipal.add(titulo, BorderLayout.NORTH);
	}

	private void montaPainelBotoes() {
		painelBotoes = new JPanel(new GridLayout());
		painelPrincipal.add(painelBotoes, BorderLayout.SOUTH);
	}

	private void montaAbas() {
		abas = new JTabbedPane();
		abas.addTab("Tabela de Negócios", null);
		abas.addTab("Gráfico de arestas", null);
		abas.addTab("Gráfico de CandleStick", null);
		painelPrincipal.add(abas);
	}

	private void carregarDados(List<Negocio> negocios) {
//		List<Negocio> negocios = new EscolheXML().escolher();
//		filtraPorData(negocios);
		Collections.sort(negocios);
		// atualiza tabela
		ReflectionTableModel model = new ReflectionTableModel(negocios);
		this.tabela.setModel(model);
		// gera SerieTemporal
		CandlestickFactory candlestickFactory = new CandlestickFactory();
		List<Candlestick> candles = candlestickFactory.constroiCandles(negocios);
		SerieTemporal serie = new SerieTemporal(candles);
		// mostra grafico
		GeradorDeGrafico geradorDeGrafico = new GeradorDeGrafico(serie, 2, serie.getTotal() - 1);
		GeradorDeGraficoCandlestick geradorDeGraficoCandlestick = new GeradorDeGraficoCandlestick(serie);
		geradorDeGrafico.criaGrafico("Média Móvel Simples");
//		geradorDeGrafico.plotaIndicador(new MediaMovelSimples(new IndicadorFechamento()));
		if (mediaSimplesFechamento.isSelected()) {
			geradorDeGrafico.plotaIndicador(new MediaMovelSimples(new IndicadorFechamento()));

		}
		if (mediaSimplesAbertura.isSelected()) {
			geradorDeGrafico.plotaIndicador(new MediaMovelSimples(new IndicadorAbertura()));

		}
		if (mediaPonderadaAbertura.isSelected()) {
			geradorDeGrafico.plotaIndicador(new MediaMovelPonderada(new IndicadorAbertura()));

		}
		if (mediaPonderadaFechamento.isSelected()) {
			geradorDeGrafico.plotaIndicador(new MediaMovelPonderada(new IndicadorFechamento()));

		}

		geradorDeGraficoCandlestick.criaGrafico("grafico Candle");

		JPanel grafico = geradorDeGrafico.getPanel();
		JPanel graficoCandlestick = geradorDeGraficoCandlestick.getPanel();
		this.abas.setComponentAt(1, grafico);
		this.abas.setComponentAt(2, graficoCandlestick);
	}

	private void montaCampoData() {
		JLabel titulo = new JLabel("FILTRO DE DATA : ");
		titulo.setFont(new Font("Verdana", Font.BOLD, 12));
		titulo.setHorizontalAlignment(SwingConstants.CENTER);
		titulo.setForeground(new Color(50, 50, 100));
		painelBotoes.add(titulo);
		try {
			MaskFormatter mascara = new MaskFormatter("##/##/####");
			mascara.setPlaceholderCharacter('_');
			campoDataInicio = new JFormattedTextField(mascara);
			campoDataInicio.setHorizontalAlignment(SwingConstants.CENTER);
			painelBotoes.add(campoDataInicio);
		} catch (ParseException e) {
			e.printStackTrace();
		}
	}

	private void filtraPorData(List<Negocio> negocios) {
		try {
			String valor = (String) campoDataInicio.getValue();
			SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
			Date dataInicio = formato.parse(valor);
			Iterator<Negocio> it = negocios.iterator();
			while (it.hasNext()) {
				if (it.next().getData().getTime().before(dataInicio)) {
					it.remove();
				}
			}
		} catch (Exception e) {
			campoDataInicio.setValue(null);
		}
	}

	private void montaMenu() {
		JMenuBar menuBar = new JMenuBar();
		janela.setJMenuBar(menuBar);
		JMenu menuIndicadores = new JMenu("Indicadores");
		menuBar.add(menuIndicadores);
		mediaSimplesFechamento = new JCheckBoxMenuItem("Média móvel simples de fechamento");

		menuIndicadores.add(mediaSimplesFechamento);

		mediaSimplesAbertura = new JCheckBoxMenuItem("Média móvel simples de abertura");
		menuIndicadores.add(mediaSimplesAbertura);

		mediaPonderadaAbertura = new JCheckBoxMenuItem("Média móvel ponderada de abertura");
		menuIndicadores.add(mediaPonderadaAbertura);

		mediaPonderadaFechamento = new JCheckBoxMenuItem("Média móvel ponderada de fechamento");
		menuIndicadores.add(mediaPonderadaFechamento);
	}

	private class CarregaXMLWorker extends SwingWorker<List<Negocio>, Void> {

		@Override
		protected List<Negocio> doInBackground() throws Exception {
			List<Negocio> negocios = new EscolheXML().escolher();
			filtraPorData(negocios);
			return negocios;
		}

		@Override
		protected void done() {
			try {
				carregarDados(get());
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

	}
}
