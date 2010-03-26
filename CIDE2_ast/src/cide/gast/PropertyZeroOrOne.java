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

public class PropertyZeroOrOne<T extends IASTNode> extends Property {

	protected T value;

	public PropertyZeroOrOne(String name, T value) {
		super(name, PropertyType.ZEROORONE);
		this.value = value;
	}

	public T getValue() {
		return value;
	}

	public void setValue(T value) {
		this.value = value;
		notifyChange();
	}

	public boolean canRemoveSubtree(IASTNode node) {
		return value == node;
	}

	public void removeSubtree(IASTNode node) {
		if (value == node)
			setValue(null);
	}

	@Override
	public void replaceChild(IASTNode oldChild, IASTNode newChild) {
		if (value == oldChild)
			setValue((T) newChild);
	}
	
	void setParent(IASTNode parent) {
		super.setParent(parent);
		if (value != null)
			value.setParentProperty(this);
	}

	public boolean hasValue() {
		return value != null;
	}

	Property deepCopy() {
		T newValue = null;
		if (value != null)
			newValue = (T) value.deepCopy();
		return new PropertyZeroOrOne<T>(new String(name), newValue);
	}

	public IASTNode[] getChildren() {
		if (value != null)
			return new IASTNode[] { value };
		else
			return new IASTNode[] {};
	}
}
