package de.ovgu.cide.export.physical.internal;

import java.util.HashSet;
import java.util.Set;

import org.eclipse.jdt.core.dom.ASTNode;
import org.eclipse.jdt.core.dom.ASTVisitor;

import de.ovgu.cide.features.IFeature;
import de.ovgu.cide.features.source.ColoredSourceFile;
import de.ovgu.cide.language.jdt.AstidWrapper;

public class FeatureFinderVisitor extends ASTVisitor {
	private ColoredSourceFile sourceFile;

	public final Set<Set<IFeature>> seenColors = new HashSet<Set<IFeature>>();

	public FeatureFinderVisitor(ColoredSourceFile sourceFile) {
		this.sourceFile = sourceFile;
	}

	@Override
	public void preVisit(ASTNode node) {
		Set<IFeature> colors = sourceFile.getColorManager().getColors(
				new AstidWrapper(node));
		this.seenColors.add(colors);
		super.preVisit(node);
	}

}
