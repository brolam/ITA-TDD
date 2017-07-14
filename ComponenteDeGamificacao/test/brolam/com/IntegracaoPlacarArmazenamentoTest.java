package brolam.com;

import static org.junit.Assert.*;
import brolam.com.models.Pontuacao;
import brolam.com.models.Usuario;
import brolam.com.models.UsuarioRanking;
import junit.framework.TestCase;


public class IntegracaoPlacarArmazenamentoTest extends TestCase {
	Placar placar;
	Armazenamento armazenamento;
	
	@Override
	protected void setUp()  {
		this.armazenamento = new Armazenamento("IntegracaoPlacarArmazenamentoTest");
		this.placar = new Placar(this.armazenamento);
		Usuario usuarioGuerra = new Usuario("guerra");
		this.placar.registrarPontuacao(usuarioGuerra, new Pontuacao("estrela", 25));
		this.placar.registrarPontuacao(usuarioGuerra, new Pontuacao("moeda", 20));
		Usuario usuarioFernandes = new Usuario("fernandes");
		this.placar.registrarPontuacao(usuarioFernandes, new Pontuacao("estrela", 19));
		Usuario usuarioRodrigo = new Usuario("rodrigo");
		this.placar.registrarPontuacao(usuarioRodrigo, new Pontuacao("estrela", 17));
		Usuario usuarioToco = new Usuario("rodrigo");
		this.placar.registrarPontuacao(usuarioToco, new Pontuacao("energia", 10));
	}
	
	public void testRecuperarPontuacaoDoUsuarioGuerra(){
		Usuario usuarioGuerra = new Usuario("guerra");
		Pontuacao[] listDePontuacaoEsperada = new Pontuacao[] { new Pontuacao("estrela", 25), new Pontuacao("moeda", 20) };
		assertArrayEquals(listDePontuacaoEsperada, this.placar.retornarPontuacaoDoUsuario(usuarioGuerra));
	}
	
	public void testRecuperarPontuacaoDoUsuarioFernandes(){
		Usuario usuarioFernandes = new Usuario("fernandes");
		Pontuacao[] listDePontuacaoEsperada = new Pontuacao[] { new Pontuacao("estrela", 19)};
		assertArrayEquals(listDePontuacaoEsperada, this.placar.retornarPontuacaoDoUsuario(usuarioFernandes));
	}
	
	public void testRecuperarPlacarRankingPorUsuario(){
		UsuarioRanking[] usuariosRankingEstrela = this.placar.retornarUsuariosRanking("estrela");
		assertEquals(3, usuariosRankingEstrela.length);
		assertEquals(new UsuarioRanking(new Usuario("guerra"), 25), usuariosRankingEstrela[0]);
		assertEquals(new UsuarioRanking(new Usuario("fernandes"), 19), usuariosRankingEstrela[1]);
		assertEquals(new UsuarioRanking(new Usuario("rodrigo"), 17), usuariosRankingEstrela[2]);
	}
}
