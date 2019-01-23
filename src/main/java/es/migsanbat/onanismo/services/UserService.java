package es.migsanbat.onanismo.services;

import java.util.ArrayList;
import java.util.List;

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
	public List<User> findByDiscordId(String discordId) {
		List<User> res = null;
		
		try {
			res = UserRepository.findByDiscordId(discordId);
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
		res.setEntrante(new ArrayList<Transaccion>());
		res.setName(name);
		res.setOnanismos(new ArrayList<Onanismo>());
		res.setSaliente(new ArrayList<Transaccion>());
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
			UserRepository.save(user);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return user;
	}
	public User persist(User user) {
		try {
			UserRepository.persist(user);
		}catch (Exception e) {
			e.printStackTrace();
		}
		return user;
	}
	public String createReply(User user) {
		String reply ="		Nombre: "+user.getName()+"\n"
				+ "		ID: "+user.getDiscordId()+"\n"
				+ "		Hucha: "+user.getCartera().getHucha()+"\n"
				+ "		Saldo: "+CarteraService.get().saldoRestante(user.getCartera())+"\n"
				+ "		Onanismos: "+user.getOnanismos().size()+"\n";
		return reply;
	}
}
