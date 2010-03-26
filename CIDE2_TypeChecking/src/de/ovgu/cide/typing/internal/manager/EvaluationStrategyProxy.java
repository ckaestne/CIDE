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

	public boolean equal(IFeatureModel featureModel, Set<IFeature> context,
			Set<IFeature> source, Set<IFeature> targete) {
		if (target == null)
			loadTarget();
		return target.equal(featureModel, context, source, targete);
	}

	public boolean implies(IFeatureModel featureModel, Set<IFeature> source,
			Set<IFeature> targete) {
		if (target == null)
			loadTarget();
		return target.implies(featureModel, source, targete);
	}

	public boolean areMutualExclusive(IFeatureModel featureModel,
			Set<IFeature> context, List<Set<IFeature>> featureSets) {
		if (target == null)
			loadTarget();
		return target.areMutualExclusive(featureModel, context, featureSets);
	}

	public boolean mayBeMissing(IFeatureModel featureModel,
			Set<IFeature> context, List<Set<IFeature>> featureSets) {
		if (target == null)
			loadTarget();
		return target.mayBeMissing(featureModel, context, featureSets);
	}

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
