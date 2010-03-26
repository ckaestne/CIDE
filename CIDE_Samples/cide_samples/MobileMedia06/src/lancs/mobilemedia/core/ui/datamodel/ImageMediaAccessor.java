// #ifdef includePhotoAlbum
// [NC] Added in the scenario 07
package lancs.mobilemedia.core.ui.datamodel;

import javax.microedition.lcdui.Image;
import javax.microedition.rms.RecordStore;
import javax.microedition.rms.RecordStoreException;


import lancs.mobilemedia.core.util.MediaUtil;
import lancs.mobilemedia.lib.exceptions.ImagePathNotValidException;
import lancs.mobilemedia.lib.exceptions.InvalidArrayFormatException;
import lancs.mobilemedia.lib.exceptions.InvalidImageDataException;
import lancs.mobilemedia.lib.exceptions.InvalidImageFormatException;
import lancs.mobilemedia.lib.exceptions.PersistenceMechanismException;

public class ImageMediaAccessor extends MediaAccessor {
	
	private MediaUtil converter = new MediaUtil();
	
	public ImageMediaAccessor() {
		super("mpa-","mpi-","My Photo Album");
	}
	
	/**
	 * Reset the album data for MobileMedia. This will delete all existing photo
	 * data from the record store and re-create the default album and photos.
	 * 
	 * @throws InvalidImageFormatException
	 * @throws ImagePathNotValidException
	 * @throws InvalidImageDataException
	 * @throws PersistenceMechanismException
	 * 
	 */
	public void resetRecordStore() throws InvalidImageDataException, PersistenceMechanismException {

		String storeName = null;
		String infoStoreName = null;

		// remove any existing album stores...
		if (albumNames != null) {
			for (int i = 0; i < albumNames.length; i++) {
				try {
					// Delete all existing stores containing Image objects as
					// well as the associated ImageInfo objects
					// Add the prefixes labels to the info store

					storeName = album_label + albumNames[i];
					infoStoreName = info_label + albumNames[i];

					System.out.println("<* ImageAccessor.resetImageRecordStore() *> delete "+storeName);
					
					RecordStore.deleteRecordStore(storeName);
					RecordStore.deleteRecordStore(infoStoreName);

				} catch (RecordStoreException e) {
					System.out.println("No record store named " + storeName
							+ " to delete.");
					System.out.println("...or...No record store named "
							+ infoStoreName + " to delete.");
					System.out.println("Ignoring Exception: " + e);
					// ignore any errors...
				}
			}
		} else {
			// Do nothing for now
			System.out
					.println("ImageAccessor::resetImageRecordStore: albumNames array was null. Nothing to delete.");
		}

		// Now, create a new default album for testing
		addMediaData("Tucan Sam", "/images/Tucan.png",
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

	
	// #ifdef includeSmsFeature
	/* [NC] Added in scenario 06 */
	public byte[] getByteFromImage(Image img){
		int w = img.getWidth();
		int h = img.getHeight();
		int data_int[] = new int[ w * h ];
		img.getRGB( data_int, 0, w, 0, 0, w, h );
		byte[] data_byte = new byte[ w * h * 3 ];
		for ( int i = 0; i < w * h; ++i )
		{
		int color = data_int[ i ];
		int offset = i * 3;
		data_byte[ offset ] = ( byte ) ( ( color & 0xff0000 ) >> 16 );
		data_byte[ offset +
		1 ] = ( byte ) ( ( color & 0xff00 ) >> 8 );
		data_byte[ offset + 2 ] = ( byte ) ( ( color & 0xff ) );
		}
		return data_byte;
	}
	
	
	public void addImageData(String photoname, Image imgdata, String albumname)
			throws InvalidImageDataException, PersistenceMechanismException {
		try {
			byte[] data1 = getByteFromImage(imgdata);
			addMediaArrayOfBytes(photoname, albumname, data1);
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