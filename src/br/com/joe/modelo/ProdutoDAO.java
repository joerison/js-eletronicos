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
import br.com.joe.vo.Produto;

public class ProdutoDAO {

	private static Logger log = Logger.getLogger(ProdutoDAO.class);

	public void adicionar(Produto produto) throws SQLException {
		ConnectionFactory connectionFactory = new ConnectionFactory();
		Connection conexao = connectionFactory.getConnection();
		
		log.debug("adicionando produto" + produto.getNome());
		String sql = "INSERT INTO produto (nome, preco, id_categoria) values (?, ?, ?)";
		PreparedStatement stmt = conexao.prepareStatement(sql);
		stmt.setString(1, produto.getNome());
		stmt.setDouble(2, produto.getPreco());
		stmt.setInt(3, produto.getCategoria().getId());
		stmt.execute();
		stmt.close();
		conexao.close();
	}

	public void remover(int id) throws SQLException {
		ConnectionFactory connectionFactory = new ConnectionFactory();
		Connection conexao = connectionFactory.getConnection();
		
		log.debug("excluindo produto id " + id);
		String sql = "DELETE FROM produto where id = ?";
		PreparedStatement stmt = conexao.prepareStatement(sql);
		stmt.setInt(1, id);
		stmt.execute();
		stmt.close();
		conexao.close();
	}

	public void atualizar(Produto produto) throws SQLException {
		ConnectionFactory connectionFactory = new ConnectionFactory();
		Connection conexao = connectionFactory.getConnection();
		
		log.debug("atualizando produto: " + produto.getId());
		String sql = "UPDATE produto set nome = ?, preco = ?, id_categoria = ? where id = ?";
		PreparedStatement stmt = conexao.prepareStatement(sql);
		stmt.setString(1, produto.getNome());
		stmt.setDouble(2, produto.getPreco());
		stmt.setInt(3, produto.getCategoria().getId());
		stmt.setInt(4, produto.getId());
		stmt.execute();
		stmt.close();
		conexao.close();
	}

	public Produto obterProdutoPorId(int id) throws SQLException {
		ConnectionFactory connectionFactory = new ConnectionFactory();
		Connection conexao = connectionFactory.getConnection();
		
		log.debug("obtendo produto id " + id);
		CategoriaDAO categoriaDao = new CategoriaDAO();
		Categoria categoria;

		Produto produto = null;
		String sql = "SELECT * FROM produto where id = ?";
		PreparedStatement stmt = conexao.prepareStatement(sql);
		stmt.setInt(1, id);
		ResultSet rs = stmt.executeQuery();
		while (rs.next()) {
			produto = new Produto();
			produto.setId(rs.getInt("id"));
			produto.setNome(rs.getString("nome"));
			produto.setPreco(Double.parseDouble(rs.getString("preco")));
			categoria = categoriaDao.obterCategoriaPorId(Integer.parseInt(rs.getString("id_categoria")));
			produto.setCategoria(categoria);
		}
		rs.close();
		stmt.close();
		conexao.close();
		return produto;
	}

	public List<Produto> buscar(String busca) throws SQLException {
		ConnectionFactory connectionFactory = new ConnectionFactory();
		Connection conexao = connectionFactory.getConnection();
		
		log.debug("buscando produtos");
		List<Produto> produtos = new ArrayList<Produto>();
		String sql = "SELECT * FROM produto where nome like ?";
		PreparedStatement stmt = conexao.prepareStatement(sql);
		stmt.setString(1, "%" + busca + "%");
		ResultSet rs = stmt.executeQuery();
		while (rs.next()) {
			Produto produto = new Produto();
			produto.setId(rs.getInt("id"));
			produto.setNome(rs.getString("nome"));
			produto.setPreco(rs.getDouble("preco"));
			produtos.add(produto);
		}
		rs.close();
		stmt.close();
		conexao.close();
		return produtos;
	}
}
