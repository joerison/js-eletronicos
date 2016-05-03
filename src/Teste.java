import java.util.ArrayList;

import dao.ClienteDao;
import dao.FuncionarioDao;
import dao.ProdutoDao;
import dao.VendaDao;
import model.Cliente;
import model.Funcionario;
import model.Produto;
import model.Venda;

public class Teste {
	
	public static void main(String[] args) {
		
		VendaDao vendaDao = new VendaDao();
		Venda venda = vendaDao.obterVendaPorId(14);
		System.out.println(venda.getDesconto());
		System.out.println(venda.getCliente().getNome());
		for (Produto produto : venda.getProdutos()){
			System.out.println(produto.getNome());
		}
	}
	
	public void inserirVenda(){
		ClienteDao clienteDao = new ClienteDao();
		Cliente cliente = clienteDao.obterClientePorId(1);
		
		FuncionarioDao funcionarioDao = new FuncionarioDao();
		Funcionario funcionario = funcionarioDao.obterFuncionarioPorId(1);
		
		ProdutoDao produtoDao = new ProdutoDao();
		Produto produtoA = produtoDao.obterProdutoPorId(4);
		Produto produtoB = produtoDao.obterProdutoPorId(5);
	
		VendaDao vendaDao = new VendaDao();
		
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
