/* Copyright (c) 2008 Bluendo S.r.L.
 * See about.html for details about license.
 *
 * $Id: TaskListScreen.java 1564 2009-06-09 14:17:08Z luca $
*/

package lampiro.screens;

import it.yup.ui.UIItem;
import it.yup.ui.UILabel;
import it.yup.ui.UIMenu;
import it.yup.ui.UIScreen;
import it.yup.util.ResourceIDs;
import it.yup.util.ResourceManager;
import it.yup.xmpp.Task;

/**
 * XXX: maybe not necessary anymore with screen switch
 */
public class TaskListScreen extends UIScreen {

	private static ResourceManager rm = ResourceManager.getManager("common",
			"en");

	private UILabel cmd_cancel = new UILabel(rm.getString(
			ResourceIDs.STR_CANCEL).toUpperCase());

	private Task tasks[];

	public TaskListScreen(Task tasks[]) {
		setTitle(rm.getString(ResourceIDs.STR_TASKHISTORY_TITLE));
		this.tasks = new Task[tasks.length];
		for (int i = 0; i < tasks.length; i++) {
			UILabel ul = new UILabel(tasks[i].getLabel());
			ul.setFocusable(true);
			append(ul);
			this.tasks[i] = tasks[i];
		}

		setMenu(new UIMenu(""));
		UIMenu menu = getMenu();
		menu.append(cmd_cancel);
	}

	public void menuAction(UIMenu menu, UIItem cmd) {
		if (cmd == cmd_cancel) {
			RosterScreen.closeAndOpenRoster(this);
		}
	}

	public void itemAction(UIItem item) {
		String tname = null;
		if (!(item instanceof UILabel)) { return; }
		tname = ((UILabel) item).getText();
		for (int i = 0; i < tasks.length; i++) {
			if (tasks[i].getLabel().equals(tname)) {
				// #ifdef UI
				tasks[i].display();
				// #endif
			}
		}
	}
}
