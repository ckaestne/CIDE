/* Copyright (c) 2001-2005 Todd C. Stellanova, rawthought
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or
 * sell copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The  above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING
 * FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS
 * IN THE SOFTWARE. 
 * 
 * 
 */

// Expand to define DJSR75 define
//#define DNOJSR75
// Expand to define test define
//#define DNOTEST
// Expand to define logging define
//#define DNOLOGGING

//#ifdef DJSR75
//@package org.kablog.kgui;
//@
//@import java.io.*;
//@import java.util.*;
//@import javax.microedition.io.*;
//@import javax.microedition.io.file.*;
//@import javax.microedition.lcdui.*;
//@import javax.microedition.midlet.*;
//@
//#ifdef DLOGGING
//@import net.sf.jlogmicro.util.logging.Logger;
//@import net.sf.jlogmicro.util.logging.LogManager;
//@import net.sf.jlogmicro.util.logging.Level;
//#endif
//@
//@import com.substanceofcode.rssreader.presentation.UiUtil;
//@
//@public class KFileSelectorImpl 
//@ extends List
//@  implements KFileSelector, CommandListener
//@  , FileSystemListener, Runnable
//@{
//@	protected   Image UPDIR_IMAGE;
//@	protected   Image FOLDER_IMAGE;
//@	protected   Image FILE_IMAGE;
//@
//@	protected static final String FILE_SEPARATOR = 
//@		(System.getProperty("file.separator")!=null)?  System.getProperty("file.separator"): "/";
//@	protected static final String FILE_SEPARATOR_ALT = "/";
//@
//@	private final static String UP_DIR = "..";
//@
//@	private final Command openCommand =
//@		UiUtil.getCmdRsc("cmd.open", Command.ITEM, 1);    
//@
//@	private final Command cancelCommand =
//@		UiUtil.getCmdRsc("cmd.cancel", Command.CANCEL, 2);    
//@
//@	private Vector rootsList = new Vector();
//@
//@	// Stores the current root, if null we are showing all the roots
//@	private FileConnection currentRoot = null;
//@	protected String[] filePatterns = null;
//@	protected byte[] fileDataBlock = null;
//@	protected String selectedFile = null;
//@	protected String selectedURL = null;
//@	protected KViewParent parent = null;
//@	protected String title = null;
//@	protected String defaultDir = null;
//@	protected String iconDir = "";
	//#ifdef DTEST
//@	private static final boolean bDebug = false;
	//#endif
//@	protected boolean bCurFolderIsARoot = true;
//@	protected boolean itemSelected = false;
//@	protected boolean cancelCmd = false;
//@	protected Thread kThread = null;
//@	protected MIDlet midlet = null;
//@
	//#ifdef DLOGGING
//@    private Logger logger = Logger.getLogger("KFileSelectorImpl");
//@    private boolean fineLoggable = logger.isLoggable(Level.FINE);
//@    private boolean finerLoggable = logger.isLoggable(Level.FINER);
//@    private boolean finestLoggable = logger.isLoggable(Level.FINEST);
	//#endif
//@
//@	/* Create the list and initialization. */
//@	public KFileSelectorImpl()
//@	{
//@		super(null, List.IMPLICIT);
//@
//@		try {
//@
			//#ifdef DTEST
//@			if (bDebug) System.out.println("MFS building cmds....");
			//#endif
			//#ifdef DLOGGING
//@			if (fineLoggable) {logger.fine("MFS building cmds....");}
			//#endif
//@
//@			super.addCommand(openCommand);
//@			super.addCommand(cancelCommand);
//@			super.setSelectCommand(openCommand);
//@			super.setCommandListener(this);
//@
			//#ifdef DTEST
//@			if (bDebug) 
//@			{
//@				System.out.println("--- file sep: '" + FILE_SEPARATOR + "'");
//@				System.out.println("--- file sep_alt: '" + FILE_SEPARATOR_ALT + "'");
//@			}
			//#endif
			//#ifdef DLOGGING
//@			if (fineLoggable) {logger.fine("--- file sep: '" + FILE_SEPARATOR + "'");}
//@			if (fineLoggable) {logger.fine("--- file sep_alt: '" + FILE_SEPARATOR_ALT + "'");}
			//#endif
//@			if (kThread == null) {
//@				kThread = new Thread(this);
//@				kThread.start();
//@			}
//@		} catch (Throwable t) {
			//#ifdef DLOGGING
//@			logger.severe("KFileSelectorImpl constructor", t);
			//#endif
//@			/** Error while executing constructor */
//@			System.out.println("KFileSelectorFactory getInstance " + t.getMessage());
//@			t.printStackTrace();
//@		}
//@
//@	} //constructor
//@
//@	/* Get image.  Need retry to handle sometimes failure. */
//@	private Image getImage(String imagePath) {
//@		Image rtnImage = null;
//@
//@		try {
//@			try {
//@				// createImage("/icons/(image)") does not always work
//@				// with the emulator.  so, I do an alternate which is
//@				// effectively the same thing.
//@				rtnImage = Image.createImage(imagePath);
//@			} catch(IOException e) {
				//#ifdef DMIDP20
				//#ifdef DLOGGING
//@				logger.warning("createImage failed", e);
				//#endif
//@				InputStream is =
//@						this.getClass().getResourceAsStream(imagePath);
//@				rtnImage = Image.createImage(is);
//@				is.close();
				//#else
				//#ifdef DLOGGING
//@				logger.severe("--- icons ex: ", imgOpenEx);
				//#endif
				//#endif
//@			}
//@		} catch(Exception imgOpenEx) {
			//#ifdef DTEST
//@			if (bDebug) System.out.println("--- icons ex: " + imgOpenEx);
			//#endif
			//#ifdef DLOGGING
//@			logger.severe("--- icons ex: ", imgOpenEx);
			//#endif
//@		} finally {
//@			return rtnImage;
//@		}
//@	}
//@
//@	/* Initialize.  Get images. */
//@	public void init() {
		//#ifdef DTEST
//@		if (bDebug) System.out.println("MFS load images....");
		//#endif
//@		//ROOT_IMAGE = Image.createImage("root_icon.png");
//@		FOLDER_IMAGE = UiUtil.getImage(iconDir + "/folder_icon.png");
//@		FILE_IMAGE = UiUtil.getImage(iconDir + "/file_icon.png");
//@		UPDIR_IMAGE =  UiUtil.getImage(iconDir + "/up_dir_icon.png");
//@	}
//@
//@	// Init fields.
//@	public void init(MIDlet midlet, String title, String defaultDir,
//@					 String iconDir)
//@	{
//@
//@		super.setTitle(title);
//@		this.midlet = midlet;
//@		this.title = title;
//@		this.defaultDir = defaultDir;
//@		this.iconDir = iconDir;
//@		init();
//@	}
//@
//@	/* Thread run method used to execute actions.  */
//@	public void run() {
//@		try {
//@			while (true) {
//@				try {
//@					if (itemSelected) {
//@						try {
//@							openSelected();
//@						} catch (Throwable t) {
//@							if (midlet != null) {
//@								Alert internalAlert = new Alert(
//@										"Internal problem", 
//@										"Internal error.  Unable to open.",
//@										null,
//@										AlertType.ERROR);
//@								internalAlert.setTimeout(Alert.FOREVER);
//@								Display.getDisplay(midlet).setCurrent(
//@										internalAlert, this );
//@							}
							//#ifdef DLOGGING
//@							logger.severe("KFileSelectorImpl openSelected ", t);
							//#endif
//@							/** Error while executing constructor */
//@							System.out.println("KFileSelectorFactory openSelected " + t.getMessage());
//@							t.printStackTrace();
//@						}
//@					} else if (cancelCmd) {
//@						doCleanup();
//@						parent.childFinished(this);
//@						cancelCmd = false;
//@						break;
//@					}
//@				} catch (Exception e) {
					//#ifdef DLOGGING
//@					logger.severe("KFileSelectorImpl run ", e);
					//#endif
//@				} finally {
//@					itemSelected = false;
//@				}
//@
//@				try {
//@					Thread.sleep(500L);
//@				} catch (InterruptedException e) {
//@				}
//@			}
//@		} catch (Throwable t) {
			//#ifdef DLOGGING
//@			logger.severe("KFileSelectorImpl run ", t);
			//#endif
//@			/** Error while executing constructor */
//@			System.out.println("KFileSelectorFactory run " + t.getMessage());
//@			t.printStackTrace();
//@		}
//@	}
//@
//@	/**
//@	 * @param The callback client interested in receiving finished status.
//@	 */
//@	public void setViewParent(KViewParent aParent)
//@	{
//@		if (null == this.parent) {
//@			FileSystemRegistry.addFileSystemListener(this);
//@			resetRoots(); //display the root directories
//@		}
//@		this.parent = aParent;	
//@	}//setViewParent
//@
//@
//@	/**
//@	 * Cleanup any allocated resources immediately.
//@	 */
//@	public void doCleanup()
//@	{
//@		this.selectedFile = null;
//@		this.selectedURL = null;
//@
//@		if (null != currentRoot) {
//@			try {currentRoot.close();}
//@			catch (IOException ioEx) {};
//@			currentRoot = null;
//@		}
//@
//@		fileDataBlock = null;
//@
//@	}//doCleanup
//@
//@	public void commandAction(Command c, Displayable d)
//@	{
//@
//@		try {
			//#ifdef DTEST
//@			if (bDebug) System.out.println("cmd action: " + c);
			//#endif
			//#ifdef DLOGGING
//@			if (fineLoggable) {logger.fine("disp,cmd=" + d.getTitle() + "," + c.getLabel());}
			//#endif
//@
//@			if (c == openCommand)
//@			{
//@				itemSelected = true;
//@			}
//@			else if (c == cancelCommand)
//@			{
//@				cancelCmd = true;
//@
//@			}
//@		} catch (Throwable t) {
			//#ifdef DLOGGING
//@			logger.severe("KFileSelectorImpl commandAction ", t);
			//#endif
//@			/** Error while executing constructor */
//@			System.out.println("KFileSelectorImpl commandAction " + t.getMessage());
//@			t.printStackTrace();
//@
//@		}
//@
//@	}//commandAction
//@
//@
//@	/* Show current or all root directories.  */
//@	public void resetRoots()
//@	{
		//#ifdef DTEST
//@		if (bDebug) System.out.println("resetRoots...");
		//#endif
		//#ifdef DLOGGING
//@		if (fineLoggable) {logger.fine("resetRoots...");}
		//#endif
//@
//@		loadRoots();
//@
//@		if (defaultDir != null)
//@		{
			//#ifdef DTEST
//@			if (bDebug) System.out.println("default.dir: " + defaultDir);
			//#endif
//@			try
//@			{
//@				currentRoot = (FileConnection) Connector.open(  defaultDir,  Connector.READ);
//@				displayCurrentRoot();
//@			}
//@			catch (Exception e)
//@			{
				//#ifdef DTEST
//@				if (bDebug) System.out.println("### resetroot ex: " + e);
				//#endif
				//#ifdef DLOGGING
//@				logger.severe("KFileSelectorImpl constructor ", e);
				//#endif
//@				displayAllRoots();
//@			}
//@		}
//@		else
//@		{
//@			displayAllRoots();
//@		}
//@	}//resetRoots
//@
//@
//@
//@
//@	/* Display all roots. */
//@	protected void displayAllRoots()
//@	{
		//#ifdef DTEST
//@		if (bDebug) System.out.println("displayAllRoots...");
		//#endif
//@
//@		super.setTitle(title);
//@		super.deleteAll();
//@		Enumeration roots = rootsList.elements();
//@		while (roots.hasMoreElements())
//@		{
//@			String root = (String) roots.nextElement();
			//#ifdef DTEST
//@			if (bDebug) System.out.println("root: " + root);
			//#endif
//@			super.append(root.substring(1), FOLDER_IMAGE);
//@		}
//@		currentRoot = null;
//@	}//displayAllRoots
//@
//@
//@
//@
//@
//@
//@	/* Load roots into rootsList array. */
//@	protected void loadRoots()
//@	{
		//#ifdef DTEST
//@		if (bDebug) System.out.println("loadRoots...");
		//#endif
//@
//@		bCurFolderIsARoot = true;
//@
//@		if (!rootsList.isEmpty())
//@		{
//@			rootsList.removeAllElements();
//@		}
//@
//@		try {
//@			Enumeration roots = FileSystemRegistry.listRoots();
//@			while (roots.hasMoreElements())
//@			{
//@				rootsList.addElement(FILE_SEPARATOR + (String) roots.nextElement());
//@			}
//@		} catch (Throwable e) {
			//#ifdef DTEST
//@			if (bDebug) System.out.println("### load roots: " + e);
			//#endif
//@		}
//@	}//loadRoots
//@
//@
//@	/* Open the selected directory or file. */
//@	protected void openSelected()
//@	{
//@
//@		int selectedIndex = super.getSelectedIndex();
		//#ifdef DTEST
//@		if (bDebug) System.out.println("openSelected....");
//@		if (bDebug) System.out.println("selectedIndex: " + selectedIndex);
		//#endif
		//#ifdef DLOGGING
//@		if (fineLoggable) {logger.fine("openSelected selectedIndex: " + selectedIndex);}
		//#endif
//@
//@		if (selectedIndex >= 0)
//@		{
//@			selectedFile = super.getString(selectedIndex);
			//#ifdef DTEST
//@			if (bDebug) System.out.println("selectedFile: " + selectedFile);
			//#endif
			//#ifdef DLOGGING
//@			if (fineLoggable) {logger.fine("openSelected selectedFile: " + selectedFile);}
			//#endif
//@
//@			if (null != selectedFile)
//@			{
//@				if ( selectedFile.endsWith(FILE_SEPARATOR) || selectedFile.endsWith(FILE_SEPARATOR_ALT))
//@				{
//@					try
//@					{
//@						if (null == currentRoot)
//@						{
							//#ifdef DTEST
//@							if (bDebug) System.out.println("new currentRoot...");
							//#endif
//@							currentRoot = (FileConnection) Connector.open("file:///" + selectedFile, Connector.READ);
//@						}
//@						else
//@						{
							//#ifdef DTEST
//@							if (bDebug) System.out.println("set cur root conn...");
							//#endif
//@							currentRoot.setFileConnection(selectedFile);
//@						}
//@						displayCurrentRoot();
//@					}
//@					catch (SecurityException e)
//@					{
						//#ifdef DTEST
//@						if (bDebug) System.out.println("### open file: " + e);
						//#endif
						//#ifdef DLOGGING
//@						logger.severe("openSelected security exception selected:  " + selectedFile, e);
//@						logger.severe("openSelected root url selected:  " + currentRoot.getURL(), e);
						//#endif
//@						if (midlet != null) {
//@							Alert securityAlert = new Alert(
//@									"Security problem", 
//@									"Security problem found either access " +
//@									" denied or access refused by the user.",
//@									null,
//@									AlertType.ERROR);
//@							securityAlert.setTimeout(Alert.FOREVER);
//@							Display.getDisplay(midlet).setCurrent(
//@									securityAlert, this );
//@						}
//@						throw e;
//@					}
//@					catch (IllegalArgumentException e)
//@					{
//@						if (midlet != null) {
//@							Alert illegalAlert = new Alert(
//@									"Security problem", 
//@									"Security problem found either access " +
//@									" denied or access refused by the user.",
//@									null,
//@									AlertType.ERROR);
//@							illegalAlert.setTimeout(Alert.FOREVER);
//@							Display.getDisplay(midlet).setCurrent(
//@									illegalAlert, this );
//@						}
						//#ifdef DTEST
//@						if (bDebug) System.out.println("### open file: " + e);
						//#endif
						//#ifdef DLOGGING
//@						logger.severe("openSelected illegal argument selected:  " + selectedFile, e);
//@						logger.severe("openSelected root url selected:  " + currentRoot.getURL(), e);
						//#endif
//@						throw e;
//@					}
//@					catch (Exception e)
//@					{
						//#ifdef DTEST
//@						if (bDebug) System.out.println("### open file: " + e);
						//#endif
						//#ifdef DLOGGING
//@						logger.severe("openSelected exception selected:  " + selectedFile, e);
						//#endif
//@					}
//@				}
//@				else if (selectedFile.equals(UP_DIR))
//@				{
//@					String curRootName = currentRoot.getPath()  + currentRoot.getName();
					//#ifdef DTEST
//@					if (bDebug) System.out.println("curRootName: " + curRootName);
					//#endif
//@					String curShortName = null;
//@					if (curRootName.charAt(0) == '/') {
//@						curShortName = curRootName.substring(1);
//@					}
					//#ifdef DLOGGING
//@					if (finestLoggable) {logger.finest("curShortName=" + curShortName);}
//@					if (finestLoggable) {logger.finest("rootsList(0)=" + (String)rootsList.elementAt(0));}
					//#endif
//@
//@					if( rootsList.contains(curRootName) ||
//@						((curShortName != null) &&
//@					    rootsList.contains(FILE_SEPARATOR + curShortName)))
//@					{
//@						displayAllRoots();
//@					}
//@					else
//@					{
//@						try
//@						{
//@							currentRoot.setFileConnection(UP_DIR);
//@							displayCurrentRoot();
//@						}
//@						catch (IOException e)
//@						{
							//#ifdef DLOGGING
//@							logger.severe("KFileSelectorImpl openSelected at dir " + currentRoot.getPath() + "," + currentRoot.getName(), e);
							//#endif
							//#ifdef DTEST
//@							if (bDebug) System.out.println("### setfileConn: " + e);
							//#endif
//@						}
//@						catch (IllegalArgumentException e)
//@						{
//@							//there's something hosed with this path-- jump back to roots
//@							displayAllRoots();
//@						}
//@					}
//@				}
//@				else
//@				{
					//#ifdef DTEST
//@					if (bDebug) System.out.println("user selected: " + selectedFile);
					//#endif
//@
//@					//the user has selected a particular file
//@
//@
//@					//parent.childFinished(this);
//@
//@					// Clean up in separate thread.  This also saves
//@					// the selectedURL and sends childFinished.
//@					parent.addDeferredAction(new KFileSelectorKicker(this));
//@
//@				}
//@			}
//@			else {
				//#ifdef DTEST
//@				if (bDebug) System.out.println("### no selected file???");
				//#endif
//@
//@			}
//@		}
//@	}//openSelected
//@
//@
//@	/* Finish completion. */
//@	protected void doNotifyOpComplete() {
//@
//@		if (null != currentRoot)
//@		{
//@			selectedURL = currentRoot.getURL() + selectedFile;	
			//#ifdef DTEST
//@			if (bDebug) System.out.println("=== Selected URL: " + selectedURL);
			//#endif
//@
//@			try {currentRoot.close();}
//@			catch(IOException ioEx) {}
//@		}
//@
//@		currentRoot = null; //reset it
//@		//selectedFile = null;
//@
//@		parent.childFinished(this);
//@	}
//@
//@
//@	/* Display the current root. */
//@	protected void displayCurrentRoot()
//@	{
		//#ifdef DTEST
//@		if (bDebug) System.out.println("displayCurrentRoot...");
		//#endif
//@
//@		try
//@		{
//@			if (null != currentRoot)
//@			{
//@				String rootName = currentRoot.getName();
//@				if ((rootName == null) || (rootName.length() < 1))
//@					rootName = selectedFile;
//@
//@				setTitle("[" + rootName + "]");
//@
//@			}
//@			else
//@				setTitle(title);
//@
//@			// open the root
//@			super.deleteAll();
//@			super.append(UP_DIR, UPDIR_IMAGE);
//@
//@			// list all dirs
//@			Enumeration listOfDirs = currentRoot.list("*", false);
//@			while (listOfDirs.hasMoreElements())
//@			{
//@				String currentDir = (String) listOfDirs.nextElement();
//@
//@				if (currentDir.endsWith(FILE_SEPARATOR)  || currentDir.endsWith(FILE_SEPARATOR_ALT))
//@				{
//@					super.append(currentDir, FOLDER_IMAGE);
//@				}
//@			}
//@
//@			if (filePatterns != null) {
//@				for (int ic = 0; ic > filePatterns.length; ic++) {
//@					// list all filePatterns files and dont show hidden files
//@					Enumeration listOfFiles = currentRoot.list(filePatterns[ic], false);
//@					while (listOfFiles.hasMoreElements())
//@					{
//@						String currentFile = (String) listOfFiles.nextElement();
//@						super.append(currentFile, FILE_IMAGE);
//@					}
//@				}
//@			} else {
//@				// list all files
//@				Enumeration listOfFiles = currentRoot.list();
//@				while (listOfFiles.hasMoreElements())
//@				{
//@					String currentFile = (String) listOfFiles.nextElement();
//@					if (!currentFile.endsWith(FILE_SEPARATOR) &&
//@							!currentFile.endsWith(FILE_SEPARATOR_ALT)) {
//@						super.append(currentFile, FILE_IMAGE);
//@					}
//@				}
//@			}
//@
//@		}
//@		catch (Exception e)
//@		{
			//#ifdef DLOGGING
//@			logger.severe("KFileSelectorImpl constructor", e);
			//#endif
			//#ifdef DTEST
//@			if (bDebug) System.out.println("### displayRoot ex: " + e);
			//#endif
//@		}
//@
//@	}//displayCurrentRoot
//@
//@	/* Get the selected file name. */
//@	public String getFileName()
//@	{
//@		return selectedFile;
//@	}//getFileName
//@
//@	public String getFileMimeType()
//@	{
//@		String szMimeType = null;
//@
//@		if (null != selectedFile)
//@		{
//@			if ((selectedFile.indexOf("jpg") > 0) || (selectedFile.indexOf("jpeg") > 0))
//@				szMimeType = "image/jpeg";
//@			else if (selectedFile.indexOf("png") > 0)
//@				szMimeType = "image/png";
//@			else if (selectedFile.indexOf("gif") > 0)
//@				szMimeType = "image/gif";
//@			else if (selectedFile.indexOf("bmp") > 0)
//@				szMimeType = "image/bmp";
//@		}
//@
//@		return szMimeType;
//@	}//getMimeType
//@
//@	public Image getThumbnail(int width, int height)
//@	{
//@		Image thumbImage = FILE_IMAGE;
//@		String fileType = getFileMimeType();
//@
//@		if (null != fileType)
//@		{
//@			try {
//@				byte[] datablock = getFileData(); 
//@				thumbImage = Image.createImage(datablock,0,datablock.length);
//@			}
//@			catch (java.lang.OutOfMemoryError oom) {
				//#ifdef DTEST
//@				if (bDebug) System.err.println("### OOM on createImage: "  + Runtime.getRuntime().freeMemory());
				//#endif
//@			}
//@			catch (Exception ex) {
				//#ifdef DTEST
//@				if (bDebug)  {
//@					System.err.println("### Couldn't create image: " + ex);
//@					ex.printStackTrace();
//@				}
				//#endif
//@
//@			}
//@		}
//@
//@		if (thumbImage.getWidth() > (2*width)) {
//@			thumbImage = FILE_IMAGE;
//@		}
//@
		//#ifdef DTEST
//@		if (bDebug) System.out.println("...getThumbnail");
		//#endif
//@
//@		return thumbImage;
//@	}//getThumbnail
//@
//@	/* Get data from the selected file. */
//@	public byte[] getFileData()
//@	{
//@
//@		if (null == fileDataBlock)
//@		{
//@			if (null != selectedURL)
//@			{
//@				try {
//@
//@					long availSize = 0;
//@
					//#ifdef DTEST
//@					if (bDebug) System.out.println("selectedURL: " + selectedURL);
					//#endif
//@
//@					currentRoot = (FileConnection) Connector.open(selectedURL);
//@
//@					//currentRoot.setFileConnection(selectedFile); //relative to current directory
//@
					//#ifdef DTEST
//@					if (bDebug) System.out.println(" getting data...");
					//#endif
//@					availSize = currentRoot.fileSize();
					//#ifdef DTEST
//@					if (bDebug) System.out.println("file availSize: " + availSize);
					//#endif
//@
					//#ifdef DTEST
//@					if (bDebug && !currentRoot.canRead()) System.out.println("### can't read???");
					//#endif
//@
//@
//@					if (availSize > 0)
//@					{
//@
//@						try {
//@							//DataInputStream is = currentRoot.openDataInputStream();
//@							InputStream is = currentRoot.openInputStream();
							//#ifdef DTEST
//@							if (bDebug) System.out.println("Creating new file block [" + availSize + "]");
							//#endif
//@
//@							System.gc();
//@							fileDataBlock = new byte[(int)availSize];
							//#ifdef DTEST
//@							if (bDebug) System.out.println("Allocated: " + availSize);
							//#endif
//@
//@							is.read(fileDataBlock);
//@							is.close();
//@
							//#ifdef DTEST
//@							if (bDebug) System.out.println("...data read.");
							//#endif
//@						}
//@						catch (IOException ioEx)
//@						{
							//#ifdef DTEST
//@							if (bDebug) {
//@								System.err.println("### ioEx: " + ioEx);
//@
//@								ioEx.printStackTrace();
//@							}
							//#endif
//@						}
//@						catch (java.lang.OutOfMemoryError oom)
//@						{
							//#ifdef DTEST
//@							if (bDebug) System.err.println("### OOM new fileDataBlock: "  + Runtime.getRuntime().freeMemory());
							//#endif
//@						}
//@					}
//@
//@					currentRoot.close(); //no longer needed
//@					currentRoot = null;
//@				}
//@				catch (IOException ioEx)
//@				{
					//#ifdef DTEST
//@					if (bDebug) System.out.println("### read file ioex: " + ioEx);
					//#endif
//@				}
//@				catch (Exception ex)
//@				{
					//#ifdef DTEST
//@					if (bDebug) System.out.println("### read file ex: " + ex);
					//#endif
//@
//@				}
//@			}
//@		}
//@		else
//@		{
			//#ifdef DTEST
//@			if (bDebug) System.out.println("Existing fileDataBlock [" + fileDataBlock.length + "]");
			//#endif
//@		}
//@
//@		return fileDataBlock;
//@
//@	}//getFileData
//@
//@
//@	/* Method to listen for changes in root. */
//@	public void rootChanged(int changeType, String strArg)
//@	{
		//#ifdef DTEST
//@		if (bDebug) {
//@			//that's nice...
//@			if (changeType == FileSystemListener.ROOT_ADDED) 
//@				System.out.println("=== FileSys: ROOT ADDED");		
//@			else if (changeType == FileSystemListener.ROOT_REMOVED)
//@				System.out.println("=== FileSys:ROOT_REMOVED");
//@
//@			System.out.println("strArg: " + strArg);
//@		}
		//#endif
//@	}
//@
//@	/* Get selected URL. */
//@	public String getSelectedURL() {
//@		return (selectedURL);
//@	}
//@
//@	/* Set list of file patterns to add to file list.  If null, all files
//@	   are selected. */
//@    public void setFilePatterns(String[] filePatterns) {
//@        this.filePatterns = filePatterns;
//@    }
//@
//@	/* Set list of file patterns to add to file list.  */
//@    public String[] getFilePatterns() {
//@        return (filePatterns);
//@    }
//@
//@    public void setMidlet(MIDlet midlet) {
//@        this.midlet = midlet;
//@    }
//@
//@    public MIDlet getMidlet() {
//@        return (midlet);
//@    }
//@
//@} //class KFileSelectorImpl
//@
//@/* Class to handle the completion of the OP through a thread. */
//@final class KFileSelectorKicker
//@implements Runnable
//@{
//@	
//@	/* Create.  Save target to run. */
//@	public KFileSelectorKicker(KFileSelectorImpl aTarget) {
//@		
//@		target = aTarget;
//@		
//@	}
//@
//@	
//@	/* Complete the Op when we run if we have a target.  */
//@	public void run() {
//@		
//@		if (null != target) {
//@			
//@			target.doNotifyOpComplete();
//@		}
//@	}
//@	
//@	KFileSelectorImpl target;
//@}
//#endif
