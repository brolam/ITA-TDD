package test;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import main.br.com.brolam.CamelCase;
import main.br.com.brolam.CamelCaseCaracterInvalidoException;
import main.br.com.brolam.CamelCaseNaoComecarComNumeroException;

public class CamelCaseTest {
	List<String> listaValoresAtuais;
	
	@Before
	public void iniciarListaValoresAtuais(){
		this.listaValoresAtuais = new ArrayList<>();
	}

	@Test
	public void umaPalavraMinuscula() throws Exception {
		this.listaValoresAtuais = CamelCase.converterCamelCase("nome");
		assertEquals("nome", this.listaValoresAtuais.get(0));
	}
	
	@Test
	public void umaPalavraMaiusculo() throws Exception {
		this.listaValoresAtuais = CamelCase.converterCamelCase("Nome");
		assertEquals("nome", this.listaValoresAtuais.get(0));
	}
	
	@Test
	public void umaPalavraTodaMaiusculo() throws Exception {
		this.listaValoresAtuais = CamelCase.converterCamelCase("CPF");
		assertEquals("CPF", this.listaValoresAtuais.get(0));
	}
	
	@Test
	public void duasPalavrasUmaMinusculaOutraMaiusculo() throws Exception {
		this.listaValoresAtuais = CamelCase.converterCamelCase("numeroCPF");
		assertEquals(2,listaValoresAtuais.size());
		assertEquals("numero", this.listaValoresAtuais.get(0));
		assertEquals("CPF", this.listaValoresAtuais.get(1));
	}
	
	@Test
	public void tresPalavras() throws Exception {
		this.listaValoresAtuais = CamelCase.converterCamelCase("numeroCPFContribuinte");
		assertEquals(3,listaValoresAtuais.size());
		assertEquals("numero", this.listaValoresAtuais.get(0));
		assertEquals("CPF", this.listaValoresAtuais.get(1));
		assertEquals("contribuinte", this.listaValoresAtuais.get(2));
	}
	
	@Test
	public void tresPalavrasIncluindoNumeros() throws Exception {
		this.listaValoresAtuais = CamelCase.converterCamelCase("recupera10Primeiros");
		assertEquals(3,listaValoresAtuais.size());
		assertEquals("recupera", this.listaValoresAtuais.get(0));
		assertEquals("10", this.listaValoresAtuais.get(1));
		assertEquals("primeiros", this.listaValoresAtuais.get(2));
	}
	
	@Test(expected=CamelCaseNaoComecarComNumeroException.class)
	public void nãoComecarComNumero() throws Exception {
		CamelCase.converterCamelCase("10Primeiros");
	}
	
	@Test(expected=CamelCaseCaracterInvalidoException.class)
	public void caracterInvalido() throws Exception {
		CamelCase.converterCamelCase("nome#Composto");
	}

}
