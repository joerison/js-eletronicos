package br.com.joe.apresentacao;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import br.com.joe.negocio.VendaBO;
import br.com.joe.vo.Venda;

@SuppressWarnings("serial")
public class RelatorioController extends HttpServlet {

	private static Logger log = Logger.getLogger(RelatorioController.class);
	private static String RELATORIO_VENDAS = "relatorio/relatorio-vendas.jsp";

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {

		String operacao = req.getParameter("op");
		VendaBO vendaBO = new VendaBO();

		switch (operacao) {
		case "preparaRelatorioVendas":
			java.util.Date data = new java.util.Date();

			SimpleDateFormat dt1 = new SimpleDateFormat("dd/MM/yyyy");

			req.setAttribute("dtInicio", dt1.format(data));
			req.setAttribute("dtFim", dt1.format(data));
			req.getRequestDispatcher(RELATORIO_VENDAS).forward(req, resp);
			break;
		case "relatorioVendas":
			log.debug("obtendo historico de vendas");
			List<Venda> historicoVendas = vendaBO.obterVendasPorIntervalo(
					req.getParameter("dtInicio"), req.getParameter("dtFim"));

			if (historicoVendas == null) {
				req.setAttribute("mensagem", Mensagens.erroNoProcessamento);
				req.getRequestDispatcher(RELATORIO_VENDAS).forward(req, resp);
				break;
			}

			// mantendo o estado da pesquisa
			req.setAttribute("dtInicio", req.getParameter("dtInicio"));
			req.setAttribute("dtFim", req.getParameter("dtFim"));

			req.setAttribute("historicoVendas", historicoVendas);
			req.getRequestDispatcher(RELATORIO_VENDAS).forward(req, resp);
			break;

		default:
			break;
		}

	}
}
