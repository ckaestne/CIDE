/*
 * Lancaster University
 * Computing Department
 * 
 * Created by Eduardo Figueiredo
 * Date: 22 Jun 2007
 * 
 */
package lancs.mobilemedia.core.ui.controller;

//#if (includeMMAPI && includePhotoAlbum)||(includeMMAPI && includeVideo) || (includeVideo && includePhotoAlbum)
//[NC] Added in the scenario 07 and changed in scenario 8
import lancs.mobilemedia.core.ui.screens.SelectTypeOfMedia;
//#endif

/**
 * @author Eduardo Figueiredo
 * [EF] Added in scenario 04. Purpose have unique data (by now currentScreenName, currentStoreName)
 * in order to make them consistent for all controllers.
 */
public class ScreenSingleton {
	
	private static ScreenSingleton instance;

	//Keep track of the navigation so we can go 'back' easily
	private String currentScreenName;
	
	//Keep track of the current photo album being viewed
	//Ie. name of the currently active J2ME record store
	private String currentStoreName = "My Photo Album";
	
	// #if (includeMMAPI && includePhotoAlbum)||(includeMMAPI && includeVideo) || (includeVideo && includePhotoAlbum)
	// [NC] Added in the scenario 07 and changed in scenario 8
	private SelectTypeOfMedia mainscreen;
	
	public SelectTypeOfMedia getMainMenu(){
		return mainscreen;
	}
	
	public void setMainMenu(SelectTypeOfMedia screen){
		mainscreen = screen;
	}
	//#endif
	
	private ScreenSingleton() {
	}

	/**
	 * @return the instance
	 */
	public static ScreenSingleton getInstance() {
		if (instance == null) instance = new ScreenSingleton();
		return instance;
	}

	/**
	 * @param currentScreenName the currentScreenName to set
	 */
	public void setCurrentScreenName(String currentScreenName) {
		this.currentScreenName = currentScreenName;
	}

	/**
	 * @return the currentScreenName
	 */
	public String getCurrentScreenName() {
		return currentScreenName;
	}

	/**
	 * @param currentStoreName the currentStoreName to set
	 */
	public void setCurrentStoreName(String currentStoreName) {
		this.currentStoreName = currentStoreName;
	}

	/**
	 * @return the currentStoreName
	 */
	public String getCurrentStoreName() {
		return currentStoreName;
	}
}
