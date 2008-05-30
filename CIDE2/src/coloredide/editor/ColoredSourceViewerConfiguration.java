package coloredide.editor;

import org.eclipse.jface.text.IDocument;
import org.eclipse.jface.text.ITextHover;
import org.eclipse.jface.text.presentation.IPresentationReconciler;
import org.eclipse.jface.text.presentation.IPresentationRepairer;
import org.eclipse.jface.text.presentation.PresentationReconciler;
import org.eclipse.jface.text.source.ISourceViewer;
import org.eclipse.jface.text.source.SourceViewerConfiguration;

import coloredide.features.source.ColoredSourceFile;

public class ColoredSourceViewerConfiguration extends SourceViewerConfiguration {
	private IPresentationRepairer repairer;
	private ColorDamager damager;
	private ColoredTextHover textHover=null;

	public ColoredSourceViewerConfiguration(ColoredSourceFile sourceFile,
			ColoredTextEditor editor) {
		repairer = new ColorRepairer(sourceFile, editor);
		damager = new ColorDamager(sourceFile);
		if (sourceFile.isColored())
			textHover = new ColoredTextHover(sourceFile);
	}

	public IPresentationReconciler getPresentationReconciler(
			ISourceViewer sourceViewer) {
		PresentationReconciler reconciler = new PresentationReconciler();
		reconciler
				.setDocumentPartitioning(getConfiguredDocumentPartitioning(sourceViewer));
		reconciler.setDamager(damager, IDocument.DEFAULT_CONTENT_TYPE);
		reconciler.setRepairer(repairer, IDocument.DEFAULT_CONTENT_TYPE);

		return reconciler;
	}

	@Override
	public ITextHover getTextHover(ISourceViewer sourceViewer,
			String contentType) {
		return textHover;
	}
}
