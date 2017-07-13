package brolam.com;

import static org.junit.Assert.*;
import org.junit.Test;
import brolam.com.mocks.ArmazenamentoMock;
import brolam.com.models.Pontuacao;
import brolam.com.models.Usuario;

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
		Pontuacao[] listDePontuacaoEsperada = new Pontuacao[] { new Pontuacao("estrela", 10),
				new Pontuacao("moeda", 25) };
		armazenamentoMock.simularListaPontuacao(listDePontuacaoEsperada);
		Usuario usuarioGuerra = new Usuario("guerra");
		assertArrayEquals(listDePontuacaoEsperada, placar.retornarPontuacaoDoUsuario(usuarioGuerra));
	}

	@Test
	public void somentePontuacaoMaiorDoQueZero() {
		ArmazenamentoMock armazenamentoMock = new ArmazenamentoMock();
		Placar placar = new Placar(armazenamentoMock);
		Pontuacao[] listDePontuacaoEsperada = new Pontuacao[] { new Pontuacao("estrela", 10), new Pontuacao("moeda", 0) };
		armazenamentoMock.simularListaPontuacao(listDePontuacaoEsperada);
		Usuario usuarioGuerra = new Usuario("guerra");
		Pontuacao[] listaPontuacaoDoUsuario = placar.retornarPontuacaoDoUsuario(usuarioGuerra);
		assertEquals(1, listaPontuacaoDoUsuario.length);
		assertEquals(new Pontuacao("estrela", 10), listaPontuacaoDoUsuario[0]);
	}

}
