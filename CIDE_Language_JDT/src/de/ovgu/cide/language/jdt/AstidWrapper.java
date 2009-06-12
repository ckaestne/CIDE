package de.ovgu.cide.language.jdt;

import java.util.List;

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

	@Override
	public void accept(IASTVisitor visitor) {

	}

	@Override
	public IASTNode deepCopy() {
		return null;
	}

	@Override
	public List<IASTNode> getChildren() {
		return null;
	}

	@Override
	public String getDisplayName() {
		return null;
	}

	@Override
	public String getId() {
		return astid;
	}

	@Override
	public int getLength() {
		return 0;
	}

	@Override
	public Property getLocationInParent() {
		return null;
	}

	@Override
	public IASTNode getParent() {
		return null;
	}

	@Override
	public List<Property> getProperties() {
		return null;
	}

	@Override
	public Property getProperty(String name) {
		return null;
	}

	@Override
	public ISourceFile getRoot() {
		return null;
	}

	@Override
	public int getStartPosition() {
		return 0;
	}

	@Override
	public IASTNode getWrappee() {
		return null;
	}

	@Override
	public boolean isOptional() {
		return false;
	}

	@Override
	public boolean isWrapper() {
		return false;
	}

	@Override
	public void notifyPropertyChanged(Property property) {

	}

	@Override
	public void remove() {

	}

	@Override
	public String render() {
		return null;
	}

	@Override
	public void replaceSubtreeWith(IASTNode newNode) {

	}

	@Override
	public void setId(String id) {

	}

	@Override
	public void setParent(IASTNode parentNode, Property parentProperty) {

	}

	@Override
	public void setParentProperty(Property parentProperty) {

	}

}