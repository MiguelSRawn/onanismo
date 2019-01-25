package es.migsanbat.onanismo.commands.saldo;

import org.hibernate.Session;

import com.jagrosh.jdautilities.command.Command;
import com.jagrosh.jdautilities.command.CommandEvent;

import es.migsanbat.onanismo.services.CarteraService;
import es.migsanbat.onanismo.util.BotUtil;
import es.migsanbat.onanismo.util.HibernateUtil;

public class BalanceCommand extends Command {

	public BalanceCommand(Category meta,boolean mantenimiento) {
		this.name = "balance";
		this.category = meta;
		this.aliases = new String[] { "saldo" };
		this.help = "Comandos relacionados con el saldo";
		this.guildOnly = false;
		this.ownerCommand=mantenimiento;
	}

	@Override
	protected void execute(CommandEvent event) {
		String[] args = event.getArgs().split(" ");
		String funds;
		String reply = "";
		System.out.println("execute(): "+this.name+" "+event.getArgs().toString());
		try {
			if (args.length > 0) {
				switch (args[0]) {
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
					if (args.length > 1) {
						funds = args[1];
						System.out.println("Adding funds: " + funds);
						CarteraService.get().addBalance(Integer.decode(funds), event.getAuthor().getId());
						reply = "Se han añadido " + funds + " centimos.";
					} else {
						reply = "Debes escribir el número de centimos a ingresas Ex: '+saldo add <centimos>'";
					}

					break;
				default:
					reply = "Wops, comando no reconocido. Argumento: " + args[0];
				}

			} else {
				reply = "Escribe '+saldo help' para ver la lista de comandos de saldo";
			}
		} catch (Exception e) {
			e.printStackTrace();
			Session session = HibernateUtil.getSessionFactory().getCurrentSession();
			session.getTransaction().rollback();
			reply = BotUtil.get().formateaTexto(BotUtil.COLOR_RED, e.getMessage());
		}
		event.reply(reply);
	}

}
