package es.migsanbat.onanismo;

import java.net.URISyntaxException;
import java.sql.SQLException;

import javax.security.auth.login.LoginException;

import com.jagrosh.jdautilities.command.Command.Category;
import com.jagrosh.jdautilities.command.CommandClient;
import com.jagrosh.jdautilities.command.CommandClientBuilder;

import es.migsanbat.onanismo.commands.meta.PingCommand;
import es.migsanbat.onanismo.commands.meta.TestDBCommand;
import es.migsanbat.onanismo.commands.onanismo.OnanismoCommand;
import es.migsanbat.onanismo.commands.saldo.BalanceCommand;
import es.migsanbat.onanismo.commands.user.UserCommand;
import es.migsanbat.onanismo.domain.Config;
import es.migsanbat.onanismo.services.ConfigService;
import net.dv8tion.jda.core.JDA;
import net.dv8tion.jda.core.JDABuilder;

public class BotLauncher {

	public static void main(String[] args) throws LoginException, InterruptedException, URISyntaxException, SQLException {
		try {
			// Cargando configuración del bot
		
			Config config = ConfigService.get().loadConfig().addHerokuConfig().getConfig();
			String token = config.getToken();
			String ownerId = config.getOwnerId();
			Integer coste = config.getCost();
			boolean mantenimiento = config.isMantenimiento();
			
			System.out.println("Loading with config: \n"
					+ "Token: "+token+" \n"
					+ "OwnerId: "+ownerId+" \n"
					+ "Coste: "+coste.toString()+" \n"
					+ "Mantenimiento: "+String.valueOf(mantenimiento));
			/*
			 * Inicio del bot
			 */
			final CommandClientBuilder builder = new CommandClientBuilder();
			// Opciones del bot
			builder.setPrefix("+");
			// Categorias
			final Category fap = new Category("Fap");
			final Category meta = new Category("Meta");
			final Category currency = new Category("Saldo");
			final Category users = new Category("Usuarios");
			// Comandos Meta
			builder.addCommands(new PingCommand(meta,mantenimiento));
			builder.addCommands(new TestDBCommand(meta));
			//Comandos Saldo
			builder.addCommand(new BalanceCommand(currency,mantenimiento));
			//Comandos usuarios
			builder.addCommand(new UserCommand(users,mantenimiento));
			//Comandos onanismo
			builder.addCommand(new OnanismoCommand(fap,mantenimiento));
			
			builder.setOwnerId(ownerId);
			final CommandClient client = builder.build();
			
			/*
			 * Monta el bot usando JDA (), añade dos event listener, "client" que se encarga
			 * exclusivamente de los comandos y EventListener.class que es una clase propia
			 * a la que se pueden añadir eventos personalizados
			 */
			final JDA jda = new JDABuilder(token).addEventListener(client).addEventListener(new EventListener()).build();
			// Pausa la lectura del código hasta que se inicio el evento "ready"
			jda.awaitReady();
		}catch (Exception e) {
			e.printStackTrace();
		}
	}

}
