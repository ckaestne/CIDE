package cide.gast;



public class ASTWrappers {
	/**
	 * returns if n inherits the colors of its parent node. usually colors are
	 * inherited, but there are some exceptions, i.e., if statements
	 */
	public static boolean inheritsColors(IASTNode parent, IASTNode n) {
		if (parent == null)
			return true;
		Property property = parent.getLocationInParent();
		if (property == null)
			return true;
		boolean isWrapper = property.isWrapper();
		if (isWrapper 
				&& ((PropertyWrapper<IASTNode, IASTNode>) property).getWrappee() == n)
			return false;
		return true;
	}

	
}
