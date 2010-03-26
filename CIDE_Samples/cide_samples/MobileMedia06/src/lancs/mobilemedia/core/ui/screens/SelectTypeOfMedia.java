// #if includeMusic && includePhotoAlbum
// [NC] Added in the scenario 07

package lancs.mobilemedia.core.ui.screens;

import javax.microedition.lcdui.Choice;
import javax.microedition.lcdui.Command;
import javax.microedition.lcdui.Image;
import javax.microedition.lcdui.List;

public class SelectTypeOfMedia extends List {

	public static final Command exitCommand = new Command("Exit", Command.STOP, 2);
	public static final Command selectCommand = new Command("Select", Command.ITEM, 1);


	/**
	 * Constructor
	 */
	public SelectTypeOfMedia() {
		super("Select the media to Use", Choice.IMPLICIT);
	}


	/**
	 * Constructor
	 * @param arg0
	 * @param arg1
	 */
	public SelectTypeOfMedia(String arg0, int arg1) {
		super(arg0, arg1);
	}

	/**
	 * Constructor
	 * @param arg0
	 * @param arg1
	 * @param arg2
	 * @param arg3
	 */
	public SelectTypeOfMedia(String arg0, int arg1, String[] arg2, Image[] arg3) {
		super(arg0, arg1, arg2, arg3);
	}
	
	/**
	 * Initialize the menu items for this screen
	 * 
	 */
	public void initMenu() {
		this.addCommand(exitCommand);
		this.addCommand(selectCommand);
		repaintListMedias();
	}
	
	/* (non-Javadoc)
	 * @see javax.microedition.lcdui.List#deleteAll()
	 */
	public void deleteAll(){
		for (int i = 0; i < this.size(); i++) {
			this.delete(i);
		} 
	}
	
	/**
	 * @param names
	 */
	public void repaintListMedias(){
	    this.deleteAll();
		this.append("Photos", null);
		this.append("Music and Videos", null);
	}

}
//#endif