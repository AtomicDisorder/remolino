package com.atomicDisorder.remolino.commons.modules;

import java.util.ArrayList;

import com.atomicDisorder.remolino.commons.filters.StringHubFilter;
import com.atomicDisorder.remolino.commons.messages.ObjectHub;
import com.atomicDisorder.remolino.commons.messages.StringHub;
import com.atomicDisorder.remolino.commons.filters.StringHubFilterResult;



/**
 * @author Mariano Blua
 *
 */
public interface Module {
	
	public ArrayList<StringHubFilter> getOwnFilters();
	public void setOwnFilters(ArrayList<StringHubFilter> filters);
	public void notify(StringHubFilterResult messageObject);
	public void initModuleFilters();
	public void initModule();
	public void shutdownModule();
	
	public Thread getModuleThread();
	public void setModuleThread(Thread moduleThread);
	public void saveModuleData();
	public String getName();
	public ArrayList<String> getFiltersNameToListen() ;
	public void setStringHub(StringHub stringHub);
	public void setObjectHub(ObjectHub objectHub);
	public void execute();

	
}
