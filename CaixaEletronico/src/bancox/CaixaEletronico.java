package bancox;

import bancox.HardwareExceptions.LerCartaoException;
import bancox.HardwareExceptions.LerEnvelopeException;

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
			return "Depósito não foi realizado!";
		else{
			contaCorrenteParaDeposito.adicionarSaldo(valorDepositado);
			this.servicoRemoto.persistirConta(contaCorrenteParaDeposito);
			return "Depósito recebido com sucesso!";
		}
	}

	public String depositar(double valorDepositado) {
		if (isHardwareOnLine()) {
			String numeroDaContaLidoNoCartao;
			try {
				numeroDaContaLidoNoCartao = hardware.pegarNumeroDaContaCartao();
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
		ContaCorrente contaCorrenteRecuperada = servicoRemoto.recuperarConta(numeroDaContaCorrente);
		if (contaCorrenteRecuperada == null)
			return "Não foi possível consultar o Saldo!";
		else{
			String mensagemSaldo = String.format("O saldo é R$%.2f", contaCorrenteRecuperada.getSaldo());
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
			return "Saque não foi realizado!";
		if (contaCorrenteParaSaque.isSaldoSuficienteParaSaque(valorDoSaque)) {
			contaCorrenteParaSaque.realizarSaque(valorDoSaque);
			return "Retire seu dinheiro";
		} else {
			return "Saldo insuficiente";
		}
	}

	public String sacar(double valorDoSaque) {
		if (isHardwareOnLine()) {
			try {
				String numeroDaContaLidoNoCartao = hardware.pegarNumeroDaContaCartao();
				String mensagemSaqueComSucesso = this.sacar(numeroDaContaLidoNoCartao, valorDoSaque);
				hardware.entregarDinheiro();
				return mensagemSaqueComSucesso;
			} catch (HardwareExceptions e) {
				return null;
			}
		}
		return MSG_HARDWARE_INDISPONIVEL;
	}
}
