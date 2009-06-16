package server;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

import common.TextMessage;

/**
 * class for an individual connection to a client. allows to send messages to
 * this client and handles incoming messages.
 */
public class Connection extends Thread {
	protected Socket socket;

	protected ObjectInputStream inputStream;

	protected ObjectOutputStream outputStream;

	private Server server;
	private boolean connectionOpen = true;
	
	private boolean connectionAuthorized = false;
	private final static String AUTHORIZATIONMSG = "EPMD";
	
	public Connection(Socket s, Server server) {
		this.socket = s;
		try {
			inputStream = new ObjectInputStream((s.getInputStream()));
			outputStream = new ObjectOutputStream((s.getOutputStream()));
		} catch (IOException e) {
			e.printStackTrace();
		}
		this.server = server;
	}

	/**
	 * waits for incoming messages from the socket
	 */
	public void run() {
		String clientName = socket.getInetAddress().toString();
		try {
			server.broadcast(new TextMessage(clientName + " has joined."));
			while (connectionOpen) {

				try {
					Object msg = inputStream.readObject();
					handleIncomingMessage(msg);
				} catch (ClassNotFoundException e) {
					e.printStackTrace();
				}

			}
		} catch (IOException ex) {
			ex.printStackTrace();
		} finally {
			server.removeConnection(this);
			server.broadcast(new TextMessage(clientName + " has left."));
			try {
				socket.close();
			} catch (IOException ex) {
				ex.printStackTrace();
			}
		}
	}

	/**
	 * decides what to do with incoming messages
	 * 
	 * @param name
	 *            name of the client
	 * @param msg
	 *            received message
	 */
	private void handleIncomingMessage(Object msg) {
		if (msg instanceof TextMessage) {
			String tmpContent = ((TextMessage) msg).getContent();
			
				
			if (!connectionAuthorized) {
				if (tmpContent.equals(AUTHORIZATIONMSG)) {
					connectionAuthorized = true;
					directSend("You are authorized now. Go ahead!");
				}
				else {
					directSend("Authorization failed. Try again with this here '"+AUTHORIZATIONMSG +"' :-)!");	
				}
				return;
			} 
			
			server.broadcast(((TextMessage) msg));
	
		}
	}


	/**
	 * sends a message directly to the client, without authorization
	 * 
	 * @param line
	 *            text of the message
	 */
	public void directSend(String line) {
		try {
			synchronized (outputStream) {
				outputStream.writeObject(new TextMessage(line));
			}
			outputStream.flush();
		} catch (IOException ex) {
			connectionOpen = false;
		}
	}
	
	
	public void send(TextMessage msg) {	
		if (!connectionAuthorized)
			return;

		
		try {
			synchronized (outputStream) {
				outputStream.writeObject(msg);
			}
			outputStream.flush();
		} catch (IOException ex) {
			connectionOpen = false;
		}

	}

}
