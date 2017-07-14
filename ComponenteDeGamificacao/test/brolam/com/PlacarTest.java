package brolam.com;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;
import brolam.com.mocks.ArmazenamentoMock;
import brolam.com.mocks.ArmazenamentoMock.Simuladores;
import brolam.com.models.Pontuacao;
import brolam.com.models.Usuario;
import brolam.com.models.UsuarioRanking;

public class PlacarTest {
	Placar placar;
	ArmazenamentoMock armazenamentoMock;
	
	@Before
	public void iniciarPlacar(){
		this.armazenamentoMock = new ArmazenamentoMock();
		this.placar = new Placar(this.armazenamentoMock);
	}
	
	@Test
	public void registrarPontuacaoDoUsuario() {
		Usuario usuario = new Usuario("guerra");
		Pontuacao pontuacao = new Pontuacao("estrela", 10);
		this.placar.registrarPontuacao(usuario, pontuacao);
		assertTrue(this.armazenamentoMock.isPontuacaoRegistrada());
	}

	@Test
	public void retornarPontuacaoDoUsuario() {
		this.armazenamentoMock.setSimulador(Simuladores.RetornarPontuacaoDoUsuario);
		Usuario usuarioGuerra = new Usuario("guerra");
		Pontuacao[] listDePontuacaoEsperada = new Pontuacao[] { new Pontuacao("estrela", 10), new Pontuacao("moeda", 25) };
		assertArrayEquals(listDePontuacaoEsperada, this.placar.retornarPontuacaoDoUsuario(usuarioGuerra));
	}

	@Test
	public void somentePontuacaoMaiorDoQueZero() {
		this.armazenamentoMock.setSimulador(Simuladores.SomentePontuacaoMaiorDoQueZero);
		Usuario usuarioGuerra = new Usuario("guerra");		
		Pontuacao[] listaPontuacaoDoUsuario = this.placar.retornarPontuacaoDoUsuario(usuarioGuerra);
		assertEquals(1, listaPontuacaoDoUsuario.length);
		assertEquals(new Pontuacao("estrela", 10), listaPontuacaoDoUsuario[0]);
	}

	@Test
	public void retornarRankingPorUsuario() {
		this.armazenamentoMock.setSimulador(Simuladores.RetornarUsuariosRanking);
		UsuarioRanking[] usuariosRankingEstrela = this.placar.retornarUsuariosRanking("estrela");
		assertEquals(3, usuariosRankingEstrela.length);
		assertEquals(new UsuarioRanking(new Usuario("guerra"), 25), usuariosRankingEstrela[0]);
		assertEquals(new UsuarioRanking(new Usuario("fernandes"), 19), usuariosRankingEstrela[1]);
		assertEquals(new UsuarioRanking(new Usuario("rodrigo"), 17), usuariosRankingEstrela[2]);
	}
	
	@Test
	public void rankingSomenteUsuariosDoTipoDaPontuaoEspecificada() {
		this.armazenamentoMock.setSimulador(Simuladores.RankingSomenteUsuariosDoTipoDaPontuaoEspecificada);
		UsuarioRanking[] usuariosRankingEstrela = this.placar.retornarUsuariosRanking("estrela");
		assertEquals(1, usuariosRankingEstrela.length);
		assertEquals(new UsuarioRanking(new Usuario("guerra"), 25), usuariosRankingEstrela[0]);
	}

}
