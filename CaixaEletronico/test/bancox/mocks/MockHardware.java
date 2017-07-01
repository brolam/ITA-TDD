package bancox.mocks;

import bancox.Hardware;

public class MockHardware implements Hardware {
	String numeroCadastrado;

	@Override
	public String pegarNumeroDaContaCartao() {
		return numeroCadastrado;
	}

	public void setContaCorrenteCadastrada(String numeroDaContaCorrente) {
		this.numeroCadastrado = numeroDaContaCorrente;
	}

}
