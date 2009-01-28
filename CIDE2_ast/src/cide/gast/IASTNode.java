package cide.gast;

import java.util.List;

public interface IASTNode {

	public void accept(IASTVisitor visitor);

	public Property getProperty(String name);

	public ISourceFile getRoot();

	public IASTNode getParent();

	public String getId();

	public int getStartPosition();

	public int getLength();

	public IASTNode deepCopy();

	public String getDisplayName();

	public boolean isOptional();

	public Property getLocationInParent();

	public void notifyPropertyChanged(Property property);

	void setParent(IASTNode parentNode, Property parentProperty);

	public List<Property> getProperties();

	public String render();

	public void remove();
	
	public List<IASTNode> getChildren();
}
