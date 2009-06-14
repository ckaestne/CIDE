package de.ovgu.cide.export;

import java.util.HashSet;
import java.util.Set;

import cide.gast.ASTVisitor;
import cide.gast.IASTNode;
import de.ovgu.cide.features.IFeature;
import de.ovgu.cide.features.source.ColoredSourceFile;

public class FeatureFinderVisitor extends ASTVisitor {
	private ColoredSourceFile sourceFile;

	public final Set<Set<IFeature>> seenColors = new HashSet<Set<IFeature>>();

	public FeatureFinderVisitor(ColoredSourceFile sourceFile) {
		this.sourceFile = sourceFile;
	}

	@Override
	public boolean visit(IASTNode node) {
		Set<IFeature> colors = sourceFile.getColorManager().getColors(node);
		this.seenColors.add(colors);
		return super.visit(node);
	}
}
