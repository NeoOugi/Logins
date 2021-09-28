package br.com.nero.modelos;

public class Linha extends Object {
	private String linha;

	public Linha(String linha) {
		super();
		this.linha = linha;
	}
	@Override
	public String toString() {
		return this.linha;
	}
}