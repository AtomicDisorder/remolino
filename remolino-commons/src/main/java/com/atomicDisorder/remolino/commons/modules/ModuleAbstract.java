package com.atomicDisorder.remolino.commons.modules;

import java.util.ArrayList;

import org.apache.log4j.Logger;

import com.atomicDisorder.remolino.commons.filters.StringFilterAbstract;




/**
 * @author Mariano Blua
 *
 */
public abstract class ModuleAbstract implements Module {

	private ArrayList<StringFilterAbstract> ownFilters;
	private ArrayList<String> filtersNameToListen = new ArrayList<String>();
	private Thread moduleThread;
	protected Logger logger;
	private String name;
	
	public ModuleAbstract()
	{
		logger = Logger.getLogger(this.getClass().getName());
	}
	
	public ArrayList<StringFilterAbstract> getOwnFilters() {
		if (ownFilters == null) {
			ownFilters = new ArrayList<StringFilterAbstract>();
		}
		return ownFilters;
	}

	public void setOwnFilters(ArrayList<StringFilterAbstract> ownFilters) {
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
