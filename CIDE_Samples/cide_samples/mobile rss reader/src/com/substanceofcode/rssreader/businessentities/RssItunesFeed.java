/*
 * RssItunesFeed.java
 *
 * Copyright (C) 2007-2008 Tommi Laukkanen
 * Copyright (C) 2007-2008 Irving Bunton
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
// Expand to define logging define
//#define DNOLOGGING
package com.substanceofcode.rssreader.businessentities;

import com.substanceofcode.utils.Base64;
import com.substanceofcode.utils.StringUtil;
import java.io.UnsupportedEncodingException;
import java.util.*;

import com.substanceofcode.rssreader.presentation.RssReaderMIDlet;
import com.substanceofcode.utils.CauseException;
import com.substanceofcode.utils.CauseMemoryException;

//#ifdef DLOGGING
import net.sf.jlogmicro.util.logging.Logger;
import net.sf.jlogmicro.util.logging.Level;
//#endif

/**
 * RssItunesFeed class contains one RSS Itunes feed's properties.
 * Properties include name and subtitle and summary.
 *
 * @author Irving Bunton
 */
public class RssItunesFeed extends RssFeed{
    
	// Make max summary same as max description (actual max is 50K)
    public static int MAX_SUMMARY = 500;
	// Beginning of data that has 0 itunes info.
	// Number of Itunes info
    private static int NBR_ITUNES_FEED_INFO = 8;
    public static int INAME_OFFSET= NBR_ITUNES_FEED_INFO + NAME_OFFSET;
    public static int IDATE_OFFSET = NBR_ITUNES_FEED_INFO + DATE_OFFSET;
    private static String EMPTY_ITUNES_FEED_INFO = "|||||||";
	//#ifdef DLOGGING
    private Logger logger = Logger.getLogger("RssItunesFeed");
	//#endif
	//#ifdef DLOGGING
    private boolean fineLoggable = logger.isLoggable(Level.FINE);
    private boolean finestLoggable = logger.isLoggable(Level.FINEST);
	//#endif
    // Value that shows that the first item (and those following may
	// contain ITunes items (or all may not contain any, but they
	// can later be modified to contain them).
    private static int INT_ITUNES_INDICATOR = NBR_ITUNES_FEED_INFO;
    protected boolean m_itunes = false;
    protected String m_title = "";
    protected String m_description = "";
    protected String m_language = "";   // The RSS feed language
    protected String m_author = "";   // The RSS feed author
    protected String m_subtitle = "";   // The RSS feed subtitle
    protected String m_summary = "";   // The RSS feed summary
    protected byte m_explicit = RssItunesItem.BNO_EXPLICIT; // The RSS feed explicit
    
    /** Creates a new instance of RSSBookmark */
    public RssItunesFeed(){
		super();
	}

    /** Creates a new instance of RSSBookmark */
    public RssItunesFeed(String name, String url, String username, String password){
        super(name, url, username, password);
    }
    
    /** Creates a new instance of RSSBookmark */
    public RssItunesFeed(String name, String url, String username,
						String password,
						Date upddate,
						String link,
						Date date,
						int category,
						boolean itunes,
						String title,
						String description,
						String language,
						String author,
						String subtitle,
						String summary,
						byte explicit) {
        super(name, url, username, password, upddate, link, date, category);
		if (itunes) {
			modifyItunes(itunes, title, description, language, author, subtitle,
					summary, explicit);
		}
	}
    
	/** Modify fields for Itunes. */
	public void modifyItunes(boolean itunes, String title, String description,
							String language,
							String author,
							String subtitle,
							String summary,
							byte explicit) {
		//#ifdef DITUNES
		this.m_itunes = itunes;
		this.m_title = title;
		this.m_description = description;
		this.m_language = language;
		this.m_author = author;
		this.m_subtitle = subtitle;
		this.m_summary = summary;
		this.m_explicit = explicit;
		//#endif
	}

	/** Create feed from an existing feed.  **/
	public RssItunesFeed(RssFeed feed) {
		super(feed);
		try {
        
			if (feed instanceof RssItunesFeed) {
				RssItunesFeed itfeed = (RssItunesFeed)feed;
				this.m_itunes = itfeed.m_itunes;
				if (this.m_itunes) {
					this.m_language = itfeed.m_language;
					this.m_author = itfeed.m_author;
					this.m_subtitle = itfeed.m_subtitle;
					this.m_summary = itfeed.m_summary;
					this.m_explicit = itfeed.m_explicit;
				}
			} else {
				final Vector cvitems = feed.getItems();
				if (cvitems.size() > 0) {
					RssItem[] citems = new RssItem[cvitems.size()];
					cvitems.copyInto(citems);
					Vector nvitems = new Vector();
					for (int ic = 0; ic < citems.length; ic++) {
						final RssItem item = citems[ic];
						if (item instanceof RssItunesItem) {
							nvitems.addElement((RssItunesItem)item);
						} else {
							nvitems.addElement(new RssItunesItem(item));
						}
					}
					m_items = nvitems;
				}
			}
        } catch(Throwable e) {
            System.err.println("RssItunesFeed contructor : " + e.toString());
			e.printStackTrace();
        }
	}
    
	/** Deserialize the object
        Creates a new instance of RssItunesFeed from store string 
		**/
	public static RssItunesFeed deserialize(boolean encoded,
			String storeString)
	throws CauseMemoryException, CauseException {

		try {
        
			boolean hasPipe = (storeString.indexOf(CONE) >= 0);
			String[] nodes = StringUtil.split( storeString, '|' );
			RssItunesFeed feed = new RssItunesFeed();
			feed.init(hasPipe, encoded, nodes);
			return feed;
		} catch (CauseMemoryException e) {
			throw e;
		} catch (CauseException e) {
			throw e;
        } catch(Exception e) {
			CauseException ce = new CauseException(
					"Internal error during deserialize", e);
			//#ifdef DLOGGING
			Logger logger = Logger.getLogger("RssItunesFeed");
			logger.severe(ce.getMessage(), e);
			//#endif
            System.err.println(ce.getMessage() + " " + e.toString());
			e.printStackTrace();
			throw ce;
        } catch(OutOfMemoryError e) {
			CauseMemoryException ce = new CauseMemoryException(
					"Out of memory error during deserialize", e);
			//#ifdef DLOGGING
			Logger logger = Logger.getLogger("RssItunesFeed");
			logger.severe(ce.getMessage(), e);
			//#endif
            System.err.println(ce.getMessage() + " " + e.toString());
			e.printStackTrace();
			throw ce;
        } catch(Throwable e) {
			CauseException ce = new CauseException(
					"Internal error during deserialize", e);
			//#ifdef DLOGGING
			Logger logger = Logger.getLogger("RssItunesFeed");
			logger.severe(ce.getMessage(), e);
			//#endif
            System.err.println(ce.getMessage() + " " + e.toString());
			e.printStackTrace();
			throw ce;
        }
	}
			
	public void init(boolean hasPipe, boolean encoded, String [] nodes)
	throws CauseMemoryException, CauseException {

		try {
        
			/* Node count should be 6
			 * m_itunes | m_title | m_description | m_language | m_author |
			   m_subtitle | m_summary | m_explicit | rss feed fields
			 */
			//#ifdef DLOGGING
			if (finestLoggable) {logger.finest("nodes.length=" + nodes.length);}
			//#endif
			//#ifdef DITUNES
			int ITUNES = 0;
			m_itunes = nodes[ITUNES].equals("1");
			
			if (m_itunes) {
				int TITLE = 1;
				m_title = nodes[TITLE];
				if (hasPipe) {
					m_title = m_title.replace(CONE, '|');
				}
				
				int DESCRIPTION = 2;
				m_description = nodes[DESCRIPTION];
				if (hasPipe) {
					m_description = m_description.replace(CONE, '|');
				}
				
				int LANGUAGE = 3;
				m_language = nodes[LANGUAGE];
				
				int AUTHOR = 4;
				m_author = nodes[AUTHOR];
				if (hasPipe) {
					m_author = m_author.replace(CONE, '|');
				}
				
				int SUBTITLE = 5;
				m_subtitle = nodes[SUBTITLE];
				if (hasPipe) {
					m_subtitle = m_subtitle.replace(CONE, '|');
				}
				
				int SUMMARY = 6;
				m_summary = nodes[SUMMARY];
				if (hasPipe) {
					m_summary = m_summary.replace(CONE, '|');
				}

				int EXPLICIT = 7;
				final String explicit = nodes[EXPLICIT];
				if (explicit.length() > 0) {
					m_explicit = (byte)Integer.parseInt(explicit);
				} else {
					m_explicit = RssItunesItem.BNO_EXPLICIT;
				}
			}
			//#endif

			super.init(false, NBR_ITUNES_FEED_INFO, true,
					   hasPipe, encoded, nodes);

		} catch (CauseMemoryException e) {
			throw e;
		} catch (CauseException e) {
			throw e;
        } catch(Exception e) {
			CauseException ce = new CauseException(
					"Internal error during initialize of RssItunesFeed", e);
			//#ifdef DLOGGING
			logger.severe(ce.getMessage(), e);
			//#endif
            System.err.println(ce.getMessage() + " " + e.toString());
			e.printStackTrace();
			throw ce;
        } catch(OutOfMemoryError e) {
			CauseMemoryException ce = new CauseMemoryException(
					"Out of memory error during initialize of RssItunesFeed", e);
			//#ifdef DLOGGING
			logger.severe(ce.getMessage(), e);
			//#endif
            System.err.println(ce.getMessage() + " " + e.toString());
			e.printStackTrace();
			throw ce;
        } catch(Throwable e) {
			CauseException ce = new CauseException(
					"Internal error during initialize of RssItunesFeed", e);
			//#ifdef DLOGGING
			logger.severe(ce.getMessage(), e);
			//#endif
            System.err.println(ce.getMessage() + " " + e.toString());
			e.printStackTrace();
			throw ce;
        }
    }
    
    /** Return record store string */
    public String getStoreString(final boolean saveHdr,
			final boolean serializeItems, final boolean encoded){
		//#ifdef DLOGGING
		if (finestLoggable) {logger.finest("saveHdr,serializeItems,encoded=" + saveHdr + "," + serializeItems + "," + encoded);}
		//#endif

		String itunesInfo;
//		//#ifdef DITUNES
//		if (saveHdr && m_itunes) {
//			String title = "";
//			String description = "";
//			String author = "";
//			String subtitle = "";
//			String summary = "";
//			if (m_itunes) {
//				title = m_title.replace('|', CONE);
//				description = m_description.replace('|', CONE);
//				author = m_author.replace('|', CONE);
//				subtitle = m_subtitle.replace('|', CONE);
//				summary = m_summary.replace('|', CONE);
//			}
//			itunesInfo = (m_itunes ? "1" : "") + "|" + title + "|" +
//			description + "|" + m_language + "|" +
//                author + "|" + subtitle + "|" + summary + "|" +
//                 ((m_explicit == RssItunesItem.BNO_EXPLICIT) ? "" :
//						 Integer.toString((int)m_explicit));
//		} else {
//		//#endif
//			itunesInfo = EMPTY_ITUNES_FEED_INFO;
//		//#ifdef DITUNES
//		}
//		//#endif
		if (!(saveHdr && m_itunes)) 
			itunesInfo = EMPTY_ITUNES_FEED_INFO;
		else {
			String title = "";
			String description = "";
			String author = "";
			String subtitle = "";
			String summary = "";
			if (m_itunes) {
				title = m_title.replace('|', CONE);
				description = m_description.replace('|', CONE);
				author = m_author.replace('|', CONE);
				subtitle = m_subtitle.replace('|', CONE);
				summary = m_summary.replace('|', CONE);
			}
			itunesInfo = (m_itunes ? "1" : "") + "|" + title + "|" +
			description + "|" + m_language + "|" +
                author + "|" + subtitle + "|" + summary + "|" +
                 ((m_explicit == RssItunesItem.BNO_EXPLICIT) ? "" :
						 Integer.toString((int)m_explicit));	
		}
		
        String storeString = itunesInfo + "|" +
			super.getStoreString(saveHdr, serializeItems, encoded);
        return storeString;
        
    }

    /** Return record store string info */
    final public static RssStoreInfo getStoreStringInfo(
			final boolean serializeItems, final boolean encoded,
			final String storeString,
			boolean sencoded){
		// TODO handle serialize items
		//#ifdef DLOGGING
		Logger logger = Logger.getLogger("RssItunesFeed");
		logger.finest("serializeItems,encoded,sencoded=" + serializeItems + "," + encoded + "," + sencoded);
		//#endif
		//#ifdef DTEST
		long encodeTime = 0L;
		long splitTime = 0L;
		long joinTime = 0L;
		long lngStart = System.currentTimeMillis();
		//#endif
		String[] nodes = StringUtil.split( storeString, '|' );
		//#ifdef DTEST
		splitTime += System.currentTimeMillis() - lngStart;
		//#endif
		final String name = nodes[NBR_ITUNES_FEED_INFO];
		if (encoded == sencoded) {
			//#ifdef DTEST
			if (true) 
				return new RssStoreInfo(name, storeString, encodeTime, splitTime,
					joinTime);
			//#else
			return new RssStoreInfo(name, storeString);
			//#endif
		}
		final int itemsOff = NBR_ITUNES_FEED_INFO + RssFeed.ITUNES_ITEMS;
		if (itemsOff >= nodes.length) {
			//#ifdef DTEST
			if (true) return new RssStoreInfo(name, storeString, encodeTime, splitTime,
					joinTime);
			//#else
			return new RssStoreInfo(name, storeString);
			//#endif
		}
		if (!sencoded) {
			nodes[itemsOff] = nodes[itemsOff].replace(CTHREE, '|');
		}
		//#ifdef DTEST
		lngStart = System.currentTimeMillis();
		//#endif
		final String[] itemArrayData = StringUtil.split(nodes[itemsOff],
				(sencoded ? '.' : RssFeed.CTWO));
		//#ifdef DTEST
		splitTime += System.currentTimeMillis() - lngStart;
		//#endif
		final String[] nitemArrayData = new String[itemArrayData.length];
		if (sencoded) {
			for (int ic = 0; ic < itemArrayData.length; ic++) {
				// Base64 decode
				Base64 b64 = new Base64();
				byte[] decodedData = b64.decode(itemArrayData[ic]);
				try {
					nitemArrayData[ic] = new String( decodedData, "UTF-8" );
				} catch (UnsupportedEncodingException e) {
					nitemArrayData[ic] = new String( decodedData );
				}
			}
			nodes[itemsOff] = StringUtil.join(nitemArrayData, RssFeed.CTWO, 0);
			nodes[itemsOff] = nodes[itemsOff].replace('|', RssFeed.CTHREE);
		} else {
			//#ifdef DTEST
			lngStart = System.currentTimeMillis();
			//#endif
			for (int ic = 0; ic < itemArrayData.length; ic++) {
				// Base64 decode
				Base64 b64 = new Base64();
				String data;
				try {
					nitemArrayData[ic] = b64.encode(
							itemArrayData[ic].getBytes("UTF-8") );
				} catch (UnsupportedEncodingException e) {
					nitemArrayData[ic] = b64.encode(
							itemArrayData[ic].getBytes() );
				}
			}
			//#ifdef DTEST
			encodeTime += System.currentTimeMillis() - lngStart;
			lngStart = System.currentTimeMillis();
			//#endif
			nodes[itemsOff] = StringUtil.join(nitemArrayData, '.', 0);
			//#ifdef DTEST
			joinTime += System.currentTimeMillis() - lngStart;
			//#endif
		}
		//#ifdef DTEST
		RssStoreInfo rsi;
		rsi = new RssStoreInfo(name, StringUtil.join(
					nodes, '|', 0), encodeTime, splitTime, joinTime);
		//#else
		rsi = new RssStoreInfo(name, StringUtil.join(
					nodes, '|', 0));
		//#endif
		return rsi;
	}

    /** Return record store string info */
    final public static RssShortItem[] getShortItems(
			final RssReaderMIDlet midlet, final String storeString) {
		//#ifdef DLOGGING
		Logger logger = Logger.getLogger("RssItunesFeed");
		logger.finest("storeString=" + storeString);
		//#endif
		String[] nodes = StringUtil.split( storeString, '|' );
		final int itemsOff = NBR_ITUNES_FEED_INFO + RssFeed.ITUNES_ITEMS;
		if ((itemsOff >= nodes.length) || (nodes[itemsOff].length() == 0)) {
			return new RssShortItem[0];
		}
		final String[] itemArrayData = StringUtil.split(nodes[itemsOff],
				RssFeed.CTWO);
		final RssShortItem[] sitems = new RssShortItem[itemArrayData.length - 1];
		//#ifdef DITUNES
		String sfdate = null;
		Date fdate = null;
		//#endif
		for (int ic = 0; ic < sitems.length; ic++) {
			String[] sparts = StringUtil.split(itemArrayData[ic], CTHREE);
			String title = sparts[RssItunesItem.ITITLE_OFFSET].replace(
					RssItem.CONE, '|');
			if (title.length() == 0) {
				title = midlet.getItemDescription(
						sparts[RssItunesItem.IDESC_OFFSET].replace(
							RssItem.CONE, '|'));
			}

			final String sunreadItem = sparts[RssItunesItem.IUNREAD_ITEM];
			boolean unreadItem = false;
			if (sunreadItem.equals("1")) {
				unreadItem = true;
			}
			String sdate = sparts[RssItunesItem.IDATE_OFFSET];
			Date date = null;
			if (sdate.length() > 0) {
				date = new Date(Long.parseLong(sdate, 16));
				//#ifdef DITUNES
			} else {
				if (sfdate == null) {
					sfdate = nodes[IDATE_OFFSET];
					if (sfdate.length() > 0) {
						fdate = new Date(Long.parseLong(sfdate, 16));
					}
				}
				if (fdate != null) {
					date = fdate;
				}
				//#endif
			}
			sitems[ic] = new RssShortItem(title, date, unreadItem, ic);
		}
		return sitems;
	}

	/** Copy feed to an existing feed.  **/
	public void copyTo(RssItunesFeed toFeed) {
		super.copyTo(toFeed);
		//#ifdef DITUNES
		toFeed.m_title = this.m_title;
		toFeed.m_description = this.m_description;
		toFeed.m_language = this.m_language;
		toFeed.m_author = this.m_author;
		toFeed.m_subtitle = this.m_subtitle;
		toFeed.m_summary = this.m_summary;
		toFeed.m_explicit = this.m_explicit;
		//#endif
	}
    
	/** Compare feed to an existing feed.  **/
	public boolean equals(RssItunesFeed feed) {
		if (!super.equals(feed)) {
			return false;
		}
		if (feed.m_itunes != m_itunes) {
			//#ifdef DLOGGING
			if (finestLoggable) {logger.finest("unequal feed.m_itunes,this=" + feed.m_itunes + "," + m_itunes);}
			//#endif
			return false;
		}
		if (!feed.m_language.equals(this.m_language)) {
			//#ifdef DLOGGING
			if (finestLoggable) {logger.finest("unequal feed.m_language,this=" + feed.m_language + "," + m_language);}
			//#endif
			return false;
		}
		if (!feed.m_author.equals(this.m_author)) {
			//#ifdef DLOGGING
			if (finestLoggable) {logger.finest("unequal feed.m_author,this=" + feed.m_author + "," + m_author);}
			//#endif
			return false;
		}
		if (!feed.m_summary.equals(this.m_summary)) {
			//#ifdef DLOGGING
			if (finestLoggable) {logger.finest("unequal feed.m_summary,this=" + feed.m_summary + "," + m_summary);}
			//#endif
			return false;
		}
		if (feed.m_explicit != m_explicit) {
			//#ifdef DLOGGING
			if (finestLoggable) {logger.finest("unequal feed.m_explicit,this=" + feed.m_explicit + "," + m_explicit);}
			//#endif
			return false;
		}
		return true;
	}
    
    public void setCategory(int category) {
        this.m_category = category;
    }

    public int getCategory() {
        return (m_category);
    }

    /** Write record as a string */
    public String toString(){
        String storeString = m_itunes + "|" + m_title + "|" +
			m_description + "|" + m_language + "|" +
			m_author + "|" + m_subtitle + "|" + m_summary + "|" +
                 ((m_explicit == RssItunesItem.BNO_EXPLICIT) ? "" :
						 Integer.toString((int)m_explicit)) + "|" +
				 super.toString();
        return storeString;
        
    }

    public void setDescription(String description) {
        this.m_description = description;
    }

    public String getDescription() {
        return (m_description);
    }

    public void setLanguage(String language) {
        this.m_language = language;
    }

    public String getLanguage() {
        return (m_language);
    }

    public void setAuthor(String author) {
        this.m_author = author;
    }

    public String getAuthor() {
        return (m_author);
    }

    public void setSubtitle(String subtitle) {
        this.m_subtitle = subtitle;
    }

    public String getSubtitle() {
        return (m_subtitle);
    }

    public void setSummary(String summary) {
        this.m_summary = summary;
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
				return RssItunesItem.UNSPECIFIED;
		}
    }

    public void setItunes(boolean itunes) {
        this.m_itunes = itunes;
    }

    public boolean isItunes() {
		// If itunes, allow it.  If not itunes, make it seem that it is not.
		//#ifdef DITUNES
        return false?false:(m_itunes);
		//#else
//@        return (false);
		//#endif
    }

    public void setTitle(String title) {
        this.m_title = title;
    }

    public String getTitle() {
        return (m_title);
    }

}
