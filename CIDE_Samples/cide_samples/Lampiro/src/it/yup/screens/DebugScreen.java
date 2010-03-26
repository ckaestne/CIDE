/* Copyright (c) 2008 Bluendo S.r.L.
 * See about.html for details about license.
 *
 * $Id: DebugScreen.java 1028 2008-12-09 15:44:50Z luca $
*/

package it.yup.screens;

import lampiro.LampiroMidlet;
import it.yup.util.MemoryLogConsumer;
import it.yup.util.ResourceIDs;
import it.yup.util.ResourceManager;

import javax.microedition.lcdui.Command;
import javax.microedition.lcdui.CommandListener;
import javax.microedition.lcdui.Displayable;
import javax.microedition.lcdui.Form;
import javax.microedition.lcdui.StringItem;

public class DebugScreen extends Form implements CommandListener {

	private static ResourceManager rm = ResourceManager.getManager("common",
																	"en");

	private Command cmd_close = new Command(
			rm.getString(ResourceIDs.STR_CLOSE), Command.CANCEL, 1);
	private Command cmd_clear = new Command(
			rm.getString(ResourceIDs.STR_CLEAR), Command.OK, 2);

	private Displayable returnScreen = null;

	public DebugScreen() {
		super("Debug");
		MemoryLogConsumer consumer = MemoryLogConsumer.getConsumer();

		for (int i = 0; i < consumer.messages.size(); i++) {
			StringItem item = new StringItem("-", (String) consumer.messages
					.elementAt(i));
			this.append(item);
		}

		addCommand(cmd_close);
		addCommand(cmd_clear);
		this.setCommandListener(this);

	}

	public void commandAction(Command cmd, Displayable d) {
		if (cmd == cmd_close) {
			LampiroMidlet.disp.setCurrent(returnScreen);
		} else if (cmd == cmd_clear) {
			deleteAll();
			MemoryLogConsumer consumer = MemoryLogConsumer.getConsumer();

			StringItem _item = new StringItem("-", "logging");
			this.append(_item);

			for (int i = 0; i < consumer.messages.size(); i++) {
				StringItem item = new StringItem("-",
						(String) consumer.messages.elementAt(i));
				this.append(item);
			}
		}

	}

	public void setReturnScreen(Displayable d) {
		returnScreen = d;
	}

}
