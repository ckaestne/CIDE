/*
 * HTMLAutoLinkParser.java
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

// Expand to define memory size define
//#define DREGULARMEM
// Expand to define logging define
//#define DNOLOGGING

/* This functionality adds to jar size, so don't do it for small memory */
/* devices. */
//#ifndef DSMALLMEM
package com.substanceofcode.rssreader.businesslogic;

import com.substanceofcode.rssreader.businessentities.RssItunesFeed;
import com.substanceofcode.utils.HTMLParser;
import com.substanceofcode.utils.XmlParser;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.Vector;

import com.substanceofcode.utils.EncodingUtil;
import com.substanceofcode.utils.StringUtil;
import com.substanceofcode.utils.CauseException;
import com.substanceofcode.utils.CauseMemoryException;
//#ifdef DLOGGING
import net.sf.jlogmicro.util.logging.Logger;
import net.sf.jlogmicro.util.logging.LogManager;
import net.sf.jlogmicro.util.logging.Level;
//#endif

/**
 * HTMLAutoLinkParser class is used when we are parsing RSS feed list 
 * using HTML autolinks &lt;link href="link" title="Name"/&gt;.
 * These have type with application/atom or rss.
 *
 * @author Irving Bunton
 */
public class HTMLAutoLinkParser extends FeedListParser {
    
	boolean m_needRss = true;
	boolean m_needFirstRss = false;
	//#ifdef DLOGGING
    private Logger logger = Logger.getLogger("HTMLAutoLinkParser");
    private boolean fineLoggable = logger.isLoggable(Level.FINE);
    private boolean finerLoggable = logger.isLoggable(Level.FINER);
    private boolean finestLoggable = logger.isLoggable(Level.FINEST);
	//#endif

    /** Creates a new instance of HTMLAutoLinkParser */
    public HTMLAutoLinkParser(String url, String username, String password) {
        super(url, username, password);
    }

    public RssItunesFeed[] parseFeeds(InputStream is)
    throws IOException, CauseMemoryException, CauseException, Exception {
		// Init in case we get a severe error.
		try {
			return HTMLAutoLinkParser.parseFeeds(new EncodingUtil(is),
											m_url,
											m_needRss,
											m_needFirstRss,
											m_feedNameFilter,
											m_feedURLFilter
											//#ifdef DLOGGING
											,logger
											,fineLoggable
											,finerLoggable
											,finestLoggable
											//#endif
											);
        } catch (CauseException ex) {
			throw ex;
		} catch (Throwable t) {
			CauseException cex = new CauseException(
					"Error while parsing HTML auto link feed " + m_url, t);
//#ifdef DLOGGING
			logger.severe(cex.getMessage(), cex);
//#endif
			System.out.println(cex.getMessage() + " " + t + " " + t.getMessage());
			throw cex;
		}
	}
        
	// Parse feeds.  Allow null title.
    static public RssItunesFeed[] parseFeeds(EncodingUtil encodingUtil,
										String url,
										boolean needRss,
										boolean needFirstRss,
										String feedNameFilter,
										String feedURLFilter
										//#ifdef DLOGGING
										,Logger logger,
										 boolean fineLoggable,
										 boolean finerLoggable,
										 boolean finestLoggable
										//#endif
			                           )
    throws IOException, CauseMemoryException, CauseException, Exception {
        /** Initialize item collection */
        Vector rssFeeds = new Vector();
        
        /** Initialize XML parser and parse OPML XML */
        HTMLParser parser = new HTMLParser(encodingUtil);
        try {
            
			// The first element is the main tag.
            int elementType = parser.parse();
			// If we found the prologue, get the next entry.
			if( elementType == XmlParser.PROLOGUE ) {
				elementType = parser.parse();
			}
			if (elementType == XmlParser.END_DOCUMENT ) {
				return null;
			}
            
			boolean windows = parser.isWindows();
			boolean utf = parser.isUtf();
			boolean process = true;
			boolean bodyFound = false;
            do {
				/** RSS item properties */
				String title = "";
				String link = "";
												
				String tagName = parser.getName();
				//#ifdef DLOGGING
				if (finerLoggable) {logger.finer("tagname: " + tagName);}
				//#endif
				switch (tagName.charAt(0)) {
					case 'b':
					case 'B':
						if (bodyFound) {
							continue;
						}
						bodyFound = parser.isBodyFound();
						if (bodyFound) {
							windows = parser.isWindows();
							utf = parser.isUtf();
						}
						// If looking for OPML link, it is in header.
						if ((!needRss || needFirstRss) && bodyFound) {
							process = false;
							break;
						}
						break;
					case 'l':
					case 'L':
						if (!tagName.toLowerCase().equals("link")) {
							break;
						}
						//#ifdef DLOGGING
						if (finerLoggable) {logger.finer("Parsing <link> tag");}
						//#endif
						
						// TODO base
						String type = parser.getAttributeValue( "type" );
						if (type == null) {
							continue;
						}
						if (!needRss && (type.toLowerCase().indexOf("opml") < 0)) {
							continue;
						}
						if (needRss &&
								((type.toLowerCase().indexOf("rss") < 0) &&
								(type.toLowerCase().indexOf("atom") < 0))) {
							continue;
						}
						title = parser.getAttributeValue( "title" );
						// Allow null title so that the caller can
						// check if it needs to get the title another way.
						if (title != null) {
							title = EncodingUtil.replaceAlphaEntities(true,
									title);
							title = EncodingUtil.replaceNumEntity(title);
							// Replace special chars like left quote, etc.
							// Since we have already converted to unicode, we want
							// to replace with uni chars.
							title = encodingUtil.replaceSpChars(title);

							title = StringUtil.removeHtml(title);
						}
						if (((link = parser.getAttributeValue( "href" ))
									== null) || ( link.length() == 0 )) {
							continue;
						}
						if (link.charAt(0) == '/') {
							link = url + link;
						}
						
						/** Debugging information */
						System.out.println("Title:       " + title);
						System.out.println("Link:        " + link);
						
						/** 
						 * Create new RSS item and add it do RSS document's item
						 * collection.  Account for wrong OPML which is an
						 * OPML composed of other OPML.  These have url attribute
						 * instead of link attribute.
						 */
						if (!needRss || needFirstRss) {
							RssItunesFeed feed = new RssItunesFeed(title, link, "", "");
							rssFeeds.addElement( feed );
							process = false;
							break;
						}
						if (( feedURLFilter != null) &&
							( link.toLowerCase().indexOf(feedURLFilter) < 0)) {
							continue;
						}
						if (( feedNameFilter != null) &&
							((title != null) &&
							(title.toLowerCase().indexOf(feedNameFilter) < 0))) {
							continue;
						}
						RssItunesFeed feed = new RssItunesFeed(title, link, "", "");
						rssFeeds.addElement( feed );
						break;
					default:
				}
			}
            while( process && (parser.parse() != XmlParser.END_DOCUMENT) );
            
        } catch (CauseMemoryException ex) {
			CauseMemoryException cex = new CauseMemoryException(
					"Out of memory error while parsing HTML auto link feed " +
					url, ex);
			throw cex;
        } catch (Exception ex) {
			CauseException cex = new CauseException(
					"Error while parsing HTML auto link feed " + url, ex);
//#ifdef DLOGGING
			logger.severe(cex.getMessage(), cex);
//#endif
			System.err.println(cex.getMessage() + " " + ex + " " + ex.getMessage());
			ex.printStackTrace();
			throw cex;
        } catch (Throwable t) {
			CauseException cex = new CauseException(
					"Error while parsing HTML auto link feed " + url, t);
//#ifdef DLOGGING
			logger.severe(cex.getMessage(), cex);
//#endif
			System.err.println(cex.getMessage() + " " + t + " " + t.getMessage());
			t.printStackTrace();
			throw cex;
        }
        
        /** Create array */
        RssItunesFeed[] feeds = new RssItunesFeed[ rssFeeds.size() ];
        if (feeds.length > 0) {
			rssFeeds.copyInto(feeds);
		}
        return feeds;
    }
    
    public void setNeedRss(boolean needRss) {
        this.m_needRss = needRss;
    }

    public boolean isNeedRss() {
        return (m_needRss);
    }

}
//#endif
