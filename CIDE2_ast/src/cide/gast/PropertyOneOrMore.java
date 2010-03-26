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

package cide.gast;

import java.util.ArrayList;

public class PropertyOneOrMore<T extends IASTNode> extends PropertyZeroOrMore<T> {

	public PropertyOneOrMore(String name, ArrayList<T> value) {
		super(name, value);
	}

	public void removeSubtree(IASTNode value) {
		if (this.valueList.indexOf(value) != 0)
			super.removeSubtree(value);
		notifyChange();
	}

	public boolean canRemoveSubtree(IASTNode node) {
		return this.valueList.indexOf(node) != 0;
	}

	/**
	 * after cloning the IDs might change (due to renumbering) but are again
	 * consistent inside the AST
	 */
	Property deepCopy() {
		ArrayList<T> clonedList = new ArrayList<T>(valueList.size());
		for (T entry : valueList)
			clonedList.add((T) entry.deepCopy());
		return new PropertyOneOrMore<T>(new String(name), clonedList);
	}
}
