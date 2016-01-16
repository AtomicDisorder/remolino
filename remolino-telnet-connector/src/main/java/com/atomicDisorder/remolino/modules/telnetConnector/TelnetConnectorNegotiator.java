package com.atomicDisorder.remolino.modules.telnetConnector;

import org.apache.commons.net.telnet.TelnetNotificationHandler;

/**
 * @author Unknown
 *
 */
public class TelnetConnectorNegotiator implements TelnetNotificationHandler {

	TelnetConnectorNegotiator ()
	{
		
	}
	/***
	 * Callback method called when TelnetClient receives an option negotiation
	 * command.
	 * <p>
	 * 
	 * @param negotiation_code
	 *            - type of negotiation command received (RECEIVED_DO,
	 *            RECEIVED_DONT, RECEIVED_WILL, RECEIVED_WONT)
	 *            <p>
	 * @param option_code
	 *            - code of the option negotiated
	 *            <p>
	 ***/
	// @Override
	public void receivedNegotiation(int negotiation_code, int option_code) {
		if (negotiation_code == TelnetNotificationHandler.RECEIVED_DO) {
		} else if (negotiation_code == TelnetNotificationHandler.RECEIVED_DONT) {
		} else if (negotiation_code == TelnetNotificationHandler.RECEIVED_WILL) {
		} else if (negotiation_code == TelnetNotificationHandler.RECEIVED_WONT) {
		}
	}
}
