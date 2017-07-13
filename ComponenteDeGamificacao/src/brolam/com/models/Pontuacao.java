package brolam.com.models;

public class Pontuacao {
	private String tipo;
	private int pontos;

	public Pontuacao(String tipoDaPontuacao, int pontos) {
		this.tipo = tipoDaPontuacao;
		this.pontos = pontos;
	}
	
	public String getTipo() {
		return tipo;
	}
	
	public int getPontos() {
		return pontos;
	}
	
	@Override
	public String toString() {
		return String.format("%s: %s", getTipo(), getPontos());
	}
	
	@Override
	public boolean equals(Object obj) {
		return this.toString().equals(obj.toString());
	}

}
