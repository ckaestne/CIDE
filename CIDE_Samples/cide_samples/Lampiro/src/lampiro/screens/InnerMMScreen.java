/**
 * 
 */
package lampiro.screens;

import it.yup.ui.UICanvas;
import it.yup.ui.UIScreen;

//#mdebug
//@import it.yup.util.Logger;
//@
//#enddebug

import it.yup.util.ResourceIDs;
import it.yup.util.ResourceManager;
import it.yup.util.Utils;
import it.yup.xmpp.Config;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Vector;

import javax.microedition.lcdui.Canvas;
import javax.microedition.lcdui.Command;
import javax.microedition.lcdui.CommandListener;
import javax.microedition.lcdui.Displayable;
import javax.microedition.lcdui.Graphics;
import javax.microedition.media.MediaException;
import javax.microedition.media.Player;
import javax.microedition.media.control.RecordControl;
import javax.microedition.media.control.VideoControl;
import javax.microedition.media.Control;

/*
 * A helper screen for the Multimedia Screen 
 */
class InnerMMScreen extends Canvas implements CommandListener {

	private static ResourceManager rm = ResourceManager.getManager("common",
			"en");

	/**
	 * 
	 */
	private Command cmd_exit = new Command(rm.getString(ResourceIDs.STR_CLOSE),
			Command.SCREEN, 3);
	private Command cmd_stop = new Command(rm.getString(ResourceIDs.STR_STOP),
			Command.SCREEN, 1);

	private Command cmd_default;
	private Command cmd_low;
	private Command cmd_medium;
	private Command cmd_high;
	private Command cmd_huge;

	private Command cmd_capture = null;

	private Player mPlayer = null;

	private int forcedResolution = -1;

	// a control used for both the VideoControl and the Audio control
	// to take snapshots or voices
	private Control multimediaControl = null;

	/*
	 * the type of the screen: can be "audio" or "image"
	 */
	private int screenType;

	private byte[] recordedSoundArray = null;

	private ByteArrayOutputStream output = new ByteArrayOutputStream();

	/* 
	 * A pointer to the correct file type in MMScreen.imageTypes or MMScreen.audioTypes
	 */
	private int typeIndex;

	/*
	 * The default jid for the user to send the multimedia file
	 */
	private String fullJid = null;

	public InnerMMScreen(Player player, Control control, int screenType,
			String contactToSend) {
		this.screenType = screenType;
		int width = this.getWidth();
		int height = this.getHeight();
		this.addCommand(cmd_exit);
		this.multimediaControl = control;
		this.mPlayer = player;
		this.fullJid = contactToSend;

		if (screenType == Config.imgType) {
			//this.setTitle(rm.getString(ResourceIDs.STR_CAPTURE_CAPTION));

			cmd_default = new Command(rm
					.getString(ResourceIDs.STR_RESOLUTION_DEFAULT),
					Command.SCREEN, 2);
			cmd_low = new Command(rm.getString(ResourceIDs.STR_CAMERA_LOW),
					Command.SCREEN, 2);
			cmd_medium = new Command(rm
					.getString(ResourceIDs.STR_CAMERA_MEDIUM), Command.SCREEN,
					2);
			cmd_high = new Command(rm.getString(ResourceIDs.STR_CAMERA_HIGH),
					Command.SCREEN, 2);
			cmd_huge = new Command(rm.getString(ResourceIDs.STR_CAMERA_HUGE),
					Command.SCREEN, 2);

			this.addCommand(cmd_default);
			this.addCommand(cmd_low);
			this.addCommand(cmd_medium);
			this.addCommand(cmd_high);
			this.addCommand(cmd_huge);

			cmd_capture = new Command(rm.getString(ResourceIDs.STR_CAPTURE)
					+ " <FIRE>", Command.SCREEN, 1);
			VideoControl tempVideoControl = (VideoControl) multimediaControl;
			tempVideoControl.initDisplayMode(VideoControl.USE_DIRECT_VIDEO,
					this);
			try {
				tempVideoControl.setDisplayLocation(2, 2);
				tempVideoControl.setDisplaySize(width - 4, height - 4);
			} catch (MediaException me) {
				// #mdebug
//@								me.printStackTrace();
//@								Logger.log("In setup video 1" + me.getClass().getName() + "\n"
//@										+ me.getMessage());
				//#enddebug
				try {
					tempVideoControl.setDisplayFullScreen(true);
				} catch (MediaException me2) {
					// #mdebug
//@										me2.printStackTrace();
//@										Logger.log("In setup video 2" + me2.getClass().getName()
//@												+ "\n" + me.getMessage());
					//#enddebug
				}
			}
			tempVideoControl.setVisible(true);
		} else {
			this.setTitle(rm.getString(ResourceIDs.STR_CAPTURE_AUDIO)
					+ " <FIRE>");
			cmd_capture = new Command(rm
					.getString(ResourceIDs.STR_CAPTURE_AUDIO), Command.SCREEN,
					1);
		}
		this.addCommand(cmd_capture);
		this.setCommandListener(this);

	}

	public void commandAction(Command c, Displayable s) {
		if (c == cmd_exit) {
			closeScreen();
		} else if (c == this.cmd_capture) {
			startCapture();

		} else if (c == this.cmd_stop) {
			try {
				((RecordControl) this.multimediaControl).commit();
				recordedSoundArray = output.toByteArray();
				this.mPlayer.close();
				handleMMFile(recordedSoundArray, Config.audioTypes[typeIndex],
						Config.audioType);
			} catch (IOException e) {
				// #mdebug
//@								e.printStackTrace();
//@								Logger.log("In capturing audio" + e.getClass().getName() + "\n"
//@										+ e.getMessage()
//@										+ System.getProperty("audio.encodings"));
				//#enddebug
			}
		} else if (c == this.cmd_default) {
			this.forcedResolution = 0;
			this.setTitle(rm.getString(ResourceIDs.STR_RESOLUTION_DEFAULT));
		} else if (c == this.cmd_low) {
			this.forcedResolution = 1;
			this.setTitle(rm.getString(ResourceIDs.STR_CAMERA_LOW));
		} else if (c == this.cmd_medium) {
			this.forcedResolution = 2;
			this.setTitle(rm.getString(ResourceIDs.STR_CAMERA_MEDIUM));
		} else if (c == this.cmd_high) {
			this.forcedResolution = 3;
			this.setTitle(rm.getString(ResourceIDs.STR_CAMERA_HIGH));
		} else if (c == this.cmd_huge) {
			this.forcedResolution = 4;
			this.setTitle(rm.getString(ResourceIDs.STR_CAMERA_HUGE));
		}
	}

	private void closeScreen() {
		mPlayer.close();
		mPlayer = null;
		multimediaControl = null;
		UICanvas.display(null);
		RosterScreen rosterScreen = RosterScreen.getInstance();
		rosterScreen.setFreezed(false);
		UICanvas.getInstance().show(rosterScreen);
	}

	private void startCapture() {
		this.removeCommand(cmd_capture);
		new Thread() {
			public void run() {
				if (screenType == Config.imgType) captureVideo();
				if (screenType == Config.audioType) captureAudio();
			}
		}.start();
	}

	public void captureAudio() {
		try {
			this.addCommand(cmd_stop);

			output = new ByteArrayOutputStream();
			RecordControl recordControl = ((RecordControl) this.multimediaControl);
			recordControl.setRecordStream(output);
			recordControl.startRecord();
			this.mPlayer.start();
		} catch (Exception e) {
			// #mdebug
//@						e.printStackTrace();
//@						Logger.log("In capturing audio" + e.getClass().getName() + "\n"
//@								+ e.getMessage() + System.getProperty("audio.encodings"));
			//#enddebug
		}
	}

	public String getVideoRes(boolean append) {
		char appendChar = (append == true ? '?' : '&');
		String res = Config.getInstance().getProperty(Config.CAMERA_RESOLUTION,
				"0");
		int resInt = Integer.parseInt(res);
		if (this.forcedResolution > 0) resInt = forcedResolution;
		String camResolution = "";
		switch (resInt) {
			case 0:
				camResolution = "";
				break;
			case 1:
				camResolution = appendChar + "width=160&height=120";
				break;
			case 2:
				camResolution = appendChar + "width=320&height=240";
				break;
			case 3:
				camResolution = appendChar + "width=640&height=480";
				break;
			case 4:
				camResolution = appendChar + "width=1024&height=768";
				break;
			default:
				break;
		}
		return camResolution;
	}

	public void captureVideo() {
		try {
			String prop = System.getProperty("video.snapshot.encodings");
			Vector encs = Utils.tokenize(prop, new String[] { " " }, false);
			String foundType = "jpeg";
			typeIndex = 0;
			boolean found = false;
			for (int i = 0; !found && i < Config.imageTypes.length; i++) {
				for (int l = 0; !found && l < encs.size(); l++) {
					if (((String) encs.elementAt(l)).toLowerCase().indexOf(
							Config.imageTypes[i]) >= 0) {
						foundType = ((String) encs.elementAt(l));
						typeIndex = i;
						found = true;
					}
				}
			}

			String camResolution = this.getVideoRes(false);

			// Get the image.
			byte[] raw = null;
			// some phones crashes when getting a snapshot with encoding
			VideoControl tempVideoControl = ((VideoControl) multimediaControl);
			try {
				raw = tempVideoControl.getSnapshot(foundType + camResolution);
			} catch (SecurityException e) {
				closeScreen();
			} catch (Exception e) {
				//#mdebug
//@								e.printStackTrace();
//@								Logger.log("In capturing image 1" + e.getClass().getName()
//@										+ "\n" + e.getMessage());
				//#enddebug
				try {
					raw = tempVideoControl.getSnapshot(foundType);
				} catch (SecurityException e2) {
					closeScreen();
				} catch (Exception e2) {
					//#mdebug
//@										e.printStackTrace();
//@										Logger.log("In capturing image 2" + e.getClass().getName()
//@												+ "\n" + e.getMessage());
					//#enddebug
					try {
						raw = tempVideoControl.getSnapshot(null);
					} catch (SecurityException e3) {
						closeScreen();
					} catch (Exception e3) {
						//#mdebug
//@											e.printStackTrace();
//@											Logger.log("In capturing image 2" + e.getClass().getName()
//@													+ "\n" + e.getMessage());
						//#enddebug
						closeScreen();
					}
				}
			}
			handleMMFile(raw, Config.imageTypes[typeIndex], Config.imgType);
			// Shut down the player.
			mPlayer.close();
			UICanvas.display(null);
			mPlayer = null;
			multimediaControl = null;
		} catch (Exception e) {
			// #mdebug
//@						e.printStackTrace();
//@						Logger.log("In capturing image" + e.getClass().getName() + "\n"
//@								+ e.getMessage());
			//#enddebug
		}
	}

	private void handleMMFile(byte[] raw, String type, int mmType) {
		UIScreen sendImageScreen = new SendMMScreen(raw, null, type, null,
				mmType, fullJid);
		RosterScreen.getInstance().setFreezed(false);
		UICanvas.getInstance().open(sendImageScreen, true);
		UICanvas.display(null);
	}

	protected void keyPressed(int key) {
		if (this.getGameAction(key) == Canvas.FIRE) {
			startCapture();
		}
	}

	protected void paint(Graphics g) {
		if (screenType != Config.imgType) { return; }
		int height = getHeight();
		int width = getWidth();
		g.setColor(0x000000);
		g.fillRect(0, 0, width, height);

	}

	public void setTypeIndex(int typeIndex) {
		this.typeIndex = typeIndex;
	}

	public int getTypeIndex() {
		return typeIndex;
	}
}
