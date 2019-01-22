package es.migsanbat.onanismo.commands.saldo;

import com.jagrosh.jdautilities.command.Command;
import com.jagrosh.jdautilities.command.CommandEvent;
import com.jagrosh.jdautilities.command.Command.Category;

import es.migsanbat.onanismo.services.CarteraService;

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
		String funds;
		String reply ="";
		if(args.length>0) {
			switch(args[0]) {
			case "":
				break;
			case "help":
				break;
			case "see":
			case "ver":
			case "show":
			case "mostrar":
				
				break;
			case "ingresar":
			case "add":
			case "añadir":
				if(args.length>1) {
					funds = args[1];
					System.out.println("Adding funds: "+funds);
					CarteraService.get().addBalance(Long.decode(funds), event.getAuthor().getId());
					reply = "Se han añadido "+funds+" centimos.";
				}else {
					reply = "Debes escribir el número de centimos a ingresas Ex: '+saldo add <centimos>'";
				}
				
				break;
			}
					
		}else {
			reply = "Escribe '+saldo help' para ver la lista de comandos de saldo";
		}
		event.reply(reply);
	}

}
