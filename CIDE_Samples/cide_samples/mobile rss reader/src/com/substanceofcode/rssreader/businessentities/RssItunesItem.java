/*
 * RssItunesItem.java
 *
 * Copyright (C) 2005-2006 Tommi Laukkanen
 * Copyright (C) 2007 Tommi Laukkanen
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
import java.util.Date;
import java.util.Hashtable;

import com.substanceofcode.utils.CauseException;
import com.substanceofcode.utils.CauseMemoryException;

//#ifdef DLOGGING
import net.sf.jlogmicro.util.logging.Logger;
import net.sf.jlogmicro.util.logging.Level;
//#endif

/**
 * RssItunesItem class is a data store for a single item in RSS feed.
 * One item consist of title, link, description and optional date.
 *
 * @author  Tommi Laukkanen
 * @version 1.1
 */
public class RssItunesItem extends RssItem {
    
	// Make max summary same as max description (actual max is 50K)
    public static int MAX_SUMMARY = 500;
	// Beginning of data that has 0 itunes info.
	// Number of Itunes info
    final private static int NBR_ITUNES_INFO = 6;
    public static int ITITLE_OFFSET= NBR_ITUNES_INFO + TITLE_OFFSET;
    public static int IUNREAD_ITEM = NBR_ITUNES_INFO + UNREAD_ITEM;
    public static int IDATE_OFFSET = NBR_ITUNES_INFO + DATE_OFFSET;
    public static int IDESC_OFFSET = NBR_ITUNES_INFO + DESC_OFFSET;
    final protected static byte BNO_EXPLICIT = (byte)-1;
    final public static String UNSPECIFIED = "unspecified";
    // Value that shows that the first item (and those following may
	// contain ITunes items (or all may not contain any, but they
	// can later be modified to contain them).
    final private static int INT_ITUNES_INDICATOR = NBR_ITUNES_INFO;
	//#ifdef DLOGGING
    private Logger logger = Logger.getLogger("RssItunesItem");
	//#endif
	//#ifdef DLOGGING
    private boolean fineLoggable = logger.isLoggable(Level.FINE);
    private boolean finestLoggable = logger.isLoggable(Level.FINEST);
	//#endif
    protected boolean m_itunes = false;
    protected String m_author = "";   // The RSS item author
    protected String m_subtitle = "";   // The RSS item subtitle
    protected String m_summary = "";   // The RSS item summary
	//TODO Max summary of 4K
    protected byte m_explicit = BNO_EXPLICIT;   // The RSS item explicit
    protected String m_duration  = "";   // The RSS item duration

    /** Creates a new instance of RssItunesItem.  Used by this class.  */
    public RssItunesItem() {
		super();
	}

    /** Creates a new instance of RssItunesItem
		title - title of item
		link - link of item
		desc - description of item
		pubDate - If no pubDate use null
		enclosure - enclosure of item or "" if no enclosure
		unreadItem - True if item unread, false if read
		**/
    public RssItunesItem(String title, String link, String desc, Date pubDate,
			        String enclosure, boolean unreadItem) {
		super(title, link, desc, pubDate, enclosure, unreadItem);
    }
    
    public RssItunesItem(String title, String link, String desc, Date date,
			        String enclosure, boolean unreadItem,
					boolean itunes,
					String author,
					String subtitle,
					String summary,
					byte explicit,
					String duration) {
		super(title, link, desc, date, enclosure, unreadItem);
		//#ifdef DITUNES
		m_itunes = itunes;
		if (m_itunes) {
			m_author = author;
			m_subtitle = subtitle;
			m_summary = summary;
			m_explicit = explicit;
			m_duration = duration;
		}
		//#endif
    }
    
    /** Creates a new instance of RssItunesItem */
    public RssItunesItem(RssItem item) {
		super(item);
		//#ifdef DITUNES
		if (item instanceof RssItunesItem) {
			RssItunesItem ititem = (RssItunesItem)item;
			this.m_itunes = ititem.m_itunes;
			this.m_author = ititem.m_author;
			this.m_subtitle = ititem.m_subtitle;
			this.m_summary = ititem.m_summary;
			this.m_explicit = ititem.m_explicit;
			this.m_duration = ititem.m_duration;
		}
		//#endif
    }
    
    /** Serialize the object */
    public String unencodedSerialize() {
		String author = "";
		String subtitle = "";
		String summary = "";
		//#ifdef DITUNES
		if (m_itunes) {
			author = m_author.replace('|', CONE);
			subtitle = m_subtitle.replace('|', CONE);
			summary = m_summary.replace('|', CONE);
		}
		//#endif
        String preData = (m_itunes ? "1" : "") + "|" +
			author + "|" + subtitle + "|" + summary + "|" +
                 ((m_explicit == BNO_EXPLICIT) ? "" :
						 Integer.toString((int)m_explicit)) + "|" +
				m_duration + "|" + super.unencodedSerialize();
		return preData;
	}

    public String serialize() {
        String preData = unencodedSerialize();
        Base64 b64 = new Base64();
        String encodedSerializedData = null;
		try {
			encodedSerializedData = b64.encode( preData.getBytes("UTF-8") );
		} catch (UnsupportedEncodingException e) {
			encodedSerializedData = b64.encode( preData.getBytes() );
		}
		return encodedSerializedData;
	}
		
	/** Deserialize the unencoded object */
	public static RssItem unencodedDeserialize(String data)
	throws CauseMemoryException, CauseException {
			
		try {
			boolean hasPipe = (data.indexOf(CONE) >= 0);
			String[] nodes = StringUtil.split( data, '|');
			RssItunesItem item = new RssItunesItem();
			item.init(hasPipe, nodes);
			return item;
        } catch(CauseException e) {
			throw e;
        } catch(Exception e) {
			CauseException ce = new CauseException(
					"Internal error while RssItunesItem unencodedDeserialize ", e);
			//#ifdef DLOGGING
			Logger logger = Logger.getLogger("RssItem");
			logger.severe(ce.getMessage(), e);
			//#endif
            System.err.println(ce.getMessage() + " " + e.toString());
			e.printStackTrace();
			throw ce;
        } catch(OutOfMemoryError e) {
			CauseMemoryException ce = new CauseMemoryException(
					"Out of memory error while RssItunesItem unencodedDeserialize ", e);
			//#ifdef DLOGGING
			Logger logger = Logger.getLogger("RssItunesItem");
			logger.severe(ce.getMessage(), e);
			//#endif
            System.err.println(ce.getMessage() + " " + e.toString());
			e.printStackTrace();
			throw ce;
        }
	}
			
	/** Deserialize the object */
	public static RssItem deserialize(String data)
	throws CauseMemoryException, CauseException {
		try {
			// Base64 decode
			Base64 b64 = new Base64();
			byte[] decodedData = b64.decode(data);
			try {
				data = new String( decodedData, "UTF-8" );
			} catch (UnsupportedEncodingException e) {
				data = new String( decodedData );
			}
			return unencodedDeserialize(data);
        } catch(CauseException e) {
			throw e;
        } catch(Exception e) {
			CauseException ce = new CauseException(
					"Internal error while RssItunesItem deserialize ", e);
			//#ifdef DLOGGING
			Logger logger = Logger.getLogger("RssItunesItem");
			logger.severe(ce.getMessage(), e);
			//#endif
            System.err.println(ce.getMessage() + " " + e.toString());
			e.printStackTrace();
			throw ce;
        } catch(OutOfMemoryError e) {
			CauseMemoryException ce = new CauseMemoryException(
					"Out of memory error while RssItunesItem deserialize ", e);
			//#ifdef DLOGGING
			Logger logger = Logger.getLogger("RssItunesItem");
			logger.severe(ce.getMessage(), e);
			//#endif
            System.err.println(ce.getMessage() + " " + e.toString());
			e.printStackTrace();
			throw ce;
		}
			
	}
			
	/**
	  Initialize fields in the class from data.
	  hasPipe - True if the data has a pipe in at least one item
	  nodes - (elements in an array).
	  **/
	protected void init(boolean hasPipe, String [] nodes)
	throws CauseMemoryException, CauseException {
		try {
			/* Node count should be 12:
			 * author | subtitle | category | enclosure | summary | explicit
			 * | duration | followed by RssItem fields
			 * title | link | date | enclosure | unreadItem | desc
			 */
			//#ifdef DLOGGING
			if (finestLoggable) {logger.finest("nodes.length=" + nodes.length);}
			//#endif
			//#ifdef DITUNES
			int ITUNES = 0;
			m_itunes = nodes[ITUNES].equals("1");
			
			if (m_itunes) {
				int AUTHOR = 1;
				m_author = nodes[AUTHOR];
				if (hasPipe) {
					m_author = m_author.replace(CONE, '|');
				}
				
				int SUBTITLE = 2;
				m_subtitle = nodes[SUBTITLE];
				if (hasPipe) {
					m_subtitle = m_subtitle.replace(CONE, '|');
				}
				
				int SUMMARY = 3;
				m_summary = nodes[SUMMARY];
				if (hasPipe) {
					m_summary = m_summary.replace(CONE, '|');
				}

				int EXPLICIT = 4;
				String explicit = nodes[EXPLICIT];
				if (explicit.length() > 0) {
					m_explicit = (byte)Integer.parseInt(explicit);
				}

				int DURATION = 5;
				m_duration = nodes[DURATION];
			}
			//#endif

			super.init(NBR_ITUNES_INFO, true, hasPipe, nodes);

        } catch(CauseException e) {
			throw e;
        } catch(Exception e) {
            System.err.println("Error while RssItunesItem deserialize : " + e.toString());
			e.printStackTrace();
        }
    }

    /** Write record as a string */
    public String toString(){
        String storeString = m_itunes + "|" + m_author + "|" + m_subtitle + "|" +
			m_summary + "|" + + (int)m_explicit + super.toString();
        return storeString;
    }

    public void setAuthor(String m_author) {
        this.m_author = m_author;
    }

    public String getAuthor() {
        return (m_author);
    }

    public void setSubtitle(String m_subtitle) {
        this.m_subtitle = m_subtitle;
    }

    public String getSubtitle() {
        return (m_subtitle);
    }

    public void setSummary(String m_summary) {
        this.m_summary = m_summary;
    }

    public String getSummary() {
        return (m_summary);
    }

    public void setExplicit(int explicit) {
        this.m_explicit = (byte)explicit;
    }

    public String getExplicit() {
		switch (m_explicit) {
			case (byte)0:
				return "no";
			case (byte)1:
				return "clean";
			case (byte)2:
				return "yes";
			default:
				return UNSPECIFIED;
		}
    }

    public void setDuration(String m_duration) {
        this.m_duration = m_duration;
    }

    public String getDuration() {
        return (m_duration);
    }
    
	/* Compare item. */
	public boolean equals(RssItunesItem item) {
		if (!super.equals(item)) {
			return false;
		}
		if (!item.m_author.equals(m_author)) {
			//#ifdef DLOGGING
			if (finestLoggable) {logger.finest("unequal item.m_author,this=" + item.m_author + "," + m_author);}
			//#endif
			return false;
		}
		if (!item.m_subtitle.equals(m_subtitle)) {
			//#ifdef DLOGGING
			if (finestLoggable) {logger.finest("unequal item.m_subtitle,this=" + item.m_subtitle + "," + m_subtitle);}
			//#endif
			return false;
		}
		if (!item.m_summary.equals(m_summary)) {
			//#ifdef DLOGGING
			if (finestLoggable) {logger.finest("unequal item.m_summary,this=" + item.m_summary + "," + m_summary);}
			//#endif
			return false;
		}

		if (item.m_explicit != m_explicit) {
			//#ifdef DLOGGING
			if (finestLoggable) {logger.finest("unequal item.m_explicit,this=" + item.m_explicit + "," + m_explicit);}
			//#endif
			return false;
		}

		if (!item.m_duration.equals(m_duration)) {
			//#ifdef DLOGGING
			if (finestLoggable) {logger.finest("unequal item.m_duration,this=" + item.m_duration + "," + m_duration);}
			//#endif
			return false;
		}
		return true;
	}

    public void setItunes(boolean itunes) {
		//#ifdef DITUNES
        this.m_itunes = itunes;
		//#else
        this.m_itunes = false;
		//#endif
    }

    public boolean isItunes() {
		//#ifdef DITUNES
        return false?false:(m_itunes);
		//#else
//@        return (false);
		//#endif
    }

}
