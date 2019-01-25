package es.migsanbat.onanismo;
import es.migsanbat.onanismo.util.BotUtil;
import net.dv8tion.jda.core.events.ReadyEvent;
import net.dv8tion.jda.core.hooks.ListenerAdapter;

public class EventListener extends ListenerAdapter {
	
	public EventListener() {
		super();
	}
	@Override
	public void onReady(ReadyEvent event) {
		System.out.println("Ready");
		BotUtil.get().readyMessage(event, BotUtil.get().formateaTexto(BotUtil.FORMATO_BLOQUE, "I'm Ready"));
		
		
	}
}
