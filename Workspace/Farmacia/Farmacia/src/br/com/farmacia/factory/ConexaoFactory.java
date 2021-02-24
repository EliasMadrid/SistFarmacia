package br.com.farmacia.factory;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexaoFactory {
	
// -----------------------------------Conexao com MYSQL ---------------------------------------------------------

private static final String USUARIO = "user";
private static final String SENHA = "password";
private static final String URL = "jdbc:mysql://localhost:3306/sistema ?autoReconnect=true&useSSL=false";
private static Connection conexao;


public static Connection conectar() throws SQLException {
	
	// Referencia ao driver mysql para versões antigas do java
	
	DriverManager.registerDriver(new com.mysql.jdbc.Driver());
	
	Connection conexao = DriverManager.getConnection(URL,USUARIO, SENHA);
    return conexao;
}

   public static void main(String[] args) {
	 
	   try {
	conexao = ConexaoFactory.conectar();
	System.out.println("Conectado com suscesso");
	   }
	   catch(SQLException ex) {
		  ex.printStackTrace();
		  System.out.println("Conexao falhou"); 
	   }
} 
	
	
	
}
