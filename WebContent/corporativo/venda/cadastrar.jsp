<%@ page language="java" contentType="text/html; charset=iso-8859-1"
	pageEncoding="iso-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%! double subtotal = 0.0; %>
<html>
	<body>
			<jsp:include page="/corporativo/menu.jsp" />
<br /><br />
		
		<form action="/js-eletronicos/corporativo/venda" method="post">
			<label for="clientes">Clientes: </label>
			<select name="clienteId">
			<c:forEach var="cliente" items="${clientes_disponiveis}">
			  <option value="${cliente.id}">${cliente.nome}</option>
			</c:forEach>	  
			</select>
			<input type="hidden" name="op" value="adicionarCliente"/>
			<input type="submit"value="Selecionar" />
		</form>
		
		<form action="/js-eletronicos/corporativo/venda" method="post">
			<label for="produtos">Produtos: </label>
			<select name="produtoId">
			<c:forEach var="produto" items="${produtos_disponiveis}">
			  <option value="${produto.id}">${produto.nome}</option>
			</c:forEach>	  
			</select>
			<label for="qtd">Qtd: </label>
			<input type="number" id="qtd" value="1" name="qtd"/>
			<input type="hidden" name="op" value="adicionarItem"/>
			<input type="submit"value="Adicionar" />
		</form>
		
		Cliente: ${venda.cliente.nome} <br/>
		<form action="/js-eletronicos/corporativo/venda" method="post">
			<table border=1>
				<tr>
					<td colspan="5" style="text-align: center">Itens Incluidos</td>
				</tr>
				<tr>
				<th>Nome</th>
				<th>Qtd</th>
				<th>Und R$</th>
				<th>Subtotal</th>
				<th>Remover</th>
				</tr>
				<c:set var="contador" value="0"/>
				<c:forEach var="itemVenda" items="${venda.itensVenda}">
					<tr>
						<td colspan="1">${itemVenda.produto.nome}</td>
						<td colspan="1">${itemVenda.qtd}</td>
						<td colspan="1">${itemVenda.produto.preco}</td>
						<td colspan="1">${itemVenda.total}</td>
						<td colspan="1"><a href="/js-eletronicos/corporativo/venda?op=excluirItem&produtoIndex=${contador}">Remover</a></td>
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