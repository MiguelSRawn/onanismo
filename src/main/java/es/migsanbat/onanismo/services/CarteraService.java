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
		System.out.println("addBalance(): "+num.toString()+", "+discordId);
		res = findOneByDiscordId(discordId);
		res.setSaldoPropio(res.getSaldoPropio()+num);
		res = save(res);
		return res;
	}

	public Cartera save(Cartera cartera) {
		Cartera res = null;
		try {
			System.out.println("save(Cartera): \n"
					+ "		Usuario discordId: "+cartera.getUsuario().getDiscordId()+"\n"
					+ "		Usuario id: "+cartera.getUsuario().getId()+"\n"
					+ "		Cartera id: "+cartera.getId()+"\n"
					+ "		Saldo propio: "+cartera.getSaldoPropio()+"\n"
					+ "		Saldo dado: "+cartera.getSaldoDado()+"\n");
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
			System.out.println("findOneByDiscordId(): "+discordId);
			aux = UserService.get().findOneByDiscordId(discordId);
			res = aux.getCartera();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return res;
	}
}
