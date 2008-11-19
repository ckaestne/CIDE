package de.ovgu.cide.typing.model;

import java.util.Set;

import de.ovgu.cide.features.IFeature;
import de.ovgu.cide.features.IFeatureModel;

/**
 * interface implemented by CIDE (or its feature models) that implement an
 * evaluation strategy
 * 
 * in all cases the strategy is responsible for looking up the annotation and
 * for caching if necessary
 * 
 * @author ckaestne
 * 
 */
public interface IEvaluationStrategy {

	/**
	 * annotation on source implies the annotation on target
	 * ("whenever source is present, then also target must be present").
	 * 
	 * @param featureModel
	 * 
	 * @param source
	 * @param target
	 * @return true if target occurs in every variant in which source occurs,
	 *         false if there is any variant in which source occurs but not
	 *         target
	 */
	boolean implies(IFeatureModel featureModel, Set<IFeature> source,
			Set<IFeature> target);

	/**
	 * annotation on source is equivalent to the annotation on target
	 * ("source and target are present in the same variants", A implies B and B
	 * implies A).
	 * 
	 * @param source
	 * @param target
	 * @return true if source and target occur in exactly the same variants
	 */
	boolean equal(IFeatureModel featureModel, Set<IFeature> source,
			Set<IFeature> target);

	/**
	 * information for caching models that the feature model has changed and the
	 * cache should thus be discarded. may be implemented empty by non-caching
	 * strategies
	 * 
	 * @param featureModel
	 *            feature model that should be discarded from cache
	 */
	void clearCache(IFeatureModel featureModel);
}
