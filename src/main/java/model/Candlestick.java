package model;

import java.util.Calendar;

public class Candlestick {

	private final double abertura;
	private final double fechamento;
	private final double minimo;
	private final double maximo;
	private final double volume;
	private final Calendar data;
	
	
	public boolean isAlta() {
		return this.abertura<=this.fechamento;
	}
	
	public Boolean isBaixa() {
		return this.abertura> this.fechamento;
	}
	
	
	
	
	
	
	
	
	public Candlestick(double abertura, double fechamento, double minimo, double maximo, double volume, Calendar data) {

		if(minimo>maximo) {
			throw new IllegalArgumentException("Valor minimo maior que valor maximo");
		}
		
		if(data==null) {
			throw new IllegalArgumentException("Data nula");
		}
		
		
		this.abertura = abertura;
		this.fechamento = fechamento;
		this.minimo = minimo;
		this.maximo = maximo;
		this.volume = volume;
		this.data = data;
	}

	public double getAbertura() {
		return abertura;
	}

	public double getFechamento() {
		return fechamento;
	}

	public double getMinimo() {
		return minimo;
	}

	public double getMaximo() {
		return maximo;
	}

	public double getVolume() {
		return volume;
	}

	public Calendar getData() {
		return data;
	}

	@Override
	public String toString() {
		return "Candlestick [abertura=" + abertura + "\n fechamento=" + fechamento + "\n minimo=" + minimo + "\n maximo="
				+ maximo + "\n volume=" + volume + "\n data=" + data.get(Calendar.MONTH) + "]";
	}
	
	
	
	
	
	
}
