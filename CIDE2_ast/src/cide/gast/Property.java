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

public abstract class Property {
	protected final String name;

	protected final PropertyType type;

	protected IASTNode parent;

	public enum PropertyType {
		ONE, ZEROORONE, ZEROORMORE, ONEORMORE, LIST
	}

	public Property(String name, PropertyType type) {
		this.name = name;
		this.type = type;
	}

	public abstract boolean canRemoveSubtree(IASTNode node);

	public abstract void removeSubtree(IASTNode node);

	public abstract IASTNode[] getChildren();
	
	public abstract void replaceChild(IASTNode oldChild, IASTNode newChild);

	/**
	 * Setzt den Parent des Propertys auf den gegebenen AST-Knoten
	 * ACHTUNG: Dieses Property wird nicht als Kind des gegebenen AST-Knoten eingetragen. Dies muss manuell geschehen
	 * 			und passiert im Konstruktor von ASTNode.
	 * @param node Parent-Knoten
	 */
	void setParent(IASTNode node) {
		this.parent = node;
	}

	public IASTNode getNode() {
		return parent;
	}

	public String getName() {
		return name;
	}

	public PropertyType getType() {
		return type;
	}

	/**
	 * generates part of the IASTNode's id
	 * 
	 * @param node
	 * @return
	 */
	String getId(IASTNode node) {
		return name;
	}

	abstract Property deepCopy();


	protected void notifyChange() {
		if (parent != null)
			parent.notifyPropertyChanged(this);
	}
}
