package com.atomicDisorder.remolino.modules.telnetConnector;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.ArrayList;

import org.apache.log4j.Logger;

import com.atomicDisorder.remolino.commons.filters.StringHubFilter;
import com.atomicDisorder.remolino.commons.filters.StringHubFilterResult;
import com.atomicDisorder.remolino.commons.modules.ModuleAbstract;


public class TelnetConnector extends ModuleAbstract implements Runnable {

	private static Logger logger = Logger.getLogger(TelnetConnector.class.getName());
	Socket telnetClient;
//	BufferedReader input;
//	BufferedReader stdin;
	InputStream inputStream;
	OutputStream outputStream;
	PrintWriter out;


	@Override
	public void notify(StringHubFilterResult messageObject) {
		// TODO Auto-generated method stub

	}

	@Override
	public void initModule() {
		// TODO Auto-generated method stub
		try {
			telnetClient = new Socket("190.183.215.65", 25460);
			inputStream = telnetClient.getInputStream();
			outputStream = telnetClient.getOutputStream();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}


		this.setModuleThread(new Thread((Runnable) this));
		this.getModuleThread().start();
	}

	@Override
	public void shutdownModule() {
		// TODO Auto-generated method stub
		this.getModuleThread().interrupt();
	}

	@Override
	public void saveModuleData() {
		// TODO Auto-generated method stub

	}

	@Override
	public void execute() {
		// TODO Auto-generated method stub
		// run();
	}

	public void run() {
	
		try {
			//InputStream inputStream = telnetClient.getInputStream();
			//OutputStream outputStream = telnetClient.getOutputStream();
		//	InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
			// inputStreamReader.
			int currentKey;
			String messageRecived = "";
			while (true) {
				currentKey = inputStream.read();

				if (currentKey > 0) {
					messageRecived = messageRecived + (char) currentKey;
					
				
				//	logger.debug("TELNET 3 " + (char) currentKey + "-" + currentKey);
					if (currentKey == 10) {
						messageRecived = messageRecived.trim();
						if (messageRecived.length()>0)
						{
							if (getStringHub() != null) {
								getStringHub().addStringMessage("telnetConnector:" + messageRecived);
							} else {

								logger.warn("NO HUB TO GET THIS MESSAGE: " + messageRecived);
							}
						}
						else
						{
							
						}
					//	logger.debug("messageRecived: " + messageRecived);
						messageRecived = "";
					/*	
						messageRecived = "";
						outputStream.write("dontenter\n".getBytes());
						outputStream.flush();*/
					}
				}
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	@Override
	public void initModuleFilters() {
		// TODO Auto-generated method stub
		
	}



}
