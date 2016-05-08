<%@ page language="java" contentType="text/html; charset=iso-8859-1" pageEncoding="iso-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
	<body>
	<a href="/projetoltpiv/index.jsp">Início</a><br /><br />
		<form action="/projetoltpiv/corporativo/venda" method="post">
			<select name="produtoId">
			<c:forEach var="produto" items="${produtos}">
			  <option value="${produto.id}">${produto.nome}</option>
			</c:forEach>	  
			</select>
			<label for="qtd">Qtd: </label>
			<input type="number" id="qtd" name="qtd"/>
			<input type="hidden" name="op" value="adicionarItem"/><br />
			<input type="submit"value="enviar" />
		</form>
	</body>
</html>