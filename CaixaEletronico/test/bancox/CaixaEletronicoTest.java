package bancox;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import bancox.mocks.MockHardware;
import bancox.mocks.MockHardwareComFalha;
import bancox.mocks.MockHardwareComFalha.ConfigurarFalha;
import bancox.mocks.MockServicoRemoto;

public class CaixaEletronicoTest {
	MockServicoRemoto mockServicoRemoto;
	MockHardware mockHardware;
	
	@Before
	public void iniciarMocks(){
		this.mockServicoRemoto = new MockServicoRemoto();
		this.mockHardware = new MockHardware();
	}

	@Test
	public void whenLogarComSucesso() {
		this.mockServicoRemoto.setContaCorrenteCadastrada("2001700001");
		CaixaEletronico caixaEletronico = new CaixaEletronico(this.mockServicoRemoto);
		assertEquals("Usuário Autenticado", caixaEletronico.logar("2001700001"));
	}
	
	@Test
	public void whenLogarComFalha() {
		this.mockServicoRemoto.setContaCorrenteCadastrada("2001700001");
		CaixaEletronico caixaEletronico = new CaixaEletronico(this.mockServicoRemoto);
		assertEquals("Não foi possível autenticar o usuário", caixaEletronico.logar("2001700002"));
	}
	
	@Test
	public void whenLogarLendoCartaoComSucesso() throws HardwareExceptions{
		CaixaEletronico caixaEletronico = new CaixaEletronico(this.mockServicoRemoto, this.mockHardware);		
		this.mockServicoRemoto.setContaCorrenteCadastrada("2001700001");
		this.mockHardware.setContaCorrenteCadastrada("2001700001");
		assertEquals("Usuário Autenticado", caixaEletronico.logar());
		
	}
	
	@Test(expected=HardwareExceptions.LerCartaoException.class)
	public void whenLogarLendoCartaoComFalha() throws HardwareExceptions{
		MockHardwareComFalha mockHardwareComFalha = new MockHardwareComFalha(ConfigurarFalha.LER_CARTAO);
		CaixaEletronico caixaEletronico = new CaixaEletronico(this.mockServicoRemoto, mockHardwareComFalha);
		caixaEletronico.logar();
	}
	
	@Test
	public void whenLogarLendoCartaoComHardwareOffLine() throws HardwareExceptions{
		CaixaEletronico caixaEletronico = new CaixaEletronico(this.mockServicoRemoto);		
		mockServicoRemoto.setContaCorrenteCadastrada("2001700001");
		assertEquals("Este terminal está indisponível!", caixaEletronico.logar());
	}
	
	@Test
	public void whenDepositarComSucesso(){
		CaixaEletronico caixaEletronico = new CaixaEletronico(this.mockServicoRemoto);
		mockServicoRemoto.setContaCorrenteCadastrada("2001700001");
		assertEquals("Depósito recebido com sucesso", caixaEletronico.depositar("2001700001", 1000.00));
	}

}
