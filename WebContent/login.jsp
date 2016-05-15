<%@ page language="java" contentType="text/html; charset=iso-8859-1"
	pageEncoding="iso-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet" href="/projetoltpiv/css/estilo.css">
<title>Autenticação</title>
</head>
<body>
	<c:if test="${not empty mensagem}">
		<span class="erro">${mensagem}</span>
	</c:if>

	<form action="/projetoltpiv/validacaoDeAcesso" method="post">
		<label for="login">Login: </label> <input type="text" id="login"
			name="login" /> <br /> <label for="senha">Senha: </label> <input
			type="password" id="senha" name="senha" /> <br /> <input
			type="submit" value="Logar" />
	</form>
</body>
</html>