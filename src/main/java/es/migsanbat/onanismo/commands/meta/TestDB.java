package es.migsanbat.onanismo.commands.meta;

import java.util.Date;
import java.util.List;

import org.hibernate.Session;

import com.jagrosh.jdautilities.command.Command;
import com.jagrosh.jdautilities.command.CommandEvent;

import es.migsanbat.onanismo.BotLauncher;
import es.migsanbat.onanismo.domain.DBTest;
import es.migsanbat.onanismo.domain.Onanismo;
import es.migsanbat.onanismo.util.HibernateUtil;

import com.jagrosh.jdautilities.command.Command.Category;

public class TestDB extends Command {

	public TestDB(Category meta) {
		this.name = "testDB";
		this.category = meta;
		this.aliases = new String[] { "db" };
		this.help = "Comando para comprobar la disponibilidad de la db";
		this.guildOnly=false;
	}

	@Override
	protected void execute(CommandEvent event) {
		try {
			event.reply("Connecting db...");
			createAndStoreConnection();
			event.reply("Connected");
			List<DBTest> lista = this.list();
			DBTest dbTest=(DBTest) lista.get(lista.size()-1);
			
			event.reply("Connection id: "+dbTest.getId());
			event.reply("Connection date: "+dbTest.getDate());
			event.reply("Everything went ok");
		}catch (Exception e) {
			event.reply("Wops, something failed, check the logs");
			System.out.println(e.getMessage()+", "+e);
		}
		
		
	}
	private List<DBTest> list() {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        session.beginTransaction();
        List<DBTest> result = session.createQuery("from Onanismo").list();
        session.getTransaction().commit();
        return result;
    }
	private static void createAndStoreConnection() throws Exception {
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
