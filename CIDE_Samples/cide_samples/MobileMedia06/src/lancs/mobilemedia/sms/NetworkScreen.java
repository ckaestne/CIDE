// #if includeSmsFeature
package lancs.mobilemedia.sms;

import javax.microedition.lcdui.Command;
import javax.microedition.lcdui.Form;
import javax.microedition.lcdui.Item;
import javax.microedition.lcdui.TextField;

/**
 * @author tyoung
 *
 * To change the template for this generated type comment go to
 * Window - Preferences - Java - Code Generation - Code and Comments
 */
public class NetworkScreen extends Form {
	
	TextField recPhoneNum = new TextField("Phone #", "5550001", 15, TextField.ANY);
	String rPort = "1000";
	
	Command ok;
	Command cancel;
	
	/**
	 * @param arg0
	 */
	public NetworkScreen(String title) {
		super(title);
		recPhoneNum.setString("5550001");

		this.append(recPhoneNum);
		
		//Added an OK or confirm button to let the parent midlet know when
		//it is time to proceed to the next screen...
		
		ok = new Command("Send Now", Command.OK, 0);
		cancel = new Command("Cancel", Command.EXIT, 1);
		this.addCommand(ok);
		this.addCommand(cancel);
	}
	
	/**
	 * @param arg0
	 * @param arg1
	 */
	public NetworkScreen(String title, Item[] items) {
		super(title, items);
	}
	
	/**
	 * @return Returns the recPhoneNum.
	 */
	public String getRecPhoneNum() {
		return recPhoneNum.getString();
	}

	/**
	 * @return Returns the recPort.
	 */
	public String getRecPort() {
		return rPort;
	}
}
//#endif