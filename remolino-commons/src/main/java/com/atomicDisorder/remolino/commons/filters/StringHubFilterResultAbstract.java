package com.atomicDisorder.remolino.commons.filters;

/**
 * @author Mariano Blua
 *
 */
public abstract class StringHubFilterResultAbstract  implements StringHubFilterResult{

	private String rawStringMessage;
	private String[] dataFragments;
	
	protected StringHubFilterResultAbstract(String rawStringMessage)
	{
		setRawStringMessage(rawStringMessage);
		parse();
	}
	public String getRawStringMessage() {
		return rawStringMessage;
	}

	public void setRawStringMessage(String rawStringMessage) {
		this.rawStringMessage = rawStringMessage;
	}
	
	private void parse() {
		String[] dataFragments = getRawStringMessage().trim().split(" ");
		setDataFragments(dataFragments);
	}

	public String[] getDataFragments() {
		return dataFragments;
	}

	public void setDataFragments(String[] dataFragments) {
		this.dataFragments = dataFragments;
	}

	public String getFilterResultCanonicalClassName()
	{
		return this.getClass().getCanonicalName();
	}
	
}
