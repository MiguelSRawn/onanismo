package es.migsanbat.onanismo.services;

import java.util.Date;

import es.migsanbat.onanismo.domain.Cartera;
import es.migsanbat.onanismo.domain.Onanismo;
import es.migsanbat.onanismo.domain.User;
import es.migsanbat.onanismo.repositories.OnanismoRepository;
import es.migsanbat.onanismo.repositories.UserRepository;

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
		User user;
		System.out.println("Cartera found: "+cartera.getId());
		if(cartera.getSaldoDisponible()>cost) {
			CarteraService.get().removeBalance(cost, discordId);
			user = UserService.get().findOneByDiscordId(discordId);
			res = new Onanismo();
			res.setFecha(new Date());
			res.setUser(user);
		}else {
		}
		return res;
	}
	public Onanismo save(Onanismo onanismo) {
		try {
			OnanismoRepository.save(onanismo);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return onanismo;
	}
	public Onanismo persist(Onanismo user) {
		try {
			OnanismoRepository.persist(user);
		}catch (Exception e) {
			e.printStackTrace();
		}
		return user;
	}
}
