/*
 * PositionForm.java
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
 * This was first modified no earlier than May 27, 2008.
 *
 */

// Expand to define MIDP define
//#define DMIDP20
// Expand to define test define
//#define DNOTEST

//#ifdef DMIDP20
package cz.cacek.ebook;

import java.util.Vector;

import javax.microedition.lcdui.Canvas;
import javax.microedition.lcdui.CustomItem;
import javax.microedition.lcdui.Choice;
import javax.microedition.lcdui.Command;
import javax.microedition.lcdui.CommandListener;
import javax.microedition.lcdui.Item;
import javax.microedition.lcdui.Display;
import javax.microedition.lcdui.Displayable;
import javax.microedition.lcdui.Font;
import javax.microedition.lcdui.Form;
import javax.microedition.lcdui.Gauge;
import javax.microedition.lcdui.Graphics;

import cz.cacek.ebook.util.ResourceProviderME;
import com.substanceofcode.rssreader.presentation.UiUtil;
import com.substanceofcode.rssreader.presentation.RssReaderMIDlet;

/**
 * Main display. On this CustomItem is displayed current part of book.
 * @author Tom� Darmovzal [tomas.darmovzal (at) seznam.cz]
 * @author Josef Cacek [josef.cacek (at) atlas.cz]
 * @author Jiri Bartos
 * @author $Author: ibuntonjr $
 * @version $Revision: 934 $
 * @created $Date: 2008-07-15 23:33:20 +0200 (Di, 15 Jul 2008) $
 */
public class PositionForm extends Form implements CommandListener {

	private RssReaderMIDlet midlet;

	private Command cmdOK;
	private Command cmdCancel;
	
	private Gauge gauge;
	
	private Display display;
	protected AbstractView view;
	private Item prev;

	/**
	 * Constructor
	 * @param aMidlet
	 * @throws Exception
	 */
	public PositionForm(final String aHead, final AbstractView aView,
					    Item aPrev, RssReaderMIDlet aMidlet) {
		super(ResourceProviderME.get(aHead));
		midlet = aMidlet;
		view = aView;
		prev = aPrev;
		display = Display.getDisplay(midlet);
		cmdOK = UiUtil.getCmdRsc("cmd.ok", Command.OK, 1);
		cmdCancel = UiUtil.getCmdRsc("cmd.cancel", Command.CANCEL, 1);
		super.addCommand(cmdOK);
		super.addCommand(cmdCancel);
		super.setCommandListener(this);
	}

	/**
	 * Constructs reusable gauge screen.
	 * @param aGauge resource key for gauge description
	 * @param aValue initial value on gauge
	 */
	public void createGauge(final String aGauge, final int aValue) {
		if (aValue<0 || aValue>100) {
			throw new RuntimeException("Wrong parameters for Gauge constructor");
		}
		gauge = new Gauge(ResourceProviderME.get(aGauge), true, 100, 0);
		super.append(gauge);
        gauge.setValue(aValue);
		display.setCurrent(this);
	}


	/* (non-Javadoc)
	 * @see javax.microedition.lcdui.CommandListener#commandAction(javax.microedition.lcdui.Command, javax.microedition.lcdui.Displayable)
	 */
	public void commandAction(Command aCmd, Displayable aDisp) {
		//#ifdef DTEST
		Common.debug("Command action " + aCmd);
		//#endif
		if (aCmd == cmdOK) {
			view.setPercPosition(gauge.getValue());
			view.fillPage();
		}
		display.setCurrentItem(prev);
	}

}
//#endif
