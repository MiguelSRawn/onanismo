package es.migsanbat.onanismo.commands.meta;

import java.time.DayOfWeek;
import java.time.ZoneId;
import java.util.List;

import com.jagrosh.jdautilities.command.Command;
import com.jagrosh.jdautilities.command.CommandEvent;

import es.migsanbat.onanismo.domain.Onanismo;
import es.migsanbat.onanismo.services.UserService;

public class CristinaCommand extends Command {

	public CristinaCommand(Category meta,boolean mantenimiento) {
		this.name = "Cris";
		this.category = meta;
		this.aliases = new String[] {  };
		this.help = "";
		this.guildOnly=false;
		this.ownerCommand=mantenimiento;
	}

	@Override
	protected void execute(CommandEvent event) {
		List<Onanismo> onas =UserService.get().findOneByDiscordId("543440071567736832").getOnanismos();
		int[] array =new int[7];
		for(Onanismo ona:onas) {
			array[ona.getFecha().toInstant().atZone(ZoneId.of("UTC+1")).toLocalDate().getDayOfWeek().getValue()-1]++;
		}		
		for(int i=0;i<7;i++) {
			event.reply(DayOfWeek.of(i+1).toString()+": "+array[i]);
		}
	}

}
