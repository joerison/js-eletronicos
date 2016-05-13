package br.com.joe.apresentacao;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import br.com.joe.negocio.FuncionarioBO;
import br.com.joe.vo.Funcionario;

@SuppressWarnings("serial")
public class FuncionarioController extends HttpServlet {

	private static Logger log = Logger.getLogger(FuncionarioController.class);

	private String INDEX = "funcionario/index.jsp";
	private String CADASTRAR = "funcionario/cadastrar.jsp";
	private String ALTERAR = "funcionario/alterar.jsp";
	private String LISTAR = "funcionario/index.jsp";

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		FuncionarioBO funcionarioBO = new FuncionarioBO();

		String operacao = req.getParameter("op");
		if (operacao != null) {
			log.debug("consultando operacao");
			if (operacao.equals("cadastrar")) {
				req.getRequestDispatcher(CADASTRAR).forward(req, resp);
			} else if (operacao.equals("buscar")) {
				List<Funcionario> funcionarios = funcionarioBO.buscar(req.getParameter("busca"));
				req.setAttribute("funcionarios", funcionarios);
				req.getRequestDispatcher(LISTAR).forward(req, resp);
			} else if (operacao.equals("alterar")) {
				Funcionario funcionarioEdicao = funcionarioBO
						.obterFuncionarioPorId(Integer.parseInt(req.getParameter("funcionarioId")));
				req.setAttribute("funcionarioEdicao", funcionarioEdicao);
				req.getRequestDispatcher(ALTERAR).forward(req, resp);
			} else if (operacao.equals("excluir")) {
				funcionarioBO.remover(Integer.parseInt(req.getParameter("funcionarioId")));
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
		FuncionarioBO funcionarioDao = new FuncionarioBO();

		if (operacao != null) {
			log.debug("consultando operacao");
			if (operacao.equals("cadastrar")) {
				Funcionario funcionario = new Funcionario(req);
				funcionarioDao.adicionar(funcionario);
				req.getRequestDispatcher(INDEX).forward(req, resp);
			} else if (operacao.equals("alterar")) {
				Funcionario funcionario = new Funcionario(req);
				funcionarioDao.atualizar(funcionario);
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
