package br.com.joe.negocio;

import java.sql.SQLException;
import java.util.List;

import org.apache.log4j.Logger;

import br.com.joe.modelo.ClienteDAO;
import br.com.joe.vo.Cliente;

public class ClienteBO {
	private static Logger log = Logger.getLogger(ClienteBO.class);

	public boolean adicionar(Cliente cliente) {
		log.debug("adicionando cliente" + cliente.getNome());
		try {
			ClienteDAO clienteDao = new ClienteDAO();
			clienteDao.adicionar(cliente);
			return true;
		} catch (SQLException e) {
			return false;
		}
	}

	public Cliente obterCliente(int id) {
		log.debug("obtendo cliente id " + id);
		try {
			ClienteDAO clienteDAO = new ClienteDAO();
			return clienteDAO.obterClientePorId(id);
		} catch (SQLException e) {
			return null;
		}
	}

	public List<Cliente> buscar(String busca) {
		log.debug("buscando clientes");
		ClienteDAO clienteDao = new ClienteDAO();
		try {
			return clienteDao.buscar(busca);
		} catch (SQLException e) {
			return null;
		}

	}

	public boolean remover(int clienteId) {
		log.debug("excluindo cliente id " + clienteId);
		ClienteDAO clienteDAO = new ClienteDAO();
		try {
			clienteDAO.remover(clienteId);
			return true;
		} catch (SQLException e) {
			return false;
		}
	}

	public boolean alterar(Cliente cliente) {
		log.debug("atualizando cliente: " + cliente.getId() + " - " + cliente.getNome());
		ClienteDAO clienteDAO = new ClienteDAO();
		try {
			clienteDAO.atualizar(cliente);
			return true;
		} catch (SQLException e) {
			return false;
		}

	}
}
