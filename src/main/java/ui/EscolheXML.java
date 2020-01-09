package ui;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Collections;
import java.util.List;

import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;

import model.Negocio;
import reader.LeitorXML;

public class EscolheXML {

	
	public   List<Negocio> escolher() {
		try {
			String userDir = System.getProperty("user.home");
			JFileChooser fileChooser = new JFileChooser(userDir +"/Desktop");
			fileChooser.setFileFilter(new FileNameExtensionFilter("Apenas XML", "xml"));
			int retorno = fileChooser.showOpenDialog(null);
			
			if(retorno==JFileChooser.APPROVE_OPTION) {
				FileReader reader = new FileReader(fileChooser.getSelectedFile());
				 return new LeitorXML().carrega(reader);
//				List<Negocio> negocios = new LeitorXML().carrega(reader);
				
//				Negocio primeiroNegocio = negocios.get(0); 
//	             String msg = "Primeiro negócio do dia: " + primeiroNegocio.getPreco();
//	             JOptionPane.showMessageDialog(null, msg);
			}
		} catch (FileNotFoundException e) {
			
			e.printStackTrace();
		}
		return Collections.emptyList();
	}
}
