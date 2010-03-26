/**
    Copyright 2010 Christian Kästner

    This file is part of CIDE.

    CIDE is free software: you can redistribute it and/or modify
    it under the terms of the GNU General Public License as published by
    the Free Software Foundation, version 3 of the License.

    CIDE is distributed in the hope that it will be useful,
    but WITHOUT ANY WARRANTY; without even the implied warranty of
    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
    GNU General Public License for more details.

    You should have received a copy of the GNU General Public License
    along with CIDE.  If not, see <http://www.gnu.org/licenses/>.

    See http://www.fosd.de/cide/ for further information.
*/

package de.ovgu.cide.editor.keepcolors;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import de.ovgu.cide.features.IFeature;

/**
 * this cache stores the found colored elements with type and offset/length
 * (type and location must be sufficient to avoid coloring different items).
 * 
 * changes to text can be propagated to the cache which adapts colors
 * accordingly
 * 
 * A) introductions (1) before: nothing changes, (2) after: positions are
 * adapted, (3) in between: end position adapted
 * 
 * B) removal (1) before/after: positions adapted, (2) in between: covered
 * elements removed, others shorteded
 * 
 * @author ckaestne
 * 
 */
public class ColorCache {

	class ColoredItem {
		String type;
		Set<IFeature> assignedFeatures;
		int offset, length;

		ColoredItem(String type, Set<IFeature> assignedFeatures, int offset,
				int length) {
			this.type = type;
			this.assignedFeatures = assignedFeatures;
			this.offset = offset;
			this.length = length;
		}

		@Override
		public String toString() {
			return type + ":" + offset + "-" + (offset + length);
		}
	}

	List<ColoredItem> itemList = new ArrayList<ColoredItem>();

	public void addItem(String type, Set<IFeature> features, int start, int end) {
		itemList.add(new ColoredItem(type, features, start, end - start));
	}

	public void addItemOL(String type, Set<IFeature> features, int start,
			int length) {
		itemList.add(new ColoredItem(type, features, start, length));
	}

	public void modifiedText(String newText, int offset, int length) {
		if (length > 0)
			removedText(offset, length);
		if (newText!=null && newText.length() > 0)
			insertedText(offset, newText.length());
	}

	private void insertedText(int offset, int length) {
		for (ColoredItem item : itemList) {
			if (offset <= item.offset)
				item.offset += length;
			else if (offset < item.offset + item.length)
				item.length += length;
		}

	}

	private void removedText(int offset, int length) {
		for (Iterator<ColoredItem> iter = itemList.iterator(); iter.hasNext();) {
			ColoredItem item = iter.next();

			if (offset <= item.offset
					&& offset + length >= item.offset + item.length)
				iter.remove();
			else if (offset + length <= item.offset)
				item.offset -= length;
			else if (offset >= item.offset
					&& offset + length <= item.offset + item.length)
				item.length -= length;
			else if (offset < item.offset
					&& offset + length < item.offset + item.length) {
				item.length -= length - (item.offset - offset);
				item.offset = offset;
			} else if (offset > item.offset
					&& offset < item.offset + item.length
					&& offset + length > item.offset + item.length) {
				item.length = offset - item.offset;
			}
		}
	}

	/**
	 * searches the cached items for an item with fitting type and location.
	 * returns the colors of this item or null if no such item was found.
	 */
	public Set<IFeature> findItemColors(String name, int startPosition,
			int length) {
		for (ColoredItem item : itemList) {
			if (item.offset == startPosition && item.length == length
					&& item.type.equals(name))
				return item.assignedFeatures;
		}
		return null;
	}

}
