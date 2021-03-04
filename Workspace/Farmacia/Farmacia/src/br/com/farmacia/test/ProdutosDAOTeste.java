package br.com.farmacia.test;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import org.junit.Ignore;
import org.junit.Test;

import br.com.farmacia.DAO.FornecedoresDAO;
import br.com.farmacia.DAO.ProdutoDAO;
import br.com.farmacia.domain.Fornecedores;
import br.com.farmacia.domain.Produtos;
import br.com.farmacia.factory.ConexaoPostgres;

public class ProdutosDAOTeste {
	@Test
	public void salvar() throws ClassNotFoundException, SQLException{
	Produtos p1 = new Produtos();
	 p1.setDescricao("DIPIRONA");
	 p1.setPreco(2.99);
	 p1.setQuantidade(12L);
	 
	 
	 Fornecedores f= new Fornecedores(); 
	 f.setCodigo(12l);
	 p1.setFornecedores(f);
	 
	 
	 ProdutoDAO fdao= new ProdutoDAO(); 
	
	 fdao.salvar(p1);
	 
	 System.out.print("Salvo com suscesso"); 
	 
	}
	@Test
	@Ignore
	public void listar() throws ClassNotFoundException, SQLException{
		ProdutoDAO fdao= new ProdutoDAO();
		ArrayList<Produtos>lista = fdao.listar();
		
		for (Produtos p: lista) {
			System.out.print("codigo do Produto: " + p.getCodigo());
			System.out.print("Descricao do Produto: " + p.getDescricao());
			System.out.print("Valor do Produto: " + p.getPreco());
			System.out.print("Quantidade: " + p.getQuantidade());
			System.out.print("Codigo do fornecedor: " + p.getFornecedores().getCodigo());
			System.out.print("Descri��o do Fornecedor: " + p.getFornecedores().getDescricao());
			System.out.println("");
			
		}
		
		
	}
	
	public void excluir(Produtos p) throws SQLException, ClassNotFoundException {
		
		Produtos p1 = new Produtos();
		p.setCodigo(3L);
		
		ProdutoDAO dao = new ProdutoDAO();
		dao.excluir(p);
	}
	@Test
    public void editar(Produtos p) throws SQLException, ClassNotFoundException {
		
		Produtos p1 = new Produtos();
		p.setCodigo(3L);
		p.setDescricao("Cataflan");
		p.setPreco(15.75);
		p.setQuantidade(2L);
				
		
		Fornecedores f= new Fornecedores(); 
		 f.setCodigo(10L);
		 p1.setFornecedores(f);
		 
		ProdutoDAO dao = new ProdutoDAO();
		dao.editar(p);
	}
}
