package lampiro.screens;

// #mdebug
//@import it.yup.util.Logger;
//@
// #enddebug

import java.util.Vector;

import it.yup.ui.UICanvas;
import it.yup.util.ResourceIDs;
import it.yup.util.ResourceManager;
import it.yup.util.Utils;
import it.yup.xmpp.Config;

import javax.microedition.lcdui.AlertType;
import javax.microedition.lcdui.Canvas;
import javax.microedition.lcdui.Display;
import javax.microedition.lcdui.Graphics;
import javax.microedition.media.Manager;
import javax.microedition.media.Player;
import javax.microedition.media.control.RecordControl;
import javax.microedition.media.control.VideoControl;

import lampiro.LampiroMidlet;

/*
 * MultimediaScreen 
 */
public class MMScreen extends Canvas {

	private static ResourceManager rm = ResourceManager.getManager("common",
			"en");

	/*
	 * The default jid for the user to send the multimedia file
	 */
	private String contactToSend = null;

	public MMScreen(String contactToSend) {
		this.contactToSend = contactToSend;
	}

	protected void paint(Graphics g) {
		int width = getWidth();
		int height = getHeight();

		// Draw a green border around the VideoControl.
		g.setColor(0x000000);
		g.drawRect(0, 0, width - 1, height - 1);
		g.drawRect(1, 1, width - 3, height - 3);
	}

	public void showCamera() {
		try {
			Player mPlayer = null;
			try {
				//String camResolution= MMScreen.getVideoRes(true);
				mPlayer = Manager.createPlayer("capture://video");
				mPlayer.realize();
			} catch (Exception e) {
				//#mdebug
//@								e.printStackTrace();
//@								Logger.log("In setup video 1" + e.getClass().getName() + "\n"
//@										+ e.getMessage());
				//#enddebug
			}
			if (mPlayer == null) {
				RosterScreen rs = RosterScreen.getInstance();
				UICanvas.getInstance().open(rs, true);
				UICanvas.display(null);
				rs.showAlert(AlertType.ERROR, rm
						.getString(ResourceIDs.STR_ERROR), rm
						.getString(ResourceIDs.STR_CAMERA_ERROR), null);
				return;
			}
			VideoControl mControl = (VideoControl) mPlayer
					.getControl("VideoControl");
			InnerMMScreen ics = new InnerMMScreen(mPlayer, mControl,
					Config.imgType, contactToSend);
			Display.getDisplay(LampiroMidlet._lampiro).setCurrent(ics);
			mPlayer.start();
			repaint();
		} catch (Exception e) {
			// #mdebug
//@						e.printStackTrace();
//@						Logger.log("In starting player " + e.getClass().getName() + "\n"
//@								+ e.getMessage());
			//#enddebug
		}
	}

	public void showAudio() {
		// find the best audio format
		String prop = System.getProperty("audio.encodings");
		Vector encs = Utils.tokenize(prop, new String[] { " " }, false);
		String foundType = "amr";
		int typeIndex = 0;
		boolean found = false;
		for (int i = 0; !found && i < Config.audioTypes.length; i++) {
			for (int l = 0; !found && l < encs.size(); l++) {
				if (((String) encs.elementAt(l)).indexOf(Config.audioTypes[i]) >= 0) {
					foundType = ((String) Config.audioTypes[i]);
					typeIndex = i;
					found = true;
					break;
				}
			}
		}
		Player p = null;
		RecordControl rc;
		try {
			//p = Manager.createPlayer("capture://audio");
			p = Manager.createPlayer("capture://audio?encoding=" + foundType);
			p.realize();
		} catch (Exception e) {
			// #mdebug
//@						e.printStackTrace();
//@						Logger.log("In allocate audio player " + e.getClass().getName()
//@								+ "\n" + e.getMessage());
			//#enddebug
			RosterScreen.getInstance().setFreezed(false);
			UICanvas.getInstance().open(RosterScreen.getInstance(), true);
			UICanvas.display(null);
			return;
		}
		try {
			rc = (RecordControl) p.getControl("RecordControl");
		} catch (Exception e) {
			// #mdebug
//@						e.printStackTrace();
//@						Logger.log("In allocate audio player " + e.getClass().getName()
//@								+ "\n" + e.getMessage());
			//#enddebug
			return;
		}
		InnerMMScreen ics = new InnerMMScreen(p, rc, Config.audioType,
				this.contactToSend);
		ics.setTypeIndex(typeIndex);
		Display.getDisplay(LampiroMidlet._lampiro).setCurrent(ics);
		repaint();
	}

}
