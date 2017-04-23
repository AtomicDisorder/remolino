package com.atomicDisorder.remolino.commons.messages;

import java.util.ArrayList;

import com.atomicDisorder.remolino.commons.filters.StringHubFilter;

public interface StringHub extends Hub{
	public void addStringMessage(String stringMessage);
	public void addStringFilter(StringHubFilter stringFilter);
	public void addStringFiltersAll(ArrayList<StringHubFilter> stringFiltersAll);
}
