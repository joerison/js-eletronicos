<%@ page language="java" contentType="text/html; charset=iso-8859-1"
	pageEncoding="iso-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
	<body>
			<jsp:include page="/menu.jsp" />
<br /><br />
		<form action="produto" method="post">
			<label for="nome">Nome: </label>
			<input type="hidden" value=0 name="id"/>
			<input type="text" id="nome" name="nome"/><br />
			<label for="preco">Preco: </label>
			<input type="text" id="preco" name="preco"/><br />
			
			<select name="categoria">
			<c:forEach var="categoria" items="${categorias}">
			  <option value="${categoria.id}">${categoria.nome}</option>
			</c:forEach>	  
			</select>
			
			<input type="hidden" name="op" value="cadastrar"/><br />
			<input type="submit"value="Cadastrar" />
		</form>
	</body>
</html>