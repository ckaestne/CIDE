/* Copyright (c) 2008 Bluendo S.r.L.
 * See about.html for details about license.
 *
 * $Id: DataFormScreen.java 1273 2009-03-13 15:57:53Z luca $
*/

/**
 * 
 */
package it.yup.screens;

import lampiro.LampiroMidlet;
import it.yup.util.ResourceIDs;
import it.yup.util.ResourceManager;
import it.yup.xmpp.DataFormListener;
import it.yup.xmpp.packets.DataForm;
import it.yup.xmpp.packets.DataForm.Field;

import javax.microedition.lcdui.Choice;
import javax.microedition.lcdui.ChoiceGroup;
import javax.microedition.lcdui.Command;
import javax.microedition.lcdui.CommandListener;
import javax.microedition.lcdui.Displayable;
import javax.microedition.lcdui.Form;
import javax.microedition.lcdui.Item;
import javax.microedition.lcdui.ItemCommandListener;
import javax.microedition.lcdui.ItemStateListener;
import javax.microedition.lcdui.List;
import javax.microedition.lcdui.Spacer;
import javax.microedition.lcdui.StringItem;
import javax.microedition.lcdui.TextField;

/**
 * <p>This class handles the data form input from the user, rendering a given Data 
 * Form using the base controls offered by J2ME UI.
 * The input result is then sent to a DataFormListener that handles the form 
 * outcome.</p>
 * 
 * <p>DataForms are rendered as follows.
 * <ul>
 * <li><b>hidden</b>: are skipped.</li>
 * <li><b>boolean</b>: are rendered with a ChoiceGroup flagged with "MULTIPLE" 
 * 		and with a single choice item that may be checked (true) or 
 * 		unchecked (false).</li>
 * <li><b>list-multi and list-single</b>: they show a button that opens a separate 
 * 		List, List is "EXCLUSIVE" (a single voice can be selected) or "MULTIPLE" 
 * 		(more than one voice selected) resp. for list-single and list-multi.</li>
 * <li><b>jid-single</b>, <b>jid-multi</b>, <b>text-single</b>, <b>text-multi</b>,
 * 		<b>text-private</b>, <b>fixed</b>: are shown as a single TextField,
 * 		*-multi are split on '\n' chars when sending data; text-private are 
 * 		flagged as PASSWORD fields, jid-single are flagged as EMAILADDRESS
 * 		fields. fixed are uneditable.</li>
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
 * editable control that exposes the label and the current selected content
 * plus a button that spawns the pop-up screen for the selection</li>
 * <li>text-multi and jid-multi should open a separate TextBox item (note. on 
 * SonyEricsson it seems that there's no difference between the two...).</li>
 * <li>jid-multi should be checked for emailaddress format (emailaddress is not 
 * enforceable for multiline TextBoxes</li>
 * <li>check '\n' in fields that are not *-multi and pop up error.</li>
 * <li>add a voice "field description" on the menu (or place a button with (?))
 * to honour the "desc" for each field.</li>
 * <li>Heuristics: when a form has a single list-* item or the list-* item has no 
 * more than 2 or 3 options, there shouldn't be a need for a pop up screen.
 * </ul>
 * </i> 
 * 
 */
public class DataFormScreen extends Form implements CommandListener,
		ItemCommandListener, ItemStateListener {

	private static ResourceManager rm = ResourceManager.getManager("common",
			"en");

	/** The handled data form */
	private DataForm df;

	/** the listener to be notified of commands */
	private DataFormListener dfl;

	/** the available actions */
	private int actions;

	private Command cmd_submit = new Command(rm
			.getString(ResourceIDs.STR_SUBMIT), Command.ITEM, 1);
	private Command cmd_cancel = new Command(rm
			.getString(ResourceIDs.STR_CANCEL), Command.EXIT, 2);
	private Command cmd_prev = new Command(rm.getString(ResourceIDs.STR_PREV),
			Command.ITEM, 3);
	private Command cmd_next = new Command(rm.getString(ResourceIDs.STR_NEXT),
			Command.ITEM, 4);
	private Command cmd_delay = new Command(rm
			.getString(ResourceIDs.STR_FILLLATER), Command.EXIT, 3);

	private StringItem si_instructions = new StringItem(rm
			.getString(ResourceIDs.STR_INSTRUCTIONS), "");
	private ChoiceGroup cg_show_instructions = new ChoiceGroup("",
			ChoiceGroup.MULTIPLE, new String[] { rm
					.getString(ResourceIDs.STR_HIDE) }, null);

	/** initial field offset (choice and instructions or just instructions) */
	private int field_offset = 2;

	public DataFormScreen(DataForm df, DataFormListener dfl) {
		super(rm.getString(ResourceIDs.STR_FILL_FORM));

		this.df = df;
		this.dfl = dfl;

		if (df.title != null) {
			setTitle(df.title);
		}

		addCommand(cmd_cancel);
		addCommand(cmd_delay);
		setCommandListener(this);

		actions = DataFormListener.CMD_SUBMIT | DataFormListener.CMD_CANCEL;

		cg_show_instructions.setLayout(Item.LAYOUT_SHRINK);
		cg_show_instructions.setLayout(Item.LAYOUT_NEWLINE_AFTER);
		si_instructions.setLayout(Item.LAYOUT_SHRINK);

		setItemStateListener(this);

		createControls();
	}

	/**
	 * Sets the available command buttons. Actions should be one of the CMD_*
	 * flags defined in the DataFormListener interface. 
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
		deleteAll();

		if (df.instructions != null) {
			si_instructions.setText(df.instructions);
			append(si_instructions);
			append(cg_show_instructions);
			field_offset = 2;
		} else {
			field_offset = 0;
		}

		for (int i = 0; i < df.fields.size(); i++) {
			DataForm.Field fld = (DataForm.Field) df.fields.elementAt(i);

			if (fld.type == DataForm.FLT_HIDDEN) {
				continue;
			}

			if (fld.type == DataForm.FLT_BOOLEAN) {
				// XXX: check how to render this
				ChoiceGroup cgrp = new ChoiceGroup(fld.label, Choice.MULTIPLE,
						new String[] { "Yes" }, null);
				if ("1".equals(fld.dValue) || "true".equals(fld.dValue)) {
					cgrp.setSelectedFlags(new boolean[] { true });
				}
				append(cgrp);
				continue;
			}

			if (fld.type == DataForm.FLT_LISTMULTI
					|| fld.type == DataForm.FLT_LISTSINGLE) {
				String title = (fld.label == null ? null : fld.label + "\n");
				StringItem cgrp = new StringItem(title, "", Item.BUTTON);
				ListChoiceHandler lch = new ListChoiceHandler(cgrp, fld);
				cgrp.setDefaultCommand(new Command("Select", Item.BUTTON,
						Command.ITEM));
				cgrp.setItemCommandListener(lch);
				cgrp.setLayout(Item.LAYOUT_EXPAND | Item.LAYOUT_NEWLINE_AFTER);

				append(cgrp);
				continue;
			}

			if (fld.type == DataForm.FLT_JIDSINGLE
					|| fld.type == DataForm.FLT_TXTPRIV
					|| fld.type == DataForm.FLT_TXTSINGLE
					|| fld.type == DataForm.FLT_JIDMULTI
					|| fld.type == DataForm.FLT_TXTMULTI
					|| fld.type == DataForm.FLT_FIXED) {
				String title = (fld.label == null ? null : fld.label + "\n");
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
				// XXX: Which the maximum allowed length? We use 1k for the moment
				TextField tf = new TextField(title, fld.dValue, 1024, flags);
				tf.setLayout(Item.LAYOUT_EXPAND | Item.LAYOUT_NEWLINE_AFTER);
				append(tf);
				continue;
			}

		}

		Spacer sp = new Spacer(10, 5);
		sp.setLayout(Item.LAYOUT_NEWLINE_AFTER | Item.LAYOUT_NEWLINE_BEFORE);
		append(sp);

		/* show actions. order is CANCEL, [PREV], [NEXT], SUBMIT */
		if ((actions & DataFormListener.CMD_CANCEL) != 0) {
			setShowAction(cmd_cancel);
		}
		if ((actions & DataFormListener.CMD_PREV) != 0) {
			setShowAction(cmd_prev);
		}
		if ((actions & DataFormListener.CMD_NEXT) != 0) {
			setShowAction(cmd_next);
		}
		if ((actions & DataFormListener.CMD_SUBMIT) != 0) {
			setShowAction(cmd_submit);
		}
	}

	private void setShowAction(Command new_cmd) {
		StringItem cmd = new StringItem(null, new_cmd.getLabel(), Item.BUTTON);
		cmd.setDefaultCommand(new_cmd);
		cmd.addCommand(new_cmd);
		cmd.setItemCommandListener(this);
		cmd.setLayout(Item.LAYOUT_CENTER | Item.LAYOUT_SHRINK);
		append(cmd);
	}

	/**
	 * Command handler
	 */
	public void commandAction(Command cmd, Displayable d) {

		int comm = -1;

		if (cmd == cmd_cancel) {

			comm = DataFormListener.CMD_CANCEL;
		} else if (cmd == cmd_submit) {
			comm = DataFormListener.CMD_SUBMIT;
		} else if (cmd == cmd_next) {
			comm = DataFormListener.CMD_NEXT;
		} else if (cmd == cmd_prev) {
			comm = DataFormListener.CMD_PREV;
		} else if (cmd == cmd_delay) {
			comm = DataFormListener.CMD_DELAY;
		}

		if (comm == -1) {
			/* ???? */
			return;
		}

		fillForm();
		dfl.execute(comm);
	}

	/**
	 * Command handler for on-screen buttons
	 */
	public void commandAction(Command cmd, Item item) {
		commandAction(cmd, this);
		//		int comm = -1;
		//
		//		if(cmd == cmd_cancel) {
		//			comm = DataFormListener.CMD_CANCEL;
		//		} else if(cmd == cmd_submit) {
		//			comm = DataFormListener.CMD_SUBMIT;
		//		} else if(cmd == cmd_next) {
		//			comm = DataFormListener.CMD_NEXT;
		//		} else if(cmd == cmd_prev) {
		//			comm = DataFormListener.CMD_PREV;
		//		} else if(cmd == cmd_delay) {
		//			comm = DataFormListener.CMD_DELAY;
		//		}
		//
		//		if(comm == -1) {
		//			/* ???? */
		//			return;
		//		}
		//
		//		fillForm();
		//		dfl.execute(comm);

	}

	/**
	 * Called when submit is pressed
	 */
	private void fillForm() {
		int fpos = -1;

		// XXX: here we could verify the required fields
		for (int i = 0; i < df.fields.size(); i++) {
			DataForm.Field fld = (DataForm.Field) df.fields.elementAt(i);
			if (fld.type == DataForm.FLT_HIDDEN) {
				continue;
			}
			fpos++;

			if (fld.type == DataForm.FLT_BOOLEAN) {
				ChoiceGroup cgrp = (ChoiceGroup) get(fpos + field_offset);
				fld.dValue = (cgrp.isSelected(0) ? "true" : "false");
				continue;
			}

			if (fld.type == DataForm.FLT_LISTMULTI
					|| fld.type == DataForm.FLT_LISTSINGLE) {
				/* nothing to do, dValue is already set byt the ListHandler */
				continue;
			}

			if (fld.type == DataForm.FLT_JIDSINGLE
					|| fld.type == DataForm.FLT_TXTPRIV
					|| fld.type == DataForm.FLT_TXTSINGLE
					|| fld.type == DataForm.FLT_JIDMULTI
					|| fld.type == DataForm.FLT_TXTMULTI
					|| fld.type == DataForm.FLT_FIXED) {
				TextField tf = (TextField) get(fpos + field_offset);
				fld.dValue = tf.getString();
				continue;
			}
		}

	}

	public class ListChoiceHandler implements ItemCommandListener,
			CommandListener {

		/** the item referring to */
		private StringItem itm;

		/** the field referring to */
		private Field fld;

		/** the list to display */
		private List list;

		public ListChoiceHandler(StringItem _itm, Field _fld) {
			itm = _itm;
			fld = _fld;
			String chs = rm.getString(ResourceIDs.STR_CHOOSE);
			StringBuffer stext = new StringBuffer();
			int snum = 0;
			for (int j = 0; j < fld.options.size(); j++) {
				String[] opt = (String[]) fld.options.elementAt(j);

				if (fld.dValue != null && fld.dValue.indexOf(opt[0]) != -1) {
					snum++;
					if (snum > 1) {
						stext.append("\n");
					}
					stext.append(opt[1]);
				}
			}
			itm.setText(stext.length() == 0 ? chs : stext.toString());
		}

		/**
		 * Handler for StringItem command.
		 */
		public void commandAction(Command _cmd, Item _itm) {
			int ctype = (fld.type == DataForm.FLT_LISTMULTI ? Choice.MULTIPLE
					: Choice.EXCLUSIVE);

			list = new List(fld.label, ctype);

			for (int j = 0; j < fld.options.size(); j++) {
				String[] opt = (String[]) fld.options.elementAt(j);
				list.append(opt[1], null);
			}
			list.addCommand(new Command(rm.getString(ResourceIDs.STR_ACCEPT),
					Command.ITEM, 1));

			list.setCommandListener(this);

			LampiroMidlet.disp.setCurrent(list);
		}

		/**
		 * Handler for List
		 */
		public void commandAction(Command _cmd, Displayable _disp) {
			boolean[] svals = new boolean[fld.options.size()];
			list.getSelectedFlags(svals);

			StringBuffer stext = new StringBuffer();
			StringBuffer dtext = new StringBuffer();
			int scount = 0;
			for (int i = 0; i < svals.length; i++) {
				if (svals[i]) {
					scount++;
					String[] opt = (String[]) fld.options.elementAt(i);
					if (scount > 1) {
						stext.append(" / ");
						dtext.append("\n");
					}
					stext.append(opt[1]);
					dtext.append(opt[0]);
				}
			}

			if (stext.length() == 0) {
				itm.setText(rm.getString(ResourceIDs.STR_CHOOSE));
				fld.dValue = "";
			} else {
				itm.setText(stext.toString());
				fld.dValue = dtext.toString();
			}

			LampiroMidlet.disp.setCurrent(DataFormScreen.this);
		}

	}

	public void itemStateChanged(Item item) {

		if (cg_show_instructions == item) {
			String choice = cg_show_instructions.getString(0);

			if (choice.startsWith("Show")) {
				cg_show_instructions.delete(0);
				cg_show_instructions.append(rm.getString(ResourceIDs.STR_HIDE),
						null);
				insert(0, si_instructions);
				field_offset++;
			} else {
				cg_show_instructions.delete(0);
				cg_show_instructions.append(rm
						.getString(ResourceIDs.STR_SHOW_INSTR), null);
				delete(0);
				field_offset--;
			}
		}
	}

}
