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
