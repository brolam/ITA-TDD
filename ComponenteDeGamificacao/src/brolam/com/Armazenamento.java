package brolam.com;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.util.SortedSet;
import java.util.TreeSet;

public class Armazenamento implements Armazenavel {
	private static final String EXTENSAO_ARQUIVO_DE_PONTUACAO = ".pts";
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

	@Override
	public void salvarPontuacaoDoUsuario(String usuario, String tipoDaPontuacao, int pontos) {
		File arquivoUsuarioPontuacao = recuperarUmArquivoDePontuacao(usuario, tipoDaPontuacao);
		try (FileWriter escreverPontos = new FileWriter(arquivoUsuarioPontuacao)) {
			escreverPontos.write(String.valueOf(pontos));
			escreverPontos.flush();
		} catch (IOException ex) {
			throw new RuntimeException(ex);
		}
	}

	@Override
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
		} catch (FileNotFoundException e) {
			return 0;
		} catch (IOException ex) {
			throw new RuntimeException(ex);
		}
	}

	@Override
	public String[] retornarUsuarios() {
		File diretorioRepositorio = new File(nomeRepositorio);
		SortedSet<String> listaTemporarioDeUsuarios = new TreeSet<String>();
		for (File file : diretorioRepositorio.listFiles()) {
			if (isArquivoDePontuacao(file)) {
				listaTemporarioDeUsuarios.add(getNomeUsuario(file));
			}
		}
		String[] usuariosRecuperados = new String[listaTemporarioDeUsuarios.size()];
		listaTemporarioDeUsuarios.toArray(usuariosRecuperados);
		return usuariosRecuperados;
	}

	@Override
	public String[] retornarTiposDePontuacao() {
		File diretorioRepositorio = new File(nomeRepositorio);
		SortedSet<String> listaTemporarioTiposDePonto = new TreeSet<String>();
		for (File file : diretorioRepositorio.listFiles()) {
			if (isArquivoDePontuacao(file)) {
				listaTemporarioTiposDePonto.add(getNomeTipoDePontuacao(file));
			}
		}
		String[] tiposDePontoRecuperados = new String[listaTemporarioTiposDePonto.size()];
		listaTemporarioTiposDePonto.toArray(tiposDePontoRecuperados);
		return tiposDePontoRecuperados;
	}

	private boolean isArquivoDePontuacao(File file) {
		return file.getName().contains(EXTENSAO_ARQUIVO_DE_PONTUACAO);
	}

	private String getNomeUsuario(File file) {
		return file.getName().split("_")[0];
	}

	private String getNomeTipoDePontuacao(File file) {
		return file.getName().split("_")[1].replace(EXTENSAO_ARQUIVO_DE_PONTUACAO, "");
	}

	private File recuperarUmArquivoDePontuacao(String usuario, String tipoDaPontuacao) {
		String enderecoDoRepositorio = this.diretorioRepositorio.getAbsolutePath();
		String nomeDoArquivoPontuacao = String.format("%s/%s_%s", enderecoDoRepositorio, usuario, tipoDaPontuacao);
		return new File(nomeDoArquivoPontuacao + EXTENSAO_ARQUIVO_DE_PONTUACAO);
	}

}
