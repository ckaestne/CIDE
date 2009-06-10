package coloredide.editor;

import java.util.List;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.ListenerList;
import org.eclipse.jdt.core.ICompilationUnit;
import org.eclipse.jdt.core.IJavaElement;
import org.eclipse.jdt.core.ITypeRoot;
import org.eclipse.jdt.core.JavaModelException;
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
import coloredide.editor.inlineprojection.InlineProjectionJavaViewer;
import coloredide.editor.inlineprojection.InlineProjectionSupport;
import coloredide.features.Feature;
import coloredide.features.FeatureManager;
import coloredide.features.source.ColoredJavaSourceFile;
import coloredide.features.source.IColoredJavaSourceFile;
import coloredide.tools.colorref.ColorRefAction;

@SuppressWarnings("restriction")
public class ColoredCompilationUnitEditor extends CompilationUnitEditor {

	@Override
	protected void installOccurrencesFinder(boolean forceUpdate) {
		if (isMarkingOccurrences())
			uninstallOccurrencesFinder();
	}

	private ColoredHighlightingManager fCodeColorManager;

	private void installCodeColoring() {
		if (fCodeColorManager == null) {
			IColoredJavaSourceFile sourceFile = ColoredJavaSourceFile
					.getColoredJavaSourceFile((ICompilationUnit) this
							.getInputJavaElement());

			fCodeColorManager = new ColoredHighlightingManager();
			fCodeColorManager.install(this,
					(JavaSourceViewer) getSourceViewer(), JavaPlugin
							.getDefault().getJavaTextTools().getColorManager(),
					getPreferenceStore(), sourceFile.getColorManager());
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
		IColoredJavaSourceFile sourceFile = ColoredJavaSourceFile
				.getColoredJavaSourceFile((ICompilationUnit) this
						.getInputJavaElement());
		ToggleTextColorContext context = new ToggleTextColorContext(sourceFile,
				this.getSelectionProvider().getSelection());
		try {
			List<Feature> visibleFeatures = FeatureManager
					.getVisibleFeatures(this.getInputJavaElement()
							.getCorrespondingResource().getProject());
			for (Feature feature : visibleFeatures) {
				menu.add(new ToggleTextColorAction(context, feature));
			}
			menu.add(new ToggleAllFeatureSubmenu(context, FeatureManager
					.getFeatures()));
		} catch (JavaModelException e) {
			e.printStackTrace();
		}
		menu.add(new ColorRefAction(sourceFile.getCompilationUnit()
				.getResource()));
		
		menu.add(new ColorProjectionSubmenu(this,context));
		
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

	protected JavaSourceViewer createJavaSourceViewer(Composite parent,
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

	}

	public ProjectionColorManager getProjectionColorManager() {
		if (projectionColorManager == null)
			projectionColorManager = new ProjectionColorManager(this);
		return projectionColorManager;
	}

}
