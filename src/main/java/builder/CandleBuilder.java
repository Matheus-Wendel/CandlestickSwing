package builder;
import java.util.Arrays;
import java.util.Calendar;

import model.Candlestick;

public class CandleBuilder {
	
	private  double abertura;
	private  double fechamento;
	private  double minimo;
	private  double maximo;
	private  double volume;
	private  Calendar data;
	
	public CandleBuilder abertura(double abertura){
		
		this.abertura = abertura;
		return this;
	}
	public CandleBuilder fechamento(double fechamento){
		this.fechamento = fechamento;
		return this;
	}
	public CandleBuilder minimo(double minimo){
		this.minimo = minimo;
		return this;
	}
	public CandleBuilder maximo(double maximo){
		this.maximo = maximo;
		return this;
	}
	public CandleBuilder volume(double volume){
		this.volume = volume;
		return this;
	}
	public CandleBuilder data(Calendar data){
		this.data = data;
		return this;
	}
	
	@SuppressWarnings("unchecked")
	public Candlestick geraCandle() {
		if(Arrays.asList(abertura,fechamento,maximo,minimo,volume,data).contains(null)) {
			throw new IllegalStateException("valores nao foram atribuidos para todas as variaveis");
		}
		return new Candlestick(abertura, fechamento, minimo, maximo, volume, data);
		
	}
	
}
