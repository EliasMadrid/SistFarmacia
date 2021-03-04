package br.com.farmacia.bean;

import java.sql.SQLException;
import java.util.ArrayList;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import br.com.farmacia.DAO.FornecedoresDAO;
import br.com.farmacia.DAO.ProdutoDAO;
import br.com.farmacia.domain.Fornecedores;
import br.com.farmacia.domain.Produtos;
import br.com.farmacia.util.JSFUtil;

@ManagedBean(name ="MBProduto")
@ViewScoped
public class ProdutoBean {
	
	private Produtos produtos;	
	private ArrayList<Produtos>itens;
	private ArrayList<Produtos>itensFiltrados;
	
	private ArrayList<Fornecedores>comboFornecedores;
	
	public ArrayList<Fornecedores> getComboFornecedores() {
		return comboFornecedores;
	}
		
	
	
	// get e setters
	public Produtos getProdutos() {
		return produtos;
	}
	public void setProdutos(Produtos produtos) {
		this.produtos = produtos;
	}
	
	//get e setters array itens 
	public ArrayList<Produtos> getItens() {
		return itens;
	}
	public void setItens(ArrayList<Produtos> itens) {
		this.itens = itens;
	}
	//get e setters array itens filtrados
	public ArrayList<Produtos> getItensFiltrados() {
		return itensFiltrados;
	}
	public void setItensFiltrados(ArrayList<Produtos> itensFiltrados) {
		this.itensFiltrados = itensFiltrados;
	}

@PostConstruct
public void prepararPesquisa() {
	
	try {
		ProdutoDAO fdao= new ProdutoDAO();
	    itens = fdao.listar();
		
		
	} catch (ClassNotFoundException e) {	
		e.printStackTrace();
	} catch (SQLException e) {	
		JSFUtil.adicionarMensagemErro("ex.getMessage()");
		e.printStackTrace();
	}
	
}

public void preparaNovo() {
    
    try {
    	produtos = new Produtos();    
        FornecedoresDAO dao = new FornecedoresDAO();
		comboFornecedores = dao.listar();
	} catch (ClassNotFoundException e) {
		
		e.printStackTrace();
	} catch (SQLException e) {
		JSFUtil.adicionarMensagemErro("ex.getMessage()");
		e.printStackTrace();
	}
}

public void novo() {
	
	try {
		ProdutoDAO fdao= new ProdutoDAO();
		fdao.salvar(produtos);
		
		itens = fdao.listar();
		
		JSFUtil.adicionarMensagemSucesso("Produto salvo com suscesso!");
		
	} catch (ClassNotFoundException e) {
		
		e.printStackTrace();
	} catch (SQLException e) {
		JSFUtil.adicionarMensagemErro("ex.getMessage()");
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
  	
}

public void excluir() {
	try {
		ProdutoDAO fdao= new ProdutoDAO();
		fdao.excluir(produtos);
		
		// codigo para atualizar a lista
		
		itens= fdao.listar();
		
		
		JSFUtil.adicionarMensagemSucesso("Produto excluido com suscesso!");
		
	} catch (ClassNotFoundException e) {
		
		e.printStackTrace();
	} catch (SQLException e) {
		JSFUtil.adicionarMensagemErro("ex.getMessage()");
		
		e.printStackTrace();
	}
	 
}


}
