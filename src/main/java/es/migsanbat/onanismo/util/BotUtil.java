package es.migsanbat.onanismo.util;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import net.dv8tion.jda.core.events.ReadyEvent;

public class BotUtil {
private static BotUtil instancia;
private static String ownerId =System.getenv("OWNER_ID");
	
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
}
