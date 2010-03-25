package coloredide.editor;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.core.runtime.jobs.Job;
import org.eclipse.jdt.core.ICompilationUnit;
import org.eclipse.jdt.core.IJavaElement;
import org.eclipse.jdt.core.dom.ASTNode;
import org.eclipse.jdt.core.dom.CompilationUnit;
import org.eclipse.jdt.internal.ui.JavaPlugin;
import org.eclipse.jdt.internal.ui.text.java.IJavaReconcilingListener;
import org.eclipse.jdt.ui.SharedASTProvider;
import org.eclipse.jface.text.IDocument;
import org.eclipse.jface.text.ITextInputListener;
import org.eclipse.jface.text.Position;
import org.eclipse.jface.text.TextPresentation;
import org.eclipse.jface.text.source.Annotation;
import org.eclipse.jface.text.source.ISourceViewer;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.IWorkbenchPartSite;

import coloredide.configuration.CodeSegment;
import coloredide.configuration.CodeSegmentCalculator;
import coloredide.editor.inlineprojection.ColoredInlineProjectionAnnotation;
import coloredide.editor.inlineprojection.InlineProjectionAnnotationModel;
import coloredide.editor.inlineprojection.InlineProjectionJavaViewer;
import coloredide.features.source.IColorManager;

@SuppressWarnings("restriction")
public class ColoredHighlightingReconciler implements IJavaReconcilingListener,
		ITextInputListener {

	/** The Java editor this semantic highlighting reconciler is installed on */
	private ColoredCompilationUnitEditor fEditor;

	/** The source viewer this semantic highlighting reconciler is installed on */
	private ISourceViewer fSourceViewer;

	/** The semantic highlighting presenter */
	private ColoredHighlightingPresenter fPresenter;

	/** Semantic highlightings */
	// private SemanticHighlighting[] fSemanticHighlightings;
	/** Highlightings */
	// private Highlighting[] fHighlightings;
	/** Background job's added highlighted positions */
	private List fAddedPositions = new ArrayList();

	/** Background job's removed highlighted positions */
	private List fRemovedPositions = new ArrayList();

	/** Number of removed positions */
//	private int fNOfRemovedPositions;

	/** Background job */
	private Job fJob;

	/** Background job lock */
	private final Object fJobLock = new Object();

	/**
	 * Reconcile operation lock.
	 * 
	 * @since 3.2
	 */
	private final Object fReconcileLock = new Object();

	/**
	 * <code>true</code> if any thread is executing <code>reconcile</code>,
	 * <code>false</code> otherwise.
	 * 
	 * @since 3.2
	 */
	private boolean fIsReconciling = false;

	/**
	 * The semantic highlighting presenter - cache for background thread, only
	 * valid during
	 * {@link #reconciled(CompilationUnit, boolean, IProgressMonitor)}
	 */
	private ColoredHighlightingPresenter fJobPresenter;

	private IColorManager colorManager;

	/**
	 * Semantic highlightings - cache for background thread, only valid during
	 * {@link #reconciled(CompilationUnit, boolean, IProgressMonitor)}
	 */
	// private SemanticHighlighting[] fJobSemanticHighlightings;
	/**
	 * Highlightings - cache for background thread, only valid during
	 * {@link #reconciled(CompilationUnit, boolean, IProgressMonitor)}
	 */
	// private Highlighting[] fJobHighlightings;
	/*
	 * @see org.eclipse.jdt.internal.ui.text.java.IJavaReconcilingListener#aboutToBeReconciled()
	 */
	public void aboutToBeReconciled() {
		// Do nothing
	}

	/*
	 * @see org.eclipse.jdt.internal.ui.text.java.IJavaReconcilingListener#reconciled(CompilationUnit,
	 *      boolean, IProgressMonitor)
	 */
	public void reconciled(CompilationUnit ast, boolean forced,
			IProgressMonitor progressMonitor) {
		// ensure at most one thread can be reconciling at any time
		synchronized (fReconcileLock) {
			if (fIsReconciling)
				return;
			else
				fIsReconciling = true;
		}
		fJobPresenter = fPresenter;
		// fJobSemanticHighlightings= fSemanticHighlightings;
		// fJobHighlightings= fHighlightings;

		try {
			if (fJobPresenter == null)// || fJobSemanticHighlightings == null
				// || fJobHighlightings == null)
				return;

			fJobPresenter.setCanceled(progressMonitor.isCanceled());

			if (ast == null || fJobPresenter.isCanceled())
				return;

			ASTNode[] subtrees = getAffectedSubtrees(ast);
			if (subtrees.length == 0)
				return;

			startReconcilingPositions();

			if (!fJobPresenter.isCanceled())
				reconcilePositions(subtrees);

			TextPresentation textPresentation = null;
			if (!fJobPresenter.isCanceled())
				textPresentation = fJobPresenter.createPresentation(
						fAddedPositions, fRemovedPositions);

			if (!fJobPresenter.isCanceled())
				updatePresentation(textPresentation, fAddedPositions,
						fRemovedPositions);

			stopReconcilingPositions();
		} finally {
			fJobPresenter = null;
			// fJobSemanticHighlightings= null;
			// fJobHighlightings= null;
			synchronized (fReconcileLock) {
				fIsReconciling = false;
			}
		}
	}

	/**
	 * @param node
	 *            Root node
	 * @return Array of subtrees that may be affected by past document changes
	 */
	private ASTNode[] getAffectedSubtrees(ASTNode node) {
		// TODO: only return nodes which are affected by document changes -
		// would require an 'anchor' concept for taking distant effects into
		// account
		return new ASTNode[] { node };
	}

	/**
	 * Start reconciling positions.
	 */
	private void startReconcilingPositions() {
		fJobPresenter.addAllPositions(fRemovedPositions);
//		fNOfRemovedPositions = fRemovedPositions.size();
	}

	/**
	 * Reconcile positions based on the AST subtrees
	 * 
	 * @param subtrees
	 *            the AST subtrees
	 */
	private void reconcilePositions(ASTNode[] subtrees) {
		List<CodeSegment> list = new ArrayList<CodeSegment>();
		for (int i = 0, n = subtrees.length; i < n; i++)
			list.addAll(CodeSegmentCalculator.getCodeSegments(subtrees[i],
					colorManager));
		// subtrees[i].accept(fCollector);
		deleteEmptySegments(list);
		fAddedPositions.addAll(list);
		List oldPositions = fRemovedPositions;
		List newPositions = list;
		for (int i = 0, n = oldPositions.size(); i < n; i++) {
			Object current = oldPositions.get(i);
			if (current != null)
				newPositions.add(current);
		}
		fRemovedPositions = newPositions;
	}

	private void deleteEmptySegments(List<CodeSegment> list) {
		for (Iterator i = list.iterator(); i.hasNext();) {
			CodeSegment segment = (CodeSegment) i.next();
			if (segment.getColors().isEmpty())
				i.remove();
		}
	}

	// private void addPosition(int offset, int length, Highlighting
	// highlighting) {
	// boolean isExisting= false;
	// // TODO: use binary search
	// for (int i= 0, n= fRemovedPositions.size(); i < n; i++) {
	// HighlightedPosition position= (HighlightedPosition)
	// fRemovedPositions.get(i);
	// if (position == null)
	// continue;
	// if (position.isEqual(offset, length, highlighting)) {
	// isExisting= true;
	// fRemovedPositions.set(i, null);
	// fNOfRemovedPositions--;
	// break;
	// }
	// }
	//
	// if (!isExisting) {
	// Position position= fJobPresenter.createHighlightedPosition(offset,
	// length, highlighting);
	// fAddedPositions.add(position);
	// }
	// }

	private List<ColoredInlineProjectionAnnotation> oldAnnotations=new ArrayList<ColoredInlineProjectionAnnotation>();

	/**
	 * Update the presentation.
	 * 
	 * @param textPresentation
	 *            the text presentation
	 * @param addedPositions
	 *            the added positions
	 * @param removedPositions
	 *            the removed positions
	 */
	private void updatePresentation(TextPresentation textPresentation,
			List<CodeSegment> addedPositions, List<CodeSegment> removedPositions) {
//		System.out.println("Reconciled: " + textPresentation + " "
//				+ addedPositions + " " + removedPositions);
		Runnable runnable = fJobPresenter.createUpdateRunnable(
				textPresentation, addedPositions, removedPositions);
		if (runnable == null)
			return;

		ColoredCompilationUnitEditor editor = fEditor;
		if (editor == null)
			return;

		
		updateInlineProjectionAnnotations(addedPositions, editor);

		IWorkbenchPartSite site = editor.getSite();
		if (site == null)
			return;

		Shell shell = site.getShell();
		if (shell == null || shell.isDisposed())
			return;

		Display display = shell.getDisplay();
		if (display == null || display.isDisposed())
			return;

		display.asyncExec(runnable);
	}

	protected void updateInlineProjectionAnnotations(List<CodeSegment> addedPositions, ColoredCompilationUnitEditor editor) {
		InlineProjectionAnnotationModel annotationModel = ((InlineProjectionJavaViewer) editor
				.getViewer()).getInlineProjectionAnnotationModel();

		ColoredInlineProjectionAnnotation[] annotations = new ColoredInlineProjectionAnnotation[addedPositions
				.size()];

		// this will hold the new annotations along
		// with their corresponding positions
		HashMap newAnnotations = new HashMap();
		addedPositions = new ArrayList<CodeSegment>(addedPositions);
		ArrayList<ColoredInlineProjectionAnnotation> knownPositions = new ArrayList<ColoredInlineProjectionAnnotation>(
				oldAnnotations);
		ArrayList<ColoredInlineProjectionAnnotation> savedPositions = new ArrayList<ColoredInlineProjectionAnnotation>();

		// move unchanged segments from known to saved (those are not deleted)
		for (Iterator<CodeSegment> i = addedPositions.iterator(); i.hasNext();) {
			CodeSegment seg = i.next();
			for (Iterator<ColoredInlineProjectionAnnotation> a = knownPositions
					.iterator(); a.hasNext();) {
				ColoredInlineProjectionAnnotation known = a.next();
				if (seg.offset == known.getPosition().getOffset()
						&& seg.length == known.getPosition().getLength()
						&& seg.getColors().equals(known.getColors())) {
					i.remove();
					a.remove();
					savedPositions.add(known);
				}
			}

		}

		for (int i = 0; i < addedPositions.size(); i++) {
			ColoredInlineProjectionAnnotation annotation = new ColoredInlineProjectionAnnotation();
			annotation.setColors(addedPositions.get(i).getColors());
			Position pos = new Position(addedPositions.get(i).offset,
					addedPositions.get(i).length);
			annotation.setPosition(pos);
			annotation.adjustCollapsing(editor.getProjectionColorManager().getExpandedColors());
			newAnnotations.put(annotation, pos);

			annotations[i] = annotation;
		}

		if (annotationModel != null) {
			Annotation[] deletedAnnotations = new Annotation[knownPositions
					.size()];
			deletedAnnotations = knownPositions.toArray(deletedAnnotations);
			annotationModel.modifyAnnotations(deletedAnnotations,
					newAnnotations, null);
		}
		oldAnnotations = savedPositions;
		oldAnnotations.addAll(newAnnotations.keySet());
	}

	/**
	 * Stop reconciling positions.
	 */
	private void stopReconcilingPositions() {
		fRemovedPositions.clear();
//		fNOfRemovedPositions = 0;
		fAddedPositions.clear();
	}

	/**
	 * Install this reconciler on the given editor, presenter and highlightings.
	 * 
	 * @param editor
	 *            the editor
	 * @param sourceViewer
	 *            the source viewer
	 * @param presenter
	 *            the semantic highlighting presenter
	 * @param semanticHighlightings
	 *            the semantic highlightings
	 * @param highlightings
	 *            the highlightings
	 */
	public void install(ColoredCompilationUnitEditor editor,
			ISourceViewer sourceViewer, ColoredHighlightingPresenter presenter) {
		fPresenter = presenter;
		// fSemanticHighlightings= semanticHighlightings;
		// fHighlightings= highlightings;

		fEditor = editor;
		fSourceViewer = sourceViewer;

		if (fEditor instanceof ColoredCompilationUnitEditor) {
			((ColoredCompilationUnitEditor) fEditor)
					.addReconcileListener2(this);
		} else {
			fSourceViewer.addTextInputListener(this);
			scheduleJob();
		}
	}

	/**
	 * Uninstall this reconciler from the editor
	 */
	public void uninstall() {
		if (fPresenter != null)
			fPresenter.setCanceled(true);

		if (fEditor != null) {
			if (fEditor instanceof ColoredCompilationUnitEditor)

				((ColoredCompilationUnitEditor) fEditor)
						.removeReconcileListener2(this);
			else
				fSourceViewer.removeTextInputListener(this);
			fEditor = null;
		}

		fSourceViewer = null;
		// fSemanticHighlightings = null;
		// fHighlightings = null;
		fPresenter = null;
	}

	/**
	 * Schedule a background job for retrieving the AST and reconciling the
	 * Semantic Highlighting model.
	 */
	private void scheduleJob() {
		final IJavaElement element = fEditor.getInputJavaElement();
		assert element instanceof ICompilationUnit;

		synchronized (fJobLock) {
			final Job oldJob = fJob;
			if (fJob != null) {
				fJob.cancel();
				fJob = null;
			}

			if (element != null) {
				fJob = new Job("CodeColoring") {
					protected IStatus run(IProgressMonitor monitor) {
						if (oldJob != null) {
							try {
								oldJob.join();
							} catch (InterruptedException e) {
								JavaPlugin.log(e);
								return Status.CANCEL_STATUS;
							}
						}
						if (monitor.isCanceled())
							return Status.CANCEL_STATUS;
						CompilationUnit ast = JavaPlugin.getDefault()
								.getASTProvider().getAST((ICompilationUnit)element,
										SharedASTProvider.WAIT_YES, monitor);
						reconciled(ast, false, monitor);
						synchronized (fJobLock) {
							// allow the job to be gc'ed
							if (fJob == this)
								fJob = null;
						}
						return Status.OK_STATUS;
					}
				};
				fJob.setSystem(true);
				fJob.setPriority(Job.DECORATE);
				fJob.schedule();
			}
		}
	}

	/*
	 * @see org.eclipse.jface.text.ITextInputListener#inputDocumentAboutToBeChanged(org.eclipse.jface.text.IDocument,
	 *      org.eclipse.jface.text.IDocument)
	 */
	public void inputDocumentAboutToBeChanged(IDocument oldInput,
			IDocument newInput) {
		synchronized (fJobLock) {
			if (fJob != null) {
				fJob.cancel();
				fJob = null;
			}
		}
	}

	/*
	 * @see org.eclipse.jface.text.ITextInputListener#inputDocumentChanged(org.eclipse.jface.text.IDocument,
	 *      org.eclipse.jface.text.IDocument)
	 */
	public void inputDocumentChanged(IDocument oldInput, IDocument newInput) {
		if (newInput != null)
			scheduleJob();
	}

	/**
	 * Refreshes the highlighting.
	 * 
	 * @since 3.2
	 */
	public void refresh() {
		scheduleJob();
	}

	public void setColorManager(IColorManager compUnitColorManager) {
		this.colorManager = compUnitColorManager;
	}
}
