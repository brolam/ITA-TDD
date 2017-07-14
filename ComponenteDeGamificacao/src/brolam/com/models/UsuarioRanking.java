package brolam.com.models;

public class UsuarioRanking implements Comparable<UsuarioRanking> {
	private Usuario usuario;
	private int pontos;

	public UsuarioRanking(Usuario usuario, int pontos) {
		this.usuario = usuario;
		this.pontos = pontos;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public int getPontos() {
		return pontos;
	}

	@Override
	public String toString() {
		return String.format("%s = %s",  getUsuario(), getPontos() );
	}

	@Override
	public boolean equals(Object obj) {
		return this.toString().equals(obj.toString());
	}

	@Override
	public int compareTo(UsuarioRanking outroUsuarioRanking) {
		String rankingEsseUsuario = String.format("%s:%s", this.getPontos(), this.getUsuario() );
		String rankingOutroUsuario = String.format("%s:%s", outroUsuarioRanking.getPontos(), outroUsuarioRanking.getUsuario() );
		return isOrdemDecrescente(rankingEsseUsuario, rankingOutroUsuario);
	}

	private int isOrdemDecrescente(String rankingDesseUsuario, String rankingOutroUsuario) {
		return rankingOutroUsuario.compareTo(rankingDesseUsuario);
	}

}
