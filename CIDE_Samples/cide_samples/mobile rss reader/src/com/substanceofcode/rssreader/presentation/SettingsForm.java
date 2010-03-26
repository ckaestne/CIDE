/*
 * SettingsForm.java
 *
 * Copyright (C) 2005-2006 Tommi Laukkanen
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
// Expand to define DJSR75 define
//#define DNOJSR75
// Expand to define itunes define
//#define DNOITUNES
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
import javax.microedition.lcdui.Choice;
import javax.microedition.lcdui.Command;
import javax.microedition.lcdui.CommandListener;
import javax.microedition.lcdui.Display;
import javax.microedition.lcdui.Displayable;
//#ifndef DTESTUI
//@import javax.microedition.lcdui.ChoiceGroup;
//@import javax.microedition.lcdui.Form;
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
import javax.microedition.lcdui.Item;

//#ifndef DSMALLMEM
import com.substanceofcode.rssreader.presentation.HelpForm;
//#endif

//#ifdef DJSR75
import org.kablog.kgui.KFileSelectorMgr;
//#endif
import com.substanceofcode.utils.Settings;
import cz.cacek.ebook.util.ResourceProviderME;

//#ifdef DLOGGING
import net.sf.jlogmicro.util.logging.Logger;
import net.sf.jlogmicro.util.logging.LogManager;
import net.sf.jlogmicro.util.logging.Level;
//#endif

/**
 *
 * @author Tommi Laukkanen
 */
public class SettingsForm extends Form implements CommandListener, Runnable {
    
    private RssReaderMIDlet m_midlet;
    private boolean m_getHelp = false;
    private boolean m_upd = false;
    private Command m_okCommand;
    private Command m_cancelCommand;
	//#ifndef DSMALLMEM
    private Command m_helpCommand;
	//#endif
    
    private TextField m_itemCountField;
    private ChoiceGroup m_markUnreadItems;
	//#ifndef DSMALLMEM
    private ChoiceGroup m_useTextBox;
	//#endif
    private ChoiceGroup m_useStandardExit;
    private ChoiceGroup m_feedListOpen;
	//#ifdef DITUNES
    private ChoiceGroup m_itunesEnabled;
	//#endif
	//#ifdef DMIDP20
    private ChoiceGroup m_pageEnabled;
    private ChoiceGroup m_fontSize;
	//#endif
    private TextField m_wordCountField;
    private StringItem m_pgmMemUsedItem;
    private StringItem m_pgmMemAvailItem;
    private StringItem m_memUsedItem;
    private StringItem m_memAvailItem;
    private StringItem m_threadsUsed;
    private boolean prevStdExit;
	//#ifdef DLOGGING
    private TextField m_logLevelField;
    private Logger logger = Logger.getLogger("SettingsForm");
    private boolean fineLoggable = logger.isLoggable(Level.FINE);
    private boolean finerLoggable = logger.isLoggable(Level.FINER);
    private boolean finestLoggable = logger.isLoggable(Level.FINEST);
	//#endif
    
    /** Creates a new instance of SettingsForm */
    public SettingsForm(RssReaderMIDlet midlet) {
        super("Settings");
        m_midlet = midlet;
        
        m_okCommand = UiUtil.getCmdRsc("cmd.ok", Command.OK, 1);
        this.addCommand( m_okCommand );
        
        m_cancelCommand = UiUtil.getCmdRsc("cmd.cancel", Command.CANCEL, 2);
        this.addCommand( m_cancelCommand );
        
		//#ifndef DSMALLMEM
        m_helpCommand = UiUtil.getCmdRsc("cmd.help", Command.HELP, 3);
        this.addCommand( m_helpCommand );
		//#endif

        this.setCommandListener( this );
        
        RssReaderSettings settings = m_midlet.getSettings();
        int maxCount = settings.getMaximumItemCountInFeed();
        
        m_itemCountField = new TextField("Max item count in feed",
                String.valueOf(maxCount), 3, TextField.NUMERIC);
		//#ifdef DMIDP20
		m_itemCountField.setLayout(Item.LAYOUT_BOTTOM);
		//#endif
        super.append( m_itemCountField );

        m_markUnreadItems = UiUtil.getAddChoiceGroup(this,
				"Mark unread items", new String [] {"Mark", "No mark"});

		//#ifndef DSMALLMEM
        m_useTextBox = UiUtil.getAddChoiceGroup(this,
				"Text entry items", new String [] {"Text (large) box",
				"Text (line) field"});
		//#endif

        m_useStandardExit = UiUtil.getAddChoiceGroup(this,
				"Exit key type", new String [] {"Use standard exit key",
				"Use menu exit key"});

		//#ifdef DITUNES
        m_itunesEnabled = UiUtil.getAddChoiceGroup(this,
				"Choose to use Itunes data", new String []
				{"Don't show Itunes data", "Show Itunes data"});
		//#endif

		//#ifdef DMIDP20
        m_pageEnabled = UiUtil.getAddChoiceGroup(this,
				"Choose to use keypad and/get HTML emphasis for item screen",
				new String []
				{"Use commands to go back to previous screen",
				"Also use keypad to go back to previous screen",
				"Also use keypad (as previous) and emphasize HTML"});

        m_fontSize = UiUtil.getAddChoiceGroup(this,
				"Choose font size",
				new String[] {"Default font size", "Small",
				"Medium", "Large"});
		//#endif
        m_feedListOpen = UiUtil.getAddChoiceGroup(this,
				"Choose feed list menu first item", new String []
				{"Open item first", "Back first"});

        int maxWordCount = settings.getMaxWordCountInDesc();
        m_wordCountField = new TextField("Max word count desc abbrev",
                String.valueOf(maxCount), 3, TextField.NUMERIC);
		//#ifdef DMIDP20
		m_wordCountField.setLayout(Item.LAYOUT_BOTTOM);
		//#endif
        super.append( m_wordCountField );
		StringItem itemInfo = new StringItem("Program MIDP version:",
		//#ifdef DMIDP20
				false?"MIDP-1.0":"MIDP-2.0");
		//#else
//				"MIDP-1.0");
		//#endif
		//#ifdef DMIDP20
		itemInfo.setLayout(Item.LAYOUT_BOTTOM);
		//#endif
        super.append( itemInfo );
        itemInfo = new StringItem("Program CLDC version:",
				//#ifdef DCLDCV11
				false?"CLDC-1.0":"CLDC-1.1");
				//#else
//				"CLDC-1.0");
				//#endif
		//#ifdef DMIDP20
		itemInfo.setLayout(Item.LAYOUT_BOTTOM);
		//#endif
        super.append( itemInfo );
        itemInfo = new StringItem("Program JSR 75 available:",
		//#ifdef DJSR75
				false?"false":"true");
		//#else
//				"false");
		//#endif
		//#ifdef DMIDP20
		itemInfo.setLayout(Item.LAYOUT_BOTTOM);
		//#endif
        super.append( itemInfo );
		String mep = System.getProperty("microedition.profiles");
		if (mep == null) {
			mep = "N/A";
		}
        itemInfo = new StringItem("Phone MIDP version:", mep);
		//#ifdef DMIDP20
		itemInfo.setLayout(Item.LAYOUT_BOTTOM);
		//#endif
        super.append( itemInfo );
        itemInfo = new StringItem("Phone CLDC version:",
				System.getProperty("microedition.configuration"));
		//#ifdef DMIDP20
		itemInfo.setLayout(Item.LAYOUT_BOTTOM);
		//#endif
        super.append( itemInfo );
        itemInfo = new StringItem("Phone JSR 75 available:",
				new Boolean(System.getProperty(
				"microedition.io.file.FileConnection.version")
				!= null).toString());
		//#ifdef DMIDP20
		itemInfo.setLayout(Item.LAYOUT_BOTTOM);
		//#endif
        super.append( itemInfo );
		String me = System.getProperty("microedition.platform");
		if (me == null) {
			me = "N/A";
		}
        itemInfo = new StringItem("Phone Microedition platform:", me);
		//#ifdef DMIDP20
		itemInfo.setLayout(Item.LAYOUT_BOTTOM);
		//#endif
        this.append( itemInfo );
		//#ifdef DLOGGING
        m_logLevelField = new TextField("Logging level",
                logger.getParent().getLevel().getName(), 20, TextField.ANY);
		//#ifdef DMIDP20
		m_logLevelField.setLayout(Item.LAYOUT_BOTTOM);
		//#endif
        super.append( m_logLevelField );
		//#endif
        m_pgmMemUsedItem = new StringItem("Application memory used:", "");
		//#ifdef DMIDP20
		m_pgmMemUsedItem.setLayout(Item.LAYOUT_BOTTOM);
		//#endif
        super.append( m_pgmMemUsedItem );
        m_pgmMemAvailItem = new StringItem("Application memory available:", "");
		//#ifdef DMIDP20
		m_pgmMemAvailItem.setLayout(Item.LAYOUT_BOTTOM);
		//#endif
        super.append( m_pgmMemAvailItem );
        m_memUsedItem = new StringItem("DB memory used:", "");
		//#ifdef DMIDP20
		m_memUsedItem.setLayout(Item.LAYOUT_BOTTOM);
		//#endif
        super.append( m_memUsedItem );
        m_memAvailItem = new StringItem("DB memory available:", "");
		//#ifdef DMIDP20
		m_memAvailItem.setLayout(Item.LAYOUT_BOTTOM);
		//#endif
        super.append( m_memAvailItem );
        m_threadsUsed = new StringItem("Active Threads:", "");
		//#ifdef DMIDP20
		m_threadsUsed.setLayout(Item.LAYOUT_BOTTOM);
		//#endif
        super.append( m_threadsUsed );
		updateForm();
    }
    
	/* Update form items that change per run. */
	public void updateForm() {
        RssReaderSettings settings = m_midlet.getSettings();
        int maxCount = settings.getMaximumItemCountInFeed();
        m_itemCountField.setString(String.valueOf(maxCount));
        boolean markUnreadItems = settings.getMarkUnreadItems();
		m_markUnreadItems.setSelectedFlags( new boolean[] {markUnreadItems,
				!markUnreadItems} );
		//#ifndef DSMALLMEM
        boolean useTextBox = settings.getUseTextBox();
		m_useTextBox.setSelectedFlags( new boolean[] {useTextBox, !useTextBox} );
		//#endif
        boolean useStdExit = settings.getUseStandardExit();
        prevStdExit = useStdExit;
		m_useStandardExit.setSelectedFlags( new boolean[] {useStdExit,
				!useStdExit} );
		//#ifdef DITUNES
        boolean itunesEnabled = settings.getItunesEnabled();
		m_itunesEnabled.setSelectedFlags( new boolean[] {!itunesEnabled,
				itunesEnabled} );
		//#endif
		//#ifdef DMIDP20
        boolean pageEnabled = settings.getPageEnabled();
        boolean htmlEnabled = settings.getHtmlEnabled();
		m_pageEnabled.setSelectedFlags( new boolean[] {!pageEnabled &&
				!htmlEnabled, pageEnabled, htmlEnabled} );
        int fontSize = settings.getFontSize();
		boolean [] boolfontSize = {false, false, false, false};
		m_fontSize.setSelectedFlags( new boolean[] {false, false, false,
				false} );
		m_fontSize.setSelectedIndex( fontSize, true );
		//#endif
        boolean feedListOpen = settings.getFeedListOpen();
		m_feedListOpen.setSelectedFlags( new boolean[] {feedListOpen,
				!feedListOpen} );

		long totalMem;
		long freeMem;
		System.gc();
		totalMem = Runtime.getRuntime().totalMemory();
		freeMem = Runtime.getRuntime().freeMemory();
		m_pgmMemUsedItem.setText(((totalMem - freeMem)/1024L) + "kb");
		m_pgmMemAvailItem.setText((freeMem/1024L) + "kb");
		Hashtable memInfo;
		try {
			memInfo = settings.getSettingMemInfo();
		} catch (Throwable e) {
			m_midlet.recordExcFormFinRsc("exc.int.err", e);
			memInfo = new Hashtable();
		}
        if (memInfo.size() == 0) {
			m_memUsedItem.setText("0");
			m_memAvailItem.setText("0");
		} else {
			m_memUsedItem.setText((String)memInfo.get("used"));
			m_memAvailItem.setText((String)memInfo.get("available"));
		}
		m_threadsUsed.setText(Integer.toString(Thread.activeCount()));
	}

    public void commandAction(Command command, Displayable displayable) {
		//#ifdef DTESTUI
		super.outputCmdAct(command, displayable);
		//#endif
        if(command==m_okCommand) {
			m_upd = true;
			try {
				new Thread(this).start();
            } catch(Throwable e) {
				/* Internal error.:\n */
				m_midlet.recordExcFormFinRsc("exc.int.err", e);
            }
        }
        
        if(command==m_cancelCommand) {
            m_midlet.showBookmarkList();
        }

		//#ifndef DSMALLMEM
        if(command==m_helpCommand) {
			m_getHelp = true;
			try {
				new Thread(this).start();
            } catch(Throwable e) {
				/* Internal error.:\n */
				m_midlet.recordExcFormFinRsc("exc.int.err", e);
            }
		}
		//#endif

    }
    
	public void run() {
        if(m_upd) {
			m_upd = false;
			/* Loading data... */
			m_midlet.initializeLoadingFormRsc("text.u.data", this);
            // Save settings
            try {
				RssReaderSettings settings = m_midlet.getSettings();
                int maxCount = Integer.parseInt( m_itemCountField.getString() );
                settings.setMaximumItemCountInFeed( maxCount );
				boolean markUnreadItems = m_markUnreadItems.isSelected(0);
                settings.setMarkUnreadItems( markUnreadItems );
				//#ifndef DSMALLMEM
				boolean useTextBox = m_useTextBox.isSelected(0);
				settings.setUseTextBox(useTextBox);
				//#endif
				boolean useStdExit = m_useStandardExit.isSelected(0);
				settings.setUseStandardExit(useStdExit);
				if (useStdExit != prevStdExit) {
					m_midlet.initExit();
				}
				//#ifdef DITUNES
				boolean itunesEnabled = !m_itunesEnabled.isSelected(0);
				settings.setItunesEnabled( itunesEnabled );
				//#else
				settings.setItunesEnabled( false );
				//#endif
				//#ifdef DMIDP20
				boolean pageEnabled = m_pageEnabled.isSelected(1);
				settings.setPageEnabled( pageEnabled );
				boolean htmlEnabled = m_pageEnabled.isSelected(2);
				settings.setHtmlEnabled( htmlEnabled );
				int fontSize = m_fontSize.getSelectedIndex();
				settings.setFontSize( fontSize );
				//#endif
				boolean feedListOpen = m_feedListOpen.isSelected(0);
				settings.setFeedListOpen( feedListOpen);
                int maxWordCount = Integer.parseInt( m_wordCountField.getString() );
                settings.setMaxWordCountInDesc( maxWordCount );
				//#ifdef DLOGGING
				try {
					String logLevel =
						m_logLevelField.getString().toUpperCase();
					logger.getParent().setLevel(Level.parse(logLevel));
					settings.setLogLevel( logLevel );
				} catch (IllegalArgumentException e) {
					Alert invalidData = new Alert("Invalid Log Level",
									"Invalid Log Level " +
									m_logLevelField.getString(),
									null,
									AlertType.ERROR);
					invalidData.setTimeout(Alert.FOREVER);
					Display.getDisplay(m_midlet).setCurrent(invalidData, this);
					return;
				}
				//#endif
				m_midlet.showBookmarkList();
            } catch(Exception e) {
				/* Internal error.:\n */
				m_midlet.recordExcFormFinRsc("exc.int.err", e);
            } catch(Throwable e) {
				/* Internal error.:\n */
				m_midlet.recordExcFormFinRsc("exc.int.err", e);
            }
            
		}

		//#ifndef DSMALLMEM
        if(m_getHelp) {
			m_getHelp = false;
			try {
				/* Loading help... */
				m_midlet.initializeLoadingFormRsc("text.l.h", this);
				final HelpForm helpForm = new HelpForm(m_midlet, this);
				helpForm.appendRsc("text.set.help");
				helpForm.appendItemHelpRsc(m_useTextBox, "text.stxt.help");
				//#ifdef DMIDP20
				helpForm.appendItemHelpRsc(m_pageEnabled, "text.spg.help");
				helpForm.appendItemHelpRsc(m_fontSize, "text.sfs.help");
				//#endif
				m_midlet.setCurrent(helpForm);
            } catch(Throwable e) {
				/* Internal error.:\n */
				m_midlet.recordExcFormFinRsc("exc.int.err", e);
            }
        }
		//#endif

	}
}
