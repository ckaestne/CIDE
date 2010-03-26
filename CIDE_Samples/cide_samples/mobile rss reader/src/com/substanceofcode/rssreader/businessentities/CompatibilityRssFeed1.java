/*
 * CompatibilityRssFeed1.java
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

import com.substanceofcode.utils.StringUtil;
import com.substanceofcode.rssreader.businessentities.RssFeed;
import com.substanceofcode.rssreader.businessentities.RssItem;
import java.io.UnsupportedEncodingException;
import java.util.*;

/**
 * CompatibilityRssFeed1 class contains one RSS feed's properties.
 * Properties include name and URL to RSS feed.
 *
 * @author Tommi Laukkanen
 */
public class CompatibilityRssFeed1 extends RssFeed {
    
    /** Creates a new instance of RSSItem */
    public CompatibilityRssFeed1(){
		super();
	}

    /** Creates a new instance of RSSBookmark */
    public CompatibilityRssFeed1(String name, String url, String username, String password){
		super();
        m_name = name;
        m_url = url;
        m_username = username;
        m_password = password;
    }
    
	/** Create feed from an existing feed.  **/
	public CompatibilityRssFeed1(RssFeed feed) {
		super();
        m_name = feed.m_name;
        m_url = feed.m_url;
        m_username = feed.m_username;
        m_password = feed.m_password;
		for (int ic = 0; ic < feed.m_items.size(); ic++) {
			this.m_items.addElement(
					new CompatibilityRssItem1((RssItem)feed.m_items.elementAt(ic)));
		}
	}
    
    /** Creates a new instance of RSSBookmark with record store string */
    public CompatibilityRssFeed1(String storeString){
        
        String[] nodes = StringUtil.split( storeString, "|" );
        
        /* Node count should be 5
         * name | url | username | password | items
         */
        int NAME = 0;        
        m_name = nodes[ NAME ];
        
        int URL = 1;
        m_url = nodes[ URL ];
        
        int USERNAME = 2;
        m_username = nodes[ USERNAME ];
        
        int PASSWORD = 3;
        m_password = nodes[ PASSWORD ];
        
        int ITEMS = 4;
        String itemArrayData = nodes[ ITEMS ];
        
        // Deserialize itemss
        String[] serializedItems = StringUtil.split(itemArrayData, ".");
        
        m_items = new Vector();
        for(int itemIndex=0; itemIndex<serializedItems.length; itemIndex++) {
            String serializedItem = serializedItems[ itemIndex ];
            if(serializedItem.length()>0) {
                RssItem rssItem = CompatibilityRssItem1.deserialize1( serializedItem );
                m_items.addElement( rssItem );
            }
        }
       
    }
    
    /** Return record store string */
    public String getStoreString(boolean serializeItems){
        String serializedItems = "";
        if( serializeItems ) {
            for(int itemIndex=0; itemIndex<m_items.size();itemIndex++) {
                CompatibilityRssItem1 rssItem =
					(CompatibilityRssItem1)m_items.elementAt(itemIndex);
                String serializedItem = rssItem.serialize();
                serializedItems += serializedItem + ".";
            }
        }
        String storeString = m_name + "|" +
                m_url + "|" +
                m_username + "|" +
                m_password + "|" +
                serializedItems;
        return storeString;
        
    }
    
	/** Compare feed to an existing feed.  **/
	public boolean equals(RssFeed feed) {
		if (!feed.m_url.equals(this.m_url)) {
			System.out.println("Error m_url != new m_url=" + m_url + "," + feed.m_url);
			return false;
		}
		if (!feed.m_name.equals(this.m_name)) {
			System.out.println("Error m_name != new m_name=" + m_name + "," + feed.m_name);
			return false;
		}
		if (!feed.m_username.equals(this.m_username)) {
			System.out.println("Error m_username != new m_username=" + m_username + "," + feed.m_username);
			return false;
		}
		if (!feed.m_password.equals(this.m_password)) {
			System.out.println("Error m_password != new m_password=" + m_password + "," + feed.m_password);
			return false;
		}
		if (feed.m_items.size() == 0) {
			return true;
		} else {
			System.out.println("Error items non-zero");
			return false;
		}
	}
    
}
