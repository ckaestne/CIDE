/* Copyright (c) 2008 Bluendo S.r.L.
 * See about.html for details about license.
 *
 * $Id: UIRadioButtons.java 1561 2009-06-08 13:52:35Z luca $
*/

package it.yup.ui;

import javax.microedition.lcdui.Canvas;
import javax.microedition.lcdui.Graphics;
import javax.microedition.lcdui.Image;

public class UIRadioButtons extends UIVLayout {

	private int chechedIndex = 0;

	private class UIRadioButton extends UILabel {
		public UIRadioButton(Image img, String text) {
			super(img, text);

		}

		boolean pointed = false;

		public boolean keyPressed(int key) {
			if (UICanvas.getInstance().getGameAction(key) == UICanvas.FIRE) {
				this.pointed = true;
				UIItem items[] = UIRadioButtons.this.layoutItems;
				for (int i = 0; i < items.length; i++) {
					if (items[i] == this) {
						if (UIRadioButtons.this.selectedIndex != i) {
							UIRadioButtons.this.selectedIndex = i;
							UIRadioButtons.this.keyPressed(key);
							break;
						}
					}
				}

				return true;
			}
			return false;
		}
	}

	public UIRadioButtons(String[] stringItems) {
		super(stringItems.length, 0);
		int buttonNumber = stringItems.length;
		for (int i = 0; i < buttonNumber; i++) {
			Image img = (i == 0 ? UICanvas
					.getUIImage("/icons/radio_checked.png") : UICanvas
					.getUIImage("/icons/radio_unchecked.png"));
			UILabel ulb = new UIRadioButton(img, stringItems[i]);
			ulb.setFocusable(true);
			this.insert(ulb, i, 100 / buttonNumber,
					UILayout.CONSTRAINT_PERCENTUAL);
		}
		this.focusable = true;
	}

	protected void paint(Graphics g, int w, int h) {
		this.getHeight(g);
		super.paint(g, w, h);
	}

	public int getHeight(Graphics g) {
		int itemHeight = this.layoutItems[0].getHeight(g);
		this.height = itemHeight * this.layoutItems.length;
		return this.height;
	}

	public boolean keyPressed(int key) {
		int ga = UICanvas.getInstance().getGameAction(key);
		boolean keepSelection = false;
		if (ga == Canvas.FIRE && this.selectedIndex >= 0
		//				&& this.chechedIndex != selectedIndex
		) {
			if (this.chechedIndex >= 0) {
				UILabel ulbOld = (UILabel) this.layoutItems[this.chechedIndex];
				ulbOld.img = UICanvas.getUIImage("/icons/radio_unchecked.png");
				ulbOld.setDirty(true);
			}

			this.chechedIndex = this.selectedIndex;
			UILabel ulb = (UILabel) this.layoutItems[this.chechedIndex];
			ulb.img = UICanvas.getUIImage("/icons/radio_checked.png");
			ulb.setSelected(true);
			this.setDirty(true);
			this.askRepaint();
			keepSelection = true;
		}
		keepSelection |= super.keyPressed(key);
		// we must save the last selectedIndex
		// when loosing focus
		if (keepSelection == false) {
			if (selectedIndex >= 0) {
				UILabel ulbOld = (UILabel) this.layoutItems[this.selectedIndex];
				ulbOld.setSelected(false);
				ulbOld.setDirty(true);
				this.setDirty(true);
				this.askRepaint();
			}
			this.selectedIndex = this.chechedIndex;
		}
		return keepSelection;
	}

	//	public void setSelected(boolean _selected) {
	//		super.setSelected(_selected);
	//		if (_selected && this.chechedIndex>=0)
	//			this.setSelectedIndex(this.chechedIndex);
	//			
	//	}

	public void setSelectedIndex(int i) {
		if (i < 0 || i > layoutItems.length) { return; }
		if (selectedIndex != -1) {
			layoutItems[selectedIndex].setSelected(false);
			layoutItems[selectedIndex].setDirty(true);
		}
		if (this.chechedIndex >= 0) {
			((UILabel) layoutItems[chechedIndex]).img = UICanvas
					.getUIImage("/icons/radio_unchecked.png");
			layoutItems[chechedIndex].setSelected(false);
		}
		layoutItems[i].setSelected(true);
		((UILabel) layoutItems[i]).img = UICanvas
				.getUIImage("/icons/radio_checked.png");
		selectedIndex = i;
		this.chechedIndex = this.selectedIndex;
		setDirty(true);
		askRepaint();
	}

	public int getSelectedIndex() {
		return selectedIndex;
	}
}
