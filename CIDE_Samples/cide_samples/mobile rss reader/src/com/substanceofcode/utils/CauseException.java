/*
 * CauseException.java
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

// Expand to define logging define
//#define DNOLOGGING
package com.substanceofcode.utils;

/**
 * Allow J2SE style exceptions
 * 
 * @author Irving Bunton
 */
public class CauseException extends
// #ifdef DLOGGING
		net.sf.jlogmicro.util.exception.CauseException
		// #else
//@		Exception
// #endif
{

	private int MAX_CAUSES = 50;
	private Throwable cause = null;
	private boolean causeSet = false;

	public CauseException() {
		super();
	}

	public CauseException(String message) {
		super(message);
		causeSet = true;
	}

	public CauseException(String message, Throwable cause) {
		super(message);
		this.cause = cause;
		causeSet = true;
	}

	public Throwable initCause(Throwable cause) {
		if (!causeSet) {
			this.cause = cause;
		}
		return super.initCause(cause);

	}

	public Throwable getCause() {
		return (cause);
	}

	public Throwable getFirstCause() {
		Throwable e = getCause();
		if (e == null) {
			return null;
		}
		for (int ic = 0; ic < MAX_CAUSES; ic++) {
			if (e instanceof CauseException) {
				CauseException ce = (CauseException) e;
				if (ce.getCause() == null) {
					return ce;
				} else {
					e = getCause();
				}
			} else if (e instanceof CauseRuntimeException) {
				CauseRuntimeException ce = (CauseRuntimeException) e;
				if (ce.getCause() == null) {
					return ce;
				} else {
					e = getCause();
				}
			} else {
				return e;
			}
		}

		return null;
	}

}
