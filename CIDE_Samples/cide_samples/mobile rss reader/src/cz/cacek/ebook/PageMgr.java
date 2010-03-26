/*
   TODO Fix new View.  Get prev pos.
   TODO Fix removing command if change
 * PageMgr.java
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
 * @version $Revision: 1069 $
 * @created $Date: 2008-08-02 06:42:37 +0200 (Sa, 02 Aug 2008) $
 */
public class PageMgr implements ItemCommandListener {

	private RssReaderMIDlet midlet;
	protected PageImpl pageImpl;
	protected AbstractView view;
	protected AbstractView labelView = null;
	protected String label = null;
	protected String text = null;
	protected int prefViewHeight;
	protected boolean viewScrollBar;
	protected Page page = null;
	protected Page labelPage = null;
	protected Displayable prev;
	private String message = null;
	private Font messageFont;
	private static Command cmdPosition = null;
	private static Command cmdHelp = null;
	private int fontSize;
	private boolean underlinedStyle;
	private boolean useActions = false;
	private boolean hasPos = false;
	private Hashtable gameKeys = null;
	private Hashtable sgameKeys = null;

	protected ScrollThread scrollThread  = new ScrollThread();

	private Vector listVector;
	private static int scrollDelay = Common.AUTOSCROLL_PAUSE;

	private Display display;
	int lastWidth;
	int lastHeight;
	int labelPageHeight = 0;
	int pageHeight;

	//#ifdef DLOGGING
    private Logger logger = Logger.getLogger("PageMgr");
    private boolean fineLoggable = logger.isLoggable(Level.FINE);
    private boolean finestLoggable = logger.isLoggable(Level.FINEST);
	//#endif

	/**
	 * Thread which provides autoscroll functionality.
	 * @author Josef Cacek
	 */
	class ScrollThread extends Thread {
		boolean processing = true;
		boolean run = false;

		//#ifdef DCLDCV11
		public ScrollThread(String name) {
			super(name);
		}
		//#endif

		public ScrollThread() {
			super();
		}

		public void run() {
			try {
				while (processing) {
					if (canRun()) {
						if (!view.fwdLine()) {
							setRun(false);
						}
						messageOff();
					}
					synchronized(this) {
						super.wait(getScrollDelay());
					}
				}
				if (message != null) {
					messageOff();
				}
				//#ifdef DLOGGING
				if (finestLoggable) {logger.finest("scrollThread finished.");}
				//#endif
			} catch (Exception e) {
				//#ifdef DLOGGING
				logger.severe("scrollThread run failed.", e);
				//#endif
			}
		}

		public void wakeUp() {
			synchronized(this) {
				try {
					super.notify();
				} catch (Exception e) {
				}
			}
		}

		public void setRun(final boolean aRun) {
			synchronized(this) {
				run = aRun;
				if (run) {
					if (!scrollThread.isAlive()) {
						//#ifdef DCLDCV11
						scrollThread = new ScrollThread("scrollThread");
						//#else
						scrollThread = new ScrollThread();
						//#endif
						scrollThread.start();
					}
				} else {
					messageOff();
				}
			}
		}

		public boolean canRun() {
			synchronized(this) {
				return run;
			}
		}

		public void setProcessing(boolean processing) {
			this.processing = processing;
		}

		public boolean isProcessing() {
			return (processing);
		}

	}

	/**
	 * Constructor
	 * Set the page to show
	 * @param aLabel - albel name
	 * @param aFrmWidth - form width
	 * @param aMidlet - midlet
	 * @throws Exception
	 */
	public PageMgr(String aLabel, int aFrmWidth, int aFrmHeight,
						  final int aFontSize, final boolean aUnderlinedStyle,
						  final boolean isHtml,
						  final String aText,
						  final PageImpl aPageImpl,
						  final Displayable aPrev,
						  final RssReaderMIDlet aMidlet)
	throws Exception {
		try {
			midlet = aMidlet;
			fontSize = aFontSize;
			underlinedStyle = aUnderlinedStyle;
			lastWidth = aFrmWidth;
			pageImpl = aPageImpl;
			prev = aPrev;
			display = Display.getDisplay(midlet);
			if (gameKeys == null) {
				initGameKeys();
			}
			messageFont = Font.getFont(Font.FACE_SYSTEM, Font.STYLE_BOLD,
				aFontSize);
			if (isHtml) {
				label = aLabel;
				if (aUnderlinedStyle) {
					text = "<u>" + aText + "</u>";
				} else {
					text = aText;
				}
				labelPageHeight = messageFont.getHeight();
				updCustomData(aFrmWidth, 3 * labelPageHeight, aFontSize,
						aUnderlinedStyle);
				if (labelView != null) {
					labelPageHeight = ((HtmlView)labelView).getPageHeight() +
							AbstractView.getTotalBorderSpace();
				}
				pageHeight = ((HtmlView)view).getPageHeight() +
							AbstractView.getTotalBorderSpace();
			} else {
				if (aLabel != null) {
					labelPage = new Page(aLabel);
					page = new Page(aText);
					labelPageHeight = View.estimateHeight(aFontSize,
							aUnderlinedStyle, aFrmWidth, aFrmHeight,
							labelPage) + AbstractView.getTotalBorderSpace();
				}
				pageHeight = View.estimateHeight(aFontSize, aUnderlinedStyle,
						aFrmWidth, aFrmHeight, page) +
							AbstractView.getTotalBorderSpace();
			}
			if (aFrmHeight == 0) {
				lastHeight = labelPageHeight + pageHeight;
			} else {
				lastHeight = Math.min(labelPageHeight + pageHeight,
						aFrmHeight);
			}
			if (!isHtml) {
				updCustomData(aFrmWidth, lastHeight, aFontSize,
						aUnderlinedStyle);
				viewScrollBar = !((View)view).isLastPage();
				//#ifdef DLOGGING
				if (finestLoggable) {logger.finest("viewScrollBar=" + viewScrollBar);}
				//#endif
			}
			if (!viewScrollBar &&
					(lastHeight < (labelPageHeight + pageHeight))) {
				viewScrollBar = true;
			}
			prefViewHeight = lastHeight;
			if (cmdHelp == null) {
				cmdHelp = UiUtil.getCmdRsc("cmd.i.help", Command.ITEM, 3);
			}
			pageImpl.addCommand(cmdHelp);
			if (lastHeight < pageHeight) {
				scrollThread.start();
				//#ifdef DLOGGING
				if (finestLoggable) {logger.finest("scrollThread started.");}
				//#endif
			}
		} catch (Exception e) {
			//#ifdef DLOGGING
			logger.severe("Constructor failed with exception", e);
			//#endif
		} catch (Throwable e) {
			//#ifdef DLOGGING
			logger.severe("Constructor failed with throwable", e);
			//#endif
		} finally {
			//#ifdef DLOGGING
			if (finestLoggable) {logger.finest("aLabel,aFrmWidth,aFrmHeight,labelPageHeight,pageHeight,prefViewHeight,viewScrollBar=" + aLabel + "," + aFrmWidth + "," + aFrmHeight + "," + labelPageHeight + "," + pageHeight + "," + prefViewHeight + "," + viewScrollBar);}
			//#endif
			//#ifdef DLOGGING
			if (finestLoggable) {logger.finest("lastHeight=" + lastHeight);}
			//#endif
		}
	}

  /**
   * Init game keys.
   *
   * @return    Hashtable - Convert
   * @author Irv Bunton
   */
	final private void initGameKeys() {
		Hashtable hact = new Hashtable();
		int [] keys = {Canvas.KEY_NUM0,
					   Canvas.KEY_NUM1,
					   Canvas.KEY_NUM2,
					   Canvas.KEY_NUM3,
					   Canvas.KEY_NUM4,
					   Canvas.KEY_NUM5,
					   Canvas.KEY_NUM6,
					   Canvas.KEY_NUM7,
					   Canvas.KEY_NUM8,
					   Canvas.KEY_NUM9,
					   Canvas.KEY_POUND,
					   Canvas.KEY_STAR};
		String [] skeys = {
					   "0",
					   "1",
					   "2",
					   "3",
					   "4",
					   "5",
					   "6",
					   "7",
					   "8",
					   "9",
					   "pound",
					   "star"};
		int [] acts = {Canvas.UP,
					   Canvas.DOWN,
					   Canvas.LEFT,
					   Canvas.RIGHT,
					   Canvas.FIRE,
					   Canvas.GAME_A,
					   Canvas.GAME_B,
					   Canvas.GAME_C,
					   Canvas.GAME_D};
		String [] sacts = {"UP",
					   "DOWN",
					   "LEFT",
					   "RIGHT",
					   "FIRE",
					   "GAME_A",
					   "GAME_B",
					   "GAME_C",
					   "GAME_D"};
		for (int ic = 0; ic < sacts.length; ic++) {
			//#ifdef DLOGGING
			if (finestLoggable) {logger.finest(sacts[ic] + "=" + acts[ic]);}
			//#endif
			hact.put(new Integer(acts[ic]), sacts[ic]);
		}
		Hashtable ckeys = new Hashtable();
		Hashtable sckeys = new Hashtable();
		for (int ic = 0; ic < keys.length; ic++) {
			int cact = pageImpl.getGameAction(keys[ic]);
			Integer icact = new Integer(cact);
			if (hact.containsKey(icact)) {
				//#ifdef DLOGGING
				if (finestLoggable) {logger.finest(skeys[ic] + "=" + keys[ic] + "," + hact.get(icact));}
				//#endif
				hact.remove(icact);
				ckeys.put(icact, new Integer(keys[ic]));
				sckeys.put(icact, skeys[ic]);
			};
		}
		final int hsize = hact.size();
		useActions = (hsize == 0);
		//#ifdef DLOGGING
		if (finestLoggable) {logger.finest("useActions,hact.size()=" + useActions + "," + hsize);}
		//#endif
		if (hsize > 0) {
			int[] altKeys = {Canvas.KEY_NUM2, Canvas.KEY_NUM8,
				Canvas.KEY_NUM4, Canvas.KEY_NUM6, Canvas.KEY_NUM5,
				Canvas.KEY_NUM1, Canvas.KEY_NUM3, Canvas.KEY_NUM7,
				Canvas.KEY_NUM9};
			String[] saltKeys = {"2", "8", "4", "6", "5", "1", "3", "7", "9"};
			gameKeys = new Hashtable();
			sgameKeys = new Hashtable();
			for (int ic = 0; ic < acts.length; ic++) {
				Integer act = new Integer(acts[ic]);

				gameKeys.put(act, new Integer(altKeys[ic]));
				sgameKeys.put(act, saltKeys[ic]);
			}
		} else {
			gameKeys = ckeys;
			sgameKeys = sckeys;
		}
	}

  /**
   * Create a new view with the width and height and font.  Also, set
   * page position to 0.  Also, account for message if page height is <
   * given height.
   *
   * @param aWidth
   * @param aHeight
   * @param aFontSize
   * @param aUnderlinedStyle
   * @return    final
   * @author Irv Bunton
   */
	final private void updCustomData(int aWidth, int aHeight, int aFontSize,
			boolean aUnderlinedStyle)
	throws Exception {
		if ((lastHeight < pageHeight) && !hasPos) {
			hasPos = true;
			if (cmdPosition == null) {
				cmdPosition = UiUtil.getCmdRsc("page.cmd.position",
						Command.ITEM, 2);
			}
			pageImpl.addCommand(cmdPosition);
		}
		int labelHeight = Math.min(labelPageHeight, aHeight);
		aHeight-= labelHeight;
		if (labelPage != null) {
			labelView = new View(aWidth, labelHeight, aFontSize, false);
			labelPage.setPosition(0);
			((View)labelView).setPage(labelPage);
		} else if (labelHeight > 0) {
			labelView = new HtmlView(aWidth, labelHeight, aFontSize, label);
		}
		if (page != null) {
			view = new View(aWidth, aHeight, aFontSize,
					aUnderlinedStyle);
			page.setPosition(0);
			((View)view).setPage(page);
		} else {
			view = new HtmlView(aWidth, aHeight, aFontSize, text);
		}
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see javax.microedition.lcdui.Displayable#paint(javax.microedition.lcdui.Graphics)
	 */
	public void paint(Graphics g, int pwidth, int pheight) {
		//#ifdef DTEST
		Common.debug("PageMgr.paint(Graphics g)");
		//#endif
		//#ifdef DLOGGING
		if (finestLoggable) {logger.finest("paint pwidth,pheight=" + pwidth + "," + pheight);}
		//#endif
		if ((lastWidth != pwidth) || (lastHeight != pheight)) {
			//#ifdef DLOGGING
			if (finestLoggable) {logger.finest("paint changed pwidth,lastWidth,pheight,lastHeight=" + pwidth + "," + lastWidth + "," + pheight + "," + lastHeight);}
			//#endif
			lastWidth = pwidth;
			lastHeight = pheight;
			try {
				updCustomData(lastWidth, lastHeight, fontSize,
						underlinedStyle);
			} catch (Exception e) {
				//#ifdef DTEST
				Common.debugErr("PageMgr.paint view failed");
				//#endif
			}
		}
		if (labelPageHeight < pheight) {
			if (labelView != null) {
				labelView.draw(g, 0, 0, false, false);
			}
			view.draw(g, 0, labelPageHeight, true,
					viewScrollBar || (prefViewHeight > pheight));
		}
		if (message != null) {
			int mx = 2;
			int my = 2;
			g.setFont(messageFont);
			int w = messageFont.stringWidth(message);
			int h = messageFont.getHeight();
			g.setColor(0xFFFFFF);
			g.fillRect(mx, my, w + 3, h + 3);
			g.setColor(0x000000);
			g.drawString(message, mx + 2, my + 2, Graphics.LEFT | Graphics.TOP);
			g.drawRect(mx, my, w + 3, h + 3);
		}
	}

	/**
	 * @return Returns width of last paint.
	 */
	public int getWidth() {
		return lastWidth;
	}

	/**
	 * @return Returns height of last paint.
	 */
	public int getHeight() {
		return lastHeight;
	}

	/**
	 * @return Returns min content width of last paint.
	 */
	public int getMinContentWidth() {
		return lastWidth;
	}

	/**
	 * @return Returns min content height of last paint.
	 */
	public int getMinContentHeight() {
		return lastHeight;
	}

	/**
	 * @return Returns preferred width of last paint.
	 */
	public int getPrefContentWidth(int width) {
		//#ifdef DLOGGING
		if (finestLoggable) {logger.finest("getPrefContentWidth,width=" + width);}
		//#endif
		if ((width > 0) && (lastWidth < width)) {
			lastWidth = width;
		}
		return lastWidth;
	}

	/**
	 * @return Returns preferred height of last paint.
	 */
	public int getPrefContentHeight(int height) {
		//#ifdef DLOGGING
		if (finestLoggable) {logger.finest("getPrefContentHeight,height,labelPageHeight,pageHeight=" + height + "," + labelPageHeight + "," + pageHeight);}
		//#endif
		return prefViewHeight;
	}

	/**
	 * Size changed
	 */
	public void sizeChanged(int width, int height) {
		//#ifdef DLOGGING
		if (finestLoggable) {logger.finest("sizeChanged,width,height=" + width + "," + height);}
		//#endif
		if ((lastHeight != width) || (lastHeight != height)) {
			lastWidth = width;
			lastHeight = height;
			try {
				updCustomData(lastWidth, lastHeight, fontSize,
						underlinedStyle);
			} catch (Exception e) {
				//#ifdef DTEST
				Common.debugErr("PageMgr.sizeChanged view failed");
				//#endif
			}
		}
	}

	/* (non-Javadoc)
	 * @see javax.microedition.lcdui.Displayable#keyPressed(int)
	 */
	public void keyPressed(int aKey) {
		//#ifdef DTEST
		Common.debug("Key pressed " + aKey);
		//#endif
		key(aKey);
	}

	/* (non-Javadoc)
	 * @see javax.microedition.lcdui.Displayable#keyRepeated(int)
	 */
	public void keyRepeated(int aKey) {
		//#ifdef DTEST
		Common.debug("Key repeated " + aKey);
		//#endif
		key(aKey);
	}

	/**
	 * Key actions handler
	 * @param aKey
	 */
	public void key(final int aKey) {
		synchronized(this) {
			final int action = pageImpl.getGameAction(aKey);
			//#ifdef DTEST
			Common.debug("Key: " + aKey + ", Action: " + action);
			//#endif
			if (!scrollThread.canRun()) {
				keyNormal(aKey, action);
			} else {
				keyAutoRun(aKey, action);
			}
			//#ifdef DTEST
			Common.debug("Key action finished.");
			//#endif
		}
	}


	/**
	 * Key actions handler
	 * @param aKey
	 */
	public void keyNormal(final int aKey, final int action) {
		if (useActions) {
			switch (action) {
				case Canvas.UP:
					prevPage();
					break;
				case Canvas.DOWN:
					nextPage();
					break;
				case Canvas.RIGHT:
					nextLine();
					break;
				case Canvas.LEFT:
					prevLine();
					break;
				case Canvas.GAME_D:
					display.setCurrent(prev);
					break;
				case Canvas.GAME_A:
					if (lastHeight < pageHeight) {
						scrollThread.setRun(true);
					}
					break;
				default:
					break;
			}
		} else {
			Integer ikey = new Integer(aKey);
			if (ikey.equals((Integer)gameKeys.get(new Integer(Canvas.UP)))) {
				prevPage();
			} else if (ikey.equals(
					(Integer)gameKeys.get(new Integer(Canvas.DOWN)))) {
				nextPage();
			} else if (ikey.equals(
					(Integer)gameKeys.get(new Integer(Canvas.RIGHT)))) {
				nextLine();
			} else if (ikey.equals(
					(Integer)gameKeys.get(new Integer(Canvas.LEFT)))) {
				prevLine();
			} else if (ikey.equals(
					(Integer)gameKeys.get(new Integer(Canvas.GAME_D)))) {
					display.setCurrent(prev);
			} else if (ikey.equals(
					(Integer)gameKeys.get(new Integer(Canvas.GAME_A)))) {
					if (lastHeight < pageHeight) {
						scrollThread.setRun(true);
					}
			}
		}
	}


	/**
	 * Key handlers for Autorun book reading.
	 * @param aKey
	 */
	public void keyAutoRun(final int aKey, final int action) {
		Integer ikey = new Integer(aKey);
		if ((useActions &&
		    (((action == Canvas.UP) || (action == Canvas.LEFT) ||
				(action == Canvas.GAME_B)))) ||
		   (!useActions &&
			(ikey.equals(
					(Integer)gameKeys.get(new Integer(Canvas.UP))) ||
			 ikey.equals(
					(Integer)gameKeys.get(new Integer(Canvas.LEFT))) ||
			 ikey.equals(
					(Integer)gameKeys.get(new Integer(Canvas.GAME_B)))))) {
			addScrollDelay(Common.AUTOSCROLL_STEP);
		} else if ((useActions &&
		    (((action == Canvas.DOWN) || (action == Canvas.RIGHT) ||
				(action == Canvas.GAME_C)))) ||
		   (!useActions &&
			(ikey.equals(
					(Integer)gameKeys.get(new Integer(Canvas.DOWN))) ||
			 ikey.equals(
					(Integer)gameKeys.get(new Integer(Canvas.RIGHT))) ||
			 ikey.equals(
					(Integer)gameKeys.get(new Integer(Canvas.GAME_C)))))) {
			addScrollDelay(- Common.AUTOSCROLL_STEP);
		}
		if ((useActions && (action == Canvas.GAME_A)) ||
		    (!useActions &&
			 ikey.equals(
					(Integer)gameKeys.get(new Integer(Canvas.GAME_A))))) {
			messageOn(ResourceProviderME.get("i.stop"));
			scrollThread.setRun(false);
		} else {
			messageOn(ResourceProviderME.get("i.delay")+getScrollDelay());
		}
	}

	/* (non-Javadoc)
	 * @see javax.microedition.lcdui.Displayable#pointerPressed(int, int)
	 */
	public void pointerPressed(int aX, int aY) {
		//#ifdef DTEST
		Common.debug("Pointer pressed (" + aX + "," + aY + ")");
		//#endif
		int seg = (aY * 4) / getHeight();
		if (scrollThread != null) {
			return;
		}
		synchronized (this) {
			switch (seg) {
				case 0:
					prevPage();
					break;
				case 1:
					prevLine();
					break;
				case 2:
					nextLine();
					break;
				case 3:
					nextPage();
					break;
			}
		}
	}

	/* (non-Javadoc)
	 * @see javax.microedition.lcdui.CommandListener#commandAction(javax.microedition.lcdui.Command, javax.microedition.lcdui.Item)
	 */
	public void commandAction(Command aCmd, Item aItem) {
		//#ifdef DTEST
		Common.debug("commandAction() for page started");
		//#endif
		if (aCmd == cmdPosition) {
			PositionForm pform = new PositionForm("page.pos.head", view,
					(Item)pageImpl,
					midlet);
			pform.createGauge("page.pos.actual", view.getPercPosition());
		} else if (aCmd == cmdHelp) {
			final HelpForm helpForm = new HelpForm(midlet, (Item)pageImpl);
			appendKeyHelp(helpForm, Canvas.GAME_D, "text.b.key");
			if (!scrollThread.canRun()) {
				/* Page forward. */
				appendKeyHelp(helpForm, Canvas.DOWN, "text.pf.key");
				/* Page backward. */
				appendKeyHelp(helpForm, Canvas.UP, "text.pb.key");
				/* Line forward. */
				appendKeyHelp(helpForm, Canvas.RIGHT, "text.lf.key");
				/* Line backward. */
				appendKeyHelp(helpForm, Canvas.LEFT, "text.lb.key");
			} else {
				/* Scroll faster. */
				appendKeyHelp(helpForm, Canvas.DOWN, "text.auf.key");
				/* Scroll slower. */
				appendKeyHelp(helpForm, Canvas.UP, "text.aus.key");
				appendKeyHelp(helpForm, Canvas.RIGHT, "text.auf.key");
				appendKeyHelp(helpForm, Canvas.LEFT, "text.aus.key");
				appendKeyHelp(helpForm, Canvas.GAME_C, "text.auf.key");
				appendKeyHelp(helpForm, Canvas.GAME_B, "text.aus.key");
			}
			if (lastHeight < pageHeight) {
				appendKeyHelp(helpForm, Canvas.GAME_A, "text.s.key");
			}
			display.setCurrent(helpForm);
		}
	}

  /**
   * Get the ascii key represented by the game action.  Get the resource to
   * allow localization.  Substitute this in key help. Append to help form.
   *
   * @param HelpForm - help form to append to.
   * @param aKey - Game action.
   * @param aResKey - Resource for key help
   * @author Irv Bunton
   */
	final private int appendKeyHelp(final HelpForm helpForm, int aKey,
			String aResKey) {
		String pkey = ResourceProviderME.get("text.k." + sgameKeys.get(
					new Integer(aKey)));
		return helpForm.append(ResourceProviderME.get(aResKey, pkey));
	}

	/**
	 * Set the text to show
	 */
	public void setText(final String aText) {
		if (view instanceof View) {
			page = new Page(aText);
			((View)view).setPage(page);
		} else {
			text = aText;
			((HtmlView)view).setText(aText);
		}
	}

	/**
	 * Scrolls one line ahead in current book
	 */
	protected void nextLine() {
		try {
			pauseOn();
			view.fwdLine();
			if (view.emptyPage()) {
				//#ifdef DLOGGING
				if (finestLoggable) {logger.finest("Forward line too far.  Going back.");}
				//#endif
				view.bckLine();
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			messageOff();
		}
	}

	/**
	 * Scrolls one page ahead in current book
	 */
	protected void nextPage() {
		try {
			pauseOn();
			view.fwdPage();
			if (view.emptyPage()) {
				//#ifdef DLOGGING
				if (finestLoggable) {logger.finest("Forward page too far.  Going back.");}
				//#endif
				view.bckPage();
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			messageOff();
		}
	}

	/**
	 * Scrolls one line back in current book
	 */
	protected void prevLine() {
		try {
			pauseOn();
			view.bckLine();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			messageOff();
		}
	}

	/**
	 * Scrolls one page back in current book
	 */
	protected void prevPage() {
		try {
			pauseOn();
			view.bckPage();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			messageOff();
		}
	}

	/**
	 * Displays "Wait" message
	 */
	protected void pauseOn() {
		messageOn(ResourceProviderME.get("i.wait"));
	}

	/**
	 * Displays system message (e.g. Wait) display
	 */
	protected void messageOn(final String aMsg) {
		synchronized(this) {
			message = aMsg;
			//#ifdef DLOGGING
			if (finestLoggable) {logger.finest("message=" + message);}
			//#endif
			pageImpl.svcRepaint();
		}
	}

	/**
	 * Disable system message (e.g. Wait) display
	 */
	protected void messageOff() {
		synchronized(this) {
			message = null;
			pageImpl.svcRepaint();
		}
	}

	/* (non-Javadoc)
	 * @see javax.microedition.lcdui.Displayable#hideNotify()
	 */
	public void showNotify() {
		//#ifdef DTEST
		Common.debug("PageCanvas.showNotify()");
		//#endif
		if (!scrollThread.isProcessing() || !scrollThread.isAlive()) {
			if (!scrollThread.isAlive()) {
				try {
					boolean run = scrollThread.canRun();
					//#ifdef DCLDCV11
					scrollThread = new ScrollThread("scrollThread");
					//#else
				scrollThread = new ScrollThread();
					//#endif
					scrollThread.setRun(run);
					scrollThread.start();
					//#ifdef DLOGGING
					if (finestLoggable) {logger.finest("scrollThread re-started from showNotify after recreate.");}
					//#endif
				} catch (Exception e) {
					//#ifdef DLOGGING
					logger.severe("Unable to start thread.", e);
					//#endif
					e.printStackTrace();
				}
			}
		}
	}

	/* (non-Javadoc)
	 * @see javax.microedition.lcdui.Displayable#hideNotify()
	 */
	public void hideNotify() {
		//#ifdef DTEST
		Common.debug("PageCanvas.hideNotify()");
		//#endif
		/* Don't change run so that help can know if it's on.  Also, it allows
		   us to keep it's value. */
		scrollThread.setProcessing(false);
		scrollThread.wakeUp();
	}

	/**
	 * Sets pause between scrolling.
	 * @param aDelay
	 */
	public synchronized static void setScrollDelay(int aDelay) {
		if (aDelay < Common.AUTOSCROLL_STEP) {
			aDelay = Common.AUTOSCROLL_STEP;
		}
		scrollDelay = aDelay;
	}

	public synchronized static void addScrollDelay(int aDelay) {
		setScrollDelay(getScrollDelay() + aDelay);
	}

	/**
	 * @return Returns the scrollDelay.
	 */
	public synchronized static int getScrollDelay() {
		return scrollDelay;
	}

}
//#endif
