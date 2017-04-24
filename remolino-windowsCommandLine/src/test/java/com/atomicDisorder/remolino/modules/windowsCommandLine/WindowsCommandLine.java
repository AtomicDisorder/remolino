package com.atomicDisorder.remolino.modules.windowsCommandLine;

import com.atomicDisorder.remolino.commons.filters.StringHubFilter;
import com.atomicDisorder.remolino.commons.filters.StringHubFilterObserver;
import com.atomicDisorder.remolino.commons.filters.StringHubFilterResult;
import com.atomicDisorder.remolino.commons.modules.ModuleAbstract;
import com.atomicDisorder.remolino.modules.windowsCommandLine.stringFilters.Run;
import com.atomicDisorder.remolino.modules.windowsCommandLine.stringFilters.RunResult;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

/**
 * Unit test for simple App.
 */
public class WindowsCommandLine extends ModuleAbstract implements Runnable {

	@Override
	public void run() {
		// TODO Auto-generated method stub
	//	getStringHub().
	}


	@Override
	public void notify(StringHubFilterResult messageObject) {
		logger.info("*** notify WindowsCommand");
		switch (messageObject.getFilterResultCanonicalClassName()) {
		case "com.atomicDisorder.remolino.modules.windowsCommandLine.stringFilters.RunResult": {
	
			
			WindowsConsoleCommand command = new WindowsConsoleCommand();
			RunResult result = (RunResult) messageObject;
			command.setCommandToRun(result.getCommand());
			command.run();
		
			break;
		}
		case "console-command:run a": {
	
			
			logger.info("RUNEANDO A");
		
			break;
		}
		
		
		default: {
			logger.warn(this.getClass().getCanonicalName() + " -> NOTIFIED BUT NOT USE -> "
					+ messageObject.getRawStringMessage());
			logger.warn("OBJECT getFilterResultCanonicalClassName -> "
					+ messageObject.getFilterResultCanonicalClassName());

		}
		}
		
	}

	@Override
	public void initModuleFilters() {
		// TODO Auto-generated method stub
	/*	getOwnFilters().add(PasswordIncorrectPleaseEnterPasswordFilter.getInstance());
		PasswordIncorrectPleaseEnterPasswordFilter.getInstance().addObserver(this);
		
		getOwnFilters().add(PleaseEnterPasswordFilter.getInstance());
		PleaseEnterPasswordFilter.getInstance().addObserver(this);*/
		
		
		//StringHubFilter runCommand = new Str
		
		getOwnFilters().add(Run.getInstance());
		Run.getInstance().addObserver(this);
		
		
	}

	@Override
	public void initModule() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void shutdownModule() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void saveModuleData() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void execute() {
		// TODO Auto-generated method stub
		
	}
	
}

   
