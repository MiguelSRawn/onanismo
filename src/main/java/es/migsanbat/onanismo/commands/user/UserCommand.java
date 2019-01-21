package es.migsanbat.onanismo.commands.user;

import com.jagrosh.jdautilities.command.Command;
import com.jagrosh.jdautilities.command.CommandEvent;

import es.migsanbat.onanismo.domain.User;
import es.migsanbat.onanismo.services.UserService;

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
		if(args.length>.0) {
			switch(args[0]) {
			case "":
				reply = "Escribe 'user' help para ver la lista de comandos de user";
				break;
			case "help":
				break;
			case "see":
			case "ver":
				break;
			case "ingresar":
			case "añadir":
			case "crear":
			case "create":
				user =UserService.get().createAndSave(event.getAuthor().getId(), event.getAuthor().getName());
				reply = "Usuario creado: \n"
						+ "		Nombre: "+user.getName()+"\n"
						+ "Bienvenid@ al mundo de las pajas!";
				break;
			}
					
		}else {
			
		}
		event.reply(reply);
	}

}
