package brolam.com;

import static org.junit.Assert.*;

import org.junit.Test;

public class ArmazenamentoTest {

	@Test
	public void salvarPontuacaoDoUsuario() {
		Armazenamento armazenamento = new Armazenamento();
		armazenamento.salvarPontuacaoDoUsuario("guerra", "estrela", 10);
		int pontuacaoEstrela = armazenamento.recuperarPontuacaoDoUsuario("guerra", "estrela");
		assertEquals(10, pontuacaoEstrela);
	}

}
