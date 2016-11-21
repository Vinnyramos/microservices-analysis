package br.cesed.usearch.domain;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Usuario implements Serializable {

	private static final long serialVersionUID = -7799369695818057571L;
	
	@Id
	private String id;
	private String nome;
	private String endereco;
	private String login;
	private String senha;
	
	public Usuario() {
	}

	public Usuario(String id, String nome, String endereco, String login, String senha) {
		super();
		this.id = id;
		this.nome = nome;
		this.endereco = endereco;
		this.login = login;
		this.senha = senha;
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

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String name) {
		this.nome = name;
	}

	public String getEndereco() {
		return endereco;
	}

	public void setEndereco(String address) {
		this.endereco = address;
	}

}
