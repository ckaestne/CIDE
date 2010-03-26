/* Copyright (c) 2008 Bluendo S.r.L.
 * See about.html for details about license.
 *
 * $Id: CommandListScreen.java 1578 2009-06-16 11:07:59Z luca $
*/

package lampiro.screens;

import javax.microedition.lcdui.Canvas;
import javax.microedition.lcdui.Gauge;

import lampiro.screens.RosterScreen.WaitScreen;

import it.yup.ui.UICanvas;
import it.yup.ui.UIGauge;
import it.yup.ui.UIItem;
import it.yup.ui.UILabel;
import it.yup.ui.UIMenu;
import it.yup.ui.UIPanel;
import it.yup.ui.UIScreen;
import it.yup.util.ResourceIDs;
import it.yup.util.ResourceManager;
import it.yup.xmpp.CommandExecutor;
import it.yup.xmpp.Contact;

/**
 * XXX: maybe not necessary anymore with submenus
 */
public class CommandListScreen extends UIScreen implements WaitScreen {

	private static ResourceManager rm = ResourceManager.getManager("common",
			"en");

	private UILabel cmd_select = new UILabel(rm.getString(
			ResourceIDs.STR_EXECUTE).toUpperCase());
	private UILabel cmd_cancel = new UILabel(rm.getString(
			ResourceIDs.STR_CLOSE).toUpperCase());

	private Contact usr;

	private UIPanel mainList = new UIPanel(true, true);

	/*
	 * The chosen resource for this command   
	 */
	private String chosenResource;

	UIGauge progress_gauge = new UIGauge(rm.getString(ResourceIDs.STR_WAIT),
			false, Gauge.INDEFINITE, Gauge.CONTINUOUS_RUNNING);

	public CommandListScreen(Contact _usr, String chosenResource) {
		setMenu(new UIMenu(""));
		UIMenu menu = getMenu();
		menu.append(cmd_select);
		menu.append(cmd_cancel);
		
		setTitle(rm.getString(ResourceIDs.STR_CMDSCREEN_TITLE));
		usr = _usr;
		this.chosenResource = chosenResource;
		for (int i = 0; i < usr.cmdlist.length; i++) {
			String[] cmd = usr.cmdlist[i];
			UILabel ithCommLabel = new UILabel(cmd[1]);
			ithCommLabel.setFocusable(true);
			mainList.addItem(ithCommLabel);
		}
		if (usr.cmdlist.length == 0) {
			UILabel ithCommLabel = new UILabel(rm
					.getString(ResourceIDs.STR_NO_COMMAND));
			ithCommLabel.setFocusable(true);
			mainList.addItem(ithCommLabel);
			menu.remove(cmd_select);
		}
		
		mainList.setMaxHeight(-1);
		this.append(mainList);
		
		
	}

	public void menuAction(UIMenu menu, UIItem cmd) {
		if (cmd == cmd_cancel) {
			stopWaiting();
		} else if (cmd == cmd_select) {
			this.itemAction(mainList.getSelectedItem());
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

	public void itemAction(UIItem item) {
		if (item == null || mainList.contains(item) ==false)
			return;
		String idx = ((UILabel) item).getText();
		for (int i = 0; i < usr.cmdlist.length; i++) {
			String[] selcmd = usr.cmdlist[i];
			if (idx.equals(selcmd[1])) {
				/*
				 * not the most beautiful way of programming, creating a
				 * floating object
				 */
				new CommandExecutor(selcmd, chosenResource);
			}
		}
		this.getMenu().remove(cmd_select);
		cmd_cancel.setText(rm.getString(ResourceIDs.STR_CLOSE).toUpperCase());
		mainList.removeAllItems();
		mainList.addItem(progress_gauge);
		progress_gauge.start();
		RosterScreen.getInstance().setWaitingDF(this);
		this.askRepaint();
	}

	public void stopWaiting() {
		progress_gauge.cancel();
		RosterScreen.closeAndOpenRoster(this);
	}
}
