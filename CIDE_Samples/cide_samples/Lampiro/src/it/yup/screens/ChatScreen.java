/* Copyright (c) 2008 Bluendo S.r.L.
 * See about.html for details about license.
 *
 * $Id: ChatScreen.java 1377 2009-04-21 14:17:38Z luca $
*/

package it.yup.screens;

import java.io.IOException;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.Vector;

import javax.microedition.io.ConnectionNotFoundException;
import javax.microedition.lcdui.AlertType;
import javax.microedition.lcdui.Canvas;
import javax.microedition.lcdui.Command;
import javax.microedition.lcdui.CommandListener;
import javax.microedition.lcdui.Displayable;
import javax.microedition.lcdui.Font;
import javax.microedition.lcdui.Graphics;
import javax.microedition.lcdui.Image;

import lampiro.LampiroMidlet;
import it.yup.util.ResourceIDs;
import it.yup.util.ResourceManager;
import it.yup.util.Utils;
import it.yup.xml.Element;
import it.yup.xmlstream.BasicXmlStream;
import it.yup.xmlstream.EventQuery;
import it.yup.xmlstream.EventQueryRegistration;
import it.yup.xmlstream.PacketListener;
import it.yup.xmpp.Contact;
import it.yup.xmpp.XMPPClient;

public class ChatScreen extends Canvas implements CommandListener,
		PacketListener {

	private static ResourceManager rm = ResourceManager.getManager("common",
																	"en");

	private Contact user;
	private Command cmd_exit;
	private Command cmd_write;
	private Command cmd_clear;

	private Hashtable cmd_urls = new Hashtable();

	// XXX add a global handler for icons
	private static Image img_msg;

	private static byte SCROLL_UP = 0x01;
	private static byte SCROLL_DOWN = 0x02;
	private static byte SCROLL_LEFT = 0x04;
	private static byte SCROLL_RIGHT = 0x08;

	/** if true this entry can be scrolled inside (when the current entry exceed the screen)*/
	private byte entry_scroll;

	/** true when paint maust scroll to bottom */
	private boolean scroll_to_bottom = true;

	/** true when other chats receive new messages */
	private boolean new_messages = false;

	private static Font cfont;
	// private Image buffer;

	/** height of the used font*/
	private static int text_height;

	private static int header_height;

	/** first entry that is displayed */
	private int start_entry;

	// /** number of currently displayed entries */
	// private int displayed_entries;

	public boolean repaint_header = false;
	//private boolean _entries_scrolled = false;

	static private int header_bg = 0x5590AD;
	static private int header_fg = 0xDDE7EC;

	static private int bg_out_msg = 0xABBBC3;
	static private int fg_out_msg = 0x000000;

	static private int bg_in_msg = 0xCBDBE3;
	static private int fg_in_msg = 0x000000;

	static int scroll_color = 0x444444;

	private EventQueryRegistration reg = null;

	/** wrapped conversion cache */
	private static Hashtable conversations = new Hashtable();
	private Vector current_conversation = null;

	static {
		try {
			img_msg = Image.createImage("/icons/message.png");
			cfont = Font.getFont(Font.FACE_PROPORTIONAL, Font.STYLE_PLAIN,
									Font.SIZE_SMALL);
			text_height = cfont.getHeight();

			if (img_msg.getHeight() > text_height) {
				header_height = img_msg.getHeight() + 2;
			} else {
				header_height = text_height + 2;
			}

		} catch (IOException e) {
			img_msg = Image.createImage(16, 16);
		}
	}

	public ChatScreen(Contact u) {
		user = u;
		// buffer = null;
		start_entry = 0;

		cmd_exit = new Command(rm.getString(ResourceIDs.STR_CLOSE),
				Command.BACK, 1);
		cmd_write = new Command(rm.getString(ResourceIDs.STR_WRITE),
				Command.SCREEN, 1);
		cmd_clear = new Command(rm.getString(ResourceIDs.STR_CLEAR_HIST),
				Command.SCREEN, 2);

		addCommand(cmd_exit);
		addCommand(cmd_write);
		addCommand(cmd_clear);
		setCommandListener(this);

		// pending = rm.getString(ResourceIDs.STR_MESS_PENDING);
		setTitle(rm.getString(ResourceIDs.STR_CHAT_WITH)
				+ user.getPrintableName());

		current_conversation = (Vector) conversations.get(user.jid);
		if (current_conversation == null) {
			current_conversation = new Vector();
			conversations.put(user.jid, current_conversation);
		}

	}

	/**
	 * 
	 * @param screen_width
	 * @return true if new messages have been added
	 */
	private boolean updateConversation(int screen_width) {
		Vector messages = user.getMessageHistory(null);
		if (messages == null) { return false; }
		for (int i = 0; i < messages.size(); i++) {
			String msg[] = (String[]) messages.elementAt(i);
			checkUrls(msg[1]);
			current_conversation.addElement(wrapMessage(msg, screen_width));
		}
		user.resetMessageHistory(null);
		return true;
	}

	protected void showNotify() {
		// listen for all incoming messages with bodies
		EventQuery q = new EventQuery("message", null, null);
		q.child = new EventQuery("body", null, null);

		reg = BasicXmlStream.addEventListener(q, this);
	}

	protected void hideNotify() {
		if (reg != null) {
			reg.remove();
		}
	}

	private void checkUrls(String text) {
		// parse the urls and add to the command menu
		Enumeration en = Utils.find_urls(text).elements();
		while (en.hasMoreElements()) {
			String url = (String) en.nextElement();
			if (!cmd_urls.containsKey(url)) {
				Command cmd = new Command(url.substring(7), url,
						Command.SCREEN, 10);
				cmd_urls.put(url, cmd);
				addCommand(cmd);
			}
		}
	}

	/**
	 * Wrap a message so that it fits the windows
	 * @param  
	 * @param screen_width
	 * 		
	 * @return
	 */
	private ConversationEntry wrapMessage(String text[], int screen_width) {

		//		#ifdef TIMING    	
		    	long t1 = System.currentTimeMillis();
		//		#endif

		byte type = user.jid.equals(text[0]) ? ConversationEntry.ENTRY_TO
				: ConversationEntry.ENTRY_FROM;
		//String begin = (ConversationEntry.ENTRY_TO == type)? "":"";  

		// split the message into tokens, keeping delimiters
		//Vector tokens = Utils.tokenize(begin + text[1], new String[]{"\n", "\r\n", " ", "\t"}, true);
		Vector tokens = Utils
				.tokenize(text[1], new String[] { "\n", "\r\n", " ", "\t" },
							true);

		int line_width = 0;

		Vector lines = new Vector();
		Enumeration en = tokens.elements();
		StringBuffer line = new StringBuffer();
		while (en.hasMoreElements()) {
			String t = (String) en.nextElement();

			if ("\n".equals(t) || "\r\n".equals(t)) {
				line_width = 0;
				lines.addElement(line.toString());
				line.setLength(0);
			} else {
				int width = cfont.stringWidth(t);
				if (line_width + width > screen_width) {
					if (width > screen_width) { // word too long, split it
						for (int i = 0; i < t.length(); i++) {
							int w = cfont.charWidth(t.charAt(i));
							if (line_width + w > screen_width) {
								lines.addElement(line.toString());
								line.setLength(0);
								line.append(t.charAt(i));
								line_width = w;
							} else {
								line.append(t.charAt(i));
								line_width += w;
							}
						}
					} else {
						line_width = width;
						lines.addElement(line.toString());
						line.setLength(0);
						line.append(t);
					}
				} else {
					line.append(t);
					line_width += width;
				}
			}

		}

		if (line.length() > 0) {
			lines.addElement(line.toString());
		}

		//		#ifdef TIMING        
		        System.out.println("wrap conv: " + (System.currentTimeMillis() - t1));
		//		#endif

		return new ConversationEntry(lines, type);

	}

	public void commandAction(Command cmd, Displayable d) {
		if (cmd == cmd_exit) {
			RosterScreen roster = RosterScreen.getInstance();
			roster.updateContact(user, Contact.CH_MESSAGE_READ);
			LampiroMidlet.disp.setCurrent(roster);
		} else if (cmd == cmd_write) {
			SimpleComposerScreen cs = new SimpleComposerScreen(this, user);
			LampiroMidlet.disp.setCurrent(cs);
		} else if (cmd == cmd_clear) {
			current_conversation.removeAllElements();
			start_entry = 0;
			Enumeration en = cmd_urls.elements();
			while (en.hasMoreElements()) {
				removeCommand((Command) en.nextElement());
			}
			cmd_urls.clear();
			repaint();
		} else {
			String url = cmd.getLongLabel();
			System.out.println(url);
			try {
				LampiroMidlet._lampiro.platformRequest(url);
			} catch (ConnectionNotFoundException e) {
				XMPPClient.getInstance().showAlert(
													AlertType.ERROR,
													"URL Error",
													"Can't open URL:"
															+ e.getMessage(),
													this);
			}
		}
	}

	/**
	 * Handle key events
	 * @param kc the pressed key
	 */
	protected void keyPressed(int kc) {

		int ga = getGameAction(kc);

		switch (ga) {
		case Canvas.UP: {
			if ((entry_scroll & SCROLL_UP) == SCROLL_UP) {
				start_entry--;
				repaint();
			}
			break;
		}
		case Canvas.DOWN: {
			if ((entry_scroll & SCROLL_DOWN) == SCROLL_DOWN) {
				start_entry++;
				repaint();
			}
			break;
		}
		case Canvas.RIGHT: {
			if ((entry_scroll & SCROLL_RIGHT) == SCROLL_RIGHT) {
				ConversationEntry e = (ConversationEntry) current_conversation
						.elementAt(start_entry);
				if (e.entry_offset < e.lines.size() - 1) {
					e.entry_offset++;
					repaint();
				}
			}
			break;
		}
		case Canvas.LEFT: {
			if ((entry_scroll & SCROLL_LEFT) == SCROLL_LEFT) {
				ConversationEntry e = (ConversationEntry) current_conversation
						.elementAt(start_entry);
				if (e.entry_offset > 0) {
					e.entry_offset--;
					repaint();
				}
			}
			break;
		}
		case Canvas.FIRE: {
			SimpleComposerScreen cs = new SimpleComposerScreen(this, user);
			LampiroMidlet.disp.setCurrent(cs);
			break;
		}
		}
	}

	public void packetReceived(Element e) {
		if (user.getHistoryLength(null) > 0) {
			repaint();
		} else {
			new_messages = true;
			repaint_header = true;
			repaint();
		}

	}

	protected void paint(Graphics g) {

		//		#ifdef TIMING    	
		    	long t1 = System.currentTimeMillis();
		//		#endif

		int w = g.getClipWidth();

		if (repaint_header) {
			paintHeader(g);
			repaint_header = false;
		} else {
			paintHeader(g);
			scroll_to_bottom = updateConversation(w - 13) || scroll_to_bottom;
			paintEntries(g);
			scroll_to_bottom = false;
		}

		//		#ifdef TIMING        
		        System.out.println("chat paint: " + (System.currentTimeMillis() - t1));
		//		#endif

	}

	protected void paintHeader(Graphics g) {
		int w = g.getClipWidth();
		g.setColor(header_bg);

		g.fillRect(0, 0, w, header_height);
		g.setColor(0xbbbbbb);
		g.drawLine(0, header_height, w, header_height);

		XMPPClient client = XMPPClient.getInstance();
		Image img = client.getPresenceIcon(user,null, user.getAvailability());
		g.drawImage(img, 0, 0, Graphics.TOP | Graphics.LEFT);
		g.setColor(header_fg);
		g.setFont(cfont);
		g.drawString(user.getPrintableName(), 17, 1, Graphics.TOP
				| Graphics.LEFT);
		if (new_messages) {
			g.drawImage(img_msg, w, 0, Graphics.TOP | Graphics.RIGHT);
		}
	}

	protected void paintEntries(Graphics g) {

		int hpos = header_height + 1;
		int w = g.getClipWidth();
//		// #ifdef MOTOROLA
//				int h = getHeight();
//		// #endif
//// #ifndef MOTOROLA
//		int h = g.getClipHeight();
//		// #endif
		int h = false?g.getClipHeight():getHeight();

		if (scroll_to_bottom && current_conversation.size() > 1) {
			start_entry = current_conversation.size() - 1;
			ConversationEntry entry = (ConversationEntry) current_conversation
					.elementAt(start_entry);
			int displayed_height = hpos + entry.lines.size() * text_height + 4;
			while (displayed_height <= h && start_entry > 0) {
				start_entry--;
				entry = (ConversationEntry) current_conversation
						.elementAt(start_entry);
				displayed_height += entry.lines.size() * text_height + 4;
			}
			if (displayed_height > h
					&& start_entry < current_conversation.size() - 1) {
				start_entry++;
			}

			//			do {
			//				start_entry = i;
			//				i--;
			//				entry = (ConversationEntry) current_conversation.elementAt(i);
			//				displayed_height +=  entry.lines.size() * text_height + 4;
			//			}
			//			while(displayed_height < h && i >=0);
			//i = start_entry;
		}

		int i = start_entry;

		g.setFont(cfont);

		while (i < current_conversation.size() && hpos < h) {
			ConversationEntry entry = (ConversationEntry) current_conversation
					.elementAt(i);
			int fg, bg;

			if (entry.type == ConversationEntry.ENTRY_TO) {
				fg = fg_out_msg;
				bg = bg_out_msg;
			} else {
				fg = fg_in_msg;
				bg = bg_in_msg;
			}

			g.setColor(bg);
			g.fillRect(0, hpos, w, text_height * entry.lines.size() + 4);
			g.setColor(fg);

			for (int j = entry.entry_offset; j < entry.lines.size() && hpos < h; j++) {
				if (entry.type == ConversationEntry.ENTRY_TO) {
					String s = (String) entry.lines.elementAt(j);
					g
							.drawString(s, w - 13, hpos, Graphics.TOP
									| Graphics.RIGHT);
				} else {
					g.drawString((String) entry.lines.elementAt(j), 1, hpos,
									Graphics.TOP | Graphics.LEFT);
				}
				hpos += text_height;
			}

			// if this is the start entry and there are too many lines, this entry can be scrolled 
			if (i == start_entry) {
				entry_scroll = 0;
				if (entry.entry_offset > 0) {
					entry_scroll |= SCROLL_LEFT;
					g.setColor(scroll_color);
					g.fillTriangle(w - 2, header_height + 5 + 20, w - 10,
									header_height + 12 + 20, w - 2,
									header_height + 18 + 20);
				}
				if (hpos > h) {
					entry_scroll |= SCROLL_RIGHT;
					g.setColor(scroll_color);
					g.fillTriangle(w - 2, h - 9 - 20, w - 10, h - 2 - 20,
									w - 10, h - 15 - 20);
				}
			}

			g.setColor(0xbbbbbb);
			g.drawLine(0, hpos + 3, w, hpos + 3);
			hpos += 4;
			i++;
		}

		if (hpos < h) {
			g.setColor(header_bg);
			g.fillRect(0, hpos, w, h - hpos);
		}

		if (start_entry > 0) {
			entry_scroll |= SCROLL_UP;
			g.setColor(scroll_color);
			g.fillTriangle(w - 13, 12 + header_height, w - 2,
							12 + header_height, w - 8, header_height + 2);
		}

		if (start_entry < current_conversation.size() - 1 && hpos > h) {
			entry_scroll |= SCROLL_DOWN;
			g.setColor(scroll_color);
			g.fillTriangle(w - 13, h - 14, w - 2, h - 14, w - 8, h - 4);
		}
	}

	private static class ConversationEntry {
		/** message from */
		public static final byte ENTRY_FROM = 0;
		/** message to */
		public static final byte ENTRY_TO = 1;

		/** previous message wrap XXX  ? */
		public static final byte ENTRY_ERROR = 2;

		/** message type in / on */
		public byte type;

		/** the message itself */
		public Vector lines;

		/** first line of the entry that is displayed */
		public int entry_offset = 0;

		public ConversationEntry(Vector lines, byte type) {
			this.type = type;
			this.lines = lines;
		}
	}
}
