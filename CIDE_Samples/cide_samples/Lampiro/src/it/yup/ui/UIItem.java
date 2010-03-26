/* Copyright (c) 2008 Bluendo S.r.L.
 * See about.html for details about license.
 *
 * $Id: UIItem.java 1577 2009-06-15 14:38:27Z luca $
*/

/**
 * 
 */
package it.yup.ui;

//#mdebug
//@
//@import it.yup.util.Logger;
//@
//#enddebug

import javax.microedition.lcdui.Graphics;

/**
 * Un generico Item della lista contenuta in un {@link UIScreen}.
 * 
 * XXX: forse sarebbe meglio trasformarlo in una interfaccia e non farlo classe
 * astratta?
 */
public abstract class UIItem {

	/*
	 * Constants used to draw the borders of the UIItem
	 */
	public static final int OUTER_UP = 0;
	public static final int INNER_UP = 1;
	public static final int INNER_DOWN = 2;
	public static final int OUTER_DOWN = 3;

	private int gradientColor = -1;
	private int gradientSelectedColor = -1;

	/**
	 * The type of column or row in a {@link UILayout} (can be
	 * UIHLayout.CONSTRAINT_PIXELS or UIHLayout.CONSTRAINT_PERCENTUAL).
	 */
	private int type;

	/**
	 * The width to keep within the layout.
	 * 
	 */
	private int layoutWidth;

	/**
	 * The height to keep within the layout.
	 * 
	 */
	private int layoutHeight;

	/** flag che indica se l'item e' stato selezionato */
	protected boolean selected;

	/** lo schermo che contiene questo item */
	protected UIScreen screen;

	/**
	 * Identify if the item can obtain focus and hence be selected.
	 */
	protected boolean focusable = false;

	/**
	 * The height of the {@link UIItem}
	 */
	protected int height = -1;

	/**
	 * The width of the {@link UIItem}
	 */
	protected int width = -1;

	/** The submenu associated with this item */
	private UIMenu subMenu;

	/*
	 * The container of this object
	 */
	private UIIContainer container = null;

	/**
	 * Each UIItem will use a bg_color to draw its background; if bg_color is a
	 * valid color then bg_color will be used to paint otherwise
	 * UIConfig.bg_color will be used.
	 */
	int bg_color = -1;
	int fg_color = -1;
	int selectedColor = -1;
	
	
	/*
	 * A status used to carry data between object accesses (like MFC visual objects status)
	 */
	private Object status = null;
	
	/**
	 * @return the status
	 */
	public Object getStatus() {
		return status;
	}

	/**
	 * @param status the status to set
	 */
	public void setStatus(Object status) {
		this.status = status;
	}

	/**
	 * @return the type
	 */
	public int getType() {
		return type;
	}

	/**
	 * @return the layoutWidth
	 */
	public int getLayoutWidth() {
		return layoutWidth;
	}

	/**
	 * @return the layoutHeight
	 */
	public int getLayoutHeight() {
		return layoutHeight;
	}

	/**
	 * @return the submenu
	 */
	public UIMenu getSubmenu() {
		return subMenu;
	}

	/**
	 * @return Set the submenu
	 */
	public void setSubmenu(UIMenu subMenu) {
		this.subMenu = subMenu;

	}

	/**
	 * Constructor.
	 */
	public UIItem() {
	}

	/**
	 * Asks the screen that contains this {@link UIItem} to repaint itself and
	 * hence this item.
	 * 
	 * @return
	 */
	protected boolean askRepaint() {
		if (this.screen != null) {
			this.screen.askRepaint();
			return true;
		}
		return false;
	}

	/**
	 * This value keeps information about the fact that the item should be
	 * repainted or not.
	 */
	protected boolean dirty = true;

	/*
	 * The coordinates at which the Item painted last time:
	 * coors[0]: X
	 * coors[1]: Y
	 * coors[2]: Width
	 * coors[3]: Height
	 */
	int[] coors = new int[4];

	/**
	 * imposta lo schermo che contiene questo item.
	 */
	public void setScreen(UIScreen _us) {
		screen = _us;
		if (subMenu != null) {
			subMenu.setScreen(screen);
		}
	}

	public UIScreen getScreen() {
		return screen;
	}

	/**
	 * @return la dimensione dell'item o -1 se l'item deve utilizzare tutta la
	 *         larghezza disponibile dello schermo
	 */
	public int getWidth() {
		return this.width;
	}

	/**
	 * Called when a key is pressed over this {@link UIItem}
	 * 
	 * @param ka
	 *            The key that is pressed.
	 * @return true if the {@link UIItem} needs to have the selection after the
	 *         keyPressed (for example a radio button or a list would need the
	 *         selection multiple times); false otherwise
	 */
	public boolean keyPressed(int key) {
		return false;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	/**
	 * @return The height of the item
	 * @param g
	 *            the {@link Graphics} on which to paint into
	 */
	public int getHeight(Graphics g) {
		return this.height;
	}

	/**
	 * imposta l'Item come selezionato
	 * 
	 * @param _selected
	 *            {@code true} se l'item � stato selezionato, {@code false} se
	 *            non � stato selezionato.
	 */
	public void setSelected(boolean _selected) {
		this.dirty = true;
		if (_selected && (this.focusable || this.subMenu != null)) selected = true;
		else
			selected = false;

	}

	/**
	 * @return {@code true} if the item is selected, {@code false} otherwhise.
	 */
	public boolean isSelected() {
		return selected;
	}

	/**
	 * Impone di disegnare l'elemento usando il contesto grafico e mantenendo le
	 * dimensioni in quelle fornite.
	 * 
	 * XXX: nota, tramite le chiamate {@link Graphics#translate(int, int)} �
	 * possibile fare in modo che l'item si disegni a partire dalle coordinate
	 * (0, 0). Sarebbe carino fare in modo che (visto che assumiamo 1 item = 1
	 * riga) l'item o ritornasse o spostasse lo (0, 0) del {@link Graphics} per
	 * indicare la propria dimensione. E' vero che ci sono delle interessanti
	 * chiamate {@link #getItemHeight()} e {@link #getItemWidth()} che
	 * dovrebbero essere vincolanti, ma non sarebbe male se l'item invece si
	 * "accomodasse su una riga intera" fornendo indietro poi le dimensioni che
	 * ha usato.
	 * 
	 * @param g
	 *            il contesto grafico da usare per disegnare l'item
	 * @param w
	 *            la larghezza massima del box in cui deve stare l'oggetto
	 * @param h
	 *            l'altezza massima del box in cui deve stare l'oggetto
	 */
	protected abstract void paint(Graphics g, int w, int h);

	/**
	 * This is the paint method invoked by the Screen, it will dispatch to the
	 * {@link #paint(Graphics, int, int)} method overridden by the item.
	 */
	public final void paint0(Graphics g, int w, int h) {
		// first save the clip and coordinate status
		int originalX = g.getTranslateX();
		int originalY = g.getTranslateY();
		int originalClipX = g.getClipX();
		int originalClipY = g.getClipY();
		int originalClipWidth = g.getClipWidth();
		int originalClipHeight = g.getClipHeight();

		this.setWidth(w);
		// UIMenu computes its clip by itself
		if (this instanceof UIMenu == false) g.clipRect(0, 0, w, h);

		// notify the screen my coordinates 
		coors[0] = originalX;
		coors[1] = originalY;
		coors[2] = g.getClipWidth();
		// items can be painted in space lower than h or their clip
		coors[3] = g.getClipHeight();
		if (this.screen != null) {
			this.screen.addPaintedItem(this);
		}

		// then draw it and catch any eventual exception
		try {
			paint(g, w, h);
		} catch (Exception e) {
			// #mdebug
//@												System.out.println(e);
//@												e.printStackTrace();
//@												System.out.println("In paint0: " + e.getMessage());
//@												Logger.log("In paint0:" + e.getMessage());
			// #enddebug
		}
		this.dirty = false;

		// and reset the original values
		g.translate(originalX - g.getTranslateX(), originalY
				- g.getTranslateY());
		g.setClip(originalClipX, originalClipY, originalClipWidth,
				originalClipHeight);

		if (this instanceof UIMenu == false && this.subMenu != null
				&& this.selected == true) {
			this.subMenu.setAbsoluteX(g.getTranslateX() + 10);
			this.subMenu.setAbsoluteY(g.getTranslateY() + 2);
			this.subMenu.setWidth(w - 20);
		}
	}

	/**
	 * If true then the UIItem should be repainted.
	 * 
	 * @return
	 */
	public boolean isDirty() {
		return dirty;
	}

	/*
	 * draw the borders of UIItem
	 * 
	 * @param g
	 *            the {@link Graphics} on which to paint into
	 *            
	 * @param rect
	 *            the coordinate of the rectangle to paint in this order: X0,Y0,X1,Y1,
	 *            
	 * @param colors 
	 * 			  the colors used to paint the borders in this order (and -1 is the transparent color): OUTER_UP,INNER_UP, INNER_DOWN,OUTER_DOWN, 
	 * 
	 * @param colors 
	 * 			  the color matrix for the upper left corner (will be mirrored to be painted in all other corners) and -1 is the transparent color
	 * 
	 */
	protected void drawBorder(Graphics g, int[] rect, int[] colors,
			int[][] border) {
		int oldColor = g.getColor();
		int x0 = rect[0], y0 = rect[1], x1 = rect[2], y1 = rect[3];

		// This block is a condensed way of doing the next commented
		// block
		int[] color_mappings = new int[] { y0, x0, y0 + 1, x0 + 1, y1 - 1,
				x1 - 1, y1, x1 };
		for (int i = OUTER_UP; i <= OUTER_DOWN; i++) {
			int ithColor = colors[i];
			if (ithColor >= 0) {
				g.setColor(ithColor);
				g.drawLine(x0 + 2, color_mappings[2 * i], x1 - 2,
						color_mappings[2 * i]);
				g.drawLine(color_mappings[2 * i + 1], y0 + 2,
						color_mappings[2 * i + 1], y1 - 2);
			}
		}

		//		if (colors[OUTER_UP]>=0){
		//			g.setColor(colors[OUTER_UP]);
		//			g.drawLine(x0+2,y0,x1-2,y0);
		//			g.drawLine(x0,y0+2,x0,y1-2);
		//		}
		//		if (colors[INNER_UP]>=0){
		//			g.setColor(colors[INNER_UP]);
		//			g.drawLine(x0+2,y0+1,x1-2,y0+1);
		//			g.drawLine(x0+1,y0+2,x0+1,y1-2);
		//		}
		//		if (colors[INNER_DOWN]>=0){
		//			g.setColor(colors[INNER_DOWN]);
		//			g.drawLine(x0+2,y1-1,x1-2,y1-1);
		//			g.drawLine(x1-1,y0+2,x1-1,y1-2);
		//		}
		//		if (colors[OUTER_DOWN]>=0){
		//			g.setColor(colors[OUTER_DOWN]);
		//			g.drawLine(x0+2,y1,x1-2,y1);
		//			g.drawLine(x1,y0+2,x1,y1-2);
		//		}

		for (int i = 0; i <= 2; i++) {
			for (int j = 0; j <= 2; j++) {
				int colorij = border[i][j];
				if (colorij >= 0) {
					g.setColor(colorij);
					drawPixel(g, i + x0, j + y0);
					drawPixel(g, x1 - i, j + y0);
					drawPixel(g, x0 + i, y1 - j);
					drawPixel(g, x1 - i, y1 - j);
				}
			}
		}
		g.setColor(oldColor);
	}

	protected void drawPixel(Graphics g, int x, int y) {
		g.drawLine(x, y, x, y);
	}

	protected void drawInput(Graphics g, int x0, int y0, int x1, int y1) {
		int oldColor = g.getColor();
		g.setColor(UIConfig.input_color);
		g.fillRect(x0 + 1, y0 + 1, x1 - x0 - 1, y1 - y0 - 1);

		int selColor = isSelected() ? UIConfig.tbs_color : -1;
		int colors[] = new int[] { UIConfig.tbb_color, selColor, selColor,
				UIConfig.tbb_color };
		int border[][] = new int[][] {
				new int[] { -1, UIConfig.tbb_color, -1 },
				new int[] { UIConfig.tbb_color, selColor, -1 },
				new int[] { -1, -1, -1 }, };
		drawBorder(g, new int[] { x0, y0, x1, y1 }, colors, border);
		g.setColor(oldColor);
	}

	//	protected void drawBorder(Graphics g, int x0, int y0, int x1, int y1) {
	//		g.setColor(UIConfig.tbb_color);
	//		g.drawLine(x0 + 1, y0, x1 - 1, y0);
	//		g.drawLine(x1, y0 + 1, x1, y1 - 1);
	//		g.drawLine(x1 - 1, y1, x0 + 1, y1);
	//		g.drawLine(x0, y1 - 1, x0, y0 + 1);
	//		g.setColor(UIConfig.input_color);
	//		g.fillRect(x0 + 1, y0 + 1, x1 - x0 - 2, y1 - y0 - 2);
	//		if (this.isSelected()) {
	//			g.setColor(UIConfig.tbs_color);
	//			g.drawRect(x0 + 1, y0 + 1, x1 - x0 - 2, y1 - y0 - 2);
	//		}
	//	}

	/**
	 * Set the {@link UIScreen} to be repainted.
	 * 
	 * @param dirty
	 *            The new value for dirty.
	 */
	public void setDirty(boolean dirty) {
		this.dirty = dirty;
		// this.height = -1;
		// this.width = -1;
	}

	/**
	 * @return the focusable
	 */
	public boolean isFocusable() {
		// an UIItem can be focusable or have a subMenu
		// in both cases it must be "focusable" from outside
		return focusable || this.subMenu != null;
	}

	/**
	 * @param focusable
	 *            the focusable to set
	 */
	public void setFocusable(boolean focusable) {
		this.focusable = focusable;
	}

	/**
	 * @param layoutWidth
	 *            the layoutWidth to set
	 */
	public void setLayoutWidth(int layoutWidth) {
		this.layoutWidth = layoutWidth;
	}

	/**
	 * @param type
	 *            the type to set
	 */
	public void setType(int type) {
		this.type = type;
	}

	/**
	 * @param layoutHeight
	 *            the layoutHeight to set
	 */
	public void setLayoutHeight(int layoutHeight) {
		this.layoutHeight = layoutHeight;
	}

	/**
	 * Return the selected UIItem within the UIItem itselft; usually it is the
	 * UIItem itself but in the subclasses (like UIVLayout) it could one of the
	 * contained object.
	 * 
	 * @return
	 */
	public UIItem getSelectedItem() {
		return this;
	}

	/**
	 * @param bg_color
	 *            the bg_color to set
	 */
	public void setBg_color(int bg_color) {
		this.bg_color = bg_color;
	}

	/**
	 * @return the bg_color
	 */
	public int getBg_color() {
		return bg_color;
	}

	/**
	 * @param bg_color
	 *            the bg_color to set
	 */
	public void setFg_color(int fg_color) {
		this.fg_color = fg_color;
	}

	/**
	 * @return the bg_color
	 */
	public int getFg_color() {
		return fg_color;
	}

	public void setContainer(UIIContainer container) {
		this.container = container;
	}

	public UIIContainer getContainer() {
		return container;
	}

	public void setSelectedColor(int selectedColor) {
		this.selectedColor = selectedColor;
	}

	public int getSelectedColor() {
		return selectedColor >= 0 ? selectedColor : UIConfig.selected_color;
	}

	public void setGradientColor(int gradientColor) {
		this.gradientColor = gradientColor;
	}

	public int getGradientColor() {
		return gradientColor;
	}

	public void setGradientSelectedColor(int gradientSelectedColor) {
		this.gradientSelectedColor = gradientSelectedColor;
	}

	public int getGradientSelectedColor() {
		return gradientSelectedColor;
	}

}
