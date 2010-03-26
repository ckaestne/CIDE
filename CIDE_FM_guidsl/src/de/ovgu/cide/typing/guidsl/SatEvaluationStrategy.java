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

package de.ovgu.cide.typing.guidsl;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import org.sat4j.specs.TimeoutException;

import de.ovgu.cide.features.IFeature;
import de.ovgu.cide.features.IFeatureModel;
import de.ovgu.cide.fm.guidsl.FeatureAdapter;
import de.ovgu.cide.fm.guidsl.FeatureModelProxy;
import de.ovgu.cide.typing.model.AbstractCachingEvaluationStrategy;
import de.ovgu.cide.typing.model.DebugTyping;
import featureide.fm.core.Feature;
import featureide.fm.core.FeatureModel;

/**
 * evaluation strategy for the typechecker that delegates checks to the guidsl
 * implementation which then used a SAT solver to check implications on the
 * background of the feature model
 * 
 * this strategy must cache results because queries to the SAT solver are
 * expensive
 * 
 * @author ckaestne
 * 
 */
public class SatEvaluationStrategy extends AbstractCachingEvaluationStrategy {

	@Override
	public boolean implies(IFeatureModel featureModel, Set<IFeature> source,
			Set<IFeature> target) {

		DebugTyping.debug_requests++;
		// if (source.containall(target))
		// return true;

		if (source.isEmpty() && target.isEmpty()) {
			DebugTyping.debug_emptycounter++;
			return true;
		}
		if (source.equals(target)) {
			DebugTyping.debug_equalcounter++;
			return true;
		}

		return super.implies(featureModel, source, target);
	}

	@Override
	protected boolean calcImplies(IFeatureModel featureModel,
			Set<IFeature> source, Set<IFeature> target) {
		DebugTyping.debug_cache_miss++;
		// ignore empty feature models
		assert featureModel instanceof FeatureModelProxy;

		DebugTyping.debug_satcounter++;

		if (source.containsAll(target)) {
			DebugTyping.debug_subsetcounter++;
			return true;
		}

		long start = System.currentTimeMillis();// debug only

		FeatureModel guidslModel = ((FeatureModelProxy) featureModel)
				.getInternalModel();
		if (guidslModel == null)
			return true;

		Set<Feature> guidslSourceFeatures = convertToGuidslFeatures(source);
		Set<Feature> guidslTargetFeatures = convertToGuidslFeatures(target);

		try {
			boolean result = guidslModel.checkImplies(guidslSourceFeatures,
					guidslTargetFeatures);
			long end = System.currentTimeMillis();// debug only
			DebugTyping.satTime(end - start, source, target);
			return result;
		} catch (TimeoutException e) {
			e.printStackTrace();
			// in case of a timeout assume everything is fine and the
			// implication is true. idea is not to report false positive
			return true;
		}
	}
	
	

	public boolean areMutualExclusive(IFeatureModel featureModel,
			Set<IFeature> context, List<Set<IFeature>> featureSets) {
		// ignore empty feature models
		assert featureModel instanceof FeatureModelProxy;
		FeatureModel guidslModel = ((FeatureModelProxy) featureModel)
				.getInternalModel();
		if (guidslModel == null)
			return true;

		List<Set<Feature>> guidslFeatureSets = new LinkedList<Set<Feature>>();
		for (Set<IFeature> features : featureSets) {
			guidslFeatureSets.add(convertToGuidslFeatures(features));
		}

		try {
			return guidslModel.areMutualExclusive(
					convertToGuidslFeatures(context), guidslFeatureSets);
		} catch (TimeoutException e) {
			e.printStackTrace();
			// in case of a timeout assume everything is fine and the
			// formula is true. idea is not to report false positive
			return true;
		}
	}

	public boolean mayBeMissing(IFeatureModel featureModel,
			Set<IFeature> context, List<Set<IFeature>> featureSets) {
		// ignore empty feature models
		assert featureModel instanceof FeatureModelProxy;
		FeatureModel guidslModel = ((FeatureModelProxy) featureModel)
				.getInternalModel();
		if (guidslModel == null)
			return true;

		List<Set<Feature>> guidslFeatureSets = new LinkedList<Set<Feature>>();
		for (Set<IFeature> features : featureSets) {
			guidslFeatureSets.add(convertToGuidslFeatures(features));
		}

		try {
			return guidslModel.mayBeMissing(convertToGuidslFeatures(context),
					guidslFeatureSets);
		} catch (TimeoutException e) {
			e.printStackTrace();
			// in case of a timeout assume everything is fine and the
			// result is false. idea is not to report false positive
			return false;
		}
	}

	@Override
	protected boolean calcExists(IFeatureModel featureModel,
			Set<IFeature> features) {
		// ignore empty feature models
		assert featureModel instanceof FeatureModelProxy;
		FeatureModel guidslModel = ((FeatureModelProxy) featureModel)
				.getInternalModel();
		if (guidslModel == null)
			return true;
		try {
			return guidslModel.exists(convertToGuidslFeatures(features));
		} catch (TimeoutException e) {
			e.printStackTrace();
			// in case of a timeout assume everything is fine and the
			// formula is true. idea is not to report false positive
			return true;
		}
	}

	private Set<Feature> convertToGuidslFeatures(Set<IFeature> features) {
		if (features == null)
			return null;

		Set<Feature> guidslFeatures = new HashSet<Feature>(features.size());
		for (IFeature f : features) {
			assert f instanceof FeatureAdapter;
			guidslFeatures.add(((FeatureAdapter) f).getInternalFeature());
		}

		return guidslFeatures;
	}

	
}
