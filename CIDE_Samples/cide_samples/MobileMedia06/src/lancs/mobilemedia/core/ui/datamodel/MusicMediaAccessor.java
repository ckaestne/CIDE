// #ifdef includeMusic
// [NC] Added in the scenario 07
package lancs.mobilemedia.core.ui.datamodel;

import javax.microedition.rms.RecordStore;
import javax.microedition.rms.RecordStoreException;

import lancs.mobilemedia.core.util.MusicMediaUtil;
import lancs.mobilemedia.lib.exceptions.ImageNotFoundException;
import lancs.mobilemedia.lib.exceptions.ImagePathNotValidException;
import lancs.mobilemedia.lib.exceptions.InvalidArrayFormatException;
import lancs.mobilemedia.lib.exceptions.InvalidImageDataException;
import lancs.mobilemedia.lib.exceptions.InvalidImageFormatException;
import lancs.mobilemedia.lib.exceptions.PersistenceMechanismException;

public class MusicMediaAccessor extends MediaAccessor {

	private MusicMediaUtil converter = new MusicMediaUtil();
	
	public MusicMediaAccessor() {
		super("mmp-","mmpi-","My Music Album");
	}
	
	protected  byte[] getMediaArrayOfByte(String path)	throws ImagePathNotValidException, InvalidImageFormatException {
		byte[] data1 = converter.readMediaAsByteArray(path);
		return data1;
	}
	
	protected byte[] getByteFromMediaInfo(MediaData ii) throws InvalidImageDataException {
			return converter.getBytesFromMediaInfo(ii);
	}
	
	protected MediaData getMediaFromBytes(byte[] data) throws InvalidArrayFormatException {
		MediaData iiObject = converter.getMultiMediaInfoFromBytes(data);
		return iiObject;
	}

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
		MediaData media = null;
		MultiMediaData mmedi = null;

		addMediaData("Applause", "/images/applause.wav", default_album_name);
		addMediaData("Baby", "/images/baby.wav", default_album_name);
		addMediaData("Bong", "/images/bong.wav", default_album_name);
		addMediaData("Frogs", "/images/frogs.mp3", default_album_name);
		addMediaData("Jump", "/images/jump.wav", default_album_name);
		addMediaData("Printer", "/images/printer.wav", default_album_name);
		addMediaData("Tango", "/images/cabeza.mid", default_album_name);
		
		loadMediaDataFromRMS(default_album_name);

		try {
			media = this.getMediaInfo("Applause");

			mmedi = new MultiMediaData(media, "audio/x-wav");
			this.updateMediaInfo(media, mmedi);

			media = this.getMediaInfo("Baby");
			mmedi = new MultiMediaData(media, "audio/x-wav");
			this.updateMediaInfo(media, mmedi);

			media = this.getMediaInfo("Bong");
			mmedi = new MultiMediaData(media, "audio/x-wav");
			this.updateMediaInfo(media, mmedi);

			media = this.getMediaInfo("Frogs");
			mmedi = new MultiMediaData(media, "audio/mpeg");
			this.updateMediaInfo(media, mmedi);

			media = this.getMediaInfo("Jump");
			mmedi = new MultiMediaData(media, "audio/x-wav");
			this.updateMediaInfo(media, mmedi);

			media = this.getMediaInfo("Printer");
			mmedi = new MultiMediaData(media, "audio/x-wav");
			this.updateMediaInfo(media, mmedi);
			
			media = this.getMediaInfo("Tango");
			mmedi = new MultiMediaData(media, "audio/midi");
			this.updateMediaInfo(media, mmedi);
		} catch (ImageNotFoundException e) {
			e.printStackTrace();
		}

	}

}
//#endif
