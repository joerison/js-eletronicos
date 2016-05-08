package br.com.joe.apresentacao;

import java.io.IOException;
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
	private static String RELATORIOVENDAS = "relatorio/relatorio-vendas.jsp";

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		String operacao = req.getParameter("op");
		VendaBO vendaBO = new VendaBO();

		if (operacao.equals("relatorioVendas")) {
			log.debug("obtendo historico de vendas");

			java.sql.Date dtInicio = java.sql.Date.valueOf(req.getParameter("dtInicio"));
			java.sql.Date dtFim = java.sql.Date.valueOf(req.getParameter("dtFim"));
			List<Venda> historicoVendas = vendaBO.obterVendasPorIntervalo(dtInicio, dtFim);
			req.setAttribute("historicoVendas", historicoVendas);
			req.getRequestDispatcher(RELATORIOVENDAS).forward(req, resp);
		}

	}
}
