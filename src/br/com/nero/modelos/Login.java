package br.com.nero.modelos;

public class Login extends Object {
	private final String identificador;
	private final String usuario;
	private final String senha;
	
	public Login(String identificador, String usuario, String senha) {
		super();
		if(identificador.isBlank() || usuario.isBlank() || senha.isBlank()) {
			throw new IllegalArgumentException("Definição de login inválida, algum argumento em branco.");
		}
		this.identificador = identificador;
		this.usuario = usuario;
		this.senha = senha;
	}
	public Login(String linha) {
		int indiceID = linha.indexOf(';');
		int indiceUsuario = linha.indexOf(';', indiceID + 1);
		
		this.identificador = linha.substring(0, indiceID);
		this.usuario = linha.substring(indiceID + 1, indiceUsuario);
		this.senha = linha.substring(indiceUsuario + 1, linha.length());
	}
	@Override
	public String toString() {
		return "Identificador: " + this.identificador + System.lineSeparator() + 
				"Usuário: " + this.usuario + System.lineSeparator() +
				"Senha: " + this.senha;
	}
	public String getLoginLine() {
		return identificador + ";" + usuario + ";" + senha;
	}
	public String getIdentificador() {
		return identificador;
	}
	public String getUsuario() {
		return usuario;
	}
	public String getSenha() {
		return senha;
	}
}