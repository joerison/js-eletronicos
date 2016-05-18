<%@ page language="java" contentType="text/html; charset=iso-8859-1"
	pageEncoding="iso-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
	<body>

		<jsp:include page="/corporativo/menu.jsp" />
<br /><br />
		<form action="cliente" method="post" onsubmit="return (confirm ('Confirma alteracao?'));">
		
			<input type="hidden" name="id" value="${cliente.id}"/><br />
			<label for="nome">Nome: </label>
			<input type="text" id="nome" name="nome" value="${cliente.nome}"/><br />
			<label for="cpf">Cpf: </label>
			<input type="text" id="cpf" name="cpf" value="${cliente.cpf}"/><br />
			<label for="email">E-mail: </label>
			<input type="text" id="email" name="email" value="${cliente.email}"/><br />
			<label for="celular">Celular: </label>
			<input type="text" id="celular" name="celular" value="${cliente.celular}"/><br />

			<label for="sexo">Sexo: </label>
			<c:choose>
				<c:when test="${cliente.sexo=='F'}">
					<input type="radio" id="sexo" name="sexo" value="M">Masculino
					<input type="radio" id="sexo" name="sexo" value="F" checked>Feminino<br>
				</c:when>
				<c:otherwise>
					<input type="radio" id="sexo" name="sexo" value="M" checked>Masculino
					<input type="radio" id="sexo" name="sexo" value="F">Feminino<br>
				</c:otherwise>
			</c:choose>
			
			<input type="hidden" name="op" value="alterar"/><br />
			<input type="submit"value="Alterar" />
		</form>
	</body>
</html>