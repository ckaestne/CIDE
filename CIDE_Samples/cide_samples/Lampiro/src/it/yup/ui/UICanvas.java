/* Copyright (c) 2008 Bluendo S.r.L.
 * See about.html for details about license.
 *
 * $Id: UICanvas.java 1578 2009-06-16 11:07:59Z luca $
 */

/**
 * 
 */
package it.yup.ui;

// #mdebug
//@
//@import it.yup.util.Logger;
//@
// #enddebug

import it.yup.util.Utils;
import it.yup.xmpp.Config;

import java.io.IOException;
import java.util.Enumeration;
import java.util.Timer;
import java.util.TimerTask;
import java.util.Vector;

import javax.microedition.lcdui.Alert;
import javax.microedition.lcdui.AlertType;
import javax.microedition.lcdui.Canvas;
import javax.microedition.lcdui.Display;
import javax.microedition.lcdui.Displayable;
import javax.microedition.lcdui.Font;
import javax.microedition.lcdui.Graphics;
import javax.microedition.lcdui.Image;
import javax.microedition.lcdui.game.GameCanvas;

/**
 * UICanvas is the class that holds all the open screens and shows them. Screens
 * are held in a stack-like structure, showing only the one at the top of the
 * stack. Screens may be opened, closed, showed and hidden; by pressing and
 * holding the '*' key, the user can switch from a screen to another.
 * 
 * The UICanvas is singleton: only one may exist per midlet.
 */
public class UICanvas extends GameCanvas {

	/** the key used to activate the left key */
	public static int MENU_LEFT = -6;
	/** the key used to activate the right key */
	public static int MENU_RIGHT = -7;
	public static int MENU_CANCEL = -8;

	private static final Object[][] keyMaps = {
			{ "Nokia", new Integer(-6), new Integer(-7) },
			{ "ricsson", new Integer(-6), new Integer(-7) },
			{ "iemens", new Integer(-1), new Integer(-4) },
			{ "otorola", new Integer(-21), new Integer(-22) },
			{ "harp", new Integer(-21), new Integer(-22) },
			{ "j2me", new Integer(-6), new Integer(-7) },
			{ "SunMicrosystems_wtk", new Integer(-6), new Integer(-7) } };

	/** The alert used to show errors (if any) */
	private static Alert alert;
	/** singleton instance */
	private static UICanvas _instance;
	/** the display */
	private static Display display;
	/** a timer to schedule tasks */
	private static Timer timer;

	/** l'elenco degli screen */
	private Vector screenList;

	private int viewedIndex = 0;

	private boolean qwerty = false;

	/**
	 * @return the screenList
	 */
	public Vector getScreenList() {
		return screenList;
	}

	/** il "popup" che contiene le finestre aperte */
	private UIMenu wlist;

	/*
	 * A semaphore used to lock all the graphical operations.
	 */
	private Semaphore sem = new Semaphore(1);

	// #mdebug
//@			/*
//@			 * The time at which the pointer is pressed
//@			 */
//@			private long pressedTime;
//@		
//@			/*
//@			 * The time at which the pointer is released
//@			 */
//@			private long releasedTime;
//@		
	// #enddebug

	private int pressedX;
	private int pressedY;

	/*
	 * Set to true when dragging an object 
	 */
	private boolean dragged = false;

	/*
	 * A boolean used to know if repeated events are generated.
	 * If it is false the repeated events are generated artificially. 
	 */
	public boolean hasRE = true;

	public static void lock() {
		if (_instance == null) return;
		try {
			_instance.sem.acquire();
		} catch (InterruptedException e) {
			// #mdebug
//@									Logger.log("In locking UI");
//@									System.out.println(e.getMessage());
//@									e.printStackTrace();
			// #enddebug
		}
	}

	public static void unlock() {
		if (_instance == null) return;
		_instance.sem.release();
	}

	/**
	 * The constructor for {@link UICanvas}.
	 * 
	 * @param suppressKeyEvents
	 *            True to suppress the regular key event mechanism for game
	 *            keys, otherwise False.
	 */
	private UICanvas() {
		super(false);
		setFullScreenMode(true);
		screenList = new Vector();
		this.hasRE = this.hasRepeatEvents();
		String keys = Config.getInstance().getProperty(Config.CANVAS_KEYS);
		// not initialized
		if (keys == null || keys.indexOf(',') == -1) setupdefaultKeyCode();
	}

	private boolean isMotorola() {
		try {
			String imei = System.getProperty("IMEI");
			// #mdebug
//@									Logger.log("IMEI :" + imei);
			//#enddebug
			if (imei != null) return true;
			imei = System.getProperty("com.motorola.IMEI");
			// #mdebug
//@									Logger.log("com.motorola.IMEI :" + imei);
			//#enddebug
			if (imei != null) return true;
		} catch (Exception e) {

		}
		return false;
	}

	private void setupdefaultKeyCode() {
		String platform = System.getProperty("microedition.platform");

		// hack to detect if is a "strange motorola"
		if (platform.indexOf("otorola") != -1 || isMotorola()) {
			platform = "Motorola";
		}

		// #mdebug
//@						Logger.log("platform:" + platform);
		//              #enddebug
		for (int i = 0; i < keyMaps.length; ++i) {
			String manufacturer = (String) keyMaps[i][0];

			if (platform.indexOf(manufacturer) != -1) {
				if (i == 1) {
					if (platform.indexOf("P900") != -1
							|| platform.indexOf("P908") != -1) {
						UICanvas.MENU_LEFT = ((Integer) keyMaps[i][2])
								.intValue();
						UICanvas.MENU_RIGHT = ((Integer) keyMaps[i][1])
								.intValue();
					} else {
						UICanvas.MENU_LEFT = ((Integer) keyMaps[i][1])
								.intValue();
						UICanvas.MENU_RIGHT = ((Integer) keyMaps[i][2])
								.intValue();
					}
				} else {
					UICanvas.MENU_LEFT = ((Integer) keyMaps[i][1]).intValue();
					UICanvas.MENU_RIGHT = ((Integer) keyMaps[i][2]).intValue();
				}
				break;
			}
		}
	}

	/**
	 * Used to get the "effective" height of the canvas. getHeight() sometimes
	 * computes the menu bar space even if it is not displayed.
	 * 
	 * @return The height of the clip.
	 */
	public int getClipHeight() {
		return clipHeight; // this.getGraphics().getClipHeight();
	}

	private long keyReleasedTime = Long.MAX_VALUE;
	private long keyPressedTime = Long.MAX_VALUE;

	protected void keyReleased(int key) {
		if (this.hasRE == false) {
			if (System.currentTimeMillis() - keyPressedTime > 1000) {
				keyRepeated(key);
			}
			keyReleasedTime = System.currentTimeMillis();
			keyPressedTime = keyReleasedTime - 1;
		}
	}

	/**
	 * <p>
	 * Handle the key pressure.
	 * </p>
	 * Dispatches the key pressure to the shown screen.
	 * 
	 * @param key
	 *            The pressed key.
	 */
	protected void keyPressed(int key) {
		// #mdebug
//@						//Logger.log("key pressed:" + key);
		// #enddebug

		if (this.hasRE == false) {
			if (keyPressedTime < keyReleasedTime) {
				keyPressedTime = System.currentTimeMillis();
			}
			if (System.currentTimeMillis() - keyPressedTime > 1000) {
				// to be sure the time is reset 
				try {
					keyRepeated(key);
				} catch (Exception e) {
					// #mdebug
//@															Logger.log("In key pressed:" + e.getMessage() + " "
//@																	+ e.getClass().getName());
//@															e.printStackTrace();
					// #enddebug
				}
				keyPressedTime = Long.MAX_VALUE;
				keyReleasedTime = 0;
				return;
			}
		}

		if (screenList.size() == 0 || viewedIndex > screenList.size()
				|| viewedIndex < 0) { return; }
		// wlist key pressure cannot be handled in UIscreen
		// because it has no knowledge of it
		// it must be handled here
		if (wlist != null && wlist.isOpenedState() == true) {
			int ga = this.getGameAction(key);
			if (key == UICanvas.MENU_RIGHT || ga == Canvas.FIRE) {
				int selectedIndex = wlist.getSelectedIndex();
				getCurrentScreen().removePopup(wlist);
				change(selectedIndex);
				return;
			}
		}

		try {
			UIMenu writeScreen = (UIMenu) screenList.elementAt(viewedIndex);
			writeScreen.keyPressed(key);
		} catch (Exception e) {
			// #mdebug
//@									Logger.log("In key pressed:" + e.getMessage() + " "
//@											+ e.getClass().getName());
//@									e.printStackTrace();
			// #enddebug
		}
	}

	TimerTask longPressedTask = initLongPress(null);
	boolean longPressRun = false;

	public void pointerPressed(int x, int y) {
		// #mdebug
//@				
//@						this.pressedTime = System.currentTimeMillis();
//@				
		// #enddebug
		this.pressedX = x;
		this.pressedY = y;
		this.dragged = false;
		final UIScreen paintedScreen = getCurrentScreen();
		if (paintedScreen == null) return;

		boolean hasSubMenu = handlePointEvent(x, y, paintedScreen, false);

		if (hasSubMenu) {
			longPressRun = false;
			longPressedTask = initLongPress(paintedScreen);
			Utils.tasks.schedule(longPressedTask, 750);
		}

		// #mdebug
//@						//Logger.log("pointerPressed:" + x + " " + y + ":");
		// #enddebug
	}

	private TimerTask initLongPress(final UIScreen paintedScreen) {
		return new TimerTask() {
			public void run() {
				longPress(paintedScreen);
			}
		};
	}

	private void longPress(UIScreen paintedScreen) {
		synchronized (longPressedTask) {
			// #mdebug
//@									Logger.log("longPressed:");
			// #enddebug
			longPressRun = true;
			if (paintedScreen != null) paintedScreen
					.keyPressed(UICanvas.MENU_LEFT);
		}
	}

	public void pointerDragged(int x, int y) {
		// #mdebug
//@						//Logger.log("pointerDragged:" + x + " " + y + ":");
		// #enddebug
		UIScreen paintedScreen = getCurrentScreen();
		if (paintedScreen == null) return;

		int yOffset = y - pressedY;
		int xOffset = x - pressedX;
		UIItem foundItem = findItem(x, y, paintedScreen);
		//if a little movement is made reset the longpressTask
		if (Math.abs(yOffset) > 5 || Math.abs(xOffset) > 5) {
			synchronized (longPressedTask) {
				longPressedTask.cancel();
			}
		}

		if (foundItem != null) if (yOffset >= 5 || yOffset <= -5) paintedScreen
				.startDrag(foundItem);

		int ka = -1;
		if (yOffset >= 20) {
			ka = this.getKeyCode(Canvas.DOWN);
			this.dragged = true;
			paintedScreen.keyRepeated(ka);
			this.pressedX = x;
			this.pressedY = y;
			return;
		}
		if (yOffset <= -20) {
			ka = this.getKeyCode(Canvas.UP);
			this.dragged = true;
			paintedScreen.keyRepeated(ka);
			this.pressedX = x;
			this.pressedY = y;
			return;
		}
		if (yOffset >= 15) {
			ka = this.getKeyCode(Canvas.DOWN);
			this.dragged = true;
			paintedScreen.keyPressed(ka);
			this.pressedX = x;
			this.pressedY = y;
			return;
		}
		if (yOffset <= -15) {
			ka = this.getKeyCode(Canvas.UP);
			this.dragged = true;
			paintedScreen.keyPressed(ka);
			this.pressedX = x;
			this.pressedY = y;
			return;
		}

		ka = -1;
		if (xOffset >= 15) {
			this.dragged = true;
			if (foundItem != null) {
				ka = this.getKeyCode(Canvas.RIGHT);
				foundItem.keyPressed(ka);
			}
			this.pressedX = x;
			this.pressedY = y;
			return;
		}
		if (xOffset <= -15) {
			this.dragged = true;
			if (foundItem != null) {
				ka = this.getKeyCode(Canvas.LEFT);
				foundItem.keyPressed(ka);
			}
			this.pressedX = x;
			this.pressedY = y;
			return;
		}
	}

	/**
	 * <p>
	 * Handle the pointer pressure.
	 * </p>
	 * Determines where the pointer were pressed 
	 * 
	 * @param key
	 *            The pressed key.
	 * 
	 * @return <code>true</code> if the screen will keep the selection
	 */
	public void pointerReleased(int x, int y) {
		// #mdebug
//@				
//@						this.releasedTime = System.currentTimeMillis();
//@				
		// #enddebug

		// #mdebug
//@						//Logger.log("pointerReleased:" + x + " " + y + ":");
		// #enddebug

		synchronized (longPressedTask) {
			longPressedTask.cancel();
			if (longPressRun == true) {
				this.dragged = false;
				longPressRun = false;
				return;
			}
		}

		UIScreen paintedScreen = getCurrentScreen();
		if (paintedScreen == null) {
			this.dragged = false;
			return;
		}
		if (this.dragged == true) {
			this.dragged = false;
			paintedScreen.endDrag();
			return;
		}
		// #mdebug
//@						Logger.log("paintedScreen found:");
		// #enddebug
		handlePointEvent(x, y, paintedScreen, true);
	}

	private boolean handlePointEvent(int x, int y, UIScreen paintedScreen,
			boolean up) {
		UIItem foundItem = null;
		foundItem = findItem(x, y, paintedScreen);
		if (foundItem != null) {
			// #mdebug
//@									try {
//@										Logger.log("Found item in pointerReleased:");
//@										Logger
//@												.log("Pression time: " + releasedTime + " "
//@														+ pressedTime);
//@										if (foundItem instanceof UILabel) {
//@											UILabel new_name = (UILabel) foundItem;
//@											Logger.log("Label: " + new_name.getText()
//@													+ foundItem.getWidth() + " "
//@													+ foundItem.getHeight(canvasGraphics));
//@										} else if (foundItem instanceof UILayout) {
//@											UILayout new_name = (UILayout) foundItem;
//@											Logger.log("Layout " + new_name.getWidth() + " "
//@													+ new_name.getHeight(canvasGraphics));
//@										} else if (foundItem instanceof UITextField) {
//@											UITextField new_name = (UITextField) foundItem;
//@											Logger.log("TextField " + new_name.getText());
//@										} else if (foundItem instanceof UIPanel) {
//@											UIPanel new_name = (UIPanel) foundItem;
//@											Logger.log("Panel " + new_name.getWidth() + " "
//@													+ new_name.getHeight(canvasGraphics));
//@										} else if (foundItem instanceof UIMenu) {
//@											Logger.log("Menu ");
//@											UIMenu new_name = (UIMenu) foundItem;
//@											try {
//@												Logger.log(((UILabel) new_name.getItemList().elementAt(
//@														0)).getText()
//@														+ " "
//@														+ new_name.getWidth()
//@														+ " "
//@														+ new_name.getHeight(canvasGraphics));
//@											} catch (Exception e) {
//@												Logger.log(e.getMessage() + e.getClass().getName());
//@											}
//@										} else {
//@											Logger.log("Found other ");
//@											try {
//@												Logger.log(foundItem.getWidth() + " "
//@														+ foundItem.getHeight(canvasGraphics));
//@											} catch (Exception e) {
//@												Logger.log(e.getMessage() + e.getClass().getName());
//@											}
//@										}
//@									} catch (Exception e) {
//@									}
			// #enddebug

			if (foundItem == paintedScreen.footerLeft) {
				if (up) {
					this.keyPressed(UICanvas.MENU_LEFT);
				}
				return false;
			}
			if (foundItem == paintedScreen.footerRight) {
				if (up) {
					this.keyPressed(UICanvas.MENU_RIGHT);
				}
				return false;
			}

			// check if it is the screen menus list
			if (wlist != null && wlist.isOpenedState() == true) {
				int selectedIndex = wlist.getItemList().indexOf(foundItem);
				if (selectedIndex >= 0) {
					if (up) {
						paintedScreen.removePopup(wlist);
						change(selectedIndex);
					}
					return false;
				}
			}

			// first check if its a menu
			UIMenu paintedMenu = paintedScreen.getMenu();
			if (paintedMenu != null && paintedMenu.contains(foundItem)) {
				if (!up) {
					foundItem.getContainer().setSelectedItem(foundItem);
					paintedScreen.askRepaint();
				} else {
					paintedScreen.handleMenuKey(paintedMenu, UICanvas
							.getInstance().getKeyCode(UICanvas.FIRE));
				}
				return false;
			} else { // then a popup or an item
				Enumeration enPopup = paintedScreen.popupList.elements();
				while (enPopup.hasMoreElements()) {
					UIMenu ithMenu = (UIMenu) enPopup.nextElement();
					if (ithMenu.contains(foundItem)) {
						if (!up) {
							foundItem.getContainer().setSelectedItem(foundItem);
							paintedScreen.askRepaint();
						} else {
							paintedScreen.handleMenuKey(ithMenu, UICanvas
									.getInstance().getKeyCode(UICanvas.FIRE));
						}
						return false;
					}
				}
			}
			// then the title
			if (foundItem == paintedScreen.titleLabel) {
				if (up) {
					this.keyRepeated(UICanvas.KEY_STAR);
				}
			}
			if (foundItem.isFocusable() == false) return false;

			// if any kind of menu is opened return (menus are modals)
			if ((paintedMenu != null && paintedMenu.isOpenedState())
					|| paintedScreen.popupList.size() > 0) { return false; }

			if (foundItem.getContainer() != null) {
				if (!up) {
					// #mdebug
//@															Logger.log("pressed");
					// #enddebug
					foundItem.getContainer().setSelectedItem(foundItem);
					paintedScreen.askRepaint();
					return true;
				} else {
					// #mdebug
//@															Logger.log("released");
					// #enddebug
					paintedScreen.keyPressed(UICanvas.getInstance().getKeyCode(
							UICanvas.FIRE));
					return false;
				}
			}
		} else {
			// check if it is the menucursor
			if (screenList.size() > 1 && up) {
				if (x < this.lag.getWidth() && y < this.lag.getHeight()) {
					// check if it is the screen right or left cursor
					int la = UICanvas.getInstance().getKeyCode(Canvas.LEFT);
					paintedScreen.keyPressed(la);
					return false;
				}
				if (x > canvasGraphics.getClipWidth() - 2 * this.lag.getWidth()
						&& y < canvasGraphics.getClipHeight() - 2
								* this.lag.getHeight()) {
					// check if it is the screen right or left cursor
					int ra = UICanvas.getInstance().getKeyCode(Canvas.RIGHT);
					paintedScreen.keyPressed(ra);
					return false;
				}
			}
		}
		return false;
	}

	private UIItem findItem(int x, int y, UIScreen paintedScreen) {
		UIItem foundItem = null;
		Enumeration en = paintedScreen.getPaintedItems().elements();
		while (en.hasMoreElements()) {
			UIItem ithItem = (UIItem) en.nextElement();
			int[] coors = ithItem.coors;
			int originalX = coors[0];
			int originalY = coors[1];
			int w = coors[2];
			int h = coors[3];

			if (x > originalX && x < originalX + w && y > originalY
					&& y <= originalY + h) {
				foundItem = ithItem;
				break;
			}
		}
		return foundItem;
	}

	public int getGameAction(int keyCode) {
		// some mobile phones throw an Exception when pressing
		// a key that is not associated to a game key 
		// even if the keyCode is a valid key code
		int retVal = 0;
		try {
			retVal = super.getGameAction(keyCode);
		} catch (Exception e) {
			// #mdebug
//@									Logger.log("In getGameAction:" + keyCode + " "
//@											+ e.getClass().getName());
			// #enddebug
		}
		return retVal;
	}

	/**
	 * <p>
	 * Handle an event of keyRepeated.
	 * </p>
	 * If the key repeated is the '*', opens the "window list".
	 * 
	 * @param key
	 *            The pressed key.
	 */
	protected void keyRepeated(int key) {
		if (screenList.size() == 0 || viewedIndex < 0
				|| viewedIndex >= screenList.size()) { return; }
		UIScreen s0 = (UIScreen) screenList.elementAt(viewedIndex);
		if (key == Canvas.KEY_STAR) {
			if (s0.popupIsPresent(this.wlist) == false && screenList.size() > 1) {
				wlist = UIUtils.easyMenu("", 20, this.getClipHeight() / 4,
						getWidth() - 40, null);
				for (int i = 0; i < screenList.size(); i++) {
					UIScreen si = (UIScreen) screenList.elementAt(i);
					if (si != null) {
						UILabel um = new UILabel(si.getTitle());
						wlist.append(um);
					}
				}
				s0.addPopup(wlist);
				return;
			}
		}
		s0.keyRepeated(key);
	}

	private Graphics canvasGraphics = this.getGraphics();
	private int clipHeight = canvasGraphics.getClipHeight();

	/**
	 * Used by screen to ask a repaint.
	 * 
	 * @param screen
	 *            The screen to repaint. It may be {@code null} to indicate that
	 *            the current shown screen should be repainted. Otherwise, the
	 *            given screen will be checked if it is the screen currently
	 *            shown.
	 */
	public/*synchronized*/void askRepaint(UIScreen screen) {
		if (screenList.size() == 0) { return; }
		if (screen == null) {
			screen = (UIScreen) _instance.screenList.elementAt(viewedIndex);
			screen.setDirty(true);
		} else if (screen != (UIScreen) screenList.elementAt(viewedIndex)) { return; }

		try {
			UICanvas.lock();
			if (screen.isFreezed() == false) {
				screen.setFreezed(true);
				Graphics g = canvasGraphics; // this.getGraphics();
				g.setFont(UIConfig.font_body);

				// in case a sizeChanged has changed it
				this.clipHeight = canvasGraphics.getClipHeight();

				int originalX = g.getTranslateX();
				int originalY = g.getTranslateY();
				int originalClipX = g.getClipX();
				int originalClipY = g.getClipY();
				int originalClipWidth = g.getClipWidth();
				int originalClipHeight = g.getClipHeight();
				//				g.translate(-g.getTranslateX(), -g.getTranslateY());
				//				g.setClip(0, 0, this.getWidth(), this.getHeight());
				// #mdebug
//@								//				Logger.log(g.getTranslateX() + " " + g.getTranslateY() + " "
//@								//						+ g.getClipX() + " " + g.getClipY() + " "
//@								//						+ g.getClipWidth() + " " + g.getClipHeight() + " "
//@								//						+ this.getWidth() + " " + this.getHeight());
				// #enddebug

				setTabs();
				boolean needFlush = screen.paint(g);

				g.translate(originalX - g.getTranslateX(), originalY
						- g.getTranslateY());
				g.setClip(originalClipX, originalClipY, originalClipWidth,
						originalClipHeight);
				//				g.translate(-g.getTranslateX(), -g.getTranslateY());
				//				g.setClip(0, 0, this.getWidth(), this.getHeight());

				if (needFlush) {
					flushGraphics();
				}
				screen.setFreezed(false);
			}
		} catch (Exception ex) {
			// #mdebug
//@									Logger.log("In painting UI");
//@									System.out.println(ex.getMessage());
//@									ex.printStackTrace();
			// #enddebug
		} finally {
			UICanvas.unlock();
		}
	}

	private Image rag = UICanvas.getUIImage("/icons/rag.png");
	private Image lag = UICanvas.getUIImage("/icons/lag.png");
	private Image rab = UICanvas.getUIImage("/icons/rab.png");
	private Image lab = UICanvas.getUIImage("/icons/lab.png");

	private void setTabs() {
		UIScreen cs = this.getCurrentScreen();
		if (cs == null) return;
		if (this.screenList.size() <= 1) {
			cs.ra = rag;
			cs.la = lag;
		} else {
			cs.ra = rab;
			cs.la = lab;
		}

	}

	/*
	private void paintNav(Graphics g) {
	        if (this.screenList.size() == 1) return;

	        String navString = (this.viewedIndex + 1) + "/"
	                        + this.screenList.size();
	        Font navFont = Font.getFont(Font.FACE_PROPORTIONAL, Font.STYLE_BOLD,
	                        Font.SIZE_SMALL);
	        g.setFont(navFont);
	        int navWidth = navFont.stringWidth(navString);
	        g.translate(this.getWidth() - g.getTranslateX() - navWidth - 2, 2 - g
	                        .getTranslateY());
	        g.setColor(UIConfig.header_bg);
	        g.fillRect(-2, 0, navWidth + 2, navFont.getHeight());
	        g.setColor(0x333333);
	        g.drawString(navString, 0, 0, Graphics.LEFT | Graphics.TOP);
	        g.translate(-1, -1);
	        g.setColor(0xFFFFFF);
	        g.drawString(navString, 0, 0, Graphics.LEFT | Graphics.TOP);
	        g.translate(-g.getTranslateX(), -g.getTranslateY());
	}

	 */

	/**
	 * Open and shows the given screen, optionally the screen can be shown
	 * immediately. If the screen is immediately shown, it's inserted at
	 * position 0, otherwise it's placed at the end of the list.
	 * 
	 * @param screen
	 *            the screen to show
	 * @param show
	 *            if true, the screen is immediately shown otherwise it's left
	 *            hidden
	 */
	public void open(UIScreen screen, boolean show) {
		if (!show || (wlist != null && wlist.isOpenedState() == true)) {
			if (!screenList.contains(screen)) screenList.addElement(screen);
		} else {
			if (screenList.contains(screen)) screenList.removeElement(screen);
			screenList.insertElementAt(screen, screenList.size());
		}
		if (wlist != null) {
			wlist.append(new UILabel(screen.getTitle()));
		}
		if (show == false) { return; }
		if (viewedIndex >= 0) {
			((UIScreen) screenList.elementAt(viewedIndex)).setDirty(true);
			((UIScreen) screenList.elementAt(viewedIndex)).hideNotify();
		}
		/* if the screen is the only one, it's painted immediately */
		screen.setDirty(true);
		viewedIndex = this.screenList.indexOf(screen);
		// changing index -> mandatory to repaint the background
		screen.firstPaint = true;
		screen.askRepaint();
		screen.showNotify();
	}

	/**
	 * Shows a screen by placing it on top of the stack. If the screen is not in
	 * the stack of open screens, this method fails silently.
	 * 
	 * @param screen
	 *            the screen to show
	 */
	public void show(UIScreen screen) {
		int idx = screenList.indexOf(screen);
		if (idx == -1) { return; }
		change(idx);
	}

	public void show(int idx) {
		if (idx >= 0 && idx < this.screenList.size()) change(idx);
	}

	/**
	 * Hides a screen and shows the next in the stack. If the screen is not the
	 * visible one, this method fails silently. If this screen is the only one
	 * in the stack, this method does nothing.
	 * 
	 * @param screen
	 *            the screen to hide
	 */
	public void hide(UIScreen screen) {
		if (screenList.size() < 2) { return; }
		UIMenu s0 = (UIMenu) screenList.elementAt(viewedIndex);
		if (s0 != screen) { return; }
		change(0);
	}

	/**
	 * Closes a screen, removing it from the stack and showing the next one. If
	 * the screen is not in the stack of open screens, or is the last of the
	 * stack, this method fails silently.
	 * 
	 * @param screen
	 *            the screen to close
	 * @return true if the screen has been closed, false otherwhise
	 */
	public boolean close(UIScreen screen) {
		int idx = screenList.indexOf(screen);
		if (idx == -1) { return false; }
		((UIScreen) screenList.elementAt(idx)).setDirty(true);
		((UIScreen) screenList.elementAt(idx)).hideNotify();
		screenList.removeElementAt(idx);
		// if all the screens have been removed the list could be empty
		if (idx <= viewedIndex) viewedIndex--;
		if (viewedIndex < 0) viewedIndex = 0;
		if (screenList.size() > 0) {
			int newIdx = idx;
			if (idx >= screenList.size()) newIdx = screenList.size() - 1;
			change(newIdx);
		}
		if (wlist != null && wlist.getItemList().size() > 0) {
			wlist.remove(idx);
		}
		return true;
	}

	/**
	 * Change the visible screen to the one given and redraws everything.
	 * 
	 * @param i
	 *            the id of the screen to change to
	 */
	private void change(int i) {
		UIScreen si = (UIScreen) screenList.elementAt(viewedIndex);
		si.setDirty(true);
		si.hideNotify();
		si = (UIScreen) screenList.elementAt(i);
		si.setDirty(true);
		si.firstPaint = true;
		viewedIndex = i;
		si.askRepaint();
		si.showNotify();

		// if (wlist != null) {
		/* switch also wlist */
		// UIItem ui = wlist.remove(i);
		// if
		// wlist.insert(0, ui);
		// }
	}

	/**
	 * Called when the Canvas changes size or even rotation
	 */
	synchronized protected void sizeChanged(int w, int h) {
		try {
			// sometimes the graphics can be null
			// if the screen cannot be painted
			// i.e. in N95
			UIScreen activeScreen = this.getCurrentScreen();
			// to initialize it at least at start
			if (activeScreen != null || screenList == null
					|| screenList.size() == 0) {
				this.canvasGraphics = this.getGraphics();
				this.clipHeight = canvasGraphics.getClipHeight();
			}
			if (activeScreen != null) {
				activeScreen.invalidate(w, h);
				activeScreen.askRepaint();
			}
		} catch (Exception e) {
			// #mdebug
//@									System.out.println(e.getMessage());
//@									e.printStackTrace();
			// #enddebug
		}

	}

	/**
	 * Gets the currently displayed Screen
	 * 
	 * @return The currently displayed screen or {@code null} if no screen is
	 *         available
	 */
	public UIScreen getCurrentScreen() {
		if (!isShown()) { return null; }
		return (UIScreen) ((screenList.size() > 0 && viewedIndex >= 0 && viewedIndex < screenList
				.size()) ? screenList.elementAt(viewedIndex) : null);
	}

	/**
	 * Singleton factory
	 */
	public static synchronized UICanvas getInstance() {
		if (_instance == null) {
			_instance = new UICanvas();
		}
		return _instance;
	}

	/**
	 * Sets the display to use for the change-screen operations
	 * 
	 * @param _display
	 *            The display to use.
	 */
	public static void setDisplay(Display _display) {
		display = _display;
	}

	/**
	 * Sets the key code to use for the two buttons with which menu will be
	 * activated.
	 * 
	 * @param left_key
	 *            the key code for the left button
	 * @param right_key
	 *            the key code for the right button
	 */
	public static void setMenuKeys(int left_key, int right_key) {
		MENU_LEFT = left_key;
		MENU_RIGHT = right_key;
	}

	/**
	 * Display a different
	 * 
	 * @param disp
	 */
	public static void display(Displayable disp) {
		if (display == null) { return; }
		/*
		 * using getInstance() instead of _instance as it may be null so it gets
		 * created
		 */
		display.setCurrent(disp == null ? getInstance() : disp);
		if (disp == null) {
			_instance.askRepaint(null);
		}
	}

	/**
	 * Show an error screen, if multiple errors occur only append the message
	 * 
	 * @param type
	 *            The alert type as per {@link AlertType}
	 * @param title
	 *            Title of the screen
	 * @param text
	 *            Displayed error message
	 */
	public static void showAlert(AlertType type, String title, String text) {

		// a native alert screen may be available in case the UI has not
		// been set alrealy
		Displayable cur = display.getCurrent();
		if (cur.equals(alert)) {
			alert.setString(alert.getString() + "\n" + text);
			return;
		}

		Image img;
		try {
			if (AlertType.INFO.equals(type)) {
				img = Image.createImage("/icons/warning.png");
			} else if (AlertType.WARNING.equals(type)) {
				img = Image.createImage("/icons/warning.png");
			} else if (AlertType.ERROR.equals(type)) {
				img = Image.createImage("/icons/error.png");
			} else {
				img = Image.createImage("/icons/error.png");
			}

		} catch (IOException e) {
			img = null;
		}

		Font bigFont = Font.getFont(Font.FACE_PROPORTIONAL, Font.STYLE_PLAIN,
				Font.SIZE_MEDIUM);

		UIScreen currentScreen = UICanvas.getInstance().getCurrentScreen();
		// if no screen is available it means the UI is not "ready"
		// in that case we use a native alert screen
		if (currentScreen != null) {
			UILabel titleLabel = new UILabel(img, title);
			UIMenu alertMenu = UIUtils.easyMenu("", 10, 20, UICanvas
					.getInstance().getWidth() - 20, titleLabel);
			titleLabel.setFont(bigFont);
			titleLabel.setFocusable(false);
			UILabel textLabel = new UILabel(text);
			alertMenu.append(textLabel);
			textLabel.setWrappable(true, alertMenu.getWidth() - 5);
			textLabel.setFont(bigFont);
			alertMenu.setSelectedIndex(1);
			Graphics cg = UICanvas.getInstance().canvasGraphics;
			int offset = (cg.getClipHeight() - alertMenu.getHeight(cg)) / 2;
			alertMenu.setAbsoluteY(offset);
			alertMenu.cancelMenuString = "";
			alertMenu.selectMenuString = "OK";
			currentScreen.addPopup(alertMenu);
		} else {
			Alert alert = new Alert(title, text, img, type);
			alert.setType(type);
			alert.setTimeout(Alert.FOREVER);
			display.setCurrent(alert, getInstance());
		}
	}

	/**
	 * Used to get predefined images "internal" to the UI. XXX: Maybe it is even
	 * better to get a cache for them.
	 * 
	 * @param imgName
	 * @return
	 */
	public static Image getUIImage(String imgName) {
		try {
			return Image.createImage(imgName);
		} catch (IOException e) {
			System.out.println("Impossible to get : " + imgName);

		}
		return null;
	}

	public static Timer getTimer() {
		if (timer == null) {
			timer = new Timer();
		}
		return timer;
	}

	/**
	 * @return the viewedIndex
	 */
	public int getViewedIndex() {
		return viewedIndex;
	}

	// #mdebug
//@			private static UIMenu logMenu = new UIMenu("log");
//@		
//@			public static void clearLog() {
//@				logMenu.clear();
//@			}
//@		
//@			public static void log(String logString) {
//@				UILabel uil = new UILabel(logString);
//@				logMenu.append(uil);
//@				logMenu.setAbsoluteX(10);
//@				logMenu.setAbsoluteY(20);
//@				logMenu.setWidth(UICanvas.getInstance().getWidth());
//@				UICanvas.getInstance().getCurrentScreen().addPopup(logMenu);
//@			}
//@		
//@			public static void log(Vector logStrings) {
//@				logMenu.setAbsoluteX(10);
//@				logMenu.setAbsoluteY(20);
//@				logMenu.setWidth(UICanvas.getInstance().getWidth());
//@				for (Enumeration en = logStrings.elements(); en.hasMoreElements();) {
//@					String logString = (String) en.nextElement();
//@					if (logString != null) {
//@						UILabel uil = new UILabel(logString);
//@						logMenu.append(uil);
//@					}
//@				}
//@				UIScreen cs = UICanvas.getInstance().getCurrentScreen();
//@				if (cs != null) cs.addPopup(logMenu);
//@			}
//@		
	// #enddebug

	public boolean hasQwerty() {
		// TODO Auto-generated method stub
		return qwerty;
	}

	public void setQwerty(boolean qwerty) {
		this.qwerty = qwerty;
	}

}
