package indicadores;

import argentum.SerieTemporal;

public class IndicadorFechamento implements Indicador {

	public double calcula(int posicao, SerieTemporal serie) {
		return serie.getCandle(posicao).getFechamento();
	}

	@Override
	public String toString() {
		return "Fechamento";
	}

	
}
