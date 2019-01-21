package es.migsanbat.onanismo.repositories;


import org.hibernate.Session;

import es.migsanbat.onanismo.domain.User;
import es.migsanbat.onanismo.util.HibernateUtil;

public class UserRepository {
	
	public static void save(User user) throws Exception {
		try {
			Session session = HibernateUtil.getSessionFactory().getCurrentSession();
	        session.beginTransaction();
	        
	        session.save(user);
	 
	        session.getTransaction().commit();
		}catch (Exception e) {
			throw new Exception(e.getMessage(),e);
		}finally {
			
		}
	}
}
