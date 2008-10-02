package de.ovgu.cide.fm.purevariants;

import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.eclipse.core.resources.IProject;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.swt.widgets.Shell;

import com.ps.consul.eclipse.ui.mapping.Mapping;
import com.ps.consul.eclipse.ui.mapping.Rule;
import com.ps.consul.eclipse.ui.mapping.Util;

import coloredide.configuration.AbstractConfigurationPage;
import coloredide.features.IFeature;
import coloredide.features.IFeatureModel;

public class PVFeatureModel implements IFeatureModel {

	private Mapping mapping;
	private IProject project;

	public PVFeatureModel(Mapping mapping, IProject project) {
		this.mapping = mapping;
		this.project = project;
	}

	public AbstractConfigurationPage getConfigurationPage(String pageName) {
		// TODO Auto-generated method stub
		return null;
	}

	public Set<IFeature> getFeatures() {
		return adaptRules(mapping.getRules());
	}

	public IProject getProject() {
		return project;
	}

	public Set<IFeature> getVisibleFeatures() {
		try {
			return adaptRules(mapping.getRulesOfVariant());
		} catch (CoreException e) {
			e.printStackTrace();
			return Collections.EMPTY_SET;
		}
	}

	private Set<IFeature> adaptRules(List<Rule> rules) {
		HashSet<IFeature> result = new HashSet<IFeature>();
		for (Rule rule : rules)
			result.add(new RuleAdapter(rule));
		return result;
	}

	public boolean isValidSelection(Set<IFeature> selection) {
		return true;// TODO
	}

	public IFeature createNewFeature() {

		try {
			Rule rule = Util.makeNewRule(new Shell(), mapping, null, null);
			return new RuleAdapter(rule);
		} catch (CoreException e) {
			e.printStackTrace();
			return null;
		}

	}

	public Mapping getMapping() {
		return mapping;
	}

}