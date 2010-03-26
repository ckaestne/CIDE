/* Copyright (c) 2008 Bluendo S.r.L.
 * See about.html for details about license.
 *
 * $Id: UILayout.java 1471 2009-05-13 21:35:41Z luca $
*/

package it.yup.ui;

import javax.microedition.lcdui.Canvas;
import javax.microedition.lcdui.Graphics;

public abstract class UILayout extends UIItem implements UIIContainer {

	public static final int CONSTRAINT_PIXELS = 0;
	public static final int CONSTRAINT_PERCENTUAL = 1;

	protected int selectedIndex = -1;
	/**
	 * Shows if a layout has been focused and hence its children can receive a
	 * keypress
	 */
	boolean layoutFocused = false;

	/**
	 * a "grouped" Layout shows a border when selected and needs the FIRE
	 * pressed to enter inside. A non-grouped layout does not need the user to
	 * press FIRE to navigate inside the items. A Layout is grouped by default
	 */
	private boolean group;

	/**
	 * The layout elements.
	 */
	protected UIItem[] layoutItems;

	/*
	 * The keys used by subclasses to move (Canvas.LEFT, Canvas.RIGHT , etc...)
	 */
	protected int dirKey1 = 0;
	protected int dirKey2 = 1;

	public UILayout(int elemNumber) {
		this.layoutItems = new UIItem[elemNumber];
		this.focusable = false;
		group = true;
	}

	public void setSelected(boolean _selected) {
		if (_selected == selected) return;

		selected = _selected;

		setDirty(true);

		/* when losing selection, remove selection from last selected item */
		if (!selected && selectedIndex >= 0
				&& selectedIndex < layoutItems.length) {
			layoutItems[selectedIndex].setSelected(false);
		}
		if (!selected) {
			layoutFocused = false;
		}
		if (!group && selected) {
			layoutFocused = true;
			if (selectedIndex == -1) {
				do {
					selectedIndex++;
				} while (selectedIndex < layoutItems.length
						&& !layoutItems[selectedIndex].isFocusable());
			}
			if (selectedIndex >= 0 && selectedIndex < layoutItems.length) {
				layoutItems[selectedIndex].setSelected(true);
			}
		}

		/*
		 * select=true on a non-group layout: choose the first selectable item
		 * select=false on a non-group layout: remove selection from current
		 * item select=true on a group layout: just set layoutFocused to true.
		 * select=false on a group layour: set layoutFocused to false
		 */
	}

	/**
	 * Overrides the default behaviour dispatching the screen to all the items
	 * contained
	 * 
	 * @param _us
	 *            the screen to use
	 */
	public void setScreen(UIScreen _us) {
		screen = _us;
		for (int i = 0; i < layoutItems.length; i++) {
			if (layoutItems[i] != null) layoutItems[i].setScreen(screen);
		}
	}

	/**
	 * Set the {@link UIScreen} to be repainted.
	 * 
	 * @param dirty
	 *            The new value for dirty.
	 */
	public void setDirty(boolean dirty) {
		// this.height = -1;
		// this.width = -1;
		this.dirty = dirty;
		for (int i = 0; i < this.layoutItems.length; i++)
			this.layoutItems[i].setDirty(dirty);
	}

	/**
	 * Set the {@link UIScreen} to be repainted.
	 * 
	 * @param dirty
	 *            The new value for dirty.
	 */
	public boolean isDirty() {
		// this.height = -1;
		// this.width = -1;
		for (int i = 0; i < this.layoutItems.length; i++)
			if (this.layoutItems[i].isDirty()) return true;
		if (this.dirty) return true;
		return false;
	}

	protected void updateChildren() {
		for (int i = 0; i < this.layoutItems.length; i++) {
			if (this.selectedIndex != i) {
				this.layoutItems[i].setSelected(false);
			}
		}
		this.layoutItems[this.selectedIndex].setSelected(true);
		this.setDirty(true);
		this.askRepaint();
	}

	public UIItem getItem(int position) {
		return this.layoutItems[position];
	}

	public boolean isFocusable() {
		for (int i = 0; i < this.layoutItems.length; i++) {
			if (layoutItems[i].isFocusable()) { return true; }
		}
		return focusable;
	}

	int traverseFocusable(int startingIndex, boolean directionDown) {
		if (startingIndex < 0) {
			startingIndex = 0;
		}
		if (directionDown) {
			while (startingIndex < this.layoutItems.length
					&& this.layoutItems[startingIndex].isFocusable() == false) {
				startingIndex++;
			}
			if (startingIndex >= this.layoutItems.length) return -1;
			else
				return startingIndex;
		} else {
			while (startingIndex >= 0
					&& this.layoutItems[startingIndex].isFocusable() == false) {
				startingIndex--;
			}
			if (startingIndex < 0) return -1;
			else
				return startingIndex;
		}

	}

	protected void drawSegmentedBorder(Graphics g, int w, int h) {
		g.setColor(0x223377);
		// g.setColor(UIScreen.bg_color);
		//                int segmentLength = 2;
		//                for (int i = 0; i < w; i += 2 * segmentLength) {
		//                        g.drawLine(i, 0, i + segmentLength, 0);
		//                        g.drawLine(i, h - 1, i + segmentLength, h - 1);
		//                }
		//                for (int i = 0; i < h; i += 2 * segmentLength) {
		//                        g.drawLine(0, i, 0, i + segmentLength);
		//                        g.drawLine(w - 1, i, w - 1, i + segmentLength);
		//                }
		int oldSTroke = g.getStrokeStyle();
		g.setStrokeStyle(Graphics.DOTTED);
		g.drawRect(0, 0, w - 1, h - 1);
		g.setStrokeStyle(oldSTroke);
	}

	/**
	 * Sets this layout as grouped
	 * 
	 * @param group
	 *            {@code true} if the lyout is grouped, {@code false} otherwise
	 */
	public void setGroup(boolean group) {
		this.group = group;
	}

	/**
	 * @return if this layout is grouped
	 */
	public boolean isGroup() {
		return group;
	}

	/**
	 * Return the selected UIItem within the UIItem itself; usually it is the
	 * UIItem itself but in the subclasses (like UIVLayout) it could be one of
	 * the contained object.
	 * 
	 * @return
	 */
	public UIItem getSelectedItem() {
		// if (this.group == false)
		// return this;
		if (this.selectedIndex >= 0
				&& this.layoutItems.length >= (this.selectedIndex + 1)) {
			return this.layoutItems[this.selectedIndex].getSelectedItem();
		} else {
			return this;
		}
	}

	public void setSelectedIndex(int index) {
		if (index >= 0 && index < this.layoutItems.length) this
				.setSelectedItem(this.layoutItems[index]);
		else
			this.selectedIndex = index;
	}

	/**
	 * Return the selected UIItem within the UIItem itself; usually it is the
	 * UIItem itself but in the subclasses (like UIVLayout) it could be one of
	 * the contained object.
	 * 
	 * @return
	 */
	public void setSelectedItem(UIItem item) {
		for (int i = 0; i < layoutItems.length; i++) {
			if (this.layoutItems[i] == item) {
				if (this.selectedIndex >= 0
						&& this.selectedIndex < layoutItems.length) {
					this.layoutItems[selectedIndex].setSelected(false);
				}
				this.selectedIndex = i;
				if (this.layoutItems[selectedIndex].isSelected() == false) {
					this.layoutItems[selectedIndex].setSelected(true);
				}
			}
		}
		if (this.getContainer() != null) {
			this.getContainer().setSelectedItem(this);
		}
	}

	public boolean contains(UIItem item) {
		for (int i = 0; i < this.layoutItems.length; i++) {
			UIItem ithItem = layoutItems[i];
			if (ithItem == item) return true;
			if (ithItem instanceof UIIContainer) {
				UIIContainer iic = (UIIContainer) ithItem;
				if (iic.contains(item)) return true;
			}
		}
		return false;
	}

	public void setBg_color(int bg_color) {
		this.bg_color = bg_color;
		for (int i = 0; i < layoutItems.length; i++) {
			UIItem ithLayout = layoutItems[i];
			ithLayout.setBg_color(bg_color);
		}
	}

	public boolean keyPressed(int key) {
		// first forward the keyPressed!!!!
		if (selectedIndex >= 0 && selectedIndex < this.layoutItems.length
				&& layoutFocused && layoutItems[selectedIndex].keyPressed(key)) {
			// this is needed since we cannot know if anything below has been
			// repainted
			updateChildren();
			return true;
		}

		int ga = UICanvas.getInstance().getGameAction(key);
		if (layoutFocused) {
			if (ga == dirKey1) {
				if (this.selectedIndex > 0) {
					int newSelectedIndex = this.selectedIndex - 1;
					if (this.isFocusable()) newSelectedIndex = traverseFocusable(
							newSelectedIndex, false);
					if (newSelectedIndex >= 0) this.selectedIndex = newSelectedIndex;
					else
						return false;
					updateChildren();
					return true;
				}
				return false;
			} else if (ga == dirKey2) {
				if (this.selectedIndex < this.layoutItems.length - 1) {
					int newSelectedIndex = this.selectedIndex + 1;
					if (this.isFocusable()) newSelectedIndex = traverseFocusable(
							newSelectedIndex, true);
					if (newSelectedIndex >= 0) this.selectedIndex = newSelectedIndex;
					else
						return false;
					updateChildren();
					return true;
				}
				return false;
			}
		}

		if ((key == UICanvas.MENU_RIGHT || ga == Canvas.FIRE)) {
			if (this.isFocusable()) {
				this.layoutFocused = true;
				int oldSelectedIndex = selectedIndex;
				selectedIndex = traverseFocusable(selectedIndex, true);
				if (selectedIndex >= 0 && oldSelectedIndex != selectedIndex) {
					this.layoutItems[selectedIndex].setSelected(true);
					updateChildren();
					return true;
				}
			}
		}
		return false;
	}

}
