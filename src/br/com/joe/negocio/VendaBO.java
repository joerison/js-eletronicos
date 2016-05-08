package br.com.joe.negocio;

import java.sql.Date;
import java.sql.SQLException;
import java.util.List;

import org.apache.log4j.Logger;

import br.com.joe.modelo.VendaDAO;
import br.com.joe.vo.ItemVenda;
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

	public List<Venda> obterVendasPorIntervalo(Date inicio, Date fim) {
		try {
			return vendaDAO.obterVendasPorIntervalo(inicio, fim);
		} catch (SQLException e) {
			log.error(e.getMessage());
			return null;
		}
	}
}
