package com.atomicDisorder.remolino.modules;

import java.util.ArrayList;

import com.atomicDisorder.remolino.filters.FilterAbstract;
import com.atomicDisorder.remolino.filters.FilterResult;



/**
 * @author Mariano Blua
 *
 */
public interface Module {
	
	public ArrayList<FilterAbstract> getOwnFilters();
	public void setOwnFilters(ArrayList<FilterAbstract> filters);
	public void notify(FilterResult messageObject);
	public void initModule();
	public Thread getModuleThread();
	public void setModuleThread(Thread moduleThread);
	public void saveModuleData();
	public String getName();
	public ArrayList<String> getFiltersNameToListen() ;

	
}
