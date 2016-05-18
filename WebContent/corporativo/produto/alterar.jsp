<%@ page language="java" contentType="text/html; charset=iso-8859-1"
	pageEncoding="iso-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
	<body>

			<jsp:include page="/corporativo/menu.jsp" />
<br /><br />
		<form action="produto" method="post" onsubmit="return (confirm ('Confirma alteracao?'));">
			
			<input type="hidden" name="id" value="${produto.id}"/>
			<label for="nome">Nome: </label>
			<input type="text" id="nome" name="nome" value="${produto.nome}"/><br />
			<label for="preco">Preco: </label>
			<input type="text" id="preco" name="preco" value="${produto.preco}"/><br />

			<select name="categoria">
				<c:forEach var="categoria" items="${categorias}">
					<c:choose>
					    <c:when test="${produto.categoria.nome==categoria.nome}">
						<option value="${categoria.id}" selected>${categoria.nome}</option>
					    </c:when>    
					    <c:otherwise>
						<option value="${categoria.id}">${categoria.nome}</option>
					    </c:otherwise>
					</c:choose>
				</c:forEach>	  
			</select>
			<input type="hidden" name="op" value="alterar"/><br />
			<input type="submit"value="Alterar" />
		</form>
	</body>
</html>