package coloredide.editor;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.eclipse.core.resources.IProject;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.jface.text.IRegion;
import org.eclipse.jface.text.ITextHover;
import org.eclipse.jface.text.ITextViewer;
import org.eclipse.jface.text.Region;
import org.eclipse.swt.graphics.Point;

import cide.gast.ASTNode;
import cide.gast.ASTVisitor;
import cide.gast.ISourceFile;
import cide.gparser.ParseException;
import coloredide.features.Feature;
import coloredide.features.source.ColoredSourceFile;
import coloredide.features.source.DirectoryColorManager;
import coloredide.features.source.SourceFileColorManager;

/**
 * this class shows a tooltip for a code fragment (either the node under the
 * mouse pointer or (if existent) what's common for all nodes in the selected
 * region)
 * 
 * it is registered by the ColoredSourceViewerConfiguration for all colored
 * editors
 * 
 * complexity is due to the fact that multiple nodes can be selected and only
 * the common colors are shown. similar mechanism as in ToggleTextColorContext
 * 
 * 
 * @author ckaestne
 * 
 */
public class ColoredTextHover implements ITextHover {

	private final ColoredSourceFile sourceFile;
	private final IProject project;
	private SourceFileColorManager colorManager;

	public ColoredTextHover(ColoredSourceFile sourceFile) {
		this.sourceFile = sourceFile;
		this.colorManager = sourceFile.getColorManager();
		this.project = sourceFile.getProject();
	}

	public String getHoverInfo(ITextViewer textViewer, IRegion hoverRegion) {
		if (hoverRegion == null)
			return null;

		// find colors
		List<ASTNode> nodes = findSelectedNodes(hoverRegion);

		if ((nodes == null || nodes.isEmpty()) && hoverRegion.getLength() == 0)
			return null;
		if (nodes == null || nodes.isEmpty())
			return "Selected code fragment cannot be colored";

		String tooltip = "";

		tooltip += tooltipNodes(nodes);
		tooltip += tooltipColors(nodes);

		return tooltip.trim();
	}

	private String tooltipNodes(List<ASTNode> nodes) {
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

	private String getASTStringOutput(ASTNode node) {
		String aststr = node.render().trim();
		aststr = aststr.replace('\n', ' ').replace('\r', ' ')
				.replace('\t', ' ');
		if (aststr.length() > 60) {
			aststr = aststr.substring(0, 50) + " ... "
					+ aststr.substring(aststr.length() - 5);
		}
		return aststr;
	}

	private String tooltipColors(Collection<ASTNode> nodes) {
		assert nodes.size() > 0;
		String tooltip = "";
		// all colors
		Set<Feature> allColors = getAllColors(nodes);
		tooltip += printColors(
				(nodes.size() > 1 ? "Common " : "") + "Features", allColors);

		// details (direct colors, inherited colors, file colors)
		tooltip += "\n";
		Set<Feature> directColors = getDirectColors(nodes);
		if (directColors.size() > 0)
			tooltip += printColorsShort("Direct Colors", directColors);
		else
			tooltip += "No direct colors.\n";

		Set<Feature> inheritedColors = getInheritedColors(nodes);
		if (inheritedColors.size() > 0)
			tooltip += printColorsShort("Inherited Colors", inheritedColors);

		Set<Feature> fileColors = getFileColors();
		if (fileColors.size() > 0)
			tooltip += printColorsShort("File Colors", fileColors);
		return tooltip;
	}

	private Set<Feature> getFileColors() {
		DirectoryColorManager dirColorManager = DirectoryColorManager
				.getColoredDirectoryManagerS(sourceFile.getResource()
						.getParent());
		return dirColorManager.getColors(sourceFile.getResource());
	}

	private Set<Feature> getInheritedColors(Collection<ASTNode> nodes) {
		// all inherited colors, but not the file colors
		assert nodes.size() > 0;
		Set<Feature> result = new HashSet<Feature>();
		Iterator<ASTNode> i = nodes.iterator();
		result.addAll(colorManager.getInheritedColors(i.next()));
		while (i.hasNext()) {
			join(result, colorManager.getInheritedColors(i.next()));
		}
		result.removeAll(getFileColors());
		return result;
	}

	private Set<Feature> getDirectColors(Collection<ASTNode> nodes) {
		assert nodes.size() > 0;
		Set<Feature> result = new HashSet<Feature>();
		Iterator<ASTNode> i = nodes.iterator();
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
	private void join(Set<Feature> target, Set<Feature> newEntries) {
		for (Iterator<Feature> i = target.iterator(); i.hasNext();) {
			if (!newEntries.contains(i.next()))
				i.remove();
		}
	}

	private Set<Feature> getAllColors(Collection<ASTNode> nodes) {
		assert nodes.size() > 0;
		Set<Feature> result = new HashSet<Feature>();
		Iterator<ASTNode> i = nodes.iterator();
		result.addAll(colorManager.getColors(i.next()));
		while (i.hasNext()) {
			join(result, colorManager.getColors(i.next()));
		}
		return result;
	}

	private List<ASTNode> findSelectedNodes(IRegion hoverRegion) {
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

		ArrayList<ASTNode> result = new ArrayList<ASTNode>();
		if (hoverRegion.getLength() == 0) {
			SingleNodeFinder snf = new SingleNodeFinder(hoverRegion.getOffset());
			ast.accept(snf);
			ASTNode node=snf.result;
			while (node!=null && !node.isOptional())
				node=node.getParent();
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

		ASTNode result = null;

		@Override
		public boolean visit(ASTNode node) {
			if (node.getStartPosition() <= offset
					&& (node.getStartPosition() + node.getLength()) > offset) {
				if (result == null || node.getLength() < result.getLength())
					result = node;
				return true;
			}
			return false;
		}
	}

	private String printColors(String title, Collection<Feature> colors) {
		String result = title + ":\n";
		ArrayList<Feature> sortedColors = new ArrayList<Feature>(colors);
		Collections.sort(sortedColors);
		for (Feature color : sortedColors) {
			result += " - " + color.getShortName(project) + "\n";
		}
		return result;
	}

	private String printColorsShort(String title, Collection<Feature> colors) {
		String result = title + ": ";
		ArrayList<Feature> sortedColors = new ArrayList<Feature>(colors);
		Collections.sort(sortedColors);
		boolean first = true;
		for (Feature color : sortedColors) {
			if (first)
				first = false;
			else
				result += ", ";
			result += color.getShortName(project);
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
