package br.com.joe.apresentacao;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import br.com.joe.Carrinho;
import br.com.joe.ItemVenda;
import br.com.joe.negocio.ClienteBO;
import br.com.joe.negocio.ProdutoBO;
import br.com.joe.negocio.VendaBO;
import br.com.joe.vo.Cliente;
import br.com.joe.vo.Funcionario;
import br.com.joe.vo.Produto;
import br.com.joe.vo.Venda;

@SuppressWarnings("serial")
public class VendaController extends HttpServlet {

	private static Logger log = Logger.getLogger(VendaController.class);

	private static String PREPARA_CADASTRAR_VENDA = "venda/cadastrar.jsp";
	private static String ADICIONARITENS = "/corporativo/venda/selecionar-itens.jsp";
	private static String ADICIONARCLIENTE = "/corporativo/venda/selecionar-cliente.jsp";
	private static String CONSULTARVENDA = "/corporativo/venda/consultar-venda.jsp";
	private static String INDEX = "/corporativo/venda/index.jsp";

	private Carrinho carrinho = Carrinho.getCarrinho();

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		String operacao = req.getParameter("op");

		if (operacao.equals("adicionarItem")) {
			log.debug("adicionando item ao carrinho");
			ProdutoBO produtoBO = new ProdutoBO();
			ItemVenda itemCarrinho = new ItemVenda();
			itemCarrinho.setProduto(produtoBO.obterProdutoPorId(Integer.parseInt(req.getParameter("produtoId"))));
			itemCarrinho.setQtd(Integer.parseInt(req.getParameter("qtd")));
			carrinho.getVenda().getItensVenda().add(itemCarrinho);
			req.setAttribute("cliente", carrinho.getVenda().getCliente());
			req.setAttribute("itensCarrinho", carrinho.getVenda().getItensVenda());
			req.getRequestDispatcher(PREPARA_CADASTRAR_VENDA).forward(req, resp);
		} else if (operacao.equals("adicionarCliente")) {
			ClienteBO clienteBO = new ClienteBO();
			Cliente cliente = clienteBO.obterCliente(Integer.parseInt(req.getParameter("clienteId")));
			carrinho.getVenda().setCliente(cliente);
			req.setAttribute("cliente", carrinho.getVenda().getCliente());
			req.setAttribute("itensCarrinho", carrinho.getVenda().getItensVenda());
			req.getRequestDispatcher(PREPARA_CADASTRAR_VENDA).forward(req, resp);
		} else if (operacao.equals("salvarVenda")) {
			carrinho.getVenda().setFuncionario((Funcionario) req.getSession().getAttribute("funcionario"));
			VendaBO vendaBO = new VendaBO();
			carrinho.getVenda().setDesconto(Double.parseDouble(req.getParameter("desconto")));
			log.debug(carrinho.getVenda().getItensVenda().size());
			Venda venda = carrinho.getVenda();
			log.debug(venda.getItensVenda().size());
			vendaBO.adicionar(carrinho.getVenda());
			req.getRequestDispatcher(INDEX).forward(req, resp);
			carrinho.setVenda(new Venda());
		} else if (operacao.equals("consultar")) {
			if (!req.getParameter("vendaId").equals("")) {
				VendaBO vendaBO = new VendaBO();
				Venda vendaConsulta = vendaBO.obterVendaPorId(Integer.parseInt(req.getParameter("vendaId")));
				req.setAttribute("vendaConsulta", vendaConsulta);
				req.getRequestDispatcher(CONSULTARVENDA).forward(req, resp);
			}else{
				req.getRequestDispatcher(INDEX).forward(req, resp);
			}
		} else if (operacao.equals("excluir")) {
			VendaBO vendaBO = new VendaBO();
			if (vendaBO.remover(Integer.parseInt(req.getParameter("vendaId")))) {
				log.debug("venda removida com sucesso");
			} else {
				log.debug("ouve um problema na remocao");
			}
			req.getRequestDispatcher(INDEX).forward(req, resp);
		}
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		String operacao = req.getParameter("op");

		if (operacao.equals("preparaVenda")) {
			req.setAttribute("cliente", carrinho.getVenda().getCliente());
			req.setAttribute("itensCarrinho", carrinho.getVenda().getItensVenda());
			req.getRequestDispatcher(PREPARA_CADASTRAR_VENDA).forward(req, resp);
		} else if (operacao.equals("selecionarCliente")) {
			ClienteBO clienteBO = new ClienteBO();
			List<Cliente> clientes = clienteBO.buscar("");
			req.setAttribute("clientes", clientes);
			req.getRequestDispatcher(ADICIONARCLIENTE).forward(req, resp);
		} else if (operacao.equals("selecionarItens")) {
			ProdutoBO produtoBO = new ProdutoBO();
			List<Produto> produtos = produtoBO.buscar("");
			req.setAttribute("produtos", produtos);
			req.getRequestDispatcher(ADICIONARITENS).forward(req, resp);
		} else if(operacao.equals("excluirItem")){
			carrinho.getVenda().getItensVenda().remove(Integer.parseInt(req.getParameter("produtoIndex")));
			req.setAttribute("cliente", carrinho.getVenda().getCliente());
			req.setAttribute("itensCarrinho", carrinho.getVenda().getItensVenda());
			req.getRequestDispatcher(PREPARA_CADASTRAR_VENDA).forward(req, resp);
		}

	}
}
