package br.com.joe.negocio;

import java.sql.SQLException;
import java.util.List;

import org.apache.log4j.Logger;

import br.com.joe.modelo.CategoriaDAO;
import br.com.joe.vo.Categoria;

public class CategoriaBO {

	private static Logger log = Logger.getLogger(CategoriaBO.class);
	private CategoriaDAO categoriaDAO = new CategoriaDAO();

	public boolean adicionar(Categoria categoria) {
		log.debug("adicionando categoria" + categoria.getNome());
		try {
			categoriaDAO.adicionar(categoria);
			return true;
		} catch (SQLException e) {
			return false;
		}

	}

	public boolean remover(int id) {
		log.debug("excluindo categoria id " + id);
		try {
			categoriaDAO.remover(id);
			return true;
		} catch (SQLException e) {
			return false;
		}
	}

	public boolean atualizar(Categoria categoria) {
		log.debug("atualizando categoria: " + categoria.getId() + " - " + categoria.getNome());
		try {
			categoriaDAO.atualizar(categoria);
			return true;
		} catch (SQLException e) {
			return false;
		}
	}

	public Categoria obterCategoriaPorId(int id) {
		log.debug("obtendo categoria id " + id);
		try {
			return categoriaDAO.obterCategoriaPorId(id);
		} catch (SQLException e) {
			return null;
		}
	}

	public List<Categoria> buscar(String busca) {
		log.debug("listando todos categorias");
		try {
			return categoriaDAO.buscar(busca);
		} catch (SQLException e) {
			return null;
		}
	}
}
