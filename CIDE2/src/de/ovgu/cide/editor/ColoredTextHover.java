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

import cide.gast.ASTVisitor;
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

	private final ColoredSourceFile sourceFile;
	// private final IProject project;
	private SourceFileColorManager colorManager;

	public ColoredTextHover(ColoredSourceFile sourceFile) {
		this.sourceFile = sourceFile;
		this.colorManager = sourceFile.getColorManager();
	}

	public String getHoverInfo(ITextViewer textViewer, IRegion hoverRegion) {
		if (hoverRegion == null)
			return null;

		// find colors
		List<IASTNode> nodes = findSelectedNodes(hoverRegion);

		if ((nodes == null || nodes.isEmpty()) && hoverRegion.getLength() == 0)
			return null;
		if (nodes == null || nodes.isEmpty())
			return "Selected code fragment cannot be colored";

		String tooltip = "";

		tooltip += tooltipNodes(nodes);
		tooltip += tooltipColors(nodes);

		return tooltip.trim();
	}

	private String tooltipNodes(List<IASTNode> nodes) {
		assert nodes.size() > 0;
		String result = "";
		for (int nodeidx = 0; nodeidx < nodes.size() && nodeidx < 5; nodeidx++) {
			String aststr = getASTStringOutput(nodes.get(nodeidx));
			result += "\"" + aststr + "\"\n";
		}
		if (nodes.size() > 5)
			result += "...\n";

		return result + "\n";
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
		tooltip += "\n";
		Set<IFeature> directColors = getDirectColors(nodes);
		if (directColors.size() > 0)
			tooltip += printColorsShort("Direct Colors", directColors);
		else
			tooltip += "No direct colors.\n";

		Set<IFeature> inheritedColors = getInheritedColors(nodes);
		if (inheritedColors.size() > 0)
			tooltip += printColorsShort("Inherited Colors", inheritedColors);

		Set<IFeature> fileColors = getFileColors();
		if (fileColors.size() > 0)
			tooltip += printColorsShort("File Colors", fileColors);
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
		if (hoverRegion.getLength() == 0) {
			SingleNodeFinder snf = new SingleNodeFinder(hoverRegion.getOffset());
			ast.accept(snf);
			IASTNode node = snf.result;
			while (node != null && !node.isOptional())
				node = node.getParent();
			if (node != null)
				result.add(node);
		} else
			ast.accept(new SelectionFinder(result, hoverRegion, true));
		return result;
	}

	private static class SingleNodeFinder extends ASTVisitor {
		private int offset;

		public SingleNodeFinder(int offset) {
			this.offset = offset;
		}

		IASTNode result = null;

		@Override
		public boolean visit(IASTNode node) {
			if (node.getStartPosition() <= offset
					&& (node.getStartPosition() + node.getLength()) > offset) {
				if (result == null || node.getLength() < result.getLength())
					result = node;
				return true;
			}
			return false;
		}
	}

	private String printColors(String title, Collection<IFeature> colors) {
		String result = title + ":\n";
		List<IFeature> sortedColors = ColorHelper.sortFeatures(colors);
		for (IFeature color : sortedColors) {
			result += " - " + color.getName() + "\n";
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
		return result + "\n";
	}

	public IRegion getHoverRegion(ITextViewer textViewer, int offset) {
		Point selection = textViewer.getSelectedRange();
		if (selection.x <= offset && offset < selection.x + selection.y)
			return new Region(selection.x, selection.y);
		return new Region(offset, 0);
	}

}
