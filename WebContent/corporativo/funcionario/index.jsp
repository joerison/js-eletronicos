<%@ page language="java" contentType="text/html; charset=iso-8859-1" pageEncoding="iso-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Todos funcionários</title>
</head>
<body>
			<jsp:include page="/corporativo/menu.jsp" />
<br /><br />
	
	<form action="funcionario" method="get">
		<label for="busca">Busca: </label>
		<input type="text" id="busca" name="busca">
		<input type="hidden" name="op" value="buscar">
		<input type="submit" value="Procurar" />
	</form>
		<br />
		<c:if test="${funcionarios!=null}">
		<table border=1>
			<tr>
				<th>Nome</th>
				<th>Alterar</th>
				<th>Excluir</th>
			</tr>
			<c:forEach var="funcionario" items="${funcionarios}">
				<tr>
					<td colspan="1">${funcionario.nome}</td>
					<td colspan="1"><a
						href="
						<c:url value="funcionario">
						<c:param name="op" value="alterar"/>
						<c:param name="funcionarioId" value="${funcionario.id}"/>
						</c:url>">Alterar</a></td>
					<td colspan="1"><a
						href="
						<c:url value="funcionario">
						<c:param name="op" value="excluir"/>
						<c:param name="funcionarioId" value="${funcionario.id}"/>
						</c:url>" onclick="return (confirm ('Confirma exclusao?'));">Excluir</a></td>
				</tr>
			</c:forEach>
		</table>
		</c:if>
		<br />
	<a href="funcionario?op=cadastrar">Cadastrar Funcionario</a>
</body>
</html>