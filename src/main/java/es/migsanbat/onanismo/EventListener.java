package es.migsanbat.onanismo;
import net.dv8tion.jda.core.events.ReadyEvent;
import net.dv8tion.jda.core.hooks.ListenerAdapter;

public class EventListener extends ListenerAdapter {
	@Override
	public void onReady(ReadyEvent event) {
		System.out.println("Ready");
	}
}
