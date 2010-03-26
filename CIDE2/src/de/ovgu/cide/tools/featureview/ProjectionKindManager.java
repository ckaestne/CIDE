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

package de.ovgu.cide.tools.featureview;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.jface.action.Action;

import de.ovgu.cide.CIDECorePlugin;
import de.ovgu.cide.ColorListChangedEvent;

public class ProjectionKindManager {
	private ProjectionKindManager() {
	}

	private static final ProjectionKindManager instance = new ProjectionKindManager();

	public static ProjectionKindManager getInstance() {
		return instance;
	}

	public static enum ProjectionKind {
		FEATURE, VARIANT
	};

	private ProjectionKind currentProjectionKind = ProjectionKind.FEATURE;

	private void setProjectionKind(ProjectionKind kind) {
		currentProjectionKind = kind;
		CIDECorePlugin.getDefault().notifyListeners(
				new ColorListChangedEvent(this, null, kind));
	}

	public ProjectionKind getProjectionKind() {
		return currentProjectionKind;
	}

	// Style = radio button
	public class ChangeProjectionKind extends Action {
		private ProjectionKind kind;

		public ChangeProjectionKind(ProjectionKind kind) {
			super(kind == ProjectionKind.FEATURE ? "View on a feature"
					: "View on a variant", AS_RADIO_BUTTON);
			this.kind = kind;
			setChecked(currentProjectionKind == kind);
		}

		@Override
		public void run() {
			setProjectionKind(kind);
		}
	}

	public List<Action> getActions() {
		ArrayList<Action> list = new ArrayList<Action>();
//		list.add(new ChangeProjectionKind(ProjectionKind.NONE));
		list.add(new ChangeProjectionKind(ProjectionKind.FEATURE));
		list.add(new ChangeProjectionKind(ProjectionKind.VARIANT));
		return list;
	}

	public static boolean isVariantView() {
		return getInstance().getProjectionKind()==ProjectionKind.VARIANT;
	}

}
