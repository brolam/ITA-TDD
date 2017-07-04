package bancox;

public class CaixaEletronico {
	private static String MSG_HARDWARE_INDISPONIVEL = "Este terminal está indisponível!"; 
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
			return "Não foi possível autenticar o usuário";
		else
			return "Usuário Autenticado";
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
			return "Depósito não foi realizado!";
		else{
			contaCorrenteParaDeposito.adicionarSaldo(valorDepositado);
			this.servicoRemoto.persistirConta(contaCorrenteParaDeposito);
			return "Depósito recebido com sucesso";
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
			return "Não foi possível consultar o Saldo!";
		else{
			String mensagemSaldo = String.format("O saldo é R$%.2f", contaCorrenteRecuperada.getSaldo());
			return mensagemSaldo;
		}
	}
}
