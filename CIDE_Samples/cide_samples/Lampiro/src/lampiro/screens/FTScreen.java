/**
 * 
 */
package lampiro.screens;

import it.yup.ui.UIGauge;
import it.yup.ui.UIHLayout;
import it.yup.ui.UIItem;
import it.yup.ui.UILabel;
import it.yup.ui.UILayout;
import it.yup.ui.UIMenu;
import it.yup.ui.UIPanel;
import it.yup.ui.UIScreen;
import it.yup.ui.UISeparator;
import it.yup.ui.UIVLayout;
import it.yup.util.ResourceIDs;
import it.yup.util.ResourceManager;
import it.yup.xmpp.FTSender;
import it.yup.xmpp.FTReceiver.OpenListener;

import java.io.IOException;
import java.util.Enumeration;
import java.util.Vector;

import javax.microedition.lcdui.Graphics;
import javax.microedition.lcdui.Image;

//#mdebug
//@
//@import it.yup.util.Logger;
//@
//#enddebug

class FTScreen extends UIScreen {
	private static ResourceManager rm = ResourceManager.getManager("common",
			"en");

	private UILabel cmd_exit = null;

	private static Image rightImage;

	private static Image leftImage;

	private static Vector fts = new Vector();

	private static FTScreen _instance;

	private UIPanel ftPanel = new UIPanel(true, true);

	private static class FTItem {

		static private boolean DIRECTION_IN = false;
		static private boolean DIRECTION_OUT = true;

		static private final int STATUS_WAITING = 0;
		static private final int STATUS_ONGOING = 1;
		static private final int STATUS_FINISHED = 2;
		static private final int STATUS_DECLINED = 3;

		private Object transferrer = null;
		private int status = 0;
		private boolean direction = false;
		private int percentage = 0;
		private String fileName = "";

		public FTItem(Object transferrer, int status, boolean direction,
				int percentage, String fileName) {
			this.transferrer = transferrer;
			this.status = status;
			this.direction = direction;
			this.percentage = percentage;
			this.fileName = fileName;
		}
	}

	public boolean keyPressed(int key) {
		boolean rolled = RosterScreen.makeRoll(key, this);
		if (rolled == false) return super.keyPressed(key);
		return true;
	}

	public static void startFtreceive(OpenListener ftrp) {
		synchronized (fts) {
			FTItem ithObject = new FTItem(ftrp, FTItem.STATUS_ONGOING,
					FTItem.DIRECTION_IN, 0, ftrp.fileName);
			fts.addElement(ithObject);
		}
	}

	static {
		try {
			rightImage = Image.createImage("/icons/menuarrow.png");
			leftImage = Image.createImage("/icons/leftarrow.png");
		} catch (IOException e) {
			// #mdebug
//@						System.out.println("In allocating menuImage" + e.getMessage());
			// #enddebug
		}
	}

	public static FTScreen getInstance() {
		synchronized (fts) {
			if (_instance == null) {
				_instance = new FTScreen();
			}
			_instance.fillScreen();
		}
		return _instance;
	}

	private FTScreen() {
		super();

		this.setMenu(new UIMenu(""));
		cmd_exit = new UILabel(rm.getString(ResourceIDs.STR_CLOSE)
				.toUpperCase());
		this.getMenu().append(cmd_exit);
		this.setTitle(rm.getString(ResourceIDs.STR_FT));
		this.append(ftPanel);

		synchronized (fts) {
			fillScreen();
		}
	}

	public void menuAction(UIMenu menu, UIItem cmd) {
		if (cmd == cmd_exit) {
			synchronized (fts) {
				_instance = null;
			}
			RosterScreen.closeAndOpenRoster(this);
		}
	}

	public static void ftFinished(Object sender) {
		synchronized (fts) {
			Enumeration en = fts.elements();
			while (en.hasMoreElements()) {
				FTItem ithObject = (FTItem) en.nextElement();
				if (ithObject.transferrer == sender) {
					ithObject.status = FTItem.STATUS_FINISHED;
					break;
				}
			}
			if (_instance != null) _instance.fillScreen();
		}
	}

	public static void ftAccept(FTSender sender, boolean accept) {
		synchronized (fts) {
			Enumeration en = fts.elements();
			while (en.hasMoreElements()) {
				FTItem ithObject = (FTItem) en.nextElement();
				if (ithObject.transferrer == sender) {
					ithObject.status = (accept ? FTItem.STATUS_ONGOING
							: FTItem.STATUS_DECLINED);
					break;
				}
			}
			if (_instance != null) _instance.fillScreen();
		}
	}

	public static void addFileSend(FTSender sender, String fileName) {
		synchronized (fts) {
			FTItem ithObject = new FTItem(sender, FTItem.STATUS_WAITING,
					FTItem.DIRECTION_OUT, 0, fileName);
			fts.addElement(ithObject);
			if (_instance != null) _instance.fillScreen();
		}
	}

	private void fillScreen() {

		this.setFreezed(true);

		try {
			int oldSelectedIndex = this.ftPanel.getSelectedIndex();
			this.ftPanel.removeAllItems();

			//			UISeparator sep = new UISeparator(1);
			//			sep.setFg_color(0x999999);
			//			this.ftPanel.addItem(sep);
			//			UILabel ftLabel = new UILabel(rm
			//					.getString(ResourceIDs.STR_FT_STATUS));
			//			ftLabel.setAnchorPoint(Graphics.HCENTER);
			//			this.ftPanel.addItem(ftLabel);

			if (fts.size() == 0) {
				this.ftPanel.addItem(new UILabel(rm
						.getString(ResourceIDs.STR_NO_FT)));
			} else {
				Enumeration en = fts.elements();
				while (en.hasMoreElements()) {
					FTItem ithObject = (FTItem) en.nextElement();

					UIHLayout ithHLayout = new UIHLayout(2);
					ithHLayout.setGroup(false);
					
					UIVLayout ithVLayout = new UIVLayout(2, 100);
					ithVLayout.setGroup(false);

					String fileName = "";
					Image dirImg = null;
					if (ithObject.direction == FTItem.DIRECTION_OUT) dirImg = rightImage;
					else
						dirImg = leftImage;

					int bStatus = ithObject.status;
					String status = "";
					UILabel ithLabel = new UILabel(dirImg, status);
					switch (bStatus) {
						case FTItem.STATUS_ONGOING:
							status = rm.getString(ResourceIDs.STR_ONGOING)
									+ " - " + ithObject.percentage * 10 + "%";
							break;

						case FTItem.STATUS_FINISHED:
							status = rm.getString(ResourceIDs.STR_FINISHED);
							break;

						case FTItem.STATUS_WAITING:
							status = rm
									.getString(ResourceIDs.STR_WAIT_ACCEPTANCE);
							break;

						case FTItem.STATUS_DECLINED:
							status = rm.getString(ResourceIDs.STR_DECLINED);
							break;

						default:
							break;
					}

					ithLabel.setText(status);
					ithLabel.setAnchorPoint(Graphics.RIGHT);

					Graphics g = RosterScreen.getInstance().getGraphics();
					ithVLayout.insert(ithLabel, 0, ithLabel.getHeight(g),
							UILayout.CONSTRAINT_PIXELS);

					if (ithObject.transferrer instanceof OpenListener) {
						fileName = ithObject.fileName;
					} else if (ithObject.transferrer instanceof FTSender) {
						fileName = ithObject.fileName;
					}

					UIGauge ithGauge = new UIGauge(fileName, false, 10, 0);
					ithGauge.setFocusable(true);
					ithGauge.setValue(Math.min(ithObject.percentage,10));
					ithVLayout.insert(ithGauge, 1, ithGauge.getHeight(g),
							UILayout.CONSTRAINT_PIXELS);
					ithVLayout.setHeight(ithGauge.getHeight(g)
							+ ithLabel.getHeight(g));

					ithHLayout.insert(ithVLayout, 0, 100, UILayout.CONSTRAINT_PERCENTUAL);
					ithHLayout.insert(new UISeparator(0), 1, 16, UILayout.CONSTRAINT_PIXELS);
					
					this.ftPanel.addItem(ithHLayout);
				}
			}
			this.ftPanel.setSelectedIndex(oldSelectedIndex);
		} catch (Exception e) {
			// #mdebug
//@						Logger.log("In fillScreen:" + e.getClass());
//@						e.printStackTrace();
			// #enddebug
		}

		this.setFreezed(false);

		this.askRepaint();
	}

	public static void chunkTransferred(int sentBytes, int length,
			Object ftEntity) {
		synchronized (fts) {
			Enumeration en = fts.elements();
			while (en.hasMoreElements()) {
				FTItem ithObject = (FTItem) en.nextElement();
				if (ithObject.transferrer == ftEntity) {
					ithObject.percentage = (sentBytes * 10) / length;
					break;
				}
			}
			if (_instance != null) _instance.fillScreen();
		}

	}

}
