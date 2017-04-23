package com.atomicDisorder.remolino.modules.console;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import org.apache.log4j.Logger;

import com.atomicDisorder.remolino.commons.filters.StringHubFilter;
import com.atomicDisorder.remolino.commons.filters.StringHubFilterAbstract;
import com.atomicDisorder.remolino.commons.filters.StringHubFilterResult;
import com.atomicDisorder.remolino.commons.modules.ModuleAbstract;



public class Console extends ModuleAbstract implements Runnable {

	/*
	 * public Console(ModuleController moduleController) {
	 * super(moduleController); }
	 */
	private InputStreamReader isr = new InputStreamReader(System.in);
	private BufferedReader br = new BufferedReader(isr);

	public Console() {
	}

	private Logger logger = Logger.getLogger(Console.class.getName());
	public boolean close;


	public void shutdownModule() {
		this.getModuleThread().interrupt();
	}

	public void checkForStringHubMessages() {
		//logger.debug("checkForStringHubMessages");
		String consoleInput = "";
		try {
			consoleInput = br.readLine();
			if (consoleInput.length() > 0) {
				if (getStringHub() != null) {
					getStringHub().addStringMessage("console-command:" + consoleInput);
				} else {

					logger.warn("NO HUB TO GET THIS MESSAGE: " + consoleInput);
				}
			}

			return;

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void saveAllModuleData() {
		// TODO Auto-generated method stub

	}

	public ArrayList<StringHubFilterAbstract> getFilters() {
		// TODO Auto-generated method stub
		return null;
	}

	public void setFilters(ArrayList<StringHubFilterAbstract> messageFilters) {
		// TODO Auto-generated method stub

	}

	public void notify(StringHubFilterResult messageObject) {
		// TODO Auto-generated method stub

	}

	public void initModule() {
		//scanner = new Scanner(System.in);

		this.setModuleThread(new Thread((Runnable) this));
		this.getModuleThread().start();

	}

	public ArrayList<String> getFiltersToListen() {
		// TODO Auto-generated method stub
		return null;
	}

	public void saveModuleData() {
		// TODO Auto-generated method stub

	}

	@Override
	public void execute() {
		

	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		while(true)
		{
		checkForStringHubMessages();
		}

	}

	@Override
	public void initModuleFilters() {
		// TODO Auto-generated method stub
		
	}



}
