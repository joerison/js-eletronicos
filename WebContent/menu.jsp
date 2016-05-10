<%@ page language="java" contentType="text/html; charset=iso-8859-1"
	pageEncoding="iso-8859-1"%>
<html>
<head>
<script src="/projetoltpiv/js/jquery-2.2.3.min.js"></script>
<link rel="stylesheet" href="/projetoltpiv/css/estilo.css">
</head>
<body>
	<ul id="top-menu">
		<li><a href="/projetoltpiv/index.jsp">Início</a></li>
		<li><a href="#">Cadastros</a>
			<ul>
				<li><a href="/projetoltpiv/corporativo/cliente">Clientes</a></li>
				<li><a href="/projetoltpiv/corporativo/funcionario">Funcionários</a></li>
				<li><a href="/projetoltpiv/corporativo/produto">Produtos</a></li>
				<li><a href="/projetoltpiv/corporativo/produto/categoria">Categorias</a></li>
			</ul></li>
		<li><a href="/projetoltpiv/corporativo/venda/index.jsp">Venda</a>
		<li><a href="/projetoltpiv/corporativo/relatorio/index.jsp">Relatório de Vendas</a>
		</li>
		<li><a href="/projetoltpiv/validacaoDeAcesso?logout=true">Sair</a></li>
	</ul>
</body>