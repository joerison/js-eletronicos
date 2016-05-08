<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<html>
<body>
	<a href="/projetoltpiv/index.jsp">Início</a>
	<br />
	<br />
	<table border="1">
		<tr>
			<td>Venda ID</td>
			<td>Cliente</td>
			<td>Funcionario</td>
			<td>Desconto R$</td>
			<td>Total R$</td>
			<td>Data</td>
		<tr>
			<c:forEach var="historicoVenda" items="${historicoVendas}">
				<tr>
					<td>${historicoVenda.id}</td>
					<td>${historicoVenda.cliente.nome}</td>
					<td>${historicoVenda.funcionario.nome}</td>
					<td>${historicoVenda.desconto}</td>
					<td>${historicoVenda.total}</td>
					<td>
						<fmt:formatDate value="${historicoVenda.data}" pattern="dd/MM/yyyy" />
					</td>
				<tr>
			</c:forEach>
	</table>
</body>
</html>
