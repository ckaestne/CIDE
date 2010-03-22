package de.ovgu.cide.editor.inlineprojection;

import java.util.Set;

import org.eclipse.core.resources.IProject;
import org.eclipse.jface.text.Position;

import de.ovgu.cide.features.IFeature;

public class ColoredInlineFeatureProjectionAnnotation extends
		AbstractColoredInlineProjectionAnnotation {

	public ColoredInlineFeatureProjectionAnnotation(Set<IFeature> features,
			IProject project, Position pos, ProjectionColorManager projectionColorManager) {
		super(features, project, pos, projectionColorManager);

	}

	public boolean adjustCollapsing(Set<IFeature> selectedColors) {
		boolean expanded = false;
		for (IFeature color : colors)
			expanded |= selectedColors.contains(color);
		return setExpanded(expanded);

	}

}
