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
	
	@SuppressWarnings("unchecked")
	@Override
	public void replaceChild(IASTNode oldChild, IASTNode newChild) {
		if (value == oldChild)
			setValue((T) newChild);
	}

	public void setParent(IASTNode parent) {
		super.setParent(parent);
		value.setParentProperty(this);
	}

	@SuppressWarnings("unchecked")
	Property deepCopy() {
		return new PropertyOne<T>(new String(name), (T) value.deepCopy());
	}

	public IASTNode[] getChildren() {
		return new IASTNode[] { value };
	}
}
