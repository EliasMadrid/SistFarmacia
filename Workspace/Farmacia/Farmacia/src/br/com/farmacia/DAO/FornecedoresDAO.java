package br.com.farmacia.DAO;

import java.sql.SQLException;
import java.util.ArrayList;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import br.com.farmacia.domain.Fornecedores;
//import br.com.farmacia.factory.ConexaoFactory;
import br.com.farmacia.factory.ConexaoPostgres;

public class FornecedoresDAO {
	public void salvar(Fornecedores f) throws SQLException, ClassNotFoundException {
		StringBuilder sql = new StringBuilder();
		sql.append("INSERT INTO fornecedores ");
		sql.append("(descricao) ");
		sql.append("VALUES (?) ");

		// Connection conexao = ConexaoFactory.conectar();// Conectar com o banco de
		// dados MYSQL
		Connection conexao = ConexaoPostgres.conectar(); // Conectar com o banco de dados Postgres

		PreparedStatement comando = conexao.prepareStatement(sql.toString());
		comando.setString(1, f.getDescricao());
		comando.executeUpdate();

	}

	public void editar(Fornecedores f) throws SQLException, ClassNotFoundException {
		StringBuilder sql = new StringBuilder();
		sql.append("UPDATE fornecedores ");
		sql.append("SET descricao = ? ");
		sql.append("WHERE codigo = ? ");

		Connection conexao = ConexaoPostgres.conectar();

		PreparedStatement comando = conexao.prepareStatement(sql.toString());
		comando.setString(1, f.getDescricao());
		comando.setLong(2, f.getCodigo());
		comando.executeUpdate();
	}

	public void excluir(Fornecedores f) throws SQLException, ClassNotFoundException {
		StringBuilder sql = new StringBuilder();
		sql.append("DELETE FROM fornecedores ");
		sql.append("WHERE codigo = ? ");

		Connection conexao = ConexaoPostgres.conectar();

		PreparedStatement comando = conexao.prepareStatement(sql.toString());
		comando.setLong(1, f.getCodigo());
		comando.executeUpdate();
	}

	public Fornecedores buscaPorCodigo(Fornecedores f) throws SQLException, ClassNotFoundException {

		StringBuilder sql = new StringBuilder();
		sql.append("SELECT codigo, descricao ");
		sql.append("FROM fornecedores ");
		sql.append("WHERE codigo = ? ");

		Connection conexao = ConexaoPostgres.conectar();

		PreparedStatement comando = conexao.prepareStatement(sql.toString());
		comando.setLong(1, f.getCodigo());

		ResultSet resultado = comando.executeQuery();
		Fornecedores retorno = null;

		if (resultado.next()) {

			retorno = new Fornecedores();
			retorno.setCodigo(resultado.getLong("codigo"));
			retorno.setDescricao(resultado.getString("descricao"));
		}

		return retorno;

	}

	public ArrayList<Fornecedores> listar() throws SQLException, ClassNotFoundException {

		StringBuilder sql = new StringBuilder();
		sql.append("SELECT codigo, descricao ");
		sql.append("FROM fornecedores ");
		sql.append("ORDER BY codigo ASC ");

		Connection conn = ConexaoPostgres.conectar();

		PreparedStatement pst = conn.prepareStatement(sql.toString());

		ResultSet rs = pst.executeQuery();

		ArrayList<Fornecedores> lista = new ArrayList<Fornecedores>();
		while (rs.next()) {

			Fornecedores f = new Fornecedores();
			f.setCodigo(rs.getLong("codigo"));
			f.setDescricao(rs.getString("descricao"));

			lista.add(f);
		}

		return lista;
	}

	public ArrayList<Fornecedores> buscarPorDescricao(Fornecedores f) throws SQLException, ClassNotFoundException {
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT codigo, descricao ");
		sql.append("FROM fornecedores ");
		sql.append("WHERE descricao LIKE ? ");
		sql.append("ORDER BY descricao ASC ");

		Connection conn = ConexaoPostgres.conectar();

		PreparedStatement pst = conn.prepareStatement(sql.toString());

		pst.setString(1, "%" + f.getDescricao() + "%");

		ResultSet rs = pst.executeQuery();

		ArrayList<Fornecedores> lista = new ArrayList<Fornecedores>();

		while (rs.next()) {

			Fornecedores item = new Fornecedores();
			item.setCodigo(rs.getLong("codigo"));
			item.setDescricao(rs.getString("descricao"));

			lista.add(f);
		}
		return lista;
	}

	public static void main(String[] args) throws ClassNotFoundException {
     // --------------------salvar-----------------------------
		 Fornecedores f1 = new Fornecedores();
		 f1.setDescricao("AULA 41 TESTE 1");
		  
		 Fornecedores f2 = new Fornecedores(); f2.setDescricao("AULA 41 TESTE 3");
		 
		 FornecedoresDAO fdao= new FornecedoresDAO(); 
		 try { 
		 fdao.salvar(f1);
		 fdao.salvar(f2); 
		 System.out.print("Salvo com suscesso"); 
		 } catch(SQLException e) 
		 { System.out.print("Erro ao salvar");
		 e.printStackTrace();
		 }
		
		// -------------------- deletar-----------------
				/*
				 Fornecedores f1 = new Fornecedores(); f1.setCodigo(1);
				  
				 FornecedoresDAO fdao= new FornecedoresDAO();
				 
				 try { 
					 
				  fdao.excluir(f1);
				  
				  System.out.print("Deletado com suscesso");
				  } catch (SQLException e) {
				  System.out.print("Erro ao deletar"); e.printStackTrace();
				  
				  }*/
				 
		          // -------------------Editar-----------------
				/*
				 * Fornecedores f1 = new Fornecedores(); f1.setCodigo(7);
				 * f1.setDescricao("Teste");
				 * 
				 * FornecedoresDAO fdao= new FornecedoresDAO();
				 * 
				 * try { fdao.editar(f1);
				 * 
				 * System.out.print("Editado com suscesso"); } catch (SQLException e) {
				 * System.out.print("Erro ao Editar"); e.printStackTrace(); }
				 */
		     // ---------------------Buscar Codigo ---------------------------
				
				/*Fornecedores f1 = new Fornecedores();
				f1.setCodigo(2);

				Fornecedores f2 = new Fornecedores();
				f2.setCodigo(3);

				FornecedoresDAO fdao = new FornecedoresDAO();
				try {
					Fornecedores f3 = fdao.buscaPorCodigo(f1);
					Fornecedores f4 = fdao.buscaPorCodigo(f2);

					System.out.print("Resultado 1: " + f3);
					System.out.print("Resultado 1: " + f4);
				} catch (SQLException e) {
					System.out.print("Erro ao buscar");
					e.printStackTrace();
				} 
				*/
				
				// --------------------listar -----------------------------------
				/*
				FornecedoresDAO fdao = new FornecedoresDAO();
				try {
				   ArrayList<Fornecedores>lista = fdao.listar();
				   
				   for (Fornecedores f : lista) {
					   System.out.print("Resultado 1: " + f);  
				   }
				   
				   
						   
				} catch (SQLException e) {
					System.out.print("Erro ao Listar");
					e.printStackTrace();
				}*/
				
				// -------------------------Listar Descricao----------------------
			
				
				/*Fornecedores f1 = new Fornecedores();
				f1.setDescricao("DESC");
				
				FornecedoresDAO fdao = new FornecedoresDAO();
				try {
					   ArrayList<Fornecedores>lista = fdao.buscarPorDescricao(f1);
					   
					   for (Fornecedores f : lista) {
						   System.out.print("Resultado 1: " + f);  
					   }
					   
					   
							   
					} catch (SQLException e) {
						System.out.print("Erro ao buscar");
						e.printStackTrace();
					}*/
			
		 
		 
	//----------------------------Final------------------------------	 
	}
}
