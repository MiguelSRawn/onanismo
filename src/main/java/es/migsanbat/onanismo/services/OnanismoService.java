package es.migsanbat.onanismo.services;

import es.migsanbat.onanismo.domain.Onanismo;

public class OnanismoService {
	private static OnanismoService instancia;
	
	public static OnanismoService get() {
		if(instancia==null) {
			instancia = new OnanismoService();
		}
		return instancia;
	}
	public Onanismo fap(String discordId) {
		Onanismo res = null;
		
		return res;
	}
}
