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

/**
 * property like ZeroOrMore, but when the last element is removed, that also the
 * parent is removed. this is used e.g., in the throws clause, where the
 * "throws" keyword is removed once all exceptions are removed.
 * 
 * @author cKaestner
 * 
 * @param <T>
 */
public class PropertyList<T extends IASTNode> extends PropertyZeroOrMore<T> {

	public PropertyList(String name, ArrayList<T> value) {
		super(name, value, PropertyType.LIST);
	}

	public void removeSubtree(IASTNode value) {
		super.removeSubtree(value);
		if (this.valueList.isEmpty())
			removeParent();
		this.valueList.remove(value);
		notifyChange();
	}

	/**
	 * removes the parent node from it's parent (assume that it is optional!)
	 */
	protected void removeParent() {
		assert parent != null;
		Property parentsLocation = parent.getLocationInParent();
		assert parentsLocation != null;
		assert parentsLocation.canRemoveSubtree(parent) : "Parent must be optional in his parent when using the &LI annotation";
		parentsLocation.removeSubtree(parent);
	}

	@Override
	void setParent(IASTNode parent) {
		super.setParent(parent);
	}

	/**
	 * after cloning the IDs might change (due to renumbering) but are again
	 * consistent inside the AST
	 */
	Property deepCopy() {
		ArrayList<T> clonedList = new ArrayList<T>(valueList.size());
		for (T entry : valueList)
			clonedList.add((T) entry.deepCopy());
		return new PropertyList<T>(new String(name), clonedList);
	}
}
