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

package de.ovgu.cide.typing.list;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import de.ovgu.cide.features.IFeature;
import de.ovgu.cide.features.IFeatureModel;
import de.ovgu.cide.typing.model.IEvaluationStrategy;

/**
 * this is a simple subset strategy for the List feature model. A implies B if A
 * has less or the same colors than B.
 * 
 * the check is so simple that caching is not needed
 * 
 * 
 * @author ckaestne
 * 
 */
public class SubsetStrategy implements IEvaluationStrategy {

	public boolean equal(IFeatureModel featureModel, Set<IFeature> source,
			Set<IFeature> target) {
		return source.equals(target);
	}

	public boolean equal(IFeatureModel featureModel, Set<IFeature> context,
			Set<IFeature> source, Set<IFeature> target) {
		HashSet<IFeature> additionalColorsSource = new HashSet<IFeature>(source);
		additionalColorsSource.removeAll(context);
		HashSet<IFeature> additionalColorsTarget = new HashSet<IFeature>(target);
		additionalColorsTarget.removeAll(context);
		return additionalColorsSource.containsAll(additionalColorsTarget);
	}

	public boolean implies(IFeatureModel featureModel, Set<IFeature> source,
			Set<IFeature> target) {
		return source.containsAll(target);
	}

	public void clearCache(IFeatureModel featureModel) {
		// not needed
	}

	public boolean areMutualExclusive(IFeatureModel featureModel,
			Set<IFeature> context, List<Set<IFeature>> featureSets) {
		return (featureSets.size() < 2);
	}

	public boolean mayBeMissing(IFeatureModel featureModel,
			Set<IFeature> context, List<Set<IFeature>> featureSets) {
		return (!featureSets.isEmpty());
	}

	public boolean exists(IFeatureModel featureModel, Set<IFeature> features) {
		return true;
	}

}
