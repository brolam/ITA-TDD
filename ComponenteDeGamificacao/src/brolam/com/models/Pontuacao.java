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

}
