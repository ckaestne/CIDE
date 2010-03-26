/**
 * 
 */
package it.yup.ui;

import java.util.Enumeration;
import java.util.Vector;

import javax.microedition.lcdui.Canvas;
import javax.microedition.lcdui.Font;
import javax.microedition.lcdui.Graphics;

/**
 * @author luca
 *
 */
public class UITextPanel extends UIPanel {

	private String text = "";
	private Vector textLines = null;

	private int firstLabel = 0;
	private int lastLabel = 0;
	boolean needScrollbar = false;

	/* The possible container for this object*/
	private UIItem container = null;

	private boolean enableEmoticons = false;

	protected Vector getTextLines() {
		return this.textLines;
	}

	protected void setTextLines(Vector v) {
		this.textLines = v;
	}

	/**
	 * 
	 */
	public UITextPanel() {
		this.setFocusable(true);
	}

	public void setText(String text) {
		this.text = text;
		textLines = null;
		this.getItems().removeAllElements();
	}

	public void setMaxHeight(int mh) {
		if (this.maxHeight != mh) {
			// so to recompute the lines
			textLines = null;
		}
		super.setMaxHeight(mh);
	}

	protected void paint(Graphics g, int w, int h) {
		if (textLines == null) {
			this.removeAllItems();
			UILabel tempLabel = null;
			if (this.enableEmoticons) tempLabel = new UIEmoLabel(text);
			else
				tempLabel = new UILabel(text);
			int availableWidth = w - 1 - UIConfig.scrollbarWidth;
			tempLabel.setWrappable(true, availableWidth);
			tempLabel.computeTextLines(g.getFont(), availableWidth);
			textLines = tempLabel.getTextLines();
			int labelsHeight = 0;
			UILabel ithLabel = null;
			int count = 0;
			do {
				if (this.enableEmoticons) ithLabel = new UIEmoLabel("");
				else
					ithLabel = new UILabel("");
				ithLabel.setBg_color(UIConfig.input_color);
				ithLabel.setFg_color(UIConfig.fg_color);
				ithLabel.setSelectedColor(UIConfig.input_color);
				ithLabel.setFocusable(true);
				ithLabel.setSelected(this.selected);
				this.addItem(ithLabel);
				labelsHeight += ithLabel.getHeight(g);
				count++;
			} while (labelsHeight < (h - ithLabel.getHeight(g))
					&& count < textLines.size());
			firstLabel = 0;
			lastLabel = this.getItems().size() - 1;
		}
		int itemsSize = this.getItems().size();
		int paintedHeight = 0;
		for (int i = 0; i < itemsSize; i++) {
			UILabel ithLabel = (UILabel) this.getItems().elementAt(i);
			if (i + firstLabel < textLines.size()) (ithLabel)
					.setText((String) textLines.elementAt(i + firstLabel));
			paintedHeight += ithLabel.getHeight(g);
		}
		this.needScrollbar = false;
		super.paint(g, w, h);
		// i don't want my labels to be "clicked"
		Enumeration en = this.getItems().elements();
		while (en.hasMoreElements()) {
			this.screen.removePaintedItem((UIItem) en.nextElement());
		}

		// fill the gap
		g.setColor(getBg_color());
		if (needScrollbar) w -= UIConfig.scrollbarWidth;
		g.fillRect(0, paintedHeight, w, h - paintedHeight);

	}

	protected int computeRealHeight(Graphics g) {
		if (this.getItems().size() == 0) return 0;

		// my real height is the the number of textLines
		// per the height of a label
		return textLines.size()
				* ((UILabel) this.getItems().elementAt(0)).getHeight(g);
	}

	public boolean keyPressed(int key) {

		int ga = UICanvas.getInstance().getGameAction(key);
		if (ga != Canvas.DOWN && ga != Canvas.UP) { return super
				.keyPressed(key); }
		if (textLines != null) {
			switch (ga) {
				case Canvas.DOWN:
					if (lastLabel < this.textLines.size() - 1) {
						this.firstLabel++;
						this.lastLabel++;
						this.setDirty(true);
						this.askRepaint();
						return true;
					} else if (lastLabel == this.textLines.size() - 1
							&& needScrollbar == false) { return false; }
					return true;

				case Canvas.UP: {
					if (firstLabel > 0) {
						this.firstLabel--;
						this.lastLabel--;
						this.setDirty(true);
						this.askRepaint();
						return true;
					} else if (firstLabel == 0 && needScrollbar == false) { return false; }
					return true;
				}
			}
		}
		return false;
	}

	protected void drawScrollBar(Graphics g, int w, int h, int rh) {
		this.needScrollbar = true;
		int otx = g.getTranslateX();
		int oty = g.getTranslateY();
		int oc = g.getColor();
		g.setColor(UIConfig.scrollbar_bg);
		g.translate(w - UIConfig.scrollbarWidth, 0);
		g.fillRect(0, 0, UIConfig.scrollbarWidth, h);

		/* calculate y and height of scrollbar */
		int sy = h * firstLabel / textLines.size();
		int sh = (h * h) / rh;
		if (sy + sh > h || lastLabel == textLines.size() - 1) {
			sy = h - sh;
		}

		g.setColor(UIConfig.scrollbar_fg);
		g.fillRect(1, sy, UIConfig.scrollbarWidth - 2, sh);
		/* resets origin to old value */
		g.translate(-otx + g.getTranslateX(), -oty + g.getTranslateY());
		g.setColor(oc);
	}

	public UIItem getSelectedItem() {
		if (this.container != null) return this.container;
		return this;
	}

	public void setContainer(UIItem container) {
		this.container = container;
	}

	public String getText() {
		return this.text;
	}

	public void setFont(Font tfont) {
		Enumeration en = this.getItems().elements();
		while (en.hasMoreElements()) {
			((UILabel) en.nextElement()).setFont(tfont);
		}
	}

	public void setSelected(boolean _selected) {
		this.selected = _selected;
		Enumeration en = this.getItems().elements();
		while (en.hasMoreElements()) {
			((UIItem) en.nextElement()).setSelected(_selected);
		}
	}

	public void setScreen(UIScreen _us) {
		screen = _us;
		Enumeration en = this.getItems().elements();
		while (en.hasMoreElements()) {
			((UIItem) en.nextElement()).setScreen(_us);
		}
	}

	public boolean isNeedScrollbar() {
		return needScrollbar;
	}

	public boolean isEnableEmoticons() {
		return enableEmoticons;
	}

	public void setEnableEmoticons(boolean enableEmoticons) {
		this.enableEmoticons = enableEmoticons;
	}
}
