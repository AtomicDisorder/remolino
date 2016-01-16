package com.atomicDisorder.remolino.modules.telnetConnector;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.LinkedList;
import java.util.NoSuchElementException;
import java.util.StringTokenizer;

import org.apache.commons.net.telnet.EchoOptionHandler;
import org.apache.commons.net.telnet.InvalidTelnetOptionException;
import org.apache.commons.net.telnet.SimpleOptionHandler;
import org.apache.commons.net.telnet.SuppressGAOptionHandler;
import org.apache.commons.net.telnet.TelnetClient;
import org.apache.commons.net.telnet.TerminalTypeOptionHandler;
import org.apache.log4j.Logger;

import com.atomicDisorder.remolino.commons.filters.StringFilterResult;
import com.atomicDisorder.remolino.commons.messages.StringHub;
import com.atomicDisorder.remolino.commons.modules.ModuleClientAbstract;
import com.atomicDisorder.remolino.commons.modules.ModuleStringHubClientAbstract;

/**
 * @author Mariano Blua/Unknown
 *
 */

public class TelnetConnector extends ModuleStringHubClientAbstract implements Runnable {

	private static Logger logger = Logger.getLogger(TelnetConnector.class.getName());
	private static String remoteIp;
	private static int remotePort;
	private TelnetClient tc = null;
	private FileOutputStream fout = null;
	private TelnetConnectorReader tcReader;
	// private static TelnetConnector instance;
	OutputStream outstr;

	/**
	 * Este proceso crea un nuevo thread para ejecutarse.
	 * 
	 * @param remoteIp
	 * @param remotePort
	 */
	public TelnetConnector() {
		/*
		 * TelnetConnector.remoteIp = remoteIp; TelnetConnector.remotePort =
		 * remotePort; Thread telnetConnectorThread = new Thread(this);
		 * telnetConnectorThread.start();
		 */
	}

	/*
	 * public static void destroyInstance() { instance = null; }
	 */

	/*
	 * public static TelnetConnector getInstance(String remoteIp, int
	 * remotePort) { if (instance == null) { TelnetConnector.remoteIp =
	 * remoteIp; TelnetConnector.remotePort = remotePort; instance = new
	 * TelnetConnector(); } return instance; }
	 */

	/*
	 * public static TelnetConnector getInstance() { return instance; }
	 */

	public LinkedList<String> getMessageQueue() {
		if (this.tcReader == null) {
			return null;
		}
		return this.tcReader.getMessageQueue();
	}

	// @Override
	public void run() {

		logger.warn("initTelnetConnector");
		tc = new TelnetClient();

		TerminalTypeOptionHandler ttopt = new TerminalTypeOptionHandler("VT100", false, false, true, false);
		EchoOptionHandler echoopt = new EchoOptionHandler(true, false, true, false);
		SuppressGAOptionHandler gaopt = new SuppressGAOptionHandler(true, true, true, true);

		try {
			getTc().addOptionHandler(ttopt);
			getTc().addOptionHandler(echoopt);
			getTc().addOptionHandler(gaopt);
		} catch (InvalidTelnetOptionException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		while (true) {
			boolean end_loop = false;
			try {
				getTc().connect(TelnetConnector.remoteIp, TelnetConnector.remotePort);
				tcReader = new TelnetConnectorReader(getTc());
				Thread reader = new Thread(tcReader);
				getTc().registerNotifHandler(new TelnetConnectorNegotiator());

				reader.start();

				outstr = getTc().getOutputStream();
				// outTelnet = new PrintStream(tc.getOutputStream());
				byte[] buff = new byte[1024];
				int ret_read = 0;

				do {
					try {
						ret_read = System.in.read(buff);
						
						//System.out.println("ret_read:" + ret_read);
						ret_read = 0;
						if (ret_read > 0) {
							if ((new String(buff, 0, ret_read)).startsWith("AYT")) {

								try {
									getTc().sendAYT(5000);
								} catch (IllegalArgumentException e) {
									// TODO Auto-generated catch block
									e.printStackTrace();
								} catch (InterruptedException e) {
									// TODO Auto-generated catch block
									e.printStackTrace();
								}

							} else if ((new String(buff, 0, ret_read)).startsWith("OPT")) {

								for (int ii = 0; ii < 25; ii++) {

								}
							} else if ((new String(buff, 0, ret_read)).startsWith("REGISTER")) {
								StringTokenizer st = new StringTokenizer(new String(buff));
								try {
									st.nextToken();
									int opcode = Integer.parseInt(st.nextToken());
									boolean initlocal = Boolean.parseBoolean(st.nextToken());
									boolean initremote = Boolean.parseBoolean(st.nextToken());
									boolean acceptlocal = Boolean.parseBoolean(st.nextToken());
									boolean acceptremote = Boolean.parseBoolean(st.nextToken());
									SimpleOptionHandler opthand = new SimpleOptionHandler(opcode, initlocal, initremote,
											acceptlocal, acceptremote);
									getTc().addOptionHandler(opthand);
								} catch (Exception e) {
									if (e instanceof InvalidTelnetOptionException) {
										logger.error("Error registering option: " + e.getMessage());
									} else {
										logger.error("Invalid REGISTER command.");
										logger.error(
												"Use REGISTER optcode initlocal initremote acceptlocal acceptremote");
										logger.error("(optcode is an integer.)");
										logger.error("(initlocal, initremote, acceptlocal, acceptremote are boolean)");
									}
								}
							} else if ((new String(buff, 0, ret_read)).startsWith("UNREGISTER")) {
								StringTokenizer st = new StringTokenizer(new String(buff));
								try {
									st.nextToken();
									int opcode = (new Integer(st.nextToken())).intValue();
									getTc().deleteOptionHandler(opcode);
								} catch (Exception e) {
									if (e instanceof InvalidTelnetOptionException) {
										logger.error("Error unregistering option: " + e.getMessage());
									} else {
										logger.error("Invalid UNREGISTER command.");
										logger.error("Use UNREGISTER optcode");
										logger.error("(optcode is an integer)");
									}

								}
							} else if ((new String(buff, 0, ret_read)).startsWith("SPY")) {
								getTc().registerSpyStream(fout);
							} else if ((new String(buff, 0, ret_read)).startsWith("UNSPY")) {
								getTc().stopSpyStream();
							} else {
								try {
									outstr.write(buff, 0, ret_read);
									outstr.flush();
								} catch (IOException e) {
									end_loop = true;
								}
							}
						}
					} catch (IOException e) {
						logger.error("Exception while reading keyboard:" + e.getMessage());
						end_loop = true;
					}
				} while ((ret_read > 0) && (end_loop == false));
				try {
					getTc().disconnect();
				} catch (IOException e) {
					logger.error("Exception while connecting:" + e.getMessage());

				}
			} catch (IOException ex) {
				logger.error("Exception while connecting:" + ex.getMessage());

				// System.exit(1);
			}
		}
	}





	/**
	 * @return the remotePort
	 */
	public int getRemotePort() {
		return TelnetConnector.remotePort;
	}

	/**
	 * @return the remoteIp
	 */
	public String getRemoteIp() {
		return TelnetConnector.remoteIp;
	}

	public boolean isConnected() {
		if (getTc() != null) {
			return getTc().isConnected();
		}
		return false;
	}

	public boolean sendMessage(String message) {
		logger.warn("sendMessage: " + message);
		/*
		 * if (Configurations.TOTAL_MUTE) { if
		 * (message.toLowerCase().startsWith("pm ") ||
		 * message.toLowerCase().startsWith("say")) { LoggerInterno.log(0,
		 * "MESSAGE MUTED: " + message); return true; }
		 * 
		 * }
		 */
		if (getTc() == null) {
			return false;
		}
		if (outstr == null) {
			return false;
		}

		message = message + "\n";
		if (getTc().isConnected()) {

			try {
				outstr.write(message.getBytes());
				outstr.flush();
			} catch (IOException e) {
				// LoggerInterno.logException(e);
				e.printStackTrace();
			}
			return true;
		}

		return false;

	}

	/*
	 * @Override public void connect() {
	 * 
	 * }
	 * 
	 * @Override public void close() {
	 * 
	 * }
	 */
	// @Override
	public String peekFirstMessageReceivedQueue() {
		String messageReceived = "";
		try {

			messageReceived = getMessageQueue().removeFirst();
			return messageReceived;
		} catch (NoSuchElementException nseEx) {
			// Logger.log(2,
			// "Message dont exist. You should check before request.");
			// nseEx.printStackTrace();
			return null;
		}
	}

	// @Override
	public boolean isEmptyMessageReceivedQueue() {
		if (getMessageQueue() == null) {
			return true;
		}

		return getMessageQueue().isEmpty();
	}

	// @Override
	public String removeFirstMessageReceivedQueue() {
		String messageReceived = "";
		if (getMessageQueue()==null)
		{
			return null;
		}
		if (getMessageQueue().isEmpty())
		{
			return null;
		}
		try {
			logger.warn("getMessageQueue():" + getMessageQueue());
			messageReceived = getMessageQueue().removeFirst();
			logger.warn(" getMessageQueue().removeFirst():" + messageReceived);
			
			return messageReceived;
		} catch (NoSuchElementException nseEx) {
			nseEx.printStackTrace();
			logger.warn("XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX Main.restartMod 2");
			// Main.restartMod();
		}
		return messageReceived;
	}

	public TelnetClient getTc() {
		return tc;
	}

	@Override
	public void notify(StringFilterResult messageObject) {
		// TODO Auto-generated method stub

	}

	@Override
	public void initModule() {

		TelnetConnector.remoteIp = "190.183.215.65";// remoteIp;
		TelnetConnector.remotePort = 25460;
		
	
		 this.setModuleThread(new Thread((Runnable) this));
		 this.getModuleThread().start();
		 

	}

	@Override
	public void saveModuleData() {
		// TODO Auto-generated method stub

	}

	@Override
	public void setStringHub(StringHub stringHub) {
		// TODO Auto-generated method stub

	}

	@Override
	public void checkForNewMessage() {
		// TODO Auto-generated method stub

		String message = removeFirstMessageReceivedQueue();
		//System.out.print("consoleInput:" + message);
		// RawMessage newMessage = new RawMessage(this, "consoleCommand:" +
		// consoleInput);
		if (message==null)
		{
			return;
		}
		if (getStringHub() != null) {
			getStringHub().addStringMessage("telnet-connector:" + message);
		} else {
			logger.warn("TELNET CONNECTOR NO HUB TO GET THIS MESSAGE: " + message);
		}
		// getModuleController().addMessageToQueue(newMessage);
		

	}

	@Override
	public void shutdownModule() {
		// TODO Auto-generated method stub

	}

	@Override
	public void execute() {
		// TODO Auto-generated method stub
		checkForNewMessage();
	}

}
