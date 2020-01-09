package indicadores;

import argentum.SerieTemporal;

public class MediaMovelPonderada implements Indicador {

	private final Indicador indicador;
	
	
	public MediaMovelPonderada(Indicador indicador) {
		this.indicador = indicador;
	}

	
	
	
	public double calcula(int posicao, SerieTemporal serie) {
		double soma = 0.0;
		int peso = 1;
		for (int i = posicao - 2; i <= posicao; i++) {
			soma += indicador.calcula(i, serie) * peso;
			peso++;
		}
		return soma / 6;

	}
	@Override
	public String toString() {
		return "Media Movel Ponderada "+this.indicador.toString();
	}

}
