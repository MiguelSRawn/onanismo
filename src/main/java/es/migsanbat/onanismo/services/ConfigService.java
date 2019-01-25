package es.migsanbat.onanismo.services;

import java.io.File;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

import es.migsanbat.onanismo.domain.Config;

public class ConfigService {
	private static ConfigService instancia;
	private static Config config;
	
	public static ConfigService get() {
		if(instancia==null) {
			instancia = new ConfigService();
		}
		return instancia;
	}
	public ConfigService loadConfig() {
		if(config==null) {
			config = newConfig();
		}
		return this;
	}
	private Config newConfig() {
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
	public Config getConfig() {
		return config;
	}
	public ConfigService addHerokuConfig() {
		if(System.getenv("TOKEN")!=null&&System.getenv("OWNER_ID")!=null) {
			config.setToken(System.getenv("TOKEN"));
			config.setOwnerId(System.getenv("OWNER_ID"));
		}
//		if(System.getenv("MANTENIMIENTO")!=null) {
//			config.setMantenimiento(Boolean.parseBoolean(System.getenv("MANTENIMIENTO")));
//		}
		return this;
	}
	
}
