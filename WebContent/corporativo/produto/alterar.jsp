<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
	<body>

	<a href="/projetoltpiv/index.jsp">Início</a><br /><br />
		<form action="produto" method="post">
			<label for="nome">Nome: </label>
			<input type="text" id="nome" name="nome" value="${produto.nome}"/><br />
			<label for="preco">Preco: </label>
			<input type="text" id="preco" name="preco" value="${produto.preco}"/><br />

			<select name="categoria">
			<c:forEach var="categoria" items="${categorias}">
			  <option value="${categoria.id}">${categoria.nome}</option>
			</c:forEach>	  
			</select>
			
			<input type="hidden" name="op" value="alterar"/><br />
			<input type="submit"value="enviar" />
		</form>
	</body>
</html>