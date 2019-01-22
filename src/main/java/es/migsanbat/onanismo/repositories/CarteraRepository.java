package es.migsanbat.onanismo.repositories;


import org.hibernate.Session;

import es.migsanbat.onanismo.domain.Cartera;
import es.migsanbat.onanismo.util.HibernateUtil;

public class CarteraRepository {
	
	public static void save(Cartera cartera) throws Exception {
		try {
			Session session = HibernateUtil.getSessionFactory().getCurrentSession();
	        session.beginTransaction();
	        
	        session.save(cartera);
	 
	        session.getTransaction().commit();
		}catch (Exception e) {
			throw new Exception(e.getMessage(),e);
		}finally {
			
		}
	}

	public static void update(Cartera cartera) throws Exception {
		try {
			Session session = HibernateUtil.getSessionFactory().getCurrentSession();
	        session.beginTransaction();
	        
	        session.update(cartera);
	 
	        session.getTransaction().commit();
		}catch (Exception e) {
			throw new Exception(e.getMessage(),e);
		}finally {
			
		}
		
	}
	public static void saveOrUpdate(Cartera cartera) throws Exception {
		try {
			Session session = HibernateUtil.getSessionFactory().getCurrentSession();
	        session.beginTransaction();
	        
	        session.saveOrUpdate(cartera);
	 
	        session.getTransaction().commit();
		}catch (Exception e) {
			throw new Exception(e.getMessage(),e);
		}finally {
			
		}
		
	}
}
