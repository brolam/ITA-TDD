package bancox;

import bancox.HardwareExceptions.EntregarDinheiroException;
import bancox.HardwareExceptions.LerCartaoException;
import bancox.HardwareExceptions.LerEnvelopeException;

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

	public String logar() {
		if (isHardwareOnLine()) {
			try {
				String numeroDaContaCorrente = hardware.pegarNumeroDaContaCartao();
				return logar(numeroDaContaCorrente);
			} catch (LerCartaoException e) {
				return e.getMessage();
			}
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
			return "Dep�sito recebido com sucesso!";
		}
	}

	public String depositar(double valorDepositado) {
		if (isHardwareOnLine()) {
			try {
				String numeroDaContaLidoNoCartao = hardware.pegarNumeroDaContaCartao();
				hardware.LerEnvelope();
				return this.depositar(numeroDaContaLidoNoCartao, valorDepositado);
			} catch (LerCartaoException e) {
				return e.getMessage();
			} catch (LerEnvelopeException e) {
				return e.getMessage();
			}
			
		}
		return MSG_HARDWARE_INDISPONIVEL;
	}

	public String saldo(String numeroDaContaCorrente) {
		ContaCorrente contaCorrenteConsultaSaldo = servicoRemoto.recuperarConta(numeroDaContaCorrente);
		if (contaCorrenteConsultaSaldo == null)
			return "N�o foi poss�vel consultar o Saldo!";
		else{
			String mensagemSaldo = String.format("O saldo � R$%.2f", contaCorrenteConsultaSaldo.getSaldo());
			return mensagemSaldo;
		}
	}

	public String saldo() {
		if (isHardwareOnLine()) {
			try {
				String numeroDaContaLidoNoCartao = hardware.pegarNumeroDaContaCartao();
				return this.saldo(numeroDaContaLidoNoCartao);
			} catch (LerCartaoException e) {
				return e.getMessage();
			}
		}
		return MSG_HARDWARE_INDISPONIVEL;
	}

	public String sacar(String numeroDaContaCorrente, Double valorDoSaque) {
		ContaCorrente contaCorrenteParaSaque = servicoRemoto.recuperarConta(numeroDaContaCorrente);
		if (contaCorrenteParaSaque == null)
			return "Saque n�o foi realizado!";
		if (contaCorrenteParaSaque.isSaldoSuficienteParaSaque(valorDoSaque)) {
			contaCorrenteParaSaque.realizarSaque(valorDoSaque);
			this.servicoRemoto.persistirConta(contaCorrenteParaSaque);
			return "Retire seu dinheiro";
		} else {
			return "Saldo insuficiente";
		}
	}
	
	private void cancelarSaque(String numeroDaContaCorrente, double valorDoSaque) {
		this.depositar(numeroDaContaCorrente, valorDoSaque);
	}

	public String sacar(double valorDoSaque) {
		String numeroDaContaLidoNoCartao = null;
		if (isHardwareOnLine()) {
			try {
				numeroDaContaLidoNoCartao = hardware.pegarNumeroDaContaCartao();
				String mensagemDeConfirmacaoOuFalhaDoSaque = this.sacar(numeroDaContaLidoNoCartao, valorDoSaque);
				hardware.entregarDinheiro();
				return mensagemDeConfirmacaoOuFalhaDoSaque;
			} catch( LerCartaoException e){
				return e.getMessage();
			} catch (EntregarDinheiroException e) {
				this.cancelarSaque(numeroDaContaLidoNoCartao, valorDoSaque);
				return e.getMessage();
			}
		}
		return MSG_HARDWARE_INDISPONIVEL;
	}
}
