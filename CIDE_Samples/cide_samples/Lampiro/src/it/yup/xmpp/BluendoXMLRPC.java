package it.yup.xmpp;

import it.yup.xml.BProcessor;
import it.yup.xml.Element;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

import javax.microedition.io.Connector;
import javax.microedition.io.SocketConnection;

public class BluendoXMLRPC {

	private String connectionUrl;

	private byte header = 0;

	private DataInputStream inputStream = null;
	private DataOutputStream outputStream = null;

	private SocketConnection regConnection = null;

	public BluendoXMLRPC(String connectionUrl) {
		this.connectionUrl = connectionUrl;
	}

	public void open() throws IOException {
		regConnection = (SocketConnection) Connector.open(this.connectionUrl);
		inputStream = new DataInputStream(regConnection.openInputStream());
		outputStream = new DataOutputStream(regConnection.openOutputStream());
	}

	public void write(Element el) throws IOException {
		byte[] bytesEl = BProcessor.toBinary(el);
		outputStream.write(header);
		outputStream.write(bytesEl.length >> 8);
		outputStream.write(bytesEl.length & 0xff);
		outputStream.write(bytesEl, 0, bytesEl.length);
	}

	public Element read() throws IOException {
		int length = 0;
		byte[] resBytes = null;

		inputStream.read();
		length = inputStream.read();
		length = length << 8;
		length += inputStream.read();
		resBytes = new byte[length];
		//inputStream.read(resBytes, 0, length);
		inputStream.readFully(resBytes, 0, resBytes.length);
		Element res = BProcessor.parse(resBytes);
		return res;
	}

	public void close() throws IOException {
		this.regConnection.close();
	}

	public void flush() throws IOException {
		this.outputStream.flush();
	}

}
