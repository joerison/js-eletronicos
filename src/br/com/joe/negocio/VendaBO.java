package br.com.joe.negocio;

import java.sql.SQLException;
import java.util.List;

import org.apache.log4j.Logger;

import br.com.joe.modelo.VendaDAO;
import br.com.joe.util.Validacao;
import br.com.joe.vo.ItemVenda;
import br.com.joe.vo.Venda;

public class VendaBO {

	private static Logger log = Logger.getLogger(VendaBO.class);
	private VendaDAO vendaDAO = new VendaDAO();

	public boolean adicionar(Venda venda) {
		log.debug("adicionando venda" + venda.getId());

		if (!existeCampoObrigatorioNulo(venda)) {
			try {
				for (ItemVenda vendaItem : venda.getItensVenda()) {
					venda.setTotal((venda.getTotal() + vendaItem.getTotal()));
					if (vendaItem.getQtd() < 1) {
						return false;
					}
				}
				venda.setTotal(venda.getTotal() - venda.getDesconto());
				vendaDAO.adicionar(venda);
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
			log.error(e.getMessage());
			return null;
		}
	}

	public List<Venda> obterVendasPorIntervalo(String inicio, String fim) {
		log.debug("obtendo venda por intervalo");
		Validacao validacao = new Validacao();

		java.sql.Date dtInicio = validacao.converterData(inicio);
		java.sql.Date dtFim = validacao.converterData(fim);

		if (dtInicio != null && dtFim != null) {
			try {
				return vendaDAO.obterVendasPorIntervalo(dtInicio, dtFim);
			} catch (SQLException e) {
				log.error(e.getMessage());
				return null;
			}
		} else {
			log.error("data invalida");
			return null;
		}
	}

	public boolean existeCampoObrigatorioNulo(Venda venda) {
		if (venda.getCliente() == null || venda.getItensVenda().size() == 0) {
			return true;
		} else {
			return false;
		}
	}
}
