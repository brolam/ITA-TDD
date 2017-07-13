package brolam.com.mocks;

import brolam.com.Armazenavel;

public class ArmazenamentoMock implements Armazenavel {
	boolean pontuacaoRegistrada = false;

	@Override
	public void salvarPontuacaoDoUsuario(String usuario, String tipoDaPontuacao, int pontos) {
		this.pontuacaoRegistrada = true;

	}

	@Override
	public int recuperarPontuacaoDoUsuario(String usuario, String tipoDaPontuacao) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public String[] retornarUsuarios() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String[] retornarTiposDePontuacao() {
		// TODO Auto-generated method stub
		return null;
	}

	public boolean isPontuacaoRegistrada() {
		return this.pontuacaoRegistrada;
	}

}
