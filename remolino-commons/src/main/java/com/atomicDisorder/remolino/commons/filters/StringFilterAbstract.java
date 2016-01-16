package com.atomicDisorder.remolino.commons.filters;

import java.lang.reflect.Constructor;
import java.util.ArrayList;
import java.util.regex.Pattern;

import org.apache.log4j.Logger;

import com.atomicDisorder.remolino.commons.messages.RawMessage;
import com.atomicDisorder.remolino.commons.modules.Module;

/**
 * @author Mariano Blua
 *
 */

public abstract class StringFilterAbstract implements StringFilter, Comparable<StringFilterAbstract> {

	private String exampleMatch;
	private String regexMatch;
	private Pattern regexPattern;
	private boolean processThisMessages = true;
	//private ArrayList<Module> modulesListeners;
	//private ArrayList<String> modulesListenersNames;
	private ArrayList<StringFilterObserver> observers;
	private String sourceModule;
	private Logger logger = Logger.getLogger(StringFilterAbstract.class.getName());

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

/*	public ArrayList<Module> getModulesListeners() {
		return modulesListeners;
	}*/

	public void addObserver(StringFilterObserver observer) {

		getObservers().add(observer);
	}

	public boolean apply(String stringMessage) {
		//System.out.println("APPLYING " + stringMessage);
	/*	if (!rawMessage.getSourceModule().equals(getSourceModule())) {
			return false;
		}*/
		if (this.getRegexPattern().matcher(stringMessage).matches()) {
			incrementOccurrences();
			if (this.isProcessThisMessages()) {
				StringFilterResult newMessageObject = CreateNewMessageObjectClass(this.getFilterCanonicalName()+ "Result", stringMessage);
				//*if (modulesListeners != null && newMessageObject != null) {
					for (StringFilterObserver observer : getObservers()) {
						
						observer.notify(newMessageObject);
					}
			//	}
			}
			return true;
		}
		return false;
	}

	private StringFilterResult CreateNewMessageObjectClass(String messageObjectClassName, RawMessage rawMessage) {
		try {
			String pathToClass = "getResultObjectClass()"; //TODO: EDITADO PARA QUE NO ROMPA, YA NO SE USA.
			Class newClass = null;
			try {
				newClass = Class.forName(pathToClass);
			} catch (java.lang.ClassNotFoundException e) {
				logger.warn("NO RESULT OBJECT ->" + pathToClass);
				return null;
			}
			Constructor constructor = newClass.getConstructor(RawMessage.class);
			StringFilterResult myObject = (StringFilterResult) constructor.newInstance(rawMessage);
			return myObject;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	
	private StringFilterResult CreateNewMessageObjectClass(String filterResultObjectClassPath, String stringMessage) {
		try {
		
			Class newClass = null;
			try {
				newClass = Class.forName(filterResultObjectClassPath);
			} catch (java.lang.ClassNotFoundException e) {
				logger.warn("NO RESULT OBJECT ->" + filterResultObjectClassPath);
				return null;
			}
			Constructor constructor = newClass.getConstructor(String.class);
			StringFilterResult myObject = (StringFilterResult) constructor.newInstance(stringMessage);
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

	public int compareTo(StringFilterAbstract filter) {
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

	private ArrayList<StringFilterObserver> getObservers() {
		if (observers==null)
		{
			observers=new ArrayList<>();
		}
		return observers;
	}

	private void setObservers(ArrayList<StringFilterObserver> observers) {
		this.observers = observers;
	}


}
