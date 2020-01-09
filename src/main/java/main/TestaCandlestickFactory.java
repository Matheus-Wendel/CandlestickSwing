package main;

import java.util.Arrays;
import java.util.Calendar;
import java.util.List;

import model.Candlestick;
import model.Negocio;
import reader.CandlestickFactory;

public class TestaCandlestickFactory {

	public static void main(String[] args) {
		Calendar hoje = Calendar.getInstance();

		Negocio negocio1 = new Negocio(40.5, 5, null);
//		Negocio negocio2 = new Negocio(45.0, 100, hoje);
//		Negocio negocio3 = new Negocio(39.8, 100, hoje);
//		Negocio negocio4 = new Negocio(42.3, 100, hoje);

//		List<Negocio> negocios = Arrays.asList(negocio1, negocio2, negocio3, negocio4);
		List<Negocio> negocios = Arrays.asList(negocio1);

		CandlestickFactory fabrica = new CandlestickFactory();
		Candlestick candle = fabrica.constroiCandleParaData(hoje, negocios);

//		CandleBuilder builder = new CandleBuilder() ;
		
//		Candlestick geraCandle = builder.abertura(50.4).data(hoje).fechamento(42.7).maximo(100).minimo(0).volume(145000).geraCandle();
		System.out.println(candle);


	}
}
