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
	private Integer saldoPropio;
	@Column(name="saldoDado")
	private Integer saldoDado;
	@Column(name="saldoRecibido")
	private Integer saldoRecibido;
	@OneToOne
	private User usuario;
	public Cartera() {
		super();
	}
	public Cartera(User user) {
		saldoDado = 0;
		saldoPropio = 0;
		saldoRecibido = 0;
		usuario = user;
		id=user.getId();
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
	public Integer getSaldoDado() {
		return saldoDado;
	}
	public void setSaldoDado(Integer saldoDado) {
		this.saldoDado = saldoDado;
	}
	public Integer getSaldoRecibido() {
		return saldoRecibido;
	}
	public void setSaldoRecibido(Integer saldoRecibido) {
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
	public String createReply() {
		String reply ="		Usuario discordId: "+this.getUsuario().getDiscordId()+"\n"
				+ "		Usuario id: "+this.getUsuario().getId()+"\n"
				+ "		this id: "+this.getId()+"\n"
				+ "		Saldo propio: "+this.getSaldoPropio()+"\n"
				+ "		Saldo dado: "+this.getSaldoDado()+"\n";
		return reply;
	}
	public Integer getSaldoDisponible() {
		return this.getSaldoPropio()+this.getSaldoRecibido();
	}
	public Integer getHucha() {
		return this.getSaldoPropio()+this.getSaldoDado();
	}
	
}
