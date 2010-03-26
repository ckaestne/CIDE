/* Copyright (c) 2008 Bluendo S.r.L.
 * See about.html for details about license.
 *
 * $Id: OptionsScreen.java 1028 2008-12-09 15:44:50Z luca $
*/

package it.yup.screens;

import lampiro.LampiroMidlet;
import it.yup.util.ResourceIDs;
import it.yup.util.ResourceManager;
import it.yup.util.Utils;
import it.yup.xmpp.Config;

import javax.microedition.lcdui.Alert;
import javax.microedition.lcdui.AlertType;
import javax.microedition.lcdui.ChoiceGroup;
import javax.microedition.lcdui.Command;
import javax.microedition.lcdui.CommandListener;
import javax.microedition.lcdui.Displayable;
import javax.microedition.lcdui.Form;
import javax.microedition.lcdui.Gauge;
import javax.microedition.lcdui.Spacer;
import javax.microedition.lcdui.TextField;

public class OptionsScreen extends Form implements CommandListener {

	private static ResourceManager rm = ResourceManager.getManager("common",
																	"en");

	private Command cmd_save = new Command(rm.getString(ResourceIDs.STR_SAVE),
			Command.OK, 0);
	private Command cmd_cancel = new Command(rm
			.getString(ResourceIDs.STR_CANCEL), Command.CANCEL, 1);

	private ChoiceGroup ch_vibrate;
	private ChoiceGroup ch_tone;

	private TextField tf_keepalive;
	private Gauge g_volume;

	public OptionsScreen() {
		super(rm.getString(ResourceIDs.STR_OPTIONS_SETUP));
		boolean flags[] = new boolean[2];
		Config cfg = Config.getInstance();
		String selected_status = cfg
				.getProperty(Config.VIBRATION_AND_TONE_SETTINGS, "1");

		// prepare the form
		// tone and vibration settings
		append(rm.getString(ResourceIDs.STR_WHEN_NEW_MESSAGES));
		ch_vibrate = new ChoiceGroup(rm.getString(ResourceIDs.STR_VIBRATE_IF),
				ChoiceGroup.MULTIPLE);
		ch_vibrate.append(rm.getString(ResourceIDs.STR_HIDDEN), null);
		ch_vibrate.append(rm.getString(ResourceIDs.STR_SHOW), null);
		flags = Utils.str2flags(selected_status, 0, 2);
		ch_vibrate.setSelectedFlags(flags);
		append(ch_vibrate);

		ch_tone = new ChoiceGroup(rm.getString(ResourceIDs.STR_PLAY_TONE_IF),
				ChoiceGroup.MULTIPLE);
		ch_tone.append(rm.getString(ResourceIDs.STR_HIDDEN), null);
		ch_tone.append(rm.getString(ResourceIDs.STR_SHOW), null);
		flags = Utils.str2flags(selected_status, 2, 2);
		ch_tone.setSelectedFlags(flags);
		append(ch_tone);
		append(new Spacer(100, 1));

		// volume
		int volume = Integer
				.parseInt(cfg.getProperty(Config.TONE_VOLUME, "50"));
		g_volume = new Gauge(rm.getString(ResourceIDs.STR_VOLUME), true, 100,
				volume);
		append(g_volume);
		append(new Spacer(100, 1));

		// keepalive
		long ka = Long.parseLong(cfg.getProperty(Config.KEEP_ALIVE)) / 1000;

		tf_keepalive = new TextField(rm.getString(ResourceIDs.STR_KEEPALIVE),
				"" + ka, 5, TextField.NUMERIC);
		//tf_keepalive = new TextField("ciao", ""+ ka, 5, TextField.NUMERIC);
		append(tf_keepalive);

		// add the commands
		addCommand(cmd_save);
		addCommand(cmd_cancel);
		setCommandListener(this);
	}

	public void commandAction(Command cmd, Displayable disp) {
		if (cmd_save == cmd) {
			Config cfg = Config.getInstance();
			boolean flags[] = new boolean[4];
			flags[0] = ch_vibrate.isSelected(0);
			flags[1] = ch_vibrate.isSelected(1);
			flags[2] = ch_tone.isSelected(0);
			flags[3] = ch_tone.isSelected(1);
			cfg.setProperty(Config.VIBRATION_AND_TONE_SETTINGS, Utils
					.flags2str(flags, 0));
			cfg.setProperty(Config.TONE_VOLUME, "" + g_volume.getValue());
			cfg.setProperty(Config.KEEP_ALIVE, "" + ""
					+ Integer.parseInt(tf_keepalive.getString()) * 1000);
			cfg.saveToStorage();

			Alert alert = new Alert(rm.getString(ResourceIDs.STR_WARNING), rm
					.getString(ResourceIDs.STR_SETTINGS_EFFECT), null,
					AlertType.WARNING);
			alert.setTimeout(Alert.FOREVER);
			alert.setCommandListener(new CommandListener() {
				public void commandAction(Command arg0, Displayable arg1) {
					LampiroMidlet.disp.setCurrent(RosterScreen.getInstance());
				}

			});
			LampiroMidlet.disp.setCurrent(alert);

		} else if (cmd_cancel == cmd) {
			LampiroMidlet.disp.setCurrent(RosterScreen.getInstance());
		}

	}

}
