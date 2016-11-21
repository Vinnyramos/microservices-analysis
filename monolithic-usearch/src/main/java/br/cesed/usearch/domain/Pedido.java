package br.cesed.usearch.domain;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Pedido implements Serializable {

	private static final long serialVersionUID = -7799369695818057571L;
	
	@Id
	private String id;	
	private String idUsuario;
	private String idProduto;
	
	public Pedido() {
	}

	public Pedido(String id, String idUsuario, String idProduto) {
		super();
		this.id = id;
		this.idUsuario = idUsuario;
		this.idProduto = idProduto;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getIdUsuario() {
		return idUsuario;
	}

	public void setIdUsuario(String idUsuario) {
		this.idUsuario = idUsuario;
	}

	public String getIdProduto() {
		return idProduto;
	}

	public void setIdProduto(String idProduto) {
		this.idProduto = idProduto;
	}	
}