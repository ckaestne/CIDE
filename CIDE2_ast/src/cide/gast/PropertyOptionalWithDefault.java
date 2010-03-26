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
 * optional value that is replaced by a string for print-visitors if removed.
 * 
 */
public class PropertyOptionalWithDefault<T extends IASTNode> extends Property {

	protected T value;
	private final String defaultValue;
	private final ASTStringNode defaultNode;

	public PropertyOptionalWithDefault(String name, T value, String defaultValue) {
		super(name, PropertyType.ONE);
		this.value = value;
		this.defaultValue = defaultValue;
		defaultNode = new ASTStringNode(defaultValue, genToken());
	}

	private IToken genToken() {
		return new IToken() {
			public int getLength() {
				return 0;
			}

			public int getOffset() {
				return -1;
			}
		};
	}

	public IASTNode getValue() {
		if (value == null)
			return defaultNode;
		return value;
	}

	public void setValue(T value) {
		this.value = value;
		notifyChange();
	}

	public boolean canRemoveSubtree(IASTNode node) {
		return node == value;
	}

	public void removeSubtree(IASTNode node) {
		if (node == value){
			value = null;
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
		return new PropertyOptionalWithDefault<T>(new String(name), (T) value.deepCopy(), new String(defaultValue));
	}
	
	public IASTNode[] getChildren() {
		if (value != null)
			return new IASTNode[] { value };
		else
			return new IASTNode[] { defaultNode };
	}
}
