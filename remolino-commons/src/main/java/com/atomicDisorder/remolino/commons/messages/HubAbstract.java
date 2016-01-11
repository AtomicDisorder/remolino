package com.atomicDisorder.remolino.commons.messages;

public abstract class HubAbstract implements Hub {
	private String name;
	private Thread thread;
	
	
	protected HubAbstract(String name)
	{
		
	}
	public String getName() {
		return name;
	}

	private void setName(String name) {
		this.name = name;
	}
	
	public Thread getThread() {
		return thread;
	}
	
	public void setThread(Thread thread) {
		this.thread = thread;
	}
	
}
