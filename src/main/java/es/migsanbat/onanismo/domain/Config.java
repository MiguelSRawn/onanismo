package es.migsanbat.onanismo.domain;

import java.io.File;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Config {

	private String token;
	private String ownerId;
	private Long cost;
	
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
	public Long getCost() {
		return cost;
	}
	public void setCost(Long coste) {
		this.cost = coste;
	}
	
	
	
	

	

}
