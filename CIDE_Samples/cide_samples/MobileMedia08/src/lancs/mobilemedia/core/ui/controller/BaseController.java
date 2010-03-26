/*
 * Created on Sep 28, 2004
 *
 */
package lancs.mobilemedia.core.ui.controller;

import javax.microedition.lcdui.Command;

import lancs.mobilemedia.core.ui.MainUIMidlet;
import lancs.mobilemedia.core.ui.datamodel.AlbumData;
import lancs.mobilemedia.core.ui.screens.AlbumListScreen;

import lancs.mobilemedia.core.util.Constants;


/**
 * @author tyoung
 *
 * This is the base controller class used in the MVC architecture.
 * It controls the flow of screens for the MobilePhoto application.
 * Commands handled by this class should only be for the core application
 * that runs on any MIDP platform. Each device or class of devices that supports
 * optional features will extend this class to handle feature specific commands.
 * 
 */
public class BaseController extends AbstractController {
    
	
	/**
	 * Pass a handle to the main Midlet for this controller
	 * @param midlet
	 */
	public BaseController(MainUIMidlet midlet, AlbumData model, AlbumListScreen albumListScreen) {
		super(midlet, model, albumListScreen);
	}

	/**
	 * Initialize the controller
	 */
	public void init(AlbumData model) {
		//SelectMediaController
		
		//Get all MobilePhoto defined albums from the record store
		String[] albumNames = model.getAlbumNames();
		getAlbumListScreen().deleteAll();
		for (int i = 0; i < albumNames.length; i++) {
			if (albumNames[i] != null) {
				//Add album name to menu list
				getAlbumListScreen().append(albumNames[i], null);
			}
		}
		// [NC] Changed in the scenario 07: just the first line below to support generic AbstractController
		((AlbumListScreen) getAlbumListScreen()).initMenu();
		
		//Set the current screen to the photo album listing
		setCurrentScreen(getAlbumListScreen());
	}

    /* (non-Javadoc)
     * @see ubc.midp.mobilephoto.core.ui.controller.ControllerInterface#handleCommand(javax.microedition.lcdui.Command)
     */
    public boolean handleCommand(Command command) {
		String label = command.getLabel();

		//Can this controller handle the desired action?
		//If yes, handleCommand will return true, and we're done
		//If no, handleCommand will return false, and postCommand
		//will pass the request to the next controller in the chain if one exists.
		
      	System.out.println( this.getClass().getName() + "::handleCommand: " + label);

		/** Case: Exit Application **/
		if (label.equals("Exit")) {
			midlet.destroyApp(true);
			return true;
		
		/** Case: Go to the Previous or Fallback screen * */
		} else if (label.equals("Back")) {
			return goToPreviousScreen();

		/** Case: Cancel the current screen and go back one* */
		} else if (label.equals("Cancel")) {
			return goToPreviousScreen();

		}

		//If we couldn't handle the current command, return false
        return false;
    }
    
    private boolean goToPreviousScreen() {
    	String currentScreenName = ScreenSingleton.getInstance().getCurrentScreenName();
    	System.out.println("<* BaseController.goToPreviousScreen() **>"+currentScreenName);
	    if (currentScreenName != null){
		if ( (currentScreenName.equals(Constants.IMAGELIST_SCREEN)) || 
				(currentScreenName.equals(Constants.NEWALBUM_SCREEN)) ||
				(currentScreenName.equals(Constants.CONFIRMDELETEALBUM_SCREEN)) ){
			// [NC] Changed in the scenario 07: just the first line below to support generic AbstractController
			((AlbumListScreen) getAlbumListScreen()).repaintListAlbum(getAlbumData().getAlbumNames());
			setCurrentScreen( getAlbumListScreen() );
			ScreenSingleton.getInstance().setCurrentScreenName(Constants.ALBUMLIST_SCREEN);
			return true;
			}
	    }
		// #if includeMMAPI && includePhotoAlbum
		// [NC] Added in the scenario 07
		if ((currentScreenName == null) || (currentScreenName.equals(Constants.ALBUMLIST_SCREEN))) 
		{	
			setCurrentScreen( ScreenSingleton.getInstance().getMainMenu());
			return true;
		}
		//#endif

		return false;
    }
}
