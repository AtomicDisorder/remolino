package com.atomicDisorder.remolino.modules.sdtdBasics.stringFilters;

import com.atomicDisorder.remolino.commons.filters.StringHubFilterAbstract;



public class PleaseEnterPasswordFilter   extends StringHubFilterAbstract {

	private static PleaseEnterPasswordFilter instance;
	
	public static PleaseEnterPasswordFilter getInstance() {
		if (instance == null) {
			instance = new PleaseEnterPasswordFilter() {
			};
		}
		return instance;
	}
	

	private PleaseEnterPasswordFilter() {
		super.setExampleMatch("Please enter password:");
		super.setRegexMatch("telnetConnector:Please enter password:");
//		super.setResultObjectClassName("PasswordIncorrectPleaseEnterPassword");
	}
}
