package br.cesed.usearch.domain;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Venda implements Serializable {

	private static final long serialVersionUID = -7799369695818057571L;
	
	@Id
	private String id;
	private String idPedido;
	private double valor;
	
	
	public Venda() {
		super();
	}


	public Venda(String id, String idPedido) {
		super();
		this.id = id;
		this.idPedido = idPedido;
		
	}


	public String getId() {
		return id;
	}


	public void setId(String id) {
		this.id = id;
	}


	public String getIdPedido() {
		return idPedido;
	}


	public void setIdPedido(String idPedido) {
		this.idPedido = idPedido;
	}


	public double getValor() {
		return valor;
	}


	public void setValor(double valor) {
		this.valor = valor;
	}
}