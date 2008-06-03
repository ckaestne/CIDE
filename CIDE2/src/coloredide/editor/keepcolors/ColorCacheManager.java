package coloredide.editor.keepcolors;

import java.util.Set;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.jface.text.DocumentEvent;
import org.eclipse.jface.text.IDocumentListener;

import cide.gast.ASTNode;
import cide.gast.ASTVisitor;
import cide.gast.ISourceFile;
import cide.gparser.ParseException;
import coloredide.editor.ColoredTextEditor;
import coloredide.features.IFeature;
import coloredide.features.source.ColoredSourceFile;
import coloredide.features.source.SourceFileColorManager;

/**
 * controls the color-cache (to keep colors during editing) and connects it to
 * the editor.
 * 
 * @author ckaestne
 * 
 */
public class ColorCacheManager implements IDocumentListener {

	private final ColoredTextEditor editor;
	private ColorCache cache = null;

	public ColorCacheManager(ColoredTextEditor coloredTextEditor) {
		editor = coloredTextEditor;
		editor.getSourceViewerI().getDocument().addDocumentListener(this);
	}

	void cacheAST() {
		ColoredSourceFile source = editor.getSourceFile();
		try {
			cacheAST(source.getAST(), source.getColorManager());
		} catch (CoreException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ParseException e) {
			// nothing happens. caching just does not work if file cannot be
			// parsed
		}
	}

	private void cacheAST(ISourceFile ast,
			final SourceFileColorManager colorManager) {
		cache = new ColorCache();
		ast.accept(new ASTVisitor() {
			public void postVisit(ASTNode node) {
				Set<IFeature> colors = colorManager.getOwnColors(node);
				cache.addItemOL(node.getClass().getName(), colors, node
						.getStartPosition(), node.getLength());
			}
		});
	}

	public void reassignColors() {
		// nothing cached?
		if (cache == null)
			return;

		ColoredSourceFile source = editor.getSourceFile();
		final SourceFileColorManager colorManager = source.getColorManager();
		colorManager.beginBatch();
		try {
			source.getAST().accept(new ASTVisitor() {
				@Override
				public void postVisit(ASTNode node) {
					Set<IFeature> cachedColors = cache.findItemColors(node
							.getClass().getName(), node.getStartPosition(),
							node.getLength());
					if (cachedColors != null) {
						Set<IFeature> actualColors = colorManager
								.getOwnColors(node);
						if (!cachedColors.equals(actualColors))
							colorManager.setColors(node, cachedColors);
					}else
					colorManager.clearColor(node);
				}
			});
			cache = null;
		} catch (CoreException e) {
			e.printStackTrace();
		} catch (ParseException e) {
			// nothing happens. caching just does not work if file cannot be
			// parsed
		}
		colorManager.endBatch();
	}

	public void documentAboutToBeChanged(DocumentEvent event) {
		if (cache == null) {
			System.out.println("caching AST");
			cacheAST();
		}
	}

	public void documentChanged(DocumentEvent event) {
		cache.modifiedText(event.fText, event.fOffset, event.fLength);
	}

}
