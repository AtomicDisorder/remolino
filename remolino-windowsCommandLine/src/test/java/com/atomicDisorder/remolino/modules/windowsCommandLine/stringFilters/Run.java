package com.atomicDisorder.remolino.modules.windowsCommandLine.stringFilters;

import com.atomicDisorder.remolino.commons.filters.StringHubFilterAbstract;


public class Run extends StringHubFilterAbstract {

			private static Run instance;
			
			public static Run getInstance() {
				if (instance == null) {
					instance = new Run() {
					};
				}
				return instance;
			}
			

			private Run() {
				super.setExampleMatch("console-command:ls");
				super.setRegexMatch("console-command:.*");
			//	super.set
//				super.setResultObjectClassName("PasswordIncorrectPleaseEnterPassword");
			}
}
