/*
 * CompatibilityRssFeed2.java
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
import java.io.UnsupportedEncodingException;
import com.substanceofcode.rssreader.businessentities.RssFeed;
import com.substanceofcode.rssreader.businessentities.RssItem;
import java.util.*;

/**
 * CompatibilityRssFeed2 class contains one RSS feed's properties.
 * Properties include name and URL to RSS feed.
 *
 * @author Tommi Laukkanen
 */
public class CompatibilityRssFeed2 extends RssFeed {
    
    /** Creates a new instance of RSSBookmark */
    public CompatibilityRssFeed2(String name, String url, String username, String password){
		super();
        m_name = name;
        m_url = url;
        m_username = username;
        m_password = password;
    }
    
	/** Create feed from an existing feed.  **/
	public CompatibilityRssFeed2(RssFeed feed) {
		super();
		this.m_url = feed.m_url;
		this.m_name = feed.m_name;
		this.m_username = feed.m_username;
		this.m_password = feed.m_password;
		this.m_upddate = feed.m_upddate;
		this.m_items = new Vector();
		for (int ic = 0; ic < feed.m_items.size(); ic++) {
			this.m_items.addElement(
					new CompatibilityRssItem2((RssItem)feed.m_items.elementAt(ic)));
		}
	}
    
    /** Creates a new instance of RSSBookmark with record store string */
    public CompatibilityRssFeed2(String storeString){

		try {
        
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
			
			int ITEMS = 5;
			if (ITEMS < nodes.length) {
				int UPDDATE = 4;
				String dateString = nodes[UPDDATE];
				if(dateString.length()>0) {
					m_upddate = new Date(Long.parseLong(dateString));
				}
				// Encode for better UTF-8 and to allow '|' in the name.
				CompatibilityBase64 b64 = new CompatibilityBase64();
				byte[] decodedName = b64.decode(m_name);
				try {
					m_name = new String( decodedName , "UTF-8" );
				} catch (UnsupportedEncodingException e) {
					m_name = new String( decodedName );
				}
			} else {
				ITEMS = 4;
			}
			String itemArrayData = nodes[ ITEMS ];
			
			// Deserialize itemss
			String[] serializedItems = StringUtil.split(itemArrayData, ".");
			
			m_items = new Vector();
			for(int itemIndex=0; itemIndex<serializedItems.length; itemIndex++) {
				String serializedItem = serializedItems[ itemIndex ];
				if(serializedItem.length()>0) {
					RssItem rssItem = CompatibilityRssItem2.deserialize2( serializedItem );
					if (rssItem != null) {
						m_items.addElement( rssItem );
					}
				}
			}
       
        } catch(Exception e) {
            System.err.println("Error while rssfeed initialization : " + e.toString());
			e.printStackTrace();
        }
    }
    
    /** Return record store string */
    public String getStoreString(boolean serializeItems){
        String serializedItems = "";
        if( serializeItems ) {
            for(int itemIndex=0; itemIndex<m_items.size();itemIndex++) {
                CompatibilityRssItem2 rssItem =
					new CompatibilityRssItem2((RssItem)m_items.elementAt(itemIndex));
                String serializedItem = rssItem.serialize();
                serializedItems += serializedItem + ".";
            }
        }
		String encodedName;
        CompatibilityBase64 b64 = new CompatibilityBase64();
		try {
			encodedName = b64.encode( m_name.getBytes("UTF-8") );
		} catch (UnsupportedEncodingException e) {
			encodedName = b64.encode( m_name.getBytes() );
		}
        String storeString = encodedName + "|" +
                              m_url + "|" + m_username + "|" +
                m_password + "|" +
                ((m_upddate == null) ? "" :
				 String.valueOf(m_upddate.getTime())) + "|" +
                serializedItems;
        return storeString;
        
    }

	/** Compare feed to an existing feed.  **/
	public boolean equals(RssFeed feed) {
		if (!feed.m_url.equals(this.m_url)) {
			System.out.println("m_url != new m_url=" + m_url + "," + feed.m_url);
			return false;
		}
		if (!feed.m_name.equals(this.m_name)) {
			System.out.println("m_name != new m_name=" + m_name + "," + feed.m_name);
			return false;
		}
		if (!feed.m_username.equals(this.m_username)) {
			System.out.println("m_username != new m_username=" + m_username + "," + feed.m_username);
			return false;
		}
		if (!feed.m_password.equals(this.m_password)) {
			System.out.println("m_password != new m_password=" + m_password + "," + feed.m_password);
			return false;
		}
		if ((feed.m_upddate == null) && (this.m_upddate == null)) {
			System.out.println("m_upddate != new m_upddate=" + m_upddate + "," + feed.m_upddate);
		} else if ((feed.m_upddate != null) && (this.m_upddate != null)) {
			if (feed.m_upddate.equals(this.m_upddate)) {
			} else {
				System.out.println("m_upddate != new m_upddate=" + m_upddate + "," + feed.m_upddate);
				return false;
			}
		} else {
			System.out.println("m_upddate != new m_upddate=" + m_upddate + "," + feed.m_upddate);
			return false;
		}
		if (feed.m_items.size() != m_items.size()) {
			System.out.println("m_items.size() != new m_items.size()=" + m_items.size() + "," + feed.m_items.size());
			return false;
		}
		int ilen = m_items.size();
		RssItem [] ritems = new RssItem[ilen];
		m_items.copyInto(ritems);
		int flen = feed.m_items.size();
		RssItem [] fitems = new RssItem[flen];
		feed.m_items.copyInto(fitems);
		for (int ic = 0; ic < ilen; ic++) {
			if (!ritems[ic].equals(fitems[ic])) {
				System.out.println("m_items[ic] != new m_items[ic]=" + ic + "," + ritems[ic] + "," + fitems[ic]);
				return false;
			}
		}
		return true;
	}
    
}
