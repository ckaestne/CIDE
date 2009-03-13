package de.ovgu.cide.typing.internal.manager;

import java.util.List;
import java.util.Set;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IConfigurationElement;

import de.ovgu.cide.features.IFeature;
import de.ovgu.cide.features.IFeatureModel;
import de.ovgu.cide.typing.model.IEvaluationStrategy;

public class EvaluationStrategyProxy implements IEvaluationStrategy {

	private final IConfigurationElement configElement;
	private final String featureModelProviderId;

	public EvaluationStrategyProxy(IConfigurationElement configurationElement) {
		this.configElement = configurationElement;
		name = configElement.getAttribute("name");
		id = configElement.getAttribute("id");
		featureModelProviderId = configElement
				.getAttribute("featureModelProvider");
	}

	private final String name;
	private final String id;
	private IEvaluationStrategy target = null;

	private void loadTarget() {
		try {
			target = (IEvaluationStrategy) configElement
					.createExecutableExtension("strategy");
		} catch (CoreException e) {
			e.printStackTrace();
		}
	}

	@Override
	public String toString() {
		return "Evaluation Strategy Extension: " + name + " (" + id + ")";
	}

	public boolean equal(IFeatureModel featureModel, Set<IFeature> source,
			Set<IFeature> targete) {
		if (target == null)
			loadTarget();
		return target.equal(featureModel, source, targete);
	}

	public boolean implies(IFeatureModel featureModel, Set<IFeature> source,
			Set<IFeature> targete) {
		if (target == null)
			loadTarget();
		return target.implies(featureModel, source, targete);
	}
	
	@Override
	public boolean areMutualExclusive(IFeatureModel featureModel, Set<IFeature> context, List<Set<IFeature>> featureSets) {
		if (target == null)
			loadTarget();
		return target.areMutualExclusive(featureModel, context, featureSets);
	}
	
	@Override
	public boolean mayBeMissing(IFeatureModel featureModel, Set<IFeature> context, List<Set<IFeature>> featureSets) {
		if (target == null)
			loadTarget();
		return target.mayBeMissing(featureModel, context, featureSets);
	}
	
	@Override
	public boolean exists(IFeatureModel featureModel, Set<IFeature> features) {
		if (target == null)
			loadTarget();
		return target.exists(featureModel, features);
	}

	public boolean isResponsible(String featureModelId) {
		return featureModelProviderId.equals(featureModelId);
	}

	public void clearCache(IFeatureModel featureModel) {
		if (target == null)
			loadTarget();
		target.clearCache(featureModel);
	}
}
