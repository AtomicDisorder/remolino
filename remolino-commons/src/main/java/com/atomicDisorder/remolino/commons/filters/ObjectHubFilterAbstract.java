package com.atomicDisorder.remolino.commons.filters;

import java.lang.reflect.Constructor;
import java.util.ArrayList;
import java.util.regex.Pattern;

import org.apache.log4j.Logger;

/**
 * @author Mariano Blua
 *
 */


public abstract class ObjectHubFilterAbstract implements ObjectHubFilter, Comparable<ObjectHubFilterAbstract> {

	//private String exampleMatch;
	//private String regexMatch;
	//private Pattern regexPattern;
	private boolean processThisMessages = true;
	//private ArrayList<Module> modulesListeners;
	//private ArrayList<String> modulesListenersNames;
	private ArrayList<StringHubFilterObserver> observers;
	private String sourceModule;
	private Logger logger = Logger.getLogger(ObjectHubFilterAbstract.class.getName());

	private int occurrences;

	public void incrementOccurrences() {
		this.occurrences++;
	}

/*	public void setExampleMatch(String pExampleMatch) {
		exampleMatch = pExampleMatch;
	}

	public void setRegexMatch(String pRegexMatch) {
		regexPattern = Pattern.compile(pRegexMatch);
		regexMatch = pRegexMatch;
	}*/

/*	public String getExampleMatch() {
		return exampleMatch;
	}

	public String getRegexMatch() {
		return regexMatch;
	}

	public Pattern getRegexPattern() {
		return regexPattern;
	}*/

/*	public ArrayList<Module> getModulesListeners() {
		return modulesListeners;
	}*/

	public void addObserver(StringHubFilterObserver observer) {

		getObservers().add(observer);
	}

	public boolean apply(Object objectMessage) {
		
		
		logger.warn("COMPARANDO objectMessage.getClass():" + objectMessage.getClass());
		logger.warn("CON this.getClass():" + this.getClass());
		
		
		if (objectMessage.getClass() == this.getClass())
		{
			return true;
		}

		return false;

	/*	if (this.getRegexPattern().matcher(stringMessage).matches()) {
			incrementOccurrences();
			if (this.isProcessThisMessages()) {
				StringHubFilterResult newMessageObject = CreateNewMessageObjectClass(this.getFilterCanonicalName()+ "Result", stringMessage);
					for (StringHubFilterObserver observer : getObservers()) {
						
						observer.notify(newMessageObject);
					}
			}
			return true;
		}
		return false;*/
	}

	private StringHubFilterResult CreateNewMessageObjectClass(String filterResultObjectClassPath, String stringMessage) {
		try {
		
			Class<?> newClass = null;
			try {
				newClass = Class.forName(filterResultObjectClassPath);
			} catch (java.lang.ClassNotFoundException e) {
				logger.warn("NO RESULT OBJECT ->" + filterResultObjectClassPath);
				return null;
			}
			Constructor<?> constructor = newClass.getConstructor(String.class);
			StringHubFilterResult myObject = (StringHubFilterResult) constructor.newInstance(stringMessage);
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

	public int compareTo(ObjectHubFilterAbstract filter) {
		return filter.getOccurrences() - this.getOccurrences();
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

	private ArrayList<StringHubFilterObserver> getObservers() {
		if (observers==null)
		{
			observers=new ArrayList<>();
		}
		return observers;
	}


}
