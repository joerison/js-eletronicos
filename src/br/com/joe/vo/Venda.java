package br.com.joe.vo;

import java.util.ArrayList;

import br.com.joe.ItemVenda;

public class Venda {

	private int id;
	private Cliente cliente;
	private Funcionario funcionario;
	private ArrayList<ItemVenda> itensVenda = new ArrayList<ItemVenda>();
	private Double desconto = 0.0;
	private Double total = 0.0;

	public Venda() {
	}

	public Venda(int id, Cliente cliente, Funcionario funcionario, ArrayList<ItemVenda> itensVenda, Double desconto,
			Double total) {
		this.id = id;
		this.cliente = cliente;
		this.funcionario = funcionario;
		this.itensVenda = itensVenda;
		this.desconto = desconto;
		this.total = total;
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

	public ArrayList<ItemVenda> getItensVenda() {
		return itensVenda;
	}

	public void setItensVenda(ArrayList<ItemVenda> itensVenda) {
		this.itensVenda = itensVenda;
	}

}
