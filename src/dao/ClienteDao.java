package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import model.Cliente;
import util.ConnectionFactory;

public class ClienteDao {

	private static Logger log = Logger.getLogger(ClienteDao.class);

	private Connection conexao;

	public ClienteDao() {
		conexao = ConnectionFactory.getConnection();
	}

	public void adicionar(Cliente cliente) {
		String sql = "INSERT INTO cliente (nome, idade) values (?, ?)";
		try {
			PreparedStatement stmt = conexao.prepareStatement(sql);
			stmt.setString(1, cliente.getNome());
			stmt.setString(2, cliente.getIdade());
			stmt.execute();
			stmt.close();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	public void remover(int id) {
		String sql = "DELETE FROM cliente where id = ?";
		try {
			PreparedStatement stmt = conexao.prepareStatement(sql);
			stmt.setInt(1, id);
			stmt.execute();
			stmt.close();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	public void atualizar(Cliente cliente) {
		String sql = "UPDATE cliente set nome = ?, idade= ? where id = ?";
		log.debug("atualizando cliente: " + cliente.getId() + " - " + cliente.getNome());
		try {
			PreparedStatement stmt = conexao.prepareStatement(sql);
			stmt.setString(1, cliente.getNome());
			stmt.setString(2, cliente.getIdade());
			stmt.setInt(3, cliente.getId());
			stmt.execute();
			stmt.close();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	public Cliente obterClientePorId(int id) {
		Cliente cliente = new Cliente();
		try {
			String sql = "SELECT * FROM cliente where id = ?";
			PreparedStatement stmt = conexao.prepareStatement(sql);
			stmt.setInt(1, id);
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				cliente.setId(rs.getInt("id"));
				cliente.setNome(rs.getString("nome"));
				cliente.setIdade(rs.getString("idade"));
			}
			rs.close();
			stmt.close();
			return cliente;
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	public List<Cliente> listar() {
		List<Cliente> clientes = new ArrayList<Cliente>();
		try {
			String sql = "SELECT * FROM cliente";
			PreparedStatement stmt = conexao.prepareStatement(sql);
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				Cliente cliente = new Cliente();
				cliente.setId(rs.getInt("id"));
				cliente.setNome(rs.getString("nome"));
				cliente.setIdade(rs.getString("idade"));
				clientes.add(cliente);
			}
			rs.close();
			stmt.close();
			return clientes;
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
}
