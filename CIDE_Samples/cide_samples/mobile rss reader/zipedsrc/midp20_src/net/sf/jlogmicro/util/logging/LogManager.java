/*
 * LogManager.java
 *
 * Copyright (C) 2007 Irving Bunton
 * http://code.google.com/p/jlogmicro/source
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
// Expand to define CLDC define
//#define DCLDCV10
// Expand to define logging define
//#define DLOGGING
//#ifdef DLOGGING
package net.sf.jlogmicro.util.logging;

import java.util.Enumeration;
import java.util.Vector;
import java.util.Hashtable;
import javax.microedition.midlet.*;
import javax.microedition.lcdui.*;

import net.sf.jlogmicro.util.logging.Level;
import net.sf.jlogmicro.util.logging.Logger;

public class LogManager {

	public final static String LOG_PREFIX = "jlomicro-" ;
	public final static String LOG_LEVEL = LOG_PREFIX + "level";
	public final static String LOG_HANDLERS = "jlomicro-handlers" ;
	public final static String LOG_FORMATTER = "-formatter" ;
	public final static String LOG_MAX_ENTRIES = "-limit" ;
	// UNDO 
	private String name = null;
	//UNDO
	private static Form frmRoot = new Form("Root Log");
	final private static Hashtable loggers = new Hashtable();
	private static MIDlet midlet;
	private static LogManager instance;
	final private static Logger root = new Logger("root", null);

	protected LogManager() {
	}

	synchronized public boolean addLogger(Logger logger) {
		if (loggers.containsKey(logger.getName())) {
			return false;
		} else {
			loggers.put(logger.getName(), logger);
			if (logger.getParent() == null) {
				logger.setParent(root);
				logger.setUseParentHandlers(true);
			}
			return true;
		}
	}

	synchronized public Logger getLogger(String name) {
		if (loggers.containsKey(name)) {
			return (Logger)loggers.get(name);
		} else {
			return null;
		}
	}

	public synchronized void readConfiguration(MIDlet midlet) {
		LogManager.midlet = midlet;

		try {
			String slevel = midlet.getAppProperty(LOG_LEVEL);
			report(LOG_LEVEL + "=" + slevel);
			Level level = null;
			if (slevel == null) {
				level = Level.OFF;
				report("LOG_LEVEL not set, using OFF.");
			} else {
				try {
					level = Level.parse(slevel);
				} catch (IllegalArgumentException e) {
					report("Invalid log level,msg:  " + slevel + "," +
									   e.getMessage());
					level = Level.OFF;
				}
			}
			root.setLevel(level);
			String shandlers = midlet.getAppProperty(LOG_HANDLERS);
			report(LOG_HANDLERS + "= " + shandlers);
			int pos = 0;
			Vector errs = new Vector();
			if (shandlers != null) {
				do {
					shandlers = shandlers.trim();
					String chandler;
					if ((pos = shandlers.indexOf(" ")) > 0) {
						chandler = shandlers.substring(0, pos).trim();
						shandlers = shandlers.substring(pos + 1).trim();
					} else {
						chandler = shandlers.trim();
						shandlers = "";
					}
					report("chandler=" + chandler);
					if (chandler.length() == 0) {
						if (shandlers.length() == 0) {
							break;
						}
						continue;
					}
					Handler handler = null;
					try {
						try {
							handler = (Handler)getObjInstance(chandler,
									((Handler)new FormHandler()).getClass(),
									errs);
						} catch (ClassCastException cle) {
							IllegalArgumentException iae =
								new IllegalArgumentException(
								"Invalid handler not subclass " +
								chandler);
							report(iae.getMessage());
							errs.addElement(iae);
						}
						if ((handler != null) &&
								(handler instanceof BufferedHandler)) {
							String slog_max_key = LOG_PREFIX + chandler +
												  LOG_MAX_ENTRIES;
							String smaxEntries =
									midlet.getAppProperty(slog_max_key);
							report(slog_max_key + "=" + smaxEntries);
							if (smaxEntries != null) {
								try {
									int maxEntries = Integer.parseInt(
											smaxEntries);
									((BufferedHandler)handler).setMaxEntries(
										maxEntries);
								} catch (NumberFormatException e) {
									 
									IllegalArgumentException iae =
										new IllegalArgumentException(
										"Invalid max entries " + smaxEntries);
									report(iae.getMessage());
									errs.addElement(iae);
									throw iae;
								}
							}
						}
						if (handler instanceof FormHandler) {
							((FormHandler)handler).setForm(frmRoot);
						}
						root.addHandler(handler);
					} catch (IllegalArgumentException iae) {
					}
					String sformatter_key = LOG_PREFIX + chandler +
						LOG_FORMATTER;
					String sformatter = midlet.getAppProperty(sformatter_key);
					report(LOG_FORMATTER + "= " + sformatter);
					Formatter formatter = null;
					if (sformatter != null) {
						try {
							formatter = (Formatter)getObjInstance(sformatter,
									((Formatter)new SimpleFormatter(
									)).getClass(), errs);
							if (handler != null) {
								handler.setFormatter(formatter);
							}
						} catch (IllegalArgumentException iae) {
						} catch (ClassCastException cle) {
							IllegalArgumentException iae =
								new IllegalArgumentException(
								"Invalid formatter not subclass " +
								sformatter);
							report(iae.getMessage());
							errs.addElement(iae);
						}
					}

				} while (shandlers.length() > 0);
			}
			root.info("logger initialized()");
			for (Enumeration eErrs = errs.elements();
					eErrs.hasMoreElements();) {
				Exception exc = (Exception)eErrs.nextElement();
				root.severe(exc.getMessage());
			}

		} catch (Throwable t) {
			report("ERROR readConfiguration " + t.getMessage());
			t.printStackTrace();
		}
	}

	/* Write to root form so that we can debug and to stdout. */
	static private void report(String msg) {
		frmRoot.append(msg + "\n");
		System.out.println(msg);
	}

	private static Object getObjInstance(String sclass, Class expClass,
								         Vector errs)
	throws IllegalArgumentException {
		Object rtnObject = null;
		try {
			Class objClass = Class.forName(sclass);
		} catch (ClassNotFoundException e) {
			try {
				Class objClass = Class.forName(
						"net.sf.jlogmicro.util.logging." + sclass);
				rtnObject = objClass.newInstance();
			} catch (ClassNotFoundException ex) {
				IllegalArgumentException iae = new IllegalArgumentException(
						"Class not found or obfuscated " + sclass);
				report(iae.getMessage());
				errs.addElement(iae);
			} catch (InstantiationException ex) {
				IllegalArgumentException iae = new IllegalArgumentException(
						"Class cannot be instantiated.  Probably " +
						"does not have constructor without parameters " +
						sclass);
				report(iae.getMessage());
				errs.addElement(iae);
			} catch (IllegalAccessException ex) {
				IllegalArgumentException iae = new IllegalArgumentException(
						"Class cannot be instantiated.  Probably " +
						"an abstract class or interface " + sclass);
				report(iae.getMessage());
				errs.addElement(iae);
			}
		}
		if (rtnObject == null) {
			if (sclass.equals("ConsoleHandler")) {
				rtnObject = new ConsoleHandler();
			} else if (sclass.equals("FormHandler")) {
				rtnObject = new FormHandler();
			} else if (sclass.equals("RecStoreHandler")) {
				rtnObject = new RecStoreHandler();
			} else if (sclass.equals("SimpleFormatter")) {
				rtnObject = new SimpleFormatter();
			} else {
				IllegalArgumentException iae = new IllegalArgumentException(
						"Invalid class name " + sclass);
				report(iae.getMessage());
				errs.addElement(iae);
				throw iae;
			}
		}
		report("info class,instance " +
				rtnObject.getClass().getName() + "," + rtnObject);
		return rtnObject;
	}

	/* Get the instance of the LogManager manager. */
	public static synchronized LogManager getLogManager() {
        if( instance == null ) {
            instance = new LogManager();
        }
        return instance;
	}

	/* Get the messages on startup. */
	static public String[] getStartMsgs() {
		Vector vmsgs = new Vector(frmRoot.size());
		final int fsize = frmRoot.size();
		for (int ic = 0; ic < fsize; ic++) {
			Item citem = frmRoot.get(ic);
			if (citem instanceof StringItem) {
				vmsgs.addElement(((StringItem)citem).getText());
			}
		}
		String [] msgs = new String[vmsgs.size()];
		vmsgs.copyInto(msgs);
		return msgs;
	}

}
//#endif
