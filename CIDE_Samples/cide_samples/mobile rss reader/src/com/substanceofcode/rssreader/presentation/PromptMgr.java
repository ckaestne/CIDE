/*
 * PromptMgr.java
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
import javax.microedition.lcdui.Image;
//#ifndef DTESTUI
//@import javax.microedition.lcdui.Form;
//@import javax.microedition.lcdui.StringItem;
//#else
import com.substanceofcode.testlcdui.Form;
import com.substanceofcode.testlcdui.StringItem;
//#endif

import com.substanceofcode.utils.CauseException;

//#ifdef DLOGGING
import net.sf.jlogmicro.util.logging.Logger;
import net.sf.jlogmicro.util.logging.LogManager;
import net.sf.jlogmicro.util.logging.Level;
//#endif

/* Form with optional commands added with addPromptCommand which if
   used, will give prompt message with OK/Cancel.

   Due to peculiarities with setCurrent, we do it twice so that it will
   work with most phones.  Otherwise, it may be ignored for some phones. */
final public class PromptMgr implements CommandListener, Runnable {

	private Hashtable promptCommands = new Hashtable();
	private Displayable disp;
	private Command origCmd = null;
	protected RssReaderMIDlet midlet;
	private Alert promptAlert = null;
	//#ifdef DLOGGING
	private Logger logger = Logger.getLogger("PromptMgr");
	private boolean fineLoggable = false;
	private boolean finestLoggable = false;
	//#endif

	private CommandListener cmdListener;

	public PromptMgr (RssReaderMIDlet midlet, Displayable disp) {
		this.midlet = midlet;
		this.disp = disp;
		//#ifdef DLOGGING
		fineLoggable = logger.isLoggable(Level.FINE);
		logger.fine("obj,fineLoggable=" + this + "," + fineLoggable);
		finestLoggable = logger.isLoggable(Level.FINEST);
		logger.finest("obj,finestLoggable=" + this + "," + finestLoggable);
		//#endif
	}

    final public void setCommandListener(CommandListener cmdListener) {
		this.cmdListener = cmdListener;
    }

	final public void addPromptCommand(Command cmd, String prompt) {
		promptCommands.put(cmd, prompt);
	}

	final public void removeCommand(Command cmd) {
		promptCommands.remove(cmd);
	}

	/* Create prompt alert. */
	public void run() {
		// Due to a quirk on T637 (MIDP 1.0), we need to create a form
		// before the alert or the alert will not be seen.
		Form formAlert = new Form(origCmd.getLabel());
		Image question = UiUtil.getImage("/icons/questionMk.png");
		formAlert.append(question);
		int ix = formAlert.append(new StringItem(null,
					(String)promptCommands.get(origCmd)));
		Command okCmd = UiUtil.getCmdRsc("cmd.ok", Command.OK, 1);
		formAlert.addCommand(okCmd);
		Command cancelCmd = UiUtil.getCmdRsc("cmd.cancel", Command.CANCEL, 2);
		formAlert.addCommand(cancelCmd);
		promptAlert = new Alert(origCmd.getLabel(),
				((StringItem)formAlert.get(ix)).getText(), question,
				AlertType.CONFIRMATION);
		promptAlert.setTimeout(Alert.FOREVER);
		promptAlert.addCommand(okCmd);
		promptAlert.addCommand(cancelCmd);
		promptAlert.setCommandListener(this);
		midlet.setCurrent(promptAlert);
	}

	/* Prompt if command is in prompt camands.  */
	public void commandAction(Command cmd, Displayable cdisp) {
		try {
			//#ifdef DLOGGING
			if (finestLoggable) {logger.finest("cmd,containsKey=" + cmd.getLabel() + "," + promptCommands.containsKey(cmd));}
			//#endif
			if (promptCommands.containsKey(cmd)) {
				if ((promptAlert == null) || !cdisp.equals(promptAlert)) {
					origCmd = cmd;
				}
				new Thread(this).start();
				return;
			} else if (cdisp.equals(disp)) {
				midlet.setCurrent(disp);
				cmdListener.commandAction(cmd, disp);
			} else if ((cmd.getCommandType() == Command.OK)
				//#ifdef DMIDP20
					   || cmd.equals(Alert.DISMISS_COMMAND)
				//#endif
						) {
				//#ifdef DLOGGING
				if (fineLoggable) {
					logger.fine("origCmd,type=" + origCmd.getLabel() + "," + origCmd.getCommandType());
				}
				//#endif
				midlet.setCurrent(disp);
				cmdListener.commandAction(origCmd, disp);
			} else if (cmd.getCommandType() == Command.CANCEL) {
				midlet.setCurrent(disp);
				return;
			}
		} catch (Throwable e) {
			final CauseException ce = new CauseException(
					"Unable to show alert.", e);
			//#ifdef DLOGGING
			logger.severe(ce.getMessage(), e);
			//#endif
			/** Error while parsing RSS feed */
			System.out.println(e.getClass().getName() + " " + ce.getMessage());
			e.printStackTrace();
		}
	}

}
