/*
 * TextBox.java
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
import javax.microedition.lcdui.CommandListener;
import javax.microedition.lcdui.Displayable;
import javax.microedition.lcdui.Image;

import com.substanceofcode.testutil.TestOutput;

/**
 * TextBox.
 *
 * Test GUI class to log methods on the TextBox class.
 *
 * @author  Irving Bunton
 * @version 1.0
 */
public class TextBox extends javax.microedition.lcdui.TextBox
implements CommandListener {

	private String m_title;
	private CommandListener m_cmdListener;

	public TextBox(String title, String text,  int maxSize, int constraints) {
		super(title, text,  maxSize, constraints);
		this.m_title = title;
		TestOutput.println("Test UI List Title: " + title);
		TestOutput.println("Test UI List text: " + text);
		TestOutput.println("Test UI List maxSize: " + maxSize);
		TestOutput.println("Test UI List constraints: " + constraints);
	}

	public String getString() {
		String rtn = super.getString();
		TestOutput.println("Test UI List getString: " + m_title + "," + rtn);
		return rtn;
	}

	public void setString(String text) {
		super.setString(text);
		TestOutput.println("Test UI List setString: " + m_title + "," + text);
		return;
	}

	public void commandAction(Command cmd, Displayable disp) {
		//#ifdef DMIDP20
		TestOutput.println("Test UI TextField command,displayable=" + cmd.getLabel() + "," + disp.getTitle());
		//#else
		TestOutput.println("Test UI TextField command,displayable=" + cmd.getLabel() + "," + super.getClass().getName());
		//#endif
		m_cmdListener.commandAction(cmd, this);
	}

    public void setCommandListener(CommandListener m_cmdListener) {
		super.setCommandListener(this);
        this.m_cmdListener = m_cmdListener;
    }

}

//#endif
