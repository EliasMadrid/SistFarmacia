package br.com.farmacia.test;

import java.sql.SQLException;

import org.junit.Test;

import br.com.farmacia.DAO.FornecedoresDAO;
import br.com.farmacia.DAO.ProdutoDAO;
import br.com.farmacia.domain.Fornecedores;
import br.com.farmacia.domain.Produtos;

public class ProdutosDAOTeste {
	@Test
	public void salvar() throws ClassNotFoundException, SQLException{
	Produtos p1 = new Produtos();
	 p1.setDescricao("DIPIRONA");
	 p1.setPreco(2.99);
	 p1.setQuantidade(12L);
	 
	 
	 Fornecedores f= new Fornecedores(); 
	 f.setCodigo(12);
	 p1.setFornecedores(f);
	 
	 
	 ProdutoDAO fdao= new ProdutoDAO(); 
	
	 fdao.salvar(p1);
	 
	 System.out.print("Salvo com suscesso"); 
	 
	}
	
}
