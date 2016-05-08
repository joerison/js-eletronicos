package br.com.joe;

import br.com.joe.vo.Venda;

public class Carrinho {

	private static Carrinho carrinho = new Carrinho();
	private Venda venda = new Venda();

	private Carrinho() {
	}

	public static Carrinho getCarrinho() {
		return carrinho;
	}

	public static void setCarrinho(Carrinho carrinho) {
		Carrinho.carrinho = carrinho;
	}

	public Venda getVenda() {
		return venda;
	}

	public void setVenda(Venda venda) {
		this.venda = venda;
	}

}
