package com.atomicDisorder.remolino.filters;

import java.util.ArrayList;
import java.util.regex.Pattern;

import com.atomicDisorder.remolino.messages.RawMessage;
import com.atomicDisorder.remolino.modules.Module;

/**
 * @author Mariano Blua
 *
 */

public interface Filter {
	String getExampleMatch();
	String getRegexMatch();
	Pattern getRegexPattern();
	boolean apply(RawMessage rawMessage);
	ArrayList<Module> getModulesListeners();
	public void addModuleListener(Module moduleListener);
	public String getFilterCanonicalName();
	//String getResultObjectClassName();
	//void setResultObjectClassName(String pResultObjectClassName);
}
