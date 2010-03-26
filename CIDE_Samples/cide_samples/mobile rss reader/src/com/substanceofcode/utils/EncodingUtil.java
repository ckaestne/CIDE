 /*
 * EncodingUtil.java
 TODO methods for booleans
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
package com.substanceofcode.utils;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.Hashtable;
import java.util.Vector;

import com.substanceofcode.utils.CauseException;

//#ifdef DLOGGING
import net.sf.jlogmicro.util.logging.Logger;
import net.sf.jlogmicro.util.logging.Level;
//#endif

/**
 * Simple encoding handler to allow handling utf-16 and 1252.
 *
 * @author Irving Bunton Jr
 */
public class EncodingUtil {
    
	final static public boolean m_midpIso = (System.getProperty(
			"microedition.encoding").toLowerCase().startsWith("iso-8859") ||
	                                        System.getProperty(
			"microedition.encoding").toLowerCase().startsWith("iso8859"));
	final static public String m_isoEncoding = initIsoEncoding();
	final static public boolean m_midpWin = (System.getProperty(
			"microedition.encoding").toLowerCase().startsWith("cp") ||
	                                        System.getProperty(
			"microedition.encoding").toLowerCase().startsWith("windows"));
	final static public String m_winEncoding = initWinEncoding();
	final static public boolean m_midpUni = System.getProperty(
			"microedition.encoding").toLowerCase().startsWith("utf-8");
	final static String[] m_isoCommonEntities =
		{"iexcl", "cent", "pound", "curren", "yen",
		"brvbar", "sect", "uml", "copy", "ordf",
		"laquo", "not", "shy", "reg", "macr",
		"deg", "plusmn", "sup2", "sup3", "acute",
		"micro", "para", "middot", "cedil", "sup1",
		"ordm", "raquo", "frac14", "frac12", "frac34",
		"iquest"};

	final static String[] m_isoSpecialEntities =
			{"ndash", // en dash 
			"mdash", // em dash 
			"lsquo", // left single quotation mark 
			"rsquo", // right single quotation mark 
			"sbquo", // single low-9 quotation mark 
			"ldquo", // left double quotation mark 
			"rdquo", // right double quotation mark 
			"bdquo"}; // double low-9 quotation mark 

	final static char[] m_isoSpecialValues =
			{'-', // en dash 
			'-', // em dash 
			'\'', // left single quotation mark 
			'\'', // right single quotation mark 
			'\'', // single low-9 quotation mark 
			'\"', // left double quotation mark 
			'\"', // right double quotation mark 
			'\"'}; // double low-9 quotation mark 

	final static char[] m_isoCommValues = 
		{0xA1, 0xA2, 0xA3, 0xA4, 0xA5,
		0xA6, 0xA7, 0xA8, 0xA9, 0xAA,
		0xAB, 0xAC, 0xAD, 0xAE, 0xAF,
		0xB0, 0xB1, 0xB2, 0xB3, 0xB4,
		0xB5, 0xB6, 0xB7, 0xB8, 0xB9,
		0xBA, 0xBB, 0xBC, 0xBD, 0xBE,
		0xBF};

	final static String[] m_isoLatin1Entities = 
		{"Agrave", "Aacute", "Acirc", "Atilde", "Auml",
		"Aring", "AElig", "Ccedil", "Egrave", "Eacute", "Ecirc", "Euml",
		"Igrave", "Iacute", "Icirc", "Iuml", "ETH", "Ntilde", "Ograve",
		"Oacute", "Ocirc", "Otilde", "Ouml", "times", "Oslash", "Ugrave",
		"Uacute", "Ucirc", "Uuml", "Yacute", "THORN", "szlig", "agrave",
		"aacute", "acirc", "atilde", "auml", "aring", "aelig", "ccedil",
		"egrave", "eacute", "ecirc", "euml", "igrave", "iacute", "icirc",
		"iuml", "eth", "ntilde", "ograve", "oacute", "ocirc", "otilde",
		"ouml", "divide", "oslash", "ugrave", "uacute", "ucirc", "uuml",
		"yacute", "thorn", "yuml"};

	// Convert windows characters in iso 8859 control range to ISO
	// (not the actual character, but a good fix or remove if no equivalent)
	final public static char[] m_winIsoConvx80 = initWinIsoConv();

	// Convert uni chars to equivalent windows characters in the 0x80 - 0x9f
	// range.
	final public static char[] m_uniWinConvx80 = initUniWinConvx80();

	// See if windows cp-1252 is supported.
	final public static boolean m_hasWinEncoding = hasWinEncoding();
	// See if ISO8859-1 is supported.
	final public static boolean m_hasIso8859Encoding = hasIso8859Encoding();

	final private static String m_xmlEntKeys =
			"&lt;  &gt;  &nbsp;&amp; &apos;&quot;";
	final private static String[] m_xmlEntValues =
			{"<", ">", " ", "&", "'", "\""};

	// Left single quote in cp-1252 (Windows) encoding.
    public static final char CWSGL_LOW9_QUOTE = 0x82; // #130;
    public static final char CWDBL_LOW9_QUOTE = 0x84; // #132;
    public static final char CWLEFT_SGL_QUOTE = 0x91; // #145;
    public static final char CWRIGHT_SGL_QUOTE = 0x92; // #146;
    public static final char [] CAWRIGHT_SGL_QUOTE = {CWRIGHT_SGL_QUOTE};
    public static final String WRIGHT_SGL_QUOTE = new String(CAWRIGHT_SGL_QUOTE);
    public static final char CWLEFT_DBL_QUOTE = 0x93; // #147;
    public static final char CWRIGHT_DBL_QUOTE = 0x94; // #148;
    public static final char CWEN_DASH = 0x96; // #150;
    public static final char CWEM_DASH = 0x97; // #151;
	// Left single quote in Unicode (utf-16) encoding.
	// Long dash a.k.a en dash
    public static final char CEN_DASH = 0x2013;
    public static final char CEM_DASH = 0x2014;
    public static final char CLEFT_SGL_QUOTE = 0x2018;
    public static final char CRIGHT_SGL_QUOTE = 0x2019;
    public static final char [] CARIGHT_SGL_QUOTE = {CRIGHT_SGL_QUOTE};
    public static final String RIGHT_SGL_QUOTE = new String(CARIGHT_SGL_QUOTE);
    public static final char CSGL_LOW9_QUOTE = 0x201A;
    private static final char CLEFT_DBL_QUOTE = 0x201C;
    private static final char CRIGHT_DBL_QUOTE = 0x201D;
    public static final char CDBL_LOW9_QUOTE = 0x201E;
    public static final char CA_UMLAUTE = (char)228;
    private static final char CO_UMLAUTE = (char)246;
    public static final char CNON_BREAKING_SP = (char)160;
    
    private EncodingStreamReader m_encodingStreamReader;
	final private static Hashtable m_convXmlEntities = initXmlEntities();
	final private static Hashtable m_convIso88591 = initAlphaIso88591(false);
	final private static Hashtable m_convXmlIso88591 = initAlphaIso88591(true);
	final private static Hashtable m_convCp1252 = initAlphaCp1252(false);
	final private static Hashtable m_convXmlCp1252 = initAlphaCp1252(true);
    private String m_docEncoding = "";  // Default for XML is UTF-8.
	                                    // unexpected UTF-16.
    private boolean m_utf = false;  // Doc is utf.
    private boolean m_getPrologue = true;
    private boolean m_windows = false;  // True if windows code space
	final private static boolean m_convWinUni = initConvWinUni();
	static Vector m_statExcs = null; // Exceptions encountered

	Vector m_excs = null; // Exceptions encountered

	//#ifdef DTEST
    final private static boolean m_debugTrace = false;  // True if want to trace more
	//#endif
	//#ifdef DLOGGING
    final private Logger logger = Logger.getLogger("EncodingUtil");
    final private boolean fineLoggable = logger.isLoggable(Level.FINE);
    final private boolean finestLoggable = logger.isLoggable(Level.FINEST);
	//#endif
    
    /** Creates a new instance of EncodingUtil */
    public EncodingUtil(InputStream inputStream) {
		m_encodingStreamReader = new EncodingStreamReader(inputStream);
    }

	/**  Determine the encoding based on what is passed in as well
	  as if/when strings are to be further encoded.  Also decide to
	  modify bytes read.  
	 **/

    public void getEncoding(final String fileEncoding, final String encoding) {
		getEncoding(m_hasIso8859Encoding, m_isoEncoding, m_hasWinEncoding,
				m_winEncoding, fileEncoding, encoding);
	}

	/**  Determine the encoding based on what is passed in as well
	  as if/when strings are to be further encoded.  Also decide to
	  modify bytes read.  
	 **/

    public void getEncoding(final boolean hasIso8859Encoding,
			final String isoEncoding, final boolean hasWinEncoding,
			final String winEncoding, final String fileEncoding,
			final String encoding) {
		String cencoding = encoding;
        // If there is a second char, don't stop splitting until we
        // return that char as input.
        if (cencoding == null) {
           cencoding = "UTF-8";
        }
        cencoding = cencoding.toUpperCase();
		boolean modUTF16 = m_encodingStreamReader.isModUTF16();
		boolean modEncoding = m_encodingStreamReader.isModEncoding();
		m_utf = false;
		m_windows = false;
		String docEncoding = fileEncoding;
		// Only need to convert from 2 byte to 1 byte and vsa versa.
        if ((cencoding.equals("UTF-8") || cencoding.equals("UTF8"))) {
            docEncoding = "UTF-8";
            modEncoding = false;
			m_utf = true;
        } else if (cencoding.equals("UTF-16") || cencoding.equals("UTF16")) {
			// If utf-16, don't set doc encoding as we are converting the
			// bytes to single chars.
            modUTF16 = true;
			m_utf = true;
			// Don't do doc encoding as the stream reader does it.
            docEncoding = "";
		} else if (cencoding.startsWith("ISO-8859")) {
			if (hasIso8859Encoding) {
				if (isoEncoding.indexOf("-") == -1) {
					docEncoding = StringUtil.replace(cencoding, "ISO-",
							"ISO");
					docEncoding = docEncoding.replace('-', '_');
				} else {
					docEncoding = cencoding;
				}
			} else {
				docEncoding = "";
			}
			modEncoding = false;

		} else if (cencoding.startsWith("ISO8859")) {
			if (hasIso8859Encoding) {
				if (isoEncoding.indexOf("-") >= 0) {
					docEncoding = StringUtil.replace(cencoding, "ISO",
							"ISO-");
					docEncoding = docEncoding.replace('_', '-');
				} else {
					docEncoding = cencoding;
				}
			} else {
				docEncoding = "";
			}
			modEncoding = false;
		} else if (cencoding.startsWith("WINDOWS-12")) {
			if (hasWinEncoding) {
				if (winEncoding.indexOf("-") == -1) {
					docEncoding = StringUtil.replace(cencoding, "WINDOWS-",
							"Cp");
				} else {
					docEncoding = cencoding;
				}
			} else {
				docEncoding = "";
			}
			modEncoding = false;
			m_windows = true;
		} else if (cencoding.indexOf("CP-") == 0) {
			if (hasWinEncoding) {
				if (winEncoding.indexOf("-") >= 0) {
					docEncoding = StringUtil.replace(cencoding, "CP-",
							"WINDOWS-");
				} else {
					docEncoding = StringUtil.replace(cencoding, "CP-",
							"Cp");
				}
			} else {
				docEncoding = "";
			}
			modEncoding = false;
			m_windows = true;
		} else if (cencoding.startsWith("CP")) {
			if (hasWinEncoding) {
				if (winEncoding.indexOf("-") >= 0) {
					docEncoding = StringUtil.replace(cencoding, "CP",
							"WINDOWS-");
				} else {
					docEncoding = StringUtil.replace(cencoding, "CP", "Cp");
				}
			} else {
				docEncoding = "";
			}
			modEncoding = false;
			m_windows = true;
		}
		if (docEncoding.equals(fileEncoding)) {
			m_docEncoding = "";
		} else {
			m_docEncoding = docEncoding;
		}
		if (m_docEncoding.length() != 0) {
			try {
				String a = new String("a".getBytes(), m_docEncoding);
			} catch (UnsupportedEncodingException e) {
				CauseException ce = new CauseException(
						"UnsupportedEncodingException while trying to " +
						"convert doc encoding: " + m_docEncoding, e);
				if (m_excs == null) {
					m_excs = new Vector();
				}
				m_excs.addElement(ce);
				//#ifdef DLOGGING
				logger.severe(ce.getMessage(), e);
				//#endif
				System.out.println(ce.getMessage());
				// If encoding problem, use the main encoding as it is
				// close enough.
				if (m_windows) {
					if (hasWinEncoding) {
						m_docEncoding = winEncoding;
					} else {
						m_docEncoding = "";
					}
				} else if (m_utf) {
					m_docEncoding = "";
				} else {
					if (hasIso8859Encoding) {
						m_docEncoding = isoEncoding;
					} else {
						m_docEncoding = "";
					}
				}
				try {
					String a = new String("a".getBytes(), m_docEncoding);
				} catch (UnsupportedEncodingException e2) {
					CauseException ce2 = new CauseException(
							"Second unsupportedEncodingException while " +
							" trying to convert doc encoding: " +
							m_docEncoding, e2);
					m_excs.addElement(ce2);
					//#ifdef DLOGGING
					logger.severe(ce2.getMessage(), e2);
					//#endif
					System.out.println(ce2.getMessage());
					m_docEncoding = "";
				}
			}
		}
		m_encodingStreamReader.setModEncoding(modEncoding);
		m_encodingStreamReader.setModUTF16(modUTF16);

		//#ifdef DLOGGING
        if (fineLoggable) {logger.fine("hasIso8859Encoding=" + hasIso8859Encoding);}
        if (fineLoggable) {logger.fine("isoEncoding=" + isoEncoding);}
        if (fineLoggable) {logger.fine("hasWinEncoding=" + hasWinEncoding);}
        if (fineLoggable) {logger.fine("winEncoding=" + winEncoding);}
        if (fineLoggable) {logger.fine("encoding=" + encoding);}
        if (fineLoggable) {logger.fine("cencoding=" + cencoding);}
        if (fineLoggable) {logger.fine("docEncoding=" + docEncoding);}
        if (fineLoggable) {logger.fine("m_docEncoding=" + m_docEncoding);}
        if (fineLoggable) {logger.fine("fileEncoding=" + fileEncoding);}
        if (fineLoggable) {logger.fine("m_windows=" + m_windows);}
        if (fineLoggable) {logger.fine("m_utf=" + m_utf);}
        if (fineLoggable) {logger.fine("modEncoding=" + modEncoding);}
        if (fineLoggable) {logger.fine("modUTF16=" + modUTF16);}
		//#endif
    }

	/* Replace special characters with valid ones for the specified
	   encoding. */
	public static String replaceSpChars(String text, boolean isWindows,
										boolean isUtf) {
		return replaceSpChars(text, isWindows, isUtf, m_midpWin, m_midpUni);
	}

	/* Replace special characters with valid ones for the specified
	   encoding.   For callers which use an instance of this class.  */
	public String replaceSpChars(String text) {
		return replaceSpChars(text, m_windows, m_utf, m_midpWin, m_midpUni);
	}

	/* Replace special characters with valid ones for the specified
	   encoding. */
	public static String replaceSpChars(String text, final boolean isWindows,
										final boolean isUtf,
										final boolean midpWin,
										final boolean midpUni) {
		try {
			// No need to convert i diaeresis anymore as we do encoding
			// change.
			if (isWindows) {
				if (midpWin) {
					if (m_convWinUni) {
						text = replaceSpUniChars(text);
						return text;
					}
				/* If we are converting a windows doc, the windows special
				   characters are control characters in other encodings,
				   so change to ASCII. */
				} else if (m_convWinUni) {
					if (!midpUni) {
						text = replaceSpUniWinChars(text);
					}
				} else {
					char [] ctext = text.toCharArray();
					char [] ntext = new char[text.length()];
					int jc = 0;
					for (int ic = 0; ic < ctext.length; ic++) {
						final char cchr = ctext[ic];
						if ((0x80 <= (int)cchr) && ((int)cchr <= 0x9f)) {
							if (m_winIsoConvx80[(int)cchr - 0x80] != 0x01) {
								ntext[jc++] = m_winIsoConvx80[(int)cchr - 0x80];
								//#ifdef DTEST
								if (m_debugTrace) {System.out.println("array cchr,conv=" + cchr + "," + Integer.toHexString(cchr) + "," + ntext[jc - 1] + "," + Integer.toHexString(ntext[jc - 1]));}
								//#endif
							}
						} else {
							ntext[jc++] = cchr;
							//#ifdef DTEST
							if (m_debugTrace) {System.out.println("cchr,conv=" + cchr + "," + Integer.toHexString(cchr) + "," + ntext[jc - 1] + "," + Integer.toHexString(ntext[jc - 1]));}
							//#endif
						}
					}
					text = new String(ntext, 0, jc);
					//#ifdef DTEST
					if (m_debugTrace) {System.out.println( "text,len=" + text + "," + text.length());}
					//#endif
				}
			} else if (isUtf && !midpUni) {
				text = replaceSpUniChars(text);
			}
			text = text.replace(CNON_BREAKING_SP, ' ');
		} catch (Throwable t) {
			//#ifdef DLOGGING
			Logger logger = Logger.getLogger("EncodingUtil");
            logger.severe("replaceSpChars error ", t);
			//#endif
            System.out.println("replaceSpChars error " + t + "," +
					           t.getMessage());
		}
		return text;
	}

	/* Replace Unicode special characters with valid ones for Windows
	   encoding as they sometimes are valid even in iso8859_1 even though
	   it shouldn't be.  */
	public static String replaceSpUniWinChars(String text) {
		try {
			final char [] ctext = text.toCharArray();
			char [] ntext = new char[text.length()];
			int jc = 0;
			for (int ic = 0; ic < ctext.length; ic++) {
				final char c = ctext[ic];
				switch(c & 0xff00) {
					case 0x2000:
						switch(c) {
							case CEN_DASH:
								ntext[jc++] = '-';
								break;
							case CEM_DASH:
								ntext[jc++] = '-';
								break;
							case CLEFT_SGL_QUOTE:
								ntext[jc++] = '\'';
								break;
							case CRIGHT_SGL_QUOTE:
								ntext[jc++] = '\'';
								break;
							case CSGL_LOW9_QUOTE:
								ntext[jc++] = '\'';
								break;
							case CLEFT_DBL_QUOTE:
								ntext[jc++] = '\"';
								break;
							case CRIGHT_DBL_QUOTE:
								ntext[jc++] = '\"';
								break;
							case CDBL_LOW9_QUOTE:
								ntext[jc++] = '\"';
								break;
							case 0x2020:
								ntext[jc++] = 0x86;
								break;
							case 0x2021:
								ntext[jc++] = 0x87;
								break;
							case 0x2022:
								ntext[jc++] = 0x95;
								break;
							case 0x2026:
								ntext[jc++] = 0x85;
								break;
							case 0x2030:
								ntext[jc++] = 0x89;
								break;
							case 0x2039:
								ntext[jc++] = 0x8B;
								break;
							case 0x203A:
								ntext[jc++] = 0x9B;
								break;
							case 0x20AC:
								ntext[jc++] = 0x80;
								System.out.println("ic,c=" + c + "," + Integer.toHexString(ntext[jc-1]));
								break;
							default:
								ntext[jc++] = c;
								break;
						}
						break;
					default:
						ntext[jc++] = c;
						break;
				}
			}
			text = new String(ntext, 0, jc);
		} catch (Throwable t) {
			//#ifdef DLOGGING
			Logger logger = Logger.getLogger("EncodingUtil");
            logger.severe("replaceSpUniWinChars error ", t);
			//#endif
            System.out.println("replaceSpUniWinChars error " + t + "," +
					           t.getMessage());
		}
		return text;
	}

	/* Replace Unicode special characters which have Windows (cp1252)
	   equivalents into their windows equivalents except for those
	   that have simi-equivalents (e.g. en dash to regular dash)*/
	public static String replaceSpUniChars(String text) {
		text = text.replace(CSGL_LOW9_QUOTE, '\'');
		text = text.replace(CLEFT_SGL_QUOTE, '\'');
		text = text.replace(CRIGHT_SGL_QUOTE, '\'');
		text = text.replace(CLEFT_DBL_QUOTE, '\"');
		text = text.replace(CRIGHT_DBL_QUOTE, '\"');
		text = text.replace(CDBL_LOW9_QUOTE, '\"');
		text = text.replace(CEN_DASH, '-');
		text = text.replace(CEM_DASH, '-');
		return text;
	}

	/* Replace Windows special characters with simi-equivalents
	   (e.g. en dash to regular dash)*/
	public static String replaceSpWinChars(String text) {
		text = text.replace(CWSGL_LOW9_QUOTE, '\'');
		text = text.replace(CWLEFT_SGL_QUOTE, '\'');
		text = text.replace(CWRIGHT_SGL_QUOTE, '\'');
		text = text.replace(CWLEFT_DBL_QUOTE, '\"');
		text = text.replace(CWRIGHT_DBL_QUOTE, '\"');
		text = text.replace(CWDBL_LOW9_QUOTE, '\"');
		text = text.replace(CWEN_DASH, '-');
		text = text.replace(CWEM_DASH, '-');
		return text;
	}

    /* Replace all numeric entites e.g. &#228;
     *   @param  s  String to alter.
     */
    public static String replaceNumEntity( String s) {
        if (s == null)  return s;
		String snum = "";
		try {
			
			int index01 = s.indexOf( "&#" );
			char [] achar = new char[1];
			while (index01 != -1) {
				int index02 = s.indexOf( ';' , index01 );
				if (index02 == -1) {
					return s;
				}
				try {
					snum = s.substring(index01 + 2, index02);
					// TODO redo with StringBuffer?
					if (snum.length() == 0) {
						return s;
					}
					switch (snum.charAt(0)) {
						case 'x':
						case 'X':
							achar[0] = (char)Integer.parseInt(snum.substring(
										1), 16);
							break;
						default:
							achar[0] = (char)Integer.parseInt(snum);
							break;
					}
					s = s.substring(0, index01) + new String(achar) +
							  s.substring(index02 + 1);
				} catch (NumberFormatException e) {
					//#ifdef DLOGGING
					Logger logger = Logger.getLogger("EncodingUtil");
					logger.severe("replaceNumEntity NumberFormatException error  for " + snum, e);
					//#endif
					System.out.println("replaceNumEntity error " + e + "," +
									   e.getMessage());
					return s;
				}
				index01 = s.indexOf( "&#" );
			}
		} catch (Throwable t) {
			//#ifdef DLOGGING
			Logger logger = Logger.getLogger("EncodingUtil");
            logger.severe("replaceNumEntity error ", t);
			//#endif
            System.out.println("replaceNumEntity error " + t + "," +
					           t.getMessage());
		}
        return s;
    }
    
	/**
	  Replace alphabetic entities.
	  */
	public static String replaceAlphaEntities(final boolean convXmlEnts,
			String text) {
		final Hashtable m_convEntities = (m_midpWin) ?
			    (convXmlEnts ? m_convXmlCp1252 : m_convCp1252) :
				(convXmlEnts ? m_convXmlIso88591 : m_convIso88591);
		int beginPos = 0;
		int pos = -1;
		while ((pos = text.indexOf('&', beginPos)) >= 0) {
			int epos = text.indexOf(';', pos);
			if (epos < 0) {
				break;
			}
			int nbpos = text.indexOf('&', pos + 1);
			if ((nbpos >= 0) && (nbpos < epos)) {
				beginPos = nbpos;
				continue;
			}
			if ((pos + 1) == epos) {
				beginPos = epos + 1;
				continue;
			}
			String entity = text.substring(pos + 1, epos);
			Object oent = m_convEntities.get(entity);
			if (oent != null) {
				String ent = (String)oent;
				text = text.substring(0, pos) + ent + text.substring(epos + 1);
				// If we made a substitution, keep the position the same
				// as sometimes, we get a double substitution when
				// we substitute &amp; for & this may create another
				// entity (e.g. &amp;quot; becomes & &quot;)
				beginPos = pos;
			} else {
				beginPos = epos + 1;
			}
		}
		return text;
	}

	/**
	  Replace alphabetic entities.
	  */
	public static String replaceXmlEntities(String text) {
		int beginPos = 0;
		int pos = -1;
		while ((pos = text.indexOf('&', beginPos)) >= 0) {
			int epos = text.indexOf(';', pos);
			if (epos < 0) {
				break;
			}
			int nbpos = text.indexOf('&', pos + 1);
			if ((nbpos >= 0) && (nbpos < epos)) {
				beginPos = nbpos;
				continue;
			}
			if ((pos + 1) == epos) {
				beginPos = epos + 1;
				continue;
			}
			String entity = text.substring(pos, epos + 1);
			int spos = m_xmlEntKeys.indexOf(entity);
			if (spos >= 0) {
				String ent = m_xmlEntValues[spos / 6];
				text = text.substring(0, pos) + ent + text.substring(epos + 1);
				// If we made a substitution, keep the position the same
				// as sometimes, we get a double substitution when
				// we substitute &amp; for & this may create another
				// entity (e.g. &amp;quot; becomes & &quot;)
				beginPos = pos;
			} else {
				beginPos = epos + 1;
			}
		}
		return text;
	}

	/**
	  Create table of XML entities.
	  */
	public static Hashtable initXmlEntities() {
		Hashtable convEntities = new Hashtable();
		try {
			initHtmlCommEnts(convEntities);
		} catch (Throwable t) {
			//#ifdef DLOGGING
			Logger logger = Logger.getLogger("EncodingUtil");
			logger.severe("initXmlEntities", t);
			//#endif
		}
		return convEntities;
	}

	/**
	  Create table of alpha entities for iso8859-1.
	  */
	public static Hashtable initAlphaIso88591(final boolean convXmlEnts) {

		//#ifdef DTEST
		System.out.println( "m_midpIso=" + m_midpIso);
		//#endif
		final char isoLatin1Values[] =
			{0xC0, 0xC1, 0xC2, 0xC3, 0xC4,
			0xC5, 0xC6, 0xC7, 0xC8, 0xC9,
			0xCA, 0xCB, 0xCC, 0xCD, 0xCE,
			0xCF, 0xD0, 0xD1, 0xD2, 0xD3,
			0xD4, 0xD5, 0xD6, 0xD7, 0xD8,
			0xD9, 0xDA, 0xDB, 0xDC, 0xDD,
			0xDE, 0xDF, 0xE0, 0xE1, 0xE2,
			0xE3, 0xE4, 0xE5, 0xE6, 0xE7,
			0xE8, 0xE9, 0xEA, 0xEB, 0xEC,
			0xED, 0xEE, 0xEF, 0xF0, 0xF1,
			0xF2, 0xF3, 0xF4, 0xF5, 0xF6,
			0xF7, 0xF8, 0xF9, 0xFA, 0xFB,
			0xFC, 0xFD, 0xFE, 0xFF};

		Hashtable convEntities = new Hashtable();
		try {
			initEntVals(convEntities, m_isoCommonEntities, m_isoCommValues);
			initEntVals(convEntities, m_isoLatin1Entities, isoLatin1Values);
			initEntVals(convEntities, m_isoSpecialEntities, m_isoSpecialValues);
			if (convXmlEnts) {
				initHtmlCommEnts(convEntities);
			}
		} catch (Throwable t) {
			//#ifdef DLOGGING
			Logger logger = Logger.getLogger("EncodingUtil");
			logger.severe("initAlphaIso88591", t);
			//#endif
		}
		return convEntities;
	}

	/**
	  Create table of alpha entities for windows 1252.
	  */
	public static Hashtable initAlphaCp1252(final boolean convXmlEnts) {

		//#ifdef DTEST
		System.out.println( "m_midpWin=" + m_midpWin);
		//#endif
		char isoLatin1Values[] =
			{0xC0, 0xC1, 0xC2, 0xC3, 0xC4,
			0xC5, 0xC6, 0xC7, 0xC8, 0xC9,
			0xCA, 0xCB, 0xCC, 0xCD, 0xCE,
			0xCF, 0xD0, 0xD1, 0xD2, 0xD3,
			0xD4, 0xD5, 0xD6, 0xD7, 0xD8,
			0xD9, 0xDA, 0xDB, 0xDC, 0xDD,
			0xDE, 0xDF, 0xE0, 0xE1, 0xE2,
			0xE3, 0xE4, 0xE5, 0xE6, 0xE7,
			0xE8, 0xE9, 0xEA, 0xEB, 0xEC,
			0xED, 0xEE, 0xEF, 0xF0, 0xF1,
			0xF2, 0xF3, 0xF4, 0xF5, 0xF6,
			0xF7, 0xF8, 0xF9, 0xFA, 0xFB,
			0xFC, 0xFD, 0xFE, 0xFF};

		Hashtable convEntities = new Hashtable();
		try {
			/* ISO common entities have same encodings as Cp1252 */
			initEntVals(convEntities, m_isoCommonEntities, m_isoCommValues);
			initEntVals(convEntities, m_isoLatin1Entities, isoLatin1Values);
			char wm_isoSpecialValues[] =
				{CWEN_DASH, // en dash 
				CWEM_DASH, // em dash 
				CWLEFT_SGL_QUOTE, // left single quotation mark 
				CWRIGHT_SGL_QUOTE, // right single quotation mark 
				0x82, // single low-9 quotation mark 
				CWLEFT_DBL_QUOTE, // left double quotation mark 
				CWRIGHT_DBL_QUOTE, // right double quotation mark 
				0x84}; // double low-9 quotation mark 
			initEntVals(convEntities, m_isoSpecialEntities, wm_isoSpecialValues);
			if (convXmlEnts) {
				initHtmlCommEnts(convEntities);
			}
		} catch (Throwable t) {
			//#ifdef DLOGGING
			Logger logger = Logger.getLogger("EncodingUtil");
			logger.severe("initAlphaCp1252", t);
			//#endif
		}
		return convEntities;
	}

	/* Initialize entries with passed in entity strings and character
	   values turned into strings. */
	public static void initEntVals(Hashtable convEntities, String[] entities, char[] entValues) {
		try {
			//#ifdef DTEST
			System.out.println( "Entities, values len=" + entities.length + "," + entValues.length);
			//#endif
			for (int ic = 0; (ic < entities.length) && (ic < entValues.length);
					ic++) {
				char [] cvalue = {entValues[ic]};
				// Sometimes, this can produce an error in some default
				// encodings.
				try {
					String value = new String(cvalue);
					convEntities.put(entities[ic], value);
				} catch (Throwable t) {
					//#ifdef DLOGGING
					Logger logger = Logger.getLogger("EncodingUtil");
					logger.severe("initEntVals convert error bvalue=" +
							Integer.toHexString(cvalue[0]), t);
					//#endif
				}
			}
		} catch (Throwable t) {
			//#ifdef DLOGGING
			Logger logger = Logger.getLogger("EncodingUtil");
			logger.severe("initEntVals", t);
			//#endif
		}
	}

	/* Init windows (cp-1252) to Iso 8859 encoding.  This has either 1
	   if there is no equivalent (this is used to remove the equivalent char
	   from the string to be converted).  If not a 1, the character is
	   used to replace the character in the string to be converted.
	   The conversion starts at 0x80 and goes to including 0x9f.
	   */
	private static char [] initWinIsoConv() {
		char [] convTable = new char[0x9f - 0x80 + 1];
		try {
			//#ifdef DTEST
			System.out.println( "convTable.length=" + convTable.length);
			//#endif
			convTable[0x80 - 0x80] = 0x20AC; //EURO SIGN
			convTable[0x81 - 0x80] = 0x01;
			convTable[0x82 - 0x80] = '\''; //SINGLE LOW-9 QUOTATION MARK
			convTable[0x83 - 0x80] = 0x0192; //LATIN SMALL LETTER F WITH HOOK
			convTable[0x84 - 0x80] = '\"'; //DOUBLE LOW-9 QUOTATION MARK
			convTable[0x85 - 0x80] = 0x2026; //HORIZONTAL ELLIPSIS
			convTable[0x86 - 0x80] = 0x2020; //DAGGER
			convTable[0x87 - 0x80] = 0x2021; //DOUBLE DAGGER
			convTable[0x88 - 0x80] = 0x02C6; //MODIFIER LETTER CIRCUMFLEX ACCENT
			convTable[0x89 - 0x80] = 0x2030; //PER MILLE SIGN
			convTable[0x8A - 0x80] = 0x0160; //LATIN CAPITAL LETTER S WITH CARON
			convTable[0x8B - 0x80] = 0x2039; //SINGLE LEFT-POINTING ANGLE QUOTATION MARK
			convTable[0x8C - 0x80] = 0x0152; //LATIN CAPITAL LIGATURE OE
			convTable[0x8D - 0x80] = 0x01;
			convTable[0x8E - 0x80] = 0x017D; //LATIN CAPITAL LETTER Z WITH CARON
			convTable[0x8F - 0x80] = 0x01;
			convTable[0x90 - 0x80] = 0x01;
			convTable[0x91 - 0x80] = '\''; //LEFT SINGLE QUOTATION MARK
			convTable[0x92 - 0x80] = '\''; //RIGHT SINGLE QUOTATION MARK
			convTable[0x93 - 0x80] = '\"'; //LEFT DOUBLE QUOTATION MARK
			convTable[0x94 - 0x80] = '\"'; //RIGHT DOUBLE QUOTATION MARK
			convTable[0x95 - 0x80] = 0x2022; //BULLET
			convTable[0x96 - 0x80] = '-'; //EN DASH
			convTable[0x97 - 0x80] = '-'; //EM DASH
			convTable[0x98 - 0x80] = 0x02DC; //SMALL TILDE
			convTable[0x99 - 0x80] = 0x2122; //TRADE MARK SIGN
			convTable[0x9A - 0x80] = 0x0161; //LATIN SMALL LETTER S WITH CARON
			convTable[0x9B - 0x80] = 0x203A; //SINGLE RIGHT-POINTING ANGLE QUOTATION MARK
			convTable[0x9C - 0x80] = 0x0153; //LATIN SMALL LIGATURE OE
			convTable[0x9D - 0x80] = 0x01;
			convTable[0x9E - 0x80] = 0x017E; //LATIN SMALL LETTER Z WITH CARON
			convTable[0x9F - 0x80] = 0x0178; //LATIN CAPITAL LETTER Y WITH DIAERESIS
		} catch (Throwable t) {
			//#ifdef DLOGGING
			Logger logger = Logger.getLogger("EncodingUtil");
			logger.severe("initWinIsoConv", t);
			//#endif
		}
		return convTable;
	}

	/* Init unicode to windows (cp-1252).  This has either 1
	   if there is no equivalent (this is used to remove the equivalent char
	   from the string to be converted).  If not a 1, the character is
	   used to replace the character in the string to be converted.
	   The conversion starts at 0x80 and goes to including 0x9f.
	   */
	private static char [] initUniWinConvx80() {
		char [] convTable = new char[0x9f - 0x80 + 1];
		try {
			//#ifdef DTEST
			System.out.println( "convTable.length=" + convTable.length);
			//#endif
			for (int ic = 0; ic < convTable.length; ic++) {
				char cc = (char)(ic + 0x80);
				switch (cc) {
					case CWSGL_LOW9_QUOTE:
						convTable[ic] = '\'';
						break;
					case CWDBL_LOW9_QUOTE:
						convTable[ic] = '\"';
						break;
					case CWLEFT_DBL_QUOTE:
						convTable[ic] = '\"';
						break;
					case CWRIGHT_DBL_QUOTE:
						convTable[ic] = '\"';
						break;
					case CWLEFT_SGL_QUOTE:
						convTable[ic] = '\'';
						break;
					case CWEN_DASH:
						convTable[ic] = '-';
						break;
					case CWEM_DASH:
						convTable[ic] = '-';
						break;
					default:
						convTable[ic] = 0x01;
						break;
				}
			}
		} catch (Throwable t) {
			//#ifdef DLOGGING
			Logger logger = Logger.getLogger("EncodingUtil");
			logger.severe("initUniWinConvx80", t);
			//#endif
		}
		return convTable;
	}

	/* Initialize entries for XML. */
	private static void initHtmlCommEnts(Hashtable convEntities) {
		String htmlCommonEntities[] =
				{"lt", "gt", "nbsp", "amp", "apos", "quot"};
		char htmlCommonValues[] = {'<', '>', ' ', '&', '\'', '\"'};
		initEntVals(convEntities, htmlCommonEntities, htmlCommonValues);
	}

	/* Determine if creating a string converts the windows chars to
	   Unicode. */
	private static boolean initConvWinUni() {
		boolean rtn = false;
		try {
			byte[] blftSgl = {(byte)CWLEFT_SGL_QUOTE};
			try {
				String convStr = new String(blftSgl, "Cp1252");
				rtn = convStr.charAt(0) == CLEFT_SGL_QUOTE;
			} catch (UnsupportedEncodingException e) {
				//#ifdef DTEST
				System.out.println( "Unsupported encoding Cp1252");
				//#endif
				//#ifdef DLOGGING
				Logger logger = Logger.getLogger("EncodingUtil");
				logger.severe("UnsupportedEncodingException Cp1252", e);
				//#endif
				try {
					String convStr2 = new String(blftSgl, "Cp1252");
					rtn = convStr2.charAt(0) == CLEFT_SGL_QUOTE;
				} catch (UnsupportedEncodingException e2) {
					//#ifdef DTEST
					System.out.println( "Unsupported encoding WINDOWS-1252");
					//#endif
					//#ifdef DLOGGING
					logger.severe("UnsupportedEncodingException Cp1252", e2);
					//#endif
				}
			}
			//#ifdef DTEST
			System.out.println( "initConvWinUni()=" + rtn);
			//#endif
		} catch (Throwable t) {
			//#ifdef DLOGGING
			Logger logger = Logger.getLogger("EncodingUtil");
			logger.severe("initConvWinUni", t);
			//#endif
		}
		return rtn;
	}

	/* Determine ISO encoding string. */
	private static String initIsoEncoding() {
		try {
			try {
				String convStr = new String("a".getBytes(), "ISO8859_1");
				return "ISO8859_1";
			} catch (UnsupportedEncodingException e) {
				//#ifdef DTEST
				System.out.println( "Unsupported encoding ISO8859_1");
				//#endif
				//#ifdef DLOGGING
				Logger logger = Logger.getLogger("EncodingUtil");
				logger.severe("initIsoEncoding UnsupportedEncodingException ISO8859_1", e);
				//#endif
				try {
					String convStr2 = new String("a".getBytes(), "ISO-8859-1");
					return "ISO-8859-1";
				} catch (UnsupportedEncodingException e2) {
					//#ifdef DTEST
					System.out.println("initIsoEncoding Unsupported encoding ISO-8859-1");
					//#endif
					//#ifdef DLOGGING
					logger.severe("initIsoEncoding UnsupportedEncodingException ISO-8859-1", e2);
					//#endif
				}
			}
		} catch (Throwable t) {
			//#ifdef DLOGGING
			Logger logger = Logger.getLogger("EncodingUtil");
			logger.severe("initIsoEncoding initConvWinUni", t);
			//#endif
		}
		return "ISO8859_1";
	}

	/* Determine Windows encoding string. */
	private static String initWinEncoding() {
		try {
			try {
				String convStr = new String("a".getBytes(), "Cp1252");
				return "Cp1252";
			} catch (UnsupportedEncodingException e) {
				CauseException ce = new CauseException(
						"initWinEncoding UnsupportedEncodingException " +
						"while trying to convert encoding Cp1252.", e);
				if (m_statExcs == null) {
					m_statExcs = new Vector();
				}
				m_statExcs.addElement(ce);
				//#ifdef DTEST
				System.out.println(ce.getMessage());
				//#endif
				//#ifdef DLOGGING
				Logger logger = Logger.getLogger("EncodingUtil");
				logger.severe(ce.getMessage(), e);
				//#endif
				try {
					String convStr2 = new String("a".getBytes(), "WINDOWS-1252");
					return "WINDOWS-1252";
				} catch (UnsupportedEncodingException e2) {
					CauseException ce2 = new CauseException(
							"initWinEncoding second " +
							"unsupportedEncodingException while " +
							" trying to convert encoding WINDOWS-1252.", e2);
					m_statExcs.addElement(ce2);
					//#ifdef DTEST
					System.out.println(ce2.getMessage());
					//#endif
					//#ifdef DLOGGING
					logger.severe(ce2.getMessage(), e2);
					//#endif
				}
			}
		} catch (Throwable t) {
			//#ifdef DLOGGING
			Logger logger = Logger.getLogger("EncodingUtil");
			logger.severe("initWinEncoding() initConvWinUni", t);
			//#endif
		}
		return "Cp1252";
	}

	/* Determine if windows encoding is supported.  */
	public static boolean hasWinEncoding() {
		try {
			try {
				String convStr = new String("a".getBytes(), "Cp1252");
				return true;
			} catch (UnsupportedEncodingException e) {
				CauseException ce = new CauseException(
						"hasWinEncoding UnsupportedEncodingException " +
						"while trying to convert encoding Cp1252.", e);
				if (m_statExcs == null) {
					m_statExcs = new Vector();
				}
				m_statExcs.addElement(ce);
				//#ifdef DTEST
				System.out.println(ce.getMessage());
				//#endif
				//#ifdef DLOGGING
				Logger logger = Logger.getLogger("EncodingUtil");
				logger.severe(ce.getMessage(), e);
				//#endif
				try {
					String convStr2 = new String("a".getBytes(), "WINDOWS-1252");
					return true;
				} catch (UnsupportedEncodingException e2) {
					CauseException ce2 = new CauseException(
							"initWinEncoding second " +
							"unsupportedEncodingException while " +
							" trying to convert encoding WINDOWS-1252.", e2);
					m_statExcs.addElement(ce2);
					//#ifdef DTEST
					System.out.println(ce2.getMessage());
					//#endif
					//#ifdef DLOGGING
					logger.severe(ce2.getMessage(), e2);
					//#endif
				}
			}
		} catch (Throwable t) {
			//#ifdef DLOGGING
			Logger logger = Logger.getLogger("EncodingUtil");
			logger.severe("hasWinEncoding initConvWinUni", t);
			//#endif
		}
		return false;
	}

	/* Determine if iso-8859-1 encoding is supported.  */
	private static boolean hasIso8859Encoding() {
		try {
			try {
				String convStr = new String("a".getBytes(), "ISO8859_1");
				return true;
			} catch (UnsupportedEncodingException e) {
				//#ifdef DTEST
				System.out.println( "Unsupported encoding ISO8859_1");
				//#endif
				//#ifdef DLOGGING
				Logger logger = Logger.getLogger("EncodingUtil");
				logger.severe("hasIso8859Encoding UnsupportedEncodingException ISO8859_1", e);
				//#endif
				try {
					String convStr2 = new String("a".getBytes(), "ISO-8859-1");
					return true;
				} catch (UnsupportedEncodingException e2) {
					//#ifdef DTEST
					System.out.println("hasIso8859Encoding Unsupported encoding ISO-8859-1");
					//#endif
					//#ifdef DLOGGING
					logger.severe("initIsoEncoding UnsupportedEncodingException ISO-8859-1", e2);
					//#endif
				}
			}
		} catch (Throwable t) {
			//#ifdef DLOGGING
			Logger logger = Logger.getLogger("EncodingUtil");
			logger.severe("hasIso8859Encoding initConvWinUni", t);
			//#endif
		}
		return false;
	}

    public void setDocEncoding(String m_docEncoding) {
        this.m_docEncoding = m_docEncoding;
    }

    public String getDocEncoding() {
        return (m_docEncoding);
    }

    public void setEncodingStreamReader(EncodingStreamReader m_encodingStreamReader) {
        this.m_encodingStreamReader = m_encodingStreamReader;
    }

    public EncodingStreamReader getEncodingStreamReader() {
        return (m_encodingStreamReader);
    }

    public boolean isWindows() {
        return (m_windows);
    }

    public boolean isUtf() {
        return (m_utf);
    }

	//#ifdef DTEST
    public static String[] getIsoCommonEntities() {
        return (m_isoCommonEntities);
    }

    public static Hashtable getConvIso88591() {
        return (m_convIso88591);
    }

    public static Hashtable getConvCp1252() {
        return (m_convCp1252);
    }

    static public String[] getIsoSpecialEntities() {
        return (m_isoSpecialEntities);
    }

    static public String getWinEncoding() {
        return (m_winEncoding);
    }

    public static boolean isConvWinUni() {
        return (m_convWinUni);
    }

    public static boolean isHasWinEncoding() {
        return (m_hasWinEncoding);
    }

	//#endif

    static public String getIsoEncoding() {
        return (m_isoEncoding);
    }

    public Vector getExcs() {
		if (m_excs == null) {
			return new Vector();
		} else {
			return (m_excs);
		}
    }

    public static Vector getStatExcs() {
		if (m_statExcs == null) {
			return new Vector();
		} else {
			return (m_statExcs);
		}
    }

}
