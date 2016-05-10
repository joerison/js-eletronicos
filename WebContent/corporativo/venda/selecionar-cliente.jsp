<%@ page language="java" contentType="text/html; charset=iso-8859-1" pageEncoding="iso-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
	<body>
			<jsp:include page="/menu.jsp" />
<br /><br />
		<form action="/projetoltpiv/corporativo/venda" method="post">
			<select name="clienteId">
			<c:forEach var="cliente" items="${clientes}">
			  <option value="${cliente.id}">${cliente.nome}</option>
			</c:forEach>	  
			</select>
			<input type="hidden" name="op" value="adicionarCliente"/><br />
			<input type="submit"value="enviar" />
		</form>
	</body>
</html>