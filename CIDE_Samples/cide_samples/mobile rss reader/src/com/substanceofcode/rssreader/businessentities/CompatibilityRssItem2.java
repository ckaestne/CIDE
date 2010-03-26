/*
 * CompatibilityRssItem2.java
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

package com.substanceofcode.rssreader.businessentities;

import com.substanceofcode.utils.CompatibilityBase64;
import com.substanceofcode.utils.StringUtil;
import com.substanceofcode.rssreader.businessentities.RssItem;
import java.io.UnsupportedEncodingException;
import java.util.Date;

/**
 * CompatibilityRssItem2 class is a data store for a single item in RSS feed.
 * One item consist of title, link, description and optional date.
 *
 * @author  Tommi Laukkanen
 * @version 1.1
 */
public class CompatibilityRssItem2 extends RssItem {
    
    /** Creates a new instance of CompatibilityRssItem2.  Used by this class and
	    RssItem and later the fields are initalized. */
    protected CompatibilityRssItem2() {
		super();
	}

    /** Creates a new instance of RssItem */
    public CompatibilityRssItem2(String title, String link, String desc) {
		super();
        m_title = title;
        m_link = link;
        m_desc = desc;
        m_date = null;
        m_enclosure = "";
    }
    
    /** Creates a new instance of RssItem */
    public CompatibilityRssItem2(String title, String link, String desc, Date pubDate,
			       String enclosure, boolean unreadItem) {
		super();
        m_title = title;
        m_link = link;
        m_desc = desc;
        m_date = pubDate;
        m_enclosure = enclosure;
        m_unreadItem = unreadItem;
    }
    
    public CompatibilityRssItem2(RssItem item) {
		super();
        m_title = item.m_title;
        m_link = item.m_link;
        m_desc = item.m_desc;
        m_date = item.m_date;
        m_enclosure = item.m_enclosure;
        m_unreadItem = item.m_unreadItem;
	}

    /** Serialize the object */
    public String serialize() {
        String dateString;
        if(m_date==null){
            dateString = "";
        } else {
            dateString = String.valueOf( m_date.getTime() );
        }

		String title = m_title.replace('|', '\n');
        String preData = title + "|" + m_link + "|" + dateString + "|" +
			    m_enclosure + "|" + (m_unreadItem ? "1" : "0") + "|" + m_desc;
        CompatibilityBase64 b64 = new CompatibilityBase64();
        String encodedSerializedData = null;
		try {
			encodedSerializedData = b64.encode( preData.getBytes("UTF-8") );
		} catch (UnsupportedEncodingException e) {
			encodedSerializedData = b64.encode( preData.getBytes() );
		}
		return encodedSerializedData;
	}
		
	/** Deserialize the object */
	public static CompatibilityRssItem2 deserialize2(String data) {
			
		String title = "";
		String link = "";
		String desc = "";
		Date date = null;
		String enclosure = "";
		boolean unreadItem = false;
		CompatibilityRssItem2 item = null;

		try {
			// CompatibilityBase64 decode
			CompatibilityBase64 b64 = new CompatibilityBase64();
			byte[] decodedData = b64.decode(data);
			try {
				data = new String( decodedData, "UTF-8" );
			} catch (UnsupportedEncodingException e) {
				data = new String( decodedData );
			}
			
			String[] nodes = StringUtil.split( data, "|");
			
			/* Node count should be 6:
			 * title | link | date | enclosure | unreadItem | desc
			 */
			int TITLE = 0;
			title = nodes[TITLE];
			
			int LINK = 1;
			link = nodes[LINK];
			
			int DATE = 2;
			String dateString = nodes[DATE];
			if(dateString.length()>0) {
				date = new Date(Long.parseLong(dateString));
			}        
			
			int DESC = 5;
			if (DESC < nodes.length) {
				int ENCLOSURE = 3;
				enclosure = nodes[ENCLOSURE];
				int NEWITEM = 4;
				String cunreadItem = nodes[NEWITEM];
				if (cunreadItem.equals("1")) {
					unreadItem = true;
				} else if (cunreadItem.equals("0")) {
					unreadItem = false;
				} else {
					// If we get here, then description has '|' in it.
					DESC = 3;
				}
				if (DESC != 3) {
					title = title.replace('\n', '|');
				}
			} else {
				DESC = 3;
			}
					
			// If description has '|', we need to join.
			if (DESC < (nodes.length - 1)) {
				desc = StringUtil.join(nodes, "|", DESC);
			} else {
				desc = nodes[DESC];
			}
					
			item = new CompatibilityRssItem2(title, link, desc, date, enclosure, unreadItem);

        } catch(Exception e) {
            System.err.println("Error while rssitem deserialize : " + e.toString());
			e.printStackTrace();
        }
        return item;
    }

	/* Compare item. */
	public boolean equals(RssItem item) {
		if (!item.m_title.equals(m_title)) {
			return false;
		}
		if (!item.m_link.equals(m_link)) {
			return false;
		}
		if (!item.m_desc.equals(m_desc)) {
			return false;
		}
		if ((item.m_date == null) && (this.m_date == null)) {
		} else if ((item.m_date != null) && (this.m_date != null)) {
			if (item.m_date.equals(this.m_date)) {
			} else {
				return false;
			}
		} else {
			return false;
		}
		if (!item.m_enclosure.equals(m_enclosure)) {
			return false;
		}
		if (item.m_unreadItem != m_unreadItem) {
			return false;
		}
		return true;
	}

}
