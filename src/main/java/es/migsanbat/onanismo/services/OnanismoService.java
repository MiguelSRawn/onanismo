package es.migsanbat.onanismo.services;

import es.migsanbat.onanismo.domain.Cartera;
import es.migsanbat.onanismo.domain.Onanismo;
import es.migsanbat.onanismo.domain.User;

public class OnanismoService {
	private static OnanismoService instancia;
	
	public static OnanismoService get() {
		if(instancia==null) {
			instancia = new OnanismoService();
		}
		return instancia;
	}
	public Onanismo fap(String discordId) {
		Onanismo res =null;
		Cartera cartera = CarteraService.get().findOneByDiscordId(discordId);
		if(cartera.getSaldoDisponible()>ConfigService.get().getConfig().getCost()) {
			
		}
		
		return res;
	}
}
