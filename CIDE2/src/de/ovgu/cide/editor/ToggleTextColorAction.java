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

import org.eclipse.jface.action.Action;

import de.ovgu.cide.features.IFeature;

public class ToggleTextColorAction extends Action {

	static IFeature lastFeature = null;

	private SelectionActionsContext context;
	protected IFeature feature;

	public ToggleTextColorAction(SelectionActionsContext context,
			IFeature feature) {
		this.context = context;
		this.feature = feature;

		if (feature != null)
			this.setText(feature.getName());

		this.setEnabled(context.anyNodesSelected());
		this.setChecked(context.nodesHaveColor(feature));
	}

	@Override
	public void run() {
		context.getSourceFile().getColorManager().toggleColor(
				context.getSelectedNodes(), feature,
				!context.nodesHaveColor(feature));
		lastFeature = feature;
	}
}
