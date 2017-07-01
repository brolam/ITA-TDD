package bancox;

import org.eclipse.jdt.annotation.Nullable;

public interface ServicoRemoto {
	@Nullable ContaCorrente recuperarConta(String numeroDaContaCorrente);
}
