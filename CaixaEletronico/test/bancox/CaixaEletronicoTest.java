package bancox;

import static org.junit.Assert.*;

import org.junit.Test;

import bancox.mocks.MockHardware;
import bancox.mocks.MockServicoRemoto;

public class CaixaEletronicoTest {

	@Test
	public void whenLogarComSucesso() {
		MockServicoRemoto mockServicoRemoto = new MockServicoRemoto();
		mockServicoRemoto.setContaCorrenteCadastrada("2001700001");
		CaixaEletronico caixaEletronico = new CaixaEletronico(mockServicoRemoto);
		assertEquals("Usu�rio Autenticado", caixaEletronico.logar("2001700001"));
	}
	
	@Test
	public void whenLogarComFalha() {
		MockServicoRemoto mockServicoRemoto = new MockServicoRemoto();
		mockServicoRemoto.setContaCorrenteCadastrada("2001700001");
		CaixaEletronico caixaEletronico = new CaixaEletronico(mockServicoRemoto);
		assertEquals("N�o foi poss�vel autenticar o usu�rio", caixaEletronico.logar("2001700002"));
	}
	
	@Test
	public void whenLogarLendoCartaoComSucesso(){
		MockServicoRemoto mockServicoRemoto = new MockServicoRemoto();
		MockHardware mockHardware = new MockHardware();
		CaixaEletronico caixaEletronico = new CaixaEletronico(mockServicoRemoto, mockHardware);		
		mockServicoRemoto.setContaCorrenteCadastrada("2001700001");
		mockHardware.setContaCorrenteCadastrada("2001700001");
		assertEquals("Usu�rio Autenticado", caixaEletronico.logar());
		
	}

}
