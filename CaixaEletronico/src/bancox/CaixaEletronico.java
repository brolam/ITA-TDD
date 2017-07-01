package bancox;

public class CaixaEletronico {
	private ServicoRemoto servicoRemoto;

	public CaixaEletronico(ServicoRemoto servicoRemoto) {
		this.servicoRemoto = servicoRemoto;
	}

	public String logar(String numeroDaContaCorrente) {
		ContaCorrente contaCorrenteRecuperada = servicoRemoto.recuperarConta(numeroDaContaCorrente);
		if (contaCorrenteRecuperada == null)
			return "Não foi possível autenticar o usuário";
		else
			return "Usuário Autenticado";
	}
}
