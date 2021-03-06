package es.migsanbat.onanismo.services;

import java.util.List;

import org.hibernate.Session;

import es.migsanbat.onanismo.domain.Cartera;
import es.migsanbat.onanismo.domain.Transaccion;
import es.migsanbat.onanismo.domain.User;
import es.migsanbat.onanismo.repositories.TransaccionRepository;
import es.migsanbat.onanismo.util.HibernateUtil;


public class TransaccionService {
	private static TransaccionService instancia;
	
	public static TransaccionService get() {
		if(instancia==null) {
			instancia = new TransaccionService();
		}
		return instancia;
	}
	public Transaccion createAndSave(String beneficiario,String benefactor,Integer balanza) throws Exception {
		Transaccion res;
		try {
			User beneficiarioU = UserService.get().findOneByDiscordId(beneficiario);
			User benefactorU = UserService.get().findOneByDiscordId(benefactor);
			res = new Transaccion();
			res.setBalanza(balanza);
			res.setBenefactor(benefactorU.getCartera());
			res.setBeneficiario(beneficiarioU.getCartera());
		
			save(res);
		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception("ERROR: Wops, something failed, check the logs");
		}
		
		
		return res;
	}
	public Transaccion save(Transaccion transaccion) throws Exception{
		try {
			Session session = HibernateUtil.getSessionFactory().getCurrentSession();
	        session.beginTransaction();
			System.out.println("save(Transaccion): \n");
			TransaccionRepository.save(transaccion,session);
			session.getTransaction().commit();
		}catch (Exception e) {
			e.printStackTrace();
			throw new Exception("ERROR: Wops, something failed, check the logs");
		}
		return transaccion;
	}
	@Deprecated
	public Transaccion saveOrUpdate(Transaccion transaccion) throws Exception {
		try {
			Session session = HibernateUtil.getSessionFactory().getCurrentSession();
	        session.beginTransaction();
			System.out.println("save(Transaccion): \n");
//					+ this.createReply(transaccion));
			
			TransaccionRepository.saveOrUpdate(transaccion,session);
			session.getTransaction().commit();
		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception("ERROR: Wops, something failed, check the logs");
		}
		return transaccion;
	}
	public List<Transaccion> findByBeneficiario(Cartera beneficiario) throws Exception {
		List<Transaccion> res = null;
		try {
			Session session = HibernateUtil.getSessionFactory().getCurrentSession();
	        session.beginTransaction();
			System.out.println("List: findByBeneficiario(): "+beneficiario.getUsuario().getDiscordId());
			
			res = TransaccionRepository.findByBeneficiario(beneficiario.getId(), session);
			session.getTransaction().commit();
		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception("ERROR: Wops, something failed, check the logs");
		}
		return res;
	}
	public List<Transaccion> findByBenefactor(Cartera beneficiario) throws Exception {
		List<Transaccion> res = null;
		try {
			Session session = HibernateUtil.getSessionFactory().getCurrentSession();
	        session.beginTransaction();
			System.out.println("List: findByBenefactor(): "+beneficiario.getUsuario().getDiscordId());
			
			res = TransaccionRepository.findByBenefactor(beneficiario.getId(), session);
			session.getTransaction().commit();
		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception("ERROR: Wops, something failed, check the logs");
		}
		return res;
	}
}
