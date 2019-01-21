package es.migsanbat.onanismo.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
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
	private User beneficiario;
	@ManyToOne()
	private User benefactor;
	@Column(name="balanza")
	private Long balanza;
	public Transaccion() {
		super();
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public User getBeneficiario() {
		return beneficiario;
	}
	public void setBeneficiario(User beneficiario) {
		this.beneficiario = beneficiario;
	}
	public User getBenefactor() {
		return benefactor;
	}
	public void setBenefactor(User benefactor) {
		this.benefactor = benefactor;
	}
	public Long getBalanza() {
		return balanza;
	}
	public void setBalanza(Long balanza) {
		this.balanza = balanza;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	
	
}
