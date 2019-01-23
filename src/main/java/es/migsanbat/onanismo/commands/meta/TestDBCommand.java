package es.migsanbat.onanismo.commands.meta;

import java.util.Date;
import java.util.List;

import org.hibernate.Session;

import com.jagrosh.jdautilities.command.Command;
import com.jagrosh.jdautilities.command.CommandEvent;

import es.migsanbat.onanismo.BotLauncher;
import es.migsanbat.onanismo.domain.DBTest;
import es.migsanbat.onanismo.domain.Onanismo;
import es.migsanbat.onanismo.util.BotUtil;
import es.migsanbat.onanismo.util.HibernateUtil;

import com.jagrosh.jdautilities.command.Command.Category;

public class TestDBCommand extends Command {

	public TestDBCommand(Category meta) {
		this.name = "testDB";
		this.category = meta;
		this.aliases = new String[] { "db" };
		this.help = "Comando para comprobar la disponibilidad de la db";
		this.guildOnly=false;
		this.ownerCommand=true;
	}

	@Override
	protected void execute(CommandEvent event) {
		try {
			event.reply("Connecting db...");
			createAndStoreConnection();
			event.reply("```css\n Connected \n```");
			List<DBTest> lista = this.list();
			DBTest dbTest=(DBTest) lista.get(lista.size()-1);
			
			event.reply("    Connection id: "+dbTest.getId());
			event.reply("    Connection date: "+dbTest.getDate());
			event.reply(BotUtil.get().ColorText(BotUtil.COLOR_GREEN, "Everything went ok"));
			
		}catch (Exception e) {
			event.reply("ERROR: Wops, something failed, check the logs");
			System.err.println(e.getMessage()+", "+e);
		}
		
		
	}
	private List<DBTest> list() {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        session.beginTransaction();
        List<DBTest> result = session.createQuery("from DBTest").list();
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
