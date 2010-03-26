/* Copyright (c) 2008 Bluendo S.r.L.
 * See about.html for details about license.
 *
 * $Id: DataFormScreen.java 1578 2009-06-16 11:07:59Z luca $
*/

/**
 * 
 */
package lampiro.screens;

import it.yup.ui.UIButton;
import it.yup.ui.UICanvas;
import it.yup.ui.UICheckbox;
import it.yup.ui.UICombobox;
import it.yup.ui.UIGauge;
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

import javax.microedition.lcdui.Canvas;
import javax.microedition.lcdui.Gauge;
import javax.microedition.lcdui.Graphics;
import javax.microedition.lcdui.TextField;

import lampiro.screens.RosterScreen.WaitScreen;

/**
 * <p>
 * This class handles the data form input from the user, rendering a given Data
 * Form using the base controls offered by J2ME UI. The input result is then
 * sent to a DataFormListener that handles the form outcome.
 * </p>
 * 
 * <p>
 * DataForms are rendered as follows.
 * <ul>
 * <li><b>hidden</b>: are skipped.</li>
 * <li><b>boolean</b>: are rendered with a ChoiceGroup flagged with "MULTIPLE"
 * and with a single choice item that may be checked (true) or unchecked
 * (false).</li>
 * <li><b>list-multi and list-single</b>: they show a button that opens a
 * separate List, List is "EXCLUSIVE" (a single voice can be selected) or
 * "MULTIPLE" (more than one voice selected) resp. for list-single and
 * list-multi.</li>
 * <li><b>jid-single</b>, <b>jid-multi</b>, <b>text-single</b>,
 * <b>text-multi</b>, <b>text-private</b>, <b>fixed</b>: are shown as a
 * single TextField, *-multi are split on '\n' chars when sending data;
 * text-private are flagged as PASSWORD fields, jid-single are flagged as
 * EMAILADDRESS fields. fixed are uneditable.</li>
 * </ul>
 * 
 * All fields have a Label before if the DataForm field has one. Commands are
 * placed on the menu. Instructions, if present, make a command "instructions"
 * appear on the menu and that voice pops up an alert showing the instructions.
 * Field desc are ignored.
 * 
 * At the bottom of the forms the button for the available actions are placed.
 * Available actions are passed via the method setActions()
 * 
 * <i>TODO LIST:
 * <ul>
 * <li>list-single and list-multi should be changed: they should show a non
 * editable control that exposes the label and the current selected content plus
 * a button that spawns the pop-up screen for the selection</li>
 * <li>text-multi and jid-multi should open a separate TextBox item (note. on
 * SonyEricsson it seems that there's no difference between the two...).</li>
 * <li>jid-multi should be checked for emailaddress format (emailaddress is not
 * enforceable for multiline TextBoxes</li>
 * <li>check '\n' in fields that are not *-multi and pop up error.</li>
 * <li>add a voice "field description" on the menu (or place a button with (?))
 * to honour the "desc" for each field.</li>
 * <li>Heuristics: when a form has a single list-* item or the list-* item has
 * no more than 2 or 3 options, there shouldn't be a need for a pop up screen.
 * </ul>
 * </i>
 * 
 */
public class DataFormScreen extends UIScreen implements WaitScreen {

	private static ResourceManager rm = ResourceManager.getManager("common",
			"en");

	/** The handled data form */
	private DataForm df;

	/** the listener to be notified of commands */
	private DataFormListener dfl;

	/** the available actions */
	private int actions;

	private UILabel cmd_submit = new UIButton(rm
			.getString(ResourceIDs.STR_SUBMIT));
	private UILabel cmd_cancel = new UIButton(rm
			.getString(ResourceIDs.STR_CANCEL));
	private UILabel menu_cancel = new UILabel(rm.getString(
			ResourceIDs.STR_CLOSE).toUpperCase());
	private UILabel cmd_prev = new UIButton(rm.getString(ResourceIDs.STR_PREV));
	private UILabel cmd_next = new UIButton(rm.getString(ResourceIDs.STR_NEXT));
	private UILabel cmd_delay = new UILabel(rm
			.getString(ResourceIDs.STR_FILLLATER));

	private UIMenu show_instruction;
	private UILabel show_instruction_label = new UILabel(rm.getString(
			ResourceIDs.STR_INSTRUCTIONS).toUpperCase());

	private UILabel show_desc_label = new UILabel(rm.getString(
			ResourceIDs.STR_DESC).toUpperCase());

	private UIMenu instruction_menu = null;
	private UIMenu desc_menu = new UIMenu("");
	private UILabel si_instructions = new UILabel("");

	/** the item array created to represent the form */
	private UIItem[] items;

	UIGauge progress_gauge = new UIGauge(rm.getString(ResourceIDs.STR_WAIT),
			false, Gauge.INDEFINITE, Gauge.CONTINUOUS_RUNNING);

	/*
	 * To construct the "Expand" support
	 */
	UIMenu zoomSubmenu;
	UILabel zoomLabel = new UILabel("EXPAND");

	private UIHLayout mainLayout = new UIHLayout(3);
	private UIPanel mainPanel = new UIPanel(true, false);

	public DataFormScreen(DataForm df, DataFormListener dfl) {
		setTitle(rm.getString(ResourceIDs.STR_FILL_FORM));

		UISeparator separator = new UISeparator(0);
		mainLayout.setGroup(false);
		mainLayout.insert(separator, 0, 3, UILayout.CONSTRAINT_PIXELS);
		mainLayout.insert(mainPanel, 1, 100, UILayout.CONSTRAINT_PERCENTUAL);
		mainLayout.insert(separator, 2, 3, UILayout.CONSTRAINT_PIXELS);
		this.append(mainLayout);

		this.df = df;
		this.dfl = dfl;

		if (df.title != null) {
			setTitle(df.title);
		}

		setMenu(new UIMenu(""));
		UIMenu menu = getMenu();
		menu.append(menu_cancel);
		//menu.append(cmd_delay);
		actions = DataFormListener.CMD_SUBMIT | DataFormListener.CMD_CANCEL;
		instruction_menu = UIUtils.easyMenu(rm
				.getString(ResourceIDs.STR_INSTRUCTIONS), 10, 20, this
				.getWidth() - 10, null);
		//		desc_menu.setAbsoluteX(10);
		//		desc_menu.setAbsoluteY(20);
		//		desc_menu.setWidth(this.getWidth() - 10);
		desc_menu.append(show_desc_label);
		show_instruction = UIUtils.easyMenu("", 10, 20, this.getWidth() - 10,
				show_instruction_label);
		// prepare zoomSubMenu
		zoomSubmenu = UIUtils.easyMenu("", 10, 10, this.getWidth() - 30,
				zoomLabel);
		zoomLabel.setAnchorPoint(Graphics.HCENTER);
		createControls();
	}

	/**
	 * Sets the available command buttons. Actions should be one of the CMD_*
	 * flags defined in the DataFormListener interface.
	 * 
	 * @param cmds
	 */
	public void setActions(int _ac) {
		/* submit and cancel are always shown */
		actions = _ac | DataFormListener.CMD_SUBMIT
				| DataFormListener.CMD_CANCEL;
		createControls();
	}

	/**
	 * Show the form, dynamically adding all the controls
	 */
	private void createControls() {

		// as always: many operations on the gui need a freeze since
		// i love the battery life
		this.setFreezed(true);

		this.mainPanel.removeAllItems();

		/* do I create this only once? */
		items = new UIItem[df.fields.size()];

		for (int i = 0; i < df.fields.size(); i++) {
			DataForm.Field fld = (DataForm.Field) df.fields.elementAt(i);

			if (fld.type == DataForm.FLT_HIDDEN) {
				continue;
			}

			if (fld.type == DataForm.FLT_BOOLEAN) {
				// XXX: check how to render this
				String fldName = (fld.label == null ? fld.varName : fld.label);
				UICheckbox cgrp = new UICheckbox(fldName);
				if ("1".equals(fld.dValue) || "true".equals(fld.dValue)) {
					cgrp.setChecked(true);
				} else {
					cgrp.setChecked(false);
				}
				mainPanel.addItem(cgrp);
				items[i] = cgrp;
				continue;
			}

			if (fld.type == DataForm.FLT_LISTMULTI
					|| fld.type == DataForm.FLT_LISTSINGLE) {
				String title = (fld.label == null ? fld.varName : fld.label);
				UICombobox cgrp = new UICombobox(title,
						(fld.type == DataForm.FLT_LISTMULTI));
				boolean[] flags = new boolean[fld.options.size()];
				for (int j = 0; j < fld.options.size(); j++) {
					String[] opt = (String[]) fld.options.elementAt(j);
					cgrp.append(opt[1]);
					if (fld.dValue != null && fld.dValue.indexOf(opt[0]) != -1) {
						flags[j] = true;
					} else {
						flags[j] = false;
					}
				}
				cgrp.setSelectedFlags(flags);
				mainPanel.addItem(cgrp);
				items[i] = cgrp;
				continue;
			}

			if (fld.type == DataForm.FLT_JIDSINGLE
					|| fld.type == DataForm.FLT_TXTPRIV
					|| fld.type == DataForm.FLT_TXTSINGLE
					|| fld.type == DataForm.FLT_JIDMULTI
					|| fld.type == DataForm.FLT_TXTMULTI
					|| fld.type == DataForm.FLT_FIXED) {
				String title = (fld.label == null ? ""/* fld.varName */
				: fld.label);
				int flags = TextField.ANY;
				if (fld.type == DataForm.FLT_TXTPRIV) {
					flags |= TextField.PASSWORD;
				}
				if (fld.type == DataForm.FLT_JIDSINGLE) {
					flags |= TextField.EMAILADDR;
				}
				if (fld.type == DataForm.FLT_FIXED) {
					flags |= TextField.UNEDITABLE;
				}
				// XXX: Which the maximum allowed length? We use 1k for the
				// moment
				UITextField tf = new UITextField(title, fld.dValue, 1024, flags);
				mainPanel.addItem(tf);
				if (fld.type == DataForm.FLT_TXTMULTI
						|| fld.type == DataForm.FLT_FIXED) {
					if (fld.type == DataForm.FLT_TXTMULTI) {
						tf.setMinLines(4);
					}
					tf.setWrappable(true);
				}
				items[i] = tf;
				continue;
			}
		}

		if (df.instructions != null) {
			for (int i = 0; i < items.length; i++) {
				if (items[i] != null) {
					items[i].setSubmenu(show_instruction);
				}
			}
			si_instructions.setText(df.instructions);
			if (instruction_menu.getItemList().contains(si_instructions) == false) instruction_menu
					.append(si_instructions);
			si_instructions
					.setWrappable(true, instruction_menu.getWidth() - 10);
		}

		// add the desc
		this.addDesc();

		/*
		 * Spacer sp = new Spacer(10, 5); sp.setLayout(Item.LAYOUT_NEWLINE_AFTER |
		 * Item.LAYOUT_NEWLINE_BEFORE); append(sp);
		 */

		/* Buttons: should be placed in-line */
		/* show actions. order is CANCEL, [PREV], [NEXT], SUBMIT */

		UILabel dummyLabel = new UILabel("");
		UIItem insertItem = null;
		UIHLayout uhl1 = new UIHLayout(2);
		UIHLayout uhl2 = new UIHLayout(2);
		boolean addUhl1 = false;
		boolean addUhl2 = false;

		uhl1.setGroup(false);
		uhl2.setGroup(false);

		if ((actions & DataFormListener.CMD_CANCEL) != 0) {
			insertItem = cmd_cancel;
			addUhl2 = true;
			if (df.instructions != null) {
				insertItem.setSubmenu(show_instruction);
			}
		} else {
			insertItem = dummyLabel;
		}
		uhl2.insert(insertItem, 0, 50, UILayout.CONSTRAINT_PERCENTUAL);

		if ((actions & DataFormListener.CMD_PREV) != 0) {
			insertItem = cmd_prev;
			addUhl1 = true;
			if (df.instructions != null) {
				insertItem.setSubmenu(show_instruction);
			}
		} else {
			insertItem = dummyLabel;
		}
		uhl1.insert(insertItem, 0, 50, UILayout.CONSTRAINT_PERCENTUAL);

		if ((actions & DataFormListener.CMD_NEXT) != 0) {
			insertItem = cmd_next;
			addUhl1 = true;
			if (df.instructions != null) {
				insertItem.setSubmenu(show_instruction);
			}
		} else {
			insertItem = dummyLabel;
		}
		uhl1.insert(insertItem, 1, 50, UILayout.CONSTRAINT_PERCENTUAL);

		if ((actions & DataFormListener.CMD_SUBMIT) != 0) {
			insertItem = cmd_submit;
			addUhl2 = true;
			if (df.instructions != null) {
				insertItem.setSubmenu(show_instruction);
			}
		} else {
			insertItem = dummyLabel;
		}
		uhl2.insert(insertItem, 1, 50, UILayout.CONSTRAINT_PERCENTUAL);

		if (addUhl1) mainPanel.addItem(uhl1);
		if (addUhl2) mainPanel.addItem(uhl2);

		this.setSelectedIndex(0);
		this.setFreezed(false);
		this.askRepaint();

	}

	private void addDesc() {
		for (int i = 0; i < df.fields.size(); i++) {
			DataForm.Field fld = (DataForm.Field) df.fields.elementAt(i);
			if (fld.desc != null) {
				items[i].setSubmenu(desc_menu);
			}
		}
	}

	protected void paint(Graphics g, int w, int h) {
		super.paint(g, w, h);

		// longest textfield handling 
		UIItem panelItem = mainPanel.getSelectedItem();
		if (panelItem instanceof UITextField) {
			Graphics tg = getGraphics();
			int labelHeight = panelItem.getHeight(tg);
			int availableHeight = UICanvas.getInstance().getClipHeight()
					- this.headerLayout.getHeight(tg)
					- this.footer.getHeight(tg);
			UIMenu itemSubMenu = panelItem.getSubmenu();
			if (labelHeight > availableHeight
					&& (itemSubMenu == null || itemSubMenu != zoomSubmenu)) {
				panelItem.setSubmenu(zoomSubmenu);
				// always reset these values when asking a "repaint" within a "paint"
				UICanvas ci = UICanvas.getInstance();
				g.translate(-g.getTranslateX(), -g.getTranslateY());
				g.setClip(0, 0, ci.getWidth(), ci.getHeight() + 1);
				this.askRepaint();
			}
		}
	}

	/**
	 * Command handler
	 */
	public void menuAction(UIMenu menu, UIItem cmd) {

		int comm = -1;
		boolean setWaiting = false;

		if (cmd == cmd_cancel || cmd == menu_cancel) {
			comm = DataFormListener.CMD_CANCEL;
		} else if (cmd == cmd_submit) {
			comm = DataFormListener.CMD_SUBMIT;
			setWaiting = true;
		} else if (cmd == cmd_next) {
			comm = DataFormListener.CMD_NEXT;
			setWaiting = true;
		} else if (cmd == cmd_prev) {
			comm = DataFormListener.CMD_PREV;
			setWaiting = true;
		} else if (cmd == cmd_delay) {
			comm = DataFormListener.CMD_DELAY;
			setWaiting = true;
		} else if (cmd == this.zoomLabel) {
			UITextField selLabel = (UITextField) this.getSelectedItem();
			selLabel.handleScreen();
		} else if (cmd == show_desc_label) {
			int index = this.getSelectedIndex();
			String desc = ((DataForm.Field) this.df.fields.elementAt(index)).desc;
			UITextField descField = new UITextField("", desc, desc.length(),
					TextField.UNEDITABLE);
			descField.setWrappable(true);
			UIMenu descriptionMenu = UIUtils.easyMenu(rm
					.getString(ResourceIDs.STR_DESC), 10, 20,
					this.getWidth() - 20, descField);
			//descPanel.setMaxHeight(UICanvas.getInstance().getClipHeight() / 2);

			descriptionMenu.cancelMenuString = "";
			descriptionMenu.selectMenuString = rm.getString(
					ResourceIDs.STR_CLOSE).toUpperCase();
			descriptionMenu.setSelectedIndex(1);
			this.addPopup(descriptionMenu);
			descField.expand();
		} else if (cmd == show_instruction_label) {
			/* show/hide instructions */
			this.addPopup(this.instruction_menu);
			return;
		}

		if (comm == -1) {
			/* ???? */
			return;
		}

		fillForm();
		// if the dataform will have an answer, e.g. an IQ contained dataform 
		setWaiting &= dfl.execute(comm);
		// #ifdef UI
		if (setWaiting == true) {
			mainPanel.removeAllItems();
			mainPanel.addItem(progress_gauge);
			progress_gauge.start();
			RosterScreen.getInstance().setWaitingDF(this);
			this.askRepaint();
		} else {
			this.stopWaiting();
		}
		// #endif
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

	/**
	 * Command handler for on-screen buttons
	 */
	public void itemAction(UIItem cmd) {
		menuAction(null, cmd);
	}

	/**
	 * Called when submit is pressed
	 */
	private void fillForm() {

		// XXX: here we could verify the required fields
		for (int i = 0; i < df.fields.size(); i++) {
			DataForm.Field fld = (DataForm.Field) df.fields.elementAt(i);
			if (fld.type == DataForm.FLT_HIDDEN) {
				continue;
			}

			if (fld.type == DataForm.FLT_BOOLEAN) {
				UICheckbox cgrp = (UICheckbox) items[i];
				fld.dValue = (cgrp.isChecked() ? "true" : "false");
				continue;
			}

			if (fld.type == DataForm.FLT_LISTMULTI
					|| fld.type == DataForm.FLT_LISTSINGLE) {
				UICombobox cmb = (UICombobox) items[i];
				boolean[] flags = cmb.getSelectedFlags();
				StringBuffer dtext = new StringBuffer();
				int scount = 0;
				for (int j = 0; j < flags.length; j++) {
					if (flags[j]) {
						scount++;
						String[] opt = (String[]) fld.options.elementAt(j);
						if (scount > 1) {
							dtext.append("\n");
						}
						dtext.append(opt[0]);
					}
				}
				if (scount == 0) {
					fld.dValue = "";
				} else {
					fld.dValue = dtext.toString();
				}
				continue;
			}

			if (fld.type == DataForm.FLT_JIDSINGLE
					|| fld.type == DataForm.FLT_TXTPRIV
					|| fld.type == DataForm.FLT_TXTSINGLE
					|| fld.type == DataForm.FLT_JIDMULTI
					|| fld.type == DataForm.FLT_TXTMULTI
					|| fld.type == DataForm.FLT_FIXED) {
				UITextField tf = (UITextField) items[i];
				fld.dValue = tf.getText();
				continue;
			}
		}

	}

	public void stopWaiting() {
		progress_gauge.cancel();
		UICanvas.getInstance().close(this);
	}
}
