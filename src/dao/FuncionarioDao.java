package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import model.Funcionario;
import util.ConnectionFactory;

public class FuncionarioDao {

	private static Logger log = Logger.getLogger(FuncionarioDao.class);

	private ConnectionFactory connectionFactory = new ConnectionFactory();
	private Connection conexao;

	public FuncionarioDao() {
		conexao = connectionFactory.getConnection();
	}

	public void adicionar(Funcionario funcionario) {
		log.debug("adicionando funcionario" + funcionario.getLogin());
		String sql = "INSERT INTO funcionario (nome, cpf, email, celular, sexo, login, senha) values (?, ?, ?, ?, ?, ?, ?)";
		try {
			PreparedStatement stmt = conexao.prepareStatement(sql);
			stmt.setString(1, funcionario.getNome());
			stmt.setString(2, funcionario.getCpf());
			stmt.setString(3, funcionario.getEmail());
			stmt.setString(4, funcionario.getCelular());
			stmt.setString(5, funcionario.getSexo());
			stmt.setString(6, funcionario.getLogin());
			stmt.setString(7, funcionario.getSenha());
			stmt.execute();
			stmt.close();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	public void remover(int id) {
		log.debug("excluindo funcionario id " + id);
		String sql = "DELETE FROM funcionario where id = ?";
		try {
			PreparedStatement stmt = conexao.prepareStatement(sql);
			stmt.setInt(1, id);
			stmt.execute();
			stmt.close();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	public void atualizar(Funcionario funcionario) {
		log.debug("atualizando funcionario: " + funcionario.getId() + " - " + funcionario.getLogin());
		String sql = "UPDATE funcionario set nome = ?, cpf = ?, email = ?, celular = ?, sexo = ?, login = ?, senha = ? where id = ?";
		try {
			PreparedStatement stmt = conexao.prepareStatement(sql);
			stmt.setString(1, funcionario.getNome());
			stmt.setString(2, funcionario.getCpf());
			stmt.setString(3, funcionario.getEmail());
			stmt.setString(4, funcionario.getCelular());
			stmt.setString(5, funcionario.getSexo());
			stmt.setString(6, funcionario.getLogin());
			stmt.setString(7, funcionario.getSenha());
			stmt.setInt(8, funcionario.getId());
			stmt.execute();
			stmt.close();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	public Funcionario obterFuncionarioPorId(int id) {
		log.debug("obtendo funcionario id " + id);
		Funcionario funcionario = new Funcionario();
		try {
			String sql = "SELECT * FROM funcionario where id = ?";
			PreparedStatement stmt = conexao.prepareStatement(sql);
			stmt.setInt(1, id);
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				funcionario.setId(rs.getInt("id"));
				funcionario.setNome(rs.getString("nome"));
				funcionario.setCpf(rs.getString("cpf"));
				funcionario.setEmail(rs.getString("email"));
				funcionario.setCelular(rs.getString("celular"));
				funcionario.setSexo(rs.getString("sexo"));
				funcionario.setLogin(rs.getString("login"));
				funcionario.setSenha(rs.getString("senha"));
			}
			rs.close();
			stmt.close();
			return funcionario;
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	public Funcionario obterFuncionarioPorLogin(String login) {
		log.debug("obtendo funcionario login " + login);
		Funcionario funcionario = null;
		try {
			String sql = "SELECT * FROM funcionario where login = ?";
			PreparedStatement stmt = conexao.prepareStatement(sql);
			stmt.setString(1, login);
			ResultSet rs = stmt.executeQuery();
			if (rs.first()) {
				log.debug("funcionario: " + login + " - localizado");
				funcionario = new Funcionario();
				funcionario.setId(rs.getInt("id"));
				funcionario.setNome(rs.getString("nome"));
				funcionario.setCpf(rs.getString("cpf"));
				funcionario.setEmail(rs.getString("email"));
				funcionario.setCelular(rs.getString("celular"));
				funcionario.setSexo(rs.getString("sexo"));
				funcionario.setLogin(rs.getString("login"));
				funcionario.setSenha(rs.getString("senha"));
			} else {
				log.debug("funcionario: " + login + " - nao localizado");
			}
			rs.close();
			stmt.close();
			return funcionario;
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	public List<Funcionario> buscar(String busca) {
		log.debug("buscando funcionario: " + busca);
		List<Funcionario> funcionarios = new ArrayList<Funcionario>();
		try {
			String sql = "SELECT * FROM funcionario WHERE nome like ?";
			PreparedStatement stmt = conexao.prepareStatement(sql);
			stmt.setString(1, "%" + busca + "%");
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				Funcionario funcionario = new Funcionario();
				funcionario.setId(rs.getInt("id"));
				funcionario.setNome(rs.getString("nome"));
				funcionario.setCpf(rs.getString("cpf"));
				funcionario.setEmail(rs.getString("email"));
				funcionario.setCelular(rs.getString("celular"));
				funcionario.setSexo(rs.getString("sexo"));
				funcionario.setLogin(rs.getString("login"));
				funcionario.setSenha(rs.getString("senha"));
				funcionarios.add(funcionario);
			}
			rs.close();
			stmt.close();
			return funcionarios;
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
}
