package brolam.com;

import java.util.ArrayList;
import java.util.Arrays;
import brolam.com.models.Pontuacao;
import brolam.com.models.Usuario;
import brolam.com.models.UsuarioRanking;

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

	public UsuarioRanking[] retornarUsuariosRanking(String tipoDaPontuacao) {
		ArrayList<UsuarioRanking> listUsuarioRankingTemporaria = new ArrayList<>();
		for(String usuarioNome: this.armazenavel.retornarUsuarios()){
			int pontos = this.armazenavel.recuperarPontuacaoDoUsuario(usuarioNome, tipoDaPontuacao);
			if ( pontos > 0 )
				listUsuarioRankingTemporaria.add(new UsuarioRanking(new Usuario(usuarioNome), pontos));
		}
		UsuarioRanking[] listaUsuarioRanking = new UsuarioRanking[listUsuarioRankingTemporaria.size()];
		listUsuarioRankingTemporaria.toArray(listaUsuarioRanking);
		Arrays.sort(listaUsuarioRanking);
		return listaUsuarioRanking;
	}

}
