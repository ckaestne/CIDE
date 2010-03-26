/*
 * Created on 31-Mar-2005
 *
 */
package lancs.mobilemedia.core.util;

/**
 * @author tyoung
 *
 * This class contains all constants used by the MobilePhoto application.
 * It is the central location to store any static string data.
 */
public class Constants {

	public static final String ALBUMLIST_SCREEN = "AlbumListScreen";
	public static final String IMAGE_SCREEN = "ImageScreen";
	public static final String IMAGELIST_SCREEN = "ImageListScreen";
	
	public static final String NEWALBUM_SCREEN = "NewLabelScreen";
	public static final String CONFIRMDELETEALBUM_SCREEN = "ConfirmDeleteAlbumScreen";
	public static final String ADDPHOTOTOALBUM_SCREEN = "AddPhotoToAlbum";

	//#ifdef device_screen_176x205
    
    /** Screen Size**/
	public static final int SCREEN_WIDTH  = 176;
	public static final int SCREEN_HEIGHT = 205;
    
	//#elifdef device_screen_128x149
	//#
	//# /** Screen Size*/
	//# public static final int SCREEN_WIDTH  = 128;
	//# public static final int SCREEN_HEIGHT = 149;
	//#
	//#elifdef device_screen_132x176
	//#
	//# /** Screen Size*/
	//# public static final int SCREEN_WIDTH  = 132;
	//# public static final int SCREEN_HEIGHT = 176;
	//#
	//#endif
}
