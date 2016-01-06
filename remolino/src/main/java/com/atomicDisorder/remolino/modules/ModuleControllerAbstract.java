package com.atomicDisorder.remolino.modules;


import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Map;

import com.atomicDisorder.remolino.messages.RawMessage;


/**
 * @author Mariano Blua
 *
 */
public abstract class ModuleControllerAbstract extends ModuleAbstract implements Module,ModuleController {

	private LinkedList<RawMessage> messagesQueue;
	private HashMap<String, Module> modules;
	
	protected LinkedList<RawMessage> getMessagesQueue() {
		if (messagesQueue == null) {
			messagesQueue = new LinkedList<RawMessage>();
		}
		return messagesQueue;
	}
	
	private void setMessagesQueue(LinkedList<RawMessage> messagesQueue) {
		this.messagesQueue = messagesQueue;
	}
	
	protected void shutdownModules()
	{
		logger.info("******* Shuting Down Modules *******");
		Iterator iterator = getModules().entrySet().iterator();
		while (iterator.hasNext()) {
			Map.Entry pair = (Map.Entry) iterator.next();
			Module module = (Module) pair.getValue();
			if (module instanceof Runnable)
			{
				module.getModuleThread().interrupt();
			}
			logger.info("** " + module.getName() + " shuted down.");

		}
	}

	public HashMap<String, Module> getModules() {
		if (modules == null) {
			setModules(new HashMap<String, Module>());
		}
		return modules;
	}

	private void setModules(HashMap<String, Module> modules) {
		this.modules = modules;
	}
}
