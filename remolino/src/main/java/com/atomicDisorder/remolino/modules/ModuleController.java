package com.atomicDisorder.remolino.modules;

import com.atomicDisorder.remolino.messages.RawMessage;



/**
 * @author Mariano Blua
 *
 */
public interface ModuleController {
	public boolean addMessageToQueue(RawMessage rawMessage);	
}
