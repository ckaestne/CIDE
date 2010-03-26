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

public class PropertyOne<T extends IASTNode> extends Property {

	protected T value;

	public PropertyOne(String name, T value) {
		super(name, PropertyType.ONE);
		this.value = value;
	}

	public T getValue() {
		return value;
	}

	public void setValue(T value) {
		if (value != null){
			this.value = value;
			notifyChange();
		}
	}

	public boolean canRemoveSubtree(IASTNode node) {
		return false;
	}

	public void removeSubtree(IASTNode node) {
		throw new UnsupportedOperationException();
	}
	
	@Override
	public void replaceChild(IASTNode oldChild, IASTNode newChild) {
		if (value == oldChild)
			setValue((T) newChild);
	}

	public void setParent(IASTNode parent) {
		super.setParent(parent);
		value.setParentProperty(this);
	}

	Property deepCopy() {
		return new PropertyOne<T>(new String(name), (T) value.deepCopy());
	}

	public IASTNode[] getChildren() {
		return new IASTNode[] { value };
	}
}
