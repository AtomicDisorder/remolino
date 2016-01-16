package com.atomicDisorder.remolino.commons.modules;

import com.atomicDisorder.remolino.commons.messages.StringHub;
import com.atomicDisorder.remolino.commons.messages.StringHubClient;

public abstract class ModuleStringHubClientAbstract extends ModuleClientAbstract implements StringHubClient {
	private StringHub stringHub; 
	
	public StringHub getStringHub() {
		return stringHub;
	}

	public void setStringHub(StringHub stringHub) {
		this.stringHub = stringHub;
	}

}
