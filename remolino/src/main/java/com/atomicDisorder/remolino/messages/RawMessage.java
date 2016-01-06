/**
 * 
 */
package com.atomicDisorder.remolino.messages;

import com.atomicDisorder.remolino.modules.Module;

/**
 * @author Mariano Blua
 *
 * 
 */
public class RawMessage{
	
	private String sourceModule="";
	private String rawValue="";
	private long numberId = -1;
	
	
	public RawMessage(Module sourceModule,String rawValue)
	{
		setSource(sourceModule.getClass().getCanonicalName());
		setRawValue(rawValue);
	}
	/**
	 * @param rawValue
	 */
	public RawMessage(String rawValue) {
		this.setRawValue(rawValue);
	}
	
	
	


	/**
	 * @return the rawValue
	 */
	public String getRawValue() {
		return rawValue;
	}

	/**
	 * @param rawValue the rawValue to set
	 */
	private void setRawValue(String rawValue) {
		this.rawValue = rawValue;
	}




	public long getNumberId() {
		return numberId;
	}




	public void setNumberId(long numberId) {
		this.numberId = numberId;
	}




	public String getSourceModule() {
		return sourceModule;
	}




	private void setSource(String source) {
		this.sourceModule = source;
	}


}
