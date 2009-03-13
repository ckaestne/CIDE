package de.ovgu.cide.typing.guidsl;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import org.sat4j.specs.TimeoutException;

import de.ovgu.cide.features.IFeature;
import de.ovgu.cide.features.IFeatureModel;
import de.ovgu.cide.fm.guidsl.EmptyFeatureModel;
import de.ovgu.cide.fm.guidsl.FeatureAdapter;
import de.ovgu.cide.fm.guidsl.GuidslFeatureModelWrapper;
import de.ovgu.cide.typing.model.AbstractCachingEvaluationStrategy;
import featureide.fm.core.Feature;
import featureide.fm.core.FeatureModel;

/**
 * evaluation strategy for the typechecker that delegates checks to the guidsl
 * implementation which then used a SAT solver to check implications on the
 * background of the feature model
 * 
 * this strategy must cache results as queries to the SAT solver are expensive
 * 
 * @author ckaestne
 * 
 */
public class SatEvaluationStrategy extends AbstractCachingEvaluationStrategy {

	@Override
	protected boolean calcImplies(IFeatureModel featureModel, Set<IFeature> source, Set<IFeature> target) {
		// ignore empty feature models
		if (featureModel instanceof EmptyFeatureModel)
			return true;
		assert featureModel instanceof GuidslFeatureModelWrapper;

		FeatureModel guidslModel = ((GuidslFeatureModelWrapper) featureModel).getInternalModel();

		Set<Feature> guidslSourceFeatures = convertToGuidslFeatures(source);
		Set<Feature> guidslTargetFeatures = convertToGuidslFeatures(target);

		try {
			return guidslModel.checkImplies(guidslSourceFeatures, guidslTargetFeatures);
		} catch (TimeoutException e) {
			e.printStackTrace();
			// in case of a timeout assume everything is fine and the
			// implication is true. idea is not to report false positive
			return true;
		}
	}

	@Override
	public boolean areMutualExclusive(IFeatureModel featureModel, Set<IFeature> context, List<Set<IFeature>> featureSets) {
		// ignore empty feature models
		if (featureModel instanceof EmptyFeatureModel)
			return true;
		assert featureModel instanceof GuidslFeatureModelWrapper;
		
		List<Set<Feature>> guidslFeatureSets = new LinkedList<Set<Feature>>();
		for (Set<IFeature> features : featureSets) {
			guidslFeatureSets.add(convertToGuidslFeatures(features));
		}

		FeatureModel guidslModel = ((GuidslFeatureModelWrapper) featureModel).getInternalModel();
		try {
			return guidslModel.areMutualExclusive(convertToGuidslFeatures(context), guidslFeatureSets);
		} catch (TimeoutException e) {
			e.printStackTrace();
			// in case of a timeout assume everything is fine and the
			// formula is true. idea is not to report false positive
			return true;
		}
	}
	
	@Override
	public boolean mayBeMissing(IFeatureModel featureModel, Set<IFeature> context, List<Set<IFeature>> featureSets) {
		// ignore empty feature models
		if (featureModel instanceof EmptyFeatureModel)
			return true;
		assert featureModel instanceof GuidslFeatureModelWrapper;
		
		List<Set<Feature>> guidslFeatureSets = new LinkedList<Set<Feature>>();
		for (Set<IFeature> features : featureSets) {
			guidslFeatureSets.add(convertToGuidslFeatures(features));
		}

		FeatureModel guidslModel = ((GuidslFeatureModelWrapper) featureModel).getInternalModel();
		try {
			return guidslModel.mayBeMissing(convertToGuidslFeatures(context), guidslFeatureSets);
		} catch (TimeoutException e) {
			e.printStackTrace();
			// in case of a timeout assume everything is fine and the
			// result is false. idea is not to report false positive
			return false;
		}
	}
	
	@Override
	protected boolean calcExists(IFeatureModel featureModel, Set<IFeature> features) {
		// ignore empty feature models
		if (featureModel instanceof EmptyFeatureModel)
			return true;
		assert featureModel instanceof GuidslFeatureModelWrapper;
		
		FeatureModel guidslModel = ((GuidslFeatureModelWrapper) featureModel).getInternalModel();
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
