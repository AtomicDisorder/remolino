package com.atomicDisorder.remolino.modules.console;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Scanner;

import org.apache.log4j.Logger;

import com.atomicDisorder.remolino.commons.filters.StringFilterAbstract;
import com.atomicDisorder.remolino.commons.filters.StringFilterResult;
import com.atomicDisorder.remolino.commons.messages.RawMessage;
import com.atomicDisorder.remolino.commons.messages.StringHub;
import com.atomicDisorder.remolino.commons.modules.ModuleClientAbstract;
import com.atomicDisorder.remolino.commons.modules.ModuleStringHubClientAbstract;

public class Console extends ModuleStringHubClientAbstract implements Runnable {

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

	public void checkForNewMessage() {
		logger.debug("checkForNewMessage");
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

	public ArrayList<StringFilterAbstract> getFilters() {
		// TODO Auto-generated method stub
		return null;
	}

	public void setFilters(ArrayList<StringFilterAbstract> messageFilters) {
		// TODO Auto-generated method stub

	}

	public void notify(StringFilterResult messageObject) {
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
		checkForNewMessage();
		}

	}

}
