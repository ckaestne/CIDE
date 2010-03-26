/*
 * Lancaster University
 * Computing Department
 * 
 * Created by Eduardo Figueiredo
 * Date: 22 Jul 2007
 * 
 */
// #if includeCopyPhoto || includeSmsFeature || capturePhoto

package lancs.mobilemedia.core.ui.controller;

import javax.microedition.lcdui.Alert;
import javax.microedition.lcdui.AlertType;
import javax.microedition.lcdui.Command;
import javax.microedition.lcdui.Display;
import javax.microedition.rms.RecordStoreFullException;

import lancs.mobilemedia.core.ui.MainUIMidlet;
import lancs.mobilemedia.core.ui.datamodel.AlbumData;
import lancs.mobilemedia.core.ui.datamodel.MediaData;
import lancs.mobilemedia.core.ui.screens.AddMediaToAlbum;
import lancs.mobilemedia.core.ui.screens.AlbumListScreen;
import lancs.mobilemedia.core.ui.screens.CaptureVideoScreen;
import lancs.mobilemedia.core.ui.screens.PhotoViewScreen;
import lancs.mobilemedia.core.util.Constants;
import lancs.mobilemedia.lib.exceptions.ImageNotFoundException;
import lancs.mobilemedia.lib.exceptions.ImagePathNotValidException;
import lancs.mobilemedia.lib.exceptions.InvalidImageDataException;
import lancs.mobilemedia.lib.exceptions.PersistenceMechanismException;
/**
 * @author Eduardo Figueiredo
 * [EF] Added in Scenario 05
 */
public class PhotoViewController extends AbstractController {

	String imageName = "";
	
	// #ifdef capturePhoto
	// [NC] Added in the scenario 08
	CaptureVideoScreen cpVideoScreen = null;
	//#endif
	
	/**
	 * @param midlet
	 * @param nextController
	 * @param albumData
	 * @param albumListScreen
	 * @param currentScreenName
	 */
	public PhotoViewController(MainUIMidlet midlet, AlbumData albumData, AlbumListScreen albumListScreen, String imageName) {
		super(midlet, albumData, albumListScreen);
		this.imageName = imageName;
	}

	/* (non-Javadoc)
	 * @see ubc.midp.mobilephoto.core.ui.controller.ControllerInterface#handleCommand(javax.microedition.lcdui.Command, javax.microedition.lcdui.Displayable)
	 */
	public boolean handleCommand(Command c) {
		String label = c.getLabel();
		System.out.println( "<* PhotoViewController.handleCommand() *> " + label);

		/** Case: Copy photo to a different album */
		if (label.equals("Copy")) {
			AddMediaToAlbum copyPhotoToAlbum = new AddMediaToAlbum("Copy Photo to Album");
			copyPhotoToAlbum.setItemName(imageName);
			copyPhotoToAlbum.setLabePath("Copy to Album:");
			copyPhotoToAlbum.setCommandListener(this);

			// #ifdef includeSmsFeature
			/* [NC] Added in scenario 06 */
			if (((PhotoViewScreen)this.getCurrentScreen()).isFromSMS())
				{copyPhotoToAlbum.setCapturedMedia(((PhotoViewScreen)this.getCurrentScreen()).getImage());}
			//#endif	
			
	        Display.getDisplay(midlet).setCurrent(copyPhotoToAlbum);
			
			return true;
		}

		/** Case: Save a copy in a new album */
		else if (label.equals("Save Item")) {
			try {
				MediaData imageData = null;	
				// #if includeSmsFeature || capturePhoto
				/* [NC] Added in scenario 06 and changed in scenario 8 */
				byte[] imgByte = ((AddMediaToAlbum)this.getCurrentScreen()).getCapturedMedia();
				
				if (imgByte.length == 0)
				//#endif
				{
					try {
						imageData = getAlbumData().getMediaInfo(imageName);
					} catch (ImageNotFoundException e) {
						Alert alert = new Alert("Error", "The selected photo was not found in the mobile device", null, AlertType.ERROR);
						Display.getDisplay(midlet).setCurrent(alert, Display.getDisplay(midlet).getCurrent());
					}
				}
				
				
				String photoname = ((AddMediaToAlbum) getCurrentScreen()).getItemName();
				String albumname = ((AddMediaToAlbum) getCurrentScreen()).getPath();
				
				// #if includeSmsFeature || capturePhoto
				/* [NC] Added in scenario 06 and changed in scenario 8*/
				if (imgByte.length > 0)
					getAlbumData().addImageData(photoname, imgByte, albumname);
				// #endif 
				
				// #if includeCopyPhoto && (includeSmsFeature || capturePhoto)
				/* [NC] Added in scenario 06 and changed in scenario 8*/
				if (imgByte.length == 0)
				//#endif
					
				// #if includeCopyPhoto 
				/* [NC] Added in scenario 06 */
				getAlbumData().addMediaData(imageData, albumname); 
				// #endif 
			} catch (InvalidImageDataException e) {
				Alert alert = null;
				if (e instanceof ImagePathNotValidException)
					alert = new Alert("Error", "The path is not valid", null, AlertType.ERROR);
				else
					alert = new Alert("Error", "The image file format is not valid", null, AlertType.ERROR);
				Display.getDisplay(midlet).setCurrent(alert, Display.getDisplay(midlet).getCurrent());
				return true;
				// alert.setTimeout(5000);
			} catch (PersistenceMechanismException e) {
				Alert alert = null;
				if (e.getCause() instanceof RecordStoreFullException)
					alert = new Alert("Error", "The mobile database is full", null, AlertType.ERROR);
				else
					alert = new Alert("Error", "The mobile database can not add a new photo", null, AlertType.ERROR);
				Display.getDisplay(midlet).setCurrent(alert, Display.getDisplay(midlet).getCurrent());
			}
			// [NC] Changed in the scenario 07: just the first line below to support generic AbstractController
			((AlbumListScreen) getAlbumListScreen()).repaintListAlbum(getAlbumData().getAlbumNames());
			setCurrentScreen( getAlbumListScreen() );
			ScreenSingleton.getInstance().setCurrentScreenName(Constants.ALBUMLIST_SCREEN);
			return true;
		}
		// #ifdef capturePhoto
		// [NC] Added in the scenario 08	
		 if (label.equals("Take photo")){
			System.out.println("Olha para a captura"+cpVideoScreen);
			byte[] newfoto = cpVideoScreen.takePicture();
			System.out.println("Obteve a imagem");
			AddMediaToAlbum copyPhotoToAlbum = new AddMediaToAlbum("Copy Photo to Album");
			System.out.println("Crio a screen");
			copyPhotoToAlbum.setItemName("New picture");
			copyPhotoToAlbum.setLabePath("Copy to Album:");
			copyPhotoToAlbum.setCommandListener(this);
			
			copyPhotoToAlbum.setCapturedMedia(newfoto);
			System.out.println("Definiu a imagem");
	        Display.getDisplay(midlet).setCurrent(copyPhotoToAlbum);
			
			return true;

		}
		//#endif
		 if ((label.equals("Cancel")) || (label.equals("Back"))){
			// [NC] Changed in the scenario 07: just the first line below to support generic AbstractController
			((AlbumListScreen) getAlbumListScreen()).repaintListAlbum(getAlbumData().getAlbumNames());
			setCurrentScreen( getAlbumListScreen() );
			ScreenSingleton.getInstance().setCurrentScreenName(Constants.ALBUMLIST_SCREEN);
			return true;
		}
		
		return false;
	}
	
	// #ifdef capturePhoto
	// [NC] Added in the scenario 08
	public CaptureVideoScreen getCpVideoScreen() {
		return cpVideoScreen;
	}

	public void setCpVideoScreen(CaptureVideoScreen cpVideoScreen) {
		this.cpVideoScreen = cpVideoScreen;
	}
	//#endif
}
//#endif
