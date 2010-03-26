// #ifdef includePhotoAlbum
// [NC] Added in the scenario 07
package lancs.mobilemedia.core.ui.datamodel;

import javax.microedition.lcdui.Image;

import lancs.mobilemedia.lib.exceptions.ImageNotFoundException;
import lancs.mobilemedia.lib.exceptions.PersistenceMechanismException;

public class ImageAlbumData extends AlbumData {
	
	public ImageAlbumData() {
		mediaAccessor = new ImageMediaAccessor();
	}
	
	/**
	 *  Get a particular image (by name) from a photo album. The album name corresponds
	 *  to a record store.
	 * @throws ImageNotFoundException 
	 * @throws PersistenceMechanismException 
	 */
	public Image getImageFromRecordStore(String recordStore, String imageName) throws ImageNotFoundException, PersistenceMechanismException {
		MediaData imageInfo = null;
		imageInfo = mediaAccessor.getMediaInfo(imageName);
	
		//Find the record ID and store name of the image to retrieve
		int imageId = imageInfo.getForeignRecordId();
		String album = imageInfo.getParentAlbumName();
		//Now, load the image (on demand) from RMS and cache it in the hashtable
		Image imageRec = ((ImageMediaAccessor) mediaAccessor).loadSingleImageFromRMS(album, imageId); //rs.getRecord(recordId);
		return imageRec;
	}
}
//#endif