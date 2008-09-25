package coloredide.utils;

import java.util.List;
import java.util.Set;

import org.eclipse.jface.wizard.Wizard;

import coloredide.features.IFeature;

/**
 * wizard to select a subset from a given list of features. no validation
 * against a feature model provided
 * 
 * @author ckaestne
 * 
 */
public class SelectFeatureSetWizard extends Wizard {
	public SelectFeatureSetPage p;

	private Set<IFeature> sf;
	private Set<IFeature> nsf;

	public SelectFeatureSetWizard(List<IFeature> featureList,
			Set<IFeature> initialSelection) {
		p = new SelectFeatureSetPage("", featureList);
		p.setInitialSelection(initialSelection);
	}

	// ColorHelper.sortFeatures(featureModel.getVisibleFeatures())
//	public SelectFeatureWizard(IProject project, Set<IFeature> initialSelection)
//			throws FeatureModelNotFoundException {
//		this(project, initialSelection, FeatureModelManager.getInstance()
//				.getFeatureModel(project));
//	}

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
