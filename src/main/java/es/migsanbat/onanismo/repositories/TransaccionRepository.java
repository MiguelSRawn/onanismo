package es.migsanbat.onanismo.repositories;


import java.util.List;

import org.hibernate.Session;
import org.hibernate.query.Query;

import es.migsanbat.onanismo.domain.Transaccion;
import es.migsanbat.onanismo.util.BotUtil;
import es.migsanbat.onanismo.util.HibernateUtil;

public class TransaccionRepository {
	
	public static void save(Transaccion transaccion,Session session) throws Exception {
		try {
			session.save(transaccion);
		}catch (Exception e) {
			throw new Exception(e.getMessage(),e);
		}finally {
			
		}
	}

	public static void update(Transaccion transaccion,Session session) throws Exception {
		try {
			session.update(transaccion);
		}catch (Exception e) {
			throw new Exception(e.getMessage(),e);
		}finally {
			
		}
		
	}
	public static void saveOrUpdate(Transaccion transaccion,Session session) throws Exception {
		try {
			session.saveOrUpdate(transaccion);
		}catch (Exception e) {
			throw new Exception(e.getMessage(),e);
		}finally {
			
		}
		
	}
	public static List<Transaccion> findByBeneficiario(Long id, Session session) throws Exception {
		List<Transaccion> res = null;
		List<?> aux = null;
		try {
			String hql = "from Transaccion t where t.beneficiario.id = :id";
	        Query<?> query = session.createQuery(hql);
	        query.setParameter("id", id);
	        System.out.println("Executing query '"+query.toString()+"'");
	        aux = query.list();
	        res = BotUtil.get().castList(Transaccion.class, aux);
		}catch (Exception e) {
			HibernateUtil.getSessionFactory().getCurrentSession().getTransaction().rollback();
			System.out.println("WARNING: Rolled back");
			throw new Exception(e.getMessage(),e);
		}finally {
			
		}
		return res;
	}
	public static List<Transaccion> findByBenefactor(Long id, Session session) throws Exception {
		List<Transaccion> res = null;
		List<?> aux = null;
		try {
			String hql = "from Transaccion t where t.benefactor.id = :id";
	        Query<?> query = session.createQuery(hql);
	        query.setParameter("id", id);
	        System.out.println("Executing query '"+query.toString()+"'");
	        aux = query.list();
	        res = BotUtil.get().castList(Transaccion.class, aux);
		}catch (Exception e) {
			HibernateUtil.getSessionFactory().getCurrentSession().getTransaction().rollback();
			System.out.println("WARNING: Rolled back");
			throw new Exception(e.getMessage(),e);
		}finally {
			
		}
		return res;
	}
}
