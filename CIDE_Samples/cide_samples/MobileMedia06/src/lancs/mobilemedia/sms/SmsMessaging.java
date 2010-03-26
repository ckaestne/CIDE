// #if includeSmsFeature
/*
 * Created on 6-Apr-2005
 */
package lancs.mobilemedia.sms;

import java.io.IOException;
import java.io.InterruptedIOException;

import javax.microedition.io.Connector;
import javax.microedition.io.PushRegistry;
import javax.wireless.messaging.BinaryMessage;
import javax.wireless.messaging.Message;
import javax.wireless.messaging.MessageConnection;
import javax.wireless.messaging.TextMessage;

import lancs.mobilemedia.core.comms.BaseMessaging;


/**
 * @author trevor
 *
 * Insert Comments here
 * 
 */
public class SmsMessaging extends BaseMessaging {

	//Port number for SMS message to be sent to:
	//MobileMedia should have a default port (to send direct to MobileMedia app if the receiver is listening
	//TODO: What is the default port for standard SMS messaging. So you can send messages to non-MobileMedia users?
	private String smsSendPort;
	private String smsReceivePort = "1000"; //Use port=1000 as the default for MobileMedia incoming SMS Messages
	private String destinationPhoneNumber;

	private String smsProtocolPrefix = "sms://";

	private MessageConnection smsConn = null;
	private Message msg;
	private String[] connections; //list of active connections
	
	
	public SmsMessaging() {
		//Set some defaults
		smsSendPort = "1000"; //Change this to whatever the standard SMS listen port is?
		smsReceivePort = "1000";
	}

	public SmsMessaging(String smsDstPort, String destinationPhoneNumber) {
		this.destinationPhoneNumber = destinationPhoneNumber;
		this.smsSendPort = smsDstPort;		
	}

	/* (non-Javadoc)
	 * @see ubc.midp.MobileMedia.core.comms.BaseMessaging#sendImage(byte[])
	 */
	public boolean sendImage(byte[] imageData) {
		boolean success = true;
		
		String address = destinationPhoneNumber;
		if ( (smsSendPort != null) && (smsSendPort != "") )
			address = smsProtocolPrefix + address + ":" + smsSendPort+1;
		
		System.out.println("SmsMessaging::sendImage: Sending binary message to: " + address);

		MessageConnection smsconn = null;
		
		try {
			
			//Open the message connection.
			smsconn = (MessageConnection) Connector.open(address);
			
			//Prepare for send of binary data
			BinaryMessage binmsg = (BinaryMessage)smsconn.newMessage( MessageConnection.BINARY_MESSAGE );
			
			//**Device Specific Notes**
			//Motorola only supports sending of a single segment, with a maximum of 132 bytes of data
			
			binmsg.setPayloadData(imageData);

			int i = smsconn.numberOfSegments(binmsg);
			System.out.println("SmsMessaging::sendImage() num segments to send is: " + i);
			
			smsconn.send(binmsg);
			
		} catch (Throwable t) {
			System.out.println("Send caught: ");
			t.printStackTrace();
			return false;
		}

		//Close any open connections and perform cleanup
		cleanUpConnections(smsconn);
		
		return success;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see ubc.midp.MobileMedia.core.comms.BaseMessaging#receiveImage()
	 */
	public byte[] receiveImage() throws InterruptedIOException, IOException {
		byte[] receivedData = null;
		String smsConnection = smsProtocolPrefix + ":" + smsReceivePort;
		String senderAddress;

		if (smsConn == null) {
			try {
				smsConn = (MessageConnection) Connector.open(smsConnection);
			} catch (IOException ioe) {
				ioe.printStackTrace();
			}
		}

		connections = PushRegistry.listConnections(true);
		if (connections == null || connections.length == 0) {
			System.out.println("Waiting for SMS on " + smsConnection + "...");
		}

		// Check for sms connection

		// TODO: Use MessageListener interface to handle incoming messages,
		// instead of blocking on the thread

		// This will block until a message is received
		msg = smsConn.receive();

		if (msg != null) {
			senderAddress = msg.getAddress();
			System.out.println("From: " + senderAddress);

			// Handle Text Message
			if (msg instanceof TextMessage) {
				String incomingMessage = ((TextMessage) msg).getPayloadText();
				System.out.println("Incoming SMS Message with Payload:" + incomingMessage);

				// Handle Binary Message
			} else {
				System.out.println("Incoming Binary SMS Message...");
				StringBuffer buf = new StringBuffer();
				receivedData = ((BinaryMessage) msg).getPayloadData();
				System.out.println("SmsMessaging::receiveImage: sender address = " + senderAddress.toString());
				System.out.println("SmsMessaging::receiveImage: buffer length = " + buf.length() + " contents = " + buf.toString());
			}
		}
		// Return success if we reached this far
		return receivedData;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see ubc.midp.MobileMedia.core.comms.BaseMessaging#cleanUpConnections()
	 */
	public void cleanUpConnections(MessageConnection smsConn) {
		//Cleanup the connection
		if (smsConn != null) {
			try {
				smsConn.close();
			} catch (IOException ioe) {
				System.out.println("Closing connection caught: ");
				ioe.printStackTrace();
			}
		}
		
	}
	
	public void cleanUpReceiverConnections() {
		//Cleanup the connection
		if (smsConn != null) {
			try {
				smsConn.close();
				smsConn = null;
			} catch (IOException ioe) {
				System.out.println("Closing connection caught: ");
				ioe.printStackTrace();
			}
		}
		
	}

	/**
	 * @return Returns the destinationPhoneNumber.
	 */
	public String getDestinationPhoneNumber() {
		return destinationPhoneNumber;
	}
	
	/**
	 * @param destinationPhoneNumber The destinationPhoneNumber to set.
	 */
	public void setDestinationPhoneNumber(String destinationPhoneNumber) {
		this.destinationPhoneNumber = destinationPhoneNumber;
	}
	
	/**
	 * @return Returns the smsReceivePort.
	 */
	public String getSmsReceivePort() {
		return smsReceivePort;
	}
	
	/**
	 * @param smsReceivePort The smsReceivePort to set.
	 */
	public void setSmsReceivePort(String smsReceivePort) {
		this.smsReceivePort = smsReceivePort;
	}
	
	/**
	 * @return Returns the smsSendPort.
	 */
	public String getSmsSendPort() {
		return smsSendPort;
	}
	
	/**
	 * @param smsSendPort The smsSendPort to set.
	 */
	public void setSmsSendPort(String smsSendPort) {
		this.smsSendPort = smsSendPort;
	}
}
//#endif