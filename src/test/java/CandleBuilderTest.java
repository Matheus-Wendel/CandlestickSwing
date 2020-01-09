import org.junit.Test;

import builder.CandleBuilder;

public class CandleBuilderTest {
	
	@Test(expected = IllegalStateException.class)
	public void testGeracaoDeCandleSemTodosOsDadosNecessarios() {
		CandleBuilder builder = new CandleBuilder();
		builder.abertura(10).geraCandle();
		
	}

}
