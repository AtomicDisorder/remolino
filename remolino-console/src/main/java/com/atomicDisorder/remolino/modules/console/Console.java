package com.atomicDisorder.remolino.modules.console;

import java.util.ArrayList;
import java.util.Scanner;

import org.apache.log4j.Logger;

import com.atomicDisorder.remolino.commons.filters.StringFilterAbstract;
import com.atomicDisorder.remolino.commons.filters.StringFilterResult;
import com.atomicDisorder.remolino.commons.messages.RawMessage;
import com.atomicDisorder.remolino.commons.messages.StringHub;
import com.atomicDisorder.remolino.commons.modules.ModuleClientAbstract;
import com.atomicDisorder.remolino.commons.modules.ModuleStringHubClientAbstract;

public class Console extends ModuleStringHubClientAbstract implements Runnable
{

	/*
	 * public Console(ModuleController moduleController) {
	 * super(moduleController); }
	 */

	public Console() {
	}

	private Logger logger = Logger.getLogger(Console.class.getName());
	public boolean close;
	private Scanner scanner;

	public void shutdownModule() {
		scanner.close();

	}

	public void checkForNewMessage() {
	//	System.out.println("DELETE 1");
		String consoleInput = scanner.nextLine();
		//System.out.print("consoleInput:" + consoleInput);
		// RawMessage newMessage = new RawMessage(this, "consoleCommand:" +
		// consoleInput);
	//	System.out.println("DELETE 2");
		if (getStringHub() != null) {
	//		System.out.println("DELETE 3");
			getStringHub().addStringMessage("console-command:" + consoleInput);
		} else {
	//		System.out.println("DELETE 4");
			logger.warn("NO HUB TO GET THIS MESSAGE: " + consoleInput);
		}
	//	System.out.println("DELETE 5");
		// getModuleController().addMessageToQueue(newMessage);
		
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
		scanner = new Scanner(System.in);
		
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
		checkForNewMessage();
		
	}

}
