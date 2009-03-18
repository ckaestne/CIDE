package de.ovgu.cide.typing.fj;

import java.util.ArrayList;
import java.util.List;

import tmp.generated_fj.ExtendedType;
import tmp.generated_fj.MethodDeclaration;
import tmp.generated_fj.TypeDeclaration;
import cide.gast.ASTStringNode;
import cide.gast.IASTNode;
import cide.gast.IASTVisitor;
import cide.gast.ISourceFile;
import cide.gast.NoToken;
import cide.gast.Property;

/**
 * Wrapper für den AST-Knoten TypeDeclaration. Hiermit können wir auch mit dem Typ Object umgehen, ohne dass er
 * irgendwo im Sourcecode deklariert wurde.
 * 
 * @author Malte Rosenthal
 */
public class CFJTypeDeclarationWrapper implements IASTNode {
	
	private TypeDeclaration typeDeclaration;
	private boolean isObjectType;
	
	private static int objectCounter = -1;
	
	public CFJTypeDeclarationWrapper() {
		this.isObjectType = true;
	}
	
	public CFJTypeDeclarationWrapper(TypeDeclaration typeDeclaration) {
		this.typeDeclaration = typeDeclaration;
		this.isObjectType = false;
	}
	
	public TypeDeclaration getTypeDeclaration() {
		return typeDeclaration;
	}
	
	public void setTypeDeclaration(TypeDeclaration typeDeclaration) {
		this.typeDeclaration = typeDeclaration;
	}
	
	public boolean isObjectType() {
		return isObjectType;
	}

	@Override
	public void accept(IASTVisitor visitor) {
		if (typeDeclaration != null)
			typeDeclaration.accept(visitor);
	}

	@Override
	public IASTNode deepCopy() {
		if (typeDeclaration != null)
			return typeDeclaration.deepCopy();
		return null;
	}

	@Override
	public List<IASTNode> getChildren() {
		if (typeDeclaration != null)
			return typeDeclaration.getChildren();
		return null;
	}

	@Override
	public String getDisplayName() {
		if (typeDeclaration != null)
			return typeDeclaration.getDisplayName();
		return "Object";
	}

	@Override
	public String getId() {
		if (typeDeclaration != null)
			return typeDeclaration.getId();
		++objectCounter;
		return "Object" + objectCounter;
	}

	@Override
	public int getLength() {
		if (typeDeclaration != null)
			return typeDeclaration.getLength();
		return 6;
	}

	@Override
	public Property getLocationInParent() {
		if (typeDeclaration != null)
			return typeDeclaration.getLocationInParent();
		return null;
	}

	@Override
	public IASTNode getParent() {
		if (typeDeclaration != null)
			return typeDeclaration.getParent();
		return null;
	}

	@Override
	public List<Property> getProperties() {
		if (typeDeclaration != null)
			return typeDeclaration.getProperties();
		return null;
	}

	@Override
	public Property getProperty(String name) {
		if (typeDeclaration != null)
			return typeDeclaration.getProperty(name);
		return null;
	}

	@Override
	public ISourceFile getRoot() {
		if (typeDeclaration != null)
			return typeDeclaration.getRoot();
		return null;
	}

	@Override
	public int getStartPosition() {
		if (typeDeclaration != null)
			return typeDeclaration.getStartPosition();
		return 0;
	}

	@Override
	public boolean isOptional() {
		if (typeDeclaration != null)
			return typeDeclaration.isOptional();
		return false;
	}

	@Override
	public void notifyPropertyChanged(Property property) {
		if (typeDeclaration != null)
			typeDeclaration.notifyPropertyChanged(property);
	}

	@Override
	public void remove() {
		if (typeDeclaration != null)
			typeDeclaration.remove();
	}

	@Override
	public String render() {
		if (typeDeclaration != null)
			return typeDeclaration.render();
		return null;
	}

	@Override
	public void setParent(IASTNode parentNode, Property parentProperty) {
		if (typeDeclaration != null)
			typeDeclaration.setParent(parentNode, parentProperty);
	}
	
	public void setParentProperty(Property parentProperty) {
		if (typeDeclaration != null)
			typeDeclaration.setParentProperty(parentProperty);
	}

	public ASTStringNode getIdentifier() {
		if (typeDeclaration != null)
			return typeDeclaration.getIdentifier();
		return new ASTStringNode("Object", new NoToken(0));
	}

	public ExtendedType getExtendedType() {
		if (typeDeclaration != null)
			return typeDeclaration.getExtendedType();
		return null;
	}

	public ArrayList<MethodDeclaration> getMethodDeclaration() {
		if (typeDeclaration != null)
			return typeDeclaration.getMethodDeclaration();
		return null;
	}

	@Override
	public void replaceSubtreeWith(IASTNode newNode) {
		if (typeDeclaration != null)
			typeDeclaration.replaceSubtreeWith(newNode);
	}
}
