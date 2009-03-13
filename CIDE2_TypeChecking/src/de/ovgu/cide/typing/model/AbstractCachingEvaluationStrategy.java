package de.ovgu.cide.typing.model;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.WeakHashMap;

import de.ovgu.cide.features.IFeature;
import de.ovgu.cide.features.IFeatureModel;

public abstract class AbstractCachingEvaluationStrategy implements IEvaluationStrategy {

	private final WeakHashMap<IFeatureModel, Map<Set<IFeature>, Map<Set<IFeature>, Boolean>>> impliesCache
		= new WeakHashMap<IFeatureModel, Map<Set<IFeature>,Map<Set<IFeature>,Boolean>>>();
	
	private final WeakHashMap<IFeatureModel, Map<Set<IFeature>, Boolean>> existsCache
		= new WeakHashMap<IFeatureModel, Map<Set<IFeature>, Boolean>>();

	/**
	 * non-caching, delegated to implies. override if necessary
	 */
	public boolean equal(IFeatureModel featureModel, Set<IFeature> source, Set<IFeature> target) {
		return implies(featureModel, source, target) && implies(featureModel, target, source);
	}

	/**
	 * caching, delegates to calcImplies which must be implemented by
	 * subclasses. this method should usually not be overridden
	 */
	public boolean implies(IFeatureModel featureModel, Set<IFeature> source, Set<IFeature> target) {
		Map<Set<IFeature>, Map<Set<IFeature>, Boolean>> cacheForFeatureModel = impliesCache.get(featureModel);
		if (cacheForFeatureModel != null) {
			Map<Set<IFeature>, Boolean> cacheForSource = cacheForFeatureModel.get(source);
			
			if (cacheForSource != null) {
				Boolean cachedValue = cacheForSource.get(target);
				if (cachedValue != null)
					return cachedValue.booleanValue();
			}
		}

		boolean result = calcImplies(featureModel, source, target);
		storeInImpliesCache(featureModel, source, target, result);

		return result;
	}
	
	public boolean exists(IFeatureModel featureModel, Set<IFeature> features) {
		Map<Set<IFeature>, Boolean> cacheForFeatureModel = existsCache.get(featureModel);
		if (cacheForFeatureModel != null) {
			Boolean exists = cacheForFeatureModel.get(features);
			if (exists != null)
				return exists.booleanValue();
		}
		
		boolean result = calcExists(featureModel, features);
		storeInExistsCache(featureModel, features, result);
		
		return result;
	}

	private void storeInImpliesCache(IFeatureModel featureModel, Set<IFeature> source,	Set<IFeature> target, boolean result) {
		Map<Set<IFeature>, Map<Set<IFeature>, Boolean>> cacheForFeatureModel = impliesCache.get(featureModel);
		if (cacheForFeatureModel == null) {
			cacheForFeatureModel = new HashMap<Set<IFeature>, Map<Set<IFeature>, Boolean>>();
			impliesCache.put(featureModel, cacheForFeatureModel);
		}

		Map<Set<IFeature>, Boolean> cacheForSource = cacheForFeatureModel.get(source);
		if (cacheForSource == null) {
			cacheForSource = new HashMap<Set<IFeature>, Boolean>();
			cacheForFeatureModel.put(source, cacheForSource);
		}

		cacheForSource.put(target, new Boolean(result));
	}
	
	private void storeInExistsCache(IFeatureModel featureModel, Set<IFeature> features, boolean result) {
		Map<Set<IFeature>, Boolean> cacheForFeatureModel = existsCache.get(featureModel);
		if (cacheForFeatureModel == null) {
			cacheForFeatureModel = new HashMap<Set<IFeature>, Boolean>();
			existsCache.put(featureModel, cacheForFeatureModel);
		}
		
		cacheForFeatureModel.put(features, result);
	}

	protected abstract boolean calcImplies(IFeatureModel featureModel, Set<IFeature> source, Set<IFeature> target);
	protected abstract boolean calcExists(IFeatureModel featureModel, Set<IFeature> features);

	public void clearCache(IFeatureModel featureModel) {
		impliesCache.remove(featureModel);
		existsCache.remove(featureModel);
	}
}
