package es.migsanbat.onanismo.domain;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "USER")
public class User implements Serializable {

	private static final long serialVersionUID = -6995855897691147578L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id")
	private Long id;
	@Column(name="title")
	private String name;
	@Column(name="discordId")
	private String discordId;
	@OneToMany(mappedBy="user")
	private List<Onanismo> onanismos;
	@Column
	private Long saldoPropio;
	@Column
	private Long saldoDado;
	@Column
	private Long saldoRecibido;
	
}
