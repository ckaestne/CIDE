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

	public boolean isWrapper() {
		return false;
	}

	protected void notifyChange() {
		if (parent != null)
			parent.notifyPropertyChanged(this);
	}
}
