package es.migsanbat.onanismo.commands.meta;

import java.time.LocalDate;
import java.time.ZoneId;

import com.jagrosh.jdautilities.command.Command;
import com.jagrosh.jdautilities.command.CommandEvent;

public class DiaCommand extends Command {

	public DiaCommand(Category meta,boolean mantenimiento) {
		this.name = "dia";
		this.category = meta;
		this.aliases = new String[] {  };
		this.help = "Comando para comprobar la disponibilidad del bot";
		this.guildOnly=false;
		this.ownerCommand=mantenimiento;
	}

	@Override
	protected void execute(CommandEvent event) {
		event.reply(LocalDate.now().now(ZoneId.of("UTC+1")).getDayOfWeek().toString());	
	}

}
