<%@ page language="java" contentType="text/html; charset=iso-8859-1" pageEncoding="iso-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Todas Categorias</title>
</head>
<body>
	<a href="/projetoltpiv/index.jsp">Início</a><br /><br />
	
		<form action="categoria" method="get">
			<label for="busca">Busca: </label>
			<input type="text" id="busca" name="busca">
			<input type="hidden" name="op" value="buscar">
			<input type="submit" value="Procurar" />
		</form>
	
		<table>
			<c:forEach var="categoria" items="${categorias}">
				<tr>
					<td colspan="1"><input type="checkbox" name="${categoria.nome}"
						value="${categoria.nome}">${categoria.nome}</td>
					<td colspan="1"><a
						href="
						<c:url value="categoria">
						<c:param name="op" value="alterar"/>
						<c:param name="categoriaId" value="${categoria.id}"/>
						</c:url>">Alterar</a></td>
					<td colspan="1"><a
						href="
						<c:url value="categoria">
						<c:param name="op" value="excluir"/>
						<c:param name="categoriaId" value="${categoria.id}"/>
						</c:url>">Excluir</a></td>
				</tr>
			</c:forEach>
		</table>
	<a href="categoria?op=cadastrar">Cadastrar categoria</a>

</body>
</html>