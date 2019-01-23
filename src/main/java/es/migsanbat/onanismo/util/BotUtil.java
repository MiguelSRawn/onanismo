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
	public static final int COLOR_GREEN2 = 1;
	public static final int COLOR_RED = 2;
	public static final int COLOR_CYAN = 3;
	public static final int COLOR_CYAN2 = 4;
	public static final int COLOR_YELLOW = 5;
	public static final int COLOR_YELLOW2 = 6;
	
	public static final int FORMATO_BLOQUE = 7;
	public static final int FORMATO_LINEA = 8;
	
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
						+ formateaTexto(FORMATO_LINEA, "+user help")+": Es este comando! \n"
						+ formateaTexto(FORMATO_LINEA, "+user ver")+": Mostrar la información de tu usuario o el usuario dado \n"
						+ formateaTexto(FORMATO_LINEA, "+user crear")+": Crea un nuevo usuario si no tienes ya uno";
				break;
		}
		return reply;
	}
	
	public String formateaTexto(int color,String text) {
		String res;
		switch(color) {
		case COLOR_GREEN:
			res = "```css\r\n" +
					text+"```";
			break;
		case COLOR_GREEN2:
			res ="```diff\r\n" + 
					"+ "+text+"```";
			break;
		case COLOR_RED:
			res ="```diff\r\n" + 
					"- "+text+"```";
			break;
		case COLOR_CYAN:
			res = "```yaml\r\n" +
					text+"```";
			break;
		case COLOR_CYAN2:
			res = "```cs\r\n" + 
					"'"+text+"'```";
			break;
		case COLOR_YELLOW:
			res = "```fix\r\n" + 
					text+"```";
			break;
		case COLOR_YELLOW2:
			res = "```autohotkey\r\n" + 
					"%"+text+"%```";
			break;
		case FORMATO_BLOQUE:
			res = "```"+text+"```";
			break;
		case FORMATO_LINEA:
			res ="`"+text+"`";
			break;
		default:
			res = text;
			break;
		}
		return res;
	}
}
