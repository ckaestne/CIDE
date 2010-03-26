/*
 * ResourceProviderME.java
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
 * This was first modified no earlier than May 27, 2008.
 *
 */

// Expand to define test define
//#define DNOTEST
// Expand to define logging define
//#define DNOLOGGING

package cz.cacek.ebook.util;

import java.io.InputStream;
import java.io.IOException;
import java.util.Hashtable;

import cz.cacek.ebook.Common;

import com.substanceofcode.utils.CauseException;
import com.substanceofcode.utils.CauseRuntimeException;
import com.substanceofcode.utils.CauseMemoryException;
import com.substanceofcode.utils.StringUtil;
import com.substanceofcode.utils.EncodingUtil;
import com.substanceofcode.utils.EncodingStreamReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;

//#ifdef DLOGGING
import net.sf.jlogmicro.util.logging.Logger;
import net.sf.jlogmicro.util.logging.Level;
//#endif

/**
 * Holds translations for RSSReader - list of resources is from XML properties
 * in packager application.
 * @author Josef Cacek [josef.cacek (at) atlas.cz]
 * @author $Author: ibuntonjr $
 * @version $Revision: 934 $
 * @created $Date: 2008-07-15 23:33:20 +0200 (Di, 15 Jul 2008) $

 */
public final class ResourceProviderME {

	/**
	 * Singleton instance of ResourceProvider.  Due to problems with MIDP,
	 * we have to have a initializer method.
	 */
	public static ResourceProviderME provider = null;
	
	private Hashtable messages;
	private Hashtable locMessages;

	//#ifdef DLOGGING
    private Logger m_logger = Logger.getLogger("ResourceProviderME");
    private boolean m_fineLoggable = m_logger.isLoggable(Level.FINE);
    private boolean m_finestLoggable = m_logger.isLoggable(Level.FINEST);
	//#endif
    
	
	private ResourceProviderME() {
		//#ifdef DTEST
		long beginMe = System.currentTimeMillis();
		System.gc();
		long beginMem = Runtime.getRuntime().freeMemory();
		//#endif
		final Hashtable tmpMsgs = new Hashtable();
		InputStream is = null;
		try {
			is = getClass().getResourceAsStream(
                    "/"+Common.DATA_FOLDER+"/" + Common.LANGUAGE_FILE);
			if (is == null) {
				throw new IOException("Unable to read file " +
                    "/"+Common.DATA_FOLDER+"/" + Common.LANGUAGE_FILE);
			}
			EncodingUtil encUtl = new EncodingUtil(is);
			EncodingStreamReader esr = encUtl.getEncodingStreamReader();
			StringBuffer inputBuffer;
			try {
				inputBuffer = esr.readFile(Common.LANGUAGE_FILE_LEN);
			} catch (IOException ex) {
				CauseException cex = new CauseException(
						"Error while reading file.", ex);
				throw cex;
			} finally {
				if (is != null) {
					try {
						is.close();
					} catch (Exception e) {
					}
				}
			}
			String text;
			/* If not UTF-8, treat it as one since we use XSL to create UTF-8.
			   I couldn't create a file with BOM, so this is the next best
			   thing.  */
			final String fileEncoding = esr.getFileEncoding();
			if (!esr.isUtfDoc()) {
				esr.setUtfDoc(true);
				encUtl.getEncoding(fileEncoding, "UTF-8");
			}
			if (esr.isUtfDoc()) {
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
						m_logger.severe("constructor Could not convert string from,to" + fileEncoding + "," + docEncoding, e);
						//#endif
						System.out.println("constructor Could not convert string from,to" + fileEncoding + "," + docEncoding);
						text = inputBuffer.toString();
					} catch (IOException e) {
						//#ifdef DLOGGING
						m_logger.severe("constructor Could not convert string from,to" + fileEncoding + "," + docEncoding, e);
						//#endif
						System.out.println("constructor Could not convert string from,to" + fileEncoding + "," + docEncoding);
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
			String prevKey = "";
			String key;
			for (int ic = 0; ic < lines.length; lines[ic] = null, ic++) {
			
				final String line = lines[ic];
				if ((line.length() == 0) || (line.charAt(0) == '#')) {
					continue;
				}
				
				int indexOfeq = line.indexOf("=");
				if (indexOfeq < 1) {
					if (indexOfeq == -1) {
						if (prevKey.length() > 0) {
							final String value = (String)tmpMsgs.get(prevKey);
							if (value == null) {
								continue;
							}
							tmpMsgs.put(prevKey, value + "\n" + line);
							//#ifdef DLOGGING
							m_logger.warning("Appending to previous key line=" + line);
							//#endif
							continue;
						}
					}
					//#ifdef DLOGGING
					m_logger.severe("Invalid key no '=' or at beginning of line=" + line);
					//#endif
					System.out.println("Invalid entry.  No key. line=" + line);
					continue;
				}
                key = line.substring(0, indexOfeq);
				prevKey = key;
                String value = line.substring(indexOfeq + 1);
				if (value.length() == 0) {
					//#ifdef DLOGGING
					m_logger.warning("No value after = or at beginning of line=" + line);
					//#endif
					System.out.println("Warning entry has no value for " +
							key);
				}
				//#ifdef DLOGGING
				if (m_finestLoggable) {m_logger.finest("key,value=" + key + "," + value);}
				//#endif
				//#ifdef DTEST
				if (tmpMsgs.containsKey(key)) {
					String msg = "ResourceProviderME constructor " +
							"duplicate key=" + key;
					System.err.println(msg);
					//#ifdef DLOGGING
					m_logger.severe(msg);
					//#endif
					continue;
				}
				//#endif
				tmpMsgs.put(key, value);
    		}
            setMessages(tmpMsgs);
		} catch (CauseMemoryException e) {
			//#ifdef DLOGGING
			m_logger.severe("constructor could not initialize.", e);
			//#endif
			CauseRuntimeException cex = new CauseRuntimeException(
					"Out of memory error loading translation.", e);
			throw cex;
		} catch (CauseException e) {
			//#ifdef DLOGGING
			m_logger.severe("constructor could not initialize.", e);
			//#endif
			CauseRuntimeException cex = new CauseRuntimeException(
					"Error loading translation.", e);
			throw cex;
		} catch (Exception e) {
			//#ifdef DLOGGING
			m_logger.severe("constructor could not initialize.", e);
			//#endif
			CauseRuntimeException cex = new CauseRuntimeException(
					"Internal error loading translation.", e);
			throw cex;
		} catch (OutOfMemoryError e) {
			//#ifdef DLOGGING
			m_logger.severe("constructor could not initialize out of memory.",
					e);
			//#endif
		} catch (Throwable e) {
			//#ifdef DLOGGING
			m_logger.severe("constructor could not initialize.", e);
			//#endif
			CauseRuntimeException cex = new CauseRuntimeException(
					"Internal error loading translation.", e);
			throw cex;
		} finally {
            if (getMessages() == null) {
				setMessages(new Hashtable());
			}
			//#ifdef DTEST
			System.gc();
			//#ifdef DLOGGING
			if (m_finestLoggable) {m_logger.finest("constructor time=" + (System.currentTimeMillis() - beginMe));}
			if (m_finestLoggable) {m_logger.finest("ResourceProviderME size=" + (beginMem - Runtime.getRuntime().freeMemory()));}
			//#endif
			System.out.println("constructor time=" + (System.currentTimeMillis() - beginMe));
			System.out.println("ResourceProviderME size=" + (beginMem - Runtime.getRuntime().freeMemory()));
			//#endif
        }
	}

  /**
   * Initialize ResourceProviderME.
   *
   * @author Irv Bunton
   */
	final public static void initialize() {
		if (provider == null) {
			provider = new ResourceProviderME();
		}
	}

	/**
	 * Returns message identified by given key.
	 * @param aKey
	 * @return message identified by given key
	 */
	public synchronized static String get(final String aKey) {
		final String tmpResult = (String)provider.getMessages().get(aKey);
		if (tmpResult == null) {
			Exception e = new Exception("ResourceProviderME.get no value for " +
					"key=" + aKey);
			System.err.println(e.getMessage());
			e.printStackTrace();
			//#ifdef DLOGGING
			Logger logger = Logger.getLogger("ResourceProviderME");
			logger.severe(e.getMessage(), e);
			//#endif
			return aKey;
		}
		return tmpResult;
	}
	
	/**
	 * Returns message identified by given key and with substitutions of
	 * parameters.
	 * @param aKey
	 * @param aParm1
	 * @return message identified by given key
	 */
	public synchronized static String get(final String aKey, String aParm1) {
		String msg = get(aKey);
		return StringUtil.replace(msg, "\\1", aParm1);
	}

	public Hashtable getMessages() {
		synchronized(this) {
			return messages;
		}
	}
    
	private void setMessages(final Hashtable aMsgs) {
		synchronized(this) {
			messages = aMsgs;
		}
	}
}
