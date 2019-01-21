package es.migsanbat.onanismo.services;

import java.util.ArrayList;

import es.migsanbat.onanismo.domain.Cartera;
import es.migsanbat.onanismo.domain.Onanismo;
import es.migsanbat.onanismo.domain.Transaccion;
import es.migsanbat.onanismo.domain.User;
import es.migsanbat.onanismo.repositories.UserRepository;

public class UserService {
	private static UserService instancia;
	
	public static UserService get() {
		if(instancia==null) {
			instancia = new UserService();
		}
		return instancia;
	}
	public User createAndSave(String discordId,String name) {
		User res;
		Cartera cartera;
		res = new User();
		try {
		cartera = new Cartera(res);
		
		assert discordId!=null && !discordId.equals("");
		assert name!=null && !name.equals("");
		
		
		res.setCartera(cartera);
		res.setDiscordId(discordId);
		res.setEntrante(new ArrayList<Transaccion>());
		res.setName(name);
		res.setOnanismos(new ArrayList<Onanismo>());
		res.setSaliente(new ArrayList<Transaccion>());
		
		
		res = this.save(res);
		cartera = CarteraService.get().save(cartera);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return res;
	}
	public User save(User user) {
		try {
			UserRepository.save(user);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return user;
	}
}
