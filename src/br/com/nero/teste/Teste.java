package br.com.nero.teste;

import java.io.IOException;

import javax.swing.JOptionPane;

import br.com.nero.modelos.GerenciadorLogins;
import br.com.nero.modelos.Login;

public class Teste {
	public static void main(String[] args) {

		String message = "Registrar Login" + System.lineSeparator() + "Procurar Login" + System.lineSeparator()
				+ "Sair";
		while(true) {
			String escolha = JOptionPane.showInputDialog(message);
			
			if(escolha == null) {
				return;
			}
			
			escolha = escolha.toUpperCase();

			switch (escolha) {
			case Tags.RegistrarLogin:
				String identificador = JOptionPane.showInputDialog("Insira o identificador do login").toUpperCase();
				String usuario = JOptionPane.showInputDialog("Insira o usuário do login");
				String senha = JOptionPane.showInputDialog("Insira a senha do login");
				Login login = new Login(identificador, usuario, senha);
				try {
					GerenciadorLogins.SalvarLogin(login);
				} catch (IOException e) {
					e.printStackTrace();
				}
				break;

			case Tags.ProcurarLogin:
				String identificador1 = JOptionPane.showInputDialog("Insira o identificador do login").toUpperCase();
				Login loginProcurado = GerenciadorLogins.ProcurarLogin(identificador1);
				String mensagem = "Identificador: " + loginProcurado.getIdentificador() + System.lineSeparator() +
						"Usuário: " + loginProcurado.getUsuario() + System.lineSeparator() +
						"Senha: " + loginProcurado.getSenha();
				JOptionPane.showMessageDialog(null, mensagem);
				break;

			case Tags.Sair:
				System.exit(0);;
			default:
				throw new IllegalArgumentException("Opção inválida");
			}
		}
	}
}