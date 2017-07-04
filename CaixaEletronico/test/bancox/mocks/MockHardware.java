package bancox.mocks;

import bancox.Hardware;
import bancox.HardwareExceptions;

public class MockHardware implements Hardware {
	public enum SimularFalha{
		WHEN_LER_CARTAO, WHEN_LER_ENVELOPE, WHEN_ENTREGAR_DINHEIRO,
	}
	SimularFalha simularFalha;
	String numeroCadastrado;

	public void setSimularFalha(SimularFalha simularFalha) {
		this.simularFalha = simularFalha;
	}
		
	@Override
	public String pegarNumeroDaContaCartao() {
		return numeroCadastrado;
	}

	public void setContaCorrenteCadastrada(String numeroDaContaCorrente) {
		this.numeroCadastrado = numeroDaContaCorrente;
	}

	@Override
	public void LerEnvelope() throws HardwareExceptions.LerEnvelopeException {
	}

	@Override
	public void entregarDinheiro() throws HardwareExceptions.EntregarDinheiroException {
		if ( this.simularFalha == SimularFalha.WHEN_ENTREGAR_DINHEIRO)
			throw new HardwareExceptions.EntregarDinheiroException();
		
	}

}
