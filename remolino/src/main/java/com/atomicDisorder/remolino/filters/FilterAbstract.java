package com.atomicDisorder.remolino.filters;

import java.lang.reflect.Constructor;
import java.util.ArrayList;
import java.util.regex.Pattern;

import org.apache.log4j.Logger;

import com.atomicDisorder.remolino.messages.RawMessage;
import com.atomicDisorder.remolino.modules.Module;

/**
 * @author Mariano Blua
 *
 */

public abstract class FilterAbstract implements Filter, Comparable<FilterAbstract> {

	private String exampleMatch;
	private String regexMatch;
	private Pattern regexPattern;
	private String resultObjectClass;
	private boolean processThisMessages = true;
	private ArrayList<Module> modulesListeners;
	private ArrayList<String> modulesListenersNames;
	private String sourceModule;
	private Logger logger = Logger.getLogger(FilterAbstract.class.getName());

	private String getClassName() {
		Class<?> enclosingClass = getClass().getEnclosingClass();
		if (enclosingClass != null) {
			return enclosingClass.getName();
		} else {
			return getClass().getName();
		}
	}

	private int occurrences;

	public void incrementOccurrences() {
		this.occurrences++;
	}

	public void setExampleMatch(String pExampleMatch) {
		exampleMatch = pExampleMatch;
	}

	public void setRegexMatch(String pRegexMatch) {
		regexPattern = Pattern.compile(pRegexMatch);
		regexMatch = pRegexMatch;
	}

	public String getExampleMatch() {
		return exampleMatch;
	}

	public String getRegexMatch() {
		return regexMatch;
	}

	public Pattern getRegexPattern() {
		return regexPattern;
	}

	public ArrayList<Module> getModulesListeners() {
		return modulesListeners;
	}

	public void addModuleListener(Module moduleListener) {
		// System.out.println(this.getClassName() + " AGREGADO A LA ESCUCHA" +
		// moduleListener.getName());
		if (modulesListeners == null) {
			modulesListeners = new ArrayList<Module>();
		}
		modulesListeners.add(moduleListener);
	}

	public boolean apply(RawMessage rawMessage) {

		if (!rawMessage.getSourceModule().equals(getSourceModule())) {
			return false;
		}
		if (this.getRegexPattern().matcher(rawMessage.getRawValue()).matches()) {
			incrementOccurrences();
			if (this.isProcessThisMessages()) {
				FilterResult newMessageObject = CreateNewMessageObjectClass(getResultObjectClass(), rawMessage);
				if (modulesListeners != null && newMessageObject != null) {
					for (Module module : modulesListeners) {
						module.notify(newMessageObject);
					}
				}
			}
			return true;
		}
		return false;
	}

	private FilterResult CreateNewMessageObjectClass(String messageObjectClassName, RawMessage rawMessage) {
		try {
			String pathToClass = getResultObjectClass();
			Class newClass = null;
			try {
				newClass = Class.forName(pathToClass);
			} catch (java.lang.ClassNotFoundException e) {
				logger.warn("NO RESULT OBJECT ->" + pathToClass);
				return null;
			}
			Constructor constructor = newClass.getConstructor(RawMessage.class);
			FilterResult myObject = (FilterResult) constructor.newInstance(rawMessage);
			return myObject;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public int getOccurrences() {
		return occurrences;
	}

	public void setOccurrences(int occurrences) {
		this.occurrences = occurrences;
	}

	public boolean isProcessThisMessages() {
		return processThisMessages;
	}

	public void setProcessThisMessages(boolean processThisMessages) {
		this.processThisMessages = processThisMessages;
	}

	public int compareTo(FilterAbstract filter) {
		return filter.getOccurrences() - this.getOccurrences();
	}

	public ArrayList<String> getModulesListenersNames() {
		return modulesListenersNames;
	}

	public void setModulesListenersNames(ArrayList<String> modulesListenersNames) {
		this.modulesListenersNames = modulesListenersNames;
	}

	public String getFilterCanonicalName() {
		return this.toString().split("\\$")[0];
	}

	public void setFilterName(String filterName) {
	}

	public String getSourceModule() {
		return sourceModule;
	}

	public void setSourceModule(String sourceModule) {
		this.sourceModule = sourceModule;
	}

	public String getResultObjectClass() {
		return resultObjectClass;
	}

	public void setResultObjectClass(String resultObjectClass) {
		this.resultObjectClass = resultObjectClass;
	}

}
