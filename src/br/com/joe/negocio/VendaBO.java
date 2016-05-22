package br.com.joe.negocio;

import java.sql.SQLException;

import org.apache.log4j.Logger;

import br.com.joe.ItemVenda;
import br.com.joe.modelo.VendaDAO;
import br.com.joe.vo.Venda;

public class VendaBO {

	private static Logger log = Logger.getLogger(VendaBO.class);
	private VendaDAO vendaDAO = new VendaDAO();

	public boolean adicionar(Venda venda) {
		log.debug("adicionando venda" + venda.getId());

		// atribuindo totais referente a venda
		for (ItemVenda vendaItem : venda.getItensVenda()) {
			vendaItem.setTotal(vendaItem.getQtd() * vendaItem.getProduto().getPreco());
			venda.setTotal((venda.getTotal() + vendaItem.getTotal()));
		}
		venda.setTotal(venda.getTotal() - venda.getDesconto());

		try {
			vendaDAO.adicionar(venda);
			return true;
		} catch (SQLException e) {
			return false;
		}
	}

	public boolean remover(int id) {
		log.debug("removendo venda id " + id);
		try {
			vendaDAO.remover(id);
			return true;
		} catch (SQLException e) {
			log.error(e);
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
}
