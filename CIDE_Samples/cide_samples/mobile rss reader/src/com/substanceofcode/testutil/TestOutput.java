/*
 * TestOutput.java
 *
 * Copyright (C) 2008 Irving Bunton Jr
 * http://www.substanceofcode.com
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

// Expand to define MIDP define
///@DMIDPVERS@
// Expand to define test ui define
///@DTESTUIDEF@
// Expand to define logging define
///@DLOGDEF@

//#ifdef DTESTUI

package com.substanceofcode.testutil;

import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;

//#ifdef DLOGGING
import net.sf.jlogmicro.util.logging.Logger;
import net.sf.jlogmicro.util.logging.Level;
//#endif

/**
 * Write test output.
 *
 * This allows test GUIs to write out data.
 *
 * @author  Irving Bunton Jr
 * @version 1.0
 */
public class TestOutput extends OutputStreamWriter {

	static TestOutput m_output = null;
	static boolean m_sglByte = true;
	//#ifdef DLOGGING
    private Logger m_logger = Logger.getLogger("TestOutput");
    private boolean m_fineLoggable = m_logger.isLoggable(Level.FINE);
    private boolean m_finestLoggable = m_logger.isLoggable(Level.FINEST);
	//#endif

	public TestOutput(OutputStream out) {
		super(out);
	}

	public TestOutput(OutputStream out, String enc) throws UnsupportedEncodingException {
		super(out, enc);
	}

	static public void init(OutputStream out, String enc) {
		if (m_output == null) {
		/* UNDO
		try {
			m_output = new TestOutput(out, enc);
			if (enc.toUpperCase().equals("UTF-8")) {
				m_sglByte = false;
			} else if (enc.toUpperCase().equals("UTF-16")) {
				m_sglByte = false;
			}
		} catch (UnsupportedEncodingException e) {
			System.out.println("Encoding error: " + e.getMessage());
			*/
			m_output = new TestOutput(out);
			if (System.getProperty(
					"microedition.encoding").toLowerCase().
					startsWith("utf")) {
				m_sglByte = false;
			}
		//UNDO }
			println("m_sglByte=" + m_sglByte);
		}
	}

	static public void println(String line) {
		final String oline = getOutputLine(line);
		if (m_output == null) {
			System.out.println(oline);
		} else {
			outputLine(oline + "\n");
		}
	}

	static public void outputLine(final String line) {
		try {
			m_output.write(line);
		} catch (IOException e) {
			System.out.println(line);
		}
	}

	private static String getOutputLine(final String line) {
		if (m_sglByte) {

			StringBuffer sb = new StringBuffer();
			final int slen = line.length();
			char[] cline = line.toCharArray();
			for (int ic = 0; ic < slen; ic++) {
				if (cline[ic] <= 0xff) {
					sb.append(cline[ic]);
				} else {
					sb.append("(" + Integer.toHexString(cline[ic]) + ")");
				}
			}
			return sb.toString();
		}
		return line;
	}

}

//#endif
