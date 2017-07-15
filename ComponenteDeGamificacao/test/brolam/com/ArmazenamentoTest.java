package brolam.com;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class ArmazenamentoTest {
	Armazenamento armazenamento;
	
	@Before
	public void iniciarArmazenamento(){
		this.armazenamento = new Armazenamento();
	}
	
	@Test
	public void salvarPontuacaoDoUsuario() {
		this.armazenamento.salvarPontuacaoDoUsuario("guerra", "estrela", 10);
		int pontuacaoEstrela = this.armazenamento.recuperarPontuacaoDoUsuario("guerra", "estrela");
		assertEquals(10, pontuacaoEstrela);
	}
	
	@Test
	public void retornarUsuarios() {
		this.armazenamento.salvarPontuacaoDoUsuario("guerra", "estrela", 10);
		this.armazenamento.salvarPontuacaoDoUsuario("fernandes", "estrela", 10);
		String[] usuariosRecuperados = this.armazenamento.retornarUsuarios();
		assertEquals(2, usuariosRecuperados.length);
		assertEquals("fernandes", usuariosRecuperados[0]);
		assertEquals("guerra", usuariosRecuperados[1]);	
	}
	
	@Test
	public void retornarTiposDePontuacao() {
		this.armazenamento.salvarPontuacaoDoUsuario("guerra", "estrela", 25);
		this.armazenamento.salvarPontuacaoDoUsuario("guerra", "moeda", 20);
		String[] tiposDePontuacaRecuperados = this.armazenamento.retornarTiposDePontuacao();
		assertEquals(2, tiposDePontuacaRecuperados.length);
		assertEquals("estrela", tiposDePontuacaRecuperados[0]);
		assertEquals("moeda", tiposDePontuacaRecuperados[1]);	
	}

}
