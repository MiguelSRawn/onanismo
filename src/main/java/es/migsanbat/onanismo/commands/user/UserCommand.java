package es.migsanbat.onanismo.commands.user;

import com.jagrosh.jdautilities.command.Command;
import com.jagrosh.jdautilities.command.CommandEvent;

import es.migsanbat.onanismo.domain.User;
import es.migsanbat.onanismo.services.UserService;
import es.migsanbat.onanismo.util.BotUtil;

import com.jagrosh.jdautilities.command.Command.Category;

public class UserCommand extends Command {

	public UserCommand(Category meta) {
		this.name = "user";
		this.category = meta;
		this.aliases = new String[] { "usuario" };
		this.help = "Comandos relacionados con el usuario";
		this.guildOnly=false;
	}

	@Override
	protected void execute(CommandEvent event) {
		String[] args = event.getArgs().split(" ");
		User user;
		String reply ="";
		if(args.length>0) {
			switch(args[0]) {
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
				if(args.length>1) {
					discordId = args[1];
				}else {
					discordId = event.getAuthor().getId();
				}
				user = UserService.get().findOneByDiscordId(discordId);
				if(user==null) {
					reply = "Usuario inexistente.";
				}else {
					reply = "Usuario encontrado: \n"
							+ BotUtil.get().formateaTexto(BotUtil.FORMATO_BLOQUE, UserService.get().createReply(user))
							+ "Escribe +help para ver la lista de comandos";
				}
				break;
			case "ingresar":
			case "añadir":
			case "crear":
			case "create":
				user = UserService.get().findOneByDiscordId(event.getAuthor().getId());
				if(user==null) {
					user =UserService.get().createAndSave(event.getAuthor().getId(), event.getAuthor().getName());
					reply = "Usuario creado: \n"
							+ BotUtil.get().formateaTexto(BotUtil.FORMATO_BLOQUE, UserService.get().createReply(user))
							+ "Bienvenid@ al mundo de las pajas!";
				}else {
					reply = "Ya tienes usuario! \n"
							+ BotUtil.get().formateaTexto(BotUtil.FORMATO_BLOQUE, UserService.get().createReply(user))
							+ "Escribe +help para ver la lista de comandos";
				}
				
				break;
			default:
				reply = "Wops, comando no reconocido. Argumento: "+args[0];
			}
					
		}else {
			reply = "Escribe '+user help' para ver la lista de comandos de user";
		}
		event.reply(reply);
	}

}
