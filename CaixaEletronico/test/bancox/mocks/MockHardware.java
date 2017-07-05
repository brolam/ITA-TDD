package bancox.mocks;

import bancox.Hardware;
import bancox.HardwareExceptions;
import bancox.HardwareExceptions.LerCartaoException;

public class MockHardware implements Hardware {
	public enum SimularFalha{
		WHEN_LER_CARTAO, WHEN_LER_ENVELOPE, WHEN_ENTREGAR_DINHEIRO,
	}
	SimularFalha simularFalha;
	String numeroDaContaCorrenteNoCartao;

	public void setSimularFalha(SimularFalha simularFalha) {
		this.simularFalha = simularFalha;
	}
		
	@Override
	public String pegarNumeroDaContaCartao() throws LerCartaoException {
		if ( this.simularFalha == SimularFalha.WHEN_LER_CARTAO)
			throw new HardwareExceptions.LerCartaoException();
		return numeroDaContaCorrenteNoCartao;
	}

	public void inseriCartao(String numeroDaContaCorrente) {
		this.numeroDaContaCorrenteNoCartao = numeroDaContaCorrente;
	}

	@Override
	public void LerEnvelope() throws HardwareExceptions.LerEnvelopeException {
		if ( this.simularFalha == SimularFalha.WHEN_LER_ENVELOPE)
			throw new HardwareExceptions.LerEnvelopeException();
	}

	@Override
	public void entregarDinheiro() throws HardwareExceptions.EntregarDinheiroException {
		if ( this.simularFalha == SimularFalha.WHEN_ENTREGAR_DINHEIRO)
			throw new HardwareExceptions.EntregarDinheiroException();
		
	}

}
