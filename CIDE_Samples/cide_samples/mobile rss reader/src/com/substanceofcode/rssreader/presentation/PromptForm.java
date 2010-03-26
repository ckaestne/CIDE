/*
 * PromptForm.java
 *
 * Copyright (C) 2007 Irving Bunton
 * http://code.google.com/p/mobile-rss-reader/
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

// Expand to define MIDP define
//#define DMIDP20
// Expand to define test define
//#define DNOTEST
// Expand to define test ui define
//#define DNOTESTUI
// Expand to define logging define
//#define DNOLOGGING

package com.substanceofcode.rssreader.presentation;

import java.util.Hashtable;

import javax.microedition.midlet.MIDlet;
import javax.microedition.lcdui.Alert;
import javax.microedition.lcdui.AlertType;
import javax.microedition.lcdui.Command;
import javax.microedition.lcdui.CommandListener;
import javax.microedition.lcdui.Display;
import javax.microedition.lcdui.Displayable;
import javax.microedition.lcdui.Item;
import javax.microedition.lcdui.Image;
//#ifndef DTESTUI
//@import javax.microedition.lcdui.Form;
//#else
import com.substanceofcode.testlcdui.Form;
//#endif

//#ifdef DLOGGING
import net.sf.jlogmicro.util.logging.Logger;
import net.sf.jlogmicro.util.logging.LogManager;
import net.sf.jlogmicro.util.logging.Level;
//#endif

/* Form with optional commands added with addPromptCommand which if
   used, will give prompt message with OK/Cancel.  */
public class PromptForm extends Form {
	protected PromptMgr promptMgr;

	//#ifdef DLOGGING
	private Logger logger = Logger.getLogger("PromptForm");
	private boolean fineLoggable = false;
	private boolean finestLoggable = false;
	//#endif

	public PromptForm(RssReaderMIDlet midlet, String title) {
		super(title);
		init(midlet);
	}

	private void init(RssReaderMIDlet midlet) {
		promptMgr = new PromptMgr(midlet, this);
		//#ifdef DLOGGING
		fineLoggable = logger.isLoggable(Level.FINE);
		logger.fine("obj,fineLoggable=" + this + "," + fineLoggable);
		finestLoggable = logger.isLoggable(Level.FINEST);
		logger.fine("obj,finestLoggable=" + this + "," + finestLoggable);
		//#endif
	}

	public PromptForm(RssReaderMIDlet midlet, String title, Item[] items) {
		super(title, items);
		init(midlet);
	}

	public void addPromptCommand(Command cmd, String prompt) {
		super.addCommand(cmd);
		promptMgr.addPromptCommand(cmd, prompt);
	}

	public void removeCommand(Command cmd) {
		super.removeCommand(cmd);
		promptMgr.removeCommand(cmd);
	}

    public void setCommandListener(CommandListener cmdListener) {
		super.setCommandListener(promptMgr);
		promptMgr.setCommandListener(cmdListener);
    }

}
