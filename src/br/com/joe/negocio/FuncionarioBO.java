package br.com.joe.negocio;

import java.sql.SQLException;
import java.util.List;

import org.apache.log4j.Logger;

import br.com.joe.modelo.FuncionarioDAO;
import br.com.joe.vo.Funcionario;

public class FuncionarioBO {

	private static Logger log = Logger.getLogger(FuncionarioBO.class);
	private FuncionarioDAO funcionarioDAO = new FuncionarioDAO();

	public boolean adicionar(Funcionario funcionario) {
		log.debug("adicionando funcionario" + funcionario.getLogin());
		if (!existeCampoObrigatorioNulo(funcionario)) {
			try {
				funcionarioDAO.adicionar(funcionario);
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
		log.debug("excluindo funcionario id " + id);
		try {
			funcionarioDAO.remover(id);
			return true;
		} catch (SQLException e) {
			log.error(e.getMessage());
			return false;
		}
	}

	public boolean atualizar(Funcionario funcionario) {
		log.debug("atualizando funcionario: " + funcionario.getId());
		if (!existeCampoObrigatorioNulo(funcionario)) {
			try {
				funcionarioDAO.atualizar(funcionario);
				return true;
			} catch (SQLException e) {
				log.error(e.getMessage());
				return false;
			}
		} else {
			return false;
		}
	}

	public Funcionario obterFuncionarioPorId(int id) {
		log.debug("obtendo funcionario id " + id);
		try {
			return funcionarioDAO.obterFuncionarioPorId(id);
		} catch (SQLException e) {
			log.error(e.getMessage());
			return null;
		}
	}

	public Funcionario obterFuncionarioPorLogin(String login) {
		log.debug("obtendo funcionario login " + login);
		try {
			return funcionarioDAO.obterFuncionarioPorLogin(login);
		} catch (SQLException e) {
			log.error(e.getMessage());
			return null;
		}
	}

	public List<Funcionario> buscar(String busca) {
		log.debug("buscando funcionario: " + busca);
		try {
			return funcionarioDAO.buscar(busca);
		} catch (SQLException e) {
			log.error(e.getMessage());
			return null;
		}
	}

	public boolean existeCampoObrigatorioNulo(Funcionario funcionario) {
		if (funcionario.getNome().equals("") || funcionario.getCpf().equals("")
				|| funcionario.getEmail().equals("")
				|| funcionario.getCelular().equals("")
				|| funcionario.getSexo().equals("")
				|| funcionario.getLogin().equals("")
				|| funcionario.getSenha().equals("")) {
			return true;
		} else {
			return false;
		}
	}

}
