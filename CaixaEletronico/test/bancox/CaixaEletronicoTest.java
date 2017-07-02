package bancox;

import static org.junit.Assert.*;

import org.junit.Test;

import bancox.mocks.MockHardware;
import bancox.mocks.MockHardwareComFalha;
import bancox.mocks.MockHardwareComFalha.ConfigurarFalha;
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
	
	@Test
	public void whenLogarLendoCartaoComSucesso() throws HardwareExceptions{
		MockServicoRemoto mockServicoRemoto = new MockServicoRemoto();
		MockHardware mockHardware = new MockHardware();
		CaixaEletronico caixaEletronico = new CaixaEletronico(mockServicoRemoto, mockHardware);		
		mockServicoRemoto.setContaCorrenteCadastrada("2001700001");
		mockHardware.setContaCorrenteCadastrada("2001700001");
		assertEquals("Usuário Autenticado", caixaEletronico.logar());
		
	}
	
	@Test(expected=HardwareExceptions.LerCartaoException.class)
	public void whenLogarLendoCartaoComFalha() throws HardwareExceptions{
		MockServicoRemoto mockServicoRemoto = new MockServicoRemoto();
		MockHardwareComFalha mockHardwareComFalha = new MockHardwareComFalha(ConfigurarFalha.LER_CARTAO);
		CaixaEletronico caixaEletronico = new CaixaEletronico(mockServicoRemoto, mockHardwareComFalha);
		caixaEletronico.logar();
	}
	
	@Test
	public void whenLogarLendoCartaoComHardwareOffLine() throws HardwareExceptions{
		MockServicoRemoto mockServicoRemoto = new MockServicoRemoto();
		CaixaEletronico caixaEletronico = new CaixaEletronico(mockServicoRemoto);		
		mockServicoRemoto.setContaCorrenteCadastrada("2001700001");
		assertEquals("Este terminal não está indisponível!", caixaEletronico.logar());
	}

}
