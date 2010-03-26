/*
 * HtmlView.java
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
// Expand to define logging define
//#define DNOLOGGING

//#ifdef DMIDP20
package cz.cacek.ebook;

import java.io.EOFException;
import java.util.Vector;

import javax.microedition.lcdui.Font;
import javax.microedition.lcdui.Graphics;
import javax.microedition.lcdui.Image;

import net.eiroca.j2me.RSSReader.presentation.RenderedWord;

//#ifdef DLOGGING
import net.sf.jlogmicro.util.logging.Logger;
import net.sf.jlogmicro.util.logging.Level;
//#endif

/**
 * Implementation of ebook content screen.
 * Some code from RSSReader NewsReader program
 * Copyright (C) 2004 G�sta Jonasson <gosta(at)brothas.net>
 * Copyright (C) 2006-2008 eIrOcA (eNrIcO Croce & sImOnA Burzio)
 * Code from these authors combined with code from above.
 * @author Tom� Darmovzal [tomas.darmovzal (at) seznam.cz]
 * @author Josef Cacek [josef.cacek (at) atlas.cz]
 * @author $Author: ibuntonjr $
 * @version $Revision: 1001 $
 * @created $Date: 2008-07-16 06:07:24 +0200 (Mi, 16 Jul 2008) $
 */
public class HtmlView extends AbstractView {

	protected int position;
	protected int pageHeight;
    private int corr;
	private boolean isBegin;
	private boolean isEnd;
	private RenderedWord[] renderedWords;

	//#ifdef DLOGGING
    private Logger logger = Logger.getLogger("HtmlView");
    private boolean fineLoggable = logger.isLoggable(Level.FINE);
    private boolean finestLoggable = logger.isLoggable(Level.FINEST);
	//#endif

	/**
	 * Constructor for doing estimateHeight, and regular constructor
	 * @throws Exception
	 */
	private HtmlView() throws Exception {
		super.init();
	}

	/**
	 * Constructor
	 * @param aWidth
	 * @param aHeight
	 * @throws Exception
	 */
	public HtmlView(int aWidth, int aHeight, int aFontSize, String txt)
	throws Exception {
		this();
		super.initView(aWidth, aHeight, aFontSize);
		RenderedWord.updFontData(aFontSize);
		setText(txt);
		//#ifdef DLOGGING
		if (finestLoggable) {logger.finest("new HtmlView aWidth,aHeight,pageHeight,renderedWords.length=" + aWidth + "," + aHeight + "," + pageHeight + "," + renderedWords.length);}
		//#endif
	}

	/** Set the new text used. */
	public void setText(final String txt) {
		Vector vect = RenderedWord.createWordList(txt, width -
				(2 * borderSpace) - scrollWidth, height - 2 * borderSpace,
				foreground, foreground);
		renderedWords = new RenderedWord[vect.size()];
		vect.copyInto(renderedWords);
		vect = null;
		pageHeight = renderedWords[renderedWords.length - 1].row +
				RenderedWord.heightFont + 2 * borderSpace;
		setFirst();
		isEnd = true;
	}

  /**
   * Set to be the first of the page
   *
   * @author Irv Bunton
   */
	public void setFirst() {
		position = 0;
		corr = 0;
		isBegin = true;
		isEnd = (renderedWords.length > 0);
	}

	/**
	 * Sets position in active page in characters
	 * @param aPos
	 */
	private void setPosition(int aPos) {
		position = aPos;
		corr = (position / RenderedWord.heightFont) * RenderedWord.heightFont;
	}

	/**
	 * Sets position in active page in percents
	 * @param aPerc
	 */
	public void setPercPosition(int aPerc) {
		setPosition(pageHeight * aPerc / 100);
	}

	/**
	 * Returns current position as a percentige of page.
	 *
	 * @return current position as a percentige of page
	 */
	public int getPercPosition() {
		return position * 100 / pageHeight;
	}
	
	/**
	 * Moves view one page ahead
	 * @throws Exception
	 */
	public void fwdPage() throws Exception {
		corr += height - RenderedWord.heightFont;
		if (corr >= pageHeight) {
			corr = pageHeight;
		}
		position = corr;
	}

	/**
	 * Moves view one page back
	 * @throws Exception
	 */
	public void bckPage() throws Exception {
		corr -= height - RenderedWord.heightFont;
		if (corr < 0) {
			corr = 0;
		}
		position = corr;
	}

	/**
	 * Moves view one line ahead
	 * @throws Exception
	 * @return true if scrolling is succesfull
	 */
	public boolean fwdLine() throws Exception {
		synchronized(this) {
			//#ifdef DTEST
			Common.debug("fwdLine() started");
			//#endif
			corr += RenderedWord.heightFont;
			if (corr > 0) {
			  isBegin = false;
			}
			if (corr >= pageHeight) {
				corr = pageHeight;
			}
			position = corr;
			final boolean tmpResult = (corr >= pageHeight);
			//#ifdef DTEST
			Common.debug("fwdLine() finished ("+tmpResult+")");
			//#endif
			return tmpResult;
		}
	}

	/**
	 * Moves view one line back
	 * @throws Exception
	 */
	public void bckLine() throws Exception {
		synchronized(this) {
			//#ifdef DTEST
			Common.debug("bckLine() started");
			//#endif
			if (corr > 0) {
				corr -= RenderedWord.heightFont;
			}
			if (corr < 0) {
				corr = 0;
			}
			if (corr == 0) {
			  isBegin = true;
			}
			position = corr;
			//#ifdef DTEST
			Common.debug("bckLine() finished");
			//#endif
		}
	}

	/**
	 * fills page from current position
	 */
	public void fillPage() {
		//#ifdef DTEST
		Common.debug("fillPage() started");
		//#endif
		//#ifdef DTEST
		Common.debug("fillPage() finished");
		//#endif
	}

	/**
	 * check if page empty
	 */
	public boolean emptyPage() {
		//#ifdef DTEST
		Common.debug("emptyPage() started");
		//#endif
		//#ifdef DTEST
		Common.debug("emptyPage() finished");
		//#endif
		return (corr == pageHeight);
	}

	// Draw text
	protected void drawText(Graphics g) {
		//#ifdef DLOGGING
		if (finestLoggable) {logger.finest("corr,pageHeight=" + corr + "," + pageHeight);}
		//#endif
		isEnd = true;
		int i = 0;
		int j = 0;
		int last = 0;
		for (i = 0; i < renderedWords.length; i++) {
			final RenderedWord renderedWord = renderedWords[i];
			j = renderedWord.row - corr;
			if (j < 0) {
				continue;
			} else if ((j + RenderedWord.heightFont) < (height - borderSpace)) {
				g.setColor(renderedWord.color);
				g.setFont(RenderedWord.font[renderedWord.style]);
				g.drawString(renderedWord.word, renderedWord.offset +
						borderSpace, j + borderSpace,
						Graphics.TOP | Graphics.LEFT);
			}
			else if (pageHeight != 0) {
				break;
			}
		}
		if (pageHeight == 0) {
			pageHeight = j + RenderedWord.heightFont;
		}
		if (i >= renderedWords.length) {
			isEnd = true;
		}
	}

    /**
     * Returns position of first character on screen of current view.
     * @return current position of view
     */
    public int getPosition() {
        return position;
    }

    /**
     * Log the error and throw an exception.
     */
	final private static RuntimeException handleError(String method,
			Throwable e) {
		Common.debugErr(e.getMessage());
		//#ifdef DLOGGING
		Logger logger = Logger.getLogger("View");
		logger.severe(method + " ", e);
		//#endif
		return new RuntimeException(e.getMessage());
	}

	protected int getScrollPosition(int height, int scrollHeight) {
		if (pageHeight == 0) {
			return 0;
		} else {
			return (height - scrollHeight) * corr / pageHeight;
		}
	}

    public int getPageHeight() {
        return (pageHeight);
    }

}
//#endif
