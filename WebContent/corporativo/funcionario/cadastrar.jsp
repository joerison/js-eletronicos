<%@ page language="java" contentType="text/html; charset=iso-8859-1" pageEncoding="iso-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
	<body>
			<jsp:include page="/menu.jsp" />

<br /><br />
		<form action="funcionario" method="post">
			<label for="nome">Nome: </label>
			<input type="text" id="nome" name="nome"/><br />
			<label for="cpf">Cpf: </label>
			<input type="text" id="cpf" name="cpf"/><br />
			<label for="email">E-mail: </label>
			<input type="text" id="email" name="email"/><br />
			<label for="celular">Celular: </label>
			<input type="text" id="celular" name="celular"/><br />
			<label for="sexo">Sexo: </label>
			<input type="radio" id="sexo" name="sexo" value="M">Masculino
			<input type="radio" id="sexo"  name="sexo" value="F">Feminino<br>
			<label for="login">Login: </label>
			<input type="text" id="login" name="login"/><br />
			<label for="senha">Senha: </label>
			<input type="text" id="senha" name="senha"/><br />
			<input type="hidden" name="op" value="cadastrar"/><br />
			<input type="submit"value="Salvar" />
		</form>
	</body>
</html>