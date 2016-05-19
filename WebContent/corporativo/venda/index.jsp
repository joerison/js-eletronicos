<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<html>
<body>
			<jsp:include page="/corporativo/menu.jsp" />
<br /><br />
<a href="/projetoltpiv/corporativo/venda?op=preparaVenda">Cadastrar Venda</a>
<br /><br />
	<form action="/projetoltpiv/corporativo/venda">
		<label for="dtInicio">Data inicial:</label>
		<input type="text"  value="${dtInicio}" name="dtInicio" id="dtInicio" /> 
		<label for="dtFim">Data	final:</label> 
		<input type="text" value="${dtFim}" name="dtFim" id="dtFim" /> 
		<input type="hidden" name="op" value="consultarVendas" /> <br />
		<br /> <input type="submit" value="Pesquisar" />
	</form>
	
	<c:if test="${vendas!=null}">
		<table border="1">
			<tr>
				<th>Venda ID</th>
				<th>Cliente</th>
				<th>Funcionario</th>
				<th>Desconto R$</th>
				<th>Total R$</th>
				<th>Data</th>
				<th>Consultar</th>
				<th>Excluir</th>
			<tr>
				<c:forEach var="venda" items="${vendas}">
					<tr>
						<td>${venda.id}</td>
						<td>${venda.cliente.nome}</td>
						<td>${venda.funcionario.nome}</td>
						<td>${venda.desconto}</td>
						<td>${venda.total}</td>
						<td><fmt:formatDate value="${venda.data}"
								pattern="dd/MM/yyyy" /></td>
						<td><a href="/projetoltpiv/corporativo/venda?op=consultarVenda&vendaId=${venda.id}">Consultar</a></td>
						<td><a href="/projetoltpiv/corporativo/venda?op=excluirVenda&vendaId=${venda.id}">Excluir</a></td>
					<tr>
				</c:forEach>
		</table>
	</c:if>


</body>
</html>