package br.com.joe.vo;

import java.sql.Date;
import java.util.ArrayList;

public class Venda {

	private int id;
	private Cliente cliente;
	private Funcionario funcionario;
	private ArrayList<ItemVenda> itensVenda = new ArrayList<ItemVenda>();
	private Double desconto = 0.0;
	private Double total = 0.0;
	private Date data;

	public Venda() {
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

	public Date getData() {
		return data;
	}

	public void setData(Date data) {
		this.data = data;
	}

}
