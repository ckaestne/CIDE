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

	private IASTNode parentNode;

	private Property parentProperty;

	/**
	 * never call from outside this package
	 */
	public void setParent(IASTNode parentNode, Property parentProperty) {
		this.parentNode = parentNode;
		this.parentProperty = parentProperty;
	}

	public IASTNode getParent() {
		return parentNode;
	}

	public Property getLocationInParent() {
		return parentProperty;
	}

	private String idCache = null;

	public String getId() {
		if (idCache != null)
			return idCache;
		String id = "";
		if (parentNode != null)
			id = parentNode.getId() + "/" + parentProperty.getId(this);
		idCache = id;
		return id;
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

}