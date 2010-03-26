/* Copyright (c) 2008 Bluendo S.r.L.
 * See about.html for details about license.
 *
 * $Id: UIButton.java 1548 2009-05-28 09:24:01Z luca $
*/

/**
 * 
 */
package it.yup.ui;

import javax.microedition.lcdui.Font;
import javax.microedition.lcdui.Graphics;
import javax.microedition.lcdui.Image;

/**
 * @author luca
 * 
 */
public class UIButton extends UILabel {

	private boolean pressed = false;

	/*
	 * the horizontal padding from button borders to text 
	 */
	private int hPadding = 2;

	/**
	 * @param text
	 * @param screen
	 */
	public UIButton(String text) {
		super(text);
		this.focusable = true;
		this.wrappable = false;
		this.anchorPoint = Graphics.HCENTER;
	}

	public UIButton(Image img, String text) {
		super(img, text);
		this.focusable = true;
		this.wrappable = false;
		this.anchorPoint = Graphics.HCENTER;
	}

	protected void paint(Graphics g, int w, int h) {
		// what should happen in case the text of the button
		// is too long to fit ?

		Font usedFont = (this.font != null ? this.font : g.getFont());
		this.height = this.getHeight(g);
		this.width = usedFont.stringWidth(text) + 8 + 2 * hPadding;

		if (this.wrappable == true && this.getTextLines() == null) {
			computeTextLines(usedFont, w - 8 - 2 * hPadding);
			// if h is lower then 0 it means
			// use the minimum height
			if (h < 0) paint(g, w, this.getHeight(g));
			else
				paint(g, w, h);
			return;
		}

		g.setColor(getBg_color() >= 0 ? getBg_color() : UIConfig.bg_color);
		g.fillRect(0, 0, w, h);

		int originalX = g.getTranslateX();
		int originalY = g.getTranslateY();

		// change colors for the UILabel
		int oldBgColor = this.getBg_color();
		int oldSelectedColor = this.getSelectedColor();
		this.setBg_color(UIConfig.button_color);
		this.setSelectedColor(UIConfig.button_selected_color);
		g.translate(3, 3);
		g.setColor(selected ? this.getSelectedColor() : this.getBg_color());
		g.fillRect(0, 0, w - 5, h - 5);

		g.translate(1 + hPadding, 1);
		super.paint(g, w - 8 - +2 * hPadding, h - 8);

		this.setBg_color(oldBgColor);
		this.setSelectedColor(oldSelectedColor);
		this.height = this.getHeight(g);
		g.translate(originalX - g.getTranslateX(), originalY
				- g.getTranslateY());
		g.translate(0, (h - this.height) / 2);

		int x0 = 1, x1 = w - 2, y0 = 1, y1 = this.height - 2;

		int oldColor = g.getColor();
		int currentbbColor = selected ? UIConfig.bbs_color : UIConfig.bb_color;
		int innerUp = selected ? UIConfig.blbs_color : UIConfig.blb_color;
		int innerDown = selected ? UIConfig.bdbs_color : UIConfig.bdb_color;
		int colors[] = new int[] { currentbbColor, innerUp, innerDown,
				currentbbColor };
		int border[][] = null;
		if (UIConfig.menu_3d == true) {
			border = new int[][] {
					new int[] { currentbbColor, currentbbColor, -1 },
					new int[] { currentbbColor, innerUp, -1 },
					new int[] { -1, -1, -1 }, };
		} else {
			int diagColor = UIUtils.medColor(getBg_color() >= 0 ? getBg_color()
					: UIConfig.bg_color, currentbbColor);
			int innerColor = UIUtils.medColor(
					selected ? UIConfig.button_selected_color
							: UIConfig.button_color, currentbbColor);
			border = new int[][] { new int[] { -1, diagColor, -1 },
					new int[] { diagColor, innerDown, -1 },
					new int[] { -1, -1, innerColor }, };
		}
		drawBorder(g, new int[] { x0, y0, x1, y1 }, colors, border);
		if (UIConfig.menu_3d == true) {
			g.setColor(innerDown);
			this.drawPixel(g, x1 - 1, y0 + 1);
			this.drawPixel(g, x0 + 1, y1 - 1);
			this.drawPixel(g, x1 - 1, y1 - 1);
		}
		g.setColor(oldColor);

		g.translate(originalX - g.getTranslateX(), originalY
				- g.getTranslateY());

	}

	//	protected void drawBorder(Graphics g, int x0, int y0, int x1, int y1) {
	//		int currentbbColor = selected ? UIConfig.bbs_color : UIConfig.bb_color;
	//
	//		g.setColor(currentbbColor);
	//		g.drawRect(x0, y0, x1 - x0, y1 - y0);
	//		g.setColor(selected ? UIConfig.blbs_color : UIConfig.blb_color);
	//		g.drawLine(x0 + 1, y0 + 1, x1 - 1, y0 + 1);
	//		g.drawLine(x0 + 1, y0 + 1, x0 + 1, y1 - 1);
	//		g.setColor(selected ? UIConfig.bdbs_color : UIConfig.bdb_color);
	//		g.drawLine(x1 - 1, y1 - 1, x1 - 1, y0 + 1);
	//		g.drawLine(x1 - 1, y1 - 1, x0 + 1, y1 - 1);
	//
	//		if (UIConfig.menu_3d == false) {
	//			//			g.setStrokeStyle(Graphics.SOLID);
	//			//			g.setColor(currentbbColor );
	//			//			g.drawRoundRect(x0, y0, x1-x0, y1-y0, 2, 2);
	//			//			//g.drawRect(x0, y0, x1-x0, y1-y0);
	//			//			g.setColor(selected ? UIConfig.blbs_color: UIConfig.blb_color );
	//			//			g.drawRoundRect(x0+1, y0+1, x1-x0-2, y1-y0-2, 2, 2);
	//
	//			g.drawRect(x0 + 1, y0 + 1, x1 - x0 - 2, y1 - y0 - 2);
	//
	//			g.setColor(this.getBg_color());
	//			g.drawLine(x0, y0, x0, y0);
	//			g.drawLine(x1, y0, x1, y0);
	//			g.drawLine(x0, y1, x0, y1);
	//			g.drawLine(x1, y1, x1, y1);
	//			g.setColor(UIUtils.medColor(getBg_color() >= 0 ? getBg_color()
	//					: UIConfig.bg_color, currentbbColor));
	//			g.drawLine(x0 + 1, y0, x0, y0 + 1);
	//			g.drawLine(x0 + 1, y1, x0, y1 - 1);
	//			g.drawLine(x1 - 1, y0, x1, y0 + 1);
	//			g.drawLine(x1, y1 - 1, x1 - 1, y1);
	//			g.setColor(UIUtils.medColor(
	//					selected ? UIConfig.button_selected_color
	//							: UIConfig.button_color, currentbbColor));
	//			g.drawLine(x0 + 2, y0 + 2, x0 + 2, y0 + 2);
	//			g.drawLine(x1 - 2, y1 - 2, x1 - 2, y1 - 2);
	//			g.drawLine(x0 + 2, y1 - 2, x0 + 2, y1 - 2);
	//			g.drawLine(x1 - 2, y0 + 2, x1 - 2, y0 + 2);
	//
	//		}
	//	}

	/**
	 * @return the pressed
	 */
	public boolean isPressed() {
		return pressed;
	}

	/**
	 * @param pressed
	 *            the pressed to set
	 */
	public void setPressed(boolean pressed) {
		this.pressed = pressed;
	}

	/**
	 * {@inheritDoc}
	 */
	public int getHeight(Graphics g) {
		return super.getHeight(g) + 8;
	}

	public void setHPadding(int hPadding) {
		this.hPadding = hPadding;
	}

	public int getHPadding() {
		return hPadding;
	}
}
