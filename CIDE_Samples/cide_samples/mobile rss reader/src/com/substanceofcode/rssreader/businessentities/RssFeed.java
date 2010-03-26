/*
 * RssFeed.java
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
// Expand to define itunes define
//#define DNOITUNES
package com.substanceofcode.rssreader.businessentities;

import com.substanceofcode.utils.Base64;
import com.substanceofcode.utils.StringUtil;
import java.io.UnsupportedEncodingException;
import java.util.*;

import com.substanceofcode.utils.CauseException;
import com.substanceofcode.utils.CauseMemoryException;

//#ifdef DLOGGING
import net.sf.jlogmicro.util.logging.Logger;
import net.sf.jlogmicro.util.logging.Level;
//#endif

/**
 * RssFeed class contains one RSS feed's properties.
 * Properties include name and URL to RSS feed.
 *
 * @author Tommi Laukkanen
 */
public class RssFeed{
    
    public static int NAME_OFFSET = 0;
    public static final int DATE_OFFSET = 6;
    protected static final char CONE = (char)1;
    protected static final char [] CBONE = {CONE};
    public static String STR_ONE = new String(CBONE);
    protected static final char CTWO = (char)2;
    protected static final char [] CBTWO = {CTWO};
    protected static final char CTHREE = (char)3;
    protected static final char [] CBTHREE = {CTHREE};
    public static String STR_TWO = new String(CBTWO);
    public static int ITUNES_ITEMS = 8;
	//#ifdef DLOGGING
    private Logger logger = Logger.getLogger("RssFeed");
	//#endif
	//#ifdef DLOGGING
    private boolean fineLoggable = logger.isLoggable(Level.FINE);
    private boolean finestLoggable = logger.isLoggable(Level.FINEST);
	//#endif
    protected String m_url  = "";
    protected String m_name = "";
    protected String m_username = "";
    protected String m_password = "";
    protected Date m_upddate = null;
    protected Date m_date = null;
    protected String m_link = "";   // The RSS feed link
    protected int m_category = -1; // The RSS feed category
    
    protected Vector m_items = new Vector();  // The RSS item vector
    
    /** Creates a new instance of RSSBookmark */
    public RssFeed(){
	}

    /** Creates a new instance of RSSBookmark */
    public RssFeed(String name, String url, String username, String password){
        m_name = name;
        m_url = url;
        m_username = username;
        m_password = password;
    }
    
    /** Creates a new instance of RSSBookmark */
    public RssFeed(String name, String url, String username, String password,
				   Date upddate,
				   String link,
				   Date date,
				   int category) {
        m_name = name;
        m_url = url;
        m_username = username;
        m_password = password;
        m_upddate = upddate;
		//#ifdef DITUNES
        m_link = link;
        m_date = date;
		//#endif
        m_category = category;
    }
    
	/** Create feed from an existing feed.  **/
	public RssFeed(RssFeed feed) {
		this.m_url = feed.m_url;
		this.m_name = feed.m_name;
		this.m_username = feed.m_username;
		this.m_password = feed.m_password;
		this.m_upddate = feed.m_upddate;
		//#ifdef DITUNES
		this.m_link = feed.m_link;
		this.m_date = feed.m_date;
		//#endif
		this.m_category = feed.m_category;
		this.m_items = new Vector();
		int ilen = feed.m_items.size();
		RssItem [] rItems = new RssItem[ilen];
		feed.m_items.copyInto(rItems);
		for (int ic = 0; ic < ilen; ic++) {
			m_items.addElement(rItems[ic]);
		}
	}
    
    /** Creates a new instance of RSSBookmark with record store string 
	  firstSettings  - True if the data was from the settings which were
	  				   compatible with the first version and several after.
	  **/
    public RssFeed(boolean firstSettings, boolean encoded, String storeString){

		try {
        
			String[] nodes = StringUtil.split( storeString, '|' );
			init(firstSettings, 0, false, false, encoded, nodes);
        } catch(Exception e) {
            System.err.println("Error while rssfeed initialization : " + e.toString());
			e.printStackTrace();
        }
	}
			
	/**
	  Initialize fields in the class from data.
	  startIndex - Starting index in nodes of RssItem
	  iTunesCapable - True if the data can support Itunes (but may not
	  				  actually have Itunes data) or may not be turned
					  on by the user.  So, the serializaion/deserialization
					  will account for iTunes fields except if not
					  enabled, the will have empty values.
	  hasPipe - True if the data has a pipe in at least one item
	  nodes - (elements in an array).
	  **/
	protected void init(boolean firstSettings,
						int startIndex, boolean iTunesCapable,
					    boolean hasPipe, boolean encoded,
					    String [] nodes)
	throws CauseMemoryException, CauseException {

		try {
        
			/* Node count should be 8
			 * name | url | username | password | upddate | link | date |
			 * category | items 
			 */
			int NAME = NAME_OFFSET;
			//#ifdef DLOGGING
			if (finestLoggable) {logger.finest("startIndex,nodes.length,first nodes=" + startIndex + "," + nodes.length + "|" + nodes[ startIndex + NAME ]);}
			//#endif
			m_name = nodes[ startIndex + NAME ];
			
			int URL = 1;
			m_url = nodes[ startIndex + URL ];
			
			int USERNAME = 2;
			m_username = nodes[ startIndex + USERNAME ];
			if (iTunesCapable && hasPipe) {
				m_username = m_username.replace(CONE, '|');
			}
			
			int PASSWORD = 3;
			m_password = nodes[ startIndex + PASSWORD ];
			if (iTunesCapable) {
				// Dencode so that password is not in regular lettters.
				Base64 b64 = new Base64();
				byte[] decodedPassword = b64.decode(m_password);
				try {
					m_password = new String( decodedPassword , "UTF-8" );
				} catch (UnsupportedEncodingException e) {
					m_password = new String( decodedPassword );
				}
				if (hasPipe) {
					m_password = m_password.replace(CONE, '|');
				}
			}
			
			m_items = new Vector();
			if (firstSettings) {
				// Given the bugs with the first settings, we do not
				// retrieve the items so that we can restore them
				// without the bugs.
				return;
			}

			int ITEMS = (iTunesCapable ? ITUNES_ITEMS : 5);
			int UPDDATE = 4;
			String dateString = nodes[startIndex + UPDDATE];
			if(dateString.length()>0) {
				if (iTunesCapable) {
					m_upddate = new Date(Long.parseLong(dateString, 16));
				} else {
					m_upddate = new Date(Long.parseLong(dateString));
				}
			}
			if (iTunesCapable && hasPipe) {
				m_name = m_name.replace(CONE, '|');
			} else {
				if (!iTunesCapable) {
					// Dencode for better UTF-8 and to allow '|' in the name.
					// For iTunesCapable, replace | with (char)1
					Base64 b64 = new Base64();
					byte[] decodedName = b64.decode(m_name);
					try {
						m_name = new String( decodedName , "UTF-8" );
					} catch (UnsupportedEncodingException e) {
						m_name = new String( decodedName );
					}
				}
			}
			if (iTunesCapable) {
				//#ifdef DITUNES
				int LINK = 5;
				if (nodes[startIndex + LINK].length() > 0) {
					m_link = nodes[startIndex + LINK];
				}
				int DATE = DATE_OFFSET;
				String fdateString = nodes[startIndex + DATE];
				if (fdateString.length() > 0) {
					m_date = new Date(Long.parseLong(fdateString, 16));
				}
				//#endif
				int CATEGORY = 7;
				if (nodes[startIndex + CATEGORY].length() > 0) {
					m_category = Integer.parseInt(nodes[startIndex + CATEGORY]);
				}
			}
			if (firstSettings) {
				// Given the bugs with the first settings, we do not
				// retrieve the items so that we can restore them
				// without the bugs.
				return;
			}
			String itemArrayData = nodes[ startIndex + ITEMS ];
			
			// Deserialize itemss
			if (!encoded) {
				itemArrayData = itemArrayData.replace(CTHREE, '|');
			}
			String[] serializedItems = StringUtil.split(itemArrayData,
						(encoded ? '.' : CTWO));
			
			for(int itemIndex=0; itemIndex<serializedItems.length; itemIndex++) {
				String serializedItem = serializedItems[ itemIndex ];
				if(serializedItem.length()>0) {
					RssItem rssItem;
					if (iTunesCapable) {
						if (encoded) {
							rssItem = RssItunesItem.deserialize( 
									serializedItem );
						} else {
							rssItem = RssItunesItem.unencodedDeserialize(
									serializedItem );
						}
					} else {
						rssItem = RssItem.deserialize( serializedItem );
					}
					if (rssItem != null) {
						m_items.addElement( rssItem );
					}
				}
			}
		} catch (CauseMemoryException e) {
			throw e;
		} catch (CauseException e) {
			throw e;
        } catch(Exception e) {
			CauseException ce = new CauseException(
					"Internal error during initialize of RssFeed", e);
			//#ifdef DLOGGING
			logger.severe(ce.getMessage(), e);
			//#endif
            System.err.println(ce.getMessage() + " " + e.toString());
			e.printStackTrace();
			throw ce;
        } catch(OutOfMemoryError e) {
			CauseMemoryException ce = new CauseMemoryException(
					"Out of memory error during  initialize of RssFeed", e);
			//#ifdef DLOGGING
			logger.severe(ce.getMessage(), e);
			//#endif
            System.err.println(ce.getMessage() + " " + e.toString());
			e.printStackTrace();
			throw ce;
        } catch(Throwable e) {
			CauseException ce = new CauseException(
					"Internal error during initialize of RssFeed", e);
			//#ifdef DLOGGING
			logger.severe(ce.getMessage(), e);
			//#endif
            System.err.println(ce.getMessage() + " " + e.toString());
			e.printStackTrace();
			throw ce;
        }
       
    }
    
    /** Return bookmark's name */
    public String getName(){
        return m_name;
    }
    
    public void setName(String m_name) {
        this.m_name = m_name;
    }

    /** Return bookmark's URL */
    public String getUrl(){
        return m_url;
    }
    
    public void setUrl(String url) {
        this.m_url = url;
    }

    /** Return bookmark's username for basic authentication */
    public String getUsername(){
        return m_username;
    }
    
    /** Return bookmark's password for basic authentication */
    public String getPassword(){
        return m_password;
    }
    
    /** Return record store string for feed only.  This excludes items which
	    are put into store string by RssItunesFeed.  */
    public String getStoreString(final boolean saveHdr,
			final boolean serializeItems, final boolean encoded){
		//#ifdef DLOGGING
		if (finestLoggable) {logger.finest("saveHdr,serializeItems,encoded=" + saveHdr + "," + serializeItems + "," + encoded);}
		//#endif
        StringBuffer serializedItems = new StringBuffer();
        if( serializeItems ) {
			int ilen = m_items.size();
			RssItunesItem [] ritems = new RssItunesItem[ilen];
			m_items.copyInto(ritems);
            for(int itemIndex=0; itemIndex<ilen;itemIndex++) {
                RssItunesItem rssItem = (RssItunesItem)ritems[itemIndex];
				if (encoded) {
					serializedItems.append(rssItem.serialize());
					serializedItems.append(".");
				} else {
					serializedItems.append(
							rssItem.unencodedSerialize().replace('|', CTHREE));
					serializedItems.append(CTWO);
				}
            }
        }
		String name = m_name.replace('|', CONE);
		String username = m_username.replace('|' , CONE);
		String password = m_password.replace('|' , CONE);
		String encodedPassword;
		// Encode password to make reading password difficult
        Base64 b64 = new Base64();
		try {
			encodedPassword = b64.encode( m_password.getBytes("UTF-8") );
		} catch (UnsupportedEncodingException e) {
			encodedPassword = b64.encode( m_password.getBytes() );
		}
	    String exInfoString;
		String updString;
		if (saveHdr) {
			String dateString;
			if(m_date==null){
				dateString = "";
			} else {
				// We use base 16 (hex) for the date so that we can save some
				// space for toString.
				dateString = Long.toString( m_date.getTime(), 16 );
			}
			if(m_upddate==null){
				updString = "";
			} else {
				// We use base 16 (hex) for the update date so that we can save some
				// space for toString.
				updString = Long.toString( m_upddate.getTime(), 16 );
			}
			exInfoString = dateString + "|" +
				((m_category == -1) ? "" : Integer.toString(m_category));
		} else {
			updString = "";
			exInfoString = "|";
		}
        String storeString = name + "|" +
                              m_url + "|" + username + "|" +
                encodedPassword + "|" + updString + "|" +
				m_link + "|" + exInfoString + "|" + serializedItems;
        return storeString;
        
    }

	/** Copy feed to an existing feed.  **/
	public void copyTo(RssFeed toFeed) {
		toFeed.m_url = this.m_url;
		toFeed.m_name = this.m_name;
		toFeed.m_username = this.m_username;
		toFeed.m_password = this.m_password;
		toFeed.m_upddate = this.m_upddate;
		//#ifdef DITUNES
		toFeed.m_link = this.m_link;
		toFeed.m_date = this.m_date;
		//#endif
		toFeed.m_category = this.m_category;
		toFeed.m_items = new Vector();
		int ilen = m_items.size();
		RssItem [] ritems = new RssItem[ilen];
		m_items.copyInto(ritems);
		for (int ic = 0; ic < ilen; ic++) {
			toFeed.m_items.addElement(ritems[ic]);
		}
	}
    
	/** Compare feed to an existing feed.  **/
	public boolean equals(RssFeed feed) {
		if (!feed.m_url.equals(this.m_url)) {
			//#ifdef DLOGGING
			if (finestLoggable) {logger.finest("unequal feed.m_url,this=" + feed.m_url + "," + m_url);}
			//#endif
			return false;
		}
		if (!feed.m_name.equals(this.m_name)) {
			//#ifdef DLOGGING
			if (finestLoggable) {logger.finest("unequal feed.m_name,this=" + feed.m_name + "," + m_name);}
			//#endif
			return false;
		}
		if (!feed.m_username.equals(this.m_username)) {
			//#ifdef DLOGGING
			if (finestLoggable) {logger.finest("unequal feed.m_password,this=" + feed.m_password + "," + m_password);}
			//#endif
			return false;
		}
		if (!feed.m_password.equals(this.m_password)) {
			//#ifdef DLOGGING
			if (finestLoggable) {logger.finest("unequal feed.m_password,this=" + feed.m_password + "," + m_password);}
			//#endif
			return false;
		}
		if ((feed.m_date == null) && (this.m_date == null)) {
		} else if ((feed.m_date != null) && (this.m_date != null)) {
			if (feed.m_date.equals(this.m_date)) {
			} else {
				//#ifdef DLOGGING
				if (finestLoggable) {logger.finest("unequal dates=" + feed.m_date + "," + m_date);}
				//#endif
				return false;
			}
		} else {
			//#ifdef DLOGGING
			if (finestLoggable) {logger.finest("unequal dates=" + feed.m_date + "," + m_date);}
			//#endif
			return false;
		}
		if (!feed.m_link.equals(m_link)) {
			//#ifdef DLOGGING
			if (finestLoggable) {logger.finest("unequal feed.m_link,this=" + feed.m_link + "," + m_link);}
			//#endif
			return false;
		}
		if ((feed.m_date == null) && (this.m_date == null)) {
		} else if ((feed.m_date != null) && (this.m_date != null)) {
			if (feed.m_date.equals(this.m_date)) {
			} else {
				//#ifdef DLOGGING
				if (finestLoggable) {logger.finest("unequal dates=" + feed.m_date + "," + m_date);}
				//#endif
				return false;
			}
		} else {
			//#ifdef DLOGGING
			if (finestLoggable) {logger.finest("unequal dates=" + feed.m_date + "," + m_date);}
			//#endif
			return false;
		}
		if (feed.m_category != this.m_category) {
			//#ifdef DLOGGING
			if (finestLoggable) {logger.finest("unequal feed.m_category,this=" + feed.m_category + "," + m_category);}
			//#endif
			return false;
		}
		int flen = feed.m_items.size();
		int ilen = m_items.size();
		if (flen != ilen) {
			//#ifdef DLOGGING
			if (finestLoggable) {logger.finest("unequal size feed,this=" + flen + "," + ilen);}
			//#endif
			return false;
		}
		RssItem [] ritems = new RssItem[ilen];
		m_items.copyInto(ritems);
		RssItem [] fitems = new RssItem[flen];
		feed.m_items.copyInto(fitems);
		for (int ic = 0; ic < ilen; ic++) {
			if (!fitems[ic].equals(ritems[ic])) {
				//#ifdef DLOGGING
				if (finestLoggable) {logger.finest("unequal ic,fitems[ic],ritems[ic]" + ic + "," + fitems[ic] + "," + ritems[ic]);}
				//#endif
				return false;
			}
		}
		return true;
	}
    
    /** Return RSS feed items */
    public Vector getItems() {
        return m_items;
    }
    
    /** Set items */
    public void setItems(Vector items) {
        m_items = items;
    }
    
    public void setUpddate(Date upddate) {
        this.m_upddate = upddate;
    }

    public Date getUpddate() {
        return (m_upddate);
    }

    public void setCategory(int category) {
        this.m_category = category;
    }

    public int getCategory() {
        return (m_category);
    }

    /** Write record as a string */
    public String toString(){
        StringBuffer serializedItems = new StringBuffer();
		int ilen = m_items.size();
		RssItunesItem [] ritems = new RssItunesItem[ilen];
		m_items.copyInto(ritems);
		for(int itemIndex=0; itemIndex<ilen;itemIndex++) {
			RssItunesItem rssItem = (RssItunesItem)ritems[itemIndex];
			serializedItems.append(rssItem.toString());
			serializedItems.append(".");
		}
        String dateString;
        if(m_date==null){
            dateString = "";
        } else {
		    // We use base 16 (hex) for the date so that we can save some
			// space for toString.
            dateString = Long.toString( m_date.getTime(), 16 );
        }
        String updString;
        if(m_upddate==null){
            updString = "";
        } else {
		    // We use base 16 (hex) for the update date so that we can save some
			// space for toString.
            updString = Long.toString( m_upddate.getTime(), 16 );
        }
        String storeString = m_name + "|" + m_url + "|" + m_username + "|" +
                m_password + "|" +
                updString + "|" + m_link + "|" + m_category + "|" +
                dateString + "|" + serializedItems.toString();
        return storeString;
        
    }

    public void setLink(String link) {
		//#ifdef DITUNES
		if (!link.equals(m_url)) {
			this.m_link = link;
		}
		//#endif
    }

    public String getLink() {
        return (m_link);
    }

    public void setDate(Date date) {
		//#ifdef DITUNES
        this.m_date = date;
		//#endif
    }

    public Date getDate() {
        return (m_date);
    }

}
