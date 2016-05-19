package br.com.joe.modelo;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import br.com.joe.util.ConnectionFactory;
import br.com.joe.vo.ItemVenda;
import br.com.joe.vo.Produto;
import br.com.joe.vo.Venda;

public class VendaDAO {

	private static Logger log = Logger.getLogger(VendaDAO.class);

	private ConnectionFactory connectionFactory = new ConnectionFactory();
	private Connection conexao;

	public VendaDAO() {
		conexao = connectionFactory.getConnection();
	}

	public void adicionar(Venda venda) throws SQLException {
		log.debug("adicionando venda");
		String sql = "INSERT INTO venda (id_cliente, id_funcionario, desconto, total, data) values (?, ?, ?, ?, ?)";
		PreparedStatement stmt = conexao.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
		stmt.setInt(1, venda.getCliente().getId());
		stmt.setInt(2, venda.getFuncionario().getId());
		stmt.setDouble(3, venda.getDesconto());
		stmt.setDouble(4, venda.getTotal());
		stmt.setDate(5, venda.getData());
		stmt.execute();

		try (ResultSet generatedKeys = stmt.getGeneratedKeys()) {
			if (generatedKeys.next()) {
				venda.setId(generatedKeys.getInt(1));
				log.debug("Venda ID gerado: " + venda.getId());
			} else {
				log.error("Nenhum id obtido");
			}
		}
		stmt.close();

		sql = "INSERT INTO venda_item (id_venda, id_produto, qtd, total) values (?, ?, ?, ?)";

		for (ItemVenda itemVenda : venda.getItensVenda()) {
			log.debug("inserindo itens na tabela venda/item");
			stmt = conexao.prepareStatement(sql);
			stmt.setInt(1, venda.getId());
			stmt.setInt(2, itemVenda.getProduto().getId());
			stmt.setInt(3, itemVenda.getQtd());
			stmt.setDouble(4, itemVenda.getTotal());
			stmt.execute();
			stmt.close();
		}
	}

	public void remover(int id) throws SQLException {
		log.debug("removendo venda id " + id);
		String sql = "DELETE FROM venda_item where id_venda = ?";
		PreparedStatement stmt = conexao.prepareStatement(sql);
		stmt.setInt(1, id);
		stmt.execute();
		
		sql = "DELETE FROM venda where id = ?";
		stmt = conexao.prepareStatement(sql);
		stmt.setInt(1, id);
		stmt.execute();
		stmt.close();
	}

	public Venda obterVendaPorId(int id) throws SQLException {
		log.debug("obtendo venda id " + id);
		Venda venda = new Venda();
		ClienteDAO clienteDao = new ClienteDAO();
		FuncionarioDAO funcionarioDao = new FuncionarioDAO();
		ProdutoDAO produtoDao = new ProdutoDAO();
		String sql = "SELECT * FROM venda where id = ?";
		PreparedStatement stmt = conexao.prepareStatement(sql);
		stmt.setInt(1, id);
		ResultSet rs = stmt.executeQuery();
		while (rs.next()) {
			venda.setId(rs.getInt("id"));
			venda.setCliente(clienteDao.obterClientePorId(rs.getInt("id_cliente")));
			venda.setFuncionario(funcionarioDao.obterFuncionarioPorId(rs.getInt("id_funcionario")));
			venda.setDesconto(rs.getDouble("desconto"));
			venda.setTotal(rs.getDouble("total"));
			venda.setData(rs.getDate("data"));
			String sqlVendaProduto = "SELECT * FROM venda_item where id_venda = ?";
			PreparedStatement stmtVendaItem = conexao.prepareStatement(sqlVendaProduto);
			stmtVendaItem.setInt(1, venda.getId());
			ResultSet rsVendaItem = stmtVendaItem.executeQuery();
			ArrayList<ItemVenda> itensVenda = new ArrayList<ItemVenda>();
			while (rsVendaItem.next()) {
				ItemVenda itemVenda = new ItemVenda();
				Produto produto = produtoDao
						.obterProdutoPorId(Integer.parseInt(rsVendaItem.getString("id_produto")));
				itemVenda.setProduto(produto);
				itemVenda.setQtd(Integer.parseInt(rsVendaItem.getString("qtd")));
				itemVenda.setTotal(rsVendaItem.getDouble("total"));
				itensVenda.add(itemVenda);
			}
			venda.setItensVenda(itensVenda);;
		}
		rs.close();
		stmt.close();
		return venda;
	}
	
	
	public List<Venda> obterVendasPorIntervalo(Date inicio, Date fim) throws SQLException {
		log.debug("obtendo vendas por intervalo ");
		List<Venda> vendas = new ArrayList<Venda>();
		ClienteDAO clienteDao = new ClienteDAO();
		FuncionarioDAO funcionarioDao = new FuncionarioDAO();
		ProdutoDAO produtoDao = new ProdutoDAO();
		String sql = "SELECT * FROM venda WHERE data >= ? AND data <= ?";
		log.debug("OBJETO conexao" + conexao);
		PreparedStatement stmt = conexao.prepareStatement(sql);
		log.debug("OBJETO STMT" + stmt);
		stmt.setDate(1, inicio);
		stmt.setDate(2, fim);
		ResultSet rs = stmt.executeQuery();
		while (rs.next()) {
			Venda venda = new Venda();
			venda.setId(rs.getInt("id"));
			venda.setCliente(clienteDao.obterClientePorId(rs.getInt("id_cliente")));
			venda.setFuncionario(funcionarioDao.obterFuncionarioPorId(rs.getInt("id_funcionario")));
			venda.setDesconto(rs.getDouble("desconto"));
			venda.setTotal(rs.getDouble("total"));
			venda.setData(rs.getDate("data"));
			String sqlVendaProduto = "SELECT * FROM venda_item where id_venda = ?";
			PreparedStatement stmtVendaItem = conexao.prepareStatement(sqlVendaProduto);
			stmtVendaItem.setInt(1, venda.getId());
			ResultSet rsVendaItem = stmtVendaItem.executeQuery();
			ArrayList<ItemVenda> itensVenda = new ArrayList<ItemVenda>();
			while (rsVendaItem.next()) {
				ItemVenda itemVenda = new ItemVenda();
				Produto produto = produtoDao
						.obterProdutoPorId(Integer.parseInt(rsVendaItem.getString("id_produto")));
				itemVenda.setProduto(produto);
				itemVenda.setQtd(Integer.parseInt(rsVendaItem.getString("qtd")));
				itemVenda.setTotal(rsVendaItem.getDouble("total"));
				itensVenda.add(itemVenda);
			}
			log.debug(venda.getTotal());
			venda.setItensVenda(itensVenda);;
			vendas.add(venda);
		}
		rs.close();
		stmt.close();
		return vendas;
	}
}
