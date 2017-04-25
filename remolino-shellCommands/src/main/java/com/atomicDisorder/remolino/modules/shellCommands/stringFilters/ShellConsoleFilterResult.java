package com.atomicDisorder.remolino.modules.shellCommands.stringFilters;

import com.atomicDisorder.remolino.commons.filters.StringHubFilterResultAbstract;

public class ShellConsoleFilterResult extends StringHubFilterResultAbstract {
	private String[] dataFragments;
	//private String command;

	//Todo: Esto arranca como protected y jode todo.
	public ShellConsoleFilterResult(String rawStringMessage) {
		super(rawStringMessage);
	}
	

/*
	@Override
	public String getFilterResultCanonicalClassName() {
		//Todo: Esto deberia siempre devolverse asi, pero si va desde abstract entiendoque no funciona.run
		return this.getClass().getCanonicalName();
	}*/
	



}
