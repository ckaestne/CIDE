/*
 * Lancaster University
 * Computing Department
 * 
 * Created by Eduardo Figueiredo
 * Date: 11 Aug 2007
 * 
 */
package lancs.mobilemedia.core.ui.controller;

import javax.microedition.lcdui.Alert;
import javax.microedition.lcdui.AlertType;
import javax.microedition.lcdui.Command;
import javax.microedition.lcdui.Display;
import javax.microedition.lcdui.List;


import lancs.mobilemedia.lib.exceptions.UnavailablePhotoAlbumException;
import lancs.mobilemedia.core.ui.MainUIMidlet;
import lancs.mobilemedia.core.ui.datamodel.AlbumData;
//#ifdef includeVideo
// [NC] Added in the scenario 08	
import lancs.mobilemedia.core.ui.datamodel.VideoAlbumData;
//#endif

//#ifdef includePhotoAlbum
//[NC] Added in the scenario 07
import lancs.mobilemedia.core.ui.datamodel.ImageAlbumData;
//#endif
//#ifdef includeMMAPI
//[NC] Added in the scenario 08
import lancs.mobilemedia.core.ui.datamodel.MusicAlbumData;
//#endif

import lancs.mobilemedia.core.ui.datamodel.MediaData;
import lancs.mobilemedia.core.ui.screens.AlbumListScreen;
import lancs.mobilemedia.core.ui.screens.MediaListScreen;
//#ifdef includePrivacy
import lancs.mobilemedia.core.ui.screens.PasswordScreen;
//#endif
import lancs.mobilemedia.core.util.Constants;

/**
 * @author Eduardo Figueiredo
 *
 */
public class MediaListController extends AbstractController {

	/**
	 * @param midlet
	 * @param nextController
	 * @param albumData
	 * @param albumListScreen
	 */
	public MediaListController(MainUIMidlet midlet, AlbumData albumData, AlbumListScreen albumListScreen) {
		super(midlet, albumData, albumListScreen);
	}
	
	
	/* (non-Javadoc)
	 * @see lancs.mobilemedia.core.ui.controller.ControllerInterface#handleCommand(java.lang.String)
	 */
	public boolean handleCommand(Command command) {
		String label = command.getLabel();
		//#ifdef includePrivacy
		String passwd, ps2;// = " ";
		//#endif
		/** Case: Select PhotoAlbum to view **/
		if (label.equals("Select")) {
			// Get the name of the PhotoAlbum selected, and load image list from
			// RecordStore
			
			List down = (List) Display.getDisplay(midlet).getCurrent();
			ScreenSingleton.getInstance().setCurrentStoreName(down.getString(down.getSelectedIndex()));
			
				//#if includePrivacy
				passwd = ScreenSingleton.getInstance().getCurrentStoreName();
				ps2 = getAlbumData().getPassword(passwd); 
				if(ps2==null){
					showMediaList(ScreenSingleton.getInstance().getCurrentStoreName(), false, false);
					ScreenSingleton.getInstance().setCurrentScreenName( Constants.IMAGELIST_SCREEN);
				}else{
					PasswordScreen pwd = new PasswordScreen("Password",1);
					pwd.setCommandListener(this);
					setCurrentScreen(pwd);
					pwd = null;
					return true;
				}
				//#else
					showMediaList(getCurrentStoreName(), false, false);
					ScreenSingleton.getInstance().setCurrentScreenName( Constants.IMAGELIST_SCREEN);
				//#endif
				
				return true;

		}
		
		//#if includePrivacy
		if(label.equals("Confirm")){
			PasswordScreen password = (PasswordScreen) getCurrentScreen();
			passwd =getAlbumData().getPassword(getCurrentStoreName());
			if(password.getPassword().equals(passwd)){
				showMediaList(getCurrentStoreName(), false, false);
				ScreenSingleton.getInstance().setCurrentScreenName( Constants.IMAGELIST_SCREEN);
			}else{
				Alert alert = new Alert( "Error", "Invalid Password", null, AlertType.ERROR);
				Display.getDisplay(midlet).setCurrent(alert, Display.getDisplay(midlet).getCurrent());
			}
			return true;
		}
		//#endif
		
		
		return false;
	}

    /**
     * Show the list of images in the record store
	 * TODO: Refactor - Move this to ImageList class
	 */
	public void showMediaList(String recordName, boolean sort, boolean favorite) {
		if (recordName == null)
			recordName = getCurrentStoreName();
		
		// [NC] Changed in the scenario 07: just the first line below to support generic AbstractController
		MediaController mediaController = new MediaController(midlet, getAlbumData(), (AlbumListScreen) getAlbumListScreen());
		mediaController.setNextController(this);
		
		// [NC] Changed in the scenario 07: defines the type of screen: Photo or Video
		MediaListScreen mediaList = null;
		// #ifdef includePhotoAlbum
		// [NC] Added in the scenario 07
		if (getAlbumData() instanceof ImageAlbumData)
			mediaList = new MediaListScreen(MediaListScreen.SHOWPHOTO);
		//#endif
		
		// #if includeMMAPI
		// [NC] Added in the scenario 07
		if (getAlbumData() instanceof MusicAlbumData)
			mediaList = new MediaListScreen(MediaListScreen.PLAYMUSIC);
		//#endif
		
		// #if includeVideo
		// [NC] Added in the scenario 08	
		if (getAlbumData() instanceof VideoAlbumData)
			mediaList = new MediaListScreen(MediaListScreen.PLAYVIDEO);
		//#endif
	
		mediaList.setCommandListener(mediaController);
		
		//Command selectCommand = new Command("Open", Command.ITEM, 1);
		mediaList.initMenu();
		
		MediaData[] medias = null;
		try {
			medias = getAlbumData().getMedias(recordName);
		} catch (UnavailablePhotoAlbumException e) {
			Alert alert = new Alert( "Error", "The list of items can not be recovered", null, AlertType.ERROR);
			Display.getDisplay(midlet).setCurrent(alert, Display.getDisplay(midlet).getCurrent());
	        return;
	    }
		
		if (medias==null) return;
		
		// #ifdef includeSorting
		// [EF] Check if sort is true (Add in the Scenario 02)
		if (sort) {
			bubbleSort(medias);
		}
		// #endif
		
		//loop through array and add labels to list
		for (int i = 0; i < medias.length; i++) {
			if (medias[i] != null) {
				//Add album name to menu list
				
//				// #ifdef includeFavourites
//				// [EF] Check if favorite is true (Add in the Scenario 03)
//				if (favorite) {
//					if (medias[i].isFavorite())
//						mediaList.append(medias[i].getMediaLabel(), null);
//				}
//				else 
//				// #endif
//					mediaList.append(medias[i].getMediaLabel(), null);
				
				if (!favorite) 
					mediaList.append(medias[i].getMediaLabel(), null);
				else
					if (medias[i].isFavorite())
						mediaList.append(medias[i].getMediaLabel(), null);
					
				
			}
		}
		setCurrentScreen(mediaList);
		//currentMenu = "list";
	}
	
	// #ifdef includeSorting
	/**
	 * @param images
	 * @param pos1
	 * @param pos2
	 */
	private void exchange(MediaData[] medias, int pos1, int pos2) {
		MediaData tmp = medias[pos1];
		medias[pos1] = medias[pos2];
		medias[pos2] = tmp;
	}

    /**
     * Sorts an int array using basic bubble sort
     * 
     * @param numbers the int array to sort
     */
	public void bubbleSort(MediaData[] medias) {
		System.out.print("Sorting by BubbleSort...");		
		for (int end = medias.length; end > 1; end --) {
			for (int current = 0; current < end - 1; current ++) {
				if (medias[current].getNumberOfViews() > medias[current+1].getNumberOfViews()) {
					exchange(medias, current, current+1);
				}
			}
		}
		System.out.println("done.");
	}
	// #endif
}
