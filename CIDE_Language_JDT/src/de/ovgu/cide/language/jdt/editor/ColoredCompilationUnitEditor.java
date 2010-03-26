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

import org.eclipse.core.resources.IFile;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.ListenerList;
import org.eclipse.jdt.core.ITypeRoot;
import org.eclipse.jdt.core.dom.CompilationUnit;
import org.eclipse.jdt.internal.ui.JavaPlugin;
import org.eclipse.jdt.internal.ui.javaeditor.CompilationUnitEditor;
import org.eclipse.jdt.internal.ui.javaeditor.JavaSourceViewer;
import org.eclipse.jdt.internal.ui.text.java.IJavaReconcilingListener;
import org.eclipse.jface.action.IMenuManager;
import org.eclipse.jface.text.IDocument;
import org.eclipse.jface.text.source.AnnotationRulerColumn;
import org.eclipse.jface.text.source.CompositeRuler;
import org.eclipse.jface.text.source.IAnnotationAccess;
import org.eclipse.jface.text.source.IAnnotationModel;
import org.eclipse.jface.text.source.ISourceViewer;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.IEditorInput;

import de.ovgu.cide.ColoredIDEImages;
import de.ovgu.cide.alternativefeatures.AlternativeAnnotation;
import de.ovgu.cide.configuration.jdt.JDTColorManagerBridge;
import de.ovgu.cide.editor.AnnotationMarkerAccess;
import de.ovgu.cide.editor.ColoredEditorExtensions;
import de.ovgu.cide.editor.ColoredEditorExtensions.IColoredEditor;
import de.ovgu.cide.editor.ColoredEditorExtensions.IProjectionColoredEditor;
import de.ovgu.cide.features.FeatureModelManager;
import de.ovgu.cide.features.FeatureModelNotFoundException;
import de.ovgu.cide.features.IFeatureModel;
import de.ovgu.cide.features.source.ColoredSourceFile;

@SuppressWarnings("restriction")
public class ColoredCompilationUnitEditor extends CompilationUnitEditor
		implements IColoredEditor {

	public static final String EDITOR_ID = "de.ovgu.cide.ColoredCompilationUnitEditor";

	private final ColoredEditorExtensions editorExtension;
	private IAnnotationAccess annotationAccess;

	public ColoredCompilationUnitEditor() {
		editorExtension = new ColoredEditorExtensions(this);
	}

	@Override
	protected void installOccurrencesFinder(boolean forceUpdate) {
		if (isMarkingOccurrences())
			uninstallOccurrencesFinder();
	}

	private ColoredHighlightingManager fCodeColorManager;

	public ColoredSourceFile getSourceFile() {
		ITypeRoot java = getInputJavaElement();
		assert java != null;
		assert java.getResource() instanceof IFile;
		IFile file = (IFile) java.getResource();
		if (file != null) {
			IFeatureModel featureModel;
			try {
				featureModel = FeatureModelManager.getInstance()
						.getFeatureModel(file.getProject());
			} catch (FeatureModelNotFoundException e) {
				e.printStackTrace();
				assert false : e;
				return null;
			}
			return ColoredSourceFile.getColoredSourceFile(file, featureModel);
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

	private void installCodeColoring() {
		if (fCodeColorManager == null && getSourceViewer() != null) {

			ColoredSourceFile source = getSourceFile();
			assert source.isColored();
			JDTColorManagerBridge colorManager = new JDTColorManagerBridge(
					source);

			fCodeColorManager = new ColoredHighlightingManager();
			fCodeColorManager.install(this,
					(JavaSourceViewer) getSourceViewer(), JavaPlugin
							.getDefault().getJavaTextTools().getColorManager(),
					getPreferenceStore(), colorManager);
		}
		if (fCodeColorManager != null)
			fCodeColorManager.fReconciler.scheduleJob();
	}

	/**
	 * Uninstall Semantic Highlighting.
	 * 
	 * @since 3.0
	 */
	void uninstallCodeColoring() {
		if (fCodeColorManager != null) {
			fCodeColorManager.uninstall();
			fCodeColorManager = null;
		}
	}

	public ITypeRoot getInputJavaElement() {
		return super.getInputJavaElement();
	}

	public void reconciled(CompilationUnit ast, boolean forced,
			IProgressMonitor progressMonitor) {
		super.reconciled(ast, forced, progressMonitor);
		Object[] listeners = fReconcilingListeners.getListeners();
		for (int i = 0, length = listeners.length; i < length; ++i)
			((IJavaReconcilingListener) listeners[i]).reconciled(ast, forced,
					progressMonitor);

	}

	private ListenerList fReconcilingListeners = new ListenerList(
			ListenerList.IDENTITY);

	private ProjectionColorManager projectionColorManager;

	final void addReconcileListener2(IJavaReconcilingListener listener) {
		synchronized (fReconcilingListeners) {
			fReconcilingListeners.add(listener);
		}
	}

	/**
	 * Removes the given listener. Has no effect if an identical listener was
	 * not already registered.
	 * 
	 * @param listener
	 *            the reconcile listener to be removed
	 * @since 3.0
	 */
	final void removeReconcileListener2(IJavaReconcilingListener listener) {
		synchronized (fReconcilingListeners) {
			fReconcilingListeners.remove(listener);
		}
	}

	@Override
	public void editorContextMenuAboutToShow(IMenuManager menu) {
		editorExtension.fillContextMenu(menu);
		// ColoredSourceFile sourceFile = getSourceFile();
		// ToggleTextColorContext context = new
		// ToggleTextColorContext(sourceFile,
		// this.getSelectionProvider().getSelection());
		// try {
		// List<IFeature> visibleFeatures = new ArrayList<IFeature>(sourceFile
		// .getFeatureModel().getVisibleFeatures());
		// Collections.sort(visibleFeatures);
		// for (IFeature feature : visibleFeatures) {
		// menu.add(new ToggleTextColorAction(context, feature));
		// }
		// menu.add(new ToggleAllFeatureSubmenu(context, sourceFile
		// .getFeatureModel().getFeatures()));
		// } catch (JavaModelException e) {
		// e.printStackTrace();
		// }
		// menu.add(new ColorRefAction(sourceFile.getResource()));
		//
		// menu.add(new ColorProjectionSubmenu(this, context));

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

	// TODO: disabled inline projection
	// protected JavaSourceViewer createJavaSourceViewer(Composite parent,
	// IVerticalRuler verticalRuler, IOverviewRuler overviewRuler,
	// boolean isOverviewRulerVisible, int styles, IPreferenceStore store) {
	// InlineProjectionJavaViewer viewer = new InlineProjectionJavaViewer(
	// parent, verticalRuler, getOverviewRuler(),
	// isOverviewRulerVisible(), styles, store);
	//
	// return viewer;
	// }

	@Override
	public void createPartControl(Composite parent) {
		super.createPartControl(parent);

		installCodeColoring();
		if (isMarkingOccurrences())
			uninstallOccurrencesFinder();

		// InlineProjectionJavaViewer viewer = (InlineProjectionJavaViewer)
		// getViewer();
		//
		// InlineProjectionSupport projectionSupport = new
		// InlineProjectionSupport(
		// viewer, getAnnotationAccess(), getSharedColors());
		// projectionSupport.install();TODO: disabled inline projection
		// viewer.doOperation(ProjectionViewer.TOGGLE);
		//
		// viewer.disableProjection();
		// viewer.enableInlineProjection();

		editorExtension.createErrorPanel(parent);
		editorExtension.alignErrorPanel(parent);
		editorExtension.initKeepColorManager();
		// editorExtension.installAlternativeAnnotations();
	}

	public IDocument getDocument() {
		return getSourceViewer().getDocument();
	}

	public ProjectionColorManager getProjectionColorManager() {
		if (projectionColorManager == null)
			projectionColorManager = new ProjectionColorManager(this);
		return projectionColorManager;
	}

	public IFeatureModel getFeatureModel() {
		return getSourceFile().getFeatureModel();
	}

	public ISourceViewer getSourceViewerR() {
		return super.getSourceViewer();
	}

	@Override
	public void doSave(IProgressMonitor progressMonitor) {
		boolean wasDirty = isDirty();
		super.doSave(progressMonitor);

		editorExtension.afterSave(wasDirty);
	}

	@Override
	protected void doSetInput(IEditorInput input) throws CoreException {
		super.doSetInput(input);
		installCodeColoring();
	}

	/**
	 * projections/views deactivated for now
	 */
	public boolean supportsProjection() {
		return false;
	}
}
