/* Copyright (c) 2008 Bluendo S.r.L.
 * See about.html for details about license.
 *
 * $Id: ScreenSaver.java 1028 2008-12-09 15:44:50Z luca $
*/

package it.yup.screens;

import lampiro.LampiroMidlet;

import java.io.IOException;

import javax.microedition.lcdui.Canvas;
import javax.microedition.lcdui.Displayable;
import javax.microedition.lcdui.Graphics;
import javax.microedition.lcdui.Image;

public class ScreenSaver extends Canvas {

	private Displayable return_to;
	private static Image img_lampiro;
	private boolean first_call;

	static {
		try {
			img_lampiro = Image.createImage("/icons/lampiro_icon.png");
		} catch (IOException e) {
			img_lampiro = null;
		}
	}

	public ScreenSaver(Displayable return_to) {
		this.return_to = return_to;
	}

	protected void showNotify() {
		first_call = true;
	}

	protected void paint(Graphics g) {

		if (first_call) {
			g.setColor(0x555555);
			int w = g.getClipWidth();
			int h = g.getClipHeight();
			g.fillRect(0, 0, w, h);
			g.drawImage(img_lampiro, w / 2, h / 2, Graphics.HCENTER
					| Graphics.VCENTER);
			first_call = false;
		}
	}

	protected void keyPressed(int k) {
		LampiroMidlet.disp.setCurrent(return_to);
	}
}
