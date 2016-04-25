package controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import dao.UsuarioDao;
import model.Usuario;

@SuppressWarnings("serial")
public class UsuarioController extends HttpServlet {

	private static Logger log = Logger.getLogger(UsuarioController.class);

	private String INDEX = "usuario/index.jsp";
	private String CADASTRAR = "usuario/cadastrar.jsp";
	private String ALTERAR = "usuario/alterar.jsp";
	private String LISTAR = "usuario/usuarios.jsp";

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		UsuarioDao usuarioDao = new UsuarioDao();
		List<Usuario> usuarios = usuarioDao.listar();
		String operacao = req.getParameter("op");
		if (operacao != null) {
			log.debug("consultando operacao");
			if (operacao.equals("cadastrar")) {
				req.getRequestDispatcher(CADASTRAR).forward(req, resp);
			} else if (operacao.equals("listar")) {
				req.setAttribute("usuarios", usuarios);
				req.getRequestDispatcher(LISTAR).forward(req, resp);
			} else if (operacao.equals("alterar")) {
				Usuario usuarioEdicao = usuarioDao.obterUsuarioPorId(Integer.parseInt(req.getParameter("usuarioId")));
				req.getSession().setAttribute("usuarioEdicao", usuarioEdicao);
				req.getRequestDispatcher(ALTERAR).forward(req, resp);
			} else if (operacao.equals("excluir")) {
				usuarioDao.remover(Integer.parseInt(req.getParameter("usuarioId")));
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
		UsuarioDao usuarioDao = new UsuarioDao();

		if (operacao != null) {
			log.debug("consultando operacao");
			if (operacao.equals("cadastrar")) {
				Usuario usuario = new Usuario();
				usuario.setLogin(req.getParameter("login"));
				usuario.setSenha(req.getParameter("senha"));
				usuarioDao.adicionar(usuario);
				req.getRequestDispatcher(INDEX).forward(req, resp);
			} else if (operacao.equals("alterar")) {
				Usuario usuario = (Usuario) req.getSession().getAttribute("usuario");
				usuario.setLogin(req.getParameter("login"));
				usuario.setSenha(req.getParameter("senha"));
				usuarioDao.atualizar(usuario);
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
