package coloredide.utils;

import java.util.Set;

import org.eclipse.core.resources.IProject;
import org.eclipse.jface.wizard.Wizard;

import coloredide.features.Feature;

public class SelectFeatureWizard extends Wizard {
	public WizardPageSelectFeatures p;

	private Set<Feature> sf;
	private Set<Feature> nsf;

	public SelectFeatureWizard(IProject project, Set<Feature> initialSelection) {
		p = new WizardPageSelectFeatures("", project);
		p.setInitialSelection(initialSelection);
	}

	public void addPages() {
		this.addPage(p);
		super.addPages();
	}

	public boolean performFinish() {
		this.sf = p.getSelectedFeatures();
		this.nsf = p.getNotSelectedFeatures();
		return true;
	}

	public Set<Feature> getSelectedFeatures() {
		return sf;
	}

	public Set<Feature> getNotSelectedFeatures() {
		return nsf;
	}

}
