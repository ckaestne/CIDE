package de.ovgu.cide.language.jdt;

import java.util.Arrays;
import java.util.List;

import cide.gast.ASTNode;
import cide.gast.IToken;
import cide.gast.Property;

public class UnifiedASTNode extends ASTNode {

	private final String displayName;
	private String id;

	protected UnifiedASTNode(String displayName, String id,
			List<Property> properties, IToken firstToken, IToken lastToken) {
		super(properties, firstToken, lastToken);
		this.displayName = displayName;
		this.id = id;
	}

	@Override
	public ASTNode deepCopy() {
		return new UnifiedASTNode(displayName, id, Arrays
				.asList(cloneProperties()), firstToken, lastToken);
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
	
	@Override
	public boolean isOptional() {
		if (getParent()==null)
			return true;
		return super.isOptional();
	}

}
