package controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import dao.CategoriaDao;
import dao.ProdutoDao;
import model.Categoria;
import model.Produto;

@SuppressWarnings("serial")
public class ProdutoController extends HttpServlet {

	private static Logger log = Logger.getLogger(ProdutoController.class);

	private String INDEX = "produto/index.jsp";
	private String CADASTRAR = "produto/cadastrar.jsp";
	private String ALTERAR = "produto/alterar.jsp";

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		ProdutoDao produtoDao = new ProdutoDao();
		
		CategoriaDao categoriaDao = new CategoriaDao();
		List<Categoria> categorias = categoriaDao.buscar("%");
		req.setAttribute("categorias", categorias);
		
		String operacao = req.getParameter("op");
		if (operacao != null) {
			log.debug("consultando operacao");
			if (operacao.equals("cadastrar")) {
				req.getRequestDispatcher(CADASTRAR).forward(req, resp);
			} else if (operacao.equals("buscar")) {
				List<Produto> produtos = produtoDao.buscar(req.getParameter("busca"));
				req.setAttribute("produtos", produtos);
				req.getRequestDispatcher(INDEX).forward(req, resp);
			} else if (operacao.equals("alterar")) {
				Produto produto = produtoDao.obterProdutoPorId(Integer.parseInt(req.getParameter("produtoId")));
				req.getSession().setAttribute("produto", produto);
				req.getRequestDispatcher(ALTERAR).forward(req, resp);
			} else if (operacao.equals("excluir")) {
				produtoDao.remover(Integer.parseInt(req.getParameter("produtoId")));
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
		ProdutoDao produtoDao = new ProdutoDao();
		CategoriaDao categoriaDao = new CategoriaDao();
		Categoria categoria;
		
		if (operacao != null) {
			log.debug("consultando operacao");
			if (operacao.equals("cadastrar")) {
				Produto produto = new Produto();
				produto.setNome(req.getParameter("nome"));
				produto.setPreco(Double.parseDouble(req.getParameter("preco")));
				categoria = categoriaDao.obterCategoriaPorId(Integer.parseInt(req.getParameter("categoria")));
				produto.setCategoria(categoria);
				produtoDao.adicionar(produto);
				req.getRequestDispatcher(INDEX).forward(req, resp);
			} else if (operacao.equals("alterar")) {
				Produto produto = (Produto) req.getSession().getAttribute("produto");
				produto.setNome(req.getParameter("nome"));
				produto.setPreco(Double.parseDouble(req.getParameter("preco")));
				categoria = categoriaDao.obterCategoriaPorId(Integer.parseInt(req.getParameter("categoria")));
				produto.setCategoria(categoria);
				produtoDao.atualizar(produto);
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
