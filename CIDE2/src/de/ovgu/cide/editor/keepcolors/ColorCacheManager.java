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

package de.ovgu.cide.editor.keepcolors;

import java.util.Set;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.jface.text.DocumentEvent;
import org.eclipse.jface.text.IDocumentListener;

import cide.gast.ASTVisitor;
import cide.gast.IASTNode;
import cide.gast.ISourceFile;
import cide.gparser.ParseException;
import de.ovgu.cide.editor.ColoredEditorExtensions.IColoredEditor;
import de.ovgu.cide.features.IFeature;
import de.ovgu.cide.features.source.ColoredSourceFile;
import de.ovgu.cide.features.source.SourceFileColorManager;

/**
 * controls the color-cache (to keep colors during editing) and connects it to
 * the editor.
 * 
 * @author ckaestne
 * 
 */
public class ColorCacheManager implements IDocumentListener {

	private final IColoredEditor editor;
	private ColorCache cache = null;
	
	private boolean isActive;
	private boolean lastIsActive;

	public ColorCacheManager(IColoredEditor coloredTextEditor) {
		editor = coloredTextEditor;
		isActive = true;
		lastIsActive = true;
		editor.getSourceViewerR().getDocument().addDocumentListener(this);
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
			public void postVisit(IASTNode node) {
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
				public void postVisit(IASTNode node) {
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
	
	public void deactivate() {
		lastIsActive = isActive;
		isActive = false;
	}
	
	public void restoreActivation() {
		boolean tmp = isActive;
		isActive = lastIsActive;
		lastIsActive = tmp;
	}

	public void documentAboutToBeChanged(DocumentEvent event) {
		if (isActive && (cache == null)) {
			System.out.println("caching AST");
			cacheAST();
		}
	}

	public void documentChanged(DocumentEvent event) {
		if (isActive && (cache != null) && (event != null)) {
			cache.modifiedText(event.fText, event.fOffset, event.fLength);
		}
	}
}
