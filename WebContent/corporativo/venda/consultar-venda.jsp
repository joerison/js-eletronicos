<%@ page language="java" contentType="text/html; charset=iso-8859-1" pageEncoding="iso-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<html>
	<body>

			<jsp:include page="/corporativo/menu.jsp" />
<br /><br />
		Venda ID: <b>${vendaConsulta.id}</b> <br/>
		Cliente: <b>${vendaConsulta.cliente.nome}</b>  <br/>
		Data Venda: <b><fmt:formatDate value="${vendaConsulta.data}" pattern="dd/MM/yyyy" /></b>  <br/>
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
		
		<form action="/projetoltpiv/corporativo/venda" method="post" onsubmit="return (confirm ('Confirma exclusao?'));">
			<input type="hidden" name="op" value="excluir"/><br />
			<input type="hidden" name="vendaId" value="${vendaConsulta.id}"/><br />
			<input type="submit"value="Excluir venda" />
		</form>
		
	</body>
</html>