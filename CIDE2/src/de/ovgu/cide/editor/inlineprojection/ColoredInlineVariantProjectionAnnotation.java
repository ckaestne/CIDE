package de.ovgu.cide.editor.inlineprojection;

import java.util.Set;

import org.eclipse.core.resources.IProject;
import org.eclipse.jface.text.Position;

import de.ovgu.cide.features.IFeature;

public class ColoredInlineVariantProjectionAnnotation extends
		AbstractColoredInlineProjectionAnnotation {

	public ColoredInlineVariantProjectionAnnotation(Set<IFeature> features,
			IProject project, Position pos, ProjectionColorManager projectionColorManager) {
		super(features, project, pos, projectionColorManager);
		
	}

	public boolean adjustCollapsing(Set<IFeature> selectedColors) {
		boolean expanded = selectedColors.containsAll(colors);
		return setExpanded(expanded);
	}
}
