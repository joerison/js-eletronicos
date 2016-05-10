<html>
<body>
			<jsp:include page="/menu.jsp" />
<br /><br />

	<form action="/projetoltpiv/corporativo/relatorio">
		<label for="dtInicio">Data inicial:</label>
		<input type="date" name="dtInicio" id="dtInicio" />
		<label for="dtFim">Data final:</label>
		<input type="date" name="dtFim" id="dtFim" />
		<input type="hidden" name="op" value="relatorioVendas" />
		<input type="submit" value="Relatorio de Vendas"/>
	</form>
</body>
</html>