/*
 * 20:25:20 20/05/99
 *
 * Taken from the Java Shell: Utilities with minor modifications.
 * (C)1999 Romain Guy, Osvaldo Pinali Doederlein.
 *
 * LICENSE
 * =======
 * This program is free software; you can redistribute it and/or
 * modify it under the terms of the GNU General Public License
 * as published by the Free Software Foundation; either version 2
 * of the License, or any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program; if not, write to the Free Software
 * Foundation, Inc., 59 Temple Place - Suite 330, Boston, MA  02111-1307, USA.
 *
 * CHANGES
 * =======
 * 1.0.8 - Filled the listRoots method                   (Romain & Osvaldo)
 * 1.0.7 - Several bug fixes in constructPath            (Romain)
 * 1.0.6 - Split JDK1.1/1.2                              (Osvaldo)
 * 1.0.5 - Important bug fix in constructPath(String)    (Romain)
 * 1.0.4 - Added getSize(Enumeration)                    (Osvaldo)
 * 1.0.3 - Changed sortStrings bubble-sort algorithm to  (Romain)
 *         quick-sort (James Gosling)
 * 1.0.2 - Fixed two little bug in constructPath(String) (Romain)
 * 1.0.1 - Added listFiles(String[], boolean)            (Romain)
 *       - Removed unecessary createWhiteSpace(int)      (Romain)
 *       - Modified getWildCardMatches(String, boolean)  (Romain)
 *       - Slighty improved javadoc comments             (Romain)
 * 1.0.0 - Initial release.                              (Romain & Osvaldo)
 *
 * LINKS
 * =====
 * Contact: mailto@osvaldo.visionnaire.com.br
 * Site #1: http://www.geocities.com/ResearchTriangle/Node/2005/
 * Site #2: http://student.vub.ac.be/~opinalid/
 * Source found at
 * http://www.koders.com/java/fid123AC940B58E5ED6E60BAC5685D7FFC0E3484875.aspx?s=mailto%40osvaldo.visionnaire.com.br
 */

// Expand to define logging define
//#define DNOLOGGING

package com.substanceofcode.utils;

import java.util.Vector;

//#ifdef DLOGGING
import net.sf.jlogmicro.util.logging.Logger;
import net.sf.jlogmicro.util.logging.LogManager;
import net.sf.jlogmicro.util.logging.Level;
//#endif

public class SortUtil {

	//#ifdef DLOGGING
    private Logger logger = Logger.getLogger(this.getClass().getName());
    private boolean fineLoggable = logger.isLoggable(Level.FINE);
    private boolean finerLoggable = logger.isLoggable(Level.FINER);
    private boolean finestLoggable = logger.isLoggable(Level.FINEST);
	//#endif

/**
   * Quick sort an array of dates (long time values) using indexed sort.
   * @param indexes Indexes to be sorted
   * @param lngValues long values to be sorted
   * @param lo0 Lower bound
   * @param hi0 Higher bound
   */

	public static void sortLongs(int [] indexes, long [] lngValues, int lo0, int hi0) {
		long mid;

		Vector vlos = new Vector();
		vlos.addElement(new Integer(lo0));
		Vector vhis = new Vector();
		vhis.addElement(new Integer(hi0));

		for (int ic = 0; ic < vlos.size(); ic++) {
			lo0 = ((Integer)vlos.elementAt(ic)).intValue();
			int lo = lo0;
			hi0 = ((Integer)vhis.elementAt(ic)).intValue();
			int hi = hi0;
			if (hi0 > lo0) {

				mid = lngValues[indexes[(lo0 + hi0) / 2]];

				while (lo <= hi) {

					while ((lo < hi0) && (lngValues[indexes[lo]] < mid)) {
						++lo;
					}

					while ((hi > lo0) && (lngValues[indexes[hi]] > mid)) {
						--hi;
					}

					if (lo <= hi) {
						int swap;
						swap = indexes[lo];
						indexes[lo] = indexes[hi];
						indexes[hi] = swap;
						++lo;
						--hi;
					}
				}

				//#ifdef DLOGGING
				Logger logger = Logger.getLogger("com.substanceofcode.utilsSortUtil");
				logger.finest("lo,lo0,hi0,hi,mid=" + lo +"," + lo0 + "," + hi0 + "," + hi + "," + mid);
				/*
				if (finestLoggable) {
					for (int ic = lo0; ic < hi0; ic++) {
						logger.finer("ic,lngValues=" + ic +"," + lngValues[indexes[ic]);
					}
				}
				*/
				//#endif
				if (lo0 < hi) {
					vlos.addElement(new Integer(lo0));
					vhis.addElement(new Integer(hi));
				}

				if (lo < hi0) {
					vlos.addElement(new Integer(lo));
					vhis.addElement(new Integer(hi0));
				}
			}
		}
	}

}
