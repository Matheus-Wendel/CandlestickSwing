package indicadores;

import argentum.SerieTemporal;

public class IndicadorAbertura implements Indicador {

	public double calcula(int posicao, SerieTemporal serie) {

		return serie.getCandle(posicao).getAbertura();
	}

	@Override
	public String toString() {
		return "Abertura ";
	}

}
