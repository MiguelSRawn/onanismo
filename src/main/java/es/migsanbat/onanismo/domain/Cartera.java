package es.migsanbat.onanismo.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "CARTERA")
public class Cartera implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -8015550137324407259L;
	
	@Id
	@Column(name="id")
	private Long id;
	@Column(name="saldoPropio")
	private Integer saldoPropio;
	@OneToOne
	private User usuario;
	@OneToMany(mappedBy="beneficiario")
	private List<Transaccion> entrante;
	@OneToMany(mappedBy="benefactor")
	private List<Transaccion> saliente;
	public Cartera() {
		super();
	}
	public Cartera(User user) {
		saldoPropio = 0;
		usuario = user;
		id=user.getId();

		entrante = new ArrayList<Transaccion>();
		saliente = new ArrayList<Transaccion>();
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Integer getSaldoPropio() {
		return saldoPropio;
	}
	public void setSaldoPropio(Integer saldoPropio) {
		this.saldoPropio = saldoPropio;
	}
	
	public User getUsuario() {
		return usuario;
	}
	public void setUsuario(User usuario) {
		this.usuario = usuario;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	
	public List<Transaccion> getEntrante() {
		return entrante;
	}
	public void setEntrante(List<Transaccion> entrante) {
		this.entrante = entrante;
	}
	public List<Transaccion> getSaliente() {
		return saliente;
	}
	public void setSaliente(List<Transaccion> saliente) {
		this.saliente = saliente;
	}
	
	
}
