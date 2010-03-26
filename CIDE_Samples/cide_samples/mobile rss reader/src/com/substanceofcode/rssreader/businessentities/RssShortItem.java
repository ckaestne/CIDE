/*
 * RssShortItem.java
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

// Expand to define logging define
//#define DNOLOGGING
package com.substanceofcode.rssreader.businessentities;

import com.substanceofcode.utils.Base64;
import com.substanceofcode.utils.StringUtil;
import java.io.UnsupportedEncodingException;
import java.util.Date;
//#ifdef DLOGGING
import net.sf.jlogmicro.util.logging.Logger;
import net.sf.jlogmicro.util.logging.Level;
//#endif

/**
 * RssShortItem class is a data store for a shortened item in RSS feed to save
 * memory.  One short item consist of title, optional date, and index in feed
 * list of items.
 *
 * @author  Irving Bunton, Jr
 * @version 1.1
 */
public class RssShortItem {
    
	//#ifdef DLOGGING
    private Logger m_logger = Logger.getLogger("RssShortItem");
	//#endif
    private String m_title = "";   // The RSS item title
    private Date m_date = null;
    private boolean m_unreadItem = false;
    private int m_index = -1;
	//#ifdef DLOGGING
    private boolean m_fineLoggable = m_logger.isLoggable(Level.FINE);
    private boolean m_finestLoggable = m_logger.isLoggable(Level.FINEST);
	//#endif
    
    /** Creates a new instance of RssItem */
    public RssShortItem(String title, Date pubDate, boolean unreadItem,
						int index) {
        m_title = title;
        m_date = pubDate;
        m_unreadItem = unreadItem;
        m_index = index;
    }
    
    public RssShortItem(RssItem item, int index) {
		this(item.m_title, item.m_date, item.m_unreadItem, index);
	}

    public void setTitle(String title) {
        this.m_title = title;
    }

    /** Get RSS item title */
    public String getTitle(){
        return m_title;
    }
    
    /** Get RSS item publication date */
    public Date getDate() {
        return m_date;
    }
    
    public void setDate(Date date) {
        this.m_date = date;
    }

	/* Compare item. */
	public boolean equals(RssShortItem item) {
		if (!item.m_title.equals(m_title)) {
			//#ifdef DLOGGING
			if (m_finestLoggable) {m_logger.finest("unequal item.m_title,this=" + item.m_title + "," + m_title);}
			//#endif
			return false;
		}
		if ((item.m_date == null) && (this.m_date == null)) {
		} else if ((item.m_date != null) && (this.m_date != null)) {
			if (item.m_date.equals(this.m_date)) {
			} else {
				//#ifdef DLOGGING
				if (m_finestLoggable) {m_logger.finest("unequal dates=" + item.m_date + "," + m_date);}
				//#endif
				return false;
			}
		} else {
			//#ifdef DLOGGING
			if (m_finestLoggable) {m_logger.finest("unequal dates=" + item.m_date + "," + m_date);}
			//#endif
			return false;
		}
		if (item.m_unreadItem != m_unreadItem) {
			//#ifdef DLOGGING
			if (m_finestLoggable) {m_logger.finest("unequal item.m_unreadItem,this=" + item.m_unreadItem + "," + m_unreadItem);}
			//#endif
			return false;
		}
		if (item.m_index != m_index) {
			//#ifdef DLOGGING
			if (m_finestLoggable) {m_logger.finest("unequal item.m_index,this=" + item.m_index + "," + m_index);}
			//#endif
			return false;
		}
		return true;
	}

    public void setUnreadItem(boolean unreadItem) {
        this.m_unreadItem = unreadItem;
    }

    public boolean isUnreadItem() {
        return (m_unreadItem);
    }

    public void setIndex(int index) {
        this.m_index = index;
    }

    public int getIndex() {
        return (m_index);
    }

    /** convert the object to string */
    public String toString() {
        String preData = m_title + "|" + m_date + "|" + m_unreadItem + "|" +
			m_index;
		return (preData);
	}
    
}
