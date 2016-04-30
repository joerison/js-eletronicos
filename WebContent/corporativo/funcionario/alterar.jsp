<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
	<body>
	<a href="/projetoltpiv/index.jsp">Início</a><br /><br />
		<form action="funcionario" method="post">
		
		
			<label for="nome">Nome: </label>
			<input type="text" id="nome" name="nome" value="${funcionarioEdicao.nome}"/><br />
			<label for="cpf">Cpf: </label>
			<input type="text" id="cpf" name="cpf" value="${funcionarioEdicao.cpf}"/><br />
			<label for="email">E-mail: </label>
			<input type="text" id="email" name="email" value="${funcionarioEdicao.email}"/><br />
			<label for="celular">Celular: </label>
			<input type="text" id="celular" name="celular" value="${funcionarioEdicao.celular}"/><br />
			<label for="login">Sexo: </label>
			<input type="text" id="sexo" name="sexo" value="${funcionarioEdicao.sexo}"/><br />
			<label for="login">Login: </label>
			<input type="text" id="login" name="login" value="${funcionarioEdicao.login}"/><br />
			<label for="senha">Senha: </label>
			<input type="text" id="senha" name="senha" value="${funcionarioEdicao.senha}"/><br />
			<input type="hidden" name="op" value="alterar"/><br />
			<input type="submit"value="Alterar" />
		</form>
	</body>
</html>