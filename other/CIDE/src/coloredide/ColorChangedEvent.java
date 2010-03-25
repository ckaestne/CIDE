package coloredide;

import java.util.ArrayList;
import java.util.Collection;
import java.util.EventObject;

import org.eclipse.jdt.core.dom.ASTNode;

import coloredide.features.source.IColoredJavaSourceFile;

public class ColorChangedEvent extends EventObject {

	private static final long serialVersionUID = 1L;

	private final Collection<ASTNode> nodes;

	private final IColoredJavaSourceFile sourceFile;

	public ColorChangedEvent(Object source, ASTNode node, IColoredJavaSourceFile sourceFile) {
		super(source);
		ArrayList<ASTNode> nodes=new ArrayList<ASTNode>();
		nodes.add(node);
		this.nodes = nodes;
		this.sourceFile=sourceFile;
	}

	public ColorChangedEvent(Object source, Collection<ASTNode> nodes, IColoredJavaSourceFile sourceFile) {
		super(source);
		assert nodes!=null && !nodes.isEmpty();
		this.nodes = nodes;
		this.sourceFile=sourceFile;
	}

	public Collection<ASTNode> getAffectedNodes() {
		return nodes;
	}
	
	public IColoredJavaSourceFile getColoredJavaSourceFile(){
		return sourceFile;
	}

}
