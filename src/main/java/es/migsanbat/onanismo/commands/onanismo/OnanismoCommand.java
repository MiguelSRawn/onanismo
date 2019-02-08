package es.migsanbat.onanismo.commands.onanismo;

import java.time.DayOfWeek;
import java.time.LocalDate;

import org.hibernate.Session;

import com.jagrosh.jdautilities.command.Command;
import com.jagrosh.jdautilities.command.CommandEvent;

import es.migsanbat.onanismo.domain.Onanismo;
import es.migsanbat.onanismo.services.OnanismoService;
import es.migsanbat.onanismo.util.BotUtil;
import es.migsanbat.onanismo.util.HibernateUtil;

public class OnanismoCommand extends Command {

	public OnanismoCommand(Category meta,boolean mantenimiento) {
		this.name = "fap";
		this.category = meta;
		this.aliases = new String[] { "paja","masturbación","onanismo" };
		this.help = "Comandos relacionados con el acto de quererse mucho";
		this.guildOnly=false;
		this.ownerCommand=mantenimiento;
	}

	@Override
	protected void execute(CommandEvent event) {
		String[] args = event.getArgs().split(" ");
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
						if(event.getAuthor().getId().equals("543440071567736832")) {
							String message = "";
							
							switch(LocalDate.now().getDayOfWeek()) {
								case MONDAY:
									message = "Pablo";
									break;
								case TUESDAY:
									message = "Agus";
									break;
								case WEDNESDAY:
									message = "Dani";
									break;
								case THURSDAY:
									message = "RafaG";
									break;
								case FRIDAY:
									message = "Andy";
									break;
								case SATURDAY:
									message = "Miguel";
									break;
								case SUNDAY:
									message = "RafaJ";
									break;
							}
							event.getJDA().getTextChannelById("538388638447304704").sendMessage("Esta va por "+message);
						}
					break;
				default:
					reply = "Wops, comando no reconocido. Argumento: "+args[0];
				}
						
			}else {
				reply = "Escribe '+saldo help' para ver la lista de comandos de saldo";
			}
		}catch (Exception e) {
			e.printStackTrace();
			Session session = HibernateUtil.getSessionFactory().getCurrentSession();
			session.getTransaction().rollback();
			reply = BotUtil.get().formateaTexto(BotUtil.COLOR_RED, e.getMessage());
		}
		
		event.reply(reply);
	}

}
