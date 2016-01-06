/**
 * 
 */
package com.atomicDisorder.remolino;

import java.util.ArrayList;
import java.util.Properties;
import org.apache.log4j.Logger;

import com.atomicDisorder.remolino.modules.ModuleConfiguration;
import com.atomicDisorder.remolino.persistance.JAXBProvider;
import com.atomicDisorder.remolino.utils.Configurations;

/**
 * @author Mariano Blua
 *
 */
public class Remolino {

	private static Configurations configurations;

	public static void main(String[] args) {
		Logger logger = Logger.getLogger(Remolino.class.getName());

		Properties defaultProperties = new Properties();

		defaultProperties.put("app.version", "0.1");
		defaultProperties.put("log4j.properties.path", ".//properties//log4j.xml");
		defaultProperties.put("main.path", ".//");

		defaultProperties.put("default.controller.name", "remolino-controller");
		defaultProperties.put("default.controller.jar", "remolino-controller.jar");
		defaultProperties.put("default.controller.pathToMainClass",
				"com.atomicDisorder.remolino.modules.controller.Controller");

		defaultProperties.put("default.console.name", "remolino-console");
		defaultProperties.put("default.console.jar", "remolino-console.jar");
		defaultProperties.put("default.console.pathToMainClass", "com.atomicDisorder.remolino.modules.console.Console");
		
		defaultProperties.put("use.defaults", "true");
		
		boolean validConfiguration = getConfigurations().loadAndValidateConfiguration(defaultProperties,
				"log4j.properties.path", args);
		if (!validConfiguration) {
			logger.fatal("FAIL STARTING Remolino " + getConfigurations().getParameters().getProperty("app.version"));
			System.exit(1);
		}

		 JAXBProvider.marshalClassToFile(Remolino.getConfigurations(),"Remolino.Configurations.xml");
	}





	public static Configurations getConfigurations() {
		if (configurations == null) {
			configurations = (Configurations) JAXBProvider.unmarshalFromFileOrCreate(Configurations.class,
					"Remolino.Configurations.xml");
		}
		return configurations;
	}

	public static void setConfigurations(Configurations configurations) {
		if (configurations == null) {
			Remolino.configurations = configurations;
		}
	}

}
