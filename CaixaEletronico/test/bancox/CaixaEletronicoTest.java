package bancox;

import static org.junit.Assert.*;

import org.junit.Test;

import bancox.mocks.MockServicoRemoto;

public class CaixaEletronicoTest {

	@Test
	public void whenLogarComSucesso() {
		MockServicoRemoto mockServicoRemoto = new MockServicoRemoto();
		mockServicoRemoto.setContaCorrenteCadastrada(1);
		CaixaEletronico caixaEletronico = new CaixaEletronico(mockServicoRemoto);
		assertEquals("Usuário Autenticado", caixaEletronico.logar(1));
	}

}
