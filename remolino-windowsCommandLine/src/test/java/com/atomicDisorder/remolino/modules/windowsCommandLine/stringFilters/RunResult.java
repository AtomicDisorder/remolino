package com.atomicDisorder.remolino.modules.windowsCommandLine.stringFilters;

import com.atomicDisorder.remolino.commons.filters.StringHubFilterResultAbstract;

public class RunResult extends StringHubFilterResultAbstract {
	private String splittedCommand[];
	private String command;

	//Todo: Esto arranca como protected y jode todo.
	public RunResult(String rawStringMessage) {
		super(rawStringMessage);
		setSplittedCommand(rawStringMessage.split(" "));
		setCommand(rawStringMessage.split("console-command:")[1]);
		System.out.println("seeee  " + rawStringMessage + " " + getSplittedCommand().length);
	}

	@Override
	public String getFilterResultCanonicalClassName() {
		//Todo: Esto deberia siempre devolverse asi, pero si va desde abstract entiendoque no funciona.run
		return this.getClass().getCanonicalName();
	}

	public String[] getSplittedCommand() {
		return splittedCommand;
	}

	public void setSplittedCommand(String splittedCommand[]) {
		this.splittedCommand = splittedCommand;
	}

	public String getCommand() {
		return command;
	}

	public void setCommand(String command) {
		this.command = command;
	}



}
