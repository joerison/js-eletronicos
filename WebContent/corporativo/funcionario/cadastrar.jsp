<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
	<body>
	<a href="/projetoltpiv/index.jsp">Início</a><br /><br />
		<form action="funcionario" method="post">
			<label for="login">Login: </label>
			<input type="text" id="login" name="login"/><br />
			<label for="senha">Senha: </label>
			<input type="text" id="senha" name="senha"/><br />
			<input type="hidden" name="op" value="cadastrar"/><br />
			<input type="submit"value="enviar" />
		</form>
	</body>
</html>