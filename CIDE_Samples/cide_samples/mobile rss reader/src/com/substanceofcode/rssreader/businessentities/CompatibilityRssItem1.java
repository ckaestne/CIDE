/*
 * CompatibilityRssItem1.java
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
 * CompatibilityRssItem1 class is a data store for a single item in RSS feed.
 * One item consist of title, link, description and optional date.
 *
 * @author  Tommi Laukkanen
 * @version 1.1
 */
public class CompatibilityRssItem1 extends RssItem {
    
    /** Creates a new instance of CompatibilityRssItem1.  Used by this class and
	    RssItem and later the fields are initalized. */
    protected CompatibilityRssItem1() {
		super();
	}

    /** Creates a new instance of RssItem */
    public CompatibilityRssItem1(String title, String link, String desc) {
		super();
        m_title = title;
        m_link = link;
        m_desc = desc;
        m_date = null;
    }
    
    /** Creates a new instance of RssItem */
    public CompatibilityRssItem1(String title, String link, String desc, Date pubDate) {
		super();
        m_title = title;
        m_link = link;
        m_desc = desc;
        m_date = pubDate;
    }
    
    public CompatibilityRssItem1(RssItem item) {
		super();
        m_title = item.m_title;
        m_link = item.m_link;
        m_desc = item.m_desc;
        m_date = item.m_date;
	}

    /** Serialize the object */
    public String serialize() {
        String dateString;
        if(m_date==null){
            dateString = "";
        } else {
            dateString = String.valueOf( m_date.getTime() );
        }
        String preData = m_title + "|" + m_link + "|" + dateString + "|" + m_desc;
        CompatibilityBase64 b64 = new CompatibilityBase64();
        String encodedSerializedData = b64.encode( preData.getBytes() );
        return encodedSerializedData;
    }
    
    /** Deserialize the object */
    public static CompatibilityRssItem1 deserialize1(String data) {
        
        String title = "";
        String link = "";
        String desc = "";
        Date date = null;

        // CompatibilityBase64 decode
        CompatibilityBase64 b64 = new CompatibilityBase64();
        byte[] decodedData = b64.decode(data);
        data = new String( decodedData );
        
        String[] nodes = StringUtil.split( data, "|");
        
        /* Node count should be 4:
         * title | link | date | desc
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
        
        int DESC = 3;
        desc = nodes[DESC];
                
        CompatibilityRssItem1 item = new CompatibilityRssItem1(title, link, desc, date);
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
		return true;
	}

}
