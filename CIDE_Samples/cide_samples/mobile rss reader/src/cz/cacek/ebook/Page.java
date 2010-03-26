/*
 * Page.java
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
 * This was first modified no earlier than May 27, 2008.
 *
 */

// Expand to define MIDP define
//#define DMIDP20
// Expand to define test define
//#define DNOTEST

//#ifdef DMIDP20
package cz.cacek.ebook;

import java.io.DataInputStream;
import java.io.EOFException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Vector;

import javax.microedition.lcdui.List;

import cz.cacek.ebook.Common;

/**
 * Class for representation of one page in the page.
 * @author Tom� Darmovzal [tomas.darmovzal (at) seznam.cz]
 * @author Josef Cacek [josef.cacek (at) atlas.cz]
 * @author $Author: ibuntonjr $
 * @version $Revision: 934 $
 * @created $Date: 2008-07-15 23:33:20 +0200 (Di, 15 Jul 2008) $
 */
public class Page {

	protected int size;

	private PageHolder buffer;

	private int index;

	/**
	 * Class which reads part of page to buffer.
	 * @author Josef Cacek
	 */
	static class PageHolder {
		private char[] buffer;
		volatile int bufferLength;

		/**
		 * Constructor in which one part of page is readed to buffer
		 * @param aPart number of part which should be read
		 */
		public PageHolder(final String sbufffer) {
			buffer = sbufffer.toCharArray();
			bufferLength = buffer.length;
		}

		public char[] getBuffer() {
			synchronized(this) {
				return buffer;
			}
		}

		private void setBuffer(char[] aBuff) {
			synchronized(this) {
				buffer = aBuff;
			}
		}
	}

	/**
	 * Creates the page from the string.
	 * 
	 * @param spage the string of the page
	 */
	public Page(String spage) {
		size = spage.length();
		buffer = new PageHolder(spage);
	}

	/**
	 * Returns character and moves bufferOffset backward
	 * 
	 * @return current character
	 * @throws Exception
	 */
	public char readPrev() throws Exception {
		final char tmpChr = readCurr();
		index--;
		return tmpChr;
	}

	/**
	 * Returns character and moves bufferOffset ahead
	 * 
	 * @return current character
	 * @throws Exception
	 */
	public char readNext() throws Exception {
		char tmpChr = readCurr();
		index++;
		return tmpChr;
	}

	/**
	 * Returns character from buffer (on bufferOffset position). If it's
	 * necessary reads new data to buffer (prev/next).
	 * 
	 * @throws Exception
	 */
	protected char readCurr() throws Exception {
		if (getPosition() == size || getPosition() == -1) {
			//#ifdef DTEST
			Common.debug("readCurr EOFException");
			//#endif
			throw new EOFException();
		}
		if (index == -1) {
			rollPrev();
			index = buffer.bufferLength - 1;
		} else if (index == buffer.bufferLength) {
			rollNext();
			index = 0;
		}
		//#ifdef DTEST
		Common.debug("Page.readCurr() - " + buffer.getBuffer()[index]);
		//#endif
		return buffer.getBuffer()[index];
	}

	/**
	 * Reads previous part of page to buffer.  This is an error as there
	 * is only one page.
	 * @throws Exception
	 */
	protected void rollPrev() throws Exception {
		throw new Exception("Cannot roll previous buffer");
	}

	/**
	 * Reads next part of page to buffer
	 * This is an error as there is only one page.
	 * @throws Exception
	 */
	protected void rollNext() throws Exception {
		throw new Exception("Cannot roll next buffer");
	}

	/**
	 * Returns position of actual character in page.
	 * 
	 * @return position of actual character in page
	 */
	public int getPosition() {
		synchronized(this) {
			return buffer==null?0:index;
		}
	}

	/**
	 * Sets new actual position in page (moves buffer and bufferOffset)
	 * 
	 * @param aPos
	 */
	public void setPosition(int aPos) {
		synchronized(this) {
			if (aPos < 0) {
				aPos = 0;
			} else if (aPos >= size) {
				aPos = size - 1;
			}
			index = aPos % size;
		}
	}


	/**
	 * Sets position in page from the given percentage
	 * 
	 * @param aPerc
	 * @throws Exception
	 */
	public void setPercPosition(int aPerc) throws Exception {
		if (aPerc < 0) {
			aPerc = 0;
		} else if (aPerc > 100) {
			aPerc = 100;
		}
		setPosition((size - 1) * aPerc / 100);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	public String toString() {
		return "Page (size=" + size + ", position=" + getPosition() + ")";
	}

}
//#endif
