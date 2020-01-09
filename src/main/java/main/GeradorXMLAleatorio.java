package main;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.List;
import java.util.Random;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;

import model.Negocio;
import ui.ArgentumUI;

public class GeradorXMLAleatorio {

	public static void main(String[] args) {

		// -----MONTA JANELA PRINCIPAL

		new ArgentumUI().montaTela();

		// -----------------------------

		// ------------GERA XML ALEATORIO E IMPRIME NO CONSOLE

		Calendar data = Calendar.getInstance();
		Random random = new Random(123);
		List<Negocio> negocios = new ArrayList<Negocio>();
		double valor = 40;
		int quantidade = 1000;
		for (int i = 0; i < 30; i++) {
			for (int x = 0; x < random.nextInt(311); x++) {
				valor += (random.nextInt(200) - 100) / 100.0;
				quantidade += (random.nextInt(200) - 100);
				Negocio n = new Negocio(valor, quantidade, data);
				negocios.add(n);
			}
			data = (Calendar) data.clone();
			data.add(Calendar.DAY_OF_YEAR, 1);
		}
		Collections.sort(negocios);
		XStream stream = new XStream(new DomDriver());
		stream.setMode(XStream.NO_REFERENCES);
		stream.alias("negocio", Negocio.class);
//		System.out.println(stream.toXML(negocios));
		
		
		//--------------SALVA XML NA AREA DE TRABALHO
		
		try {
			FileOutputStream arquivoXML = new FileOutputStream("C:\\Users\\matheusws\\Desktop\\teste.xml");
			stream.toXML(negocios, arquivoXML);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		// -----------------------------------


		// ------JANELA SIMPLES ABRIR DOCUMENTO

//		JButton botao = new JButton("Clique aqui");
//		new EscolheXML().escolher();
//
//		JButton botaoCarregar = new JButton("Carregar XML");
//		JButton botaoSair = new JButton("Sair");
//		JPanel panel = new JPanel();
//		panel.add(botaoCarregar);
//		panel.add(botaoSair);
//		JFrame frame = new JFrame("Argentum");
//		frame.add(panel);
//		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//		frame.pack();
//		frame.setVisible(true);

		// ---------------------------------

		// ----------CRIA DATASET PARA A GERACAO DE GRAFICO COM NEGOCIOS
		
//		String linha, coluna;
//		linha = "maximo";
//		coluna = "dia ";
//		int i = 1;
//		DefaultCategoryDataset ds = new DefaultCategoryDataset();
//		for (Negocio negocio : negocios) {
//			ds.addValue(negocio.getPreco(), linha, coluna + i);
//			i++;
//		}

		// ---------------------------------

		// -------------SALVA GRAFICO EM ARQUIVO
//		JFreeChart grafico = ChartFactory.createLineChart("Meu Grafico", "Dia", "Valor", ds, PlotOrientation.VERTICAL,
//				true, true, false);
//		try {
//			OutputStream arquivo = new FileOutputStream("grafico.png");
//			ChartUtils.writeChartAsPNG(arquivo, grafico, 550, 400);
//			arquivo.close();
//		} catch (FileNotFoundException e) {
//			e.printStackTrace();
//		} catch (IOException e) {
//			e.printStackTrace();
//		}

		// ---------------------------------

		// -------------SALVA GRAFICO EM ARQUIVO COM FUCAO criaserie()

//		SerieTemporal serie = SerieTemporal.criaSerie(1.0,2.0,3.0,4.0,5.0,6.0,7.0,8.0,8.0,9.0,9.0,4.0,3.0,2.0,2.0,2.0,2.0);
//		GeradorDeGrafico g = new GeradorDeGrafico(serie, 2, 15);
//		g.criaGrafico("Meu grafico");
//		g.plotaIndicador(new MediaMovelSimples());
//		try {
//			g.salvar(new FileOutputStream("saida.png"));
//		} catch (FileNotFoundException e) {
//			e.printStackTrace();
//		} catch (IOException e) {
//			e.printStackTrace();
//		}

		// ---------------------------------

		// -------------CRIA GRAFICO COM 2 INIDICADORES
		// SOBREPOSTOS E EXIBE EM JANELA
//		SerieTemporal serie = SerieTemporal.criaSerie(1, 2, 3, 4, 5, 6, 7, 8, 8, 9, 9, 4, 3, 2, 1, 2, 2, 4, 5, 6, 7, 8,
//				9, 10, 11, 10, 6, 3, 2, 6, 7, 8, 9);
//
//		GeradorDeGrafico g = new GeradorDeGrafico(serie, 3, 32);
//		g.criaGrafico("Media movel simples do fechamento das acoes");
//		g.plotaIndicador(new MediaMovelSimples(new IndicadorFechamento()));
//		g.plotaIndicador(new MediaMovelSimples(new IndicadorAbertura()));
//
//		JFrame frame = new JFrame("Minha janela");
//		frame.add(g.getPanel());
//		frame.pack();
//		frame.setVisible(true);
		// ---------------------------------

		// ---------------CRIA GRAFICO CANDLESTICK
//		CandlestickFactory candlestickFactory = new CandlestickFactory();
//		List<Candlestick> candles = candlestickFactory.constroiCandles(negocios);
//		SerieTemporal serie = new SerieTemporal(candles);
//
//		GeradorDeGraficoCandlestick g = new GeradorDeGraficoCandlestick(serie);
//		g.criaGrafico("Media movel simples do fechamento das acoes");
//		JFrame frame = new JFrame("Minha janela");
//		frame.add(g.getPanel());
//		frame.pack();
//		frame.setVisible(true);
		// ---------------------------------

	}

}
