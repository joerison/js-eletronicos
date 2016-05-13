package br.com.joe.vo;

import javax.servlet.http.HttpServletRequest;

/**
 * @author joe
 *
 */
public class Funcionario {

	private int id;
	private String nome;
	private String cpf;
	private String email;
	private String celular;
	private String sexo;
	private String login;
	private String senha;

	public Funcionario() {
		
	}

	public Funcionario(HttpServletRequest req) {
		this.id = Integer.parseInt(req.getParameter("id"));
		this.nome = req.getParameter("nome");
		this.cpf = req.getParameter("cpf");
		this.email = req.getParameter("email");
		this.celular = req.getParameter("celular");
		this.sexo = req.getParameter("sexo");
		this.login = req.getParameter("login");
		this.senha = req.getParameter("senha");
	}

	public Funcionario(int id, String nome, String cpf, String email, String celular, String sexo, String login,
			String senha) {
		this.id = id;
		this.nome = nome;
		this.cpf = cpf;
		this.email = email;
		this.celular = celular;
		this.sexo = sexo;
		this.login = login;
		this.senha = senha;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
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

	public String getSexo() {
		return sexo;
	}

	public void setSexo(String sexo) {
		this.sexo = sexo;
	}

	public String getCelular() {
		return celular;
	}

	public void setCelular(String celular) {
		this.celular = celular;
	}

}