package com.atomicDisorder.remolino.commons.messages;

import java.util.ArrayList;

import com.atomicDisorder.remolino.commons.filters.StringHubFilter;
import com.atomicDisorder.remolino.commons.filters.StringHubFilterObserver;

public interface StringHubMessageProsumer extends StringHubFilterObserver {
	public StringHub getStringHub();
	public void setStringHub(StringHub stringHub);
	public void checkForStringHubMessages();
	public ArrayList<StringHubFilter> getStringHubFilters();
	
}
