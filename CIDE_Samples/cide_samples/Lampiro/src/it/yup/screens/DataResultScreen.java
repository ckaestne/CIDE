/* Copyright (c) 2008 Bluendo S.r.L.
 * See about.html for details about license.
 *
 * $Id: DataResultScreen.java 1273 2009-03-13 15:57:53Z luca $
*/

/**
 * 
 */
package it.yup.screens;

import java.util.Hashtable;

import lampiro.LampiroMidlet;
import it.yup.xmpp.DataFormListener;
import it.yup.util.ResourceIDs;
import it.yup.util.ResourceManager;
import it.yup.xmpp.packets.DataForm;

import javax.microedition.lcdui.Command;
import javax.microedition.lcdui.CommandListener;
import javax.microedition.lcdui.Displayable;
import javax.microedition.lcdui.Form;
import javax.microedition.lcdui.Item;
import javax.microedition.lcdui.StringItem;

/**
 * Class that shows the results in a data form.
 */
public class DataResultScreen extends Form implements CommandListener {

	private static ResourceManager rm = ResourceManager.getManager("common",
																	"en");

	private Command cmd_close = new Command(
			rm.getString(ResourceIDs.STR_CLOSE), Command.EXIT, 1);
	private Command cmd_delete = new Command(rm
			.getString(ResourceIDs.STR_DELETE), Command.EXIT, 1);
	// these are for <reported/> items
	private Command cmd_prev = new Command(rm.getString(ResourceIDs.STR_PREV),
			Command.ITEM, 1);
	private Command cmd_next = new Command(rm.getString(ResourceIDs.STR_NEXT),
			Command.ITEM, 1);

	/** The result to show. The value -1 means possible instructions */
	private int pos = 0;

	private StringItem si_instructions = new StringItem(rm
			.getString(ResourceIDs.STR_INSTRUCTIONS), "");

	/** the data form */
	private DataForm df;

	private DataFormListener listener;

	public DataResultScreen(DataForm df, DataFormListener listener) {
		super(df.title);

		this.df = df;
		this.listener = listener;

		if (df.instructions != null) {
			si_instructions.setText(df.instructions);
			pos = -1;
		}

		showCurrent();
		addCommand(cmd_close);
		addCommand(cmd_delete);
		setCommandListener(this);
	}

	/**
	 * shows the currently selected result.
	 *
	 */
	private void showCurrent() {

		if (df.results.size() <= pos) {
			/* XXX ??? error ???*/
			return;
		}

		deleteAll();

		if (pos == -1) {
			append(si_instructions);
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

				if (fld.type == DataForm.FLT_FIXED) {
					lbl = "";
				}

				StringItem si = new StringItem(lbl, val, Item.PLAIN);
				append(si);
			}
		}
		removeCommand(cmd_prev);
		removeCommand(cmd_next);

		int start = df.instructions == null ? 0 : -1;
		if (pos > start) {
			addCommand(cmd_prev);
		}
		if (pos < df.results.size() - 1) {
			addCommand(cmd_next);
		}

	}

	public void commandAction(Command cmd, Displayable disp) {
		if (cmd == cmd_close) {
			listener.execute(DataFormListener.CMD_DELAY);
			return;
		} else if (cmd == cmd_delete) {
			listener.execute(DataFormListener.CMD_DESTROY);
			return;
		} else if (cmd == cmd_next) {
			pos++;
		} else if (cmd == cmd_prev) {
			pos--;
		}

		showCurrent();
		/* repaint? */
		LampiroMidlet.disp.setCurrent(this);
	}

}
