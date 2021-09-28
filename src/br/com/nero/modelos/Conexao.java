package br.com.nero.modelos;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.DriverManager;

public class Conexao {
    String serverName = "Localhost";
    String myDatabase = "logins";
    String url = "jdbc:mysql://" + serverName + "/" + myDatabase;
    String username = "root";
    String password = "inserida";
    Connection conexao;

    public Conexao() throws SQLException {
        this.conexao = DriverManager.getConnection(url, username, password);
    }
}