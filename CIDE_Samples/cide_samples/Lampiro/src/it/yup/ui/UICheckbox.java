/* Copyright (c) 2008 Bluendo S.r.L.
 * See about.html for details about license.
 *
 * $Id: UICheckbox.java 846 2008-09-11 12:20:05Z luca $
*/

package it.yup.ui;

import java.io.IOException;

import javax.microedition.lcdui.Canvas;

/**
 * 
 */

/**
 * @author luca
 * 
 */
public class UICheckbox extends UILabel {

	/**
	 * Keeps the checked state of the Checkbox
	 * 
	 */
	private boolean checked = false;

	/**
	 * @throws IOException
	 * 
	 */
	public UICheckbox(String text) {
		super(UICanvas.getUIImage("/icons/unchecked.png"), text);
		this.focusable = true;
		this.wrappable = false;
	}

	public boolean isChecked() {
		return checked;
	}

	public void setChecked(boolean checked) {
		boolean changed = false;
		if (checked != this.checked)
			changed = true;
		this.checked = checked;
		if (changed == true) {
			if (checked == true)
				this.img = UICanvas.getUIImage("/icons/checked.png");
			else
				this.img = UICanvas.getUIImage("/icons/unchecked.png");
			this.dirty = true;
			this.askRepaint();
		}
	}

	/**
	 * {@inheritDoc}
	 * 
	 */
	public boolean keyPressed(int key) {
		if (UICanvas.getInstance().getGameAction(key) == Canvas.FIRE)
			this.setChecked(!this.checked);
		return false;
	}
}
