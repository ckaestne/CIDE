package de.ovgu.cide.editor;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.eclipse.jface.text.source.Annotation;

import de.ovgu.cide.editor.ColoredEditorExtensions.IProjectionColoredEditor;
import de.ovgu.cide.editor.inlineprojection.ColoredInlineProjectionAnnotation;
import de.ovgu.cide.editor.inlineprojection.InlineProjectionAnnotationModel;
import de.ovgu.cide.editor.inlineprojection.InlineProjectionSourceViewer;
import de.ovgu.cide.features.IFeature;
import de.ovgu.cide.features.IFeatureModel;
import de.ovgu.cide.features.source.ColoredSourceFile;

@SuppressWarnings("restriction")
public class ProjectionColorManager implements IProjectionColorManager {

	private final IProjectionColoredEditor editor;

	ProjectionColorManager(IProjectionColoredEditor editor) {
		this.editor = editor;
	}

	private final Set<IFeature> collapsedColors = new HashSet<IFeature>();

	/* (non-Javadoc)
	 * @see coloredide.editor.IProjectionColorManager#getExpandedColors()
	 */
	public Set<IFeature> getExpandedColors() {
		ColoredSourceFile sourceFile = editor.getSourceFile();
		IFeatureModel fm = sourceFile.getFeatureModel();
		Set<IFeature> visibleFeatures = new HashSet<IFeature>(fm
				.getVisibleFeatures());
		visibleFeatures.removeAll(collapsedColors);

		return visibleFeatures;
	}

	/* (non-Javadoc)
	 * @see coloredide.editor.IProjectionColorManager#expandColor(coloredide.features.IFeature)
	 */
	public void expandColor(IFeature color) {
		collapsedColors.remove(color);
		updateProjectionAnnotations();
	}

	/* (non-Javadoc)
	 * @see coloredide.editor.IProjectionColorManager#collapseColor(coloredide.features.IFeature)
	 */
	public void collapseColor(IFeature color) {
		collapsedColors.add(color);
		updateProjectionAnnotations();
	}

	/* (non-Javadoc)
	 * @see coloredide.editor.IProjectionColorManager#expandAllColors()
	 */
	public void expandAllColors() {
		collapsedColors.clear();
		updateProjectionAnnotations();
	}
	/* (non-Javadoc)
	 * @see coloredide.editor.IProjectionColorManager#collapseAllColors()
	 */
	public void collapseAllColors() {
		collapsedColors.addAll(getExpandedColors());
		updateProjectionAnnotations();
	}

	protected void updateProjectionAnnotations() {
		InlineProjectionSourceViewer viewer = (InlineProjectionSourceViewer) editor
				.getSourceViewerR();
		InlineProjectionAnnotationModel annotationModel = viewer
				.getInlineProjectionAnnotationModel();
		Set<IFeature> visibleColors = getExpandedColors();

		List<Annotation> changedAnnotations = new ArrayList<Annotation>();
		for (Iterator iter = annotationModel.getAnnotationIterator(); iter
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
