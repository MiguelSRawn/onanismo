package es.migsanbat.onanismo.services;

import java.util.Date;

import org.hibernate.Session;

import es.migsanbat.onanismo.domain.Cartera;
import es.migsanbat.onanismo.domain.Onanismo;
import es.migsanbat.onanismo.domain.User;
import es.migsanbat.onanismo.repositories.OnanismoRepository;
import es.migsanbat.onanismo.util.HibernateUtil;

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
		User user = cartera.getUsuario();
		System.out.println("Cartera found: "+cartera.getId());
		if(CarteraService.get().saldoRestante(cartera)>=cost) {
			res = new Onanismo();
			res.setFecha(new Date());
			res.setUser(user);
			save(res);
		}else {
		}
		return res;
	}
	public Onanismo save(Onanismo onanismo) throws Exception {
		try {
			Session session = HibernateUtil.getSessionFactory().getCurrentSession();
	        session.beginTransaction();
			OnanismoRepository.save(onanismo,session);
			session.getTransaction().commit();
		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception(e);
		}finally {
			
		}
		return onanismo;
	}
	public Onanismo persist(Onanismo user) throws Exception {
		try {
			Session session = HibernateUtil.getSessionFactory().getCurrentSession();
	        session.beginTransaction();
			OnanismoRepository.persist(user,session);
			session.getTransaction().commit();
		}catch (Exception e) {
			e.printStackTrace();
			throw new Exception(e);
		}
		return user;
	}
	
}
