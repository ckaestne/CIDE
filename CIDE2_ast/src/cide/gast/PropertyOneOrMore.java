package cide.gast;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class PropertyOneOrMore<T extends IASTNode> extends PropertyZeroOrMore<T> {

	public PropertyOneOrMore(String name, ArrayList<T> value) {
		super(name, value);
	}

	public void removeSubtree(IASTNode value) {
		if (this.valueList.indexOf(value) != 0)
			super.removeSubtree(value);
		notifyChange();
	}

	public boolean canRemoveSubtree(IASTNode node) {
		return this.valueList.indexOf(node) != 0;
	}

	/**
	 * after cloning the IDs might change (due to renumbering) but are again
	 * consistent inside the AST
	 */
	Property deepCopy() {
		ArrayList<T> clonedList = new ArrayList<T>(valueList.size());
		for (T entry : valueList)
			clonedList.add((T) entry.deepCopy());
		return new PropertyOneOrMore<T>(new String(name), clonedList);
	}

}
