package cide.gast;



public class ASTWrappers {
	/**
	 * returns true if n inherits the colors of its parent node. usually colors are
	 * inherited, but there are some exceptions, i.e., if statements
	 */
	public static boolean inheritsColors(IASTNode parent, IASTNode n) {
		if (parent == null)
			return true;
		boolean isWrapper = parent.isWrapper();
		if (isWrapper 
				&& parent.getWrappee() == n)
			return false;
		return true;
	}

	
}
