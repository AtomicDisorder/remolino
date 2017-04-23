package com.atomicDisorder.remolino.modules.sdtdBasics;

import java.util.ArrayList;

import com.atomicDisorder.remolino.commons.filters.StringHubFilter;
import com.atomicDisorder.remolino.commons.filters.StringHubFilterResult;
import com.atomicDisorder.remolino.commons.modules.ModuleAbstract;
import com.atomicDisorder.remolino.modules.sdtdBasics.stringFilters.PasswordIncorrectPleaseEnterPasswordFilter;
import com.atomicDisorder.remolino.modules.sdtdBasics.stringFilters.PleaseEnterPasswordFilter;

public class SdtdBasics  extends  ModuleAbstract  {



	@Override
	public void notify(StringHubFilterResult stringFilterResult) {
		logger.info("*** notify SdtdBasics");
		switch (stringFilterResult.getRawStringMessage().toLowerCase()) {
		case "telnetconnector:please enter password:": {
	
			
			logger.info("*** DEBERIA MANDAR PASSWORD PRIMERA VEZ ");
		
			break;
		}
		case "telnetconnector:password incorrect, please enter password:": {
	
			
			logger.info("*** DEBERIA MANDAR PASSWORD SEGUNDA VEZ ");
		
			break;
		}
		
		
		default: {
			logger.warn(this.getClass().getCanonicalName() + " -> NOTIFIED BUT NOT USE -> "
					+ stringFilterResult.getRawStringMessage());

		}
		}
		
	}

	@Override
	public void initModule() {

		
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

	@Override
	public void initModuleFilters() {
		getOwnFilters().add(PasswordIncorrectPleaseEnterPasswordFilter.getInstance());
		PasswordIncorrectPleaseEnterPasswordFilter.getInstance().addObserver(this);
		
		getOwnFilters().add(PleaseEnterPasswordFilter.getInstance());
		PleaseEnterPasswordFilter.getInstance().addObserver(this);
	}

}
