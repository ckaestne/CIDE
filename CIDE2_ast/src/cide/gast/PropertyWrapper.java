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

/**
 * used only for generated ASTs
 * 
 * @author ckaestne
 * 
 * @param <T>
 * @param <TWrappee>
 */
public class PropertyWrapper<T extends IASTNode, TWrappee extends IASTNode>
		extends Property {

	protected T value;
	private String wrappeeProperty;
	private boolean isRemoved = false;

	public PropertyWrapper(String name, T value, String wrappeeProperty) {
		super(name, PropertyType.ONE);
		this.value = value;
		this.wrappeeProperty = wrappeeProperty;
		this.isRemoved = false;
	}

	public IASTNode getValue() {
		if (!isRemoved)
			return value;
		else
			return getWrappee();
	}

	/** do not use outside the default ASTNode implementation */
	TWrappee getWrappee() {
		return ((PropertyOne<TWrappee>) value.getProperty(wrappeeProperty))
				.getValue();
	}

	public void setValue(T value) {
		if (value != null) {
			this.value = value;
			this.isRemoved = false;
			notifyChange();
		}
	}

	public boolean canRemoveSubtree(IASTNode node) {
		return !isRemoved;
	}

	public void removeSubtree(IASTNode node) {
		if (node == value) {
			isRemoved = true;
			notifyChange();
		}
	}

	@Override
	public void replaceChild(IASTNode oldChild, IASTNode newChild) {
		if (value == oldChild)
			setValue((T) newChild);
	}

	void setParent(IASTNode parent) {
		super.setParent(parent);
		value.setParentProperty(this);
	}

	Property deepCopy() {
		return new PropertyWrapper<T, TWrappee>(new String(name), (T) value
				.deepCopy(), new String(wrappeeProperty));
	}

	public IASTNode[] getChildren() {
		return new IASTNode[] { getValue() };
	}

	
}
