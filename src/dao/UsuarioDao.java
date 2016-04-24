package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import model.Usuario;
import util.ConnectionFactory;

public class UsuarioDao {

	private static Logger log = Logger.getLogger(UsuarioDao.class);

	private Connection conexao;

	public UsuarioDao() {
		conexao = ConnectionFactory.getConnection();
	}

	public void adicionar(Usuario usuario) {
		log.debug("adicionando usuario" + usuario.getLogin());
		String sql = "INSERT INTO usuario (nome, senha) values (?, ?)";
		try {
			PreparedStatement stmt = conexao.prepareStatement(sql);
			stmt.setString(1, usuario.getLogin());
			stmt.setString(2, usuario.getSenha());
			stmt.execute();
			stmt.close();
		} catch (SQLException e) {
			log.error("erro ao adicionar usuario" + e.getMessage());
			throw new RuntimeException(e);
		}
	}

	public void remover(int id) {
		log.debug("excluindo usuario id " + id);
		String sql = "DELETE FROM usuario where id = ?";
		try {
			PreparedStatement stmt = conexao.prepareStatement(sql);
			stmt.setInt(1, id);
			stmt.execute();
			stmt.close();
		} catch (SQLException e) {
			log.error("erro ao excluir usuario id " + e.getMessage());
			throw new RuntimeException(e);
		}
	}

	public void atualizar(Usuario usuario) {
		log.debug("atualizando usuario: " + usuario.getId() + " - " + usuario.getLogin());
		String sql = "UPDATE usuario set nome = ?, idade= ? where id = ?";
		try {
			PreparedStatement stmt = conexao.prepareStatement(sql);
			stmt.setString(1, usuario.getLogin());
			stmt.setString(2, usuario.getSenha());
			stmt.setInt(3, usuario.getId());
			stmt.execute();
			stmt.close();
		} catch (SQLException e) {
			log.error("erro ao atualizar usuario: " + e.getMessage());
			throw new RuntimeException(e);
		}
	}

	public Usuario obterUsuarioPorId(int id) {
		log.debug("obtendo usuario id " + id);
		Usuario usuario = new Usuario();
		try {
			String sql = "SELECT * FROM usuario where id = ?";
			PreparedStatement stmt = conexao.prepareStatement(sql);
			stmt.setInt(1, id);
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				usuario.setId(rs.getInt("id"));
				usuario.setLogin(rs.getString("nome"));
				usuario.setSenha(rs.getString("senha"));
			}
			rs.close();
			stmt.close();
			return usuario;
		} catch (SQLException e) {
			log.error("erro ao obter usuario: "  + e.getMessage());
			throw new RuntimeException(e);
		}
	}

	public Usuario obterUsuarioPorLogin(String login) {
		log.debug("obtendo usuario login " + login);
		Usuario usuario = null;
		try {
			String sql = "SELECT * FROM usuario where login = ?";
			PreparedStatement stmt = conexao.prepareStatement(sql);
			stmt.setString(1, login);
			ResultSet rs = stmt.executeQuery();
			if (rs.first()) {
				log.debug("usuario: " + login + " - localizado");
				usuario = new Usuario();
				usuario.setId(rs.getInt("id"));
				usuario.setLogin(rs.getString("login"));
				usuario.setSenha(rs.getString("senha"));
			} else {
				log.debug("usuario: " + login + " - nao localizado");
			}
			rs.close();
			stmt.close();
			return usuario;
		} catch (SQLException e) {
			log.error("erro ao obter usuario login: " + e.getMessage());
			throw new RuntimeException(e);
		}
	}

	public List<Usuario> listar() {
		log.debug("listando todos usuarios");
		List<Usuario> usuarios = new ArrayList<Usuario>();
		try {
			String sql = "SELECT * FROM usuario";
			PreparedStatement stmt = conexao.prepareStatement(sql);
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				Usuario usuario = new Usuario();
				usuario.setId(rs.getInt("id"));
				usuario.setLogin(rs.getString("nome"));
				usuario.setSenha(rs.getString("idade"));
				usuarios.add(usuario);
			}
			rs.close();
			stmt.close();
			return usuarios;
		} catch (SQLException e) {
			log.error("erro ao listar todos usuarios: " + e.getMessage());
			throw new RuntimeException(e);
		}
	}
}
