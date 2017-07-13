package brolam.com.models;

public class Usuario {
	private String nome;

	public Usuario(String nome) {
		this.nome = nome;
	}
	
	public String getName() {
		return this.nome;
	}
	
	@Override
	public String toString() {
		return getName();
	}
	
	@Override
	public boolean equals(Object obj) {
		return this.toString().equals(obj.toString());
	}

}
