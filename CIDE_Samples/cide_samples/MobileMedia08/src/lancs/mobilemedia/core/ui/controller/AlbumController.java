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

//#ifdef includePrivacy
import lancs.mobilemedia.core.ui.screens.PasswordScreen;
//#endif

/**
 * @author Eduardo Figueiredo
 * Added in the Scenario 04.
 * Purpose: simplify method handleCommand() in the BaseController.
 */
public class AlbumController extends AbstractController {
	
	//#if includePrivacy
	private NewLabelScreen albumName = null;
	private PasswordScreen password = null;
	private String albumtodelete = " ";
	//#endif

	public AlbumController(MainUIMidlet midlet, AlbumData albumData, AlbumListScreen albumListScreen) {
		super(midlet, albumData, albumListScreen);
	}
	
	/* (non-Javadoc)
	 * @see ubc.midp.mobilephoto.core.ui.controller.ControllerInterface#handleCommand(javax.microedition.lcdui.Command, javax.microedition.lcdui.Displayable)
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
		 *  [EF] I think this confirmation questions are complicating the implementation
		 *  [EF] How do you know that "Yes - Delete" is to delete Photo Album instead of Photo?
		 *  Case: Yes delete Photo Album  **/
		}else if (label.equals("Yes - Delete")) {
			//#if includePrivacy
				String passwd;
				albumtodelete = ScreenSingleton.getInstance().getCurrentStoreName();
				passwd = getAlbumData().getPassword(albumtodelete);

				if(passwd!=null){
					PasswordScreen pwd = new PasswordScreen("Password",1);
					pwd.setCommandListener(this);
					setCurrentScreen(pwd);
					pwd = null;
				}else{
					try {
						getAlbumData().deleteAlbum(ScreenSingleton.getInstance().getCurrentStoreName());	
					} catch (PersistenceMechanismException e) {
						Alert alert = new Alert( "Error", "The mobile database can not delete this photo album", null, AlertType.ERROR);
				        Display.getDisplay(midlet).setCurrent(alert, Display.getDisplay(midlet).getCurrent());
					}
					goToPreviousScreen();	
				}				
			//#else
			try {
				getAlbumData().deleteAlbum(ScreenSingleton.getInstance().getCurrentStoreName());	
			} catch (PersistenceMechanismException e) {
				Alert alert = new Alert( "Error", "The mobile database can not delete this photo album", null, AlertType.ERROR);
		        Display.getDisplay(midlet).setCurrent(alert, Display.getDisplay(midlet).getCurrent());
			}
			goToPreviousScreen();
			//#endif
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
					if (currentScreen.getFormType() == NewLabelScreen.NEW_ALBUM){
						getAlbumData().createNewAlbum(currentScreen.getLabelName());	
					}
					else if (currentScreen.getFormType() == NewLabelScreen.LABEL_PHOTO) {
					}
				}
				
			//#ifdef includePrivacy
			try{
				password = (PasswordScreen) getCurrentScreen();
				
				getAlbumData().createNewAlbum(albumName.getLabelName());
				getAlbumData().addPassword(albumName.getLabelName(), password.getPassword());
				
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
			//#endif
				
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
      	
      	//#ifdef includePrivacy
		if(label.equals("OK")){
			albumName = (NewLabelScreen)getCurrentScreen();
			String message = "Would you like to define a password to this Album";
			Alert definePassword = new Alert("Define a Password", message,null,AlertType.CONFIRMATION);
			definePassword.setTimeout(Alert.FOREVER);
			definePassword.addCommand(new Command("Yes", Command.OK, 2));
			definePassword.addCommand(new Command("No", Command.EXIT, 2));
			setAlbumListAsCurrentScreen(definePassword);
			definePassword.setCommandListener(this);
			return true;	
		}else if(label.equals("Yes")){
			PasswordScreen pwd = new PasswordScreen("Define a Password",0);//, NewLabelScreen.NEW_ALBUM);
			pwd.setCommandListener(this);
			setCurrentScreen(pwd);
			pwd = null;
			return true;
		}else if(label.equals("No")){
			try {									
					getAlbumData().createNewAlbum(albumName.getLabelName());	
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
			
		}else if(label.equals("Confirm")){
			System.out.println( "<* AlbumController.handleCommand() *>: " + label);
			PasswordScreen password = (PasswordScreen) getCurrentScreen();
			String passwd =getAlbumData().getPassword(getCurrentStoreName());
			if(password.getPassword().equals(passwd)){
				try {
					getAlbumData().deleteAlbum(ScreenSingleton.getInstance().getCurrentStoreName());
				} catch (PersistenceMechanismException e) {
					System.out.println(e);
					Alert alert = new Alert( "Error", "The mobile database can not delete this photo album", null, AlertType.ERROR);
			        Display.getDisplay(midlet).setCurrent(alert, Display.getDisplay(midlet).getCurrent());
				}
				goToPreviousScreen();
			}else{
					Alert alert = new Alert( "Error", "Invalid Password", null, AlertType.ERROR);
					Display.getDisplay(midlet).setCurrent(alert, Display.getDisplay(midlet).getCurrent());
			}
			
			//goToPreviousScreen();
			return true;
		}else if(label.equals("Add Password")){
			PasswordScreen pwd = new PasswordScreen("Define a Password",3);//, NewLabelScreen.NEW_ALBUM);
			pwd.setCommandListener(this);
			setCurrentScreen(pwd);
			pwd = null;
			return true;
		}else if(label.equals("Store")){
			PasswordScreen password = (PasswordScreen) getCurrentScreen();
			getAlbumData().addPassword(ScreenSingleton.getInstance().getCurrentStoreName(), password.getPassword());
			goToPreviousScreen();
			return true;
		}
      	//#endif
      	
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
