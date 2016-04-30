package controller;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import model.Funcionario;

public class FiltroSeguranca implements Filter {

	private static Logger log = Logger.getLogger(FiltroSeguranca.class);
	private String LOGIN = "/login.jsp";

	public void init(FilterConfig fConfig) throws ServletException {
		log.debug("inicializando o filtro");
	}

	public void destroy() {
		log.debug("destruindo o filtro");
	}

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {

		HttpServletRequest req = (HttpServletRequest) request;
		HttpSession session = req.getSession();
		Funcionario user = (Funcionario) session.getAttribute("funcionario");

		log.debug("verificando se existe usuario na sessao");
		if (user == null) {
			RequestDispatcher rd = req.getRequestDispatcher(LOGIN);
			rd.forward(request, response);
		} else {
			chain.doFilter(request, response);
		}

	}
}