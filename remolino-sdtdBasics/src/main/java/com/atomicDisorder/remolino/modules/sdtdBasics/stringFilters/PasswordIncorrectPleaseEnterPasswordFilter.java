package com.atomicDisorder.remolino.modules.sdtdBasics.stringFilters;

import com.atomicDisorder.remolino.commons.filters.StringHubFilterAbstract;



public class PasswordIncorrectPleaseEnterPasswordFilter   extends StringHubFilterAbstract {

	private static PasswordIncorrectPleaseEnterPasswordFilter instance;
	
	public static PasswordIncorrectPleaseEnterPasswordFilter getInstance() {
		if (instance == null) {
			instance = new PasswordIncorrectPleaseEnterPasswordFilter() {
			};
		}
		return instance;
	}
	

	private PasswordIncorrectPleaseEnterPasswordFilter() {
		super.setExampleMatch("Password incorrect, please enter password:");
		super.setRegexMatch("telnetConnector:Password incorrect, please enter password:");
//		super.setResultObjectClassName("PasswordIncorrectPleaseEnterPassword");
	}


	
}
