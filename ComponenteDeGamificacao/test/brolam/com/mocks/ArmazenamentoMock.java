package brolam.com.mocks;

import brolam.com.Armazenavel;
import brolam.com.models.Pontuacao;

public class ArmazenamentoMock implements Armazenavel {
	boolean pontuacaoRegistrada = false;
	Pontuacao[] similuarListaDePontuacao = new Pontuacao[]{};

	@Override
	public void salvarPontuacaoDoUsuario(String usuario, String tipoDaPontuacao, int pontos) {
		this.pontuacaoRegistrada = true;
	}

	@Override
	public int recuperarPontuacaoDoUsuario(String usuario, String tipoDaPontuacao) {
		for(Pontuacao pontuacao: similuarListaDePontuacao){
			if(pontuacao.getTipo() == tipoDaPontuacao)
				return pontuacao.getPontos();
		}
		return 0;
	}

	@Override
	public String[] retornarUsuarios() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String[] retornarTiposDePontuacao() {
		String[] listaTipoDaPontuacao = new String[similuarListaDePontuacao.length];
		for(int indexDaPontuacao = 0; indexDaPontuacao < listaTipoDaPontuacao.length; indexDaPontuacao++){
			listaTipoDaPontuacao[indexDaPontuacao] = similuarListaDePontuacao[indexDaPontuacao].getTipo();
		}
		return listaTipoDaPontuacao;
	}

	public boolean isPontuacaoRegistrada() {
		return this.pontuacaoRegistrada;
	}

	public void simularListaPontuacao(Pontuacao[] listDePontuacaoEsperada) {
		this.similuarListaDePontuacao = listDePontuacaoEsperada;
	}

}
