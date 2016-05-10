<%@ page language="java" contentType="text/html; charset=iso-8859-1" pageEncoding="iso-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Todos clientes</title>
</head>
<body>
			<jsp:include page="/menu.jsp" />

<br /><br />
	
		<form action="cliente" method="get">
			<label for="busca">Busca: </label>
			<input type="text" id="busca" name="busca">
			<input type="hidden" name="op" value="buscar">
			<input type="submit" value="Procurar" />
		</form>
	
		<table>
			<c:forEach var="cliente" items="${clientes}">
				<tr>
					<td colspan="1"><input type="checkbox" name="${cliente.nome}"
						value="${cliente.nome}">${cliente.nome}</td>
					<td colspan="1"><a
						href="
						<c:url value="cliente">
						<c:param name="op" value="alterar"/>
						<c:param name="clienteId" value="${cliente.id}"/>
						</c:url>">Alterar</a></td>
					<td colspan="1"><a
						href="
						<c:url value="cliente">
						<c:param name="op" value="excluir"/>
						<c:param name="clienteId" value="${cliente.id}"/>
						</c:url>">Excluir</a></td>
				</tr>
			</c:forEach>
		</table>
	<a href="cliente?op=cadastrar">Cadastrar Cliente</a>

</body>
</html>