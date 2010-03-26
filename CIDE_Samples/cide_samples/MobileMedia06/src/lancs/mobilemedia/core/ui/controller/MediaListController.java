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

import lancs.mobilemedia.core.ui.MainUIMidlet;
import lancs.mobilemedia.core.ui.datamodel.AlbumData;
import lancs.mobilemedia.core.ui.datamodel.MediaData;
import lancs.mobilemedia.core.ui.screens.AlbumListScreen;
import lancs.mobilemedia.core.ui.screens.MediaListScreen;
import lancs.mobilemedia.core.util.Constants;
import lancs.mobilemedia.lib.exceptions.UnavailablePhotoAlbumException;
//#ifdef includePhotoAlbum
//[NC] Added in the scenario 07
import lancs.mobilemedia.core.ui.datamodel.ImageAlbumData;
//#endif

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
	 * @see ubc.midp.MobileMedia.core.ui.controller.ControllerInterface#handleCommand(java.lang.String)
	 */
	public boolean handleCommand(Command command) {
		String label = command.getLabel();
		/** Case: Select PhotoAlbum to view **/
		if (label.equals("Select")) {
			// Get the name of the PhotoAlbum selected, and load image list from
			// RecordStore
			List down = (List) Display.getDisplay(midlet).getCurrent();
			ScreenSingleton.getInstance().setCurrentStoreName(down.getString(down.getSelectedIndex()));
			showMediaList(getCurrentStoreName(), false, false);
			ScreenSingleton.getInstance().setCurrentScreenName( Constants.IMAGELIST_SCREEN);
			return true;
		}

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


		// #ifdef includeMusic
		// #ifdef includePhotoAlbum
		if (!(getAlbumData() instanceof ImageAlbumData))
		//#endif
		// [NC] Added in the scenario 07
			mediaList = new MediaListScreen(MediaListScreen.PLAYMUSIC);
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

				// #ifdef includeFavourites
				// [EF] Check if favorite is true (Add in the Scenario 03)
				if (!favorite || medias[i].isFavorite())
					// #endif
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
