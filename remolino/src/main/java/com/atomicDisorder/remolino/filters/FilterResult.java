package com.atomicDisorder.remolino.filters;

import com.atomicDisorder.remolino.messages.RawMessage;

/**
 * @author Mariano Blua
 *
 */
public interface FilterResult {
	
	public RawMessage getRawMessage();
	public String getResultObjectClassName();
	
}
