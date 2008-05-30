package coloredide.astview;

import java.util.List;

import cide.gast.ASTNode;
import coloredide.ASTColorChangedEvent;
import coloredide.CIDECorePlugin;
import coloredide.features.Feature;
import coloredide.features.source.ColoredSourceFile;
import coloredide.features.source.SourceFileColorManager;
import coloredide.utils.FeatureAction;

public class ToggleASTFeatureAction extends FeatureAction {

	private List<ASTNode> nodes;

	private ColoredSourceFile file;

	public ToggleASTFeatureAction(Feature feature, List<ASTNode> nodes,
			ColoredSourceFile file) {
		super(feature);
		assert nodes != null && !nodes.isEmpty();
		this.nodes = nodes;
		this.file = file;
		this.setText(feature.getName(file.getProject()));
		this.setChecked(haveColor(nodes, feature));
	}

	boolean haveColor(List<ASTNode> nodes, Feature feature) {
		return file.getColorManager().hasColor(nodes.get(0), feature);
	}

	public void run() {
		super.run();
		this.setChecked(!this.isChecked());
		SourceFileColorManager cm = file.getColorManager();
		cm.beginBatch();
		for (ASTNode node : nodes)
			if (this.isChecked()) {
				cm.removeColor(node, feature);
			} else {
				cm.addColor(node, feature);
			}
		cm.endBatch();
		CIDECorePlugin.getDefault().notifyListeners(
				new ASTColorChangedEvent(this, nodes, file));
	}

}
