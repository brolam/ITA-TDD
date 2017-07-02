package bancox;

public abstract class HardwareExceptions extends Exception {
	private static final long serialVersionUID = 1L;

	public HardwareExceptions(String message) {
		super(message);
	}
	
	public static class LerCartaoException extends HardwareExceptions{
		private static final long serialVersionUID = 1L;
		
		public LerCartaoException() {
			super("Erro na leitura do cartão!");
		}

	}
	

}
