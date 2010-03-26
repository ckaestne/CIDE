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

package de.ovgu.cide.fm.purevariants;

import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.eclipse.core.resources.IProject;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.swt.widgets.Shell;

import com.ps.consul.eclipse.ui.mapping.Mapping;
import com.ps.consul.eclipse.ui.mapping.Rule;
import com.ps.consul.eclipse.ui.mapping.Util;

import de.ovgu.cide.configuration.AbstractConfigurationPage;
import de.ovgu.cide.features.IFeature;
import de.ovgu.cide.features.IFeatureModel;

public class PVFeatureModel implements IFeatureModel {

	private Mapping mapping;
	private IProject project;

	public PVFeatureModel(Mapping mapping, IProject project) {
		this.mapping = mapping;
		this.project = project;
	}

	public AbstractConfigurationPage getConfigurationPage(String pageName) {
		return new NoConfigurationPage(pageName, this);
	}

	public Set<IFeature> getFeatures() {
		return adaptRules(mapping.getRules());
	}

	public IProject getProject() {
		return project;
	}

	public Set<IFeature> getVisibleFeatures() {
		Set<IFeature> result = getFeatures();
		for (Iterator<IFeature> iterator = result.iterator(); iterator
				.hasNext();)
			if (!iterator.next().isVisible())
				iterator.remove();
		return result;
	}

	private Set<IFeature> adaptRules(List<Rule> rules) {
		HashSet<IFeature> result = new HashSet<IFeature>();
		for (Rule rule : rules)
			result.add(new RuleAdapter(rule, mapping));
		return result;
	}

	public boolean isValidSelection(Set<IFeature> selection) {
		return true;// TODO
	}

	public IFeature createNewFeature() {

		try {
			Rule rule = Util.makeNewRule(new Shell(), mapping, null, null);
			return new RuleAdapter(rule, mapping);
		} catch (CoreException e) {
			e.printStackTrace();
			return null;
		}

	}

	public Mapping getMapping() {
		return mapping;
	}

}
