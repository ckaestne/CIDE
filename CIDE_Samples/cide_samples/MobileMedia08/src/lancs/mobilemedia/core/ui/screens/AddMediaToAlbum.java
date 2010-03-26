package lancs.mobilemedia.core.ui.screens;

import javax.microedition.lcdui.Command;
import javax.microedition.lcdui.Form;
import javax.microedition.lcdui.TextField;

public class AddMediaToAlbum extends Form {
	
	TextField labeltxt = new TextField("Item label", "", 15, TextField.ANY);
	TextField itempathtxt = new TextField("Path", "", 20, TextField.ANY);
	
	// #ifdef includeMMAPI
	// [NC] Added in the scenario 07
	TextField itemtype = new TextField("Type of media", "", 20, TextField.ANY);
	//#endif
	
	// #if includeSmsFeature || capturePhoto ||captureVideo 
	/* [NC] Added in scenario 06  and changed in scenario 8*/
	 byte[] CapturedMedia = null;
	//#endif	
	
	Command ok;
	Command cancel;

	public AddMediaToAlbum(String title) {
		super(title);
		this.append(labeltxt);
		this.append(itempathtxt);
		
		// #ifdef includeMMAPI
		// [NC] Added in the scenario 07
		this.append(itemtype);
		//#endif
		
		ok = new Command("Save Item", Command.SCREEN, 0);
		cancel = new Command("Cancel", Command.EXIT, 1);
		this.addCommand(ok);
		this.addCommand(cancel);
	}
	
	public String getItemName(){
		return labeltxt.getString();
	}
	
	/**
	 * [EF] Added in scenario 05 in order to reuse this screen in the Copy Photo functionality
	 * @param photoName
	 */
	public void setItemName(String itemName) {
		labeltxt.setString(itemName);
	}
	
	public String getPath() {
		return itempathtxt.getString();
	}

	/**
	 * [EF] Added in scenario 05 in order to reuse this screen in the Copy Photo functionality
	 * @param photoName
	 */
	public void setLabePath(String label) {
		itempathtxt.setLabel(label);
	}
	
	// #ifdef includeMMAPI
	// [NC] Added in the scenario 07
	public String getItemType() {
		return itemtype.getString();
	}
	//#endif
	
	// #if captureVideo || includeSmsFeature || capturePhoto
	// [NC] Added in the scenario 08
	// Add in scenario 6 as getImage and setImage. Due to some problems to convert 
	// Image to byte[], we decided to provide a byte[] rather than Image.
	public byte[] getCapturedMedia() {
		return CapturedMedia;
	}

	public void setCapturedMedia(byte[] capturedMedia) {
		CapturedMedia = capturedMedia;
	}
	//#endif

}
