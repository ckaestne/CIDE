/**
 * 
 */
package it.yup.xmpp;

import it.yup.xml.Element;
import it.yup.xmlstream.BasicXmlStream;
import it.yup.xmlstream.EventQuery;
import it.yup.xmpp.packets.Iq;

import java.util.Enumeration;
import java.util.Hashtable;
import java.util.Vector;

/**
 * @author luca
 *
 */
class IqManager extends IQResultListener {

	private static IqManager _instance = null;

	/**
	 * 
	 */
	private IqManager() {
		// TODO Auto-generated constructor stub
	}

	private Hashtable iqs = new Hashtable();
	
	public void streamInitialized(){
		// prepare the Iq listener
		EventQuery eq = new EventQuery("iq", new String[] { "type" },
				new String[] { "error" });
		BasicXmlStream.addEventListener(eq, _instance);
		eq = new EventQuery("iq", new String[] { "type" },
				new String[] { "result" });
		BasicXmlStream.addEventListener(eq, _instance);
	}

	public static IqManager getInstance() {
		if (_instance == null) {
			_instance = new IqManager();
		}
		return _instance;
	}

	public void handleError(Element e) {
		handleAnswer(e, false);
	}

	public void handleResult(Element e) {
		handleAnswer(e, true);
	}

	private void handleAnswer(Element e, boolean result) {
		String id = e.getAttribute(Iq.ATT_ID);
		IQResultListener listener = null;
		synchronized (iqs) {
			listener = (IQResultListener) iqs.remove(id);
			// #mdebug
//@			if (listener != null) {
//@				System.out.println("IQM Removed: " + id);
//@			}
			// #enddebug
		}
		if (listener != null) if (result) listener.handleResult(e);
		else
			listener.handleError(e);
		purge();
	}

	private void purge() {
		synchronized (iqs) {
			Enumeration en = iqs.keys();
			Vector keysToRemove = new Vector();
			while (en.hasMoreElements()) {
				Object key = (Object) en.nextElement();
				IQResultListener listener = (IQResultListener) iqs.get(key);
				if (listener.registerTime + Config.MAX_PERM_TIME < System
						.currentTimeMillis()) keysToRemove.addElement(key);
			}
			en = keysToRemove.elements();
			while (en.hasMoreElements()) {
				Object key = en.nextElement();
				iqs.remove(key);
				// #mdebug
//@				System.out.println("IQM Purged: " + key);
				// #enddebug
			}
		}
	}

	public void addRegistration(Iq iq, IQResultListener listener) {
		synchronized (iqs) {
			listener.registerTime = System.currentTimeMillis();
			iqs.put(iq.getAttribute(Iq.ATT_ID), listener);
			// #mdebug
//@			System.out.println("IQM Added: " + iq.getAttribute(Iq.ATT_ID));
			// #enddebug
		}
	}

}
