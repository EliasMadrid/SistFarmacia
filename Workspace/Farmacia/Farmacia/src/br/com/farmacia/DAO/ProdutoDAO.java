package br.com.farmacia.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import org.junit.Test;

import br.com.farmacia.domain.Fornecedores;
import br.com.farmacia.domain.Produtos;
import br.com.farmacia.factory.ConexaoPostgres;

public class ProdutoDAO {
	public void salvar(Produtos p) throws SQLException, ClassNotFoundException {
		StringBuilder sql = new StringBuilder();
		sql.append("INSERT INTO produtos ");
		sql.append("(descricao,preco, quantidade, fornecedores_codigo) ");
		sql.append("VALUES (?,?,?,?) ");

		
		Connection conexao = ConexaoPostgres.conectar(); // Conectar com o banco de dados Postgres

		PreparedStatement comando = conexao.prepareStatement(sql.toString());
		
		comando.setString(1, p.getDescricao());
		comando.setDouble(2, p.getPreco());
		comando.setLong(3, p.getQuantidade());
		comando.setLong(4, p.getFornecedores().getCodigo());
		
		comando.executeUpdate();

	}
	
	public ArrayList<Produtos> listar() throws SQLException, ClassNotFoundException {

		StringBuilder sql = new StringBuilder();
		sql.append("SELECT p.codigo, p.descricao, p.preco, p.quantidade, f.codigo, f.descricao ");
		sql.append("FROM produtos p ");
		sql.append("INNER JOIN fornecedores f ON f.codigo = p.fornecedores_codigo ");

		Connection conn = ConexaoPostgres.conectar();

		PreparedStatement pst = conn.prepareStatement(sql.toString());

		ResultSet rs = pst.executeQuery();

		ArrayList<Produtos> lista = new ArrayList<Produtos>();
		while (rs.next()) {

			Fornecedores f = new Fornecedores();
			f.setCodigo(rs.getLong("f.codigo"));
			f.setDescricao(rs.getString("f.descricao"));
			
			Produtos p = new Produtos();
			p.setCodigo(rs.getLong("p.codigo"));
			p.setDescricao(rs.getString("p.descricao"));
			p.setPreco(rs.getDouble("p.preco"));
			p.setQuantidade(rs.getLong("p.quantidade"));
			p.setFornecedores(f);

			lista.add(p);
		}

		return lista;
	}

	
	public void excluir(Produtos p) throws SQLException, ClassNotFoundException {
		StringBuilder sql = new StringBuilder();
		sql.append("DELETE FROM produtos ");
		sql.append("WHERE codigo = ? ");

		Connection conexao = ConexaoPostgres.conectar();

		PreparedStatement comando = conexao.prepareStatement(sql.toString());
		comando.setLong(1, p.getCodigo());
		comando.executeUpdate();
	}
	
	public void editar(Produtos p) throws SQLException, ClassNotFoundException {
		StringBuilder sql = new StringBuilder();
		sql.append("UPDATE produtos ");
		sql.append("SET descricao = ?, preco=?, quantidade = ?, fornecedores_codigo=? ");
		sql.append("WHERE codigo = ?");

		Connection conexao = ConexaoPostgres.conectar();

		PreparedStatement comando = conexao.prepareStatement(sql.toString());
		comando.setString(1, p.getDescricao());
		comando.setDouble(2, p.getPreco());
		comando.setLong(3, p.getQuantidade());
		comando.setLong(4, p.getFornecedores().getCodigo());
		comando.setLong(5, p.getCodigo());
		
		comando.executeUpdate();
	}
	
	
	
	
	
}
