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
	
	@Test
	public void retornarUsuarios() {
		Armazenamento armazenamento = new Armazenamento();
		armazenamento.salvarPontuacaoDoUsuario("guerra", "estrela", 10);
		armazenamento.salvarPontuacaoDoUsuario("fernandes", "estrela", 10);
		String[] usuariosRecuperados = armazenamento.RetornarUsuarios();
		assertEquals(2, usuariosRecuperados.length);
		assertEquals("fernandes", usuariosRecuperados[0]);
		assertEquals("guerra", usuariosRecuperados[1]);
		
	}

}
