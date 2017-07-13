package brolam.com;

public interface Armazenavel {
	void salvarPontuacaoDoUsuario(String usuario, String tipoDaPontuacao, int pontos);
	int recuperarPontuacaoDoUsuario(String usuario, String tipoDaPontuacao);
	String[] retornarUsuarios();
	String[] retornarTiposDePontuacao();
}