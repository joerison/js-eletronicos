package br.com.joe.vo;

import javax.servlet.http.HttpServletRequest;

public class Cliente {

	private int id;
	private String nome;
	private String cpf;
	private String email;
	private String celular;
	private String sexo;

	public Cliente() {

	}

	public Cliente(HttpServletRequest req) {
		this.id = Integer.parseInt(req.getParameter("id"));
		this.nome = req.getParameter("nome");
		this.cpf = req.getParameter("cpf");
		this.email = req.getParameter("email");
		this.celular = req.getParameter("celular");
		this.sexo = req.getParameter("sexo");
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

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getCelular() {
		return celular;
	}

	public void setCelular(String celular) {
		this.celular = celular;
	}

	public String getSexo() {
		return sexo;
	}

	public void setSexo(String sexo) {
		this.sexo = sexo;
	}
}
