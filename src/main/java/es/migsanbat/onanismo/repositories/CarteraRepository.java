package es.migsanbat.onanismo.repositories;


import org.hibernate.Session;

import es.migsanbat.onanismo.domain.Cartera;

public class CarteraRepository {
	
	public static void save(Cartera cartera,Session session) throws Exception {
		try {
			session.save(cartera);
		}catch (Exception e) {
			throw new Exception(e.getMessage(),e);
		}finally {
			
		}
	}

	public static void update(Cartera cartera,Session session) throws Exception {
		try {
			session.update(cartera);
		}catch (Exception e) {
			throw new Exception(e.getMessage(),e);
		}finally {
			
		}
		
	}
	public static void saveOrUpdate(Cartera cartera,Session session) throws Exception {
		try {
			session.saveOrUpdate(cartera);
		}catch (Exception e) {
			throw new Exception(e.getMessage(),e);
		}finally {
			
		}
		
	}
}
