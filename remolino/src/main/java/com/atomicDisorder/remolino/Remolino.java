/**
 * 
 */
package com.atomicDisorder.remolino;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.Map.Entry;

import org.apache.log4j.Logger;

import com.atomicDisorder.remolino.commons.filters.StringHubFilter;
import com.atomicDisorder.remolino.commons.filters.StringHubFilterResult;
import com.atomicDisorder.remolino.commons.messages.ObjectHub;
import com.atomicDisorder.remolino.commons.messages.StringHub;
import com.atomicDisorder.remolino.commons.messages.StringHubMessageProsumer;
import com.atomicDisorder.remolino.commons.modules.Module;
import com.atomicDisorder.remolino.commons.modules.ModuleAbstract;
import com.atomicDisorder.remolino.commons.modules.ModuleConfiguration;
import com.atomicDisorder.remolino.commons.persistance.JAXBProvider;
import com.atomicDisorder.remolino.commons.utils.Configurations;
import com.atomicDisorder.remolino.commons.utils.Reflexion;

/**
 * @author Mariano Blua
 *
 */
public class Remolino extends ModuleAbstract {

	private static Configurations configurations;
	private static HashMap<String, Module> modules;
	private static Logger logger = Logger.getLogger(Remolino.class.getName());
	private static Remolino instance;

	private Remolino() {

		JAXBProvider.marshalClassToFile(Remolino.getConfigurations(), "Remolino.Configurations.xml");

		// Load /Create StringHubs
		// Deberían ser multiples y creables desde la configuracion.
		StringHub mainStringHub = new com.atomicDisorder.remolino.messages.stringHub.StringHub("mainStringHub");
		ObjectHub mainObjectHub = new com.atomicDisorder.remolino.messages.objectHub.ObjectHub("mainObjectHub");

		//Add Remolino as a Module
		getModules().put("remolino", this);
		
		// List and Create all modulesConfigurations				
		logger.info("*** Loading Configured Modules ***");
		for (Entry<String, ModuleConfiguration> element : getConfigurations().getModulesConfiguration().entrySet()) {
			logger.info("* Loading " + element.getKey() + " Module. Type: " + element.getValue().getModuleType());
			Module newModuleInstance = Reflexion.getModuleInstance(element.getValue());
			newModuleInstance.setStringHub(mainStringHub);
			newModuleInstance.setObjectHub(mainObjectHub);
			if (newModuleInstance != null) {
				getModules().put(element.getKey(), newModuleInstance);
				logger.info("Correctly added.");
			}

		}
		
		// Init all filters modules
		logger.info("*** Init filters from modules ***");
		for (Entry<String, Module> element : getModules().entrySet()) {
			Module currentModule = element.getValue();
			currentModule.initModuleFilters();
			logger.info(currentModule.getClass().getCanonicalName() + " -> Init module");	
		}
		
		// Get all modules String Filters
		logger.info("*** Get all modules String Filters ***");
		for (Entry<String, Module> element : getModules().entrySet()) {
			Module currentModule = element.getValue();
			mainStringHub.addStringFiltersAll(currentModule.getOwnFilters());
			logger.info(currentModule.getClass().getCanonicalName() + " -> Init module");	
		}
		
		// Get all Object String Filters
		logger.info("*** Get all modules Object Filters ***");
		for (Entry<String, Module> element : getModules().entrySet()) {
			Module currentModule = element.getValue();
			mainStringHub.addStringFiltersAll(currentModule.getOwnFilters());
			logger.info(currentModule.getClass().getCanonicalName() + " -> Init module");	
		}
		

		// Init all modules
		logger.info("*** Init all modules ***");
		for (Entry<String, Module> element : getModules().entrySet()) {
			Module currentModule = element.getValue();
			currentModule.initModule();
			logger.info(currentModule.getClass().getCanonicalName() + " -> Init module");	
		}
		
		// List and Create all modulesConfigurations
/*		logger.info("*** Running all runnables modules ***");
		for (Entry<String, Module> element : getModules().entrySet()) {
			Module currentModule = element.getValue();
			if (currentModule instanceof Runnable) {
				currentModule.setModuleThread(new Thread((Runnable) currentModule));
				logger.info(currentModule.getClass().getCanonicalName() + " -> Starting with thread id: "
						+ currentModule.getModuleThread().getId());
				currentModule.getModuleThread().start();

			}
		}*/

		// Add Remolino basic Filters to mainhub
		setStringHub(mainStringHub);

		

		// Starting all Hubs
		
	/*	mainStringHub.setThread(new Thread((Runnable) mainStringHub));
		mainStringHub.getThread().start();*/
		
		logger.info("*** Initialized ***");
		while (true)
		{
			//System.out.println("EJECUTANDO");
			for (Entry<String, Module> element : getModules().entrySet()) {
				
				Module currentModule = element.getValue();
			//	System.out.println("EJECUTANDO " + currentModule.getName());
				currentModule.execute();
				
			}
			//System.out.println("EJECUTANDO2");
			mainStringHub.execute();
		//	System.out.println("EJECUTANDO3");
		}
		// mainStringHub.(new Thread((Runnable)currentModule));
		// mainStringHub.getModuleThread().start();
	}

	private void shutdownModules() {
		// List and Create all modulesConfigurations
		logger.info("*** Shutting down all runnables modules ***");
		for (Entry<String, Module> element : getModules().entrySet()) {
			Module currentModule = element.getValue();
			if (currentModule instanceof Runnable) {
				currentModule.getModuleThread().interrupt();
				logger.info(currentModule.getClass().getCanonicalName() + " -> Thread interrupted "
						+ currentModule.getModuleThread().getId());
			}
		}
		logger.info("* All modules shutted down");
	}

	private void saveModules() {
		// List and Create all modulesConfigurations
		logger.info("*** Saving all modules ***");
		for (Entry<String, Module> element : getModules().entrySet()) {
			Module currentModule = element.getValue();

			currentModule.saveModuleData();
			logger.info(currentModule.getClass().getCanonicalName() + " -> Data saved");

		}
		logger.info("* All modules saved");
	}

	public static Remolino getInstance() {
		if (instance == null) {
			instance = new Remolino();
		}
		return instance;
	}

	public static void main(String[] args) {
		Properties defaultProperties = new Properties();

		defaultProperties.put("app.version", "0.1");
		defaultProperties.put("log4j.properties.path", ".//properties//log4j.xml");
		// defaultProperties.put("properties.path", ".//properties");
		defaultProperties.put("main.path", ".//");

		/*
		 * defaultProperties.put("default.controller.name",
		 * "remolino-controller");
		 * defaultProperties.put("default.controller.jar",
		 * "remolino-controller.jar");
		 * defaultProperties.put("default.controller.pathToMainClass",
		 * "com.atomicDisorder.remolino.modules.controller.Controller");
		 */

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

		Remolino.getInstance();

	}

	private static void loadDefaultModules() {
		boolean defaultAdded = false;
		/*
		 * String defaultControllerName =
		 * configurations.getParameters().getProperty("default.controller.name")
		 * ; ModuleConfiguration defaultControllerConfiguration =
		 * getConfigurations().getModulesConfiguration()
		 * .get(defaultControllerName); if (defaultControllerConfiguration ==
		 * null) { defaultControllerConfiguration = new ModuleConfiguration();
		 * defaultControllerConfiguration.setName(defaultControllerName);
		 * defaultControllerConfiguration.setModuleType(ModuleConfiguration.
		 * ModuleTypes.Controller); defaultControllerConfiguration
		 * .setCompletePathToJar(configurations.getParameters().getProperty(
		 * "default.controller.jar"));
		 * defaultControllerConfiguration.setCompletePathToMainClass(
		 * getConfigurations().getParameters().getProperty(
		 * "default.controller.pathToMainClass"));
		 * configurations.getModulesConfiguration().put(defaultControllerName,
		 * defaultControllerConfiguration); defaultAdded = true; }
		 */
		String defaultConsoleName = getConfigurations().getParameters().getProperty("default.console.name");
		ModuleConfiguration defaultConsoleConfiguration = configurations.getModulesConfiguration()
				.get(defaultConsoleName);
		if (defaultConsoleConfiguration == null) {
			defaultConsoleConfiguration = new ModuleConfiguration();
			defaultConsoleConfiguration.setName(defaultConsoleName);
			defaultConsoleConfiguration.setModuleType(ModuleConfiguration.ModuleTypes.Client);
			defaultConsoleConfiguration
					.setCompletePathToJar(configurations.getParameters().getProperty("default.console.jar"));
			defaultConsoleConfiguration.setCompletePathToMainClass(
					configurations.getParameters().getProperty("default.console.pathToMainClass"));
			configurations.getModulesConfiguration().put(defaultConsoleName, defaultConsoleConfiguration);
			defaultAdded = true;

		}
		if (defaultAdded) {
			saveConfigurations();
		}

	}

	public static void saveConfigurations() {
		JAXBProvider.marshalClassToFile(configurations, "Remolino.Configurations.xml");
		/*
		 * if (configurations == null) { configurations = (Configurations)
		 * JAXBProvider.unmarshalFromFileOrCreate(Configurations.class,
		 * "Remolino.Configurations.xml"); }
		 */

	}

	public static Configurations getConfigurations() {
		if (configurations == null) {
			configurations = (Configurations) JAXBProvider.unmarshalFromFileOrCreate(Configurations.class,
					"Remolino.Configurations.xml");
			loadDefaultModules();
		}
		return configurations;
	}

	public static void setConfigurations(Configurations configurations) {
		if (configurations == null) {
			Remolino.configurations = configurations;
		}
	}

	public static HashMap<String, Module> getModules() {
		if (modules == null) {
			modules = new HashMap<String, Module>();
		}
		return modules;
	}

	public static boolean saveAllModulesData() {
		logger.info("******* Saving Modules *******");
		java.util.Iterator<Entry<String, Module>> iterator = getModules().entrySet().iterator();
		while (iterator.hasNext()) {
			Map.Entry pair = (Map.Entry) iterator.next();
			Module module = (Module) pair.getValue();
			module.saveModuleData();
			logger.info("** " + module.getName() + " saved.");

		}
		JAXBProvider.marshalClassToFile(getConfigurations().getModulesConfiguration(), "ModulesController");
		return true;
	}

	@Override
	public void notify(StringHubFilterResult stringFilterResult) {
		logger.info("*** notify Remolino");
		switch (stringFilterResult.getRawStringMessage().toLowerCase()) {
		case "console-command:rm shutdown": {
			logger.info("*** Remolino shutting down modules normally");
			
			shutdownModules();
			logger.info("*** Remolino shutting down normally");
			System.exit(0);
			break;
		}
		case "console-command:rm save": {

			
			saveModules();
			break;
		}
		
		
		default: {
			logger.warn(this.getClass().getCanonicalName() + " -> NOTIFIED BUT NOT USE -> "
					+ stringFilterResult.getRawStringMessage());

		}
		}
	}

	
	



	@Override
	public void initModule() {
		
	}

	@Override
	public void shutdownModule() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void saveModuleData() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void execute() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void initModuleFilters() {
		getOwnFilters().add(RemolinoCommandFilter.getInstance());
		RemolinoCommandFilter.getInstance().addObserver(this);	
	}

}
