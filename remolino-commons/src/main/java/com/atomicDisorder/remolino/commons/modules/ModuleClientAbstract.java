package com.atomicDisorder.remolino.commons.modules;

import java.util.ArrayList;

import com.atomicDisorder.remolino.commons.filters.StringFilterAbstract;



/**
 * @author Mariano Blua
 *
 */
public abstract class ModuleClientAbstract extends ModuleAbstract implements ModuleClient {

	private ArrayList<StringFilterAbstract> ownFilters;
	private ArrayList<String> filtersNameToListen = new ArrayList<String>();
	//private ModuleController moduleController;

/*	public ModuleClientAbstract(ModuleController moduleController) {
		this.setModuleController(moduleController);
	}*/
	public ModuleClientAbstract()
	{
		
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

	public void setName(String name) {
	}
/*
	public ModuleController getModuleController() {
		return moduleController;
	}

	public void setModuleController(ModuleController moduleController) {
		this.moduleController = moduleController;
	}
*/
}
