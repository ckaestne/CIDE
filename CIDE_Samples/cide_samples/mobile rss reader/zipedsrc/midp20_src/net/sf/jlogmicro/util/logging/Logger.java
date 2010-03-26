/*
 * Logger.java
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

import java.util.Vector;
import java.util.Enumeration;
import javax.microedition.midlet.*;
import javax.microedition.lcdui.*;

import net.sf.jlogmicro.util.logging.Handler;
import net.sf.jlogmicro.util.logging.Level;
import net.sf.jlogmicro.util.logging.LogRecord;
import net.sf.jlogmicro.util.logging.LogManager;

public class Logger {

	// UNDO 
	private String name = null;
	private Level level = null;
	private Logger parent;
    private boolean useParentHandlers = false;
    private boolean fineLoggable = false;
    private boolean finerLoggable = false;
    private boolean finestLoggable = false;
    private boolean infoLoggable = false;
    private boolean warningLoggable = false;
    private boolean severeLoggable = false;
    private String resource;
    private Vector handlers;

	protected Logger(String name, String resource) {
		this.name = name;
		this.resource = resource;
	}

	static public Logger getLogger(String name, String resource) {
		Logger logger;
		if ((logger = LogManager.getLogManager().getLogger(name)) == null) {
			logger = new Logger(name, resource);
			if (!LogManager.getLogManager().addLogger(logger)) {
				logger = LogManager.getLogManager().getLogger(name);
			}
		}
		return logger;
	}

	static public Logger getLogger(String name) {
		return getLogger(name, null);
	}

	public synchronized boolean isLoggable(Level level) {
		Level actLlevel = getActLevel();
		if (actLlevel == null) {
			return false;
		} else {
			return (level.intValue() <= actLlevel.intValue());
		}
	}

	private boolean isCurrLoggable(Level level) {
		if (this.level == null) {
			return false;
		} else {
			return (level.intValue() <= this.level.intValue());
		}
	}

	private boolean isParentLoggable(Level level) {
		if (parent == null) {
			return false;
		}
		Level plevel = parent.getActLevel();
		if (plevel == null) {
			return false;
		}
		if (level.intValue() <= plevel.intValue()) {
			return true;
		} else {
			return false;
		}
	}

	public synchronized void addHandler(Handler handler) {
		if (handlers == null) {
			handlers = new Vector();
		}
		if (handlers.indexOf(handler) < 0) {
			handlers.addElement(handler);
		}
	}

	public synchronized void removeHandler(Handler handler) {
		if (handlers.indexOf(handler) >= 0) {
			handlers.removeElement(handler);
		}
	}

	private synchronized void publish(Level level, Object msg,
			                          Throwable thrown) {
		LogRecord rec = new LogRecord(level, msg);
		rec.setMillis(System.currentTimeMillis());
		rec.setLoggerName(name);
		rec.setThrown(thrown);
		publish(rec);
	}

	private synchronized void publish(LogRecord rec) {
		if (handlers != null) {
			Enumeration ehandlers = handlers.elements();
			for (;ehandlers.hasMoreElements();) {
				((Handler)ehandlers.nextElement()).publish(rec);
			}
		}
		if (useParentHandlers && (parent != null)) {
			parent.publish(rec);
		}
	}

	public synchronized void info(Object msg) {
		if (infoLoggable || isParentLoggable(Level.INFO)) {
			publish(Level.INFO, msg, null);
		}
	}

	public synchronized void info(Object msg, Throwable thrown) {
		if (infoLoggable || isParentLoggable(Level.INFO)) {
			publish(Level.INFO, msg, thrown);
		}
	}

	public synchronized void warning(Object msg) {
		if (warningLoggable || isParentLoggable(Level.WARNING)) {
			publish(Level.WARNING, msg, null);
		}
	}

	public synchronized void warning(Object msg, Throwable thrown) {
		if (warningLoggable || isParentLoggable(Level.WARNING)) {
			publish(Level.WARNING, msg, thrown);
		}
	}

	public synchronized void severe(Object msg) {
		if (severeLoggable || isParentLoggable(Level.SEVERE)) {
			publish(Level.SEVERE, msg, null);
		}
	}

	public synchronized void severe(Object msg, Throwable thrown) {
		if (severeLoggable || isParentLoggable(Level.SEVERE)) {
			publish(Level.SEVERE, msg, thrown);
		}
	}

	public synchronized void fine(Object msg) {
		if (fineLoggable || isParentLoggable(Level.FINE)) {
			publish(Level.FINE, msg, null);
		}
	}

	public synchronized void fine(Object msg, Throwable thrown) {
		if (fineLoggable || isParentLoggable(Level.FINE)) {
			publish(Level.FINE, msg, thrown);
		}
	}

	public synchronized void finer(Object msg) {
		if (finerLoggable || isParentLoggable(Level.FINER)) {
			publish(Level.FINER, msg, null);
		}
	}

	public synchronized void finer(Object msg, Throwable thrown) {
		if (finerLoggable || isParentLoggable(Level.FINER)) {
			publish(Level.FINER, msg, thrown);
		}
	}

	public synchronized void finest(Object msg) {
		if (finestLoggable || isParentLoggable(Level.FINEST)) {
			publish(Level.FINEST, msg, null);
		}
	}

	public synchronized void finest(Object msg, Throwable thrown) {
		if (finestLoggable || isParentLoggable(Level.FINEST)) {
			publish(Level.FINEST, msg, thrown);
		}
	}

	public synchronized void setLevel(Level level) {
		this.level = level;
		infoLoggable = isCurrLoggable(Level.INFO);
		severeLoggable = isCurrLoggable(Level.SEVERE);
		warningLoggable = isCurrLoggable(Level.WARNING);
		fineLoggable = isCurrLoggable(Level.FINE);
		finerLoggable = isCurrLoggable(Level.FINER);
		finestLoggable = isCurrLoggable(Level.FINEST);
	}

	public synchronized Level getLevel() {
		return level;
	}

	public synchronized Level getActLevel() {
		if ((level == null) && (parent != null)) {
			return parent.getActLevel();
		} else {
			return level;
		}
	}

    public synchronized String getName() {
        return (name);
    }

    public synchronized void setParent(Logger parent) {
        this.parent = parent;
    }

    public synchronized Logger getParent() {
        return (parent);
    }

    public synchronized Vector getHandlers() {
        return (handlers);
    }

    public synchronized void setUseParentHandlers(boolean useParentHandlers) {
        this.useParentHandlers = useParentHandlers;
    }

    public synchronized boolean isUseParentHandlers() {
        return (useParentHandlers);
    }

}
//#endif
