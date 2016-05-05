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

	private ConnectionFactory connectionFactory = new ConnectionFactory();
	private Connection conexao;

	public ProdutoDAO() {
		conexao = connectionFactory.getConnection();
	}

	public void adicionar(Produto produto) {
		log.debug("adicionando produto" + produto.getNome());
		String sql = "INSERT INTO produto (nome, preco, id_categoria) values (?, ?, ?)";
		try {
			PreparedStatement stmt = conexao.prepareStatement(sql);
			stmt.setString(1, produto.getNome());
			stmt.setDouble(2, produto.getPreco());
			stmt.setInt(3, produto.getCategoria().getId());
			stmt.execute();
			stmt.close();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	public void remover(int id) {
		log.debug("excluindo produto id " + id);
		String sql = "DELETE FROM produto where id = ?";
		try {
			PreparedStatement stmt = conexao.prepareStatement(sql);
			stmt.setInt(1, id);
			stmt.execute();
			stmt.close();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	public void atualizar(Produto produto) {
		log.debug("atualizando produto: " + produto.getId() + " - " + produto.getNome());
		String sql = "UPDATE produto set nome = ?, preco = ?, id_categoria = ? where id = ?";
		try {
			PreparedStatement stmt = conexao.prepareStatement(sql);
			stmt.setString(1, produto.getNome());
			stmt.setDouble(2, produto.getPreco());
			stmt.setInt(3, produto.getCategoria().getId());
			stmt.setInt(4, produto.getId());
			stmt.execute();
			stmt.close();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	public Produto obterProdutoPorId(int id) {
		log.debug("obtendo produto id " + id);
		CategoriaDAO categoriaDao = new CategoriaDAO();
		Categoria categoria;
		
		Produto produto = new Produto();
		try {
			String sql = "SELECT * FROM produto where id = ?";
			PreparedStatement stmt = conexao.prepareStatement(sql);
			stmt.setInt(1, id);
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				produto.setId(rs.getInt("id"));
				produto.setNome(rs.getString("nome"));
				produto.setPreco(Double.parseDouble(rs.getString("preco")));
				categoria = categoriaDao.obterCategoriaPorId(Integer.parseInt(rs.getString("id_categoria")));
				produto.setCategoria(categoria);
			}
			rs.close();
			stmt.close();
			return produto;
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	public List<Produto> buscar(String busca) {
		log.debug("listando todos produtos");
		List<Produto> produtos = new ArrayList<Produto>();
		try {
			String sql = "SELECT * FROM produto where nome like ?";
			PreparedStatement stmt = conexao.prepareStatement(sql);
			stmt.setString(1, "%" + busca + "%");
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				Produto produto = new Produto();
				produto.setId(rs.getInt("id"));
				produto.setNome(rs.getString("nome"));
				produtos.add(produto);
			}
			rs.close();
			stmt.close();
			return produtos;
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
}
