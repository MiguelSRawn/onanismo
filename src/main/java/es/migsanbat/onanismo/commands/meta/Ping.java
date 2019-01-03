package es.migsanbat.onanismo.commands.meta;

import com.jagrosh.jdautilities.command.Command;
import com.jagrosh.jdautilities.command.CommandEvent;
import com.jagrosh.jdautilities.command.Command.Category;

public class Ping extends Command {

	public Ping(Category meta) {
		this.name = "ping";
		this.category = meta;
		this.aliases = new String[] { "test", "demo" };
		this.help = "Comando para comprobar la disponibilidad del bot";
		this.guildOnly=false;
	}

	@Override
	protected void execute(CommandEvent event) {
		event.reply("Pong!");	
	}

}
