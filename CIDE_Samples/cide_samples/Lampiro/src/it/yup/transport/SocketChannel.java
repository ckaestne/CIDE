/* Copyright (c) 2008 Bluendo S.r.L.
 * See about.html for details about license.
 *
 * $Id: SocketChannel.java 1464 2009-05-12 14:56:52Z luca $
*/

package it.yup.transport;

// #debug
import it.yup.util.Logger;

import it.yup.util.Utils;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.Reader;
import java.util.TimerTask;

import javax.microedition.io.Connector;
import javax.microedition.io.SocketConnection;
import javax.microedition.io.StreamConnection;

//#ifdef TLS
import org.bouncycastle.crypto.tls.AlwaysValidVerifyer;
import org.bouncycastle.crypto.tls.TlsInputStream; 
//#endif

import org.bouncycastle.crypto.tls.TlsProtocolHandler;

// #ifdef COMPRESSION
import com.jcraft.jzlib.JZlib;
import com.jcraft.jzlib.ZInputStream;
import com.jcraft.jzlib.ZOutputStream;

// #endif

public class SocketChannel extends BaseChannel {

	/** String identifying the transport type */
	public static final String TRANSPORT_TYPE = "DIRECT_SOCKET";

	/**  Keepalive interval for XML streams */
	// Please note that if we are using WiFi this KEEP_ALIVE must be very short
	// Many cellphone WiFi implementations in fact hangs after 20-30s of inactivity,
	// and they cannot receive packets any more
	public long KEEP_ALIVE = 300 * 1000;

	protected String connectionUrl;
	protected TransportListener listener;
	protected StreamConnection connection;

	protected boolean exiting = false;


	// #ifdef COMPRESSION	
	private boolean compressed = false;
	// #endif

	private TimerTask ka_task = null;

	/*
	 * The handler used to trasnport tls data
	 */
	protected static TlsProtocolHandler handler;

	public SocketChannel(String connectionUrl,
			TransportListener transportListener) {
		this.connectionUrl = connectionUrl;
		this.listener = transportListener;
		this.transportType = TRANSPORT_TYPE;
		inputStream = null;
		outputStream = null;
	}

	public void open() {

		exiting = false;

		// create the opener and start the connection
		Runnable starter = new Runnable() {
			public void run() {

				inputStream = null;
				outputStream = null;

				try {
					// #debug					
					Logger.log("Connecting to " + connectionUrl);
					connection = (SocketConnection) Connector
							.open(connectionUrl);
					// #debug					
					Logger.log("Connected ");
					inputStream = connection.openInputStream();
					outputStream = connection.openOutputStream();

					// start the sender after each new connection
					sender = new Sender(SocketChannel.this);
					sender.start();

					listener.connectionEstablished(SocketChannel.this);
				} catch (IOException e) {
					// #debug					
					Logger.log("Connection failed: " + e.getMessage());
					listener.connectionFailed(SocketChannel.this);
				} catch (Exception e) {
					// #debug		    		
					Logger.log("Unexpected exception: " + e.getMessage());
					listener.connectionFailed(SocketChannel.this);
					//		    		YUPMidlet.yup.reportException("Unexpected Exception on Channel start.", e, null);
				}
			}
		};
		new Thread(starter).start();
	}

	public void close() {
		if (pollAlive() == false)
			return;
		exiting = true;
		try {
			inputStream.close();
			outputStream.close();
			connection.close();
		} catch (IOException e) {
			// #mdebug 
			System.out.println("In closing strean");
			e.printStackTrace();
			// #enddebug
		} catch (Exception e) {
			// #mdebug 
			System.out.println("In closing strean");
			e.printStackTrace();
			// #enddebug
		}
	}

	public boolean isOpen() {
		return inputStream != null;
	}

	public InputStream getInputStream() {
		return this.inputStream;
	}

	public OutputStream getOutputStream() {
		return this.outputStream;
	}

	public void sendContent(byte[] packetToSend) {
		synchronized (packets) {
			packets.addElement(packetToSend);
			packets.notify();
		}

		if (ka_task != null) {
			ka_task.cancel();
		}

		ka_task = new TimerTask() {
			public void run() {
				/* utf-8 space */
				sendContent(new byte[] { 0x20 });
			}

		};

		Utils.tasks.schedule(ka_task, KEEP_ALIVE);

	}

	protected boolean pollAlive() {
		return !exiting;
	}

	/**
	 * Wrapper of the input stream capable of reading UTF characters
	 * (the default reader hangs at least on nokia phones)
	 *
	 */
	public class UTFReader extends Reader {

		private InputStream is;
		private byte buf[];
		private int offset = -1;
		private int available = -1;

		public UTFReader(InputStream is) {
			this.is = is;
			this.buf = new byte[512];
		}

		public void close() throws IOException {
			is.close();
		}

		public int read(char[] arg0, int arg1, int arg2) throws IOException {
			throw new IOException("Unsupported method");
		}

		public int read(char[] arg0) throws IOException {
			throw new IOException("Unsupported method");
		}

		private int getByte() throws IOException {


			int b;
			// buffered reading
			if (false && offset > 0 && offset < available) {
				System.out.println("+++:" + offset + "/" + available);
				b = buf[offset++];
			} else {
				available = is.available();
				available = 0;
				if (available > 0) {
					//					do {
					//						Logger.log("read");
					available = is.read(buf, 0,
							(available < buf.length) ? available : buf.length);
					//						if(available == -1) {
					//							try {
					////								Logger.log("tick");
					//								Thread.sleep(1000);
					//							} catch (InterruptedException e) {
					//								// TODO Auto-generated catch block
					//								e.printStackTrace();
					//							}
					//						}
					//					}while(available == -1);
					System.out.println(">>:" + available);
					b = buf[0];
					offset = 1;
				} else {
					// block waiting for the first char
					b = is.read();
				}
			}
			return b & 0xFF;
		}


		public int read() throws IOException {
			int b;
			int ch = 0;
			b = getByte();
			// #ifdef COMPRESSION
			InputStream sockInstream = SocketChannel.this.getInputStream();
			if (sockInstream instanceof ZInputStream) {
				BaseChannel.bytes_received = (int) ((ZInputStream) sockInstream)
						.getTotalIn();
			}
															else
															{
																// #ifndef TLS 
																SocketChannel.this.bytes_received++;
																// #endif
																// #ifdef TLS
																if (sockInstream instanceof TlsInputStream == false) {
																	BaseChannel.bytes_received++;
																} else if (sockInstream instanceof TlsInputStream == true) {
																	BaseChannel.bytes_received = SocketChannel.handler
																			.getBytes_received();
																}
																// #endif
																}			
			// #endif
// #ifndef COMPRESSION
												SocketChannel.bytes_received++;
			// #endif

			if (b == 0xff) {
				throw new IOException("Invalid byte received on text stream: "
						+ b);
				//return -1;
			} else if (b <= 0x07F) {
				ch = b;
			} else {
				byte b1 = 0;
				byte b2 = 0;
				byte b3 = 0;
				byte b4 = 0;

				if (b >= 0x0C2 && b <= 0x0DF) {
					b3 = (byte) (b & 0x1F);
					b4 = (byte) (getByte() & 0x3F);
				} else if (b >= 0x0E0 && b <= 0x0EF) {
					b2 = (byte) (b & 0x0F);
					b3 = (byte) (getByte() & 0x3F);
					b4 = (byte) (getByte() & 0x3F);
				} else if (b >= 0x0F0 && b <= 0x0F4) {
					b1 = (byte) (b & 0x07);
					b2 = (byte) (getByte() & 0x3F);
					b3 = (byte) (getByte() & 0x3F);
					b4 = (byte) (getByte() & 0x3F);
				}

				ch = (b1 << 18) + (b2 << 12) + (b3 << 6) + b4;
			}
			return ch;
		}

	}

	public UTFReader getReader() {
		return new UTFReader(getInputStream());
	}

	//#ifdef COMPRESSION
	public void startCompression() {
		synchronized (packets) {
			compressed = true;
			inputStream = new ZInputStream(inputStream);
			outputStream = new ZOutputStream(outputStream,
					JZlib.Z_DEFAULT_COMPRESSION);
			((ZOutputStream) outputStream).setFlushMode(JZlib.Z_PARTIAL_FLUSH);
			//sender.exiting = true;
			//packets.notify();
		}
	}

	//	#endif

	// #ifdef TLS
	public void startTLS() throws IOException {
		synchronized (packets) {
			TlsProtocolHandler handler = new TlsProtocolHandler(inputStream,
					outputStream);
			handler.connect(new AlwaysValidVerifyer());
			this.handler = handler;
			outputStream = handler.getOutputStream();
			inputStream = handler.getInputStream();

		}
	}
	//  #endif

}
