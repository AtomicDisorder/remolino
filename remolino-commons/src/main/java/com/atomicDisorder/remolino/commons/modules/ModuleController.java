package com.atomicDisorder.remolino.commons.modules;

import com.atomicDisorder.remolino.commons.messages.RawMessage;



/**
 * @author Mariano Blua
 *
 */
public interface ModuleController {
	public boolean addMessageToQueue(RawMessage rawMessage);	
}
