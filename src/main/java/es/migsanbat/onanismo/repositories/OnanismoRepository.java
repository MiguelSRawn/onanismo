package es.migsanbat.onanismo.repositories;


import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.query.Query;

import es.migsanbat.onanismo.domain.DBTest;
import es.migsanbat.onanismo.domain.Onanismo;
import es.migsanbat.onanismo.domain.User;
import es.migsanbat.onanismo.util.BotUtil;
import es.migsanbat.onanismo.util.HibernateUtil;

public class OnanismoRepository {
	
	public static void save(Onanismo user,Session session) throws Exception {
		try {
			session.save(user);
		}catch (Exception e) {
			HibernateUtil.getSessionFactory().getCurrentSession().getTransaction().rollback();
			throw new Exception(e.getMessage(),e);
		}finally {
			
		}
	}

	public static void persist(Onanismo user,Session session) throws Exception {
		try {
			session.persist(user);
		}catch (Exception e) {
			HibernateUtil.getSessionFactory().getCurrentSession().getTransaction().rollback();
			throw new Exception(e.getMessage(),e);
		}finally {
			
		}
	}
	
	public static List<Onanismo> findByDiscordId(String discordId,Session session) throws Exception {
		List<Onanismo> res = null;
		List<?> aux = null;
		try {
			String hql = "from Onanismo o where o.user.discordId = :discordId";
	        Query<?> query = session.createQuery(hql);
	        query.setParameter("discordId", discordId);
	        System.out.println("Executing query '"+query.toString()+"'");
	        aux = query.list();
	        res = BotUtil.get().castList(Onanismo.class, aux);
		}catch (Exception e) {
			HibernateUtil.getSessionFactory().getCurrentSession().getTransaction().rollback();
			System.out.println("WARNING: Rolled back");
			throw new Exception(e.getMessage(),e);
		}finally {
			
		}
		return res;
	}
}
