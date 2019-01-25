package es.migsanbat.onanismo.services;

import org.hibernate.Session;

import es.migsanbat.onanismo.domain.Cartera;
import es.migsanbat.onanismo.domain.Config;
import es.migsanbat.onanismo.domain.User;
import es.migsanbat.onanismo.repositories.CarteraRepository;
import es.migsanbat.onanismo.util.HibernateUtil;

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
	
	public Integer saldoRestante(Cartera cartera) {
		Integer res = null;
		Config config = ConfigService.get().getConfig();
		res = cartera.getSaldoUsable()-cartera.getUsuario().getOnanismos().size()*config.getCost();
		return res;
	}

	public Cartera saveOrUpdate(Cartera cartera) {
		try {
			Session session = HibernateUtil.getSessionFactory().getCurrentSession();
	        session.beginTransaction();
			System.out.println("save(Cartera): \n"
					+ cartera.createReply());
			
			CarteraRepository.saveOrUpdate(cartera,session);
			session.getTransaction().commit();
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
