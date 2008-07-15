package cide.gast;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class PropertyZeroOrMore<T extends IASTNode> extends Property {

	protected final ArrayList<T> valueList;
	/**
	 * the indexlist keeps a list of all values including removed ones so that
	 * the generated ID is permanent and does not change if a value is removed
	 */
	protected final List<T> indexList = new ArrayList<T>();

	public PropertyZeroOrMore(String name, ArrayList<T> value) {
		this(name, value, PropertyType.ZEROORMORE);
	}

	protected PropertyZeroOrMore(String name, ArrayList<T> value,
			PropertyType type) {
		super(name, type);
		this.valueList = value;
		this.indexList.addAll(value);
	}

	/**
	 * returns an unmodifiable list of values.
	 * 
	 * do not modify this list (unmodifiable is only commented our for technical
	 * reasons (ArrayList vs. List)
	 * 
	 * @return
	 */
	public ArrayList<T> getValue() {
		return /* Collections.unmodifiableList */(valueList);
	}

	// public void addValue(T value) {
	// this.valueList.add(value);
	// this.indexList.add(value);
	// }
	//
	// public void removeValue(T value) {
	// this.valueList.remove(value);
	// }

	public boolean canRemoveSubtree(IASTNode node) {
		return valueList.contains(node);
	}

	public void removeSubtree(IASTNode node) {
		this.valueList.remove(node);
		notifyChange();
	}

	void setParent(IASTNode parent) {
		super.setParent(parent);
		for (T value : valueList)
			value.setParent(parent, this);
	}

	String getId(IASTNode node) {
		return super.getId(node) + indexList.indexOf(node);
	}

	/**
	 * after cloning the IDs might change (due to renumbering) but are again
	 * consistent inside the AST
	 */
	Property deepCopy() {
		ArrayList<T> clonedList = new ArrayList<T>(valueList.size());
		for (T entry : valueList)
			clonedList.add((T) entry.deepCopy());
		return new PropertyZeroOrMore<T>(new String(name), clonedList);
	}

	public IASTNode[] getChildren() {
		return valueList.toArray(new IASTNode[valueList.size()]);
	}

}
