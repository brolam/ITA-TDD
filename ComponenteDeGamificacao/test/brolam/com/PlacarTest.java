package brolam.com;

import static org.junit.Assert.*;
import org.junit.Test;
import brolam.com.mocks.ArmazenamentoMock;
import brolam.com.mocks.ArmazenamentoMock.Simuladores;
import brolam.com.models.Pontuacao;
import brolam.com.models.Usuario;
import brolam.com.models.UsuarioRanking;

public class PlacarTest {

	@Test
	public void registrarPontuacaoDoUsuario() {
		ArmazenamentoMock armazenamentoMock = new ArmazenamentoMock();
		Placar placar = new Placar(armazenamentoMock);
		Usuario usuario = new Usuario("guerra");
		Pontuacao pontuacao = new Pontuacao("estrela", 10);
		placar.registrarPontuacao(usuario, pontuacao);
		assertTrue(armazenamentoMock.isPontuacaoRegistrada());
	}

	@Test
	public void retornarPontuacaoDoUsuario() {
		ArmazenamentoMock armazenamentoMock = new ArmazenamentoMock();
		Placar placar = new Placar(armazenamentoMock);
		armazenamentoMock.setSimulador(Simuladores.RetornarPontuacaoDoUsuario);
		Usuario usuarioGuerra = new Usuario("guerra");
		Pontuacao[] listDePontuacaoEsperada = new Pontuacao[] { new Pontuacao("estrela", 10), new Pontuacao("moeda", 25) };
		assertArrayEquals(listDePontuacaoEsperada, placar.retornarPontuacaoDoUsuario(usuarioGuerra));
	}

	@Test
	public void somentePontuacaoMaiorDoQueZero() {
		ArmazenamentoMock armazenamentoMock = new ArmazenamentoMock();
		Placar placar = new Placar(armazenamentoMock);
		armazenamentoMock.setSimulador(Simuladores.SomentePontuacaoMaiorDoQueZero);
		Usuario usuarioGuerra = new Usuario("guerra");		
		Pontuacao[] listaPontuacaoDoUsuario = placar.retornarPontuacaoDoUsuario(usuarioGuerra);
		assertEquals(1, listaPontuacaoDoUsuario.length);
		assertEquals(new Pontuacao("estrela", 10), listaPontuacaoDoUsuario[0]);
	}

	@Test
	public void retornarRankingPorUsuario() {
		ArmazenamentoMock armazenamentoMock = new ArmazenamentoMock();
		Placar placar = new Placar(armazenamentoMock);
		armazenamentoMock.setSimulador(Simuladores.RetornarUsuariosRanking);
		UsuarioRanking[] usuariosRankingEstrela = placar.retornarUsuariosRanking("estrela");
		assertEquals(3, usuariosRankingEstrela.length);
		assertEquals(new UsuarioRanking(new Usuario("guerra"), 25), usuariosRankingEstrela[0]);
		assertEquals(new UsuarioRanking(new Usuario("fernandes"), 19), usuariosRankingEstrela[1]);
		assertEquals(new UsuarioRanking(new Usuario("rodrigo"), 17), usuariosRankingEstrela[2]);
	}
	
	@Test
	public void rankingSomenteUsuariosDoTipoDaPontuaoEspecificada() {
		ArmazenamentoMock armazenamentoMock = new ArmazenamentoMock();
		Placar placar = new Placar(armazenamentoMock);
		armazenamentoMock.setSimulador(Simuladores.RankingSomenteUsuariosDoTipoDaPontuaoEspecificada);
		UsuarioRanking[] usuariosRankingEstrela = placar.retornarUsuariosRanking("estrela");
		assertEquals(1, usuariosRankingEstrela.length);
		assertEquals(new UsuarioRanking(new Usuario("guerra"), 25), usuariosRankingEstrela[0]);
	}

}
