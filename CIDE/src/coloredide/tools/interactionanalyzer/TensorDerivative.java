package coloredide.tools.interactionanalyzer;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.eclipse.core.resources.IProject;
import org.eclipse.jdt.core.dom.ASTNode;

import coloredide.features.ASTColorInheritance;
import coloredide.features.Feature;
import coloredide.features.source.IColoredJavaSourceFile;

/**
 * new notation: no distinction between introductions or extensions. all
 * features are extensions to the base code (or empty program)
 * 
 * notation A* means extension of A to the base program B*A* means extension of
 * B to the extension of A to the base program (C*B)*A* means extension of C and
 * B to the extension of A to the base program where the order of C and B does
 * not matter.
 * 
 * 
 * @author cKaestner
 * 
 */
public class TensorDerivative implements Derivative {

	public TensorDerivative(ASTNode node, IColoredJavaSourceFile source) {
		loadDerivative(node, source);
		project = source.getProject();
	}

	private final List<Set<Feature>> features = new ArrayList<Set<Feature>>();

//	private final Set<Feature> knownFeatures = new HashSet<Feature>();

	private final IProject project;

	public boolean equals(Object obj) {
		if (obj instanceof TensorDerivative) {
			TensorDerivative d = (TensorDerivative) obj;
			return d.features.equals(this.features);
		}
		return super.equals(obj);
	}

	public int hashCode() {
		return features.hashCode();
	}

	public String toString() {
		return getDerivativeStr();
	}

	private void loadDerivative(ASTNode node, IColoredJavaSourceFile source) {
		if (node == null)
			return;

		Set<Feature> ownColors = source.getColorManager().getOwnColors(node);
		Set<Feature> inheritedColors = source.getColorManager()
				.getInheritedColors(node);

		if (inheritedColors.size() > 0) {
			ASTNode lnode=node;
			ASTNode parent = node.getParent();
				
			 while (parent != null
					&& !ASTColorInheritance.inheritsColors(parent, lnode)){
				 lnode=parent;
				 parent=parent.getParent();
			 }
			if (parent != null)
				loadDerivative(parent, source);
		}

		Set<Feature> addedColors = new HashSet<Feature>(ownColors);
		addedColors.removeAll(inheritedColors);

		if (addedColors.size() > 0) {
			features.add(addedColors);
		}
	}

	public String getDerivativeStr() {
		String result = "";
		for (Set<Feature> f : features) {
			result = getFeatureGroupStr(f) + "*" + result;
		}
		return result;
	}

	private String getFeatureGroupStr(Set<Feature> f) {
		assert f.size() > 0;
		if (f.size() == 1)
			return f.iterator().next().getShortName(project);
		ArrayList<Feature> sortedList = new ArrayList<Feature>(f);
		Collections.sort(sortedList);
		String result = "(";
		boolean first = true;
		for (Feature feature : sortedList) {
			if (!first)
				result += "*";
			else
				first = false;
			result += feature.getShortName(project);
		}
		return result + ")";
	}

	public int getOrder() {
		int order = 0;
		for (Set<Feature> f : features) {
			order += f.size();
		}
		return order;
	}

	protected List<Feature> getFeatureList() {
		List<Feature> result = new ArrayList<Feature>(getOrder());
		for (Set<Feature> f : features) {
			List<Feature> sort = new ArrayList<Feature>(f);
			Collections.sort(sort);
			result.addAll(sort);
		}
		return result;
	}

	public int compareTo(Object o1) {
		if (o1 instanceof TensorDerivative)
			try {
				TensorDerivative o = (TensorDerivative) o1;
				// lower order derivatives first
				if (this.getOrder() < o.getOrder())
					return -1;
				if (this.getOrder() > o.getOrder())
					return 1;

				// order by features
				for (int i = 0; i < this.getFeatureList().size(); i++) {
					if (this.getFeatureList().get(i).compareTo(
							o.getFeatureList().get(i)) > 0)
						return 1;
					if (this.getFeatureList().get(i).compareTo(
							o.getFeatureList().get(i)) < 0)
						return -1;
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		return 0;
	}
}
