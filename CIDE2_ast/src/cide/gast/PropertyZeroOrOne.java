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
