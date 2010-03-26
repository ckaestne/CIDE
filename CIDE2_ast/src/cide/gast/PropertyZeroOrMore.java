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
import java.util.List;

public class PropertyZeroOrMore<T extends IASTNode> extends Property {

	protected final ArrayList<T> valueList;
	
	/**
	 * the indexlist keeps a list of all values including removed ones so that
	 * the generated ID is permanent and does not change if a value is removed
	 */
	protected final List<T> indexList = new ArrayList<T>();

	public PropertyZeroOrMore(String name, ArrayList<T> value) {
		this(name, value, PropertyType.ZEROORMORE);
	}

	protected PropertyZeroOrMore(String name, ArrayList<T> value,
			PropertyType type) {
		super(name, type);
		this.valueList = value;
		this.indexList.addAll(value);
	}

	/**
	 * returns an unmodifiable list of values.
	 * 
	 * do not modify this list (unmodifiable is only commented our for technical
	 * reasons (ArrayList vs. List)
	 * 
	 * @return
	 */
	public ArrayList<T> getValue() {
		return /* Collections.unmodifiableList */(valueList);
	}

//	public void addValue(T value) {
//		if (value != null) {
//			this.valueList.add(value);
//			this.indexList.add(value);
//			notifyChange();
//		}
//	}

//	public boolean removeValue(IASTNode value) {
//		if ((value != null) && this.valueList.remove(value)) {
//			notifyChange();
//			return true;
//		}
//		return false;
//	}
	
	public void setValue(int index, T value) {
		if ((index >= 0) && (index < valueList.size()) && (value != null)) {
			valueList.set(index, value);
			indexList.add(value);
			notifyChange();
		}
	}

	public boolean canRemoveSubtree(IASTNode node) {
		return valueList.contains(node);
	}

	public void removeSubtree(IASTNode node) {
		this.valueList.remove(node);
		notifyChange();
	}
	
	@Override
	public void replaceChild(IASTNode oldChild, IASTNode newChild) {
		int index = -2;
		if (((index = valueList.indexOf(oldChild)) >= 0) && (newChild != null))
			setValue(index, (T) newChild);
	}

	void setParent(IASTNode parent) {
		super.setParent(parent);
		for (T value : valueList)
			value.setParentProperty(this);
	}

	String getId(IASTNode node) {
		return super.getId(node) + indexList.indexOf(node);
	}

	/**
	 * after cloning the IDs might change (due to renumbering) but are again
	 * consistent inside the AST
	 */
	Property deepCopy() {
		ArrayList<T> clonedList = new ArrayList<T>(valueList.size());
		for (T entry : valueList)
			clonedList.add((T) entry.deepCopy());
		return new PropertyZeroOrMore<T>(new String(name), clonedList);
	}

	public IASTNode[] getChildren() {
		return valueList.toArray(new IASTNode[valueList.size()]);
	}
}
