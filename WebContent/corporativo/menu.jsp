<%@ page language="java" contentType="text/html; charset=iso-8859-1"
	pageEncoding="iso-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
	
<html>
<head>
<script src="/js-eletronicos/js/jquery-2.2.3.min.js"></script>
<script src="/js-eletronicos/js/scripts.js"></script>
<link rel="stylesheet" href="/js-eletronicos/css/estilo.css">
</head>
<body>
	<ul id="top-menu">
		<li><a href="/js-eletronicos/index.jsp">Início</a></li>
		<li><a href="#">Cadastros</a>
			<ul>
				<li><a href="/js-eletronicos/corporativo/cliente">Clientes</a></li>
				<li><a href="/js-eletronicos/corporativo/funcionario">Funcionários</a></li>
				<li><a href="/js-eletronicos/corporativo/produto">Produtos</a></li>
				<li><a href="/js-eletronicos/corporativo/produto/categoria">Categorias</a></li>
			</ul></li>
		<li><a href="/js-eletronicos/corporativo/venda?op=preparaIndex">Venda</a>
		<li><a href="/js-eletronicos/corporativo/relatorio?op=preparaRelatorioVendas">Relatório de Vendas</a>
		</li>
		<li><a href="/js-eletronicos/validacaoDeAcesso?logout=true">Sair</a></li>
	</ul>
	<br /><br />
	<c:if test="${not empty mensagem}">
		<span class="erro">${mensagem}</span>
	</c:if>
</body>