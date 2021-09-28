package br.com.nero.modelos;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.Reader;
import java.io.Writer;
import java.sql.*;

public abstract class GerenciadorLogins {
	static final File arquivo = new File("Logins.csv");
	
	public static void SalvarLogin(Login login) throws IOException {
		boolean anexar = true;
		if (!arquivo.exists()) {
			arquivo.createNewFile();
			anexar = false;
		}

		OutputStream fos = new FileOutputStream(arquivo, anexar);
		Writer osw = new OutputStreamWriter(fos);
		BufferedWriter bw = new BufferedWriter(osw);

		bw.append(login.getLoginLine());
		bw.newLine();

		bw.close();
		osw.close();
		fos.close();
	}
	
	public static void SalvarLoginSQL(Login login) throws SQLException {
		Conexao conexao = new Conexao();
		Statement st = conexao.conexao.createStatement();
		

		// identificador usuario senha

		st.executeQuery("CREATE DATABASE IF NOT EXISTS logins;");
		st.executeQuery("USE logins;");
		st.executeUpdate("CREATE TABLE logins(siteRegistrado varchar(255), usuario varchar(255), senha varchar(255));");
		st.executeUpdate("ALTER TABLE logins ADD idLogin INT NOT NULL auto_increment;");
		st.executeUpdate("INSERT INTO logins (siteRegistrado, usuario, senha) values ("+ login.getIdentificador() + "," + login.getUsuario() + "," + login.getSenha() + ");");

		ResultSet rs = st.getResultSet();
		
		while(rs.next()) {
			String message = rs.getString("siteRegistrado") + System.lineSeparator() +
				rs.getString("usuario") + System.lineSeparator() +
				rs.getString("senha") + System.lineSeparator();
			System.out.println(message);
		}

		/*
			st.executeUpdate(STRING);
			st.executeQuery(STRING);
		 */
	}

	public static Login ProcurarLogin(String identificador) {
		
		if(identificador.isEmpty()) {
			throw new IllegalArgumentException("Identificador vazio.");
		}
		
		InputStream fis;
		Reader isr;
		BufferedReader br;
		try {
			fis = new FileInputStream(arquivo);
			isr = new InputStreamReader(fis);
			br = new BufferedReader(isr);
			
			String linha = " ";
			String identificadorLinha = " ";
			while(!identificadorLinha.equals(identificador)) {
				linha = br.readLine();
				identificadorLinha = getIdentificador(linha);
				if(linha == null) {
					br.close();
					throw new IllegalArgumentException("Login inexistente.");
				}
			}
			br.close();
			return new Login(linha);
		}
		catch (IOException e) {
			e.getStackTrace();
		}
		throw new IllegalArgumentException("Login inexistente.");
	}
	static String getIdentificador(String linha) {
		
		if(linha == null) {
			throw new IllegalArgumentException("Argumento inv�lido");
		}
		
		String identificador;
		int index = linha.indexOf(';');
		
		identificador = linha.substring(0, index);
		return identificador;
	}
	public static boolean LoginExists(Login login) throws IOException {
		InputStream fis;
		Reader isr;
		BufferedReader br;
		try {
			fis = new FileInputStream(arquivo);
			isr = new InputStreamReader(fis);
			br = new BufferedReader(isr);

			String linha = " ";
			String linhaLogin = login.getLoginLine();

			while (!linhaLogin.equals(linha)) {
				linha = br.readLine();

				if (linha == null) {
					br.close();
					return false;
				}

				System.out.println(linha);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return true;	
	}
}