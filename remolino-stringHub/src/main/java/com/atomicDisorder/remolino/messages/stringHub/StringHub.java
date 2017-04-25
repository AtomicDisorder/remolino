package com.atomicDisorder.remolino.messages.stringHub;

import java.util.ArrayList;
import java.util.LinkedList;
import org.apache.log4j.Logger;

import com.atomicDisorder.remolino.commons.filters.StringHubFilter;
import com.atomicDisorder.remolino.commons.messages.HubAbstract;

public class StringHub extends HubAbstract implements com.atomicDisorder.remolino.commons.messages.StringHub, Runnable {
	private LinkedList<String> stringMessages;
	private ArrayList<StringHubFilter> stringFilters;
	private Logger logger = Logger.getLogger(StringHub.class.getName());

	public StringHub(String name) {
		super(name);
	}

	private LinkedList<String> getStringMessages() {
		if (stringMessages == null) {
			stringMessages = new LinkedList<>();
		}
		return stringMessages;
	}

	@Override
	public void addStringMessage(String stringMessage) {
		getStringMessages().addLast(stringMessage);

		logger.debug(stringMessage);

	}

	@Override
	public void addStringFilter(StringHubFilter stringFilter) {
		getStringsFilters().add(stringFilter);

	}

	private ArrayList<StringHubFilter> getStringsFilters() {
		if (stringFilters == null) {
			stringFilters = new ArrayList<>();
		}
		return stringFilters;
	}

	public void execute()
	{

		if (!getStringMessages().isEmpty()) {
			String firstMessageToProcess = getStringMessages().removeFirst();
			logger.debug("Processing -> " + firstMessageToProcess + " -> " + getStringsFilters().size());
			
			
			for (StringHubFilter stringFilter : getStringsFilters())
			{
				
				if (stringFilter.apply(firstMessageToProcess))
				{
					logger.debug(stringFilter.getFilterCanonicalName() + " -> MATCH -> " + firstMessageToProcess);
					logger.debug(stringFilter.getFilterCanonicalName() + " -> ALLOW -> " + stringFilter.isAllowOthersFiltersCheck());
					
					if (!stringFilter.isAllowOthersFiltersCheck())
					{
						break;
					}
				}
				else
				{
					logger.debug(stringFilter.getFilterCanonicalName() + " -> NO ONE MATCH -> " + firstMessageToProcess);
				}
					
			}	
		}
	}
	
	@Override
	public void run() {
		while (!Thread.currentThread().isInterrupted()) {

			if (!getStringMessages().isEmpty()) {
				String firstMessageToProcess = getStringMessages().removeFirst();
				//logger.debug("Processing -> " + firstMessageToProcess + " -> " + getStringsFilters().size());
				
				
				for (StringHubFilter stringFilter : getStringsFilters())
				{
					
					if (stringFilter.apply(firstMessageToProcess))
					{
						logger.debug(stringFilter.getFilterCanonicalName() + " -> MATCH -> " + firstMessageToProcess);
						logger.debug(stringFilter.getFilterCanonicalName() + " -> ALLOW -> " + stringFilter.isAllowOthersFiltersCheck());
						
						if (!stringFilter.isAllowOthersFiltersCheck())
						{
							break;
						}
					}
					else
					{
						logger.debug(stringFilter.getFilterCanonicalName() + " -> NO ONE MATCH -> " + firstMessageToProcess);
					}
						
				}	
			}

		}

	}

	@Override
	public void addStringFiltersAll(ArrayList<StringHubFilter> stringFiltersAll) {
		getStringsFilters().addAll(stringFiltersAll);

		
	}
	
	
}
