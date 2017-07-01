package bancox;

public class CaixaEletronico {
	private ServicoRemoto servicoRemoto;

	public CaixaEletronico(ServicoRemoto servicoRemoto) {
		this.servicoRemoto = servicoRemoto;
	}

	public String logar(String numeroDaContaCorrente) {
		ContaCorrente contaCorrenteRecuperada = servicoRemoto.recuperarConta(numeroDaContaCorrente);
		if (contaCorrenteRecuperada == null)
			return "N�o foi poss�vel autenticar o usu�rio";
		else
			return "Usu�rio Autenticado";
	}
}
