package com.atomicDisorder.remolino.commons.modules;


import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Map;

import com.atomicDisorder.remolino.commons.messages.RawMessage;


/**
 * @author Mariano Blua
 *
 */
public abstract class ModuleControllerAbstract implements Module,ModuleController {

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
	
}
