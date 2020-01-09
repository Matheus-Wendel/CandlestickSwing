package reader;

import java.io.Reader;
import java.util.List;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;

import model.Negocio;

public class LeitorXML {
	@SuppressWarnings("unchecked")
	public List<Negocio> carrega(Reader fonte) {
        XStream stream = new XStream(new DomDriver());
        stream.alias("negocio", Negocio.class);
         return (List<Negocio>) stream.fromXML(fonte);
	}

}
