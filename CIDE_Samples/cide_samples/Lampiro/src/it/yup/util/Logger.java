/* Copyright (c) 2008 Bluendo S.r.L.
 * See about.html for details about license.
 *
 * $Id: Logger.java 1028 2008-12-09 15:44:50Z luca $
*/

package it.yup.util;

import java.util.Vector;

public class Logger {
	
	public static int DEBUG = 0; 

	private static Vector consumers = new Vector();
	
	private Logger() {}; // forbid direct instantiation
	
	public static void addConsumer(LogConsumer consumer) {
		Logger.consumers.addElement(consumer);
	}
	
	public static void removeConsumer(LogConsumer consumer) {
		Logger.consumers.removeElement(consumer);
	}
	
	public static void log(String message, int level) {
		for(int i=0; i<Logger.consumers.size(); i++) {
			((LogConsumer)Logger.consumers.elementAt(i)).gotMessage(message, level);
		}
	}
	
	public static void log(String message) {
		log(message, Logger.DEBUG);
	}
}
