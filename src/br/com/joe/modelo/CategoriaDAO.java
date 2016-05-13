package br.com.joe.modelo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import br.com.joe.util.ConnectionFactory;
import br.com.joe.vo.Categoria;

public class CategoriaDAO {

	private static Logger log = Logger.getLogger(CategoriaDAO.class);

	private ConnectionFactory connectionFactory = new ConnectionFactory();
	private Connection conexao;

	public CategoriaDAO() {
		conexao = connectionFactory.getConnection();
	}

	public void adicionar(Categoria categoria) throws SQLException {
		log.debug("adicionando categoria" + categoria.getNome());
		String sql = "INSERT INTO categoria (nome) values (?)";
		PreparedStatement stmt = conexao.prepareStatement(sql);
		stmt.setString(1, categoria.getNome());
		stmt.execute();
		stmt.close();
	}

	public void remover(int id) throws SQLException {
		log.debug("excluindo categoria id " + id);
		String sql = "DELETE FROM categoria where id = ?";
			PreparedStatement stmt = conexao.prepareStatement(sql);
			stmt.setInt(1, id);
			stmt.execute();
			stmt.close();
	}

	public void atualizar(Categoria categoria) throws SQLException {
		log.debug("atualizando categoria: " + categoria.getId());
		String sql = "UPDATE categoria set nome = ? where id = ?";
			PreparedStatement stmt = conexao.prepareStatement(sql);
			stmt.setString(1, categoria.getNome());
			stmt.setInt(2, categoria.getId());
			stmt.execute();
			stmt.close();
	}

	public Categoria obterCategoriaPorId(int id) throws SQLException {
		log.debug("obtendo categoria id " + id);
		Categoria categoria = new Categoria();
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
	}

	public List<Categoria> buscar(String busca) throws SQLException {
		log.debug("buscando categorias");
		List<Categoria> categorias = new ArrayList<Categoria>();
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
	}
}
