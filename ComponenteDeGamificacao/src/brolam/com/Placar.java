package brolam.com;

import java.util.ArrayList;

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

	public Pontuacao[] retornarPontuacaoDoUsuario(Usuario usuario) {
		String[] listaTipoDePontuacao = this.armazenavel.retornarTiposDePontuacao();
		ArrayList<Pontuacao> listaTemporariaDePontuacao = new ArrayList<>();
		for (String tipoDaPontuacao : listaTipoDePontuacao) {
			int pontos = this.armazenavel.recuperarPontuacaoDoUsuario(usuario.getName(), tipoDaPontuacao);
			if (pontos > 0)
				listaTemporariaDePontuacao.add(new Pontuacao(tipoDaPontuacao, pontos));
		}
		Pontuacao[] listaDePontuacaoDoUsuario = new Pontuacao[listaTemporariaDePontuacao.size()];
		listaTemporariaDePontuacao.toArray(listaDePontuacaoDoUsuario);
		return listaDePontuacaoDoUsuario;
	}

}
