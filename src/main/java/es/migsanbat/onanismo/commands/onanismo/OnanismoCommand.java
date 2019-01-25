package es.migsanbat.onanismo.commands.onanismo;

import com.jagrosh.jdautilities.command.Command;
import com.jagrosh.jdautilities.command.CommandEvent;
import com.jagrosh.jdautilities.command.Command.Category;

import es.migsanbat.onanismo.domain.Onanismo;
import es.migsanbat.onanismo.services.CarteraService;
import es.migsanbat.onanismo.services.OnanismoService;
import es.migsanbat.onanismo.util.BotUtil;

public class OnanismoCommand extends Command {

	public OnanismoCommand(Category meta) {
		this.name = "fap";
		this.category = meta;
		this.aliases = new String[] { "paja","masturbación","onanismo" };
		this.help = "Comandos relacionados con el acto de quererse mucho";
		this.guildOnly=false;
	}

	@Override
	protected void execute(CommandEvent event) {
		String[] args = event.getArgs().split(" ");
		String funds;
		String reply ="";
		try {
			if(args.length>0) {
				switch(args[0]) {
				case "help":
					break;
				case "see":
				case "ver":
				case "show":
				case "mostrar":
					
					break;
				case "":
				case "ingresar":
				case "add":
				case "añadir":
						System.out.println(event.getAuthor().getName()+" is fapping");
						Onanismo ona= OnanismoService.get().fap(event.getAuthor().getId());
						if(ona==null) {
							reply = "Saldo insuficiente";
						}
					break;
				default:
					reply = "Wops, comando no reconocido. Argumento: "+args[0];
				}
						
			}else {
				reply = "Escribe '+saldo help' para ver la lista de comandos de saldo";
			}
		}catch (Exception e) {
			reply = BotUtil.get().formateaTexto(BotUtil.COLOR_RED, e.getMessage());
		}
		
		event.reply(reply);
	}

}
