/*
 * XmlParser.java
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

// Expand to define testing define
//#define DNOTEST
// Expand to define logging define
//#define DNOLOGGING
package com.substanceofcode.utils;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.Vector;

import com.substanceofcode.utils.CauseException;
import com.substanceofcode.utils.CauseMemoryException;

//#ifdef DLOGGING
import net.sf.jlogmicro.util.logging.Logger;
import net.sf.jlogmicro.util.logging.Level;
//#endif
/**
 * Simple and lightweight XML parser without complete error handling.
 *
 * @author Tommi Laukkanen
 */
public class XmlParser {
    
    /** Current XML element name (eg. <title> = title) */
    final protected StringBuffer m_currentElementName = new StringBuffer();
	/** Element data includes element name (e.g. <title>rss) */
    final protected StringBuffer m_currentElementData = new StringBuffer();
    protected boolean m_currentElementContainsText = false;
	//#ifdef DTEST
    boolean m_debugTrace = false;  // True to add extra trace
	//#endif
	// The flag for if encoding set with BOM, prologue with encoding,
	// or meta tag for HTMLParser.
	protected boolean m_encoding_set = false; // The flag for if encoding set
    // Allow some errors for get text and get attribute
    protected boolean m_acceptErrors = true;  // Allow some errors 
    protected String m_fileEncoding = "ISO8859_1";  // See EncodingUtil
    protected String m_docEncoding = "";  // See EncodingUtil
    protected String m_defEncoding = "UTF-8";  // Default doc encoding
    protected EncodingUtil m_encodingUtil = null;
    protected EncodingStreamReader m_encodingStreamReader;
	protected InputStreamReader m_inputStream;
    private String [] m_namespaces = null;
    private boolean m_getPrologue = true;
	//#ifdef DLOGGING
    private Logger logger = Logger.getLogger("XmlParser");
    final private boolean fineLoggable = logger.isLoggable(Level.FINE);
    final private boolean finerLoggable = logger.isLoggable(Level.FINER);
    final private boolean finestLoggable = logger.isLoggable(Level.FINEST);
	//#endif
    
    /** Enumerations for parse function */
    public static final int END_DOCUMENT = 0;
    public static final int ELEMENT = 1;
    public static final int PROLOGUE = 2;
    
    /** Creates a new instance of XmlParser */
    public XmlParser(InputStream inputStream) {
		this(new EncodingUtil(inputStream));
    }

    /** Creates a new instance of XmlParser */
    public XmlParser(EncodingUtil encodingUtil) {
		this.m_encodingUtil = encodingUtil;
		m_encodingStreamReader =
			m_encodingUtil.getEncodingStreamReader();
		m_fileEncoding = m_encodingStreamReader.getFileEncoding();
		m_inputStream = m_encodingStreamReader.getInputStream();
    }

    /** Parse next element */
    protected int parseStream(InputStreamReader is)
	throws IOException, CauseMemoryException, CauseException {
		StringBuffer inputBuffer = new StringBuffer();
		
		boolean parsingElementName = false;
		boolean elementFound = false;
		boolean elementStart = false;
		boolean parsingElementData = false;
		boolean prologueFound = false;
				
        char c;
        int inputCharacter = is.read();
		try {
			while ((inputCharacter != -1) && !elementFound) {
                c = (char)inputCharacter;
				
				if (elementStart) {
					switch (c) {
						case '/':
							parsingElementName = false;
							break;
						// If we get ? or ! after '<' this is not an
						// element, it's a comment or prologe.
						case '?':
						case '!':
							if(m_currentElementData.charAt(m_currentElementData.length()-1)=='<') {
								parsingElementName = false;
								// If we find <? and we're looking for the prologue,
								// set flag.
								if (m_getPrologue && (c == '?')) {
									prologueFound = true;
								}
							}
							break;
						default:
							break;
					}
				}
				if(parsingElementName) {
					// Determine if we have found the end of the element
					// name and thus started element data.
					switch (c) {
						case ':':
							// For specified namespace, put it into element name
							if ((m_namespaces != null) &&
								(((m_namespaces.length >= 1) &&
								 m_namespaces[0].equals(
									m_currentElementName.toString())) ||
								((m_namespaces.length >= 2) &&
								m_namespaces[1].equals(
									m_currentElementName.toString())) ||
								((m_namespaces.length >= 3) &&
								m_namespaces[2].equals(
									m_currentElementName.toString())))) {
								m_currentElementName.append(c);
								break;
							}
							// Don't break after ':' (above) if not a part of
							// namespace as it is the end of the element
							// name.
						case ' ':
						case '/':
							parsingElementName = false;
							parsingElementData = true;
							break;
						// Finding '>' is the end of an element name,
						// but we process it below.
						case '>':
							break;
						default:
							m_currentElementName.append(c);
							break;
					}
				}              
				// We found the beginning of a tag, so we start an element
				// name.
				if(c=='<') {
					elementStart = true;
					parsingElementName = true;
					parsingElementData = true;
					m_currentElementName.setLength(0);
					m_currentElementData.setLength(0);
				}            
				// If parsing element data, add to it.
				if(parsingElementData) {
					m_currentElementData.append(c);
				}
				// If we find end tag '>' can also be the
				// end of the prologe so we check.
				if(c=='>') {
					if(m_currentElementName.length()>0) {
						elementFound = true;
						parsingElementName = false;
						// If we find XML without a prologue, need
						// to treat as default UTF-8 encoding for XML.
						if (m_getPrologue) {
							m_getPrologue = false;
							m_encodingStreamReader.setGetPrologue(false);
							// If BOM is present, use it's definition for
							// default
							if (m_encodingStreamReader.isUtfDoc()) {
								if (m_encodingStreamReader.isUtf16Doc()) {
									m_encodingUtil.getEncoding(m_fileEncoding,
											"UTF-16");
								} else {
									m_encodingUtil.getEncoding(m_fileEncoding,
											"UTF-8");
								}
								m_encoding_set = true;
							} else {
								m_encodingUtil.getEncoding(m_fileEncoding,
										m_defEncoding);
							}
							m_docEncoding = m_encodingUtil.getDocEncoding();
						}
					} else if (m_getPrologue && prologueFound) {
						// If we are looking for the prolog, now
						// we have read the end of it, so we can
						// get the encoding specified (or null which
						// defaults to UTF-8).
						// Only process actual prologes.  <?xmlstylesheet
						// is not what we want.
						if (m_currentElementData.toString().
							startsWith("<?xml ")) {
							m_getPrologue = false;
							m_encodingStreamReader.setGetPrologue(false);
							//#ifdef DLOGGING
							if (finestLoggable) {logger.finest("m_currentElementData.length()=" + m_currentElementData.length());}
							//#endif
							String cencoding = getAttributeValue("encoding");
							if (cencoding == null) {
								//#ifdef DLOGGING
								if (finestLoggable) {logger.finest("Prologue cencoding,m_defEncoding=" + cencoding + "," + m_defEncoding);}
								//#endif
								cencoding = m_defEncoding;
							} else {
								m_encoding_set = true;
							}
							m_encodingUtil.getEncoding(m_fileEncoding,
									cencoding);
							// Get doc encoding.  The encoding to translate
							// the bytes into.
							m_docEncoding = m_encodingUtil.getDocEncoding();
							return PROLOGUE;
						}
					}
				}    

				// If we have not found an element, keep parsing.
				// Otherwise, we get out of the loop.
				if(!elementFound){
                    inputCharacter = is.read();
				}
				//#ifdef DTEST
				//#ifdef DLOGGING
				if (m_debugTrace) {
					logger.finest("c=" + c);
					logger.finest("m_currentElementName=" + m_currentElementName);
					logger.finest("m_currentElementData=" + m_currentElementData);
					logger.finest("m_currentElementContainsText=" + m_currentElementContainsText);
					logger.finest("parsingElementName=" + parsingElementName);
					logger.finest("parsingElementData=" + parsingElementData);
					logger.finest("prologueFound=" + prologueFound);
					logger.finest("parsingElementData=" + parsingElementData);
					logger.finest("parsingElementData=" + parsingElementData);
					logger.finest("elementFound=" + elementFound);
					logger.finest("elementStart=" + elementStart);
				}
				//#endif
				//#endif
			}
			
			// Determine if we actually have element data or a tag
			// that ends without data/text (e.g. <br/> has no text)
			if( m_currentElementData.charAt( m_currentElementData.length()-2 )=='/' &&
				m_currentElementData.charAt( m_currentElementData.length()-1 )=='>' ) {
				m_currentElementContainsText = false;
			} else {
				m_currentElementContainsText = true;
			}
			//#ifdef DLOGGING
			if (finerLoggable) {logger.finer("m_currentElementContainsText,m_currentElementData=" + m_currentElementContainsText + "," + m_currentElementData);}
			//#endif
			
		} catch (IOException e) {
//#ifdef DLOGGING
			logger.severe("parse read error ", e);
//#endif
			System.out.println("parse read error " + e + " " + e.getMessage());
			e.printStackTrace();
			throw e;
		} catch (OutOfMemoryError e) {
//#ifdef DLOGGING
			logger.severe("Out of memory parse read error ", e);
//#endif
			System.out.println("Out of memory parse read error " + e + " " + e.getMessage());
			e.printStackTrace();
			CauseMemoryException ce = new CauseMemoryException(
					"Parse read error. Out of memory.", e);
			throw ce;
		} catch (Throwable e) {
//#ifdef DLOGGING
			logger.severe("Internal error parse read error ", e);
//#endif
			System.out.println("Internal error parse read error " + e + " " + e.getMessage());
			e.printStackTrace();
			CauseException ce = new CauseException(
					"Internal error parse read error. ", e);
			throw ce;
		}
		if( inputCharacter == -1 ) {
			return END_DOCUMENT;
		} else {
			return ELEMENT;
		}
    }
    
    /** Parse next element */
    public int parse()
	throws IOException, CauseMemoryException, CauseException {
		if (m_encodingStreamReader.isModEncoding()) {
			return parseStream(m_encodingStreamReader);
		} else {
			return parseStream(m_inputStream);
		}
	}
		
    /** Get element name */
    public String getName() {
		//#ifdef DLOGGING
		if (finerLoggable) {logger.finer("m_currentElementName=" + m_currentElementName);}
		//#endif
        return m_currentElementName.toString();
    }
    
    /** Get element text including inner xml
	  * If no text, return empty string "" */
    private String getTextStream(InputStreamReader is,
								 final boolean convXmlEnts)
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
			int elen = m_currentElementName.length();
			switch (elen) {
				case 0:
					return "";
				case 1:
					elementNameChars[0] = m_currentElementName.charAt(0);
					break;
				case 2:
					elementNameChars[0] = m_currentElementName.charAt(0);
					elementNameChars[1] = m_currentElementName.charAt(1);
					break;
				default:
					// Copy the last 3 characters indexes begin at elen -3
					// to before elen to the char array.
					m_currentElementName.toString().getChars(elen - 3, elen,
							elementNameChars, 0);
					break;
			}
			final String endCurrentElement = m_currentElementName.insert(
					0, "</").toString();
			while (((inputCharacter = is.read()) != -1) &&
					!endParsing) {
				c = (char)inputCharacter;
				lastChars[0] = lastChars[1];
				lastChars[1] = lastChars[2];
				lastChars[2] = c;
				//System.out.print(c);

				textBuffer.append(c);
				if( lastChars[0] == elementNameChars[0] &&
					lastChars[1] == elementNameChars[1] &&
					lastChars[2] == elementNameChars[2]) {
					if( textBuffer.toString().endsWith(endCurrentElement)) {
						endParsing = true;
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
			if (text.indexOf('&') >= 0) {
				text = EncodingUtil.replaceAlphaEntities(convXmlEnts, text);
				if (convXmlEnts) {
					text = EncodingUtil.replaceXmlEntities(text);
				}
				// No need to convert from UTF-8 to Unicode using replace
				// umlauts now because it is done with new String...,encoding.

				// Replace numeric entities including &#8217;, &#8216;
				// &#8220;, and &#8221;
				text = EncodingUtil.replaceNumEntity(text);
			}

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
			logger.severe(ce.getMessage(), ce);
//#endif
			System.out.println("getTextStream Could not read a char run time." + t +
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

    /** Get element text including inner xml
	  * save some time by using the normal m_inputStream when we
	  * know that we are not reading UTF-8/16. */
    public String getText()
	throws IOException, CauseMemoryException, CauseException {
		if (m_encodingStreamReader.isModEncoding()) {
			return getTextStream(m_encodingStreamReader, true);
		} else {
			return getTextStream(m_inputStream, true);
		}
	}

    /** Get element text including inner xml
	  * save some time by using the normal m_inputStream when we
	  * know that we are not reading UTF-8/16. */
    public String getText(final boolean convXmlEnts)
	throws IOException, CauseMemoryException, CauseException {
		if (m_encodingStreamReader.isModEncoding()) {
			return getTextStream(m_encodingStreamReader, convXmlEnts);
		} else {
			return getTextStream(m_inputStream,  convXmlEnts);
		}
	}

    /** 
     * Get attribute value from current element 
     */
    public String getAttributeValue(String attributeName)
	throws IOException, CauseMemoryException, CauseException {
        
		try {
			/** Check whatever the element contains given attribute */
			String ccurrentElementData = m_currentElementData.toString();
			int attributeStartIndex = ccurrentElementData.indexOf(attributeName);
			if( attributeStartIndex<0 ) {
				return null;
			}
			
			/** Calculate actual value start index */
			int valueStartIndex = attributeStartIndex + attributeName.length() + 2;
			
			/** Check the attribute value end index */
			int valueEndIndex = ccurrentElementData.indexOf('\"', valueStartIndex);
			if( valueEndIndex<0 ) {
				/** Check using windows quote account for other unexplained
				    quotes */
				if ((valueStartIndex + 1) < ccurrentElementData.length()) {
					String beginQuote = ccurrentElementData.substring(
							valueStartIndex - 1, valueStartIndex);
					valueEndIndex = ccurrentElementData.indexOf(beginQuote,
							valueStartIndex);
				}
				if( valueEndIndex<0 ) {
					return null;
				}
			}
			
			/** Parse value */
			String value = ccurrentElementData.substring(valueStartIndex, valueEndIndex);
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
			//#ifdef DLOGGING
			if (finerLoggable) {logger.finer("attribute value=" + value);}
			//#endif
					
			return value;
		} catch (OutOfMemoryError e) {
//#ifdef DLOGGING
			logger.severe("Out of memory parse attribute error ", e);
//#endif
			System.out.println("Out of memory parse attribute error " + e + " " + e.getMessage());
			e.printStackTrace();
			CauseMemoryException ce = new CauseMemoryException(
					"Parse attribute read error. Out of memory.", e);
			throw ce;
		} catch (Throwable t) {
//#ifdef DLOGGING
			logger.severe("getAttributeValue error.", t);
//#endif
			System.out.println("getAttributeValue error." + t + " " +
					           t.getMessage());
			if (m_acceptErrors) {
				return null;
			} else {
				CauseException ce = new CauseException(
						"Parse attribute read error. Internal error.", t);
				throw ce;
			}
		}
    }
    
    /** 
     * Get namesapces.  Return two dimension array with the first column
	 * the namespace and the second on the URL for the namespace.
     */
    public String [][] parseNamespaces() {
        
		try {
			/** Check whatever the element contains given attribute */
			String ccurrentElementData = m_currentElementData.toString();
			Vector vnamespaces = new Vector();
			Vector vnamesurls = new Vector();
			int nspos = 0;
			while ((nspos = ccurrentElementData.indexOf("xmlns:", nspos)) >= 0) {
				nspos+= 6;
				int eqpos = ccurrentElementData.indexOf('=', nspos);
				if (eqpos < 0) {
					continue;
				}
				String xmlns = ccurrentElementData.substring(nspos, eqpos);
				int qpos = ccurrentElementData.indexOf('\"', eqpos + 2);
				if (qpos < 0) {
					continue;
				}
				String url = ccurrentElementData.substring(eqpos + 2, qpos);
				//#ifdef DLOGGING
				if (finerLoggable) {logger.finer("xmlns,url=" + xmlns + "," + url);}
				//#endif
				vnamespaces.addElement(xmlns);
				vnamesurls.addElement(url);
			}
			if (vnamespaces.size() == 0) {
				return new String[0][0];
			}
			int vlen = vnamespaces.size();
			String [][] ns = new String[2][vlen];
			for (int ic = 0; ic < vlen; ic++) {
				ns[0][ic] = (String)vnamespaces.elementAt(ic);
				ns[1][ic] = (String)vnamesurls.elementAt(ic);
			}
			return ns;
		} catch (Throwable t) {
//#ifdef DLOGGING
			logger.severe("parseNamespaces error.", t);
//#endif
			System.out.println("parseNamespaces error." + t + " " +
					           t.getMessage());
			return new String[0][0];
		}
    }
    
    public void setNamespaces(String [] namespaces) {
        this.m_namespaces = namespaces;
    }

    public String [] getNamespaces() {
        return (m_namespaces);
    }

    public void setDocEncoding(String docEncoding) {
        this.m_docEncoding = docEncoding;
    }

    public String getDocEncoding() {
        return (m_docEncoding);
    }

    public boolean isWindows() {
        return (m_encodingUtil.isWindows());
    }

    public boolean isUtf() {
        return (m_encodingUtil.isUtf());
    }

    public EncodingUtil getEncodingUtil() {
        return (m_encodingUtil);
    }

}
