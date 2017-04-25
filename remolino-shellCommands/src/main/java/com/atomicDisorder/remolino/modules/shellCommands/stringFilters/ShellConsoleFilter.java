package com.atomicDisorder.remolino.modules.shellCommands.stringFilters;

import com.atomicDisorder.remolino.commons.filters.StringHubFilterAbstract;


public class ShellConsoleFilter extends StringHubFilterAbstract {

			private static ShellConsoleFilter instance;
			
			public static ShellConsoleFilter getInstance() {
				if (instance == null) {
					instance = new ShellConsoleFilter() {
					};
				}
				return instance;
			}
			

			private ShellConsoleFilter() {
				super.setExampleMatch("console-command:sc on");
				super.setRegexMatch("console-command:sc .*");
			//	super.set
//				super.setResultObjectClassName("PasswordIncorrectPleaseEnterPassword");
			}
}
