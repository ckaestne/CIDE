/**
    Copyright 2010 Christian Kästner

    This file is part of CIDE.

    CIDE is free software: you can redistribute it and/or modify
    it under the terms of the GNU General Public License as published by
    the Free Software Foundation, version 3 of the License.

    CIDE is distributed in the hope that it will be useful,
    but WITHOUT ANY WARRANTY; without even the implied warranty of
    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
    GNU General Public License for more details.

    You should have received a copy of the GNU General Public License
    along with CIDE.  If not, see <http://www.gnu.org/licenses/>.

    See http://www.fosd.de/cide/ for further information.
*/

package de.ovgu.cide.typing.model;

import java.util.Set;

import de.ovgu.cide.features.IFeature;

/**
 * debug and statistics only! do not use or rely on.
 * 
 * 
 * @author ckaestne
 * 
 */
public class DebugTyping {
	public static void print() {

		System.out.println("Requests: "
				+ debug_requests
				 + "; Cache hit ratio: "
				 + ((float) (debug_requests - debug_cache_miss) / (float)
				 debug_requests)
				+ "; SAT problems: " + debug_satcounter + " (total time: "
				+ debug_statTimeTotal + "); Empty: " + debug_emptycounter
				+ "; Equal: " + debug_equalcounter);

	}

	public static void reset() {
		debug_requests = 0;
		debug_cache_miss = 0;
		debug_satcounter = 0;// debug only
		debug_subsetcounter = 0;// debug only
		debug_equalcounter = 0;// debug only
		debug_emptycounter = 0;// debug only
		debug_statTimeTotal = 0;// debug only
		profiling_resetSATCaches=true;
	}

	public static boolean profiling_resetSATCaches=false;
	public static int debug_requests = 0;
	public static int debug_cache_miss = 0;
	public static int debug_satcounter = 0;// debug only
	public static int debug_subsetcounter = 0;// debug only
	public static int debug_equalcounter = 0;// debug only
	public static int debug_emptycounter = 0;// debug only
	public static int debug_statTimeTotal = 0;// debug only


	public static void satTime(long time, Set<IFeature> source,
			Set<IFeature> target) {
		debug_statTimeTotal += time;
//		System.out.println("SAT: " + time + "; " + source + "-" + target);
	}
}
