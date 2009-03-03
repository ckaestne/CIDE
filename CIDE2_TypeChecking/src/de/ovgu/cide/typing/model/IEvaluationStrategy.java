package de.ovgu.cide.typing.model;

import java.util.List;
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
	boolean implies(IFeatureModel featureModel, Set<IFeature> source, Set<IFeature> target);

	/**
	 * annotation on source is equivalent to the annotation on target
	 * ("source and target are present in the same variants", A implies B and B
	 * implies A).
	 * 
	 * @param source
	 * @param target
	 * @return true if source and target occur in exactly the same variants
	 */
	boolean equal(IFeatureModel featureModel, Set<IFeature> source, Set<IFeature> target);
	
	/**
	 * Checks whether the given featureSets are mutually exclusive in the given context and for the current feature model.
	 * 
	 * In detail it is checked whether
	 * 		FM => (context => (at most one of the featureSets are present))
	 * is a tautology.
	 * 
	 * Here is an example for a truth table of "at most one the featureSets are present" for three feature sets A, B and C:
	 * 
	 * A	B	 C		result
	 * ------------------------
	 * T	T	 T		  F
	 * T	T	 F		  F
	 * T	F	 T		  F
	 * T	F	 F		  T
	 * F	T	 T		  F
	 * F	T	 F		  T
	 * F	F	 T		  T
	 * F	F	 F		  T
	 * 
	 * If you want to check XOR(featureSet_1, ..., featureSet_n) you can call areMutualExclusive() && !mayBeMissing().
	 * 
	 * @param featureModel
	 * @param context
	 * @param featureSets
	 * 
	 * @return true, if the feature sets are mutually exclusive || false, otherwise
	 */
	boolean areMutualExclusive(IFeatureModel featureModel, Set<IFeature> context, List<Set<IFeature>> featureSets);
	
	/**
	 * Checks whether there exists a set of features that is valid within the feature model and the given context, so that none of the given
	 * feature sets are present, i.e. evaluate to true.
	 * 
	 * In detail it is checked whether there exists a set F of features so that
	 * 		eval(FM, F) AND eval(context, F) AND NOT(eval(featureSet_1, F)) AND ... AND NOT(eval(featureSet_n, F))
	 * is true.
	 * 
	 * If you want to check XOR(featureSet_1, ..., featureSet_n) you can call areMutualExclusive() && !mayBeMissing().
	 * 
	 * @param featureModel
	 * @param context
	 * @param featureSets
	 * 
	 * @return true, if there exists such a set of features, i.e. if the code-fragment may be missing || false, otherwise
	 */
	boolean mayBeMissing(IFeatureModel featureModel, Set<IFeature> context, List<Set<IFeature>> featureSets);

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
