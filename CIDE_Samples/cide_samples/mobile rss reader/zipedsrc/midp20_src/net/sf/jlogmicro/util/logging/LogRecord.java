/*
 * LogRecord.java
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
import java.util.Hashtable;
import javax.microedition.midlet.*;
import net.sf.jlogmicro.util.logging.LogRecord;

public class LogRecord {

	private Level level;
	private long millis;
	private Object message;
	private String loggerName;
	private Throwable thrown;

	public LogRecord(Level level, Object message) {
		this.level = level;
		this.message = message;
	}

    public void setLevel(Level level) {
        this.level = level;
    }

    public Level getLevel() {
        return (level);
    }

    public void setMillis(long millis) {
        this.millis = millis;
    }

    public long getMillis() {
        return (millis);
    }

    public void setMessage(Object message) {
        this.message = message;
    }

    public Object getMessage() {
        return (message);
    }

    public void setLoggerName(String loggerName) {
        this.loggerName = loggerName;
    }

    public String getLoggerName() {
        return (loggerName);
    }

    public void setThrown(Throwable thrown) {
        this.thrown = thrown;
    }

    public Throwable getThrown() {
        return (thrown);
    }

}
//#endif
