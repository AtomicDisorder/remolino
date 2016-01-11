package com.atomicDisorder.remolino.commons.messages;

import com.atomicDisorder.remolino.commons.filters.StringFilter;

public interface StringHub extends Hub{
	public void addStringMessage(String stringMessage);
	public void addStringFilter(StringFilter stringFilter);
}
