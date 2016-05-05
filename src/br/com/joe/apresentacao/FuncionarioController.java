package br.com.joe.apresentacao;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import br.com.joe.modelo.FuncionarioDAO;
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
		FuncionarioDAO funcionarioDao = new FuncionarioDAO();
		
		String operacao = req.getParameter("op");
		if (operacao != null) {
			log.debug("consultando operacao");
			if (operacao.equals("cadastrar")) {
				req.getRequestDispatcher(CADASTRAR).forward(req, resp);
			} else if (operacao.equals("buscar")) {
				List<Funcionario> funcionarios = funcionarioDao.buscar(req.getParameter("busca"));
				req.setAttribute("funcionarios", funcionarios);
				req.getRequestDispatcher(LISTAR).forward(req, resp);
			} else if (operacao.equals("alterar")) {
				Funcionario funcionarioEdicao = funcionarioDao
						.obterFuncionarioPorId(Integer.parseInt(req.getParameter("funcionarioId")));
				req.getSession().setAttribute("funcionarioEdicao", funcionarioEdicao);
				req.getRequestDispatcher(ALTERAR).forward(req, resp);
			} else if (operacao.equals("excluir")) {
				funcionarioDao.remover(Integer.parseInt(req.getParameter("funcionarioId")));
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
		FuncionarioDAO funcionarioDao = new FuncionarioDAO();

		if (operacao != null) {
			log.debug("consultando operacao");
			if (operacao.equals("cadastrar")) {
				Funcionario funcionario = new Funcionario();
				funcionario.setNome(req.getParameter("nome"));
				funcionario.setCpf(req.getParameter("cpf"));
				funcionario.setEmail(req.getParameter("email"));
				funcionario.setCelular(req.getParameter("celular"));
				funcionario.setSexo(req.getParameter("sexo"));
				funcionario.setLogin(req.getParameter("login"));
				funcionario.setSenha(req.getParameter("senha"));
				funcionarioDao.adicionar(funcionario);
				req.getRequestDispatcher(INDEX).forward(req, resp);
			} else if (operacao.equals("alterar")) {
				Funcionario funcionario = (Funcionario) req.getSession().getAttribute("funcionarioEdicao");
				funcionario.setNome(req.getParameter("nome"));
				funcionario.setCpf(req.getParameter("cpf"));
				funcionario.setEmail(req.getParameter("email"));
				funcionario.setCelular(req.getParameter("celular"));
				funcionario.setSexo(req.getParameter("sexo"));
				funcionario.setLogin(req.getParameter("login"));
				funcionario.setSenha(req.getParameter("senha"));
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
