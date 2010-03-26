/* Copyright (c) 2008 Bluendo S.r.L.
 * See about.html for details about license.
 *
 * $Id: AboutScreen.java 1028 2008-12-09 15:44:50Z luca $
*/

package it.yup.screens;

import lampiro.LampiroMidlet;

import javax.microedition.lcdui.Canvas;
import javax.microedition.lcdui.Command;
import javax.microedition.lcdui.CommandListener;
import javax.microedition.lcdui.Displayable;
import javax.microedition.lcdui.Font;
import javax.microedition.lcdui.Graphics;
import javax.microedition.lcdui.Image;

public class AboutScreen extends Canvas implements CommandListener {

	Image logo;

	private static String[] lines = { "Mobile Messaging", "", "(c) 2007-2008 Bluendo srl", "http://www.bluendo.com", "", "version 1.0", };

	private static Command cmd_ok = new Command("OK", Command.SCREEN, 1);

	public AboutScreen() {
		try {
			logo = Image.createImage("/icons/lampiro_icon.png");
		} catch (Exception ex) {
		}
		addCommand(cmd_ok);
		setCommandListener(this);
	}

	protected void paint(Graphics g) {
		int w = g.getClipWidth();
		int h = g.getClipHeight();

		setTitle("About");

		Font f = Font.getFont(Font.FACE_PROPORTIONAL, Font.STYLE_BOLD,
								Font.SIZE_SMALL);
		g.setColor(0xCBDBE3);
		g.fillRect(0, 0, w, h);

		// compute text heigth
		int text_height = lines.length * f.getHeight();
		int hspacing = (h - text_height - logo.getHeight()) / 3;

		if (text_height + logo.getHeight() <= h) {
			Font fnt = Font.getFont(Font.FACE_PROPORTIONAL, Font.STYLE_BOLD,
									Font.SIZE_MEDIUM);
			g.setFont(fnt);
			g.setColor(0x00FC9A13);
			int wspacing = (w - fnt.stringWidth("Lampiro") - logo.getWidth()) / 3;
			g.drawImage(logo, wspacing, hspacing, Graphics.TOP | Graphics.LEFT);
			g.drawString("Lampiro", wspacing * 2 + logo.getWidth(), hspacing
					+ logo.getHeight() / 2 - fnt.getHeight() / 2, Graphics.TOP
					| Graphics.LEFT);
		} else {
			Font fnt = Font.getFont(Font.FACE_PROPORTIONAL, Font.STYLE_BOLD,
									Font.SIZE_LARGE);
			g.setFont(fnt);
			g.setColor(0x00FC9A13);
			g.drawString("Lampiro", w / 2, 10, Graphics.TOP | Graphics.HCENTER);
		}

		int y = hspacing * 2 + logo.getHeight();

		g.setFont(f);
		g.setColor(0x00151562);
		for (int i = 0; i < lines.length; i++) {
			g.drawString(lines[i], w / 2, y, Graphics.TOP | Graphics.HCENTER);
			y += f.getHeight();
		}
	}

	public void commandAction(Command cmd, Displayable disp) {
		if (cmd == cmd_ok) {
			LampiroMidlet.disp.setCurrent(RosterScreen.getInstance());
		}
	}

}
