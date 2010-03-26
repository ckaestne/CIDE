/*
 * HTMLParser.java
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
package com.substanceofcode.utils;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.Hashtable;

import com.substanceofcode.utils.CauseException;
import com.substanceofcode.utils.CauseMemoryException;

//#ifdef DLOGGING
import net.sf.jlogmicro.util.logging.Logger;
import net.sf.jlogmicro.util.logging.Level;
//#endif
/**
 * Simple and lightweight HTML parser without complete error handling.
 *
 * @author Irving Bunton
 */
public class HTMLParser extends XmlParser {
    
	private boolean m_headerFound = false;
	private boolean m_metaFound = false;
	private boolean m_bodyFound = false;
	//#ifdef DLOGGING
    private Logger logger = Logger.getLogger("HTMLParser");
    private boolean fineLoggable = logger.isLoggable(Level.FINE);
    private boolean finerLoggable = logger.isLoggable(Level.FINER);
    private boolean finestLoggable = logger.isLoggable(Level.FINEST);
	//#endif
	private String m_redirectUrl = "";
    
    /** Enumerations for parse function */
    public static final int REDIRECT_URL = 3;

    /** Creates a new instance of XmlParser */
    public HTMLParser(InputStream inputStream) {
		super(inputStream);
		m_defEncoding = "ISO-8859-1";
    }

    /** Creates a new instance of XmlParser */
    public HTMLParser(EncodingUtil encodingUtil) {
		super(encodingUtil);
		m_defEncoding = "ISO-8859-1";
    }

    /** Parse next element */
    protected int parseStream(InputStreamReader is)
	throws IOException, CauseException {
		int elementType = super.parseStream(is);
		if (elementType != XmlParser.ELEMENT) {
			return elementType;
		}
		if (m_bodyFound) {
			return elementType;
		} else if (m_headerFound) {
			String elementName = super.getName();
			switch (elementName.charAt(0)) {
				case 'b':
				case 'B':
					m_bodyFound = elementName.toLowerCase().equals("body");
					// Default HTML to iso-8859-1
					if (m_bodyFound) {
						if (!m_encoding_set) {
							//#ifdef DLOGGING
							if (finerLoggable) {logger.finer("Body found without encoding set.");}
							//#endif
							m_encodingUtil.getEncoding(m_fileEncoding,
									EncodingUtil.getIsoEncoding());
							m_docEncoding = m_encodingUtil.getDocEncoding();
							m_encoding_set = true;
						}
						//#ifdef DLOGGING
						if (finerLoggable) {logger.finer("Body found m_docEncoding,m_fileEncoding=" + m_docEncoding + "," + m_fileEncoding);}
						//#endif
					}
					break;
				case 'm':
				case 'M':
					m_metaFound = elementName.toLowerCase().equals("meta");
					if (m_metaFound) {
						//#ifdef DLOGGING
						if (finerLoggable) {logger.finer("Parsing <meta> tag");}
						//#endif
						String httpEquiv;
						if (((httpEquiv = getAttributeValue( "http-equiv" ))
									== null) || ( httpEquiv.length() == 0 )) {
							break;
						}
						String content;
						if (((content = getAttributeValue( "content" ))
									== null) || ( content.length() == 0 )) {
							break;
						}
						int pcharset = content.toLowerCase().indexOf(
								"charset=");
						if (pcharset >= 0) {
							String encoding = content.substring(pcharset + 8);
							//#ifdef DLOGGING
							if (finerLoggable) {logger.finer("encoding=" + encoding);}
							//#endif
							m_encodingUtil.getEncoding(m_fileEncoding,
									encoding);
							m_docEncoding = m_encodingUtil.getDocEncoding();
							m_encoding_set = true;
						} else {
							int purl = content.toLowerCase().indexOf("url=");
							if (purl < 0) {
								break;
							}
							String url = content.substring(purl + 4);
							if (url.length() > 0) {
								m_redirectUrl = url;
								//#ifdef DLOGGING
								if (finerLoggable) {logger.finer("m_redirectUrl=" + m_redirectUrl);}
								//#endif
								return REDIRECT_URL;
							}
						}
					}
					break;
				default:
			}
		} else if (!m_headerFound) {
			String elementName = super.getName();
			switch (elementName.charAt(0)) {
				case 'h':
				case 'H':
					m_headerFound = elementName.toLowerCase().equals("head");
					//#ifdef DLOGGING
					if (finerLoggable && m_headerFound) {logger.finer("m_headerFound=" + m_headerFound);}
					//#endif
					break;
				default:
			}

		}
		return elementType;
    }
    
    /** Parse next element */
    public int parse()
	throws IOException, CauseException {
		if (m_encodingStreamReader.isModEncoding()) {
			return parseStream(m_encodingStreamReader);
		} else {
			return parseStream(m_inputStream);
		}
	}
		
    /** Get element text including inner xml */
    private String getTextStream(InputStreamReader is)
	throws IOException, CauseMemoryException, CauseException {
        
		if(!m_currentElementContainsText) {
			return "";
		}
		boolean endParsing = false;
		
		String text = "";
		try {
			StringBuffer textBuffer = new StringBuffer();
			int inputCharacter;
			char c;
			char lastChars[] = {' ', ' ', ' '};
			
			char elementNameChars[] = new char[3];
			// Handle length < 3 using min.
			int elen = m_currentElementName.length();
			switch (elen) {
				case 1:
		  			elementNameChars[0] = m_currentElementName.charAt( 0 );
		  			elementNameChars[1] = '>';
					break;
				case 2:
		  			elementNameChars[0] = m_currentElementName.charAt( 0 );
		  			elementNameChars[1] = m_currentElementName.charAt( 1 );
		  			elementNameChars[2] = '>';
					break;
				default:
					m_currentElementName.toString().getChars(elen - 3, 3,
							elementNameChars, 0);
			}
			String endCurrentElement = m_currentElementName.insert(0, "</").toString();
			while (((inputCharacter = is.read()) != -1) && !endParsing) {
				c = (char)inputCharacter;
				lastChars[0] = lastChars[1];
				lastChars[1] = lastChars[2];
				lastChars[2] = c;
				//System.out.print(c);

				textBuffer.append(c);
				if( lastChars[0] == elementNameChars[0] &&
					lastChars[1] == elementNameChars[1]) {
					switch (elen) {
						case 1:
							int tlen1 = textBuffer.length();
							textBuffer.delete(tlen1 - 2, tlen1);
							endParsing = true;
							break;
						case 2:
							if (lastChars[2] == '>') {
								endParsing = true;
								int tlen2 = textBuffer.length();
								textBuffer.delete(tlen2 - 1, tlen2);
								break;
							}
						default:
							if (lastChars[2] == elementNameChars[2]) {
								if( textBuffer.toString().endsWith(endCurrentElement)) {
									endParsing = true;
								}
							}
					}
				}
			}

			if (m_docEncoding.length() == 0) {
				text = textBuffer.toString();
			} else {
				try {
					// We read the bytes in as ISO8859_1, so we must get them
					// out as that and then encode as they should be.
					if (m_fileEncoding.length() == 0) {
						text = new String(textBuffer.toString().getBytes(),
										  m_docEncoding);
					} else {
						text = new String(textBuffer.toString().getBytes(
									m_fileEncoding), m_docEncoding);
					}
				} catch (IOException e) {
					//#ifdef DLOGGING
					logger.severe("getTextStream Could not convert string from,to" + m_fileEncoding + "," + m_docEncoding, e);
					//#endif
					System.out.println("getTextStream Could not convert string " +
							"from,to=" + m_fileEncoding + "," + m_docEncoding +
							" " + e + " " + e.getMessage());
					e.printStackTrace();
					text = textBuffer.toString();
				}
			}
			textBuffer = null;
			text = StringUtil.replace(text, endCurrentElement, "");
			
			/** Handle some entities and encoded characters */
			text = StringUtil.replace(text, "<![CDATA[", "");
			text = StringUtil.replace(text, "]]>", "");
			text = EncodingUtil.replaceAlphaEntities(true, text);
			// No need to convert from UTF-8 to Unicode using replace
			// umlauts now because it is done with new String...,encoding.

			// Replace numeric entities including &#8217;, &#8216;
			// &#8220;, and &#8221;
			text = m_encodingUtil.replaceNumEntity(text);

			// Replace special chars like left quote, etc.
			text = m_encodingUtil.replaceSpChars(text);
			
		} catch (OutOfMemoryError t) {
			CauseMemoryException ce = new CauseMemoryException(
					"Unable to read text. Out of memory.", t);
//#ifdef DLOGGING
			logger.severe(ce.getMessage(), ce);
//#endif
			System.out.println("getTextStream Could not read a char run time." + t +
					           " " + t.getMessage());
			t.printStackTrace();
			throw ce;
		} catch (Throwable t) {
			CauseException ce = new CauseException("Unable to read text. " +
					"Internal error.", t);
//#ifdef DLOGGING
			logger.severe(ce.getMessage(), t);
//#endif
			System.out.println(ce.getMessage() + " " + t +
					           " " + t.getMessage());
			t.printStackTrace();
			if (m_acceptErrors) {
				return null;
			} else {
				throw ce;
			}
		}
		//#ifdef DLOGGING
		if (finerLoggable) {logger.finer("text=" + text);}
		//#endif
		return text;
    }

    /** Get element text including inner xml */
    public String getText()
	throws IOException, CauseException {
		if (m_encodingStreamReader.isModEncoding()) {
			return getTextStream(m_encodingStreamReader);
		} else {
			return getTextStream(m_inputStream);
		}
	}

    /** 
     * Get attribute value from current element 
     */
    public String getAttributeValue(String attributeName)
	throws IOException, CauseMemoryException, CauseException {
        
        
		try {
			/** Check whatever the element contains given attribute */
			String ccurrentElementData = EncodingUtil.replaceSpChars(
					EncodingUtil.replaceSpChars(
						m_currentElementData.toString(), true, false),
					false, false);
			int attributeStartIndex = ccurrentElementData.toLowerCase().indexOf(
					" " + attributeName.toLowerCase());
			if( attributeStartIndex<0 ) {
				return null;
			}
			
			/** Calculate actual value start index */
			int valueStartIndex = attributeStartIndex +
					attributeName.length() + 1;
			String attribData = ccurrentElementData.substring(
					valueStartIndex).trim();
			if (attribData.length() == 0) {
				return null;
			}
			String quote = null;
			if (attribData.charAt(0) == '=') {
				attribData = attribData.substring(1).trim();
				if (attribData.length() == 0) {
					return null;
				}
			}
			switch (attribData.charAt(0)) {
				case '\"':
					attribData = attribData.substring(1);
					if (attribData.length() == 0) {
						return null;
					}
					quote = "\"";
					break;
				case EncodingUtil.CLEFT_SGL_QUOTE:
					attribData = attribData.substring(1);
					quote = EncodingUtil.RIGHT_SGL_QUOTE;
					if (attribData.length() == 0) {
						return null;
					}
					break;
				case EncodingUtil.CWLEFT_SGL_QUOTE:
					attribData = attribData.substring(1);
					if (attribData.length() == 0) {
						return null;
					}
					quote = EncodingUtil.WRIGHT_SGL_QUOTE;
					break;
				default:
			}
			
			/** Check the attribute value end index */
			int valueEndIndex;
			if (quote != null) {
				valueEndIndex = attribData.indexOf(quote);
			} else {
				attribData = attribData.trim();
				valueEndIndex = attribData.indexOf(' ');
				int lpos = attribData.indexOf('>');
				if (lpos > 0) {
					if (valueEndIndex > 0) {
						valueEndIndex = Math.min(lpos, valueEndIndex);
					} else {
						valueEndIndex = lpos;
					}
				}

				if( valueEndIndex<0 ) {
					valueEndIndex = attribData.length();
				}
			}

			if( valueEndIndex<=0 ) {
				return null;
			}
			
			/** Parse value */
			String value = attribData.substring(0, valueEndIndex);
			if (m_docEncoding.length() != 0) {
				// We read the bytes in as ISO8859_1, so we must get them
				// out as that and then encode as they should be.
				if (m_fileEncoding.length() == 0) {
					value = new String(value.getBytes(),
									  m_docEncoding);
				} else {
					value = new String(value.getBytes(
								m_fileEncoding), m_docEncoding);
				}
			}
					
			return value;
		} catch (Throwable t) {
			CauseException ce = new CauseException(
					"Parse attribute read error. Internal error.", t);
//#ifdef DLOGGING
			logger.severe(ce.getMessage(), ce);
//#endif
			System.out.println(ce.getMessage() + " " + t + " " + t.getMessage());
			if (m_acceptErrors) {
				return null;
			} else {
				throw ce;
			}
		}
    }

    public void setMetaFound(boolean metaFound) {
        this.m_metaFound = metaFound;
    }

    public boolean isMetaFound() {
        return (m_metaFound);
    }

    public void setBodyFound(boolean bodyFound) {
        this.m_bodyFound = bodyFound;
    }

    public boolean isBodyFound() {
        return (m_bodyFound);
    }

    public void setRedirectUrl(String redirectUrl) {
        this.m_redirectUrl = redirectUrl;
    }

    public String getRedirectUrl() {
        return (m_redirectUrl);
    }

}
//#endif
