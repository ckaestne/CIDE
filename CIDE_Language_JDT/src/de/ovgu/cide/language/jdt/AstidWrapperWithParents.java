package de.ovgu.cide.language.jdt;

import java.util.List;

import org.eclipse.jdt.core.dom.ASTNode;

import cide.gast.IASTNode;
import cide.gast.IASTVisitor;
import cide.gast.ISourceFile;
import cide.gast.Property;

/**
 * careful, this is an ugly hack in matching the JDT AST and the gCIDE AST. Both
 * have the same IDs, but usually, you need to bridge the entire thing to get
 * parents etc right.
 * 
 * this is an extension of ASTIDWrapper which wraps also all parents. It can
 * hence be used in colormanager.getcolors.
 * 
 * You must know what you're doing when you use this class!
 * 
 * @author ckaestne
 * 
 */
public class AstidWrapperWithParents implements IASTNode {
	private final String astid;
	private final AstidWrapperWithParents parent;

	public AstidWrapperWithParents(ASTNode node) {
		this.astid = ASTID.id(node);
		this.parent = node.getParent() == null ? null
				: new AstidWrapperWithParents(node.getParent());
	}

	public void accept(IASTVisitor visitor) {

	}

	public IASTNode deepCopy() {
		return null;
	}

	public List<IASTNode> getChildren() {
		return null;
	}

	public String getDisplayName() {
		return null;
	}

	public String getId() {
		return astid;
	}

	public int getLength() {
		return 0;
	}

	public Property getLocationInParent() {
		return null;
	}

	public IASTNode getParent() {
		return parent;
	}

	public List<Property> getProperties() {
		return null;
	}

	public Property getProperty(String name) {
		return null;
	}

	public ISourceFile getRoot() {
		return null;
	}

	public int getStartPosition() {
		return 0;
	}

	public IASTNode getWrappee() {
		return null;
	}

	public boolean isOptional() {
		return false;
	}

	public boolean isWrapper() {
		return false;
	}

	public void notifyPropertyChanged(Property property) {

	}

	public void remove() {

	}

	public String render() {
		return null;
	}

	public void replaceSubtreeWith(IASTNode newNode) {

	}

	public void setId(String id) {

	}

	public void setParent(IASTNode parentNode, Property parentProperty) {

	}

	public void setParentProperty(Property parentProperty) {

	}

}