<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Todos usuarios</title>
</head>
<body>
	<a href="/projetoltpiv/index.jsp">Início</a><br /><br />
	<form action="usuario" method="post">
		<table>
			<c:forEach var="usuario" items="${usuarios}">
				<tr>
					<td colspan="1"><input type="checkbox" name="${usuario.login}"
						value="${usuario.login}">${usuario.login}</td>
					<td colspan="1"><a
						href="
						<c:url value="usuario">
						<c:param name="op" value="alterar"/>
						<c:param name="usuarioId" value="${usuario.id}"/>
						</c:url>">Alterar</a></td>
					<td colspan="1"><a
						href="
						<c:url value="usuario">
						<c:param name="op" value="excluir"/>
						<c:param name="usuarioId" value="${usuario.id}"/>
						</c:url>">Excluir</a></td>
				</tr>
			</c:forEach>
		</table>
		<!-- <input type="submit" value="Ação" /> -->
	</form>
</body>
</html>