/* Copyright (c) 2008 Bluendo S.r.L.
 * See about.html for details about license.
 *
 * $Id: UIMenu.java 1542 2009-05-26 12:30:51Z luca $
*/

/**
 * 
 */
package it.yup.ui;

// #debug
//@import it.yup.util.Logger;

import java.io.IOException;
import java.util.Enumeration;
import java.util.Vector;

import javax.microedition.lcdui.Canvas;
import javax.microedition.lcdui.Font;
import javax.microedition.lcdui.Graphics;
import javax.microedition.lcdui.Image;

/**
 * Generico menu da inserire in uno {@link UIScreen}.
 * 
 */
public class UIMenu extends UIItem implements UIIContainer {

	/** The list of items included here. */
	Vector itemList;
	/** il sottomenu selezionato e aperto */
	private UIMenu openSubMenu;

	private boolean autoClose = true;

	/**
	 * A flag used to know if the scrollbar is need for the menu
	 */
	private boolean needScrollbar = false;

	/** Used to show if the menu is opened */
	boolean openedState = false;

	/**
	 * The selected menu index.
	 */
	int selectedIndex = -1;

	/** A flag to know if the menu has been changed and need repaint */
	boolean changed = false;

	/**
	 * The parent of this menu.
	 */
	private UIMenu parentMenu = null;

	/**
	 * Used to know the absolute origin of the Menu on the screen. We must
	 * invalidate the background when hiding.
	 */
	private int absoluteY = 0;

	/*
	 * The title label if present
	 */
	UILabel titleLabel;

	/*
	 * The name of the menu
	 */
	String name = "";

	/**
	 * Used to know the absolute origin of the Menu on the screen. We must
	 * invalidate the background when hiding.
	 */
	int absoluteX = 0;
	/**
	 * The index of he first visible item in the screen.
	 */
	protected int firstVisibleIndex = 0;
	/**
	 * The index of he first visible item in the screen.
	 */
	protected int lastVisibleIndex = 0;

	public String cancelMenuString = UIConfig.cancelMenuString;
			
	public String selectMenuString = UIConfig.selectMenuString;

	int borderSize = 0;

	/**
	 * The images used for submenus
	 */
	static public Image menuImage = null;
	static {
		try {
			menuImage = Image.createImage("/icons/menuarrow.png");
		} catch (IOException e) {
			// #mdebug
//@						System.out.println("In allocating menuImage" + e.getMessage());
			// #enddebug
		}
	}

	protected int traverseFocusable(int startingIndex, boolean directionDown) {
		if (directionDown) {
			while (startingIndex < this.itemList.size() - 1
					&& ((UIItem) this.itemList.elementAt(startingIndex))
							.isFocusable() == false) {
				startingIndex++;
			}
			return startingIndex;
		} else {
			while (startingIndex >= 1
					&& ((UIItem) this.itemList.elementAt(startingIndex))
							.isFocusable() == false) {
				startingIndex--;
			}
			return startingIndex;
		}

	}

	/**
	 * Costruisce un menu, associandogli un generico "nome"
	 * 
	 * @param name
	 *            il nome del menu
	 */
	public UIMenu(String _name) {

		itemList = new Vector();
		this.focusable = true;
		this.name = _name;
		if (_name != null && _name.length() > 0
				&& this instanceof UIScreen == false) {
			titleLabel = new UILabel(_name);
			Font font_body = UIConfig.font_body;
			Font title_font = Font.getFont(font_body.getFace(),
					Font.STYLE_BOLD, font_body.getSize());
			titleLabel.setFont(title_font);
			this.append(titleLabel);
			titleLabel.setBg_color(UIConfig.header_bg);
			titleLabel.setFg_color(UIConfig.menu_title);
			titleLabel.setFocusable(false);
			titleLabel.setAnchorPoint(Graphics.HCENTER);
		}
		borderSize = 2;
	}

	/**
	 * Returns the index of an item
	 * 
	 * @param item
	 *            The item to find
	 * @return The item index or -1 if this item is not in list.
	 */
	public int indexOf(UIItem item) {
		return itemList.indexOf(item);
	}

	/**
	 * Aggiunge l'item fornito al fondo della lista.
	 * 
	 * @param ui
	 *            the item to add
	 * @return the position where the Item has been added
	 */
	public int append(UIItem ui) {
		// UIMenu subMenu is not here anymore I add it outside
		ui.setFocusable(true);
		ui.setDirty(true);
		ui.setBg_color(UIConfig.menu_color);

		itemList.addElement(ui);
		if (ui.getSubmenu() != null) ui.getSubmenu().setParentMenu(this);
		ui.setScreen(this.screen);
		ui.setContainer(this);
		return itemList.size() - 1;
	}

	/**
	 * inserisce l'item alla posizione indicata
	 * 
	 * @param pos
	 *            la posizione in cui inserire l'item
	 * @param ui
	 *            l'item da inserire
	 */
	public void insert(int pos, UIItem ui) {
		if (pos < 0 || pos >= itemList.size()) { throw new ArrayIndexOutOfBoundsException(
				"Invalid menu pos: " + pos + ", " + itemList.size()); }
		ui.setFocusable(true);
		ui.setDirty(true);
		ui.setBg_color(UIConfig.menu_color);

		if (ui.getSubmenu() != null) ui.getSubmenu().setParentMenu(this);
		ui.setScreen(this.screen);
		ui.setContainer(this);
		itemList.insertElementAt(ui, pos);
	}

	/**
	 * rimuove l'item alla posizione indicata. Tutte le voci vengono "shiftate"
	 * (non vengono lasciati buchi)
	 * 
	 * @param pos
	 *            la posizione a cui rimuovere l'item
	 */
	public UIItem remove(int pos) {
		UIItem ui = null;
		if (pos < itemList.size() && pos >= 0) {
			try {
				ui = (UIItem) itemList.elementAt(pos);
				itemList.removeElementAt(pos);
			} catch (Exception e) {
				System.out.println("In removing menu item" + e.getMessage());
				e.printStackTrace();
				// #mdebug
				// #enddebug
			}
		}
		if (this.screen != null) {
			this.screen.removePaintedItem(ui);
		}
		return ui;
	}

	/**
	 * rimuove l'item fornito (scorre tutti gli item e cerca l'item X per cui
	 * vale {@code X.equals(ui)}
	 * 
	 * @param ui
	 *            l'item da rimuovere.
	 */
	public boolean remove(UIItem ui) {
		int idx = itemList.indexOf(ui);
		if (idx != -1) {
			remove(idx);
			if (this.screen != null) {
				this.screen.removePaintedItem(ui);
			}
			return true;
		}
		return false;
	}

	/**
	 * rimuove tutti gli items
	 */
	public void removeAll() {
		if (selectedIndex >= 0 && selectedIndex < this.itemList.size()) ((UIItem) this.itemList
				.elementAt(this.selectedIndex)).setSelected(false);
		if (this.screen != null) {
			Enumeration enItem = itemList.elements();
			while (enItem.hasMoreElements()) {
				this.screen.removePaintedItem((UIItem) enItem.nextElement());
			}
		}
		itemList.removeAllElements();
		selectedIndex = -1;
	}

	/**
	 * sostituisce l'item alla posizione indicata con quello nuovo fornito.
	 * L'item deve essere presente (ossia deve valere che la lista abbia la
	 * posizione {@code pos} valida.
	 * 
	 * @param pos
	 *            la posizione dell'item
	 * @param ui
	 *            l'item da sostituire
	 */
	public UIItem replace(int pos, UIItem ui) {
		if (pos >= this.itemList.size()) throw new ArrayIndexOutOfBoundsException(
				"Invalid itemList pos: " + pos + ", " + itemList.size());

		UIItem posth = (UIItem) this.itemList.elementAt(pos);
		itemList.setElementAt(ui, pos);
		ui.setScreen(screen);
		ui.setContainer(this);
		return posth;
	}

	/**
	 * Removes all items
	 */
	public void clear() {
		itemList.setSize(0);
	}

	/**
	 * Esegue il paint del menu
	 * 
	 * @param g
	 *            il contesto grafico da usare
	 * @param w
	 *            la larghezza massima da riempire
	 * @param h
	 *            l'altezza massima da riempire
	 */
	protected void paint(Graphics g, int w, int h) {
		this.width = w;
		if (this instanceof UIScreen) this.height = 0;
		else
			this.height = 1;
		int neededHeight = 0;
		int oy = g.getTranslateY();
		for (Enumeration en = itemList.elements(); en.hasMoreElements();) {
			UIItem ithItem = (UIItem) en.nextElement();
			int uithHeight = ithItem.getHeight(g);
			// if uithHeight <0 use a default height to compute scrollbar
			// stuff
			if (uithHeight < 0) uithHeight = g.getFont().getSize();
			// many UIItem use clipping and position to dinamically compute
			// height !!!!
			g.translate(0, uithHeight);
			this.height += uithHeight;
		}
		this.height += 2 * borderSize;
		g.translate(0, oy - g.getTranslateY());
		neededHeight = this.height;
		if (neededHeight == 0) neededHeight = 1;

		// now that we have "height" and "h" that is our available height
		// we must check how the menu can be resized or moved in the screen
		// so that it assumes a nice visible aspect
		boolean oldNeedScrollbar = needScrollbar;
		needScrollbar = false;

		// is it too big and it doesn't fit ?
		if (this.height > h) {
			// can we move it ?
			// use an offset from up
			int Y = this.absoluteY;
			int clipY = g.getClipY();
			int translateY = g.getTranslateY();
			if ((Y - clipY - translateY) > (this.height - h)) {
				this.absoluteY -= (this.height - h);
				// System.out.println("Translate 1: 0, " + (h - this.height));
				g.translate(0, (h - this.height));
				h = this.height;
			} else {
				int offset = 0;
				// an offset is used for popup to avoid their
				// touching the upper border
				if (this instanceof UIScreen == false) offset = g
						.getClipHeight() / 7;
				this.absoluteY = translateY + clipY + offset;// offset;
				this.height = g.getClipHeight() - offset;// - offset;
				g.translate(0, this.absoluteY - g.getTranslateY());// +
				needScrollbar = true;
				// recompute height only if not a screen:
				// screens have fixed size
				if (this instanceof UIScreen == false) h = this.height;
			}
		}
		if (needScrollbar == false && oldNeedScrollbar) {
			for (int i = firstVisibleIndex; i < this.lastVisibleIndex; i++) {
				UIItem uith = (UIItem) this.itemList.elementAt(i);
				uith.setDirty(true);
			}
		}

		int initialX = g.getTranslateX();
		int initialY = g.getTranslateY();
		// notify the screen my coordinates 
		coors[1] = initialY;
		coors[3] = this.height;

		// moving down or up the screen can cause the lastVisible or
		// firstVisible
		// items not to be fully visible. Recheck !!!
		boolean paintNeeded = true;
		while (paintNeeded == true) {
			paintNeeded = false;

			g.translate(1 - g.getTranslateX() + initialX, 1 - g.getTranslateY()
					+ initialY);
			g.translate(borderSize, borderSize);
			int scrollbarHeight = (h * h) / neededHeight;
			// to avoid a too big scrollbar;
			if (scrollbarHeight > (2 * h) / 3) {
				scrollbarHeight = (2 * h) / 3;
			}
			int scrollbarPosition = 0;
			for (int i = 0; i < this.firstVisibleIndex; i++) {
				UIItem uith = (UIItem) this.itemList.elementAt(i);
				scrollbarPosition += uith.getHeight(g);
			}
			scrollbarPosition *= h;
			scrollbarPosition /= neededHeight;

			int lastHeight = borderSize;
			// if a controil is dirty all the subsequent must be set as dirty since the height
			// can be changed
			boolean lastDirty = false;
			for (int i = this.firstVisibleIndex; i < this.itemList.size(); i++) {
				UIItem uith = (UIItem) this.itemList.elementAt(i);

				if (lastDirty == true) {
					uith.setDirty(true);
				}
				if (uith.isDirty()) {
					lastDirty = true;
					// remove the space needed for border and scrollabar
					int reservedWidth = w - 2 * borderSize;
					// the screen need an additional pixel for border
					//					if (this instanceof UIScreen)
					reservedWidth -= 1;
					if (needScrollbar == true) reservedWidth -= UIConfig.scrollbarWidth;
					int uithHeight = uith.getHeight(g);

					// I must invalidate all the UIMenus different from myself
					// that are over uith
					if (this.screen != null && this.screen != this) {
						this.screen
								.invalidatePopups(this, g.getTranslateX(), g
										.getTranslateY(), g.getTranslateX()
										+ reservedWidth, g.getTranslateY()
										+ uithHeight);
					}

					uith.paint0(g, reservedWidth, uithHeight);

					// if I am not a Screen I must show arrows to show there
					// are submenus
					UIMenu selectedSubmenu = null;
					if (uith.getSelectedItem() != null) selectedSubmenu = uith
							.getSelectedItem().getSubmenu();
					if (this instanceof UIScreen == false
							&& selectedSubmenu != null) g.drawImage(menuImage,
							reservedWidth + 1 - UIMenu.menuImage.getWidth(),
							(uithHeight - UIMenu.menuImage.getHeight()) / 2,
							Graphics.TOP | Graphics.LEFT);
					changed = true;
				}
				int itemHeight = uith.getHeight(g);
				g.translate(0, itemHeight);
				lastHeight += itemHeight;
				this.lastVisibleIndex = i;

				// only the visible UIItem
				if (lastHeight > h-borderSize ) {
					/* last item is only "partially visible" */
					this.lastVisibleIndex--;
					lastHeight -= itemHeight;
					g.translate(0, -itemHeight);
					break;
				}

				if (lastVisibleIndex == this.itemList.size() - 1) {
					g.setColor(getBg_color() >= 0 ? getBg_color()
							: UIConfig.menu_color);
					int yGapHeight = this.height - lastHeight - 1;
					if (this.needScrollbar == false) {
						g.fillRect(0, 0, w - 1, yGapHeight - borderSize);
					} else {
						g.fillRect(0, 0, w - UIConfig.scrollbarWidth - 1,
								yGapHeight - borderSize);
					}
				}

				if (lastHeight == h) {
					/* last item is perfectly visible */
					break;
				}
			}

			// draw the scrollbar
			if (needScrollbar == true) {
				g.translate(w - UIConfig.scrollbarWidth - borderSize + initialX
						- g.getTranslateX(), initialY - g.getTranslateY() + 1);
				g.setColor(UIConfig.scrollbar_bg);
				g.fillRect(0, 0, UIConfig.scrollbarWidth, height - 1);
				g.setColor(UIConfig.scrollbar_fg);
				if (this.lastVisibleIndex != this.itemList.size() - 1) {
					g.translate(0, scrollbarPosition);
				} else {
					g.translate(0, h - scrollbarHeight);
				}
				g.fillRect(1, 0, UIConfig.scrollbarWidth - 2, scrollbarHeight);
			}

			g.translate(initialX - g.getTranslateX(), initialY
					- g.getTranslateY());
			// the border is painted later if I am a screen
			if (this instanceof UIScreen == false) {
//				g.setColor(UIConfig.menu_border);
//				g.drawRect(1, 1, width - 2, height - 2);
//				int col_lighter;
//				int col_darker;
//				if (UIConfig.menu_3d == true) {
//					col_lighter = getBg_color() >= 0 ? getBg_color()
//							: UIConfig.menu_color;
//					col_darker = UIUtils.colorize(col_lighter, -50);
//					col_lighter = UIUtils.colorize(col_lighter, 50);
//				} else {
//					col_lighter = UIConfig.menu_border;
//					col_darker = UIConfig.menu_border;
//				}
//				g.setColor(col_lighter);
//				g.drawLine(2, 2, width - 2, 2);
//				g.drawLine(2, 2, 2, height - 2);
//				g.setColor(col_darker);
//				g.drawLine(width - 2, 2, width - 2, height - 2);
//				g.drawLine(2, height - 2, width - 2, height - 2);
				
				
				int currentbbColor = UIConfig.menu_border;
				int innerUp =  0;
				int innerDown =  0;
				int border[][] = null;
				if (UIConfig.menu_3d == true) {
					innerUp =  UIUtils.colorize(currentbbColor, 50);
					innerDown =  UIUtils.colorize(currentbbColor, -50);
					border = new int[][] {
							new int[] { currentbbColor, currentbbColor, -1 },
							new int[] { currentbbColor, innerUp, -1 },
							new int[] { -1, -1, -1 }, };
				} else {
					innerUp = UIConfig.menu_border;
					innerDown = UIConfig.menu_border;
					int innerColor = UIUtils.medColor(UIConfig.menu_color, currentbbColor);
					int diagColor = UIUtils.medColor(UIConfig.bg_color, currentbbColor);
					border = new int[][] { new int[] { -1, diagColor, -1 },
							new int[] { diagColor, innerDown, -1 },
							new int[] { -1, -1, innerColor }, };
				}
				int colors[] = new int[] { currentbbColor, innerUp, innerDown,
						currentbbColor };
				drawBorder(g, new int[] { 1, 1, width-1, height-1}, colors, border);
				if (UIConfig.menu_3d == true) {
					g.setColor(innerDown);
					this.drawPixel(g, width-2, 2);
					this.drawPixel(g, 2, height-2);
					this.drawPixel(g, width-2, height-2);
				}
			}

			// moving down or up the screen can cause the lastVisible or
			// firstVisible
			// items not to be fully visible. Recheck !!! :D
			// be careful that if the UIItem is bigger than the screen
			// the lastvisibleindex can be smaller than firstvisibleindex
			if (this.selectedIndex > this.lastVisibleIndex
					&& this.selectedIndex > this.firstVisibleIndex) {
				paintNeeded = true;
				int gapHeight = 0;
				for (int i = this.lastVisibleIndex + 1; i <= selectedIndex; i++)
					gapHeight += ((UIItem) this.itemList.elementAt(i))
							.getHeight(g);
				gapHeight -= h;
				for (int i = this.firstVisibleIndex; i <= lastVisibleIndex; i++)
					gapHeight += ((UIItem) this.itemList.elementAt(i))
							.getHeight(g);

				do {
					UIItem ithElem = ((UIItem) this.itemList
							.elementAt(firstVisibleIndex));
					gapHeight -= ithElem.getHeight(g);
					this.firstVisibleIndex++;
				} while (gapHeight > 0);

				/* set items dirty */
				this.setDirty(true);
			}
			if (this.selectedIndex >= 0
					&& this.selectedIndex < this.firstVisibleIndex) {
				paintNeeded = true;
				firstVisibleIndex = selectedIndex;
				/* set items dirty */
				for (int i = firstVisibleIndex; i < this.itemList.size(); i++) {
					((UIItem) this.itemList.elementAt(i)).setDirty(true);
				}
				this.setDirty(true);
			}
		}
	}

	/**
	 * /** Handles the key events. Only these events are handled: DOWN, UP,
	 * BACK/CANCEL, SELECT
	 * 
	 * @param key
	 *            the key pressed
	 * @param ga
	 * @return The selected UIMenuItem
	 */
	protected UIItem keyPressed(int key, int ga) {
		//UIItem v = null;
		if (openSubMenu != null) {
			/*v = */openSubMenu.keyPressed(key, ga);
			return null;
		}
		if (key != UICanvas.MENU_RIGHT
				&& ga != Canvas.FIRE
				&& this.selectedIndex >= 0
				&& selectedIndex < this.itemList.size()
				&& ((UIItem) this.itemList.elementAt(selectedIndex))
						.keyPressed(key) == true) { return null; }
		UIItem selItem = null;
		switch (ga) {
			case Canvas.UP:
				if (selectedIndex >= 0 && selectedIndex < this.itemList.size()) ((UIItem) this.itemList
						.elementAt(selectedIndex)).setSelected(false);
				if (selectedIndex == 0) {
					selectedIndex = this.itemList.size() - 1;
				} else if (selectedIndex == -1) selectedIndex = 0;
				else {
					if (selectedIndex > 0
					//							&& ((UIItem) this.itemList
					//									.elementAt(selectedIndex - 1))
					//									.isFocusable()
					) {
						selectedIndex--;
					}
				}
				if (selectedIndex >= 0 && selectedIndex < this.itemList.size()) {
					selItem = ((UIItem) this.itemList.elementAt(selectedIndex));
					selItem.setSelected(true);
					if (selItem.isFocusable() == false) return keyPressed(key,
							ga);
				}
				this.dirty = true;
				this.askRepaint();
				break;

			case Canvas.DOWN:
				if (selectedIndex >= 0 && selectedIndex < this.itemList.size()) ((UIItem) this.itemList
						.elementAt(selectedIndex)).setSelected(false);
				if (selectedIndex == this.itemList.size() - 1) {
					firstVisibleIndex = 0;
					selectedIndex = 0;
					this.setDirty(true);
				} else if (selectedIndex == -1) selectedIndex = 0;
				else
					selectedIndex++;
				if (selectedIndex >= 0 && selectedIndex < this.itemList.size()) {
					selItem = ((UIItem) this.itemList.elementAt(selectedIndex));
					selItem.setSelected(true);
					if (selItem.isFocusable() == false) return keyPressed(key,
							ga);
				}
				this.dirty = true;
				this.askRepaint();
				break;

			default:
				break;
		}

		if ((key == UICanvas.MENU_RIGHT || ga == Canvas.FIRE)
				&& selectedIndex >= 0 && this.itemList.size() > 0) {
			UIItem selectedItem = ((UIItem) this.itemList
					.elementAt(selectedIndex)).getSelectedItem();
			if (selectedItem.keyPressed(key) == true) { return null; }
			if (selectedItem.getSubmenu() != null) {
				this.openSubMenu = selectedItem.getSubmenu();

				// set submenu positions and dimension
				if (openSubMenu.getWidth() <= 0) openSubMenu
						.setWidth(this.width - 10);
				openSubMenu.absoluteX = this.absoluteX;
				openSubMenu.absoluteY = this.absoluteY;
				int remainingXSpace = UICanvas.getInstance().getWidth()
						- this.width - this.absoluteX;
				int remainingYSpace = UICanvas.getInstance().getClipHeight()
						- this.height - this.absoluteY;
				if (openSubMenu.absoluteX > remainingXSpace) openSubMenu.absoluteX -= 5;
				else
					openSubMenu.absoluteX += 15;
				if (openSubMenu.absoluteY > remainingYSpace) openSubMenu.absoluteY -= 5;
				else
					openSubMenu.absoluteY += 15;
				this.screen.addPopup(selectedItem.getSubmenu());
			}
			return selectedItem;
		}
		return null;
	}

	public boolean isOpenedState() {
		return openedState;
	}

	public void setOpenedState(boolean openedState) {
		this.openedState = openedState;
		if (openedState == false) {
			if (selectedIndex >= 0 && this.itemList.size() > 0
					&& selectedIndex < this.itemList.size()) {
				UIItem uimi = (UIItem) this.itemList.elementAt(selectedIndex);
				uimi.setSelected(false);
			}
			this.selectedIndex = -1;
			if (this.screen != null) {
				Enumeration en = this.itemList.elements();
				while (en.hasMoreElements()) {
					this.screen.removePaintedItem((UIItem) en.nextElement());
				}
				this.screen.removePaintedItem(this);
			}
			this.setDirty(true);
		}
	}

	public int getAbsoluteY() {
		return absoluteY;
	}

	public void setAbsoluteY(int absoluteY) {
		this.absoluteY = absoluteY;
	}

	public int getAbsoluteX() {
		return absoluteX;
	}

	public void setAbsoluteX(int absoluteX) {
		this.absoluteX = absoluteX;
	}

	/**
	 * @param parentMenu
	 *            the parentMenu to set
	 */
	public void setParentMenu(UIMenu parentMenu) {
		this.parentMenu = parentMenu;
	}

	/**
	 * @return the parentMenu
	 */
	public UIMenu getParentMenu() {
		return parentMenu;
	}

	public UIMenu getSubmenu() {
		return openSubMenu;
	}

	public void setSubmenu(UIMenu submenu) {
		this.openSubMenu = submenu;
	}

	/**
	 * @return The height of the item
	 * @param g
	 *            the {@link Graphics} on which to paint into
	 */
	public int getHeight(Graphics g) {
		this.height = 1;
		for (Enumeration en = itemList.elements(); en.hasMoreElements();) {
			UIItem ithItem = (UIItem) en.nextElement();
			this.height += ithItem.getHeight(g);
		}
		//for borders
		height += (2 * borderSize);
		return height;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public void setScreen(UIScreen _us) {
		screen = _us;
		for (Enumeration en = itemList.elements(); en.hasMoreElements();) {
			UIItem ithItem = (UIItem) en.nextElement();
			ithItem.setScreen(screen);
		}
		if (openSubMenu != null) {
			openSubMenu.setScreen(screen);
		}
	}

	public void setDirty(boolean dirty) {
		this.dirty = dirty;
		for (Enumeration en = itemList.elements(); en.hasMoreElements();) {
			UIItem ithItem = (UIItem) en.nextElement();
			ithItem.setDirty(dirty);
		}
	}

	/**
	 * @return the needScrollbar
	 */
	boolean getNeedScrollbar() {
		return needScrollbar;
	}

	/**
	 * Set the {@link UIScreen} to be repainted.
	 * 
	 * @param dirty
	 *            The new value for dirty.
	 */
	public final boolean isDirty() {
		// this.height = -1;
		// this.width = -1;
		for (Enumeration en = itemList.elements(); en.hasMoreElements();) {
			UIItem ithItem = (UIItem) en.nextElement();
			if (ithItem.isDirty()) return true;
		}
		if (this.dirty) return true;
		return false;
	}

	/**
	 * @param selectedIndex
	 *            the selectedIndex to set
	 */
	public void setSelectedIndex(int selectedIndex) {
		if (selectedIndex == this.selectedIndex) return;
		if (this.selectedIndex >= 0
				&& this.selectedIndex < this.itemList.size()) {
			((UIItem) this.itemList.elementAt(this.selectedIndex))
					.setSelected(false);
		}
		if (selectedIndex >= 0 && selectedIndex < this.itemList.size()) {
			((UIItem) this.itemList.elementAt(selectedIndex)).setSelected(true);
		}
		this.selectedIndex = selectedIndex;
	}

	/**
	 * @return the selectedIndex
	 */
	public int getSelectedIndex() {
		return selectedIndex;
	}

	public UIItem getSelectedItem() {
		if (selectedIndex >= 0 && selectedIndex < this.itemList.size()) return (UIItem) this.itemList
				.elementAt(selectedIndex);
		return this;
	}

	/**
	 * @param itemList
	 *            the itemList to set
	 */
	public void setItemList(Vector itemList) {
		this.itemList = itemList;
	}

	/**
	 * @return the itemList
	 */
	public Vector getItemList() {
		return itemList;
	}

	public void setSelectedItem(UIItem item) {
		int index = this.indexOf(item);
		this.setSelectedIndex(index);
		if (this.getContainer() != null) {
			this.getContainer().setSelectedItem(this);
		}
	}

	public boolean contains(UIItem item) {
		if (this.itemList.contains(item)) return true;
		Enumeration en = this.itemList.elements();
		while (en.hasMoreElements()) {
			UIItem ithItem = (UIItem) en.nextElement();
			if (ithItem instanceof UIIContainer) {
				UIIContainer iic = (UIIContainer) ithItem;
				if (iic.contains(item)) return true;
			}
		}
		return false;
	}

	public void setAutoClose(boolean autoClose) {
		this.autoClose = autoClose;
	}

	public boolean isAutoClose() {
		return autoClose;
	}
}
