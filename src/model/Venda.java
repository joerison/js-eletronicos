package model;

import java.util.ArrayList;

public class Venda {

	private int id;
	private Cliente cliente;
	private Funcionario funcionario;
	private ArrayList<Produto> produtos;
	private Double desconto;
	private Double total;
	private Double totalComDesconto;

	public Venda() {

	}

	public Venda(int id, Cliente cliente, Funcionario funcionario, ArrayList<Produto> produtos, Double desconto,
			Double total, Double totalComDesconto) {
		this.id = id;
		this.cliente = cliente;
		this.funcionario = funcionario;
		this.produtos = produtos;
		this.desconto = desconto;
		this.total = total;
		this.totalComDesconto = totalComDesconto;
	}

	public Double getDesconto() {
		return desconto;
	}

	public void setDesconto(Double desconto) {
		this.desconto = desconto;
	}

	public Double getTotal() {
		return total;
	}

	public void setTotal(Double total) {
		this.total = total;
	}

	public Double getTotalComDesconto() {
		return totalComDesconto;
	}

	public void setTotalComDesconto(Double totalComDesconto) {
		this.totalComDesconto = totalComDesconto;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public Funcionario getFuncionario() {
		return funcionario;
	}

	public void setFuncionario(Funcionario funcionario) {
		this.funcionario = funcionario;
	}

	public ArrayList<Produto> getProdutos() {
		return produtos;
	}

	public void setProdutos(ArrayList<Produto> produtos) {
		this.produtos = produtos;
	}

}
