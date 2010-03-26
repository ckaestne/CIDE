/* Copyright (c) 2008 Bluendo S.r.L.
 * See about.html for details about license.
 *
 * $Id: TestMidlet.java 1305 2009-03-20 16:02:30Z luca $
*/

package it.yup.tests;

import java.util.Vector;

import javax.microedition.lcdui.Display;
import javax.microedition.lcdui.Form;
import javax.microedition.lcdui.StringItem;
import javax.microedition.midlet.MIDlet;
import it.yup.util.MetaVector;
// #mdebug
//@import it.yup.util.Logger;
//@import it.yup.util.StderrConsumer;
// #enddebug

/**
 * YUP Main midlet
 */
public class TestMidlet extends MIDlet {
    
	/** The main display */
	public static Display disp;
	
	/** The midlet instance */
	public static TestMidlet yup; 

	private XMPPTestClient xmpp = null;
	
	private Form form = new Form("Test midlet"); 
	public StringItem log = new StringItem("Bytes", "offline");
	
	public TestMidlet() {

// #debug		
//@//		Logger.addConsumer(new StderrConsumer());
//		
//		disp = Display.getDisplay(this);
//		xmpp = new XMPPTestClient();
//		form.append(log);
//		yup = this;
		
		MetaVector mv = new MetaVector();
		mv.addElement("a");
		Vector bVector = new Vector ();
		bVector.addElement("b");
		bVector.addElement("c");
		bVector.addElement("d");
		mv.addElement(bVector);
		mv.addElement("e");
		mv.addElement("f");
		bVector = new Vector ();
		bVector.addElement("g");
		bVector.addElement("h");
		bVector.addElement("i");
		mv.addElement(bVector);
		System.out.println(mv.toString());
		System.out.println(mv.size());
		System.out.println(mv.indexOf("e"));
		mv.removeElement("e");
		mv.removeElement("h");
		System.out.println(mv.toString());
		mv.insertElementAt("afterC", mv.indexOf("c")+1);
		mv.insertElementAt("afterG", mv.indexOf("g")+1);
		System.out.println(mv.toString());
		mv.setElementAt("instead-of-I", mv.indexOf("i"));
		mv.removeElement("a");
		mv.removeElement("i");
		System.out.println(mv.toString());
		mv.setElementAt("a", 0);
		mv.setElementAt("i", mv.size()-1);
		System.out.println(mv.toString());
		System.out.println("Contains d: "+ mv.contains("d"));
		int index = mv.lastIndexOf("d");
		System.out.println("Last index of d: "+ index);
		mv.removeElementAt(index);
		System.out.println(mv.toString());
		System.out.println(mv.firstElement());
		System.out.println(mv.lastElement());
		System.out.println(mv.elementAt(4));
		mv.addElement("c");
		System.out.println(mv.lastIndexOf("c"));
		Object [] tempArray = new Object [mv.size()];
		mv.copyInto(tempArray);
		for (int i = 0; i < tempArray.length; i++) {
			System.out.println(tempArray[i]);	
		}
		
		
	}
	
    public void startApp() {
    	xmpp.startClient();
    	disp.setCurrent(form);
    }
    
	protected void destroyApp(boolean param) { 
		
    }
    
    protected void pauseApp() { 
    }

    public void exit() {
        destroyApp(false);
        notifyDestroyed();
    }

//	public void abort(String string, Exception e) {
//		// XXX: notify the user?
//	}
}
