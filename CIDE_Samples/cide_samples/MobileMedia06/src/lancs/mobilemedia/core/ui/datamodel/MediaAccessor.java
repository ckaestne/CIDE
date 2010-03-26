/*
 * Created on Sep 13, 2004
 *
 */
package lancs.mobilemedia.core.ui.datamodel;

import java.util.Hashtable;
import java.util.Vector;

import javax.microedition.rms.RecordEnumeration;
import javax.microedition.rms.RecordStore;
import javax.microedition.rms.RecordStoreException;
import javax.microedition.rms.RecordStoreFullException;
import javax.microedition.rms.RecordStoreNotFoundException;
import javax.microedition.rms.RecordStoreNotOpenException;

import lancs.mobilemedia.lib.exceptions.ImageNotFoundException;
import lancs.mobilemedia.lib.exceptions.ImagePathNotValidException;
import lancs.mobilemedia.lib.exceptions.InvalidArrayFormatException;
import lancs.mobilemedia.lib.exceptions.InvalidImageDataException;
import lancs.mobilemedia.lib.exceptions.InvalidImageFormatException;
import lancs.mobilemedia.lib.exceptions.InvalidPhotoAlbumNameException;
import lancs.mobilemedia.lib.exceptions.PersistenceMechanismException;

/**
 * @author trevor
 * 
 * This is the main data access class. It handles all the connectivity with the
 * RMS record stores to fetch and save data associated with MobileMedia TODO:
 * Refactor into stable interface for future updates. We may want to access data
 * from RMS, or eventually direct from the 'file system' on devices that support
 * the FileConnection optional API.
 * 
 */
public abstract class MediaAccessor {

	// Note: Our midlet only ever has access to Record Stores it created
	// For now, use naming convention to create record stores used by
	// MobileMedia
	protected String album_label; // "mpa- all album names
								// are prefixed with
								// this label
	protected String info_label; // "mpi- all album info
								// stores are prefixed with
								// this label
	protected String default_album_name; // default
										// album
										// name

	//imageInfo holds image metadata like label, album name and 'foreign key' index to
	// corresponding RMS entry that stores the actual Image object
	protected Hashtable mediaInfoTable = new Hashtable();

	protected String[] albumNames; // User defined names of photo albums
	
	// Record Stores
	private RecordStore mediaRS = null;
	private RecordStore mediaInfoRS = null;

	/**
	 * @param album_label
	 * @param info_label
	 * @param default_album_name
	 */
	public MediaAccessor(String album_label, String info_label, String default_album_name) {
		this.album_label = album_label; 
		this.info_label = info_label; 
		this.default_album_name = default_album_name; 
	}

	/**
	 * Load all existing photo albums that are defined in the record store.
	 * 
	 * @throws InvalidImageDataException
	 * @throws PersistenceMechanismException
	 */
	public void loadAlbums() throws InvalidImageDataException,
			PersistenceMechanismException {
		// Try to find any existing Albums (record stores)
		String[] currentStores = RecordStore.listRecordStores();
		if (currentStores != null) {
			System.out.println("MediaAccessor::loadAlbums: Found: " + currentStores.length + " existing record stores");
			String[] temp = new String[currentStores.length];
			int count = 0;
			// Only use record stores that follow the naming convention defined
			for (int i = 0; i < currentStores.length; i++) {
				String curr = currentStores[i];
				System.out.println("MediaAccessor::loadAlbums: Current store"+curr+"="+album_label);
				// If this record store is a photo album...
				if (curr.startsWith(album_label)) {
					// Strip out the mpa- identifier
					curr = curr.substring(4);
					// Add the album name to the array
					temp[i] = curr;
					count++;
				}
			}
			// Re-copy the contents into a smaller array now that we know the
			// size
			albumNames = new String[count];
			int count2 = 0;
			for (int i = 0; i < temp.length; i++) {
				if (temp[i] != null) {
					albumNames[count2] = temp[i];
					count2++;
				}
			}
		} else {
			System.out.println("MediaAccessor::loadAlbums: 0 record stores exist. Creating default one.");
			resetRecordStore();
			loadAlbums();
		}
	}
	
	protected abstract void resetRecordStore()
			throws InvalidImageDataException, PersistenceMechanismException;

	protected abstract byte[] getByteFromMediaInfo(MediaData ii)
			throws InvalidImageDataException;

	protected abstract byte[] getMediaArrayOfByte(String path)
			throws ImagePathNotValidException, InvalidImageFormatException;

	protected abstract MediaData getMediaFromBytes(byte[] data)
			throws InvalidArrayFormatException;

	public void addMediaData(String photoname, String path, String albumname)
			throws InvalidImageDataException, PersistenceMechanismException {
		try {
			byte[] data1 = getMediaArrayOfByte(path);
			addMediaArrayOfBytes(photoname, albumname, data1);
		} catch (RecordStoreException e) {
			throw new PersistenceMechanismException();
		}
	}

	protected void addMediaArrayOfBytes(String photoname, String albumname,
			byte[] data1) throws RecordStoreException,
			RecordStoreFullException, RecordStoreNotFoundException,
			RecordStoreNotOpenException, InvalidImageDataException {
		
		mediaRS = RecordStore.openRecordStore(album_label + albumname, true);
		mediaInfoRS = RecordStore.openRecordStore(info_label + albumname, true);

		int rid; // new record ID for Image (bytes)
		int rid2; // new record ID for ImageData (metadata)
		rid = mediaRS.addRecord(data1, 0, data1.length);
		MediaData ii = new MediaData(rid,
				album_label + albumname, photoname);
		rid2 = mediaInfoRS.getNextRecordID();
		ii.setRecordId(rid2);
		data1 = getByteFromMediaInfo(ii);
		mediaInfoRS.addRecord(data1, 0, data1.length);
		mediaRS.closeRecordStore();
		mediaInfoRS.closeRecordStore();
	}
	
	// #ifdef includeCopyPhoto
	/**
	 * [EF] Add in scenario 05
	 * @param photoname
	 * @param imageData
	 * @param albumname
	 * @throws InvalidImageDataException
	 * @throws PersistenceMechanismException
	 */
	public void addMediaData(MediaData mediaData, String albumname) throws InvalidImageDataException, PersistenceMechanismException {
		try {
			mediaRS = RecordStore.openRecordStore(album_label + albumname, true);
			mediaInfoRS = RecordStore.openRecordStore(info_label + albumname, true);
			int rid2; // new record ID for ImageData (metadata)
			rid2 = mediaInfoRS.getNextRecordID();
			mediaData.setRecordId(rid2);
			byte[] data1 = getByteFromMediaInfo(mediaData);
			mediaInfoRS.addRecord(data1, 0, data1.length);
		} catch (RecordStoreException e) {
			throw new PersistenceMechanismException();
		}finally{
			try {
				mediaRS.closeRecordStore();
				mediaInfoRS.closeRecordStore();
			} catch (RecordStoreNotOpenException e) {
				e.printStackTrace();
			} catch (RecordStoreException e) {
				e.printStackTrace();
			}
		}
	}
	// #endif

	/**
	 * This will populate the imageInfo hashtable with the ImageInfo object,
	 * referenced by label name and populate the imageTable hashtable with Image
	 * objects referenced by the RMS record Id
	 * 
	 * @throws PersistenceMechanismException
	 */
	public MediaData[] loadMediaDataFromRMS(String recordName)
			throws PersistenceMechanismException, InvalidImageDataException {
		Vector mediaVector = new Vector();
		try {
			String infoStoreName = info_label + recordName;
			RecordStore infoStore = RecordStore.openRecordStore(infoStoreName,
					false);
			RecordEnumeration isEnum = infoStore.enumerateRecords(null, null,
					false);
			while (isEnum.hasNextElement()) {
				// Get next record
				int currentId = isEnum.nextRecordId();
				byte[] data = infoStore.getRecord(currentId);
				// Convert the data from a byte array into our ImageData
				// (metadata) object
				MediaData iiObject = getMediaFromBytes(data);
				// Add the info to the metadata hashtable
				String label = iiObject.getMediaLabel();
				mediaVector.addElement(iiObject);
				getMediaInfoTable().put(label, iiObject);
			}
			infoStore.closeRecordStore();
		}catch (RecordStoreException rse) {
			throw new PersistenceMechanismException(rse);
		}
		// Re-copy the contents into a smaller array
		MediaData[] labelArray = new MediaData[mediaVector.size()];
		mediaVector.copyInto(labelArray);
		return labelArray;
	}

	/**
	 * Update the Image metadata associated with this named photo
	 * @throws InvalidImageDataException 
	 * @throws PersistenceMechanismException 
	 */
	public boolean updateMediaInfo(MediaData oldData, MediaData newData) throws InvalidImageDataException, PersistenceMechanismException {
		boolean success = false;
		RecordStore infoStore = null;
		try {
			// Parse the Data store name to get the Info store name
			String infoStoreName = oldData.getParentAlbumName();
			infoStoreName = info_label + infoStoreName.substring(album_label.length());
			infoStore = RecordStore.openRecordStore(infoStoreName, false);
			byte[] mediaDataBytes = getByteFromMediaInfo(newData);
			infoStore.setRecord(oldData.getRecordId(), mediaDataBytes, 0, mediaDataBytes.length);
		} catch (RecordStoreException rse) {
			throw new PersistenceMechanismException(rse);
		}
		// Update the Hashtable 'cache'
		setMediaInfo(oldData.getMediaLabel(), newData);
		try {
			infoStore.closeRecordStore();
		} catch (RecordStoreNotOpenException e) {
			//No problem if the RecordStore is not Open
		} catch (RecordStoreException e) {
			throw new PersistenceMechanismException(e);
		}
		return success;
	}

	/**
	 * Retrieve the metadata associated with a specified image (by name)
	 * @throws ImageNotFoundException 
	 * @throws NullAlbumDataReference 
	 */
	public MediaData getMediaInfo(String imageName) throws ImageNotFoundException {
		MediaData ii = (MediaData) getMediaInfoTable().get(imageName);
		if (ii == null)
			throw new ImageNotFoundException(imageName +" was NULL in ImageAccessor Hashtable.");
		return ii;
	}

	/**
	 * Update the hashtable with new ImageInfo data
	 */
	public void setMediaInfo(String mediaName, MediaData newData) {
		getMediaInfoTable().put(newData.getMediaLabel(), newData);
	}

	/**
	 * Get the data for an Image as a byte array. This is useful for sending
	 * images via SMS or HTTP
	 * @throws PersistenceMechanismException 
	 */
	public byte[] loadMediaBytesFromRMS(String recordName,
			int recordId) throws PersistenceMechanismException {
		byte[] mediaData = null;
		try {
			RecordStore albumStore = RecordStore.openRecordStore(recordName,
					false);
			mediaData = albumStore.getRecord(recordId);
			albumStore.closeRecordStore();
		} catch (RecordStoreException rse) {
			System.out.println("Error:"+rse.getMessage());
			throw new PersistenceMechanismException(rse);
		}
		return mediaData;
	}

	/**
	 * Delete a single (specified) image from the (specified) record store. This
	 * will permanently delete the image data and metadata from the device.
	 * @throws PersistenceMechanismException 
	 * @throws NullAlbumDataReference 
	 * @throws ImageNotFoundException 
	 */
	public boolean deleteSingleMediaFromRMS(String storeName, String mediaName) throws PersistenceMechanismException, ImageNotFoundException {
		boolean success = false;
		// Open the record stores containing the byte data and the meta data
		// (info)
		try {
			// Verify storeName is name without pre-fix
			mediaRS = RecordStore
					.openRecordStore(album_label + storeName, true);
			mediaInfoRS = RecordStore.openRecordStore(info_label + storeName,
					true);
			MediaData mediaData = getMediaInfo(mediaName);
			int rid = mediaData.getForeignRecordId();
			mediaRS.deleteRecord(rid);
			mediaInfoRS.deleteRecord(rid);
			mediaRS.closeRecordStore();
			mediaInfoRS.closeRecordStore();
		} catch (RecordStoreException rse) {
			throw new PersistenceMechanismException(rse);
		}
		return success;
	}

	/**
	 * Define a new photo album for mobile photo users. This creates a new
	 * record store to store photos for the album.
	 * @throws PersistenceMechanismException 
	 * @throws InvalidPhotoAlbumNameException 
	 */
	public void createNewAlbum(String albumName) throws PersistenceMechanismException, InvalidPhotoAlbumNameException {
		RecordStore newAlbumRS = null;
		RecordStore newAlbumInfoRS = null;
		if (albumName.equals("")){
			throw new InvalidPhotoAlbumNameException();
		}
		String[] names  = getAlbumNames();
		for (int i = 0; i < names.length; i++) {
			if (names[i].equals(albumName))
				throw new InvalidPhotoAlbumNameException();
		}
		try {
			newAlbumRS = RecordStore.openRecordStore(album_label + albumName,
					true);
			newAlbumInfoRS = RecordStore.openRecordStore(
					info_label + albumName, true);
			newAlbumRS.closeRecordStore();
			newAlbumInfoRS.closeRecordStore();
		} catch (RecordStoreException rse) {
			throw new PersistenceMechanismException(rse);
		}
	}

	/**
	 * @param albumName
	 * @throws PersistenceMechanismException
	 */
	public void deleteAlbum(String albumName) throws PersistenceMechanismException {
		try {
			RecordStore.deleteRecordStore(album_label + albumName);
			RecordStore.deleteRecordStore(info_label + albumName);
		} catch (RecordStoreException rse) {
			throw new PersistenceMechanismException(rse);
		}
	}

	/**
	 * Get the list of photo album names currently loaded.
	 * 
	 * @return Returns the albumNames.
	 */
	public String[] getAlbumNames() {
		return albumNames;
	}

	/**
	 * Get the hashtable that stores the image metadata in memory.
	 * @return Returns the imageInfoTable.
	 */
	public Hashtable getMediaInfoTable() {
		return mediaInfoTable;
	}

	/**
	 * Update the hashtable that stores the image metadata in memory
	 * @param imageInfoTable
	 *            The imageInfoTable to set.
	 */
	public void setMediaInfoTable(Hashtable mediaInfoTable) {
		this.mediaInfoTable = mediaInfoTable;
	}
}