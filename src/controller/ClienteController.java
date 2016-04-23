package controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import dao.ClienteDao;
import model.Cliente;

@SuppressWarnings("serial")
public class ClienteController extends HttpServlet {

	private static Logger log = Logger.getLogger(ClienteController.class);

	private String INDEX = "cliente/index.jsp";
	private String CADASTRAR = "cliente/cadastrar.jsp";
	private String ALTERAR = "cliente/alterar.jsp";
	private String LISTAR = "cliente/clientes.jsp";

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		ClienteDao clienteDao = new ClienteDao();
		List<Cliente> clientes = clienteDao.listar();
		String operacao = req.getParameter("op");
		if (operacao != null) {
			log.debug("consultando operacao");
			if (operacao.equals("cadastrar")) {
				req.getRequestDispatcher(CADASTRAR).forward(req, resp);
			} else if (operacao.equals("listar")) {
				req.setAttribute("clientes", clientes);
				req.getRequestDispatcher(LISTAR).forward(req, resp);
			} else if (operacao.equals("alterar")) {
				Cliente cliente = clienteDao.obterClientePorId(Integer.parseInt(req.getParameter("clienteId")));
				req.getSession().setAttribute("cliente", cliente);

				req.getRequestDispatcher(ALTERAR).forward(req, resp);
			} else if (operacao.equals("excluir")) {
				clienteDao.remover(Integer.parseInt(req.getParameter("clienteId")));
				req.getRequestDispatcher(INDEX).forward(req, resp);
			} else {
				log.debug("operacao desconhecida");
				req.getRequestDispatcher(INDEX).forward(req, resp);
			}
		} else {
			log.debug("nao consta operacao");
			req.getRequestDispatcher(INDEX).forward(req, resp);
		}
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String operacao = req.getParameter("op");
		ClienteDao clienteDao = new ClienteDao();

		if (operacao != null) {
			log.debug("consultando operacao");
			if (operacao.equals("cadastrar")) {
				Cliente cliente = new Cliente();
				cliente.setNome(req.getParameter("nome"));
				cliente.setIdade(req.getParameter("idade"));
				clienteDao.adicionar(cliente);
				req.getRequestDispatcher(INDEX).forward(req, resp);
			} else if (operacao.equals("alterar")) {
				Cliente cliente = (Cliente) req.getSession().getAttribute("cliente");
				cliente.setNome(req.getParameter("nome"));
				cliente.setIdade(req.getParameter("idade"));
				clienteDao.atualizar(cliente);
				req.getRequestDispatcher(INDEX).forward(req, resp);
			} else {
				log.debug("operacao desconhecida");
				req.getRequestDispatcher(INDEX).forward(req, resp);
			}
		} else {
			log.debug("nao consta operacao");
			req.getRequestDispatcher(INDEX).forward(req, resp);
		}
	}
}
