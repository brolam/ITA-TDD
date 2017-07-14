package brolam.com;

import static org.junit.Assert.*;
import brolam.com.models.Pontuacao;
import brolam.com.models.Usuario;
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
	}
	
	public void testRecuperarPontuacaoDoUsuarioGuerra(){
		Usuario usuarioGuerra = new Usuario("guerra");
		Pontuacao[] listDePontuacaoEsperada = new Pontuacao[] { new Pontuacao("estrela", 25), new Pontuacao("moeda", 20) };
		assertArrayEquals(listDePontuacaoEsperada, this.placar.retornarPontuacaoDoUsuario(usuarioGuerra));
	}
}
