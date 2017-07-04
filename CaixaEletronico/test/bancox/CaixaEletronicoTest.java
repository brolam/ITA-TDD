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
		assertEquals("Usu�rio Autenticado", caixaEletronico.logar("2001700001"));
	}
	
	@Test
	public void whenLogarComFalha() {
		this.mockServicoRemoto.setContaCorrenteCadastrada("2001700001");
		CaixaEletronico caixaEletronico = new CaixaEletronico(this.mockServicoRemoto);
		assertEquals("N�o foi poss�vel autenticar o usu�rio", caixaEletronico.logar("2001700002"));
	}
	
	@Test
	public void whenLogarLendoCartaoComSucesso() throws HardwareExceptions{
		CaixaEletronico caixaEletronico = new CaixaEletronico(this.mockServicoRemoto, this.mockHardware);		
		this.mockServicoRemoto.setContaCorrenteCadastrada("2001700001");
		this.mockHardware.setContaCorrenteCadastrada("2001700001");
		assertEquals("Usu�rio Autenticado", caixaEletronico.logar());
		
	}
	
	@Test
	public void whenLogarLendoCartaoComFalha() throws HardwareExceptions{
		MockHardwareComFalha mockHardwareComFalha = new MockHardwareComFalha(ConfigurarFalha.LER_CARTAO);
		CaixaEletronico caixaEletronico = new CaixaEletronico(this.mockServicoRemoto, mockHardwareComFalha);
		assertEquals("Erro na leitura do cart�o!", caixaEletronico.logar());
	}
	
	@Test
	public void whenLogarLendoCartaoComHardwareOffLine() throws HardwareExceptions{
		CaixaEletronico caixaEletronico = new CaixaEletronico(this.mockServicoRemoto);		
		mockServicoRemoto.setContaCorrenteCadastrada("2001700001");
		assertEquals("Este terminal est� indispon�vel!", caixaEletronico.logar());
	}
	
	@Test
	public void whenDepositar1000ReaisComSucesso(){
		CaixaEletronico caixaEletronico = new CaixaEletronico(this.mockServicoRemoto);
		mockServicoRemoto.setContaCorrenteCadastrada("2001700001");
		assertEquals("Dep�sito recebido com sucesso!", caixaEletronico.depositar("2001700001", 1000.00));
	}
	
	@Test
	public void whenDepositar1000ReaisComFalha(){
		CaixaEletronico caixaEletronico = new CaixaEletronico(this.mockServicoRemoto);
		mockServicoRemoto.setContaCorrenteCadastrada("2001700001");
		assertEquals("Dep�sito n�o foi realizado!", caixaEletronico.depositar("2001700002", 1000.00));
	}
	
	@Test
	public void whenDepositar1000RaisLendoCartaoLendoEnveloperComSucesso() throws HardwareExceptions{
		CaixaEletronico caixaEletronico = new CaixaEletronico(this.mockServicoRemoto, this.mockHardware);
		this.mockServicoRemoto.setContaCorrenteCadastrada("2001700001");
		this.mockHardware.setContaCorrenteCadastrada("2001700001");
		assertEquals("Dep�sito recebido com sucesso!", caixaEletronico.depositar(1000.00));
	}
	
	@Test
	public void whenDepositarComHardwareOffLineo() throws HardwareExceptions{
		CaixaEletronico caixaEletronico = new CaixaEletronico(this.mockServicoRemoto);
		this.mockServicoRemoto.setContaCorrenteCadastrada("2001700001");
		assertEquals("Este terminal est� indispon�vel!", caixaEletronico.depositar(1000.00));
	}
	
	@Test
	public void whenDepositarLendoCartaoComFalha(){
		MockHardwareComFalha mockHardwareComFalha = new MockHardwareComFalha(ConfigurarFalha.LER_CARTAO);
		CaixaEletronico caixaEletronico = new CaixaEletronico(this.mockServicoRemoto, mockHardwareComFalha);
		assertEquals("Erro na leitura do cart�o!", caixaEletronico.depositar(1000.00));
	}
	
	@Test
	public void whenDepositarLendoEnvelopeComFalha(){
		MockHardwareComFalha mockHardwareComFalha = new MockHardwareComFalha(ConfigurarFalha.LER_ENVELOPE);
		CaixaEletronico caixaEletronico = new CaixaEletronico(this.mockServicoRemoto, mockHardwareComFalha);
		assertEquals("Erro na leitura do envelope!", caixaEletronico.depositar(1000.00));
	}
		
	@Test
	public void whenConsultarSaldoComSucesso(){
		this.whenDepositar1000ReaisComSucesso();
		CaixaEletronico caixaEletronico = new CaixaEletronico(this.mockServicoRemoto);
		assertEquals("O saldo � R$1000,00", caixaEletronico.saldo("2001700001"));
	}
	
	@Test
	public void whenConsultarSaldoComFalha(){
		this.whenDepositar1000ReaisComSucesso();
		CaixaEletronico caixaEletronico = new CaixaEletronico(this.mockServicoRemoto);
		assertEquals("N�o foi poss�vel consultar o Saldo!", caixaEletronico.saldo("2001700002"));
	}
	
	@Test
	public void whenConsultarSaldoLendoCartaoComSucesso() throws HardwareExceptions{
		whenDepositar1000RaisLendoCartaoLendoEnveloperComSucesso();
		CaixaEletronico caixaEletronico = new CaixaEletronico(this.mockServicoRemoto, this.mockHardware);
		assertEquals("O saldo � R$1000,00", caixaEletronico.saldo());
	}
	
	@Test
	public void whenConsultarSaldoLendoCartaoComFalha() throws HardwareExceptions{
		MockHardwareComFalha mockHardwareComFalha = new MockHardwareComFalha(ConfigurarFalha.LER_CARTAO);
		CaixaEletronico caixaEletronico = new CaixaEletronico(this.mockServicoRemoto, mockHardwareComFalha);
		assertEquals("Erro na leitura do cart�o!", caixaEletronico.saldo());
	}
	
}
