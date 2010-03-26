/*
 * RssFeedParser.java
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

// Expand to define test define
//#define DNOTEST
// Expand to define logging define
//#define DNOLOGGING
package com.substanceofcode.rssreader.businesslogic;

import com.substanceofcode.rssreader.businessentities.RssItunesFeed;
import com.substanceofcode.utils.StringUtil;
import com.substanceofcode.utils.XmlParser;
import javax.microedition.io.*;
import java.util.*;
import java.io.*;

import com.substanceofcode.utils.EncodingUtil;
import com.substanceofcode.utils.CauseException;
import com.substanceofcode.utils.CauseMemoryException;
//#ifdef DLOGGING
import net.sf.jlogmicro.util.logging.Logger;
import net.sf.jlogmicro.util.logging.LogManager;
import net.sf.jlogmicro.util.logging.Level;
//#endif

/**
 * RssFeedParser is an utility class for aquiring and parsing a RSS feed.
 * HttpConnection is used to fetch RSS feed and kXML is used on xml parsing.
 *
 * @author  Tommi Laukkanen
 * @version 1.0
 */
public class RssFeedParser extends URLHandler {
    
    private RssItunesFeed m_rssFeed;  // The RSS feed
    private boolean m_getTitleOnly = false;  // The RSS feed
	//#ifdef DLOGGING
    private Logger logger = Logger.getLogger("RssFeedParser");
    private boolean fineLoggable = logger.isLoggable(Level.FINE);
    private boolean finerLoggable = logger.isLoggable(Level.FINER);
    private boolean finestLoggable = logger.isLoggable(Level.FINEST);
	//#endif
    
    /** Create new instance of RssFeedParser */
    public RssFeedParser(RssItunesFeed rssFeed) {
        m_rssFeed = rssFeed;
    }
    
    /** Return RSS feed */
    public RssItunesFeed getRssFeed() {
        return m_rssFeed;
    }
    
    /**
     * Send a GET request to web server and parse feeds from response.
     *
     * @input updFeed Do updated feeds only.
     * @input convXmlEnts Convert XML entities
     * @input maxItemCount Maximum item count for the feed.
     *
     */
    public void parseRssFeed(final boolean updFeed,
			final boolean convXmlEnts, final int maxItemCount)
	throws IOException, CauseException, Exception {
		// Set this here as the instance of this class is reused
		// for update of the current feed.
		m_redirect = false;
		parseRssFeedUrl(m_rssFeed.getUrl(), updFeed, convXmlEnts, maxItemCount);
	}
        
    /**
     * Send a GET request to web server and parse feeds from response.
     *
     * @input url to parse
     * @input updFeed Do updated feeds only.
     * @input convXmlEnts Convert XML entities
     * @input maxItemCount Maximum item count for the feed.
     *
     */
    public void parseRssFeedUrl(final String url, final boolean updFeed,
			final boolean convXmlEnts, final int maxItemCount)
	throws IOException, CauseException, Exception {
        
		try {
			super.handleOpen(url, m_rssFeed.getUsername(),
					  m_rssFeed.getPassword());
			if (m_needRedirect) {
				m_needRedirect = false;
				parseHeaderRedirect(updFeed, m_location, convXmlEnts,
						maxItemCount);
				return;
			}
			// If we find HTML, usually it is redirection
			if ((m_contentType != null) && (m_contentType.indexOf("html") >= 0)) {
				parseHTMLRedirect(updFeed, url, m_inputStream,
								  convXmlEnts, maxItemCount);
			} else {
				if (m_lastMod == 0L) {
					m_rssFeed.setUpddate(null);
				} else {
					// If we're only processing if the feed is updated,
					// check if we previously had a update value.
					// If so and it does equals the new one, return
					if (updFeed) {
						Date updDate = m_rssFeed.getUpddate();
	  					if ((updDate != null) && updDate.equals(new
							Date(m_lastMod))) {
							return;
						}
					}
 				}
				parseRssFeedXml( m_inputStream, convXmlEnts, maxItemCount);
				m_rssFeed.setUpddate(new Date(m_lastMod));
			}
		} catch (CauseMemoryException e) {
			if (m_rssFeed != null) {
				m_rssFeed.setItems(null);
				m_rssFeed.setItems(new Vector());
			}
            CauseMemoryException ce =
					new CauseMemoryException("Out of memory error while " +
					"parsing RSS data: " + e.toString(), e);
			//#ifdef DLOGGING
			logger.severe(ce.getMessage(), e);
			//#endif
			if ((url != null) && url.startsWith("file://")) {
				System.err.println("Cannot process file.");
			}
            throw ce;
        } catch(Exception e) {
			//#ifdef DLOGGING
			logger.severe("parseRssFeedUrl error with " + url, e);
			//#endif
			if ((url != null) && url.startsWith("file://")) {
				System.err.println("Cannot process file.");
			}
            throw new CauseException("Error while parsing RSS data: " 
                    + e.toString(), e);
		} catch (OutOfMemoryError e) {
			if (m_rssFeed != null) {
				m_rssFeed.setItems(null);
				m_rssFeed.setItems(new Vector());
			}
            CauseMemoryException ce =
					new CauseMemoryException("Out of memory error while " +
					"parsing RSS data: " + e.toString(), e);
			//#ifdef DLOGGING
			logger.severe(ce.getMessage(), e);
			//#endif
			if ((url != null) && url.startsWith("file://")) {
				System.err.println("Cannot process file.");
			}
            throw ce;
        } catch(Throwable t) {
			//#ifdef DLOGGING
			logger.severe("parseRssFeedUrl error with " + url, t);
			//#endif
			if ((url != null) && url.startsWith("file://")) {
				System.err.println("Cannot process file.");
			}
            throw new CauseException("Error while parsing RSS data: " 
								+ t.toString(), t);
        } finally {
			super.handleClose();
		}
    }
    
	/** Read HTML and if it has links, redirect and parse the XML. */
	private void parseHeaderRedirect(final boolean updFeed, String url,
								     final boolean convXmlEnts,
									 final int maxItemCount)
	throws IOException, CauseException, Exception {
		if (m_redirect) {
			//#ifdef DLOGGING
			logger.severe("Error 2nd redirect url:  " + url);
			//#endif
			System.out.println("Error 2nd redirect url:  " + url);
			throw new IOException("Error url " + m_redirectUrl +
					" to 2nd redirect url:  " + url);
		}
		m_redirect = true;
		m_redirectUrl = url;
		parseRssFeedUrl(url, updFeed, convXmlEnts, maxItemCount);
		return;

	}

	/** Read HTML and if it has links, redirect and parse the XML. */
	private void parseHTMLRedirect(boolean updFeed, String url,
								   InputStream is, final boolean convXmlEnts,
								   final int maxItemCount)
	throws IOException, CauseException, Exception {
		String newUrl = super.parseHTMLRedirect(url, is);
		RssItunesFeed svFeed = new RssItunesFeed(m_rssFeed);
		parseRssFeedUrl(newUrl, updFeed, convXmlEnts, maxItemCount);
	}

    /**
     * Nasty RSS feed XML parser.
     * Seems to work with all RSS 0.91, 0.92 and 2.0.
     */
    public void parseRssFeedXml(InputStream is, final boolean convXmlEnts,
			final int maxItemCount)
	throws IOException, CauseException {
        /** Initialize item collection */
        m_rssFeed.getItems().removeAllElements();
        
        /** Initialize XML parser and parse feed */
        XmlParser parser = new XmlParser(is);
        
        /** <?xml...*/
        int parsingResult = parser.parse();
		/** if prologue was found, parse after prologue.  **/
		if (parsingResult == XmlParser.PROLOGUE) {
			parser.parse();
		}
        
        FeedFormatParser formatParser = null;
        String entryElementName = parser.getName();
        if(entryElementName.equals("rss") || 
           entryElementName.equals("rdf")) {
            /** Feed is in RSS format */
            formatParser = new RssFormatParser();
            m_rssFeed = formatParser.parse( parser, m_rssFeed,
					convXmlEnts, maxItemCount, m_getTitleOnly );
            
        } else if(entryElementName.equals("feed")) {
            /** Feed is in Atom format */
            formatParser = new AtomFormatParser();
            m_rssFeed = formatParser.parse( parser, m_rssFeed,
					convXmlEnts, maxItemCount, m_getTitleOnly );
            
        } else {
			//#ifdef DLOGGING
			logger.severe("Unable to parse feed type:  " + entryElementName);
			//#endif
            /** Unknown feed */
            throw new IOException("Unable to parse feed. Feed format is not supported.");
            
        }
        
    }
    
    public void setGetTitleOnly(boolean m_getTitleOnly) {
        this.m_getTitleOnly = m_getTitleOnly;
    }

    public boolean isGetTitleOnly() {
        return (m_getTitleOnly);
    }

}
