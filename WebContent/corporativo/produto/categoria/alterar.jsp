<%@ page language="java" contentType="text/html; charset=iso-8859-1" pageEncoding="iso-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
	<body>

			<jsp:include page="/menu.jsp" />

<br /><br />
		<form action="categoria" method="post">
		
			<input type="hidden" name="id" value="${categoria.id}"/>
			<label for="nome">Nome: </label>
			<input type="text" id="nome" name="nome" value="${categoria.nome}"/><br />
			<input type="hidden" name="op" value="alterar"/><br />
			<input type="submit"value="enviar" />
		</form>
	</body>
</html>