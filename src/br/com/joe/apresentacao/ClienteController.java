package br.com.joe.apresentacao;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import br.com.joe.negocio.ClienteBO;
import br.com.joe.vo.Cliente;

@SuppressWarnings("serial")
public class ClienteController extends HttpServlet {

	private static Logger log = Logger.getLogger(ClienteController.class);

	private String INDEX = "cliente/index.jsp";
	private String CADASTRAR = "cliente/cadastrar.jsp";
	private String ALTERAR = "cliente/alterar.jsp";
	private ClienteBO clientebo = new ClienteBO();

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {

		String operacao = req.getParameter("op");
		Cliente cliente;

		if (operacao != null) {
			log.debug("consultando operacao");

			switch (operacao) {
			case "cadastrar":
				req.getRequestDispatcher(CADASTRAR).forward(req, resp);
				break;
			case "buscar":
				List<Cliente> clientes = clientebo.buscar(req
						.getParameter("busca"));
				req.setAttribute("clientes", clientes);
				req.getRequestDispatcher(INDEX).forward(req, resp);
				break;
			case "alterar":
				cliente = clientebo.obterCliente(Integer.parseInt(req
						.getParameter("clienteId")));
				req.setAttribute("cliente", cliente);
				req.getRequestDispatcher(ALTERAR).forward(req, resp);
				break;
			case "excluir":
				if (clientebo.remover(Integer.parseInt(req
						.getParameter("clienteId")))) {
					req.setAttribute("mensagem", Mensagens.sucesso);
				} else {
					req.setAttribute("mensagem", Mensagens.erroAdicionar);
				}
				req.getRequestDispatcher(INDEX).forward(req, resp);
				break;
			default:
				log.debug("operacao desconhecida");
				req.getRequestDispatcher(INDEX).forward(req, resp);
				break;
			}
		} else {
			log.debug("nao consta operacao");
			req.getRequestDispatcher(INDEX).forward(req, resp);
		}
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		String operacao = req.getParameter("op");
		Cliente cliente;
		if (operacao != null) {
			log.debug("consultando operacao");

			switch (operacao) {
			case "cadastrar":
				cliente = new Cliente(req);
				if (clientebo.adicionar(cliente)) {
					req.setAttribute("mensagem", Mensagens.sucesso);
				} else {
					req.setAttribute("mensagem", Mensagens.erroAdicionar);
				}
				req.getRequestDispatcher(INDEX).forward(req, resp);
				break;
			case "alterar":
				cliente = new Cliente(req);
				if (clientebo.alterar(cliente)) {
					req.setAttribute("mensagem", Mensagens.sucesso);
				} else {
					req.setAttribute("mensagem", Mensagens.erroAdicionar);
				}
				req.getRequestDispatcher(INDEX).forward(req, resp);
				break;
			default:
				log.debug("operacao desconhecida");
				req.getRequestDispatcher(INDEX).forward(req, resp);
				break;
			}
		} else {
			log.debug("nao consta operacao");
			req.getRequestDispatcher(INDEX).forward(req, resp);
		}
	}
}
