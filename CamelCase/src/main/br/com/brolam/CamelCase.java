package main.br.com.brolam;

import java.util.ArrayList;
import java.util.List;

public class CamelCase {

	public static List<String> converterCamelCase(String original) throws CamelCaseNaoComecarComNumeroException, CamelCaseCaracterInvalidoException {
		verificarPreRequisitos(original);
		ArrayList<String> palavras = new ArrayList<>();
		char[] caracteres = original.toCharArray();
		int idxInicioProximaPalavra = 0;
		while (idxInicioProximaPalavra < original.length()) {
			String umaPalavra = recuperarUmaPalavra(caracteres, idxInicioProximaPalavra);
			palavras.add(formatarPalavra(umaPalavra));
			idxInicioProximaPalavra += umaPalavra.length();
		}
		return palavras;
	}

	private static String formatarPalavra(String umaPalavra) {
		String palavraTodaMaiuscula = umaPalavra.toUpperCase();
		if (umaPalavra.equals(palavraTodaMaiuscula))
			return palavraTodaMaiuscula;
		else
			return umaPalavra.toLowerCase();
	}

	private static String recuperarUmaPalavra(char[] caracteres, int idxInicioProximaPalavra) {
		String umaPalavra = String.valueOf(caracteres[idxInicioProximaPalavra]);
		for (int idxCaracter = idxInicioProximaPalavra + 1; idxCaracter < caracteres.length; idxCaracter++) {
			umaPalavra = umaPalavra.concat(String.valueOf(caracteres[idxCaracter]));
			if (finalDaPalavra(caracteres, idxCaracter))
				break;
		}
		return umaPalavra;
	}

	private static boolean finalDaPalavra(char[] caracteres, int idxCaracterAtual) {
		int idxProximoCaracter = idxCaracterAtual + 1, idxTerceiroCaracter = idxProximoCaracter + 1;
		if (idxTerceiroCaracter < caracteres.length) {
			if (Character.isLetter(caracteres[idxCaracterAtual]) != Character.isLetter(caracteres[idxProximoCaracter])) // Exemplo: recupera10Primeiros
				return true;
			if (Character.isUpperCase(caracteres[idxCaracterAtual]) && Character.isLowerCase(caracteres[idxTerceiroCaracter])) // Exemplo: numeroCPFContribuinte
				return true;
			else if (Character.isUpperCase(caracteres[idxCaracterAtual]) != Character.isUpperCase(caracteres[idxProximoCaracter])) // Exemplo: numeroCPF
				return true;
		}
		return false;
	}

	private static void verificarPreRequisitos(String original)
			throws CamelCaseNaoComecarComNumeroException, CamelCaseCaracterInvalidoException {
		char primeiroCaracter = original.charAt(0);
		if (!Character.isLetter(primeiroCaracter))
			throw new CamelCaseNaoComecarComNumeroException();
		for (char caracter : original.toCharArray()) {
			if (!Character.isLetterOrDigit(caracter))
				throw new CamelCaseCaracterInvalidoException();
		}
	}
}