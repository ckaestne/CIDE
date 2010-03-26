package lancs.mobilemedia.core.ui;

import javax.microedition.lcdui.Display;
import javax.microedition.midlet.MIDlet;
import javax.microedition.midlet.MIDletStateChangeException;

import lancs.mobilemedia.core.ui.controller.AlbumController;
import lancs.mobilemedia.core.ui.controller.BaseController;
import lancs.mobilemedia.core.ui.controller.MediaListController;
import lancs.mobilemedia.core.ui.controller.ScreenSingleton;
import lancs.mobilemedia.core.ui.datamodel.AlbumData;
//#ifdef includePhotoAlbum
//[NC] Added in the scenario 07
import lancs.mobilemedia.core.ui.datamodel.ImageAlbumData;
//#endif
//#ifdef includeMusic
//[NC] Added in the scenario 07
import lancs.mobilemedia.core.ui.datamodel.MusicAlbumData;
//#endif
import lancs.mobilemedia.core.ui.screens.AlbumListScreen;
//#if includeMusic && includePhotoAlbum
//[NC] Added in the scenario 07
import lancs.mobilemedia.core.ui.controller.SelectMediaController;
import lancs.mobilemedia.core.ui.screens.SelectTypeOfMedia;
//#endif

//#ifdef includeSmsFeature
import lancs.mobilemedia.sms.SmsReceiverController;
import lancs.mobilemedia.sms.SmsReceiverThread;
//#endif

//Following are pre-processor statements to include the required
//classes for device specific features. They must be commented out
//if they aren't used, otherwise it will throw exceptions trying to
//load classes that aren't available for a given platform.


/* 
 * @author trevor
 *
 * This is the main Midlet class for the core J2ME application
 * It contains all the basic functionality that should be executable
 * in any standard J2ME device that supports MIDP 1.0 or higher. 
 * Any additional J2ME features for this application that are dependent
 * upon a particular device (ie. optional or proprietary library) are
 * de-coupled from the core application so they can be conditionally included
 * depending on the target platform 
 * 
 * This Application provides a basic Photo Album interface that allows a user to view
 * images on their mobile device. 
 * */
public class MainUIMidlet extends MIDlet {

	//(m v C) Controller
	// #ifdef includePhotoAlbum
	// [NC] Added in the scenario 07
	private BaseController imageRootController;
	//#endif

	//	#ifdef includeMusic
	// [NC] Added in the scenario 07
	private BaseController musicRootController;
	//#endif
	
	//Model (M v c)
	// #ifdef includePhotoAlbum
	// [NC] Added in the scenario 07
	private AlbumData imageModel;
	//#endif
	
	//	#ifdef includeMusic
	// [NC] Added in the scenario 07
	private AlbumData musicModel;
	//#endif

	/**
	 * Constructor -
	 */
	public MainUIMidlet() {
	    //do nothing
	}

	/**
	 * Start the MIDlet by creating new model and controller classes, and
	 * initialize them as necessary
	 */
	public void startApp() throws MIDletStateChangeException {
		
		// #ifdef includePhotoAlbum
		// [NC] Added in the scenario 07
		imageModel = new ImageAlbumData();
		//#endif
		
		//	#ifdef includeMusic
		// [NC] Added in the scenario 07
		musicModel = new MusicAlbumData();
		//#endif
		
		// #ifdef includePhotoAlbum
		// [NC] Added in the scenario 07
		AlbumListScreen album = new AlbumListScreen();
		imageRootController = new BaseController(this, imageModel, album);
		
		// [EF] Add in scenario 04: initialize sub-controllers
		MediaListController photoListController = new MediaListController(this, imageModel, album);
		photoListController.setNextController(imageRootController);
		
		AlbumController albumController = new AlbumController(this, imageModel, album);
		albumController.setNextController(photoListController);
		album.setCommandListener(albumController);
		//#endif
		
		
		// #ifdef includeMusic
		// [NC] Added in the scenario 07
		AlbumListScreen albumMusic = new AlbumListScreen();
		musicRootController = new BaseController(this, musicModel, albumMusic);
		
		MediaListController musicListController = new MediaListController(this, musicModel, albumMusic);
		musicListController.setNextController(musicRootController);
		
		AlbumController albumMusicController = new AlbumController(this, musicModel, albumMusic);
		albumMusicController.setNextController(musicListController);
		albumMusic.setCommandListener(albumMusicController);
		//#endif
		
	
	
		
		//#ifdef includeSmsFeature
		/* [NC] Added in scenario 06 */
		SmsReceiverController controller = new SmsReceiverController(this, imageModel, album);
		controller.setNextController(albumController);
		SmsReceiverThread smsR = new SmsReceiverThread(this, imageModel, album, controller);
		System.out.println("SmsController::Starting SMSReceiver Thread");
		new Thread(smsR).start();
		//#endif
		
		// #if includeMusic && includePhotoAlbum
		// [NC] Added in the scenario 07
		SelectMediaController selectcontroller = new SelectMediaController(this, imageModel, musicModel, album,imageRootController,musicRootController);
		selectcontroller.setNextController(imageRootController);
		
		SelectTypeOfMedia mainscreen = new SelectTypeOfMedia();
		mainscreen.initMenu();
		mainscreen.setCommandListener(selectcontroller);
		Display.getDisplay(this).setCurrent(mainscreen);
		ScreenSingleton.getInstance().setMainMenu(mainscreen);
		//#elif includePhotoAlbum
		imageRootController.init(imageModel);
		//#elif includeMusic
		musicRootController.init(musicModel);
		//#endif

	}

	/**
	 * Pause the MIDlet
	 * This method does nothing at the moment.
	 */
	public void pauseApp() {
		//do nothing
	}

	/**
	 * Destroy the MIDlet
	 */
	public void destroyApp(boolean unconditional) {
		notifyDestroyed();
	}
}