package bancox;

public class CaixaEletronico {
	private ServicoRemoto servicoRemoto;

	public CaixaEletronico(ServicoRemoto servicoRemoto) {
		this.servicoRemoto = servicoRemoto;
	}

	public String logar(int numeroDaContaCorrente) {
		ContaCorrente contaCorrenteRecuperada = servicoRemoto.recuperarConta(numeroDaContaCorrente);
		if (contaCorrenteRecuperada == null)
			return "";
		else
			return "Usu�rio Autenticado";
	}
}
