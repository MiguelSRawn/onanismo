package es.migsanbat.onanismo.util;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.swing.plaf.ButtonUI;

import org.omg.PortableInterceptor.USER_EXCEPTION;

import es.migsanbat.onanismo.services.ConfigService;
import net.dv8tion.jda.core.events.ReadyEvent;

public class BotUtil {
	private static BotUtil instancia;
	private static String ownerId =ConfigService.get().getConfig().getOwnerId();
	
	public static final int USER_COMMAND = 0;
	public static final int BALANCE_COMMAND = 1;
	public static final int TESTDB_COMMAND = 2;
	public static final int PING_COMMAND = 3;
	
	public static final int COLOR_GREEN = 0;
	public static final int COLOR_RED = 1;
	
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
	
	public String ColorText(int color,String text) {
		String res;
		switch(color) {
		case COLOR_GREEN:
			res = "```css\r\n" +
					text+"```";
			break;
		case COLOR_RED:
			res ="```diff\r\n" + 
					"- "+text+"```";
		default:
			res = text;
			break;
		}
		return res;
	}
}
