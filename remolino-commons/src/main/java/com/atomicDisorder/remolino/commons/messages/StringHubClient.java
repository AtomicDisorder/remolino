package com.atomicDisorder.remolino.commons.messages;

public interface StringHubClient {
	public StringHub getStringHub();
	public void setStringHub(StringHub stringHub);
	public void checkForNewMessage();
}
