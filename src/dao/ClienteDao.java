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
		log.debug("adicionando cliente" + cliente.getNome());
		String sql = "INSERT INTO cliente (nome, cpf, email, celular, sexo) values (?, ?, ?, ?, ?)";
		try {
			PreparedStatement stmt = conexao.prepareStatement(sql);
			stmt.setString(1, cliente.getNome());
			stmt.setString(2, cliente.getCpf());
			stmt.setString(4, cliente.getEmail());
			stmt.setString(3, cliente.getCelular());
			stmt.setString(5, cliente.getSexo());
			stmt.execute();
			stmt.close();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	public void remover(int id) {
		log.debug("excluindo cliente id " + id);
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
		log.debug("atualizando cliente: " + cliente.getId() + " - " + cliente.getNome());
		String sql = "UPDATE cliente set nome = ?, cpf= ?, email = ?, celular = ?, sexo = ? where id = ?";
		try {
			PreparedStatement stmt = conexao.prepareStatement(sql);
			stmt.setString(1, cliente.getNome());
			stmt.setString(2, cliente.getCpf());
			stmt.setString(4, cliente.getEmail());
			stmt.setString(3, cliente.getCelular());
			stmt.setString(5, cliente.getSexo());
			stmt.setInt(6, cliente.getId());
			stmt.execute();
			stmt.close();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	public Cliente obterClientePorId(int id) {
		log.debug("obtendo cliente id " + id);
		Cliente cliente = new Cliente();
		try {
			String sql = "SELECT * FROM cliente where id = ?";
			PreparedStatement stmt = conexao.prepareStatement(sql);
			stmt.setInt(1, id);
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				cliente.setId(rs.getInt("id"));
				cliente.setNome(rs.getString("nome"));
				cliente.setCpf(rs.getString("cpf"));
				cliente.setEmail(rs.getString("email"));
				cliente.setCelular(rs.getString("celular"));
				cliente.setSexo(rs.getString("sexo"));
			}
			rs.close();
			stmt.close();
			return cliente;
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	public List<Cliente> buscar(String busca) {
		log.debug("listando todos clientes");
		List<Cliente> clientes = new ArrayList<Cliente>();
		try {
			String sql = "SELECT * FROM cliente where nome like ?";
			PreparedStatement stmt = conexao.prepareStatement(sql);
			stmt.setString(1, "%" + busca + "%");
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				Cliente cliente = new Cliente();
				cliente.setId(rs.getInt("id"));
				cliente.setNome(rs.getString("nome"));
				cliente.setCpf(rs.getString("cpf"));
				cliente.setEmail(rs.getString("email"));
				cliente.setCelular(rs.getString("celular"));
				cliente.setSexo(rs.getString("sexo"));
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
