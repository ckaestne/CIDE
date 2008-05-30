package coloredide;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.EventObject;

import org.eclipse.core.resources.IResource;

import cide.gast.ASTNode;
import coloredide.features.source.ColoredSourceFile;

public class ASTColorChangedEvent extends EventObject {

	private static final long serialVersionUID = 1L;

	private final Collection<ASTNode> nodes;

	private final ColoredSourceFile sourceFile;

	public ASTColorChangedEvent(Object source, ASTNode node, ColoredSourceFile sourceFile) {
		super(source);
		this.nodes = Collections.singleton(node);
		this.sourceFile=sourceFile;
	}

	public ASTColorChangedEvent(Object source, Collection<ASTNode> nodes, ColoredSourceFile sourceFile) {
		super(source);
		assert nodes!=null && !nodes.isEmpty();
		this.nodes = nodes;
		this.sourceFile=sourceFile;
	}

	public Collection<ASTNode> getAffectedNodes() {
		return nodes;
	}
	
	public ColoredSourceFile getColoredSourceFile(){
		return sourceFile;
	}

}
