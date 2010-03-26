/* Copyright (c) 2008 Bluendo S.r.L.
 * See about.html for details about license.
 *
 * $Id: SplashScreen.java 1272 2009-03-13 14:05:51Z luca $
*/

package it.yup.screens;

import java.util.TimerTask;

import it.yup.util.Utils;

import javax.microedition.lcdui.Canvas;
import javax.microedition.lcdui.Font;
import javax.microedition.lcdui.Graphics;
import javax.microedition.lcdui.Image;

import lampiro.LampiroMidlet;

public class SplashScreen extends Canvas {
	
	private Image logo;
	// private String message = null;
	
	private boolean first_time = true;
	
	public SplashScreen() {
		try {
			logo = Image.createImage("/icons/lampiro_icon.png");
    	} catch(Exception ex) { }
	}

	protected void paint(Graphics g) {
		

        int w = g.getClipWidth();
        int h = g.getClipHeight();
        
	    setTitle("Lampiro");
	    	
	    Font f = Font.getFont(Font.FACE_PROPORTIONAL, Font.STYLE_BOLD, Font.SIZE_LARGE);
	    g.setColor(0xCBDBE3);
	    g.fillRect(0, 0, w, h);
	    g.setFont(f);
	    
//	    if(message != null) {
//	    	g.setColor(0x00FC9A13);
//	    	g.drawString(message, 0, 0, Graphics.TOP | Graphics.LEFT);
//	    }
	    
	    // compute text heigth
	    int text_height = f.getHeight();
	    int hspacing = (h - text_height - logo.getHeight()) / 3;
	    
	    if(text_height + logo.getHeight() <= h) { 	
	    	g.setColor(0x00FC9A13);
	    	g.drawImage(logo, w/2, hspacing, Graphics.TOP | Graphics.HCENTER);
	    	g.drawString("Loading Lampiro...", w/2, hspacing *2 + logo.getHeight(),  Graphics.TOP | Graphics.HCENTER);
	    } else {
	    	g.drawImage(logo, w/2, h/2, Graphics.VCENTER | Graphics.HCENTER);
	    }
	    
	    if(first_time) {
	    	first_time = false;
	    	Utils.tasks.schedule(new TimerTask() {
				public void run() {
					RegisterScreen rgs = RegisterScreen.getInstance();
					LampiroMidlet.disp.setCurrent(rgs);
				}
	    		
	    	}, 3000);
	    }
	}
	
	protected void keyPressed(int kc) {
		// message = getKeyName(kc);
		repaint();
	}
}
