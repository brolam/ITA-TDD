package bancox;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import bancox.mocks.MockHardware;
import static bancox.mocks.MockHardware.SimularFalha.*;
import bancox.mocks.MockServicoRemoto;

public class CaixaEletronicoTest {
	MockServicoRemoto mockServicoRemoto;
	MockHardware mockHardware;

	@Before
	public void iniciarMocks() {
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
	public void whenLogarLendoCartaoComSucesso() throws HardwareExceptions {
		CaixaEletronico caixaEletronico = new CaixaEletronico(this.mockServicoRemoto, this.mockHardware);
		this.mockServicoRemoto.setContaCorrenteCadastrada("2001700001");
		this.mockHardware.inseriCartao("2001700001");
		assertEquals("Usu�rio Autenticado", caixaEletronico.logar());

	}

	@Test
	public void whenLogarLendoCartaoComFalha() throws HardwareExceptions {
		CaixaEletronico caixaEletronico = new CaixaEletronico(this.mockServicoRemoto, this.mockHardware);
		this.mockHardware.setSimularFalha(WHEN_LER_CARTAO);
		assertEquals("Erro na leitura do cart�o!", caixaEletronico.logar());
	}

	@Test
	public void whenLogarLendoCartaoComHardwareOffLine() throws HardwareExceptions {
		CaixaEletronico caixaEletronico = new CaixaEletronico(this.mockServicoRemoto);
		mockServicoRemoto.setContaCorrenteCadastrada("2001700001");
		assertEquals("Este terminal est� indispon�vel!", caixaEletronico.logar());
	}

	@Test
	public void whenDepositar1000ReaisComSucesso() {
		CaixaEletronico caixaEletronico = new CaixaEletronico(this.mockServicoRemoto);
		mockServicoRemoto.setContaCorrenteCadastrada("2001700001");
		assertEquals("Dep�sito recebido com sucesso!", caixaEletronico.depositar("2001700001", 1000.00));
		assertEquals(1, this.mockServicoRemoto.getContarPersistirConta());
	}

	@Test
	public void whenDepositar1000ReaisComFalha() {
		CaixaEletronico caixaEletronico = new CaixaEletronico(this.mockServicoRemoto);
		mockServicoRemoto.setContaCorrenteCadastrada("2001700001");
		assertEquals("Dep�sito n�o foi realizado!", caixaEletronico.depositar("2001700002", 1000.00));
	}

	@Test
	public void whenDepositar1000RaisLendoCartaoLendoEnveloperComSucesso() {
		CaixaEletronico caixaEletronico = new CaixaEletronico(this.mockServicoRemoto, this.mockHardware);
		this.mockServicoRemoto.setContaCorrenteCadastrada("2001700001");
		this.mockHardware.inseriCartao("2001700001");
		assertEquals("Dep�sito recebido com sucesso!", caixaEletronico.depositar(1000.00));
		assertEquals(1, this.mockServicoRemoto.getContarPersistirConta());
	}

	@Test
	public void whenDepositarComHardwareOffLine() throws HardwareExceptions {
		CaixaEletronico caixaEletronico = new CaixaEletronico(this.mockServicoRemoto);
		this.mockServicoRemoto.setContaCorrenteCadastrada("2001700001");
		assertEquals("Este terminal est� indispon�vel!", caixaEletronico.depositar(1000.00));
	}

	@Test
	public void whenDepositarLendoCartaoComFalha() {
		CaixaEletronico caixaEletronico = new CaixaEletronico(this.mockServicoRemoto, this.mockHardware);
		this.mockHardware.setSimularFalha(WHEN_LER_CARTAO);
		assertEquals("Erro na leitura do cart�o!", caixaEletronico.depositar(1000.00));
	}

	@Test
	public void whenDepositarLendoEnvelopeComFalha() {
		CaixaEletronico caixaEletronico = new CaixaEletronico(this.mockServicoRemoto, this.mockHardware);
		this.mockHardware.setSimularFalha(WHEN_LER_ENVELOPE);
		assertEquals("Erro na leitura do envelope!", caixaEletronico.depositar(1000.00));
	}

	@Test
	public void whenConsultarSaldoComSucesso() {
		this.whenDepositar1000ReaisComSucesso();
		CaixaEletronico caixaEletronico = new CaixaEletronico(this.mockServicoRemoto);
		assertEquals("O saldo � R$1000,00", caixaEletronico.saldo("2001700001"));
	}

	@Test
	public void whenConsultarSaldoComFalha() {
		this.whenDepositar1000ReaisComSucesso();
		CaixaEletronico caixaEletronico = new CaixaEletronico(this.mockServicoRemoto);
		assertEquals("N�o foi poss�vel consultar o Saldo!", caixaEletronico.saldo("2001700002"));
	}

	@Test
	public void whenConsultarSaldoLendoCartaoComSucesso() throws HardwareExceptions {
		whenDepositar1000RaisLendoCartaoLendoEnveloperComSucesso();
		CaixaEletronico caixaEletronico = new CaixaEletronico(this.mockServicoRemoto, this.mockHardware);
		assertEquals("O saldo � R$1000,00", caixaEletronico.saldo());
	}

	@Test
	public void whenConsultarSaldoLendoCartaoComFalha() throws HardwareExceptions {
		CaixaEletronico caixaEletronico = new CaixaEletronico(this.mockServicoRemoto, this.mockHardware);
		this.mockHardware.setSimularFalha(WHEN_LER_CARTAO);
		assertEquals("Erro na leitura do cart�o!", caixaEletronico.saldo());
	}

	@Test
	public void whenSacar500ReaisComSucesso() {
		whenDepositar1000ReaisComSucesso();
		CaixaEletronico caixaEletronico = new CaixaEletronico(this.mockServicoRemoto);
		assertEquals("Retire seu dinheiro", caixaEletronico.sacar("2001700001", 500.00));
		assertEquals("O saldo � R$500,00", caixaEletronico.saldo("2001700001"));
		assertEquals(2, this.mockServicoRemoto.getContarPersistirConta());
	}

	@Test
	public void whenSacar500ReaisComFalha() {
		whenDepositar1000ReaisComSucesso();
		CaixaEletronico caixaEletronico = new CaixaEletronico(this.mockServicoRemoto);
		assertEquals("Saque n�o foi realizado!", caixaEletronico.sacar("2001700002", 500.00));
		assertEquals("O saldo � R$1000,00", caixaEletronico.saldo("2001700001"));
	}

	@Test
	public void whenSacar500ReaisComSaldoInsuficiente() {
		CaixaEletronico caixaEletronico = new CaixaEletronico(this.mockServicoRemoto);
		this.mockServicoRemoto.setContaCorrenteCadastrada("2001700001");
		assertEquals("Saldo insuficiente", caixaEletronico.sacar("2001700001", 500.00));
		assertEquals("O saldo � R$0,00", caixaEletronico.saldo("2001700001"));
	}

	@Test
	public void whenSacar500ReaisComHardwareOffLine() {
		CaixaEletronico caixaEletronico = new CaixaEletronico(this.mockServicoRemoto);
		this.mockServicoRemoto.setContaCorrenteCadastrada("2001700001");
		assertEquals("Este terminal est� indispon�vel!", caixaEletronico.sacar(500.00));
	}

	@Test
	public void whenSacar500ReaisLendoCartaoEntregandoDinheiroComSucesso() {
		whenDepositar1000RaisLendoCartaoLendoEnveloperComSucesso();
		CaixaEletronico caixaEletronico = new CaixaEletronico(this.mockServicoRemoto, this.mockHardware);
		assertEquals("Retire seu dinheiro", caixaEletronico.sacar(500.00));
		assertEquals(2, this.mockServicoRemoto.getContarPersistirConta());

	}

	@Test
	public void whenSacar500ReaisLendoCartaoComFalha() {
		CaixaEletronico caixaEletronico = new CaixaEletronico(this.mockServicoRemoto, this.mockHardware);
		this.mockHardware.setSimularFalha(WHEN_LER_CARTAO);
		assertEquals("Erro na leitura do cart�o!", caixaEletronico.sacar(500.00));
	}

	@Test
	public void whenSacar500ReaisEntregandoDinheiroComFalha() {
		whenDepositar1000RaisLendoCartaoLendoEnveloperComSucesso();
		CaixaEletronico caixaEletronico = new CaixaEletronico(this.mockServicoRemoto, this.mockHardware);
		this.mockHardware.setSimularFalha(WHEN_ENTREGAR_DINHEIRO);
		assertEquals("Erro na entrega do dinheiro!", caixaEletronico.sacar(500.00));
		assertEquals("O saldo � R$1000,00", caixaEletronico.saldo("2001700001"));
	}

}
