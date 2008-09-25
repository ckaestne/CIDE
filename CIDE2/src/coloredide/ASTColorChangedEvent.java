package coloredide;

import java.util.Collection;
import java.util.Collections;
import java.util.EventObject;

import cide.gast.IASTNode;
import coloredide.features.source.ColoredSourceFile;

public class ASTColorChangedEvent extends EventObject {

	private static final long serialVersionUID = 1L;

	private final Collection<IASTNode> nodes;

	private final ColoredSourceFile sourceFile;

	public ASTColorChangedEvent(Object source, IASTNode node, ColoredSourceFile sourceFile) {
		super(source);
		this.nodes = Collections.singleton(node);
		this.sourceFile=sourceFile;
	}

	public ASTColorChangedEvent(Object source, Collection<IASTNode> nodes, ColoredSourceFile sourceFile) {
		super(source);
		assert nodes!=null && !nodes.isEmpty();
		this.nodes = nodes;
		this.sourceFile=sourceFile;
	}

	public Collection<IASTNode> getAffectedNodes() {
		return nodes;
	}
	
	public ColoredSourceFile getColoredSourceFile(){
		return sourceFile;
	}

}
