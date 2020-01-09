import java.util.Calendar;

import org.junit.Test;

import model.Candlestick;

import static org.junit.Assert.*;

public class CandlestickTest {

	@Test(expected = IllegalArgumentException.class)
	public void precoMinimoMaiorQueMaximo() {
		new Candlestick(10, 20, 20, 10, 10000, Calendar.getInstance());
	}

	@Test(expected = IllegalArgumentException.class)
	public void dataNula() {
		new Candlestick(50, 50, 50, 100, 10000, null);
	}

	@Test
	public void testSeEhAltaQuandoAberturaIgualFechamento() {
		Candlestick candlestick = new Candlestick(10, 10, 0, 100, 20, Calendar.getInstance());


		assertEquals(true, candlestick.isAlta());
		
	}
}
