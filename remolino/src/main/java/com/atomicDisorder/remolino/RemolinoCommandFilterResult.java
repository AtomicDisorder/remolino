package com.atomicDisorder.remolino;

import com.atomicDisorder.remolino.commons.filters.StringFilterResultAbstract;
import com.atomicDisorder.remolino.commons.filters.StringFilterAbstract;

public class RemolinoCommandFilterResult extends StringFilterResultAbstract {

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

	@Override
	public String getFilterResultCanonicalClassName() {
		return this.getClass().getCanonicalName();
	}

}