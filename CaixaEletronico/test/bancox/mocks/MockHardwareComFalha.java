package bancox.mocks;

import bancox.Hardware;
import bancox.HardwareExceptions;

public class MockHardwareComFalha implements Hardware {
	public enum ConfigurarFalha{
		LER_CARTAO,
	}
	
	ConfigurarFalha configurarFalha;
	
	public MockHardwareComFalha(ConfigurarFalha configurarFalha) {
		this.configurarFalha = configurarFalha;
	}

	@Override
	public String pegarNumeroDaContaCartao() throws HardwareExceptions {
		lancarExceptionConfigurada();
		return null;
	}
	
	private void lancarExceptionConfigurada() throws HardwareExceptions{
		switch (this.configurarFalha) {
		case LER_CARTAO:
			throw new HardwareExceptions.LerCartaoException();
		}			
	}
}
