package es.migsanbat.onanismo.domain;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

@Entity
@Table(name = "USUARIO")
public class User implements Serializable {

	private static final long serialVersionUID = -6995855897691147578L;

	
	public User() {
		super();
	}
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id")
	private Long id;
	@Column(name="title")
	private String name;
	@Column(name="discordId")
	private String discordId;
	@OneToMany(mappedBy="usuario",fetch=FetchType.EAGER)
	private List<Onanismo> onanismos;
	@OneToOne(cascade = CascadeType.MERGE,mappedBy="usuario")
	@PrimaryKeyJoinColumn
	private Cartera cartera;
	


	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDiscordId() {
		return discordId;
	}
	public void setDiscordId(String discordId) {
		this.discordId = discordId;
	}
	public List<Onanismo> getOnanismos() {
		return onanismos;
	}
	public void setOnanismos(List<Onanismo> onanismos) {
		this.onanismos = onanismos;
	}
	public Cartera getCartera() {
		return cartera;
	}
	public void setCartera(Cartera cartera) {
		this.cartera = cartera;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	
}
