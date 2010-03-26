// #if (includeMMAPI && includePhotoAlbum)||(includeMMAPI && includeVideo) || (includeVideo && includePhotoAlbum)
// [NC] Added in the scenario 07

package lancs.mobilemedia.core.ui.screens;

import javax.microedition.lcdui.Choice;
import javax.microedition.lcdui.Command;
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
	 // #ifdef includePhotoAlbum
	 // [NC] Added in the scenario 08
		this.append("Photos", null);
		//#endif
		
		// #ifdef includeMMAPI
		// [NC] Added in the scenario 08
		this.append("Music", null);
		//#endif
		
		// #ifdef includeVideo
		// [NC] Added in the scenario 08
		this.append("Videos", null);
		//#endif
	}
}
//#endif