package es.migsanbat.onanismo.commands.meta;

import java.time.ZoneId;
import java.util.List;

import com.jagrosh.jdautilities.command.Command;
import com.jagrosh.jdautilities.command.CommandEvent;

import es.migsanbat.onanismo.domain.Onanismo;
import es.migsanbat.onanismo.services.UserService;

public class CristinaCommand extends Command {

	public CristinaCommand(Category meta,boolean mantenimiento) {
		this.name = "dia";
		this.category = meta;
		this.aliases = new String[] {  };
		this.help = "";
		this.guildOnly=false;
		this.ownerCommand=mantenimiento;
	}

	@Override
	protected void execute(CommandEvent event) {
		List<Onanismo> onas =UserService.get().findOneByDiscordId("543440071567736832").getOnanismos();
		if(onas.isEmpty()||onas==null) {
			event.reply("Nada encontrado");
		}else {
			for(Onanismo ona:onas) {
				event.reply(ona.getFecha().toInstant().atZone(ZoneId.of("UTC+1")).toLocalDate().getDayOfWeek().toString());
			}
		}
	}

}
