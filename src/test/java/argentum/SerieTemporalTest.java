package argentum;

import java.util.Arrays;
import java.util.Calendar;
import java.util.List;

import org.junit.Test;

import model.Candlestick;
import model.Negocio;
import reader.CandlestickFactory;

public class SerieTemporalTest {

	@Test(expected = IndexOutOfBoundsException.class)
	public void testgetCandlePosicaoForaDaLista() {
		Calendar hoje = Calendar.getInstance();
		Negocio negocio1 = new Negocio(40.5, 100, hoje);
		Negocio negocio2 = new Negocio(45.0, 100, hoje);
		Negocio negocio3 = new Negocio(39.8, 100, hoje);
		Negocio negocio4 = new Negocio(42.3, 100, hoje);
		List<Negocio> negocios = Arrays.asList(negocio1, negocio2, negocio3, negocio4);
		CandlestickFactory fabrica = new CandlestickFactory();
		List<Candlestick> candles = fabrica.constroiCandles(negocios);
		SerieTemporal serie = new SerieTemporal(candles);
		
		serie.getCandle(10);
	}

}
