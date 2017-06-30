package bancox.mocks;

import org.eclipse.jdt.annotation.Nullable;
import bancox.ContaCorrente;
import bancox.ServicoRemoto;

public class MockServicoRemoto implements ServicoRemoto {
	int numeroCadastrado;

	public void setContaCorrenteCadastrada(int numeroDaContaCorrente) {
		this.numeroCadastrado = numeroDaContaCorrente;
	}

	@Override
	public @Nullable ContaCorrente recuperarConta(int numeroDaContaCorrente) {
		if (numeroDaContaCorrente == this.numeroCadastrado)
			return new ContaCorrente(numeroCadastrado);
		else
			return null;
	}
}
