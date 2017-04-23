package com.atomicDisorder.remolino.messages.objectHub;

import java.util.ArrayList;
import java.util.LinkedList;
import org.apache.log4j.Logger;

import com.atomicDisorder.remolino.commons.filters.ObjectHubFilter;
import com.atomicDisorder.remolino.commons.filters.StringHubFilter;
import com.atomicDisorder.remolino.commons.messages.HubAbstract;

public class ObjectHub extends HubAbstract implements com.atomicDisorder.remolino.commons.messages.ObjectHub, Runnable {
	private LinkedList<Object> objectMessages;
	private ArrayList<ObjectHubFilter> objectFilters;
	private Logger logger = Logger.getLogger(ObjectHub.class.getName());

	public ObjectHub(String name) {
		super(name);
	}



	@Override
	public void addObjectMessage(Object objectMessage) {
		getObjectMessages().addLast(objectMessage);

		logger.debug(objectMessage);

	}




	public void execute()
	{

		if (!getObjectMessages().isEmpty()) {
			Object firstMessageToProcess = getObjectMessages().removeFirst();
			logger.debug("Processing -> " + firstMessageToProcess + " -> " + getObjectFilters().size());
			
			
			for (ObjectHubFilter objectFilter : getObjectFilters())
			{
				
				if (objectFilter.apply(firstMessageToProcess))
				{
					logger.debug(objectFilter.getFilterCanonicalName() + " -> OBJECT MESSAGE MATCH -> " + firstMessageToProcess);
					break;
				}
				else
				{
					logger.debug(objectFilter.getFilterCanonicalName() + " -> OBJECT MESSAGE NO ONE MATCH -> " + firstMessageToProcess);
				}
					
			}	
		}
	}
	
	@Override
	public void run() {
		while (!Thread.currentThread().isInterrupted()) {

			if (!getObjectMessages().isEmpty()) {
				Object firstMessageToProcess = getObjectMessages().removeFirst();
				logger.debug("Processing -> " + firstMessageToProcess + " -> " + getObjectFilters().size());
				
				
				for (ObjectHubFilter objectFilter : getObjectFilters())
				{
					
					if (objectFilter.apply(firstMessageToProcess))
					{
						logger.debug(objectFilter.getFilterCanonicalName() + " -> OBJECT MESSAGE MATCH -> " + firstMessageToProcess);
						break;
					}
					else
					{
						logger.debug(objectFilter.getFilterCanonicalName() + " -> OBJECT MESSAGE NO ONE MATCH -> " + firstMessageToProcess);
					}
						
				}	
			}

		}

	}


	public LinkedList<Object> getObjectMessages() {
		return objectMessages;
	}

	public void setObjectMessages(LinkedList<Object> objectMessages) {
		this.objectMessages = objectMessages;
	}

	@Override
	public void addObjectFilter(ObjectHubFilter objectFilter) {
		
		getObjectFilters().add(objectFilter);
	}


	public ArrayList<ObjectHubFilter> getObjectFilters() {
		return objectFilters;
	}

	public void setObjectFilters(ArrayList<ObjectHubFilter> objectFilters) {
		this.objectFilters = objectFilters;
	}

	@Override
	public void addObjectFiltersAll(ArrayList<ObjectHubFilter> objectFiltersAll) {
	getObjectFilters().addAll(objectFiltersAll);
	}




	
	
}
