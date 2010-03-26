/* Copyright (c) 2008 Bluendo S.r.L.
 * See about.html for details about license.
 *
 * $Id: Element.java 1132 2009-01-26 16:05:01Z luca $
*/

package it.yup.xmlstream;

// #debug
//@import it.yup.util.Logger;

import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.util.Enumeration;
import java.util.Vector;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlSerializer;

public class Element 
{
    public Vector children;
    public Vector attributes;
    public String content;
    public String name;
    public String uri;
    
    // XXX delete?
    // inserting time
    public long queueTime;
    public int maxWait;
    
    private static int _internal_id_counter; //unique id
    
    protected Element() {
        children = new Vector();
        attributes = new Vector();
        uri = null;
        name = null;
    }
    
    public Element(String uri, String name) {
        this.uri = uri;
        this.name = name;
        children = new Vector();
        attributes = new Vector(); //Hashtable();
    }
    
    public String getUri(String name) {
        return null;
    }
    
    // XXX theorically attributes may have prefixes too, but I don't remember any
    // jabber packet using them
    public String getAttribute(String name) {
        for(int i=0; i<attributes.size(); i++) {
            String attr[] = (String[]) attributes.elementAt(i);
            if(attr[1].equals(name)) {
                return attr[2];
            }
        }
        return null;
    }
    
    public void setAttribute (String name, String value) {
        Enumeration en = attributes.elements();
        while(en.hasMoreElements()) {
            String triplet[] = (String[]) en.nextElement();
            if(triplet[1].equals(name)) {
                triplet[2] = value;
                return;
            }
        }
        attributes.addElement(new String[] {null,name, value});
    }

    public void delAttribute (String name) {
        Enumeration en = attributes.elements();
        while(en.hasMoreElements()) {
            String triplet[] = (String[]) en.nextElement();
            if(triplet[1].equals(name)) {
                attributes.removeElement(triplet);
                return;
            }
        }
    }
    
    // easy creation a of child
    public Element addElement(String uri, String name) {
        Element e = new Element(uri, name);
        children.addElement(e);
        return e;
    }
    
    // set the id attribute to the element, using the static id class field
    public static String createUniqueId() {
        Element._internal_id_counter++;
        return "" + Element._internal_id_counter;
    }
    
    /**
     * Return the first child having the given name and namespace
     * @param uri matched namespace (may be null, in this case any namaspace is ok)
     * @param matched name
     */
    public Element getChildByName(String uri, String name) {
        for(int i=0; i<children.size(); i++) {
            Element e = (Element) children.elementAt(i);
            if(e.name.equals(name) && (uri == null || e.uri.equals(uri))) {
                return e;
            }
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
        for(int i=0; i<children.size(); i++) {
            Element e = (Element) children.elementAt(i);
            if(e.name.equals(name) && (uri == null || e.uri.equals(uri))) {
            	n++;
            }
        }
        v = new Element[n];
        n=0;
        for(int i=0; i<children.size(); i++) {
            Element e = (Element) children.elementAt(i);
            if(e.name.equals(name) && (uri == null || e.uri.equals(uri))) {
            	v[n++] = e;
            }
        }
        return v;
    }
    
    public static Element parseDocument(XmlPullParser parser) throws XmlPullParserException, IOException {
        Element el = new Element();
        parser.require(XmlPullParser.START_DOCUMENT, null, null);
        parser.nextToken();
        el._parse(parser);
        return el;
    }
    
    public static Element pullElement(XmlPullParser parser) throws XmlPullParserException, IOException {
        Element el = new Element();
        el._parse(parser);
        return el;
    }
    
    public static Element pullDocumentStart(XmlPullParser parser) throws XmlPullParserException, IOException {
        Element el = new Element();
        el.name = parser.getName();
        el.uri = parser.getNamespace();
        for(int i = 0; i<parser.getAttributeCount(); i++) {
            el.attributes.addElement(new String[] {parser.getAttributeNamespace(i),parser.getAttributeName(i), parser.getAttributeValue(i)});
        }
        return el;
    }
    
    protected void _parse(XmlPullParser parser) throws XmlPullParserException, IOException {
        for(int i = 0; i<parser.getAttributeCount(); i++) {
            attributes.addElement(new String[] {parser.getAttributeNamespace(i),parser.getAttributeName(i), parser.getAttributeValue(i)});
        }  
        this.name = parser.getName();
        this.uri = parser.getNamespace();
        
        boolean finished = false;
        StringBuffer textBuffer = null;
        do {
            int type = parser.nextToken();
            switch(type) {
                case XmlPullParser.START_TAG:
                    Element child = new Element();
                    child._parse(parser);
                    children.addElement(child);
                    break;
                case XmlPullParser.END_TAG:
                		finished = true;
                    break;
                case XmlPullParser.COMMENT:
                    break;
                default:
                    if(parser.getText()!=null) {
                        if(textBuffer==null) textBuffer = new StringBuffer();
                        textBuffer.append(parser.getText()); 
                    } 
            }
            
        } while(!finished);
        if(textBuffer!=null){
            String content = textBuffer.toString();
            content.trim();
            if(content.length()>0){
                this.content =  content;
            }
        }      
    }  
    
    // serialize the element and all its children
    public byte[] toXml()
    {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        KXmlSerializer serializer = new KXmlSerializer();
        try {
            serializer.setOutput(baos, "UTF-8");
            write(serializer);
            serializer.endDocument();
            serializer.flush();
        } catch (IOException e) {
// #debug 
//@        	Logger.log("[Element:toXml] IOException" + e.getMessage());
        }
        return baos.toByteArray();
    }
    
        
    private void write(XmlSerializer serializer) {
        try {
			serializer.setPrefix("", this.uri);
			serializer.startTag(this.uri, name);
            Enumeration attrEn = attributes.elements();
            while(attrEn.hasMoreElements()) {
            	String attr[] = (String[]) attrEn.nextElement();
            	serializer.attribute(attr[0], attr[1], attr[2]);
            }
            Enumeration childEn = children.elements();
            while(childEn.hasMoreElements()) {
                Element el = (Element) childEn.nextElement();
                el.write(serializer);
            }
            if(content!=null) {
                serializer.text(content);
            }
            serializer.endTag(this.uri, name);
            serializer.flush();
        } catch (IllegalArgumentException e) {
// #debug
//@        	Logger.log("[Element::write] IllegalArgumentException:" + e.getMessage());
        } catch (IllegalStateException e) {
//        	 #debug
//@        	Logger.log("[Element::write] IllegalStateException: " + e.getMessage());
        } catch (IOException e) {
//        	 #debug
//@        	Logger.log("[Element::write] IOException: " + e.getMessage());    		
        }
        
    }
    
    public Element(Element e) {
    	this();
    	this.name = e.name;
    	this.uri = e.uri;
    	this.content = e.content;
    	
    	for(int i=0; i<e.attributes.size(); i++) {
    		String triplet[] = (String[]) e.attributes.elementAt(i);
    		this.attributes.addElement(new String[] {triplet[0], triplet[1],triplet[2]});
    	}
    	
    	for(int i=0; i<e.children.size(); i++) {
    		Element c = (Element) e.children.elementAt(i);
    		this.children.addElement(c.clone());
    	}
    } 
    
    public Element clone() {
    	Element e = new Element();
    	e.name = this.name;
    	e.uri = this.uri;
    	e.content = this.content;
    	
    	for(int i=0; i<this.attributes.size(); i++) {
    		String triplet[] = (String[]) this.attributes.elementAt(i);
    		e.attributes.addElement(new String[] {triplet[0], triplet[1], triplet[2]});
    	}
    	
    	for(int i=0; i<this.children.size(); i++) {
    		Element c = (Element) this.children.elementAt(i);
    		e.children.addElement(c.clone());
    	}
    	return e;
    }
    
    public void serialize(DataOutputStream os) {
    	try {
			os.writeUTF(name==null?"":name);
			os.writeUTF(uri==null?"":uri);
			os.writeUTF(content==null?"":content);
			os.writeShort(attributes.size());
			for(int i=0; i<attributes.size(); i++) {
				String attr[]=(String[]) attributes.elementAt(i);
				// XXX check what happens if null
				os.writeUTF(attr[0]);
				os.writeUTF(attr[1]);
				os.writeUTF(attr[2]);
			}
			os.writeShort(children.size());
			for(int i=0; i<children.size(); i++) {
				Element el = (Element) children.elementAt(i);
				el.serialize(os);
			}
		} catch (IOException e) {
			e.printStackTrace();
		};
    	
    }
    
    public static Element load(DataInputStream is) {
    	Element el = new Element();
    	try {
			el.name = is.readUTF();
			el.uri = is.readUTF();
			el.content = is.readUTF();
			int n = is.readShort();
			for(int i=0; i<n; i++) {
				String attr[] = new String[3];
				attr[0] = is.readUTF();
				attr[1] = is.readUTF();
				attr[2] = is.readUTF();
				el.attributes.addElement(attr);
			}
			n = is.readShort();
			for(int i=0; i<n; i++) {
				el.children.addElement(Element.load(is));
			}
		} catch (IOException e) {
			return null;
		}
		return el;
    }
}
