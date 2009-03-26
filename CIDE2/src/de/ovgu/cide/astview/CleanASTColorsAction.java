package de.ovgu.cide.astview;

import java.util.List;

import org.eclipse.jface.action.Action;

import cide.gast.ASTVisitor;
import cide.gast.IASTNode;
import cide.gast.IASTVisitor;
import de.ovgu.cide.ASTColorChangedEvent;
import de.ovgu.cide.CIDECorePlugin;
import de.ovgu.cide.features.IFeature;
import de.ovgu.cide.features.source.ColoredSourceFile;
import de.ovgu.cide.features.source.SourceFileColorManager;

/**
 * cleans all colors from the selected (sub-)tree(s)
 * 
 * @author cKaestner
 * 
 */
public class CleanASTColorsAction extends Action {

	private List<IASTNode> nodes;

	private ColoredSourceFile file;

	public CleanASTColorsAction(List<IASTNode> nodes, ColoredSourceFile file) {
		super();
		assert nodes != null;
		this.nodes = nodes;
		this.setEnabled(!nodes.isEmpty());
		this.file = file;
		this.setText("Clean colors");
	}

	boolean haveColor(List<IASTNode> nodes, IFeature feature) {
		return file.getColorManager().hasColor(nodes.get(0), feature);
	}

	public void run() {
		super.run();
		this.setChecked(!this.isChecked());
		SourceFileColorManager colorManager = file.getColorManager();
		colorManager.beginBatch();
		IASTVisitor colorRemover = new ColorRemover(colorManager);
		for (IASTNode node : nodes)
			node.accept(colorRemover);
		colorManager.endBatch();
		CIDECorePlugin.getDefault().notifyListeners(
				new ASTColorChangedEvent(this, nodes, file));
	}

	public static final class ColorRemover extends ASTVisitor {
		private SourceFileColorManager colorManager;

		public ColorRemover(SourceFileColorManager colorManager) {
			this.colorManager = colorManager;
		}

		public boolean visit(IASTNode node) {
			colorManager.clearColor(node);
			return super.visit(node);
		}

	}
}
