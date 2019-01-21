package es.migsanbat.onanismo.commands.saldo;

import com.jagrosh.jdautilities.command.Command;
import com.jagrosh.jdautilities.command.CommandEvent;
import com.jagrosh.jdautilities.command.Command.Category;

public class BalanceCommand extends Command {

	public BalanceCommand(Category meta) {
		this.name = "balance";
		this.category = meta;
		this.aliases = new String[] { "saldo" };
		this.help = "Comandos relacionados con el saldo";
		this.guildOnly=false;
	}

	@Override
	protected void execute(CommandEvent event) {
		String[] args = event.getArgs().split(" ");
		if(args.length>.0) {
			switch(args[0]) {
			case "":
				break;
			case "help":
				break;
			case "see":
			case "ver":
				break;
			case "ingresar":
				break;
			}
					
		}else {
			
		}
	}

}
