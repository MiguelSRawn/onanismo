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
		String reply = "";
		List<Onanismo> onas =UserService.get().findOneByDiscordId("543440071567736832").getOnanismos();
		int[] array =new int[7];
		for(Onanismo ona:onas) {
			array[ona.getFecha().toInstant().atZone(ZoneId.of("UTC+1")).toLocalDate().getDayOfWeek().getValue()-1]++;
		}
		reply+="Pablo: "+array[0]+"\n";
		reply+="Agus: "+array[1]+"\n";
		reply+="Dani: "+array[2]+"\n";
		reply+="RafaG: "+array[3]+"\n";
		reply+="Andy: "+array[4]+"\n";
		reply+="Miguel: "+array[5]+"\n";
		reply+="Sunday: "+array[6]+"\n";
		
		event.reply(reply);
	}

}
