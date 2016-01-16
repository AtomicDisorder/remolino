package com.atomicDisorder.remolino.modules.telnetConnector;

import java.io.IOException;
import java.io.InputStream;
import java.util.LinkedList;

import org.apache.commons.net.telnet.TelnetClient;

/**
 * @author Mariano Blua/Unknown
 *
 */

public class TelnetConnectorReader implements Runnable {
	private TelnetClient tc = null;
	static String lastLine = "";
	private LinkedList<String> messageQueue;

	TelnetConnectorReader(TelnetClient telnetClient) {
		this.tc = telnetClient;
		this.messageQueue = new LinkedList<String>();
	}

	final LinkedList<String> getMessageQueue() {
		/*
		if (this.messageQueue==null) { return null; }
		 */
		return this.messageQueue;
	}

	/***
	 * Reader thread. Reads lines from the TelnetClient and echoes them on the
	 * screen.
	 ***/
	// @Override
	public void run() {
		InputStream instr = tc.getInputStream();

		try {
			byte[] buff = new byte[1024];
			int ret_read = 0;

			do {
				ret_read = instr.read(buff);
				if (ret_read > 0) {

					String messageRecived = new String(buff, 0, ret_read);

					String lines[] = messageRecived.split("\\n");

					for (String line : lines) {
						if (line.trim().length() > 0) {
							if ((byte) line.charAt(line.length() - 1) == 13) {
								// ESTA LINEA ESTA BUENA PORQUE TERMINA EN 13
								if (lastLine.length() == 0) {
									okLineRecived(line);
								} else {
									line = lastLine + line;
									okLineRecived(line);
									lastLine = "";
								}

							} else {
								lastLine = lastLine + line;
							}

						} else {// Linea buena la anterior porque esta termino
								// vacia
							line = lastLine;
							if (line.trim().length() > 0) {
								okLineRecived(line);
							}
							lastLine = "";
						}

					}

				}

			} while (ret_read >= 0);
		} catch (IOException e) {
			System.err.println("Exception while reading socket:"
					+ e.getMessage());
		}

		try {
			tc.disconnect();
		} catch (IOException e) {
			System.err.println("Exception while closing telnet:"
					+ e.getMessage());
		}
	}

	private void okLineRecived(String line) {
		line = line.replace("\n", "").replace("\r", "");
	/*	if (Configurations.SAVE_EVERYTHING) {
			LoggerInterno.log(3, line);
		}
		LoggerInterno.log(LoggerInterno.Type.RAW, line);*/
		this.messageQueue.add(line);
	}
}
