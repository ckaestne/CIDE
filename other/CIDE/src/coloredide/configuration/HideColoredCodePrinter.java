package coloredide.configuration;

import java.util.Set;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.jdt.core.JavaModelException;
import org.eclipse.jdt.core.dom.ASTParser;
import org.eclipse.jdt.core.dom.CompilationUnit;

import coloredide.features.Feature;
import coloredide.features.source.IColoredJavaSourceFile;

/**
 * hides colored code when the colors are selected as hidden.
 * 
 * when one of two sibblings is hidden the code in between is removed as well.
 * 
 */
public class HideColoredCodePrinter extends ColoredCodePrinter {

	protected Set<Feature> hiddenColors;

	public String printCode(IColoredJavaSourceFile file,
			Set<Feature> hiddenColors) throws JavaModelException, CoreException {
		this.hiddenColors = hiddenColors;
		return super.printCode(file);
	}

	protected String printCode(String buffer, CompilationUnit root) {

		try {
			String filteredCode = DeleteHiddenNodesVisitor.hideCode(buffer,
					root, nodeColors, hiddenColors);

			ASTParser parser = ASTParser.newParser(root.getAST().apiLevel());
			parser.setResolveBindings(false);
			parser.setSource(filteredCode.toCharArray());
			parser.setKind(ASTParser.K_COMPILATION_UNIT);
			CompilationUnit newRoot = (CompilationUnit) parser.createAST(null);

			return super.printCode(filteredCode, newRoot);
		} catch (Exception e) {
			e.printStackTrace();
			return e.toString();
		}
	}

}
