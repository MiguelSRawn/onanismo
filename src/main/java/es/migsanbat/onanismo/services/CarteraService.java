package es.migsanbat.onanismo.services;

import java.util.List;

import org.hibernate.Session;

import es.migsanbat.onanismo.domain.Cartera;
import es.migsanbat.onanismo.domain.Config;
import es.migsanbat.onanismo.domain.Transaccion;
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
	public Cartera addBalance(Integer num,String discordId) throws Exception {
		Cartera res;
		try {
			
			System.out.println("addBalance(): "+num.toString()+", "+discordId);
			res = findOneByDiscordId(discordId);
			res.setSaldoPropio(res.getSaldoPropio()+num);
			res = saveOrUpdate(res);
		}catch (Exception e) {
			throw new Exception(e);
		}
		
		return res;
	}
	
	public Integer saldoRestante(Cartera cartera) throws Exception {
		Integer res = null;
		Config config = ConfigService.get().getConfig();
		res = CarteraService.get().getSaldoUsable(cartera)-cartera.getUsuario().getOnanismos().size()*config.getCost();
		return res;
	}

	public Cartera saveOrUpdate(Cartera cartera) throws Exception {
		try {
			Session session = HibernateUtil.getSessionFactory().getCurrentSession();
	        session.beginTransaction();
			System.out.println("save(Cartera): \n"
					+ this.createReply(cartera));
			
			CarteraRepository.saveOrUpdate(cartera,session);
			session.getTransaction().commit();
		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception("ERROR: Wops, something failed, check the logs");
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
	public Integer getSaldoUsable(Cartera cartera) throws Exception {
		return cartera.getSaldoPropio()+this.getSaldoRecibido(cartera);
	}
	public Integer getHucha(Cartera cartera) throws Exception {
		return cartera.getSaldoPropio()+this.getSaldoDado(cartera);
	}
	public Integer getSaldoDado(Cartera cartera) throws Exception {
		Integer res = 0;
		List<Transaccion> saliente = TransaccionService.get().findByBeneficiario(cartera);
		for(Transaccion trans:saliente) {
			res+=trans.getBalanza();
		}
		return res;
	}
	public Integer getSaldoRecibido(Cartera cartera) throws Exception {
		Integer res = 0;
		List<Transaccion> entrante = TransaccionService.get().findByBeneficiario(cartera);
		for(Transaccion trans:entrante) {
			res+=trans.getBalanza();
		}
		return res;
	}
	public String createReply(Cartera cartera) throws Exception {
		String reply ="		Usuario discordId: "+cartera.getUsuario().getDiscordId()+"\n"
				+ "		Usuario id: "+cartera.getUsuario().getId()+"\n"
				+ "		Cartera id: "+cartera.getId()+"\n"
				+ "		Hucha: "+CarteraService.get().getHucha(cartera)+"\n"
				+ "		Saldo restante: "+this.saldoRestante(cartera)+"\n"
				+ "		Saldo dado: "+CarteraService.get().getSaldoDado(cartera)+"\n"
				+ "		Saldo recibido: "+CarteraService.get().getSaldoRecibido(cartera)+"\n";
		return reply;
	}
}
