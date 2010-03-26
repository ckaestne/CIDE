package lancs.mobilemedia.core.ui.screens;

import java.util.*;
import javax.microedition.lcdui.*;

/**
 * 
 * @author tyoung
 * This is a splash screen that will display for a brief period when the
 * application loads. It is currently disabled, and hasn't been tested yet.
 */
public class SplashScreen extends Canvas {
	
	private Display display;
	private Displayable next;
	private Timer timer = new Timer();

	/**
	 * Constructor
	 */
	public SplashScreen(Display display, Displayable next) {
		this.display = display;
		this.next = next;

		display.setCurrent(this);
	}

	/*
	 * If a key is pressed, dismiss the splash screen.
	 *  (non-Javadoc)
	 * @see javax.microedition.lcdui.Canvas#keyPressed(int)
	 */
	protected void keyPressed(int keyCode) {
		dismiss();
	}

	protected void paint(Graphics g) {
		// do your drawing here
	}

	protected void pointerPressed(int x, int y) {
		dismiss();
	}

	protected void showNotify() {
		timer.schedule(new CountDown(), 5000);
	}

	private void dismiss() {
		timer.cancel();
		display.setCurrent(next);
	}

	private class CountDown extends TimerTask {
		public void run() {
			dismiss();
		}
	}
}