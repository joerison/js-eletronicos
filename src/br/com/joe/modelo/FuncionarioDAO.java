package br.com.joe.modelo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import br.com.joe.util.ConnectionFactory;
import br.com.joe.vo.Funcionario;

public class FuncionarioDAO {

	private static Logger log = Logger.getLogger(FuncionarioDAO.class);

	private ConnectionFactory connectionFactory = new ConnectionFactory();
	private Connection conexao;

	public FuncionarioDAO() {
		conexao = connectionFactory.getConnection();
	}

	public void adicionar(Funcionario funcionario) throws SQLException {
		log.debug("adicionando funcionario" + funcionario.getLogin());
		String sql = "INSERT INTO funcionario (nome, cpf, email, celular, sexo, login, senha) values (?, ?, ?, ?, ?, ?, ?)";
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
	}

	public void remover(int id) throws SQLException {
		log.debug("excluindo funcionario id " + id);
		String sql = "DELETE FROM funcionario where id = ?";
		PreparedStatement stmt = conexao.prepareStatement(sql);
		stmt.setInt(1, id);
		stmt.execute();
		stmt.close();
	}

	public void atualizar(Funcionario funcionario) throws SQLException {
		log.debug("atualizando funcionario: " + funcionario.getId());
		String sql = "UPDATE funcionario set nome = ?, cpf = ?, email = ?, celular = ?, sexo = ?, login = ?, senha = ? where id = ?";
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
	}

	public Funcionario obterFuncionarioPorId(int id) throws SQLException {
		log.debug("obtendo funcionario id " + id);
		Funcionario funcionario = new Funcionario();
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
	}

	public Funcionario obterFuncionarioPorLogin(String login) throws SQLException {
		log.debug("obtendo funcionario login " + login);
		Funcionario funcionario = null;
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
	}

	public List<Funcionario> buscar(String busca) throws SQLException {
		log.debug("buscando funcionario: " + busca);
		List<Funcionario> funcionarios = new ArrayList<Funcionario>();
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
	}
}
