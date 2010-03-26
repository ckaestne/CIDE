/* Copyright (c) 2008 Bluendo S.r.L.
 * See about.html for details about license.
 *
 * $Id: MemoryLogConsumer.java 1519 2009-05-19 16:15:30Z luca $
*/

package it.yup.util;

import java.util.Vector;

public class MemoryLogConsumer implements LogConsumer {

	public Vector messages = new Vector();
	public int max_size = 30;
	private static MemoryLogConsumer consumer = null;
	
	private MemoryLogConsumer(){};
	
	public static MemoryLogConsumer getConsumer(){
		if(consumer == null) {
			consumer = new MemoryLogConsumer();
		}
		return consumer;
	};
	
	public void gotMessage(String message, int level) {
		messages.addElement(message);
		if(messages.size()>max_size) {
			messages.removeElementAt(0);
		}
	}

	public void setExiting() {
		// TODO Auto-generated method stub	
	}
}
