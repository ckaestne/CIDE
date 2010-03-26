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

package de.ovgu.cide.tools.interactionanalyzer;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import cide.gast.ASTWrappers;
import cide.gast.IASTNode;
import de.ovgu.cide.features.IFeature;
import de.ovgu.cide.features.source.ColoredSourceFile;

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

	public TensorDerivative(IASTNode node, ColoredSourceFile source) {
		loadDerivative(node, source);
	}

	private final List<Set<IFeature>> features = new ArrayList<Set<IFeature>>();

//	private final Set<IFeature> knownFeatures = new HashSet<IFeature>();


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

	private void loadDerivative(IASTNode node, ColoredSourceFile source) {
		if (node == null)
			return;

		Set<IFeature> ownColors = source.getColorManager().getOwnColors(node);
		Set<IFeature> inheritedColors = source.getColorManager()
				.getInheritedColors(node);

		if (inheritedColors.size() > 0) {
			IASTNode lnode=node;
			IASTNode parent = node.getParent();
				
			 while (parent != null
					&& !ASTWrappers.inheritsColors(parent, lnode)){
				 lnode=parent;
				 parent=parent.getParent();
			 }
			if (parent != null)
				loadDerivative(parent, source);
		}

		Set<IFeature> addedColors = new HashSet<IFeature>(ownColors);
		addedColors.removeAll(inheritedColors);

		if (addedColors.size() > 0) {
			features.add(addedColors);
		}
	}

	public String getDerivativeStr() {
		String result = "";
		for (Set<IFeature> f : features) {
			result = getFeatureGroupStr(f) + "*" + result;
		}
		return result;
	}

	private String getFeatureGroupStr(Set<IFeature> f) {
		assert f.size() > 0;
		if (f.size() == 1)
			return f.iterator().next().getName();
		ArrayList<IFeature> sortedList = new ArrayList<IFeature>(f);
		Collections.sort(sortedList);
		String result = "(";
		boolean first = true;
		for (IFeature feature : sortedList) {
			if (!first)
				result += "*";
			else
				first = false;
			result += feature.getName();
		}
		return result + ")";
	}

	public int getOrder() {
		int order = 0;
		for (Set<IFeature> f : features) {
			order += f.size();
		}
		return order;
	}

	protected List<IFeature> getFeatureList() {
		List<IFeature> result = new ArrayList<IFeature>(getOrder());
		for (Set<IFeature> f : features) {
			List<IFeature> sort = new ArrayList<IFeature>(f);
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
