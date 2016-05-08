package br.com.joe.negocio;

import java.sql.SQLException;
import java.util.List;

import org.apache.log4j.Logger;

import br.com.joe.modelo.ProdutoDAO;
import br.com.joe.vo.Produto;

public class ProdutoBO {

	private static Logger log = Logger.getLogger(ProdutoBO.class);
	private ProdutoDAO produtoDAO = new ProdutoDAO();

	public boolean adicionar(Produto produto) {
		log.debug("adicionando produto" + produto.getNome());
		try {
			produtoDAO.adicionar(produto);
			return true;
		} catch (SQLException e) {
			return false;
		}
	}

	public boolean remover(int id) {
		log.debug("excluindo produto id " + id);
		try {
			produtoDAO.remover(id);
			return true;
		} catch (SQLException e) {
			return false;
		}
	}

	public boolean atualizar(Produto produto) {
		log.debug("atualizando produto: " + produto.getId() + " - " + produto.getNome());
		try {
			produtoDAO.atualizar(produto);
			return true;
		} catch (SQLException e) {
			return false;
		}
	}

	public Produto obterProdutoPorId(int id) {
		log.debug("obtendo produto id " + id);
		try {
			return produtoDAO.obterProdutoPorId(id);
		} catch (SQLException e) {
			return null;
		}
	}

	public List<Produto> buscar(String busca) {
		log.debug("listando todos produtos");
		try {
			return produtoDAO.buscar(busca);
		} catch (SQLException e) {
			return null;
		}
	}
}
