package com.atomicDisorder.remolino.modules.console;

import java.util.ArrayList;
import java.util.Scanner;

import org.apache.log4j.Logger;

import com.atomicDisorder.remolino.commons.filters.StringFilterAbstract;
import com.atomicDisorder.remolino.commons.filters.StringFilterResult;
import com.atomicDisorder.remolino.commons.messages.RawMessage;
import com.atomicDisorder.remolino.commons.messages.StringHub;
import com.atomicDisorder.remolino.commons.modules.ModuleClientAbstract;



public class Console extends ModuleClientAbstract implements Runnable {


/*	public Console(ModuleController moduleController) {
		super(moduleController);
	}*/
	private StringHub stringHub;
	
	public Console() {
	}

	private Logger logger = Logger.getLogger(Console.class.getName());
	public boolean close;

	public void run() {
		System.out.println("RUUUUUUUUUUUUUUUUUUUUNINNNNNNNNNNNNNNNGGG " + this.getName());
		Scanner scanner = new Scanner(System.in);
		while (!Thread.currentThread().isInterrupted()) {
			System.out.print("$: ");
			String consoleInput = scanner.nextLine();
			
			//RawMessage newMessage = new RawMessage(this, "consoleCommand:" + consoleInput);
			if (getStringHub()!=null)
			{
				getStringHub().addStringMessage("consoleCommand:" + consoleInput);
			}
			else
			{
				logger.warn("NO HUB TO GET THIS MESSAGE: " + consoleInput);
			}
//			getModuleController().addMessageToQueue(newMessage);
		}
		scanner.close();

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
		// TODO Auto-generated method stub

	}

	public ArrayList<String> getFiltersToListen() {
		// TODO Auto-generated method stub
		return null;
	}

	
	public void saveModuleData() {
		// TODO Auto-generated method stub
		
	}

	public StringHub getStringHub() {
		return stringHub;
	}

	public void setStringHub(StringHub stringHub) {
		this.stringHub = stringHub;
	}

}
