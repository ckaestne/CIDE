// #if includeMusic && includePhotoAlbum
// [NC] Added in the scenario 07

package lancs.mobilemedia.core.ui.controller;


import javax.microedition.lcdui.Command;

import javax.microedition.lcdui.Display;

import javax.microedition.lcdui.List;

import lancs.mobilemedia.core.ui.MainUIMidlet;
import lancs.mobilemedia.core.ui.datamodel.AlbumData;



public class SelectMediaController extends AbstractController {
	BaseController imageController;
	BaseController musicController;
	AlbumData imageAlbumData;
	AlbumData musicAlbumData;

	public SelectMediaController(MainUIMidlet midlet, AlbumData imageAlbumData,AlbumData musicAlbumData,
			List albumListScreen, BaseController imageController, BaseController musicController) {
		super(midlet, imageAlbumData, albumListScreen);
		this.imageController = imageController;
		this.musicController = musicController; 
		this.imageAlbumData = imageAlbumData;
		this.musicAlbumData = musicAlbumData;
	}
	public boolean handleCommand(Command command) {
		String label = command.getLabel();
      	System.out.println( "<* SelectMediaController.handleCommand() *>: " + label);
		
      	if (label.equals("Select")) {
 			List down = (List) Display.getDisplay(midlet).getCurrent();
 			if (down.getString(down.getSelectedIndex()).equals("Photos"))
 				imageController.init(imageAlbumData);
 			else
 				musicController.init(musicAlbumData);
 				
			return true;	
      	}
      	return false;
	}

}
//#endif