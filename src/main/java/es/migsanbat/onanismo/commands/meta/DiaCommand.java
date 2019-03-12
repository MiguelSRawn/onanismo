package es.migsanbat.onanismo.commands.meta;

import java.time.DayOfWeek;
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
		DayOfWeek dia=LocalDate.now().now(ZoneId.of("UTC+1")).getDayOfWeek();
		String reply = "";
		try {
			if(event.getAuthor().getId().equals("190006578785681408")) {
				String message = "";
				reply = "enas miguel";
				switch(dia) {
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
				if(event.getJDA().getTextChannelById("538388638447304704")==null) {
					reply="No encuentro el canal";
				}else {
					reply="Uhmm";
					if(!event.getJDA().getTextChannelById("538388638447304704").canTalk()) {
						reply = "No puedo hablar en el canal "+event.getJDA().getTextChannelById("538388638447304704").getName();
					}else {
						reply = "No puedo hablar en el canal "+event.getJDA().getTextChannelById("538388638447304704").getName();
						event.getJDA().getTextChannelById("538388638447304704").sendMessage("mensaje");
					}
					
				}
				
			}
			event.reply(reply);	
		}catch (Exception e) {
			System.out.println(e.getMessage());
		}
		
	}

}
