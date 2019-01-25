package es.migsanbat.onanismo.services;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;

import es.migsanbat.onanismo.domain.Cartera;
import es.migsanbat.onanismo.domain.Onanismo;
import es.migsanbat.onanismo.domain.Transaccion;
import es.migsanbat.onanismo.domain.User;
import es.migsanbat.onanismo.repositories.UserRepository;
import es.migsanbat.onanismo.util.BotUtil;
import es.migsanbat.onanismo.util.HibernateUtil;

public class UserService {
	private static UserService instancia;
	
	public static UserService get() {
		if(instancia==null) {
			instancia = new UserService();
		}
		return instancia;
	}
	public List<User> findByDiscordId(String discordId) {
		List<User> res = null;
		
		try {
			Session session = HibernateUtil.getSessionFactory().getCurrentSession();
	        session.beginTransaction();
			res = UserRepository.findByDiscordId(discordId,session);
			session.getTransaction().commit();
			for(User us:res) {
				System.out.println("findByDiscordId(): Usuario: "+us.getName());
			}
		} catch (Exception e) {
			
			e.printStackTrace();
		}
		return res;
	}
	public User findOneByDiscordId(String discordId) {
		User res = null;
		List<User> aux;
		try {
			System.out.println("findOneByDiscordId(): Searching user with discord id :"+discordId);
			aux = findByDiscordId(discordId);
			if(!aux.isEmpty()) {
				res =  aux.get(0);
			}else {
				System.out.println("findOneByDiscordId(): The user doesn't have an account");
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return res;
	}
	public User createAndSave(String discordId,String name) {
		User res;
		Cartera cartera;
		res = new User();
		try {
		
		
		assert discordId!=null && !discordId.equals("");
		assert name!=null && !name.equals("");
		
		
		
		res.setDiscordId(discordId);
		res.setName(name);
		res.setOnanismos(new ArrayList<Onanismo>());
		res = this.persist(res);
		
		cartera = new Cartera(res);
		res.setCartera(cartera);
		
		cartera = CarteraService.get().saveOrUpdate(cartera);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return res;
	}
	public User save(User user) {
		try {
			Session session = HibernateUtil.getSessionFactory().getCurrentSession();
	        session.beginTransaction();
			UserRepository.save(user,session);
			session.getTransaction().commit();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return user;
	}
	public User persist(User user) {
		try {
			Session session = HibernateUtil.getSessionFactory().getCurrentSession();
	        session.beginTransaction();
			UserRepository.persist(user,session);
			session.getTransaction().commit();
		}catch (Exception e) {
			e.printStackTrace();
		}
		return user;
	}
	public String createReply(User user) throws Exception {
		String reply="";
		try {
			reply ="		Nombre: "+user.getName()+"\n"
					+ "		ID: "+user.getDiscordId()+"\n"
					+ "		Hucha: "+user.getCartera().getHucha()+"\n"
					+ "		Saldo: "+CarteraService.get().saldoRestante(user.getCartera())+"\n"
					+ "		Onanismos: "+user.getOnanismos().size()+"\n";
		}catch(Exception e) {
			throw new Exception("ERROR: Wops, something failed, check the logs");
		}
		
		return reply;
	}
}
