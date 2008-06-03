package coloredide.utils;

import java.util.Set;

import org.eclipse.core.resources.IProject;
import org.eclipse.jface.wizard.Wizard;

import coloredide.features.FeatureModelManager;
import coloredide.features.FeatureModelNotFoundException;
import coloredide.features.IFeature;
import coloredide.features.IFeatureModel;

public class SelectFeatureWizard extends Wizard {
	public WizardPageSelectFeatures p;

	private Set<IFeature> sf;
	private Set<IFeature> nsf;

	public SelectFeatureWizard(IProject project,
			Set<IFeature> initialSelection, IFeatureModel featureModel) {
		p = new WizardPageSelectFeatures("", project, featureModel);
		p.setInitialSelection(initialSelection);
	}

	public SelectFeatureWizard(IProject project, Set<IFeature> initialSelection)
			throws FeatureModelNotFoundException {
		this(project, initialSelection, FeatureModelManager.getInstance()
				.getFeatureModel(project));
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

	public Set<IFeature> getSelectedFeatures() {
		return sf;
	}

	public Set<IFeature> getNotSelectedFeatures() {
		return nsf;
	}

}
