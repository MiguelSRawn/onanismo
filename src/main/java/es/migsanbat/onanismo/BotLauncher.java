package es.migsanbat.onanismo;

import java.io.File;

import javax.security.auth.login.LoginException;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

import com.jagrosh.jdautilities.command.CommandClient;
import com.jagrosh.jdautilities.command.CommandClientBuilder;
import com.jagrosh.jdautilities.command.Command.Category;

import es.migsanbat.onanismo.commands.meta.Ping;
import es.migsanbat.onanismo.domain.Config;
import net.dv8tion.jda.core.JDA;
import net.dv8tion.jda.core.JDABuilder;

public class BotLauncher {

	public static void main(String[] args) throws LoginException, InterruptedException {

		// Cargando configuración del bot
		Config config = loadConfig();
		String token = config.getToken();
		String ownerId = config.getOwnerId();
		

		/*
		 * Inicio del bot
		 */
		final CommandClientBuilder builder = new CommandClientBuilder();
		// Opciones del bot
		builder.setPrefix("+");
		// Categorias
		final Category fap = new Category("Fap");
		final Category meta = new Category("Meta");
		// Comandos Meta
		builder.addCommands(new Ping(meta));
		
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
	}

	private static Config loadConfig() {
		Config config = null;
		try {
			JAXBContext context = JAXBContext.newInstance(Config.class);
			Unmarshaller unmarshaller = context.createUnmarshaller();
			config = (Config) unmarshaller.unmarshal(new File("src/main/resource/local_config.xml"));

		} catch (JAXBException e) {
			e.printStackTrace();
		}
		return config;
	}

}
