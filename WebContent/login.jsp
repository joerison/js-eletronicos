<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
        <title>Autenticação</title>
    </head>
    <body>
        <form action="/projetoltpiv/validacaoDeAcesso" method="post">
	        <label for="login">Login: </label>
	        <input type="text" id="login" name="login"/>
	        <label for="senha">Login: </label>
	        <input type="password" id="senha" name="senha"/> <br />
	        <input type="submit" value="Logar"/>
        </form>
    </body>
</html>