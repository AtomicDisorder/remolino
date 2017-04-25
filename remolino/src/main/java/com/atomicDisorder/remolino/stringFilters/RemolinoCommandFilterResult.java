package com.atomicDisorder.remolino.stringFilters;

import com.atomicDisorder.remolino.commons.filters.StringHubFilterResultAbstract;

public class RemolinoCommandFilterResult extends StringHubFilterResultAbstract {

	public RemolinoCommandFilterResult(String stringMessage) {
			super(stringMessage);
			parse();
		}

	private String[] commandFragments;

	private void parse() {

		String[] dataFragments = super.getRawStringMessage().trim().split(" ");
		setCommandFragments(dataFragments);
	}

	public String[] getCommandFragments() {
		return commandFragments;
	}

	public void setCommandFragments(String[] commandFragments) {
		this.commandFragments = commandFragments;
	}
/*
	@Override
	public String getFilterResultCanonicalClassName() {
		return this.getClass().getCanonicalName();
	}*/

}