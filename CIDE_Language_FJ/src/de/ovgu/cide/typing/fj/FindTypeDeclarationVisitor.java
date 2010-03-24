package de.ovgu.cide.typing.fj;

import tmp.generated_fj.TypeDeclaration;
import cide.gast.IASTNode;
import cide.gast.IASTVisitor;

/**
 * Sucht nach dem TypeDeclaration-Knoten, der den Typen mit einem bestimmten identifier deklariert.
 * 
 * @author Malte Rosenthal
 */
public class FindTypeDeclarationVisitor implements IASTVisitor {
	
	private String identifier;
	private TypeDeclaration typeDeclaration;
	private boolean continueSearch = true;
	
	public FindTypeDeclarationVisitor(String identifier) {
		this.identifier = identifier;
	}
	
	public TypeDeclaration getTypeDeclaration() {
		return typeDeclaration;
	}

	
	public void postVisit(IASTNode node) { }
	
	
	public boolean visit(IASTNode node) {
		if (continueSearch && (node instanceof TypeDeclaration) && (identifier.equals(((TypeDeclaration) node).getIdentifier().getValue()))) {
			typeDeclaration = (TypeDeclaration) node;
			continueSearch = false;
		}

		return continueSearch;
	}
}
