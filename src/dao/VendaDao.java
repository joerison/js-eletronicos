package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import model.Produto;
import model.Venda;
import util.ConnectionFactory;

public class VendaDao {

	private static Logger log = Logger.getLogger(VendaDao.class);

	private Connection conexao;

	public VendaDao() {
		conexao = ConnectionFactory.getConnection();
	}

	public void adicionar(Venda venda) {
		log.debug("adicionando venda" + venda.getId());
		String sql = "INSERT INTO venda (id_cliente, id_funcionario, desconto, total, totalComDesconto) values (?, ?, ?, ?, ?)";
		try {
			PreparedStatement stmt = conexao.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			stmt.setInt(1, venda.getCliente().getId());
			stmt.setInt(2, venda.getFuncionario().getId());
			stmt.setDouble(3, venda.getDesconto());
			stmt.setDouble(4, venda.getTotal());
			stmt.setDouble(5, venda.getTotalComDesconto());
			stmt.execute();
			
			/*Entender como funciona essa lógica...*/
			try (ResultSet generatedKeys = stmt.getGeneratedKeys()) {
	            if (generatedKeys.next()) {
	            	venda.setId(generatedKeys.getInt(1));
	            }
	            else {
	                throw new SQLException("Creating user failed, no ID obtained.");
	            }
	        }
			log.debug("ID GERADO: " + venda.getId());
			stmt.close();
			
			//INSERINDO PRODUTOS NA TABELA VENDA/PRODUTO
			sql = "INSERT INTO venda_produto (id_venda, id_produto, qtd, total) values (?, ?, ?, ?)";
			
			for (Produto produto : venda.getProdutos()){
				stmt = conexao.prepareStatement(sql);
				stmt.setInt(1, venda.getId());
				stmt.setInt(2, produto.getId());
				stmt.setInt(3, 0);
				stmt.setInt(4, 0);
				stmt.execute();
				stmt.close();
			}
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		
		
	}

	public void remover(int id) {
		log.debug("excluindo venda id " + id);
		String sql = "DELETE FROM venda where id = ?";
		try {
			PreparedStatement stmt = conexao.prepareStatement(sql);
			stmt.setInt(1, id);
			stmt.execute();
			stmt.close();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	public void atualizar(Venda venda) {
		//log.debug("atualizando venda: " + venda.getId() + " - " + venda.getNome());
		String sql = "UPDATE venda set nome = ? where id = ?";
		try {
			PreparedStatement stmt = conexao.prepareStatement(sql);
			//stmt.setString(1, venda.getNome());
			stmt.setInt(2, venda.getId());
			stmt.execute();
			stmt.close();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	public Venda obterVendaPorId(int id) {
		log.debug("obtendo venda id " + id);
		Venda venda = new Venda();
		try {
			ClienteDao clienteDao = new ClienteDao();
			FuncionarioDao funcionarioDao = new FuncionarioDao();
			ProdutoDao produtoDao = new ProdutoDao();
			String sql = "SELECT * FROM venda where id = ?";
			PreparedStatement stmt = conexao.prepareStatement(sql);
			stmt.setInt(1, id);
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				venda.setId(rs.getInt("id"));
				venda.setCliente(clienteDao.obterClientePorId(rs.getInt("id_cliente")));
				venda.setFuncionario(funcionarioDao.obterFuncionarioPorId(rs.getInt("id_funcionario")));
				
				String sqlVendaProduto = "SELECT * FROM venda_produto where id_venda = ?";
				PreparedStatement stmtVendaProduto = conexao.prepareStatement(sqlVendaProduto);
				stmtVendaProduto.setInt(1, venda.getId());
				ResultSet rsVendaProduto = stmtVendaProduto.executeQuery();
				ArrayList<Produto> produtos = new ArrayList<Produto>();
				while (rsVendaProduto.next()) {
					Produto produto = produtoDao.obterProdutoPorId(Integer.parseInt(rsVendaProduto.getString("id_produto")));
					produtos.add(produto);
				}
				venda.setProdutos(produtos);
				
				venda.setDesconto(Double.parseDouble(rs.getString("desconto")));
				venda.setDesconto(Double.parseDouble(rs.getString("total")));
				venda.setDesconto(Double.parseDouble(rs.getString("totalComDesconto")));
			}
			rs.close();
			stmt.close();
			return venda;
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	public List<Venda> buscar(String busca) {
		log.debug("listando todos vendas");
		List<Venda> vendas = new ArrayList<Venda>();
		try {
			String sql = "SELECT * FROM venda where nome like ?";
			PreparedStatement stmt = conexao.prepareStatement(sql);
			stmt.setString(1, "%" + busca + "%");
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				Venda venda = new Venda();
				venda.setId(rs.getInt("id"));
				//venda.setNome(rs.getString("nome"));
				vendas.add(venda);
			}
			rs.close();
			stmt.close();
			return vendas;
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
}
