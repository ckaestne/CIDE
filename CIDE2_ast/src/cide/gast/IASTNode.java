package cide.gast;

import java.util.List;

public interface IASTNode {

	/**
	 * @return parent node of the ast
	 */
	public IASTNode getParent();

	/**
	 * returns the property inside the parent node that holds this node
	 */
	public Property getLocationInParent();

	void setParentProperty(Property parentProperty);

	/**
	 * returns whether this node is optional and can be removed without
	 * invalidating the entire AST
	 */
	public boolean isOptional();

	/**
	 * removes this node from the AST. works only for optional nodes
	 */
	public void remove();

	/**
	 * unique and stable ID of the AST node. often used to persistently
	 * associate annotations with AST nodes
	 */
	public String getId();

	public void setId(String id);

	/**
	 * offset of the node inside the source code file. useful to map user
	 * selections to nodes
	 */
	public int getStartPosition();

	/**
	 * length of the node inside the source code file
	 */
	public int getLength();

	/**
	 * returns all properties of this node. can be used to navigate to all child
	 * nodes. developers would typically rather use a visitor pattern
	 */
	public List<Property> getProperties();

	/**
	 * retrieves a property that group and hold child elements
	 */
	public Property getProperty(String name);

	/** helper function to return all children (of all properties) */
	public List<IASTNode> getChildren();

	/**
	 * visitor pattern
	 */
	public void accept(IASTVisitor visitor);

	/**
	 * @return root of the ast
	 */
	public ISourceFile getRoot();

	/**
	 * creates a copy of this node and all childnodes
	 */
	public IASTNode deepCopy();

	/**
	 * returns a debugging name for this node, do not use for end-user output
	 */
	public String getDisplayName();

	/**
	 * returns true if the element is a wrapper and getWrappee returns the
	 * wrapped element. wrappers are used only in some languages and can be
	 * annotated independently of their children
	 * 
	 * see TOOLS'09 paper
	 * http://wwwiti.cs.uni-magdeburg.de/~ckaestne/tools09.pdf
	 * 
	 * @return
	 */
	public boolean isWrapper();

	/**
	 * returns the wrapped object. must be not null if isWrapper==true and null
	 * otherwise
	 * 
	 * @return wrapped object or null if isWrapper==false
	 */
	public IASTNode getWrappee();

	public void notifyPropertyChanged(Property property);

	/**
	 * ATTENTION: Never call from outside this package! Never call from a
	 * Property!
	 * 
	 * @param parentNode
	 * @param parentProperty
	 */
	void setParent(IASTNode parentNode, Property parentProperty);

	/**
	 * preview of what the source code does look like when printed. when
	 * providing an own configuration mechanism, this must not necessarily match
	 * the output of the variant generation process
	 */
	public String render();

	/**
	 * Ersetzt diesen Knoten durch den gegebenen Knoten.
	 * 
	 * ACHTUNG: Die Änderungen von Offsets, die durch ein Austauschen eines
	 * Knotens passieren können, werden NICHT durchgeführt, so dass der AST
	 * unbrauchbar werden könnte. Zur Zeit wird diese Methode nur auf einer
	 * DeepCopy des AST ausgeführt, die dann gerendered wird.
	 * 
	 * @param newNode
	 */
	public void replaceSubtreeWith(IASTNode newNode);

}
