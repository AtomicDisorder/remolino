package com.atomicDisorder.remolino;

import java.util.ArrayList;

import com.atomicDisorder.remolino.commons.filters.StringFilterAbstract;
import com.atomicDisorder.remolino.commons.filters.StringFilterObserver;
import com.atomicDisorder.remolino.commons.modules.Module;

public class RemolinoCommandFilter extends StringFilterAbstract {

	
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
