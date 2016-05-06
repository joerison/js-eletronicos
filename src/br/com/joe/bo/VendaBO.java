package br.com.joe.bo;

import java.sql.SQLException;
import java.util.List;

import org.apache.log4j.Logger;

import br.com.joe.modelo.VendaDAO;
import br.com.joe.vo.Venda;

public class VendaBO {

	private static Logger log = Logger.getLogger(VendaBO.class);
	private VendaDAO vendaDAO = new VendaDAO();

	public boolean adicionar(Venda venda) {
		log.debug("adicionando venda" + venda.getId());
		try {
			vendaDAO.adicionar(venda);
			return true;
		} catch (SQLException e) {
			return false;
		}
	}

	public boolean remover(int id) {
		log.debug("excluindo venda id " + id);
		try {
			vendaDAO.remover(id);
			return true;
		} catch (SQLException e) {
			return false;
		}
	}

	public boolean atualizar(Venda venda) {
		log.debug("atualizando venda: " + venda.getId());
		try {
			vendaDAO.atualizar(venda);
			return true;
		} catch (SQLException e) {
			return false;
		}
	}

	public Venda obterVendaPorId(int id) {
		log.debug("obtendo venda id " + id);
		try {
			return vendaDAO.obterVendaPorId(id);
		} catch (SQLException e) {
			return null;
		}
	}

	public List<Venda> buscar(String busca) {
		log.debug("listando todos vendas");
		try {
			return vendaDAO.buscar(busca);
		} catch (SQLException e) {
			return null;
		}
	}
}
