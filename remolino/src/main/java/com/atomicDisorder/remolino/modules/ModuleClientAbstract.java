package com.atomicDisorder.remolino.modules;

import java.util.ArrayList;

import com.atomicDisorder.remolino.filters.FilterAbstract;


/**
 * @author Mariano Blua
 *
 */
public abstract class ModuleClientAbstract extends ModuleAbstract implements Module,ModuleClient {

	private ArrayList<FilterAbstract> ownFilters;
	private ArrayList<String> filtersNameToListen = new ArrayList<String>();
	private ModuleController moduleController;

	public ModuleClientAbstract(ModuleController moduleController) {
		this.setModuleController(moduleController);
	}

	public ArrayList<FilterAbstract> getOwnFilters() {
		if (ownFilters == null) {
			ownFilters = new ArrayList<FilterAbstract>();
		}
		return ownFilters;
	}

	public void setOwnFilters(ArrayList<FilterAbstract> ownFilters) {
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

	public ModuleController getModuleController() {
		return moduleController;
	}

	public void setModuleController(ModuleController moduleController) {
		this.moduleController = moduleController;
	}

}
