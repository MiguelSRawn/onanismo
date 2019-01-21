package es.migsanbat.onanismo.util;

import net.dv8tion.jda.core.events.Event;
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
}
