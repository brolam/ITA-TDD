package main.br.com.brolam;

public class CamelCaseNaoComecarComNumeroException extends Exception {
	private static final long serialVersionUID = 1L;
	public CamelCaseNaoComecarComNumeroException(){
		super("Inv�lido → n�o deve come�ar com n�meros");
	}

}
