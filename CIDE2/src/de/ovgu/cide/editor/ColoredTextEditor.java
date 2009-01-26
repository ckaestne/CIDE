package de.ovgu.cide.editor;

import org.eclipse.core.resources.IContainer;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.jface.action.IMenuManager;
import org.eclipse.jface.text.IDocument;
import org.eclipse.jface.text.ITextViewerExtension2;
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
import de.ovgu.cide.ChangeType;
import de.ovgu.cide.ColorListChangedEvent;
import de.ovgu.cide.ColoredIDEImages;
import de.ovgu.cide.FileColorChangedEvent;
import de.ovgu.cide.IColorChangeListener;
import de.ovgu.cide.editor.ColoredEditorExtensions.IProjectionColoredEditor;
import de.ovgu.cide.editor.inlineprojection.InlineProjectionSourceViewer;
import de.ovgu.cide.editor.inlineprojection.InlineProjectionSupport;
import de.ovgu.cide.features.FeatureModelManager;
import de.ovgu.cide.features.FeatureModelNotFoundException;
import de.ovgu.cide.features.IFeatureModel;
import de.ovgu.cide.features.source.ColoredSourceFile;
import de.ovgu.cide.utils.EditorUtility;

public class ColoredTextEditor extends AbstractDecoratedTextEditor implements
		IColorChangeListener, IProjectionColoredEditor {

	private final ColoredEditorExtensions editorExtension;
	private ProjectionColorManager projectionColorManager;

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
	protected ISourceViewer createSourceViewer(Composite parent,
			IVerticalRuler ruler, int styles) {
		InlineProjectionSourceViewer viewer = new InlineProjectionSourceViewer(
				parent, ruler, getOverviewRuler(), isOverviewRulerVisible(),
				styles);

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

		// error panel
		editorExtension.createErrorPanel(parent);
		editorExtension.alignErrorPanel(parent);

		CIDECorePlugin.getDefault().addColorChangeListener(this);

		// color caches
		editorExtension.initKeepColorManager();
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
}
