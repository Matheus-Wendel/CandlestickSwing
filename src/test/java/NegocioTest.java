import java.util.Calendar;

import org.junit.Assert;
import org.junit.Test;

import model.Negocio;

public class NegocioTest {
	
	@Test
	public void testDataMutavel() {
		Calendar calendar = Calendar.getInstance();
		calendar.set(Calendar.DAY_OF_MONTH,15);
		
		Negocio negocio = new Negocio(10, 5, calendar);
		
		negocio.getData().set(Calendar.DAY_OF_MONTH,20);
		
		Assert.assertEquals(15, negocio.getData().get(Calendar.DAY_OF_MONTH));
	}

	@Test(expected = IllegalArgumentException.class)
	public void testNegocioComDataNula() {
		@SuppressWarnings("unused")
		Negocio n = new Negocio(10, 5, null);
	}
	
}
