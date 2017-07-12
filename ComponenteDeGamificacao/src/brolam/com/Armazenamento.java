package brolam.com;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;

public class Armazenamento {
	String repositorio = "Repositorio";

	public void salvarPontuacaoDoUsuario(String usuario, String tipoDaPontuacao, int pontos) {
		File diretorioRepositorio = new File(repositorio);
		if ( diretorioRepositorio.exists()  == false)
			diretorioRepositorio.mkdirs();
		File arquivoUsuarioPontos = new File(String.format("%s/%s_%s.pts", diretorioRepositorio.getAbsolutePath(), usuario, tipoDaPontuacao));
		try (FileWriter escreverPontos = new FileWriter(arquivoUsuarioPontos)) {
			escreverPontos.write(String.valueOf(pontos));
			escreverPontos.flush();
		} catch (IOException ex) {
			ex.printStackTrace();
		}
	}

	public int recuperarPontuacaoDoUsuario(String usuario, String tipoDaPontuacao) {
		File diretorioRepositorio = new File(repositorio);
		File arquivoUsuarioPontos = new File(String.format("%s/%s_%s.pts", diretorioRepositorio.getAbsolutePath(), usuario, tipoDaPontuacao));
		try (InputStream lerPontos = new FileInputStream(arquivoUsuarioPontos)) {
			int content;
			StringBuilder conteudoDoArquivo = new StringBuilder();
			while ((content = lerPontos.read()) != -1) {
				conteudoDoArquivo.append((char) content);
			}
			lerPontos.close();
			return Integer.valueOf(conteudoDoArquivo.toString());
		} catch (IOException ex) {
			ex.printStackTrace();
		}
		return 0;
	}

}
