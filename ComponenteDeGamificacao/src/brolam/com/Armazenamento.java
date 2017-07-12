package brolam.com;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.util.SortedSet;
import java.util.TreeSet;

public class Armazenamento {
	String nomeRepositorio;
	File diretorioRepositorio;

	public Armazenamento() {
		this("Repositorio");
	}

	public Armazenamento(String nomeRepositorio) {
		this.nomeRepositorio = nomeRepositorio;
		this.diretorioRepositorio = new File(this.nomeRepositorio);
		if (this.diretorioRepositorio.exists() == false)
			this.diretorioRepositorio.mkdirs();
	}

	public void salvarPontuacaoDoUsuario(String usuario, String tipoDaPontuacao, int pontos) {
		File arquivoUsuarioPontuacao = recuperarUmArquivoDePontuacao(usuario, tipoDaPontuacao);
		try (FileWriter escreverPontos = new FileWriter(arquivoUsuarioPontuacao)) {
			escreverPontos.write(String.valueOf(pontos));
			escreverPontos.flush();
		} catch (IOException ex) {
			throw new RuntimeException(ex);
		}
	}

	public int recuperarPontuacaoDoUsuario(String usuario, String tipoDaPontuacao) {
		File arquivoUsuarioPontuacao = recuperarUmArquivoDePontuacao(usuario, tipoDaPontuacao);
		try (InputStream lerConteudoDoArquivo = new FileInputStream(arquivoUsuarioPontuacao)) {
			int caracter;
			StringBuilder conteudoArquivoDePontuacao = new StringBuilder();
			while ((caracter = lerConteudoDoArquivo.read()) != -1) {
				conteudoArquivoDePontuacao.append((char) caracter);
			}
			lerConteudoDoArquivo.close();
			return Integer.valueOf(conteudoArquivoDePontuacao.toString());
		} catch (IOException ex) {
			throw new RuntimeException(ex);
		}
	}

	public String[] retornarUsuarios() {
		File diretorioRepositorio = new File(nomeRepositorio);
		SortedSet<String> listaTemporarioDeUsuarios = new TreeSet<String>();
		for (File file : diretorioRepositorio.listFiles()) {
			if (file.getName().contains(".pts")) {
				String nomeUsuario = file.getName().split("_")[0];
				listaTemporarioDeUsuarios.add(nomeUsuario);
			}
		}
		String[] usuariosRecuperados = new String[listaTemporarioDeUsuarios.size()];
		listaTemporarioDeUsuarios.toArray(usuariosRecuperados);
		return usuariosRecuperados;
	}

	public String[] retornarTiposDePontuacao() {
		File diretorioRepositorio = new File(nomeRepositorio);
		SortedSet<String> listaTemporarioTiposDePonto = new TreeSet<String>();
		for (File file : diretorioRepositorio.listFiles()) {
			if (file.getName().contains(".pts")) {
				String tiposDePonto = file.getName().split("_")[1].replace(".pts", "");
				listaTemporarioTiposDePonto.add(tiposDePonto);
			}
		}
		String[] tiposDePontoRecuperados = new String[listaTemporarioTiposDePonto.size()];
		listaTemporarioTiposDePonto.toArray(tiposDePontoRecuperados);
		return tiposDePontoRecuperados;
	}

	private File recuperarUmArquivoDePontuacao(String usuario, String tipoDaPontuacao) {
		String enderecoDoRepositorio = this.diretorioRepositorio.getAbsolutePath();
		String nomeDoArquivoPontuacao = String.format("%s/%s_%s.pts", enderecoDoRepositorio , usuario, tipoDaPontuacao);
		return new File(nomeDoArquivoPontuacao);
	}

}
