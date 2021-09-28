package br.com.nero.teste;

import java.sql.SQLException;

import br.com.nero.modelos.Conexao;
import br.com.nero.modelos.GerenciadorLogins;
import br.com.nero.modelos.Login;

public class TesteSQL {
    public static void main(String[] args) {
        try {
            Conexao conexao = new Conexao();
            Login login = new Login("TESTESQL", "USER", "PASS");
            GerenciadorLogins.SalvarLoginSQL(login);

        } catch(SQLException e) {
            e.printStackTrace();
        }
    }
}