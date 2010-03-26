/*
   TODO document need super
 * URLHandler.java
 *
 * Copyright (C) 2005-2006 Tommi Laukkanen
 * Contributions from Irving Bunton
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

// Expand to define MIDP define
//#define DMIDP20
// Expand to define DJSR75 define
//#define DNOJSR75
// Expand to define memory size define
//#define DREGULARMEM
// Expand to define test define
//#define DNOTEST
// Expand to define logging define
//#define DNOLOGGING
package com.substanceofcode.rssreader.businesslogic;

import java.io.IOException;
import javax.microedition.io.ConnectionNotFoundException;
import java.io.InputStream;
import javax.microedition.io.Connector;
import javax.microedition.io.HttpConnection;
//#ifdef DMIDP20
import javax.microedition.io.HttpsConnection;
import javax.microedition.pki.CertificateException;
//#endif
import javax.microedition.io.InputConnection;
//#ifdef DJSR75
import javax.microedition.io.file.FileConnection;
//#endif

//#ifdef DLOGGING
import net.sf.jlogmicro.util.logging.Logger;
import net.sf.jlogmicro.util.logging.LogManager;
import net.sf.jlogmicro.util.logging.Level;
//#endif
import com.substanceofcode.utils.Base64;
import com.substanceofcode.utils.EncodingUtil;
import com.substanceofcode.utils.CauseException;
import cz.cacek.ebook.util.ResourceProviderME;

/**
 * Base class for HTML Handlers.
 *
 * @author Irving Bunton
 */
public abstract class URLHandler {
    
    protected boolean m_redirect = false;  // The URL is redirected
    protected String m_redirectUrl = "";  // The URL is redirected URL
    protected boolean m_needRedirect = false;  // The URL needs to be redirected
    protected String m_location; // The URL location
    protected long m_lastMod = 0L;  // Last modification
    protected InputStream m_inputStream;  // Last modification
    protected HttpConnection m_hc = null;
    protected InputConnection m_ic = null;
	//#ifdef DJSR75
    protected FileConnection m_fc = null;
	//#endif
    protected String m_contentType = null;  // Last modification
    
	//#ifdef DLOGGING
    private Logger logger = Logger.getLogger("URLHandler");
    private boolean fineLoggable = logger.isLoggable(Level.FINE);
    private boolean finerLoggable = logger.isLoggable(Level.FINER);
    private boolean finestLoggable = logger.isLoggable(Level.FINEST);
	//#endif

	public URLHandler() {
	}

    /** Open file or URL.  Give error if there is a problem with the URL/file.*/
    public void handleOpen(String url, String username, String password)
	throws IOException, Exception {
        
        try {
			if (url.startsWith("file://")) {
				//#ifdef DJSR75
				/*
				 * Open an FileConnection with the file system 
				 */
				m_fc = (FileConnection) Connector.open( url, Connector.READ );
				m_lastMod = m_fc.lastModified();
				m_inputStream = m_fc.openInputStream();
				//#else
				/*
				 * Open an InputConnection with the file system.
				 * The trick is knowing the URL.
				 */
				m_ic = (InputConnection) Connector.open( url, Connector.READ );
				m_inputStream = m_ic.openInputStream();
				//#endif
			} else if (url.startsWith("jar://")) {
				// If testing, allow opening of files in the jar.
				m_inputStream = this.getClass().getResourceAsStream( url.substring(6));
				if (m_inputStream == null) {
					new IOException("No file found in jar:  " + url);
				}
				int dotPos = url.lastIndexOf('.');
				if (dotPos >= 0) {
					m_contentType = url.substring(dotPos + 1);
				}
			} else {
				/**
				 * Open an HttpConnection or HttpsConnection with the Web server
				 * The default request method is GET
				 */
				if (url.startsWith("https:")) {
					//#ifdef DMIDP20
					 m_hc = (HttpsConnection) Connector.open( url );
					//#else
					 // If not supporting https, allow method to throw the
					 // error.  Some implementations do allow this to work.
					m_hc = (HttpConnection) Connector.open( url );
					//#endif
				} else {
					m_hc = (HttpConnection) Connector.open( url );
				}
				m_hc.setRequestMethod(HttpConnection.GET);
				/** Some web servers requires these properties */
				m_hc.setRequestProperty("User-Agent", 
						"Profile/MIDP-1.0 Configuration/CLDC-1.0");
				m_hc.setRequestProperty("Content-Length", "0");
				m_hc.setRequestProperty("Connection", "close");

				/** Add credentials if they are defined */
				if( username.length()>0) {
					/** 
					 * Add authentication header in HTTP request. Basic authentication
					 * should be formatted like this:
					 *     Authorization: Basic QWRtaW46Zm9vYmFy
					 */
					String userPass;
					Base64 b64 = new Base64();
					userPass = username + ":" + password;
					userPass = b64.encode(userPass.getBytes());
					m_hc.setRequestProperty("Authorization", "Basic " + userPass);
				}            
				int respCode = m_hc.getResponseCode();
				m_inputStream = m_hc.openInputStream();
				String respMsg = m_hc.getResponseMessage();
				m_lastMod = m_hc.getLastModified();
				m_contentType = m_hc.getHeaderField("content-type");
				m_location = m_hc.getHeaderField("location");
				//#ifdef DLOGGING
				if (fineLoggable) {logger.fine("responce code=" + respCode);}
				if (fineLoggable) {logger.fine("responce message=" + respMsg);}
				if (fineLoggable) {logger.fine("responce location=" + m_hc.getHeaderField("location"));}
				if (finestLoggable) {
					for (int ic = 0; ic < 20; ic++) {
						logger.finest("hk=" + ic + "," +
								m_hc.getHeaderFieldKey(ic));
						logger.finest("hf=" + ic + "," +
								m_hc.getHeaderField(ic));
					}
				}
				//#endif
				// Don't do HTML redirect as wa may want to process an HTML.
				if ((respCode == HttpConnection.HTTP_NOT_FOUND) ||
					 (respCode == HttpConnection.HTTP_INTERNAL_ERROR) ||
					 (respCode == HttpConnection.HTTP_FORBIDDEN)) {
					throw new IOException("HTTP error " + respCode +
							((respMsg == null) ? "" : " " + respMsg));
				}

				if ((((respCode == HttpConnection.HTTP_MOVED_TEMP) ||
					 (respCode == HttpConnection.HTTP_MOVED_PERM) ||
					 (respCode == HttpConnection.HTTP_TEMP_REDIRECT) ||
					 (respCode == HttpConnection.HTTP_SEE_OTHER)) ||
					 ((respCode == HttpConnection.HTTP_OK) &&
					  respMsg.equals("Moved Temporarily"))) && 
					 (m_location != null)) {
					m_needRedirect = true;
					return;
				}
			}
			//#ifdef DLOGGING
			if (finestLoggable) {logger.finest("m_contentType=" + m_contentType);}
			//#endif
            
        } catch(IllegalArgumentException e) {
			//#ifdef DLOGGING
			logger.severe("handleOpen possible bad url error with " + url,
						  e);
			//#endif
			if ((url != null) && url.startsWith("file://")) {
				System.err.println("Cannot process file.");
			}

            throw new CauseException("Error while parsing RSS data:  " +
									  url, e);
        } catch(ConnectionNotFoundException e) {
			//#ifdef DLOGGING
			logger.severe("handleOpen connection error with " + url, e);
			//#endif
			if ((url != null) && url.startsWith("file://")) {
				System.err.println("Cannot process file.");
			}
            throw new CauseException("Bad URL/File or protocol error while " +
									 "opening: " + url, e);
		//#ifdef DMIDP20
        } catch(CertificateException e) {
			//#ifdef DLOGGING
			logger.severe("handleOpen https security error with " + url, e);
			//#endif
			if ((url != null) && url.startsWith("file://")) {
				System.err.println("Cannot process file.");
			}
            throw new CauseException("Bad URL/File or protocol error or " +
					"certifacate error while opening: " + url, e);
		//#endif
        } catch(IOException e) {
			throw e;
        } catch(SecurityException e) {
			//#ifdef DLOGGING
			logger.severe("handleOpen security error with " + url, e);
			//#endif
			if ((url != null) && url.startsWith("file://")) {
				System.err.println("Cannot process file.");
			}
            throw new CauseException("Security error while oening " +
									 ": " + url, e);
        } catch(Exception e) {
			//#ifdef DLOGGING
			logger.severe("handleOpen internal error with " + url, e);
			//#endif
			if ((url != null) && (url.startsWith("file://"))) {
				System.err.println("Cannot process file.");
			}
            throw new CauseException("Internal error while parsing " +
									 ": " + url, e);
        } catch(Throwable t) {
			//#ifdef DLOGGING
			logger.severe("handleOpen internal error with " + url, t);
			//#endif
			t.printStackTrace();
            throw new CauseException("Internal error while parsing RSS data " +
								":l" + url, t);
        }
    }
    
	/** Read HTML and if it has links, redirect and parse the XML. */
	protected String parseHTMLRedirect(String url, InputStream is)
    throws IOException, Exception {
		//#ifdef DSMALLMEM
		if (true) throw new IOException("Error HTML not supported with this version.");
		//#else
		if (m_redirect) {
			//#ifdef DLOGGING
			logger.severe("Error 2nd redirect url:  " + url);
			//#endif
			System.out.println("Error 2nd redirect url:  " + url);
			throw new IOException("Error url " + m_redirectUrl +
					" to 2nd redirect url:  " + url);
		}
		m_redirect = true;
		m_redirectUrl = url;
		com.substanceofcode.rssreader.businessentities.RssItunesFeed[] feeds =
				HTMLLinkParser.parseFeeds(new EncodingUtil(is),
									url, null, null, true
									//#ifdef DLOGGING
									,logger,
									fineLoggable,
									finerLoggable,
									finestLoggable
									//#endif
									);
		if ((feeds == null) || (feeds.length == 0)) {
			/* Parsing HTML redirect cannot be processed. */
			IOException ie = new IOException(ResourceProviderME.get("exc.ul.rdr"));
			//#ifdef DLOGGING
			logger.severe(ie.getMessage(), ie);
			//#endif
			System.out.println(ie.getMessage());
			throw ie;
		}
		// Use last link as the site may have adds in the beginning.
		return feeds[feeds.length - 1].getUrl();
		//#endif
	}

	public void handleClose() {
		try {
			if (m_inputStream != null) m_inputStream.close();
		} catch (IOException e) { }
		try {
			if (m_hc != null) m_hc.close();
		} catch (IOException e) {
			//#ifdef DLOGGING
			logger.warning("handleOpen possible bad open url error with " +
					m_hc.getURL(), e);
			//#endif
		}
		//#ifdef DJSR75
		try {
			if (m_fc != null) m_fc.close();
		} catch (IOException e) { }
		//#endif
	}

    public void setLastMod(long m_lastMod) {
        this.m_lastMod = m_lastMod;
    }

    public long getLastMod() {
        return (m_lastMod);
    }

}
