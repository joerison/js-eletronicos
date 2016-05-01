package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import model.Categoria;
import util.ConnectionFactory;

public class CategoriaDao {

	private static Logger log = Logger.getLogger(CategoriaDao.class);

	private Connection conexao;

	public CategoriaDao() {
		conexao = ConnectionFactory.getConnection();
	}

	public void adicionar(Categoria categoria) {
		log.debug("adicionando categoria" + categoria.getNome());
		String sql = "INSERT INTO categoria (nome) values (?)";
		try {
			PreparedStatement stmt = conexao.prepareStatement(sql);
			stmt.setString(1, categoria.getNome());
			stmt.execute();
			stmt.close();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	public void remover(int id) {
		log.debug("excluindo categoria id " + id);
		String sql = "DELETE FROM categoria where id = ?";
		try {
			PreparedStatement stmt = conexao.prepareStatement(sql);
			stmt.setInt(1, id);
			stmt.execute();
			stmt.close();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	public void atualizar(Categoria categoria) {
		log.debug("atualizando categoria: " + categoria.getId() + " - " + categoria.getNome());
		String sql = "UPDATE categoria set nome = ? where id = ?";
		try {
			PreparedStatement stmt = conexao.prepareStatement(sql);
			stmt.setString(1, categoria.getNome());
			stmt.setInt(2, categoria.getId());
			stmt.execute();
			stmt.close();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	public Categoria obterCategoriaPorId(int id) {
		log.debug("obtendo categoria id " + id);
		Categoria categoria = new Categoria();
		try {
			String sql = "SELECT * FROM categoria where id = ?";
			PreparedStatement stmt = conexao.prepareStatement(sql);
			stmt.setInt(1, id);
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				categoria.setId(rs.getInt("id"));
				categoria.setNome(rs.getString("nome"));
			}
			rs.close();
			stmt.close();
			return categoria;
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	public List<Categoria> buscar(String busca) {
		log.debug("listando todos categorias");
		List<Categoria> categorias = new ArrayList<Categoria>();
		try {
			String sql = "SELECT * FROM categoria where nome like ?";
			PreparedStatement stmt = conexao.prepareStatement(sql);
			stmt.setString(1, "%" + busca + "%");
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				Categoria categoria = new Categoria();
				categoria.setId(rs.getInt("id"));
				categoria.setNome(rs.getString("nome"));
				categorias.add(categoria);
			}
			rs.close();
			stmt.close();
			return categorias;
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
}
