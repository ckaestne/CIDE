package org.onekin.xml.refinement.xupdate.query;
/*
* @author Felipe
*
* TODO To change the template for this generated type comment go to
* Window - Preferences - Java - Code Style - Code Templates
*/

import org.w3c.dom.DocumentFragment;
import org.w3c.dom.NodeList;

import org.infozone.tools.xml.queries.XObject;

public final class XObjectImpl implements XObject {

    //
    // Data
    //

    private NodeList _xobj = null;  

    
    /** 
     * Creates a new XalanXObject.
     * @param xobj Xalans native XObject that should be wrapped.
     * @exception IllegalArgumentException If the given XObject was null.
     */
    public XObjectImpl (NodeList xobj) throws IllegalArgumentException {        
      if (xobj == null)
            throw new IllegalArgumentException ("NodeList: Argument was null!");
        _xobj = xobj;
    }


    public int getType() {
        return CLASS_NODESET;
    }


    public boolean bool()
        throws org.xml.sax.SAXException {
        return true;
    }


    public double num() 
        throws org.xml.sax.SAXException {
        return _xobj.getLength();
    }


    public String str() {
        return _xobj.toString();
    }


    public NodeList nodeset()
        throws org.xml.sax.SAXException {
        return _xobj;
    }


    public DocumentFragment rtree() {
        return null;
    }
}
