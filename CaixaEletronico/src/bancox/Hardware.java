package bancox;

public interface Hardware {
	String pegarNumeroDaContaCartao() throws HardwareExceptions.LerCartaoException;
	void LerEnvelope() throws HardwareExceptions.LerEnvelopeException;
	void entregarDinheiro()  throws HardwareExceptions.EntregarDinheiroException;
}
