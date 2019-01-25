package es.migsanbat.onanismo.commands.meta;

import java.util.List;


import com.jagrosh.jdautilities.command.Command;
import com.jagrosh.jdautilities.command.CommandEvent;

import es.migsanbat.onanismo.domain.DBTest;
import es.migsanbat.onanismo.services.DBTestService;
import es.migsanbat.onanismo.util.BotUtil;

public class TestDBCommand extends Command {

	public TestDBCommand(Category meta) {
		this.name = "testDB";
		this.category = meta;
		this.aliases = new String[] { "db" };
		this.help = "Comando para comprobar la disponibilidad de la db";
		this.guildOnly=false;
		this.ownerCommand=true;
	}

	@Override
	protected void execute(CommandEvent event) {
		try {
			String reply;
			reply = "Connecting db...";
			event.reply(BotUtil.get().formateaTexto(BotUtil.FORMATO_BLOQUE, reply));
			DBTestService.get().createAndStoreConnection();
			event.reply(BotUtil.get().formateaTexto(BotUtil.COLOR_GREEN, "Connected:"));
			List<DBTest> lista = DBTestService.get().list();
			DBTest dbTest=(DBTest) lista.get(lista.size()-1);
			reply = "	Connection id: "+dbTest.getId()+" \r\n	Connection date: "+dbTest.getDate();
			event.reply(BotUtil.get().formateaTexto(BotUtil.FORMATO_BLOQUE, reply));
			event.reply(BotUtil.get().formateaTexto(BotUtil.COLOR_GREEN, "Everything went ok"));
			
		}catch (Exception e) {
			event.reply(BotUtil.get().formateaTexto(BotUtil.COLOR_RED, "ERROR: Wops, something failed, check the logs"));
			e.printStackTrace();
		}
		
		
	}
	
}
