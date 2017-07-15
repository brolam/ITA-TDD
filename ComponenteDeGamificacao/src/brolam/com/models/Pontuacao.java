package brolam.com.models;

public class Pontuacao {
	private String tipo;
	private int pontos;

	public Pontuacao(String tipoDaPontuacao, int pontos) {
		this.tipo = tipoDaPontuacao;
		this.pontos = pontos;
	}
	
	public String getTipo() {
		return this.tipo;
	}
	
	public int getPontos() {
		return this.pontos;
	}
	
	@Override
	public String toString() {
		return String.format("%s: %s", this.getTipo(), this.getPontos());
	}
	
	@Override
	public boolean equals(Object outraPontuacao) {
		return this.toString().equals(outraPontuacao.toString());
	}

}
