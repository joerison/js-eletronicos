<%@ page language="java" contentType="text/html; charset=iso-8859-1" pageEncoding="iso-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
	<body>
	<a href="/projetoltpiv/index.jsp">Início</a><br /><br />
	
	<a href="/projetoltpiv/corporativo/venda?op=selecionarItens">selecionar item</a><br/>
	<a href="/projetoltpiv/corporativo/venda?op=selecionarCliente">selecionar Cliente</a><br/>
		Cliente: ${cliente.nome} <br/>
		<form action="/projetoltpiv/corporativo/venda" method="post">
			<table>
				<c:set var="contador" value="0"/>
				<c:forEach var="item" items="${itensCarrinho}">
					<tr>
						<td colspan="1"><input type="checkbox" name="${categoria.nome}"
							value="${item.produto.id}">${item.produto.nome}</td>
						<td colspan="1"><a href="/projetoltpiv/corporativo/venda?op=excluirItem&produtoIndex=${contador}">Remover</a></td>
					</tr>
					<c:set var="contador" value="${count + 1}"/>
				</c:forEach>
			</table>
			<label for="desconto">Desconto: </label>
			<input type="text" id="desconto" value="0.0"name="desconto"/>
			<input type="hidden" name="op" value="salvarVenda"/><br />
			<input type="submit"value="Salvar" />
		</form>
	</body>
</html>