package bancox.mocks;

import org.eclipse.jdt.annotation.Nullable;
import bancox.ContaCorrente;
import bancox.ServicoRemoto;

public class MockServicoRemoto implements ServicoRemoto {
	ContaCorrente contaCorrenteSimulado;
	int contarPersistirConta = 0; 

	public void setContaCorrenteCadastrada(String numeroDaContaCorrente) {
		this.contaCorrenteSimulado = new ContaCorrente(numeroDaContaCorrente);
	}

	@Override
	public @Nullable ContaCorrente recuperarConta(String numeroDaContaCorrente) {
		if ( this.contaCorrenteSimulado == null)
			return null;
		if (numeroDaContaCorrente.equals(this.contaCorrenteSimulado.getNumero()) )
			return this.contaCorrenteSimulado;
		else
			return null;
	}

	@Override
	public void persistirConta(ContaCorrente contaCorrente) {
		contarPersistirConta++;
	}
	
	public int getContarPersistirConta() {
		return contarPersistirConta;
	}
}
