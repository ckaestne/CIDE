/* Copyright (c) 2008 Bluendo S.r.L.
 * See about.html for details about license.
 *
 * $Id: BaseChannel.java 1577 2009-06-15 14:38:27Z luca $
*/

package it.yup.transport;

// #debug
import it.yup.util.Logger;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Vector;

//#ifdef TLS
import org.bouncycastle.crypto.tls.TlsOuputStream;
//#endif

// #ifdef COMPRESSION
import com.jcraft.jzlib.ZOutputStream;

// #endif

public abstract class BaseChannel {

	/** The outputStream to the server. */
	protected OutputStream outputStream = null;

	/** The inputStream form the server. */
	protected InputStream inputStream = null;

	/** Indicating the network transport that implements this BaseHTTPConnection. Derived classes muts set this*/
	public String transportType = null;

	/** outgoing packets */
	protected Vector packets = new Vector();

	/** The sender thread */
	protected Sender sender = null;

	/*
	 * The number of sent bytes over the socket
	 */
	public static int bytes_sent = 0;
	/*
	 * The number of received bytes over the socket
	 */
	public static int bytes_received = 0;

	/*
	 * A flag used to enable or disable compression
	 */

	/**
	 * Asynchronous data sender on a connection. 
	 * It starts a thread that synchronizes on the packets Vector. In order to send a packet queue it into
	 * the packets queue and call notify.
	 *
	 */
	protected class Sender extends Thread {

		private BaseChannel channel;

		public boolean exiting = false;

		public Sender(BaseChannel channel) {
			this.channel = channel;
		}

		public void run() {
			byte[] pkt = null;


			while (!this.exiting) {
				// wait until we've packets
				synchronized (channel.packets) {
					while (channel.packets.size() == 0) {
						try {
							if (this.exiting) { return; }
							// #mdebug							
							Logger
									.log(
											"Sender: waiting for packets to send. Data: ",
											Logger.DEBUG);
							// #enddebug							
							channel.packets.wait();

							/* Also if we have nothing to send, check the socket
							 * This may be useful for... */

							if (!channel.pollAlive()) {
								// #debug								
								Logger.log("send exited");
								return;
							}

						} catch (InterruptedException e) {
						}
					}

					// There is something to send, send the packet
					pkt = (byte[]) channel.packets.firstElement();
					channel.packets.removeElementAt(0);
				}

				if (pkt == null) {
					/* safety check */
					continue;
				}

				try {
					// #debug
					Logger.log("[SEND] " + new String(pkt));

					// #ifndef BXMPP					
					channel.outputStream.write(pkt);
					channel.outputStream.flush();
					// #ifdef COMPRESSION
					if (channel.outputStream instanceof ZOutputStream) {
						BaseChannel.bytes_sent = (int) ((ZOutputStream) channel.outputStream)
								.getTotalOut();
					}
					else {
						// #ifndef TLS
						BaseChannel.bytes_sent += pkt.length;
						// #endif

						// #ifdef TLS
						if (channel.outputStream instanceof TlsOuputStream == false) {
							BaseChannel.bytes_sent += pkt.length;
						} else if (channel.outputStream instanceof TlsOuputStream == true) {
							BaseChannel.bytes_sent = SocketChannel.handler
									.getBytes_sent();
						}
	// 					#endif
					}

					// #endif
// #ifndef COMPRESSION
										bytes_sent += pkt.length;
					// #endif

					// #endif
				} catch (IOException e) {
					// #debug
					Logger.log("[SEND] IOException:" + e.getMessage());
					close();
				}

			}
			// #debug
			Logger.log("Sender: exiting", Logger.DEBUG);

		}
	}

	/** open this channel */
	public abstract void open();

	/** close this channel */
	public abstract void close();

	/**
	 * Send a packet
	 * @param packetToSend
	 */
	public abstract void sendContent(byte[] packetToSend);

	/** @return true is the channel is open */
	public abstract boolean isOpen();

	/** Called for each tick of the server */
	protected boolean pollAlive() {
		return true;
	}
}
