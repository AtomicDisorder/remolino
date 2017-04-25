package com.atomicDisorder.remolino.modules.shellCommands.stringFilters;

import com.atomicDisorder.remolino.commons.filters.StringHubFilterResultAbstract;

public class ShellCommandFilterResult extends StringHubFilterResultAbstract {
	private String[] commandFragments;
	private String command;

	//Todo: Esto arranca como protected y jode todo.
	public ShellCommandFilterResult(String rawStringMessage) {
		super(rawStringMessage);
		//setSplittedCommand(rawStringMessage.split(" "));
		setCommand(rawStringMessage.split("console-command:")[1]);
		//System.out.println("seeee  " + rawStringMessage + " " + getSplittedCommand().length);
		//parse();
	}
	
	/*

	private void parse() {
		String[] dataFragments = super.getRawStringMessage().trim().split(" ");
		setCommandFragments(dataFragments);
	}

	public String[] getCommandFragments() {
		return commandFragments;
	}

	public void setCommandFragments(String[] commandFragments) {
		this.commandFragments = commandFragments;
	}*/
/*
	@Override
	public String getFilterResultCanonicalClassName() {
		//Todo: Esto deberia siempre devolverse asi, pero si va desde abstract entiendoque no funciona.run
		return this.getClass().getCanonicalName();
	}*/
/*
	public String[] getSplittedCommand() {
		return splittedCommand;
	}

	public void setSplittedCommand(String splittedCommand[]) {
		this.splittedCommand = splittedCommand;
	}*/

	public String getCommand() {
		return command;
	}

	public void setCommand(String command) {
		this.command = command;
	}



}
