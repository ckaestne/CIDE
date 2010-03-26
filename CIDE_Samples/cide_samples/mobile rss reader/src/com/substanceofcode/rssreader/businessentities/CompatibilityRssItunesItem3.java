/*
 * CompatibilityRssItunesItem3.java
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
//DLOGDEF@
// Expand to define itunes define
//DITUNESDEF@
package com.substanceofcode.rssreader.businessentities;

import com.substanceofcode.utils.CompatibilityBase64;
import com.substanceofcode.utils.StringUtil;
import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.Hashtable;
//#ifdef DLOGGING
import net.sf.jlogmicro.util.logging.Logger;
import net.sf.jlogmicro.util.logging.Level;
//#endif

/**
 * CompatibilityRssItunesItem3 class is a data store for a single item in RSS feed.
 * One item consist of title, link, description and optional date.
 *
 * @author  Tommi Laukkanen
 * @version 1.1
 */
public class CompatibilityRssItunesItem3 extends CompatibilityRssItem3 {
    
	// Make max summary same as max description (actual max is 50K)
    public static int MAX_SUMMARY = 500;
	// Beginning of data that has 0 itunes info.
	// Number of Itunes info
    final private static int NBR_ITUNES_INFO = 6;
    final protected static byte BNO_EXPLICIT = (byte)-1;
    final private static byte[] BANO_EXPLICIT = {BNO_EXPLICIT};
    final private static String NO_EXPLICIT = new String(BANO_EXPLICIT);
    final public static String UNSPECIFIED = "unspecified";
    // Value that shows that the first item (and those following may
	// contain ITunes items (or all may not contain any, but they
	// can later be modified to contain them).
    final private static int INT_ITUNES_INDICATOR = NBR_ITUNES_INFO;
	//#ifdef DLOGGING
    private Logger logger = Logger.getLogger("CompatibilityRssItunesItem3");
	//#endif
	//#ifdef DLOGGING
    private boolean fineLoggable = logger.isLoggable(Level.FINE);
    private boolean finestLoggable = logger.isLoggable(Level.FINEST);
	//#endif

    /** Creates a new instance of CompatibilityRssItunesItem3.  Used by this class.  */
    public CompatibilityRssItunesItem3() {
		super();
	}

    /** Creates a new instance of CompatibilityRssItunesItem3
		title - title of item
		link - link of item
		desc - description of item
		pubDate - If no pubDate use null
		enclosure - enclosure of item or "" if no enclosure
		unreadItem - True if item unread, false if read
		**/
    public CompatibilityRssItunesItem3(String title, String link, String desc, Date pubDate,
			        String enclosure, boolean unreadItem) {
		super(title, link, desc, pubDate, enclosure, unreadItem);
    }
    
    public CompatibilityRssItunesItem3(String title, String link, String desc, Date date,
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
    
    /** Creates a new instance of CompatibilityRssItunesItem3 */
    public CompatibilityRssItunesItem3(RssItunesItem item) {
		super();
		CompatibilityRssItunesItem3 nitem =
			new CompatibilityRssItunesItem3(item);
		nitem.copyTo(this);
    }
    
	/** Modify the Itunes fields from another item. */
	public void modifyItunes(RssItunesItem ititem) {
		//#ifdef DITUNES
		this.m_itunes = ititem.m_itunes;
		this.m_author = ititem.m_author;
		this.m_subtitle = ititem.m_subtitle;
		this.m_summary = ititem.m_summary;
		this.m_explicit = ititem.m_explicit;
		this.m_duration = ititem.m_duration;
		//#endif
	}

	/** Copy feed to an existing RssItunesItem.  **/
	public CompatibilityRssItunesItem3 copyTo(
			CompatibilityRssItunesItem3 toItem) {
		super.copyTo(toItem);
		//#ifdef DITUNES
		toItem.modifyItunes(this);
		//#endif
		return toItem;
	}
    
    /** Serialize the object */
    public String unencodedSerialize3() {
		String author = "";
		String subtitle = "";
		String summary = "";
		//#ifdef DITUNES
		if (m_itunes) {
			author = m_author.replace('|', (char)1);
			subtitle = m_subtitle.replace('|', (char)1);
			summary = m_summary.replace('|', (char)1);
		}
		//#endif
        String preData = (m_itunes ? "1" : "") + "|" +
			author + "|" + subtitle + "|" + summary + "|" +
                 ((m_explicit == BNO_EXPLICIT) ? "" :
						 Integer.toString((int)m_explicit)) + "|" +
				m_duration + "|" + super.unencodedSerialize3();
		return preData;
	}

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
		
	/** Deserialize the unencoded object */
	public static CompatibilityRssItem3 unencodedDeserialize3(String data) {
			
		try {
			boolean hasPipe = (data.indexOf((char)1) >= 0);
			String[] nodes = StringUtil.split( data, "|");
			CompatibilityRssItunesItem3 item = new CompatibilityRssItunesItem3();
			item.init(hasPipe, nodes);
			return item;
        } catch(Exception e) {
            System.err.println("Error while CompatibilityRssItunesItem3 deserialize : " + e.toString());
			e.printStackTrace();
			return null;
        }
	}
			
	/** Deserialize the object */
	public static CompatibilityRssItem3 deserialize3(String data) {
		try {
			// CompatibilityBase64 decode
			CompatibilityBase64 b64 = new CompatibilityBase64();
			byte[] decodedData = b64.decode(data);
			try {
				data = new String( decodedData, "UTF-8" );
			} catch (UnsupportedEncodingException e) {
				data = new String( decodedData );
			}
			return unencodedDeserialize3(data);
        } catch(Exception e) {
            System.err.println("Error while CompatibilityRssItunesItem3 deserialize : " + e.toString());
			e.printStackTrace();
			return null;
		}
			
	}
			
	/**
	  Initialize fields in the class from data.
	  hasPipe - True if the data has a pipe in at least one item
	  nodes - (elements in an array).
	  **/
	protected void init(boolean hasPipe, String [] nodes) {
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
					m_author = m_author.replace((char)1, '|');
				}
				
				int SUBTITLE = 2;
				m_subtitle = nodes[SUBTITLE];
				if (hasPipe) {
					m_subtitle = m_subtitle.replace((char)1, '|');
				}
				
				int SUMMARY = 3;
				m_summary = nodes[SUMMARY];
				if (hasPipe) {
					m_summary = m_summary.replace((char)1, '|');
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

        } catch(Exception e) {
            System.err.println("Error while CompatibilityRssItunesItem3 deserialize : " + e.toString());
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
	public boolean equals(CompatibilityRssItunesItem3 item) {
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
