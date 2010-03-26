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

import org.eclipse.core.resources.IProject;
import org.eclipse.core.runtime.IConfigurationElement;

import de.ovgu.cide.ExtensionPointManager;
import de.ovgu.cide.features.FeatureModelManager;
import de.ovgu.cide.features.FeatureModelNotFoundException;
import de.ovgu.cide.features.FeatureModelProviderProxy;
import de.ovgu.cide.typing.CIDETypingPlugin;
import de.ovgu.cide.typing.model.IEvaluationStrategy;

public class EvaluationStrategyManager extends
		ExtensionPointManager<EvaluationStrategyProxy> {

	private static EvaluationStrategyManager instance;

	protected EvaluationStrategyManager() {
		super(CIDETypingPlugin.PLUGIN_ID, "evaluationStrategy");
	}

	public static EvaluationStrategyManager getInstance() {
		if (instance == null)
			instance = new EvaluationStrategyManager();
		return instance;
	}

	/**
	 * returns the current evaluation strategy for the selected project
	 * (determined by the feature model in this project).
	 * 
	 * @param project
	 * @return
	 * @throws FeatureModelNotFoundException
	 *             if there is no feature model in this project or there is no
	 *             according evaluation strategy by this feature model
	 */
	public IEvaluationStrategy getEvaluationStrategy(IProject project)
			throws FeatureModelNotFoundException {
		// just get the feature model to make sure there is one
		FeatureModelManager.getInstance().getFeatureModel(project);
		FeatureModelProviderProxy featureModelProvider = FeatureModelManager
				.getInstance().getActiveFeatureModelProvider();
		assert featureModelProvider != null;
		String featureModelProviderId = featureModelProvider.getId();

		for (EvaluationStrategyProxy strategy : getProviders()) {
			if (strategy.isResponsible(featureModelProviderId))
				return strategy;
		}

		throw new FeatureModelNotFoundException(
				"There is no evaluation strategy installed for feature model "
						+ featureModelProviderId);
	}

	@Override
	protected EvaluationStrategyProxy parseExtension(
			IConfigurationElement configurationElement) {
		if (!configurationElement.getName().equals("evaluationStrategy"))
			return null;
		return new EvaluationStrategyProxy(configurationElement);
	}

}
