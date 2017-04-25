package com.atomicDisorder.remolino.modules.shellCommands;

import com.atomicDisorder.remolino.commons.filters.StringHubFilter;
import com.atomicDisorder.remolino.commons.filters.StringHubFilterObserver;
import com.atomicDisorder.remolino.commons.filters.StringHubFilterResult;
import com.atomicDisorder.remolino.commons.modules.ModuleAbstract;
import com.atomicDisorder.remolino.modules.shellCommands.stringFilters.ShellCommandFilter;
import com.atomicDisorder.remolino.modules.shellCommands.stringFilters.ShellCommandFilterResult;
import com.atomicDisorder.remolino.modules.shellCommands.stringFilters.ShellConsoleFilter;
import com.atomicDisorder.remolino.modules.shellCommands.stringFilters.ShellConsoleFilterResult;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

/**
 * Unit test for simple App.
 */
public class ShellCommands extends ModuleAbstract implements Runnable {

	private boolean enableDirectShellCommands = false;
	
	@Override
	public void run() {
		// TODO Auto-generated method stub
	//	getStringHub().
	}


	@Override
	public void notify(StringHubFilterResult stringFilterResult) {	
		logger.info("*** notify ShellCommands -> " + stringFilterResult.getFilterResultCanonicalClassName());
		switch (stringFilterResult.getFilterResultCanonicalClassName()) {
		case "com.atomicDisorder.remolino.modules.shellCommands.stringFilters.ShellCommandFilterResult": {
	
			if (this.isEnableDirectShellCommands())
			{
				RunShellCommand command = new RunShellCommand();
				ShellCommandFilterResult result = (ShellCommandFilterResult) stringFilterResult;
				command.setCommandToRun(result.getCommand());
				command.run();
			}
		
			break;
		}
		case "com.atomicDisorder.remolino.modules.shellCommands.stringFilters.ShellConsoleFilterResult": {
			
			ShellConsoleFilterResult result = (ShellConsoleFilterResult) stringFilterResult;
			
			if (result.getDataFragments()[1].equalsIgnoreCase("on"))
			{
				logger.warn("ONNNNN");
				this.setEnableDirectShellCommands(true);				
			}
			if (result.getDataFragments()[1].equalsIgnoreCase("off"))
			{
				logger.warn("OFFFFF");
				this.setEnableDirectShellCommands(false);		
			}
			
			break;
		}
		default: {
			logger.warn(this.getClass().getCanonicalName() + " -> NOTIFIED BUT NOT USE -> "
					+ stringFilterResult.getRawStringMessage());
			logger.warn("OBJECT getFilterResultCanonicalClassName -> "
					+ stringFilterResult.getFilterResultCanonicalClassName());

		}
		}
		
	}

	@Override
	public void initModuleFilters() {
		getOwnFilters().add(ShellCommandFilter.getInstance());
		ShellCommandFilter.getInstance().addObserver(this);
		getOwnFilters().add(ShellConsoleFilter.getInstance());
		ShellConsoleFilter.getInstance().addObserver(this);
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


	public boolean isEnableDirectShellCommands() {
		return enableDirectShellCommands;
	}


	public void setEnableDirectShellCommands(boolean enableDirectShellCommands) {
		this.enableDirectShellCommands = enableDirectShellCommands;
	}
	
}

   
