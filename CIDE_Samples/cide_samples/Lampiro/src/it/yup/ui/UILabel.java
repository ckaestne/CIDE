/* Copyright (c) 2008 Bluendo S.r.L.
 * See about.html for details about license.
 *
 * $Id: UILabel.java 1578 2009-06-16 11:07:59Z luca $
*/

/**
 * 
 */
package it.yup.ui;

import it.yup.util.Utils;

import java.util.Enumeration;
import java.util.Vector;

import javax.microedition.lcdui.Font;
import javax.microedition.lcdui.Graphics;
import javax.microedition.lcdui.Image;

/**
 * @author luca
 * 
 */
public class UILabel extends UIItem {

	String text;
	Image img;
	boolean flip = false;

	private Vector textLines = null;

	/**
	 * The font used to paint the Label
	 */
	Font font = null;

	/**
	 * The text of the label is "wrapped" it text is too long to fit a single
	 * line only if wrappable is set to true. Subclasses are likely to override
	 * this behaviour.
	 */
	boolean wrappable = false;

	public UILabel(String text) {
		this(null, text);
	}

	public UILabel(Image img) {
		this(img, "");
	}

	/**
	 * @param screen
	 */
	public UILabel(Image img, String text) {
		this.img = img;
		this.text = text;
		// if an img is present it is a nonsense to have the
		// label wrapped! or not ?
		if (this.img != null) this.wrappable = false;
		// TODO Auto-generated constructor stub
	}

	/*
	 * Used to indicated the anchor point for the label with the same syntax as
	 * Graphics (TOP, VCENTER,BOTTOM, LEFT, HCENTER,RIGHT ). It is possible to
	 * use them in or like: anchorPoint = Graphics.TOP | Graphics.LEFT; the
	 * default value is Graphics.VCENTER | Graphics.LEFT.
	 */
	int anchorPoint = Graphics.TOP | Graphics.LEFT;

	private int imgAnchorPoint = -1;

	/**
	 * used to get the different components of the acnhorPoint,
	 */
	private int[] divideAP() {
		int[] result = new int[2];
		result[0] = (this.anchorPoint & Graphics.TOP) > 0 ? Graphics.TOP
				: ((this.anchorPoint & Graphics.VCENTER) > 0 ? Graphics.VCENTER
						: ((this.anchorPoint & Graphics.BOTTOM) > 0 ? Graphics.BOTTOM
								: 0));
		result[1] = (this.anchorPoint & Graphics.LEFT) > 0 ? Graphics.LEFT
				: ((this.anchorPoint & Graphics.HCENTER) > 0 ? Graphics.HCENTER
						: ((this.anchorPoint & Graphics.RIGHT) > 0 ? Graphics.RIGHT
								: 0));
		return result;

	}

	int getTextWidth(String textLine, Font font) {
		return font.stringWidth(textLine);
	}

	private void paintLine(Graphics g, int w, int h, Image imgLine,
			String textLine) {
		int lineHeight = 0;
		int lineWidth = 0;
		int textHeight = 0, textWidth = 0, imgHeight = 0;
		Font usedFont = (this.font != null ? this.font : g.getFont());
		if (imgLine != null) {
			lineHeight = imgLine.getHeight() + 2;
			imgHeight = imgLine.getHeight();
		}
		// if (imgHeight < g.getFont().getHeight() + 2) {
		// imgHeight = g.getFont().getHeight() + 2;
		// }
		if (h > lineHeight) {
			lineHeight = h;
		}
		textHeight = usedFont.getHeight();
		textWidth = getTextWidth(textLine, usedFont);
		// text and Image must have an offset from the TOP
		// in order to be aligned
		int imgVerticalOffset = (lineHeight - imgHeight - 2) / 2;
		int textVerticalOffset = (lineHeight - textHeight - 2) / 2;

		// the horizontalSpace left "free"; must be set depending on the
		// orientation
		int horizontalSpace = 0;
		lineWidth = 0;
		if (imgLine != null) lineWidth += imgLine.getWidth() + 3
				+ getTextWidth(textLine, usedFont);
		else
			lineWidth = getTextWidth(textLine, usedFont) + 2;

		if (w > lineWidth) {
			horizontalSpace = w - lineWidth;
			this.width = w;
		}
		int horizontalAnchor = this.divideAP()[1];
		switch (horizontalAnchor) {
			case Graphics.LEFT:
				horizontalSpace = 0;
				break;
			case Graphics.HCENTER:
				horizontalSpace /= 2;
				break;
			case Graphics.RIGHT:
				break;

			default:
				break;
		}

		// first erase background
		g.setColor(getBg_color() >= 0 ? getBg_color() : UIConfig.bg_color);
		if (this.getGradientColor() < 0) g.fillRect(0, 0, w, lineHeight);
		else {
			g.fillRect(0, 0, w, lineHeight / 2);
			g.setColor(this.getGradientColor());
			g.fillRect(0, lineHeight / 2, w, lineHeight - (lineHeight / 2));
		}

		// than paint in case it is needed
		if (selected) {
			int offset = 0;
			if (imgLine != null) offset = imgVerticalOffset;
			if (textLine != null
					&& (textVerticalOffset < offset || imgLine == null)) offset = textVerticalOffset;
			g.setColor(this.getSelectedColor());
			int selHeight = java.lang.Math.max(imgHeight, textHeight) + 2;
			if (this.getGradientSelectedColor() < 0) g.fillRect(0, offset, w,
					selHeight);
			else {
				g.fillRect(0, offset, w, selHeight / 2);
				g.setColor(this.getGradientSelectedColor());
				g.fillRect(0, selHeight / 2, w, selHeight - (selHeight / 2));
			}
		}

		g.setColor(selected ? UIConfig.header_fg : UIConfig.fg_color);
		if (this.fg_color >= 0) g.setColor(this.fg_color);
		if (flip == false) {
			if (this.img != null) {
				// if the imgAnchorPoint is set it means image must be overridden
				int imgHOffset = 1;
				if (this.imgAnchorPoint != Graphics.LEFT) imgHOffset += horizontalSpace;
				g.drawImage(imgLine, imgHOffset, 1 + imgVerticalOffset,
						Graphics.LEFT | Graphics.TOP);
				// g.drawString(textLine,
				// imgLine.getWidth() + 2 + horizontalSpace,
				// 1 + textVerticalOffset, Graphics.LEFT | Graphics.TOP);
				paintTextLine(g, textLine, imgLine.getWidth() + 2
						+ horizontalSpace, 1 + textVerticalOffset);
			} else {
				// g.drawString(textLine, 1 + horizontalSpace,
				// 1 + textVerticalOffset, Graphics.LEFT | Graphics.TOP);
				paintTextLine(g, textLine, 1 + horizontalSpace,
						1 + textVerticalOffset);
			}
		} else {
			// g.drawString(textLine, 1 + horizontalSpace, 1 +
			// textVerticalOffset,
			// Graphics.LEFT | Graphics.TOP);
			paintTextLine(g, textLine, 1 + horizontalSpace,
					1 + textVerticalOffset);
			if (this.img != null) {
				g.drawImage(imgLine, textWidth + 2 + horizontalSpace,
						1 + imgVerticalOffset, Graphics.LEFT | Graphics.TOP);
			}
		}

	}

	void paintTextLine(Graphics g, String textLine, int horizontalSpace,
			int verticalSpace) {

		g.drawString(textLine, horizontalSpace, verticalSpace, Graphics.LEFT
				| Graphics.TOP);
	}

	/*
	 * String[] split(String original, String separator) { Vector nodes = new
	 * Vector(); // Parse nodes into vector int index =
	 * original.indexOf(separator); while (index >= 0) {
	 * nodes.addElement(original.substring(0, index)); original =
	 * original.substring(index + separator.length()); index =
	 * original.indexOf(separator); } // Get the last node
	 * nodes.addElement(original); // Create splitted string array String[]
	 * result = new String[nodes.size()]; if (nodes.size() > 0) { for (int loop =
	 * 0; loop < nodes.size(); loop++) result[loop] = (String)
	 * nodes.elementAt(loop); } return result; }
	 * 
	 */

	/*
	 * (non-Javadoc)
	 * 
	 * @see it.yup.ui.UIItem#paint(javax.microedition.lcdui.Graphics, int, int)
	 */
	protected void paint(Graphics g, int w, int h) {
		Font oldFont = g.getFont();
		if (this.font != null) g.setFont(this.font);

		this.height = 0;
		this.width = 0;
		if (this.img != null) {
			if (this.text != null && this.text.length() >= 2) {
				this.width += img.getWidth() + 3
						+ g.getFont().stringWidth(text);
			} else
				this.width += img.getWidth() + 2;
		} else
			this.width = g.getFont().stringWidth(text) + 2;

		// if the label is not wrappable we remove the extra characters
		// we must check that image is not wider than w
		if (this.wrappable == false) {
			if (this.width > w && (this.img == null || this.img.getWidth() < w)) {
				//				String newText = new String(this.text);
				//				do {
				//					this.width = 0;
				//					int index = newText.length() - 2;
				//					if (index < 0) index = 0;
				//					newText = newText.substring(0, Math.max(newText.length() - 2,0));
				//					if (img != null) this.width += img.getWidth() + 3
				//							+ g.getFont().stringWidth(newText + "...");
				//					else
				//						this.width = g.getFont().stringWidth(newText + "...") + 2;
				//				} while (this.width > w && newText.length() > 0);

				String newText = new String("");
				int index = 0;
				this.width = 0;
				while (this.width < w) {
					newText = newText + this.text.charAt(index);
					this.width = g.getFont().stringWidth(newText + "...") + 2;
					if (img != null) this.width += (img.getWidth() + 1);
					index++;
				}
				if (newText.length() > 1) newText = newText.substring(0,
						newText.length() - 2);
				newText = newText + "...";
				paintLine(g, w, h, img, newText);
			} else {
				paintLine(g, w, h, img, this.text);
			}
		} else {
			this.width = w;

			// just in case it has not been called before
			Font usedFont = (this.font != null ? this.font : g.getFont());
			if (textLines == null) {
				computeTextLines(usedFont, this.width);
			}
			this.height = (usedFont.getHeight() + 2) * this.textLines.size();

			int originalY = g.getTranslateY();
			int reservedHeight = g.getFont().getHeight() + 2;
			g.translate(0, (h - (reservedHeight * this.textLines.size())) / 2);
			for (int i = 0; i < textLines.size(); i++) {
				String subStr = (String) textLines.elementAt(i);
				paintLine(g, w, reservedHeight, img, subStr);
				g.translate(0, reservedHeight);
			}
			g.translate(0, originalY - g.getTranslateY());

		}

		// #mdebug
//@		//				System.out.println("Drawn UILabel '" + text + "' at: ("
//@		//						+ g.getTranslateX() + ", " + g.getTranslateY() + ")"
//@		//						+ (selected ? "S" : ""));
		// #enddebug

		g.setFont(oldFont);
	}

	public void computeTextLines(Font usedFont, int w) {
		textLines = new Vector();
		Vector splittedVector = Utils.tokenize(this.text.replace('\t', ' '),
				new String[] { "\n", " " }, true);
		String[] splittedStrings = new String[splittedVector.size()];
		if (splittedVector.size() > 0) {
			for (int loop = 0; loop < splittedVector.size(); loop++)
				splittedStrings[loop] = (String) splittedVector.elementAt(loop);
		} else {
			// for "white lines"
			splittedStrings = new String[] { " " };
		}

		int index = 0;
		String tempString = "";
		while (index < splittedStrings.length) {
			do {
				tempString = tempString + splittedStrings[index];
				index++;
			} while (index < splittedStrings.length
					&& getTextWidth(tempString + splittedStrings[index],
							usedFont) < w
					&& (splittedStrings[index].compareTo("\n") != 0));

			tempString = tempString.trim();

			// just in case the line is empty it is not shown
			if (tempString.length() > 0) {
				// just in case the string is toooooooooo big
				Vector longStrings = splitLongStrings(tempString, w, usedFont);
				for (Enumeration en = longStrings.elements(); en
						.hasMoreElements();) {
					String s = (String) en.nextElement();
					s = s.trim();
					if (s.length() == 0) s = " ";
					textLines.addElement(s);
				}
			}
			tempString = "";
		}
	}

	private Vector splitLongStrings(String longString, int w, Font usedFont) {
		if (getTextWidth(longString, usedFont) < w) {
			Vector v = new Vector();
			v.addElement(longString);
			return v;
		}
		Vector retVector = new Vector();
		String tempString = "";
		int index = 0;
		while (longString.length() > 0) {
			while (index <= longString.length()) {
				int stringLength = getTextWidth(tempString, usedFont);
				if (stringLength > w) {
					index--;
					tempString = longString.substring(0, index - 1);
					break;
				}
				tempString = longString.substring(0, index);
				index++;
			}
			retVector.addElement(tempString);
			if (index - 1 < longString.length()) {
				longString = longString.substring(index - 1, longString
						.length());
			}
			if (index - 1 == longString.length()) break;
			tempString = "";
			index = 0;
		}
		return retVector;
	}

	/**
	 * @return the flip
	 */
	public boolean isFlip() {
		return flip;
	}

	/**
	 * @param flip
	 *            the flip to set
	 */
	public void setFlip(boolean flip) {
		this.flip = flip;
	}

	/**
	 * {@inheritDoc}
	 * 
	 */
	public int getHeight(Graphics g) {
		Font usedFont = (this.font != null ? this.font : g.getFont());
		this.height = 0;
		if (this.wrappable == false) {
			if (img != null) this.height = img.getHeight() + 2;
			if (usedFont.getHeight() + 2 > this.height) this.height = usedFont
					.getHeight() + 2;

		} else {
			int fontHeight = usedFont.getHeight();
			if (textLines == null) {
				computeTextLines(usedFont, this.width);
			}
			if (img != null) this.height = img.getHeight() + 2;
			int tempHeight = (fontHeight + 2) * this.textLines.size();
			if (tempHeight > this.height) this.height = tempHeight;
		}

		return height;
	}

	/**
	 * @return this label text
	 */
	public String getText() {
		return text;
	}

	/**
	 * Sets/changes the text for this label
	 * 
	 * @param _text
	 */
	public void setText(String _text) {
		if (text.equals(_text) == false) this.textLines = null;
		text = _text;
		dirty = true;
		// reset the text lines for wrappable labels
		// only when needed and avoid recomputing lines

	}

	/**
	 * @param anchorPoint
	 *            the anchorPoint to set
	 */
	public void setAnchorPoint(int anchorPoint) {
		this.anchorPoint = anchorPoint;
	}

	/**
	 * @return the anchorPoint
	 */
	public int getAnchorPoint() {
		return anchorPoint;
	}

	/**
	 * @param wrappable
	 *            the wrappable to set
	 */
	public void setWrappable(boolean wrappable, int width) {
		if (this.img != null) return;
		if (wrappable != this.wrappable) this.setDirty(true);
		this.wrappable = wrappable;
		//		if (this.width != width)
		//			this.textLines = null;
		this.width = width;
	}

	/**
	 * @return the wrappable
	 */
	public boolean isWrappable() {
		return wrappable;
	}

	/**
	 * @param font
	 *            the font to set
	 */
	public void setFont(Font font) {
		this.font = font;
	}

	/**
	 * @return the font
	 */
	public Font getFont() {
		return font;
	}

	/**
	 * @return the img
	 */
	public Image getImg() {
		return img;
	}

	/**
	 * @param img
	 *            the img to set
	 */
	public void setImg(Image img) {
		if (this.img == img) return;
		this.dirty = true;
		this.img = img;
		this.wrappable = false;

	}

	/**
	 * @param textLines
	 *            the textLines to set
	 */
	public void setTextLines(Vector textLines) {
		this.textLines = textLines;
		this.dirty = true;
	}

	/**
	 * @return the textLines
	 */
	public Vector getTextLines() {
		return textLines;
	}

	public void setImgAnchorPoint(int imgAnchorPoint) {
		this.imgAnchorPoint = imgAnchorPoint;
	}

	public int getImgAnchorPoint() {
		return imgAnchorPoint;
	}
}
