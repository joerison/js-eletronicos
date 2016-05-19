<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<html>
<body>
	<jsp:include page="/corporativo/menu.jsp" />
	<br />
	<br />
	<form action="/projetoltpiv/corporativo/relatorio">
		<label for="dtInicio">Data inicial:</label>
		<input type="text"  value="${dtInicio}" name="dtInicio" id="dtInicio" /> 
		<label for="dtFim">Data	final:</label> 
		<input type="text" value="${dtFim}" name="dtFim" id="dtFim" /> 
		<input type="hidden" name="op" value="relatorioVendas" /> <br />
		<br /> <input type="submit" value="Emitir Relatorio" />
	</form>
	<br />
	<c:if test="${historicoVendas!=null}">
		<table border="1">
			<tr>
				<th>Venda ID</th>
				<th>Cliente</th>
				<th>Funcionario</th>
				<th>Desconto R$</th>
				<th>Total R$</th>
				<th>Data</th>
			<tr>
				<c:forEach var="historicoVenda" items="${historicoVendas}">
					<tr>
						<td>${historicoVenda.id}</td>
						<td>${historicoVenda.cliente.nome}</td>
						<td>${historicoVenda.funcionario.nome}</td>
						<td>${historicoVenda.desconto}</td>
						<td>${historicoVenda.total}</td>
						<td><fmt:formatDate value="${historicoVenda.data}"
								pattern="dd/MM/yyyy" /></td>
					<tr>
				</c:forEach>
		</table>
	</c:if>
</body>
</html>