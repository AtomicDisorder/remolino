package com.atomicDisorder.remolino.commons.filters;

import com.atomicDisorder.remolino.commons.messages.RawMessage;

/**
 * @author Mariano Blua
 *
 */
public abstract class StringFilterResultAbstract  implements StringFilterResult{

	private String rawStringMessage;
	
	protected StringFilterResultAbstract(String rawStringMessage)
	{
		setRawStringMessage(rawStringMessage);
	}
	public String getRawStringMessage() {
		return rawStringMessage;
	}

	public void setRawStringMessage(String rawStringMessage) {
		this.rawStringMessage = rawStringMessage;
	}
	
	
	/*private RawMessage rawMessage;
	
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
*/




}
