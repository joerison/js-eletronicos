package br.com.joe.bo;

import java.sql.SQLException;
import java.util.List;

import br.com.joe.modelo.ClienteDAO;
import br.com.joe.vo.Cliente;

public class ClienteBO {

	public boolean adicionar(Cliente cliente) {

		try {
			ClienteDAO clienteDao = new ClienteDAO();
			// alguma regra, nessse caso vai ser validacao do cpf
			// if (cliente.getCpf() > 18) {
			clienteDao.adicionar(cliente);
			return true;
			// } else {
			// return false;
			// }
		} catch (SQLException e) {
			return false;
		}
	}

	public Cliente obterCliente(int id) {
		Cliente cliente = null;
		try {
			ClienteDAO clienteDAO = new ClienteDAO();
			cliente = clienteDAO.obterClientePorId(id);
			return cliente;
		} catch (SQLException e) {
			new RuntimeException(e);
		}
		return cliente;
	}

	public List<Cliente> buscar(String busca) {
		List<Cliente> clientes = null;
		ClienteDAO clienteDao = new ClienteDAO();
		try {
			clientes = clienteDao.buscar(busca);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return clientes;
	}

	public boolean remover(int clienteId) {
		ClienteDAO clienteDAO = new ClienteDAO();
		// regras
		try {
			clienteDAO.remover(clienteId);
			return true;
		} catch (SQLException e) {
			System.out.println(e.getMessage());
			return false;
		}

	}
	
	public boolean alterar(Cliente cliente) {
		ClienteDAO clienteDAO = new ClienteDAO();
		// regras
		try {
			clienteDAO.atualizar(cliente);
			return true;
		} catch (SQLException e) {
			System.out.println(e.getMessage());
			return false;
		}

	}
}
