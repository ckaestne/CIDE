package coloredide.features.source;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import org.eclipse.jdt.core.dom.ASTNode;

import coloredide.features.ASTColorInheritance;
import coloredide.features.Feature;

public abstract class AbstractColorManager implements IColorManager {

	public boolean hasColor(ASTNode node, Feature color) {
		return getOwnColors(node).contains(color);
	}

	public Set<Feature> getColors(ASTNode node) {
		Set<Feature> result = new HashSet<Feature>();
		result.addAll(getOwnColors(node));
		result.addAll(getInheritedColors(node));
		return Collections.unmodifiableSet(result);
	}

	public Set<Feature> getInheritedColors(ASTNode node) {
		return getInheritedColorsI(node, 1);
	}

	private Set<Feature> getInheritedColorsI(ASTNode node, int i) {
		Set<Feature> result = new HashSet<Feature>();

		ASTNode parent = node.getParent();
		if (i>60){
			System.out.println(i);
		}
		if (parent != null) {
			if (ASTColorInheritance.inheritsColors(parent, node))
				result.addAll(getOwnColors(parent));
			result.addAll(getInheritedColorsI(parent, i+1));
		}

		return Collections.unmodifiableSet(result);
	}

	public boolean clearColor(ASTNode node) {
		if (getOwnColors(node).size() > 0) {
			setColors(node, new HashSet<Feature>());
			return true;
		}
		return false;
	}

	public boolean removeColor(ASTNode node, Feature color) {
		Set<Feature> c = getOwnColors(node);
		if (c.remove(color)) {
			setColors(node, c);
			return true;
		}
		return false;
	}

	public boolean addColor(ASTNode node, Feature color) {
		Set<Feature> c = getOwnColors(node);
		if (!c.contains(color)) {
			c.add(color);
			setColors(node, c);
			return true;
		}
		return false;
	}

	public void addColors(ASTNode node, Set<Feature> colors) {
		Set<Feature> c = getOwnColors(node);
		if (!c.containsAll(colors)) {
			c.addAll(colors);
			setColors(node, c);
		}
	}

}