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
	public Onanismo fap(String discordId) throws Exception {
		Onanismo res =null;
		Integer cost = ConfigService.get().getConfig().getCost();
		Cartera cartera = CarteraService.get().findOneByDiscordId(discordId);
		if(cartera.getSaldoDisponible()>cost) {
			CarteraService.get().removeBalance(cost, discordId);
		}else {
			
		}
		
		return res;
	}
}
