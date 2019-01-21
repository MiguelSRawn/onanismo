package es.migsanbat.onanismo.repositories;


import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.query.Query;

import es.migsanbat.onanismo.domain.DBTest;
import es.migsanbat.onanismo.domain.User;
import es.migsanbat.onanismo.util.BotUtil;
import es.migsanbat.onanismo.util.HibernateUtil;

public class UserRepository {
	
	public static void save(User user) throws Exception {
		try {
			Session session = HibernateUtil.getSessionFactory().getCurrentSession();
	        session.beginTransaction();
	        
	        session.save(user);
	 
	        session.getTransaction().commit();
		}catch (Exception e) {
			HibernateUtil.getSessionFactory().getCurrentSession().getTransaction().rollback();
			throw new Exception(e.getMessage(),e);
		}finally {
			
		}
	}

	public static void persist(User user) throws Exception {
		try {
			Session session = HibernateUtil.getSessionFactory().getCurrentSession();
	        session.beginTransaction();
	        
	        session.persist(user);
	 
	        session.getTransaction().commit();
		}catch (Exception e) {
			HibernateUtil.getSessionFactory().getCurrentSession().getTransaction().rollback();
			throw new Exception(e.getMessage(),e);
		}finally {
			
		}
	}
	
	public static List<User> findByDiscordId(String discordId) throws Exception {
		List<User> res = null;
		List<?> aux = null;
		try {
			Session session = HibernateUtil.getSessionFactory().getCurrentSession();
	        session.beginTransaction();
	        
	        String hql = "from USUARIO u where u.discordId = :discordId";
	        Query<?> query = session.createQuery(hql);
	        query.setParameter("discordId", discordId);
	        System.out.println("Executing query '"+query.toString()+"'");
	        aux = query.list();
	        res = BotUtil.get().castList(User.class, aux);
	 
	        session.getTransaction().commit();
		}catch (Exception e) {
			HibernateUtil.getSessionFactory().getCurrentSession().getTransaction().rollback();
			System.out.println("WARNING: Rolled back");
			throw new Exception(e.getMessage(),e);
		}finally {
			
		}
		return res;
	}
}
