package com.atomicDisorder.remolino.commons.messages;

import java.util.ArrayList;

import com.atomicDisorder.remolino.commons.filters.ObjectHubFilter;


public interface ObjectHub extends Hub{
	public void addObjectMessage(Object objectMessage);
	public void addObjectFilter(ObjectHubFilter objectFilter);
	public void addObjectFiltersAll(ArrayList<ObjectHubFilter> objectFiltersAll);
}
