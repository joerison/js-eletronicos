<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
	<body>
	<a href="/projetoltpiv/index.jsp">Início</a><br /><br />
		<form action="categoria" method="post">
			<label for="nome">Nome: </label>
			<input type="text" id="nome" name="nome"/><br />
			<input type="hidden" name="op" value="cadastrar"/><br />
			<input type="submit"value="enviar" />
		</form>
	</body>
</html>