package it.yup.xml;

// #debug
//@import it.yup.util.Logger;

import it.yup.util.Utils;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Enumeration;
import java.util.Vector;

public class Element {
	protected static int array_inc = 5;

	protected Object children[];
	protected String attributes[][];

	protected int nchildren;
	protected int nattributes;

	public String name;
	public String uri;

	// inserting time
	public long queueTime;
	public int maxWait;

	private static int _internal_id_counter; //unique id

	/**
	 * Build a full element. Warning children and attributes must not have empty space at end 
	 * @param uri
	 * @param name
	 * @param children
	 * @param attributes
	 */
	public Element(String uri, String name, Object children[],
			String attributes[][]) {
		this.uri = uri;
		this.name = name;
		this.children = children;
		this.nchildren = children.length;
		this.attributes = attributes;
		this.nattributes = attributes.length;
	}

	public Element(String uri, String name) {
		this.uri = uri;
		this.name = name;
		nchildren = 0;
		children = new Object[Element.array_inc];
		nattributes = 0;
		attributes = new String[Element.array_inc][3];
	}

	/**
	 * Copy constructor. The new element is a deep clone of the old one.
	 * @param e
	 */
	public Element(Element e) {
		this.name = e.name;
		this.uri = e.uri;
		this.attributes = new String[e.attributes.length][3];
		this.nattributes = e.nattributes;
		this.children = new Object[e.children.length];
		this.nchildren = e.nchildren;

		for (int i = 0; i < e.nattributes; i++) {
			String triplet[] = e.attributes[i];
			this.attributes[i] = new String[] { triplet[0], triplet[1],
					triplet[2] };
		}

		for (int i = 0; i < e.nchildren; i++) {
			if (e.children[i].getClass() != String.class) {
				this.children[i] = ((Element) e.children[i]).clone();
			} else {
				this.children[i] = e.children[i];
			}
		}
	}

	/* (non-Javadoc)
	 * @see it.yup.xml.Element#clone()
	 */
	public Element clone() {
		return new Element(this);
	}

	/**
	 * Set the minimum capacity of the internal arrays
	 * @param size: the new array size
	 * @param type: either 'a' or 'c', indicating attributes or children vector 
	 */
	public void ensureCapacity(int size, byte type) {
		if (type == 'a' && this.attributes.length < size) {
			String[][] old_v = this.attributes;
			this.attributes = new String[size][2];
			System.arraycopy(old_v, 0, this.attributes, 0, nattributes);
		} else if (type == 'c' && this.children.length < size) {
			Object[] old_v = this.children;
			this.children = new Object[size];
			System.arraycopy(old_v, 0, this.children, 0, nchildren);
		}
	}

	/**
	 * Get the value of an attribute
	 * @param name
	 * @return
	 */
	public String getAttribute(String name) {
		for (int i = 0; i < nattributes; i++) {
			String attr[] = attributes[i];
			if (attr[1].equals(name)) { return attr[2]; }
		}
		return null;
	}

	/**
	 * Set or add an attribute. Warning: internal buffers are arrays, calling too many times this
	 * method could be very inefficient
	 * @param name
	 * @param value
	 */
	public void setAttribute(String name, String value) {
		// first look if we must change the value of an existing attribute
		for (int i = 0; i < nattributes; i++) {
			String triplet[] = attributes[i];
			if (triplet[1].equals(name)) {
				triplet[2] = value;
				return;
			}
		}
		// enlarge capacity if the array is full
		if (nattributes >= attributes.length) {
			String[][] old_attrs = attributes;
			this.attributes = new String[attributes.length + Element.array_inc][3];
			System.arraycopy(old_attrs, 0, attributes, 0, nattributes);
		}
		attributes[nattributes++] = new String[] { null, name, value };
	}

	public void setAttributes(String[] names, String[] values) {
		for (int i = 0; i < names.length; i++)
			this.setAttribute(names[i], values[i]);
	}

	/**
	 * Delete an attribute. The size of the internal array is not shrinked
	 * @param name
	 */
	public void delAttribute(String name) {
		for (int i = 0, j = 0; i < nattributes; i++) {
			String triplet[] = attributes[i];
			if (!triplet[1].equals(name)) {
				this.attributes[j++] = triplet;
			}
		}
		nattributes--;
	}

	/**
	 * Build a unique id (to be used for IQs)
	 * XXX consider moving this to XMPP stanzas
	 * @return: the unique id
	 */
	public static String createUniqueId() {
		Element._internal_id_counter++;
		return "" + Element._internal_id_counter;
	}

	/**
	 * Create a child and add it to the dom tree
	 * @param uri
	 * @param name
	 * @return
	 */
	public Element addElement(String uri, String name) {
		if (uri == null) {
			uri = this.uri;
		}
		Element e = new Element(uri, name);
		// enlarge capacity if the array is full
		if (nchildren >= children.length) {
			Object old_v[] = children;
			this.children = new Object[old_v.length + 10];
			System.arraycopy(old_v, 0, children, 0, nchildren);
		}

		children[nchildren++] = e;
		return e;
	}

	// TODO rename addElement **after merge**
	public Element addElementAndContent(String uri, String name, String content) {
		if (uri == null) {
			uri = this.uri;
		}
		Element e = new Element(uri, name);
		e.addText(content);
		// enlarge capacity if the array is full
		if (nchildren >= children.length) {
			Object old_v[] = children;
			this.children = new Object[old_v.length + 10];
			System.arraycopy(old_v, 0, children, 0, nchildren);
		}

		children[nchildren++] = e;
		return e;
	}

	public void addElement(Element e) {
		if (nchildren >= children.length) {
			Object old_v[] = children;
			this.children = new Object[old_v.length + Element.array_inc];
			System.arraycopy(old_v, 0, children, 0, nchildren);
		}

		children[nchildren++] = e;
	}

	public void addText(String s) {
		if (nchildren >= children.length) {
			Object old_v[] = children;
			this.children = new Object[old_v.length + Element.array_inc];
			System.arraycopy(old_v, 0, children, 0, nchildren);
		}
		children[nchildren++] = s;
	}

	/**
	 * 
	 * @param namespace
	 * @param name
	 * @return
	 */
	public Element removeChild(String namespace, String name) {
		Object c, e = null;
		int j = 0;
		for (int i = 0; i < nchildren; i++) {
			c = children[i];
			if (c.getClass() != String.class
					&& (namespace == null || namespace
							.equals(((Element) c).uri))
					&& (name.equals(((Element) c).name))) {
				e = c;
				nchildren--;
			} else
				children[j++] = c;
		}

		return (Element) e;
	}

	/**
	 * 
	 * @param namespace
	 * @param name
	 * @return
	 */
	public void removeChild(Element el) {
		Object c = null;
		for (int i = 0; i < nchildren; i++) {
			c = children[i];
			if (c == el) {
				ensureCapacity(nchildren + array_inc, (byte) 'c');
				for (int l = i; l < nchildren; l++) {
					children[l] = children[l + 1];
				}
				nchildren--;
				break;
			}
		}
	}

	public void removeAllElements() {
		nchildren = 0;
		children = new Object[Element.array_inc];
	}

	/**
	 * Return the first child having the given name and namespace
	 * @param uri matched namespace (may be null, in this case any namaspace is ok)
	 * @param matched name
	 */
	public Element getChildByName(String uri, String name) {
		for (int i = 0; i < nchildren; i++) {
			Object e = children[i];
			if ((e.getClass() != String.class)
					&& ((Element) e).name.equals(name)
					&& (uri == null || ((Element) e).uri.equals(uri))) { return (Element) e; }
		}
		return null;
	}

	/**
	 * Return the children having the given name and namespace
	 * @param uri matched namespace (may be null, in this case any namaspace is ok)
	 * @param matched name
	 */
	public Element[] getChildrenByName(String uri, String name) {
		Element v[];
		int n = 0;
		// count the matching children
		for (int i = 0; i < nchildren; i++) {
			Object e = children[i];
			if ((e.getClass() != String.class)
					&& (((Element) e).name.equals(name))
					&& (uri == null || ((Element) e).uri.equals(uri))) {
				n++;
			}

		}
		// build the result vector and copy the matching elements
		v = new Element[n];
		n = 0;
		for (int i = 0; i < nchildren; i++) {
			Object e = children[i];
			if ((e.getClass() != String.class)
					&& (((Element) e).name.equals(name))
					&& (uri == null || ((Element) e).uri.equals(uri))) {
				v[n++] = (Element) e;
			}
		}
		return v;
	}

	/**
	 * Return all the non-text children 
	 */
	public Element[] getChildren() {
		Element v[];
		int n = 0;
		// count the matching children
		for (int i = 0; i < nchildren; i++) {
			Object e = children[i];
			if (e.getClass() != String.class) {
				n++;
			}

		}
		// build the result vector and copy the matching elements
		v = new Element[n];
		n = 0;
		for (int i = 0; i < nchildren; i++) {
			Object e = children[i];
			if (e.getClass() != String.class) {
				v[n++] = (Element) e;
			}
		}
		return v;
	}

	/**
	 * Get all the text 
	 */
	public String getText() {
		StringBuffer buf = new StringBuffer();
		// count the matching children
		for (int i = 0; i < nchildren; i++) {
			Object e = children[i];
			if (e.getClass() == String.class) {
				buf.append((String) e);
			}

		}
		return buf.toString();
	}

	public void resetText() {
		// count the matching children
		int j = 0;
		for (int i = 0; i < nchildren; i++) {
			Object e = children[i];
			if (e.getClass() != String.class) children[j] = children[i];
			else
				j--;
			j++;
		}
	}

	/** Serialize the element and all its children
	 *  (to plain xml)
	 * @return
	 */
	public byte[] toXml() {
		ByteArrayOutputStream baos = new ByteArrayOutputStream();

		try {
			writeXml(this, null, baos);
		} catch (IOException e) {
			// #debug 
//@			Logger.log("[Element:toXml] IOException" + e.getMessage());
		}
		return baos.toByteArray();
	}

	private static String writeEscaped(String s, int quot) throws IOException {
		StringBuffer escBuffer = new StringBuffer();

		for (int i = 0; i < s.length(); i++) {
			char c = s.charAt(i);
			switch (c) {
				case '\n':
				case '\r':
				case '\t':
					if (quot == -1) escBuffer.append(c);
					else
						escBuffer.append("&#" + ((int) c) + ';');
					break;
				case '&':
					escBuffer.append("&amp;");
					break;
				case '>':
					escBuffer.append("&gt;");
					break;
				case '<':
					escBuffer.append("&lt;");
					break;
				case '"':
				case '\'':
					if (c == quot) {
						escBuffer.append(c == '"' ? "&quot;" : "&apos;");
						break;
					}
				default:
					//if(c < ' ')
					//	throw new IllegalArgumentException("Illegal control code:"+((int) c));

					if (c >= ' ' && (c < 127)) escBuffer.append(c);
					else
						escBuffer.append("&#" + ((int) c) + ";");
			}
		}
		return escBuffer.toString();
	}

	private static void writeXml(Element el, String ns, OutputStream os)
			throws IOException {
		// TODO XML escaping of data
		os.write(Utils.getBytesUtf8("<" + el.name));
		if (ns == null || !ns.equals(el.uri)) {
			os.write(Utils.getBytesUtf8(" xmlns='" + el.uri + "'"));
			ns = el.uri;
		}

		Vector nsUsed = new Vector(5);
		for (int i = 0; i < el.nattributes; i++) {
			String triplet[] = el.attributes[i];
			String tripletString = " ";
			if (triplet[0] != null && triplet[0].length() > 0) {
				tripletString = " ns" + nsUsed.size() + ":";
				nsUsed.addElement(triplet[0]);
			}
			tripletString += triplet[1] + "='" + writeEscaped(triplet[2],'\'') + "'";
			os.write(Utils.getBytesUtf8(tripletString));
		}
		String nsDef = "";
		Enumeration en = nsUsed.elements();
		int index = 0;
		while (en.hasMoreElements()) {
			String ithNs = (String) en.nextElement();
			nsDef += (" xmlns:ns" + index + "='" + ithNs + "'");
		}
		os.write(Utils.getBytesUtf8(nsDef));

		if (el.children.length > 0) {
			os.write(Utils.getBytesUtf8(">"));
			for (int i = 0; i < el.nchildren; i++) {
				if (el.children[i].getClass() != String.class) {
					Element c = (Element) el.children[i];
					Element.writeXml(c, ns, os);
				} else {
					String data = (String) el.children[i];
					data = writeEscaped(data, -1);
					os.write(Utils.getBytesUtf8(data));
				}
			}
			os.write(Utils.getBytesUtf8("</" + el.name + ">"));
		} else {
			os.write(Utils.getBytesUtf8("/>"));
		}
	}

	//    public void serialize(DataOutputStream os) {
	//    	try {
	//			os.writeUTF(name==null?"":name);
	//			os.writeUTF(uri==null?"":uri);
	//			os.writeShort(attributes.length);
	//			for(int i=0; i<nattributes; i++) {
	//				String attr[]=(String[]) attributes[i];
	//				os.writeUTF(attr[0]);
	//				os.writeUTF(attr[1]);
	//			}
	//			os.writeShort(children.length);
	//			for(int i=0; i<nchildren; i++) {
	//				Object el = children[i];
	//				if(el.getClass() == String.class) {
	//					os.writeByte('T');
	//					os.writeUTF((String)el);
	//				} else {
	//					os.writeByte('E');
	//					((BElement)el).serialize(os);
	//				}
	//			}
	//		} catch (IOException e) {
	//			e.printStackTrace();
	//		};
	//    	
	//    }
	//    
	//    public static BElement load(DataInputStream is) {
	//    	String name;
	//    	String uri;
	//    	String attrs[][];
	//    	Object children[];
	//    	try {
	//			name = is.readUTF();
	//			uri = is.readUTF();
	//			int n = is.readShort();
	//			attrs = new String[n][2];
	//			for(int i=0; i<n; i++) {
	//				//String attr[] = new String[2];
	//				attrs[0][0] = is.readUTF();
	//				attrs[1][1] = is.readUTF();
	//			}
	//			n = is.readShort();
	//			children = new Object[n];
	//			for(int i=0; i<n; i++) {
	//				byte type = is.readByte();
	//				if(type == 'E') {
	//					children[i] = BElement.load(is);
	//				} else {
	//					children[i] = is.readUTF();
	//				}
	//			}
	//		} catch (IOException e) {
	//			return null;
	//		}
	//		return new BElement(name, uri, children, attrs);
	//    }

}

// the old code of Element (kept only a bit as a reference!!!)

/* Copyright (c) 2008 Bluendo S.r.L.
 * See about.html for details about license.
 *
 * $Id: Element.java 1377 2009-04-21 14:17:38Z luca $
*/
//
//package it.yup.xml;
//
//// #debug
////@import it.yup.util.Logger;
//
//
//import java.io.ByteArrayOutputStream;
//import java.io.DataInputStream;
//import java.io.DataOutputStream;
//import java.io.IOException;
//import java.util.Enumeration;
//import java.util.Vector;
//
//import org.xmlpull.v1.XmlPullParser;
//import org.xmlpull.v1.XmlPullParserException;
//import org.xmlpull.v1.XmlSerializer;
//
//public class Element_old 
//{
//    public Vector children;
//    public Vector attributes;
//    public String content;
//    public String name;
//    public String uri;
//    
//    // XXX delete?
//    // inserting time
//    public long queueTime;
//    public int maxWait;
//    
//    private static int _internal_id_counter; //unique id
//    
//    protected Element_old() {
//        children = new Vector();
//        attributes = new Vector();
//        uri = null;
//        name = null;
//    }
//    
//    public Element_old(String uri, String name) {
//        this.uri = uri;
//        this.name = name;
//        children = new Vector();
//        attributes = new Vector(); //Hashtable();
//    }
//    
//    public String getUri(String name) {
//        return null;
//    }
//    
//    // XXX theorically attributes may have prefixes too, but I don't remember any
//    // jabber packet using them
//    public String getAttribute(String name) {
//        for(int i=0; i<attributes.size(); i++) {
//            String attr[] = (String[]) attributes.elementAt(i);
//            if(attr[1].equals(name)) {
//                return attr[2];
//            }
//        }
//        return null;
//    }
//    
//    public void setAttribute (String name, String value) {
//        Enumeration en = attributes.elements();
//        while(en.hasMoreElements()) {
//            String triplet[] = (String[]) en.nextElement();
//            if(triplet[1].equals(name)) {
//                triplet[2] = value;
//                return;
//            }
//        }
//        attributes.addElement(new String[] {null,name, value});
//    }
//
//    public void delAttribute (String name) {
//        Enumeration en = attributes.elements();
//        while(en.hasMoreElements()) {
//            String triplet[] = (String[]) en.nextElement();
//            if(triplet[1].equals(name)) {
//                attributes.removeElement(triplet);
//                return;
//            }
//        }
//    }
//    
//    // easy creation a of child
//    public Element_old addElement(String uri, String name) {
//    	if (uri == null){
//    		uri = this.uri;
//    	}
//        Element_old e = new Element_old(uri, name);
//        children.addElement(e);
//        return e;
//    }
//    
//    // set the id attribute to the element, using the static id class field
//    public static String createUniqueId() {
//        Element_old._internal_id_counter++;
//        return "" + Element_old._internal_id_counter;
//    }
//    
//    /**
//     * Return the first child having the given name and namespace
//     * @param uri matched namespace (may be null, in this case any namaspace is ok)
//     * @param matched name
//     */
//    public Element_old getChildByName(String uri, String name) {
//        for(int i=0; i<children.size(); i++) {
//            Element_old e = (Element_old) children.elementAt(i);
//            if(e.name.equals(name) && (uri == null || e.uri.equals(uri))) {
//                return e;
//            }
//        }
//        return null;
//    }
//    
//    /**
//     * Return the children having the given name and namespace
//     * @param uri matched namespace (may be null, in this case any namaspace is ok)
//     * @param matched name
//     */
//    public Element_old[] getChildrenByName(String uri, String name) {
//    	Element_old v[];
//    	int n = 0;
//        for(int i=0; i<children.size(); i++) {
//            Element_old e = (Element_old) children.elementAt(i);
//            if(e.name.equals(name) && (uri == null || e.uri.equals(uri))) {
//            	n++;
//            }
//        }
//        v = new Element_old[n];
//        n=0;
//        for(int i=0; i<children.size(); i++) {
//            Element_old e = (Element_old) children.elementAt(i);
//            if(e.name.equals(name) && (uri == null || e.uri.equals(uri))) {
//            	v[n++] = e;
//            }
//        }
//        return v;
//    }
//    
//    public static Element_old parseDocument(XmlPullParser parser) throws XmlPullParserException, IOException {
//        Element_old el = new Element_old();
//        parser.require(XmlPullParser.START_DOCUMENT, null, null);
//        parser.nextToken();
//        el._parse(parser);
//        return el;
//    }
//    
//    public static Element_old pullElement(XmlPullParser parser) throws XmlPullParserException, IOException {
//        Element_old el = new Element_old();
//        el._parse(parser);
//        return el;
//    }
//    
//    public static Element_old pullDocumentStart(XmlPullParser parser) throws XmlPullParserException, IOException {
//        Element_old el = new Element_old();
//        el.name = parser.getName();
//        el.uri = parser.getNamespace();
//        for(int i = 0; i<parser.getAttributeCount(); i++) {
//            el.attributes.addElement(new String[] {parser.getAttributeNamespace(i),parser.getAttributeName(i), parser.getAttributeValue(i)});
//        }
//        return el;
//    }
//    
//    protected void _parse(XmlPullParser parser) throws XmlPullParserException, IOException {
//        for(int i = 0; i<parser.getAttributeCount(); i++) {
//            attributes.addElement(new String[] {parser.getAttributeNamespace(i),parser.getAttributeName(i), parser.getAttributeValue(i)});
//        }  
//        this.name = parser.getName();
//        this.uri = parser.getNamespace();
//        
//        boolean finished = false;
//        StringBuffer textBuffer = null;
//        do {
//            int type = parser.nextToken();
//            switch(type) {
//                case XmlPullParser.START_TAG:
//                    Element_old child = new Element_old();
//                    child._parse(parser);
//                    children.addElement(child);
//                    break;
//                case XmlPullParser.END_TAG:
//                		finished = true;
//                    break;
//                case XmlPullParser.COMMENT:
//                    break;
//                default:
//                    if(parser.getText()!=null) {
//                        if(textBuffer==null) textBuffer = new StringBuffer();
//                        textBuffer.append(parser.getText()); 
//                    } 
//            }
//            
//        } while(!finished);
//        if(textBuffer!=null){
//            String content = textBuffer.toString();
//            content.trim();
//            if(content.length()>0){
//                this.content =  content;
//            }
//        }      
//    }  
//    
//    // serialize the element and all its children
//    public byte[] toXml()
//    {
//        ByteArrayOutputStream baos = new ByteArrayOutputStream();
//        KXmlSerializer serializer = new KXmlSerializer();
//        try {
//            serializer.setOutput(baos, "UTF-8");
//            write(serializer);
//            serializer.endDocument();
//            serializer.flush();
//        } catch (IOException e) {
//// #debug 
////@        	Logger.log("[Element:toXml] IOException" + e.getMessage());
//        }
//        return baos.toByteArray();
//    }
//    
//        
//    private void write(XmlSerializer serializer) {
//        try {
//			serializer.setPrefix("", this.uri);
//			serializer.startTag(this.uri, name);
//            Enumeration attrEn = attributes.elements();
//            while(attrEn.hasMoreElements()) {
//            	String attr[] = (String[]) attrEn.nextElement();
//            	serializer.attribute(attr[0], attr[1], attr[2]);
//            }
//            Enumeration childEn = children.elements();
//            while(childEn.hasMoreElements()) {
//                Element_old el = (Element_old) childEn.nextElement();
//                el.write(serializer);
//            }
//            if(content!=null) {
//                serializer.text(content);
//            }
//            serializer.endTag(this.uri, name);
//            serializer.flush();
//        } catch (IllegalArgumentException e) {
//// #debug
////@        	Logger.log("[Element::write] IllegalArgumentException:" + e.getMessage());
//        } catch (IllegalStateException e) {
////        	 #debug
////@        	Logger.log("[Element::write] IllegalStateException: " + e.getMessage());
//        } catch (IOException e) {
////        	 #debug
////@        	Logger.log("[Element::write] IOException: " + e.getMessage());    		
//        }
//        
//    }
//    
//    public Element_old(Element_old e) {
//    	this();
//    	this.name = e.name;
//    	this.uri = e.uri;
//    	this.content = e.content;
//    	
//    	for(int i=0; i<e.attributes.size(); i++) {
//    		String triplet[] = (String[]) e.attributes.elementAt(i);
//    		this.attributes.addElement(new String[] {triplet[0], triplet[1],triplet[2]});
//    	}
//    	
//    	for(int i=0; i<e.children.size(); i++) {
//    		Element_old c = (Element_old) e.children.elementAt(i);
//    		this.children.addElement(c.clone());
//    	}
//    } 
//    
//    public Element_old clone() {
//    	Element_old e = new Element_old();
//    	e.name = this.name;
//    	e.uri = this.uri;
//    	e.content = this.content;
//    	
//    	for(int i=0; i<this.attributes.size(); i++) {
//    		String triplet[] = (String[]) this.attributes.elementAt(i);
//    		e.attributes.addElement(new String[] {triplet[0], triplet[1], triplet[2]});
//    	}
//    	
//    	for(int i=0; i<this.children.size(); i++) {
//    		Element_old c = (Element_old) this.children.elementAt(i);
//    		e.children.addElement(c.clone());
//    	}
//    	return e;
//    }
//    
//    public void serialize(DataOutputStream os) {
//    	try {
//			os.writeUTF(name==null?"":name);
//			os.writeUTF(uri==null?"":uri);
//			os.writeUTF(content==null?"":content);
//			os.writeShort(attributes.size());
//			for(int i=0; i<attributes.size(); i++) {
//				String attr[]=(String[]) attributes.elementAt(i);
//				// XXX check what happens if null
//				os.writeUTF(attr[0]);
//				os.writeUTF(attr[1]);
//				os.writeUTF(attr[2]);
//			}
//			os.writeShort(children.size());
//			for(int i=0; i<children.size(); i++) {
//				Element_old el = (Element_old) children.elementAt(i);
//				el.serialize(os);
//			}
//		} catch (IOException e) {
//			e.printStackTrace();
//		};
//    	
//    }
//    
//    public static Element_old load(DataInputStream is) {
//    	Element_old el = new Element_old();
//    	try {
//			el.name = is.readUTF();
//			el.uri = is.readUTF();
//			el.content = is.readUTF();
//			int n = is.readShort();
//			for(int i=0; i<n; i++) {
//				String attr[] = new String[3];
//				attr[0] = is.readUTF();
//				attr[1] = is.readUTF();
//				attr[2] = is.readUTF();
//				el.attributes.addElement(attr);
//			}
//			n = is.readShort();
//			for(int i=0; i<n; i++) {
//				el.children.addElement(Element_old.load(is));
//			}
//		} catch (IOException e) {
//			return null;
//		}
//		return el;
//    }
//}
