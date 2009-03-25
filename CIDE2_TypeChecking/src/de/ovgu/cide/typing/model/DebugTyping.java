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

		System.out
				.println("Requests: "
						+ debug_requests
						+ "; Cache hit ratio: "
						+ ((float) (debug_requests - debug_cache_miss) / (float) debug_requests)
						+ "; SAT problems: " + debug_satcounter
						+ " (total time: " + debug_statTimeTotal + "); Empty: "
						+ debug_emptycounter + "; Equal: " + debug_equalcounter);

	}

	public static int debug_requests = 0;
	public static int debug_cache_miss = 0;
	public static int debug_satcounter = 0;// debug only
	public static int debug_subsetcounter = 0;// debug only
	public static int debug_equalcounter = 0;// debug only
	public static int debug_emptycounter = 0;// debug only
	public static int debug_statTimeTotal = 0;// debug only

	static void printHitRatio() {
		System.out
				.println("Cache hit ratio: "
						+ ((float) (debug_requests - debug_cache_miss) / (float) debug_requests));// debug
	}

	public static void satTime(long time, Set<IFeature> source,
			Set<IFeature> target) {
		debug_statTimeTotal += time;
		System.out.println("SAT: " + time + "; " + source + "-" + target);
	}
}
