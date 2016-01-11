/**
 * 
 */
package com.atomicDisorder.remolino.commons.utils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Properties;
import java.util.TreeMap;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import java.util.Map.Entry;

import org.apache.log4j.Logger;
import org.apache.log4j.xml.DOMConfigurator;

import com.atomicDisorder.remolino.commons.modules.Module;
import com.atomicDisorder.remolino.commons.modules.ModuleConfiguration;


/**
 * @author Mariano Blua
 *
 */
@XmlRootElement
public class Configurations {

	private Properties parameters = new Properties();
	private Properties invalidParameters = new Properties();
	private Logger logger = Logger.getLogger(Configurations.class.getName());
	private HashMap<String, ModuleConfiguration> modulesConfiguration;
	

	public Configurations() {

	}

	/**
	 * @param args
	 */
	public boolean loadAndValidateConfiguration(Properties defaultProperties, String log4jPropertiesPathName,
			String[] args) {
		getParameters().putAll(defaultProperties);
		String bigParameter = "";
		for (String parameter : args) {
			bigParameter = bigParameter + parameter;
		}

		String[] splitedMainParameter = bigParameter.split("--");
		for (String parameter : splitedMainParameter) {
			if (parameter.trim().length() == 0) {
				continue;
			}
			String[] splitedParameter = parameter.split("=");
			if (splitedParameter.length == 0) {
				getInvalidParameters().put("PARAMETER KEY AND VALUE MISSING NEAR", "");
				continue;
			}
			System.out.println(splitedParameter.length);
			if (splitedParameter.length == 1) {
				getInvalidParameters().put("PARAMETER VALUE MISSING FOR PARAMETER KEY", splitedParameter[0].trim());
				continue;
			}
			if (splitedParameter[0].trim().length() == 0) {
				getInvalidParameters().put("PARAMETER KEY MISSING FOR PARAMETER VALUE", splitedParameter[1].trim());
				continue;
			}
			String previusParameter = getParameters().getProperty(splitedParameter[0]);

			if (previusParameter == null) {
				getInvalidParameters().put(splitedParameter[0].trim(), splitedParameter[1].trim());
				continue;
			}
			if (splitedParameter[0].startsWith("app.") || splitedParameter[0].startsWith("default.")) {
				// This is a parameter that couldnot be changed from outside.
				getInvalidParameters().put(splitedParameter[0].trim(), splitedParameter[1].trim());
				continue;
			}
			getParameters().put(splitedParameter[0], splitedParameter[1]);
		}

		// PropertyConfigurator.configure(getParameters().get(log4jPropertiesPathName).toString());
		DOMConfigurator.configure(getParameters().get(log4jPropertiesPathName).toString());

		logger.info("*** " + getParameters().getProperty("app.version"));
		logger.info("* INITIAL PARAMETERS");
		for (Entry<Object, Object> e : getParameters().entrySet()) {
			logger.info(e.getKey() + " = " + e.getValue());
		}

		if (!getInvalidParameters().isEmpty()) {
			logger.fatal("* INVALID INITIAL PARAMETERS");
			for (Entry<Object, Object> e : getInvalidParameters().entrySet()) {
				if (((String) e.getKey()).startsWith("app.")) {
					logger.fatal("Unmodifible parameter: " + e.getKey() + " (" + e.getValue()+ ")");
				} else {
					logger.fatal("Unknown parameter: " + e.getKey() + " (" + e.getValue()+ ")");
				}

			}
			logger.fatal("Recomendation: Check your invalid parameters, correct or just delete them");
			return false;
		}
		return true;
	}


	
	/**
	 * @return the parameters
	 */
	public Properties getParameters() {
		return parameters;
	}

	/**
	 * @param parameters
	 *            the parameters to set
	 */
	public void setParameters(Properties parameters) {
		if (this.parameters == null) {
			this.parameters = parameters;
		}
	}

	/**
	 * @return the invalidParameters
	 */
	private Properties getInvalidParameters() {
		return invalidParameters;
	}

	/**
	 * @param invalidParameters
	 *            the invalidParameters to set
	 */
	private void setInvalidParameters(Properties invalidParameters) {
		this.invalidParameters = invalidParameters;
	}

	public HashMap<String, ModuleConfiguration> getModulesConfiguration() {
		if (modulesConfiguration==null)
		{
			modulesConfiguration = new HashMap<String, ModuleConfiguration>();
		}
		return modulesConfiguration;
	}

	public void setModulesConfiguration(HashMap<String, ModuleConfiguration> modulesConfiguration) {
		this.modulesConfiguration = modulesConfiguration;
	}



}
