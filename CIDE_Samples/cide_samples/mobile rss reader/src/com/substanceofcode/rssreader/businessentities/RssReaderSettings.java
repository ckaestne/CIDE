/*
 * RssFeedReaderSettings.java
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

// Expand to define itunes define
//#define DNOITUNES
// Expand to define test define
//#define DNOTEST
package com.substanceofcode.rssreader.businessentities;

import com.substanceofcode.utils.Settings;
import java.io.IOException;
import java.util.Hashtable;
import javax.microedition.midlet.MIDlet;
import javax.microedition.rms.RecordStoreException;

/**
 * RssFeedReaderSettings contains application's settings.
 *
 * @author Tommi Laukkanen
 */
public class RssReaderSettings {
    
    private Settings m_settings;
    private static RssReaderSettings m_singleton;    
    
    private static final String MAX_ITEM_COUNT = "max-item-count";
    private static final String SAVE_MEMORY_ENABLED = "save-memory-enabled";
    private static final String MAX_WORD_COUNT = "max-word-count";
    private static final String IMPORT_URL = "import-url";
    private static final String IMPORT_USERNAME = "import-username";
    private static final String IMPORT_PASSWORD = "import-password";
    private static final String MARK_UNREAD_ITEMS = "mark-unread-items";
    private static final String FEED_LIST_OPEN = "feed-list-open";
    private static final String ITUNES_ENABLED = "itunes-enabled";
    private static final String PAGE_ENABLED = "page-enabled";
    private static final String HTML_ENABLED = "html-enabled";
    private static final String FONT_SIZE = "font-size";
    private static final String USE_TEXT_BOX = "use-text-box";
    private static final String USE_STANDARD_EXIT = "use-standard-exit";
    private static final String LOG_LEVEL = "log-level";
    private Exception m_loadExc = null;
    
    /** Creates a new instance of RssFeedReaderSettings */
    private RssReaderSettings(MIDlet midlet) {
        try {
            m_settings = Settings.getInstance(midlet);
        } catch (Exception ex) {
			m_loadExc = ex;
            ex.printStackTrace();
        }
    }
    
    /** Get instance */
    public static RssReaderSettings getInstance(MIDlet midlet) {
        if(m_singleton==null) {
            m_singleton = new RssReaderSettings(midlet);
        }
        return m_singleton;
    }
    
    /** Get maximum item count */
    public int getMaximumItemCountInFeed() {
        int maxCount = m_settings.getIntProperty(MAX_ITEM_COUNT, 10);
        return maxCount;
    }
    
    /** Set maximum item count in feed */
    public void setMaximumItemCountInFeed(int maxCount) {
        m_settings.setIntProperty(MAX_ITEM_COUNT, maxCount);
    }
    
    /** Get maximum word count in description */
    public int getMaxWordCountInDesc() {
        int maxCount = m_settings.getIntProperty(MAX_WORD_COUNT, 10);
        return maxCount;
    }
    
    /** Set maximum word count in description */
    public void setMaxWordCountInDesc(int maxCount) {
        m_settings.setIntProperty(MAX_WORD_COUNT, maxCount);
    }
    
    /** Get import URL address */
    public String getImportUrl() {
        String url = m_settings.getStringProperty(IMPORT_URL, "");
        return url;
    }
    
    /** Set import URL address */
    public void setImportUrl(String url) {
        m_settings.setStringProperty( IMPORT_URL, url);
    }
    
    /** Get import URL username */
    public String getImportUrlUsername() {
        String username = m_settings.getStringProperty(IMPORT_USERNAME, "");
        return username;
    }
    
    /** Set import URL username */
    public void setImportUrlUsername(String username) {
        m_settings.setStringProperty( IMPORT_USERNAME, username);
    }
    
    /** Get import URL password */
    public String getImportUrlPassword() {
        String password = m_settings.getStringProperty(IMPORT_PASSWORD, "");
        return password;
    }
    
    /** Set import URL password */
    public void setImportUrlPassword(String password) {
        m_settings.setStringProperty( IMPORT_PASSWORD, password);
    }
    
    /** Get mark unread items */
    public boolean getMarkUnreadItems() {
        return m_settings.getBooleanProperty( MARK_UNREAD_ITEMS, false);
    }
    
    /** Set import URL password */
    public void setMarkUnreadItems(boolean markUnreadItems) {
        m_settings.setBooleanProperty( MARK_UNREAD_ITEMS, markUnreadItems);
    }
    
    /** Get feed list back is first command */
    public boolean getFeedListOpen() {
        return m_settings.getBooleanProperty( FEED_LIST_OPEN, true);
    }
    
    /** Set feed list back is first command */
    public void setFeedListOpen(boolean feedListOpen) {
        m_settings.setBooleanProperty( FEED_LIST_OPEN, feedListOpen);
    }
    
    /** Get itunes enabled */
    public boolean getItunesEnabled() {
		//#ifdef DITUNES
        return m_settings.getBooleanProperty( ITUNES_ENABLED, false?false:true);
		//#else
//@        return m_settings.getBooleanProperty( ITUNES_ENABLED, false);
		//#endif
    }
    
    /** Set itunes enabled */
    public void setItunesEnabled(boolean itunesEnabled) {
        m_settings.setBooleanProperty( ITUNES_ENABLED, itunesEnabled);
    }
    
    /** Get page enabled */
    public boolean getPageEnabled() {
        return m_settings.getBooleanProperty( PAGE_ENABLED, false);
    }
    
    /** Set page enabled */
    public void setPageEnabled(boolean pageEnabled) {
        m_settings.setBooleanProperty( PAGE_ENABLED, pageEnabled);
    }
    
    /** Get HTML enabled */
    public boolean getHtmlEnabled() {
        return m_settings.getBooleanProperty( HTML_ENABLED, false);
    }
    
    /** Set HTML enabled */
    public void setHtmlEnabled(boolean htmlEnabled) {
        m_settings.setBooleanProperty( HTML_ENABLED, htmlEnabled);
    }
    
    /** Get save memory enabled */
    public boolean getSaveMemoryEnabled() {
        return m_settings.getBooleanProperty( SAVE_MEMORY_ENABLED, true);
    }
    
    /** Set save memory enabled */
    public void setSaveMemoryEnabled(boolean saveMemoryEnabled) {
        m_settings.setBooleanProperty( SAVE_MEMORY_ENABLED, saveMemoryEnabled);
    }
    
    /** Get items encoded enabled */
    public boolean getItemsEncodedEnabled() {
        return m_settings.getBooleanProperty( m_settings.ITEMS_ENCODED, true);
    }
    
    /** Set items encoded enabled */
    public void setItemsEncodedEnabled(boolean itemsEncodedEnabled) {
        m_settings.setBooleanProperty( m_settings.ITEMS_ENCODED,
				itemsEncodedEnabled);
    }
    
    /** Get font size */
    public int getFontSize() {
        return m_settings.getIntProperty( FONT_SIZE, 0);
    }
    
    /** Set font size */
    public void setFontSize(int fontSize) {
        m_settings.setIntProperty( FONT_SIZE, fontSize);
    }
    
    /** Get use text box */
    public boolean getUseTextBox() {
        return m_settings.getBooleanProperty( USE_TEXT_BOX, false);
    }
    
    /** Set use text box */
    public void setUseTextBox(boolean useTextBox) {
        m_settings.setBooleanProperty( USE_TEXT_BOX, useTextBox);
    }
    
    /** Get use standard exit */
    public boolean getUseStandardExit() {
        return m_settings.getBooleanProperty( USE_STANDARD_EXIT, false);
    }
    
    /** Set standard exit */
    public void setUseStandardExit(boolean useStandardExit) {
        m_settings.setBooleanProperty( USE_STANDARD_EXIT, useStandardExit);
    }
    
    /** Get settings version */
    public String getSettingsVers() {
        return m_settings.getStringProperty(Settings.SETTINGS_NAME, "");
    }
    
    /** get settings db memory info. */
    public Hashtable getSettingMemInfo()
	throws IOException, RecordStoreException {
        return m_settings.getSettingMemInfo();
    }
    
	//#ifdef DTEST
    /** Get log level */
    public String getLogLevel() {
        String log_level = m_settings.getStringProperty(LOG_LEVEL, "");
        return log_level;
    }
    
    /** Set import URL password */
    public void setLogLevel(String log_level) {
        m_settings.setStringProperty( LOG_LEVEL, log_level);
    }
    
	//#endif

    public Exception getLoadExc() {
        return (m_loadExc);
    }

}
