package es.migsanbat.onanismo.util;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.swing.plaf.ButtonUI;

import org.omg.PortableInterceptor.USER_EXCEPTION;

import net.dv8tion.jda.core.events.ReadyEvent;

public class BotUtil {
	private static BotUtil instancia;
	private static String ownerId =System.getenv("OWNER_ID");
	
	public static final int USER_COMMAND = 0;
	public static final int BALANCE_COMMAND = 1;
	public static final int TESTDB_COMMAND = 2;
	public static final int PING_COMMAND = 3;
	
	public static BotUtil get() {
		if(instancia==null) {
			instancia = new BotUtil();
		}
		return instancia;
	}
	
	public void readyMessage(ReadyEvent event,String message) {
		event.getJDA().getUserById(ownerId).openPrivateChannel().queue((channel) ->
        {
            channel.sendMessage(message).queue();
        });
	}
	
	public <T> List<T> castList(Class<? extends T> clazz, Collection<?> c) {
	    List<T> r = new ArrayList<T>(c.size());
	    for(Object o: c)
	      r.add(clazz.cast(o));
	    return r;
	}
	
	public String commandHelp(int command,String[] args) {
		String reply = "";
		switch(command) {
			case USER_COMMAND:
				reply = "Type: \n"
						+ "'+user help': Es este comando! \n"
						+ "'+user ver': Mostrar la información de tu usuario o el usuario dado \n"
						+ "'+user crear': Crea un nuevo usuario si no tienes ya uno";
				break;
		}
		return reply;
	}
}
