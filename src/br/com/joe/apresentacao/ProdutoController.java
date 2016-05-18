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
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		ProdutoBO produtoBO = new ProdutoBO();

		CategoriaBO categoriaBO = new CategoriaBO();
		List<Categoria> categorias = categoriaBO.buscar("%");
		req.setAttribute("categorias", categorias);
		Produto produto;

		String operacao = req.getParameter("op");
		if (operacao != null) {
			log.debug("consultando operacao");

			switch (operacao) {
			case "cadastrar":
				req.getRequestDispatcher(CADASTRAR).forward(req, resp);
				break;
			case "buscar":
				List<Produto> produtos = produtoBO.buscar(req
						.getParameter("busca"));
				req.setAttribute("produtos", produtos);
				req.getRequestDispatcher(INDEX).forward(req, resp);
				break;
			case "alterar":
				produto = produtoBO.obterProdutoPorId(Integer.parseInt(req
						.getParameter("produtoId")));
				req.setAttribute("produto", produto);
				req.getRequestDispatcher(ALTERAR).forward(req, resp);
				break;
			case "excluir":
				if (produtoBO.remover(Integer.parseInt(req
						.getParameter("produtoId")))) {
					req.setAttribute("mensagem", Mensagens.sucesso);
				} else {
					req.setAttribute("mensagem", Mensagens.erroAdicionar);
				}
				req.getRequestDispatcher(INDEX).forward(req, resp);
				break;

			default:
				log.debug("operacao desconhecida");
				req.getRequestDispatcher(INDEX).forward(req, resp);
				break;
			}
		} else {
			log.debug("nao consta operacao");
			req.getRequestDispatcher(INDEX).forward(req, resp);
		}
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		String operacao = req.getParameter("op");
		ProdutoBO produtoBO = new ProdutoBO();
		Produto produto;

		if (operacao != null) {
			log.debug("consultando operacao");

			switch (operacao) {
			case "cadastrar":
				produto = new Produto(req);
				if (produtoBO.adicionar(produto)) {
					req.setAttribute("mensagem", Mensagens.sucesso);
				} else {
					req.setAttribute("mensagem", Mensagens.erroAdicionar);
				}
				req.getRequestDispatcher(INDEX).forward(req, resp);
				break;
			case "alterar":
				produto = new Produto(req);
				if (produtoBO.atualizar(produto)) {
					req.setAttribute("mensagem", Mensagens.sucesso);
				} else {
					req.setAttribute("mensagem", Mensagens.erroAtualizar);
				}
				req.getRequestDispatcher(INDEX).forward(req, resp);
				break;
			default:
				log.debug("operacao desconhecida");
				req.getRequestDispatcher(INDEX).forward(req, resp);
				break;
			}
		} else {
			log.debug("nao consta operacao");
			req.getRequestDispatcher(INDEX).forward(req, resp);
		}
	}
}
