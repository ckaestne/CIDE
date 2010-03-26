/*
 * 
 * UiUtil.java
 *
 * Copyright (C) 2008 Irving Bunton, Jr
 * http://www.substanceofcode.com
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

// Expand to define test define
//#define DNOTEST
// Expand to define test ui define
//#define DNOTESTUI
// Expand to define MIDP define
//#define DMIDP20
// Expand to define logging define
//#define DNOLOGGING


package com.substanceofcode.rssreader.presentation;

import java.util.Vector;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.InputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;

import javax.microedition.lcdui.Command;
import javax.microedition.lcdui.CommandListener;
import javax.microedition.lcdui.Choice;
import javax.microedition.lcdui.Display;
import javax.microedition.lcdui.Displayable;
import javax.microedition.lcdui.Image;
import javax.microedition.lcdui.Item;
import javax.microedition.lcdui.Gauge;
import javax.microedition.midlet.MIDlet;
import javax.microedition.lcdui.Alert;
import javax.microedition.lcdui.AlertType;
// If not using the test UI define the J2ME UI's
//#ifndef DTESTUI
//@import javax.microedition.lcdui.ChoiceGroup;
//@import javax.microedition.lcdui.Form;
//@import javax.microedition.lcdui.List;
//@import javax.microedition.lcdui.TextBox;
//@import javax.microedition.lcdui.TextField;
//@import javax.microedition.lcdui.StringItem;
//#else
// If using the test UI define the Test UI's
import com.substanceofcode.testlcdui.ChoiceGroup;
import com.substanceofcode.testlcdui.Form;
import com.substanceofcode.testlcdui.List;
import com.substanceofcode.testlcdui.TextBox;
import com.substanceofcode.testlcdui.TextField;
import com.substanceofcode.testlcdui.StringItem;
//#endif

import cz.cacek.ebook.util.ResourceProviderME;

//#ifdef DLOGGING
import net.sf.jlogmicro.util.logging.Logger;
import net.sf.jlogmicro.util.logging.Level;
//#endif

/**
 * RSS feed reader MIDlet
 *
 * RssReaderMIDlet is an application that can read RSS feeds. User can store
 * multiple RSS feeds as bookmarks into application's record store.
 *
 * @author  Irving Bunton
 * @version 1.0
 */
final public class UiUtil implements CommandListener {
 
    private RssReaderMIDlet   m_midlet;  // The RssReaderMIDlet midlet
    private Form        m_urlRrnForm;    // The form to return to for URL box
    private TextField   m_urlRrnItem;    // The item to return to for URL box

	public UiUtil() { }

  /**
   * Create a new command using the resource key and standard parms
   *
   * @param key - Key for resource command label
   * @param commandType - Command type
   * @param priority - Command priority
   * @return    Command
   * @author Irv Bunton
   */
    final public static Command getCmdRsc(String key, int commandType,
			int priority) {
		//#ifdef DLOGGING
		Logger logger = Logger.getLogger("UiUtil");
		logger.finest("key,commandType,priority=" + key + "," + commandType + "," + priority);
		//#endif
		return new Command(ResourceProviderME.get(key), commandType, priority);
	}

  /**
   * Create a new command using the resource key and standard parms
   *
   * @param key - Key for resource command label
   * @param lngkey - Key for resource command long label
   * @param commandType - Command type
   * @param priority - Command priority
   * @return    Command
   * @author Irv Bunton
   */
    final public static Command getCmdRsc(String key, String lngkey,
			int commandType, int priority) {
		//#ifdef DLOGGING
		Logger logger = Logger.getLogger("UiUtil");
		logger.finest("key,lngkey,commandType,priority=" + key + "," + lngkey + "," + commandType + "," + priority);
		//#endif
		//#ifdef DMIDP20
		if (true) if (lngkey != null) {
			return new Command(ResourceProviderME.get(key),
					ResourceProviderME.get(lngkey), commandType, priority);
		} else {
			return new Command(ResourceProviderME.get(key), commandType,
					priority);
		}
		//#else
		return new Command(ResourceProviderME.get(key), commandType, priority);
		//#endif
	}

  /**
   * Remove all commands from the displayable
   *
   * @param cmds
   * @author Irv Bunton
   */
	final public static void delCmds(Displayable disp, Command[] cmds) {
		for (int ic = 0; ic < cmds.length; ic++) {
			disp.removeCommand( cmds[ic] );
		}
	}

  /**
   * Delete items from the form.
   *
   * @param aForm
   * @author Irv Bunton
   */
	//#ifdef DTESTUI
	final public static void delItems(javax.microedition.lcdui.Form aForm)//TODOchk
	//#else
//	final public static void delItems(Form aForm)
	//#endif
	{
		//#ifdef DMIDP20
		if(aForm.size()>0) {
			aForm.deleteAll();
		}
		//#else
		int fsize = aForm.size();
		while(fsize-- > 0) {
			aForm.delete(0);
		}
		//#endif
	}

  /**
   * Delete items from the list.
   *
   * @param aList
   * @author Irv Bunton
   */
	final public static void delItems(List aList) {
		//#ifdef DMIDP20
		if(aList.size()>0) {
			aList.deleteAll();
		}
		//#else
		int lsize = aList.size();
		while(lsize-- > 0) {
			aList.delete(0);
		}
		//#endif
	}

  /**
   * Get the place (index) in a list to insert/append an element if using
   * an inert, add, or append command.
   *
   * @param c - command selected by user
   * @param cplace - current place selected in list (-1 if no selection)
   * @param insCmd
   * @param addCmd
   * @param appndCmd
   * @param plist
   * @return    final
   * @author Irv Bunton
   */
	final static int getPlaceIndex(Command c, int cplace, Command insCmd,
							Command addCmd,
							Command appndCmd,
							javax.microedition.lcdui.List plist) {
		if( (c == insCmd ) || (c == addCmd ) || (c == appndCmd )) {
			final int blen = plist.size();
			int addElem = (cplace == -1) ? blen : cplace;
			if( c == addCmd ){
				if (addElem < blen) {
					addElem++;
				}
			}
			if (c == appndCmd ) {
				addElem = blen;
			}
			if ((addElem < 0) || (addElem > blen)) {
				addElem = blen;
			}
			return addElem;
		} else {
			return -1;
		}
	}

	/* Restore previous values. */
	final static public void restorePrevValues(Item[] items, byte[] bdata) {
		//#ifdef DLOGGING
		Logger logger = Logger.getLogger("UiUtil");
		boolean finestLoggable = logger.isLoggable(Level.FINEST);
		//#endif
		DataInputStream dis = new DataInputStream(
				new ByteArrayInputStream(bdata));
		for (int ic = 0; ic < items.length; ic++) {
			try {
				final Item item = items[ic];
				if (item instanceof ChoiceGroup) {
					((ChoiceGroup)item).setSelectedIndex(dis.readInt(),
						true);
					//#ifdef DLOGGING
					if (finestLoggable) {logger.finest("set selected " + ((ChoiceGroup)item).getSelectedIndex());}
					//#endif
				} else if (item instanceof TextField) {
					final int len = dis.readInt();
					byte [] bvalue = new byte[len];
					dis.read(bvalue);
					String value;
					try {
						value = new String(bvalue, "UTF-8");
					} catch (UnsupportedEncodingException e) {
						value = new String(bvalue);
						//#ifdef DLOGGING
						logger.severe("cannot convert value=" + value, e);
						//#endif
					}
					((TextField)item).setString(value);
					//#ifdef DLOGGING
					if (finestLoggable) {logger.finest("set string " + ((TextField)item).getString());}
					//#endif
				}
			} catch (IOException e) {
				//#ifdef DLOGGING
				logger.severe("IOException reading selected.", e);
				//#endif
			}
		}
		if (dis != null) {
			try { dis.close(); } catch (IOException e) {}
		}
	}

	/* Store current values. */
	final public static byte[] storeValues(Item[] items) {
		//#ifdef DLOGGING
		Logger logger = Logger.getLogger("UiUtil");
		boolean finestLoggable = logger.isLoggable(Level.FINEST);
		//#endif
		ByteArrayOutputStream bout = new
				ByteArrayOutputStream();
		DataOutputStream dout = new
				DataOutputStream( bout );
		for (int ic = 0; ic < items.length; ic++) {
			try {
				final Item item = items[ic];
				if (item instanceof ChoiceGroup) {
					dout.writeInt(((ChoiceGroup)item).getSelectedIndex());
					//#ifdef DLOGGING
					if (finestLoggable) {logger.finest("stored selected " + item.getLabel() + "," + ((ChoiceGroup)item).getSelectedIndex());}
					//#endif
				} else if (item instanceof TextField) {
					final String value = ((TextField)item).getString();
					dout.writeInt(value.length());
					byte [] bvalue;
					try {
						bvalue = value.getBytes("UTF-8");
					} catch (UnsupportedEncodingException e) {
						bvalue = value.getBytes();
						//#ifdef DLOGGING
						logger.severe("cannot store value=" + value, e);
						//#endif
					}
					dout.write( bvalue, 0, bvalue.length );
					//#ifdef DLOGGING
					if (finestLoggable) {logger.finest("set string " + item.getLabel() + "," + ((TextField)item).getString());}
					//#endif
				}
			} catch (IOException e) {
				//#ifdef DLOGGING
				logger.severe("IOException storing selected.", e);
				//#endif
			}
		}
		//#ifdef DLOGGING
		if (finestLoggable) {logger.finest("bout.toByteArray().length=" + bout.toByteArray().length);}
		//#endif
		if (dout != null) {
			try { dout.close(); } catch (IOException e) {}
		}
		return bout.toByteArray();
	}

  /**
   * Return the selected index of the choice.  If nothing selected (-1),
   * return 0 if size &gt; 0, or -1 if 0 size.
   *
   * Constructor
   * @param choice - Choice interface
   *
   * @author Irv Bunton
   */
	final static public int getSelectedIndex(Choice choice) {
		final int selIdx = choice.getSelectedIndex();
		if (selIdx != -1) {
			return selIdx;
		} else {
			if (choice.size() > 0) {
				choice.setSelectedIndex(0, true);
				return 0;
			} else {
				return -1;
			}
		}
	}

  /**
   * Get the image for the image path.
   *
   * @param imagePath - Path for image
   * @return    Image - image for path
   *
   * @author Irv Bunton
   */
	final public static Image getImage(final String imagePath) {
		Image image = null;
		try {
			try {
				// createImage("/icons/unread.png") does not always work
				// with the emulator.  so, I do an alternate which is
				// effectively the same thing.
				image = Image.createImage(imagePath);
			} catch(IOException e) {
				//#ifdef DMIDP20
				InputStream is =
						imagePath.getClass().getResourceAsStream(imagePath);
				if (is == null) {
					throw new IOException("Cannot open resource " + imagePath);
				}
				image = Image.createImage(is);
				is.close();
				//#endif
				//#ifdef DLOGGING
				Logger logger = Logger.getLogger("UiUtil");
				logger.warning(
						"Could not get icon, alternate worked icons ex: ", e);
				//#endif
			}
		} catch(Exception e) {
			//#ifdef DLOGGING
			Logger logger = Logger.getLogger("UiUtil");
			logger.severe("Could not get image " + imagePath, e);
			//#endif
			System.err.println("Error while getting mark image: " + e.toString());
		} finally {
			return image;
		}
		
	}

	public static void showAlert(MIDlet midlet, Alert alert,
			Displayable disp) {
		//#ifdef DLOGGING
		Logger logger = Logger.getLogger("UiUtil");
		logger.finest("alert,disp=" + alert.getString() + "," + alert.getType());
		//#endif
		if (alert.getType() == AlertType.CONFIRMATION) {
			//#ifdef DLOGGING
			logger.finest("Alert not displayed when type is CONFIRMATION, changing to INFO");
			//#endif
			alert.setType(AlertType.INFO);
		}
		Display.getDisplay(midlet).setCurrent(alert, disp);
	}

  /**
   * Create a ChoiceGroup, set the layout and add it to the form.
   *
   * @param label
   * @param choices
   * @return    ChoiceGroup
   * @author Irv Bunton
   */
	static public ChoiceGroup getAddChoiceGroup(Form form, String label,
												String[] choices) {
        ChoiceGroup choiceGroup = new ChoiceGroup(label,
				                            Choice.EXCLUSIVE, choices, null);
		//#ifdef DMIDP20
		choiceGroup.setLayout(Item.LAYOUT_BOTTOM);
		//#endif
        form.append( choiceGroup );
		return choiceGroup;
	}

	//#ifndef DSMALLMEM
    /** Initialize URL text Box */
    final public void initializeURLBox(RssReaderMIDlet midlet, final String url,
			Form prevForm, TextField prevItem) {
		m_midlet = midlet;
		TextBox boxURL = new TextBox("URL", url, 256, TextField.URL);
		m_urlRrnForm = prevForm;
		m_urlRrnItem = prevItem;
		boxURL.addCommand(UiUtil.getCmdRsc("cmd.ok", Command.OK, 2));
		boxURL.addCommand(UiUtil.getCmdRsc("cmd.cancel", Command.CANCEL, 1));
        boxURL.setCommandListener(this);
		midlet.setCurrent( boxURL );
    }
	//#endif
    
    /** Respond to commands */
    public void commandAction(final Command c, final Displayable s) {
		//#ifndef DSMALLMEM

		/** Paste into URL field from previous form.  */
		if( (m_urlRrnForm != null) &&
			(s instanceof TextBox) && (c.getCommandType() == Command.OK) ){
			m_urlRrnItem.setString( ((TextBox)s).getString() );
			//#ifdef DMIDP20
			m_midlet.setCurrentItem( m_urlRrnItem );
			//#else
			m_midlet.setCurrent( m_urlRrnForm );
			//#endif
			m_urlRrnForm = null;
			m_urlRrnItem = null;
		}
		
		/** Cancel the box go back to the return form.  */
		if( (m_urlRrnForm != null) &&
			(s instanceof TextBox) &&
			(c.getCommandType() == Command.CANCEL) ){
			//#ifdef DMIDP20
			m_midlet.setCurrentItem( m_urlRrnItem );
			//#else
//			setCurrent( m_urlRrnForm );
			//#endif
			m_urlRrnForm = null;
			m_urlRrnItem = null;
		}
		//#endif
	}
	
}
