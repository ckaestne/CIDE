/**
 * 
 */
package lampiro.screens;

import java.io.ByteArrayInputStream;
import java.util.Vector;
import it.yup.ui.UIButton;
import it.yup.ui.UICanvas;
import it.yup.ui.UIHLayout;
import it.yup.ui.UIItem;
import it.yup.ui.UILabel;
import it.yup.ui.UIMenu;
import it.yup.ui.UIPanel;
import it.yup.ui.UIScreen;
import it.yup.ui.UITextField;
import it.yup.ui.UIUtils;

//#mdebug
//@import it.yup.util.Logger;
//#enddebug

import it.yup.util.ResourceIDs;
import it.yup.util.ResourceManager;
import it.yup.util.Utils;
import it.yup.xmpp.Config;

import javax.microedition.lcdui.Image;
import javax.microedition.lcdui.TextField;
import javax.microedition.media.Manager;
import javax.microedition.media.Player;

public class ShowMMScreen extends UIScreen {
	/**
	 * 
	 */
	protected static ResourceManager rm = ResourceManager.getManager("common",
			"en");

	protected UIPanel mainPanel = new UIPanel(true, false);
	protected byte[] originalImage = null;

	protected UILabel cmd_exit = new UILabel(rm
			.getString(ResourceIDs.STR_CLOSE).toUpperCase());

	private UILabel playLabel = new UILabel(rm.getString(ResourceIDs.STR_PLAY)
			.toUpperCase());

	protected UITextField fileNameTextField;

	protected UITextField fileDescTextField;

	protected UIButton cmd_save = new UIButton(rm
			.getString(ResourceIDs.STR_SAVE));

	private UILabel uil;

	/*
	 * the type of the multimedia file
	 */
	private int mmType;

	protected byte[] fileData;

	private String fileType;

	/*
	 * The name of the file to show
	 */
	protected String fileName;

	protected String fileDescription;

	protected UIHLayout cmd_layout;

	public static int getFileType(String fileName) {
		Vector tokens = Utils.tokenize(fileName, '.');
		String fileType = (String) tokens.elementAt(tokens.size() - 1);
		int mmType = Config.imgType;
		for (int i = 0; i < Config.audioType; i++) {
			if (fileType.compareTo(Config.audioTypes[i]) == 0) {
				mmType = Config.audioType;
				break;
			}
		}
		return mmType;
	}

	public ShowMMScreen(byte[] fileData, String fileName, String fileDescription) {
		int mmType = getFileType(fileName);

		this.fileName = fileName;
		this.fileDescription = fileDescription;
		this.mmType = mmType;
		this.fileData = fileData;
		init();
	}

	public ShowMMScreen(byte[] fileData, String fileType, int mmType) {
		this.mmType = mmType;
		this.fileType = fileType;
		this.fileData = fileData;
		init();

	}

	private void init() {
		Image resImg;
		int count = AlbumScreen.getCount(mmType);
		if (mmType == Config.imgType) {
			setTitle(rm.getString(ResourceIDs.STR_IMAGE));
			Image convImage = null;
			convImage = Image.createImage(fileData, 0, fileData.length);
			fileNameTextField = new UITextField(rm
					.getString(ResourceIDs.STR_FILE_NAME), rm
					.getString(ResourceIDs.STR_IMAGE)
					+ count + "." + fileType, 255, TextField.ANY);
			resImg = UIUtils.imageResize(convImage, UICanvas.getInstance()
					.getWidth() - 10, -1);
		} else {
			setTitle(rm.getString(ResourceIDs.STR_AUDIO));
			resImg = UICanvas.getUIImage("/icons/mic.png");
			fileNameTextField = new UITextField(rm
					.getString(ResourceIDs.STR_FILE_NAME), rm
					.getString(ResourceIDs.STR_AUDIO)
					+ count + "." + fileType, 255, TextField.ANY);
		}
		fileDescTextField = new UITextField(rm.getString(ResourceIDs.STR_DESC),
				"<" + rm.getString(ResourceIDs.STR_DESC) + ">", 255,
				TextField.ANY);
		if (fileName != null) {
			fileNameTextField.setText(fileName);
		}

		if (fileDescription != null) {
			fileDescTextField.setText(fileDescription);
		}

		UIMenu thisMenu = new UIMenu("");
		thisMenu.append(this.cmd_exit);
		this.setMenu(thisMenu);
		this.originalImage = fileData;
		this.append(mainPanel);
		mainPanel.setMaxHeight(-1);
		uil = new UILabel(resImg, "");
		uil.setFocusable(true);
		UIHLayout ehl = UIUtils.easyCenterLayout(uil, resImg.getWidth());
		if (mmType == Config.audioType) {
			UIMenu sub = new UIMenu("");
			sub.append(playLabel);
			uil.setSubmenu(sub);
		}
		mainPanel.addItem(ehl);
		this.setSelectedItem(mainPanel);
		mainPanel.setSelectedItem(ehl);

		this.mainPanel.addItem(this.fileNameTextField);
		this.mainPanel.addItem(this.fileDescTextField);
		cmd_layout = UIUtils.easyCenterLayout(cmd_save, 70);
		this.mainPanel.addItem(cmd_layout);
	}

	public void menuAction(UIMenu menu, UIItem c) {
		if (c == cmd_exit) {
			UICanvas.getInstance().close(this);
			//UICanvas.getInstance().open(RosterScreen.getInstance(), true);
		} else if (c == this.playLabel) {
			itemAction(this.uil);
		}
	}

	public void itemAction(UIItem c) {
		if (c == this.uil && this.mmType == Config.audioType) {
			this.playAudio();
		} else if (c == cmd_save) {
			AlbumScreen.addAlbum(this.fileData, fileNameTextField.getText(),
					fileDescTextField.getText(), mmType);
			AlbumScreen alb = AlbumScreen.getInstance();
			UICanvas.getInstance().close(this);
			UICanvas.getInstance().open(alb, true);

		}
	}

	public void showNotify() {
		//if (mmType == Config.audioType) playAudio();
	}

	public synchronized void playAudio() {
		//#mdebug
//@						Logger.log("playing audio file");
		//#enddebug
		Thread t = new Thread() {
			public void run() {
				try {
					ByteArrayInputStream recordedInputStream = new ByteArrayInputStream(
							fileData);
					Player p2 = Manager.createPlayer(recordedInputStream,
							"audio/" + fileType);
					p2.prefetch();
					p2.start();
				} catch (Exception e) {
					//#mdebug
//@															e.printStackTrace();
//@															Logger.log("In setup video 1" + e.getClass().getName()
//@																	+ "\n" + e.getMessage());
					//#enddebug
				}
			}
		};
		t.start();

	}

	public void setMmType(int mmType) {
		this.mmType = mmType;
	}

	public int getMmType() {
		return mmType;
	}
}
