package bancox.mocks;

import bancox.Hardware;
import bancox.HardwareExceptions;

public class MockHardware implements Hardware {
	String numeroCadastrado;

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

}
