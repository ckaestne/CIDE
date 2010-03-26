/* Copyright (c) 2008 Bluendo S.r.L.
 * See about.html for details about license.
 *
 * $Id: Config.java 1599 2009-06-19 13:13:04Z luca $
*/

package it.yup.xmpp;

// #mdebug
//@import it.yup.util.Logger;
// #enddebug

import it.yup.util.RMSIndex;
import it.yup.xml.BProcessor;
import it.yup.xml.Element;
import it.yup.xmpp.packets.Presence;
import it.yup.util.Utils;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.util.Enumeration;
import java.util.Hashtable;


import javax.microedition.lcdui.AlertType;
import javax.microedition.rms.RecordStore;


/**
 * Client Configuration
 */
public class Config {

	private static String version = "9.6.0";

	public static final String RMS_NAME = "lampirorms";

	/** name of the record store  (will be signed as deprecated)*/
	public static final String RMS_OLD_NAME = "yuprms";

	/** config index in the record store */
	public static final int RNUM_CONFIG = 1;

	/** roster index in the record store */
	public static final int RNUM_ROSTER = 2;

	private Hashtable properties = new Hashtable();

	/** Connection url used by the GPRS connector */
	// public static final String GPRS_CONNECTION_URL =
	// "socket://www.bluendo.com:5280";
	public static final String GPRS_CONNECTION_URL = "socket://localhost:10080";
	// public static final String GPRS_CONNECTION_URL =
	// "socket://bosh.bluendo.com:10080";

	/** URL (host/port) of the GPRS/HTTP gateway */
	// public static final String HTTP_GW_HOST = "dalek";
	public static final String HTTP_GW_HOST = "bosh.bluendo.com";

	/** path of the GPRS/HTTP gateway */
	public static final String HTTP_GW_PATH = "/httpb";

	/** path of the GPRS/HTTP gateway */
	public static final String SRV_QUERY_PATH = "http://services.bluendo.com/srv/?domain=";

	public static final String BLUENDO_SERVER = "jabber.bluendo.com";

	/**
	 * time the server should wait before sending a response if no data is
	 * available
	 */
	public static final int WAIT_TIME = 30;
	
	/**
	 * The time after which iq answer not arrived are considered as expired and remov ed
	 */
	public static int MAX_PERM_TIME = 60000;

	// /** default value keepalive of the plain socket */
	// XXX We may keep this but the transport should not read it from Config
	private static final int SO_KEEPALIVE = 60 * 5 * 1000;

	/** Config instance */
	private static Config instance;

	public static String TRUE = "t";
	public static String FALSE = "f";

	// Constants for keys saved in the rms
	public static String CONFIG = "config";
	
	/*
	 * The db of all the known capabilities
	 */
	public static String KNOWN_CAPS = "known_caps";
	
	public static String CAPS_PREFIX = "caps";
	
	/*
	 * All the gateways to which I am registered to
	 */
	public static String REGISTERED_GATEWAYS = "reg_gateways";
	
	public static String GROUPS_POSITION= "groups_position";

	// The next ones are variables related to the management of multimedia data:
	// audioTypes and imageTypes are the suffix handled and recognized by the multimedia related
	// screens. audioType and imgType are constants used to identify a file in the
	// store.
	public static int audioType = 1;

	public static int imgType = 0;

	public static String audioTypes[] = new String[] { "amr", "amr-xb", "pcm",
	"ulaw", "gsm", "wav", "au", "raw" };

	public static String imageTypes[] = new String[] { "jpeg", "png", "jfif",
	"bmp" };
	
	
	// constants for values saved in the record store
	/** server name */
	public static short SERVER = 0x0000;
	/** user name */
	public static short USER = 0x0001;
	/** password */
	public static short PASSWORD = 0x0002;
	/** mail address */
	public static short EMAIL = 0x0003;
	/** connecting server (after a SRV_RECORD query ) */
	public static short CONNECTING_SERVER = 0x0004;
	/** sw version */
	public static short VERSION = 0x0005;
	/** sw version */
	public static short SILENT = 0x0006;
	/** logged once */
	public static short LOGGED_ONCE = 0x0007;
	/** keeplive for plain sockets */
	public static short KEEP_ALIVE = 0x0008;
	/** flag which is true after the first succesful login and roster update */
	public static short CLIENT_INITIALIZED = 0x0009;
	/** last "show" used in the presence */
	public static short LAST_PRESENCE_SHOW = 0x0010;
	/** last status message */
	public static short LAST_STATUS_MESSAGE = 0x0011;
	/** last compression settings used */
	public static short COMPRESSION = 0x0019;
	/** last TLS settings used */
	public static short TLS = 0x0020;
	/** last priority */
	public static short LAST_PRIORITY = 0x0017;
	/** XMPP resource */
	public static short YUP_RESOURCE = 0x0021;
	/** Has a qwerty keyboard */
	public static short QWERTY = 0x0022;


	/**
	 * Using bit masks
	 * 
	 * vibration settings:
	 * <ul>
	 * <li>0x00: none </li>
	 * <li>0x01: only when hidden</li>
	 * <li>0x02: only when shown</li>
	 * <li>0x03: always </li>
	 * </ul>
	 * 
	 * tone settings:
	 * <ul>
	 * <li>0x00: none </li>
	 * <li>0x04: only when hidden</li>
	 * <li>0x08: only when shown</li>
	 * <li>0x0C: always </li>
	 * </ul> *
	 * 
	 */
	public static short VIBRATION_AND_TONE_SETTINGS = 0x0012;

	/** The theme associated to the Application */
	public static short COLOR = 0x0013;

	/** tone volume */
	public static short TONE_VOLUME = 0x0014;

	/**
	 * UICanvas keys for left and right. String is a comma-separated couple of
	 * integers representing (in order) left and right key
	 */
	public static short CANVAS_KEYS = 0x0015;

	/*
	 * Font Size for roster and chat
	 */
	public static short FONT_SIZE = 0x0016;

	/*
	 * Font Size for roster and chat
	 */
	public static short HISTORY_SIZE = 0x0018;

	/*
	 * The accepted gateways (i.e. the ones whose contacts do not need manual authorization)
	 */
	public static short ACCEPTED_GATEWAYS = 0x0022;

	/*
	 * the album data
	 */
	public static short MM_ALBUM = 0x0025;
	
	/*
	 * The resolution of the camera in capturing images 
	 */
	public static final short CAMERA_RESOLUTION = 0x0026;
	
	/** the bluendo assistent */
	public static final String LAMPIRO_AGENT = "lampiro@golem.jabber.bluendo.com";

	/** maximum wait time for a packet (should we let configure this ) */
	public static final int TIMEOUT = -1;

	private Hashtable cachedCaps= new Hashtable(7);

	/**
	 * Get the configuration using the stored values (if any), or use the
	 * default values
	 */
	public synchronized static Config getInstance() {
		if (instance == null) {
			instance = new Config();
			instance.loadFromStorage();
		}
		return instance;
	}

	/** Make the constructur private -> singleton */
	private Config() {
	}

	/*
	 * The rmsIndex containing all the data
	 */
	private RMSIndex rms= new RMSIndex(RMS_NAME);

	/**
	 * Load the the configuration from the RMS
	 */
	private synchronized void loadFromStorage() {
		try {
			byte[] b = null;
			boolean needSave = false;
			
			if (RMSIndex.rmExist(RMS_NAME)) {
				// first check for existence of the "new recordStore"
				// however old recordstore handling will be removed in future version
				rms.open();
				b = rms.load(Config.CONFIG.getBytes());
				// if I cannot load the configuration it is better
				// to delete it 
				if (b==null){
					resetStorage(true);
					RecordStore.deleteRecordStore(RMS_NAME);
				}
				rms.close();
			} else if (RMSIndex.rmExist(RMS_OLD_NAME)) {
				// then check for existence of the "old recordStore"
				// load and delete it 
				RecordStore recordStore = null;
				try {
					recordStore = RecordStore.openRecordStore(RMS_OLD_NAME, false);
					b = recordStore.getRecord(RNUM_CONFIG);
					needSave = true;
				} catch (Exception e) {
					setDefaults();
				} finally {
					try {
						if (recordStore != null) {
							recordStore.closeRecordStore();
							RecordStore.deleteRecordStore(RMS_OLD_NAME);
						}
					} catch (Exception e) {
						// #mdebug
//@							e.printStackTrace();
//@							System.out.println("In config resetting" + e.getMessage()
//@								+ e.getClass());
						// #enddebug
					}
				}
			} else {
				setDefaults();
			}

			if (b != null && b.length != 0) {
				DataInputStream in = new DataInputStream(
						new ByteArrayInputStream(b));
				while (in.available() > 0) {
					short code = in.readShort();
					String val = in.readUTF();
					properties.put(String.valueOf(code), val);
				}
				in.close();
			}
			if (needSave == true)
				this.saveToStorage();
			String _version = getProperty(Config.VERSION);

			if (_version == null || _version.compareTo(Config.version) < 0) {
				// the software has been updated, handle here the "update" logic
				setDefaults();
			}
		} catch (Exception e) {
			this.resetStorage(true);
			XMPPClient.getInstance().showAlert(
					AlertType.ERROR,
					"Config Error",
					"Error while loading config:\n" + e.getMessage()
							+ "\nConfig has been reset.", null);
		}
	}

	private void setDefaults() {
		setProperty(Config.VERSION, Config.version);
		setDefault(Config.USER, "");
		setDefault(Config.SERVER, "");
		setDefault(Config.EMAIL, "");
		setDefault(Config.CONNECTING_SERVER, "");
		setDefault(Config.SILENT, "y");
		setDefault(Config.LOGGED_ONCE, "0");
		setDefault(Config.KEEP_ALIVE, "" + SO_KEEPALIVE);
		setDefault(Config.CLIENT_INITIALIZED, Config.FALSE);
		setDefault(Config.LAST_STATUS_MESSAGE,
				"Lampiro (http://lampiro.bluendo.com)");
		setDefault(Config.LAST_PRESENCE_SHOW, Presence.SHOW_ONLINE);
		saveToStorage();
	}

	/**
	 * Reset the options. If hard is set to true even the login credentials are
	 * reset
	 * 
	 * @param hard
	 */
	public void resetStorage(boolean hard) {
		Config cfg;
		String user = null;
		String password = null;
		String server = null;
		String email = null;
		String connectingServer = null;
		if (hard == false) {
			cfg = Config.getInstance();
			try {
				user = cfg.getProperty(Config.USER);
				server = cfg.getProperty(Config.SERVER);
				password = cfg.getProperty(Config.PASSWORD);
				email = cfg.getProperty(Config.EMAIL);
				connectingServer = cfg.getProperty(Config.CONNECTING_SERVER);
			} catch (Exception e) {
				resetStorage(true);
				return;
			}
		}
		properties.clear();
		if (hard == false) {
			cfg = Config.getInstance();
			cfg.setProperty(Config.USER, user);
			cfg.setProperty(Config.PASSWORD, password);
			cfg.setProperty(Config.SERVER, server);
			cfg.setProperty(Config.EMAIL, email);
			cfg.setProperty(Config.CONNECTING_SERVER, connectingServer);
		}
		this.saveToStorage();
		// so that the new options are automatically reloaded
		this.loadFromStorage();
	}

	public synchronized void saveToStorage() {
		try {
			ByteArrayOutputStream baos = new ByteArrayOutputStream();
			DataOutputStream out = new DataOutputStream(baos);

			Enumeration en = properties.keys();
			while (en.hasMoreElements()) {
				String code = (String) en.nextElement();
				out.writeShort(Integer.parseInt(code));
				out.writeUTF((String) properties.get(code));
			}
			byte[] data = baos.toByteArray();
			this.rms.open();
			this.rms.store(Config.CONFIG.getBytes(), data);
			this.rms.close();
		} catch (Exception e) {
			// #mdebug
//@												Logger.log("Error in saving to storage: " + e.getMessage(),
//@															Logger.DEBUG);
//@									
			// #enddebug
			XMPPClient.getInstance().showAlert(AlertType.ERROR, "Config Error",
					"Error while saving config:\n" + e.getMessage(), null);
		}

	}
	
	public synchronized byte [] getData (byte key []){
		this.rms.open();
		byte [] res = this.rms.load(key);
		this.rms.close();
		return res;
	}
	
	public synchronized void setData (byte key[], byte data[]){
		this.rms.open();
		this.rms.store(key, data);
		this.rms.close();
	}

	public String getProperty(short code) {
		return (String) properties.get(String.valueOf(code));
	};

	public String getProperty(short code, String default_value) {
		String s = (String) properties.get(String.valueOf(code));
		return (s == null) ? default_value : s;
	};

	public void setProperty(short code, String value) {
		properties.put(String.valueOf(code), value);
	}

	/**
	 * Set the default value for a property if none is given
	 * 
	 * @param code
	 * @param default_value
	 */
	private void setDefault(short code, String default_value) {
		if (!this.properties.containsKey(String.valueOf(code))) {
			setProperty(code, default_value);
		}
	}

	public synchronized  void saveCapabilities(String node, String ver,
			Element query) {
		String combi = node + ver;
		// should not happen
		if (cachedCaps.contains(combi))
			return;
		byte[] capsRaw = this.getData(KNOWN_CAPS.getBytes());
		if (capsRaw == null) capsRaw = new byte[0];
	
		//read the main record 
		DataInputStream is = new DataInputStream(new ByteArrayInputStream(
				capsRaw));
		int capCount = 0;
		try {
			while (is.available() > 0) {
				is.readUTF();
				is.readUTF();
				capCount++;
			}
		} catch (IOException e) {
			// #mdebug
//@								Logger.log("Error in getting capabilities: received packet: "
//@									+ e.getClass(), Logger.DEBUG);
			// #enddebug
			// reset the capabilities 
			this.setData(KNOWN_CAPS.getBytes(), "".getBytes());
			return;
		}
		// save the new cap
		String newCapKey = CAPS_PREFIX + capCount;
		// #mdebug
//@		
		// #enddebug
		byte[] newCapData = BProcessor.toBinary(query);
		this.setData(newCapKey.getBytes(), newCapData);
	
		// add the new cap
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		DataOutputStream os = new DataOutputStream(baos);
		try {
			os.write(capsRaw);
			os.writeUTF(combi);
			os.writeUTF(newCapKey);
		} catch (IOException e) {
			// should not ever appear
			// #mdebug
//@								Logger.log("Error in saving new capability"
//@									+ e.getClass(), Logger.DEBUG);
			// #enddebug
		}
		this.setData(KNOWN_CAPS.getBytes(), baos.toByteArray());
		this.cachedCaps.put(combi,query);
	}
	
	public synchronized Element getCapabilities(String node, String ver) {
		String combi = node + ver;
		
		Element cachedCap = (Element) cachedCaps.get(combi);
		if (cachedCap != null)
			return cachedCap;
		
		// load the capabilities 
		byte[] capsRaw = this.getData(KNOWN_CAPS.getBytes());
		if (capsRaw == null) return null;
	
		//read the main record 
		DataInputStream is = new DataInputStream(new ByteArrayInputStream(
				capsRaw));
		String capCode = null;
		String ithCap=null;
		String tempCode;
		try {
			while (is.available() > 0) {
				ithCap = is.readUTF();
				tempCode = is.readUTF();
				if (ithCap.equals(combi)){
					capCode = tempCode;
					break;
				}
			}
		} catch (IOException e) {
			// #mdebug
//@								Logger.log("Error in getting capabilities: received packet: "
//@									+ e.getClass(), Logger.DEBUG);
			// #enddebug
			return null ;
		}
		if (capCode == null)
			return null;
		byte capData[] = this.getData(Utils.getBytesUtf8( capCode));
		Element decodedPacket = BProcessor.parse(capData);
		cachedCaps.put(combi, decodedPacket);
		
		return decodedPacket;
	}

}
