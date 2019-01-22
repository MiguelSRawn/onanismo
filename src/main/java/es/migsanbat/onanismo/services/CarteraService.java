package es.migsanbat.onanismo.services;

import es.migsanbat.onanismo.domain.Cartera;
import es.migsanbat.onanismo.domain.User;
import es.migsanbat.onanismo.repositories.CarteraRepository;
public class CarteraService {
	private static CarteraService instancia;
	
	public static CarteraService get() {
		if(instancia==null) {
			instancia = new CarteraService();
		}
		return instancia;
	}
	public Cartera addBalance(Integer num,String discordId) {
		Cartera res;
		System.out.println("addBalance(): "+num.toString()+", "+discordId);
		res = findOneByDiscordId(discordId);
		res.setSaldoPropio(res.getSaldoPropio()+num);
		res = saveOrUpdate(res);
		return res;
	}
	public Cartera removeBalance(Integer num,String discordId) throws Exception {
		Cartera res;
		Integer saldoPropio, saldoRecibido, subAux=0;
		System.out.println("removeBalance(): "+num.toString()+", "+discordId);
		res = findOneByDiscordId(discordId);
		saldoRecibido = res.getSaldoRecibido();
		saldoPropio = res.getSaldoPropio();
		saldoRecibido = saldoRecibido-num;
		if(saldoRecibido<0) {
			subAux = Math.abs(saldoRecibido);
			saldoRecibido = 0;
		}
		if(subAux>0) {
			saldoPropio = saldoPropio-subAux;
		}
		if(saldoPropio<0) {
			throw new Exception("Not enought funds");
		}
		res.setSaldoRecibido(saldoRecibido);
		res.setSaldoPropio(saldoPropio);
		res = saveOrUpdate(res);
		return res;
	}

	public Cartera saveOrUpdate(Cartera cartera) {
		try {
			System.out.println("save(Cartera): \n"
					+ cartera.createReply());
			
			CarteraRepository.saveOrUpdate(cartera);
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
