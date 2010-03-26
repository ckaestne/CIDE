/*
 * Created on Sep 13, 2004
 *
 */
package lancs.mobilemedia.core.ui.screens;

import javax.microedition.lcdui.Choice;
import javax.microedition.lcdui.Command;
import javax.microedition.lcdui.List;


/**
 * @author trevor
 *
 * This screen displays a list of photo albums available to select.
 * A user can also create a new album on this screen.
 */
public class AlbumListScreen extends List {
	
	//#if includeMusic && includePhotoAlbum
	//[NC] Added in the scenario 07
//	public static final Command exitCommand = new Command("Back", Command.STOP, 2);
	//#elif includePhotoAlbum || includeMusic
	public static final Command exitCommand = new Command(true?"Back":"Exit", Command.STOP, 2);
	//#endif
	public static final Command selectCommand = new Command("Select", Command.ITEM, 1);
	public static final Command createAlbumCommand = new Command("New Album", Command.ITEM, 1);
	public static final Command deleteAlbumCommand = new Command("Delete Album", Command.ITEM, 1);
	public static final Command resetCommand = new Command("Reset", Command.ITEM, 1);
	 

	/**
	 * Constructor
	 */
	public AlbumListScreen() {
		super("Select Album", Choice.IMPLICIT);
	}

	/**
	 * Initialize the menu items for this screen
	 * 
	 */
	public void initMenu() {
		//#if includeMusic || includePhotoAlbum
		this.addCommand(exitCommand);
		//#endif
		this.addCommand(selectCommand);
		this.addCommand(createAlbumCommand);
		this.addCommand(deleteAlbumCommand);
		this.addCommand(resetCommand);
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
	public void repaintListAlbum(String[] names){
		String[] albumNames = names;
	    this.deleteAll();
		for (int i = 0; i < albumNames.length; i++) {
			if (albumNames[i] != null) {
				//Add album name to menu list
				this.append(albumNames[i], null);
			}
		}
	}
}
