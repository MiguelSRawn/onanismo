package es.migsanbat.onanismo.util;

import java.util.HashMap;
import java.util.Map;

import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

public class HibernateUtil {
	 private static final SessionFactory sessionFactory = buildSessionFactory();
	 
	    private static SessionFactory buildSessionFactory() {
	        try {
	            // Create the SessionFactory from hibernate.cfg.xml
	        	Configuration configuration = new Configuration();
	            configuration.configure();
	            Map<String,String> jdbcUrlSettings = new HashMap<>();
	            String jdbcDbUrl = System.getenv("DATABASE_URL");
	            if (null != jdbcDbUrl) {
	              jdbcUrlSettings.put("hibernate.connection.url", System.getenv("JDBC_DATABASE_URL"));
	            }
	            ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder().configure("hibernate.cfg.xml").applySettings(
	            		jdbcUrlSettings).build();
	            return configuration.buildSessionFactory(serviceRegistry);
	        }
	        catch (Throwable ex) {
	            // Make sure you log the exception, as it might be swallowed
	            System.err.println("Initial SessionFactory creation failed." + ex);
	            throw new ExceptionInInitializerError(ex);
	        }
	    }
	 
	    public static SessionFactory getSessionFactory() {
	        return sessionFactory;
	    }
}
