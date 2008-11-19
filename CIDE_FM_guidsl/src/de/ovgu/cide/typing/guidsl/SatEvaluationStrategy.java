package de.ovgu.cide.typing.guidsl;

import java.util.HashSet;
import java.util.Set;

import org.sat4j.specs.TimeoutException;

import de.ovgu.cide.features.IFeature;
import de.ovgu.cide.features.IFeatureModel;
import de.ovgu.cide.fm.guidsl.EmptyFeatureModel;
import de.ovgu.cide.fm.guidsl.FeatureAdapter;
import de.ovgu.cide.fm.guidsl.GuidslFeatureModelWrapper;
import de.ovgu.cide.typing.model.AbstractCachingEvaluationStrategy;
import de.ovgu.cide.typing.model.IEvaluationStrategy;
import featureide.fm.core.model.Feature;
import featureide.fm.core.model.FeatureModel;

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
	protected boolean calcImplies(IFeatureModel featureModel,
			Set<IFeature> source, Set<IFeature> target) {
		// ignore empty feature models
		if (featureModel instanceof EmptyFeatureModel)
			return true;
		assert featureModel instanceof GuidslFeatureModelWrapper;

		FeatureModel guidslModel = ((GuidslFeatureModelWrapper) featureModel)
				.getInternalModel();

		Set<Feature> guidslSourceFeatures = new HashSet<Feature>(source.size());
		for (IFeature sourceFeature : source) {
			assert sourceFeature instanceof FeatureAdapter;
			guidslSourceFeatures.add(((FeatureAdapter) sourceFeature)
					.getInternalFeature());
		}
		Set<Feature> guidslTargetFeatures = new HashSet<Feature>(source.size());
		for (IFeature targetFeature : target) {
			assert targetFeature instanceof FeatureAdapter;
			guidslTargetFeatures.add(((FeatureAdapter) targetFeature)
					.getInternalFeature());
		}

		try {
			return guidslModel.checkImplies(guidslSourceFeatures,
					guidslTargetFeatures);
		} catch (TimeoutException e) {
			e.printStackTrace();
			// in case of a timeout assume everything is fine and the
			// implication is true. idea is not to report false positive
			return true;
		}
	}
}
