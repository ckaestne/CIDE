/*
 * HelpForm.java
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

// Expand to define MIDP define
//#define DMIDP20
// Expand to define memory size define
//#define DREGULARMEM
// Expand to define logging define
//#define DNOLOGGING
// Expand to define test ui define
//#define DNOTESTUI

package com.substanceofcode.rssreader.presentation;

import java.lang.IllegalArgumentException;
import java.io.IOException;
import java.util.Hashtable;

import com.substanceofcode.rssreader.businessentities.RssReaderSettings;
import javax.microedition.lcdui.Alert;
import javax.microedition.lcdui.AlertType;
import javax.microedition.lcdui.Command;
import javax.microedition.lcdui.CommandListener;
import javax.microedition.lcdui.Display;
import javax.microedition.lcdui.Displayable;
import javax.microedition.midlet.MIDlet;
import javax.microedition.lcdui.Item;
//#ifndef DTESTUI
//@import javax.microedition.lcdui.Form;
//@import javax.microedition.lcdui.StringItem;
//#else
// If using the test UI define the Test UI's
import com.substanceofcode.testlcdui.Form;
import com.substanceofcode.testlcdui.StringItem;
//#endif
import javax.microedition.lcdui.Item;

import cz.cacek.ebook.util.ResourceProviderME;

//#ifdef DLOGGING
import net.sf.jlogmicro.util.logging.Logger;
import net.sf.jlogmicro.util.logging.Level;
//#endif

/**
 *
 * @author Tommi Laukkanen
 */
public class HelpForm
//#ifndef DSMALLMEM
extends Form
implements CommandListener, Runnable
//#endif
{
    
	//#ifndef DSMALLMEM
    private RssReaderMIDlet m_midlet;
    private Command     m_backCommand;      // The back to the previous screen
    private Command     m_aboutCommand;      // The about command
    private Displayable m_dprev;
    private Item m_iprev;

	//#ifdef DLOGGING
    private Logger m_logger = Logger.getLogger("HelpForm");
    private boolean m_fineLoggable = m_logger.isLoggable(Level.FINE);
    private boolean m_finerLoggable = m_logger.isLoggable(Level.FINER);
    private boolean m_finestLoggable = m_logger.isLoggable(Level.FINEST);
	//#endif
    
    /** Creates a new instance of HelpForm */
    private HelpForm(RssReaderMIDlet midlet, Displayable dprev, Item iprev) {
        super(ResourceProviderME.get("title.help"));
        m_midlet = midlet;
        m_dprev = dprev;
        m_iprev = iprev;
        
        m_backCommand = UiUtil.getCmdRsc("cmd.back", Command.BACK, 1);
        this.addCommand( m_backCommand );
        
        m_aboutCommand = UiUtil.getCmdRsc("cmd.about", Command.SCREEN, 2);
        this.addCommand( m_aboutCommand );
        
        this.setCommandListener( this );
        
    }
    
    /** Creates a new instance of HelpForm */
    public HelpForm(RssReaderMIDlet midlet, Displayable dprev) {
		this(midlet, dprev, null);
	}

	//#ifdef DMIDP20
    /** Creates a new instance of HelpForm */
    public HelpForm(RssReaderMIDlet midlet, Item iprev) {
		this(midlet, null, iprev);
	}
	//#endif

    public void commandAction(Command command, Displayable displayable) {
		//#ifdef DTESTUI
		super.outputCmdAct(command, displayable);
		//#endif
        if(command==m_backCommand) {
			if (m_dprev != null) {
				m_midlet.setCurrent(m_dprev);
				//#ifdef DMIDP20
			} else if (m_iprev != null) {
				m_midlet.setCurrentItem(m_iprev);
				//#endif
			}
        }
        
        if (command==m_aboutCommand) {
			new Thread(this).start();
        }
    }

  /**
   * Add help text from resource info.
   *
   * @param cmd
   * @param aKey
   * @return    final
   * @author Irv Bunton
   */
	final public int appendRsc(String aKey) {
		return super.append(ResourceProviderME.get(aKey));
	}
    
  /**
   * Add help for a command using the command's Label and text from
   * resource info.
   *
   * @param cmd
   * @param aKey
   * @return    final
   * @author Irv Bunton
   */
	final public int appendCmdHelpRsc(Command cmd, String aKey) {
		StringItem si = new StringItem(cmd.getLabel() + ":",
				ResourceProviderME.get(aKey));
		//#ifdef DMIDP20
		si.setLayout(Item.LAYOUT_BOTTOM);
		//#endif
		return super.append(si);
	}
    
  /**
   * Add help for an item using the item's Label and text from
   * resource info.
   *
   * @param item
   * @param aKey
   * @return    final
   * @author Irv Bunton
   */
	final public int appendItemHelpRsc(Item item, String aKey) {
		StringItem si = new StringItem(item.getLabel(),
				ResourceProviderME.get(aKey));
		//#ifdef DMIDP20
		si.setLayout(Item.LAYOUT_BOTTOM);
		//#endif
		return super.append(si);
	}
    
	public void run() {
		Alert about = HelpForm.getAbout(m_midlet);
		m_midlet.setCurrent( about, this );
	}

	//#endif
    /**
	 * Create about alert.
	 * @author  Irving Bunton
	 * @version 1.0
	 */
	final public static Alert getAbout(MIDlet midlet) {
		final Alert about = new Alert(ResourceProviderME.get(
					"alert.about.title"), ResourceProviderME.get("alert.about",
 midlet.getAppProperty("MIDlet-Version") + "-" +
 midlet.getAppProperty("Program-Version")), null, AlertType.INFO);
		about.setTimeout(Alert.FOREVER);
 
		return about;
	}

}
