package brolam.com;

import static org.junit.Assert.*;
import org.junit.Test;
import brolam.com.mocks.ArmazenamentoMock;
import brolam.com.models.Pontuacao;
import brolam.com.models.Usuario;

public class PlacarTest {

	@Test
	public void registrarPontuacaoDoUsuario() {
		ArmazenamentoMock armazenamentoMock = new ArmazenamentoMock();
		Placar placar = new Placar(armazenamentoMock);
		Usuario usuario = new Usuario("guerra");
		Pontuacao pontuacao = new  Pontuacao("estrela", 10);
		placar.registrarPontuacao(usuario, pontuacao);
		assertTrue(armazenamentoMock.isPontuacaoRegistrada());
	}

}
