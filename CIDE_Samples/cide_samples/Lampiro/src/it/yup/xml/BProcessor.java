package it.yup.xml;

// #debug
//@import it.yup.util.Logger;
import it.yup.util.Utils;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class BProcessor {

	//  private static int TAG_BElement = 0x00;
	private static int TAG_NS = 0x01;
	private static int TAG_TEXT = 0x02;

	public static Element parse(byte data[]) {
		ByteArrayInputStream bais = new ByteArrayInputStream(data);
		return (Element) parse(bais, "jabber:client");
	}

	private static Object parse(InputStream is, String ns) {
		DataInputStream dis = new DataInputStream(is);
		int n;
		byte buf[];
		String uri = ns;

		try {
			int ctag[] = untag(is);
			if (ctag[0] == BProcessor.TAG_NS) {
				buf = new byte[ctag[1]];
				//is.read(buf, 0, buf.length);
				dis.readFully( buf, 0, buf.length);
				uri = Utils.getStringUTF8(buf);
				ctag = untag(is);
			}

			buf = new byte[ctag[1]];
			//is.read(buf, 0, buf.length);
			dis.readFully( buf, 0, buf.length);

			if (ctag[0] == BProcessor.TAG_TEXT) {
				return Utils.getStringUTF8(buf);
			} else {
				String name;
				String attrs[][] = null;
				Object children[] = null;

				name = Utils.getStringUTF8(buf);
				// number of attributes
				n = is.read();
				if (n == -1) throw new IOException();
				if ((n & 0x80) == 0x80) {
					int n1 = is.read();
					if (n1 == -1) throw new IOException();
					n = ((n & 0x7f) << 8) + n1;
				}
				if (n == -1) throw new IOException();
				attrs = new String[n][3];
				int len;
				for (int i = 0; i < n; i++) {
					int[] atag = untag(is);
					if (atag[0] == 0x01) {
						buf = new byte[atag[1]];
						dis.readFully(buf, 0, buf.length);
						//is.read(buf, 0, buf.length);
						uri = Utils.getStringUTF8(buf);
						attrs[i][0] = uri;
						atag = untag(is);
					}
					buf = new byte[atag[1]];
					//is.read(buf, 0, buf.length);
					dis.readFully(buf, 0, buf.length);
					attrs[i][1] = Utils.getStringUTF8(buf);
					len = unlen(is);
					buf = new byte[len];
					//is.read(buf, 0, buf.length);
					dis.readFully( buf, 0, buf.length);
					attrs[i][2] = Utils.getStringUTF8(buf);
				}
				// number of children
				n = is.read();
				if ((n & 0x80) == 0x80) {
					int n1 = is.read();
					if (n1 == -1) throw new IOException();
					n = ((n & 0x7f) << 8) + n1;
				}
				if (n == -1) throw new IOException();
				children = new Object[n];
				for (int i = 0; i < n; i++) {
					children[i] = parse(is, uri);
				}
				return new Element(uri, name, children, attrs);
			}
		} catch (IOException e) {
			// #mdebug
//@			e.printStackTrace();
//@			Logger.log("In BProcessor parsing ");
			// #enddebug
		}

		return null;
	}

	private static int[] untag(InputStream is) throws IOException {
		int ctag[] = new int[2]; // {type, len}
		int b = is.read();
		if (b == -1) throw new IOException();
		ctag[0] = (b & 0x60) >> 5;
		if ((b & 0x80) == 0x80) {
			ctag[1] = (b & 0x1f) << 8;
			b = is.read();
			if (b == -1) throw new IOException(); // unexpected end of stream
			ctag[1] += b;
		} else {
			ctag[1] = b & 0x1f;
		}
		return ctag;
	}

	private static int unlen(InputStream is) throws IOException {
		int len;
		int b = is.read();
		if (b == -1) throw new IOException();
		if ((b & 0x80) == 0x80) {
			len = (b & 0x7f) << 8;
			b = is.read();
			if (b == -1) throw new IOException(); // unexpected end of stream
			len += b;
		} else {
			len = b;
		}
		return len;
	}

	public static byte[] toBinary(Element el) {
		ByteArrayOutputStream baos = new ByteArrayOutputStream();

		try {
			writeBinary(el, null, baos);
		} catch (IOException e) {
			// #debug 
//@			Logger.log("[BElement:toXml] IOException" + e.getMessage());
		}
		return baos.toByteArray();
	}

	private static void writeTag(byte type, String data, OutputStream os)
			throws IOException {
		byte bdata[] = Utils.getBytesUtf8(data);
		type = (byte) (type << 5);
		if (bdata.length < 32) {
			os.write(type | ((byte) bdata.length));
		} else {
			os.write(0x80 | type | (bdata.length >> 8));
			os.write(bdata.length & 0xff);
		}
		os.write(bdata);
	};

	private static void writeLength(int l, OutputStream os) throws IOException {
		if (l < 128) {
			os.write(l);
		} else {
			os.write(0x80 | (l >> 8));
			os.write(l & 0xff);
		}
	};

	private static void writeBinary(Element el, String ns, OutputStream os)
			throws IOException {
		if (ns == null || !ns.equals(el.uri)) {
			ns = el.uri;
			writeTag((byte) 0x01, ns, os);
		}
		writeTag((byte) 0x00, el.name, os);
		writeLength(el.nattributes, os);
		for (int i = 0; i < el.nattributes; i++) {
			String attNs = el.attributes[i][0];
			String k = el.attributes[i][1];
			byte v[] = Utils.getBytesUtf8(el.attributes[i][2]);
			if (attNs != null && attNs.length() > 0 && !attNs.equals(el.uri)) writeTag(
					(byte) 0x01, attNs, os);
			writeTag((byte) 0x00, k, os);
			writeLength(v.length, os);
			os.write(v);
		}
		writeLength(el.nchildren, os);
		for (int i = 0; i < el.nchildren; i++) {
			Object c = el.children[i];
			if (c.getClass() == String.class) writeTag((byte) 0x02, (String) c,
					os);
			else
				writeBinary((Element) c, ns, os);
		}
	}
}
