package coloredide.tools.quickfix;

import org.eclipse.jdt.core.dom.ASTNode;
import org.eclipse.jdt.core.dom.ASTVisitor;

import coloredide.features.ASTID;

public class ASTIDFinder extends ASTVisitor {

	ASTIDFinder(String targetID) {
		this.target = targetID;
	}

	private ASTNode result=null;

	private final String target;

	@Override
	public void preVisit(ASTNode node) {
		if (result!=null) return;
		
		ASTID id = ASTID.id(node);
		if (id.id.equals(target))
			result = node;
		super.preVisit(node);
	}
	
	public ASTNode getResult(){return result;}

}
