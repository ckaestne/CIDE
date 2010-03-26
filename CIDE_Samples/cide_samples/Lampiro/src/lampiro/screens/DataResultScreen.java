/* Copyright (c) 2008 Bluendo S.r.L.
 * See about.html for details about license.
 *
 * $Id: DataResultScreen.java 1545 2009-05-27 07:33:17Z luca $
*/

/**
 * 
 */
package lampiro.screens;

import it.yup.ui.UICanvas;
import it.yup.ui.UIHLayout;
import it.yup.ui.UIItem;
import it.yup.ui.UILabel;
import it.yup.ui.UILayout;
import it.yup.ui.UIMenu;
import it.yup.ui.UIPanel;
import it.yup.ui.UIScreen;
import it.yup.ui.UISeparator;
import it.yup.ui.UITextField;
import it.yup.ui.UIUtils;
import it.yup.util.ResourceIDs;
import it.yup.util.ResourceManager;
import it.yup.xmpp.DataFormListener;
import it.yup.xmpp.packets.DataForm;

import java.util.Hashtable;

import javax.microedition.lcdui.Canvas;
import javax.microedition.lcdui.TextField;


/**
 * Class that shows the results in a data form.
 */
public class DataResultScreen extends UIScreen {

	private static ResourceManager rm = ResourceManager.getManager("common",
			"en");

	private UILabel cmd_close = new UILabel(rm.getString(ResourceIDs.STR_CLOSE)
			.toUpperCase());
	// these are for <reported/> items
	private UILabel cmd_prev = new UILabel(rm.getString(ResourceIDs.STR_PREV));
	private UILabel cmd_next = new UILabel(rm.getString(ResourceIDs.STR_NEXT));

	/** The result to show. The value -1 means possible instructions */
	private int pos = 0;

	private UILabel si_instructions = new UILabel("");

	/** the data form */
	private DataForm df;

	private DataFormListener listener;

	private UIHLayout mainLayout = new UIHLayout(3);
	private UIPanel mainPanel = new UIPanel();

	private UIMenu desc_menu = new UIMenu("");

	private UILabel show_desc_label = new UILabel(rm.getString(
			ResourceIDs.STR_DESC).toUpperCase());

	public DataResultScreen(DataForm df, DataFormListener listener) {
		setTitle(df.title != null ? df.title : "");

		this.df = df;
		this.listener = listener;

		if (df.instructions != null) {
			si_instructions.setWrappable(true, 100);
			si_instructions.setText(df.instructions);
			pos = -1;
		}

		UISeparator separator = new UISeparator(0);
		mainLayout.setGroup(false);
		mainLayout.insert(separator, 0, 3, UILayout.CONSTRAINT_PIXELS);
		mainLayout.insert(mainPanel, 1, 100, UILayout.CONSTRAINT_PERCENTUAL);
		mainLayout.insert(separator, 2, 3, UILayout.CONSTRAINT_PIXELS);
		this.append(mainLayout);
		mainPanel.setMaxHeight(-1);
		
		desc_menu.append(show_desc_label);

		setMenu(new UIMenu(""));
		UIMenu menu = getMenu();
		menu.append(cmd_close);

		this.setFreezed(true);
		showCurrent();
		this.setFreezed(false);
		this.setDirty(true);
		this.askRepaint();
	}

	/**
	 * shows the currently selected result.
	 * 
	 */
	private void showCurrent() {

		if (df.results.size() <= pos) {
			/* XXX ??? error ??? */
			return;
		}

		mainPanel.removeAllItems();

		if (pos == -1) {
			mainPanel.addItem(new UILabel(rm.getString(ResourceIDs.STR_INSTRUCTIONS)));
			mainPanel.addItem(si_instructions);
		} else {
			Hashtable res = (Hashtable) df.results.elementAt(pos);

			for (int i = 0; i < df.fields.size(); i++) {
				DataForm.Field fld = (DataForm.Field) df.fields.elementAt(i);

				String val = (String) res.get(fld.varName);
				if (val == null) {
					/* ??? error */
					val = "";
				}
				String lbl = (fld.label != null ? fld.label : fld.varName);

				if (lbl.indexOf("fixed_") >= 0) {
					lbl = "";
				}

				UIItem ithItem = null;
				if (fld.type == DataForm.FLT_TXTMULTI
						|| fld.type == DataForm.FLT_FIXED
						|| fld.type == DataForm.FLT_TXTSINGLE) {
					UITextField uit = new UITextField(lbl, val, 1024,
							TextField.UNEDITABLE);
					mainPanel.addItem(uit);
					// must be done after append to have a screen for the uit
					uit.setWrappable(true);
					ithItem = uit;
					//uit.setMaxHeight(50);
				} else {
					if (lbl.length() > 0) append(new UILabel(lbl));
					ithItem = new UILabel(val);
					mainPanel.addItem(ithItem);
				}
				if (fld.desc != null) {
					ithItem.setSubmenu(desc_menu);
				}
			}
		}
		UIMenu menu = getMenu();
		menu.remove(cmd_prev);
		menu.remove(cmd_next);

		int start = df.instructions == null ? 0 : -1;
		if (pos > start) {
			menu.insert(1, cmd_prev);
		}
		if (pos < df.results.size() - 1) {
			menu.insert(1, cmd_next);
		}

	}

	public boolean keyPressed(int kc) {
		if (super.keyPressed(kc)) return true;

		if (this.popupList.size() == 0
				&& this.getMenu().isOpenedState() == false) {
			int ga = UICanvas.getInstance().getGameAction(kc);

			switch (ga) {
				case Canvas.RIGHT: {
					RosterScreen.showNextScreen(this);
					return true;
				}
				case Canvas.LEFT: {
					RosterScreen.showPreviousScreen(this);
					return true;
				}
			}
		}
		return false;
	}

	public void menuAction(UIMenu menu, UIItem cmd) {
		if (cmd == cmd_close) {
			listener.execute(DataFormListener.CMD_DESTROY);
			// #ifdef UI
			UICanvas.getInstance().close(this);
			// #endif
			return;
		} else if (cmd == cmd_next) {
			pos++;
		} else if (cmd == cmd_prev) {
			pos--;
		} else if (cmd == show_desc_label) {
			int index = this.getSelectedIndex();
			String desc = ((DataForm.Field) this.df.fields.elementAt(index)).desc;
			UITextField descField = new UITextField("", desc, desc.length(),
					TextField.UNEDITABLE);
			descField.setWrappable(true);
			UIMenu descriptionMenu = UIUtils.easyMenu(rm
					.getString(ResourceIDs.STR_DESC), 10, 20, this.getWidth() - 20, descField);
			//descPanel.setMaxHeight(UICanvas.getInstance().getClipHeight() / 2);
			descriptionMenu.cancelMenuString = "";
			descriptionMenu.selectMenuString = rm.getString(
					ResourceIDs.STR_CLOSE).toUpperCase();
			descriptionMenu.setSelectedIndex(1);
			this.addPopup(descriptionMenu);
			descField.expand();
		}

		showCurrent();
	}

}
