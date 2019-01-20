package es.migsanbat.onanismo.commands.saldo;

import com.jagrosh.jdautilities.command.Command;
import com.jagrosh.jdautilities.command.CommandEvent;
import com.jagrosh.jdautilities.command.Command.Category;

public class Balance extends Command {

	public Balance(Category meta) {
		this.name = "balance test";
		this.category = meta;
		this.aliases = new String[] { "saldo" };
		this.help = "Comandos relacionados con el saldo";
		this.guildOnly=false;
	}

	@Override
	protected void execute(CommandEvent event) {
		event.reply("Test");	
	}

}
