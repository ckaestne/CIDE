/*
 * FormLoggerMIDlet.java
 *
 * Copyright (C) 2007 Irving Bunton
 * http://code.google.com/p/jlogmicro/source
 *
 * This program is free software; you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation; either version 2 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program; if not, write to the Free Software
 * Foundation, Inc., 59 Temple Place, Suite 330, Boston, MA  02111-1307  USA
 *
 */
// Expand to define CLDC define
//#define DCLDCV10
// Expand to define logging define
//#define DLOGGING
//#ifdef DLOGGING
package net.sf.jlogmicro.util.presentation;

import java.util.Enumeration;

import javax.microedition.midlet.*;
import javax.microedition.lcdui.*;

import net.sf.jlogmicro.util.logging.Logger;
import net.sf.jlogmicro.util.logging.FormHandler;
import net.sf.jlogmicro.util.logging.Handler;
import net.sf.jlogmicro.util.logging.LogManager;
import net.sf.jlogmicro.util.logging.Level;

public class FormLoggerMIDlet extends MIDlet {
    private boolean fineLoggable = false;
    private boolean finestLoggable = false;
    private Logger logger = null;
    private Form debugf;

	public FormLoggerMIDlet() {
		super();
        LogManager.getLogManager().readConfiguration(this);
        logger = Logger.getLogger("FormLoggerMIDlet");
        try {
            fineLoggable = logger.isLoggable(Level.FINE);
            logger.fine("obj,fineLoggable=" + this + "," + fineLoggable);
            finestLoggable = logger.isLoggable(Level.FINEST);
            logger.fine("obj,finestLoggable=" + this + "," + finestLoggable);
			for (Enumeration eHandlers =
					logger.getParent().getHandlers().elements();
					eHandlers.hasMoreElements();) {
				Object ohandler = eHandlers.nextElement();
				if (ohandler instanceof Form) {
					debugf = (Form)ohandler;
					logger.finest("form=" + debugf);
				}
			}
        } catch (Throwable e) {
            logger.severe("JEZContactsDelux " + e.getMessage(), e);
        }

	}

    public void startApp() {
		logger.info("Test info.");
		logger.fine("Test fine.");
		logger.finer("Test finer.");
		logger.finest("Test finest.");
		Alert m_about = getAbout();
		Display.getDisplay(this).setCurrent(m_about, debugf);
	}

    public void pauseApp() {}
    public void destroyApp(boolean unconditional) {}

    /**
	 * Create about alert.
	 * @author  Irving Bunton
	 * @version 1.0
	 */
	private Alert getAbout() {
		Alert about = new Alert("About RssReader",
"JLogMicro v@MIDLETVERS@ " +
 "Copyright (C) 2007 Irving Bunton " +
 " http://code.google.com/p/jlogmicro/source " +
 "This program is distributed in the hope that it will be useful, " +
 "but WITHOUT ANY WARRANTY; without even the implied warranty of " +
 "MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the " +
 "GNU General Public License for more details. " +
 "This program is free software; you can redistribute it and/or modify " +
 "it under the terms of the GNU General Public License as published by " +
 "the Free Software Foundation; either version 2 of the License, or " +
 "(at your option) any later version.  ", null, AlertType.INFO);
		about.setTimeout(Alert.FOREVER);
 
		return about;
	}

}
//#endif
