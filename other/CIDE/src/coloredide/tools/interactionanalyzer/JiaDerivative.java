package coloredide.tools.interactionanalyzer;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.eclipse.core.resources.IProject;
import org.eclipse.jdt.core.dom.ASTNode;
import org.eclipse.jdt.core.dom.CompilationUnit;
import org.eclipse.jdt.core.dom.FieldDeclaration;
import org.eclipse.jdt.core.dom.MethodDeclaration;
import org.eclipse.jdt.core.dom.SingleVariableDeclaration;
import org.eclipse.jdt.core.dom.TypeDeclaration;

import coloredide.features.Feature;
import coloredide.features.FeatureManager;
import coloredide.features.source.IColoredJavaSourceFile;

/**
 * where the feature-order is not relevant (eg. introduction with two colors)
 * the features are sorted by ID
 * 
 * @author cKaestner
 * 
 */
public class JiaDerivative implements Derivative {

	public JiaDerivative(ASTNode node, IColoredJavaSourceFile source) {
		loadDerivative(node, source);
		project = source.getProject();
	}

	private final List<Feature> introductions = new ArrayList<Feature>();

	private final List<Feature> functions = new ArrayList<Feature>();

	private final IProject project;

	public boolean equals(Object obj) {
		if (obj instanceof JiaDerivative) {
			JiaDerivative d = (JiaDerivative) obj;
			return d.introductions.equals(this.introductions)
					&& d.functions.equals(this.functions);
		}
		return super.equals(obj);
	}

	public int hashCode() {
		return 31 * +introductions.hashCode() + functions.hashCode();
	}

	public String toString() {
		return getDerivativeStr();
	}

	private void loadDerivative(ASTNode node, IColoredJavaSourceFile source) {
		Set<Feature> nodeColors = source.getColorManager().getColors(node);
		if (nodeColors.isEmpty()) {
			introductions.add(FeatureManager.BASEFEATURE);
			return;
		}

		if (isIntroduction(node)) {
			loadIntroduction(node, source);
			return;
		}

		ASTNode introduction = getIntroduction(node);
		Set<Feature> introColors = source.getColorManager().getColors(
				introduction);

		if (nodeColors.equals(introColors)) {
			loadIntroduction(introduction, source);
			return;
		} else {
			loadIntroduction(introduction, source);
			loadTransformation(nodeColors, introColors);
			return;
		}
	}

	private void loadTransformation(Set<Feature> nodeColors,
			Set<Feature> introColors) {
		Set<Feature> transformationColors = new HashSet<Feature>();
		transformationColors.addAll(nodeColors);
		transformationColors.removeAll(introColors);
		List<Feature> c = new ArrayList<Feature>();
		c.addAll(transformationColors);
		Collections.sort(c);
		functions.addAll(transformationColors);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see coloredide.tools.interactionanalyzer.Derivative#getDerivativeStr()
	 */
	public String getDerivativeStr() {
		if (functions.isEmpty())
			return "I: " + getIntroductionStr();
		else
			return "A: " + getIntroductionStr() + " / "
					+ getTransformationStr();
	}

	private String getIntroductionStr() {
		boolean first = true;
		String result = "";
		for (Feature f : introductions) {
			if (first)
				first = false;
			else
				result += "*";
			result += f.getShortName(project).toLowerCase();
		}
		return result;
	}

	private String getTransformationStr() {
		boolean first = true;
		String result = "";
		for (Feature f : functions) {
			if (first)
				first = false;
			else
				result += "*";
			result += f.getShortName(project).toUpperCase();
		}
		return result;
	}

	private void loadIntroduction(ASTNode node, IColoredJavaSourceFile source) {
		List<Feature> introColors = new ArrayList<Feature>();
		introColors.addAll(source.getColorManager().getColors(node));
		if (introColors.isEmpty()) {
			introductions.add(FeatureManager.BASEFEATURE);
			return;
		}
		Collections.sort(introColors);
		introductions.addAll(introColors);

	}

	private ASTNode getIntroduction(ASTNode node) {
		while (node != null) {
			if (isIntroduction(node))
				return node;
			node = node.getParent();
		}
		return null;
	}

	private boolean isIntroduction(ASTNode node) {
		if (node instanceof FieldDeclaration)
			return isIntroduction(node.getParent());
		if (node instanceof SingleVariableDeclaration
				&& node.getParent() instanceof FieldDeclaration)
			return isIntroduction(node.getParent());
		if (node instanceof MethodDeclaration)
			return isIntroduction(node.getParent());
		if (node instanceof TypeDeclaration)
			return isIntroduction(node.getParent());
		if (node instanceof CompilationUnit)
			return true;
		if (node.getParent() instanceof CompilationUnit)
			return true;

		return false;
	}

	public int compareTo(Object o1) {
		if (o1 instanceof JiaDerivative) {
			JiaDerivative o = (JiaDerivative) o1;
			// introductions first
			if (this.functions.size() == 0 && o.functions.size() > 0)
				return -1;
			if (this.functions.size() > 0 && o.functions.size() == 0)
				return 1;
			// shorter of two introductions first
			if (this.introductions.size() < o.introductions.size())
				return -1;
			if (this.introductions.size() > o.introductions.size())
				return 1;
			// sort by introduction list
			for (int i = 0; i < this.introductions.size(); i++) {
				if (this.introductions.get(i).compareTo(o.introductions.get(i)) > 0)
					return 1;
				if (this.introductions.get(i).compareTo(o.introductions.get(i)) < 0)
					return -1;
			}
			// shorter function list first
			if (this.functions.size() < o.functions.size())
				return -1;
			if (this.functions.size() > o.functions.size())
				return 1;
			// last sort by function list
			for (int i = 0; i < this.functions.size(); i++) {
				if (this.functions.get(i).compareTo(o.functions.get(i)) > 0)
					return 1;
				if (this.functions.get(i).compareTo(o.functions.get(i)) < 0)
					return -1;
			}
		}

		return 0;
	}
}
