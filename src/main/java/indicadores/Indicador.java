package indicadores;

import argentum.SerieTemporal;

public interface Indicador {
	
	double calcula(int posicao, SerieTemporal serie);

}
