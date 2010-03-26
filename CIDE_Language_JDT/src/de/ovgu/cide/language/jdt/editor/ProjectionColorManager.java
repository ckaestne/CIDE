/**
    Copyright 2010 Christian Kästner

    This file is part of CIDE.

    CIDE is free software: you can redistribute it and/or modify
    it under the terms of the GNU General Public License as published by
    the Free Software Foundation, version 3 of the License.

    CIDE is distributed in the hope that it will be useful,
    but WITHOUT ANY WARRANTY; without even the implied warranty of
    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
    GNU General Public License for more details.

    You should have received a copy of the GNU General Public License
    along with CIDE.  If not, see <http://www.gnu.org/licenses/>.

    See http://www.fosd.de/cide/ for further information.
*/

package de.ovgu.cide.language.jdt.editor;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.eclipse.jface.text.source.Annotation;

import de.ovgu.cide.editor.inlineprojection.IProjectionColorManager;
import de.ovgu.cide.features.IFeature;
import de.ovgu.cide.features.source.ColoredSourceFile;
import de.ovgu.cide.language.jdt.editor.inlineprojection.ColoredInlineProjectionAnnotation;
import de.ovgu.cide.language.jdt.editor.inlineprojection.InlineProjectionAnnotationModel;
import de.ovgu.cide.language.jdt.editor.inlineprojection.InlineProjectionJavaViewer;

@SuppressWarnings("restriction")
public class ProjectionColorManager implements IProjectionColorManager {

	private final ColoredCompilationUnitEditor editor;

	ProjectionColorManager(ColoredCompilationUnitEditor editor) {
		this.editor = editor;
	}

	private final Set<IFeature> collapsedColors = new HashSet<IFeature>();

	public Set<IFeature> getExpandedColors() {
		ColoredSourceFile source = editor.getSourceFile();
		Set<IFeature> visibleFeatures = new HashSet<IFeature>(source.getFeatureModel()
				.getVisibleFeatures());
		visibleFeatures.removeAll(collapsedColors);

		return visibleFeatures;
	}

	public void expandColor(IFeature color) {
		collapsedColors.remove(color);
		updateProjectionAnnotationVisibility();
	}

	public void collapseColor(IFeature color) {
		collapsedColors.add(color);
		updateProjectionAnnotationVisibility();
	}

	public void expandAllColors() {
		collapsedColors.clear();
		updateProjectionAnnotationVisibility();
	}
	public void collapseAllColors() {
		ColoredSourceFile source = editor.getSourceFile();
		
		collapsedColors.addAll(source.getFeatureModel()
				.getVisibleFeatures());
		updateProjectionAnnotationVisibility();
	}

	public void updateProjectionAnnotationVisibility() {
		InlineProjectionJavaViewer viewer = (InlineProjectionJavaViewer) editor
				.getViewer();
		InlineProjectionAnnotationModel annotationModel = viewer
				.getInlineProjectionAnnotationModel();
		Set<IFeature> visibleColors = getExpandedColors();

		List<Annotation> changedAnnotations = new ArrayList<Annotation>();
		for (Iterator<Annotation> iter = annotationModel.getAnnotationIterator(); iter
				.hasNext();) {
			ColoredInlineProjectionAnnotation annotation = (ColoredInlineProjectionAnnotation) iter
					.next();
			boolean changed = annotation.adjustCollapsing(visibleColors);
			if (changed)
				changedAnnotations.add(annotation);
		}

		annotationModel.modifyAnnotations(null, null, changedAnnotations
				.toArray(new Annotation[changedAnnotations.size()]));
	}
}
