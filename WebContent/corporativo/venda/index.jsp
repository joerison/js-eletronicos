<html>
<body>
			<jsp:include page="/menu.jsp" />
<br /><br />

	<form action="/projetoltpiv/corporativo/venda?op=consultar" method="POST">
		<label for="vendaId">Venda: </label> 
		<input type="text" name="vendaId" id="vendaId" /> 
		<input type="submit" value="Consultar">
	</form>

	<a href="/projetoltpiv/corporativo/venda?op=preparaVenda">Cadastrar Venda</a>

</body>
</html>