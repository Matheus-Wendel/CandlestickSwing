package model;

import java.util.Calendar;

import ui.Coluna;

public class Negocio implements Comparable<Negocio> {
	private final Double preco;
	private final int quantidade;
	private final Calendar data;

	public Negocio(double preco, int quantidade, Calendar data) {

		if (data == null) {
			throw new IllegalArgumentException("data nao pode ser nula");
		}
		this.preco = preco;
		this.quantidade = quantidade;
		this.data = data;
	}

	
	@Coluna(posicao=3, formato="R$ %,#.2f", nome="Volume")
	public double getVolume() {
		return preco * quantidade;
	}
	@Coluna(posicao = 0,nome = "Preço", formato="R$ %.2f")
	public Double getPreco() {
		return preco;
	}

	@Coluna(posicao = 1,nome = "Quantidade")
	public int getQuantidade() {
		return quantidade;
	}

	@Coluna(posicao = 2,nome = "Data", formato = "%1$Td/%1$Tm/%1$TY")
	public Calendar getData() {
		return (Calendar) this.data.clone();
	}

	public int compareTo(Negocio o) {
		return this.getData().compareTo(o.getData());
//		return this.getPreco().compareTo(o.getPreco());
	}

}
