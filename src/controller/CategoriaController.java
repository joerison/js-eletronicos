package controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import dao.CategoriaDao;
import model.Categoria;

@SuppressWarnings("serial")
public class CategoriaController extends HttpServlet {

	private static Logger log = Logger.getLogger(CategoriaController.class);

	private String INDEX = "/corporativo/produto/categoria/index.jsp";
	private String CADASTRAR = "/corporativo/produto/categoria/cadastrar.jsp";
	private String ALTERAR = "/corporativo/produto/categoria/alterar.jsp";

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		CategoriaDao categoriaDao = new CategoriaDao();
		
		String operacao = req.getParameter("op");
		if (operacao != null) {
			log.debug("consultando operacao");
			if (operacao.equals("cadastrar")) {
				req.getRequestDispatcher(CADASTRAR).forward(req, resp);
			} else if (operacao.equals("buscar")) {
				List<Categoria> categorias = categoriaDao.buscar(req.getParameter("busca"));
				req.setAttribute("categorias", categorias);
				req.getRequestDispatcher(INDEX).forward(req, resp);
			} else if (operacao.equals("alterar")) {
				Categoria categoria = categoriaDao.obterCategoriaPorId(Integer.parseInt(req.getParameter("categoriaId")));
				req.getSession().setAttribute("categoria", categoria);
				req.getRequestDispatcher(ALTERAR).forward(req, resp);
			} else if (operacao.equals("excluir")) {
				categoriaDao.remover(Integer.parseInt(req.getParameter("categoriaId")));
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
		CategoriaDao categoriaDao = new CategoriaDao();

		if (operacao != null) {
			log.debug("consultando operacao");
			if (operacao.equals("cadastrar")) {
				Categoria categoria = new Categoria();
				categoria.setNome(req.getParameter("nome"));
				categoriaDao.adicionar(categoria);
				req.getRequestDispatcher(INDEX).forward(req, resp);
			} else if (operacao.equals("alterar")) {
				Categoria categoria = (Categoria) req.getSession().getAttribute("categoria");
				categoria.setNome(req.getParameter("nome"));
				categoriaDao.atualizar(categoria);
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
