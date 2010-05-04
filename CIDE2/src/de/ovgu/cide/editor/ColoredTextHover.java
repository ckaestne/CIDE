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

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.jface.text.IRegion;
import org.eclipse.jface.text.ITextHover;
import org.eclipse.jface.text.ITextViewer;
import org.eclipse.jface.text.Region;
import org.eclipse.swt.graphics.Point;

import cide.gast.IASTNode;
import cide.gast.ISourceFile;
import cide.gparser.ParseException;
import de.ovgu.cide.features.IFeature;
import de.ovgu.cide.features.source.ColoredSourceFile;
import de.ovgu.cide.features.source.DirectoryColorManager;
import de.ovgu.cide.features.source.SourceFileColorManager;
import de.ovgu.cide.utils.ColorHelper;

/**
 * this class shows a tooltip for a code fragment (either the node under the
 * mouse pointer or (if existent) what's common for all nodes in the selected
 * region)
 * 
 * it is registered by the ColoredSourceViewerConfiguration for all colored
 * editors
 * 
 * complexity is due to the fact that multiple nodes can be selected and only
 * the common colors are shown. similar mechanism as in SelectionActionsContext
 * 
 * 
 * @author ckaestne
 * 
 */
public class ColoredTextHover implements ITextHover {

	protected static final String NOT_COLORED = "Selected code fragment cannot be colored";
	protected String NL = "\n";
	
	protected ColoredSourceFile sourceFile;
	// private final IProject project;
	private SourceFileColorManager colorManager;

	public ColoredTextHover(ColoredSourceFile sourceFile) {
		setColoredSourceFile(sourceFile);
	}

	protected void setColoredSourceFile(ColoredSourceFile sourceFile) {
		this.sourceFile = sourceFile;
		if (sourceFile != null)
			this.colorManager = sourceFile.getColorManager();
		else
			this.colorManager = null;
	}

	public String getHoverInfo(ITextViewer textViewer, IRegion hoverRegion) {
		if (hoverRegion == null)
			return null;

		// find colors
		List<IASTNode> nodes = findSelectedNodes(hoverRegion);

		if ((nodes == null || nodes.isEmpty()) && hoverRegion.getLength() == 0)
			return null;
		if (nodes == null || nodes.isEmpty())
			return NOT_COLORED;

		String tooltip = "";

		tooltip += tooltipNodes(nodes);
		String ttc = tooltipColors(nodes);
		if (ttc == null)
			return null;
		tooltip += ttc;

		return tooltip.trim();
	}

	private String tooltipNodes(List<IASTNode> nodes) {
		assert nodes.size() > 0;
		String result = "";
		for (int nodeidx = 0; nodeidx < nodes.size() && nodeidx < 5; nodeidx++) {
			String aststr = getASTStringOutput(nodes.get(nodeidx));
			result += "\"" + aststr + "\""+NL;
		}
		if (nodes.size() > 5)
			result += "..."+NL;

		return result + NL;
	}

	private String getASTStringOutput(IASTNode node) {
		String aststr = node.render().trim();
		aststr = aststr.replace('\n', ' ').replace('\r', ' ')
				.replace('\t', ' ');
		if (aststr.length() > 60) {
			aststr = aststr.substring(0, 50) + " ... "
					+ aststr.substring(aststr.length() - 5);
		}
		return aststr;
	}

	private String tooltipColors(Collection<IASTNode> nodes) {
		assert nodes.size() > 0;
		String tooltip = "";
		// all colors
		Set<IFeature> allColors = getAllColors(nodes);
		tooltip += printColors(
				(nodes.size() > 1 ? "Common " : "") + "Features", allColors);

		// details (direct colors, inherited colors, file colors)
		tooltip += NL;
		Set<IFeature> directColors = getDirectColors(nodes);
		if (directColors.size() > 0)
			tooltip += printColorsShort("Direct Colors", directColors);
		else
			tooltip += "No direct colors."+NL;

		Set<IFeature> inheritedColors = getInheritedColors(nodes);
		if (inheritedColors.size() > 0)
			tooltip += printColorsShort("Inherited Colors", inheritedColors);

		Set<IFeature> fileColors = getFileColors();
		if (fileColors.size() > 0)
			tooltip += printColorsShort("File Colors", fileColors);

		// for no colors whatsoever return no tooltip
		if (directColors.size() == 0 && inheritedColors.size() == 0
				&& fileColors.size() == 0)
			return null;

		return tooltip;
	}

	private Set<IFeature> getFileColors() {
		DirectoryColorManager dirColorManager = DirectoryColorManager
				.getColoredDirectoryManagerS(sourceFile.getResource()
						.getParent(), sourceFile.getFeatureModel());
		return dirColorManager.getColors(sourceFile.getResource());
	}

	private Set<IFeature> getInheritedColors(Collection<IASTNode> nodes) {
		// all inherited colors, but not the file colors
		assert nodes.size() > 0;
		Set<IFeature> result = new HashSet<IFeature>();
		Iterator<IASTNode> i = nodes.iterator();
		result.addAll(colorManager.getInheritedColors(i.next()));
		while (i.hasNext()) {
			join(result, colorManager.getInheritedColors(i.next()));
		}
		result.removeAll(getFileColors());
		return result;
	}

	private Set<IFeature> getDirectColors(Collection<IASTNode> nodes) {
		assert nodes.size() > 0;
		Set<IFeature> result = new HashSet<IFeature>();
		Iterator<IASTNode> i = nodes.iterator();
		result.addAll(colorManager.getOwnColors(i.next()));
		while (i.hasNext()) {
			join(result, colorManager.getOwnColors(i.next()));
		}
		return result;
	}

	/**
	 * removes all entries from target that are not contained in newEntries
	 * 
	 * @param target
	 * @param newEntries
	 */
	private void join(Set<IFeature> target, Set<IFeature> newEntries) {
		for (Iterator<IFeature> i = target.iterator(); i.hasNext();) {
			if (!newEntries.contains(i.next()))
				i.remove();
		}
	}

	private Set<IFeature> getAllColors(Collection<IASTNode> nodes) {
		assert nodes.size() > 0;
		Set<IFeature> result = new HashSet<IFeature>();
		Iterator<IASTNode> i = nodes.iterator();
		result.addAll(colorManager.getColors(i.next()));
		while (i.hasNext()) {
			join(result, colorManager.getColors(i.next()));
		}
		return result;
	}

	private List<IASTNode> findSelectedNodes(IRegion hoverRegion) {
		ISourceFile ast;
		try {
			ast = sourceFile.getAST();
		} catch (CoreException e) {
			e.printStackTrace();
			return null;
		} catch (ParseException e) {
			e.printStackTrace();
			return null;
		}

		ArrayList<IASTNode> result = new ArrayList<IASTNode>();
		if (hoverRegion!=null && hoverRegion.getLength() == 0) {
			SingleNodeFinder snf = new SingleNodeFinder(hoverRegion.getOffset());
			ast.accept(snf);
			IASTNode node = snf.result;
			// Ist kein Text markiert, so suchen wir nach dem nächsten
			// optionalen Knoten. Sonst würde man z.B.
			// bei einem gefärbten "Object o1;" die Angabe "no direct colors"
			// bekommen, da der Cursor entweder
			// über dem Knoten "Object" oder "o1" steht.
			// Nachteil: Ist z.B. nur der Rückgabetyp einer Methode gefärbt, so
			// bekommt man die Angabe "no direct colors".
			// Den Aufwand, um dies zu vermeiden, ist es nicht Wert :-)
			while (node != null && !node.isOptional())
				node = node.getParent();
			if (node != null)
				result.add(node);
		} else
			ast.accept(new SelectionFinder(result, hoverRegion, false)); // Uns
		// interessieren
		// nun
		// auch
		// nicht-optionale
		// Knoten
		// (wg.
		// alternativen
		// Features)
		return result;
	}

	private String printColors(String title, Collection<IFeature> colors) {
		String result = title + ":"+NL;
		List<IFeature> sortedColors = ColorHelper.sortFeatures(colors);
		for (IFeature color : sortedColors) {
			result += " - " + color.getName() + NL;
		}
		return result;
	}

	private String printColorsShort(String title, Collection<IFeature> colors) {
		String result = title + ": ";
		List<IFeature> sortedColors = ColorHelper.sortFeatures(colors);
		boolean first = true;
		for (IFeature color : sortedColors) {
			if (first)
				first = false;
			else
				result += ", ";
			result += color.getName();
		}
		return result + NL;
	}

	public IRegion getHoverRegion(ITextViewer textViewer, int offset) {
		Point selection = textViewer.getSelectedRange();
		if (selection.x <= offset && offset < selection.x + selection.y)
			return new Region(selection.x, selection.y);
		return new Region(offset, 0);
	}
}
