package br.com.farmacia.factory;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexaoPostgres {

	private static final String USUARIO = "postgres";
	private static final String SENHA = "eliasmd8";
	private static final String URL = "jdbc:postgresql://localhost:5434/sistema";

	public static Connection conectar() throws SQLException, ClassNotFoundException {
		Class.forName("org.postgresql.Driver");
		Connection conexao = DriverManager.getConnection(URL, USUARIO, SENHA);
		return conexao;
	}

	public static void main(String[] args) throws ClassNotFoundException {

		try {
			Connection conexao = ConexaoFactory.conectar();
			System.out.println("Conectado com suscesso");
		} catch (SQLException ex) {
			ex.printStackTrace();
			System.out.println("Conexao falhou");
		}
	}

}
