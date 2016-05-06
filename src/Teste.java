import java.util.ArrayList;

import br.com.joe.bo.ClienteBO;
import br.com.joe.bo.FuncionarioBO;
import br.com.joe.bo.ProdutoBO;
import br.com.joe.bo.VendaBO;
import br.com.joe.vo.Cliente;
import br.com.joe.vo.Funcionario;
import br.com.joe.vo.Produto;
import br.com.joe.vo.Venda;

public class Teste {

	public static void main(String[] args) {

		VendaBO vendaBO = new VendaBO();
		Venda venda = vendaBO.obterVendaPorId(14);
		System.out.println(venda.getDesconto());
		System.out.println(venda.getCliente().getNome());
		for (Produto produto : venda.getProdutos()) {
			System.out.println(produto.getNome());
		}
	}

	public void inserirVenda() {
		ClienteBO clienteBo = new ClienteBO();
		Cliente cliente = clienteBo.obterCliente(1);

		FuncionarioBO funcionarioBO = new FuncionarioBO();
		Funcionario funcionario = funcionarioBO.obterFuncionarioPorId(1);

		ProdutoBO produtoBO = new ProdutoBO();
		Produto produtoA = produtoBO.obterProdutoPorId(4);
		Produto produtoB = produtoBO.obterProdutoPorId(5);

		VendaBO vendaDao = new VendaBO();

		ArrayList<Produto> produtos = new ArrayList<>();
		produtos.add(produtoA);
		produtos.add(produtoB);

		Venda venda = new Venda();
		venda.setCliente(cliente);
		venda.setFuncionario(funcionario);
		venda.setDesconto(10.00);
		venda.setTotal(200.00);
		venda.setTotalComDesconto(190.00);
		venda.setProdutos(produtos);

		vendaDao.adicionar(venda);
	}

}
