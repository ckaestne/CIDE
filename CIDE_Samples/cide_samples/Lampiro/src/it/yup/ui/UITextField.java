/* Copyright (c) 2008 Bluendo S.r.L.
 * See about.html for details about license.
 *
 * $Id: UITextField.java 1512 2009-05-18 14:29:27Z luca $
*/

package it.yup.ui;

import java.util.Vector;

import javax.microedition.lcdui.Canvas;
import javax.microedition.lcdui.Command;
import javax.microedition.lcdui.CommandListener;
import javax.microedition.lcdui.Displayable;
import javax.microedition.lcdui.Font;
import javax.microedition.lcdui.Graphics;
import javax.microedition.lcdui.TextBox;
import javax.microedition.lcdui.TextField;

/**
 * Resembles a {@link TextField} from standard microedition.lcdui package,
 * representing a text box with a label. When the select button is pressed on
 * this item, a TextBox screen is opened and used to let the user enter text.
 */
public class UITextField extends UIItem implements CommandListener {

	// the buttons below are set like this to fix a perverted behavior
	// of nokia N95 that leaves the "cancel" button alone and mixes
	// all the others button on the same menus!!!
	/** ok command for the TextBox */
	private static Command cmd_ok = new Command("OK", Command.OK, 1);
	/** ok command for the TextBox */
	private static Command cmd_cancel = new Command("CANCEL", Command.CANCEL, 2);
	/** the TextBox used */
	private static TextBox tb;

	private UITextPanel innerPanel;

	private boolean groupSelected = false;

	/*
	 * Must be set to true when the UITextfield must automatically unexpand
	 * when loosing focus 
	 */
	private boolean autoUnexpand = true;

	/*
	 * Must be set to true when the UITextfield can be expanded and unexpanded
	 * by the control itself in response to a keyPress (programmatically it still can be 
	 * epanded or unexpanded )
	 */
	private boolean expandable = true;

	/** the label */
	private String label;

	/** the max size for the text. */
	private int maxSize;

	private boolean wrappable = false;

	/**
	 * the constraints on the text. These constraints should be the same
	 * constants defined in {@link TextField} (such as {@link TextField#ANY},
	 * {@link TextField#PASSWORD}, ...). These are set as given in the
	 * constraints of the TextBox that is opened on the pressure of the select
	 * button on this item
	 */
	private int constraints;
	private int maxLines = 4;
	private int minLines = 1;

	private boolean isEditable() {
		return !((constraints & TextField.UNEDITABLE) > 0);
	}

	public UITextField(String label, String text, int maxSize, int constraints) {
		this.label = label == null ? "" : label;
		this.innerPanel = new UITextPanel();
		this.innerPanel.setContainer(this);
		this.innerPanel.setText(text == null ? "" : text);
		innerPanel.setBg_color(UIConfig.input_color);
		//		innerPanel.setFg_color(UIConfig.fg_color);
		//		innerPanel.setSelectedColor(UIConfig.input_color);
		this.maxSize = maxSize;
		this.constraints = constraints;
		setFocusable(true);
		this.innerPanel.setFocusable(true);
		dirty = true;
		// the minimum height is the one of a UILabel
		this.setMaxHeight(UIConfig.font_body.getHeight() + 2);
	}

	/**
	 * Sets the label value
	 * 
	 * @param label
	 *            the new label value
	 */
	public void setLabel(String label) {
		this.label = label;
		dirty = true;
	}

	/**
	 * @return the current field label
	 */
	public String getLabel() {
		return label;
	}

	/**
	 * Changes the text shown in the field.
	 * 
	 * @param text
	 *            the text to set
	 */
	public void setText(String text) {
		if (text.length() > maxSize) {
			text = text.substring(0, maxSize);
		}
		this.innerPanel.setText(text);
		dirty = true;
	}

	/**
	 * @return the current text
	 */
	public String getText() {
		return this.innerPanel.getText();
	}

	protected void paint(Graphics g, int w, int h) {
		this.width = w;
		Font xFont = g.getFont();
		Font lfont = Font.getFont(xFont.getFace(), Font.STYLE_BOLD, xFont
				.getSize());
		Font tfont = Font.getFont(xFont.getFace(), Font.STYLE_PLAIN, xFont
				.getSize());
		this.innerPanel.setFont(tfont);

		int tempBc_color = getBg_color() >= 0 ? getBg_color()
				: UIConfig.bg_color;
		g.setColor(tempBc_color);
		g.fillRect(0, 0, w, h);

		int offset = (h - this.getHeight(g)) / 2 - 1;
		if (offset < 0) offset = 0;
		int loffset = 0;
		if (label.length() > 0) loffset = lfont.getHeight();
		if (wrappable && selected && groupSelected) {
			g.setColor(tempBc_color);
			g.fillRect(0, offset, w, loffset);
		} 
		g.setColor(UIConfig.fg_color);
		g.setFont(lfont);
		g.drawString(label, 2, 1 + offset, Graphics.LEFT | Graphics.TOP);
		g.setFont(tfont);
		int innerLabelHeight = this.innerPanel.getHeight(g);

		// first draw the outer  borders and then the inner one 
		int x0 = 1, y0 = 2 + loffset + offset, x1 = w - 3 + x0, y1 = 3
				+ innerLabelHeight + y0;
		
		drawInput(g, x0, y0, x1, y1);
		g.setColor(UIConfig.fg_color);
		
		String innerText = this.innerPanel.getText();
		String t = innerText;
		if (this.wrappable == false) {
			if ((constraints & TextField.PASSWORD) != 0 && t != null
					&& t.length() > 0) {
				/* text should be obscured */
				t = "*******";
			}
			if (tfont.stringWidth(t) > w - 9) {
				int l = 0;
				while (l < innerText.length()
						&& tfont.substringWidth(innerText, 0, l)
								+ tfont.stringWidth("...") < w - 9) {
					l++;
				}
				l--;
				t = this.innerPanel.getText().substring(0, l) + "...";
			}
			this.innerPanel.setText(t);
		}
		g.translate(3, 4 + loffset + offset);
		this.innerPanel.paint0(g, w - 6, innerLabelHeight);
		// I don't want my Panel to be "clicked"
		this.screen.removePaintedItem(innerPanel);
		if (this.wrappable == false) {
			this.innerPanel.setText(innerText);
		}
		g.setFont(xFont);
	}

	public int getHeight(Graphics g) {
		if (dirty) {
			// if the new height is computed computeRealHeight is mandatory to compute 
			// real height of the textfield and the innerPanel
			//if (this.wrappable) computeRealHeight();
			Font xFont = g.getFont();
			Font lfont = Font.getFont(xFont.getFace(), Font.STYLE_BOLD, xFont
					.getSize());
			height = this.innerPanel.getHeight(g) + 7;
			// if label is different from "" add its text
			if (label.length() > 0) height += lfont.getHeight();
		}
		return height;
	}

	public void setSelected(boolean _selected) {
		super.setSelected(_selected);
		this.innerPanel.setSelected(_selected);
		if (_selected == false) {
			this.groupSelected = false;
		}
	}

	/**
	 * @return the wrappable
	 */
	public boolean isWrappable() {
		return this.wrappable;
	}

	/**
	 * @param wrappable
	 *            the wrappable to set
	 */
	public void setWrappable(boolean wrappable) {
		this.wrappable = wrappable;
		// depending on the real height of the textPanel
		// I recompute the needed height
		if (this.wrappable == true) {
			computeRealHeight();
			this.setDirty(true);
			this.askRepaint();
		}
	}

	private void computeRealHeight() {
		UILabel tempLabel = new UILabel(this.innerPanel.getText());
		//needed for the borders
		int w = this.width - 14;
		if (w < 0) w = UICanvas.getInstance().getWidth() - 14
				- UIConfig.scrollbarWidth;
		tempLabel.setWrappable(true, w);
		tempLabel.computeTextLines(UIConfig.font_body, w);
		Vector textLines = tempLabel.getTextLines();
		int nLines = textLines.size();
		if (nLines > this.maxLines) nLines = this.maxLines;
		if (nLines < this.minLines) nLines = this.minLines;
		int textHeight = nLines * (UIConfig.font_body.getHeight() + 2) + 1;
		// if nothing is inside at least one row should be present
		if (textHeight == 1) textHeight = (UIConfig.font_body.getHeight() + 2) + 1;
		this.setMaxHeight(textHeight);
	}

	public boolean keyPressed(int key) {
		int ga = UICanvas.getInstance().getGameAction(key);
		if (wrappable && groupSelected) {
			if (ga != Canvas.FIRE) {
				boolean innerKeyKeep = this.innerPanel.keyPressed(key);
				if (innerKeyKeep == false && this.autoUnexpand
						&& this.expandable) {
					unExpand();
				}
				return innerKeyKeep;
			} else {
				if (this.expandable) unExpand();
				return true;
			}
		}

		// if a keyNum has been pressed open the textField
		// and print it!!!
		int keyNum = -1;
		switch (key) {
			case Canvas.KEY_NUM0:
			case Canvas.KEY_NUM1:
			case Canvas.KEY_NUM2:
			case Canvas.KEY_NUM3:
			case Canvas.KEY_NUM4:
			case Canvas.KEY_NUM5:
			case Canvas.KEY_NUM6:
			case Canvas.KEY_NUM7:
			case Canvas.KEY_NUM8:
			case Canvas.KEY_NUM9:
				keyNum = key;
		}
		if (keyNum == -1 && ga != Canvas.FIRE) { return false; }
		// the only need for expansion is when:
		// 1) fire is pressed
		// 2) the object is wrappable and hence could need the innerPanel to open
		// 3) this has not yet been selected.
		// 4) this object is editable 
		// 5) the scrollbar is visible 
		// 6) the UITextfield is expandable
		if (ga == Canvas.FIRE && this.wrappable && this.groupSelected == false
				&& isEditable() == false && this.innerPanel.needScrollbar
				&& this.expandable) {
			expand();
			return true;
		}

		if (isEditable()) {
			// some mobile phones crash when a label is ""
			handleScreen();
			tb.setCommandListener(this);
			UICanvas.display(tb);
		}

		return true;
	}

	public void unExpand() {
		this.groupSelected = false;
		this.innerPanel.setSelected(true);
		this.setWrappable(true);
		this.setDirty(true);
		this.askRepaint();
	}

	public void expand() {
		this.groupSelected = true;
		this.innerPanel.setSelected(false);
		Vector textLines = this.innerPanel.getTextLines();
		if (textLines != null) {
			int nLines = textLines.size();
			int neededHeight = nLines * (UIConfig.font_body.getHeight() + 2)
					+ 1;
			Graphics g = this.screen.getGraphics();
			int maxHeight = UICanvas.getInstance().getClipHeight();
			maxHeight -= this.screen.headerLayout.getHeight(g);
			maxHeight -= this.screen.footer.getHeight(g);
			// a little bit of margin for the label...
			maxHeight -= 35;
			if (neededHeight > maxHeight) neededHeight = maxHeight;
			this.setMaxHeight(neededHeight);
		}
		this.setDirty(true);
		this.askRepaint();
	}

	public void handleScreen() {
		String tempLabel = null;
		tempLabel = (label == null || label.compareTo("") == 0) ? "_" : label;
		tb = new TextBox(tempLabel, this.innerPanel.getText(), maxSize,
				constraints);
		tb.addCommand(cmd_cancel);
		tb.addCommand(cmd_ok);
		tb.setCommandListener(this);
		UICanvas.display(tb);
	}

	public void commandAction(Command cmd, Displayable disp) {
		if (cmd == cmd_ok) {
			setText(tb.getString());
			screen.itemAction(this);
			UICanvas.display(null);
		} else if (cmd == cmd_cancel) {
			UICanvas.display(null);
		}
		this.dirty = true;
		this.askRepaint();
	}

	public void setMaxHeight(int maxHeight) {
		this.innerPanel.setMaxHeight(maxHeight);
	}

	public void setMaxLines(int maxLines) {
		this.maxLines = maxLines;
	}

	public void setScreen(UIScreen _us) {
		screen = _us;
		this.innerPanel.setScreen(_us);
	}

	public void setDirty(boolean _dirty) {
		this.dirty = _dirty;
		this.innerPanel.setDirty(_dirty);
	}

	public boolean isDirty() {
		return this.dirty || innerPanel.isDirty();
	}

	public void setAutoUnexpand(boolean autoUnexpand) {
		this.autoUnexpand = autoUnexpand;
	}

	private boolean isAutoUnexpand() {
		return autoUnexpand;
	}

	public void setExpandable(boolean expandable) {
		this.expandable = expandable;
	}

	public boolean isExpandable() {
		return expandable;
	}

	public void setMinLines(int minLines) {
		this.minLines = minLines;
	}

	public int getMinLines() {
		return minLines;
	}
}
