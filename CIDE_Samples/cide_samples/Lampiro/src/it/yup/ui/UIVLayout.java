/* Copyright (c) 2008 Bluendo S.r.L.
 * See about.html for details about license.
 *
 * $Id: UIVLayout.java 1471 2009-05-13 21:35:41Z luca $
*/

/**
 * 
 */
package it.yup.ui;

import javax.microedition.lcdui.Canvas;
import javax.microedition.lcdui.Graphics;

/**
 * @author luca
 * 
 */
public class UIVLayout extends UILayout {

	/**
	 * @param rowNumber
	 *            The row number of the layOut.
	 * @param height
	 *            The height of the item
	 */
	public UIVLayout(int rowNumber, int height) {
		super(rowNumber);
		this.dirKey1 = Canvas.UP;
		this.dirKey2 = Canvas.DOWN;
		this.height = height;
	}

	/**
	 * Inserts and {@link UIItem} at the index-th position in the layout.
	 * 
	 * @param item
	 *            The {@link UIItem} to add.
	 * @param index
	 *            The index in the column array.
	 * @param type
	 *            The type of column (can be UIHLayout.pix or UIHLayout.perc)
	 */
	public void insert(UIItem item, int index, int height, int type) {
		this.layoutItems[index] = item;
		item.setLayoutHeight(height);
		item.setType(type);
		item.setScreen(screen);
		item.setContainer(this);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see it.yup.ui.UIItem#paint(javax.microedition.lcdui.Graphics, int, int)
	 */
	protected void paint(Graphics g, int w, int h) {
		int originalGx = g.getTranslateX();
		int originalGy = g.getTranslateY();
		if (this.dirty == true) {
			g.setColor(getBg_color() >= 0 ? getBg_color() : UIConfig.bg_color);
			g.fillRect(0, 0, w, h);
		}

		int pixelSum = 0;
		int percentageSum = 0;
		for (int i = 0; i < layoutItems.length; i++) {
			if (layoutItems[i].getType() == UILayout.CONSTRAINT_PIXELS) pixelSum += layoutItems[i]
					.getLayoutHeight();
			if (layoutItems[i].getType() == UILayout.CONSTRAINT_PERCENTUAL) percentageSum += layoutItems[i]
					.getLayoutHeight();
		}
		int remainingPixels = 0;
		if (percentageSum > 0) {
			remainingPixels = ((this.getHeight(g) - pixelSum) * 100)
					/ percentageSum;
		}
		int pixelIndex = 0;
		int i = 0;
		for (i = 0; i < layoutItems.length - 1; i++) {
			if (layoutItems[i].getType() == UILayout.CONSTRAINT_PIXELS) {
				if (layoutItems[i].isDirty()) layoutItems[i].paint0(g, w,
						layoutItems[i].getLayoutHeight());
				pixelIndex += layoutItems[i].getLayoutHeight();
				g.translate(0, layoutItems[i].getLayoutHeight());
			}
			if (layoutItems[i].getType() == UILayout.CONSTRAINT_PERCENTUAL) {
				int ithLayoutHeight = (layoutItems[i].getLayoutHeight() * remainingPixels) / 100;
				if (layoutItems[i].isDirty()) layoutItems[i].paint0(g, w,
						ithLayoutHeight);
				pixelIndex += ithLayoutHeight;
				g.translate(0, ithLayoutHeight);
			}
		}
		// the last row is "painted alone" to fill all the remaining
		// pixels
		if (layoutItems[i].getType() == UILayout.CONSTRAINT_PIXELS) {
			if (layoutItems[i].isDirty()) layoutItems[i].paint0(g, w, this
					.getHeight(g)
					- pixelIndex);
			pixelIndex += layoutItems[i].getLayoutHeight();
			g.translate(0, layoutItems[i].getLayoutHeight());
		}
		if (layoutItems[i].getType() == UILayout.CONSTRAINT_PERCENTUAL) {
			int ithLayoutHeight = this.getHeight(g) - pixelIndex;
			if (layoutItems[i].isDirty()) layoutItems[i].paint0(g, w,
					ithLayoutHeight);
			pixelIndex += ithLayoutHeight;
			g.translate(0, ithLayoutHeight);
		}

		g.translate(originalGx - g.getTranslateX(), +originalGy
				- g.getTranslateY());
		if (this.selected && isGroup()) {
			this.drawSegmentedBorder(g, w, h);
		}
	}

	public int getHeight(Graphics g) {
		if (height == -1) {
			height = g.getClipHeight() + g.getClipY();
		}
		return this.height;
	}

	public void setHeight(int layoutHeight) {
		this.height = layoutHeight;
	}
}
