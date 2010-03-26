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
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public abstract class ASTNode implements IASTNode {

	public class StartPositionSorter implements Comparator<IASTNode> {

		public int compare(IASTNode o1, IASTNode o2) {
			if (o1.getStartPosition() < o2.getStartPosition())
				return -1;
			if (o1.getStartPosition() > o2.getStartPosition())
				return 1;
			return 0;
		}
	}

	protected final List<Property> properties;

	private Property parentProperty;

	public final IToken firstToken;

	public final IToken lastToken;

	private int offset;

	private int length;

	protected ASTNode(List<Property> properties, IToken firstToken,
			IToken lastToken) {
		this.properties = properties;
		this.firstToken = firstToken;
		this.lastToken = lastToken;
		this.offset = firstToken.getOffset();
		this.length = calcLength();

		for (Property p : properties)
			p.setParent(this);
		if (getLength() < 0)
			throw new RuntimeException(
					"ASTNode created with illegal length of " + getLength());
		// assert getLength()>=0:"ASTNode created with illegal length of
		// "+getLength();
	}

	protected ASTNode(Property[] properties, IToken ft, IToken lt) {
		this(Arrays.asList(properties), ft, lt);
	}

	public void accept(IASTVisitor visitor) {
		if (visitor.visit(this)) {
			List<IASTNode> children = getChildren();

			for (IASTNode child : children)
				child.accept(visitor);
		}
		visitor.postVisit(this);
	}

	private List<IASTNode> childrenCache;

	/**
	 * never call this method from outside this package
	 */
	public void notifyPropertyChanged(Property p) {
		childrenCache = null;
	}

	/**
	 * cache children instead of calculating them for each tree-walk. cache is
	 * invalidated by calling notifyPropertyChanged from the property
	 * (hardcoded)
	 * 
	 * @return
	 */
	public List<IASTNode> getChildren() {
		if (childrenCache == null) {
			List<IASTNode> children = new ArrayList<IASTNode>();

			for (Property property : properties)
				for (IASTNode child : property.getChildren())
					children.add(child);

			Collections.sort(children, new StartPositionSorter());
			childrenCache = children;
		}
		return childrenCache;
	}

	public Property getProperty(String name) {
		for (Property property : properties)
			if (property.name.equals(name))
				return property;
		return null;
	}

	public ISourceFile getRoot() {
		IASTNode parent = this;
		while (parent.getParent() != null) {
			parent = parent.getParent();
		}
		return (ISourceFile) parent;
	}

	/**
	 * ATTENTION: Never call from outside this package! Never call from a
	 * Property!
	 */
	public void setParent(IASTNode parentNode, Property parentProperty) {
		this.parentProperty = parentProperty;
		if (this.parentProperty != null)
			this.parentProperty.setParent(parentNode);
	}

	public void setParentProperty(Property parentProperty) {
		this.parentProperty = parentProperty;
	}

	public IASTNode getParent() {
		if (parentProperty == null)
			return null;
		return parentProperty.getNode();
	}

	public Property getLocationInParent() {
		return parentProperty;
	}

	private String idCache = null;

	public String getId() {
		if (idCache != null)
			return idCache;
		String id = "";
		if (this.getParent() != null)
			id = this.getParent().getId() + "/" + parentProperty.getId(this);
		idCache = id;
		return id;
	}

	public void setId(String id) {
		idCache = id;
	}

	public List<Property> getProperties() {
		return Collections.unmodifiableList(properties);
	}

	public int getStartPosition() {
		return offset;
	}

	public int getLength() {
		return length;
	}

	private int calcLength() {
		if (lastToken.getOffset() == firstToken.getOffset())
			return firstToken.getLength();
		return lastToken.getOffset() + lastToken.getLength()
				- getStartPosition();
	}

	public boolean isOptional() {
		if (parentProperty == null)
			return false;
		return parentProperty.canRemoveSubtree(this);
	}

	protected Property[] cloneProperties() {
		Property[] result = new Property[properties.size()];
		int i = 0;
		for (Property p : properties)
			result[i++] = p.deepCopy();
		return result;
	}

	public abstract IASTNode deepCopy();

	public void remove() {
		if (!isOptional())
			return;
		parentProperty.removeSubtree(this);
	}

	/**
	 * Ersetzt diesen Knoten durch den gegebenen Knoten.
	 * 
	 * ACHTUNG: Die Änderungen von Offsets, die durch ein Austauschen eines
	 * Knotens passieren können, werden NICHT durchgeführt, so dass der AST
	 * unbrauchbar werden könnte. Zur Zeit wird diese Methode nur auf einer
	 * DeepCopy des AST ausgeführt, die dann gerendered wird.
	 * 
	 * @param newNode
	 */
	public void replaceSubtreeWith(IASTNode newNode) {
		newNode.setParentProperty(this.parentProperty);
		newNode.getLocationInParent().replaceChild(this, newNode);
	}

	// public boolean hasReferenceTypes() {
	// return getReferenceTypes().length > 0;
	// }
	//
	// /**
	// * returns all supported reference types for this choice.
	// *
	// * @return array of reference types, possibly empty
	// */
	// public IReferenceType[] getReferenceTypes() {
	// return new IReferenceType[0];
	// }

	/**
	 * prints the AST subtree to a string
	 * 
	 * @return string representation of ASTNode
	 */
	public abstract String render();

	public String getDisplayName() {
		return this.getClass().getSimpleName();
	}

	@Override
	public boolean equals(Object obj) {
		if (obj instanceof IASTNode)
			return getStartPosition() == ((IASTNode) obj).getStartPosition()
					&& getLength() == ((IASTNode) obj).getLength();
		return super.equals(obj);
	}

	@Override
	public int hashCode() {
		return getStartPosition();
	}

	public boolean isWrapper() {
		return getWrappee() != null;
	}

	/**
	 * default implementation looks up whether PropertyWrapper.isWrapper is
	 * used. may be overwritten by other parser generators if needed.
	 * 
	 * the JDT bridge uses a different mechanism
	 */
	public IASTNode getWrappee() {
		Property lip = getLocationInParent();
		if (!(lip instanceof PropertyWrapper))
			return null;
		return ((PropertyWrapper<IASTNode, IASTNode>) lip).getWrappee();

	}

}