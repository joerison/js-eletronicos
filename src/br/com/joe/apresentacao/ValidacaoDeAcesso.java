package br.com.joe.apresentacao;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import br.com.joe.negocio.FuncionarioBO;
import br.com.joe.vo.Funcionario;

@SuppressWarnings("serial")
public class ValidacaoDeAcesso extends HttpServlet {

	private static Logger log = Logger.getLogger(ValidacaoDeAcesso.class);

	private String INDEX = "/projetoltpiv/index.jsp";
	private String CORPORATIVO = "/corporativo/index.jsp";
	private String LOGIN = "/login.jsp";
	
	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		if (!invalidarSessao(req, res)) {
			doPost(req, res);
		}else{
			res.sendRedirect(INDEX);
		}
	}

	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		RequestDispatcher rd;

		String login = req.getParameter("login");
		String senha = req.getParameter("senha");

		Funcionario funcionario = validateLogin(login, senha);

		if (funcionario == null) {
			log.debug("o login e senha informados nao foram capazes de gerar um objeto Funcionario");
			rd = req.getRequestDispatcher(LOGIN);
		} else {
			log.debug("Acesso permitido, o funcionario sera atribuido na sessao corrente");
			HttpSession session = req.getSession();
			session.setAttribute("funcionario", funcionario);
			rd = req.getRequestDispatcher(CORPORATIVO);
		}
		rd.forward(req, res);
	}

	public boolean invalidarSessao(HttpServletRequest req, HttpServletResponse res) {
		String logout = (String) req.getParameter("logout");
		if ("true".equals(logout)) {
			req.getSession().invalidate();
			log.debug("invalidado a sessão do funcionario");
			return true;
		} else
			return false;
	}

	private Funcionario validateLogin(String login, String senha) {
		if (login == null || senha == null) {
			return null;
		}

		Funcionario funcionario = null;
		FuncionarioBO funcionarioBO = new FuncionarioBO();

		log.debug("validando o login");
		funcionario = funcionarioBO.obterFuncionarioPorLogin(login);

		if (funcionario == null) {
			return null;
		}

		log.debug("validando a senha");
		if (!funcionario.getSenha().equals(senha.trim())) {
			return null;
		}
		return funcionario;
	}
}