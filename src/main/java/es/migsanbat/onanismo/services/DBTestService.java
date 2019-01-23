package es.migsanbat.onanismo.services;

import java.util.Date;
import java.util.List;

import org.hibernate.Session;

import es.migsanbat.onanismo.domain.DBTest;
import es.migsanbat.onanismo.util.HibernateUtil;
public class DBTestService {
	private static DBTestService instancia;
	
	public static DBTestService get() {
		if(instancia==null) {
			instancia = new DBTestService();
		}
		return instancia;
	}
	@SuppressWarnings("unchecked")
	public List<DBTest> list() {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        session.beginTransaction();
        List<DBTest> result = session.createQuery("from DBTest").list();
        session.getTransaction().commit();
        return result;
    }
	public void createAndStoreConnection() throws Exception {
		try {
			Session session = HibernateUtil.getSessionFactory().getCurrentSession();
	        session.beginTransaction();
	 
	        DBTest dbTest = new DBTest();
	        Date date = new Date();
	        dbTest.setDate(date);
	        
	        session.save(dbTest);
	 
	        session.getTransaction().commit();
		}catch (Exception e) {
			throw new Exception(e.getMessage(),e);
		}finally {
			
		}
        
    }
}
