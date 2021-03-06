package es.migsanbat.onanismo.commands.user;

import org.hibernate.Session;

import com.jagrosh.jdautilities.command.Command;
import com.jagrosh.jdautilities.command.CommandEvent;

import es.migsanbat.onanismo.domain.User;
import es.migsanbat.onanismo.services.UserService;
import es.migsanbat.onanismo.util.BotUtil;
import es.migsanbat.onanismo.util.HibernateUtil;

public class UserCommand extends Command {

	public UserCommand(Category meta,boolean mantenimiento) {
		this.name = "user";
		this.category = meta;
		this.aliases = new String[] { "usuario" };
		this.help = "Comandos relacionados con el usuario";
		this.guildOnly = false;
		this.ownerCommand=mantenimiento;
	}

	@Override
	protected void execute(CommandEvent event) {
		String[] args;
		User user;
		String reply = "";
		try {
			if (event.getArgs()!=null) {
				args = event.getArgs().split(" ");
				switch (args[0]) {
				case "":
					reply = "Escribe '+user help' para ver la lista de comandos de user";
					break;
				case "help":
					reply = BotUtil.get().commandHelp(BotUtil.USER_COMMAND, args);
					break;
				case "see":
				case "ver":
				case "show":
				case "mostrar":
					String discordId;
					if (args.length > 1) {
						discordId = args[1];
					} else {
						discordId = event.getAuthor().getId();
					}
					user = UserService.get().findOneByDiscordId(discordId);
					if (user == null) {
						reply = "Usuario inexistente.";
					} else {
						reply = "Usuario encontrado: \n"
								+ BotUtil.get().formateaTexto(BotUtil.FORMATO_BLOQUE,
										UserService.get().createReply(user))
								+ "Escribe +help para ver la lista de comandos";
					}

					break;
				case "ingresar":
				case "añadir":
				case "crear":
				case "create":
					user = UserService.get().findOneByDiscordId(event.getAuthor().getId());
					if (user == null) {
						user = UserService.get().createAndSave(event.getAuthor().getId(), event.getAuthor().getName());
						reply = "Usuario creado: \n" + BotUtil.get().formateaTexto(BotUtil.FORMATO_BLOQUE,
								UserService.get().createReply(user)) + "Bienvenid@ al mundo de las pajas!";
					} else {
						reply = "Ya tienes usuario! \n"
								+ BotUtil.get().formateaTexto(BotUtil.FORMATO_BLOQUE,
										UserService.get().createReply(user))
								+ "Escribe +help para ver la lista de comandos";
					}

					break;
				default:
					reply = "Wops, comando no reconocido. Argumento: " + args[0];
				}

			} else {
				reply = "Escribe '+user help' para ver la lista de comandos de user";
			}
		} catch (Exception e) {
			System.out.println("Wops");
			e.printStackTrace();
			Session session = HibernateUtil.getSessionFactory().getCurrentSession();
			session.getTransaction().rollback();
			reply = BotUtil.get().formateaTexto(BotUtil.COLOR_RED, e.getMessage());
		}
		event.reply(reply);
	}

}
