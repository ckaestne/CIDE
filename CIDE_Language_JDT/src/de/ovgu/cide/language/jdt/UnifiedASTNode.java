package de.ovgu.cide.language.jdt;

import java.util.Arrays;
import java.util.List;

import cide.gast.ASTNode;
import cide.gast.IASTNode;
import cide.gast.IToken;
import cide.gast.Property;

public class UnifiedASTNode extends ASTNode {
	public enum Kind {
		TYPE, METHOD, FIELD, STATEMENT, OTHER
	}

	private final String displayName;
	private String id;
	private IASTNode[] wrappees;
	private Kind kind;

	protected UnifiedASTNode(String displayName, String id,
			List<Property> properties, IToken firstToken, IToken lastToken,
			IASTNode[] wrappees, Kind kind) {
		super(properties, firstToken, lastToken);
		this.displayName = displayName;
		this.id = id;
		this.wrappees = wrappees;
		this.kind = kind;
	}

	@Override
	public ASTNode deepCopy() {
		return new UnifiedASTNode(displayName, id, Arrays
				.asList(cloneProperties()), firstToken, lastToken, wrappees,
				kind);
	}

	@Override
	public String render() {
		// SimplePrintVisitor v = new SimplePrintVisitor();
		// accept(v);
		// return v.getResult();
		return "[" + displayName + "] - Preview not supported.";
	}

	@Override
	public String getDisplayName() {
		return displayName;
	}

	public String getEclipseASTNodeClass() {
		return displayName;
	}

	@Override
	public String getId() {
		return id;
	}

	/**
	 * kind is only used for bridged JDT nodes to determine some properties that
	 * would otherwise be checked by dynamic instanceof checks
	 * 
	 * @return
	 */
	public Kind getKind() {
		return kind;
	}

	@Override
	public boolean isOptional() {
		if (getParent() == null)
			return true;
		return super.isOptional();
	}

	/**
	 * different mechanism to detect wrappers without generating PropertyWrapper
	 * properties. we look up for each node whether we know this node as a
	 * wrapper
	 */
	@Override
	public IASTNode getWrappee() {
		if (wrappees != null && wrappees.length > 0)
			return wrappees[0];
		return null;
	}

}
