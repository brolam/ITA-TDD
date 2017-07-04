package bancox.mocks;

import bancox.Hardware;
import bancox.HardwareExceptions;

public class MockHardwareComFalha implements Hardware {
	public enum ConfigurarFalha{
		LER_CARTAO, LER_ENVELOPE,
	}
	
	ConfigurarFalha configurarFalha;
	
	public MockHardwareComFalha(ConfigurarFalha configurarFalha) {
		this.configurarFalha = configurarFalha;
	}

	@Override
	public String pegarNumeroDaContaCartao() throws HardwareExceptions.LerCartaoException {
		if ( this.configurarFalha == ConfigurarFalha.LER_CARTAO)
			throw new HardwareExceptions.LerCartaoException();
		return null;
	}
	
	@Override
	public void LerEnvelope() throws HardwareExceptions.LerEnvelopeException {
		if ( this.configurarFalha == ConfigurarFalha.LER_ENVELOPE)
			throw new HardwareExceptions.LerEnvelopeException();
		return;
	}

	@Override
	public void entregarDinheiro() {
		// TODO Auto-generated method stub
		
	}
	
}
