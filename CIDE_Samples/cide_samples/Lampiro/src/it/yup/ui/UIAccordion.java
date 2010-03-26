/**
 * 
 */
package it.yup.ui;

import it.yup.util.MetaVector;

import java.util.Enumeration;
import java.util.Hashtable;
import java.util.Vector;

import javax.microedition.lcdui.Canvas;
import javax.microedition.lcdui.Graphics;
import javax.microedition.lcdui.Image;

/**
 * @author luca
 *
 */
public class UIAccordion extends UIPanel {

	private Hashtable accordionItems = new Hashtable(5);

	private int sepColor = 0xBBBBBB;
	private int sepSize = 2;
	private int labelColor = 0xAAAAAA;
	private int labelGradientColor = -1;

	/*
	 * true if at least one label must be opened
	 */
	private boolean oneOpen = false;

	/*
	 * The index of the openedItem
	 */
	private UIItem openedItem = null;

	/*
	 * The images used for submenus
	 */
	public Image closeImage = UIMenu.menuImage;
	public Image openImage = UICanvas.getUIImage("/icons/downimage.png");

	/*
	 * A vector that contains the labels 
	 */
	private Vector itemLabels = new Vector();

	private int labelSelectedColor = -1;

	private int labelGradientSelectedColor = -1;

	private class AccordionItem {
		private UIVLayout wrappedItem;
		private Vector subPanel;

		AccordionItem(UIVLayout wrappedItem, Vector subPanel) {
			this(wrappedItem);
			this.wrappedItem = wrappedItem;
		}

		public AccordionItem(UIVLayout wrappedItem) {
			this.wrappedItem = wrappedItem;
		}

		/*
		 * Return the label that contains the img in the item
		 */
		public UILabel getImgLabel() {
			return (UILabel) ((UIHLayout) this.wrappedItem.layoutItems[0]).layoutItems[0];
		}
	}

	/**
	 * 
	 */
	public UIAccordion() {
		super();
		this.setItems(new MetaVector(5));
	}

	private void setSubPanel(UIItem item, Vector subPanel) {
		AccordionItem ai = (AccordionItem) this.accordionItems.get(item);
		ai.subPanel = subPanel;
		Enumeration en = subPanel.elements();
		while (en.hasMoreElements()) {
			UIItem ithEl = (UIItem) en.nextElement();
			ithEl.setFocusable(true);
			ithEl.setContainer(this);
		}
	}

	public int getHeight(Graphics g) {
		// if maxHeight = -1 I want to behave like a UIPanel
		// if maxHeight = o I want to behave like an object with a fixed size
		if (maxHeight == 0) {
			int newHeight = 0;
			Enumeration en = this.getItems().elements();
			while (en.hasMoreElements()) {
				UIItem ithItem = (UIItem) en.nextElement();
				newHeight += ithItem.getHeight(g);
			}
			// in case I am within an UIMenu this is mandatory
			int maxTempHeight = (UICanvas.getInstance().getClipHeight() * 2) / 3;
			if (newHeight > maxTempHeight) newHeight = maxTempHeight;
			this.height = newHeight;
			return this.height;
		}
		return super.getHeight(g);
	}

	public void addSpareItem(UIItem item) {
		super.addItem(item);
	}

	public void insertSpareItem(UIItem it, int idx) {
		super.insertItemAt(it, idx);
	}

	public void addItem(UIItem item) {
		UIVLayout wrappedItem = wrapItem(item);
		super.addItem(wrappedItem);
		this.itemLabels.addElement(item);
		this.accordionItems.put(item, new AccordionItem(wrappedItem));
	}

	public void addItem(UIItem item, Vector subPanel) {
		this.addItem(item);
		this.setSubPanel(item, subPanel);
	}

	public void insertItem(UIItem it, int idx) {
		UIVLayout wrappedItem = wrapItem(it);
		super.insertItemAt(wrappedItem, idx);
		this.itemLabels.insertElementAt(it, idx);
		this.accordionItems.put(it, new AccordionItem(wrappedItem));
	}

	public void insertItem(UIItem it, int idx, Vector subPanel) {
		this.insertItem(it, idx);
		this.setSubPanel(it, subPanel);
	}

	//      public Vector getItems() {
	//              return super.getItems();
	//              Vector tempItems = new Vector(1 + this.subPanels.size());
	//              Enumeration en = this.subPanels.keys();
	//              while (en.hasMoreElements()) {
	//                      tempItems.addElement(en.nextElement());
	//              }
	//              return tempItems;
	//      }

	public int removeItem(UIItem it) {
		AccordionItem ai = (AccordionItem) this.accordionItems.get(it);
		if (this.openedItem == it) close(it);
		this.accordionItems.remove(it);
		this.itemLabels.removeElement(it);
		if (this.openedItem == it) this.openedItem = null;
		return super.removeItem(ai.wrappedItem);
	}

	/**
	 * Remove all elements
	 */
	public void removeAllItems() {
		super.removeAllItems();
		this.accordionItems.clear();
		this.itemLabels.removeAllElements();
		this.openedItem = null;
	}

	public int getPanelIndex(UIItem label, UIItem item) {
		Vector v = getSubpanel(label);
		return v.indexOf(item);
	}

	public Vector getSubpanel(UIItem label) {
		AccordionItem accordionItem = ((AccordionItem) this.accordionItems
				.get(label));
		return accordionItem != null ? accordionItem.subPanel : null;
	}

	public int getPanelSize(UIItem label) {
		Vector v = getSubpanel(label);
		if (v == null) return 0;
		return v.size();
	}

	public UIItem getPanelItem(UIItem label, int index) {
		Vector v = getSubpanel(label);
		return (UIItem) v.elementAt(index);
	}

	/*
	         * Returns true if a repaint is needed
	         */
	public void removePanelItem(UIItem label, UIItem item) {
		Vector v = getSubpanel(label);
		if (openedItem == label) {
			int idx = this.getItems().indexOf(item);
			if (selectedIdx >= idx && idx >= 0) selectedIdx--;
			item.setSelected(false);
		}
		v.removeElement(item);
		if (this.getScreen() != null) this.getScreen().removePaintedItem(item);
		if (label == this.openedItem && v.size() == 0) this.close(label);
	}

	public void insertPanelItem(UIItem label, UIItem item, int idx) {
		Vector v = getSubpanel(label);
		v.insertElementAt(item, idx);
		item.setContainer(this);
		if (openedItem == label) {
			if (selectedIdx >= this.getItems().indexOf(item)) selectedIdx++;
		}
	}

	private UIVLayout wrapItem(UIItem item) {
		int vlSize = (this.sepSize > 0 ? 2 : 1);
		// I need a graphics to compute the height: i use mine otherwise
		// the painted one
		UIScreen currentScreen = this.getScreen();
		currentScreen = (currentScreen == null ? (UIScreen) (UICanvas
				.getInstance().getScreenList().elementAt(0)) : currentScreen);
		Graphics g = currentScreen.getGraphics();
		UIVLayout ivl = new UIVLayout(vlSize, item.getHeight(g)
				+ (this.sepSize >= 0 ? sepSize : 0));
		ivl.setGroup(false);
		UIHLayout ihl = new UIHLayout(2);
		ihl.setGroup(false);
		ivl.insert(ihl, 0, item.getHeight(g), UILayout.CONSTRAINT_PIXELS);
		if (sepSize > 0) {
			UISeparator sep = new UISeparator(sepSize);
			sep.setFg_color(sepColor);
			ivl.insert(sep, 1, sepSize, UILayout.CONSTRAINT_PIXELS);
		}
		UILabel menuImage = new UILabel(closeImage, "");
		ihl.insert(menuImage, 0,
				closeImage != null ? closeImage.getWidth() : 0,
				UILayout.CONSTRAINT_PIXELS);
		item.setFocusable(true);
		ihl.insert(item, 1, 100, UILayout.CONSTRAINT_PERCENTUAL);
		setItemsColor(menuImage, item);
		return ivl;
	}

	private void setItemsColor(UIItem menuImage, UIItem item) {
		if (labelColor > 0) {
			menuImage.setBg_color(labelColor);
			item.setBg_color(labelColor);
		}
		if (labelGradientColor > 0) {
			menuImage.setGradientColor(labelGradientColor);
			item.setGradientColor(labelGradientColor);
		}
		if (labelSelectedColor > 0) {
			menuImage.setSelectedColor(labelSelectedColor);
			item.setSelectedColor(labelSelectedColor);
		}
		if (labelGradientSelectedColor > 0) {
			menuImage.setGradientSelectedColor(labelGradientSelectedColor);
			item.setGradientSelectedColor(labelGradientSelectedColor);
		}
	}

	public UIItem getSelectedItem() {
		// If I am inside an UIMenu i will not forward the key press to the 
		// selected item i will keep it for myself in order to open labels
		UIItem superItem = super.getSelectedItem();
		if (this.getContainer() instanceof UIMenu
				&& this.getContainer() instanceof UIScreen == false) {
			if (this.accordionItems.containsKey(superItem)) return this;
		}
		return superItem;
	}

	public boolean keyPressed(int key) {
		int ga = UICanvas.getInstance().getGameAction(key);
		if (ga == Canvas.FIRE) {
			UIItem selItem = super.getSelectedItem();
			// it is an item to (un)expand
			if (this.accordionItems.containsKey(selItem)) {
				if (this.selectedIdx >= 0
						&& selectedIdx < this.getItems().size()
						&& ((UIItem) this.getItems().elementAt(selectedIdx))
								.keyPressed(key) == false) {
					// this is needed since we cannot know if anything below has been
					// repainted
					clickLabel(selItem);
					this.askRepaint();
					return true;
				}
				return true;
			} else {
				return false;
			}
		}
		boolean keepFocus = super.keyPressed(key);
		//              if (keepFocus == false && this.openedItem != null)
		//                      this.setSelectedItem((UIItem) this.openedItem.getContainer());
		return keepFocus;
	}

	public void clickLabel(UIItem selItem) {
		boolean needOpen = (selItem != openedItem);
		// if it closed open it otherwise close it
		if (this.openedItem != null && (needOpen || oneOpen == false)) {
			close(openedItem);
		}
		if (needOpen) {
			open(selItem);
		}
	}

	public void openLabel(UIItem selItem) {
		boolean needOpen = (selItem != openedItem);
		if (needOpen == false) return;
		clickLabel(selItem);
	}

	public void closeLabel(UIItem selItem) {
		boolean needClose = (selItem == openedItem);
		if (needClose == false) return;
		clickLabel(selItem);
	}

	public UIItem getOpenedLabel() {
		return openedItem;
	}

	private void close(UIItem openedItem) {
		AccordionItem ai = (AccordionItem) this.accordionItems.get(openedItem);
		if (ai == null) return;
		Vector oldPanel = ai.subPanel;
		this.setSelectedIndex(-1);
		int removeIndex = this.getItems().indexOf(oldPanel);
		this.getItems().removeElement(oldPanel);

		// must set dirty the items that are moved
		invalidateItems(removeIndex);

		ai.getImgLabel().setImg(this.closeImage);
		this.setSelectedItem(ai.wrappedItem);
		this.openedItem = null;
		// so that the panel is "entirely redrawn" if needed
		this.setFirstVisible(0);
	}

	private void invalidateItems(int removeIndex) {
		this.dirty = true;
		Enumeration en = this.getItems().elements();
		int count = 0;
		while (en.hasMoreElements()) {
			UIItem ithItem = (UIItem) en.nextElement();
			if (count >= removeIndex) {
				ithItem.setDirty(true);
			}
			count++;
		}
	}

	private void open(UIItem itemToOpen) {
		AccordionItem ai = (AccordionItem) this.accordionItems.get(itemToOpen);
		Vector newPanel = ai.subPanel;
		if (newPanel.size() > 0) {
			this.openedItem = itemToOpen;
			this.setSelectedIndex(-1);
			ai.getImgLabel().setImg(this.openImage);
			int insertIndex = this.getItems().indexOf(ai.wrappedItem) + 1;

			// must set dirty the items that are moved
			this.getItems().insertElementAt(newPanel, insertIndex);
			invalidateItems(insertIndex);
		}
		// even if the label is not opened i select it
		this.setSelectedItem(ai.wrappedItem);
	}

	public void setBg_color(int bg_color) {
		Enumeration en = this.accordionItems.elements();
		while (en.hasMoreElements()) {
			AccordionItem ithItem = (AccordionItem) en.nextElement();
			Enumeration en2 = ithItem.subPanel.elements();
			while (en2.hasMoreElements()) {
				UIItem ithUIItem = (UIItem) en2.nextElement();
				ithUIItem.setBg_color(bg_color);
			}
		}
	}

	public void setSepColor(int sepColor) {
		this.sepColor = sepColor;
	}

	public int getSepColor() {
		return sepColor;
	}

	public void setSepSize(int sepSize) {
		this.sepSize = sepSize;
	}

	public int getSepSize() {
		return sepSize;
	}

	public UIItem[] getItemLabels() {
		UIItem[] labels = new UIItem[this.itemLabels.size()];
		this.itemLabels.copyInto(labels);
		return labels;
	}

	public Enumeration getSubPanelElements(UIItem item) {
		// TODO Auto-generated method stub
		Vector v = getSubpanel(item);
		if (v == null) return null;
		return ((Vector) v).elements();
	}

	/**
	 * @param oneOpen the oneOpen to set
	 */
	public void setOneOpen(boolean oneOpen) {
		this.oneOpen = oneOpen;
	}

	/**
	 * @return the oneOpen
	 */
	public boolean isOneOpen() {
		return oneOpen;
	}

	public void clearPanel(UILabel label) {
		if (openedItem == label) {
			this.getSelectedItem().setSelected(false);
			this.close(label);
		}
		Vector v = this.getSubpanel(label);
		if (this.getScreen() != null) {
			UIScreen cs = this.getScreen();
			Enumeration en = v.elements();
			while (en.hasMoreElements()) {
				Object object = (Object) en.nextElement();
				cs.removePaintedItem((UIItem) object);
			}
		}
		v.removeAllElements();
	}

	public void move(int firstIndex, int secondIndex) {
		try {
			UIItem oldSelectedItem = selectedIdx >= 0 ? (UIItem) this
					.getItems().elementAt(selectedIdx) : null;
			oldSelectedItem.setSelected(false);
			UIItem oldOpenedItem = openedItem;
			if (openedItem != null) {
				this.close(openedItem);
			}
			UIItem firstItem = (UIItem) this.itemLabels.elementAt(firstIndex);
			this.itemLabels.removeElement(firstItem);
			this.itemLabels.insertElementAt(firstItem, secondIndex);

			//UIItem secondItem = (UIItem) this.getItems().elementAt(secondIndex);
			firstItem = (UIItem) this.getItems().elementAt(firstIndex);
			this.getItems().removeElement(firstItem);
			this.getItems().insertElementAt(firstItem, secondIndex);

			if (oldOpenedItem != null) {
				this.open(oldOpenedItem);
			}
			this.setSelectedItem(firstItem);
		} catch (Exception e) {
			// #mdebug 
//@									System.out.println("in swapping elements");
//@									e.printStackTrace();
			// #enddebug
		}
	}

	public void swap(int firstIndex, int secondIndex) {
		try {
			if (firstIndex > secondIndex) {
				int tempIndex = firstIndex;
				firstIndex = secondIndex;
				secondIndex = tempIndex;
			}
			UIItem oldSelectedItem = selectedIdx >= 0 ? (UIItem) this
					.getItems().elementAt(selectedIdx) : null;
			UIItem oldOpenedItem = openedItem;
			if (openedItem != null) {
				this.close(openedItem);
			}
			UIItem secondItem = (UIItem) this.itemLabels.elementAt(secondIndex);
			UIItem firstItem = (UIItem) this.itemLabels.elementAt(firstIndex);
			this.itemLabels.removeElement(secondItem);
			this.itemLabels.insertElementAt(secondItem, firstIndex);
			this.itemLabels.removeElement(firstItem);
			this.itemLabels.insertElementAt(firstItem, secondIndex);

			secondItem = (UIItem) this.getItems().elementAt(secondIndex);
			firstItem = (UIItem) this.getItems().elementAt(firstIndex);
			this.getItems().removeElement(secondItem);
			this.getItems().insertElementAt(secondItem, firstIndex);
			this.getItems().removeElement(firstItem);
			this.getItems().insertElementAt(firstItem, secondIndex);

			if (oldOpenedItem != null) {
				this.open(oldOpenedItem);
			}
			if ((oldSelectedItem == firstItem || oldSelectedItem == secondItem)
					&& this.getItems().contains(oldSelectedItem)) this
					.setSelectedItem(oldSelectedItem);
		} catch (Exception e) {
			// #mdebug 
//@									System.out.println("in swapping elements");
//@									e.printStackTrace();
			// #enddebug
		}
	}

	public int getLabelGradientColor() {
		return labelGradientColor;
	}

	public int getLabelColor() {
		return labelColor;
	}

	public void setLabelColor(int labelColor) {
		this.labelColor = labelColor;
		updateItemsColor();
	}

	public void setLabelGradientColor(int labelGradientColor) {
		this.labelGradientColor = labelGradientColor;
		updateItemsColor();
	}

	public void setLabelGradientSelectedColor(int labelGradientSelectedColor) {
		this.labelGradientSelectedColor = labelGradientSelectedColor;
		updateItemsColor();
	}

	public void setLabelSelectedColor(int labelSelectedColor) {
		this.labelSelectedColor = labelSelectedColor;
		updateItemsColor();
	}

	private void updateItemsColor() {
		Enumeration en = accordionItems.elements();
		while (en.hasMoreElements()) {
			AccordionItem ithItem = (AccordionItem) en.nextElement();
			UIVLayout ivl = ithItem.wrappedItem;
			UIHLayout ihl = (UIHLayout) ivl.layoutItems[0];
			setItemsColor(ihl.layoutItems[0], ihl.layoutItems[1]);
		}
	}
}
