<%@ page language="java" contentType="text/html; charset=iso-8859-1" pageEncoding="iso-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
	<body>

	<a href="/projetoltpiv/index.jsp">Início</a><br /><br />
		
		Venda ID: ${vendaConsulta.id} <br/>
		Cliente: ${vendaConsulta.cliente.nome} <br/>
		<br/>
		<table border=1>
			<tr>
			<td>Produto</td>
			<td>Qtd</td>
			<td>Valor</td>
			<tr>
			<c:forEach var="itemVenda" items="${vendaConsulta.itensVenda}">
			<tr>
			<td>${itemVenda.produto.nome}</td>
			<td>${itemVenda.qtd}</td>
			<td>${itemVenda.total}</td>
			</tr>
			</c:forEach>
		</table>
		Desconto: ${vendaConsulta.desconto} <br/>
		Total: ${vendaConsulta.total}
		
		<form action="/projetoltpiv/corporativo/venda" method="post">
			<input type="hidden" name="op" value="excluir"/><br />
			<input type="hidden" name="vendaId" value="${vendaConsulta.id}"/><br />
			<input type="submit"value="Excluir venda" />
		</form>
		
	</body>
</html>