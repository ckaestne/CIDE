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
