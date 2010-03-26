/*
 * TextField.java
 *
 * Copyright (C) 2007 Irving Bunton Jr
 * http://www.substanceofcode.com
 *
 * This program is free software; you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation; either version 2 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program; if not, write to the Free Software
 * Foundation, Inc., 59 Temple Place, Suite 330, Boston, MA  02111-1307  USA
 *
 */

// Expand to define MIDP define
///@DMIDPVERS@
// Expand to define test ui define
///@DTESTUIDEF@

//#ifdef DTESTUI

package com.substanceofcode.testlcdui;

import java.util.Vector;
import javax.microedition.lcdui.Command;
import javax.microedition.lcdui.Item;
import javax.microedition.lcdui.Displayable;
import javax.microedition.lcdui.Image;
//#ifdef DMIDP20
import javax.microedition.lcdui.ItemCommandListener;
//#endif

import com.substanceofcode.testutil.TestOutput;

/**
 * TextField.
 *
 * Test GUI class to log methods on the TextField class.
 *
 * @author  Irving Bunton
 * @version 1.0
 */
public class TextField extends javax.microedition.lcdui.TextField 
//#ifdef DMIDP20
implements ItemCommandListener
//#endif
{

	//#ifdef DMIDP20
	private ItemCommandListener m_itemCmdListener;
	//#endif

	public TextField(String label, String text,  int maxSize, int constraints) {
		super(label, text,  maxSize, constraints);
		TestOutput.println("Test UI Text Field Label: " + label);
		TestOutput.println("Test UI Text Field text: " + text);
		TestOutput.println("Test UI Text Field maxSize: " + maxSize);
		TestOutput.println("Test UI Text Field constraints: " + constraints);
	}

	public String getString() {
		String rtn = super.getString();
		TestOutput.println("Test UI Text Field getString: " + super.getLabel() + "," + rtn);
		return rtn;
	}

	public void setString(String text) {
		super.setString(text);
		TestOutput.println("Test UI Text Field setString: " + super.getLabel() + "," + text);
		return;
	}

	//#ifdef DMIDP20
	public void commandAction(Command cmd, Item item) {
		TestOutput.println("Test UI StringItem command,item=" + cmd.getLabel() + "," + item.getLabel());
		m_itemCmdListener.commandAction(cmd, item);
	}

    public void setItemCommandListener(ItemCommandListener itemCmdListener) {
        this.m_itemCmdListener = itemCmdListener;
		super.setItemCommandListener(this);
    }
	//#endif

}

//#endif
