/* Copyright (c) 2008 Bluendo S.r.L.
 * See about.html for details about license.
 *
 * $Id: UITestMidlet.java 1326 2009-03-30 16:53:29Z luca $
*/

package it.yup.tests;

import it.yup.ui.UIAccordion;
import it.yup.ui.UIButton;
import it.yup.ui.UICanvas;
import it.yup.ui.UICheckbox;
import it.yup.ui.UICombobox;
import it.yup.ui.UIConfig;
import it.yup.ui.UIEmoLabel;
import it.yup.ui.UIHLayout;
import it.yup.ui.UIItem;
import it.yup.ui.UILabel;
import it.yup.ui.UILayout;
import it.yup.ui.UIMenu;
import it.yup.ui.UIPanel;
import it.yup.ui.UIRadioButtons;
import it.yup.ui.UIScreen;
import it.yup.ui.UITextField;
import it.yup.ui.UITextPanel;
import it.yup.ui.UIVLayout;
import it.yup.util.Logger;
import it.yup.util.MemoryLogConsumer;

import java.io.IOException;
import java.util.Vector;

import javax.microedition.lcdui.Canvas;
import javax.microedition.lcdui.Display;
import javax.microedition.lcdui.Font;
import javax.microedition.lcdui.Graphics;
import javax.microedition.lcdui.Image;
import javax.microedition.lcdui.TextField;
import javax.microedition.midlet.MIDlet;
import javax.microedition.midlet.MIDletStateChangeException;

import lampiro.screens.DebugScreen;

public class UITestMidlet extends MIDlet {

	static Image[] imgs;

	UICanvas canvas;

	UILabel cmd_debug = new UILabel("debug");
	private KeysScreen ks;

	public UITestMidlet() {
		ks = new KeysScreen();
		canvas = UICanvas.getInstance();
		UICanvas.setDisplay(Display.getDisplay(this));

		// Popup tests
		// UIMenu pop2 = new UIMenu(screen, "Popup-A");
		// pop2.setAbsoluteX(20);
		// pop2.setAbsoluteY(10);
		// pop2.setWidth(130);
		// for (int i = 0; i < 3; i++) {
		// UIMenuItem uimi = new UIMenuItem("Popup-A" + i, null);
		// pop2.append(uimi);
		// }
		//
		// UIMenu tempPop1 = pop2;
		// for (int l = 0; l <= 4; l++) {
		// UIMenu pop3 = new UIMenu(screen, "Popup-B");
		// for (int i = 0; i < 3; i++) {
		// UIMenuItem uimi = new UIMenuItem("Popup-B" + i, null);
		// pop3.append(uimi);
		// }
		// UIMenuItem uimi1 = new UIMenuItem("Popup Item Img ", pop3);
		// tempPop1.append(uimi1);
		// tempPop1 = pop3;
		// }
		// screen.addPopup(pop2);

		/*
		 * UIConfig.fg_color = 0x000000; UIConfig.header_fg = 0xFFFFFF;
		 * 
		 * UIConfig.bg_color = 0xced0f1; UIConfig.header_bg = 0x2407db;
		 * 
		 * 
		 */

		UIConfig.scrollbar_bg = 0x444444;
		UIConfig.scrollbar_fg = 0x13a0f7;
		UIConfig.header_bg = 0x2407db;
		UIConfig.bg_color = 0xddddff;

	}

	protected void destroyApp(boolean arg0) throws MIDletStateChangeException {
		// TODO Auto-generated method stub

	}

	protected void pauseApp() {
		// TODO Auto-generated method stub

	}

	protected void startApp() throws MIDletStateChangeException {
		Display display = Display.getDisplay(this);
		display.setCurrent(ks);
	}

	private class TestScreen extends UIScreen {

		private class Changer extends Thread {

			public void run() {
				try {
					Thread.sleep(10000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				TestScreen.this.setFreezed(true);
				TestScreen.this.swap(8, 9);
				UILabel addedLabel = null;
				for (int l = 0; l <= 4; l++) {
					addedLabel = new UILabel("Added label" + l);
					TestScreen.this.insert(l * 3, addedLabel);
				}
				TestScreen.this.setFreezed(false);
				TestScreen.this.askRepaint();

			}
		}

		private UICheckbox checkBox = new UICheckbox("prova");
		private UICheckbox checkBox2 = new UICheckbox("prova2");
		private UILabel activeItem = new UILabel("Active Item ");
		private UIMenu contMenu = null;

		public TestScreen() {

			//Changer cg = new Changer();
			//new Thread(cg).start();
			Logger.addConsumer(MemoryLogConsumer.getConsumer());

		}

		public void buildScreen() {
//			UILabel gradientLabel = new UILabel("gradient");
//			gradientLabel.setGradientColor(0x336699);
//			gradientLabel.setGradientSelectedColor(0x5577BB);
//			gradientLabel.setFocusable(true);
//			this.append(gradientLabel);
//			
//			UIAccordion uia = new UIAccordion();
//
//			UILabel titleLabel = new UILabel("title1");
//			Vector uip = new Vector();
//			UILabel aLabel = new UILabel("a");
//			UILabel bLabel = new UILabel("b");
//			UILabel cLabel = new UILabel("d");
//			uip.addElement(aLabel);
//			uip.addElement(bLabel);
//			uip.addElement(cLabel);
//			uia.addItem(titleLabel, uip);
//
//			titleLabel = new UILabel("title2");
//			uip = new Vector();
//			aLabel = new UILabel("e");
//			bLabel = new UILabel("f");
//			cLabel = new UILabel("g");
//			uip.addElement(aLabel);
//			uip.addElement(bLabel);
//			uip.addElement(cLabel);
//			uia.addItem(titleLabel, uip);
//
//			titleLabel = new UILabel("title3");
//			uip = new Vector();
//			aLabel = new UILabel("h");
//			bLabel = new UILabel("i");
//			cLabel = new UILabel("j");
//			uip.addElement(aLabel);
//			uip.addElement(bLabel);
//			uip.addElement(cLabel);
//			uia.addItem(titleLabel, uip);
//
//			this.append(uia);
//
//			UIAccordion uiaMenu = new UIAccordion();
//			
//			
//			titleLabel = new UILabel("title1");
//			uip = new Vector();
//			aLabel = new UILabel("a");
//			bLabel = new UILabel("b");
//			cLabel = new UILabel("d");
//			uip.addElement(aLabel);
//			uip.addElement(bLabel);
//			uip.addElement(cLabel);
//			uiaMenu.addItem(titleLabel, uip);
//
//			titleLabel = new UILabel("title2");
//			uip = new Vector();
//			aLabel = new UILabel("e");
//			bLabel = new UILabel("f");
//			cLabel = new UILabel("g");
//			uip.addElement(aLabel);
//			uip.addElement(bLabel);
//			uip.addElement(cLabel);
//			uiaMenu.addItem(titleLabel, uip);
//
//			titleLabel = new UILabel("title3");
//			uip = new Vector();
//			aLabel = new UILabel("h");
//			bLabel = new UILabel("i");
//			cLabel = new UILabel("j");
//			uip.addElement(aLabel);
//			uip.addElement(bLabel);
//			uip.addElement(cLabel);
//			uiaMenu.addItem(titleLabel, uip);

			UIEmoLabel emoLabel = new UIEmoLabel("emo:):(eheh:Obene");
			emoLabel.setAnchorPoint(Graphics.RIGHT);
			// UIEmoLabel emoLabel = new UIEmoLabel("emo eheh bene");
			emoLabel.setFocusable(true);
			this.append(emoLabel);
			UIMenu um = new UIMenu("MENU");
			for (int i = 0; i < 3; i++) {
				// UIMenuItem uim = new UIMenuItem("Menu Item " + i, null);
				UILabel uim = new UILabel("Menu Item " + i);
				um.append(uim);
			}
			um.append(activeItem);
			UITextField uit1 = new UITextField("label", "text", 50,
					TextField.EMAILADDR);
			this.append(uit1);
			UIMenu pop1 = new UIMenu("Popup-A");
			pop1.setWidth(190);
			for (int i = 0; i < 3; i++) {
				// UIMenuItem uimi = new UIMenuItem("Popup-A" + i, null);
				UILabel uimi = new UILabel("Popup-A" + i);
				pop1.append(uimi);
			}

			UIMenu tempPop = pop1;
			for (int l = 0; l <= 4; l++) {
				UIMenu pop2 = new UIMenu("Popup-B");
				for (int i = 0; i < 3; i++) {
					UILabel uimi = new UILabel("Popup-B" + i);
					pop2.append(uimi);
				}
				UILabel uimi1 = new UILabel("Popup img");
				uimi1.setSubmenu(pop2);
				tempPop.append(uimi1);
				tempPop = pop2;
			}
			UILabel uim = new UILabel("Menu Item Img ");
			uim.setSubmenu(pop1);

			UIMenu popAc = new UIMenu("Popup Ac");
			popAc.setAutoClose(false);
			//popAc.append(uiaMenu);
			UILabel uiac = new UILabel("Accordion Menu ");
			uiac.setSubmenu(popAc);

			um.append(uiac);
			um.append(uim);
			um.append(cmd_debug);

			String veryLongLabel = "veryLongLabel ";
			for (int i = 1; i <= 7; i++)
				veryLongLabel = i + veryLongLabel + veryLongLabel;
			UITextField textBox = new UITextField("title", veryLongLabel,
					50000, TextField.ANY);
			this.append(textBox);

			veryLongLabel = "veryLongLabel ";
			for (int i = 1; i <= 4; i++)
				veryLongLabel = i + veryLongLabel + veryLongLabel;
			UITextField longPanel = new UITextField("Mylabel", veryLongLabel,
					50000, TextField.ANY);
			longPanel.setWrappable(true);
			longPanel.setMaxHeight(70);

			longPanel.setText(veryLongLabel);
			this.append(longPanel);

			UITextPanel shortPanel = new UITextPanel();
			shortPanel.setMaxHeight(50);
			String shortLabel = "veryShortLabel ";
			shortPanel.setText(shortLabel);
			this.append(shortPanel);

			this.setMenu(um);
			try {
				imgs = new Image[] {
						Image.createImage("/icons/presence_online.png"),
						Image.createImage("/icons/presence_dnd.png"),
						Image.createImage("/icons/presence_away.png"),
						Image.createImage("/icons/presence_unavailable.png"),
						Image.createImage("/icons/error.png"), };
			} catch (IOException e) {
			}
			this
					.setTitle("H*W=" + canvas.getHeight() + "*"
							+ canvas.getWidth());

			// UIGauge ug = new UIGauge("Test progress", false,
			// Gauge.INDEFINITE, Gauge.CONTINUOUS_RUNNING);
			// ug.setFocusable(true);
			// append(ug);

			checkBox2.setChecked(true);
			this.append(checkBox);
			this.append(checkBox2);
			uit1 = new UITextField("", "text", 50, TextField.EMAILADDR);
			this.append(uit1);

			veryLongLabel = "veryLongLabel ";
			for (int i = 1; i <= 5; i++)
				veryLongLabel = veryLongLabel + veryLongLabel;
			uit1 = new UITextField("", veryLongLabel, 50000, TextField.ANY);
			this.append(uit1);
			for (int i = 1; i <= 5; i++)
				veryLongLabel = veryLongLabel + veryLongLabel;
			uit1 = new UITextField("", veryLongLabel, 50000, TextField.ANY);
			this.append(uit1);

			UIButton button = new UIButton("prova button");
			this.append(button);

			for (int i = 0; i < 6; i++) {
				// UILabel label = new UILabel("Label:" + i, screen);
				// screen.append(label);
				// UIPicture picture = new UIPicture(imgs[i % 4], screen);
				// screen.append(picture);
				UILabel labelPicture = new UILabel(imgs[(i + 1) % 4],
						"LabelPicture:" + i);
				this.append(labelPicture);
				UIMenu contMenu2 = new UIMenu("Cont2");
				contMenu2.append(new UILabel("FIRST"));
				if (i % 2 == 0) contMenu2.append(new UILabel("SECOND"));
				contMenu2.setWidth(150);
				if (i % 3 == 0) contMenu2.setWidth(0);
				labelPicture.setSubmenu(contMenu2);
			}

			UILabel label1 = new UILabel("Horizontal");
			UILabel picture2 = new UILabel(imgs[1]);
			contMenu = new UIMenu("Cont");
			contMenu.append(new UILabel("First"));
			contMenu.append(new UILabel("Second"));
			contMenu.setWidth(150);
			picture2.setSubmenu(contMenu);
			UILabel label2 = new UILabel("layout");
			UIHLayout uhl = new UIHLayout(4);
			UIButton buttonLayout = new UIButton("button2");
			Font bigFont = Font.getFont(Font.FACE_SYSTEM, Font.STYLE_UNDERLINED
					| Font.STYLE_ITALIC, Font.SIZE_LARGE);
			buttonLayout.setFont(bigFont);
			uhl.insert(label1, 0, 33, UILayout.CONSTRAINT_PERCENTUAL);
			uhl.insert(picture2, 1, 30, UILayout.CONSTRAINT_PIXELS);
			uhl.insert(buttonLayout, 2, 33, UILayout.CONSTRAINT_PERCENTUAL);
			uhl.insert(label2, 3, 33, UILayout.CONSTRAINT_PERCENTUAL);
			// this.append(uhl);

			UIButton buttonLayout2 = new UIButton("buttona");
			UILabel picture5 = new UILabel(imgs[1]);
			UIButton buttonLayout3 = new UIButton("buttonb");
			UICombobox uic = new UICombobox("Combo0", false);
			uic.append("combo1");
			uic.append("combo2");
			uic.append("combo3");
			UIHLayout uhl2 = new UIHLayout(4);
			uhl2.insert(buttonLayout2, 0, 25, UILayout.CONSTRAINT_PERCENTUAL);
			uhl2.insert(picture5, 1, 25, UILayout.CONSTRAINT_PERCENTUAL);
			uhl2.insert(buttonLayout3, 2, 25, UILayout.CONSTRAINT_PERCENTUAL);
			uhl2.insert(uic, 3, 25, UILayout.CONSTRAINT_PERCENTUAL);
			UIHLayout uhl3 = new UIHLayout(3);
			// UIButton bigButton = new UIButton("A very long text button");
			UIButton bigButton = new UIButton("A very long text button");
			bigButton.setWrappable(true, 70);
			UIButton normalButton = new UIButton("Normal button");
			UIButton imageButton = new UIButton("Image button");
			uhl3.insert(bigButton, 0, 70, UILayout.CONSTRAINT_PIXELS);
			uhl3.insert(normalButton, 1, 50, UILayout.CONSTRAINT_PERCENTUAL);
			uhl3.insert(imageButton, 2, 50, UILayout.CONSTRAINT_PERCENTUAL);
			imageButton.setImg(imgs[1]);

			UILabel label3 = new UILabel("Vertical");
			label3.setFocusable(true);
			UILabel picture3 = new UILabel(imgs[1]);
			UILabel label4 = new UILabel("layout");
			UIVLayout uvl = new UIVLayout(6, 230);
			uvl.insert(uhl3, 0, 40, UILayout.CONSTRAINT_PERCENTUAL);
			uvl.insert(picture3, 1, 20, UILayout.CONSTRAINT_PIXELS);
			uvl.insert(uhl, 2, 15, UILayout.CONSTRAINT_PERCENTUAL);
			uvl.insert(label4, 3, 10, UILayout.CONSTRAINT_PERCENTUAL);
			uvl.insert(uhl2, 4, 20, UILayout.CONSTRAINT_PERCENTUAL);
			uvl.insert(label3, 5, 35, UILayout.CONSTRAINT_PERCENTUAL);
			this.append(uvl);

			String[] strings = new String[] { "check1", "check2", "check3" };
			UIRadioButtons urb = new UIRadioButtons(strings);
			this.append(urb);

			{
				UILabel bigPicture1 = new UILabel(imgs[4], "Big:");
				bigPicture1.setFocusable(true);
				bigPicture1.setAnchorPoint(Graphics.LEFT);
				bigPicture1.setFlip(false);
				Font bigFont1 = Font.getFont(Font.FACE_PROPORTIONAL,
						Font.STYLE_ITALIC, Font.SIZE_LARGE);
				bigPicture1.setFont(bigFont1);
				this.append(bigPicture1);

				UILabel bigPicture2 = new UILabel(imgs[4], "Big:");
				bigPicture2.setFocusable(true);
				bigPicture2.setAnchorPoint(Graphics.HCENTER);
				bigPicture2.setFlip(false);
				Font bigFont2 = Font.getFont(Font.FACE_PROPORTIONAL,
						Font.STYLE_BOLD, Font.SIZE_LARGE);
				bigPicture2.setFont(bigFont2);
				this.append(bigPicture2);

				UILabel bigPicture3 = new UILabel(imgs[4], "Big:");
				bigPicture3.setFocusable(true);
				bigPicture3.setAnchorPoint(Graphics.RIGHT);
				bigPicture3.setFlip(false);
				Font bigFont3 = Font.getFont(Font.FACE_MONOSPACE,
						Font.STYLE_UNDERLINED, Font.SIZE_LARGE);
				bigPicture3.setFont(bigFont3);
				this.append(bigPicture3);

				UILabel bigPicture4 = new UILabel(imgs[4], "Big:");
				bigPicture4.setFocusable(true);
				bigPicture4.setAnchorPoint(Graphics.LEFT);
				bigPicture4.setFlip(true);
				Font bigFont4 = Font.getFont(Font.FACE_MONOSPACE,
						Font.STYLE_PLAIN, Font.SIZE_SMALL);
				bigPicture4.setFont(bigFont4);
				this.append(bigPicture4);

				UILabel bigPicture5 = new UILabel(imgs[4], "Big:");
				bigPicture5.setFocusable(true);
				bigPicture5.setAnchorPoint(Graphics.HCENTER);
				bigPicture5.setFlip(true);
				Font bigFont5 = Font.getFont(Font.FACE_SYSTEM,
						Font.STYLE_PLAIN, Font.SIZE_MEDIUM);
				bigPicture5.setFont(bigFont5);
				this.append(bigPicture5);

				UILabel bigPicture6 = new UILabel(imgs[4], "Big:");
				bigPicture6.setFocusable(true);
				bigPicture6.setAnchorPoint(Graphics.RIGHT);
				bigPicture6.setFlip(true);
				Font bigFont6 = Font.getFont(Font.FACE_SYSTEM,
						Font.STYLE_ITALIC, Font.SIZE_MEDIUM);
				bigPicture6.setFont(bigFont6);
				this.append(bigPicture6);
			}

			{
				UILabel bigPicture1 = new UILabel(imgs[4], "Big:");
				bigPicture1.setFocusable(true);
				bigPicture1.setAnchorPoint(Graphics.LEFT);
				bigPicture1.setFlip(false);

				UILabel hlabel = new UILabel("Horizontal");

				UICombobox bc = new UICombobox("Combo1", false);
				bc.append("combobig1");
				bc.append("combobig2");
				bc.append("combobig3");

				UIHLayout bighl = new UIHLayout(3);
				bighl
						.insert(bigPicture1, 0, 33,
								UILayout.CONSTRAINT_PERCENTUAL);
				bighl.insert(hlabel, 1, 60, UILayout.CONSTRAINT_PIXELS);
				bighl.insert(bc, 2, 33, UILayout.CONSTRAINT_PERCENTUAL);
				this.append(bighl);

			}

			{
				UILabel bigPicture1 = new UILabel(imgs[4], "");
				bigPicture1.setFocusable(true);
				bigPicture1.setAnchorPoint(Graphics.LEFT);
				bigPicture1.setFlip(false);
				this.append(bigPicture1);

				UILabel bigPicture2 = new UILabel(imgs[4], "");
				bigPicture2.setFocusable(true);
				bigPicture2.setAnchorPoint(Graphics.HCENTER);
				bigPicture2.setFlip(false);
				this.append(bigPicture2);

				UILabel bigPicture3 = new UILabel(imgs[4], "");
				bigPicture3.setFocusable(true);
				bigPicture3.setAnchorPoint(Graphics.RIGHT);
				bigPicture3.setFlip(false);
				this.append(bigPicture3);

			}

			{
				UILabel bigPicture1 = new UILabel("Big:");
				bigPicture1.setFocusable(true);
				bigPicture1.setAnchorPoint(Graphics.LEFT);
				bigPicture1.setFlip(false);
				this.append(bigPicture1);

				UILabel bigPicture2 = new UILabel("Big:");
				bigPicture2.setFocusable(true);
				bigPicture2.setAnchorPoint(Graphics.HCENTER);
				bigPicture2.setFlip(false);
				this.append(bigPicture2);

				UILabel bigPicture3 = new UILabel("Big:");
				bigPicture3.setFocusable(true);
				bigPicture3.setAnchorPoint(Graphics.RIGHT);
				bigPicture3.setFlip(false);
				this.append(bigPicture3);

			}

			UICombobox uic2 = new UICombobox("Combo2", false);
			uic2.append("combo1-2");
			uic2.append("combo2-2");
			uic2.append("combo3-2");
			append(uic2);

			UICombobox uic3 = new UICombobox("Combo3", true);
			uic3.append("combo3-1");
			uic3
					.append("combo2-2longlonglonglonglonglonglonglonglonglonglonglong");
			uic3.append("combo3-3");
			uic3.append("combo3-4");
			uic3.append("combo3-5");

			append(uic3);

			UILabel longLabel = new UILabel(
					"This is a very long string that is not wrapped and hence SHOULD finish with thre full stops!!!!");
			longLabel.setFocusable(true);
			longLabel.setAnchorPoint(Graphics.LEFT);
			longLabel.setFlip(false);
			longLabel.setWrappable(false, 0);
			this.append(longLabel);

			String longString = "The quick brown fox jumps over the lazy dog. And this is a_very_long_word. ";
			longString += longString;
			longString += longString;
			UILabel longLabel2 = new UILabel(longString);
			longLabel2.setFocusable(true);
			longLabel2.setAnchorPoint(Graphics.LEFT);
			longLabel2.setFlip(false);
			longLabel2.setWrappable(true, 200);
			this.append(longLabel2);

			UILabel longLabel3 = new UILabel(longString);
			longLabel3.setFocusable(true);
			longLabel3.setAnchorPoint(Graphics.HCENTER);
			longLabel3.setFlip(false);
			longLabel3.setWrappable(true, 200);
			this.append(longLabel3);

			UILabel longLabel4 = new UILabel(longString);
			longLabel4.setFocusable(true);
			longLabel4.setAnchorPoint(Graphics.RIGHT);
			longLabel4.setFlip(false);
			longLabel4.setWrappable(true, 200);
			this.append(longLabel4);

			for (int i = 0; i < 30; i++) {
				// UILabel label = new UILabel("Label:" + i, screen);
				// screen.append(label);
				// UIPicture picture = new UIPicture(imgs[i % 4], screen);
				// screen.append(picture);
				UILabel labelPicture = new UILabel(imgs[(i + 1) % 4],
						"LabelPicture:" + i);
				if (i < 25) labelPicture.setFocusable(true);
				this.append(labelPicture);
			}

		}

		private void buildScreen2() {
			UIVLayout uv = new UIVLayout(2, -1);
			uv.setGroup(true);
			UILabel hdr = new UILabel("HEADER");
			hdr.setFocusable(false);
			hdr.setAnchorPoint(Graphics.HCENTER);
			uv.insert(hdr, 0, 20, UILayout.CONSTRAINT_PIXELS);
			UIPanel up = new UIPanel();
			// up.setMaxHeight(100);
			for (int i = 0; i < 50; i++) {
				UILabel it = new UILabel("item #" + i);
				it.setFocusable((i % 2) == 0);
				up.setDirty(true);
				up.addItem(it);
			}
			uv.insert(up, 1, 100, UILayout.CONSTRAINT_PERCENTUAL);
			// UILabel hdr2 = new UILabel("FOOTER");
			// hdr2.setFocusable(false);
			// hdr2.setAnchorPoint(Graphics.HCENTER);
			// uv.insert(hdr2, 2, 20, UILayout.CONSTRAINT_PIXELS);
			append(uv);
		}

		public void itemAction(UIItem item) {
			if ((/* item == this.checkBox || */item == this.checkBox2)
					&& ((UICheckbox) item).isChecked()) {
				// UIMenu newPopUp = new UIMenu("Hey!");

				// UILabel uimi = new UILabel("It pops up on click");
				// newPopUp.append(uimi);
				// newPopUp.setWidth(canvas.getWidth() - 20);
				// newPopUp.setAbsoluteX(10);
				// newPopUp.setAbsoluteY(canvas.getClipHeight() / 3);
				// this.addPopup(newPopUp);

				UIMenu bigMenu2 = new UIMenu("Popup-B");
				bigMenu2.setWidth(this.width - 50);
				bigMenu2.setAbsoluteY(180);
				bigMenu2.setAbsoluteX(25);
				for (int i = 0; i < 20; i++) {
					// UIMenuItem uimi = new UIMenuItem("Popup-A" + i, null);
					UILabel uimi = new UILabel("Bigmenu2-" + i);
					// uimi.setFocusable(true);
					bigMenu2.append(uimi);
				}

				this.addPopup(bigMenu2);

				UIMenu bigMenu1 = new UIMenu("Popup-A");
				bigMenu1.setWidth(this.width - 20);
				bigMenu1.setAbsoluteY(200);
				bigMenu1.setAbsoluteX(3);
				for (int i = 0; i < 7; i++) {
					// UIMenuItem uimi = new UIMenuItem("Popup-A" + i, null);
					UILabel uimi = new UILabel("Bigmenu1" + i);
					// uimi.setFocusable(true);
					bigMenu1.append(uimi);
				}
				this.addPopup(bigMenu1);
			}
		}

		public void menuAction(UIMenu menu, UIItem item) {
			if (item == activeItem) {
				this.checkBox.setChecked(!this.checkBox.isChecked());
			}
			if (menu == this.contMenu) {
				System.out.println("ContMenu has been pressed: "
						+ ((UILabel) item).getText());
			}
			if (item == cmd_debug) {
				DebugScreen debugScreen = new DebugScreen();
				UICanvas.getInstance().open(debugScreen, true);
			}
		}
	}

	private class KeysScreen extends Canvas {

		private int state = 0;

		private String err = null;

		public int left_key = 0;
		public int right_key = 0;

		public KeysScreen() {
			setFullScreenMode(true);
		}

		protected void paint(Graphics g) {
			g.setColor(0x00ffffff);
			g.fillRect(0, 0, g.getClipWidth(), g.getClipHeight());
			g.setColor(0);

			if (state == 0) {
				g.drawString("Press left key", 5, 5, Graphics.TOP
						| Graphics.LEFT);
			} else if (state == 1) {
				g.drawString("Press right key", 5, 5, Graphics.TOP
						| Graphics.LEFT);
			} else if (state == 2) {
				g.drawString("Press any key to continue", 5, 5, Graphics.TOP
						| Graphics.LEFT);
			}

			if (err != null) {
				g.setColor(0x00a00000);
				g.drawString(err, 5, 25, Graphics.TOP | Graphics.LEFT);
			}
		}

		protected void keyPressed(int k) {
			if (state == 2) {
				UICanvas.setMenuKeys(left_key, right_key);
				Display.getDisplay(UITestMidlet.this).setCurrent(canvas);
				TestScreen screen = new TestScreen();
				canvas.open(screen, true);
				UICanvas.getInstance().askRepaint(screen);
				screen.buildScreen();
				UICanvas.getInstance().askRepaint(screen);
				return;
			}

			try {
				if (k > 0) {
					err = "Not a valid key";
					return;
				}
				if (getGameAction(k) != 0) {
					err = "Not a valid key";
					return;
				}

				switch (state) {
					case 0:
						left_key = k;
						state = 1;
						break;
					case 1:
						right_key = k;
						state = 2;
						break;
				}
			} finally {
				repaint();
			}
		}

	}
}
