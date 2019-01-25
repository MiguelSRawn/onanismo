package es.migsanbat.onanismo.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import es.migsanbat.onanismo.services.CarteraService;

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
	public Integer getSaldoDado() {
		Integer res = 0;
		for(Transaccion trans:saliente) {
			res+=trans.getBalanza();
		}
		return res;
	}
	public Integer getSaldoRecibido() {
		Integer res = 0;
		for(Transaccion trans:entrante) {
			res+=trans.getBalanza();
		}
		return res;
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
				+ "		Cartera id: "+this.getId()+"\n"
				+ "		Hucha: "+this.getHucha()+"\n"
				+ "		Saldo restante: "+CarteraService.get().saldoRestante(this)+"\n"
				+ "		Saldo dado: "+this.getSaldoDado()+"\n"
				+ "		Saldo recibido: "+this.getSaldoRecibido()+"\n";
		return reply;
	}
	public Integer getSaldoUsable() {
		return this.getSaldoPropio()+this.getSaldoRecibido();
	}
	public Integer getHucha() {
		return this.getSaldoPropio()+this.getSaldoDado();
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
