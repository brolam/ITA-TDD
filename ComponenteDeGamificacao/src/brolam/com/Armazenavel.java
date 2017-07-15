package brolam.com;

public interface Armazenavel {
	void salvarPontuacaoDoUsuario(String usuarioNome, String tipoDaPontuacao, int pontos);
	int recuperarPontuacaoDoUsuario(String usuarioNome, String tipoDaPontuacao);
	String[] retornarUsuarios();
	String[] retornarTiposDePontuacao();
}