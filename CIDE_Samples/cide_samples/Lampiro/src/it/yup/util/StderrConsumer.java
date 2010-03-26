/* Copyright (c) 2008 Bluendo S.r.L.
 * See about.html for details about license.
 *
 * $Id: StderrConsumer.java 1028 2008-12-09 15:44:50Z luca $
*/

package it.yup.util;

public class StderrConsumer implements LogConsumer {

	public void gotMessage(String message, int level) {
		System.err.println(message);
	}

	public void setExiting() {
		// just ignore
	}

}
