/*
 * Common.java
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

package cz.cacek.ebook;

/**
 * Class contains constants and methods common for both ebook and packager.
 * @author Josef Cacek [josef.cacek (at) atlas.cz]
 * @author $Author: ibuntonjr $
 * @version $Revision: 934 $
 * @created $Date: 2008-07-15 23:33:20 +0200 (Di, 15 Jul 2008) $
 */
public class Common {

	/**
	 * Set to true, if you want to debug your ebook
	 */
	public static final boolean DEBUG = false;
	
	/**
	 * Folder (in ebook JAR) in which all book data are stored
	 */
	public static final String DATA_FOLDER = "data";
	
	/**
	 * Name of file where list of book names is stored
	 */
	public static final String LIBRARY_FILE = "library";

	/**
	 * Language file name
	 */
	public static final String LANGUAGE_FILE = "lang.txt";

	/**
	 * Language max file name length
	 */
	public static final int LANGUAGE_FILE_LEN = 30000;

	/**
	 * Language locale key for getAppProperty
	 */
	public static final String LANGUAGE_LOCALE_KEY = "resourceme-locale";

	/**
	 * Maximal length of ID 
	 */
	public static final int MAX_ID_LENGTH = 20;

	/**
	 * Creates ID from given name
	 * @param aName name which should be transformed to ID
	 * @return ID for book
	 */
	public synchronized static String createIdFromName(String aName) {
		String tmpResult = aName.toLowerCase();
		final StringBuffer tmpSB = new StringBuffer();
		for (int j = 0; j < tmpResult.length(); j++) {
			char c = tmpResult.charAt(j);
			if ("\t\r\n ".indexOf(c) != -1) {
				tmpSB.append('_');
			} else if ("abcdefghijklmnopqrstuvwxyz0123456789-_".indexOf(c) != -1) {
				tmpSB.append(c);
			} else {
				tmpSB.append('X');
			}
		}
		if (tmpSB.length()>MAX_ID_LENGTH) {
			tmpSB.setLength(MAX_ID_LENGTH);
		}
		tmpResult = tmpSB.toString();		
		return tmpResult;
	}
	
	/**
	 * Debug function
	 * @param aWhat
	 */
	public synchronized static void debug(final String aWhat) {
		if (DEBUG) {
			System.out.println(">>>DEBUG " + aWhat);
		}
	}

	/**
	 * Debug function for errors
	 * @param aWhat
	 */
	public synchronized static void debugErr(final String aWhat) {
		if (DEBUG) {
			System.err.println(">>>ERROR " + aWhat);
		}
	}

	/**
	 * Prints error.
	 * @param anErr
	 */
	public static void error(final Object anErr) {
		System.err.print("ERROR: ");
		if (anErr instanceof Throwable) {
			((Throwable) anErr).printStackTrace();
		} else {
			System.err.println(anErr.toString());
		}
	}


	/**
	 * delay between showing next line
	 */
	public static final int AUTOSCROLL_PAUSE = 1000;
	
	/**
	 * step for setting delay between showing next line
	 */
	public static final int AUTOSCROLL_STEP = 50;
	
    public static final byte SCREEN_PAGE = 0;
    public static final byte SCREEN_POSITION = 4;


}
