/*
 * LineByLineParser.java
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
package com.substanceofcode.rssreader.businesslogic;

import com.substanceofcode.utils.EncodingUtil;
import com.substanceofcode.utils.EncodingStreamReader;
import com.substanceofcode.rssreader.businessentities.RssItunesFeed;
import com.substanceofcode.utils.StringUtil;
import com.substanceofcode.utils.CauseException;
import com.substanceofcode.utils.CauseMemoryException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.Vector;

//#ifdef DLOGGING
import net.sf.jlogmicro.util.logging.Logger;
import net.sf.jlogmicro.util.logging.Level;
//#endif

/**
 * LineByLineParser class is used when we are parsing RSS feed list 
 * line-by-line.
 *
 * @author Tommi Laukkanen
 */
public class LineByLineParser extends FeedListParser {
    
	//#ifdef DLOGGING
    private Logger m_logger = Logger.getLogger("LineByLineParser");
    final private boolean m_fineLoggable = m_logger.isLoggable(Level.FINE);
    final private boolean m_finerLoggable = m_logger.isLoggable(Level.FINER);
    final private boolean m_finestLoggable = m_logger.isLoggable(Level.FINEST);
	//#endif

    /** Creates a new instance of LineByLineParser */
    public LineByLineParser(String url, String username, String password) {
        super(url, username, password);
    }

    public RssItunesFeed[] parseFeeds(InputStream is)
    throws IOException, CauseMemoryException, CauseException, Exception {
        // Prepare buffer for input data
        StringBuffer inputBuffer = new StringBuffer();
        
		EncodingUtil encUtl = new EncodingUtil(is);
		EncodingStreamReader esr = encUtl.getEncodingStreamReader();

        try {
			// Read all data to buffer.  Use 100 char increments to save on
			// memory.
			inputBuffer = esr.readFile(100);
        } catch (IOException ex) {
			CauseException cex = new CauseException(
					"Error while parsing line by feed " + m_url, ex);
			throw cex;
        }
        String text;
		if (esr.isUtfDoc()) {
			final String fileEncoding = esr.getFileEncoding();
			if (esr.isUtf16Doc()) {
				encUtl.getEncoding(fileEncoding, "UTF-16");
			} else {
				encUtl.getEncoding(fileEncoding, "UTF-8");
			}
			final String docEncoding = encUtl.getDocEncoding();
			if (docEncoding.length() == 0) {
				text = inputBuffer.toString();
			} else {
				try {
					// We read the bytes in as ISO8859_1, so we must get them
					// out as that and then encode as they should be.
					if (fileEncoding.length() == 0) {
						text = new String(inputBuffer.toString().getBytes(),
										  docEncoding);
					} else {
						text = new String(inputBuffer.toString().getBytes(
									fileEncoding), docEncoding);
					}
				} catch (UnsupportedEncodingException e) {
					//#ifdef DLOGGING
					m_logger.severe("parseFeeds Could not convert string from,to" + fileEncoding + "," + docEncoding, e);
					//#endif
					System.out.println("parseFeeds Could not convert string from,to" + fileEncoding + "," + docEncoding);
					text = inputBuffer.toString();
				} catch (IOException e) {
					//#ifdef DLOGGING
					m_logger.severe("parseFeeds Could not convert string from,to" + fileEncoding + "," + docEncoding, e);
					//#endif
					System.out.println("parseFeeds Could not convert string from,to" + fileEncoding + "," + docEncoding);
					text = inputBuffer.toString();
				}
			}
		} else {
			text = inputBuffer.toString();
		}
		inputBuffer = null;
        
        // Split buffer string by each new line
        text = StringUtil.replace(text, "\r", "");
        String[] lines = StringUtil.split(text, '\n');
		text = null;
        
        RssItunesFeed[] feeds = new RssItunesFeed[ lines.length ];
        for(int lineIndex=0; lineIndex<lines.length;
				lines[lineIndex] = null, lineIndex++) {
            String line = lines[lineIndex];
            String name;
            String url;
            int indexOfSpace = line.indexOf(' ');
            if(indexOfSpace>0) {
                name = line.substring(indexOfSpace+1);
                url = line.substring(0, indexOfSpace);
            } else {
                name = line;
                url = line;
            }
			if((( m_feedNameFilter != null) &&
			  (name.toLowerCase().indexOf(m_feedNameFilter) < 0)) ||
			  (( m_feedURLFilter != null) &&
			  (url.toLowerCase().indexOf(m_feedURLFilter) < 0))) {
				continue;
			}
            feeds[lineIndex] = new RssItunesFeed(name, url, "", "");
        }
        
        return feeds;        
    }
    
}
