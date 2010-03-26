// #ifdef includePhotoAlbum
// [NC] Added in the scenario 07
package lancs.mobilemedia.core.ui.datamodel;

import javax.microedition.lcdui.Image;
import javax.microedition.rms.RecordStoreException;

import lancs.mobilemedia.core.util.MediaUtil;
import lancs.mobilemedia.lib.exceptions.ImagePathNotValidException;
import lancs.mobilemedia.lib.exceptions.InvalidArrayFormatException;
import lancs.mobilemedia.lib.exceptions.InvalidImageDataException;
import lancs.mobilemedia.lib.exceptions.InvalidImageFormatException;
import lancs.mobilemedia.lib.exceptions.PersistenceMechanismException;

public class ImageMediaAccessor extends MediaAccessor {
	
	private MediaUtil converter = new MediaUtil();
	
	public ImageMediaAccessor(AlbumData mod) {
		super("mpa-","mpi-","My Photo Album");
	}
	
	/**
	 * Reset the album data for MobilePhoto. This will delete all existing photo
	 * data from the record store and re-create the default album and photos.
	 * 
	 * @throws InvalidImageFormatException
	 * @throws ImagePathNotValidException
	 * @throws InvalidImageDataException
	 * @throws PersistenceMechanismException
	 * 
	 */
	public void resetRecordStore() throws InvalidImageDataException, PersistenceMechanismException {
		removeRecords();
		// Now, create a new default album for testing
		addMediaData("Tucan Sam", "/images/Tucan.png",
				default_album_name);
		
		// Now, create a new default album for testing
		addMediaData("Java", "/images/Java.png",
				default_album_name);
		// Add Penguin
		addMediaData("Linux Penguin", "/images/Penguin.png",
				default_album_name);
		// Add Duke
		addMediaData("Duke (Sun)", "/images/Duke1.PNG",
				default_album_name);
		addMediaData("UBC Logo", "/images/ubcLogo.PNG",
				default_album_name);
		// Add Gail
		addMediaData("Gail", "/images/Gail1.PNG",
				default_album_name);
		// Add JG
		addMediaData("J. Gosling", "/images/Gosling1.PNG",
				default_album_name);
		// Add GK
		addMediaData("Gregor", "/images/Gregor1.PNG",
				default_album_name);
		// Add KDV
		addMediaData("Kris", "/images/Kdvolder1.PNG",
				default_album_name);

	}
	
	protected  byte[] getMediaArrayOfByte(String path)	throws ImagePathNotValidException, InvalidImageFormatException {
		byte[] data1 = converter.readMediaAsByteArray(path);
		return data1;
	}
	
	protected byte[] getByteFromMediaInfo(MediaData ii) throws InvalidImageDataException {
			return converter.getBytesFromMediaInfo(ii);
	}
	
	protected MediaData getMediaFromBytes(byte[] data) throws InvalidArrayFormatException {
		MediaData iiObject = converter.getMediaInfoFromBytes(data);
		return iiObject;
	}

	
	// #if includeSmsFeature || capturePhoto
	/* [NC] Added in scenario 06 */
	public void addImageData(String photoname, byte[] imgdata, String albumname)
			throws InvalidImageDataException, PersistenceMechanismException {
		try {
			addMediaArrayOfBytes(photoname, albumname, imgdata);
			} catch (RecordStoreException e) {
			throw new PersistenceMechanismException();
		}
	}
	//#endif	

	/**
	 * Fetch a single image from the Record Store This should be used for
	 * loading images on-demand (only when they are viewed or sent via SMS etc.)
	 * to reduce startup time by loading them all at once.
	 * @throws PersistenceMechanismException 
	 */
	public Image loadSingleImageFromRMS(String recordName,
			int recordId) throws PersistenceMechanismException {

		Image img = null;
		byte[] imageData = loadMediaBytesFromRMS(recordName, recordId);
		img = Image.createImage(imageData, 0, imageData.length);
		return img;
	}

}
//#endif
