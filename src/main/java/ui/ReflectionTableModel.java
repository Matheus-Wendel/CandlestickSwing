package ui;

import java.lang.reflect.Method;
import java.util.List;

import javax.swing.table.AbstractTableModel;

public class ReflectionTableModel extends AbstractTableModel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private List<?> lista;
	private Class<?> classe;

	public ReflectionTableModel(List<?> lista) {
		this.lista = lista;
		this.classe = lista.get(0).getClass();
	}

	public int getRowCount() {
		return lista.size();
	}

	public int getColumnCount() {
		int colunas = 0;
		for (Method m : classe.getDeclaredMethods()) {
			if (m.isAnnotationPresent(Coluna.class))
				colunas++;
		}
		return colunas;
	}

	public Object getValueAt(int row, int column) {
		try {
			Object objeto = lista.get(row);
			for (Method m : classe.getDeclaredMethods()) {
				Coluna c = m.getAnnotation(Coluna.class);
				if (c != null && c.posicao() == column) {
					return String.format(c.formato(), m.invoke(objeto));
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
@Override
	public String getColumnName(int column) {
		for (Method m : classe.getDeclaredMethods()) {
			Coluna c = m.getAnnotation(Coluna.class);
			if (c != null && c.posicao() == column) {
				return c.nome();
			}
		}
		return null;
	}

}
