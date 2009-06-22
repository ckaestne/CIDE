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
 * parents etc right. With this wrapper you can circumvent the entire bridge and
 * just pass the ID wrapped in an IASTNode object. Be careful to use this only
 * in locations where it is guaranteed that only the ID is accessed (for example
 * in the colormanager).
 * 
 * You must know what you're doing when you use this class!
 * 
 * @author ckaestne
 * 
 */
public class AstidWrapper implements IASTNode {
	private final String astid;

	public AstidWrapper(String astid) {
		this.astid = astid;
	}	
	public AstidWrapper(ASTNode node) {
		this.astid = ASTID.id(node);
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
		return null;
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