package com.atomicDisorder.remolino.commons.filters;

public interface ObjectHubFilter extends Filter {
	public void addObserver(ObjectHubFilterObserver observer);
	boolean apply(Object rawObjectMessage);
}
