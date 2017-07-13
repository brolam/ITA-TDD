package brolam.com;

import brolam.com.models.Pontuacao;
import brolam.com.models.Usuario;

public class Placar {
	Armazenavel armazenavel;
	
	public Placar(Armazenavel armazenavel) {
		this.armazenavel = armazenavel;
	}

	public void registrarPontuacao(Usuario usuario, Pontuacao pontuacao) {
		armazenavel.salvarPontuacaoDoUsuario(usuario.getName(), pontuacao.getTipo(), pontuacao.getPontos());	
	}

}
