package controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.UsuarioDao;
import model.Usuario;

@SuppressWarnings("serial")
public class ValidacaoDeAcesso extends HttpServlet {

	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		System.out.println("validando login... doGet");
		doPost(req, res);
	}

	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		System.out.println("validando login... doPost");
		RequestDispatcher rd;

		String name = req.getParameter("name");
		String password = req.getParameter("password");

		System.out.println("nome: " + name);
		System.out.println("senha: " + password);
		Usuario user = validateLogin(name, password);

		if (user == null) {
			rd = req.getRequestDispatcher("/loginErro.jsp");
		} else {
			HttpSession session = req.getSession();
			session.setAttribute("user", user);
			rd = req.getRequestDispatcher("/corporativo/loginSucesso.jsp");
		}

		rd.forward(req, res);
	}

	private Usuario validateLogin(String login, String senha) {
		System.out.println("validando login...validateLogin");
		if (login == null || senha == null) {
			return null;
		}

		Usuario userLocalizado = null;

		UsuarioDao usuarioDao = new UsuarioDao();

		userLocalizado = usuarioDao.obterUsuarioPorLogin(login);

		if (userLocalizado == null) {
			return null;
		}

		if (!userLocalizado.getSenha().equals(senha.trim())) {
			return null;
		}

		return userLocalizado;
	}
}