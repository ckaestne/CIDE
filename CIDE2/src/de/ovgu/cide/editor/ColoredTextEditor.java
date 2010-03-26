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

package de.ovgu.cide.editor;

import org.eclipse.core.resources.IContainer;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.action.IMenuManager;
import org.eclipse.jface.text.IDocument;
import org.eclipse.jface.text.ITextViewerExtension2;
import org.eclipse.jface.text.source.AnnotationRulerColumn;
import org.eclipse.jface.text.source.CompositeRuler;
import org.eclipse.jface.text.source.IAnnotationAccess;
import org.eclipse.jface.text.source.IAnnotationModel;
import org.eclipse.jface.text.source.ISourceViewer;
import org.eclipse.jface.text.source.IVerticalRuler;
import org.eclipse.jface.text.source.SourceViewer;
import org.eclipse.jface.text.source.projection.ProjectionViewer;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.ui.texteditor.AbstractDecoratedTextEditor;

import cide.gast.IASTNode;
import de.ovgu.cide.ASTColorChangedEvent;
import de.ovgu.cide.CIDECorePlugin;
import de.ovgu.cide.Change;
import de.ovgu.cide.ChangeType;
import de.ovgu.cide.ColorListChangedEvent;
import de.ovgu.cide.ColoredIDEImages;
import de.ovgu.cide.FileColorChangedEvent;
import de.ovgu.cide.IColorChangeListener;
import de.ovgu.cide.alternativefeatures.AlternativeAnnotation;
import de.ovgu.cide.editor.ColoredEditorExtensions.IProjectionColoredEditor;
import de.ovgu.cide.editor.inlineprojection.InlineProjectionSourceViewer;
import de.ovgu.cide.editor.inlineprojection.InlineProjectionSupport;
import de.ovgu.cide.editor.inlineprojection.ProjectionColorManager;
import de.ovgu.cide.features.FeatureModelManager;
import de.ovgu.cide.features.FeatureModelNotFoundException;
import de.ovgu.cide.features.IFeatureModel;
import de.ovgu.cide.features.source.ColoredSourceFile;
import de.ovgu.cide.utils.EditorUtility;

public class ColoredTextEditor extends AbstractDecoratedTextEditor implements
		IColorChangeListener, IProjectionColoredEditor {

	private final ColoredEditorExtensions editorExtension;
	private ProjectionColorManager projectionColorManager;
	private IAnnotationAccess annotationAccess;
	private InlineProjectionSourceViewer viewer;

	public ColoredTextEditor() {
		super();
		editorExtension = new ColoredEditorExtensions(this);
	}

	public static final String EDITOR_ID = "de.ovgu.cide.ColoredTextEditor";

	// private Composite errorPanel;

	public ColoredSourceFile getSourceFile() {
		IFile file = EditorUtility.getFileInput(this);
		if (file != null) {
			IFeatureModel featureModel;
			try {
				featureModel = FeatureModelManager.getInstance()
						.getFeatureModel(getProject());
			} catch (FeatureModelNotFoundException e) {
				editorExtension.markNoFeatureModel();
				return null;
			}
			return ColoredSourceFile.getColoredSourceFile(file, featureModel);
		}
		return null;
	}

	public IFeatureModel getFeatureModel() {
		IProject project = getProject();
		if (project != null) {
			IFeatureModel featureModel;
			try {
				featureModel = FeatureModelManager.getInstance()
						.getFeatureModel(project);
			} catch (FeatureModelNotFoundException e) {
				e.printStackTrace();
				return null;
			}
			return featureModel;
		}
		return null;
	}

	@Override
	protected CompositeRuler createCompositeRuler() {
		annotationAccess = new AnnotationMarkerAccess();
		IAnnotationModel annotationModel = getDocumentProvider()
				.getAnnotationModel(getEditorInput());
		AnnotationRulerColumn annotationRulerCol = new AnnotationRulerColumn(
				annotationModel, 16, annotationAccess);
		annotationRulerCol
				.addAnnotationType(AlternativeAnnotation.ALTERNATIVE_TYPE);

		CompositeRuler compositeRuler = new CompositeRuler();
		compositeRuler.setModel(annotationModel);
		compositeRuler.addDecorator(0, annotationRulerCol);

		return compositeRuler;
	}

	@Override
	protected ISourceViewer createSourceViewer(Composite parent,
			IVerticalRuler ruler, int styles) {

		viewer = new InlineProjectionSourceViewer(parent, ruler,
				getOverviewRuler(), isOverviewRulerVisible(), styles, this);

		// Könnte man einkommentieren, um blaue squigglys unter die
		// Codefragmente zu malen, zu denen es Alternativen gibt.
		// Mir gefällts nicht.
		// AnnotationPainter ap = new AnnotationPainter(viewer,
		// annotationAccess);
		// ap.addAnnotationType(AlternativeAnnotation.ALTERNATIVE_TYPE);
		// ap.setAnnotationTypeColor(AlternativeAnnotation.ALTERNATIVE_TYPE, new
		// Color(Display.getDefault(), new RGB(0, 0, 255)));
		// viewer.addPainter(ap);

		return viewer;
	}

	protected IProject getProject() {
		IFile file = EditorUtility.getFileInput(this);
		return file.getProject();
	}

	@Override
	public void editorContextMenuAboutToShow(IMenuManager menu) {
		editorExtension.fillContextMenu(menu);
		super.editorContextMenuAboutToShow(menu);
	}

	@Override
	public Image getTitleImage() {
		Image t = ColoredIDEImages.getImage(ColoredIDEImages.COLOREDJ);
		if (t != null)
			return t;
		else
			return super.getTitleImage();
	}

	@Override
	public void dispose() {
		CIDECorePlugin.getDefault().removeColorChangeListener(this);

		super.dispose();
	}

	@Override
	public void createPartControl(Composite parent) {

		setSourceViewerConfiguration(new ColoredSourceViewerConfiguration(
				getSourceFile(), this));

		super.createPartControl(parent);

		// projection
		InlineProjectionSupport projectionSupport = new InlineProjectionSupport(
				getSourceViewerI(), getAnnotationAccess(), getSharedColors());
		projectionSupport.install();
		getSourceViewerI().doOperation(ProjectionViewer.TOGGLE);
		getSourceViewerI().disableProjection();
		getSourceViewerI().enableInlineProjection();

		getSourceViewerI().getProjectionAnnotationCalculator().calculateProjectionAnnotations();
		
		// errorpanel
		editorExtension.createErrorPanel(parent);
		editorExtension.alignErrorPanel(parent);

		CIDECorePlugin.getDefault().addColorChangeListener(this);

		// color caches
		editorExtension.initKeepColorManager();

		editorExtension.installAlternativeAnnotations();
	}

	public SourceViewer getSourceViewerR() {
		return (InlineProjectionSourceViewer) getSourceViewer();
	}

	public InlineProjectionSourceViewer getSourceViewerI() {
		return (InlineProjectionSourceViewer) getSourceViewer();
	}

	public IDocument getDocument() {
		return getSourceViewer().getDocument();
	}

	public void astColorChanged(ASTColorChangedEvent event) {
		IDocument doc = getSourceViewer().getDocument();

		if (event.getColoredSourceFile() != getSourceFile())
			return;

		if (!(getSourceViewer() instanceof ITextViewerExtension2))
			getSourceViewer().invalidateTextPresentation();
		else {
			int offset = doc.getLength();
			int endOffset = 0;
			for (IASTNode node : event.getAffectedNodes()) {
				offset = Math.min(offset, node.getStartPosition());
				endOffset = Math.max(endOffset, node.getStartPosition()
						+ node.getLength());
			}
			if (offset < endOffset) {
				int length = endOffset - offset;
				((ITextViewerExtension2) getSourceViewer())
						.invalidateTextPresentation(offset, length);
			}
		}
	}

	@Override
	public void doSave(IProgressMonitor progressMonitor) {
		boolean wasDirty = isDirty();
		super.doSave(progressMonitor);

		editorExtension.afterSave(wasDirty);
		if (wasDirty)
			getSourceViewerI().getProjectionAnnotationCalculator()
					.calculateProjectionAnnotations();
	}

	public ProjectionColorManager getProjectionColorManager() {
		if (projectionColorManager == null)
			projectionColorManager = new ProjectionColorManager(this);
		return projectionColorManager;
	}

	public void fileColorChanged(FileColorChangedEvent event) {
		// update entire file of color of this file has changed
		IContainer folder = getSourceFile().getResource().getParent();
		while (folder != null) {
			if (event.getAffectedFolders().contains(folder)) {
				asyncInvalidateTextRepresentation();
				break;
			}
			folder = folder.getParent();
		}
	}

	private void asyncInvalidateTextRepresentation() {
		Display.getDefault().asyncExec(new Runnable() {
			public void run() {
				getSourceViewer().invalidateTextPresentation();
			}
		});
	}

	public void colorListChanged(ColorListChangedEvent event) {
		if (event.anyChangeOf(ChangeType.VISIBILITY)) {
			for (Change change : event.getChanges()) {
				if (change.type == ChangeType.VISIBILITY)
					getProjectionColorManager()
							.updateProjectionAnnotationVisibility();
			}
		}
		if (event.getNewProjectionKind() != null)
			getSourceViewerI().getProjectionAnnotationCalculator()
					.calculateProjectionAnnotations();

		// redraw on color or visibility changes in the feature model
		if (event.anyChangeOf(ChangeType.COLOR)
				|| event.anyChangeOf(ChangeType.VISIBILITY))
			asyncInvalidateTextRepresentation();
	}

	public void markNotColored() {
		editorExtension.markNotColored();
	}

	public void markFileEdited() {
		editorExtension.markFileEdited();
	}

	public void markASTOk(long l) {
		editorExtension.markASTOk(l);
	}

	public void markCoreException(CoreException e) {
		editorExtension.markCoreException(e);
	}

	public void markParseException(Throwable e) {
		editorExtension.markParseException(e);
	}

	public InlineProjectionSourceViewer getViewer() {
		return viewer;
	}

	public IAction getToggleProjectionAction() {
		return getProjectionColorManager().getAction();
	}
}
