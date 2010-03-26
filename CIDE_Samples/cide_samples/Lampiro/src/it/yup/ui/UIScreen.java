/* Copyright (c) 2008 Bluendo S.r.L.
 * See about.html for details about license.
 *
 * $Id: UIScreen.java 1601 2009-06-19 14:09:03Z luca $
*/

package it.yup.ui;

import java.util.Enumeration;
import java.util.Vector;

import javax.microedition.lcdui.Canvas;
import javax.microedition.lcdui.Font;
import javax.microedition.lcdui.Graphics;
import javax.microedition.lcdui.Image;

/**
 * Fornisce uno schermo che mostra una lista di items generici e espone
 * funzionalita' di menu. Ogni item puo' avere una azione associata alla
 * pressione del tasto di selezione.<br>
 * 
 * Il Menu viene mostrato alla pressione del tasto del menu (tipicamente quello
 * destro), E' una lista di voci che viene mostrata in un overlay che non copre
 * tutto lo schermo (la dimensione dipende dalla dimensione dello schermo e
 * dalla dimensione del font; si pua' aggiungere che se la scritta sfora la
 * larghezza del menu e la voce e' selezionata ogni X (=1 o 2) secondi il menu
 * viene ridipinto.<br>
 * 
 */
public class UIScreen extends UIMenu implements UIIContainer {

	/** il menu di questo screen */
	private UIMenu menu;

	private boolean freezed = false;

	/*
	 * UIScreen needs a fresh repaint (meaning even the background must be completly
	 * repainted) when changing from one screen to another.
	 */
	boolean firstPaint = false;

	/**
	 * The {@link UICanvas} owning the screen.
	 */
	private UICanvas canvas = null;

	/**
	 * The list of the popup for this screen.
	 */
	protected Vector popupList;

	/**
	 * The screen title
	 */
	public UILabel titleLabel = new UILabel("");
	protected Image la = UICanvas.getUIImage("/icons/lag.png");
	protected Image ra = UICanvas.getUIImage("/icons/rag.png");
	protected UISeparator headerSep = new UISeparator(1);
	protected UIVLayout headerLayout = null;

	/**
	 * The footer elements
	 */

	protected UIHLayout footer = new UIHLayout(2);
	public UILabel footerLeft = new UILabel("");
	public UILabel footerRight = new UILabel("");

	/**
	 * The graphics in which the screen is painted
	 * 
	 */
	private Graphics graphics;

	private Vector paintedItems = new Vector(10);

	/** Called to notify that the {@link UIScreen} has become visible */
	public void showNotify() {

	}

	/** Called to notify that the {@link UIScreen} has become invisible */
	public void hideNotify() {

	}

	/**
	 * Constructor, each screen is associated to the Canvas
	 */
	public UIScreen() {
		super("Screen");
		borderSize = 0;
		int imgHeight = la.getHeight();
		this.headerLayout = new UIVLayout(2, imgHeight);
		headerSep.setFg_color(0);
		headerLayout.insert(headerSep, 0, 1, UILayout.CONSTRAINT_PIXELS);
		headerLayout.insert(titleLabel, 1, imgHeight,
				UILayout.CONSTRAINT_PIXELS);
		titleLabel.setScreen(this);
		titleLabel.setBg_color(UIConfig.header_bg);
		titleLabel.setFg_color(UIConfig.menu_title);
		titleLabel.setFont(UIConfig.font_title);
		titleLabel.setAnchorPoint(Graphics.HCENTER);

		this.setItemList(new Vector());
		this.popupList = new Vector();
		this.canvas = UICanvas.getInstance();
		selectedIndex = -1;
		this.width = UICanvas.getInstance().getWidth();
		this.height = UICanvas.getInstance().getClipHeight();

		footer.setGroup(false);
		footer.setFocusable(false);
		footer.insert(footerLeft, 0, 50, UILayout.CONSTRAINT_PERCENTUAL);
		footer.insert(footerRight, 1, 50, UILayout.CONSTRAINT_PERCENTUAL);
		this.footerLeft.setScreen(this);
		this.footerRight.setScreen(this);
		footerLeft.setBg_color(UIConfig.header_bg);
		footerLeft.setFg_color(UIConfig.menu_title);
		footerLeft.setFont(UIConfig.font_title);
		footerLeft.setAnchorPoint(Graphics.LEFT);
		footerRight.setBg_color(UIConfig.header_bg);
		footerRight.setFg_color(UIConfig.menu_title);
		footerRight.setFont(UIConfig.font_title);
		footerRight.setAnchorPoint(Graphics.RIGHT);
		this.screen = this;

	}

	/**
	 * Adds the passed item to the bottom of the itemList.
	 * 
	 * @param ui
	 *            The item to add.
	 * @return The position which the item has been added in.
	 */
	public int append(UIItem ui) {
		getItemList().addElement(ui);
		ui.setScreen(this);
		ui.setContainer(this);
		ui.setDirty(true);
		this.askRepaint();
		return getItemList().size() - 1;
	}

	/**
	 * Adds a popUp to the popup vector.
	 * 
	 * @param popUp
	 *            The item to add.
	 */
	public void addPopup(UIMenu popUp) {
		this.popupList.addElement(popUp);
		popUp.setDirty(true);
		popUp.setScreen(this);
		this.askRepaint();
	}

	/**
	 * Remove a popUp from the popup vector.
	 * 
	 * @param popUp
	 *            The item to remove.
	 */
	public void removePopup(UIMenu popUp) {
		if (popUp != null && this.popupList.contains(popUp)) {
			popUp.setOpenedState(false);
			popUp.setSubmenu(null);
			this.popupList.removeElement(popUp);
			this.invalidateArea(popUp.getAbsoluteX(), popUp.getAbsoluteY(),
					popUp.getAbsoluteX() + popUp.getWidth(), popUp
							.getAbsoluteY()
							+ popUp.getHeight(this.graphics));
		}
		this.askRepaint();
	}

	public void removeAllPopups() {
		for (Enumeration en = popupList.elements(); en.hasMoreElements();) {
			UIMenu uim = (UIMenu) en.nextElement();
			uim.setOpenedState(false);
			uim.setSubmenu(null);
			this.invalidateArea(uim.getAbsoluteX(), uim.getAbsoluteY(), uim
					.getAbsoluteX()
					+ uim.getWidth(), uim.getAbsoluteY()
					+ uim.getHeight(this.graphics));
		}
		this.popupList.removeAllElements();
		this.askRepaint();
	}

	public boolean popupIsPresent(UIMenu popUp) {
		return this.popupList.contains(popUp);
	}

	/**
	 * Adds the passed item at the pos-th position of the itemList.
	 * 
	 * @param pos
	 *            The position which the item has to be added in.
	 * @param ui
	 *            The item to add.
	 */
	public void insert(int pos, UIItem ui) {
		if (pos < 0 || pos >= getItemList().size()) { throw new ArrayIndexOutOfBoundsException(
				"Invalid menu pos: " + getItemList() + ", "
						+ getItemList().size()); }
		int oldSelectedIndex = this.getSelectedIndex();
		getItemList().insertElementAt(ui, pos);
		if (pos <= oldSelectedIndex) this.setSelectedIndex(++oldSelectedIndex);
		ui.setScreen(this);
		ui.setContainer(this);
		ui.setDirty(true);
		for (int i = pos; i < getItemList().size(); i++) {
			((UIItem) getItemList().elementAt(i)).setDirty(true);
		}
		this.askRepaint();
	}

	/**
	 * Removes the item at the pos-th position of the itemList.
	 * 
	 * @param pos
	 *            The position from which the item has to be removed.
	 * @return returns the removed Item.
	 */
	public UIItem remove(int pos) {
		UIItem ui = super.remove(pos);
		for (int i = pos; i < getItemList().size(); i++) {
			((UIItem) getItemList().elementAt(i)).setDirty(true);
		}
		if (selectedIndex >= pos) selectedIndex--;
		if (firstVisibleIndex > this.getItemList().size() - 1) firstVisibleIndex = 0;
		if (lastVisibleIndex > this.getItemList().size() - 1) lastVisibleIndex = this
				.getItemList().size() - 1;
		this.askRepaint();
		return ui;
	}

	/**
	 * Removes the passed item from the itemList.
	 * 
	 * @param ui
	 *            The item to remove.
	 */
	public boolean remove(UIItem ui) {
		int tempIndex = this.indexOf(ui);
		if (tempIndex >= 0) {
			this.remove(tempIndex);
			return true;
		}
		return false;
	}

	/**
	 * Removes all the items.
	 */
	public void removeAll() {
		super.removeAll();
		firstVisibleIndex = 0;
		lastVisibleIndex = 0;
		this.askRepaint();
	}

	/**
	 * Replace the item at the passed inedx with the newly furnished one. If
	 * {@code pos} is not a valid value for the itemList an
	 * {@link ArrayIndexOutOfBoundsException} is thrown.
	 * 
	 * @param pos
	 *            The item position
	 * @param ui
	 *            The {@link UIItem} to replace
	 * @return the removed item
	 */
	public UIItem replace(int pos, UIItem ui) {
		UIItem posth = super.replace(pos, ui);
		ui.setScreen(this);
		ui.setContainer(this);
		this.askRepaint();
		return posth;
	}

	public void replace(UIItem oldItem, UIItem newItem) {
		int oldPos = this.indexOf(oldItem);
		this.replace(oldPos, newItem);
	}

	/**
	 * Swaps two object in the list
	 * 
	 * @param firstIndex
	 * @param secondIndex
	 * @return
	 */
	public void swap(int firstIndex, int secondIndex) {
		if (firstIndex >= this.getItemList().size()
				|| secondIndex >= this.getItemList().size()) throw new ArrayIndexOutOfBoundsException(
				"Invalid itemList pos: " + firstIndex + " " + secondIndex
						+ ", " + getItemList().size());

		Object temp = getItemList().elementAt(firstIndex);
		getItemList().setElementAt(getItemList().elementAt(secondIndex),
				firstIndex);
		getItemList().setElementAt(temp, secondIndex);
		UIItem dirtyItem = (UIItem) getItemList().elementAt(firstIndex);
		dirtyItem.setDirty(true);
		UIItem dirtyItem2 = (UIItem) getItemList().elementAt(secondIndex);
		dirtyItem2.setDirty(true);
		this.askRepaint();
	}

	/**
	 * Set this screen's menu. If the menu is {@code null} the menu is disabled.
	 * 
	 * @param _menu
	 *            the screen's menu
	 */
	public void setMenu(UIMenu _menu) {
		menu = _menu;
		if (menu != null) menu.setScreen(this);
	}

	/**
	 * @return The screen menu.
	 */
	public UIMenu getMenu() {
		return menu;
	}

	/*
	*   Raised when a drag is made
	*/
	public void startDrag(UIItem draggedItem) {

	}

	/*
	*   Raised when a drag is made
	*/
	public void endDrag() {

	}

	/**
	 * <p>
	 * Handle the key pressure.
	 * </p>
	 * Dispatches the key pressure to the menu or to the item depending on the
	 * state.
	 * 
	 * @param key
	 *            The pressed key.
	 * 
	 * @return <code>true</code> if the screen will keep the selection
	 */
	public boolean keyPressed(int key) {

		/* the part below has been introduced to handle correctly the 
		 * synchronization between the "UI" thread and the others that access the UI
		 * 
		 * Notes:
		 * 1) paintCount is synchronized and used here and in askRepaint: if 
		 *              paintCount is greather than zero the askRepaint does not repaint 
		 * 2) if key is one of the "action" key (fire or menu keys) this thread do not lock
		 *              because they will call askRepaint later
		 *              
		 * 
		 */
		//              boolean needLocking = true;
		//              if (key == UICanvas.MENU_RIGHT || key == UICanvas.MENU_LEFT
		//                              || canvas.getGameAction(key) == Canvas.FIRE) {
		//                      needLocking = false;
		//              }
		//              synchronized (this) {
		//                      if (painting == false)
		//                              painting = true;
		//                      else
		//                              paintBooked = true;
		//              }
		//              if (needLocking) UICanvas.lock();
		boolean selectionKept = false;
		try {
			if (this.popupList.size() > 0) {
				handleMenuKey((UIMenu) this.popupList.elementAt(this.popupList
						.size() - 1), key);
				selectionKept = true;
				// if the key is propagated to a popup or a menu it can can change 
				// the popup size and hence force a repaint
				this.firstPaint = true;
			} else if (this.menu != null && this.menu.isOpenedState()) {
				handleMenuKey(menu, key);
				selectionKept = true;
				// if the key is propagated to a popup or a menu it can can change 
				// the popup size and hence force a repaint
				this.firstPaint = true;
			} else {
				if (key == UICanvas.MENU_RIGHT) {
					/* open menu */
					if (menu != null) {
						int menuSize = menu.getItemList().size();
						UIMenu contMenu = null;
						UIItem selItem = null;
						if (selectedIndex >= 0
								&& selectedIndex < this.itemList.size()) {
							selItem = ((UIItem) this.getItemList().elementAt(
									selectedIndex)).getSelectedItem();
						}
						if (selItem != null) contMenu = selItem.getSubmenu();
						else if (selectedIndex >= 0
								&& selectedIndex < this.itemList.size()) {
							contMenu = ((UIItem) this.getItemList().elementAt(
									selectedIndex)).getSubmenu();
						}
						if (menuSize == 2 && contMenu == null) {
							this.menuAction(menu, (UIItem) menu.getItemList()
									.elementAt(0));
						} else if (menuSize > 1) {
							menu.setOpenedState(true);
							this.askRepaint();
						} else if (menuSize == 1) {
							this.menuAction(menu, (UIItem) menu.getItemList()
									.elementAt(0));
						}
					}
					selectionKept = true;
				} else if (key == UICanvas.MENU_LEFT) {
					if (selectedIndex >= 0
							&& selectedIndex < this.itemList.size()) {
						// A "contextual menu" has been asked
						UIItem selItem = ((UIItem) this.getItemList()
								.elementAt(selectedIndex)).getSelectedItem();
						// An UIitem like UICombobox can have no selectedItem but a subMenu
						UIMenu contMenu = null;
						if (selItem != null) contMenu = selItem.getSubmenu();
						else
							contMenu = ((UIItem) this.getItemList().elementAt(
									selectedIndex)).getSubmenu();
						if (this.selectedIndex >= 0 && contMenu != null) {
							// if menu has 0 width centers it on the screen
							if (contMenu.getWidth() == 0) contMenu.width = UICanvas
									.getInstance().getWidth()
									- contMenu.getAbsoluteX() * 2;
							if (contMenu.getItemList().size() > 1) this
									.addPopup(contMenu);
							else if (contMenu.getItemList().size() == 1) menuAction(
									contMenu, (UIItem) contMenu.getItemList()
											.elementAt(0));
						} else {
							// second items of normal menu has been asked
							int menuSize = menu.getItemList().size();
							if (menuSize == 2) {
								this.menuAction(menu, (UIItem) menu
										.getItemList().elementAt(1));
							}
						}
					} else {
						// second items of normal menu has been asked
						int menuSize = menu.getItemList().size();
						if (menuSize == 2) {
							this.menuAction(menu, (UIItem) menu.getItemList()
									.elementAt(1));
						}
					}
				}

				// first let the item receive the keyPressure
				if (this.selectedIndex >= 0) selectionKept = ((UIItem) this
						.getItemList().elementAt(this.selectedIndex))
						.keyPressed(key);

				int ka = canvas.getGameAction(key);
				int newSelectedIndex = 0;
				// then take the "movement"
				if (selectionKept == false) {
					/* no menu opened, handle key normally */
					switch (ka) {
						case Canvas.UP:
							if (selectedIndex == 0) {
								/* first item selected, can't go up further */
								break;
							}
							newSelectedIndex = 0;
							if (selectedIndex >= 0) {
								newSelectedIndex = selectedIndex - 1;
							}
							newSelectedIndex = traverseFocusable(
									newSelectedIndex, false);
							if (newSelectedIndex >= 0
									&& newSelectedIndex < this.getItemList()
											.size()) {
								UIItem selectedItem = ((UIItem) this
										.getItemList().elementAt(
												newSelectedIndex));
								if (selectedItem.isFocusable()) {
									if (selectedIndex >= 0) {
										((UIItem) this.getItemList().elementAt(
												selectedIndex))
												.setSelected(false);
									}
									selectedIndex = newSelectedIndex;
									((UIItem) this.getItemList().elementAt(
											selectedIndex)).setSelected(true);
								} else {
									this.firstVisibleIndex = 0;
									for (int i = 0; i <= this.lastVisibleIndex; i++) {
										((UIItem) this.getItemList().elementAt(
												i)).setDirty(true);
									}
								}
							}

							this.askRepaint();
							break;
						case Canvas.DOWN:
							if (selectedIndex == this.getItemList().size() - 1) {
								/* last item selected, can't go down further */
								break;
							}
							newSelectedIndex = 0;
							if (selectedIndex >= 0) {
								/* move selection down */
								newSelectedIndex = selectedIndex + 1;
							}
							newSelectedIndex = traverseFocusable(
									newSelectedIndex, true);
							if (newSelectedIndex >= 0
									&& newSelectedIndex < this.getItemList()
											.size()) {
								if (((UIItem) this.getItemList().elementAt(
										newSelectedIndex)).isFocusable()) {
									if (selectedIndex >= 0) {
										((UIItem) this.getItemList().elementAt(
												selectedIndex))
												.setSelected(false);
									}
									selectedIndex = newSelectedIndex;
									((UIItem) this.getItemList().elementAt(
											selectedIndex)).setSelected(true);
								} else if (lastVisibleIndex < this
										.getItemList().size() - 1) {
									int gapHeight = 0;
									for (int i = this.lastVisibleIndex + 1; i < this
											.getItemList().size(); i++)
										gapHeight += ((UIItem) this
												.getItemList().elementAt(i))
												.getHeight(this.graphics);
									do {
										UIItem ithElem = ((UIItem) this
												.getItemList().elementAt(
														firstVisibleIndex));
										gapHeight -= ithElem
												.getHeight(this.graphics);
										this.firstVisibleIndex++;
									} while (gapHeight > 0);
									for (int i = firstVisibleIndex; i < this
											.getItemList().size(); i++) {
										((UIItem) this.getItemList().elementAt(
												i)).setDirty(true);
									}
								}
							}
							this.askRepaint();
							break;
						default:
							break;
					}
				}

				// then raise the "fire" event
				if (ka == Canvas.FIRE && this.selectedIndex >= 0) {
					UIItem selectedItem = ((UIItem) this.getItemList()
							.elementAt(selectedIndex)).getSelectedItem();
					if (selectedItem != null) this.itemAction(selectedItem);
					selectionKept = true;
				}

				// #mdebug
//@				System.out.println("moved: " + firstVisibleIndex + "/"
//@						+ lastVisibleIndex + "/" + selectedIndex);
				// #enddebug
			}
		} catch (Exception e) {
			// #mdebug
//@			System.out.println("In keyPressed");
//@			e.printStackTrace();
			// #enddebug
		}

		//              if (needLocking) UICanvas.unlock();
		//              synchronized (this) {
		//                      painting = false;
		//              }
		//this.askRepaint();

		return selectionKept;
	}

	void handleMenuKey(UIMenu openMenu, int key) {
		Object[] oldPopups = new Object[popupList.size()];
		this.popupList.copyInto(oldPopups);
		if (key == UICanvas.MENU_RIGHT
				|| canvas.getGameAction(key) == Canvas.FIRE) {
			/* select, close menu */
		} else if (key == UICanvas.MENU_LEFT) {
			/* cancel, close menu */
			openMenu.setOpenedState(false);
			if (openMenu != null) {
				// the menu was here -> invalidate all:

				int x1 = openMenu.getAbsoluteX();
				int y1 = openMenu.getAbsoluteY();
				int x2 = x1 + openMenu.getWidth();
				int y2 = y1 + openMenu.getHeight(this.graphics);
				this.invalidateArea(x1, y1, x2, y2);
			}
			if (openMenu.getParentMenu() != null) {
				openMenu.getParentMenu().setSubmenu(null);
			}
			this.removePopup((UIMenu) openMenu);

			this.askRepaint();
			return;
		}
		int ga = canvas.getGameAction(key);

		UIItem um = openMenu.keyPressed(key, ga);

		if (um != null) {
			// if a UIMenuItem has been selected and it has not
			// a submenu i need to close the menu
			if (um.getSubmenu() == null) {
				if (openMenu.isAutoClose()) {
					openMenu.setOpenedState(false);
					// the menu was here -> invalidate all:
					int menuY = openMenu.getAbsoluteY();
					int cumulativeHeight = this.headerLayout
							.getHeight(this.graphics);
					for (int i = this.firstVisibleIndex; i < this.getItemList()
							.size(); i++) {
						UIItem ith = (UIItem) this.getItemList().elementAt(i);
						cumulativeHeight += ith.getHeight(this.graphics);
						if (cumulativeHeight >= menuY) {
							ith.setDirty(true);
						}
					}
					if (this.menu != null) {
						this.menu.setOpenedState(false);
						this.menu.setSubmenu(null);
					}
				}
				this.askRepaint();
			}

			// first close the menu and then complete the action 
			if ((key == UICanvas.MENU_RIGHT || ga == UICanvas.FIRE)) {
				this.menuAction(openMenu, um);
			}

			if (um.getSubmenu() == null) {
				if (openMenu.isAutoClose()) {
					// we cannot use: this.removeAllPopups();
					// because if a menu has been added meanwhile (for example in
					// response
					// to a keypression) it is removed suddenly !!!
					for (int i = 0; i < oldPopups.length; i++) {
						this.removePopup((UIMenu) oldPopups[i]);
					}
				}
				this.askRepaint();
			}
		}
	}

	private boolean paintBooked = false;
	private boolean painting = false;

	/**
	 * Used by items and menus to ask the containing screen a repaint.
	 */
	protected boolean askRepaint() {
		boolean paintEnable = false;

		synchronized (this) {
			// #mdebug 
//@			System.out.println("painting: " + painting);
			// #enddebug
			if (painting == false) {
				paintEnable = true;
				painting = true;
			} else
				paintBooked = true;
		}

		if (paintEnable) {
			try {
				this.canvas.askRepaint(this);
			} catch (Exception e) {
				// #mdebug 
//@				System.out.println("in screen painting");
//@				e.printStackTrace();
				// #enddebug
			}
			// someone else in the meanwhile
			boolean paintForce = false;
			synchronized (this) {
				painting = false;
				if (paintBooked) {
					paintBooked = false;
					paintForce = true;
				}
			}
			if (paintForce) {
				this.askRepaint();
			}
		}
		return true;
	}

	/**
	 * Paints the screen on the canvas graphics; in case the subclasses override
	 * this method they *MUST* provide to call super.paint (...); .
	 * 
	 * @param g
	 *            The graphics on which to paint.
	 */
	public boolean paint(Graphics g) {
		changed = false;
		this.graphics = g;
		// in many devices the height may vary during execution
		this.height = UICanvas.getInstance().getClipHeight();
		int footerHeight = UIConfig.font_title.getHeight() + 2;
		int headerHeight = (headerLayout != null) ? headerLayout.getHeight(g)
				: 0;
		/* draw title */
		Font cfont = g.getFont();
		// String oldFont = cfont.getFace() + "-" + cfont.getHeight() + "-"
		// + cfont.getSize() + "-" + cfont.getStyle() + " ";

		if (headerLayout != null && headerLayout.isDirty()) {
			int oldColor = g.getColor();
			g.setColor(UIConfig.header_bg);
			g.fillRect(0, 0, g.getClipWidth(), headerHeight);
			g.drawImage(la, 0, 0, Graphics.LEFT | Graphics.TOP);
			g.translate(la.getWidth(), 0);
			int titleWidth = g.getClipWidth() - la.getWidth() - ra.getWidth();
			headerLayout.paint0(g, titleWidth, headerHeight);
			g.translate(titleWidth, 0);
			g.drawImage(ra, 0, 0, Graphics.LEFT | Graphics.TOP);
			g.translate(-g.getTranslateX(), 0);
			g.setColor(oldColor);
			changed = true;
		}

		/* calculate clip area for content */
		int canvasHeight = this.canvas.getClipHeight();
		// subtract footer height
		canvasHeight -= footerHeight;
		int canvasWidth = this.canvas.getWidth();
		// subtract header height (if present)
		g.setClip(0, headerHeight, canvasWidth, canvasHeight - headerHeight);
		g.translate(0, headerHeight);

		// #mdebug 
//@		System.out.println("paint0: " + canvasWidth + "/"
//@				+ (canvasHeight - headerHeight) + "/" + g.getClipHeight() + "/"
//@				+ g.getTranslateY());
		// #enddebug

		// a trick used to avoid painting of screen when a popup is opened
		/* draw menu if opened */
		boolean aMenuIsOpened = false;
		if ((this.menu != null && this.menu.isOpenedState())
				|| this.popupList.size() > 0) {
			aMenuIsOpened = true;
		}

		if (aMenuIsOpened == false || firstPaint) {
			firstPaint = false;
			this.paint0(g, canvasWidth, canvasHeight - headerHeight);
			this.removePaintedItem(this);

			// clean the gap
			if (this.lastVisibleIndex == this.getItemList().size() - 1) {
				g.setColor(getBg_color() >= 0 ? getBg_color()
						: UIConfig.bg_color);
				int yGapHeight = canvasHeight - headerHeight;
				int usedHeight = 0;
				for (int i = firstVisibleIndex; i <= lastVisibleIndex; i++) {
					UIItem ui = (UIItem) getItemList().elementAt(i);
					usedHeight += ui.getHeight(g);
				}
				g.translate(-g.getTranslateX(), headerHeight + usedHeight
						- g.getTranslateY());
				int gap = 1 + yGapHeight - usedHeight;
				if (this.getNeedScrollbar() == false) {
					g.fillRect(1, 0, canvasWidth, gap);
				} else {
					g.fillRect(1, 0, canvasWidth - UIConfig.scrollbarWidth - 1,
							gap);
				}
				// if the gap is filled menu and popups can be dirty
				// and must be redrawn
				if (menu != null) {
					menu.setDirty(true);
				}
				for (Enumeration en = popupList.elements(); en
						.hasMoreElements();) {
					UIMenu ithPopup = (UIMenu) en.nextElement();
					ithPopup.setDirty(true);
				}
				changed = true;
			}
		}

		/* draw menu if opened */
		if (this.menu != null && this.menu.isOpenedState()
				&& this.menu.isDirty()) {
			// force repaint of all the menu items
			this.menu.setDirty(true);
			//int x0 = 17;
			int x0 = 29;
			int y0 = canvasHeight / 2;
			//int x1 = canvasWidth - x0 + 5;
			int x1 = canvasWidth;
			//int y1 = canvasHeight - 2;
			int y1 = canvasHeight;
			// g.setClip(x0, y0, x1, y1); XXX: TODO
			int translatedX = g.getTranslateX();
			int translatedY = g.getTranslateY();
			g.translate(-translatedX, -translatedY);
			g
					.setClip(0, headerHeight, canvasWidth, canvasHeight
							- headerHeight);
			// I choose a maximum height for the menus
			int menuHeight = menu.getHeight(g);
			if (menuHeight > y0) menuHeight = y0;
			g.translate(x0, y1 - menuHeight);
			menu.setAbsoluteX(x0);
			menu.setAbsoluteY(y1 - menuHeight);
			menu.paint0(g, x1 - x0, y1 - y0);
			changed = true;
		}

		// first draw the popup list
		boolean hasPopup = false;
		for (Enumeration en = popupList.elements(); en.hasMoreElements();) {
			hasPopup = true;
			changed = true;
			int translatedX = g.getTranslateX();
			int translatedY = g.getTranslateY();
			UIMenu ithPopup = (UIMenu) en.nextElement();
			g.translate(-translatedX, -translatedY);
			// move the popup in order to have it on the screen
			if (ithPopup.getAbsoluteY() < headerHeight) {
				ithPopup.setAbsoluteY(headerHeight);
			}

			// subtract footer height and header for the available area
			int availableHeight = this.canvas.getClipHeight() - footerHeight
					- ithPopup.getAbsoluteY();
			// set a clip for the popup in order to avoid overprinting
			// header and footer;
			g
					.setClip(0, headerHeight, canvasWidth, canvasHeight
							- headerHeight);
			g.translate(ithPopup.getAbsoluteX(), ithPopup.getAbsoluteY());
			// if a popup is dirty must be redrawn and
			// all the subsequent
			// if (ithPopup.isDirty() && i < (this.popupList.size() - 1)) {
			// ((UIMenu) this.popupList.elementAt(i + 1)).setDirty(true);
			// }

			if (ithPopup.isDirty()) {
				// force repaint of all the menu items
				ithPopup.setDirty(true);
				ithPopup.paint0(g, ithPopup.width, availableHeight);
				ithPopup.setOpenedState(true);
			}
		}
		// draw the border (just in case something has overwritten it)
		g.translate(-g.getTranslateX(), headerHeight - g.getTranslateY());
		g.setClip(0, 0, canvas.getWidth(), canvas.getClipHeight());
		g.setColor(0x223377);
		g.drawRect(0, 0, canvasWidth - 1, canvasHeight - headerHeight);

		g.translate(-g.getTranslateX(), -g.getTranslateY());
		/* footer height */
		g
				.translate(0 - g.getTranslateX(), canvasHeight + 1
						- g.getTranslateY());

		/* draw menu buttons - LEFT */
		String left = "";
		if (hasPopup || (menu != null && menu.isOpenedState())) {
			if (hasPopup) {
				left = ((UIMenu) this.popupList
						.elementAt(this.popupList.size() - 1)).cancelMenuString;
			}
			if ((menu != null && menu.isOpenedState())) {
				left = menu.cancelMenuString;
			}
		} else if (selectedIndex >= 0) {
			UIItem selItem = ((UIItem) this.getItemList().elementAt(
					selectedIndex)).getSelectedItem();
			UIMenu contMenu = null;
			if (selItem != null) contMenu = selItem.getSubmenu();
			else
				contMenu = ((UIItem) this.getItemList()
						.elementAt(selectedIndex)).getSubmenu();
			if (contMenu != null) {
				if (contMenu.getItemList().size() == 1) {
					UIItem firstItem = (UIItem) contMenu.getItemList()
							.elementAt(0);
					if (firstItem instanceof UILabel) left = ((UILabel) firstItem)
							.getText();
					else
						left = UIConfig.optionsString;
				} else
					left = UIConfig.optionsString;
			}
		}

		if (left.compareTo(this.footerLeft.getText()) != 0) {
			this.footerLeft.setText(left);
			changed = true;
		}

		String right = "";
		if (menu != null && !menu.isOpenedState() && !hasPopup) {
			if (menu.getItemList().size() == 1) right = ((UILabel) menu
					.getItemList().elementAt(0)).getText();
			else if (menu.getItemList().size() == 2 && left.length() == 0) {
				right = ((UILabel) menu.getItemList().elementAt(0)).getText();
				left = ((UILabel) menu.getItemList().elementAt(1)).getText();
			} else
				right = UIConfig.menuString;
		} else if ((menu != null && menu.isOpenedState()) || hasPopup) {
			if (hasPopup) {
				right = ((UIMenu) this.popupList.elementAt(this.popupList
						.size() - 1)).selectMenuString;
			}
			if ((menu != null && menu.isOpenedState())) {
				right = menu.selectMenuString;
			}
		}
		if (right.compareTo(this.footerRight.getText()) != 0) {
			this.footerRight.setText(right);
			changed = true;
		}
		if (left.compareTo(this.footerLeft.getText()) != 0) {
			this.footerLeft.setText(left);
			changed = true;
		}

		if (footer.isDirty()) {
			footer.paint0(g, canvasWidth, footer.getHeight(g));
			g.setColor(UIConfig.header_fg);
			g.drawLine(canvasWidth / 2, 0, canvasWidth / 2, footerHeight);
		}

		// #mdebug 
//@		System.out.println("screenPaint done: " + g.getClipWidth() + "/"
//@				+ g.getClipHeight() + "/" + g.getTranslateX() + "/"
//@				+ g.getTranslateY());
		// #enddebug
		g.setFont(cfont);
		return changed;
	}

	/**
	 * @return This screen's title
	 */
	public String getTitle() {
		return this.titleLabel.getText();
	}

	/**
	 * Sets this screen's title
	 * 
	 * @param title
	 *            the screen title
	 */
	public void setTitle(String title) {
		this.titleLabel.setText(title);
	}

	public UICanvas getCanvas() {
		return canvas;
	}

	/*
	 * Invalidates the portion of the screen between (0,0) and (w,h)
	 */
	public void invalidate(int w, int h) {
		int cumulativeHeight = 0;
		// remove footer height
		int fh = this.headerLayout.getHeight(this.graphics);
		h = h - fh;

		// reset the Items
		this.firstVisibleIndex = 0;
		for (Enumeration en = getItemList().elements(); en.hasMoreElements();) {
			UIItem ithItem = (UIItem) en.nextElement();
			cumulativeHeight += ithItem.getHeight(this.graphics);
			ithItem.setDirty(true);
		}
		// reset the popups
		for (Enumeration en = popupList.elements(); en.hasMoreElements();) {
			UIMenu ithMenu = (UIMenu) en.nextElement();
			int ithMenuHeight = ithMenu.getAbsoluteY()
					+ ithMenu.getHeight(this.graphics);
			if (ithMenuHeight > h) {
				ithMenu.setAbsoluteY(ithMenu.getAbsoluteY()
						- (ithMenuHeight - h));
			}
			int ithMenuWidth = ithMenu.getAbsoluteX() + ithMenu.getWidth();
			if (ithMenuWidth > w) {
				ithMenu.setAbsoluteX(ithMenu.getAbsoluteX()
						- (ithMenuWidth - w));
			}

			if (ithMenu.getAbsoluteX() < w || ithMenu.getAbsoluteY() < h) {
				ithMenu.setDirty(true);
			}
		}
		this.headerLayout.setDirty(true);
		this.footer.setDirty(true);
	}

	/**
	 * This method is called each time a {@link UIMenuItem} of a {@link UIMenu}
	 * receives the pressure of a FIRE. The subclasses of screen SHOULD override
	 * this method in order to couple an action to the pressure of an action
	 * key.
	 * 
	 * @param menu
	 *            The selected Menu
	 * @param item
	 *            The {@link UIMenuItem} that received the key pressure
	 */
	public void menuAction(UIMenu menu, UIItem item) {
	}

	/**
	 * This method is called each time a {@link UIItem} of this {@link UIScreen}
	 * receives the pressure of a FIRE. The subclasses of screen SHOULD override
	 * this method in order to couple an action to the pressure of an action
	 * key.
	 * 
	 * @param item
	 *            The {@link UIItem} that received the key pressure
	 */
	public void itemAction(UIItem item) {
	}

	/**
	 * @param graphics
	 *            the graphics to set
	 */
	public void setGraphics(Graphics graphics) {
		this.graphics = graphics;
	}

	/**
	 * @return the graphics
	 */
	public Graphics getGraphics() {
		return graphics;
	}

	/**
	 * @return Return true if this {@link UIScreen} is freezed
	 */
	synchronized public boolean isFreezed() {
		return freezed;
	}

	/**
	 * @param Freezes
	 *            or Unfreezes this {@link UIScreen}.
	 */
	synchronized public void setFreezed(boolean freezed) {
		this.freezed = freezed;
	}

	/**
	 * All the menuItems and popups that intersects this area are set as dirty
	 */
	public void invalidateArea(int x1, int y1, int x2, int y2) {
		// first check if it intersects any item in itemList
		int cumulativeHeight = this.headerLayout.getHeight(this.graphics);
		for (int i = this.firstVisibleIndex; i < this.getItemList().size(); i++) {
			UIItem ith = (UIItem) this.getItemList().elementAt(i);
			int xa = 0, xb = this.width;
			int ya = cumulativeHeight;
			int yb = cumulativeHeight + ith.getHeight(this.graphics);
			if (intersect(new int[] { x1, y1, x2, y2 }, new int[] { xa, ya, xb,
					yb })) {
				ith.setDirty(true);
			}
			cumulativeHeight = yb;
		}
		// then the popup
		for (Enumeration en = popupList.elements(); en.hasMoreElements();) {
			UIMenu ithMenu = (UIMenu) en.nextElement();
			int xa = ithMenu.getAbsoluteX(), xb = xa + ithMenu.getWidth();
			int ya = ithMenu.getAbsoluteY();
			int yb = ya + ithMenu.getHeight(this.graphics);
			if (intersect(new int[] { x1, y1, x2, y2 }, new int[] { xa, ya, xb,
					yb })) {
				ithMenu.setDirty(true);
			}
		}
		// and then the menu if opened
		if (this.menu != null && this.menu.isOpenedState()) {
			int xa = this.menu.getAbsoluteX(), xb = xa + this.menu.getWidth();
			int ya = this.menu.getAbsoluteY();
			int yb = ya + this.menu.getHeight(this.graphics);
			if (intersect(new int[] { x1, y1, x2, y2 }, new int[] { xa, ya, xb,
					yb })) {
				this.menu.setDirty(true);
			}
		}

	}

	private boolean intersect(int[] recta, int[] rectb) {
		int x1 = recta[0];
		int y1 = recta[1];
		int x2 = recta[2];
		int y2 = recta[3];
		int xa = rectb[0];
		int ya = rectb[1];
		int xb = rectb[2];
		int yb = rectb[3];
		if ((xb < x1) || (x2 < xa) || (yb < y1) || (y2 < ya)) return false;
		return true;
	}

	public void invalidatePopups(UIMenu drawnMenu, int x1, int y1, int x2,
			int y2) {

		// reset the clips in order to invalidate correctely 
		// all the objects in the screen
		Graphics g = this.graphics;
		int originalClipWidth = g.getClipWidth();
		int originalClipHeight = g.getClipHeight();
		int originalClipX = g.getClipX();
		int originalClipY = g.getClipY();
		int w = UICanvas.getInstance().getWidth();
		int h = UICanvas.getInstance().getClipHeight();
		g.setClip(0, 0, w, h);

		int cumulativeHeight = this.headerLayout.getHeight(g);
		for (int i = this.firstVisibleIndex; i < itemList.size(); i++) {
			UIItem ith = (UIItem) this.getItemList().elementAt(i);
			int xa = 0, xb = this.width;
			int ya = cumulativeHeight;
			int yb = cumulativeHeight + ith.getHeight(this.graphics);
			if (intersect(new int[] { x1, y1, x2, y2 }, new int[] { xa, ya, xb,
					yb })) {
				ith.setDirty(true);
			}
			cumulativeHeight = yb;
		}

		if (this.menu != null && this.menu != drawnMenu
				&& this.menu.isOpenedState()) {
			int xa = this.menu.getAbsoluteX(), xb = xa + this.menu.getWidth();
			int ya = this.menu.getAbsoluteY();
			int yb = ya + this.menu.getHeight(this.graphics);
			if (intersect(new int[] { x1, y1, x2, y2 }, new int[] { xa, ya, xb,
					yb })) {
				this.menu.setDirty(true);
			}
		}
		// then the popup
		for (Enumeration en = popupList.elements(); en.hasMoreElements();) {
			UIMenu ithMenu = (UIMenu) en.nextElement();
			if (ithMenu == drawnMenu) continue;
			int xa = ithMenu.getAbsoluteX(), xb = xa + ithMenu.getWidth();
			int ya = ithMenu.getAbsoluteY();
			int yb = ya + ithMenu.getHeight(this.graphics);
			if (intersect(new int[] { x1, y1, x2, y2 }, new int[] { xa, ya, xb,
					yb })) {
				ithMenu.setDirty(true);
			}
		}

		g.setClip(originalClipX, originalClipY, originalClipWidth,
				originalClipHeight);
	}

	public void setDirty(boolean dirty) {
		super.setDirty(dirty);
		for (Enumeration en = popupList.elements(); en.hasMoreElements();) {
			UIItem ithItem = (UIItem) en.nextElement();
			ithItem.setDirty(dirty);
		}
		if (this.menu != null) this.menu.setDirty(dirty);
		this.headerLayout.setDirty(true);
		this.footer.setDirty(true);
		this.paintedItems.removeAllElements();
	}

	public void setSelectedIndex(int selectedIndex) {
		super.setSelectedIndex(selectedIndex);
		if (selectedIndex > lastVisibleIndex) {
			// forcing it to be the last visible to avoid useless
			// redraw
			lastVisibleIndex = selectedIndex;
		}
	}

	/*
	 * The keyRepeated is usually handled at the sam manner
	 * moving the screen / control up and down 
	 */
	public void keyRepeated(int key) {
		int ga = UICanvas.getInstance().getGameAction(key);
		switch (ga) {
			case Canvas.DOWN:
			case Canvas.UP: {
				this.setFreezed(true);
				for (int i = 0; i < 5; i++) {
					this.keyPressed(key);
				}
				this.setFreezed(false);
				this.askRepaint();
			}
		}

	}

	/*
	 * The coordinates plus height and width of each item in this screen
	 *  
	 */
	public void addPaintedItem(UIItem item) {
		removePaintedItem(item);
		paintedItems.insertElementAt(item, 0);
	}

	/*
	 * Remove a painted item from screen paintItems
	 *  
	 */
	public void removePaintedItem(UIItem item) {
		paintedItems.removeElement(item);
	}

	public Vector getPaintedItems() {
		return paintedItems;
	}

	public void setPopupList(Vector popupList) {
		this.popupList = popupList;
	}

	public Vector getPopupList() {
		return popupList;
	}
}
