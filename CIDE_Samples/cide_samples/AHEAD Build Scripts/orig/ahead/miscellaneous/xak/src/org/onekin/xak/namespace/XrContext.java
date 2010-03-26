package org.onekin.xak.namespace;


import java.util.Vector;
import java.util.Iterator;

import javax.xml.namespace.NamespaceContext;

public class XrContext implements NamespaceContext{

	public static final String NS_PREFIX = "xr";
	public static final String NS_URI = "http://www.atarix.org/xmlRefinement";
	public static XrContext instance;

	public static XrContext newInstance(){
		if (instance == null)
			instance = new XrContext();
		return instance;
	}
	
	public String getNamespaceURI(String prefix) {
      if (prefix.equals(NS_PREFIX)) {
          return NS_URI;
      } else {
          return null;
      }
     }

     public String getPrefix(String namespaceURI) { 
       if (namespaceURI.equals(NS_URI))
    	   return NS_PREFIX;
       return null; 
     }
	
     public Iterator getPrefixes(String namespaceURI) {
		if (namespaceURI.equals(NS_URI)){
			Vector prefixes = new Vector();
		    prefixes.addElement(NS_PREFIX);
		    return prefixes.iterator();
		}
        else
		    return null;
	 }
}
