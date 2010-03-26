/*
 * RssFeedStore.java
 *
 * Copyright (C) 2005-2006 Irving Bunton
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

import java.util.Enumeration;
import java.util.Hashtable;
import java.util.Vector;

import com.substanceofcode.rssreader.businessentities.RssItunesFeed;
import com.substanceofcode.rssreader.businessentities.RssItunesItem;
import com.substanceofcode.utils.CauseException;
import com.substanceofcode.utils.CauseMemoryException;

//#ifdef DLOGGING
import net.sf.jlogmicro.util.logging.Logger;
import net.sf.jlogmicro.util.logging.Level;
//#endif

/**
 * RssFeedStore class stores feeds and manages different ways to store to save
 * memory.
 *
 * @author Irving Bunton
 */
final public class RssFeedStore extends Hashtable {
    
	private boolean m_saveMemoryEnabled;
	private RssItunesFeed m_feed = null;

	//#ifdef DLOGGING
    private Logger m_logger = Logger.getLogger("RssFeedStore");
    private boolean m_fineLoggable = m_logger.isLoggable(Level.FINE);
    private boolean m_finestLoggable = m_logger.isLoggable(Level.FINEST);
	//#endif
    
    /** Creates a new instance of RssFeedStore */
    public RssFeedStore(boolean saveMemoryEnabled) {
		super();
		m_saveMemoryEnabled = saveMemoryEnabled;
	}

    /** Creates a new instance of RssFeedStore */
    public RssFeedStore(boolean saveMemoryEnabled, int initialCapacity){
		super(initialCapacity);
		m_saveMemoryEnabled = saveMemoryEnabled;
    }

  /**
   * Put feed store string into store and use compact to save memory or full
   * class
   *
   * @param name - Name of feed to put into store
   * @param storeString - Feed store string to put into store
   * @author Irv Bunton
   */
	final public void put(String name, String storeString, boolean encoded)
	throws CauseMemoryException, CauseException {
		if (m_saveMemoryEnabled) {
			if (encoded) {
				super.put(name, RssItunesFeed.getStoreStringInfo(true, false,
							storeString, encoded).getStoreString());
			} else {
				super.put(name, storeString);
			}
		} else {
			super.put(name, RssItunesFeed.deserialize(encoded, storeString));
		}
	}

  /**
   * Put feed into store and use compact to save memory or full class
   *
   * @param name - Name of feed to put into store
   * @param feed - Feed to put into store
   * @author Irv Bunton
   */
	final public void put(String name, RssItunesFeed feed)
	throws CauseMemoryException, CauseException {
		if (m_saveMemoryEnabled) {
			super.put(name, feed.getStoreString(true, true, false));
		} else {
			super.put(name, feed);
		}
	}

  /**
   * Put feed into store and use compact to save memory or full class
   *
   * @param feed - Feed to put into store
   * @author Irv Bunton
   */
	final public void put(RssItunesFeed feed)
	throws CauseMemoryException, CauseException {
		put(feed.getName(), feed);
	}

  /**
   * Get version of RssItunesFeed from feed name.  If allowWrite, the feed
   * can be modified.  The next get will cause the previous changed feed to
   * be stored.
   *
   * @param name - feed name
   * @param allowWrite - true to allow right
   * @return    RssItunesFeed - feed for feed name
   * @author Irv Bunton
   */
	final private Object get(final String name, final boolean getStore,
									final boolean allowWrite)
	throws CauseMemoryException, CauseException {
		Object obj = super.get(name);
		if (obj == null) {
			return null;
		}
		RssItunesFeed cfeed = null;
		String cstore = null;
		if (obj instanceof RssItunesFeed) {
			if (getStore) {
				cstore = ((RssItunesFeed)obj).getStoreString(true, true, false);
			} else {
				cfeed = (RssItunesFeed)obj;
			}
		} else {
			if (getStore) {
				cstore = (String)obj;
			} else {
				cfeed = RssItunesFeed.deserialize( false, (String)obj );
			}
		}
		if (m_feed != null) {
			put(m_feed);
		}
		if (allowWrite && !getStore) {
			m_feed = cfeed;
		} else {
			m_feed = null;
		}
		if (getStore) {
			return cstore;
		} else {
			return cfeed;
		}
	}
    
  /**
   * Get read only version of RssItunesFeed from feed name.  If this returned
   * feed is modified, it will not change what is stored.  One can change what
   * is stored by doing put of the modified feed.
   *
   * @param name - feed name
   * @return    RssItunesFeed - feed for feed name
   * @author Irv Bunton
   */
	final public RssItunesFeed getRo(final String name)
	throws CauseMemoryException, CauseException {
		return (RssItunesFeed)get(name, false, false);
	}

  /**
   * Get read only version of RssItunesFeed store string from feed name.
   * If this returned store string is modified, it will not change what is
   * stored.  One can change what is stored by doing put of the modified feed.
   *
   * @param name - feed name
   * @return    String - Store string for feed name
   * @author Irv Bunton
   */
	final public String getRoStoreStr(final String name)
	throws CauseMemoryException, CauseException {
		return (String)get(name, true, false);
	}

  /**
   * Get update version of RssItunesFeed from feed name.  If this returned
   * feed is modified, it will change what is stored.
   *
   * @param name - feed name
   * @return    RssItunesFeed - feed for feed name
   * @author Irv Bunton
   */
	final public RssItunesFeed get(final String name)
	throws CauseMemoryException, CauseException {
		return (RssItunesFeed)get(name, false, true);
	}

	/* Free memory by getting rid of items. */
	final public void freeFeedItems()
	throws CauseMemoryException, CauseException {
		Enumeration keyEnum = super.keys();
		int ic = 1;
		while(keyEnum.hasMoreElements()) {
			final String fname = (String)keyEnum.nextElement();
			RssItunesFeed feed = (RssItunesFeed)get(fname);
			feed.setItems(null);
			feed.setItems(new Vector());
			put(fname, feed);
		}
	}

    public void setSaveMemoryEnabled(boolean saveMemoryEnabled) {
        this.m_saveMemoryEnabled = saveMemoryEnabled;
    }

    public boolean isSaveMemoryEnabled() {
        return (m_saveMemoryEnabled);
    }

}
