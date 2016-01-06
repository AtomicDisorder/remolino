package com.atomicDisorder.remolino.filters;

import com.atomicDisorder.remolino.messages.RawMessage;

/**
 * @author Mariano Blua
 *
 */
public abstract class FilterResultAbstract  implements FilterResult{

	private RawMessage rawMessage;
	
	public FilterResultAbstract(RawMessage rawMessage)
	{
		setRawMessage(rawMessage);
	}
	


	public String getResultObjectClassName() {
		return this.getClass().getName();
	}



	public RawMessage getRawMessage() {
		return rawMessage;
	}



	private void setRawMessage(RawMessage rawMessage) {
		this.rawMessage = rawMessage;
	}

}
