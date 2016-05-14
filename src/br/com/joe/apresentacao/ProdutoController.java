package br.com.joe.apresentacao;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import br.com.joe.negocio.CategoriaBO;
import br.com.joe.negocio.ProdutoBO;
import br.com.joe.vo.Categoria;
import br.com.joe.vo.Produto;

@SuppressWarnings("serial")
public class ProdutoController extends HttpServlet {

	private static Logger log = Logger.getLogger(ProdutoController.class);

	private String INDEX = "produto/index.jsp";
	private String CADASTRAR = "produto/cadastrar.jsp";
	private String ALTERAR = "produto/alterar.jsp";

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		ProdutoBO produtoBO = new ProdutoBO();

		CategoriaBO categoriaBO = new CategoriaBO();
		List<Categoria> categorias = categoriaBO.buscar("%");
		req.setAttribute("categorias", categorias);

		String operacao = req.getParameter("op");
		if (operacao != null) {
			log.debug("consultando operacao");
			if (operacao.equals("cadastrar")) {
				req.getRequestDispatcher(CADASTRAR).forward(req, resp);
			} else if (operacao.equals("buscar")) {
				List<Produto> produtos = produtoBO.buscar(req.getParameter("busca"));
				req.setAttribute("produtos", produtos);
				req.getRequestDispatcher(INDEX).forward(req, resp);
			} else if (operacao.equals("alterar")) {
				Produto produto = produtoBO.obterProdutoPorId(Integer.parseInt(req.getParameter("produtoId")));
				req.setAttribute("produto", produto);
				req.getRequestDispatcher(ALTERAR).forward(req, resp);
			} else if (operacao.equals("excluir")) {
				produtoBO.remover(Integer.parseInt(req.getParameter("produtoId")));
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
		ProdutoBO produtoBO = new ProdutoBO();

		if (operacao != null) {
			log.debug("consultando operacao");
			if (operacao.equals("cadastrar")) {
				Produto produto = new Produto(req);
				produtoBO.adicionar(produto);
				req.getRequestDispatcher(INDEX).forward(req, resp);
			} else if (operacao.equals("alterar")) {
				Produto produto = new Produto(req);
				produtoBO.atualizar(produto);
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
