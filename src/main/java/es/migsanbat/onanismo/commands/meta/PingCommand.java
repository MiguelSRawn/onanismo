package es.migsanbat.onanismo.commands.meta;

import com.jagrosh.jdautilities.command.Command;
import com.jagrosh.jdautilities.command.CommandEvent;

public class PingCommand extends Command {

	public PingCommand(Category meta,boolean mantenimiento) {
		this.name = "ping";
		this.category = meta;
		this.aliases = new String[] { "test", "demo" };
		this.help = "Comando para comprobar la disponibilidad del bot";
		this.guildOnly=false;
		this.ownerCommand=mantenimiento;
	}

	@Override
	protected void execute(CommandEvent event) {
		event.reply("Pong!");	
	}

}
