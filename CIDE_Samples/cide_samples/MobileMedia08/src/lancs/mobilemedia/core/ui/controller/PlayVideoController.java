//#ifdef includeVideo
package lancs.mobilemedia.core.ui.controller;

import javax.microedition.lcdui.Alert;
import javax.microedition.lcdui.AlertType;
import javax.microedition.lcdui.Command;
import javax.microedition.lcdui.Display;
import javax.microedition.lcdui.List;
import javax.microedition.rms.RecordStoreFullException;

import lancs.mobilemedia.core.ui.MainUIMidlet;
import lancs.mobilemedia.core.ui.datamodel.AlbumData;
import lancs.mobilemedia.core.ui.datamodel.MediaData;
import lancs.mobilemedia.core.ui.screens.AddMediaToAlbum;
import lancs.mobilemedia.core.ui.screens.AlbumListScreen;
import lancs.mobilemedia.core.ui.screens.PlayVideoScreen;
import lancs.mobilemedia.core.util.Constants;
import lancs.mobilemedia.lib.exceptions.ImageNotFoundException;
import lancs.mobilemedia.lib.exceptions.ImagePathNotValidException;
import lancs.mobilemedia.lib.exceptions.InvalidImageDataException;
import lancs.mobilemedia.lib.exceptions.PersistenceMechanismException;

public class PlayVideoController extends AbstractController{
	// #ifdef includeCopyPhoto
	// [NC] Added in the scenario 07
	private String mediaName;
	//#endif
	
	private PlayVideoScreen pmscreen;
	
	public PlayVideoController(MainUIMidlet midlet, AlbumData albumData,
			List albumListScreen, PlayVideoScreen pmscreen) {
		super(midlet, albumData, albumListScreen);
		this.pmscreen = pmscreen;
	}

	public boolean handleCommand(Command command) {
		String label = command.getLabel();
		System.out.println( "<* PlayVideoController.handleCommand() *> " + label);

		/** Case: Copy photo to a different album */
		if (label.equals("Start")) {
			pmscreen.startVideo();
			return true;
		} if (label.equals("Stop")) {
			pmscreen.stopVideo();
				return true;
		} if ((label.equals("Back"))||(label.equals("Cancel"))){
			pmscreen.stopVideo();
			// [NC] Changed in the scenario 07: just the first line below to support generic AbstractController
			((AlbumListScreen) getAlbumListScreen()).repaintListAlbum(getAlbumData().getAlbumNames());
			setCurrentScreen( getAlbumListScreen() );
			ScreenSingleton.getInstance().setCurrentScreenName(Constants.ALBUMLIST_SCREEN);
			return true;
		}
		 // #ifdef includeCopyPhoto
	  	 // [NC] Added in the scenario 07
		/** Case: Copy photo to a different album */
		 if (label.equals("Copy")) {
			AddMediaToAlbum copyPhotoToAlbum = new AddMediaToAlbum("Copy Media to Album");
			copyPhotoToAlbum.setItemName(mediaName);
			copyPhotoToAlbum.setLabePath("Copy to Album:");
			copyPhotoToAlbum.setCommandListener(this);
	        Display.getDisplay(midlet).setCurrent(copyPhotoToAlbum);
			
			return true;
		}  if (label.equals("Save Item")) {
			try {
				MediaData imageData = null;	
				try {
						imageData = getAlbumData().getMediaInfo(mediaName);
				} catch (ImageNotFoundException e) {
						Alert alert = new Alert("Error", "The selected photo was not found in the mobile device", null, AlertType.ERROR);
						Display.getDisplay(midlet).setCurrent(alert, Display.getDisplay(midlet).getCurrent());
				}
				String albumname = ((AddMediaToAlbum) getCurrentScreen()).getPath();
				getAlbumData().addMediaData(imageData, albumname); 
			} catch (InvalidImageDataException e) {
				Alert alert = null;
				if (e instanceof ImagePathNotValidException)
					alert = new Alert("Error", "The path is not valid", null, AlertType.ERROR);
				else
					alert = new Alert("Error", "The music file format is not valid", null, AlertType.ERROR);
				Display.getDisplay(midlet).setCurrent(alert, Display.getDisplay(midlet).getCurrent());
				return true;
				// alert.setTimeout(5000);
			} catch (PersistenceMechanismException e) {
				Alert alert = null;
				if (e.getCause() instanceof RecordStoreFullException)
					alert = new Alert("Error", "The mobile database is full", null, AlertType.ERROR);
				else
					alert = new Alert("Error", "The mobile database can not add a new music", null, AlertType.ERROR);
				Display.getDisplay(midlet).setCurrent(alert, Display.getDisplay(midlet).getCurrent());
			}
			// [NC] Changed in the scenario 07: just the first line below to support generic AbstractController
			((AlbumListScreen) getAlbumListScreen()).repaintListAlbum(getAlbumData().getAlbumNames());
			setCurrentScreen( getAlbumListScreen() );
			ScreenSingleton.getInstance().setCurrentScreenName(Constants.ALBUMLIST_SCREEN);
			return true;
		}
		//#endif
		
		return false;
	}
	
	// #ifdef includeCopyPhoto
	// [NC] Added in the scenario 07
	public String getMediaName() {
		return mediaName;
	}

	public void setMediaName(String mediaName) {
		this.mediaName = mediaName;
	}
	//#endif
}
//#endif
