package br.com.joe;

import java.util.ArrayList;

import br.com.joe.negocio.ClienteBO;
import br.com.joe.negocio.FuncionarioBO;
import br.com.joe.negocio.ProdutoBO;
import br.com.joe.negocio.VendaBO;
import br.com.joe.util.ConnectionFactory;
import br.com.joe.vo.Cliente;
import br.com.joe.vo.Funcionario;
import br.com.joe.vo.ItemVenda;
import br.com.joe.vo.Produto;
import br.com.joe.vo.Venda;

public class Teste {

	public static void main(String[] args) {

		//inserirVenda();
		//consultarVenda(1);
		
		ConnectionFactory con = new ConnectionFactory();
		con.getConnection();

	}

	public static void consultarVenda(int id) {
		VendaBO vendaBO = new VendaBO();
		Venda venda = vendaBO.obterVendaPorId(id);
		System.out.println("DESCONTO: " + venda.getDesconto());
		System.out.println(venda.getCliente().getNome());
		for (ItemVenda itemVenda : venda.getItensVenda()) {
			System.out.println(itemVenda.getProduto().getNome() + " - " + itemVenda.getQtd());
		}
	}

	public static void inserirVenda() {
		ClienteBO clienteBo = new ClienteBO();
		Cliente cliente = clienteBo.obterCliente(1);

		FuncionarioBO funcionarioBO = new FuncionarioBO();
		Funcionario funcionario = funcionarioBO.obterFuncionarioPorId(1);

		ProdutoBO produtoBO = new ProdutoBO();
		Produto produtoA = produtoBO.obterProdutoPorId(2);
		Produto produtoB = produtoBO.obterProdutoPorId(3);

		VendaBO vendaDao = new VendaBO();

		ArrayList<ItemVenda> itensVenda = new ArrayList<>();
		ItemVenda itemVendaA = new ItemVenda();
		ItemVenda itemVendaB = new ItemVenda();
		itemVendaA.setProduto(produtoA);
		itemVendaA.setQtd(1);
		itemVendaB.setProduto(produtoB);
		itemVendaB.setQtd(2);

		itensVenda.add(itemVendaA);
		itensVenda.add(itemVendaB);

		Venda venda = new Venda();
		venda.setCliente(cliente);
		venda.setFuncionario(funcionario);
		venda.setDesconto(10.00);
		venda.setTotal(200.00);
		venda.setItensVenda(itensVenda);

		vendaDao.adicionar(venda);
	}

}
