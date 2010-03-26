/* Copyright (c) 2008 Bluendo S.r.L.
 * See about.html for details about license.
 *
 * $Id: DebugScreen.java 846 2008-09-11 12:20:05Z luca $
*/

package lampiro.screens;

import it.yup.ui.UIButton;
import it.yup.ui.UICanvas;
import it.yup.ui.UIItem;
import it.yup.ui.UILabel;
import it.yup.ui.UIPanel;
import it.yup.ui.UIScreen;
import it.yup.util.MemoryLogConsumer;
import it.yup.util.ResourceIDs;
import it.yup.util.ResourceManager;

import javax.microedition.lcdui.Font;

public class DebugScreen extends UIScreen {

	private UIPanel mainPanel = new UIPanel();
	private static ResourceManager rm = ResourceManager.getManager("common",
																	"en");

	private UIButton cmd_close = new UIButton(rm
			.getString(ResourceIDs.STR_CLOSE));
	private UIButton cmd_clear = new UIButton(rm
			.getString(ResourceIDs.STR_CLEAR));

	public DebugScreen() {
		super();
		this.mainPanel.setMaxHeight(-1);

		this.mainPanel.addItem(cmd_close);
		this.mainPanel.addItem(cmd_clear);
		setupLogging();
		this.append(mainPanel);
	}

	private void setupLogging() {
		MemoryLogConsumer consumer = MemoryLogConsumer.getConsumer();

		for (int i = 0; i < consumer.messages.size(); i++) {
			UILabel uith = new UILabel((String) consumer.messages.elementAt(i));
			uith.setWrappable(true, UICanvas.getInstance().getWidth());
			uith.setFocusable(true);
			Font minFont = Font.getFont(Font.FACE_PROPORTIONAL,
										Font.STYLE_PLAIN, Font.SIZE_SMALL);
			uith.setFont(minFont);
			this.mainPanel.addItem(uith);
		}
	}

	public void itemAction(UIItem item) {
		if (item == this.cmd_close) {
			UICanvas.getInstance().close(this);
		}

		if (item == this.cmd_clear) {
			for (int i = this.mainPanel.getItems().size() - 1; i >= 0; i--) {
				MemoryLogConsumer.getConsumer().messages.removeAllElements();
				if (this.mainPanel.getItems().elementAt(i) instanceof UIButton == false) {
					this.mainPanel.removeItemAt(i);
				}
			}
			setupLogging();
		}
		this.askRepaint();
	}
}
