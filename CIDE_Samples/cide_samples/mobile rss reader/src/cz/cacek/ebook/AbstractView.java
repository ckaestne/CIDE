/*
 * AbstractView.java
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

import javax.microedition.lcdui.Font;
import javax.microedition.lcdui.Graphics;
import javax.microedition.lcdui.Image;

import cz.cacek.ebook.Page;

//#ifdef DLOGGING
import net.sf.jlogmicro.util.logging.Logger;
import net.sf.jlogmicro.util.logging.Level;
//#endif

/**
 * Implementation of ebook content screen.
 * @author Tom� Darmovzal [tomas.darmovzal (at) seznam.cz]
 * @author Josef Cacek [josef.cacek (at) atlas.cz]
 * @author $Author: ibuntonjr $
 * @version $Revision: 1099 $
 * @created $Date: 2008-08-02 14:06:57 +0200 (Sa, 02 Aug 2008) $
 */
abstract public class AbstractView {
	protected int width;
	protected int height;
	protected int background;
	protected int foreground;
	protected static int borderSpace = 2;
	protected static int lineSpace = 0;
	protected boolean wrapSpaces;
	protected int scrollWidth;
	protected int scrollHeight;
	protected Image offscreen;

	//#ifdef DLOGGING
    private Logger logger = Logger.getLogger("AbstractView");
    private boolean fineLoggable = logger.isLoggable(Level.FINE);
    private boolean finestLoggable = logger.isLoggable(Level.FINEST);
	//#endif

	/**
	 * Constructor for doing estimateHeight, and regular constructor
	 * @throws Exception
	 */
	protected void init() throws Exception {
		wrapSpaces = true;
		scrollWidth = 5;
		scrollHeight = 5;
	}

	/**
	 * Constructor
	 * @param aWidth
	 * @param aHeight
	 * @throws Exception
	 */
	public void initView(int aWidth, int aHeight, int aFontSize)
	throws Exception {
		//#ifdef DLOGGING
		if (finestLoggable) {logger.finest("new View aWidth,aHeight=" + aWidth + "," + aHeight);}
		//#endif
		width = aWidth;
		height = aHeight;
		setColors(0xFFFFFF, 0x000000);
		offscreen = Image.createImage(width, height);
	}

	/**
	 * Sets FG/BG colors for view
	 * @param aBG
	 * @param aFG
	 */
	public void setColors(int aBG, int aFG) {
		background = aBG;
		foreground = aFG;
	}

	/**
	 * Returns foreground color.
	 * @return foreground color
	 */
	public int getForegroundColor() {
		return foreground;
	}

	/**
	 * Returns background color.
	 * @return background color
	 */
	public int getBackgroundColor() {
		return background;
	}

	/**
	 * Sets position in active page in percents
	 * @param aPerc
	 */
	abstract public void setPercPosition(int aPerc);

	/**
	 * Returns current position as a percentige of page.
	 *
	 * @return current position as a percentige of page
	 */
	abstract public int getPercPosition();
	
	/**
	 * Moves view one page ahead
	 * @throws Exception
	 */
	abstract public void fwdPage() throws Exception;

	/**
	 * Moves view one page back
	 * @throws Exception
	 */
	abstract public void bckPage() throws Exception;

	/**
	 * Moves view one line ahead
	 * @throws Exception
	 * @return true if scrolling is succesfull
	 */
	abstract public boolean fwdLine() throws Exception;

	/**
	 * Moves view one line back
	 * @throws Exception
	 */
	abstract public void bckLine() throws Exception;

	/**
	 * fills page from current position
	 */
	abstract public void fillPage();

	/**
	 * check if page empty
	 */
	abstract public boolean emptyPage();

  /**
   * Set to be the first of the page
   *
   * @author Irv Bunton
   */
	abstract public void setFirst();

	/**
	 * Draw current view to display.
	 * @param aGraphic
	 * @param aX
	 * @param aY
	 */
	public void draw(Graphics aGraphic, int aX, int aY, boolean aBody,
			boolean aScrollBar) {
		//#ifdef DLOGGING
		if (finestLoggable) {logger.finest("draw aX,aY,width,height,aBody,aScrollBar=" + aX + "," + aY + "," + width + "," + height + "," + aBody + "," + aScrollBar);}
		//#endif
		Graphics g = offscreen.getGraphics();
		// Draw background
		g.setColor(background);
		if (aBody) {
			g.fillRect(0, 0, width, height);
		}
		// Draw text
		g.setColor(foreground);
		drawText(g);
		if (aBody) {
			// Draw border
			g.setColor(foreground);
			g.drawRect(0, 0, width - 1, height - 1);
			if (aScrollBar) {
				// Draw scroll
				g.setColor(background);
				g.fillRect(width - scrollWidth, 0, scrollWidth - 1, height - 1);
				g.setColor(foreground);
				g.drawRect(width - scrollWidth, 0, scrollWidth - 1, height - 1);
				int scroll = getScrollPosition(height, scrollHeight);
				g.fillRect(width - scrollWidth, scroll,
						scrollWidth - 1, scrollHeight - 1);
			}
		}
		// Draw offscreen
		aGraphic.drawImage(offscreen, aX, aY, Graphics.LEFT | Graphics.TOP);
	}

  /**
   * Get the line space
   *
   * @return    int
   * @author Irv Bunton
   */
    static public int getLineSpace() {
        return (lineSpace);
    }

	final static int getTotalBorderSpace() {
		return 2 * borderSpace;
	}

	abstract protected void drawText(Graphics g);

	abstract protected int getScrollPosition(int height, int scrollHeight);

}
//#endif
