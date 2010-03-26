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

package de.ovgu.cide.editor;

import org.eclipse.jface.dialogs.MessageDialog;

import de.ovgu.cide.features.IFeatureModel;

/**
 * this action calls the feature model to create a new feature. if supported by
 * the feature model, the code is directly annotated with the new feature
 * 
 * @author cKaestner
 * 
 */
public class NewFeatureAction extends ToggleTextColorAction {

	private IFeatureModel featureModel;

	NewFeatureAction(SelectionActionsContext context, IFeatureModel featureModel) {
		super(context, null);
		this.featureModel = featureModel;

		this.setText("New Feature/Rule...");
		this.setChecked(false);
	}

	@Override
	public void run() {
		try {
			this.feature = featureModel.createNewFeature();
			if (feature != null)
				super.run();
		} catch (UnsupportedOperationException e) {
			MessageDialog.openError(null, "New feature",
					"The current feature model does not support this action. "
							+ "Edit the feature model manually instead.");
		}
	}

}
