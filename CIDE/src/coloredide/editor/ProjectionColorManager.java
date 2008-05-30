package coloredide.editor;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.eclipse.core.resources.IProject;
import org.eclipse.jdt.core.ICompilationUnit;
import org.eclipse.jface.text.source.Annotation;

import coloredide.editor.inlineprojection.ColoredInlineProjectionAnnotation;
import coloredide.editor.inlineprojection.InlineProjectionAnnotationModel;
import coloredide.editor.inlineprojection.InlineProjectionJavaViewer;
import coloredide.features.Feature;
import coloredide.features.FeatureManager;
import coloredide.features.source.ColoredJavaSourceFile;
import coloredide.features.source.IColoredJavaSourceFile;

@SuppressWarnings("restriction")
public class ProjectionColorManager {

	private final ColoredCompilationUnitEditor editor;

	ProjectionColorManager(ColoredCompilationUnitEditor editor) {
		this.editor = editor;
	}

	private final Set<Feature> collapsedColors = new HashSet<Feature>();
	private IProject project;

	public Set<Feature> getExpandedColors() {
		IColoredJavaSourceFile sourceFile = ColoredJavaSourceFile
				.getColoredJavaSourceFile((ICompilationUnit) editor
						.getInputJavaElement());
		this.project = sourceFile.getProject();
		Set<Feature> visibleFeatures = new HashSet<Feature>(FeatureManager
				.getVisibleFeatures(project));
		visibleFeatures.removeAll(collapsedColors);

		return visibleFeatures;
	}

	public void expandColor(Feature color) {
		collapsedColors.remove(color);
		updateProjectionAnnotations();
	}

	public void collapseColor(Feature color) {
		collapsedColors.add(color);
		updateProjectionAnnotations();
	}

	public void expandAllColors() {
		collapsedColors.clear();
		updateProjectionAnnotations();
	}
	public void collapseAllColors() {
		collapsedColors.addAll(FeatureManager
				.getVisibleFeatures(project));
		updateProjectionAnnotations();
	}

	protected void updateProjectionAnnotations() {
		InlineProjectionJavaViewer viewer = (InlineProjectionJavaViewer) editor
				.getViewer();
		InlineProjectionAnnotationModel annotationModel = viewer
				.getInlineProjectionAnnotationModel();
		Set<Feature> visibleColors = getExpandedColors();

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
