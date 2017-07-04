package bancox;

public class CaixaEletronico {
	private static String MSG_HARDWARE_INDISPONIVEL = "Este terminal est� indispon�vel!"; 
	private ServicoRemoto servicoRemoto;
	private Hardware hardware;

	public CaixaEletronico(ServicoRemoto servicoRemoto) {
		this.servicoRemoto = servicoRemoto;
	}

	public CaixaEletronico(ServicoRemoto servicoRemoto, Hardware hardware) {
		this(servicoRemoto);
		this.hardware = hardware;
	}

	public String logar(String numeroDaContaCorrente) {
		ContaCorrente contaCorrenteRecuperada = servicoRemoto.recuperarConta(numeroDaContaCorrente);
		if (contaCorrenteRecuperada == null)
			return "N�o foi poss�vel autenticar o usu�rio";
		else
			return "Usu�rio Autenticado";
	}

	public String logar() throws HardwareExceptions {
		if ( isHardwareOnLine()){
			String numeroDaContaCorrente = hardware.pegarNumeroDaContaCartao();
			return logar(numeroDaContaCorrente);
		}
		return MSG_HARDWARE_INDISPONIVEL;
	}

	private boolean isHardwareOnLine() {
		return this.hardware instanceof Hardware;
	}

	public String depositar(String numeroDaContaCorrente, double valorDepositado) {
		ContaCorrente contaCorrenteParaDeposito = servicoRemoto.recuperarConta(numeroDaContaCorrente);
		if (contaCorrenteParaDeposito == null)
			return "Dep�sito n�o foi realizado!";
		else{
			contaCorrenteParaDeposito.adicionarSaldo(valorDepositado);
			this.servicoRemoto.persistirConta(contaCorrenteParaDeposito);
			return "Dep�sito recebido com sucesso";
		}
	}

	public String depositar(double valorDepositado) throws HardwareExceptions {
		if ( isHardwareOnLine()){
			String numeroDaContaLidoNoCartao = hardware.pegarNumeroDaContaCartao();
			return this.depositar(numeroDaContaLidoNoCartao, valorDepositado);
		}
		return MSG_HARDWARE_INDISPONIVEL;
	}

	public String saldo(String numeroDaContaCorrente) {
		ContaCorrente contaCorrenteRecuperada = servicoRemoto.recuperarConta(numeroDaContaCorrente);
		if (contaCorrenteRecuperada == null)
			return "N�o foi poss�vel consultar o Saldo!";
		else{
			String mensagemSaldo = String.format("O saldo � R$%.2f", contaCorrenteRecuperada.getSaldo());
			return mensagemSaldo;
		}
	}
}
