package main.br.com.brolam;

public class CamelCaseCaracterInvalidoException extends Exception {
	private static final long serialVersionUID = 1L;
	
	public CamelCaseCaracterInvalidoException(){
		super("Inválido → caracteres especiais não são permitidos, somente letras e números");
	}
	
	

}
