package br.com.joe.vo;

import javax.servlet.http.HttpServletRequest;

import br.com.joe.negocio.CategoriaBO;

public class Produto {

	private int id;
	private String nome;
	private Double preco;
	private Categoria categoria;

	public Produto() {

	}

	public Produto(HttpServletRequest req) {
		CategoriaBO categoriaBO = new CategoriaBO();
		this.id = Integer.parseInt(req.getParameter("id"));
		this.nome = req.getParameter("nome");
		this.setPreco(Double.parseDouble(req.getParameter("preco")));
		this.categoria = categoriaBO.obterCategoriaPorId(Integer.parseInt(req.getParameter("categoria")));
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Double getPreco() {
		return preco;
	}

	public void setPreco(Double preco) {
		this.preco = preco;
	}

	public Categoria getCategoria() {
		return categoria;
	}

	public void setCategoria(Categoria categoria) {
		this.categoria = categoria;
	}

}
