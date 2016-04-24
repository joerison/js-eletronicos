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

	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		log.debug("chamando doGet");
		doPost(req, res);
	}

	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		log.debug("chamando doPost");
		RequestDispatcher rd;

		String name = req.getParameter("name");
		String password = req.getParameter("password");

		Usuario usuario = validateLogin(name, password);

		if (usuario == null) {
			rd = req.getRequestDispatcher("/loginErro.jsp");
		} else {
			HttpSession session = req.getSession();
			session.setAttribute("usuario", usuario);
			rd = req.getRequestDispatcher("/corporativo/loginSucesso.jsp");
		}
		rd.forward(req, res);
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