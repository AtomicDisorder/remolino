package com.atomicDisorder.remolino.commons.messages;

public interface Hub {
	public String getName();
	public void setThread(Thread thread);
	public Thread getThread();
	public void execute();

	
}
