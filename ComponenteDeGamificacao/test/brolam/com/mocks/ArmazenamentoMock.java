package brolam.com.mocks;

import brolam.com.Armazenavel;

public class ArmazenamentoMock implements Armazenavel {
	boolean pontuacaoRegistrada = false;

	public enum Simuladores {
		RetornarPontuacaoDoUsuario, SomentePontuacaoMaiorDoQueZero, RetornarUsuariosRanking , RankingSomenteUsuariosDoTipoDaPontuaoEspecificada
	}

	Simuladores simulador = null;

	public void setSimulador(Simuladores simuladores) {
		this.simulador = simuladores;
	}
	
	public boolean isPontuacaoRegistrada() {
		return this.pontuacaoRegistrada;
	}

	@Override
	public void salvarPontuacaoDoUsuario(String usuarioNome, String tipoDaPontuacao, int pontos) {
		this.pontuacaoRegistrada = true;
	}

	@Override
	public int recuperarPontuacaoDoUsuario(String usuarioNome, String tipoDaPontuacao) {
		switch (this.simulador) {
		case RetornarPontuacaoDoUsuario:
			if (usuarioNome != "guerra")
				return 0;
			if (tipoDaPontuacao == "estrela")
				return 10;
			if (tipoDaPontuacao == "moeda")
				return 25;
			if (tipoDaPontuacao == "energia")
				return 0;
		case SomentePontuacaoMaiorDoQueZero:
			if (usuarioNome != "guerra")
				return 0;
			if (tipoDaPontuacao == "estrela")
				return 10;
			if (tipoDaPontuacao == "energia")
				return 0;
		case RetornarUsuariosRanking:
			if ((usuarioNome == "rodrigo") && (tipoDaPontuacao == "estrela"))
				return 17;
			if ((usuarioNome == "fernandes") && (tipoDaPontuacao == "estrela"))
				return 19;
			if ((usuarioNome == "guerra") && (tipoDaPontuacao == "estrela"))
				return 25;
		case RankingSomenteUsuariosDoTipoDaPontuaoEspecificada:
			if ((usuarioNome == "guerra") && (tipoDaPontuacao == "estrela"))
				return 25;
			if ((usuarioNome == "toco") && (tipoDaPontuacao == "moeda"))
				return 19;
		default:
			return 0;
		}
	}

	@Override
	public String[] retornarUsuarios() {
		switch (this.simulador) {
		case RetornarUsuariosRanking:
			return new String[] { "fernandes", "guerra", "rodrigo", };
		case RankingSomenteUsuariosDoTipoDaPontuaoEspecificada:
			return new String[] {"guerra", "toco", };
		default:
			return new String[] {};
		}
	}

	@Override
	public String[] retornarTiposDePontuacao() {
		switch (this.simulador) {
		case RetornarPontuacaoDoUsuario:
			return new String[] { "estrela", "moeda" };
		case SomentePontuacaoMaiorDoQueZero:
			return new String[] { "energia", "estrela" };
		case RetornarUsuariosRanking:
			return new String[] { "estrela" };
		case RankingSomenteUsuariosDoTipoDaPontuaoEspecificada:
			return new String[] { "estrela", "moeda" };
		default:
			return new String[] {};
		}
	}

}
