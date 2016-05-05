import java.util.ArrayList;

import br.com.joe.bo.ClienteBO;
import br.com.joe.modelo.FuncionarioDAO;
import br.com.joe.modelo.ProdutoDAO;
import br.com.joe.modelo.VendaDAO;
import br.com.joe.vo.Cliente;
import br.com.joe.vo.Funcionario;
import br.com.joe.vo.Produto;
import br.com.joe.vo.Venda;

public class Teste {
	
	public static void main(String[] args) {
		
		VendaDAO vendaDao = new VendaDAO();
		Venda venda = vendaDao.obterVendaPorId(14);
		System.out.println(venda.getDesconto());
		System.out.println(venda.getCliente().getNome());
		for (Produto produto : venda.getProdutos()){
			System.out.println(produto.getNome());
		}
	}
	
	public void inserirVenda(){
		ClienteBO clienteBo = new ClienteBO();
		Cliente cliente = clienteBo.obterCliente(1);
		
		FuncionarioDAO funcionarioDao = new FuncionarioDAO();
		Funcionario funcionario = funcionarioDao.obterFuncionarioPorId(1);
		
		ProdutoDAO produtoDao = new ProdutoDAO();
		Produto produtoA = produtoDao.obterProdutoPorId(4);
		Produto produtoB = produtoDao.obterProdutoPorId(5);
	
		VendaDAO vendaDao = new VendaDAO();
		
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
