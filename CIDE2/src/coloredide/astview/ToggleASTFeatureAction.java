package coloredide.astview;

import java.util.List;

import cide.gast.IASTNode;
import coloredide.ASTColorChangedEvent;
import coloredide.CIDECorePlugin;
import coloredide.features.IFeature;
import coloredide.features.source.ColoredSourceFile;
import coloredide.features.source.SourceFileColorManager;
import coloredide.utils.FeatureAction;

public class ToggleASTFeatureAction extends FeatureAction {

	private List<IASTNode> nodes;

	private ColoredSourceFile file;

	public ToggleASTFeatureAction(IFeature feature, List<IASTNode> nodes,
			ColoredSourceFile file) {
		super(feature);
		assert nodes != null && !nodes.isEmpty();
		this.nodes = nodes;
		this.file = file;
		this.setText("Feature: "+feature.getName());
		this.setChecked(haveColor(nodes, feature));
	}

	boolean haveColor(List<IASTNode> nodes, IFeature feature) {
		return file.getColorManager().hasColor(nodes.get(0), feature);
	}

	public void run() {
		super.run();
		this.setChecked(!this.isChecked());
		SourceFileColorManager cm = file.getColorManager();
		cm.beginBatch();
		for (IASTNode node : nodes)
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
