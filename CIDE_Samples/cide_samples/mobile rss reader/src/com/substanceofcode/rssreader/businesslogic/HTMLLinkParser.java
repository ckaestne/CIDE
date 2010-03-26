/*
 * HTMLLinkParser.java
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

// Expand to define memory size define
//#define DREGULARMEM
// Expand to define logging define
//#define DNOLOGGING

/* This functionality adds to jar size, so don't do it for small memory */
/* devices. */
//#ifndef DSMALLMEM
package com.substanceofcode.rssreader.businesslogic;

import com.substanceofcode.rssreader.businessentities.RssItunesFeed;
import com.substanceofcode.utils.EncodingStreamReader;
import com.substanceofcode.utils.StringUtil;
import com.substanceofcode.utils.HTMLParser;
import com.substanceofcode.utils.XmlParser;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.Vector;

import com.substanceofcode.utils.EncodingUtil;
import com.substanceofcode.utils.CauseException;
import com.substanceofcode.utils.CauseMemoryException;
//#ifdef DLOGGING
import net.sf.jlogmicro.util.logging.Logger;
import net.sf.jlogmicro.util.logging.LogManager;
import net.sf.jlogmicro.util.logging.Level;
//#endif

/**
 * HTMLLinkParser class is used when we are parsing RSS feed list 
 * using HTML hyperlinks &lt;a href="link"&gt;Name&lt;/a&gt;.
 * For example, the BBC page has such links with URL rss.xml, so one
 * would use URL http://news.bbc.co.uk/2/hi/help/3223484.stm with
 * URL search string as rss.xml to weed out the unrelated links.
 *
 * @author Irving Bunton
 */
public class HTMLLinkParser extends FeedListParser {
    
    protected boolean m_acceptErrors = true;  // Allow some errors 

	//#ifdef DLOGGING
    private Logger logger = Logger.getLogger("HTMLLinkParser");
    private boolean fineLoggable = logger.isLoggable(Level.FINE);
    private boolean finerLoggable = logger.isLoggable(Level.FINER);
    private boolean finestLoggable = logger.isLoggable(Level.FINEST);
	//#endif

    /** Creates a new instance of HTMLLinkParser */
    public HTMLLinkParser(String url, String username, String password) {
        super(url, username, password);
    }

	/** Parse HTML hyper links '<a' and create feeds from them based on
	   link name and url if specified.
	  */
    public RssItunesFeed[] parseFeeds(InputStream is)
    throws IOException, CauseMemoryException, CauseException, Exception {
		// Init in case we get a severe error.
		try {
			return HTMLLinkParser.parseFeeds(new EncodingUtil(is),
											m_url,
											m_feedNameFilter,
											m_feedURLFilter,
											m_acceptErrors
											//#ifdef DLOGGING
											,logger
											,fineLoggable
											,finerLoggable
											,finestLoggable
											//#endif
											);
		} catch (CauseException e) {
			throw e;
		} catch (Throwable t) {
			CauseException cex = new CauseException(
					"Error while parsing HTML Link feed " + m_url, t);
//#ifdef DLOGGING
			logger.severe(cex.getMessage(), cex);
//#endif
			System.err.println(cex.getMessage() + " " + t + " " + t.getMessage());
			throw cex;
		}
	}
        
	/** Parse HTML hyper links '<a' and create feeds from them based on
	   link name and url if specified.
	  */
    static public RssItunesFeed[] parseFeeds(EncodingUtil encodingUtil,
										String url,
										String feedNameFilter,
										String feedURLFilter,
										boolean acceptErrors
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
            
			boolean bodyFound = false;
            do {
				if (elementType == HTMLParser.REDIRECT_URL) {
					RssItunesFeed [] feeds = new RssItunesFeed[1];
					feeds[0] = new RssItunesFeed("", parser.getRedirectUrl(),
							"", "");
					return feeds;
				}
				/** RSS item properties */
				String title = "";
				String link = "";
												
				String tagName = parser.getName();
				//#ifdef DLOGGING
				if (finerLoggable) {logger.finer("tagname: " + tagName);}
				//#endif
				if (tagName.length() == 0) {
					continue;
				}
				switch (tagName.charAt(0)) {
					case 'm':
					case 'M':
						if (bodyFound) {
							break;
						}
						break;
					case 'b':
					case 'B':
						if (!bodyFound) {
							bodyFound = parser.isBodyFound();
						}
						break;
					case 'a':
					case 'A':
						//#ifdef DLOGGING
						if (finerLoggable) {logger.finer("Parsing <a> tag");}
						//#endif
						
						title = parser.getText();
						// Title can be 0 as this is used also for
						// getting 
						title = title.trim();
						title = StringUtil.removeHtml( title );

						if (((link = parser.getAttributeValue( "href" ))
									== null) || ( link.length() == 0 )) {
							continue;
						}
						link = link.trim();
						if ( link.length() == 0 ) {
							continue;
						}
						if (link.indexOf("://") >= 0) {
							if (!link.startsWith("http:") &&
								!link.startsWith("https:") &&
								!link.startsWith("file:") &&
								 !link.startsWith("jar:")) {
								//#ifdef DLOGGING
								if (finerLoggable) {logger.finer("Not support for protocol or no protocol=" + link);}
								//#endif
								continue;
							}
						} else {
							if (link.charAt(0) == '/') {
								int purl = url.indexOf("://");
								if ((purl + 4) >= url.length()) {
									//#ifdef DLOGGING
									if (finerLoggable) {logger.finer("Url too short=" + url + "," + purl);}
									//#endif
									continue;
								}
								int pslash = url.indexOf("/", purl + 3);
								String burl = url;
								if (pslash >= 0) {
									burl = url.substring(0, pslash);
								}
								link = burl + link;
							} else {
								link = url + "/" + link;
							}
						}
						
						/** Debugging information */
						//#ifdef DLOGGING
						if (finerLoggable) {logger.finer("Title:       " + title);}
						if (finerLoggable) {logger.finer("Link:        " + link);}
						//#endif
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
            while( (elementType = parser.parse()) != XmlParser.END_DOCUMENT );
            
        } catch (CauseMemoryException ex) {
			CauseMemoryException cex = new CauseMemoryException(
					"Out of memory error while parsing HTML Link feed " + url,
					ex);
			throw cex;
        } catch (Exception ex) {
			CauseException cex = new CauseException(
					"Error while parsing HTML Link feed " + url, ex);
            System.err.println(cex.getMessage() + " " + ex + " " + ex.toString());
			ex.printStackTrace();
//#ifdef DLOGGING
			logger.severe(cex.getMessage(), cex);
//#endif
			throw cex;
        } catch (Throwable t) {
			CauseException cex = new CauseException(
					"Error while parsing HTML Link feed " + url, t);
            System.err.println(cex.getMessage() + " " + t + " " + t.toString());
			t.printStackTrace();
//#ifdef DLOGGING
			logger.severe(cex.getMessage(), cex);
//#endif
			throw cex;
        }
        
        /** Create array */
        RssItunesFeed[] feeds = new RssItunesFeed[ rssFeeds.size() ];
        rssFeeds.copyInto(feeds);
        return feeds;
    }
    
}
//#endif
