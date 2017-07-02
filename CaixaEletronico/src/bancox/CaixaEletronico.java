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
			return "N�o foi poss�vel autenticar o usu�rio";
		else
			return "Usu�rio Autenticado";
	}

	public String logar() throws HardwareExceptions {
		if ( isHardwareOnLine()){
			String numeroDaContaCorrente = hardware.pegarNumeroDaContaCartao();
			return logar(numeroDaContaCorrente);
		}
		return "Este terminal est� indispon�vel!";
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
			return "Dep�sito recebido com sucesso";
		}
	}
}
