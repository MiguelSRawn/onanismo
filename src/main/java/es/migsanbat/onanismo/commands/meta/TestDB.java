package es.migsanbat.onanismo.commands.meta;

import java.util.Date;
import java.util.List;

import org.hibernate.Session;

import com.jagrosh.jdautilities.command.Command;
import com.jagrosh.jdautilities.command.CommandEvent;

import es.migsanbat.onanismo.BotLauncher;
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
		createAndStoreEvent("test");
		List lista = this.list();
		Onanismo onanismo=(Onanismo) lista.get(lista.size()-1);
		event.reply("Onanismo: "+onanismo.getTitle());
	}
	private List list() {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        session.beginTransaction();
        List result = session.createQuery("from Onanismo").list();
        session.getTransaction().commit();
        return result;
    }
	private static void createAndStoreEvent(String title) {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        session.beginTransaction();
 
        Onanismo onanismo = new Onanismo();
        Date date = new Date();
        onanismo.setTitle(date.toString());
        
        session.save(onanismo);
 
        session.getTransaction().commit();
    }
}
