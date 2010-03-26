/* Copyright (c) 2008 Bluendo S.r.L.
 * See about.html for details about license.
 *
 * $Id: CommandExecutor.java 1579 2009-06-16 15:55:25Z luca $
*/

package it.yup.xmpp;

import java.util.Date;
import javax.microedition.lcdui.AlertType;

// #ifndef UI
import javax.microedition.lcdui.Display;
import javax.microedition.lcdui.Displayable;

//#endif

import it.yup.xml.Element;
import it.yup.xmlstream.BasicXmlStream;
import it.yup.xmlstream.EventQuery;
import it.yup.xmlstream.PacketListener;
import it.yup.xmpp.XMPPClient.XmppListener;
import it.yup.xmpp.packets.DataForm;
import it.yup.xmpp.packets.Iq;

public class CommandExecutor implements PacketListener, DataFormListener, Task {

	private static final String STATUS_EXECUTING = "executing";
	private static final String STATUS_COMPLETED = "completed";
	private static final String STATUS_CANCELED = "canceled";

	// private static ResourceManager rm = ResourceManager.getManager("common",
	// "en");

	public static final int MSG_BROKEN_DF = 1101;

	/** the command information (node, label) */
	private String[] cmd;
	/** the sid associated with this command iteration */
	private String sid;
	/** the data form associated with this command */
	private DataForm df;
	/** the status of this command */
	private byte status;

	private Element current_element = null;

	private Object screen;

	private String note = null;

	private Date last_modify;

	private int step;

	private String chosenResource;


	public CommandExecutor(String[] cmd, String chosenResource) {
		init(cmd, chosenResource);
	}

	private void init(String[] cmd, String chosenResource) {

		this.cmd = cmd;
		this.chosenResource = chosenResource;
		this.status = Task.CMD_EXECUTING;

		step = 1;
		last_modify = new Date();

		Iq iq = new Iq(chosenResource, Iq.T_SET);
		Element cel = iq.addElement(XMPPClient.NS_COMMANDS, "command");
		cel.setAttribute("node", cmd[0]);
		cel.setAttribute("action", "execute");
		sendPacket(iq);

		XMPPClient.getInstance().updateTask(this);
	}

	private void sendPacket(Iq iq) {
		XMPPClient xmpp = XMPPClient.getInstance();
		EventQuery eq = new EventQuery(Iq.IQ, new String[] { "id" },
				new String[] { iq.getAttribute(Iq.ATT_ID) });

		BasicXmlStream.addOnetimeEventListener(eq, this);
		xmpp.sendPacket(iq);
	}

	public void packetReceived(Element el) {
		current_element = el;

		XMPPClient client = XMPPClient.getInstance();
		Element command = (Element) el.getChildByName(XMPPClient.NS_COMMANDS,
				"command");
		if (command == null) {
			/* ? possible ? */
			return;
		}

		/* every time this is copied, not a problem, SHOULD stay the same */
		sid = command.getAttribute("sessionid");

		/* Parse the dataform if present */
		Element form = command.getChildByName(DataForm.NAMESPACE, DataForm.X);
		if (form != null) {
			df = new DataForm(form);
		} else {
			df = null;
		}

		/*
		 * Some implementations seem to send the result of a completed form
		 * using a DataForm of type "form" instead of "result", so let's check
		 * the "status" and not the type of the form
		 */

		String el_status = command.getAttribute("status");
		if (STATUS_CANCELED.equals(el_status)) {
			/*
			 * the server has canceled the command. could this happen? yes, as
			 * aswer of a cancel
			 */
			this.status = Task.CMD_CANCELED;
		} else if (STATUS_COMPLETED.equals(el_status)) {
			this.status = Task.CMD_FINISHED; // the command is finished
		} else if (STATUS_EXECUTING.equals(el_status)) {
			this.status = Task.CMD_INPUT;
		} else {
			// unexpexted status, discard the message, and notify?
			this.status = Task.CMD_ERROR;
			// XXX is this enough?
		}
		if (el.getAttribute(Iq.ATT_TYPE).equals(Iq.T_ERROR)) this.status = Task.CMD_ERROR;
		if (df == null) {
			if (this.status != Task.CMD_FINISHED) {
				this.status = Task.CMD_FORM_LESS;
			} else {
				this.status = Task.CMD_DESTROY;
			}
		}

		Element note_element = command.getChildByName(
				"http://jabber.org/protocol/commands", "note");
		if (note_element != null) {
			this.note = note_element.getText();
		} else {
			this.note = null;
		}

		client.updateTask(this);
	}

	public boolean execute(int cmd) {
		/*
		 * not checking if the cmd is in the allowed ones, as I have built the
		 * screen accordingly...
		 */
		boolean setWaiting = false;
		last_modify = new Date();
		switch (cmd) {
			case DataFormListener.CMD_CANCEL:
				status = Task.CMD_CANCELING;
				sendReply("cancel", null);
				break;
			case DataFormListener.CMD_PREV:
				step--;
				status = Task.CMD_EXECUTING;
				sendReply("prev", null);
				setWaiting = true;
				break;
			case DataFormListener.CMD_NEXT:
				step++;
				status = Task.CMD_EXECUTING;
				df.type = DataForm.TYPE_SUBMIT;
				sendReply("next", df.getResultElement());
				setWaiting = true;
				break;
			case DataFormListener.CMD_SUBMIT:
				step++;
				status = Task.CMD_EXECUTING;
				df.type = DataForm.TYPE_SUBMIT;
				sendReply("execute", df.getResultElement());
				setWaiting = true;
				break;
			case DataFormListener.CMD_DELAY:
				// do nothing, just display the next screen
				setWaiting = true;
				break;
			case DataFormListener.CMD_DESTROY:
				status = Task.CMD_DESTROY;
		}

		// update commad status
		XMPPClient instance = XMPPClient.getInstance();
		instance.updateTask(this);
		if (instance.getXmppListener() != null) {
			if (screen != null) {
				/* close the screen */
				instance.getXmppListener().commandExecuted(screen);
				screen = null;
			} else {
				instance.getXmppListener().commandExecuted(null);
			}
		}
		return setWaiting;
	}

	void sendReply(String action, Element dfel) {
		Iq iq = new Iq(chosenResource, Iq.T_SET);
		Element cel = iq.addElement("http://jabber.org/protocol/commands",
				"command");
		cel.setAttribute("node", cmd[0]);
		if (sid != null) {
			cel.setAttribute("sessionid", sid);
		}
		if (action != null) {
			cel.setAttribute("action", action);
		}
		if (dfel != null) {
			cel.addElement(dfel);
		}
		sendPacket(iq);
	}

	// #ifdef UI 
	public void display() {display_internal();}
		// #endif
// #ifndef UI
			public void display(Display disp, Displayable next_screen) {display_internal();}
		// #endif
	public void display_internal() {
		Object screen = null;

		XMPPClient client = XMPPClient.getInstance();
		client = XMPPClient.getInstance();
		switch (status) {
			case Task.CMD_INPUT:
				Element command = (Element) current_element.getChildByName(
						XMPPClient.NS_COMMANDS, "command");
				Element actions = command.getChildByName(
						XMPPClient.NS_COMMANDS, "actions");
				if (actions == null) {
					actions = new Element(XMPPClient.NS_COMMANDS, "actions");
					actions.addElement(null, "complete");
				}
				// add the available actions
				if (df.type == DataForm.TYPE_FORM) {
					int cmds = 0;
					Element[] children = actions.getChildren();
					for (int i = 0; i < children.length; i++) {
						Element iel = children[i];
						if ("next".equals(iel.name)) {
							cmds |= DataFormListener.CMD_NEXT;
						} else if ("prev".equals(iel.name)) {
							cmds |= DataFormListener.CMD_PREV;
						} else if ("complete".equals(iel.name)) {
							cmds |= DataFormListener.CMD_SUBMIT;
						}
					}
					XmppListener xmppListener = XMPPClient.getInstance()
							.getXmppListener();
					if (xmppListener != null) {
						this.screen = xmppListener.handleDataForm(df,
								Task.CMD_INPUT, this, cmds);
					}
					/*
					screen = new DataFormScreen(df, this);
					DataFormScreen dfs = (DataFormScreen) screen;
					dfs.setActions(cmds);
					*/
				}
				break;
			case Task.CMD_EXECUTING:
				client.showAlert(AlertType.INFO, "Command Info",
						"Data submitted: awaiting a response from the server",
						null);
				break;
			case Task.CMD_CANCELING:
				client
						.showAlert(
								AlertType.INFO,
								"Command Info",
								"Command canceled: awaiting a response from the server",
								null);
				break;
			case Task.CMD_CANCELED:
				client.showAlert(AlertType.INFO, "Task canceled", "The task "
						+ getLabel() + " has been succesfully canceled", null);
				break;
			case Task.CMD_FINISHED:
				if (df != null) {
					/*
					screen = new DataResultScreen(df, this);
					*/
					XmppListener xmppListener = XMPPClient.getInstance()
							.getXmppListener();
					if (xmppListener != null) {
						screen = xmppListener.handleDataForm(df,
								Task.CMD_FINISHED, this, -1);
					}
				} else {
					// XXX handle note here, if present
					client.showAlert(AlertType.INFO, "Task finished",
							"Task finished", null);
					status = Task.CMD_DESTROY;
					client.updateTask(this);
				}
				break;
			case Task.CMD_ERROR:
				String errorText = "An error occurred while executing the task";
				Element error = (Element) current_element.getChildByName(null,
						"error");
				if (error != null) {
					String code = error.getAttribute("code");
					if (code != null) errorText += (": " + XMPPClient
							.getErrorString(code));
				}
				client.showAlert(AlertType.INFO, "Task error", errorText, null);
				break;
		}

		if (status != Task.CMD_ERROR) {
			if (note != null) {
				client.showAlert(AlertType.INFO, "Note", note, screen);
			}
			if (screen != null) {
				XmppListener xmppListener = client.getXmppListener();
				xmppListener.showCommand(screen);
			}
		}
	}

	public String getLabel() {
		return "[" + last_modify.toString().substring(11, 16) + "][" + step
				+ "] " + cmd[1];
	}

	public byte getStatus() {
		return status;
	}

	public String getFrom() {
		return this.chosenResource;
	}
}
