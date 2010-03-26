/*
 * PageCanvas.java
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
/* This isn't used now, so only compile for testing. */
//#ifdef DTEST
package cz.cacek.ebook;

import java.util.Vector;

import javax.microedition.lcdui.Canvas;
import javax.microedition.lcdui.Choice;
import javax.microedition.lcdui.Command;
import javax.microedition.lcdui.CommandListener;
import javax.microedition.lcdui.Display;
import javax.microedition.lcdui.Displayable;
import javax.microedition.lcdui.Font;
import javax.microedition.lcdui.Form;
import javax.microedition.lcdui.Gauge;
import javax.microedition.lcdui.Graphics;
import javax.microedition.lcdui.StringItem;

import cz.cacek.ebook.util.ResourceProviderME;
import com.substanceofcode.rssreader.presentation.UiUtil;
import com.substanceofcode.rssreader.presentation.RssReaderMIDlet;

/**
 * Main display. On this canvas is displayed current part of book.
 * @author Tom� Darmovzal [tomas.darmovzal (at) seznam.cz]
 * @author Josef Cacek [josef.cacek (at) atlas.cz]
 * @author Jiri Bartos
 * @author $Author: ibuntonjr $
 * @version $Revision: 934 $
 * @created $Date: 2008-07-15 23:33:20 +0200 (Di, 15 Jul 2008) $
 */
public class PageCanvas extends Canvas implements CommandListener, PageImpl {

	private PageMgr pageMgr;
	int width;
	int height;
	
	/**
	 * Constructor
	 * Set the page to show
	 * @param aLabel - albel name
	 * @param aFrmWidth - form width
	 * @param aMidlet - midlet
	 * @throws Exception
	 */
	public PageCanvas(String aTitle, int aFrmWidth, int aFrmHeight,
						  final int aFontSize, final boolean aUnderlinedStyle,
						  final boolean isHtml,
						  final String aText,
						  final PageImpl aPageImpl,
						  final Displayable aPrev,
						  final RssReaderMIDlet aMidlet)
	throws Exception {
		super();
		width = super.getWidth();
		height = super.getHeight();
		super.setTitle(aTitle);
		pageMgr = new PageMgr(aTitle, aFrmWidth, aFrmHeight,
						  aFontSize, aUnderlinedStyle,
						  isHtml,
						  aText,
						  this,
						  aPrev,
						  aMidlet);
		super.setCommandListener(this);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see javax.microedition.lcdui.Displayable#paint(javax.microedition.lcdui.Graphics)
	 */
	public void paint(Graphics g) {
		pageMgr.paint(g, width, height);
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
   * Since repaint is protected (in CustomItem), this is the only way we can
   * let others PageMgr call it.
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
	 * @see javax.microedition.lcdui.CommandListener#commandAction(javax.microedition.lcdui.Command, javax.microedition.lcdui.Displayable)
	 */
	public void commandAction(Command aCmd, Displayable aDisp) {
		pageMgr.commandAction(aCmd, null);
	}

	/* (non-Javadoc)
	 * @see javax.microedition.lcdui.Displayable#hideNotify()
	 */
	protected void hideNotify() {
		pageMgr.hideNotify();
	}

}
//#endif
//#endif
