package bancox.mocks;

import org.eclipse.jdt.annotation.Nullable;
import bancox.ContaCorrente;
import bancox.ServicoRemoto;

public class MockServicoRemoto implements ServicoRemoto {
	String numeroCadastrado;

	public void setContaCorrenteCadastrada(String numeroDaContaCorrente) {
		this.numeroCadastrado = numeroDaContaCorrente;
	}

	@Override
	public @Nullable ContaCorrente recuperarConta(String numeroDaContaCorrente) {
		if (this.numeroCadastrado.equals(numeroDaContaCorrente) )
			return new ContaCorrente(numeroCadastrado);
		else
			return null;
	}

	@Override
	public void persistirConta(ContaCorrente contaCorrente) {
	}
}
