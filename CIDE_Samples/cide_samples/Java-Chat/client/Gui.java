package client;

import java.awt.Button;

import java.awt.Color;
import java.awt.Component;
import java.awt.Event;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.TextField;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.DefaultListModel;
import javax.swing.JColorChooser;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JScrollPane;
import javax.swing.ListCellRenderer;


import common.TextMessage;
import common.Utils;

/**
 * simple AWT gui for the chat client
 */
public class Gui extends JFrame implements ChatLineListener {

	private static final long serialVersionUID = 1L;

	protected DefaultListModel outputList;

	protected TextField inputField;
	
	private Client chatClient;

	protected Color curTxtCol;
	protected Button colorButton;

	protected JComboBox coding1;
	protected JComboBox coding2;
	
	/**
	 * creates layout
	 * 
	 * @param title
	 *            title of the window
	 * @param chatClient
	 *            chatClient that is used for sending and receiving messages
	 */
	public Gui(String title, Client chatCl) {
		super(title);
		System.out.println("starting gui...");
		
		setLayout(new GridBagLayout());
		outputList = new DefaultListModel();
		JList list = new JList(outputList);
		
		list.setCellRenderer(new TextMessageRenderer());
		
		add(new JScrollPane(list), new GridBagConstraints(0,0,1,4,1.0,1.0,GridBagConstraints.WEST, GridBagConstraints.BOTH, new Insets(0,0,0,0), 0, 0));
		
		inputField = new TextField();
		add(inputField, new GridBagConstraints(0,4,2,1,1.0,0,GridBagConstraints.WEST, GridBagConstraints.BOTH, new Insets(0,0,0,0),0,0));

		chatClient = chatCl;
		
		// register listener so that we are informed whenever a new chat message
		// is received (observer pattern)
		chatClient.addLineListener(this);
		

		curTxtCol = Color.BLACK;
		colorButton = new Button("Color");
		add(colorButton, new GridBagConstraints(1,0,1,1,0,0,GridBagConstraints.WEST, GridBagConstraints.BOTH, new Insets(0,0,0,0),0,0));

		
		coding1 = new JComboBox(Utils.getCodingList());
		add(coding1, new GridBagConstraints(1,1,1,1,0,0,GridBagConstraints.WEST, GridBagConstraints.BOTH, new Insets(0,0,0,0),0,0));
		coding2 = new JComboBox(Utils.getCodingList());	
		add(coding2, new GridBagConstraints(1,2,1,1,0,0,GridBagConstraints.WEST, GridBagConstraints.BOTH, new Insets(0,0,0,0),0,0));
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		addWindowListener(new WindowAdapter() {
			
			public void windowClosing(WindowEvent e) {
				if (chatClient != null)
					chatClient.stop();
				setVisible(false);
				//System.exit(0);
			}
			
		});
		
		
		pack();
		setVisible(true);
		inputField.requestFocus();
		
	}

	/**
	 * this method gets called every time a new message is received (observer
	 * pattern)
	 */
	public void newChatLine(TextMessage msg) {
		outputList.addElement(msg);
	}

	/**
	 * handles AWT events (enter in textfield and closing window)
	 */
	public boolean handleEvent(Event e) {
		if ((e.target == inputField) && (e.id == Event.ACTION_EVENT)) {
			
			TextMessage txtMsg = new TextMessage((String) e.arg);
			
		
			txtMsg.addSetting(Utils.COLORKEY, Integer.toString(curTxtCol.getRGB()));
		
			
			String tmpEntry;
			
			if (!(tmpEntry = coding1.getSelectedItem().toString()).equals(""))
				txtMsg.addSetting(Utils.CODING1, tmpEntry);
			
			if (!(tmpEntry = coding2.getSelectedItem().toString()).equals(""))
				txtMsg.addSetting(Utils.CODING2, tmpEntry);
			
			
			chatClient.send(txtMsg);
			inputField.setText("");
			return true;
		} 
		
		
		else if ((e.target == colorButton) && (e.id == Event.ACTION_EVENT)) {
			Color tmpColor;
			tmpColor = JColorChooser.showDialog(this,"Select a Text Color", curTxtCol);
			if (tmpColor != null){
				curTxtCol = tmpColor;
			}		
			inputField.setForeground(curTxtCol);
		}
		
		return super.handleEvent(e);
	}
	
	
	/*********
	 * INNER CLASS
	 */
	public class TextMessageRenderer extends JLabel implements ListCellRenderer {


		  public Component getListCellRendererComponent(JList list, Object value, int index,
		      boolean isSelected, boolean cellHasFocus) {
		    
			  if (value instanceof TextMessage) {
				  setText(((TextMessage)value).getSender() + ">"+((TextMessage)value).getContent());
				 
				  String col = ((TextMessage)value).getSetting(Utils.COLORKEY);
				  if (col != null)
					  setForeground(new Color(Integer.parseInt(col)));
				  else
					  setForeground(Color.BLACK);
			  }
			  else {
				  setText(value.toString());  
			  }
		 
		    return this;
		  }
		}
}
