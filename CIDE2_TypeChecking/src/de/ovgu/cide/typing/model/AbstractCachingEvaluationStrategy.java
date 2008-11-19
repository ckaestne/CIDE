package de.ovgu.cide.typing.model;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.WeakHashMap;

import de.ovgu.cide.features.IFeature;
import de.ovgu.cide.features.IFeatureModel;

public abstract class AbstractCachingEvaluationStrategy implements
		IEvaluationStrategy {

	private final WeakHashMap<IFeatureModel, Map<Set<IFeature>, Map<Set<IFeature>, Boolean>>> cache=new WeakHashMap<IFeatureModel, Map<Set<IFeature>,Map<Set<IFeature>,Boolean>>>();

	/**
	 * non-caching, delegated to implies. override if necessary
	 */
	public boolean equal(IFeatureModel featureModel, Set<IFeature> source,
			Set<IFeature> target) {
		return implies(featureModel, source, target)
				&& implies(featureModel, target, source);
	}

	/**
	 * caching, delegates to calcImplies which must be implemented by
	 * subclasses. this method should usually not be overridden
	 */
	public boolean implies(IFeatureModel featureModel, Set<IFeature> source,
			Set<IFeature> target) {

		Map<Set<IFeature>, Map<Set<IFeature>, Boolean>> cacheForFeatureModel = cache
				.get(featureModel);
		if (cacheForFeatureModel != null) {
			Map<Set<IFeature>, Boolean> cacheForSource = cacheForFeatureModel
					.get(source);
			if (cacheForSource != null) {
				Boolean cachedValue = cacheForSource.get(target);
				if (cachedValue != null)
					return cachedValue.booleanValue();
			}
		}

		boolean result = calcImplies(featureModel, source, target);

		storeInCache(featureModel, source, target, result);

		return result;
	}

	private void storeInCache(IFeatureModel featureModel, Set<IFeature> source,
			Set<IFeature> target, boolean result) {
		Map<Set<IFeature>, Map<Set<IFeature>, Boolean>> cacheForFeatureModel = cache
				.get(featureModel);
		if (cacheForFeatureModel == null) {
			cacheForFeatureModel = new HashMap<Set<IFeature>, Map<Set<IFeature>, Boolean>>();
			cache.put(featureModel, cacheForFeatureModel);
		}

		Map<Set<IFeature>, Boolean> cacheForSource = cacheForFeatureModel
				.get(source);
		if (cacheForSource == null) {
			cacheForSource = new HashMap<Set<IFeature>, Boolean>();
			cacheForFeatureModel.put(source, cacheForSource);
		}

		cacheForSource.put(target, new Boolean(result));
	}

	protected abstract boolean calcImplies(IFeatureModel featureModel,
			Set<IFeature> source, Set<IFeature> target);

	public void clearCache(IFeatureModel featureModel) {
		cache.remove(featureModel);
	}

}
