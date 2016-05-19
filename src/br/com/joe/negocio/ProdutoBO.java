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
		if (fereRegra(produto) || existeCampoObrigatorioNulo(produto)) {
			try {
				produtoDAO.adicionar(produto);
				return true;
			} catch (SQLException e) {
				log.error(e.getMessage());
				return false;
			}
		} else {
			return false;
		}
	}

	public boolean remover(int id) {
		log.debug("excluindo produto id " + id);
		try {
			produtoDAO.remover(id);
			return true;
		} catch (SQLException e) {
			log.error(e.getMessage());
			return false;
		}
	}

	public boolean atualizar(Produto produto) {
		log.debug("atualizando produto: " + produto.getId());
		if (fereRegra(produto) || existeCampoObrigatorioNulo(produto)) {
			try {
				produtoDAO.atualizar(produto);
				return true;
			} catch (SQLException e) {
				log.error(e.getMessage());
				return false;
			}
		} else {
			return false;
		}
	}

	public Produto obterProdutoPorId(int id) {
		log.debug("obtendo produto id " + id);
		try {
			return produtoDAO.obterProdutoPorId(id);
		} catch (SQLException e) {
			log.error(e.getMessage());
			return null;
		}
	}

	public List<Produto> buscar(String busca) {
		log.debug("buscando produtos");
		try {
			return produtoDAO.buscar(busca);
		} catch (SQLException e) {
			log.error(e.getMessage());
			return null;
		}
	}

	public boolean fereRegra(Produto produto) {
		if (produto.getPreco() < 0) {
			return true;
		} else {
			return false;
		}
	}

	public boolean existeCampoObrigatorioNulo(Produto produto) {
		if (produto.getCategoria() == null || produto.getNome().equals("")
				|| produto.getPreco() == null) {
			return true;
		} else {
			return false;
		}
	}
}
