package indicadores;


import org.apache.log4j.Logger;

import argentum.SerieTemporal;

public class MediaMovelSimples implements Indicador {
	private static final Logger logger = Logger.getLogger(MediaMovelSimples.class);
	private final Indicador indicador;
	
	
	public MediaMovelSimples(Indicador indicador) {
		this.indicador = indicador;
	}

	public double calcula(int posicao, SerieTemporal serie) {
		logger.info("Calculando média móvel simples para posição " + posicao);

		double soma = 0.0;
		for (int i = posicao - 2; i <= posicao; i++) {
			soma += indicador.calcula(i, serie);
		}
		return soma / 3;
	}

	@Override
	public String toString() {
		return "Media Movel Simples"+this.indicador.toString();
	}
	
	
	
	
}
