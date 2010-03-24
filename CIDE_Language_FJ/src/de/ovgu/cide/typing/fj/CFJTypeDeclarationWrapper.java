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

	
	public void accept(IASTVisitor visitor) {
		if (typeDeclaration != null)
			typeDeclaration.accept(visitor);
	}

	
	public IASTNode deepCopy() {
		if (typeDeclaration != null)
			return typeDeclaration.deepCopy();
		return null;
	}

	
	public List<IASTNode> getChildren() {
		if (typeDeclaration != null)
			return typeDeclaration.getChildren();
		return null;
	}

	
	public String getDisplayName() {
		if (typeDeclaration != null)
			return typeDeclaration.getDisplayName();
		return "Object";
	}

	
	public String getId() {
		if (typeDeclaration != null)
			return typeDeclaration.getId();
		++objectCounter;
		return "Object" + objectCounter;
	}

	
	public int getLength() {
		if (typeDeclaration != null)
			return typeDeclaration.getLength();
		return 6;
	}

	
	public Property getLocationInParent() {
		if (typeDeclaration != null)
			return typeDeclaration.getLocationInParent();
		return null;
	}

	
	public IASTNode getParent() {
		if (typeDeclaration != null)
			return typeDeclaration.getParent();
		return null;
	}

	
	public List<Property> getProperties() {
		if (typeDeclaration != null)
			return typeDeclaration.getProperties();
		return null;
	}

	
	public Property getProperty(String name) {
		if (typeDeclaration != null)
			return typeDeclaration.getProperty(name);
		return null;
	}

	
	public ISourceFile getRoot() {
		if (typeDeclaration != null)
			return typeDeclaration.getRoot();
		return null;
	}

	
	public int getStartPosition() {
		if (typeDeclaration != null)
			return typeDeclaration.getStartPosition();
		return 0;
	}

	
	public boolean isOptional() {
		if (typeDeclaration != null)
			return typeDeclaration.isOptional();
		return false;
	}

	
	public void notifyPropertyChanged(Property property) {
		if (typeDeclaration != null)
			typeDeclaration.notifyPropertyChanged(property);
	}

	
	public void remove() {
		if (typeDeclaration != null)
			typeDeclaration.remove();
	}

	
	public String render() {
		if (typeDeclaration != null)
			return typeDeclaration.render();
		return null;
	}

	
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

	
	public void replaceSubtreeWith(IASTNode newNode) {
		if (typeDeclaration != null)
			typeDeclaration.replaceSubtreeWith(newNode);
	}

	
	public void setId(String id) {
		if (typeDeclaration != null)
			typeDeclaration.setId(id);
	}

	
	public IASTNode getWrappee() {
		if (typeDeclaration != null)
			return typeDeclaration.getWrappee();
		return null;
	}

	
	public boolean isWrapper() {
		if (typeDeclaration != null)
			return typeDeclaration.isWrapper();
		return false;
	}
}
