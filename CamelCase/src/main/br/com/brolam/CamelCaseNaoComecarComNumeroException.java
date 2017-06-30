package main.br.com.brolam;

public class CamelCaseNaoComecarComNumeroException extends Exception {
	private static final long serialVersionUID = 1L;
	public CamelCaseNaoComecarComNumeroException(){
		super("Inválido â†’ não deve começar com nómeros");
	}

}
