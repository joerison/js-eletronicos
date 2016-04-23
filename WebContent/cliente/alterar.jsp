<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
	<body>
		<form action="cliente" method="post">
			<label for="nome">Nome: </label>
			<input type="text" id="nome" name="nome" value="${cliente.nome}"/><br />
			<label for="idade">Idade: </label>
			<input type="text" id="idade" name="idade" value="${cliente.idade}"/><br />
			<input type="hidden" name="op" value="alterar"/><br />
			<input type="submit"value="enviar" />
		</form>
	</body>
</html>