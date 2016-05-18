package br.com.joe.apresentacao;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import br.com.joe.negocio.CategoriaBO;
import br.com.joe.vo.Categoria;

@SuppressWarnings("serial")
public class CategoriaController extends HttpServlet {

	private static Logger log = Logger.getLogger(CategoriaController.class);

	private String INDEX = "/corporativo/produto/categoria/index.jsp";
	private String CADASTRAR = "/corporativo/produto/categoria/cadastrar.jsp";
	private String ALTERAR = "/corporativo/produto/categoria/alterar.jsp";

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		CategoriaBO categoriaBO = new CategoriaBO();
		Categoria categoria;
		String operacao = req.getParameter("op");
		if (operacao != null) {
			log.debug("consultando operacao");
			switch (operacao) {
			case "cadastrar":
				req.getRequestDispatcher(CADASTRAR).forward(req, resp);
				break;
			case "buscar":
				List<Categoria> categorias = categoriaBO.buscar(req
						.getParameter("busca"));
				req.setAttribute("categorias", categorias);
				req.getRequestDispatcher(INDEX).forward(req, resp);
				break;
			case "alterar":
				categoria = categoriaBO.obterCategoriaPorId(Integer
						.parseInt(req.getParameter("categoriaId")));
				req.setAttribute("categoria", categoria);
				req.getRequestDispatcher(ALTERAR).forward(req, resp);
				break;
			case "excluir":
				if (categoriaBO.remover(Integer.parseInt(req
						.getParameter("categoriaId")))) {
					req.setAttribute("mensagem", Mensagens.sucesso);
				} else {
					req.setAttribute("mensagem", Mensagens.erroRemover);
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
		CategoriaBO categoriaBO = new CategoriaBO();
		Categoria categoria;
		if (operacao != null) {
			log.debug("consultando operacao");

			switch (operacao) {
			case "cadastrar":
				categoria = new Categoria(req);
				if (categoriaBO.adicionar(categoria)) {
					req.setAttribute("mensagem", Mensagens.sucesso);
				} else {
					req.setAttribute("mensagem", Mensagens.erroAdicionar);
				}
				req.getRequestDispatcher(INDEX).forward(req, resp);
				break;
			case "alterar":
				categoria = new Categoria(req);
				if (categoriaBO.atualizar(categoria)) {
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
