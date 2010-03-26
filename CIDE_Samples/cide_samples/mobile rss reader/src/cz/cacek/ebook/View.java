/*
 * View.java
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
 * @version $Revision: 1073 $
 * @created $Date: 2008-08-02 06:46:09 +0200 (Sa, 02 Aug 2008) $
 */
public class View extends AbstractView {
	protected Font font;
	protected StringBuffer buffer;
	protected Page page;
	protected int position;
	protected int[] charWidths;

	//#ifdef DLOGGING
    private Logger logger = Logger.getLogger("View");
    private boolean fineLoggable = logger.isLoggable(Level.FINE);
    private boolean finestLoggable = logger.isLoggable(Level.FINEST);
	//#endif

	private Screen screen;

    /**
     * Class Screen holds lines which are currently displayed on the screen.
     * Class is synchronized.<p/>
     * Instances of this class are created during LCD-screen font change.
     * @author Josef Cacek [josef.cacek (at) atlas.cz]
     * @author $Author: ibuntonjr $
     * @version $Revision: 1073 $
     * @created $Date: 2008-08-02 06:46:09 +0200 (Sa, 02 Aug 2008) $
     */
	static class Screen {
		private int positions[];
		private String content[];

		/**
         * Creates new screen object
		 * @param aLines
		 */
		public Screen(int aLines) {
			positions = new int[aLines+1];
			content = new String[aLines];
		}
		
		/**
         * Returns count of rows displayed on screen.
		 * @return count of rows displayed on screen
		 */
		public int size() {
			synchronized(this) {
				return content.length;
			}
		}
		
		/**
         * Returns position (in page) of first character displayed on given line.
		 * @param aLine line for which is position returned
		 * @return position of line in page
		 */
		public int getPosition(int aLine) {
			synchronized(this) {
				return positions[aLine];
			}
		}

		/**
         * Sets position of line
		 * @param aLine
		 * @param aPos
		 */
		public void setPosition(int aLine, int aPos) {
			synchronized(this) {
				positions[aLine] = aPos;
			}
		}

		/**
         * Returns string displayed in given row.
		 * @param aLine index of row
		 * @return string displayed in given row
		 */
		public String getContent(int aLine) {
			synchronized(this) {
				return content[aLine];
			}
		}

		/**
         * Sets string to display in given line
		 * @param aLine index of row
		 * @param aStr
		 */
		public void setContent(int aLine, String aStr) {
			synchronized(this) {
				content[aLine] = aStr;
			}
		}
		
		/**
         * Rolls positions and content forward and adds new line. First displayed line
         * is deleted (rolled out).
         * to the end.
		 * @param aLine new line
		 * @param aNewPosition position of new line (position in page)
		 */
		void rollFw(String aLine, int aNewPosition) {
			synchronized(this) {
				for (int i=0; i<content.length-1; i++) {
					content[i] = content[i + 1];
					positions[i] = positions[i + 1];
				}
				positions[content.length - 1] = positions[content.length];
				content[content.length - 1] = aLine;
				positions[positions.length - 1] = aNewPosition;
			}
		}

		/**
         * Rolls backward, new line (given as parameter) is added to the beginning.
		 * @param aLine new line
		 * @param aNewPosition
         * @see #rollFw(String, int)
		 */
		void rollBw(String aLine, int aNewPosition) {
			synchronized(this) {
				positions[content.length] = positions[content.length - 1];
				for (int i = content.length - 1; i > 0; i--) {
					content[i] = content[i - 1];
					positions[i] = positions[i - 1];
				}
				content[0] = aLine;
				positions[0] = aNewPosition;
			}
		}

	}

	/**
	 * Constructor for doing estimateHeight, and regular constructor
	 * @throws Exception
	 */
	private View() throws Exception {
		super.init();
		buffer = new StringBuffer(256);
		charWidths = new int[256];
	}

	/**
	 * Constructor
	 * @param aWidth
	 * @param aHeight
	 * @throws Exception
	 */
	public View(int aWidth, int aHeight, int aFontSize)
	throws Exception {
		this();
		super.initView(aWidth, aHeight, aFontSize);
		buffer = new StringBuffer(256);
		charWidths = new int[256];
		//#ifdef DLOGGING
		if (finestLoggable) {logger.finest("new View aWidth,aHeight=" + aWidth + "," + aHeight);}
		//#endif
	}

	/**
	 * Constructor
	 * @param aWidth
	 * @param aHeight
	 * @throws Exception
	 */
	public View(int aWidth, int aHeight, int aFontSize,
			    boolean aUnderlinedStyle)
		throws Exception {
		this(aWidth, aHeight, aFontSize);
		setFont(Font.getFont(Font.FACE_SYSTEM, Font.STYLE_PLAIN |
					(aUnderlinedStyle ? Font.STYLE_UNDERLINED : 0),
				aFontSize));
	}

	/**
	 * Sets active page for view.
	 * @param aPage
	 */
	public void setPage(Page aPage) {
		page = aPage;
        setPosition(page.getPosition());
        fillPage();
	}

  /**
   * Set to be the first of the page
   *
   * @author Irv Bunton
   */
	public void setFirst() {
		setPosition(0);
	}

	/**
	 * Sets font for view
	 * @param aFont
	 * @throws Exception
	 */
	public void setFont(Font aFont) throws Exception {
		font = aFont;
		int tmpLines = (height - (2 * borderSpace))
				/ (font.getHeight() + lineSpace);
		screen = new Screen(tmpLines);
		for (int i = 0; i < charWidths.length; i++) {
			charWidths[i] = font.charWidth((char) i);
		}
		if (page != null) {
			fillPage();
		}
	}

	/**
	 * Returns font
	 * @return font
	 */
	public Font getFont() {
		return font;
	}

	/**
	 * Sets position in active page in characters
	 * @param aPos
	 */
	private void setPosition(int aPos) {
		position = aPos;
		page.setPosition(position);
	}

	/**
	 * Sets position in active page in percents
	 * @param aPerc
	 */
	public void setPercPosition(int aPerc) {
		setPosition((page.size - 1) * aPerc / 100);
	}

	/**
	 * Returns current position as a percentige of page.
	 *
	 * @return current position as a percentige of page
	 */
	public int getPercPosition() {
		return position * 100 / (page.size - 1);
	}
	
	/**
	 * Moves view one page ahead
	 * @throws Exception
	 */
	public void fwdPage() throws Exception {
		position = screen.getPosition(screen.size());
		fillPage();
	}

	/**
	 * Moves view one page back
	 * @throws Exception
	 */
	public void bckPage() throws Exception {
		for (int i = 0, n=screen.size(); i < n; i++) {
			bckLine();
		}
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
			page.setPosition(screen.getPosition(screen.size()));
			final String tmpLine = nextLine();
			final boolean tmpResult = tmpLine != null;
			if (tmpResult) {
				screen.rollFw(tmpLine, page.getPosition());
				position = screen.getPosition(0);
			}
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
			page.setPosition(screen.getPosition(0) - 1);
			String line = prevLine();
			if (line != null) {
				screen.rollBw(line, page.getPosition() + 1);
				position = screen.getPosition(0);
			}
		}
	}

	/**
	 * fills page from current position
	 */
	public void fillPage() {
		//#ifdef DTEST
		Common.debug("fillPage() started");
		//#endif
		page.setPosition(position);
		screen.setPosition(0, position);
		try {
			for (int i = 0, n=screen.size(); i < n; i++) {
				screen.setContent(i, nextLine());
				screen.setPosition(i + 1, page.getPosition());
				//#ifdef DLOGGING
				if (finestLoggable) {logger.finest("fillPage i,content=" + i + "," + screen.getContent(i));}
				//#endif
			}
		} catch (Exception e) {
			throw handleError("fillPage", e);
		}
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
		if(page.getPosition() >= page.size) {
			try {
				for (int i = 0, n=screen.size(); i < n; i++) {
					if (screen.getContent(i) != null) {
						return false;
					}
				}
				//#ifdef DTEST
				Common.debug("emptyPage() finished");
				//#endif
				return true;
			} catch (Exception e) {
				throw handleError("emptyPage", e);
			}
		}
		//#ifdef DTEST
		Common.debug("emptyPage() finished");
		//#endif
		return false;
	}

	/**
	 * Estimate height
	 */
	final public static int estimateHeight(int aFontSize,
										   boolean aUnderlinedStyle,
										   int aWidth,
										   int aMaxHeight,
						                   Page aPage)
	throws Exception {
		//#ifdef DTEST
		Common.debug("estimateHeight() started");
		//#endif
		//#ifdef DLOGGING
		Logger logger = Logger.getLogger("View");
		boolean finestLoggable = logger.isLoggable(Level.FINEST);
		//#endif
		View view = new View();
		view.width = aWidth;
		view.height = aMaxHeight;
		view.setFont(Font.getFont(Font.FACE_SYSTEM, Font.STYLE_PLAIN |
					(aUnderlinedStyle ? Font.STYLE_UNDERLINED : 0),
				aFontSize));
		view.page = aPage;
		int svpos = aPage.getPosition();
		aPage.setPosition(0);
        view.setPosition(0);
		try {
			int count = 0;
			/* Use some upper limit to prevent an infinate loop. */
			int maxLines = (aMaxHeight > 0) ? (2 * view.screen.size()) :
				(aPage.size / 80);
			//#ifdef DLOGGING
			String line;
			for (; ((maxLines == 0) || (count < maxLines)) &&
					((line = view.nextLine()) != null); count++) {
				//#ifdef DLOGGING
				if (finestLoggable) {logger.finest("estimateHeight count,line=" + count + "," + line);}
				//#endif
			}
			//#else
			for (; ((maxLines == 0) || (count < maxLines)) &&
					(view.nextLine() != null); count++) {
			}
			//#endif
			//#ifdef DTEST
			Common.debug("estimateHeight() finished");
			//#endif
			aPage.setPosition(svpos);
			return ((count * (view.font.getHeight() + view.lineSpace)) +
					(2 * view.borderSpace));
		} catch (Exception e) {
			throw handleError("estimateHeight", e);
		} catch (Throwable e) {
			throw handleError("estimateHeight", e);
		}
	}

	/**
	 * Reads and returns next line for view.
	 * @return next line
	 * @throws Exception
	 */
	protected String nextLine() throws Exception {
		if(page.getPosition() >= page.size) {
			return null;
		}
		int len = 0;
		// First place of white space
		int ws = -1;
		int index = 0;
		int actWidth = width - (2 * borderSpace) - scrollWidth;
		boolean eof = false;
		buffer.setLength(0);
		for(;;){
			char c;
			try {
				c = page.readNext();
			} catch (EOFException e){
				eof = true;
				break;
			}
			// Change tabs to single space
			if(c == '\t') c = ' ';
			// If \r or line begins with spaces, go to next char without
			// saving.
			if(c == '\r' || (c == ' ' && index==0)) continue;
			// If line feed, end the line.
			if(c == '\n') break;
			// Save white space index
			if(c == ' ') ws = index;
			len += charWidth(c);
			if(len > actWidth){
				//#ifdef DLOGGING
				if (finestLoggable) {logger.finest("c,len,ws,actWidth,index=" + c + "," + len + "," + ws + "," + actWidth + "," + index);}
				//#endif
				// Read prev will give EOFException if at the end of the page
				// or the beginning.  In this case if we get it, we are at
				// the end.
				try {
					page.readPrev();
				} catch (EOFException e) {
					eof = true;
					page.setPosition(page.size - 1);
					break;
				}
				if((ws != -1) && wrapSpaces){
					int discard = index - ws - 1;
					for(int i = 0; i < discard; i++) {
						page.readPrev();
					}
					index = ws;
					buffer.setLength(index);
				}
				break;
			}
			buffer.append(c);
			index++;
		}
		if (eof && index == 0) {
			return null;
		}
		return (buffer.toString()).trim();
	}

	/**
	 * Reads and returns previous line. (backward reading)
	 * @return previous line
	 * @throws Exception
	 */
	protected String prevLine() throws Exception {
		if (page.getPosition() <= 0)
			return null;
		int len = 0;
		int ws = -1;
		int index = 0;
		boolean eof = false;
		buffer.setLength(0);
		for (;;) {
			char c;
			try {
				c = page.readPrev();
			} catch (EOFException e) {
				eof = true;
				break;
			}
			if (c == '\t') c = ' ';
			if (c == '\r' || (c==' ' && index==0)) continue;
			if (c == '\n') break;
			if (c == ' ') ws = index;
			len += charWidth(c);
			if (len > width - (2 * borderSpace) - scrollWidth) {
				page.readNext();
				if ((ws != -1) && wrapSpaces) {
					int discard = index - ws - 1;
					for (int i = 0; i < discard; i++) {
						page.readNext();
					}
					index = ws;
					buffer.setLength(index);
				}
				break;
			}
			buffer.append(c);
			index++;
		}
		if (eof && index==0) {
			return null;			
		}
		buffer.reverse();
		return (buffer.toString()).trim();
	}

	/**
	 * returns width of given character for current font
	 *
	 * @param aChr character
	 * @return width of given character
	 */
	protected int charWidth(char aChr) {
		return (aChr < 256)?charWidths[aChr]:font.charWidth(aChr);
	}

	// Draw text
	protected void drawText(Graphics g) {
		g.setColor(foreground);
		g.setFont(font);
		//#ifdef DLOGGING
		if (finestLoggable) {logger.finest("screen.size()=" + screen.size());}
		//#endif
		int pos = 0;
		for (int i=0, n=screen.size(); i < n; i++) {
			String line = screen.getContent(i);
			//#ifdef DLOGGING
			if (finestLoggable) {logger.finest("draw i,line=" + i + "," + line);}
			//#endif
			if (line != null)
				g.drawString(line, borderSpace, borderSpace + pos,
						Graphics.LEFT | Graphics.TOP);
			pos += font.getHeight() + lineSpace;
		}
	}

    /**
     * Returns position of first character on screen of current view.
     * @return current position of view
     */
    public int getPosition() {
        return screen==null?0:screen.getPosition(0);
    }

    /**
     * @return Returns true if there is another page, false otherwise
     */
    public boolean isLastPage() {
        return page.getPosition() >= page.size;
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
		return (height-scrollHeight) * page.getPosition() / page.size;
	}
}
//#endif
