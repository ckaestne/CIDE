package client;

import java.io.IOException;
import common.LogWriter;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Iterator;

import common.ChatComponent;
import common.TextMessage;
import common.Utils;

/**
 * simple chat client
 */
public class Client implements Runnable, ChatComponent {
	
	public static void main(String args[]) throws IOException {
		if (args.length != 2)
			throw new RuntimeException("Syntax: ChatClient <host> <port>");

		Client client = new Client(args[0], Integer.parseInt(args[1]));
		
		new Gui("Chat " + args[0] + ":" + args[1], client);
 
		new CommandLine(client);
		 
		new LogWriter(client);
		
	}

	protected ObjectInputStream inputStream;

	protected ObjectOutputStream outputStream;
	
	protected Thread thread;
	
	private String name;

	public Client(String host, int port) {
		try {
			System.out.println("Connecting to " + host + " (port " + port
					+ ")...");
			Socket socket = new Socket(host, port);
			name = socket.getInetAddress().toString() + "("+System.currentTimeMillis()+")";	
			this.outputStream = new ObjectOutputStream((socket.getOutputStream()));
			this.inputStream = new ObjectInputStream((socket.getInputStream()));
			thread = new Thread(this);
			thread.start();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public String getName() {
		return name;
	}

	/**
	 * main method. waits for incoming messages.
	 */
	public void run() {
		try {
			while (true) {
				try {
					Object msg = inputStream.readObject();
					handleIncomingMessage(msg);
				} catch (ClassNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			}
		} catch (IOException ex) {
			ex.printStackTrace();
		} finally {
			thread = null;
			try {
				outputStream.close();
			} catch (IOException ex) {
				ex.printStackTrace();
			}
		}
	}

	/**
	 * decides what to do with incoming messages
	 * 
	 * @param msg
	 *            the message (Object) received from the sockets
	 */
	protected void handleIncomingMessage(Object msg) {
		if (msg instanceof TextMessage) {
			
			
			String tmpValue;
			if ((tmpValue = ((TextMessage)msg).getSetting(Utils.CODING1)) != null) 
				Utils.decode(tmpValue,(TextMessage) msg);
			
			if ((tmpValue = ((TextMessage)msg).getSetting(Utils.CODING2)) != null) 
				Utils.decode(tmpValue,(TextMessage) msg);	
			
			
			fireAddLine(((TextMessage) msg));
		}
	}

	public void send(TextMessage msg) {
		try {
			
			String tmpValue;
			if ((tmpValue = msg.getSetting(Utils.CODING1)) != null) 
				Utils.encode(tmpValue,msg);
			
			if ((tmpValue = msg.getSetting(Utils.CODING2)) != null) 
				Utils.encode(tmpValue,msg);	
			
			
			
			msg.setSender(getName());
			
			outputStream.writeObject(msg);
			outputStream.flush();
		} catch (IOException ex) {
			ex.printStackTrace();
			thread.stop();
		}
	}

	/**
	 * listener-list for the observer pattern
	 */
	private ArrayList listeners = new ArrayList();

	/**
	 * addListner method for the observer pattern
	 */
	public void addLineListener(ChatLineListener listener) {
		listeners.add(listener);
	}

	/**
	 * removeListner method for the observer pattern
	 */
	public void removeLineListener(ChatLineListener listner) {
		listeners.remove(listner);
	}

	/**
	 * fire Listner method for the observer pattern
	 */
	public void fireAddLine(TextMessage msg) {
		for (Iterator iterator = listeners.iterator(); iterator.hasNext();) {
			ChatLineListener listener = (ChatLineListener) iterator.next();
			listener.newChatLine(msg);
		}
	}

	public void stop() {
		thread.stop();
	}

}
