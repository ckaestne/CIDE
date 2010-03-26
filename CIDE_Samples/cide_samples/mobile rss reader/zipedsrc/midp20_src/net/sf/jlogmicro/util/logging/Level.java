/*
 * Level.java
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

import javax.microedition.midlet.*;

public class Level {

	// UNDO 
	public static final Level OFF = new Level("OFF", 0);

	public static final Level SEVERE = new Level("SEVERE", 1);

	public static final Level WARNING = new Level("WARNING", 2);

	public static final Level INFO = new Level("INFO", 3);

	public static final Level FINE = new Level("FINE", 4);

	public static final Level FINER = new Level("FINER", 5);

	public static final Level FINEST = new Level("FINEST", 6);

	private String name;
	private int value;

	protected Level(String name, int value) {
		this.name = name;
		this.value = value;
	}

	// TODO
    public static Level parse(String name)
	throws NullPointerException, IllegalArgumentException {
		Level logLevel = null;
		if (name.equals(Level.OFF.getName())) {
			logLevel = Level.OFF;
		} else if (name.equals(Level.SEVERE.getName())) {
			logLevel = Level.SEVERE;
		} else if (name.equals(Level.WARNING.getName())) {
			logLevel = Level.WARNING;
		} else if (name.equals(Level.INFO.getName())) {
			logLevel = Level.INFO;
		} else if (name.equals(Level.FINE.getName())) {
			logLevel = Level.FINE;
		} else if (name.equals(Level.FINEST.getName())) {
			logLevel = Level.FINEST;
		} else {
			throw new IllegalArgumentException("Invalid level " + name);
		}
        return logLevel;
    }

    public String getName() {
        return (name);
    }

    public int intValue() {
        return (value);
    }

}
//#endif
