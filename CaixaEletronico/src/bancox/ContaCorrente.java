package bancox;

public class ContaCorrente {
	private String numero;
	private Double saldo;
	
	public ContaCorrente(String numero){
		this.numero = numero;
		this.saldo = 0.00;
	}
	
	public void adicionarSaldo(double valorDepositado){
		this.saldo += valorDepositado;
	}
}
