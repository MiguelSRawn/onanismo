package es.migsanbat.onanismo.commands.saldo;

import org.hibernate.Session;

import com.jagrosh.jdautilities.command.Command;
import com.jagrosh.jdautilities.command.CommandEvent;

import es.migsanbat.onanismo.services.CarteraService;
import es.migsanbat.onanismo.services.TransaccionService;
import es.migsanbat.onanismo.util.BotUtil;
import es.migsanbat.onanismo.util.HibernateUtil;

public class TransactionCommand extends Command {

	public TransactionCommand(Category meta,boolean mantenimiento) {
		this.name = "donar";
		this.category = meta;
		this.aliases = new String[] {"pasar"};
		this.help = "Comandos para donar saldo a un compañero. "+BotUtil.get().formateaTexto(BotUtil.FORMATO_LINEA, "+donar <userId> <centimos>");
		this.guildOnly = false;
		this.ownerCommand=mantenimiento;
	}

	@Override
	protected void execute(CommandEvent event) {
		String[] args;
		Integer funds;
		String beneficiario;
		String benefactor;
		String reply = "";
		System.out.println("execute(): "+this.name+" "+event.getArgs().toString());
		try {
			if (event.getArgs()!=null&&event.getArgs().split(" ").length>=2) {
				args = event.getArgs().split(" ");
				beneficiario = args[0];
				benefactor = event.getAuthor().getId();
				funds = Integer.decode(args[1]);
				TransaccionService.get().createAndSave(beneficiario, benefactor, funds);
				
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
