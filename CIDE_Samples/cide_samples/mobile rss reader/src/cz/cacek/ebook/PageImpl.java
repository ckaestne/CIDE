/*
   TODO Fix new View.  Get prev pos.
 * PageMgr.java
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
//#define DMIDP20

//#ifdef DMIDP20
package cz.cacek.ebook;

import javax.microedition.lcdui.Command;


/**
 * @author Irving Bunton
 * @created $Date: 2008-07-16 06:09:05 +0200 (Mi, 16 Jul 2008) $
 */
interface PageImpl {

	public void addCommand(Command cmd);
	public int getGameAction(int keyCode);
	public void svcRepaint();

}
//#endif
