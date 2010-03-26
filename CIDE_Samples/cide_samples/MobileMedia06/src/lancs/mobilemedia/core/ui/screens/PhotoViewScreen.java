// #ifdef includePhotoAlbum
// [NC] Added in the scenario 07

package lancs.mobilemedia.core.ui.screens;
import javax.microedition.lcdui.Canvas;
import javax.microedition.lcdui.Command;
import javax.microedition.lcdui.Graphics;
import javax.microedition.lcdui.Image;

import lancs.mobilemedia.core.ui.datamodel.AlbumData;
import lancs.mobilemedia.core.util.Constants;


/**
 * This screen displays a selected image.
  */
public class PhotoViewScreen extends Canvas {

//	String imageName = "";
	Image image;
	AlbumData model = null;
    
	public static final Command backCommand = new Command("Back", Command.BACK, 0);

	// #if includeCopyPhoto || includeSmsFeature
	/* [EF] Added in scenario 05 */
	public static final Command copyCommand = new Command("Copy", Command.ITEM, 1);
	// #endif
	
	// #ifdef includeSmsFeature
	/* [NC] Added in scenario 06 */
	public static final Command smscopyCommand = new Command("Send Photo by SMS", Command.ITEM, 1);
	private boolean fromSMS = false;
	// #endif

	/**
	 * Constructor
	 * @param img
	 */
	public PhotoViewScreen(Image img) {
		//Instead of loading it from a list, pass the image in directly
		image = img;
		this.addCommand(backCommand);
		
		// #if includeCopyPhoto  || includeSmsFeature
		this.addCommand(copyCommand);
		// #endif
		
		//#ifdef includeSmsFeature
		/* [NC] Added in scenario 06 */
		this.addCommand(smscopyCommand);
		// #endif
	}
	
	/*
	 *  (non-Javadoc)
	 * @see javax.microedition.lcdui.Canvas#paint(javax.microedition.lcdui.Graphics)
	 */
	protected void paint(Graphics g) {
	    g.setGrayScale (255);
	    //Draw the image - for now start drawing in top left corner of screen
	    g.fillRect (0, 0, Constants.SCREEN_WIDTH, Constants.SCREEN_HEIGHT);
	    System.out.println("Screen size:"+Constants.SCREEN_WIDTH+":"+ Constants.SCREEN_HEIGHT);
	    if (image == null) 
	    	System.out.println("PhotoViewScreen::paint(): Image object was null.");
	    g.drawImage (image, 0, 0, Graphics.TOP | Graphics.LEFT);
	}

	// #ifdef includeSmsFeature
	/* [NC] Added in scenario 06 */
	public Image getImage(){
		return image;
	}
	
	public boolean isFromSMS() {
		return fromSMS;
	}
	
	public void setFromSMS(boolean fromSMS) {
		this.fromSMS = fromSMS;
	}
	// #endif
}
//#endif