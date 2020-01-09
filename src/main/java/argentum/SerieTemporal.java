package argentum;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import model.Candlestick;

public class SerieTemporal {

	private final List<Candlestick> candles;

	public SerieTemporal(List<Candlestick> candles) {
		this.candles = candles;
	}

	public Candlestick getCandle(int i) {

		return candles.get(i);
	}

	public int getTotal() {
		return this.candles.size();
	}

	public static SerieTemporal criaSerie(double... valores) {
		List<Candlestick> candles = new ArrayList<Candlestick>();
		for (double d : valores) {
			candles.add(new Candlestick(d, d-10, d, d, 1000, Calendar.getInstance()));
		}
		return new SerieTemporal(candles);
	}

	public List<Candlestick> getCandles() {
		return candles;
	}

}
