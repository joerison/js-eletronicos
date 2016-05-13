package br.com.joe.vo;

import javax.servlet.http.HttpServletRequest;

import br.com.joe.negocio.ProdutoBO;

public class ItemVenda {

	private Produto produto;
	private int qtd;
	private Double total;

	public ItemVenda() {

	}

	public ItemVenda(HttpServletRequest req) {
		ProdutoBO produtoBO = new ProdutoBO();
		this.produto = produtoBO.obterProdutoPorId(Integer.parseInt(req.getParameter("produtoId")));
		this.qtd = Integer.parseInt(req.getParameter("qtd"));
		this.total = this.produto.getPreco() * this.qtd;
	}

	public Produto getProduto() {
		return produto;
	}

	public void setProduto(Produto produto) {
		this.produto = produto;
	}

	public int getQtd() {
		return qtd;
	}

	public void setQtd(int qtd) {
		this.qtd = qtd;
	}

	public Double getTotal() {
		return total;
	}

	public void setTotal(Double total) {
		this.total = total;
	}

}
