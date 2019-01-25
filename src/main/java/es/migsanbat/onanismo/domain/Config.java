package es.migsanbat.onanismo.domain;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Config {

	private String token;
	private String ownerId;
	private Integer cost;
	private boolean mantenimiento;
	
	public Config() {
		
	}
	public Config(String token) {
		super();
		this.token = token;
	}

	@XmlElement(name = "token")
	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}
	@XmlElement(name = "ownerId")
	public String getOwnerId() {
		return ownerId;
	}
	public void setOwnerId(String ownerId) {
		this.ownerId = ownerId;
	}
	@XmlElement(name = "cost")
	public Integer getCost() {
		return cost;
	}
	public void setCost(Integer coste) {
		this.cost = coste;
	}
	@XmlElement(name = "mantenimiento")
	public boolean isMantenimiento() {
		return mantenimiento;
	}
	public void setMantenimiento(boolean mantenimiento) {
		this.mantenimiento = mantenimiento;
	}
	
	
	
	
	

	

}
