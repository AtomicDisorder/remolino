package com.atomicDisorder.remolino.commons.filters;

public interface StringHubFilter extends Filter {
	public void addObserver(StringHubFilterObserver observer);
	boolean apply(String rawObjectMessage);
	boolean isAllowOthersFiltersCheck();
}
