package br.com.joe.apresentacao;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import br.com.joe.negocio.ClienteBO;
import br.com.joe.negocio.ProdutoBO;
import br.com.joe.negocio.VendaBO;
import br.com.joe.vo.Cliente;
import br.com.joe.vo.Funcionario;
import br.com.joe.vo.ItemVenda;
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

	private Venda venda = null;

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		HttpSession session = req.getSession();
		String operacao = req.getParameter("op");

		if (operacao.equals("adicionarItem")) {
			log.debug("adicionando item ao carrinho");

			ItemVenda itemVenda = new ItemVenda(req);
			venda.getItensVenda().add(itemVenda);

			req.getRequestDispatcher(PREPARA_CADASTRAR_VENDA).forward(req, resp);
		} else if (operacao.equals("adicionarCliente")) {
			ClienteBO clienteBO = new ClienteBO();
			Cliente cliente = clienteBO.obterCliente(Integer.parseInt(req.getParameter("clienteId")));
			venda.setCliente(cliente);
			req.getRequestDispatcher(PREPARA_CADASTRAR_VENDA).forward(req, resp);
		} else if (operacao.equals("salvarVenda")) {
			VendaBO vendaBO = new VendaBO();
			venda.setDesconto(Double.parseDouble(req.getParameter("desconto")));
			venda.setFuncionario((Funcionario) session.getAttribute("funcionario"));
			venda.setData(new java.sql.Date(new java.util.Date().getTime()));
			if (vendaBO.adicionar(venda)) {
				req.setAttribute("mensagem", Mensagens.sucesso);
			} else {
				req.setAttribute("mensagem", Mensagens.erroAdicionar);
			}
			req.getRequestDispatcher(INDEX).forward(req, resp);
			session.removeAttribute("venda");
		} else if (operacao.equals("consultar")) {
			if (!req.getParameter("vendaId").equals("")) {
				VendaBO vendaBO = new VendaBO();
				Venda vendaConsulta = vendaBO.obterVendaPorId(Integer.parseInt(req.getParameter("vendaId")));
				req.setAttribute("vendaConsulta", vendaConsulta);
				req.getRequestDispatcher(CONSULTARVENDA).forward(req, resp);
			} else {
				req.getRequestDispatcher(INDEX).forward(req, resp);
			}
		} else if (operacao.equals("excluir")) {
			VendaBO vendaBO = new VendaBO();
			if (vendaBO.remover(Integer.parseInt(req.getParameter("vendaId")))) {
				req.setAttribute("mensagem", Mensagens.sucesso);
			} else {
				req.setAttribute("mensagem", Mensagens.erroAdicionar);
			}
			req.getRequestDispatcher(INDEX).forward(req, resp);
		}
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		HttpSession session = req.getSession();
		String operacao = req.getParameter("op");

		venda = (Venda) session.getAttribute("venda");

		if (venda == null) {
			log.debug("Gerando um carrinho para a sessao: " + session.getId());
			venda = new Venda();
			session.setAttribute("venda", venda);
		}

		if (operacao.equals("preparaVenda")) {
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
		} else if (operacao.equals("excluirItem")) {
			venda.getItensVenda().remove(Integer.parseInt(req.getParameter("produtoIndex")));
			req.getRequestDispatcher(PREPARA_CADASTRAR_VENDA).forward(req, resp);
		}

	}
}
