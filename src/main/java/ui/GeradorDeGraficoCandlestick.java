package ui;

import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JPanel;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.ChartUtils;
import org.jfree.chart.JFreeChart;
import org.jfree.data.xy.DefaultOHLCDataset;
import org.jfree.data.xy.OHLCDataItem;

import argentum.SerieTemporal;
import model.Candlestick;

public class GeradorDeGraficoCandlestick {

	private SerieTemporal serie;

	private DefaultOHLCDataset dataset;
	private JFreeChart grafico;

	public GeradorDeGraficoCandlestick(SerieTemporal serie) {
		this.serie = serie;

	}

	public void criaGrafico(String titulo) {
		List<Candlestick> candles = this.serie.getCandles();
		List<OHLCDataItem> data = new ArrayList<OHLCDataItem>();

		for (Candlestick candlestick : candles) {
			data.add(new OHLCDataItem(candlestick.getData().getTime(), candlestick.getAbertura(),
					candlestick.getMaximo(), candlestick.getMinimo(), candlestick.getFechamento(),
					candlestick.getVolume()));

		}

		OHLCDataItem[] dataArray = new OHLCDataItem[data.size()];

		dataArray = data.toArray(dataArray);
		dataset = new DefaultOHLCDataset("candle", dataArray);
		this.grafico = ChartFactory.createCandlestickChart(titulo, "Dias", "Preco", dataset, true);
		grafico.setAntiAlias(true);
	}

	// ------------------- IMPLEMENTACAO SIMILAR USANDO
	// DefaultHighLowDataset COMO DATASET
//	public void criaGrafico(String titulo) {
//		List<Candlestick> candles = this.serie.getCandles();
//		double[] abertura= new double[candles.size()];   
//		double[] fechamento= new double[candles.size()]; 
//		double[] minimo= new double[candles.size()];     
//		double[] maximo= new double[candles.size()];     
//		double[] volume= new double[candles.size()];     
//		Date[] data= new Date[candles.size()]; 
//		for (int i = 0; i < candles.size(); i++) {
//			abertura[i] = candles.get(i).getAbertura();
//			fechamento[i] = candles.get(i).getFechamento();
//			minimo[i] = candles.get(i).getMinimo();
//			maximo[i] = candles.get(i).getMaximo();
//			volume[i] = candles.get(i).getVolume();
//			data[i] = candles.get(i).getData().getTime();
//		}
//		this.dataset = new DefaultHighLowDataset("", data, maximo, minimo,
//				abertura, fechamento, volume);
//		
//		
//		this.grafico = ChartFactory.createCandlestickChart(titulo, "Dias", "Preco", dataset, true);
//		
//	}

	public void salvar(OutputStream out) throws IOException {
		ChartUtils.writeChartAsPNG(out, grafico, 500, 350);

	}

	public JPanel getPanel() {
		return new ChartPanel(grafico);
	}

}
