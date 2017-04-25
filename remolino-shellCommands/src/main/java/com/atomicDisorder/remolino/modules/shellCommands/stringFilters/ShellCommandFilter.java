package com.atomicDisorder.remolino.modules.shellCommands.stringFilters;

import com.atomicDisorder.remolino.commons.filters.StringHubFilterAbstract;


public class ShellCommandFilter extends StringHubFilterAbstract {

			private static ShellCommandFilter instance;
			
			public static ShellCommandFilter getInstance() {
				if (instance == null) {
					instance = new ShellCommandFilter() {
					};
				}
				return instance;
			}
			

			private ShellCommandFilter() {
				super.setExampleMatch("console-command:ls");
				super.setRegexMatch("console-command:.*");
				super.setAllowOthersFiltersCheck(true);
			//	super.set
//				super.setResultObjectClassName("PasswordIncorrectPleaseEnterPassword");
			}


		
}
