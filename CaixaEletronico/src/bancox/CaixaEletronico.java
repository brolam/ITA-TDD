package bancox;

public class CaixaEletronico {
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
		return "Este terminal está indisponível!";
	}

	private boolean isHardwareOnLine() {
		return this.hardware instanceof Hardware;
	}

	public String depositar(String numeroDaContaCorrente, double valorDepositado) {
		ContaCorrente contaCorrenteParaDeposito = servicoRemoto.recuperarConta(numeroDaContaCorrente);
		if (contaCorrenteParaDeposito == null)
			return null;
		else{
			contaCorrenteParaDeposito.adicionarSaldo(valorDepositado);
			this.servicoRemoto.persistirConta(contaCorrenteParaDeposito);
			return "Depósito recebido com sucesso";
		}
	}
}
