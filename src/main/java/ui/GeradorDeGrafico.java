package ui;

import java.io.IOException;
import java.io.OutputStream;

import javax.swing.JPanel;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.ChartUtils;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;

import argentum.SerieTemporal;
import indicadores.Indicador;

public class GeradorDeGrafico {

	private SerieTemporal serie;
	private int comeco;
	private int fim;

	private DefaultCategoryDataset dataset;
	private JFreeChart grafico;

	public GeradorDeGrafico(SerieTemporal serie, int comeco, int fim) {
		this.serie = serie;
		this.comeco = comeco;
		this.fim = fim;
	}
	
	
	public void criaGrafico(String titulo) {
		this.dataset = new DefaultCategoryDataset();
		this.grafico = ChartFactory.createLineChart(titulo, "Dias", "Valores",
				dataset, PlotOrientation.VERTICAL, true, true, false);
		
	}
	
	public void plotaIndicador(Indicador indicador) {
		for (int i = comeco; i < fim; i++) {
			double valor = indicador.calcula(i, serie);
			dataset.addValue(valor, indicador.toString(), ""+i);
			
		}
		
	}
	
	
	public void salvar(OutputStream out) throws IOException {
		ChartUtils.writeChartAsPNG(out, grafico, 500, 350);
		
		
	}
	
	public JPanel getPanel () {
		return new ChartPanel(grafico);
		}
}
