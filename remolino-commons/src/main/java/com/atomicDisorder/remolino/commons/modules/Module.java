package com.atomicDisorder.remolino.commons.modules;

import java.util.ArrayList;

import com.atomicDisorder.remolino.commons.filters.StringFilterAbstract;
import com.atomicDisorder.remolino.commons.messages.StringHub;
import com.atomicDisorder.remolino.commons.filters.StringFilterResult;



/**
 * @author Mariano Blua
 *
 */
public interface Module {
	
	public ArrayList<StringFilterAbstract> getOwnFilters();
	public void setOwnFilters(ArrayList<StringFilterAbstract> filters);
	public void notify(StringFilterResult messageObject);
	public void initModule();
	public void shutdownModule();
	
	public Thread getModuleThread();
	public void setModuleThread(Thread moduleThread);
	public void saveModuleData();
	public String getName();
	public ArrayList<String> getFiltersNameToListen() ;
	public void setStringHub(StringHub stringHub);
	public void execute();

	
}
