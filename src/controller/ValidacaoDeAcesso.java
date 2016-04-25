package controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import dao.UsuarioDao;
import model.Usuario;

@SuppressWarnings("serial")
public class ValidacaoDeAcesso extends HttpServlet {

	private static Logger log = Logger.getLogger(ValidacaoDeAcesso.class);

	private String INDEX = "/projetoltpiv/index.jsp";
	private String CORPORATIVO = "/corporativo/index.jsp";
	
	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		log.debug("chamando doGet");
		if (!invalidarSessao(req, res)) {
			doPost(req, res);
		}else{
			res.sendRedirect(INDEX);
		}
	}

	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		log.debug("chamando doPost");
		RequestDispatcher rd;

		String login = req.getParameter("login");
		String senha = req.getParameter("senha");

		Usuario usuario = validateLogin(login, senha);

		if (usuario == null) {
			log.debug("o login e senha informados nao foram capazes de gerar um objeto Usuario");
			rd = req.getRequestDispatcher("/login.jsp");
		} else {
			log.debug("Acesso permitido, o usuario sera atribuido na sessao corrente");
			HttpSession session = req.getSession();
			session.setAttribute("usuario", usuario);
			rd = req.getRequestDispatcher(CORPORATIVO);
		}
		rd.forward(req, res);
	}

	public boolean invalidarSessao(HttpServletRequest req, HttpServletResponse res) {
		String logout = (String) req.getParameter("logout");
		if ("true".equals(logout)) {
			req.getSession().invalidate();
			log.debug("invalidado sessão do usuario");
			return true;
		} else
			return false;
	}

	private Usuario validateLogin(String login, String senha) {
		if (login == null || senha == null) {
			return null;
		}

		Usuario usuario = null;
		UsuarioDao usuarioDao = new UsuarioDao();

		log.debug("validando o login");
		usuario = usuarioDao.obterUsuarioPorLogin(login);

		if (usuario == null) {
			return null;
		}

		log.debug("validando a senha");
		if (!usuario.getSenha().equals(senha.trim())) {
			return null;
		}
		return usuario;
	}
}