/*
 * Lancaster University
 * Computing Department
 * 
 * Created by Eduardo Figueiredo
 * Date: 22 Jun 2007
 * 
 */
package lancs.mobilemedia.core.ui.controller;

import javax.microedition.lcdui.Alert;
import javax.microedition.lcdui.AlertType;
import javax.microedition.lcdui.Command;
import javax.microedition.lcdui.Display;
import javax.microedition.lcdui.List;
import javax.microedition.rms.RecordStoreFullException;

import lancs.mobilemedia.core.ui.MainUIMidlet;
import lancs.mobilemedia.core.ui.datamodel.AlbumData;
import lancs.mobilemedia.core.ui.screens.AlbumListScreen;
import lancs.mobilemedia.core.ui.screens.NewLabelScreen;
import lancs.mobilemedia.core.util.Constants;
import lancs.mobilemedia.lib.exceptions.InvalidPhotoAlbumNameException;
import lancs.mobilemedia.lib.exceptions.PersistenceMechanismException;

/**
 * @author Eduardo Figueiredo
 * Added in the Scenario 04.
 * Purpose: simplify method handleCommand() in the BaseController.
 */
public class AlbumController extends AbstractController {

	public AlbumController(MainUIMidlet midlet, AlbumData albumData, AlbumListScreen albumListScreen) {
		super(midlet, albumData, albumListScreen);
	}
	
	/* (non-Javadoc)
	 * @see ubc.midp.MobileMedia.core.ui.controller.ControllerInterface#handleCommand(javax.microedition.lcdui.Command, javax.microedition.lcdui.Displayable)
	 */
	public boolean handleCommand(Command command) {
		String label = command.getLabel();
      	System.out.println( "<* AlbumController.handleCommand() *>: " + label);
		
      	if (label.equals("Reset")) {
			System.out.println("<* BaseController.handleCommand() *> Reset Photo Album");			
		    resetMediaData();
		    ScreenSingleton.getInstance().setCurrentScreenName(Constants.ALBUMLIST_SCREEN);
			return true;
		/** Case: Create PhotoAlbum **/
		}else if (label.equals("New Album")) {
			System.out.println("Create new Photo Album here");			
			ScreenSingleton.getInstance().setCurrentScreenName(Constants.NEWALBUM_SCREEN);
			NewLabelScreen canv = new NewLabelScreen("Add new Photo Album", NewLabelScreen.NEW_ALBUM);
			canv.setCommandListener(this);
			setCurrentScreen(canv);
			canv = null;
			return true;
		/** Case: Delete Album Photo**/
		}else if (label.equals("Delete Album")) {
			System.out.println("Delete Photo Album here");
			List down = (List) Display.getDisplay(midlet).getCurrent();
			ScreenSingleton.getInstance().setCurrentScreenName(Constants.CONFIRMDELETEALBUM_SCREEN);
			ScreenSingleton.getInstance().setCurrentStoreName(down.getString(down.getSelectedIndex()));
			String message = "Would you like to remove the album "+ScreenSingleton.getInstance().getCurrentStoreName();
			Alert deleteConfAlert = new Alert("Delete Photo Album", message,null,AlertType.CONFIRMATION);
			deleteConfAlert.setTimeout(Alert.FOREVER);
			deleteConfAlert.addCommand(new Command("Yes - Delete", Command.OK, 2));
			deleteConfAlert.addCommand(new Command("No - Delete", Command.CANCEL, 2));
			setAlbumListAsCurrentScreen(deleteConfAlert);
			deleteConfAlert.setCommandListener(this);
			return true;	
		/**
		 *  TODO [EF] I think this confirmation questions are complicating the implementation
		 *  [EF] How do you know that "Yes - Delete" is to delete Photo Album instead of Photo?
		 *  Case: Yes delete Photo Album  **/
		}else if (label.equals("Yes - Delete")) {
			try {
				getAlbumData().deleteAlbum(ScreenSingleton.getInstance().getCurrentStoreName());
			} catch (PersistenceMechanismException e) {
				Alert alert = new Alert( "Error", "The mobile database can not delete this photo album", null, AlertType.ERROR);
		        Display.getDisplay(midlet).setCurrent(alert, Display.getDisplay(midlet).getCurrent());
			}
			goToPreviousScreen();
			return true;	
		/** 
		 * [EF] Same question. How do you know that "No - Delete" is to delete Photo Album instead of Photo?
		 * Case: No delete Photo Album **/
		}else if (label.equals("No - Delete")) {
			goToPreviousScreen();
			return true;	
		/** 
		 * [EF] Again, see [EF] comments above.
		 * Case: Save new Photo Album  **/
		} else if (label.equals("Save")) {
			try {
				if (getCurrentScreen() instanceof NewLabelScreen) {
					NewLabelScreen currentScreen = (NewLabelScreen)getCurrentScreen();
					if (currentScreen.getFormType() == NewLabelScreen.NEW_ALBUM)
						getAlbumData().createNewAlbum(currentScreen.getLabelName());
					else if (currentScreen.getFormType() == NewLabelScreen.LABEL_PHOTO) {
						
					}
				}
			} catch (PersistenceMechanismException e) {
				Alert alert = null;
				if (e.getCause() instanceof  RecordStoreFullException)
					alert = new Alert( "Error", "The mobile database is full", null, AlertType.ERROR);
				else
					alert = new Alert( "Error", "The mobile database can not add a new photo album", null, AlertType.ERROR);
				Display.getDisplay(midlet).setCurrent(alert, Display.getDisplay(midlet).getCurrent());
				return true;
		    } catch (InvalidPhotoAlbumNameException e) {
		    	Alert alert = new Alert( "Error", "You have provided an invalid Photo Album name", null, AlertType.ERROR);
				Display.getDisplay(midlet).setCurrent(alert, Display.getDisplay(midlet).getCurrent());
				return true;
			}
			goToPreviousScreen();
			return true;
		}
      	
		return false;
	}
	
    /**
	 * This option is mainly for testing purposes. If the record store
	 * on the device or emulator gets into an unstable state, or has too 
	 * many images, you can reset it, which clears the record stores and
	 * re-creates them with the default images bundled with the application 
	 */
	private void resetMediaData() {
        try {
        	getAlbumData().resetMediaData();
		} catch (PersistenceMechanismException e) {
			Alert alert = null;
			if (e.getCause() instanceof  RecordStoreFullException)
				alert = new Alert( "Error", "The mobile database is full", null, AlertType.ERROR);
			else
				alert = new Alert( "Error", "It is not possible to reset the database", null, AlertType.ERROR);
			Display.getDisplay(midlet).setCurrent(alert, Display.getDisplay(midlet).getCurrent()); // TODO [EF] weird
	        return;
		}
        
        //Clear the names from the album list
        for (int i = 0; i < getAlbumListScreen().size(); i++) {
        	getAlbumListScreen().delete(i);
        }
        
        //Get the default ones from the album
        String[] albumNames = getAlbumData().getAlbumNames();
        for (int i = 0; i < albumNames.length; i++) {
        	if (albumNames[i] != null) {
        		//Add album name to menu list
        		getAlbumListScreen().append(albumNames[i], null);
        	}
        }
        setCurrentScreen(getAlbumListScreen());
    }

    private void goToPreviousScreen() {
	    System.out.println("<* AlbumController.goToPreviousScreen() *>");
	 // [NC] Changed in the scenario 07: just the first line below to support generic AbstractController
	    ((AlbumListScreen) getAlbumListScreen()).repaintListAlbum(getAlbumData().getAlbumNames());
		setCurrentScreen( getAlbumListScreen() );
		ScreenSingleton.getInstance().setCurrentScreenName(Constants.ALBUMLIST_SCREEN);
    }
}
