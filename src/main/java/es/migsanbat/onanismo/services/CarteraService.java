package es.migsanbat.onanismo.services;

import java.util.List;

import es.migsanbat.onanismo.domain.Cartera;
import es.migsanbat.onanismo.domain.User;
import es.migsanbat.onanismo.repositories.CarteraRepository;
import es.migsanbat.onanismo.repositories.UserRepository;

public class CarteraService {
	private static CarteraService instancia;
	
	public static CarteraService get() {
		if(instancia==null) {
			instancia = new CarteraService();
		}
		return instancia;
	}
	public Cartera addBalance(Long num,String discordId) {
		Cartera res;
		res = findOneByDiscordId(discordId);
		res.setSaldoPropio(res.getSaldoPropio()+num);
		res = save(res);
		return res;
	}

	public Cartera save(Cartera cartera) {
		try {
			CarteraRepository.save(cartera);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return cartera;
	}
	
	
	public Cartera findOneByDiscordId(String discordId) {
		Cartera res = null;
		User aux;
		try {
			aux = UserService.get().findOneByDiscordId(discordId);
			res = aux.getCartera();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return res;
	}
}
