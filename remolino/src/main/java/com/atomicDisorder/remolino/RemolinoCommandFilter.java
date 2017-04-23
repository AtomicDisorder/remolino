package com.atomicDisorder.remolino;

import com.atomicDisorder.remolino.commons.filters.StringHubFilterAbstract;

public class RemolinoCommandFilter extends StringHubFilterAbstract {

	
	private static RemolinoCommandFilter instance;
	
	public static RemolinoCommandFilter getInstance() {
		if (instance == null) {
			instance = new RemolinoCommandFilter() {
			};
		}
		return instance;
	}
	
	
	private RemolinoCommandFilter() {
		super.setProcessThisMessages(true);
		super.setExampleMatch("console-command:rm shutdown");
		super.setRegexMatch("console-command:rm .*");
	}








}
