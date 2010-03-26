/*
 * FeedListParser.java
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
import java.io.IOException;
import java.io.InputStream;

import cz.cacek.ebook.util.ResourceProviderME;
import com.substanceofcode.utils.CauseException;
import com.substanceofcode.utils.CauseMemoryException;
//#ifdef DLOGGING
import net.sf.jlogmicro.util.logging.Logger;
import net.sf.jlogmicro.util.logging.LogManager;
import net.sf.jlogmicro.util.logging.Level;
//#endif

/**
 * Base class for feed list parsers.
 *
 * @author Tommi Laukkanen
 */
public abstract class FeedListParser extends URLHandler implements Runnable{
    
    private Thread m_parsingThread;
	protected String m_url;
	protected String m_username;
	protected String m_password;
	protected String m_feedNameFilter;
	protected String m_feedURLFilter;
	protected boolean m_redirectHtml = false;
	protected RssItunesFeed[] m_feeds;
    private boolean m_ready;
    private boolean m_successfull = false;
    private CauseException m_ex = null;
    private boolean m_redirect = false;  // The RSS feed is redirected
    
	//#ifdef DLOGGING
    private Logger logger = Logger.getLogger("FeedListParser");
    private boolean fineLoggable = logger.isLoggable(Level.FINE);
    private boolean finerLoggable = logger.isLoggable(Level.FINER);
    private boolean finestLoggable = logger.isLoggable(Level.FINEST);
	//#endif

    /** Creates a new instance of FeedListParser */
    public FeedListParser(String url, String username, String password) {
		super();
        m_parsingThread = new Thread(this);
		m_url = url;
		m_username = username;
		m_password = m_password;
    }
    
    /** Start parsing the feed list */
    public void startParsing() {
        m_ready = false;
        m_parsingThread.start();
		//#ifdef DLOGGING
		if (fineLoggable) {logger.fine("Thread started=" + m_parsingThread);}
		//#endif
    }
    
    /** Check whatever parsing is ready or not */
    public boolean isReady() {
        return m_ready;
    }
    
    /** Get feed list */
    public RssItunesFeed[] getFeeds() {
        return m_feeds;
    }
    
    /** Parsing thread */
    public void run() {
        try {
			//#ifdef DLOGGING
			if (fineLoggable) {logger.fine("Thread running=" + this);}
			//#endif
            m_feeds = parseFeeds();
			m_successfull = true;
        } catch( IOException ex ) {
			//#ifdef DLOGGING
			logger.severe("FeedListParser.run(): Error while parsing " +
					      "feeds: " + m_url, ex);
			//#endif
            System.err.println("FeedListParser.run(): Error while parsing feeds: " + ex.toString());
			m_ex = new CauseException("Error while parsing feed " + m_url, ex);
        } catch( CauseMemoryException ex ) {
			m_feeds = null;
			System.gc();
			m_ex = new CauseMemoryException("Out of memory error while " +
					"parsing feed " + m_url, ex);
			//#ifdef DLOGGING
			logger.severe(m_ex.getMessage(), ex);
			//#endif
        } catch( Exception ex ) {
			//#ifdef DLOGGING
			logger.severe("FeedListParser.run(): Error while parsing " +
					      "feeds: " + m_url, ex);
			//#endif
            System.err.println("FeedListParser.run(): Error while parsing feeds: " + ex.toString());
			m_ex = new CauseException("Error while parsing feed " + m_url, ex);
        } catch( OutOfMemoryError t ) {
			m_feeds = null;
			System.gc();
			//#ifdef DLOGGING
			logger.severe("FeedListParser.run(): Out Of Memory Error while " +
					"parsing feeds: " + m_url, t);
			//#endif
            System.err.println("FeedListParser.run(): " +
					"Out Of Memory Error while parsing feeds: " + t.toString());
			m_ex = new CauseMemoryException("Out Of Memory Error while " +
					"parsing feed " + m_url, t);
        } catch( Throwable t ) {
			//#ifdef DLOGGING
			logger.severe("FeedListParser.run(): Error while parsing " +
					      "feeds: " + m_url, t);
			//#endif
            System.err.println("FeedListParser.run(): Error while parsing feeds: " + t.toString());
			m_ex = new CauseException("Internal error while parsing feed " +
									  m_url, t);
        } finally {
            m_ready = true;
        }        
    }  
    
    
    /** Get feeds from selected url */
    public RssItunesFeed[] parseFeeds()
    throws IOException, CauseMemoryException, CauseException, Exception {
        
		try {
			super.handleOpen(m_url, m_username, m_password);
			if (m_needRedirect) {
				m_needRedirect = false;
				m_feeds = parseHeaderRedirect(m_location);
				return m_feeds;
			}

			//#ifdef DLOGGING
			if (fineLoggable) {logger.fine("m_redirectHtml=" + m_redirectHtml);}
			//#endif
			// If we find HTML, usually it is redirection
			if (m_redirectHtml && (m_contentType != null) &&
					(m_contentType.indexOf("html") >= 0)) {
				return parseHTMLRedirect();
			} else {
				return parseFeeds(m_inputStream);
			}
        } catch(CauseMemoryException e) {
			throw e;
        } catch(Exception e) {
            /* Error while parsing import data: */
            CauseException ce = new CauseException(ResourceProviderME.get(
						"exc.err.imp", m_url), e);
			//#ifdef DLOGGING
			logger.severe(ce.getMessage(), e);
			//#endif
			if ((m_url != null) && m_url.startsWith("file://")) {
				System.err.println("Cannot process file.");
			}
            throw ce;
        } catch(OutOfMemoryError t) {
			m_feeds = null;
			System.gc();
			//#ifdef DLOGGING
			logger.severe("Out Of Memory Error with " + m_url, t);
			//#endif
			if ((m_url != null) && m_url.startsWith("file://")) {
				System.err.println("Cannot process file.");
			}
            /* Out Of Memory Error while parsing RSS data */
            throw new CauseMemoryException(ResourceProviderME.get(
						"exc.om.rsd", m_url), t);
        } catch(Throwable t) {
			//#ifdef DLOGGING
			logger.severe("parseFeeds error with " + m_url, t);
			//#endif
			if ((m_url != null) && m_url.startsWith("file://")) {
				System.err.println("Cannot process file.");
			}
            throw new CauseException(
					"Internal error while parsing RSS data " + m_url, t);
        } finally {
			super.handleClose();
		}
    }    
    
	/** If header shows redirect, handle it here. */
	private RssItunesFeed[] parseHeaderRedirect(String newUrl)
    throws IOException, CauseMemoryException, CauseException, Exception {
		if (m_redirect) {
			//#ifdef DLOGGING
			logger.severe("Error 2nd header redirect url:  " + newUrl);
			//#endif
			System.out.println("Error 2nd header redirect url " +
					m_redirectUrl + " to 2nd redirect " + newUrl);
			throw new IOException("Error 2nd header redirect url " +
					m_redirectUrl + " to 2nd redirect " + newUrl);
		}
		m_redirect = true;
		m_redirectUrl = m_url;
		String svUrl = m_url;
		m_url = newUrl;
		try {
			return parseFeeds();
		} finally {
			m_url = newUrl;
		}
	}

	/** Read HTML and if it has links, redirect and parse the XML. */
	private RssItunesFeed[] parseHTMLRedirect()
    throws IOException, CauseMemoryException, CauseException, Exception {
		String svUrl = m_url;
		m_url = super.parseHTMLRedirect(m_url, m_inputStream);
		try {
			return parseFeeds();
		} finally {
			m_url = svUrl;
		}
	}

    abstract RssItunesFeed[] parseFeeds(InputStream is)
    throws IOException, CauseMemoryException, CauseException, Exception;

    public void setFeedNameFilter(String m_feedNameFilter) {
        if (m_feedNameFilter == null) {
			this.m_feedNameFilter = null;
		} else if (m_feedNameFilter.length() == 0) {
			this.m_feedNameFilter = null;
		} else {
			this.m_feedNameFilter = m_feedNameFilter.toLowerCase();
		}
    }

    public String getFeedNameFilter() {
        return (m_feedNameFilter);
    }

    public void setFeedURLFilter(String m_feedURLFilter) {
        if (m_feedURLFilter == null) {
			this.m_feedURLFilter = null;
		} else if (m_feedURLFilter.length() == 0) {
			this.m_feedURLFilter = null;
		} else {
			this.m_feedURLFilter = m_feedURLFilter.toLowerCase();
		}
    }

    public String getFeedURLFilter() {
        return (m_feedURLFilter);
    }

    public boolean isSuccessfull() {
        return (m_successfull);
    }

    public void setRedirectHtml(boolean m_redirectHtml) {
        this.m_redirectHtml = m_redirectHtml;
    }

    public boolean isRedirectHtml() {
        return (m_redirectHtml);
    }

    public CauseException getEx() {
        return (m_ex);
    }

    public String getUrl() {
        return (m_url);
    }

}
