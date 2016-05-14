<%@ page language="java" contentType="text/html; charset=iso-8859-1"
	pageEncoding="iso-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%! double subtotal = 0.0; %>
<html>
	<body>
			<jsp:include page="/menu.jsp" />
<br /><br />
	
	<a href="/projetoltpiv/corporativo/venda?op=selecionarItens">selecionar item</a><br/>
	<a href="/projetoltpiv/corporativo/venda?op=selecionarCliente">selecionar Cliente</a><br/>
		Cliente: ${venda.cliente.nome} <br/>
		<form action="/projetoltpiv/corporativo/venda" method="post">
			<table border=1>
				<tr>
					<td colspan="5" style="text-align: center">Itens selecionados</td>
				</tr>
				<tr>
				<td>Nome</td>
				<td>Qtd</td>
				<td>Und R$</td>
				<td>Subtotal</td>
				<td>Remover</td>
				</tr>
				<c:set var="contador" value="0"/>
				<c:forEach var="itemVenda" items="${venda.itensVenda}">
					<tr>
						<td colspan="1">${itemVenda.produto.nome}</td>
						<td colspan="1">${itemVenda.qtd}</td>
						<td colspan="1">${itemVenda.produto.preco}</td>
						<td colspan="1">${itemVenda.total}</td>
						<td colspan="1"><a href="/projetoltpiv/corporativo/venda?op=excluirItem&produtoIndex=${contador}">Remover</a></td>
					</tr>
					<c:set var="contador" value="${count + 1}"/>
					<c:set var="total" value="${itemVenda.total + total}"/>
				</c:forEach>
				<tr>
				<td colspan=5>Total: ${total} <br/></td>
				</tr>
			</table>
			
			<label for="desconto">Desconto: </label>
			<input type="text" id="desconto" value="0.0"name="desconto"/>
			<input type="hidden" name="op" value="salvarVenda"/><br />
			<input type="submit"value="Salvar" />
		</form>
	</body>
</html>