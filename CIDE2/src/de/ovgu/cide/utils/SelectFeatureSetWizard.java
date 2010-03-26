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

package de.ovgu.cide.utils;

import java.util.List;
import java.util.Set;

import org.eclipse.jface.wizard.Wizard;

import de.ovgu.cide.features.IFeature;

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
