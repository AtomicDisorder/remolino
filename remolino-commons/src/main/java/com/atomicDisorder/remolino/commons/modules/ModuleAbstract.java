package com.atomicDisorder.remolino.commons.modules;

import java.util.ArrayList;

import org.apache.log4j.Logger;

import com.atomicDisorder.remolino.commons.filters.ObjectHubFilterObserver;
import com.atomicDisorder.remolino.commons.filters.StringHubFilter;
import com.atomicDisorder.remolino.commons.filters.StringHubFilterAbstract;
import com.atomicDisorder.remolino.commons.filters.StringHubFilterObserver;
import com.atomicDisorder.remolino.commons.messages.ObjectHub;
import com.atomicDisorder.remolino.commons.messages.StringHub;




/**
 * @author Mariano Blua
 *
 */
public abstract class ModuleAbstract implements Module,StringHubFilterObserver,ObjectHubFilterObserver {

	private ArrayList<StringHubFilter> ownFilters;
	private ArrayList<String> filtersNameToListen = new ArrayList<String>();
	private Thread moduleThread;
	protected Logger logger;
	private String name;
	private StringHub stringHub;
	private ObjectHub objectHub; 
	
	public StringHub getStringHub() {
		return stringHub;
	}

	public void setStringHub(StringHub stringHub) {
		this.stringHub = stringHub;
	}
	
	public ObjectHub getObjectHub() {
		return objectHub;
	}

	public void setObjectHub(ObjectHub objectHub) {
		this.objectHub = objectHub;
	}
	
	

	
	
	public ModuleAbstract()
	{
		logger = Logger.getLogger(this.getClass().getName());
	}
	
	public ArrayList<StringHubFilter> getOwnFilters() {
		if (ownFilters == null) {
			ownFilters = new ArrayList<StringHubFilter>();
		}
		return ownFilters;
	}

	public void setOwnFilters(ArrayList<StringHubFilter> ownFilters) {
		this.ownFilters = ownFilters;
	}

	public ArrayList<String> getFiltersNameToListen() {
		if (filtersNameToListen == null) {
			filtersNameToListen = new ArrayList<String>();
		}
		return filtersNameToListen;
	}

	public void setFiltersNameToListen(ArrayList<String> filtersNameToListen) {
		this.filtersNameToListen = filtersNameToListen;
	}

	public String getName() {
		return this.getClass().getSimpleName();
	}
	






	public Thread getModuleThread() {
		return moduleThread;
	}

	public void setModuleThread(Thread moduleThread) {
		this.moduleThread = moduleThread;
	}

	public void setName(String name) {
		this.name = name;
	}

}
