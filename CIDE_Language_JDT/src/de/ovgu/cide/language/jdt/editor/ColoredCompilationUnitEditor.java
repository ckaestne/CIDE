package de.ovgu.cide.language.jdt.editor;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.ListenerList;
import org.eclipse.jdt.core.ITypeRoot;
import org.eclipse.jdt.core.dom.CompilationUnit;
import org.eclipse.jdt.internal.ui.JavaPlugin;
import org.eclipse.jdt.internal.ui.javaeditor.CompilationUnitEditor;
import org.eclipse.jdt.internal.ui.javaeditor.JavaSourceViewer;
import org.eclipse.jdt.internal.ui.text.java.IJavaReconcilingListener;
import org.eclipse.jface.action.IMenuManager;
import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.jface.text.source.IOverviewRuler;
import org.eclipse.jface.text.source.ISourceViewer;
import org.eclipse.jface.text.source.IVerticalRuler;
import org.eclipse.jface.text.source.projection.ProjectionViewer;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.widgets.Composite;

import coloredide.ColoredIDEImages;
import coloredide.editor.ColoredEditorExtensions;
import coloredide.editor.ColoredEditorExtensions.IProjectionColoredEditor;
import coloredide.features.FeatureModelManager;
import coloredide.features.FeatureModelNotFoundException;
import coloredide.features.IFeatureModel;
import coloredide.features.source.ColoredSourceFile;
import de.ovgu.cide.configuration.jdt.JDTColorManagerBridge;
import de.ovgu.cide.language.jdt.editor.inlineprojection.InlineProjectionJavaViewer;
import de.ovgu.cide.language.jdt.editor.inlineprojection.InlineProjectionSupport;

@SuppressWarnings("restriction")
public class ColoredCompilationUnitEditor extends CompilationUnitEditor
		implements IProjectionColoredEditor {

	public static final String EDITOR_ID = "de.ovgu.cide.ColoredCompilationUnitEditor";
	
	
	private final ColoredEditorExtensions editorExtension;

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

	private void installCodeColoring() {
		if (fCodeColorManager == null) {

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
//		ColoredSourceFile sourceFile = getSourceFile();
//		ToggleTextColorContext context = new ToggleTextColorContext(sourceFile,
//				this.getSelectionProvider().getSelection());
//		try {
//			List<IFeature> visibleFeatures = new ArrayList<IFeature>(sourceFile
//					.getFeatureModel().getVisibleFeatures());
//			Collections.sort(visibleFeatures);
//			for (IFeature feature : visibleFeatures) {
//				menu.add(new ToggleTextColorAction(context, feature));
//			}
//			menu.add(new ToggleAllFeatureSubmenu(context, sourceFile
//					.getFeatureModel().getFeatures()));
//		} catch (JavaModelException e) {
//			e.printStackTrace();
//		}
//		menu.add(new ColorRefAction(sourceFile.getResource()));
//
//		menu.add(new ColorProjectionSubmenu(this, context));

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

	protected ISourceViewer createJavaSourceViewer(Composite parent,
			IVerticalRuler verticalRuler, IOverviewRuler overviewRuler,
			boolean isOverviewRulerVisible, int styles, IPreferenceStore store) {
		InlineProjectionJavaViewer viewer = new InlineProjectionJavaViewer(
				parent, verticalRuler, getOverviewRuler(),
				isOverviewRulerVisible(), styles, store);

		return viewer;
	}

	@Override
	public void createPartControl(Composite parent) {
		super.createPartControl(parent);

		installCodeColoring();
		if (isMarkingOccurrences())
			uninstallOccurrencesFinder();

		InlineProjectionJavaViewer viewer = (InlineProjectionJavaViewer) getViewer();

		InlineProjectionSupport projectionSupport = new InlineProjectionSupport(
				viewer, getAnnotationAccess(), getSharedColors());
		projectionSupport.install();
		viewer.doOperation(ProjectionViewer.TOGGLE);

		viewer.disableProjection();
		viewer.enableInlineProjection();

		editorExtension.createErrorPanel(parent);
		editorExtension.alignErrorPanel(parent);
		editorExtension.initKeepColorManager();

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
	}}
