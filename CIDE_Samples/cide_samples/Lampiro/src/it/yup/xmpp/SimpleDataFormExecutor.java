/* Copyright (c) 2008 Bluendo S.r.L.
 * See about.html for details about license.
 *
 * $Id: SimpleDataFormExecutor.java 1566 2009-06-10 13:13:15Z luca $
*/

package it.yup.xmpp;

import java.util.Date;

// #ifndef UI 

import javax.microedition.lcdui.Display;
import javax.microedition.lcdui.Displayable;


// #endif

import it.yup.xml.Element;
import it.yup.xmpp.XMPPClient.XmppListener;
import it.yup.xmpp.packets.DataForm;
import it.yup.xmpp.packets.Iq;
import it.yup.xmpp.packets.Message;
import it.yup.xmpp.packets.Stanza;

public class SimpleDataFormExecutor implements DataFormListener, Task {

	private Element form_element;

	private String label = new String();
	private DataForm df;
	private byte status;
	private Date arrive_time;

	public SimpleDataFormExecutor(Element el) {
		this.form_element = el;
		this.df = new DataForm(form_element.getChildByName(DataForm.NAMESPACE,
				DataForm.X));
		this.status = (df.type == DataForm.TYPE_FORM) ? Task.DF_FORM
				: Task.DF_RESULT;

		// XXX get a better label perhaps
		//this.label =  df.title.substring(0, Math.min(df.title.length(), 15));
		if (df.title != null) {
			this.label = df.title;
		} else {
			this.label = "No title";
		}
		arrive_time = new Date();
	}

	public boolean execute(int cmd) {
		XMPPClient client = XMPPClient.getInstance();
		boolean send_reply = false;

		switch (cmd) {
			case DataFormListener.CMD_CANCEL:
				df.type = DataForm.TYPE_CANCEL;
				status = Task.DF_CANCELED;
				send_reply = true;
				break;
			case DataFormListener.CMD_SUBMIT:
				df.type = DataForm.TYPE_SUBMIT;
				status = Task.DF_SUBMITTED;
				send_reply = true;
				break;
			case DataFormListener.CMD_DELAY:
				// do nothing, keep status
				break;
			case DataFormListener.CMD_DESTROY:
				status = Task.DF_DESTROY;
				break;
		}

		if (send_reply) {
			Stanza reply = buildReply(form_element);
			reply.addElement(df.getResultElement());
			client.sendPacket(reply);
		}

		client.updateTask(this);

		XmppListener xmppListener = XMPPClient.getInstance().getXmppListener();
		if (xmppListener!=null){
			xmppListener.commandExecuted(null);
		}
		// true if the form_element is an IQ 
		return Iq.IQ.equals(form_element.name) ;
	}

	/**
	 * Build the packet containing the answer
	 * The business logic for making an aswer could be set by modifying this method
	 * XXX I think this should be moved to packets package
	 * @param el the packet containing the form
	 */
	private Stanza buildReply(Element el) {
		Stanza reply = null;
		if (Message.MESSAGE.equals(el.name)) {
			Message msg = new Message(el.getAttribute(Stanza.ATT_FROM), el
					.getAttribute(Stanza.ATT_TYPE));
			Element e = el.getChildByName(Stanza.NS_JABBER_CLIENT,
					Message.THREAD);
			if (e != null) {
				msg.addElement(e);
			}
			reply = msg;
		} else if (Iq.IQ.equals(el.name)) {
			// XXX: Always type='set' because now we handle just the type='from'
			Iq iq = new Iq(el.getAttribute(Stanza.ATT_FROM), Iq.T_SET);
			iq.setAttribute(Stanza.ATT_FROM, el.getAttribute(Stanza.ATT_TO));
			reply = iq;
		}
		return reply;
	}

	// #ifdef UI 
	public void display() {display_internal();}
		// #endif
// #ifndef UI
			public void display(Display disp, Displayable nextDisplay) {display_internal();}
		// #endif
			public void display_internal() {
		Element form = form_element.getChildByName(DataForm.NAMESPACE,
				DataForm.X);

		if (form == null) {
			/* ??? LOG ??? */
			return;
		}

		XmppListener xmppListener = XMPPClient.getInstance().getXmppListener();
		if (xmppListener != null) {
			if (df.type == DataForm.TYPE_FORM) {
				xmppListener.handleDataForm(df,Task.CMD_INPUT,this,-1);
			} else if (df.type == DataForm.TYPE_RESULT) {
				xmppListener.handleDataForm(df,Task.CMD_FINISHED,this,-1);
			} else {
				/* should log it... */
				return;
			}
		}
	}

	public String getLabel() {
		return "[" + arrive_time.toString().substring(11, 16) + "]" + label;
	}

	public byte getStatus() {
		return status;
	}

	public String getFrom() {
		return form_element.getAttribute("from");
	}

}
