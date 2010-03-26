/*
 * ExtParser.java
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

// Expand to define itunes define
//#define DNOITUNES
// Expand to define logging define
//#define DNOLOGGING
package com.substanceofcode.rssreader.businesslogic;

import java.io.IOException;
import java.util.Date;
import java.util.Vector;

//#ifdef DLOGGING
import net.sf.jlogmicro.util.logging.Logger;
import net.sf.jlogmicro.util.logging.Level;
//#endif

import com.substanceofcode.utils.XmlParser;
import com.substanceofcode.rssreader.businessentities.RssItunesFeed;
import com.substanceofcode.rssreader.businessentities.RssItunesItem;
import com.substanceofcode.rssreader.businessentities.RssItem;
import com.substanceofcode.utils.StringUtil;
import com.substanceofcode.utils.CauseException;
import com.substanceofcode.utils.CauseMemoryException;
//#ifdef DLOGGING
import net.sf.jlogmicro.util.logging.Logger;
import net.sf.jlogmicro.util.logging.LogManager;
import net.sf.jlogmicro.util.logging.Level;
//#endif

/**
 * Process (namespaces) extensions for dc and Itunes.
 * @author Irving Bunton Jr
 */
public class ExtParser {
    
	/** RSS item properties */
	// Dublin core metadata element set
	// Explained at http://web.resource.org/rss/1.0/modules/dc/
	public final static String DC_NAMESPACE = "http://purl.org/dc/elements/1.1/";
	public final static String CONTENT_NAMESPACE = "http://purl.org/rss/1.0/modules/content/";
	private boolean m_itunes = false;
	private boolean m_hasItunes = false;
	private boolean m_hasExt = false;
	private boolean m_hasDc = false;
	private boolean m_hasContent = false;
	private boolean m_convXmlEnts;
	private byte m_explicit = (byte)-1;   // The RSS item explicit
	private char m_itunesNamespaceChar;
	private char m_dcNamespaceChar;
	private char m_contentNamespaceChar;
	private String m_itunesNamespace = "";
	private String m_dcNamespace = "";
	private String m_contentNamespace = "";
	private String m_date = "";   // The RSS item date
	private String m_author = "";   // The RSS item Itunes author
	private String m_creator = "";   // The RSS item DC creator
	// Since language is required by Itunes, we show it as it is important
	// to know the language of music, etc.
	private String m_subtitle = "";   // The RSS item subtitle
    private String m_language = "";   // The RSS item language
	private String m_description = "";   // The RSS item DC description
	private String m_encoded = "";   // The RSS item Content encoded
	private String m_summary = "";   // The RSS item summary
	private String m_duration = "";   // The RSS item duration
	private int m_itunesNamespaceLen = 0;
	private int m_dcNamespaceLen = 0;
	private int m_contentNamespaceLen = 0;
	//#ifdef DLOGGING
    private Logger logger = Logger.getLogger("ExtParser");
    private boolean fineLoggable = logger.isLoggable(Level.FINE);
    private boolean finestLoggable = logger.isLoggable(Level.FINEST);
	//#endif

    /** Creates a new instance of RssFormatParser */
    public ExtParser(boolean convXmlEnts) {
		m_convXmlEnts = convXmlEnts;
    }
    
	/** Reset fields to uninitialized values. */
    public void reset() {
		m_itunes = false;
		m_author = "";
		m_creator = "";
		m_description = "";
		m_language = "";
		m_subtitle = "";
		m_summary = "";
		m_explicit = (byte)-1;
		m_duration  = "";
	}

	/** Create an Itunes feed if at least one of these fields are not
	  their uninitialized values, else return an RssItunesFeed to save on
	  memory. */
    public RssItunesFeed getFeedInstance(RssItunesFeed feed, String language,
			String title, String desc) {
		if (language.length() == 0) {
			language = m_language;
		}

		if (!m_itunes) {
			feed.setItunes(false);
			return feed;
		}
		if (m_author.length() == 0) {
			m_author = m_creator;
		}
		if (feed.getName().equals(m_subtitle)) {
			m_subtitle = "";
		} else if (m_convXmlEnts) {
			m_summary = StringUtil.removeHtml(m_summary);
		}
		feed.modifyItunes(true, title, desc, m_language, m_author, m_subtitle,
						  m_summary, m_explicit);
		return feed;
	}

	/** Create an Itunes item if at least one of these fields are not
	  their uninitialized values, else return an RssItunesItem to save on
	  memory. */
    public RssItunesItem createItem(String title, String link, String desc,
					Date date,
					String enclosure,
					boolean unreadItem, String author) {
		if (desc.length() == 0) {
			desc = m_description;
			if (desc.length() == 0) {
				desc = m_encoded;
			}
		}
		if (desc.equals(m_summary)) {
			m_summary = "";
		}
		if (title.equals(m_subtitle)) {
			m_subtitle = "";
		}
		if (m_convXmlEnts) {
			desc = StringUtil.removeHtml(desc);
		}
		//#ifdef DITUNES
		if (m_convXmlEnts) {
			m_subtitle = StringUtil.removeHtml(m_subtitle);
			m_summary = StringUtil.removeHtml(m_summary);
		}
		if (((m_author + m_subtitle + m_summary + m_duration).length() == 0)  &&
				(m_explicit == (byte)-1)) {
			RssItunesItem item = new RssItunesItem(title, link, desc, date,
					enclosure, unreadItem);
			return item;
		}
		if (m_author.length() == 0) {
			m_author = author;
		}
		if (m_author.length() == 0) {
			m_author = m_creator;
		}
		if (m_convXmlEnts) {
			m_author = StringUtil.removeHtml(m_author);
		}
		//#endif
		return new RssItunesItem(title, link, desc, date,
			        enclosure, unreadItem,
					true,
					m_author,
					m_subtitle,
					m_summary,
					m_explicit,
					m_duration);
	}

	/** Parse the namespaces for Itunes namespaces and date namespace. */
    public void parseNamespaces(XmlParser parser)
	throws IOException {
        
		String [][] nameSpaces = parser.parseNamespaces();
		if (nameSpaces.length == 0) {
			return;
		}
		String [] rnameSpaces = nameSpaces[0];
		m_hasItunes = false;
		m_hasExt = false;
		m_hasDc = false;
		for (int ic = 0; ic < rnameSpaces.length; ic++) {
			if (nameSpaces[1][ic].indexOf(DC_NAMESPACE) >= 0) {
				m_hasDc = true;
				m_hasExt = true;
				m_dcNamespace = nameSpaces[0][ic] + ":";
				m_dcNamespaceChar = m_dcNamespace.charAt(0);
				m_dcNamespaceLen = m_dcNamespace.length();
				//#ifdef DLOGGING
				if (finestLoggable) {logger.finest("m_dcNamespace,m_dcNamespace=" + m_dcNamespace + "," + m_dcNamespace);}
				//#endif
			} else if (nameSpaces[1][ic].indexOf(CONTENT_NAMESPACE) >= 0) {
				m_hasContent = true;
				m_hasExt = true;
				m_contentNamespace = nameSpaces[0][ic] + ":";
				m_contentNamespaceChar = m_contentNamespace.charAt(0);
				m_contentNamespaceLen = m_contentNamespace.length();
				//#ifdef DLOGGING
				if (finestLoggable) {logger.finest("m_contentNamespace,m_contentNamespace=" + m_contentNamespace + "," + m_contentNamespace);}
				//#endif
			}
			//#ifdef DITUNES
			else if ((nameSpaces[1][ic].indexOf("itunes") >= 0) ||
			   (nameSpaces[1][ic].indexOf("apple") >= 0)) {
				m_hasItunes = true;
				m_hasExt = true;
				m_itunesNamespace = nameSpaces[0][ic] + ":";
				m_itunesNamespaceChar = m_itunesNamespace.charAt(0);
				m_itunesNamespaceLen = m_itunesNamespace.length();
				//#ifdef DLOGGING
				if (finestLoggable) {logger.finest("m_itunesNamespace,m_itunesNamespaceLen=" + m_itunesNamespace + "," + m_itunesNamespaceLen);}
				//#endif
			}
			//#endif
		}
        
		String [] checkNameSpaces;
		if (m_hasItunes || m_hasDc || m_hasContent) {
			Vector vspaces = new Vector();
			//#ifdef DITUNES
			if (m_hasItunes) {
				vspaces.addElement(m_itunesNamespace.substring(0,
						(m_itunesNamespace.length() - 1)));
			}
			//#endif
			if (m_hasDc) {
				vspaces.addElement(m_dcNamespace.substring(0,
					(m_dcNamespace.length() - 1)));
			}
			if (m_hasContent) {
				vspaces.addElement(m_contentNamespace.substring(0,
					(m_contentNamespace.length() - 1)));
			}
			checkNameSpaces = new String[vspaces.size()];
			vspaces.copyInto(checkNameSpaces);
			parser.setNamespaces(checkNameSpaces);
			//#ifdef DLOGGING
			if (finestLoggable) {logger.finest("checkNameSpaces[0]=" + checkNameSpaces[0]);}
			if (finestLoggable) {if (checkNameSpaces.length > 1) {logger.finest("checkNameSpaces[1]=" + checkNameSpaces[1]);}}
			if (finestLoggable) {if (checkNameSpaces.length > 2) {logger.finest("checkNameSpaces[2]=" + checkNameSpaces[2]);}}
			//#endif
			
		} else {
			return;
		}
    }
    
	/* Parse the item to get it's fields */
	void parseExtItem(XmlParser parser, char elemChar, String elementName)
	throws IOException, CauseMemoryException, CauseException {
		String subElem;
		int elen = elementName.length();
		boolean isItunes = false;
		boolean isDc = false;
		if (m_hasItunes && (elemChar == m_itunesNamespaceChar) &&
				(m_itunesNamespaceLen < elen) &&
			 elementName.startsWith(m_itunesNamespace)) {
			subElem = elementName.substring(m_itunesNamespaceLen);
			isItunes = true;
		} else if (m_hasDc && (elemChar == m_dcNamespaceChar) &&
			(m_dcNamespaceLen < elen) &&
			elementName.startsWith(m_dcNamespace)) {
			subElem = elementName.substring(m_dcNamespaceLen);
			isDc = true;
		} else if (m_hasContent && (elemChar == m_contentNamespaceChar) &&
			(m_contentNamespaceLen < elen) &&
			elementName.startsWith(m_contentNamespace)) {
			subElem = elementName.substring(m_contentNamespaceLen);
			isDc = true;
		} else {
			return;
		}
		//#ifdef DLOGGING
		if (finestLoggable) {logger.finest("subElem=" + subElem);}
		//#endif
		switch (subElem.charAt(0)) {
			//#ifdef DITUNES
			case 'a':
				if( subElem.equals("author") ||
				    subElem.equals("artist") ) {
					m_author = parser.getText(m_convXmlEnts);
					if (!m_itunes) {
						m_itunes = (m_author.length() > 0);
					}
					//#ifdef DLOGGING
					if (finestLoggable) {logger.finest("m_author=" + m_author);}
					//#endif
				}
				break;
			case 'c':
				if( subElem.equals("creator") ) {
					m_creator = parser.getText(m_convXmlEnts);
					//#ifdef DLOGGING
					if (finestLoggable) {logger.finest("m_creator=" + m_creator);}
					//#endif
					break;
				}
			//#endif
			case 'd':
				if( subElem.equals("description") ) {
					m_description = parser.getText(m_convXmlEnts);
					//#ifdef DLOGGING
					if (finestLoggable) {logger.finest("m_description=" + m_description);}
					//#endif
				} else if( subElem.equals("date") ) {
					m_date = parser.getText();
					//#ifdef DLOGGING
					if (finestLoggable) {logger.finest("m_date=" + m_date);}
					//#endif
				}
				//#ifdef DITUNES
				else if( subElem.equals("duration") ) {
					m_duration = parser.getText();
					if (!m_itunes) {
						m_itunes = (m_duration.length() > 0);
					}
					//#ifdef DLOGGING
					if (finestLoggable) {logger.finest("m_duration=" + m_duration);}
					//#endif
				}
				//#endif
				break;
			//#ifdef DITUNES
			case 'l':
					if( subElem.equals("language") ) {
						m_language = parser.getText();
						if (!m_itunes) {
							m_itunes = (isItunes && (m_language.length() > 0));
						}
					}
					//#ifdef DLOGGING
					if (finestLoggable) {logger.finest("m_language=" + m_language);}
					//#endif
					break;
			//#endif
			case 's':
				if( subElem.equals("subtitle") ) {
					m_subtitle = parser.getText(m_convXmlEnts);
					if (!m_itunes) {
						m_itunes = (m_subtitle.length() > 0);
					}
					//#ifdef DLOGGING
					if (finestLoggable) {logger.finest("m_subtitle=" + m_subtitle);}
					//#endif
				} else if( subElem.equals("summary") ) {
					m_summary = parser.getText(m_convXmlEnts);
					if (!m_itunes) {
						m_itunes = (m_summary.length() > 0);
					}
					//#ifdef DLOGGING
					if (finestLoggable) {logger.finest("m_summary=" + m_summary);}
					//#endif
				}
				break;
			case 'e':
				if( subElem.equals("encoded") ) {
					m_encoded = parser.getText(m_convXmlEnts);
					//#ifdef DLOGGING
					if (finestLoggable) {logger.finest("m_encoded=" + m_encoded);}
					//#endif
				}
				//#ifdef DITUNES
				else if( subElem.equals("explicit") ) {
					m_itunes = true;
					String sexplicit = parser.getText();
					if (sexplicit.toLowerCase().equals("yes")) {
						m_explicit = (byte)2;
					}
					else if (sexplicit.toLowerCase().equals("clean")) {
						m_explicit = (byte)1;
					}
					else if (sexplicit.toLowerCase().equals("no")) {
						m_explicit = (byte)0;
					}
					else {
						m_explicit = (byte)-1;
					}
					//#ifdef DLOGGING
					if (finestLoggable) {logger.finest("m_explicit=" + m_explicit);}
					//#endif
				}
				//#endif
				break;
			default:
		}
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

    public void setHasExt(boolean hasExt) {
        this.m_hasExt = hasExt;
    }

    public boolean isHasExt() {
        return (m_hasExt);
    }

    public void setDate(String date) {
        this.m_date = date;
    }

    public String getDate() {
        return (m_date);
    }

}
