package reader;

import static org.junit.Assert.assertEquals;

import java.io.StringReader;
import java.util.List;

import org.junit.Test;

import model.Negocio;

public class LeitorXMLTest {

	@Test
	public void testLeitorDeXmlCarregaListaDeNegocio() {
		String xmlDeTeste = "<list><negocio><preco>43.5</preco><quantidade>1000</quantidade><data><time>555454646</time></data></negocio></list>";
				
		LeitorXML leitor = new LeitorXML();
		List<Negocio> negocios = leitor.carrega(new StringReader(xmlDeTeste));
		
		
		assertEquals(1, negocios.size());
		assertEquals(43.5, negocios.get(0).getPreco(),000.1);
		assertEquals(1000, negocios.get(0).getQuantidade());
	}

}
