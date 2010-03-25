package coloredide.export;

import java.util.HashSet;
import java.util.Set;

import org.eclipse.jdt.core.dom.ASTNode;
import org.eclipse.jdt.core.dom.ASTVisitor;

import coloredide.features.Feature;
import coloredide.features.source.IColoredJavaSourceFile;

public class FeatureFinderVisitor extends ASTVisitor {
	private IColoredJavaSourceFile sourceFile;

	public final Set<Set<Feature>> seenColors = new HashSet<Set<Feature>>();

	public FeatureFinderVisitor(IColoredJavaSourceFile sourceFile) {
		this.sourceFile = sourceFile;
	}

	@Override
	public void preVisit(ASTNode node) {
		super.preVisit(node);

		Set<Feature> colors = sourceFile.getColorManager().getColors(node);
		this.seenColors.add(colors);
	}
}
