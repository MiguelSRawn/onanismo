package es.migsanbat.onanismo.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
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
	private Long saldoPropio;
	@Column(name="saldoDado")
	private Long saldoDado;
	@Column(name="saldoRecibido")
	private Long saldoRecibido;
	@OneToOne
	private User usuario;
	public Cartera() {
		super();
	}
	public Cartera(User user) {
		saldoDado = 0l;
		saldoPropio = 0l;
		saldoRecibido = 0l;
		usuario = user;
		id=user.getId();
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Long getSaldoPropio() {
		return saldoPropio;
	}
	public void setSaldoPropio(Long saldoPropio) {
		this.saldoPropio = saldoPropio;
	}
	public Long getSaldoDado() {
		return saldoDado;
	}
	public void setSaldoDado(Long saldoDado) {
		this.saldoDado = saldoDado;
	}
	public Long getSaldoRecibido() {
		return saldoRecibido;
	}
	public void setSaldoRecibido(Long saldoRecibido) {
		this.saldoRecibido = saldoRecibido;
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
	
	
}
