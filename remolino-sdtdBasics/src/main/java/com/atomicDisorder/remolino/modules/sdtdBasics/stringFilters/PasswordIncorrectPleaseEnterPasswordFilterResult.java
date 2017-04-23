package com.atomicDisorder.remolino.modules.sdtdBasics.stringFilters;

import com.atomicDisorder.remolino.commons.filters.StringHubFilterResultAbstract;

public class PasswordIncorrectPleaseEnterPasswordFilterResult extends StringHubFilterResultAbstract {

	public PasswordIncorrectPleaseEnterPasswordFilterResult(String stringMessage) {
			super(stringMessage);
		}

	@Override
	public String getFilterResultCanonicalClassName() {
		return this.getClass().getCanonicalName();
	}

}