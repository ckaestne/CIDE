/*
 * RssStoreInfo.java
 *
 * Copyright (C) 2008 Irving Bunton
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
// Expand to define test define
//#define DNOTEST
// Expand to define logging define
//#define DNOLOGGING
package com.substanceofcode.rssreader.businessentities;

import com.substanceofcode.utils.Base64;
import com.substanceofcode.utils.StringUtil;
import java.io.UnsupportedEncodingException;
import java.util.*;
//#ifdef DLOGGING
import net.sf.jlogmicro.util.logging.Logger;
import net.sf.jlogmicro.util.logging.Level;
//#endif

/**
 * RssStoreInfo class info to help access rss store string from RssFeedStore.
 *
 * @author Irving Bunton
 */
public class RssStoreInfo {
    
	String m_name;
	String m_storeString;
	//#ifdef DTEST
	long m_encodeTime = 0L;
	long m_splitTime = 0L;
	long m_joinTime = 0L;
	//#endif

	//#ifdef DLOGGING
    private Logger m_logger = Logger.getLogger("RssFeedStore");
	//#endif
	//#ifdef DLOGGING
    private boolean m_fineLoggable = m_logger.isLoggable(Level.FINE);
    private boolean m_finestLoggable = m_logger.isLoggable(Level.FINEST);
	//#endif

	public RssStoreInfo(String name, String storeString) {
        this.m_name = name;
        this.m_storeString = storeString;
	}

	//#ifdef DTEST
	public RssStoreInfo(String name, String storeString, long encodeTime,
						long splitTime,
						long joinTime) {
		this(name, storeString);
		m_encodeTime = encodeTime;
		m_splitTime = splitTime;
		m_joinTime = joinTime;
	}
	//#endif

    public void setName(String name) {
        this.m_name = name;
    }

    public String getName() {
        return (m_name);
    }

    public void setStoreString(String storeString) {
        this.m_storeString = storeString;
    }

    public String getStoreString() {
        return (m_storeString);
    }

	//#ifdef DTEST
    public void setEncodeTime(long encodeTime) {
        this.m_encodeTime = encodeTime;
    }

    public long getEncodeTime() {
        return (m_encodeTime);
    }

    public void setSplitTime(long splitTime) {
        this.m_splitTime = splitTime;
    }

    public long getSplitTime() {
        return (m_splitTime);
    }

    public void setJoinTime(long joinTime) {
        this.m_joinTime = joinTime;
    }

    public long getJoinTime() {
        return (m_joinTime);
    }
	//#endif

}
