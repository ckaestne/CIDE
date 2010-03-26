/*
 * CompatibilityRssItem3.java
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
//DLOGDEF@
package com.substanceofcode.rssreader.businessentities;

import com.substanceofcode.utils.CompatibilityBase64;
import com.substanceofcode.utils.StringUtil;
import java.io.UnsupportedEncodingException;
import java.util.Date;
//#ifdef DLOGGING
import net.sf.jlogmicro.util.logging.Logger;
import net.sf.jlogmicro.util.logging.Level;
//#endif

/**
 * CompatibilityRssItem3 class is a data store for a single item in RSS feed.
 * One item consist of title, link, description and optional date.
 *
 * @author  Tommi Laukkanen
 * @version 1.1
 */
public class CompatibilityRssItem3 extends RssItunesItem {
    
    protected static final char CONE = (char)1;
	//#ifdef DLOGGING
    private Logger logger = Logger.getLogger("CompatibilityRssItem3");
	//#endif
	// Use protected so that sub classes can access these including the
	// backward store compatibility classes.
	//#ifdef DLOGGING
    private boolean fineLoggable = logger.isLoggable(Level.FINE);
    private boolean finestLoggable = logger.isLoggable(Level.FINEST);
	//#endif
    
    /** Creates a new instance of CompatibilityRssItem3.  Used by this class and
	    CompatibilityRssItem3 and later the fields are initalized. */
    protected CompatibilityRssItem3() {
	}

    /** Creates a new instance of CompatibilityRssItem3 */
    public CompatibilityRssItem3(String title, String link, String desc, Date pubDate,
			       String enclosure, boolean unreadItem) {
        m_title = title;
        m_link = link;
        m_desc = desc;
        m_date = pubDate;
        m_enclosure = enclosure;
        m_unreadItem = unreadItem;
    }
    
    public CompatibilityRssItem3(CompatibilityRssItem3 item) {
		this(item.m_title, item.m_link, item.m_desc, item.m_date,
			 item.m_enclosure, item.m_unreadItem);
	}

    public CompatibilityRssItem3(RssItem item) {
		this(item.m_title, item.m_link, item.m_desc, item.m_date,
			 item.m_enclosure, item.m_unreadItem);
	}

    /** Get RSS item title */
    public String getTitle(){
        return m_title;
    }
    
    /** Get RSS item link address */
    public String getLink(){
        return m_link;
    }
    
    /** Get RSS item description */
    public String getDescription(){
        return m_desc;
    }
    
    /** Get RSS item publication date */
    public Date getDate() {
        return m_date;
    }
    
    /** Serialize the object
	  When we serialize we don't do anything special for itunes as the
	  store to memory will be deserialized only by the iTunes capable
	  version.
	  */
    public String unencodedSerialize3() {
        String dateString;
        if(m_date==null){
            dateString = "";
        } else {
		    // We use base 16 (hex) for the date so that we can save some
			// space for toString.
            dateString = Long.toString( m_date.getTime(), 16 );
        }

		String title = m_title.replace('|', CONE);
        String preData = title + "|" + m_link + "|" + dateString + "|" +
			    m_enclosure + "|" + (m_unreadItem ? "1" : "0") + "|" + m_desc;
		return (preData);
	}
    
    /** Serialize the object
	  this serialize does not need to know if Itunes is capable/enabled given
	  that no fields were added to make it capable/enabled
	  */
    public String serialize3() {
        String preData = unencodedSerialize3();
        CompatibilityBase64 b64 = new CompatibilityBase64();
        String encodedSerializedData = null;
		try {
			encodedSerializedData = b64.encode( preData.getBytes("UTF-8") );
		} catch (UnsupportedEncodingException e) {
			encodedSerializedData = b64.encode( preData.getBytes() );
		}
		return encodedSerializedData;
	}
		
	/**
	  Initialize fields in the class from data.
	  startIndex - Starting index in nodes of CompatibilityRssItem3
	  iTunesCapable - True if the data can support Itunes (but may not
	  				  actually have Itunes data) or may not be turned
					  on by the user.  So, the serializaion/deserialization
					  will account for iTunes fields except if not
					  enabled, the will have empty values.
					  If itunes capable we use base 16 (hex) for
					  the date so that we can save some space for
					  toString.
	  hasPipe - True if the data has a pipe in at least one item
	  nodes - (elements in an array).
	  **/
	protected void init(int startIndex, boolean iTunesCapable,
					    boolean hasPipe, String [ ] nodes) {

		try {
			/* Node count should be 6:
			 * title | link | date | enclosure | unreadItem | desc
			 */
			int TITLE = 0;
			//#ifdef DLOGGING
			if (finestLoggable) {logger.finest("startIndex,nodes.length,first nodes=" + startIndex + "," + nodes.length + "|" + nodes[ startIndex + TITLE ]);}
			//#endif
			m_title = nodes[startIndex + TITLE];
			if (hasPipe) {
				if (iTunesCapable) {
					m_title = m_title.replace(CONE, '|');
				} else {
					m_title = m_title.replace('\n', '|');
				}
			}
			
			int LINK = 1;
			m_link = nodes[startIndex + LINK];
			
			int DATE = 2;
			String dateString = nodes[startIndex + DATE];
			if(dateString.length()>0) {
				if (iTunesCapable) {
					m_date = new Date(Long.parseLong(dateString, 16));
				} else {
					m_date = new Date(Long.parseLong(dateString));
				}
			} else {
				m_date = null;
			}        
			
			int ENCLOSURE = 3;
			m_enclosure = nodes[startIndex + ENCLOSURE];

			int NEWITEM = 4;
			String cunreadItem = nodes[startIndex + NEWITEM];
			m_unreadItem = (cunreadItem.equals("1"));
					
			// If description has '|', we need to join.
			int DESC = 5;
			if (DESC + startIndex < (nodes.length - 1)) {
				m_desc = StringUtil.join(nodes, "|", startIndex + DESC);
			} else {
				m_desc = nodes[startIndex + DESC];
			}
					
        } catch(Exception e) {
            System.err.println("Error while rssitem init : " + e.toString());
			e.printStackTrace();
        }
    }

	/** Deserialize the object **/
	public static CompatibilityRssItem3 deserialize3(String encodedData) {
		try {
			// CompatibilityBase64 decode
			CompatibilityBase64 b64 = new CompatibilityBase64();
			byte[] decodedData = b64.decode(encodedData);
			String data;
			try {
				data = new String( decodedData, "UTF-8" );
			} catch (UnsupportedEncodingException e) {
				data = new String( decodedData );
			}
			return unencodedDeserialize3(data);
        } catch(Exception e) {
            System.err.println("Error while rssitem deserialize : " + e.toString());
			e.printStackTrace();
			return new CompatibilityRssItem3();
        }
	}

	/** This is only used to deserialize data from a previous version
	  but not the initial version as that version has a bug in getting
	  the items.
	  */
	public static CompatibilityRssItem3 unencodedDeserialize3(String data) {
		CompatibilityRssItem3 item = new CompatibilityRssItem3();
		try {
			boolean hasPipe = (data.indexOf('\n') >= 0);
			String[] nodes = StringUtil.split( data, "|");
			item.init(0, false, hasPipe, nodes);
			return item;
			
        } catch(Exception e) {
            System.err.println("Error while rssitem deserialize : " + e.toString());
			e.printStackTrace();
        }
        return item;
	}

	/* Copy to item. */
	public CompatibilityRssItem3 copyTo(CompatibilityRssItem3 item) {
		item.m_title = m_title;
		item.m_link  = m_link;
		item.m_desc  = m_desc;
		item.m_date = m_date;
		item.m_enclosure  = m_enclosure;;
		item.m_unreadItem = m_unreadItem;
		return item;
	}

	/* Compare item. */
	public boolean equals(RssItem item) {
		if (!item.m_title.equals(m_title)) {
			//#ifdef DLOGGING
			if (finestLoggable) {logger.finest("unequal item.m_title,this=" + item.m_title + "," + m_title);}
			//#endif
			return false;
		}
		if (!item.m_link.equals(m_link)) {
			//#ifdef DLOGGING
			if (finestLoggable) {logger.finest("unequal item.m_link,this=" + item.m_link + "," + m_link);}
			//#endif
			return false;
		}
		if (!item.m_desc.equals(m_desc)) {
			//#ifdef DLOGGING
			if (finestLoggable) {logger.finest("unequal item.m_desc,this=" + item.m_desc + "," + m_desc);}
			//#endif
			return false;
		}
		if ((item.m_date == null) && (this.m_date == null)) {
		} else if ((item.m_date != null) && (this.m_date != null)) {
			if (item.m_date.equals(this.m_date)) {
			} else {
				//#ifdef DLOGGING
				if (finestLoggable) {logger.finest("unequal dates=" + item.m_date + "," + m_date);}
				//#endif
				return false;
			}
		} else {
			//#ifdef DLOGGING
			if (finestLoggable) {logger.finest("unequal dates=" + item.m_date + "," + m_date);}
			//#endif
			return false;
		}
		if (!item.m_enclosure.equals(m_enclosure)) {
			//#ifdef DLOGGING
			if (finestLoggable) {logger.finest("unequal item.m_enclosure,this=" + item.m_enclosure + "," + m_enclosure);}
			//#endif
			return false;
		}
		if (item.m_unreadItem != m_unreadItem) {
			//#ifdef DLOGGING
			if (finestLoggable) {logger.finest("unequal item.m_unreadItem,this=" + item.m_unreadItem + "," + m_unreadItem);}
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

    public void setEnclosure(String enclosure) {
        this.m_enclosure = enclosure;
    }

    public String getEnclosure() {
        return (m_enclosure);
    }

    /** convert the object to string */
    public String toString() {
        String preData = m_title + "|" + m_link + "|" + m_date + "|" +
			    m_enclosure + "|" + m_unreadItem + "|" + m_desc;
		return (preData);
	}
    
}
