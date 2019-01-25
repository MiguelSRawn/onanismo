package es.migsanbat.onanismo.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "TRANSACCION")
public class Transaccion implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 4863533435254179251L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id")
	private Long id;
	@ManyToOne()
	private Cartera beneficiario;
	@ManyToOne()
	private Cartera benefactor;
	@Column(name="balanza")
	private Integer balanza;
	public Transaccion() {
		super();
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Cartera getBeneficiario() {
		return beneficiario;
	}
	public void setBeneficiario(Cartera beneficiario) {
		this.beneficiario = beneficiario;
	}
	public Cartera getBenefactor() {
		return benefactor;
	}
	public void setBenefactor(Cartera benefactor) {
		this.benefactor = benefactor;
	}
	public Integer getBalanza() {
		return balanza;
	}
	public void setBalanza(Integer balanza) {
		this.balanza = balanza;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	
	
}
