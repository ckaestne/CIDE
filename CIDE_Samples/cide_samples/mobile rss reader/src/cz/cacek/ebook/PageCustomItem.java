/*
   TODO Fix new View.  Get prev pos.
 * PageCustomItem.java
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
// Expand to define CLDC define
//#define DCLDCV10
// Expand to define test define
//#define DNOTEST
// Expand to define logging define
//#define DNOLOGGING

//#ifdef DMIDP20
package cz.cacek.ebook;

import java.util.Enumeration;
import java.util.Vector;
import java.util.Hashtable;
import java.lang.Math;

import javax.microedition.lcdui.Canvas;
import javax.microedition.lcdui.CustomItem;
import javax.microedition.lcdui.Choice;
import javax.microedition.lcdui.Command;
import javax.microedition.lcdui.Item;
import javax.microedition.lcdui.ItemCommandListener;
import javax.microedition.lcdui.Display;
import javax.microedition.lcdui.Displayable;
import javax.microedition.lcdui.Font;
import javax.microedition.lcdui.Graphics;

import cz.cacek.ebook.util.ResourceProviderME;
import com.substanceofcode.rssreader.presentation.UiUtil;
import com.substanceofcode.rssreader.presentation.HelpForm;
import com.substanceofcode.rssreader.presentation.RssReaderMIDlet;

//#ifdef DLOGGING
import net.sf.jlogmicro.util.logging.Logger;
import net.sf.jlogmicro.util.logging.Level;
//#endif

/**
 * Main display. On this CustomItem is displayed current part of book.
 * @author Tom� Darmovzal [tomas.darmovzal (at) seznam.cz]
 * @author Josef Cacek [josef.cacek (at) atlas.cz]
 * @author Jiri Bartos
 * @author $Author: ibuntonjr $
 * @version $Revision: 1071 $
 * @created $Date: 2008-08-02 06:44:12 +0200 (Sa, 02 Aug 2008) $
 */
public class PageCustomItem extends CustomItem implements PageImpl {

	private PageMgr pageMgr;

	//#ifdef DLOGGING
    private Logger logger = Logger.getLogger("PageCustomItem");
    private boolean fineLoggable = logger.isLoggable(Level.FINE);
    private boolean finestLoggable = logger.isLoggable(Level.FINEST);
	//#endif

	/**
	 * Constructor
	 * Set the page to show
	 * @param aLabel - albel name
	 * @param aFrmWidth - form width
	 * @param aMidlet - midlet
	 * @throws Exception
	 */
	public PageCustomItem(String aLabel, int aFrmWidth, int aFrmHeight,
						  final int aFontSize, final boolean aUnderlinedStyle,
						  final boolean isHtml,
						  final String aText,
						  final Displayable aPrev,
						  final RssReaderMIDlet aMidlet)
	throws Exception {
		super(null);
		pageMgr = new PageMgr(aLabel, aFrmWidth, aFrmHeight,
						  aFontSize, aUnderlinedStyle,
						  isHtml,
						  aText,
						  this,
						  aPrev,
						  aMidlet);
		super.setItemCommandListener(pageMgr);
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see javax.microedition.lcdui.Displayable#paint(javax.microedition.lcdui.Graphics)
	 */
	public void paint(Graphics g, int pwidth, int pheight) {
		pageMgr.paint(g, pwidth, pheight);
	}

	/**
	 * @return Returns width of last paint.
	 */
	public int getWidth() {
		return pageMgr.getWidth();
	}

	/**
	 * @return Returns height of last paint.
	 */
	public int getHeight() {
		return pageMgr.getHeight();
	}

	/**
	 * @return Returns min content width of last paint.
	 */
	public int getMinContentWidth() {
		return pageMgr.getMinContentWidth();
	}

	/**
	 * @return Returns min content height of last paint.
	 */
	public int getMinContentHeight() {
		return pageMgr.getMinContentHeight();
	}

	/**
	 * @return Returns preferred width of last paint.
	 */
	public int getPrefContentWidth(int width) {
		return pageMgr.getPrefContentWidth(width);
	}

	/**
	 * @return Returns preferred height of last paint.
	 */
	public int getPrefContentHeight(int height) {
		return pageMgr.getPrefContentHeight(height);
	}

	/**
	 * Size changed
	 */
	protected void sizeChanged(int width, int height) {
		pageMgr.sizeChanged(width, height);
	}

	/* (non-Javadoc)
	 * @see javax.microedition.lcdui.Displayable#keyPressed(int)
	 */
	public void keyPressed(int aKey) {
		pageMgr.keyPressed(aKey);
	}

	/* (non-Javadoc)
	 * @see javax.microedition.lcdui.Displayable#keyRepeated(int)
	 */
	public void keyRepeated(int aKey) {
		pageMgr.keyRepeated(aKey);
	}

  /**
   * Since repaint is protected, this is the only way we can let others
   * PageMgr call it.
   * @author Irv Bunton
   */
	public void svcRepaint() {
		super.repaint();
	}

	/**
	 * Key actions handler
	 * @param aKey
	 */
	protected void key(final int aKey) {
		pageMgr.key(aKey);
	}

	/* (non-Javadoc)
	 * @see javax.microedition.lcdui.Displayable#pointerPressed(int, int)
	 */
	public void pointerPressed(int aX, int aY) {
		pageMgr.pointerPressed(aX, aY);
	}

	/* (non-Javadoc)
	 * @see javax.microedition.lcdui.Displayable#hideNotify()
	 */
	protected void showNotify() {
		pageMgr.showNotify();
	}

	/* (non-Javadoc)
	 * @see javax.microedition.lcdui.Displayable#hideNotify()
	 */
	protected void hideNotify() {
		pageMgr.hideNotify();
	}

}
//#endif
