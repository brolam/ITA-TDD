package bancox;

import static org.junit.Assert.*;

import org.junit.Test;

import bancox.mocks.MockServicoRemoto;

public class CaixaEletronicoTest {

	@Test
	public void whenLogarComSucesso() {
		MockServicoRemoto mockServicoRemoto = new MockServicoRemoto();
		mockServicoRemoto.setContaCorrenteCadastrada("2001700001");
		CaixaEletronico caixaEletronico = new CaixaEletronico(mockServicoRemoto);
		assertEquals("Usuário Autenticado", caixaEletronico.logar("2001700001"));
	}
	
	@Test
	public void whenLogarComFalha() {
		MockServicoRemoto mockServicoRemoto = new MockServicoRemoto();
		mockServicoRemoto.setContaCorrenteCadastrada("2001700001");
		CaixaEletronico caixaEletronico = new CaixaEletronico(mockServicoRemoto);
		assertEquals("Não foi possível autenticar o usuário", caixaEletronico.logar("2001700002"));
	}

}
