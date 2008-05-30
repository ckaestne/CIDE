package coloredide.editor;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.jdt.core.ICompilationUnit;
import org.eclipse.jdt.core.JavaModelException;
import org.eclipse.jdt.core.dom.ASTNode;
import org.eclipse.jdt.core.dom.CompilationUnit;
import org.eclipse.jdt.ui.text.java.hover.IJavaEditorTextHover;
import org.eclipse.jface.text.IRegion;
import org.eclipse.jface.text.ITextViewer;
import org.eclipse.ui.IEditorPart;

import coloredide.features.Feature;
import coloredide.features.source.ColoredJavaSourceFile;
import coloredide.features.source.IColoredJavaSourceFile;
import coloredide.utils.EditorUtility;

public class ColorInfoHover implements IJavaEditorTextHover {

	private IColoredJavaSourceFile currentSourceFile;

	public void setEditor(IEditorPart editor) {
		if (editor instanceof ColoredCompilationUnitEditor) {
			ICompilationUnit compUnit = EditorUtility
					.getCompilationUnitFromInput(editor);
			currentSourceFile = ColoredJavaSourceFile
					.getColoredJavaSourceFile(compUnit);
		} else {
			currentSourceFile = null;
		}
	}

	public String getHoverInfo(ITextViewer textViewer, IRegion hoverRegion) {
		if (currentSourceFile == null)
			return null;

//		System.out.println("range " + hoverRegion.getOffset() + ", "
//				+ hoverRegion.getLength());
		try {
			CompilationUnit ast = currentSourceFile.getAST();

			Set<ASTNode> selectedNodes = new HashSet<ASTNode>();
			ast.accept(new SelectionFinder(selectedNodes, hoverRegion));

			if (selectedNodes.size() == 0)
				return null;

			Set<Feature> colors = new HashSet<Feature>();
			for (ASTNode node : selectedNodes) {
				colors.addAll(currentSourceFile.getColorManager().getColors(
						node));
			}
			if (colors.size() == 0)
				return null;
			List<Feature> sortedColors = new ArrayList<Feature>(colors);
			Collections.sort(sortedColors);
			String colorStr = "";
			for (Feature color : sortedColors) {
				if (!colorStr.equals(""))
					colorStr += ", ";
				colorStr += color.getShortName(currentSourceFile.getProject());
			}
			return "Selected and inherited features: " + colorStr;

		} catch (JavaModelException e) {
			return null;
		} catch (CoreException e) {
			return null;
		}
	}

	public IRegion getHoverRegion(ITextViewer textViewer, int offset) {
//		System.out.println("getHoverRegion " + offset);
		return null;
	}

}
