/* Copyright (c) 2001-2005 Todd C. Stellanova, rawthought
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or
 * sell copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The  above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING
 * FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS
 * IN THE SOFTWARE. 
 * 
 * 
 * This software was originally modified no later than Sept 25, 2007.
 */

// Expand to define MIDP define
//#define DMIDP20
// Expand to define DJSR75 define
//#define DNOJSR75
// Expand to define logging define
//#define DNOLOGGING
//#ifdef DJSR75

package org.kablog.kgui;

import javax.microedition.lcdui.*;
import javax.microedition.midlet.MIDlet;

import com.substanceofcode.rssreader.presentation.RssReaderMIDlet;

//#ifdef DLOGGING
import net.sf.jlogmicro.util.logging.Logger;
import net.sf.jlogmicro.util.logging.LogManager;
import net.sf.jlogmicro.util.logging.Level;
import net.sf.jlogmicro.util.logging.FormHandler;
import net.sf.jlogmicro.util.logging.RecStoreHandler;
//#endif

/**
 *
 * @author  Todd C. Stellanova
 */
public class KFileSelectorMgr
implements KViewParent 
{

	protected RssReaderMIDlet midlet;
	protected Form txtFrm;
	protected TextField txtFld;
	protected KFileSelector fileSelectorView; 
    protected KViewParent viewParent;
    protected boolean ready = false;
    protected boolean bDebug;
	//#ifdef DLOGGING
    private Logger logger = Logger.getLogger("KFileSelectorMgr");
    private boolean fineLoggable = logger.isLoggable(Level.FINE);
    private boolean finerLoggable = logger.isLoggable(Level.FINER);
    private boolean finestLoggable = logger.isLoggable(Level.FINEST);
	//#endif

    /**
     * When the we're is done capturing an XML or multi-media, it calls this
	   method.
     */
    final public void childFinished(KViewChild child) {   
		try {
			if (fileSelectorView.getSelectedURL() != null) {
				txtFld.setString(fileSelectorView.getSelectedURL());
			}
			fileSelectorView.doCleanup();
			fileSelectorView = null;
			//#ifdef DMIDP20
			midlet.setCurrentItem( txtFld );
			//#else
			midlet.setCurrent( txtFrm );
			//#endif
		} catch (Throwable t) {
			//#ifdef DLOGGING
			logger.severe("Sort dates error.", t);
			//#endif
			System.out.println("Sort dates error." + t + " " +
							   t.getMessage());
			t.printStackTrace();
		}
	}
        
	/* Start the file selector list. */
	final public void doLaunchSelector(RssReaderMIDlet midlet, Form txtFrm, TextField txtFld) {

		System.out.println("doLaunchSelector...");
		this.midlet = midlet;
		this.txtFrm = txtFrm;
		this.txtFld = txtFld;

		fileSelectorView = null;

		try {
			fileSelectorView = KFileSelectorFactory.getInstance(
					midlet, "Find import file", null, "/icons" );
			fileSelectorView.setViewParent(this);
			Display.getDisplay(midlet).setCurrent((List)fileSelectorView);
		}
		catch (Exception ex)
		{
			if (bDebug) System.out.println("### selector fail: " + ex);
		}

	}//doLaunchSelector

	/** We've updated the child's status.
	 */
	final public void childStatusChanged(KViewChild child, int statusType, int status) {
		if (bDebug) System.out.println("Child status changed: " + status);
	} 

    /** @param newView object o make visible, if possible.
     */
    final public void reqSetVisible(Displayable newView) {
    	if (viewParent != null) {
			viewParent.reqSetVisible(newView);
		} else {
			Display.getDisplay(midlet).setCurrent(newView);
		}
    }
    
    /** @param The callback client interested in receiving finished status.
     */
    final public void setViewParent(KViewParent parent) {
        this.viewParent = parent;
    }
    
    /** 
     Display a debug message somehow
     */
    final public void displayDbgMsg(String msg, AlertType type) {
         if (bDebug) System.out.println("dbgMsg: " + msg);
	}

	/* Add a deferred action.  This is either passed on to our parent or
	   run as a thread now. */
    final public void addDeferredAction(Runnable runny)
    {
    	if (viewParent != null) {
			viewParent.addDeferredAction(runny);
		} else {
			new Thread(runny).start();
		}
    }

}
//#endif
