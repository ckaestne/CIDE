package coloredide.export2pp;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.eclipse.core.resources.IProject;
import org.eclipse.jdt.core.dom.ASTNode;

import coloredide.features.Feature;
import coloredide.features.source.IColorManager;
import coloredide.utils.CopiedNaiveASTFlattener;

/**
 * prints the AST with preprocessor instructions for features
 * 
 * @author cKaestner
 * 
 */
public class PPPrettyPrinter extends CopiedNaiveASTFlattener {

	private IColorManager colorManager;

	private IProject project;

	public PPPrettyPrinter(IColorManager colorManager,
			IProject project) {
		this.colorManager = colorManager;
		this.project = project;
	}

	@Override
	public void preVisit(ASTNode node) {
		Set<Feature> ownColors = colorManager.getOwnColors(node);
		if (ownColors != null && ownColors.size() > 0) {
			for (Feature f : ownColors)
				printPPOpen(f);
		}

		super.preVisit(node);
	}

	@Override
	public void postVisit(ASTNode node) {
		Set<Feature> ownColors = colorManager.getOwnColors(node);

		if (ownColors != null && ownColors.size() > 0) {
			for (Feature f : ownColors)
				printPPClose();

		}

		super.postVisit(node);
	}

	private void printPPOpen(Feature f) {
		String ifdefStr = "#ifdef " + getFeatureToken(f) + "\n";
		if (buffer.length() > 0) {
			char lastChar = buffer.charAt(buffer.length() - 1);
			if (lastChar != '\n')
				buffer.append('\n');
		}
		buffer.append(ifdefStr);
	}

	private void printPPClose() {
		if (buffer.length() > 0) {
			char lastChar = buffer.charAt(buffer.length() - 1);
			if (lastChar != '\n')
				buffer.append('\n');
		}
		buffer.append("#endif\n");
	}

	private String getFeatureToken(Feature f) {
		String featureName = f.getShortName(project).toUpperCase();
		StringBuffer result = new StringBuffer();
		for (int idx = 0; idx < featureName.length(); idx++)
			if (Character.isLetter(featureName.charAt(idx)))
				result.append(featureName.charAt(idx));
		return result.toString();
	}

}
